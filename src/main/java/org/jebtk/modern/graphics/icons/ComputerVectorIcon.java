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
 * The class ComputerVectorIcon.
 */
public class ComputerVectorIcon extends ModernVectorScalableIcon {

  /**
   * The constant SCALE.
   */
  private static final double SCALE = SettingsService.getInstance().getDouble("theme.icons.computer-icon.width-scale");

  /**
   * The constant TOWER_SCALE.
   */
  private static final double TOWER_SCALE = SettingsService.getInstance()
      .getDouble("theme.icons.computer-icon.tower-scale");

  /**
   * The constant MONITOR_WIDTH_SCALE.
   */
  private static final double MONITOR_WIDTH_SCALE = SettingsService.getInstance()
      .getDouble("theme.icons.computer-icon.monitor-width-scale");

  /**
   * The constant MONITOR_HEIGHT_SCALE.
   */
  private static final double MONITOR_HEIGHT_SCALE = SettingsService.getInstance()
      .getDouble("theme.icons.computer-icon.monitor-height-scale");

  /**
   * The constant MONITOR_BASE_WIDTH_SCALE.
   */
  private static final double MONITOR_BASE_WIDTH_SCALE = SettingsService.getInstance()
      .getDouble("theme.icons.computer-icon.monitor-base-width-scale");

  /**
   * The constant TOWER_COLOR.
   */
  private static final Color TOWER_COLOR = SettingsService.getInstance()
      .getColor("theme.icons.computer-icon.colors.tower");

  /**
   * The constant BACKGROUND.
   */
  private static final Color BACKGROUND = SettingsService.getInstance()
      .getColor("theme.icons.computer-icon.colors.background");

  /**
   * The constant MONITOR_OUTLINE.
   */
  private static final Color MONITOR_OUTLINE = SettingsService.getInstance()
      .getColor("theme.icons.computer-icon.colors.monitor-outline");

  /**
   * The constant MONITOR_BACKGROUND.
   */
  private static final Color MONITOR_BACKGROUND = SettingsService.getInstance()
      .getColor("theme.icons.computer-icon.colors.monitor-background");

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
    double towerWidth = wf * TOWER_SCALE;
    double bw = wf * MONITOR_BASE_WIDTH_SCALE;

    g2.setColor(BACKGROUND);

    g2.fillRect((int) Math.round(xf), (int) Math.round(yf), (int) Math.round(towerWidth), (int) Math.round(wf));

    g2.setColor(TOWER_COLOR);

    g2.drawRect((int) Math.round(xf), (int) Math.round(yf), (int) Math.round(towerWidth), (int) Math.round(wf));

    g2.setStroke(ModernTheme.DOUBLE_LINE_STROKE);

    double monitorWidth = wf * MONITOR_WIDTH_SCALE;
    double monitorHeight = wf * MONITOR_HEIGHT_SCALE;

    // base

    g2.setColor(MONITOR_OUTLINE);

    g2.drawLine((int) Math.round(xf + wf - monitorWidth + monitorWidth / 2.0), (int) Math.round(yf + wf / 2.0),
        (int) Math.round(xf + wf - monitorWidth + monitorWidth / 2.0), (int) Math.round(yf + wf));

    g2.drawLine((int) Math.round(xf + wf - monitorWidth + (monitorWidth - bw) / 2.0), (int) Math.round(yf + wf),
        (int) Math.round(xf + wf - monitorWidth + (monitorWidth - bw) / 2.0 + bw), (int) Math.round(yf + wf));

    g2.setColor(MONITOR_BACKGROUND);

    g2.fillRect((int) Math.round(xf + wf - monitorWidth), (int) Math.round(yf + (wf - monitorHeight) / 2.0),
        (int) Math.round(monitorWidth), (int) Math.round(monitorHeight));

    g2.setColor(MONITOR_OUTLINE);

    g2.drawRect((int) Math.round(xf + wf - monitorWidth), (int) Math.round(yf + (wf - monitorHeight) / 2.0),
        (int) Math.round(monitorWidth), (int) Math.round(monitorHeight));

  }
}
