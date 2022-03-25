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
package org.jebtk.modern.ribbon;

import java.awt.Dimension;
import java.awt.Graphics2D;

import org.jebtk.modern.ModernWidget;

// TODO: Auto-generated Javadoc
/**
 * The class RibbonSubSectionSeparator.
 */
public class RibbonSubSectionSeparator extends ModernWidget {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant WIDTH.
   */
  public static final int WIDTH = 11;

  /**
   * The constant MINIMUM_SIZE.
   */
  public static final Dimension MINIMUM_SIZE = new Dimension(WIDTH, 1);

  /**
   * The constant SIZE.
   */
  public static final Dimension SIZE = new Dimension(WIDTH, Short.MAX_VALUE);

  /**
   * The constant MID_POINT.
   */
  public static final int MID_POINT = WIDTH / 2;

  /**
   * Instantiates a new ribbon sub section separator.
   */
  public RibbonSubSectionSeparator() {

    setMinimumSize(MINIMUM_SIZE);
    setPreferredSize(MINIMUM_SIZE);
    setMaximumSize(SIZE);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {

    g2.setColor(Ribbon.SEPARATOR_COLOR);

    int x = mRect.getW() / 2;
    int y = (mRect.getH() - RibbonSection.SEPARATOR_HEIGHT) / 2;

    g2.drawLine(x, y, x, y + RibbonSection.SEPARATOR_HEIGHT);

    /*
     * GradientPaint gradient = new GradientPaint(0, y, Color.WHITE, 0, h2 + y,
     * RibbonSection.LINE_COLOR, false);
     * 
     * g2.setPaint(gradient); g2.drawLine(MID_POINT, y, MID_POINT, y + h);
     * 
     * gradient = new GradientPaint(0, h2 + y, LINE_COLOR, 0, h + y, Color.WHITE,
     * false);
     * 
     * g2.setPaint(gradient);
     * 
     * g2.drawLine(MID_POINT, y + h2, MID_POINT, y + h);
     */
  }
}
