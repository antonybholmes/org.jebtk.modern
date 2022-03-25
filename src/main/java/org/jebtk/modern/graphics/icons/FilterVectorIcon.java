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
import org.jebtk.modern.ModernWidget;

/**
 * The class FilterVectorIcon.
 */
public class FilterVectorIcon extends ModernVectorScalableIcon {

  /**
   * The constant HEIGHT_SCALE.
   */
  private static final double HEIGHT_SCALE = SettingsService.getInstance()
      .getDouble("theme.icons.filter-icon.height-scale");

  /**
   * The constant WIDTH_SCALE.
   */
  private static final double WIDTH_SCALE = SettingsService.getInstance()
      .getDouble("theme.icons.filter-icon.width-scale");

  /**
   * The constant LIP_SCALE.
   */
  private static final double LIP_SCALE = SettingsService.getInstance().getDouble("theme.icons.filter-icon.lip-scale");

  /**
   * The constant FUNNEL_WIDTH_SCALE.
   */
  private static final double FUNNEL_WIDTH_SCALE = SettingsService.getInstance()
      .getDouble("theme.icons.filter-icon.funnel-width-scale");

  /**
   * The constant FUNNEL_HEIGHT_SCALE.
   */
  private static final double FUNNEL_HEIGHT_SCALE = SettingsService.getInstance()
      .getDouble("theme.icons.filter-icon.funnel-height-scale");

  /**
   * The constant SPOUT_SCALE.
   */
  private static final double SPOUT_SCALE = SettingsService.getInstance()
      .getDouble("theme.icons.filter-icon.spout-scale");

  /**
   * The member color.
   */
  private Color mColor;

  /** The m fill color. */
  private Color mFillColor;

  /**
   * Instantiates a new filter vector icon.
   */
  public FilterVectorIcon() {
    this(ModernWidget.DARK_LINE_COLOR);
  }

  /**
   * Instantiates a new filter vector icon.
   *
   * @param color the color
   */
  public FilterVectorIcon(Color color) {
    this(color, color);
  }

  /**
   * Instantiates a new filter vector icon.
   *
   * @param line the line
   * @param fill the fill
   */
  public FilterVectorIcon(Color line, Color fill) {
    mColor = line;
    mFillColor = fill;
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
    double hf = h * HEIGHT_SCALE;
    double wf = h * WIDTH_SCALE;

    double lip = h * LIP_SCALE;

    double fwf = w * FUNNEL_WIDTH_SCALE;
    double fhf = h * FUNNEL_HEIGHT_SCALE;

    double xf = x + (w - wf) / 2.0;
    double yf = y + (h - hf) / 2.0;

    double fxf = xf + (wf - fwf) / 2.0;

    double hs = hf * SPOUT_SCALE;

    GeneralPath gp = new GeneralPath();

    gp.moveTo(xf, yf);

    gp.lineTo(xf + wf, yf);
    gp.lineTo(xf + wf, yf + lip);
    gp.lineTo(fxf + fwf, yf + fhf);
    gp.lineTo(fxf + fwf, yf + hf - hs);
    gp.lineTo(fxf, yf + hf);
    gp.lineTo(fxf, yf + fhf);
    gp.lineTo(xf, yf + lip);
    gp.closePath();

    g2.setColor(mFillColor);
    g2.fill(gp);

    g2.setColor(mColor);
    g2.draw(gp);
  }
}
