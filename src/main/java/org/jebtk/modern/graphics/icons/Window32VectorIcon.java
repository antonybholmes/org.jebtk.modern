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

import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.theme.ThemeService;

/**
 * Simple pane icon.
 * 
 * @author Antony Holmes
 *
 */
public class Window32VectorIcon extends ModernVectorIcon {

  /**
   * The constant WIDTH.
   */
  protected static final int WIDTH = 32;

  /**
   * The constant HEIGHT.
   */
  protected static final int HEIGHT = 28;

  /**
   * The constant BAR_HEIGHT.
   */
  protected static final int BAR_HEIGHT = 6;

  /**
   * The member bar color.
   */
  protected Color mBarColor;

  /**
   * Instantiates a new window32 vector icon.
   */
  public Window32VectorIcon() {
    this(ThemeService.getInstance().getColors().getTheme(4));
  }

  /**
   * Instantiates a new window32 vector icon.
   *
   * @param barColor the bar color
   */
  public Window32VectorIcon(Color barColor) {
    mBarColor = barColor;
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
    x = x + (w - WIDTH) / 2;
    y = y + (h - HEIGHT) / 2;

    g2.setColor(Color.WHITE);
    g2.fillRect(x, y, WIDTH, HEIGHT);

    g2.setColor(ModernWidget.LINE_COLOR);
    g2.drawRect(x, y, WIDTH - 1, HEIGHT);

    g2.setColor(mBarColor);
    g2.fillRect(x, y, WIDTH, BAR_HEIGHT);

  }

}
