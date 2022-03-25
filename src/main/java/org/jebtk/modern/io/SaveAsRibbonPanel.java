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
import java.nio.file.Path;

import org.jebtk.modern.AssetService;
import org.jebtk.modern.UI;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.graphics.icons.ComputerVectorIcon;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.graphics.icons.OpenFolderVectorIcon;
import org.jebtk.modern.ribbon.RibbonMenuPanel;
import org.jebtk.modern.ribbon.RibbonVertTabsPanel;
import org.jebtk.modern.scrollpane.ModernScrollPane;
import org.jebtk.modern.scrollpane.ScrollBarPolicy;
import org.jebtk.modern.tabs.TabEvent;
import org.jebtk.modern.tabs.TabEventAdapter;
import org.jebtk.modern.tabs.TabsModel;

// TODO: Auto-generated Javadoc
/**
 * The class SaveAsRibbonPanel.
 */
public class SaveAsRibbonPanel extends RibbonMenuPanel {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant DIRECTORY_SELECTED.
   */
  public static final String DIRECTORY_SELECTED = "save_as_panel_directory_selected";

  /** The Constant THIS_PC_ICON. */
  private static final ModernIcon THIS_PC_ICON = AssetService.getInstance().loadIcon(ComputerVectorIcon.class, 24);

  /**
   * The tabs model.
   */
  private TabsModel mTabsModel = new TabsModel();

  /**
   * The side tabs.
   */
  // private RibbonPanelSideTabs sideTabs = new RibbonPanelSideTabs(tabsModel);

  /**
   * The view panel.
   */
  // private TabsViewPanel viewPanel = new TabsViewPanel(tabsModel);

  /**
   * The member recent directory list.
   */
  private RecentDirectoriesMenuBox mRecentDirectoryList = new RecentDirectoriesMenuBox();

  /**
   * The member selected directory.
   */
  private Path mSelectedDirectory = RecentFilesService.getInstance().getPwd();

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
      } else {
        mSelectedDirectory = RecentFilesService.getInstance().getPwd();
      }

      fireClicked(DIRECTORY_SELECTED);
    }

  }

  /**
   * Instantiates a new save as ribbon panel.
   */
  public SaveAsRibbonPanel() {
    super("Save As");

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    // ModernLabel label = new ModernTitleLabel("Save As");
    // label.setBorder(BorderService.getInstance().createBottomBorder(OpenRibbonPanel.GAP));
    // setHeader(label);

    // sideTabs.addTab("This PC", THIS_PC_ICON);

    // sideTabs.add(UI.createVGap(20));
    // sideTabs.add(new ModernHDivider(20));
    // sideTabs.add(UI.createVGap(20));

    /*
     * ModernClickWidget button = new BrowseButton();
     * 
     * sideTabs.add(button);
     * 
     * button.addClickListener(new ModernClickListener() {
     * 
     * @Override public void clicked(ModernClickEvent e) { mSelectedDirectory =
     * RecentFilesService.getInstance().getPwd(); fireClicked(DIRECTORY_SELECTED);
     * }});
     * 
     * UI.setSize(sideTabs, 300, Short.MAX_VALUE);
     */

    // add(sideTabs, BorderLayout.LINE_START);

    mRecentDirectoryList.setBorder(RIGHT_BORDER);
    ModernScrollPane scrollPane = new ModernScrollPane(mRecentDirectoryList);
    scrollPane.setHorizontalScrollBarPolicy(ScrollBarPolicy.NEVER);

    mTabsModel.addTab(UI.ASSET_THIS_PC, THIS_PC_ICON, scrollPane);

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

    setBody(new RibbonVertTabsPanel(mTabsModel, 250, OpenRibbonPanel.GAP, true));

    mTabsModel.changeTab(0);

    mRecentDirectoryList.addClickListener(new DirectoryEvents());
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
    try {
      reload();
    } catch (IOException e) {
      e.printStackTrace();
    }

    mTabsModel.changeTab(0);
  }

  /**
   * Reload.
   *
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public final void reload() throws IOException {

    // current dir

    mRecentDirectoryList.reload();
  }

  /**
   * Gets the selected directory.
   *
   * @return the selected directory
   */
  public final Path getSelectedDirectory() {
    return mSelectedDirectory;
  }

  /*
   * @Override public void selectionChanged(ChangeEvent e) { if
   * (e.getSource().equals(mCurrentDirectoryList)) { int i =
   * mCurrentDirectoryList.getSelectedIndex();
   * 
   * if (i != -1) { selectedDirectory = mRecentDirectoriesList.getValueAt(i);
   * 
   * fireClicked(new ModernClickEvent(this, DIRECTORY_SELECTED)); } } else if
   * (e.getSource().equals(mRecentDirectoriesList)) { int i =
   * mRecentDirectoriesList.getSelectedIndex();
   * 
   * if (i != -1) { selectedDirectory = mRecentDirectoriesList.getValueAt(i);
   * 
   * fireClicked(new ModernClickEvent(this, DIRECTORY_SELECTED)); } } else { // do
   * nothing } }
   */
}
