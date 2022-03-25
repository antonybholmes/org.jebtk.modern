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
package org.jebtk.modern.button;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.menu.ModernPopup2;
import org.jebtk.modern.menu.ModernPopupMenu2;
import org.jebtk.modern.theme.RenderMode;
import org.jebtk.modern.tooltip.ModernToolTipEvent;
import org.jebtk.modern.tooltip.ModernToolTipListener;
import org.jebtk.modern.tooltip.ToolTipService;

// TODO: Auto-generated Javadoc
/**
 * Implementation of a drop down widget for showing a menu, but without the UI
 * in place.
 * 
 * @author Antony Holmes
 *
 */
public abstract class ModernDropDownWidget2 extends ModernButtonWidget {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant DROP_DOWN_TRIANGLE_OFFSET.
   */
  public static final int DROP_DOWN_TRIANGLE_OFFSET = 15;

  /**
   * The constant DROP_DOWN_TRIANGLE_WIDTH.
   */
  public static final int DROP_DOWN_TRIANGLE_WIDTH = 6;

  /**
   * The constant DROP_DOWN_TRIANGLE_HEIGHT.
   */
  public static final int DROP_DOWN_TRIANGLE_HEIGHT = 3;

  /**
   * The constant STATE_NORMAL.
   */
  protected static final int STATE_NORMAL = 0;

  /**
   * The constant STATE_BUTTON_PRESSED.
   */
  protected static final int STATE_BUTTON_PRESSED = 1;

  /**
   * The constant STATE_BUTTON_MENU_VISIBLE.
   */
  protected static final int STATE_BUTTON_MENU_VISIBLE = 2;

  /**
   * The state.
   */
  protected int state = STATE_NORMAL;

  /**
   * The member menu.
   */
  protected ModernPopup2 mMenu;

  /**
   * The member popup shown.
   */
  public boolean mPopupShown = false;

  /**
   * The pressed.
   */
  protected boolean mPressed;

  /**
   * The class MouseEvents.
   */
  private class MouseEvents extends MouseAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(MouseEvent e) {
      dropButtonClicked(e);
    }
  }

  /**
   * The class ActionEvents.
   */
  private class ActionEvents extends AbstractAction {

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
      showMenu();
    }
  }

  /**
   * The class MenuEvents.
   */
  private class MenuEvents implements ModernClickListener {

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
     * modern .event.ModernClickEvent)
     */
    @Override
    public void clicked(ModernClickEvent e) {
      fireClicked(e);
    }
  }

  /**
   * Instantiates a new modern drop down widget.
   *
   * @param text1 the text1
   * @param icon  the icon
   * @param menu  the menu
   */
  public ModernDropDownWidget2(String text1, ModernIcon icon, ModernPopupMenu2 menu) {

    this(text1, icon);

    setMenu(menu);
  }

  /**
   * Instantiates a new modern drop down widget.
   *
   * @param text1 the text1
   * @param icon  the icon
   */
  public ModernDropDownWidget2(String text1, ModernIcon icon) {
    super(text1, icon);

    init();
  }

  /**
   * Instantiates a new modern drop down widget.
   *
   * @param text1 the text1
   * @param menu  the menu
   */
  public ModernDropDownWidget2(String text1, ModernPopupMenu2 menu) {
    super(text1);

    setMenu(menu);

    init();
  }

  /**
   * Instantiates a new modern drop down widget.
   *
   * @param icon the icon
   * @param menu the menu
   */
  public ModernDropDownWidget2(ModernIcon icon, ModernPopupMenu2 menu) {
    super(icon);

    setMenu(menu);

    init();
  }

  /**
   * Instantiates a new modern drop down widget.
   *
   * @param text1 the text1
   */
  public ModernDropDownWidget2(String text1) {
    super(text1);

    init();
  }

  /**
   * Instantiates a new modern drop down widget.
   *
   * @param icon the icon
   */
  public ModernDropDownWidget2(ModernIcon icon) {
    super(icon);

    init();
  }

  /**
   * Setup.
   */
  private void init() {
    // Only the button can respond to the click event

    // Disable tooltips so that we are not bothered by popups over popups.
    setToolTipsEnabled(false);

    addMouseListener(new MouseEvents());

    getInputMap(WHEN_FOCUSED).put(KeyStroke.getKeyStroke("ENTER"), "enter_pressed");
    getActionMap().put("enter_pressed", new ActionEvents());

    final ModernDropDownWidget2 source = this;

    ModernToolTipListener l = new ModernToolTipListener() {

      @Override
      public void tooltipShown(ModernToolTipEvent e) {
        tooltipAdded(e);
      }

      @Override
      public void tooltipAdded(ModernToolTipEvent e) {
        // If some other tooltip is being shown, we should reset.
        mPopupShown = e.getSource().equals(source);
        mHighlight = mPopupShown;

        repaint();
      }

      @Override
      public void tooltipHidden(ModernToolTipEvent e) {
        hide(e);
      }

      @Override
      public void tooltipsHidden(ModernToolTipEvent e) {
        hide(e);
      }

      private void hide(ModernToolTipEvent e) {
        mPopupShown = false;
        mHighlight = mPopupShown;

        repaint();
      }
    };

    // Listen for all
    ToolTipService.getInstance().addAllToolTipListener(l);

    // setAnimations("dropdown-button-2");
  }

  @Override
  protected void mouseShowToolTip() {
    // Don't hide tooltip if popup is shown as this will affect the UI.
    if (!getPopupShown()) {
      super.mouseShowToolTip();
    }
  }

  @Override
  protected void mouseHideToolTips() {
    // Don't hide tooltip if popup is shown as this will affect the UI.
    if (!getPopupShown()) {
      super.mouseHideToolTips();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.widget.ModernClickWidget#getRenderMode()
   */
  @Override
  public RenderMode getRenderMode() {
    if (isSelected() || mPopupShown) {
      return RenderMode.SELECTED;
    } else if (mHighlight) {
      return RenderMode.HIGHLIGHT;
    } else {
      return RenderMode.NONE;
    }
  }

  /**
   * Gets the popup.
   *
   * @return the popup
   */
  public ModernPopup2 getPopup() {
    return mMenu;
  }

  /**
   * Show menu.
   */
  public void showMenu() {
    mPopupShown = true;

    // System.err.println("show menu " + mMenu.isVisible());

    showToolTip(createToolTipEvent(mMenu));
  }

  @Override
  public boolean getPopupShown() {
    return mPopupShown;
  }

  /**
   * Sets the menu.
   *
   * @param menu the new menu
   */
  public void setMenu(ModernPopup2 menu) {
    if (menu == null) {
      return;
    }

    mMenu = menu;
    mMenu.addClickListener(new MenuEvents());
  }

  /**
   * Called when the drop button is clicked.
   * 
   * @param e
   */
  protected void dropButtonClicked(MouseEvent e) {
    if (e.isPopupTrigger()) {
      return;
    }

    showMenu();
  }
}
