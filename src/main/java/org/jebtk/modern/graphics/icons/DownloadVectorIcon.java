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

import org.jebtk.core.Mathematics;
import org.jebtk.core.Props;

/**
 * Download arrow vector icon.
 * 
 * @author Antony Holmes
 *
 */
public class DownloadVectorIcon extends ModernVectorColorIcon {

  /**
   * The constant COLOR.
   */
  private static final Color COLOR = Color.BLACK; // SettingsService.getInstance().getColor("theme.icons.download-icon.colors.foreground");

  /**
   * Instantiates a new download vector icon.
   */
  public DownloadVectorIcon() {
    this(COLOR);
  }

  /**
   * Instantiates a new download vector icon.
   *
   * @param color the color
   */
  public DownloadVectorIcon(Color color) {
    super(color);
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

    double wf = Mathematics.makeMult2(Math.min(w, h) * 0.75);
    double w2 = wf * 0.5;
    double w4 = wf * 0.25;
    double w34 = wf * 0.75;

    double xf = x + (w - wf) / 2;
    double yf = y; // + (w - wf) / 2;

    GeneralPath gp = new GeneralPath();

    gp.moveTo(xf + w4, yf);

    gp.lineTo(xf + w34, yf);
    gp.lineTo(xf + w34, yf + w2);
    gp.lineTo(xf + wf, yf + w2);
    gp.lineTo(xf + w2, yf + wf);
    gp.lineTo(xf, yf + w2);
    gp.lineTo(xf + w4, yf + w2);

    gp.closePath();

    g2.setColor(mColor);
    g2.fill(gp);

    double w5 = Math.max(1, wf * 0.25);

    g2.fillRect((int) xf, (int) (yf + w - w5), (int) wf, (int) w5);
  }
}
