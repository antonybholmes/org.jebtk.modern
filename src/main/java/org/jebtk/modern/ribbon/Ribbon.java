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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Arc2D;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import org.jebtk.core.Mathematics;
import org.jebtk.core.text.TextUtils;
import org.jebtk.modern.AssetService;
import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.animation.AnimationService;
import org.jebtk.modern.button.ModernButtonGroup;
import org.jebtk.modern.button.ModernClickWidget;
import org.jebtk.modern.event.HighlightEvent;
import org.jebtk.modern.event.HighlightEventProducer;
import org.jebtk.modern.event.HighlightListener;
import org.jebtk.modern.event.HighlightListeners;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.graphics.icons.RasterIcon;
import org.jebtk.modern.help.GuiAppInfo;
import org.jebtk.modern.menu.ModernPopupMenu2;
import org.jebtk.modern.menu.ModernRadioMenuItem;
import org.jebtk.modern.panel.ModernPanel;
import org.jebtk.modern.panel.VBox;
import org.jebtk.modern.tabs.Tab;
import org.jebtk.modern.tabs.TabEvent;
import org.jebtk.modern.tabs.TabEventListener;
import org.jebtk.modern.tabs.TabEventProducer;
import org.jebtk.modern.tabs.TabsModel;
import org.jebtk.modern.theme.MaterialService;
import org.jebtk.modern.theme.ThemeService;
import org.jebtk.modern.tooltip.ModernToolTipEvent;
import org.jebtk.modern.tooltip.ModernToolTipListener;
import org.jebtk.modern.tooltip.ToolTipService;
import org.jebtk.modern.window.ModernTitleBar;
import org.jebtk.modern.window.ModernWindow;
import org.jebtk.modern.window.ModernWindowTitleBar;

// TODO: Auto-generated Javadoc
/**
 * The Class Ribbon.
 */
