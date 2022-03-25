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

import java.awt.Color;
import java.awt.Graphics2D;

import org.jebtk.core.ColorUtils;
import org.jebtk.modern.UI;

// TODO: Auto-generated Javadoc
/**
 * The class RibbonBackMenuItem.
 */
public class RibbonBackMenuItem extends RibbonMenuItem {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  // public static final Color HIGHLIGHT_COLOR = new Color(0x2c5aa8);
  // public static final Color SELECTED_COLOR = new Color(0x5f8dd3);

  // private static final int ICON_OFFSET = 10;
  // private static final int ICON_WIDTH = 16;

  /**
   * The constant HEIGHT.
   */
  public static final int HEIGHT = 36;

  /**
   * The constant SIZE.
   */
  // public static final Dimension SIZE =
  // new Dimension(64, 64);

  public static final Color BASE_COLOR = ColorUtils.getTransparentColor25(Color.WHITE);

  /**
   * Instantiates a new ribbon back menu item.
   */
  public RibbonBackMenuItem() {
    super(UI.MENU_BACK);

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    UI.setSize(this, RIBBON_MENU_ITEM_SIZE);

    super.setSelected(false);

    setAnimations(new RibbonBackMenuAnimation(this));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ribbon.RibbonMenuItem#setSelected(boolean)
   */
  @Override
  public void setSelected(boolean selected) {
    // do nothing
  }

  /*
   * @Override public void drawBackground(Graphics2D g2) {
   * 
   * }
   */

  @Override
  public void drawForegroundAA(Graphics2D g2) {
    /*
     * Graphics2D g2Temp = ImageUtils.createAAStrokeGraphics(g2);
     * 
     * try { g2Temp.setStroke(new BasicStroke(2));
     * 
     * if (mHighlight) { g2Temp.setColor(Color.WHITE); } else {
     * g2Temp.setPaint(BASE_COLOR); }
     * 
     * int x = (mRect.getW() - HEIGHT) / 2; int y = (mRect.getH() - HEIGHT) / 2;
     * 
     * drawIcon(g2Temp, x, y); } finally { g2Temp.dispose(); }
     */
  }

  /**
   * Draw icon.
   *
   * @param g2 the g 2
   * @param x  the x
   * @param y  the y
   */
  public static void drawIcon(Graphics2D g2, int x, int y) {
    g2.drawOval(x, y, HEIGHT, HEIGHT);

    int x1 = x + DOUBLE_PADDING;
    int y1 = y + HEIGHT / 2;

    g2.drawLine(x1, y1, x1 + HEIGHT - 2 * DOUBLE_PADDING, y1);

    g2.drawLine(x1, y1, x1 + PADDING, y1 - PADDING);
    g2.drawLine(x1, y1, x1 + PADDING, y1 + PADDING);
  }
}