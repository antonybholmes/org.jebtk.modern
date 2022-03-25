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
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.modern.ColorTheme;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.graphics.ImageUtils;
import org.jebtk.modern.theme.ThemeService;

/**
 * Represents a vector icon that can have its color and size specified. These
 * types of vector icons are designed to be scale invariant.
 * 
 * @author Antony Holmes
 *
 */
public class ModernApplicationIcon extends ModernVectorScalableIcon {

  /** The Constant FONT_SCALE. */
  private static final double FONT_SCALE = 0.7;

  /** The Constant FONT_1. */
  private static final Font FONT_1 = ModernWidget.TITLE_FONT_BOLD;

  /** The Constant FONT_2. */
  private static final Font FONT_2 = ModernWidget.TITLE_FONT;

  /** The m text 1. */
  private String mText1;

  /** The m text 2. */
  private String mText2;

  /**
   * Instantiates a new modern application icon.
   *
   * @param text1  the text 1
   * @param text2  the text 2
   * @param color1 the color 1
   * @param color2 the color 2
   */
  public ModernApplicationIcon(String text1, String text2, Color color1, Color color2) {
    super(color1, color2);

    mText1 = text1;
    mText2 = text2;
  }

  /**
   * Instantiates a new modern application icon.
   *
   * @param text1 the text 1
   * @param text2 the text 2
   * @param theme the theme
   */
  public ModernApplicationIcon(String text1, String text2, ColorTheme theme) {
    this(text1, text2, ThemeService.getInstance().getColors().getColorTheme(theme).getColor32(22),
        ThemeService.getInstance().getColors().getColorTheme(theme).getColor32(24));

  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.graphics.icons.ModernIcon#drawIcon(java.awt.Graphics2D,
   * int, int, int, int)
   */
  @Override
  public void drawIcon(Graphics2D g2, int x, int y, int w, int h, Props props) {
    GradientPaint p = new GradientPaint(0, y, mColor1, 0, y + h, mColor2);

    // int rounding = (int)Math.round(Math.max(1, w / 4.0f));

    Graphics2D g2Temp = ImageUtils.createAAStrokeGraphics(g2);

    try {
      g2Temp.translate(x, y);

      g2Temp.setPaint(p);
      // g2Temp.setColor(mColor2);
      g2Temp.fillOval(x, y, w, h);

      // g2Temp.setColor(mColor2);
      // g2Temp.drawOval(x, y, w, h);

      // g2Temp.fillRoundRect(x, y, w, h, rounding, rounding);
    } finally {
      g2Temp.dispose();
    }

    drawScaledText(g2, w, x, y, w, h, mText1, mText2, Color.WHITE);
  }

  //
  // Static methods
  //

  /**
   * Draws a scaled version of some text in the center of an icon.
   *
   * @param g2    the g 2
   * @param size  the size
   * @param x     the x
   * @param y     the y
   * @param w     the w
   * @param h     the h
   * @param text1 the text 1
   * @param text2 the text 2
   * @param color the color
   */
  public static void drawScaledText(Graphics2D g2, int size, int x, int y, int w, int h, String text1, String text2,
      Color color) {

    int x1;
    int y1;
    double wf = 0;
    double w1 = 0;
    double w2 = 0;

    double factor1;
    double factor2;

    Graphics2D g2Temp = ImageUtils.clone(g2);

    try {
      g2Temp.setFont(FONT_1);

      int fontHeight1 = ImageUtils.getFontHeight(g2Temp);

      // Scale factor
      factor1 = size / (double) fontHeight1 * FONT_SCALE;

      w1 = g2Temp.getFontMetrics().stringWidth(text1) * factor1;

      g2Temp.setFont(FONT_2);

      int fontHeight2 = ImageUtils.getFontHeight(g2Temp);

      // Scale factor
      factor2 = size / (double) fontHeight2 * FONT_SCALE;

      w2 = g2Temp.getFontMetrics().stringWidth(text2) * factor2;

      wf = w1 + w2;
    } finally {
      g2Temp.dispose();
    }

    g2Temp = ImageUtils.clone(g2);

    try {
      g2Temp.setFont(FONT_1);

      x1 = x + (int) ((w - wf) / 2);
      y1 = y + ModernWidget.getTextYPosCenter(h, g2Temp.getFontMetrics().getAscent() * factor1,
          g2Temp.getFontMetrics().getDescent() * factor1); // h - (int)((h -
      // fontHeight *
      // factor) / 2);

      g2Temp.translate(x1, y1);
      g2Temp.scale(factor1, factor1);

      g2Temp.setColor(color);

      g2Temp.drawString(text1, 0, 0);
    } finally {
      g2Temp.dispose();
    }

    g2Temp = ImageUtils.clone(g2);

    try {
      g2Temp.setFont(FONT_2);

      x1 = x + (int) ((w - wf) / 2 + w1);
      y1 = y + ModernWidget.getTextYPosCenter(h, g2Temp.getFontMetrics().getAscent() * factor2,
          g2Temp.getFontMetrics().getDescent() * factor2); // h - (int)((h -
      // fontHeight *
      // factor) / 2);

      g2Temp.translate(x1, y1);
      g2Temp.scale(factor2, factor2);

      g2Temp.setColor(color);

      g2Temp.drawString(text2, 0, 0);
    } finally {
      g2Temp.dispose();
    }
  }
}
