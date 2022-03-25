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
package org.jebtk.modern.combobox;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import org.jebtk.modern.AssetService;
import org.jebtk.modern.graphics.icons.TriangleDownVectorIcon;
import org.jebtk.modern.menu.ModernScrollPopupMenu;

// TODO: Auto-generated Javadoc
/**
 * The class ModernHiddenComboBox.
 */
public class ModernHiddenComboBox extends ModernComboBox {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new modern hidden combo box.
   */
  public ModernHiddenComboBox() {
    // TODO Auto-generated constructor stub
  }

  /**
   * Instantiates a new modern hidden combo box.
   *
   * @param name  the name
   * @param popup the popup
   */
  public ModernHiddenComboBox(String name, ModernScrollPopupMenu popup) {
    super(name, popup);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.combobox.ModernComboBox#drawBackground(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawBackground(Graphics2D g2) {
    Rectangle buttonRect = new Rectangle(mButtonX, mRect.getY(), BUTTON_WIDTH, mRect.getH());

    if (mHighlight) {
      // ModernTheme.fillRect(g2, getRect(), highlightBorderColor);

      drawHighlightedOutline(g2, getRect());

      paintHighlightedOutlined(g2, buttonRect);

      // g2.setColor(highlightBorderColor);
      // g2.drawRect(mRect.getX(), mRect.getY(), mRect.getW() - 1, mRect.getH()
      // - 1);
      // Theme.paintRect(g2, getRect(), highlightBorderColor);

      // g2.setColor(ThemeService.getInstance().getThemeColor(2));
      // ModernTheme.drawRect(g2, buttonRect, highlightBorderColor);
      // g2.drawRect(buttonX, mRect.getY(), BUTTON_WIDTH - 1, mRect.getH() - 1);
    } else if (mPopupShown) {
      // g2.setColor(highlightBorderColor);
      // g2.drawRect(mRect.getX(), mRect.getY(), mRect.getW() - 1, mRect.getH()
      // - 1);
      paintSelectedBorder(g2, getRect());

      paintSelected(g2, buttonRect);
    } else {

    }

    // paintImage(this, g2, ModernDropDownMenuButton.DROP_ARROW_ICON,
    // buttonRect);

    AssetService.getInstance().loadIcon(TriangleDownVectorIcon.class, 16).drawIcon(g2, buttonRect.x,
        buttonRect.y + (buttonRect.height - 16) / 2, 16);
  }
}
