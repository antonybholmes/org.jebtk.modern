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

import org.jebtk.core.Mathematics;
import org.jebtk.core.Props;
import org.jebtk.core.settings.SettingsService;
import org.jebtk.modern.theme.ThemeService;

/**
 * Down arrow vector icon for combobox buttons etc.
 * 
 * @author Antony Holmes
 *
 */
public abstract class TriangleVectorIcon extends ModernVectorScalableIcon {

  /**
   * The constant WIDTH_SCALE.
   */
  public static final double WIDTH_SCALE = SettingsService.getInstance()
      .getDouble("theme.icons.triangle-icon.width-scale");

  /**
   * The constant ANGLE.
   */
  protected static final double ANGLE = Math.tan(Mathematics.QUARTER_PI);

  /**
   * The constant COLOR.
   */
  protected static final Color COLOR = ThemeService.getInstance().getColors().getGray(10);

  /** The m wf. */
  protected double mWf;

  /** The m W 2. */
  protected double mW2;

  /** The m hf. */
  protected double mHf;

  /** The m xf. */
  protected double mXf;

  /** The m yf. */
  protected double mYf;

  /**
   * Instantiates a new triangle vector icon.
   */
  public TriangleVectorIcon() {
    this(COLOR);
  }

  /**
   * Instantiates a new triangle vector icon.
   *
   * @param color1 the color 1
   */
  public TriangleVectorIcon(Color color1) {
    super(color1 != null ? color1 : COLOR);
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

    mWf = w * WIDTH_SCALE;

    mW2 = mWf / 2.0;

    mHf = mW2 * ANGLE;
  }
}
