/**
 * Copyright 2017 Antony Holmes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jebtk.modern.io;

import java.awt.Component;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;

import org.jebtk.core.collections.CollectionUtils;
import org.jebtk.modern.AssetService;
import org.jebtk.modern.ComponentTable;
import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.button.ModernButton;
import org.jebtk.modern.button.ModernOutlineButton;
import org.jebtk.modern.dialog.ModernDialogStatus;
import org.jebtk.modern.dialog.ModernMessageDialog;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.graphics.icons.DeleteVectorIcon;
import org.jebtk.modern.graphics.icons.PlusVectorIcon;
import org.jebtk.modern.panel.HBox;
import org.jebtk.modern.window.ModernWindow;

/**
 * The Class ChooseFilesPanel.
 */
public class ChooseFilesPanel extends ModernComponent {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The m add button. */
  private ModernButton mAddButton = new ModernOutlineButton("Add",
      AssetService.getInstance().loadIcon(PlusVectorIcon.class, 16));

  /** The m delete button. */
  private ModernButton mDeleteButton = new ModernOutlineButton("Remove",
      AssetService.getInstance().loadIcon(DeleteVectorIcon.class, 16));

  /** The m filters. */
  private List<GuiFileExtFilter> mFilters;

  /** The m component table. */
  private ComponentTable mComponentTable = new ComponentTable();

  /** The m parent. */
  private ModernWindow mParent = null;

  /** The m dir mode. */
  private boolean mDirMode = false;

  /**
   * Instantiates a new choose files panel.
   *
   * @param parent  the parent
   * @param filters the filters
   */
  public ChooseFilesPanel(ModernWindow parent, GuiFileExtFilter filter, GuiFileExtFilter... filters) {
    this(parent, false, filter, filters);
  }

  /**
   * Instantiates a new choose files panel.
   *
   * @param parent  the parent
   * @param dirMode the dir mode
   * @param filters the filters
   */
  public ChooseFilesPanel(ModernWindow parent, boolean dirMode, GuiFileExtFilter filter, GuiFileExtFilter... filters) {
    mParent = parent;
    mDirMode = dirMode;
    mFilters = CollectionUtils.toList(filter, filters);

    setBody(mComponentTable);

    Box box = HBox.create();
    box.add(mAddButton);
    box.add(UI.createHGap(5));
    box.add(mDeleteButton);
    box.setBorder(TOP_BORDER);

    setFooter(box);

    mAddButton.addClickListener(new ModernClickListener() {

      @Override
      public void clicked(ModernClickEvent e) {
        Path pwd = RecentFilesService.getInstance().getPwd();

        List<Path> files;

        if (mDirMode) {
          files = FileDialog.open(mParent).dirs().getFiles(pwd);
        } else {
          files = FileDialog.open(mParent).filter(mFilters).multiSelect(true).getFiles(pwd);
        }

        if (files.size() > 0) {
          for (Path file : files) {
            addFile(file);
          }

          RecentFilesService.getInstance().setPwd(files.get(0).getParent());
        }
      }
    });

    mDeleteButton.addClickListener(new ModernClickListener() {

      @Override
      public void clicked(ModernClickEvent e) {
        delete();
      }
    });

    UI.setSize(this, ModernWidget.MAX_SIZE);
  }

  /**
   * Add a file to the file chooser.
   *
   * @param file the file
   */
  public void addFile(Path file) {
    mComponentTable.add(new ChooseFilePanel(mParent, mDirMode, file, mFilters));
  }

  /**
   * Delete.
   */
  private void delete() {
    if (ModernMessageDialog.createOkCancelWarningDialog(mParent,
        "Are you sure you want to remove the selected files?") == ModernDialogStatus.OK) {
      mComponentTable.delete();
    }
  }

  /**
   * Gets the files.
   *
   * @return the files
   */
  public List<Path> getFiles() {
    List<Path> ret = new ArrayList<Path>();

    for (Component c : mComponentTable) {
      Path file = ((ChooseFilePanel) c).getFile();

      if (file != null) {
        ret.add(file);
      }
    }

    return ret;
  }
}
