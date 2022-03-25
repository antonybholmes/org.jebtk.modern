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
 * The class OpenFolderVectorIcon.
 */
public class OpenFolderVectorIcon extends ModernVectorScalableIcon {

  /**
   * The constant WIDTH_SCALE.
   */
  private static final double WIDTH_SCALE = SettingsService.getInstance()
      .getDouble("theme.icons.folder-icon.width-scale");

  /**
   * The constant CORNER_SCALE.
   */
  private static final double CORNER_SCALE = SettingsService.getInstance()
      .getDouble("theme.icons.folder-icon.corner-scale");

  /**
   * The constant HEIGHT_SCALE.
   */
  private static final double HEIGHT_SCALE = SettingsService.getInstance()
      .getDouble("theme.icons.folder-icon.height-scale");

  /**
   * The constant OFFSET.
   */
  private static final double OFFSET = SettingsService.getInstance().getDouble("theme.icons.folder-icon.offset");

  /**
   * The constant COLOR1.
   */
  private static final Color COLOR1 = SettingsService.getInstance().getColor("theme.icons.folder-icon.colors.color1");

  /**
   * Instantiates a new open folder vector icon.
   */
  public OpenFolderVectorIcon() {
    this(COLOR1);
  }

  /**
   * Instantiates a new open folder vector icon.
   *
   * @param color1 the color 1
   */
  public OpenFolderVectorIcon(Color color1) {
    super(color1);
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
    double o = wf * OFFSET;
    double hf = wf * HEIGHT_SCALE;
    double w2 = wf / 2.0;

    double corner = wf * CORNER_SCALE;
    double corner2 = corner / 2.0;

    double xf = x + (w - wf) / 2.0;
    double yf = y + (h - hf) / 2.0;

    GeneralPath gp = new GeneralPath();

    gp.moveTo(xf, yf);

    gp.lineTo(xf + w2 - corner2, yf);
    gp.lineTo(xf + w2 + corner2, yf + corner);
    gp.lineTo(xf + wf - o, yf + corner);
    gp.lineTo(xf + wf - o, yf + hf);
    gp.lineTo(xf, yf + hf);
    gp.closePath();

    g2.setColor(Color.WHITE);
    g2.fill(gp);

    g2.setColor(mColor1);

    g2.draw(gp);

    gp = new GeneralPath();

    gp.moveTo(xf + o, yf + corner + o);
    gp.lineTo(xf + w, yf + corner + o);
    gp.lineTo(xf + w - o, yf + hf);
    gp.lineTo(xf, yf + hf);
    gp.closePath();

    g2.fill(gp);
    g2.draw(gp);

    /*
     * // open part gp = new GeneralPath();
     * 
     * gp.moveTo(x - corner, y + 2 * corner); gp.lineTo(x + w + corner, y + 2 *
     * corner); gp.lineTo(x + w, y + h); gp.lineTo(x, y + h); gp.closePath();
     * 
     * paint = new GradientPaint((float)x, (float)y, COLOR1, (float)x, (float)(y +
     * w), COLOR2);
     * 
     * g2.setPaint(paint); g2.fill(gp);
     */
  }
}
