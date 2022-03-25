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

import java.awt.Color;
import java.awt.Graphics2D;

import org.jebtk.core.ColorUtils;
import org.jebtk.modern.graphics.ImageUtils;

/**
 * The class ModernWindowTitleBarCloseButton.
 */
public abstract class ModernWindowTitleBarRoundButton extends ModernWindowTitleBarButton {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The Constant OUTLINE_COLOR. */
  protected static final Color OUTLINE_COLOR = ColorUtils.getTransparentColor70(Color.BLACK);

  /** The Constant SIZE. */
  private static final int SIZE = 14;

  /** The m color. */
  private Color mColor;

  /**
   * Instantiates a new modern window title bar round button.
   *
   * @param color the color
   */
  public ModernWindowTitleBarRoundButton(Color color) {
    mColor = color;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.ribbon.QuickAccessButton#drawBackground(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawBackground(Graphics2D g2) {
    Graphics2D g2Temp = ImageUtils.createAAStrokeGraphics(g2);

    int x = (getWidth() - SIZE) / 2;

    try {
      g2Temp.setColor(mColor);
      g2Temp.fillOval(x, x, SIZE, SIZE);
      g2Temp.setColor(OUTLINE_COLOR);
      g2Temp.drawOval(x, x, SIZE, SIZE);
    } finally {
      g2Temp.dispose();
    }

  }
}
