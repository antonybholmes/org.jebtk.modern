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
import java.awt.geom.GeneralPath;

import org.jebtk.core.Props;
import org.jebtk.core.settings.SettingsService;

/**
 * Vector based save icon.
 * 
 * @author Antony Holmes
 *
 */
public class SaveVectorIcon extends ModernVectorScalableIcon {

  /**
   * The constant SCALE.
   */
  private static final double SCALE = SettingsService.getInstance().getDouble("theme.icons.save-icon.width-scale");

  /**
   * The constant CORNER_SCALE.
   */
  private static final double CORNER_SCALE = SettingsService.getInstance()
      .getDouble("theme.icons.save-icon.corner-scale");

  /**
   * The constant COLOR.
   */
  private static final Color COLOR = SettingsService.getInstance().getColor("theme.icons.save-icon.colors.background");

  /**
   * The member color.
   */
  private Color mColor;

  /**
   * Instantiates a new save vector icon.
   */
  public SaveVectorIcon() {
    this(COLOR);
  }

  /**
   * Instantiates a new save vector icon.
   *
   * @param color the color
   */
  public SaveVectorIcon(Color color) {
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
    double wf = w * SCALE;

    double corner = wf * CORNER_SCALE;

    double xf = x + (w - wf) / 2.0;
    double yf = y + (h - wf) / 2.0;

    GeneralPath gp = new GeneralPath();

    gp.moveTo(xf, yf);

    gp.lineTo(xf + wf, yf);
    gp.lineTo(xf + wf, yf + wf);
    gp.lineTo(x + corner, y + w);
    gp.lineTo(xf, yf + wf - corner);

    gp.closePath();

    g2.setColor(mColor);
    g2.fill(gp);

    g2.setColor(Color.WHITE);

    // label

    double labelW = wf * 0.75;
    double labelH = wf * 0.45;
    double offset = wf * 0.1;

    g2.fillRect((int) Math.round(xf + (wf - labelW) / 2.0), (int) Math.round(yf + offset), (int) Math.round(labelW),
        (int) Math.round(labelH));

    // slider

    labelW = wf * 0.5;
    labelH = wf * 0.3;

    g2.fillRect((int) Math.round(xf + (wf - labelW) / 2.0), (int) Math.round(yf + wf - labelH),
        (int) Math.round(labelW), (int) Math.round(labelH));

    // g2.setColor(ThemeService.getInstance().getThemeColor(4));

    // labelW = w * 0.1;
    // labelH = h * 0.2;

    // g2.fillRect((int)(x + w * 0.4), (int)(y + h - labelH), (int)labelW,
    // (int)labelH);
  }
}