public final class Ribbon extends ModernClickWidget
    implements IRibbonModeProperty, TabEventProducer, TabEventListener, HighlightEventProducer, ModernToolTipListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  static {
    AnimationService.getInstance().get("ribbon").add(RibbonAnimation.class)
        // .add(RibbonPressedAnimation.class)
        .add(RibbonHighlightAnimation.class).add(RibbonChangeAnimation.class).add(RibbonHighlightTextAnimation.class);
  }

  /** The Constant HOME_TOOLBAR. */
  public static final String HOME_TOOLBAR = UI.ASSET_RIBBON_HOME;

  /** The height of each tab. */
  public static final int TAB_HEIGHT = 36; // WIDGET_HEIGHT; //28;

  /** The border between the window and where the ribbon starts. */
  public static final int Y_OFFSET = 0; // (UI.CUSTOM_WINDOW_DECORATION ?
  // TAB_HEIGHT : 0); // +1;

  /** The y position where tabs start. */
  public static final int TAB_BODY_Y = Y_OFFSET + TAB_HEIGHT;

  /** The Constant TAB_GAP. */
  private static final int TAB_GAP = 1;

  /** The height of a compact tool bar. */
  private static final int COMPACT_TOOLBAR_HEIGHT = 45;

  /** The Constant TOOLBAR_HEIGHT. */
  private static final int TOOLBAR_HEIGHT = 90;

  /** The Constant MIN_BUTTON_WIDTH. */
  public static final int MIN_BUTTON_WIDTH = 50;

  /** The Constant MIN_COMPACT_BUTTON_WIDTH. */
  public static final int MIN_COMPACT_BUTTON_WIDTH = Mathematics.makeMult2(1.5 * COMPACT_TOOLBAR_HEIGHT);

  /**
   * The constant TOOLBAR_HEIGHT.
   */
  protected int mToolbarHeight = COMPACT_TOOLBAR_HEIGHT;

  /**
   * The constant TAB_PADDING_X.
   */
  private static final int TAB_PADDING_X = 12;

  /**
   * The constant TOTAL_TAB_PADDING_X.
   */
  private static final int TOTAL_TAB_PADDING_X = 2 * TAB_PADDING_X;

  /**
   * The constant QUICK_BUTTON_Y.
   */
  private static final int QUICK_BUTTON_Y = Y_OFFSET + (TAB_HEIGHT - WIDGET_HEIGHT) / 2;

  /**
   * The constant LARGE_BUTTON_HEIGHT.
   */
  public static final int LARGE_BUTTON_HEIGHT = 72;

  /** The Constant COMPACT_BUTTON_HEIGHT. */
  public static final int COMPACT_BUTTON_HEIGHT = COMPACT_TOOLBAR_HEIGHT;

  /**
   * The constant LARGE_BUTTON_WIDTH.
   */
  public static final int LARGE_BUTTON_WIDTH = (int) Mathematics
      .makeMult2(Math.round(LARGE_BUTTON_HEIGHT * Mathematics.RATIO_43));

  /**
   * The constant DEFAULT_BUTTON_SIZE.
   */
  public static final Dimension DEFAULT_BUTTON_SIZE = new Dimension(LARGE_BUTTON_WIDTH, LARGE_BUTTON_HEIGHT);

  /**
   * The constant TINY_BUTTON_SIZE.
   */
  public static final Dimension TINY_BUTTON_SIZE = new Dimension(LARGE_BUTTON_HEIGHT, LARGE_BUTTON_HEIGHT);

  /**
   * The constant TEXT_BUTTON_SIZE.
   */
  public static final Dimension TEXT_BUTTON_SIZE = new Dimension(
      (int) Mathematics.makeMult2(Math.round(1.6 * LARGE_BUTTON_WIDTH)), LARGE_BUTTON_HEIGHT);

  /**
   * The constant MEDIUM_TEXT_BUTTON_SIZE.
   */
  public static final Dimension MEDIUM_TEXT_BUTTON_SIZE = new Dimension(
      (int) Mathematics.makeMult2(Math.round(2 * LARGE_BUTTON_WIDTH)), LARGE_BUTTON_HEIGHT);

  /**
   * The constant LARGE_TEXT_BUTTON_SIZE.
   */
  public static final Dimension LARGE_TEXT_BUTTON_SIZE = new Dimension(
      (int) Mathematics.makeMult2(Math.round(2.5 * LARGE_BUTTON_WIDTH)), LARGE_BUTTON_HEIGHT);

  /**
   * The constant EXTRA_LARGE_TEXT_BUTTON_SIZE.
   */
  public static final Dimension EXTRA_LARGE_TEXT_BUTTON_SIZE = new Dimension(
      (int) Mathematics.makeMult2(Math.round(3 * LARGE_BUTTON_WIDTH)), LARGE_BUTTON_HEIGHT);

  /**
   * The constant TAB_FONT.
   */
  public static final Font TAB_FONT = MaterialService.getInstance().getFonts().text(); // ModernWidget.BOLD_FONT;
  // //MaterialUtils.TEXT_FONT;
  // //ModernWidget.FONT;
  // //ThemeService.loadFont("theme.ribbon.getFonts.tab");

  /**
   * The constant FILE_MENU_WIDTH.
   */
  // private static final int FILE_MENU_WIDTH = getTabWidth(FILE_MENU_TEXT);

  /**
   * The constant EVENT_MINIMIZE.
   */
  public static final String EVENT_MINIMIZE = "minimize";

  /**
   * The constant EVENT_MAXIMIZE.
   */
  public static final String EVENT_MAXIMIZE = "maximize";

  /**
   * The constant EVENT_CLOSE.
   */
  public static final String EVENT_CLOSE = "close";

  /** The Constant MARGIN. */
  private static final int MARGIN = 2;

  // private int mFileTabX = MARGIN + PADDING;

  /**
   * The tab start x.
   */
  protected int mTabStartX = MARGIN + PADDING; // FILE_MENU_WIDTH +
  // DOUBLE_PADDING;

  /** The Constant BAR_BACKGROUND. */
  public static final Color BAR_BACKGROUND = MaterialService.getInstance().getColor("ribbon");

  /** The constant TAB_COLOR. */
  public static final Color TAB_COLOR = ThemeService.getInstance().getColors().getGray32(1);

  /** The Constant TAB_COLOR_2. */
  public static final Color TAB_COLOR_2 = ThemeService.getInstance().getColors().getGray32(3);

  /**
   * The constant BORDER_COLOR.
   */
  public static final Color BORDER_COLOR = ThemeService.getInstance().getColors().getGray(4);

  /**
   * The constant BUTTON_HIGHLIGHTED_COLOR.
   */
  public static final Color BUTTON_HIGHLIGHTED_COLOR = ThemeService.getInstance().getColors().getGray(5); // Color.WHITE;

  /**
   * The constant BUTTON_SELECTED_COLOR.
   */
  public static final Color BUTTON_SELECTED_COLOR = ThemeService.getInstance().getColors().getGray(5);

  // ThemeService.getInstance().getThemeColor(4);

  /**
   * The constant ICON_SIZE.
   */
  public static final int COMPACT_ICON_SIZE = 24;

  /** The Constant LARGE_ICON_SIZE. */
  public static final int LARGE_ICON_SIZE = 32;

  /** The Constant FILE_TOOLBAR. */
  // private static final String FILE_TOOLBAR = "FILE";

  /** The Constant ROUNDING. */
  private static final int ROUNDING = MaterialService.getInstance().getInts().cornerRadius();

  /** The Constant SEPARATOR_COLOR. */
  public static final Color SEPARATOR_COLOR = MaterialService.getInstance().getColor("ribbon.separator");

  /**
   * The member tabs panel.
   */
  private final RibbonViewPanel mTabsPanel = new RibbonViewPanel();

  /**
   * The member selected tab.
   */
  protected int mSelectedTab = -1;

  /**
   * The member highlighted tab.
   */
  protected int mHighlightedTab = -1;

  /**
   * The member hide button.
   */
  // protected ModernButton mHideButton =
  // new
  // RibbonButton(UIService.getInstance().loadIcon(CheveronUpVectorIcon.class,
  // Color.WHITE, 16));

  /**
   * The member help button.
   */
  protected RibbonHelpButton mHelpButton = new RibbonHelpButton();

  /**
   * The member toolbar visible.
   */
  protected boolean mToolbarVisible = true;

  /**
   * The member titles.
   */
  protected List<String> mTitles = new ArrayList<>();

  /**
   * The member tab widths.
   */
  protected List<Integer> mTabWidths = new ArrayList<>();

  /**
   * The member tab starts.
   */
  protected List<Integer> mTabStarts = new ArrayList<>();

  /**
   * The member tabs.
   */
  private final TabsModel mTabs = new TabsModel();

  /**
   * The member quick access buttons.
   */
  private final List<ModernClickWidget> mQuickAccessButtons = new ArrayList<>();

  /**
   * The member right toolbar buttons.
   */
  private final List<ModernComponent> mRightToolbarButtons = new ArrayList<>();

  /** The m height. */
  private int mHeight;

  /** The m size. */
  private RibbonSize mSize;

  /** The m mode. */
  private RibbonMode mMode = RibbonMode.TABS;

  /** The m title bar. */
  private ModernWindowTitleBar mTitleBar;

  /** The m window. */
  private final ModernWindow mWindow;

  /** The m mode button. */
  private QuickAccessMenuButton2 mModeButton;

  private final HighlightListeners mHighlightListeners = new HighlightListeners();

  /**
   * The class MouseEvents.
   */
  private class MouseEvents extends MouseAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseExited(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseExited(MouseEvent e) {
      mHighlightedTab = -1;

      repaint();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseClicked(MouseEvent e) {
      if (e.getY() < Y_OFFSET || e.getY() > TAB_BODY_Y) {
        return;
      }

      changeTab(mHighlightedTab);
    }
  }

  /**
   * The class MouseMotionEvents.
   */
  private class MouseMotionEvents extends MouseMotionAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseMoved(MouseEvent e) {
      int index = -1;

      if (e.getY() < Y_OFFSET || e.getY() > TAB_BODY_Y) {
        return;
      }

      int x = e.getX() - mTabStartX;

      for (int i = 0; i < mTitles.size(); ++i) {
        if (x >= mTabStarts.get(i) && x < mTabStarts.get(i) + mTabWidths.get(i)) {

          index = i;

          break;
        }
      }

      if (index == -1 || index == mHighlightedTab) {
        return;
      }

      mHighlightedTab = index;

      hightlightTab(index);

      // repaint();
    }
  }

  /**
   * The class ComponentEvents.
   */
  private class ComponentEvents extends ComponentAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.ComponentAdapter#componentResized(java.awt.event.
     * ComponentEvent)
     */
    @Override
    public final void componentResized(ComponentEvent e) {
      adjustTitleBar();

      alterTabLayout();

      adjustRightButtons();
    }
  }

  /**
   * The class NextTabAction.
   */
  private class NextTabAction extends AbstractAction {

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
      setSelectedIndex(getSelectedIndex() % getTabCount() + 1);
    }
  }

  /**
   * The class PreviousTabAction.
   */
  private class PreviousTabAction extends AbstractAction {

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

      int cycleTab = getSelectedIndex() - 1;

      if (cycleTab == 0) {
        // the first tab is not selectable as a normal tab
        cycleTab = mTitles.size();
      }

      setSelectedIndex(cycleTab);
    }
  }

  /**
   * Creates the toolbar button column panel.
   *
   * @return the box
   */
  public static final Box createToolbarButtonColumnPanel() {
    return createToolbarButtonColumnPanel(100);
  }

  /**
   * Creates the toolbar button column panel.
   *
   * @param width the width
   * @return the box
   */
  public static final Box createToolbarButtonColumnPanel(int width) {

    Box box = VBox.create();

    box.setPreferredSize(new Dimension(width, Ribbon.LARGE_BUTTON_HEIGHT));

    return box;
  }

  /**
   * The class ShowHelpAction.
   */
  private class ShowHelpAction extends AbstractAction {

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
      mHelpButton.doClick();
    }
  }

  /**
   * The class HideClickEvents.
   */
  private class HideClickEvents implements ModernClickListener {

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
     * modern .event.ModernClickEvent)
     */
    @Override
    public final void clicked(ModernClickEvent e) {
      toggleVisible();
    }

  }

  /**
   * The Class FocusEvents.
   */
  private class FocusEvents implements FocusListener {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
     */
    @Override
    public void focusGained(FocusEvent e) {
      // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
     */
    @Override
    public void focusLost(FocusEvent e) {
      if (mMode == RibbonMode.TABS) {
        mWindow.getLayeredPane().remove(mTabsPanel);
        mWindow.getLayeredPane().revalidate();
        mWindow.getLayeredPane().repaint();
      }
    }

  }

  /**
   * Creates a new Ribbon control.
   *
   * @param window the window
   */
  public Ribbon(ModernWindow window) {
    mWindow = window;

    setLayout(null);

    /*
     * if (UI.CUSTOM_WINDOW_DECORATION) { mTitleBar = new
     * ModernWindowTitleBar(window);
     * 
     * // add the title bar before the ribbon // mHeaderContainer.removeAll();
     * add(mTitleBar);
     * 
     * // So the title bar can be used to move the window new WindowMover(window,
     * mTitleBar); }
     */

    addMouseListener(new MouseEvents());
    addMouseMotionListener(new MouseMotionEvents());
    addComponentListener(new ComponentEvents());
    addFocusListener(new FocusEvents());

    mTabs.addTabListener(this);

    // add right control buttons in reverse order

    mTabsPanel.getHideButton().addClickListener(new HideClickEvents());

    mHelpButton.setVisible(false);

    getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, KeyEvent.CTRL_MASK), "nextTabAction");
    getActionMap().put("nextTabAction", new NextTabAction());

    getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, KeyEvent.CTRL_MASK), "previousTabAction");
    getActionMap().put("previousTabAction", new PreviousTabAction());

    getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("F1"), "show_help");
    getActionMap().put("show_help", new ShowHelpAction());

    createRightToolbar();

    setFont(TAB_FONT);

    setSize(RibbonSize.COMPACT);

    addToolbar(UI.ASSET_RIBBON_FILE);

    setMode(RibbonMode.TABS_COMMANDS);

    // Register to recieve events sent to the ribbon so that we can forward
    // them to the underlying window
    ToolTipService.getInstance().addToolTipListener(this);

    // As a pass through entity, this component does not generate nor need
    // to respond to tooltips
    setToolTipsEnabled(false);

    // addStyleClass("ribbon");

    setAnimations("ribbon");

    setCSSMode(false);

    /*
     * getBackgroundAnimations() .set(new RibbonAnimation(this)) .add(new
     * RibbonHighlightAnimation(this)) .add(new RibbonChangeAnimation(this));
     */
  }

  /**
   * Sets the mode.
   *
   * @param mode the new mode
   */
  public void setMode(RibbonMode mode) {
    mMode = mode;

    mToolbarVisible = false;

    if (mode == RibbonMode.TABS) {
      remove(mTabsPanel);
    } else {
      add(mTabsPanel);
    }

    toggleVisible();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.ribbon.RibbonModeProperty#setSize(org.abh.common.ui.
   * ribbon. RibbonSize)
   */
  @Override
  public void setSize(RibbonSize mode) {
    mSize = mode;

    // notify all tabs the mode has changed
    for (Tab tab : mTabs) {
      ((RibbonToolbar) tab.getComponent()).setSize(mode);
    }

    if (mode == RibbonSize.COMPACT) {
      mToolbarHeight = COMPACT_TOOLBAR_HEIGHT;
    } else {
      mToolbarHeight = TOOLBAR_HEIGHT;
    }

    mHeight = TAB_BODY_Y + mToolbarHeight + PADDING;

    toggleVisible();
  }

  public RibbonSize getRibbonSize() {
    return mSize;
  }

  /**
   * Creates the right toolbar.
   */
  protected void createRightToolbar() {

    ModernRadioMenuItem showTabsMenuItem = new ModernRadioMenuItem("Show Tabs");

    ModernRadioMenuItem showTabsCommandsMenuItem = new ModernRadioMenuItem("Show Tabs and Commands");

    new ModernButtonGroup(showTabsMenuItem, showTabsCommandsMenuItem);

    showTabsCommandsMenuItem.doClick();

    ModernPopupMenu2 menu = new ModernPopupMenu2(showTabsMenuItem, showTabsCommandsMenuItem);

    mModeButton = new QuickAccessMenuButton2(
        AssetService.getInstance().loadIcon(RibbonModeVectorIcon.class, ModernWidget.TEXT_COLOR, 16), menu);
    mModeButton.setToolTip("Ribbon Display Options", "Configure ribbon behavior.");
    addRightToolbarButton(mModeButton);

    addRightToolbarButton(mHelpButton);

    menu.addClickListener((ModernClickEvent e) -> {
      if (e.getMessage().equals("Show Tabs")) {
        setMode(RibbonMode.TABS);
      } else {
        setMode(RibbonMode.TABS_COMMANDS);
      }
    });
  }

  /**
   * Adds the right toolbar button.
   *
   * @param button the button
   */
  public void addRightToolbarButton(ModernClickWidget button) {
    if (mTitleBar != null) {
      mTitleBar.addRight(button);
    } else {
      _addRightToolbarButton(button);
    }

  }

  /**
   * Adds the right toolbar button.
   *
   * @param button the button
   */
  private void _addRightToolbarButton(ModernClickWidget button) {
    add(button);
    mRightToolbarButtons.add(button);
  }

  /**
   * Adds the quick access button.
   *
   * @param button the button
   */
  public void addQuickAccessButton(ModernClickWidget button) {
    if (mTitleBar != null) {
      mTitleBar.addQuickAccessButton(button);
    } else {
      _addQuickAccessButton(button);
    }
  }

  /**
   * Adds the quick access button.
   *
   * @param button the button
   */
  private void _addQuickAccessButton(ModernClickWidget button) {
    mQuickAccessButtons.add(button);

    button.addClickListener(new ModernClickListener() {
      @Override
      public final void clicked(ModernClickEvent e) {
        fireClicked(new ModernClickEvent(this, e.getMessage()));
      }
    });

    add(button);

    int x = 0;// QUICK_ACCESS_X;
    // //ModernTheme.getInstance().getClass("widget").getInt("padding");

    for (ModernClickWidget b : mQuickAccessButtons) {
      b.setBounds(x, QUICK_BUTTON_Y, b.getPreferredSize().width, b.getPreferredSize().height);

      x += b.getWidth();
    }

    // mFileTabX += button.getPreferredSize().width;
    mTabStartX += button.getPreferredSize().width;
  }

  /**
   * Adjust title bar.
   */
  private void adjustTitleBar() {
    if (mTitleBar != null) {
      mTitleBar.setBounds(0, 0, getWidth(), TAB_HEIGHT);
    }
  }

  /**
   * Alter tab layout.
   */
  private void alterTabLayout() {
    if (mSelectedTab < 1) {
      return;
    }

    if (mMode == RibbonMode.TABS) {
      mTabsPanel.setBounds(mWindow.getInsets().left + ModernComponent.PADDING, TAB_BODY_Y,
          mWindow.getWidth() - mWindow.getInsets().left - mWindow.getInsets().right - ModernComponent.DOUBLE_PADDING, mToolbarHeight);
    } else {
      mTabsPanel.setBounds(ModernComponent.PADDING, TAB_BODY_Y, mRect.getW() - ModernComponent.DOUBLE_PADDING, mToolbarHeight);
    }

    mWindow.revalidate();
    mWindow.repaint();

    // repaint();
    // revalidate();
  }

  /**
   * Adjust right buttons.
   */
  private void adjustRightButtons() {
    int x = getWidth(); // ModernTheme.getInstance().getClass("widget").getInt("padding");

    for (ModernComponent b : mRightToolbarButtons) {
      x -= b.getWidth();

      b.setBounds(x, QUICK_BUTTON_Y, b.getPreferredSize().width, b.getPreferredSize().height);
    }
  }

  /**
   * Set a help file for the ribbon so it can display help to the user.
   *
   * @param product the new help button enabled
   */
  public void setHelpButtonEnabled(GuiAppInfo product) {
    mHelpButton.setHelpProductName(product);
    mHelpButton.setVisible(true); // product != null);
    mHelpButton.setToolTip(product.getName() + " Help", "Get help with " + product.getName() + ".");
  }

  /**
   * Create a toolbar if it does not exist otherwise return the existing one.
   *
   * @param name the name
   * @return the toolbar
   */
  public final RibbonToolbar getToolbar(String name) {
    String nu = TextUtils.sentenceCase(name); // name.toUpperCase(); //

    Tab tab = mTabs.getTab(nu);

    if (tab != null) {
      return (RibbonToolbar) tab.getComponent();
    }

    addToolbar(nu);

    return getToolbar(nu);
  }

  /**
   * Gets the home toolbar.
   *
   * @return the home toolbar
   */
  public RibbonToolbar getHomeToolbar() {
    return getToolbar(HOME_TOOLBAR);
  }

  /*
   * public final RibbonToolbar getToolbar(String name) { Tab tab =
   * mTabs.getTab(name);
   * 
   * if (tab == null) { return null; }
   * 
   * return (RibbonToolbar)tab.getComponent(); }
   */

  /**
   * Allows components to be appended to an existing tab toolbar.
   *
   * @param index the index
   * @param panel the panel
   */
  public final void addToToolbar(int index, ModernPanel panel) {
    if (mTabs.getTab(index).getComponent().getComponentCount() > 0) {
      mTabs.getTab(index).getComponent().remove(mTabs.getTab(index).getComponent().getComponentCount() - 1);
    }

    mTabs.getTab(index).getComponent().add(panel);

    mTabs.getTab(index).getComponent().add(Box.createHorizontalGlue());

    alterTabLayout();
  }

  /**
   * Adds the toolbar.
   *
   * @param name the name
   */
  private void addToolbar(String name) {
    addToolbar(new RibbonToolbar(this, name));
  }

  /**
   * Add a toolbar to a particular view. Views allow the ribbon to change its
   * contents in context with the the window content.
   *
   * @param toolbar the toolbar
   */
  private void addToolbar(RibbonToolbar toolbar) {
    toolbar.setSize(mSize);

    mTabs.addTab(toolbar.getName(), toolbar, false);
    mTitles.add(toolbar.getName());

    int x = 0;

    for (int w : mTabWidths) {
      x += w + TAB_GAP;
    }

    int tabWidth = getTabWidth(toolbar.getName());

    mTabWidths.add(tabWidth);
    mTabStarts.add(x);

    setup();
  }

  /**
   * Removes the toolbar.
   *
   * @param index the index
   */
  public final void removeToolbar(int index) {
    if (index < 1) {
      return;
    }

    mTabs.removeTab(mTitles.get(index));
    mTitles.remove(index);
    mTabStarts.remove(index);
    mTabWidths.remove(index);

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    changeTab(1);

    repaint();
    revalidate();
  }

  /**
   * Returns the number of tabs excluding the File tab which is pseudo tab.
   *
   * @return the tab count
   */
  public int getTabCount() {
    return mTabs.getTabCount();
  }

  public TabsModel getTabsModel() {
    return mTabs;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawBackground(java.awt.Graphics2D)
   */

  // @Override
  // public void drawBackground(Graphics2D g2) {
  // fill(g2, Color.WHITE);
  // }

  /**
   * @Override public void drawForegroundAA(Graphics2D g2) {
   * 
   * 
   *           int x; int y = TAB_BODY_Y - TAB_HEIGHT + (TAB_HEIGHT +
   *           g2.getFontMetrics().getAscent()) / 2; int tabWidth;
   * 
   *           // // Paint the file menu item //
   * 
   *           //int textX = x + TAB_PADDING_X;
   * 
   *           //g2.setColor(Color.WHITE); //g2.drawString(FILE_TOOLBAR, textX,
   *           y);
   * 
   * 
   *           // // Paint the tabs. //
   * 
   *           //x = mTabStartX;
   * 
   *           for (int i = 0; i < mTitles.size(); ++i) { x = mTabStartX +
   *           mTabStarts.get(i); tabWidth = mTabWidths.get(i);
   * 
   *           // Render the headings
   * 
   *           //if (mToolbarVisible && i == mSelectedTab) { //
   *           g2.setColor(BAR_BACKGROUND); //} else { //g2.setColor(i ==
   *           mSelectedTab ? BAR_BACKGROUND : TEXT_COLOR); //Color.WHITE); //}
   * 
   *           if (i == mSelectedTab) {
   *           g2.setColor(MaterialService.getInstance().getColor("ribbon-menu-font-selected"));
   *           } else if (i == mHighlightedTab) {
   *           g2.setColor(MaterialService.getInstance().getColor("ribbon-menu-font-highlight"));
   *           } else {
   *           g2.setColor(MaterialService.getInstance().getColor("ribbon-menu-font"));
   *           }
   * 
   *           int textX = x + (tabWidth -
   *           g2.getFontMetrics().stringWidth(mTitles.get(i))) / 2;
   * 
   *           g2.drawString(mTitles.get(i), textX, y); } }
   */

  /**
   * Creates the tab.
   *
   * @param x      the x
   * @param y      the y
   * @param width  the width
   * @param height the height
   * @return the general path
   */
  protected GeneralPath createTab(int x, int y, int width, int height) {
    GeneralPath path = new GeneralPath();

    path.moveTo(x + ROUNDING, y);
    path.lineTo(x + width - ROUNDING, y);
    path.append(new Arc2D.Double(x + width - ROUNDING, y, ROUNDING, ROUNDING, 90, -90, Arc2D.OPEN), true);
    path.lineTo(x + width, y + height);
    path.lineTo(x, y + height);
    path.lineTo(x, y + ROUNDING);
    path.append(new Arc2D.Double(x, y, ROUNDING, ROUNDING, 180, -90, Arc2D.OPEN), true);
    path.closePath();

    /*
     * path.moveTo(x, y); path.lineTo(x + width, y); path.lineTo(x + width, y +
     * height); path.lineTo(x, y + height); path.closePath();
     */

    return path;
  }

  /**
   * Change tab actually changes the tab view and allows access to the file view.
   *
   * @param selectedTab the selected tab
   */
  private void changeTab(int selectedTab) {
    if (selectedTab < 0) {
      return;
    }

    if (selectedTab == 0) {
      ((ModernWindow) ModernWindow.getParentWindow(this)).showRibbonMenu();
    } else {
      mTabs.changeTab(selectedTab);

      if (mMode == RibbonMode.TABS_COMMANDS) {
        // if we change tabs (but not the file tab), ensure the tab is visible
        if (!mToolbarVisible) {
          toggleVisible();
        }
      }
    }
  }

  /**
   * Hightlight tab.
   *
   * @param index the index
   */
  public void hightlightTab(int index) {
    fireHighlighted(new HighlightEvent(this, index)); // mTabs.fireTabHighlighted(new
    // TabEvent(this,
    // mTabs.getTab(index)));
  }

  /**
   * Change tab.
   *
   * @param name the name
   */
  public void changeTab(String name) {
    if (name.equals("File")) {
      ((ModernWindow) ModernWindow.getParentWindow(this)).showRibbonMenu();
    } else {
      mTabs.changeTab(name);

      // if we change tabs (but not the file tab), ensure the tab is visible

      if (mMode == RibbonMode.TABS_COMMANDS) {
        if (mTabs.getTab(name) != null && !mToolbarVisible) {
          toggleVisible();
        }
      }
    }
  }

  /**
   * Set the selected tab. The first tab is "File" but this is a special tab that
   * cannot be selected so the first selectable tab is 1.
   *
   * @param index the new selected index
   */
  public final void setSelectedIndex(int index) {

    // must be at least 1 since tab 0 is the special file tab
    changeTab(Mathematics.bound(index, 1, mTabs.getTabCount()));
  }

  /**
   * Sets the selected tab.
   *
   * @param name the new selected tab
   */
  public void setSelectedTab(String name) {
    changeTab(name);
  }

  /**
   * Gets the selected index.
   *
   * @return the selected index
   */
  public final int getSelectedIndex() {
    return mSelectedTab;
  }

  /**
   * Flips back and forth between the toolbar being visible and invisible.
   */
  private void toggleVisible() {

    Dimension d = new Dimension(Short.MAX_VALUE, TAB_BODY_Y);

    if (mMode == RibbonMode.TABS) {
      setPreferredSize(d);
      setMaximumSize(d);
    } else {
      if (mToolbarVisible) {
        // tabHeight = getHeight();

        setPreferredSize(d);
        setMaximumSize(d);

        // new ModernChangeSizeNoAnimation(this, getPreferredSize(), new
        // Dimension(getWidth(), TAB_BODY_Y)).start();
      } else {
        setPreferredSize(new Dimension(Short.MAX_VALUE, mHeight));
        // new ModernChangeSizeNoAnimation(this, getPreferredSize(), new
        // Dimension(getWidth(), mHeight)).start();
      }

      // mHideButton.setHidden(mToolbarVisible);

      mToolbarVisible = !mToolbarVisible;
    }

    revalidate();
    repaint();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.tabs.TabEventListener#tabChanged(org.abh.lib.ui.
   * modern. tabs.TabEvent)
   */
  @Override
  public void tabChanged(TabEvent e) {
    if (mTabs.getSelectedIndex() == -1) {
      return;
    }

    mSelectedTab = mTabs.getSelectedIndex();

    mTabsPanel.getViewPanel().changeView(mTabs.getSelectedTab().getComponent());

    if (mMode == RibbonMode.TABS) {
      mWindow.getLayeredPane().remove(mTabsPanel);
      mWindow.getLayeredPane().add(mTabsPanel, new Integer(250));
      mWindow.getLayeredPane().moveToFront(mTabsPanel);
      mWindow.getLayeredPane().revalidate();
      mWindow.getLayeredPane().repaint();
    }

    repaint();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.tabs.TabEventListener#tabHighlighted(org.abh.common.ui.
   * tabs .TabEvent)
   */
  @Override
  public void tabHighlighted(TabEvent e) {
    repaint();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.tabs.TabEventListener#tabAdded(org.abh.lib.ui.modern.
   * tabs.TabEvent)
   */
  @Override
  public void tabAdded(TabEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.tabs.TabEventListener#tabRemoved(org.abh.lib.ui.
   * modern. tabs.TabEvent)
   */
  @Override
  public void tabRemoved(TabEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.tabs.TabEventListener#tabResized(org.abh.lib.ui.
   * modern. tabs.TabEvent)
   */
  @Override
  public void tabResized(TabEvent e) {
    // TODO Auto-generated method stub

  }

  /**
   * Gets the title bar.
   *
   * @return the title bar
   */
  public ModernTitleBar getTitleBar() {
    return mTitleBar;
  }

  /**
   * Sets the title.
   *
   * @param title the new title
   */
  public void setTitle(String title) {
    if (mTitleBar != null) {
      mTitleBar.setTitle(title);
    }
  }

  /**
   * Gets the tab width.
   *
   * @param name the name
   * @return the tab width
   */
  public static int getTabWidth(String name) {
    return ((getStringWidth(TAB_FONT, name) + TOTAL_TAB_PADDING_X) / 10 + 1) * 10;
  }

  /**
   * The icon is automatically scaled to be appropriate for the toolbar.
   *
   * @param icon the icon
   * @return the modern icon
   */
  public static ModernIcon scaleIcon(ModernIcon icon) {
    if (icon.getWidth() <= Ribbon.COMPACT_ICON_SIZE) {
      return icon;
    } else {
      return new RasterIcon(icon, Ribbon.COMPACT_ICON_SIZE);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.tabs.TabEventProducer#addTabListener(org.abh.common.ui.
   * tabs .TabEventListener)
   */
  @Override
  public void addTabListener(TabEventListener l) {
    mTabs.addTabListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.tabs.TabEventProducer#removeTabListener(org.abh.common. ui.
   * tabs.TabEventListener)
   */
  @Override
  public void removeTabListener(TabEventListener l) {
    mTabs.removeTabListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.tabs.TabEventProducer#fireTabAdded(org.abh.common.ui.
   * tabs. TabEvent)
   */
  @Override
  public void fireTabAdded(TabEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.tabs.TabEventProducer#fireTabRemoved(org.abh.common.ui.
   * tabs .TabEvent)
   */
  @Override
  public void fireTabRemoved(TabEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.tabs.TabEventProducer#fireTabResized(org.abh.common.ui.
   * tabs .TabEvent)
   */
  @Override
  public void fireTabResized(TabEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.tabs.TabEventProducer#fireTabChanged(org.abh.common.ui.
   * tabs .TabEvent)
   */
  @Override
  public void fireTabChanged(TabEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.tabs.TabEventProducer#fireTabHighlighted(org.abh.common.
   * ui. tabs.TabEvent)
   */
  @Override
  public void fireTabHighlighted(TabEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addHighlightListener(HighlightListener l) {
    mHighlightListeners.addHighlightListener(l);
  }

  @Override
  public void removeHighlightListener(HighlightListener l) {
    mHighlightListeners.removeHighlightListener(l);
  }

  @Override
  public void fireHighlighted(HighlightEvent e) {
    mHighlightListeners.fireHighlighted(e);
  }

  /*
   * @Override public synchronized void showToolTip(Component source, Component
   * tooltipPanel) { hideToolTips(source);
   * 
   * Rectangle wb = mWindow.getBounds();
   * 
   * Point p = source.getLocationOnScreen();
   * 
   * if (p.x + tooltipPanel.getPreferredSize().width > wb.x + wb.width) { p.x =
   * source.getWidth() - tooltipPanel.getPreferredSize().width; }
   * 
   * p.y = getLocationOnScreen().y + getHeight();
   * 
   * showToolTip(this, tooltipPanel, p); }
   * 
   * @Override public synchronized void showToolTip(Component source, Component
   * tooltipPanel, Point location) { getToolTipModel().showToolTip(source,
   * tooltipPanel, location); }
   * 
   * @Override public synchronized void hideToolTips(Component source) {
   * getToolTipModel().hideToolTips(source); }
   */

  @Override
  public void tooltipShown(ModernToolTipEvent e) {
    showToolTip(createToolTipEvent(e.getTooltip(), tooltipP(e)));
  }

  @Override
  public void tooltipAdded(ModernToolTipEvent e) {
    addToolTip(createToolTipEvent(e.getTooltip(), tooltipP(e)));
  }

  private Point tooltipP(ModernToolTipEvent e) {
    Rectangle wb = mWindow.getBounds();

    Component source = e.getSource();
    Component tooltip = e.getTooltip();

    Point p = source.getLocationOnScreen();
    SwingUtilities.convertPointFromScreen(p, mWindow.getLayeredPane());

    Point p2 = new Point(); // getLocationOnScreen();
    // SwingUtilities.convertPointFromScreen(p2, mWindow.getLayeredPane());

    p2.x = 0; // p.x;
    p2.y = getHeight();

    int pw = tooltip.getPreferredSize().width;

    // If tooltip would go out of bounds, shift it back so that it is flush
    // with the right edge of the source
    if (p2.x + pw > wb.width) {
      p2.x += source.getWidth() - pw;
    }

    return p2;
  }

  @Override
  public void tooltipHidden(ModernToolTipEvent e) {
    // Forward
    hideToolTip(createToolTipEvent(e.getTooltip()));
  }

  @Override
  public void tooltipsHidden(ModernToolTipEvent e) {
    // Forward
    hideToolTips(createToolTipEvent());
  }
}
