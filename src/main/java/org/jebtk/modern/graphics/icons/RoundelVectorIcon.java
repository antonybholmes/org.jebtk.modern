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
import org.jebtk.core.settings.SettingsService;
import org.jebtk.modern.theme.ModernTheme;

/**
 * The class RoundelVectorIcon.
 */
public class RoundelVectorIcon extends ModernVectorIcon {

  /**
   * The constant WIDTH_SCALE.
   */
  private static final double WIDTH_SCALE = SettingsService.getInstance()
      .getDouble("theme.icons.roundel-icon.width-scale");

  /**
   * The constant LINE_SCALE.
   */
  private static final double LINE_SCALE = SettingsService.getInstance()
      .getDouble("theme.icons.roundel-icon.line-scale");

  /**
   * The constant ARROW_SCALE.
   */
  private static final double ARROW_SCALE = SettingsService.getInstance()
      .getDouble("theme.icons.roundel-icon.arrow-scale");

  /**
   * The member color.
   */
  private Color mColor;

  /**
   * Instantiates a new roundel vector icon.
   */
  public RoundelVectorIcon() {
    this(SettingsService.getInstance().getColor("theme.icons.roundel-icon.colors.foreground"));
  }

  /**
   * Instantiates a new roundel vector icon.
   *
   * @param color the color
   */
  public RoundelVectorIcon(Color color) {
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

    g2.setStroke(ModernTheme.DOUBLE_LINE_STROKE);

    double wf = w * WIDTH_SCALE;
    double lw = wf * LINE_SCALE;
    double aw = lw * ARROW_SCALE;

    double xf = x + (w - wf) / 2.0;
    double yf = y + (h - wf) / 2.0;

    g2.setColor(Color.WHITE);
    g2.fillOval((int) Math.round(xf), (int) Math.round(yf), (int) Math.round(wf), (int) Math.round(wf));

    g2.setColor(mColor);
    g2.drawOval((int) Math.round(xf), (int) Math.round(yf), (int) Math.round(wf), (int) Math.round(wf));

    xf = xf + (wf - lw) / 2.0;
    yf = yf + h / 2.0;

    g2.drawLine((int) Math.round(xf), (int) Math.round(yf), (int) Math.round(xf + lw), (int) Math.round(yf));

    g2.drawLine((int) Math.round(xf + lw), (int) Math.round(yf), (int) Math.round(xf + lw - aw),
        (int) Math.round(yf - aw));
    g2.drawLine((int) Math.round(xf + lw), (int) Math.round(yf), (int) Math.round(xf + lw - aw),
        (int) Math.round(yf + aw));
  }
}
