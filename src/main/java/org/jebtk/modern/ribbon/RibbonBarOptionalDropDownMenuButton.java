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

import java.awt.Graphics2D;
import java.awt.Rectangle;

import org.jebtk.core.settings.SettingsService;
import org.jebtk.modern.button.ModernOptionalDropDownMenuButton;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.menu.ModernPopupMenu;

// TODO: Auto-generated Javadoc
/**
 * The class RibbonBarOptionalDropDownMenuButton.
 */
public class RibbonBarOptionalDropDownMenuButton extends ModernOptionalDropDownMenuButton {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new ribbon bar optional drop down menu button.
   *
   * @param text1 the text1
   * @param icon  the icon
   * @param menu  the menu
   */
  public RibbonBarOptionalDropDownMenuButton(String text1, ModernIcon icon, ModernPopupMenu menu) {
    super(text1, icon, menu);

    setup();
  }

  /**
   * Instantiates a new ribbon bar optional drop down menu button.
   *
   * @param icon the icon
   * @param menu the menu
   */
  public RibbonBarOptionalDropDownMenuButton(ModernIcon icon, ModernPopupMenu menu) {
    super(icon, menu);

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    setBackground(SettingsService.getInstance().getColor("ribbon-bar.color"));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.button.ModernOptionalDropDownMenuButton#
   * drawBackground( java.awt.Graphics2D)
   */
  @Override
  public void drawBackground(Graphics2D g2) {
    fill(g2, getBackground(), getRect());

    Rectangle subRect = new Rectangle();

    subRect.y = mRect.getY();
    subRect.height = mRect.getH();

    if (mPrimaryButton) {
      subRect.x = mRect.getX();
      subRect.width = mDividerLocation - mRect.getX();
    } else {
      subRect.x = mDividerLocation;
      subRect.width = mRect.getW() - mDividerLocation;
    }

    if (isSelected()) {
      paintHighlightedOutlined(g2, getRect());
    } else if (mPopupShown || mHighlight) {
      drawHighlightedOutline(g2, getRect());

      paintHighlightedOutlined(g2, subRect);
    } else {
      // do nothing
    }
  }
}