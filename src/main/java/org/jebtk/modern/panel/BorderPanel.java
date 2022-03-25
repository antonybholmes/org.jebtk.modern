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
package org.jebtk.modern.panel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.LayoutManager;

// TODO: Auto-generated Javadoc
/**
 * Replacement ModernButton with a common skin.
 *
 * @author Antony Holmes
 *
 */
public class BorderPanel extends ModernPanel {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The color t.
   */
  private Color colorT = null;

  /**
   * The color l.
   */
  private Color colorL = null;

  /**
   * The color b.
   */
  private Color colorB = null;

  /**
   * The color r.
   */
  private Color colorR = null;

  /**
   * Instantiates a new border panel.
   *
   * @param colorT the color t
   * @param colorL the color l
   * @param colorB the color b
   * @param colorR the color r
   * @param layout the layout
   */
  public BorderPanel(Color colorT, Color colorL, Color colorB, Color colorR, LayoutManager layout) {
    super(layout);

    this.colorT = colorT;
    this.colorL = colorL;
    this.colorB = colorB;
    this.colorR = colorR;
  }

  /**
   * Instantiates a new border panel.
   *
   * @param colorT the color t
   * @param colorL the color l
   * @param colorB the color b
   * @param colorR the color r
   */
  public BorderPanel(Color colorT, Color colorL, Color colorB, Color colorR) {
    this.colorT = colorT;
    this.colorL = colorL;
    this.colorB = colorB;
    this.colorR = colorR;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.panel.ModernPanel#drawBackground(java.awt.Graphics2D)
   */
  @Override
  public void drawBackground(Graphics2D g2) {
    super.drawBackground(g2);

    if (colorT != null) {
      g2.setColor(colorT);

      g2.drawLine(0, 0, getWidth(), 0);
    }

    if (colorL != null) {
      g2.setColor(colorL);

      g2.drawLine(0, 0, 0, getHeight());
    }

    if (colorB != null) {
      g2.setColor(colorL);

      int y = getHeight() - 1;

      g2.drawLine(0, y, getWidth(), y);
    }

    if (colorR != null) {
      g2.setColor(colorL);

      int x = getWidth() - 1;

      g2.drawLine(x, 0, x, getHeight());
    }
  }
}
