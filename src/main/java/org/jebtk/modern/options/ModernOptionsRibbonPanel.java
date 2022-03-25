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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.path.Path;
import org.jebtk.core.settings.SettingsService;
import org.jebtk.core.text.TextUtils;
import org.jebtk.core.tree.TreeNode;
import org.jebtk.core.tree.TreePath;
import org.jebtk.modern.BorderService;
import org.jebtk.modern.event.ModernSelectionListener;
import org.jebtk.modern.help.GuiAppInfo;
import org.jebtk.modern.ribbon.RibbonMenuPanel;
import org.jebtk.modern.scrollpane.ModernScrollPane;
import org.jebtk.modern.scrollpane.ScrollBarPolicy;
import org.jebtk.modern.splitpane.ModernHSplitPaneLine;
import org.jebtk.modern.splitpane.SplitPane;
import org.jebtk.modern.table.ModernRowTable;
import org.jebtk.modern.text.ModernAutoSizeLabel;
import org.jebtk.modern.tree.PathTree;

// TODO: Auto-generated Javadoc
/**
 * Display the program settings tree inline within the Ribbon menu.
 * 
 * @author Antony Holmes
 *
 */
public class ModernOptionsRibbonPanel extends RibbonMenuPanel implements ModernSelectionListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member table.
   */
  private ModernRowTable mTable;

  /**
   * The tree label.
   */
  private ModernAutoSizeLabel mTreeLabel = new ModernAutoSizeLabel("");

  /**
   * The split pane.
   */
  private SplitPane mSplitPane;

  /**
   * The member tree.
   */
  private PathTree mTree;

  /**
   * Instantiates a new modern options ribbon panel.
   *
   * @param productDetails the product details
   */
  public ModernOptionsRibbonPanel(GuiAppInfo productDetails) {
    super(productDetails.getName() + " Settings");

    setup(productDetails.getName() + " Settings");
  }

  /**
   * Sets the up.
   *
   * @param title the new up
   */
  private void setup(String title) {
    createUi(title);

    refresh();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ribbon.RibbonMenuPanel#refresh()
   */
  @Override
  public void refresh() {
    try {
      createTree();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Creates the ui.
   *
   * @param title the title
   */
  private final void createUi(String title) {
    // ModernLabel heading = new ModernTitleLabel(title);
    // heading.setBorder(BorderService.getInstance().createBottomBorder(40));
    // setHeader(heading);

    mTreeLabel.setBorder(BorderService.getInstance().createBorder(20));

    setFooter(mTreeLabel);
  }

  /**
   * Creates the tree.
   *
   * @throws IOException Signals that an I/O exception has occurred.
   */
  private void createTree() throws IOException {
    // Copy the existings settings so we can modify

    List<Path> paths = new ArrayList<Path>();

    for (Path path : SettingsService.getInstance()) {
      paths.add(path);
    }

    mTree = new PathTree(paths, false);

    mTree.addSelectionListener(this);
    mTree.setBorder(RIGHT_BORDER);

    ModernScrollPane scrollPane = new ModernScrollPane(mTree).setHorizontalScrollBarPolicy(ScrollBarPolicy.NEVER)
        .setVerticalScrollBarPolicy(ScrollBarPolicy.AUTO_SHOW);

    mTable = new ModernRowTable();

    mSplitPane = new ModernHSplitPaneLine().setDividerWidth(10);

    mSplitPane.addComponent(scrollPane, 0.3);
    mSplitPane.addComponent(new ModernScrollPane(mTable), -1);
    // splitPane.setDividerLocation(200);

    setBody(mSplitPane);

    mTree.selectNode(0);
  }

  /**
   * Sets the table model.
   *
   * @param node the new table model
   */
  private void setTableModel(TreeNode<List<Path>> node) {
    ModernSettingsTableModel tableModel = new ModernSettingsTableModel(node);

    mTable.setModel(tableModel);
    // table.getColumnModel().setCellEditor(1, new
    // ModernTableTextCellEditor(true));
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

    // System.err.println("tp " + node.getName() + " " + node.getParent() + " "
    // +
    // treePath);

    Path path = mTree.convertToPath(treePath);

    mTreeLabel.setText(path.toString());

    if (mTree.getSelectedNode().getValue() != null) {
      setTableModel(node);
    } else {
      setTableModel(new TreeNode<List<Path>>(TextUtils.EMPTY_STRING));
    }
  }
}
