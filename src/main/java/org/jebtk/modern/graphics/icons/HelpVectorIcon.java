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
package org.jebtk.modern.graphics.icons;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.modern.graphics.ImageUtils;
import org.jebtk.modern.theme.ThemeService;

/**
 * Help icon.
 * 
 * @author Antony Holmes
 *
 */
public class HelpVectorIcon extends ModernMessageIcon {

  /** The Constant COLOR1. */
  private static final Color COLOR1 = ThemeService.getInstance().getColors().getTheme32(14);

  /** The Constant COLOR2. */
  private static final Color COLOR2 = ThemeService.getInstance().getColors().getTheme32(16);

  /** The Constant TEXT. */
  private static final String TEXT = "?";

  /**
   * Instantiates a new help vector icon.
   */
  public HelpVectorIcon() {
    this(COLOR1, COLOR2);
  }

  /**
   * Instantiates a new help vector icon.
   *
   * @param color1 the color 1
   * @param color2 the color 2
   */
  public HelpVectorIcon(Color color1, Color color2) {
    super(color1 != null ? color1 : COLOR1, color2 != null ? color2 : COLOR2);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.icons.ModernIcon#drawForeground(java.awt.Graphics2D,
   * java.awt.Rectangle)
   */
  @Override
  public void drawIcon(Graphics2D g2, int x, int y, int w, int h, Props props) {
    int size = w - 2;

    int xf = x + (w - size) / 2;
    int yf = y + (h - size) / 2;

    Graphics2D g2Temp = ImageUtils.clone(g2);

    try {
      // Useful for drawing circles with sub-pixel accuracy.
      ImageUtils.setAAStrokeHints(g2Temp);

      GradientPaint paint = new GradientPaint(0, yf, mColor1, 0, yf + size, mColor2);

      g2Temp.setPaint(paint);

      // g2Temp.setColor(Color.WHITE);
      g2Temp.fillOval(xf, yf, size, size);
      /// g2Temp.setColor(mColor2);
      // g2Temp.drawOval(xf, yf, size, size);
    } finally {
      g2Temp.dispose();
    }

    drawScaledText(g2, w, x, y, w, h, TEXT, Color.WHITE);
  }
}
