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

import org.jebtk.core.Props;

/**
 * Down arrow vector icon for combobox buttons etc.
 * 
 * @author Antony Holmes
 *
 */
public class TriangleLeftVectorIcon extends TriangleVectorIcon {

  /**
   * Instantiates a new triangle left vector icon.
   */
  public TriangleLeftVectorIcon() {
    this(COLOR);
  }

  /**
   * Instantiates a new triangle left vector icon.
   *
   * @param color1 the color 1
   */
  public TriangleLeftVectorIcon(Color color1) {
    super(color1);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.graphics.icons.TriangleVectorIcon#drawIcon(java.awt.
   * Graphics2D, int, int, int, int)
   */
  @Override
  public void drawIcon(Graphics2D g2, int x, int y, int w, int h, Props props) {
    super.drawIcon(g2, x, y, w, h, props);

    mXf = x + (w - mHf) / 2.0;
    mYf = y + (h - mWf) / 2.0;

    GeneralPath gp = new GeneralPath();
    gp.moveTo(mXf, mYf + mW2);
    gp.lineTo(mXf + mHf, mYf);
    gp.lineTo(mXf + mHf, mYf + mWf);
    gp.closePath();

    g2.setColor(COLOR);
    g2.fill(gp);
  }
}
