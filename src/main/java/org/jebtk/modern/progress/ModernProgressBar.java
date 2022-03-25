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
package org.jebtk.modern.progress;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.theme.ThemeService;

/**
 * The class ModernProgressBar.
 */
public class ModernProgressBar extends ModernWidget implements ProgressEventListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The progress model.
   */
  protected ProgressModel progressModel = new ProgressModel();

  /**
   * Instantiates a new modern progress bar.
   */
  public ModernProgressBar() {
    setup();
  }

  /**
   * Instantiates a new modern progress bar.
   *
   * @param model the model
   */
  public ModernProgressBar(ProgressModel model) {
    this.progressModel = model;

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    progressModel.addProgressListener(this);

    UI.setSize(this, new Dimension(200, WIDGET_HEIGHT), BORDER);
  }

  /**
   * Gets the model.
   *
   * @return the model
   */
  public ProgressModel getModel() {
    return progressModel;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  public void drawForegroundAA(Graphics2D g2) {
    int w = getWidth() - this.getInsets().left - this.getInsets().right;

    int y1 = getHeight() - this.getInsets().top;

    int h = y1 - this.getInsets().bottom;

    g2.setColor(Color.WHITE);
    g2.fillRect(this.getInsets().left, this.getInsets().top, w - 1, h - 1);

    g2.setColor(ThemeService.getInstance().getColors().getGray(1));
    g2.drawRect(this.getInsets().left, this.getInsets().top, w - 1, h - 1);

    int width = (int) (w * progressModel.getPercentComplete());

    if (width < 2) {
      return;
    }

    Rectangle rect = new Rectangle(this.getInsets().left, this.getInsets().top, width, h);

    paintSelected(g2, rect);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.progress.ProgressEventListener#progressUpdated(org.
   * abh. lib.ui.modern.progress.ProgressEvent)
   */
  public void progressUpdated(ProgressEvent e) {
    repaint();
  }
}
