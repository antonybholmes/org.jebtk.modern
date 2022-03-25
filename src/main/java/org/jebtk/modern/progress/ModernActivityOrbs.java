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

import org.jebtk.core.Mathematics;
import org.jebtk.core.settings.SettingsService;
import org.jebtk.modern.ModernWidget;

/**
 * Shows animated balls to indicate something is happening.
 * 
 * @author Antony Holmes
 *
 */
public class ModernActivityOrbs extends ModernWidget {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The xpos.
   */
  private double[] xpos = { -4.5, -4, -3.5, -3 };

  /**
   * The increment.
   */
  private double increment = 0.1;

  /**
   * The w.
   */
  private int w = 8;

  /**
   * The mean.
   */
  private double mean = 0;

  /**
   * The sd.
   */
  private double sd = 0.5;

  /**
   * The var.
   */
  private double var = sd * sd;

  /**
   * The exdiv.
   */
  private double exdiv = 2 * var;

  /**
   * The sc.
   */
  private double sc = 1 / (sd * Math.sqrt(2 * Math.PI));

  /**
   * The max x.
   */
  private int maxX = 5;

  /**
   * The timer.
   */
  private ProgressWorker timer;

  /**
   * The constant COLOR.
   */
  private static final Color COLOR = SettingsService.getInstance().getColor("theme.activity-bar.dot-color");

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
      double a;
      double s;
      double dx;

      while (!isCancelled()) {

        for (int i = 0; i < xpos.length; ++i) {
          a = sc * Math.exp(-Mathematics.square(xpos[i] - mean) / exdiv);

          s = 1 + a;

          dx = s * increment;

          // System.err.println("pos " + xpos[i] + " " + dx + " " + a);

          xpos[i] += dx;

          if (xpos[i] > maxX) {
            xpos[i] = -5;
          }
        }

        repaint();

        Thread.sleep(20);
      }

      return null;
    }

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  public void drawForegroundAA(Graphics2D g2) {
    int y = getInsets().top + (mInternalRect.getH() - w) / 2;

    g2.setColor(COLOR);

    int offset = getWidth() / 2;

    int p = getWidth() / 4;

    for (int i = 0; i < xpos.length; ++i) {
      // System.err.println(xpos[i] + " " + y);

      g2.fillOval((int) (xpos[i] * p) + offset, y, w, w);
    }
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
