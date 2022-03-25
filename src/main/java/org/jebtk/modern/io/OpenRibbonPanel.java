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
package org.jebtk.modern.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jebtk.core.collections.UniqueArrayList;
import org.jebtk.core.io.FileUtils;
import org.jebtk.core.text.TextUtils;
import org.jebtk.modern.AssetService;
import org.jebtk.modern.UI;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.graphics.icons.ClockVectorIcon;
import org.jebtk.modern.graphics.icons.ComputerVectorIcon;
import org.jebtk.modern.graphics.icons.FileVectorIcon;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.graphics.icons.OpenFolderVectorIcon;
import org.jebtk.modern.menu.ModernMenuBox;
import org.jebtk.modern.ribbon.RibbonMenuPanel;
import org.jebtk.modern.ribbon.RibbonVertTabsPanel;
import org.jebtk.modern.scrollpane.ModernScrollPane;
import org.jebtk.modern.scrollpane.ScrollBarPolicy;
import org.jebtk.modern.tabs.TabEvent;
import org.jebtk.modern.tabs.TabEventAdapter;
import org.jebtk.modern.tabs.TabsModel;

// TODO: Auto-generated Javadoc
/**
 * The class OpenRibbonPanel.
 */
public class OpenRibbonPanel extends RibbonMenuPanel {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant FILE_SELECTED.
   */
  public static final String FILE_SELECTED = "open_panel_file_selected";

  /**
   * The constant DIRECTORY_SELECTED.
   */
  public static final String DIRECTORY_SELECTED = "open_panel_directory_selected";

  /** The Constant DATE_FORMAT. */
  private static final String DATE_FORMAT = "MM/dd/yyyy";

  /** The Constant GAP. */
  public static final int GAP = 40;

  /**
   * The member recent file list.
   */
  private final ModernMenuBox mRecentFileList = new ModernMenuBox(); // ModernList<File>

  /**
   * The member recent directory list.
   */
  private final RecentDirectoriesMenuBox mRecentDirectoryList = new RecentDirectoriesMenuBox();

  /**
   * The selected file.
   */
  private Path selectedFile = null;

  /**
   * The member tabs model.
   */
  private final TabsModel mTabsModel = new TabsModel();

  /**
   * The side tabs.
   */
  // private TabsModel sideTabs =
  // new RibbonPanelSideTabs(mTabsModel);

  /**
   * The view panel.
   */
  // private TabsViewPanel viewPanel = new TabsViewPanel(mTabsModel);

  /**
   * The member selected directory.
   */
  private Path mSelectedDirectory = RecentFilesService.getInstance().getPwd();

