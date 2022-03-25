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

import org.jebtk.core.ColorUtils;
import org.jebtk.core.Mathematics;
import org.jebtk.core.Props;

/**
 * The class FolderVectorIcon.
 */
public class FolderVectorIcon extends ModernVectorScalableIcon {

  /**
   * The constant WIDTH_SCALE.
   */
  protected static final double WIDTH_SCALE = 1;

  /**
   * The constant CORNER_SCALE.
   */
  // private static final double CORNER_SCALE_TAB = 0.1;

  private static final double CORNER_SCALE = 0.2;

  /** The Constant TAB_HEIGHT. */
  private static final double TAB_HEIGHT = 0.08;

  /** The Constant TAB_WIDTH. */
  private static final double TAB_WIDTH = 0.5;

  /** The Constant REAR_OFFSET. */
  private static final double REAR_OFFSET = 0.08;

  /** The Constant HEIGHT_SCALE. */
  protected static final double HEIGHT_SCALE = 0.9;

  /** The Constant MIN_SIZE. */
  private static final int MIN_SIZE = 1;

  /** The Constant COLOR1. */
  private static final Color COLOR1 = ColorUtils.decodeHtmlColor("#ffe680");

  /** The Constant COLOR2. */
  private static final Color COLOR2 = ColorUtils.decodeHtmlColor("#ffcc00");

  /**
   * Instantiates a new folder vector icon.
   */
  public FolderVectorIcon() {
    super(COLOR1, COLOR2);
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
    w = Mathematics.makeMult2(w * WIDTH_SCALE);
    h = Mathematics.makeMult2(w * HEIGHT_SCALE);

    int offset = Math.max(MIN_SIZE, Mathematics.makeMult2(h * REAR_OFFSET));

    int tabHeight = Math.max(MIN_SIZE, Mathematics.makeMult2(h * TAB_HEIGHT));
    int tabWidth = Math.max(MIN_SIZE, Mathematics.makeMult2(w * TAB_WIDTH));

    x = x + (w - w) / 2;
    y = y + (h - h) / 2;

    // Rear

    g2.setColor(COLOR2);

    int corner = Math.max(MIN_SIZE, (int) (w * CORNER_SCALE));

    g2.fillRoundRect(x, y, tabWidth, h, corner, corner);

    // corner = (int)(w * CORNER_SCALE);

    int rh = h - tabHeight;

    g2.fillRoundRect(x, y + tabHeight, w, rh, corner, corner);

    g2.setColor(COLOR1);

    rh = h - tabHeight - offset;

    g2.fillRoundRect(x, y + tabHeight + offset, w, rh, corner, corner);

    /*
     * GeneralPath gp = new GeneralPath();
     * 
     * gp.moveTo(x, y);
     * 
     * gp.lineTo(x + w2 - corner2, y); gp.lineTo(x + w2 + corner2, y + corner);
     * gp.lineTo(x + w, y + corner); gp.lineTo(x + w, y + h); gp.lineTo(x, y + h);
     * gp.closePath();
     * 
     * 
     * g2.setColor(Color.WHITE); g2.fill(gp);
     * 
     * g2.setColor(mColor);
     * 
     * g2.draw(gp);
     * 
     * gp = new GeneralPath();
     * 
     * gp.moveTo(x, y + corner); gp.lineTo(x + w, y + corner); gp.lineTo(x + w, y +
     * h); gp.lineTo(x, y + h); gp.closePath();
     * 
     * g2.fill(gp);
     */
  }
}
