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
import org.jebtk.modern.ribbon.Ribbon;

/**
 * Run arrow icon, like the standard play button.
 * 
 * @author Antony Holmes
 *
 */
public class RunVectorIcon extends ModernVectorIcon {

  /**
   * The constant ANGLE.
   */
  private static final double ANGLE = Math.sin(Mathematics.QUARTER_PI);

  /**
   * The constant SCALE.
   */
  private static final double SCALE = 1; // SettingsService.getInstance().getDouble("theme.icons.run-icon.width-scale");

  /**
   * The constant BACKGROUND.
   */
  private static final Color BACKGROUND = Ribbon.BAR_BACKGROUND; // SettingsService.getInstance().getColor("theme.icons.run-icon.colors.foreground");

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.icons.ModernIcon#drawForeground(java.awt.Graphics2D,
   * java.awt.Rectangle)
   */
  @Override
  public void drawIcon(Graphics2D g2, int x, int y, int w, int h, Props props) {

    double hf = w * SCALE;
    double h2 = hf / 2.0;
    double wf = h * ANGLE;

    double xf = x + (w - wf) / 2.0;
    double yf = y + (h - hf) / 2.0;

    GeneralPath gp = new GeneralPath();

    gp.moveTo(xf, yf);
    gp.lineTo(xf + wf, yf + h2);
    gp.lineTo(xf, yf + hf);
    gp.closePath();

    g2.setColor(BACKGROUND);
    g2.fill(gp);
  }

//  @Override
//  public Image getFxImage(int w) {
//    Canvas canvas = new Canvas(w, w);
//    
//    GraphicsContext gc = canvas.getGraphicsContext2D();
//    
//    
//    double hf = w * SCALE;
//    double h2 = hf / 2.0;
//    double wf = w * ANGLE;
//
//    double xf = (w - wf) / 2.0;
//
//    gc.setFill(FxUtils.toFxColor(BACKGROUND));
//    
//    gc.beginPath();
//
//    gc.moveTo(xf, xf);
//    gc.lineTo(xf + wf, xf + h2);
//    gc.lineTo(xf, xf + hf);
//    gc.fill();
//    gc.closePath();
//    
//    return canvas.snapshot(null, null);
//  }
}
