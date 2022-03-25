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
package org.jebtk.modern.status;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import org.jebtk.modern.progress.ModernProgressBar;
import org.jebtk.modern.progress.ProgressModel;
import org.jebtk.modern.theme.ThemeService;

/**
 * The class ModernStatusProgressBar.
 */
public class ModernStatusProgressBar extends ModernProgressBar {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new modern status progress bar.
   *
   * @param model the model
   */
  public ModernStatusProgressBar(ProgressModel model) {
    super(model);
  }

  /**
   * Instantiates a new modern status progress bar.
   */
  public ModernStatusProgressBar() {

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawBackground(java.awt.Graphics2D)
   */
  @Override
  public void drawBackground(Graphics2D g2) {
    fill(g2, ThemeService.getInstance().getColors().getTheme(5), getRect());
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.progress.ModernProgressBar#drawForegroundAA(java.awt.
   * Graphics2D)
   */
  public void drawForegroundAA(Graphics2D g2) {
    int y1 = getHeight() - this.getInsets().top;

    int h = y1 - this.getInsets().bottom;

    fill(g2, Color.WHITE, getInternalRect());

    int width = (int) (mInternalRect.getW() * progressModel.getPercentComplete());

    if (width < 2) {
      return;
    }

    Rectangle r = new Rectangle(this.getInsets().left, this.getInsets().top, width, h);

    fill(g2, ThemeService.getInstance().getColors().getTheme(1), r);
  }
}
