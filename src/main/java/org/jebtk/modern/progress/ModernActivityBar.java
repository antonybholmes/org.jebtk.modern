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
import java.awt.Graphics2D;

import javax.swing.SwingWorker;

import org.jebtk.modern.ModernWidget;

/**
 * Shows animated balls to indicate something is happening.
 * 
 * @author Antony Holmes
 *
 */
public class ModernActivityBar extends ModernWidget {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant BAR_WIDTH.
   */
  private static final int BAR_WIDTH = 100;

  /**
   * The xpos.
   */
  private int xpos = -BAR_WIDTH;

  /**
   * The increment.
   */
  private int increment = 5;

  /**
   * The w.
   */
  private int w = 2;

  /**
   * The timer.
   */
  private ProgressWorker timer;

  /**
   * The constant COLOR.
   */
  private static final Color COLOR = ModernWidget.SELECTED_COLOR;

  /**
   * The class ProgressWorker.
   */
  private class ProgressWorker extends SwingWorker<Void, Void> {

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.SwingWorker#doInBackground()
     */
    @Override
    public Void doInBackground() throws InterruptedException {
      while (!isCancelled()) {

        xpos += increment;

        if (xpos > getWidth()) {
          xpos = -BAR_WIDTH;
        }

        repaint();

        Thread.sleep(20);
      }

      return null;
    }

  }

  /*
   * @Override public void drawBackground(Graphics2D g2) { int y = (getHeight() -
   * w) / 2;
   * 
   * g2.setColor(BACKGROUND);
   * 
   * g2.fillRect(0, y, getWidth(), w); }
   */

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    int y = (getHeight() - w) / 2;

    g2.setColor(COLOR);

    g2.fillRect(xpos, y, BAR_WIDTH, w);
  }

  /**
   * Start.
   */
  public void start() {
    if (timer != null) {
      stop();
    }

    timer = new ProgressWorker();
    timer.execute();
  }

  /**
   * Stop.
   */
  public void stop() {
    timer.cancel(true);
  }
}
