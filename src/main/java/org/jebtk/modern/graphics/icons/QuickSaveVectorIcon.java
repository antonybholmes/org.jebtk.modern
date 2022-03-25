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
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;

/**
 * Vector based save icon.
 * 
 * @author Antony Holmes
 *
 */
public class QuickSaveVectorIcon extends ModernVectorIcon {

  /**
   * The constant CORNER.
   */
  private static final int CORNER = 3;
  private Color mColor;

  public QuickSaveVectorIcon() {
    this(ModernWidget.TEXT_COLOR); // Ribbon.BAR_BACKGROUND);
  }

  public QuickSaveVectorIcon(Color color) {
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

    GeneralPath gp = new GeneralPath();

    gp.moveTo(x, y);
    gp.lineTo(x + w, y);
    gp.lineTo(x + w, y + h);
    gp.lineTo(x + CORNER, y + h);
    gp.lineTo(x, y + h - CORNER);
    gp.closePath();

    Area s1 = new Area(gp);

    Area s2 = new Area(new Rectangle2D.Double(x + 1, y + 1, 14, 8));
    s1.exclusiveOr(s2);

    s2 = new Area(new Rectangle2D.Double(x + 5, y + h - 5, 7, 4));
    s1.exclusiveOr(s2);

    g2.setColor(mColor);

    g2.fill(s1);
  }
}
