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
package org.jebtk.modern.window;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.AWTEventListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import org.jebtk.modern.BorderService;
import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.contentpane.ModernHContentPane;
import org.jebtk.modern.dialog.ModernDialogWindow;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.help.GuiAppInfo;
import org.jebtk.modern.panel.ModernPanel;
import org.jebtk.modern.ribbon.RibbonFileMenu;
import org.jebtk.modern.theme.ThemeService;
import org.jebtk.modern.tooltip.ModernToolTipEvent;
import org.jebtk.modern.tooltip.ModernToolTipListener;
import org.jebtk.modern.tooltip.ModernToolTipPanel;
import org.jebtk.modern.tooltip.ToolTipLevel;
import org.jebtk.modern.tooltip.ToolTipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.jebtk.modern.dialog.IModernDialogConstructor;

/**
 * All windowed apps should inherit from this.
 *
 * @author Antony Holmes
 */
public class ModernWindow extends JFrame implements IModernDialogConstructor, ModernToolTipListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant WINDOW_BORDER.
   */
  public static final Border WINDOW_BORDER = BorderService.getInstance()
      .createLineBorder(ThemeService.getInstance().getColors().getGray(6));

  /**
   * The member layered pane.
   */
  // p//rivate JLayeredPane mLayeredPane;

  /**
   * The member tooltips.
   */
  private final List<Component> mTooltips = new ArrayList<>();

  /**
   * The member app info.
   */
  protected GuiAppInfo mAppInfo;

  /**
   * The member cards.
   */
  protected ModernPanel mCards = new ModernPanel(new CardLayout());

  /**
   * The constant MENU_CARD.
   */
  private static final String MENU_CARD = "menu_card";

  /**
   * The constant CONTENT_CARD.
   */
  private static final String CONTENT_CARD = "content_card";

  /**
   * The member title bar panel.
   */
  // private ModernPanel mHeaderPanel = new ModernPanel();

  /**
   * All content is ultimately a child of this. Content is never directly added to
   * the JFrame. This is the default view.
   */
  protected ModernPanel mWindowPanel = new ModernWindowPanel(); // ModernGradientPanel();

  private final ModernComponent mContentPanel = new ModernComponent();

  private final ModernHContentPane mTabsPane = new ModernHContentPane();

  /**
   * The member cl.
   */
  private final CardLayout mCl;

  /**
   * The member ribbon menu.
   */
  protected RibbonFileMenu mRibbonMenu;

  /**
   * The member sub title.
   */
  private String mSubTitle;

  /** The m center. */
  private Component mCenter = null;

  /**
   * Allows multiple items such as toolbars and ribbons to be added to the top of
   * the window within the {@code CONTENT} context.
   */
  // protected VBoxAutoWidth mHeaderContainer;

  /**
   * The constant LOG.
   */
  private static final Logger LOG = LoggerFactory.getLogger(ModernWindow.class);

  /**
   * The class MenuActions.
   */
  private class MenuActions implements ModernClickListener {

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
     * modern .event.ModernClickEvent)
     */
    @Override
    public void clicked(ModernClickEvent e) {
      showContent();
    }

  }

  /**
   * The class WindowEvents.
   */
  private class WindowEvents extends WindowAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
     */
    @Override
    public void windowClosing(WindowEvent e) {
      close();
    }
  }

  private class AllMouseEvents implements AWTEventListener {
    @Override
    public void eventDispatched(AWTEvent e) {
      if (e.getID() == MouseEvent.MOUSE_PRESSED) {
        // Point l = MouseInfo.getPointerInfo().getLocation();

        // SwingUtilities.convertPointFromScreen(l, mContentPane);

        // Component component =
        // SwingUtilities.getDeepestComponentAt(
        // mContentPane,
        // l.x,
        // l.y);

        // If there is nothing underneath, or the component is different
        // to the one that generated the mouse event, assume that means we
        // are clicking outside the bounds of the tooltip so we can hide
        // all the tooltips to replicate a loss of focus event
        // if (component == null || !component.equals(e.getSource())) {
        // Hide all tooltips
        doHideTooltips();
        // }
      }
    }
  }

  /**
   * Instantiates a new modern window.
   *
   * @param appInfo the app info
   */
  public ModernWindow(GuiAppInfo appInfo) {
    setAppInfo(appInfo);

    // To implement the card interface we use a card layout with two cards
    // MENU and CONTENT. CONTENT is the default view for adding UI elements
    // whereas MENU offers a ribbon like file menu for import/export etc.

    mRibbonMenu = new RibbonFileMenu(this);

    mCards.add(mRibbonMenu, MENU_CARD);
    mRibbonMenu.addClickListener(new MenuActions());
    mCards.add(mWindowPanel, CONTENT_CARD);

    // Window uses card layout
    getContentPane().add(mCards, BorderLayout.CENTER);

    mCl = (CardLayout) mCards.getLayout();
    mCl.show(mCards, CONTENT_CARD);

    // By default,
    mContentPanel.setBody(mTabsPane);
    // mContentPanel.setBorder(ModernWidget.BORDER);

    setBody(mContentPanel);

    // mHeaderContainer = new WindowVBoxAutoWidth(this);
    // getWindowContentPanel().add(mHeaderContainer, BorderLayout.PAGE_START);

    addWindowListener(new WindowEvents());

    // We hook into the close event so that cleanup can be done before
    // triggering a close
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    // Register existence
    WindowService.getInstance().register(this);

    // Register to receive tooltips. The tooltip system can directly messages
    // to multiple sources. Normally the current window captures the events
    // and shows a tooltip
    ToolTipService.getInstance().addToolTipListener(this);

    // Listen for clicking anywhere on the window to get rid of the tool tip
    Toolkit.getDefaultToolkit().addAWTEventListener(new AllMouseEvents(), AWTEvent.MOUSE_EVENT_MASK);

    addComponentListener(new ComponentAdapter() {

      @Override
      public void componentResized(ComponentEvent e) {
        doHideTooltips();
      }

      @Override
      public void componentHidden(ComponentEvent e) {
        doHideTooltips();
      }
    });
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.dialog.ModernDialogConstructor#init()
   */
  @Override
  public void init() {
    // Do nothing
  }

  /**
   * Gets the app info.
   *
   * @return the app info
   */
  public GuiAppInfo getAppInfo() {
    return mAppInfo;
  }

  /**
   * Sets the app info.
   *
   * @param appInfo the new app info
   */
  public void setAppInfo(GuiAppInfo appInfo) {
    mAppInfo = appInfo;

    setIconImage(getAppInfo().getIcon().getImage(32));

    setTitle(getAppInfo().getName());
  }

  /**
   * Set the window title but include the main app title.
   *
   * @param subTitle the new sub title
   */
  public void setSubTitle(String subTitle) {
    setTitle(subTitle + " - " + getAppInfo().getName());

    mSubTitle = subTitle;
  }

  /**
   * Gets the sub title.
   *
   * @return the sub title
   */
  public String getSubTitle() {
    return mSubTitle;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.Frame#setTitle(java.lang.String)
   */
  @Override
  public void setTitle(String title) {
    mSubTitle = title;

    super.setTitle(title);
  }

  /**
   * Gets the ribbon menu.
   *
   * @return the ribbon menu
   */
  public RibbonFileMenu getRibbonMenu() {
    return mRibbonMenu;
  }

  /**
   * Show ribbon menu.
   */
  public void showRibbonMenu() {
    mCl.show(mCards, MENU_CARD);

    mRibbonMenu.setActiveMenuItem();
  }

  /**
   * Sets the header.
   *
   * @param c the new header
   */
  protected void setHeader(Component c) {
    // mHeaderContainer.add(c);
    getWindowPanel().add(c, BorderLayout.PAGE_START);
  }

  protected void setContentHeader(Component c) {
    getContentPanel().setHeader(c);
  }

  /**
   * Sets the body UI content of the main window. This method should only be used
   * for overriding the default behaviour and use of tabs. It is better to call
   * the setContent methods.
   *
   * @param c the new body
   */
  public void setBody(Component c) {
    if (mCenter != null) {
      getWindowPanel().remove(mCenter);
    }

    mCenter = c;

    getWindowPanel().add(c, BorderLayout.CENTER);
    getWindowPanel().validate();
    getWindowPanel().repaint();
  }

  /**
   * Set the body of the content panel. The body is normally a tabbed pane element
   * to allow for multi-column layouts which can be accessed via the
   * {@code tabsPane()} method. Calling this method will destroy the tabbed
   * layout.
   * 
   * @param c
   */
  public void setContentBody(Component c) {
    getContentPanel().setBody(c);
  }

  /**
   * Sets the footer.
   *
   * @param c the new footer
   */
  public void setFooter(Component c) {
    getWindowPanel().add(c, BorderLayout.PAGE_END);
  }

  /**
   * Show the content view.
   */
  public void showContent() {
    mCl.show(mCards, CONTENT_CARD);
  }

  /**
   * Returns the panel that should have the main window content added to it.
   *
   * @return the content panel
   */
  public ModernPanel getWindowPanel() {
    return mWindowPanel;
  }

  /**
   * Returns the panel within the window content panel where the main ui controls
   * go. This panel typically appears below any tool bars.
   * 
   * @return
   */
  public ModernComponent getContentPanel() {
    return mContentPanel;
  }

  /**
   * Returns the default tabs within the content panel. For multi-column
   * interfaces such as UIs with a sidebar and main panel, use this to create the
   * columns.
   * 
   * @return
   */
  public ModernHContentPane tabsPane() {
    return mTabsPane;
  }

  public void setCenterTab(JComponent c) {
    tabsPane().tabs().setCenterTab(c);
  }

  /**
   * Set the center content on top of a simple white panel.
   * 
   * @param c
   */
  public void setPanel(Component c) {
    setCenterTab(new ModernPanel(c, ModernWidget.DOUBLE_BORDER));
  }

  /**
   * Sets the title bar.
   */
  // public void setTitleBar(ModernTitleBar titlebar) {
  // mHeaderPanel.setHeader(titlebar);
  // }

  /**
   * Sets the ribbon.
   *
   * @param ribbon the new ribbon
   */
  // public void setRibbon(Component ribbon) {
  // mHeaderPanel.setBody(ribbon);
  // }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.window.ModernDialogConstructor#createUi()
   */
  @Override
  public void createUi() {
    // Do nothing
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.window.ModernDialogConstructor#createMenus()
   */
  @Override
  public void createMenus() {
    // Do nothing
  }

  @Override
  public void tooltipShown(ModernToolTipEvent e) {
    if (e.getP() != null) {
      SwingUtilities.convertPointToScreen(e.getP(), e.getSource());

      Point p = toolTipPosFromScreen(e.getP(), e.getTooltip());

      showToolTip(e.getSource(), e.getTooltip(), p);
    } else {
      showToolTip(e.getSource(), e.getTooltip());
    }
  }

  @Override
  public void tooltipAdded(ModernToolTipEvent e) {
    if (e.getP() != null) {
      SwingUtilities.convertPointToScreen(e.getP(), e.getSource());

      Point p = toolTipPosFromScreen(e.getP(), e.getTooltip());

      addToolTip(e.getSource(), e.getTooltip(), p);
    } else {
      addToolTip(e.getSource(), e.getTooltip());
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.tooltip.ModernToolTipModel#showToolTip(org.abh.lib. ui.
   * modern.ModernComponent, org.abh.lib.ui.modern.tooltip.ModernToolTipPanel)
   */
  private synchronized void showToolTip(Component source, Component tooltip) {
    showToolTip(source, tooltip, toolTipPos(source, tooltip));
  }

  private synchronized void addToolTip(Component source, Component tooltip) {
    addToolTip(source, tooltip, toolTipPos(source, tooltip));
  }

  /**
   * Where to position the tooltip on screen.
   * 
   * @param source
   * @param tooltip
   * @return
   */
  private synchronized Point toolTipPos(Component source, Component tooltip) {
    return toolTipPosFromScreen(source, source.getLocationOnScreen(), tooltip);
  }

  /**
   * Calculate where the tooltip should be given a screen position which is
   * assumed to be within the bounds of the window.
   * 
   * @param source
   * @param p
   * @param tooltip
   * @return
   */
  private synchronized Point toolTipPosFromScreen(Component source, Point p, Component tooltip) {
    // Put in the context of the window
    SwingUtilities.convertPointFromScreen(p, getLayeredPane());

    Rectangle wb = getBounds();

    Dimension ps = tooltip.getPreferredSize();

    int w = ps.width;
    int h = ps.height;

    if (p.x + w > wb.width) {
      p.x += source.getWidth() - w;
    }

    int sh = source.getHeight();

    // Always show below the component
    p.y += sh;

    if (p.y + h > wb.height) {
      p.y -= sh + h;
    }

    return p;
  }

  private synchronized Point toolTipPosFromScreen(Point p, Component tooltip) {
    // Put in the context of the window
    SwingUtilities.convertPointFromScreen(p, getLayeredPane());

    // Rectangle wb = getBounds();

    // Dimension ps = tooltip.getPreferredSize();

    // int w = ps.width;
    // int h = ps.height;

    // if (p.x + w > wb.width) {
    // p.x += source.getWidth() - w;
    // }

    // int sh = source.getHeight();

    // Always show below the component
    // p.y += sh;

    // if (p.y + h > wb.height) {
    // p.y -= sh + h;
    // }

    return p;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.tooltip.ModernToolTipModel#showToolTip(org.abh.lib. ui.
   * modern.ModernComponent, org.abh.lib.ui.modern.tooltip.ModernToolTipPanel,
   * java.awt.Point)
   */
  private synchronized void showToolTip(Component source, Component tooltip, Point p) {

    // System.err.println("show");

    // Hide any current tips
    hideToolTips(ToolTipLevel.NORMAL);

    addToolTip(source, tooltip, p);
  }

  private synchronized void addToolTip(Component source, Component tooltip, Point p) {
    mTooltips.add(tooltip);

    Dimension ps = tooltip.getPreferredSize();

    tooltip.setBounds(p.x, p.y, ps.width, ps.height);

    getLayeredPane().add(tooltip, JLayeredPane.POPUP_LAYER);

    revalidate();
    repaint();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.tooltip.ModernToolTipModel#hideToolTips(org.abh.lib.
   * ui. modern.ModernComponent)
   */
  private synchronized void hideToolTips(ToolTipLevel l) {
    // System.err.println("hide");

    if (!mTooltips.isEmpty()) {
      for (Component c : mTooltips) {
        removeToolTip(c, l);
      }

      mTooltips.clear();
    }

    revalidate();
    repaint();
  }

  @Override
  public void tooltipHidden(ModernToolTipEvent e) {
    System.err.println("hide tooltip");

    removeToolTip(e.getTooltip(), e.getLevel());

    revalidate();
    repaint();
  }

  /**
   * Remove a tooltip from the cache.
   * 
   * @param e
   * @param c
   */
  private void removeToolTip(Component c, ToolTipLevel level) {
    if (c instanceof ModernToolTipPanel) {
      if (level == ToolTipLevel.FORCE || ((ModernToolTipPanel) c).getAutoHide()) {
        getLayeredPane().remove(c);
      }
    } else {
      getLayeredPane().remove(c);
    }
  }

  /**
   * Hide tool tips.
   */
  @Override
  public synchronized void tooltipsHidden(ModernToolTipEvent e) {
    hideToolTips(e.getLevel());

    validate();
    repaint();
  }

  /**
   * Fire events to hide all tooltips. This method sends the hide event rather
   * than simply destroying any tooltips, hence this is the preferred way to
   * communicate to other components if the tooltips are hidden.
   */
  protected void doHideTooltips() {
    ToolTipService.getInstance().hideToolTips(new ModernToolTipEvent(this, this));
  }

  /**
   * Clear popups.
   */
  /*
   * public void clearPopups() { for (Component c :
   * getLayeredPane().getComponentsInLayer(JLayeredPane.POPUP_LAYER)) {
   * getLayeredPane().remove(c); }
   * 
   * validate(); repaint(); }
   */

  /**
   * Set the current popup.
   *
   * @param component the new popup
   */
  /*
   * public void setPopup(Component component) { for (Component c :
   * getLayeredPane().getComponentsInLayer(JLayeredPane.POPUP_LAYER)) {
   * getLayeredPane().remove(c); }
   * 
   * getLayeredPane().add(component, JLayeredPane.POPUP_LAYER);
   * 
   * validate(); repaint(); }
   */

  /**
   * Terminates the application normally.
   */
  public void exit() {
    exit(0);
  }

  /**
   * Terminate application with a given status code (non zero implies error).
   *
   * @param status the status
   */
  public void exit(int status) {
    System.exit(status);
  }

  /**
   * Close the window. If this is the last windows, it causes the VM to stop as
   * well.
   */
  public void close() {
    close(true);
  }

  /**
   * Close the window and exit application if this is the only window and auto
   * exit is true.
   *
   * @param autoExit the auto exit
   */
  public void close(boolean autoExit) {
    WindowService.getInstance().remove(this);

    setVisible(false);
    dispose();

    if (!autoExit) {
      return;
    }

    if (WindowService.getInstance().size() == 0) {
      LOG.info("Auto exit application; window count 0.");

      exit();
    }
  }

  /**
   * Restart.
   */
  public void restart() {
    close();
  }

  /**
   * Determine the underlying JFrame of a component.
   *
   * @param c the c
   * @return the modern window parent
   */
  public static ModernWindow getModernWindowParent(Container c) {
    Component w = c;

    while (w != null) {
      if (w instanceof ModernWindow) {
        return (ModernWindow) w;
      }

      w = w.getParent();
    }

    return null;
  }

  /**
   * Gets the modern dialog parent.
   *
   * @param c the c
   * @return the modern dialog parent
   */
  public static ModernDialogWindow getModernDialogParent(Container c) {
    Component w = c;

    while (w != null) {
      if (w instanceof ModernDialogWindow) {
        return (ModernDialogWindow) w;
      }

      w = w.getParent();
    }

    return null;
  }

  /**
   * Returns the ModernWindow or ModernDialog the component is a child of.
   *
   * @param c the c
   * @return the parent window
   */
  public static Window getParentWindow(Component c) {

    Component w = c.getParent();

    while (w != null) {
      if (w instanceof JFrame) {
        return (JFrame) w;
      }

      if (w instanceof JDialog) {
        return (JDialog) w;
      }

      if (w instanceof JWindow) {
        return (JWindow) w;
      }

      w = w.getParent();
    }

    return null;

    // return SwingUtilities.getWindowAncestor(c);
  }

  /**
   * Gets the layered pane.
   *
   * @param c the c
   * @return the layered pane
   */
  public static JLayeredPane getLayeredPane(Component c) {
    return getLayeredPane(getParentWindow(c));
  }

  /**
   * Returns the layer pane for a given window,.
   *
   * @param window the window
   * @return the layered pane
   */
  public static JLayeredPane getLayeredPane(Window window) {
    if (window instanceof JDialog) {
      return ((JDialog) window).getLayeredPane();
    } else if (window instanceof JFrame) {
      return ((JFrame) window).getLayeredPane();
    } else if (window instanceof JWindow) {
      return ((JWindow) window).getLayeredPane();
    } else {
      return null;
    }
  }

  /**
   * Finds the glass pane of the container window of a component.
   * 
   * @param c
   * @return
   */
  public static Component getGlassPane(Component c) {
    return getGlassPane(getParentWindow(c));
  }

  /**
   * Returns the layer pane for a given window,.
   *
   * @param window the window
   * @return the layered pane
   */
  public static Component getGlassPane(Window window) {
    if (window instanceof JDialog) {
      return ((JDialog) window).getGlassPane();
    } else if (window instanceof JFrame) {
      return ((JFrame) window).getGlassPane();
    } else if (window instanceof JWindow) {
      return ((JWindow) window).getGlassPane();
    } else {
      return null;
    }
  }

  /**
   * Trigger the window closing events.
   *
   * @param window the window
   */
  public static void close(ModernWindow window) {
    window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
  }

}
