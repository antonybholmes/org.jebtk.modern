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
package org.jebtk.modern.table.header;

import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import org.jebtk.core.geom.IntRect;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.theme.ThemeService;

/**
 * Paints the corner of a table.
 *
 * @author Antony Holmes
 */
public class ModernTableCorner extends ModernWidget {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawBackground(java.awt.Graphics2D)
   */
  @Override
  public void drawBackground(Graphics2D g2) {
    // fill(g2, getRect(),
    // ThemeService.getInstance().getColors().getHighlight(1));

    fillBackground(g2);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    IntRect rect = getRect();

    g2.setColor(ModernWidget.LINE_COLOR);

    /*
     * GradientPaint gradient = new GradientPaint(x, 0,
     * ThemeService.getInstance().getColors().getHighlight(1), x + w, 0, LINE_COLOR,
     * false);
     * 
     * g2.setPaint(gradient);
     */

    g2.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);

    /*
     * gradient = new GradientPaint(0, y,
     * ThemeService.getInstance().getColors().getHighlight(1), 0, y + h, LINE_COLOR,
     * false);
     * 
     * g2.setPaint(gradient);
     */

    g2.drawLine(rect.getX() + rect.getW() - 1, rect.getY(), rect.getX() + rect.getW() - 1, rect.getY() + rect.getH());

    g2.setColor(ThemeService.getInstance().getColors().getGray(2));

    // Draw triangle in corner

    int o = getHeight() / 4;
    int h = o * 2;

    GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 3);

    polygon.moveTo(getWidth() - o, o);

    polygon.lineTo(getWidth() - o, o + h);
    polygon.lineTo(getWidth() - o - h, o + h);

    polygon.closePath();
    g2.fill(polygon);
  }

}
