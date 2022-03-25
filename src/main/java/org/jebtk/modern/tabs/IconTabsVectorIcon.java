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

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.graphics.icons.ModernVectorIcon;

/**
 * Group vector icon showing 3 colored balls spaced equally around a circle.
 * 
 * @author Antony Holmes
 *
 */
public class IconTabsVectorIcon extends ModernVectorIcon {

  private String mLetter;

  public IconTabsVectorIcon(char c) {
    mLetter = Character.toString(c);
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

    Color color = props.getColor("color");

    g2.setColor(color);
    g2.fillOval(x, y, w, w);

    g2.setColor(Color.WHITE);
    g2.setFont(ModernWidget.FONT);

    Point p = ModernWidget.getStringCenterPlotCoordinates(g2, w, h, mLetter);

    g2.drawString(mLetter, x + p.x, y + p.y);

    /*
     * Color color = (Color)props[0];
     * 
     * int blockSize = (w - 2) / 3;
     * 
     * g2.setColor(color);
     * 
     * x += (w - blockSize * 3 - 2) / 2; y = x;
     * 
     * for (int i = 0; i < 3; ++i) { int x1 = x;
     * 
     * for (int j = 0; j < 3; ++j) { g2.fillRect(x1, y, blockSize, blockSize);
     * 
     * x1 += blockSize + 1; }
     * 
     * y += blockSize + 1; }
     */
  }

}
