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
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.GeneralPath;

import org.jebtk.core.Props;
import org.jebtk.core.settings.SettingsService;

/**
 * Warning icon.
 * 
 * @author Antony Holmes
 *
 */
public class WarningVectorIcon extends ModernMessageIcon {

  /** The Constant COLOR. */
  private static final Color COLOR = SettingsService.getInstance()
      .getColor("theme.icons.warning-icon.colors.background");

  /** The Constant BORDER_COLOR. */
  private static final Color BORDER_COLOR = SettingsService.getInstance()
      .getColor("theme.icons.warning-icon.colors.border");

  /** The Constant TEXT. */
  private static final String TEXT = "!";

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.icons.ModernIcon#drawForeground(java.awt.Graphics2D,
   * java.awt.Rectangle)
   */
  @Override
  public void drawIcon(Graphics2D g2, int x, int y, int w, int h, Props props) {

    int height = (int) (Math.sin(Math.PI / 3.0) * w);

    y = y + (h - height) / 2;
    int w2 = w / 2;

    GeneralPath gp = new GeneralPath();
    gp.moveTo(x + w2, y);
    gp.lineTo(x, y + height);
    gp.lineTo(x + w, y + height);
    gp.closePath();

    GradientPaint paint = new GradientPaint(0, y, COLOR, 0, y + height, BORDER_COLOR);

    // g2.setColor(COLOR);
    g2.setPaint(paint);
    g2.fill(gp);

    g2.setColor(BORDER_COLOR);
    g2.draw(gp);

    Rectangle smallRect = new Rectangle(x, w - height, w, height);

    // System.err.println(smallRect + " " + rect);

    drawScaledText(g2, w, smallRect, TEXT, Color.BLACK);

    // g2.setColor(ModernWidget.TEXT_COLOR);

    // g2.drawString("!",
    // x + (w - g2.getFontMetrics().stringWidth("!")) / 2,
    // y + mHeight - ModernWidget.PADDING);
  }
}
