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

/**
 * Group vector icon showing 3 colored balls spaced equally around a circle.
 * 
 * @author Antony Holmes
 *
 */
public class GroupVectorIcon extends ModernVectorIcon {

  /**
   * The constant COLOR1.
   */
  private static final Color COLOR1 = SettingsService.getInstance().getColor("theme.icons.group-icon.colors.color1");

  /**
   * The constant COLOR2.
   */
  private static final Color COLOR2 = SettingsService.getInstance().getColor("theme.icons.group-icon.colors.color2");

  /**
   * The constant COLOR3.
   */
  private static final Color COLOR3 = SettingsService.getInstance().getColor("theme.icons.group-icon.colors.color3");

  /**
   * The constant RADIUS.
   */
  private static final double RADIUS = SettingsService.getInstance().getDouble("theme.icons.group-icon.radius-scale");

  /**
   * The constant BALL_RADIUS.
   */
  private static final double BALL_RADIUS = SettingsService.getInstance()
      .getDouble("theme.icons.group-icon.ball-radius-scale");

  /**
   * The constant ANGLE2.
   */
  private static final double ANGLE2 = Math.PI * 11 / 6.0;

  /**
   * The constant ANGLE3.
   */
  private static final double ANGLE3 = Math.PI * 7.0 / 6.0;

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.icons.ModernIcon#drawForeground(java.awt.Graphics2D,
   * java.awt.Rectangle)
   */
  @Override
  public void drawIcon(Graphics2D g2, int x, int y, int w, int h, Props props) {

    double wf = w * RADIUS;
    double br = wf * BALL_RADIUS;
    int bc = (int) Math.round(br * 2.0);

    double xf = x + wf / 2.0;
    double yf = y + h / 2.0;

    // Color 1

    g2.setColor(COLOR1);

    double x2 = xf - br;
    double y2 = yf - wf - br;

    g2.fillOval((int) Math.round(x2), (int) Math.round(y2), bc, bc);

    // Color 2

    g2.setColor(COLOR2);

    x2 = xf + wf * Math.cos(ANGLE2) - br;
    y2 = yf - wf * Math.sin(ANGLE2) - br;

    g2.fillOval((int) Math.round(x2), (int) Math.round(y2), bc, bc);

    // Color 3

    g2.setColor(COLOR3);

    x2 = xf + wf * Math.cos(ANGLE3) - br;
    y2 = yf - wf * Math.sin(ANGLE3) - br;

    g2.fillOval((int) Math.round(x2), (int) Math.round(y2), bc, bc);
  }

}
