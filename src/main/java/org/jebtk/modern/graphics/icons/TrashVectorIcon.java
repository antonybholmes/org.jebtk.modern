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
import java.awt.geom.GeneralPath;

import org.jebtk.core.Mathematics;
import org.jebtk.core.Props;

/**
 * The class FolderVectorIcon.
 */
public class TrashVectorIcon extends ModernVectorScalableIcon {

  /**
   * The constant WIDTH_SCALE.
   */
  protected static final double WIDTH_SCALE = 0.9;

  /**
   * The constant CORNER_SCALE.
   */
  // private static final double CORNER_SCALE_TAB = 0.1;

  private static final double TAPER_SCALE = 0.2;

  /** The Constant LID_GAP. */
  private static final double LID_GAP = 0.1;

  /** The Constant HANDLE_SIZE. */
  private static final double HANDLE_SIZE = TAPER_SCALE;

  /** The Constant HANDLE_WIDTH. */
  private static final double HANDLE_WIDTH = 0.5;

  /** The Constant LID_SIZE. */
  private static final double LID_SIZE = TAPER_SCALE;

  /**
   * Instantiates a new trash vector icon.
   */
  public TrashVectorIcon() {
    super(Color.BLACK);
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
    int w1 = Mathematics.makeMult2(w * WIDTH_SCALE);

    x = x + (w - w1) / 2;
    y = y + (h - w1) / 2;

    // Rear

    g2.setColor(mColor1);

    int width = Mathematics.makeMult2(w1 * HANDLE_WIDTH);
    int height = (int) Math.max(1, HANDLE_SIZE * w1);

    int x1 = x + (w1 - width) / 2;
    int y1 = 0;
    g2.fillRect(x1, y1, width, height);

    y1 += height;

    height = Math.max(1, (int) (LID_SIZE * w1));
    g2.fillRect(x, y1, w1, height);

    y1 += height + Math.max(1, (int) (w1 * LID_GAP));

    width = Math.max(1, (int) (w1 * TAPER_SCALE));

    x1 = x + (w - width) / 2;

    GeneralPath path = new GeneralPath();

    path.moveTo(x, y1);
    path.lineTo(x + w1, y1);
    path.lineTo(x + w1 - width, y + w1);
    path.lineTo(x + width, y + w1);
    path.closePath();

    g2.fill(path);
  }
}
