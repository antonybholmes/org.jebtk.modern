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
package org.jebtk.modern.button;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics2D;

import org.jebtk.modern.UI;
import org.jebtk.modern.theme.ThemeService;

// TODO: Auto-generated Javadoc
/**
 * Creates a large roundel button to indicate user should press it to do
 * something.
 * 
 * @author Antony Holmes
 *
 */
public class ModernRoundelButton extends ModernButton {

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
  private static final int HEIGHT = 64;

  /**
   * The constant SIZE.
   */
  public static final Dimension SIZE = new Dimension(72, 72);

  /**
   * Instantiates a new modern roundel button.
   *
   * @param text the text
   */
  public ModernRoundelButton(String text) {
    super(text);

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    UI.setSize(this, SIZE);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.button.ModernButtonWidget#drawBackground(java.awt.
   * Graphics2D)
   */
  @Override
  public final void drawBackground(Graphics2D g2) {
    fillBackground(g2, getRect());
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.button.ModernButton#drawForegroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    g2.setStroke(new BasicStroke(2));

    if (mHighlight) {
      g2.setPaint(ThemeService.getInstance().getColors().getTheme(4));
    } else {
      g2.setPaint(ThemeService.getInstance().getColors().getTheme(2));
    }

    int x = (mRect.getW() - HEIGHT) / 2;
    int y = (mRect.getH() - HEIGHT) / 2;

    g2.drawOval(x, y, HEIGHT, HEIGHT);

    x += DOUBLE_PADDING;
    int x2 = x + HEIGHT - 2 * DOUBLE_PADDING;
    y = mRect.getH() / 2;

    g2.drawLine(x, y, x2, y);

    g2.drawLine(x2, y, x2 - DOUBLE_PADDING, y - DOUBLE_PADDING);
    g2.drawLine(x2, y, x2 - DOUBLE_PADDING, y + DOUBLE_PADDING);
  }
}