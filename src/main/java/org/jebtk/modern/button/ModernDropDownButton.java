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

import org.jebtk.modern.AssetService;
import org.jebtk.modern.UI;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.graphics.icons.TriangleDownVectorIcon;
import org.jebtk.modern.menu.ModernPopupMenu;
import org.jebtk.modern.theme.ModernTheme;

// TODO: Auto-generated Javadoc
/**
 * Drop down button that displays a menu.
 * 
 * @author Antony Holmes
 *
 */
public class ModernDropDownButton extends ModernDropDownWidget {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The Constant TRIANGLE_ICON. */
  protected static final ModernIcon TRIANGLE_ICON = AssetService.getInstance().loadIcon(TriangleDownVectorIcon.class,
      16);

  /**
   * The constant SIZE.
   */
  private static final Dimension SIZE = ModernTheme.loadDimension("theme.button.dimensions.dropdown-icon-only");

  protected boolean mChangeText = true;

  /**
   * Instantiates a new modern drop down button.
   *
   * @param text1 the text1
   * @param icon  the icon
   * @param menu  the menu
   */
  public ModernDropDownButton(String text1, ModernIcon icon, ModernPopupMenu menu) {

    this(text1, icon);

    setMenu(menu);
  }

  /**
   * Instantiates a new modern drop down button.
   *
   * @param text1 the text1
   * @param icon  the icon
   */
  public ModernDropDownButton(String text1, ModernIcon icon) {
    super(text1, icon);
  }

  /**
   * Instantiates a new modern drop down button.
   *
   * @param text1 the text1
   * @param menu  the menu
   */
  public ModernDropDownButton(String text1, ModernPopupMenu menu) {
    super(text1);

    setMenu(menu);
  }

  /**
   * Instantiates a new modern drop down button.
   *
   * @param icon the icon
   * @param menu the menu
   */
  public ModernDropDownButton(ModernIcon icon, ModernPopupMenu menu) {
    this(icon);

    setMenu(menu);
  }

  /**
   * Instantiates a new modern drop down button.
   *
   * @param icon the icon
   */
  public ModernDropDownButton(ModernIcon icon) {
    super(icon);

    UI.setSize(this, SIZE);
  }

  /**
   * Instantiates a new modern drop down button.
   *
   * @param text1 the text1
   */
  public ModernDropDownButton(String text1) {
    super(text1);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    int iconX = PADDING;

    g2.setColor(getForeground());

    if (mText1 != null) {
      int x = iconX;

      if (this.getIcon() != null) {
        x += getIcon().getWidth() + PADDING;
      }

      g2.drawString(mText1, x, getTextYPosCenter(g2, getHeight()));
    }

    if (getIcon() != null) {
      int iconY = (getHeight() - 16) / 2;

      getIcon().drawIcon(g2, iconX, iconY, 16);
    }

    TRIANGLE_ICON.drawIcon(g2, mRect.getW() - 16, (getHeight() - 16) / 2, 16);
  }

  /**
   * Default to a particular menu item.
   *
   * @param index the new selected index
   */
  public void setSelectedIndex(int index) {
    ((ModernPopupMenu) mMenu).get(index).doClick();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.ModernClickWidget#fireClicked(org.abh.lib.ui.modern.
   * event.ModernClickEvent)
   */
  @Override
  public void fireClicked(ModernClickEvent e) {
    if (mChangeText) {
      mText1 = e.getMessage();

      setClickMessage(mText1);
    }

    repaint();

    super.fireClicked(e);
  }

  /**
   * Sets whether the drop down should change its text in response to a menu item
   * being clicked on.
   * 
   * @param change
   * @return
   */
  public ModernDropDownButton setChangeText(boolean change) {
    mChangeText = change;

    return this;
  }
}
