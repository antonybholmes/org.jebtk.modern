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

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import javax.swing.Box;

import org.jebtk.core.collections.CollectionUtils;
import org.jebtk.core.io.PathUtils;
import org.jebtk.modern.AssetService;
import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.button.ModernButton;
import org.jebtk.modern.button.ModernOutlineButton;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.graphics.icons.FolderVectorIcon;
import org.jebtk.modern.panel.HBox;
import org.jebtk.modern.text.ModernTextBorderPanel;
import org.jebtk.modern.text.ModernTextField;
import org.jebtk.modern.window.ModernWindow;

/**
 * The Class ChooseFilePanel.
 */
public class ChooseFilePanel extends ModernComponent {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  private static List<GuiFileExtFilter> EMPTY_FILTERS = Collections.emptyList();

  /** The m file field. */
  private ModernTextField mFileField = new ModernTextField();

  /** The m choose button. */
  private ModernButton mChooseButton = new ModernOutlineButton(
      AssetService.getInstance().loadIcon(FolderVectorIcon.class, 16));

  // private ModernButton mDeleteButton =
  // new ModernButton(UIService.getInstance().loadIcon(DeleteVectorIcon.class,
  // 16));

  /** The m parent. */
  private ModernWindow mParent;

  /** The m dir mode. */
  private boolean mDirMode;

  /** The m file. */
  private Path mFile;

  /** The m filters. */
  private List<GuiFileExtFilter> mFilters;

  /**
   * Instantiates a new choose file panel.
   *
   * @param parent  the parent
   * @param filters the filters
   */
  public ChooseFilePanel(ModernWindow parent, GuiFileExtFilter filter, GuiFileExtFilter... filters) {
    this(parent, false, filter, filters);
  }

  public ChooseFilePanel(ModernWindow parent, boolean dirMode, GuiFileExtFilter filter, GuiFileExtFilter... filters) {
    this(parent, dirMode, CollectionUtils.toList(filter, filters));
  }

  public ChooseFilePanel(ModernWindow parent, boolean dirMode) {
    this(parent, dirMode, EMPTY_FILTERS);
  }

  /**
   * Instantiates a new choose file panel.
   *
   * @param parent  the parent
   * @param dirMode the dir mode
   * @param filters the filters
   */
  public ChooseFilePanel(ModernWindow parent, boolean dirMode, List<GuiFileExtFilter> filters) {
    mParent = parent;
    mDirMode = dirMode;
    mFilters = filters;

    setBody(new ModernTextBorderPanel(mFileField));

    Box box = HBox.create();
    box.add(UI.createHGap(5));
    box.add(mChooseButton);
    // box.add(UI.createHGap(5));
    // box.add(mDeleteButton);

    setRight(box);

    mChooseButton.addClickListener(new ModernClickListener() {

      @Override
      public void clicked(ModernClickEvent e) {
        Path pwd = RecentFilesService.getInstance().getPwd();

        Path file = null;

        if (mDirMode) {
          file = FileDialog.open(mParent).dirs().getFile(pwd);
        } else {
          file = FileDialog.open(mParent).filter(mFilters).multiSelect(false).getFile(pwd);
        }

        if (file != null) {
          setFile(file);

          RecentFilesService.getInstance().setPwd(file.getParent());
        }
      }
    });

    /*
     * mDeleteButton.addClickListener(new ModernClickListener() {
     * 
     * @Override public void clicked(ModernClickEvent e) { mFile = null;
     * mFileField.setText(TextUtils.EMPTY_STRING); }});
     */

    mFileField.setEditable(false);

    UI.setSize(this, ModernWidget.MAX_SIZE);
  }

  /**
   * Instantiates a new choose file panel.
   *
   * @param parent  the parent
   * @param dirMode the dir mode
   * @param file    the file
   * @param filters the filters
   */
  public ChooseFilePanel(ModernWindow parent, boolean dirMode, Path file, GuiFileExtFilter filter,
      GuiFileExtFilter... filters) {
    this(parent, dirMode, filter, filters);

    setFile(file);
  }

  public ChooseFilePanel(ModernWindow parent, boolean dirMode, Path file, List<GuiFileExtFilter> filters) {
    this(parent, dirMode, filters);

    setFile(file);
  }

  /**
   * Gets the file.
   *
   * @return the file
   */
  public Path getFile() {
    return mFile;
  }

  /**
   * Sets the file.
   *
   * @param file the new file
   */
  public void setFile(Path file) {
    if (file != null) {
      mFile = file;

      mFileField.setText(PathUtils.toString(file));
    }
  }
}
