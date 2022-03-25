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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import org.jebtk.modern.progress.ProgressEvent;
import org.jebtk.modern.progress.ProgressModel;
import org.jebtk.modern.theme.ThemeService;

/**
 * The class ModernStatusAnimatedProgressBar.
 */
public class ModernStatusAnimatedProgressBar extends ModernStatusProgressBar implements ActionListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The timer.
   */
  private Timer timer;

  /**
   * The s1.
   */
  private int s1 = -20;

  /**
   * The s2.
   */
  private int s2 = 0;

  /**
   * The min s1.
   */
  private int minS1;

  /**
   * Instantiates a new modern status animated progress bar.
   *
   * @param model the model
   */
  public ModernStatusAnimatedProgressBar(ProgressModel model) {
    super(model);

    setup();
  }

  /**
   * Instantiates a new modern status animated progress bar.
   */
  public ModernStatusAnimatedProgressBar() {
    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    timer = new Timer(30, this);
  }

  /**
   * Start.
   */
  public void start() {
    s1 = -20;
    s2 = 0;
    minS1 = 0;

    timer.start();
  }

  /**
   * Stop.
   */
  public void stop() {
    timer.stop();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.status.ModernStatusProgressBar#drawBackground(java.
   * awt. Graphics2D)
   */
  @Override
  public void drawBackground(Graphics2D g2) {
    fill(g2, ThemeService.getInstance().getColors().getTheme(5), getRect());
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.status.ModernStatusProgressBar#drawForegroundAA(java.
   * awt.Graphics2D)
   */
  public void drawForegroundAA(Graphics2D g2) {
    int y1 = getHeight() - this.getInsets().top;

    int h = y1 - this.getInsets().bottom;

    fill(g2, Color.WHITE, getInternalRect());

    Rectangle r = new Rectangle(this.getInsets().left + (int) (minS1 * mInternalRect.getW() / 100.0),
        this.getInsets().top, (int) ((s2 - minS1) * mInternalRect.getW() / 100.0), h);

    fill(g2, ThemeService.getInstance().getColors().getTheme(1), r);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.progress.ModernProgressBar#progressUpdated(org.abh.
   * lib. ui.modern.progress.ProgressEvent)
   */
  public void progressUpdated(ProgressEvent e) {
    stop();

    s1 = 0;
    minS1 = 0;
    s2 = progressModel.getPercentComplete();

    super.progressUpdated(e);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  public void actionPerformed(ActionEvent e) {
    repaint();

    s2 = Math.min(100, s2 + 1);
    s1 = Math.min(100, s1 + 1);

    minS1 = Math.max(s1, 0);

    if (s1 == s2) {
      s1 = -20;
      s2 = 0;
    }
  }
}
