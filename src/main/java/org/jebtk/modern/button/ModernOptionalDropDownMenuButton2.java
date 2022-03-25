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

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import org.jebtk.modern.AssetService;
import org.jebtk.modern.UI;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.graphics.icons.TriangleDownVectorIcon;
import org.jebtk.modern.menu.ModernPopupMenu2;
import org.jebtk.modern.theme.ModernTheme;

// TODO: Auto-generated Javadoc
/**
 * Represents a button with a click action and an optional drop down menu
 * component.
 * 
 * @author Antony Holmes
 *
 */
public class ModernOptionalDropDownMenuButton2 extends ModernDropDownWidget2 {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The Constant TRIANGLE_ICON. */
  protected static final ModernIcon TRIANGLE_ICON = AssetService.getInstance().loadIcon(TriangleDownVectorIcon.class,
      16);

  /**
   * The primary button.
   */
  public boolean mPrimaryButton = false;

  /**
   * The constant SECONDARY_BUTTON_WIDTH.
   */
  public static final int SECONDARY_BUTTON_WIDTH = 16;

  /**
   * The constant PRIMARY_BUTTON.
   */
  public static final String PRIMARY_BUTTON = "primary_button";

  /**
   * The constant SIZE.
   */
  private static final Dimension SIZE = ModernTheme.loadDimension("theme.button.dimensions.dropdown-icon-only");

  /**
   * The divider location.
   */
  public int mDividerLocation = -1;

  /** The m secondary button width. */
  protected int mSecondaryButtonWidth;

  /**
   * The class MouseMotionEvents.
   */
  private class MouseMotionEvents implements MouseMotionListener {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseMoved(MouseEvent e) {
      if (!isEnabled()) {
        return;
      }

      mHighlight = true;

      mPrimaryButton = e.getX() < mDividerLocation;

      repaint();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.
     * MouseEvent)
     */
    @Override
    public void mouseDragged(MouseEvent e) {
      // TODO Auto-generated method stub

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
    public void componentResized(ComponentEvent e) {
      mDividerLocation = getWidth() - mSecondaryButtonWidth;
    }
  }

  /**
   * Instantiates a new modern optional drop down menu button.
   *
   * @param text1 the text1
   * @param icon  the icon
   * @param menu  the menu
   */
  public ModernOptionalDropDownMenuButton2(String text1, ModernIcon icon, ModernPopupMenu2 menu) {
    super(text1, icon);

    setMenu(menu);

    setup();
  }

  /**
   * Instantiates a new modern optional drop down menu button.
   *
   * @param icon the icon
   * @param menu the menu
   */
  public ModernOptionalDropDownMenuButton2(ModernIcon icon, ModernPopupMenu2 menu) {
    super(icon);

    setMenu(menu);

    setClickMessage(PRIMARY_BUTTON);

    UI.setSize(this, SIZE);

    setup();
  }

  public ModernOptionalDropDownMenuButton2(String text1, ModernPopupMenu2 menu) {
    super(text1);

    setMenu(menu);

    setClickMessage(PRIMARY_BUTTON);

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    setSecondaryButtonWidth(SECONDARY_BUTTON_WIDTH);
    // addMouseListener(new MouseEvents());
    addMouseMotionListener(new MouseMotionEvents());
    addComponentListener(new ComponentEvents());

    // addStyleClass("content-box");
  }

  /**
   * Sets the secondary button width.
   *
   * @param secondaryButtonWidth the new secondary button width
   */
  public void setSecondaryButtonWidth(int secondaryButtonWidth) {
    mSecondaryButtonWidth = secondaryButtonWidth;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.button.ModernButtonWidget#drawBackground(java.awt.
   * Graphics2D)
   */
  /*
   * @Override public void drawBackgroundAA(Graphics2D g2) { IntRect rect = new
   * IntRect(0, 0, getWidth(), getHeight());
   * 
   * int x = 0; int y = mRect.getY(); int h = mRect.getH(); int w = 0;
   * 
   * if (mPrimaryButton || mPopupShown) { x = mRect.getX(); w = mDividerLocation -
   * mRect.getX(); } else if (mHighlight) { x = mDividerLocation; w = mRect.getW()
   * - mDividerLocation; } else { // do nothing }
   * 
   * if (isSelected() || mPopupShown) {
   * DrawUIService.getInstance().getRenderer("button-fill").draw(g2, rect); } else
   * if (mHighlight) {
   * DrawUIService.getInstance().getRenderer("content-box").draw(this, g2, rect);
   * DrawUIService.getInstance().getRenderer("button-outline").draw(g2, rect);
   * DrawUIService.getInstance().getRenderer("button-fill") .draw(g2, x, y, w, h);
   * } else { // do nothing } }
   */

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    if (mText1 != null) {
      int x = (getWidth() - g2.getFontMetrics().stringWidth(mText1)) / 2;
      int y = 16;
      g2.setColor(getForeground());
      g2.drawString(mText1, x, y);
    }

    if (this.getIcon() != null) {
      int iconY = (getHeight() - 16) / 2;

      getIcon().drawIcon(g2, PADDING, iconY, 16);
    }

    Rectangle subRect = new Rectangle(getWidth() - getInsets().right - 16, 0, 16, getHeight());

    // UIService.getInstance().loadIcon(TriangleDownVectorIcon.class,
    // 16).drawIcon(g2, subRect.x, (subRect.height - 16) / 2, 16);

    TRIANGLE_ICON.drawIcon(g2, subRect.x, (subRect.height - 16) / 2, 16);
  }

  @Override
  protected void dropButtonClicked(MouseEvent e) {
    if (e.isPopupTrigger()) {
      return;
    }

    if (mPrimaryButton) {
      setHighlighted(false);

      fireClicked();
    } else {
      showMenu();
    }
  }
}
