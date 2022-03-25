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

import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.nio.file.Path;

import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import org.jebtk.core.io.PathUtils;
import org.jebtk.core.text.TextUtils;
import org.jebtk.modern.BorderService;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.button.ModernClickWidget;
import org.jebtk.modern.dialog.DialogEventListener;
import org.jebtk.modern.dialog.MessageDialogOkCancelGlassPane;
import org.jebtk.modern.dialog.MessageDialogTaskGlassPane;
import org.jebtk.modern.dialog.MessageDialogType;
import org.jebtk.modern.dialog.ModernMessageDialog;
import org.jebtk.modern.help.GuiAppInfo;
import org.jebtk.modern.panel.CardPanel2;
import org.jebtk.modern.ribbon.Ribbon;
import org.jebtk.modern.status.ModernStatusBar;

/**
 * All windowed apps should inherit from this.
 *
 * @author Antony Holmes
 */
public class ModernRibbonWindow extends ModernWindow {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  private class GlassPaneMouseEvents implements MouseListener {

    private final Container mContentPane;
    private final Component mGlassPane;

    public GlassPaneMouseEvents(Container contentPane, Component glassPane) {
      mContentPane = contentPane;
      mGlassPane = glassPane;
    }

    private void redispatchMouseEvent(MouseEvent e) {
      Point glassPanePoint = e.getPoint();
      Point containerPoint = SwingUtilities.convertPoint(mGlassPane, glassPanePoint, mContentPane);

      if (containerPoint.y < 0) { // we're not in the content pane
        // Could have special code to handle mouse events over
        // the menu bar or non-system window decorations, such as
        // the ones provided by the Java look and feel.
      } else {
        // The mouse event is probably over the content pane.
        // Find out exactly which component it's over.
        Component component = SwingUtilities.getDeepestComponentAt(mContentPane, containerPoint.x, containerPoint.y);

        if (component != null) {
          // Forward events over the check box.
          Point componentPoint = SwingUtilities.convertPoint(mGlassPane, glassPanePoint, component);
          component.dispatchEvent(new MouseEvent(component, e.getID(), e.getWhen(), e.getModifiers(), componentPoint.x,
              componentPoint.y, e.getClickCount(), e.isPopupTrigger()));
        }
      }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      redispatchMouseEvent(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
      System.err.println("glass mouse " + e.getX() + " " + e.getY());
      redispatchMouseEvent(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      redispatchMouseEvent(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
      redispatchMouseEvent(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
      redispatchMouseEvent(e);
    }
  }

  private class GlassPaneMouseMotionEvents implements MouseMotionListener {

    private final Container mContentPane;
    private final Component mGlassPane;

    public GlassPaneMouseMotionEvents(Container contentPane, Component glassPane) {
      mContentPane = contentPane;
      mGlassPane = glassPane;
    }

    private void redispatchMouseEvent(MouseEvent e) {
      Point glassPanePoint = e.getPoint();
      Point containerPoint = SwingUtilities.convertPoint(mGlassPane, glassPanePoint, mContentPane);

      if (containerPoint.y < 0) { // we're not in the content pane
        // Could have special code to handle mouse events over
        // the menu bar or non-system window decorations, such as
        // the ones provided by the Java look and feel.
      } else {
        // The mouse event is probably over the content pane.
        // Find out exactly which component it's over.
        Component component = SwingUtilities.getDeepestComponentAt(mContentPane, containerPoint.x, containerPoint.y);

        if (component != null) {
          // Forward events over the check box.
          Point componentPoint = SwingUtilities.convertPoint(mGlassPane, glassPanePoint, component);
          component.dispatchEvent(new MouseEvent(component, e.getID(), e.getWhen(), e.getModifiers(), componentPoint.x,
              componentPoint.y, e.getClickCount(), e.isPopupTrigger()));
        }
      }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
      redispatchMouseEvent(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
      redispatchMouseEvent(e);
    }
  }

  /**
   * The member message pane.
   */
  // private MessageDialogGlassPane mMessagePane =
  // new MessageDialogGlassPane();

  private static final Border RIBBON_BORDER = BorderService.getInstance().createLineBorder(Ribbon.BAR_BACKGROUND);

  /**
   * The member ribbon.
   */
  protected Ribbon mRibbon = null;

  /** The m status bar. */
  protected ModernStatusBar mStatusBar = new ModernStatusBar();

  private final WindowTabNav mIconTabs;

  private final WindowTabNav mIconTabsRight;

  /**
   * Instantiates a new modern window2.
   *
   * @param appInfo the app info
   */
  public ModernRibbonWindow(GuiAppInfo appInfo) {
    super(appInfo);

    // setGlassPane(mMessagePane);
    mRibbon = new Ribbon(this);
    super.setHeader(mRibbon);

    // getGlassPane().setVisible(true);
    // getGlassPane().addMouseListener(new
    // GlassPaneMouseEvents(getContentPanel(), getGlassPane()));
    // getGlassPane().addMouseMotionListener(new
    // GlassPaneMouseMotionEvents(getContentPanel(), getGlassPane()));

    /*
     * if (UI.CUSTOM_WINDOW_DECORATION) { // Disable chrome setUndecorated(true);
     * 
     * // Add a colored border for aesthetics and to ensure resizing // works from
     * any border location // mContentPanel.setBorder(RIBBON_BORDER); //
     * mRibbonMenu.setBorder(RIBBON_BORDER); mCards.setBorder(RIBBON_BORDER);
     * 
     * new WindowMover(this, mRibbon); new WindowResizer(this); }
     */

    setFooter(mStatusBar);

    setTitle(getTitle());

    mIconTabs = new WindowLeftTabNav(tabsPane());
    mIconTabsRight = new WindowRightTabNav(tabsPane());
  }

  /**
   * Gets the ribbon.
   *
   * @return the ribbon
   */
  public Ribbon getRibbon() {
    return mRibbon;
  }

  /**
   * Gets the status bar.
   *
   * @return the status bar
   */
  public ModernStatusBar getStatusBar() {
    return mStatusBar;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.window.ModernWindow#setTitle(java.lang.String)
   */
  @Override
  public void setTitle(String title) {
    if (mRibbon != null) {
      mRibbon.setTitle(title);
    }

    super.setTitle(title);
  }

  /**
   * Gets the title bar.
   *
   * @return the title bar
   */
  public ModernTitleBar getTitleBar() {
    return mRibbon.getTitleBar();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.window.ModernWindow#setBody(java.awt.Component)
   */
  // @Override
  // public void setBody(Component c) {
  // super.setBody(new TopShadowPanel(c));
  // }

  /**
   * Set the center card panel.
   * 
   * @param c
   */
  public void setCard(Component c) {
    setCenterTab(new CardPanel2(c, ModernWidget.DOUBLE_BORDER));
  }

  /**
   * Gets the icon tabs pane that sits on the left of the window. This is
   * automatically created if it does not exist.
   * 
   * @return
   */
  public WindowTabNav getIconTabs() {
    return mIconTabs;
  }

  public WindowTabNav getIconTabsRight() {
    return mIconTabsRight;
  }

  /**
   * Add a quick access button. Depending on the window decoration this will
   * automatically be either on the left side of the title bar or the ribbon
   * itself.
   *
   * @param button the button
   */
  public void addQuickAccessButton(ModernClickWidget button) {
    // Add the quick access buttons to the title bar rather than the
    // ribbon.

    mRibbon.addQuickAccessButton(button);

    // super.addQuickAccessButton(button);
  }

  // public void setRibbon(Ribbon2 ribbon, RibbonFileMenu menu) {
  // setHeader(ribbon);
  // setRibbonMenu(menu);
  // }

  /**
   * Creates the file saved dialog.
   *
   * @param file the file
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public void createFileSavedDialog(Path file) throws IOException {
    createInformationDialog(
        TextUtils.singleQuote(ModernMessageDialog.truncate(PathUtils.getName(file))) + " was saved.");
  }

  /**
   * Creates the file exists dialog.
   *
   * @param file the file
   * @param l    the l
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public void createFileExistsDialog(Path file, DialogEventListener l) throws IOException {
    createOkCancelDialog(TextUtils.singleQuote(ModernMessageDialog.truncate(PathUtils.getName(file)))
        + " already exists. Do you want to replace it?", l);
  }

  /**
   * Creates the ok cancel dialog.
   *
   * @param message the message
   * @param l       the l
   * @return the message dialog ok cancel glass pane
   */
  public MessageDialogOkCancelGlassPane createOkCancelDialog(String message, DialogEventListener l) {
    MessageDialogOkCancelGlassPane searchPane = new MessageDialogOkCancelGlassPane();

    setGlassPane(searchPane);

    searchPane.setVisible(true);

    searchPane.showMessage(message, l);

    return searchPane;
  }

  /**
   * Creates the information dialog.
   *
   * @param message the message
   */
  public void createInformationDialog(String message) {
    createDialog(message, MessageDialogType.INFORMATION);
  }

  /**
   * Create a message overlay over the window.
   *
   * @param message the message
   * @param type    the type
   */
  public void createDialog(String message, MessageDialogType type) {
    // setGlassPane(mMessagePane);

    // mMessagePane.showMessage(message);
  }

  /**
   * Creates the task dialog.
   *
   * @param message the message
   * @return the message dialog task glass pane
   */
  public MessageDialogTaskGlassPane createTaskDialog(final String message) {
    MessageDialogTaskGlassPane pane = new MessageDialogTaskGlassPane();

    setGlassPane(pane);

    invalidate();
    repaint();

    pane.showMessage(message);

    // setVisible(true);

    // SwingUtilities.invokeLater(new TaskPaneRunnable(message, searchPane));

    // setGlassPane(searchPane);

    // searchPane.setVisible(true);

    // searchPane.showMessage(message);

    return pane;
  }
}
