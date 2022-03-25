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
package org.jebtk.modern.ribbon;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import org.jebtk.core.collections.CollectionUtils;
import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.button.ModernButton;
import org.jebtk.modern.button.ModernButtonGroup;
import org.jebtk.modern.button.ModernClickWidget;
import org.jebtk.modern.dialog.ModernDialogButton;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.help.GuiAppInfo;
import org.jebtk.modern.help.RibbonPanelProductInfo;
import org.jebtk.modern.options.ModernOptionsRibbonPanel;
import org.jebtk.modern.panel.ModernPanel;
import org.jebtk.modern.tabs.TabsModel;
import org.jebtk.modern.theme.MaterialService;
import org.jebtk.modern.window.ModernWindow;
import org.jebtk.modern.window.WindowMover;

// TODO: Auto-generated Javadoc
/**
 * Ribbon menu system.
 * 
 * @author Antony Holmes
 *
 */
public class RibbonFileMenu extends ModernClickWidget implements ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member menu panel.
   */
  private final RibbonFileMenuPanel mMenuPanel = new RibbonFileMenuPanel();
  
  private final ModernButton mCloseButton = new ModernDialogButton("Back");

  // private RibbonFileMenuContentPanel contentPanel =
  // new RibbonFileMenuContentPanel();

  // private Map<RibbonMenuItem, RibbonMenuPanel> contentPanels =
  // new HashMap<RibbonMenuItem, RibbonMenuPanel>();

  /**
   * The member content panels.
   */
  private RibbonMenuTabsContainerPanel mContentPanels = null;

  /**
   * The model.
   */
  private final TabsModel mModel = new TabsModel();

  /**
   * The group.
   */
  private final ModernButtonGroup mGroup = new ModernButtonGroup();

  /**
   * The menu items.
   */
  private final List<RibbonMenuItem> mMenuItems = new ArrayList<>();

  /**
   * The member default menu index.
   */
  private int mDefaultMenuIndex = 0;

  /** The m title bar. */
  private RibbonTitleBar mTitleBar;

  //private final Toolbar mToolbar = new Toolbar(Color.WHITE);

  /**
   * The constant CLOSE_MENU.
   */
  private static final String CLOSE_MENU = "close_menu";

  //private static final Color RIBBON_FILE_BACKGROUND = MaterialService.getInstance()
  //    .getColor("window.background.gradient.end"); // ThemeService.getInstance().getColors().getGray32(1);

  //private static final Color C1 = MaterialService.getInstance().getColor("window.background.gradient.start");
  //private static final Color C2 = MaterialService.getInstance().getColor("window.background.gradient.end");

  /**
   * The class CloseAction.
   */
  private class CloseAction extends AbstractAction {

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      // trigger the menu to close
      fireClicked(new ModernClickEvent(this, CLOSE_MENU));
    }
  }

  /**
   * Instantiates a new ribbon file menu.
   *
   * @param window the window
   */
  public RibbonFileMenu(ModernWindow window) {

    // If we need custom window dressing
    if (UI.CUSTOM_WINDOW_DECORATION) {
      mTitleBar = new RibbonTitleBar(window);
      mTitleBar.setTitle(window.getTitle());
      setHeader(mTitleBar);
      // mTitleBar.addClickListener(this);
      // So we can respond to move events
      new WindowMover(window, mTitleBar);
    }

    mContentPanels = new RibbonMenuTabsContainerPanel(mModel);

    //setHeader(mToolbar);

    ModernComponent panel = new ModernComponent();
    ModernComponent leftPanel = new ModernComponent();
    leftPanel.setBorder(ModernWidget.DOUBLE_BORDER);
    leftPanel.setBody(mMenuPanel);
    leftPanel.setFooter(mCloseButton);
    
    mCloseButton.addClickListener(e -> {
      fireClicked(new ModernClickEvent(this, CLOSE_MENU));
              });
    
    panel.setLeft(leftPanel);
    panel.setBody(new ModernPanel(mContentPanels));

    // panel.setBorder(BorderService.getInstance().createBorder(20));

    // setBody(new TopShadowPanel(panel));
    setBody(panel);

    // menuPanel.add(new TabbedBarSeparator());
    //mMenuPanel.add(UI.createVGap(40));

    // RibbonBackMenuItem ribbonBackMenuItem = new RibbonBackMenuItem();
    // ribbonBackMenuItem.addClickListener(this);
    // mMenuPanel.add(ribbonBackMenuItem);
    // mMenuPanel.add(UI.createVGap(20));

    //RibbonBackButton ribbonBackButton = new RibbonBackButton();
    //ribbonBackButton.addClickListener(this);
    //m/Toolbar.add(ribbonBackButton);

    // When the menu is visible, make it respond to the escape key
    getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), CLOSE_MENU);

    getActionMap().put(CLOSE_MENU, new CloseAction());
  }

  /**
   * Sets the default index.
   *
   * @param defaultMenuIndex the new default index
   */
  public void setDefaultIndex(int defaultMenuIndex) {
    mDefaultMenuIndex = defaultMenuIndex;
  }

  /**
   * Add a line separator between menu items.
   */
  public void addSeparator() {
    mMenuPanel.add(new RibbonMenuSeparator());
  }

  /**
   * Adds the tabbed menu item.
   *
   * @param item the item
   */
  public final void addTabbedMenuItem(RibbonMenuItem item) {

    mMenuPanel.add(item);
    mMenuItems.add(item);
    mGroup.add(item);

    item.addClickListener(this);
  }

  /**
   * Add a menu item with associated content panel.
   *
   * @param item         the item
   * @param contentPanel the content panel
   */
  public final void addTabbedMenuItem(RibbonMenuItem item, RibbonMenuPanel contentPanel) {

    if (contentPanel == null) {
      return;
    }

    // register so the menu can take action to make itself invisible if a button
    // action is performed that doesn't already achive this

    // TODO is this necessary?
    contentPanel.addClickListener(this);

    mModel.addTab(item.getText(), contentPanel);

    addTabbedMenuItem(item);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  @Override
  public void clicked(ModernClickEvent e) {
    if (e.getSource() instanceof RibbonMenuItem) {
      // if the item is a normal menu item
      click((RibbonMenuItem) e.getSource());
    } else {
      // super.clicked(e);

      super.fireClicked(new ModernClickEvent(this, e.getMessage()));
    }
  }

  /**
   * Sets the active menu item.
   */
  public final void setActiveMenuItem() {
    // return setActiveMenuItem(defaultMenuIndex);

    setActiveMenuItem(mDefaultMenuIndex);
  }

  /**
   * Sets the active menu item.
   *
   * @param index the new active menu item
   */
  public void setActiveMenuItem(int index) {
    if (CollectionUtils.inBounds(index, mMenuItems)) {
      setActiveMenuItem(mMenuItems.get(index));
    }

    // menuItems.get(index).doClick();
  }

  /**
   * Sets an active item which should be an item with an appropriate loadable side
   * menu.
   *
   * @param item the item
   * @return true, if successful
   */
  public final boolean setActiveMenuItem(RibbonMenuItem item) {
    if (!mModel.containsTab(item.getText())) {
      return false;
    }

    mModel.changeTab(item.getText());

    ((RibbonMenuPanel) mModel.getSelectedTab().getComponent()).refresh();

    item.setSelected(true);

    return true;
  }

  /**
   * Click.
   *
   * @param item the item
   */
  private void click(RibbonMenuItem item) {
    boolean active = setActiveMenuItem(item);

    if (!active) {
      // menu item does not have a panel so
      // fire menu click in case we
      // need to close the menu

      fireClicked(new ModernClickEvent(this, item.getClickMessage()));
    }
  }

  /**
   * Add a predefined amount of vertical space between menu items.
   */
  public void addSpace() {
    mMenuPanel.add(ModernPanel.createVGap());
  }

  /**
   * Adds the default items.
   *
   * @param appInfo the app info
   */
  public void addDefaultItems(GuiAppInfo appInfo) {
    addDefaultItems(appInfo, true);
  }

  /**
   * Adds default Exit and option buttons to the ribbon since these are fairly
   * boilerplate.
   *
   * @param appInfo the app info
   * @param exit    If true adds exit item to menu, otherwise adds close item.
   */
  public void addDefaultItems(GuiAppInfo appInfo, boolean exit) {
    RibbonMenuItem menuItem = new RibbonMenuItem(exit ? UI.MENU_EXIT : UI.MENU_CLOSE);

    addTabbedMenuItem(menuItem);

    // addSeparator();

    mMenuPanel.add(UI.createVGap(20));

    menuItem = new RibbonMenuItem(UI.MENU_INFO);

    addTabbedMenuItem(menuItem, new RibbonPanelProductInfo(appInfo));

    menuItem = new RibbonMenuItem(UI.MENU_OPTIONS);
    addTabbedMenuItem(menuItem, new ModernOptionsRibbonPanel(appInfo));
  }

  //@Override
  //public void drawBackground(Graphics2D g2) {
    // fill(g2, RIBBON_FILE_BACKGROUND);

    //fill(g2, C1, C2);
  //}

  public RibbonMenuItem addTabbedMenuItem(String text) {
    RibbonMenuItem item = new RibbonMenuItem(text);

    addTabbedMenuItem(item);

    return item;
  }
}