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
package org.jebtk.modern.tabs;

import java.awt.Graphics2D;

// TODO: Auto-generated Javadoc
/**
 * Simple horizontal tabs using labels as buttons.
 * 
 * 
 * @author Antony Holmes
 *
 */
public class TextTabsTriangle extends TextTabs {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The Constant SIZE. */
  private static final int SIZE = 15;

  /** The Constant HALF_SIZE. */
  private static final int HALF_SIZE = SIZE / 2;

  /** The Constant OFFSET. */
  private static final int OFFSET = 1;

  /**
   * Instantiates a new text tabs triangle.
   *
   * @param model the model
   */
  public TextTabsTriangle(TabsModel model) {
    this(model, false);
  }

  /**
   * Instantiates a new text tabs.
   *
   * @param model  the model
   * @param center the center
   */
  public TextTabsTriangle(TabsModel model, boolean center) {
    super(model, center);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    super.drawForegroundAA(g2);

    g2.setColor(LIGHT_LINE_COLOR);

    int x1 = getInsets().left;
    int x2 = x1 + mOffset;

    for (int i = 0; i < getTabsModel().getSelectedIndex(); ++i) {
      x2 += mTabWidths.get(i);
    }

    int cumWidth = getTabsModel().getSelectedIndex() * GAP_WIDTH;

    x2 += cumWidth;

    int y = getHeight() - OFFSET;

    g2.drawLine(x1, y, x2, y);

    x1 = x2;

    int w = mTabWidths.get(getTabsModel().getSelectedIndex());

    int w2 = w / 2;

    x2 = x1 + w;

    // draw line from edge of tab to center less the triangle width
    g2.drawLine(x1, y, x1 + w2 - HALF_SIZE, y);

    // draw line from edge of triangle to edge of tab
    g2.drawLine(x2 - w2 + HALF_SIZE, y, x2, y);

    // draw the inverted triangle/arrow
    g2.drawLine(x1 + w2 - HALF_SIZE, y, x1 + w2, y - HALF_SIZE);
    g2.drawLine(x1 + w2, y - HALF_SIZE, x1 + w2 + HALF_SIZE, y);

    x1 = getInsets().left + mOffset;

    for (int i = 0; i <= getTabsModel().getSelectedIndex(); ++i) {
      x1 += mTabWidths.get(i);
    }

    x1 += cumWidth;

    x2 = getWidth() - getInsets().right;

    g2.drawLine(x1, y, x2, y);
  }
}
