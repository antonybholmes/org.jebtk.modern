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

import java.awt.Graphics2D;

import org.jebtk.modern.UI;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.menu.ModernPopupMenu2;

// TODO: Auto-generated Javadoc
/**
 * Drop down button that displays a menu.
 * 
 * @author Antony Holmes
 *
 */
public class ModernMenuButton2 extends ModernDropDownWidget2 {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new modern drop down button.
   *
   * @param icon the icon
   * @param menu the menu
   */
  public ModernMenuButton2(ModernIcon icon, ModernPopupMenu2 menu) {
    super(icon);

    setMenu(menu);

    UI.setSize(this, ModernButton.ICON_ONLY_SIZE);
  }

  /*
   * @Override public void drawBackgroundAA(Graphics2D g2) { if (isEnabled()) { if
   * (isSelected() || mHighlight || mPopupShown) {
   * getWidgetRenderer().drawButton(g2, mInternalRect, RenderMode.SELECTED); }
   * else { getWidgetRenderer().drawButton(g2, mInternalRect,
   * RenderMode.SELECTED); } } }
   */

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    int x = (getWidth() - 16) / 2;
    int y = (getHeight() - 16) / 2;

    getIcon().drawIcon(g2, x, y, 16);
  }

  /**
   * Default to a particular menu item.
   *
   * @param index the new selected index
   */
  public void setSelectedIndex(int index) {
    ((ModernPopupMenu2) mMenu).get(index).doClick();
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
    mText1 = e.getMessage();

    repaint();

    super.fireClicked(e);
  }
}