  /**
   * The class RecentEvents.
   */
  private class RecentEvents implements ModernClickListener {

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
     * modern .event.ModernClickEvent)
     */
    @Override
    public void clicked(ModernClickEvent e) {
      if (e.getSource() instanceof RecentFileMenuItem) {
        selectedFile = ((RecentFileMenuItem) e.getSource()).getFile();

        fireClicked(FILE_SELECTED);
      } else {
        fireClicked(e.getMessage());
      }
    }

  }

  /**
   * The class DirectoryEvents.
   */
  private class DirectoryEvents implements ModernClickListener {

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
     * modern .event.ModernClickEvent)
     */
    @Override
    public void clicked(ModernClickEvent e) {
      if (e.getSource() instanceof DirectoryMenuItem) {
        mSelectedDirectory = ((DirectoryMenuItem) e.getSource()).getFile();

        fireClicked(DIRECTORY_SELECTED);
      } else {
        fireClicked(e.getMessage());
      }
    }

  }

  /**
   * Instantiates a new open ribbon panel.
   */
  public OpenRibbonPanel() {
    this("Files", AssetService.getInstance().loadIcon(FileVectorIcon.class, 32));
  }

  /**
   * Instantiates a new open ribbon panel.
   *
   * @param title the title
   * @param icon  the icon
   */
  public OpenRibbonPanel(String title, ModernIcon icon) {
    super("Open");

    setup(title, icon);
  }

  /**
   * Setup.
   *
   * @param title the title
   * @param icon  the icon
   */
  private void setup(String title, ModernIcon icon) {
    // ModernLabel label = new ModernTitleLabel("Open");
    // label.setBorder(BorderService.getInstance().createBottomBorder(GAP));
    // setHeader(label);

    // sideTabs.add(UI.createVGap(20));
    // sideTabs.add(new ModernHDivider(20));
    // sideTabs.add(UI.createVGap(20));

    /*
     * ModernClickWidget button = new BrowseButton();
     * 
     * button.addClickListener(new ModernClickListener() {
     * 
     * @Override public void clicked(ModernClickEvent e) { mSelectedDirectory =
     * RecentFilesService.getInstance().getPwd(); fireClicked(DIRECTORY_SELECTED);
     * }});
     * 
     * Box box = VBox.create(); box.add(UI.createVGap(GAP)); box.add(button);
     * setFooter(box);
     */

    // UI.setSize(sideTabs, 300, Short.MAX_VALUE);

    // add(sideTabs, BorderLayout.LINE_START);

    //
    // Recent files
    //

    // mRecentFileList = new ModernList<File>(new
    // ModernListRecentFileRenderer(icon));
    // mRecentFileList.addSelectionListener(this);
    // mRecentFileList.setRowHeight(UIResources.ICON_SIZE_48);

    mRecentFileList.addClickListener(new RecentEvents());
    mRecentDirectoryList.addClickListener(new DirectoryEvents());

    mRecentFileList.setBorder(RIGHT_BORDER);
    ModernScrollPane scrollPane = new ModernScrollPane(mRecentFileList)
        .setHorizontalScrollBarPolicy(ScrollBarPolicy.NEVER);
    // scrollPane.setBackground(Color.WHITE);

    // title.toUpperCase()

    mTabsModel.addTab("Recent " + TextUtils.sentenceCase(title),
        AssetService.getInstance().loadIcon(ClockVectorIcon.class, 24), scrollPane);

    //
    // Recent directory
    //

    /*
     * Box box = Box.createVerticalBox();
     * 
     * box.add(new ModernColoredHeadingLabel("Computer"));
     * 
     * box.add(Ui.createVGap(20));
     * 
     * box.add(new ModernDialogHeadingLabel("Current Folder"));
     * 
     * box.add(Ui.createVGap(5));
     * 
     * mCurrentDirectoryList = new ModernList<File>(new
     * ModernListRecentDirectoryRenderer());
     * 
     * mCurrentDirectoryList.addSelectionListener(this);
     * mCurrentDirectoryList.setRowHeight(UIResources.ICON_SIZE_48);
     * 
     * scrollPane = new ModernScrollPane2(mCurrentDirectoryList);
     * scrollPane.setHorizontalScrollBarPolicy(ScrollBarPolicy.NEVER);
     * 
     * Ui.setSize(scrollPane, new Dimension(Short.MAX_VALUE, 50));
     * 
     * box.add(scrollPane);
     * 
     * box.add(Ui.createVGap(30));
     * 
     * box.add(new ModernDialogHeadingLabel("Recent Folders"));
     * 
     * box.add(Ui.createVGap(5));
     * 
     * mRecentDirectoriesList = new ModernList<File>(new
     * ModernListRecentDirectoryRenderer());
     * 
     * mRecentDirectoriesList.addSelectionListener(this);
     * mRecentDirectoriesList.setRowHeight(UIResources.ICON_SIZE_48);
     * 
     * scrollPane = new ModernScrollPane2(mRecentDirectoriesList);
     * scrollPane.setHorizontalScrollBarPolicy(ScrollBarPolicy.NEVER);
     * 
     * box.add(scrollPane);
     * 
     * box.add(Ui.createVGap(30));
     * 
     * ModernButtonWidget button = new RibbonPanelButton(Ui.MENU_BROWSE,
     * ModernVectorIcon.FOLDER_32_ICON);
     * 
     * button.addClickListener(this); button.setClickMessage(DIRECTORY_SELECTED);
     * 
     * box.add(button);
     */

    mRecentDirectoryList.setBorder(RIGHT_BORDER);
    scrollPane = new ModernScrollPane(mRecentDirectoryList);
    scrollPane.setHorizontalScrollBarPolicy(ScrollBarPolicy.NEVER);
    // scrollPane.setBackground(Color.WHITE);

    mTabsModel.addTab(UI.ASSET_THIS_PC, AssetService.getInstance().loadIcon(ComputerVectorIcon.class, 24), scrollPane); // new
    // ModernContentPanel(scrollPane));

    mTabsModel.addTab(UI.ASSET_BROWSE, AssetService.getInstance().loadIcon(OpenFolderVectorIcon.class, 24), null);

    mTabsModel.addTabListener(new TabEventAdapter() {

      @Override
      public void tabChanged(TabEvent e) {
        if (mTabsModel.getSelectedTab().getName().equals(UI.ASSET_BROWSE)) {
          mSelectedDirectory = RecentFilesService.getInstance().getPwd();
          fireClicked(DIRECTORY_SELECTED);
        }
      }
    });

    // viewPanel.setBorder(BorderService.getInstance().createLeftBorder(20));

    setBody(new RibbonVertTabsPanel(mTabsModel, 250, GAP, true));

    mTabsModel.changeTab(0);
  }

  /*
   * public final Table getTable() {
   * 
   * return table; }
   */

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ribbon.RibbonMenuPanel#refresh()
   */
  @Override
  public final void refresh() {
    reload();

    mTabsModel.changeTab(0);
  }

  /**
   * Reload.
   */
  public final void reload() {

    // Recent files

    // ModernListModel<File> model = new ModernListModel<File>();

    mRecentFileList.removeAll();

    // mRecentFileList.add(new BrowseFileMenuItem());
    // mRecentFileList.add(new ModernMenuDivider());

    List<Path> files = new UniqueArrayList<>();

    Date now = Calendar.getInstance().getTime();

    for (Path file : RecentFilesService.getInstance()) {
      try {
        if (FileUtils.exists(file) && today(file, now, RecentFilesService.getInstance().getDate(file))) {
          files.add(file);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    if (!files.isEmpty()) {
      mRecentFileList.add(new FileTitleMenuItem(UI.ASSET_TODAY));

      for (Path file : files) {
        mRecentFileList.add(new RecentFileMenuItem(file, RecentFilesService.getInstance().getDate(file)));
      }
    }

    files.clear();

    for (Path file : RecentFilesService.getInstance()) {
      try {
        if (FileUtils.exists(file) && !today(file, now, RecentFilesService.getInstance().getDate(file))) {
          files.add(file);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    if (!files.isEmpty()) {
      mRecentFileList.add(new FileTitleMenuItem(UI.ASSET_OLDER));

      for (Path file : files) {
        mRecentFileList.add(new RecentFileMenuItem(file, RecentFilesService.getInstance().getDate(file)));
      }
    }

    // mRecentFileList.setModel(model);

    // current directory

    mRecentDirectoryList.reload();
  }

  /**
   * Clicked.
   *
   * @param e the e
   */
  public final void clicked(ModernClickEvent e) {
    fireClicked(new ModernClickEvent(this, e.getMessage()));
  }

  /**
   * Gets the selected file.
   *
   * @return the selected file
   */
  public final Path getSelectedFile() {
    return selectedFile;
  }

  /**
   * Gets the selected directory.
   *
   * @return the selected directory
   */
  public final Path getSelectedDirectory() {
    return mSelectedDirectory;
  }

  /**
   * Today.
   *
   * @param file the file
   * @return true, if successful
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static boolean today(final Path file) throws IOException {
    return today(file, Calendar.getInstance().getTime());
  }

  /**
   * Today.
   *
   * @param file the file
   * @param now  the now
   * @return true, if successful
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static boolean today(final Path file, final Date now) throws IOException {
    return today(file, new Date(Files.getLastModifiedTime(file).toMillis()), now);
  }

  /**
   * Today.
   *
   * @param file the file
   * @param now  the now
   * @param date the date
   * @return true, if successful
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static boolean today(final Path file, final Date now, final Date date) throws IOException {
    SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);

    return df.format(now).equals(df.format(date));
  }

  /*
   * @Override public void selectionChanged(ChangeEvent e) { if
   * (e.getSource().equals(mCurrentDirectoryList)) { int i =
   * mCurrentDirectoryList.getSelectedIndex();
   * 
   * if (i != -1) { mSelectedDirectory = mCurrentDirectoryList.getValueAt(i);
   * 
   * fireClicked(new ModernClickEvent(this, DIRECTORY_SELECTED)); } } else if
   * (e.getSource().equals(mRecentDirectoriesList)) { int i =
   * mRecentDirectoriesList.getSelectedIndex();
   * 
   * if (i != -1) { mSelectedDirectory = mRecentDirectoriesList.getValueAt(i);
   * 
   * fireClicked(new ModernClickEvent(this, DIRECTORY_SELECTED)); } } else { // do
   * nothing } }
   */
}
