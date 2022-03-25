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
 * The class CutVectorIcon.
 */
public class CutVectorIcon extends ModernVectorIcon {

  /**
   * The constant SCALE.
   */
  private static final double SCALE = SettingsService.getInstance().getDouble("theme.icons.cut-icon.width-scale");

  /**
   * The constant HANDLE_SCALE.
   */
  private static final double HANDLE_SCALE = SettingsService.getInstance()
      .getDouble("theme.icons.cut-icon.handle-scale");

  /**
   * The constant COLOR.
   */
  private static final Color COLOR = SettingsService.getInstance().getColor("theme.icons.cut-icon.colors.foreground");

  /**
   * The constant BACKGROUND.
   */
  private static final Color BACKGROUND = SettingsService.getInstance()
      .getColor("theme.icons.cut-icon.colors.background");

  /**
   * The constant HANDLE.
   */
  private static final Color HANDLE = SettingsService.getInstance().getColor("theme.icons.cut-icon.colors.handle");

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

    double xf = x + (w - w) / 2.0;
    double yf = y + (h - w) / 2.0;
    double hw = w * HANDLE_SCALE;

    g2.setStroke(ModernTheme.DOUBLE_LINE_STROKE);
    g2.setColor(COLOR);

    g2.drawLine((int) Math.round(xf + hw / 2.0), (int) Math.round(yf), (int) Math.round(x + wf - hw / 2.0),
        (int) Math.round(yf + wf - hw / 2.0));
    g2.drawLine((int) Math.round(xf + hw / 2.0), (int) Math.round(yf + wf - hw / 2.0),
        (int) Math.round(xf + wf - hw / 2.0), (int) Math.round(yf));

    g2.setStroke(ModernTheme.SINGLE_LINE_STROKE);

    g2.setColor(BACKGROUND);
    g2.fillOval((int) Math.round(xf), (int) Math.round(y + w - hw), (int) Math.round(hw), (int) Math.round(hw));
    g2.fillOval((int) Math.round(xf + wf - hw), (int) Math.round(yf + wf - hw), (int) Math.round(hw),
        (int) Math.round(hw));

    g2.setColor(HANDLE);
    g2.drawOval((int) Math.round(xf), (int) Math.round(y + w - hw), (int) Math.round(hw), (int) Math.round(hw));
    g2.drawOval((int) Math.round(xf + wf - hw), (int) Math.round(y + wf - hw), (int) Math.round(hw),
        (int) Math.round(hw));
  }
}
