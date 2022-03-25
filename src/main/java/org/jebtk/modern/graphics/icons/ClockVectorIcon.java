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
 * Help icon.
 * 
 * @author Antony Holmes
 *
 */
public class ClockVectorIcon extends ModernVectorScalableIcon {

  /**
   * The constant SCALE.
   */
  private static final double SCALE = SettingsService.getInstance().getDouble("theme.icons.clock-icon.width-scale");

  /**
   * The constant HAND_SCALE_1.
   */
  private static final double HAND_SCALE_1 = SettingsService.getInstance()
      .getDouble("theme.icons.clock-icon.hand-scale-1");

  /**
   * The constant HAND_SCALE_2.
   */
  private static final double HAND_SCALE_2 = SettingsService.getInstance()
      .getDouble("theme.icons.clock-icon.hand-scale-2");

  /**
   * The constant OUTLINE_COLOR.
   */
  private static final Color OUTLINE_COLOR = SettingsService.getInstance()
      .getColor("theme.icons.clock-icon.colors.outline");

  /**
   * The constant BACKGROUND.
   */
  private static final Color BACKGROUND = SettingsService.getInstance()
      .getColor("theme.icons.clock-icon.colors.background");

  /**
   * The constant HAND_COLOR.
   */
  private static final Color HAND_COLOR = SettingsService.getInstance().getColor("theme.icons.clock-icon.colors.hand");

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

    double xf = x + (w - wf) / 2.0;
    double yf = y + (h - wf) / 2.0;
    double w2 = wf / 2.0;
    double h1 = wf * HAND_SCALE_1;
    double h2 = wf * HAND_SCALE_2;

    g2.setStroke(ModernTheme.DOUBLE_LINE_STROKE);
    g2.setColor(BACKGROUND);

    g2.fillOval((int) Math.round(xf), (int) Math.round(yf), (int) Math.round(wf), (int) Math.round(wf));

    g2.setColor(OUTLINE_COLOR);

    g2.drawOval((int) Math.round(xf), (int) Math.round(yf), (int) Math.round(wf), (int) Math.round(wf));

    g2.setColor(HAND_COLOR);

    g2.drawLine((int) Math.round(xf + w2), (int) Math.round(yf + w2), (int) Math.round(xf + w2),
        (int) Math.round(yf + w2 - h1));

    g2.drawLine((int) Math.round(xf + w2), (int) Math.round(yf + w2), (int) Math.round(xf + w2 + h2),
        (int) Math.round(yf + w2));
  }
}
