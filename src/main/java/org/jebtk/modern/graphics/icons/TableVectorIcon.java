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
import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.theme.ThemeService;

/**
 * The class TableVectorIcon.
 */
public class TableVectorIcon extends ModernVectorIcon {

  /**
   * The member color.
   */
  // private String text;
  protected Color mColor;

  /**
   * The constant WIDTH_SCALE.
   */
  protected static final double WIDTH_SCALE = 0.9;

  /**
   * The constant HEIGHT_SCALE.
   */
  protected static final double HEIGHT_SCALE = 0.8;

  /**
   * Instantiates a new table vector icon.
   */
  public TableVectorIcon() {
    this(ThemeService.getInstance().getColors().getTheme(4));
  }

  /**
   * Instantiates a new table vector icon.
   *
   * @param color the color
   */
  public TableVectorIcon(Color color) {
    mColor = color;
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
    double wf = w * WIDTH_SCALE;
    double hf = w * HEIGHT_SCALE;

    double xf = x + (w - wf) / 2.0;
    double yf = y + (h - hf) / 2.0;

    double barH = hf * 0.25;

    g2.setColor(ModernWidget.BACKGROUND_COLOR);
    g2.fillRect((int) Math.round(xf), (int) Math.round(yf), (int) Math.round(wf), (int) Math.round(hf));

    g2.setColor(ModernWidget.DARK_LINE_COLOR);
    g2.drawRect((int) Math.round(xf), (int) Math.round(yf), (int) Math.round(wf), (int) Math.round(hf));

    g2.setColor(mColor);
    g2.fillRect((int) Math.round(xf), (int) Math.round(yf), (int) (Math.round(wf) + 1), (int) Math.round(barH));

    /*
     * g2.setColor(ModernTheme.getInstance().getClass("widget").getColor("text") );
     * 
     * g2.setFont(ThemeService.loadFont("widget.text"));
     * 
     * double x = x + (w - g2.getFontMetrics().stringWidth(text)) / 2.0; double y =
     * y + h * 0.75;
     * 
     * g2.drawString(text, (int)x, (int)y);
     */
  }

}
