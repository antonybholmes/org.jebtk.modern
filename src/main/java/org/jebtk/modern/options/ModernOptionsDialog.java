/**
 * Copyright (C) 2016, Antony Holmes
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *  1. Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *  3. Neither the name of copyright holder nor the names of its contributors 
 *     may be used to endorse or promote products derived from this software 
 *     without specific prior written permission. 
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 */
package org.jebtk.modern.options;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.path.Path;
import org.jebtk.core.settings.SettingsService;
import org.jebtk.core.tree.TreeNode;
import org.jebtk.core.tree.TreePath;
import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.UI;
import org.jebtk.modern.dataview.ModernDataGridCellEditor;
import org.jebtk.modern.dialog.ModernDialogBorderPanel;
import org.jebtk.modern.dialog.ModernDialogTaskWindow;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernSelectionListener;
import org.jebtk.modern.help.GuiAppInfo;
import org.jebtk.modern.panel.ModernPanel;
import org.jebtk.modern.scrollpane.ModernScrollPane;
import org.jebtk.modern.splitpane.HSplitPane;
import org.jebtk.modern.splitpane.ModernHSplitPaneLine;
import org.jebtk.modern.table.ModernRowTable;
import org.jebtk.modern.text.ModernAutoSizeLabel;
import org.jebtk.modern.tree.ModernTree;
import org.jebtk.modern.tree.PathTree;
import org.jebtk.modern.window.ModernWindow;

/**
 * Allow users to look at and modify program settings.
 * 
 * @author Antony Holmes
 *
 */
public class ModernOptionsDialog extends ModernDialogTaskWindow implements ModernSelectionListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member table.
   */
  private ModernRowTable mTable;

  /**
   * The member tree.
   */
  private ModernTree<List<Path>> mTree = new ModernTree<>();

  /**
   * The tree label.
   */
  private ModernAutoSizeLabel treeLabel = new ModernAutoSizeLabel("");

  /**
   * Instantiates a new modern options dialog.
   *
   * @param parent  the parent
   * @param details the details
   */
  public ModernOptionsDialog(ModernWindow parent, GuiAppInfo details) {
    super(parent);

    setTitle(details.getName() + " Options");

    // setResizable(false);

    setSize(800, 600);

    try {
      setup();
    } catch (IOException e) {
      e.printStackTrace();
    }

    createUi();
  }

  /**
   * Setup.
   *
   * @throws IOException Signals that an I/O exception has occurred.
   */
  private void setup() throws IOException {
    createUi();

    createTree();

    mTree.selectNode(0);
  }

  /**
   * Creates the ui.
   */
  private void createUi() {
    ModernComponent content = new ModernComponent();

    ModernScrollPane scrollPane1 = new ModernScrollPane(mTree);

    mTable = new ModernRowTable();
    ModernScrollPane scrollPane2 = new ModernScrollPane(mTable);

    HSplitPane splitPane = new ModernHSplitPaneLine();
    splitPane.addComponent(scrollPane1, 0.2);
    splitPane.addComponent(scrollPane2, 0.8);

    // splitPane.setDividerLocation(200);

    content.add(new ModernDialogBorderPanel(splitPane), BorderLayout.CENTER);

    setBody(content);

    getButtonBar().addLeft(ModernPanel.createHGap());
    getButtonBar().addLeft(treeLabel);

  }

  /**
   * Creates the tree.
   *
   * @throws IOException Signals that an I/O exception has occurred.
   */
  private void createTree() throws IOException {
    List<Path> paths = new ArrayList<>();

    for (Path path : SettingsService.getInstance()) {
      paths.add(path);
    }

    mTree = new PathTree(paths, false);

    mTree.addSelectionListener(this);

    mTree.selectNode(0);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  @Override
  public final void clicked(ModernClickEvent e) {
    if (e.getMessage().equals(UI.BUTTON_OK)) {
      save();
    }

    super.clicked(e);
  }

  /**
   * Save.
   */
  private void save() {
    SettingsService.getInstance().save();
  }

  /**
   * Sets the table model.
   *
   * @param node the new table model
   */
  private void setTableModel(TreeNode<List<Path>> node) {
    ModernSettingsTableModel tableModel = new ModernSettingsTableModel(node);

    mTable.setModel(tableModel);
    mTable.getEditorModel().setCol(1, new ModernDataGridCellEditor(true));
    mTable.getColumnModel().setWidth(0, 200);
    mTable.getColumnModel().setWidth(1, 200);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.event.ModernSelectionListener#selectionChanged(org.
   * abh. lib.event.ChangeEvent)
   */
  @Override
  public void selectionAdded(ChangeEvent e) {
    selectionRemoved(e);
  }

  @Override
  public void selectionRemoved(ChangeEvent e) {
    if (mTree.getSelectedNode() == null) {
      return;
    }

    TreeNode<List<Path>> node = mTree.getSelectedNode();

    TreePath treePath = node.getPath();

    Path path = mTree.convertToPath(treePath);

    treeLabel.setText(path.toString());

    if (mTree.getSelectedNode().getValue() != null) {
      setTableModel(node);
    } else {
      setTableModel(new TreeNode<>(""));
    }
  }

  /**
   * Sets the visible.
   *
   * @param parent  the parent
   * @param details the details
   */
  public static void setVisible(ModernWindow parent, GuiAppInfo details) {
    JDialog dialog = new ModernOptionsDialog(parent, details);

    UI.centerWindowToScreen(dialog);

    dialog.setVisible(true);
  }
}
