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
package org.jebtk.modern.hidepane;

import java.awt.Graphics2D;

import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.ribbon.QuickAccessButton;

/**
 * The class ModernHideButton.
 */
public class ModernHideButton extends QuickAccessButton {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member hidden.
   */
  protected boolean mHidden = false;

  /**
   * The member icon2.
   */
  private ModernIcon mIcon2;

  /**
   * Instantiates a new modern hide button.
   *
   * @param icon1 the icon1
   * @param icon2 the icon2
   */
  public ModernHideButton(ModernIcon icon1, ModernIcon icon2) {
    super(icon1);

    mIcon2 = icon2;

    // Ui.setSize(this,
    // ModernTheme.getInstance().getClass("button").getDimension("icon-only"));
  }

  /**
   * Set whether the button is in hidden mode or not.
   *
   * @param hidden the new hidden
   */
  public void setHidden(boolean hidden) {
    mHidden = hidden;

    repaint();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.button.ModernButton#drawForegroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    int x = (getWidth() - 16) / 2;
    int y = (getHeight() - 16) / 2;

    if (!mHidden) {
      mIcon.drawIcon(g2, x, y, 16);
    } else {
      mIcon2.drawIcon(g2, x, y, 16);
    }
  }
}
