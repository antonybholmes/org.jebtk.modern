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
package org.jebtk.modern.splitpane;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Creates a horizontal split pane that uses three holds for the grab handle.
 * 
 * @author Antony Holmes
 *
 */
public class ModernVSplitPaneEllipsis extends VSplitPane {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant WIDTH.
   */
  private static final int WIDTH = 2;

  /**
   * The constant DY.
   */
  private static final int DY = 5;

  /**
   * The constant H.
   */
  private static final int H = DY * 2;

  /**
   * The constant COLOR.
   */
  private static final Color COLOR = LINE_COLOR;

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    int y = getInsets().top; // mDividerMidPoint - WIDTH / 2;

    int h = getInternalRect().getH();

    g2.setColor(COLOR);

    for (int i = 0; i < mDividerLocations.size() - 1; ++i) {
      y = getInsets().top + (int) (h * mDividerLocations.get(i));

      int x = (getHeight() - H) / 2;

      for (int j = 0; j < 3; ++j) {
        g2.fillRect(x, y, WIDTH, WIDTH);

        x += DY;
      }
    }
  }
}
