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
package org.jebtk.modern.slider;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

import org.jebtk.modern.theme.ThemeService;

/**
 * Slider with an orb button and plus minus controls.
 *
 * @author Antony Holmes
 *
 */
public class ModernOrbSlider extends OrbSlider {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The Constant HIGHLIGHT. */
  protected static final Color HIGHLIGHT = ThemeService.getInstance().getColors().getGray(7);

  /** The Constant COLOR. */
  protected static final Color COLOR = ThemeService.getInstance().getColors().getGray(9);

  /**
   * Instantiates a new modern orb slider.
   *
   * @param min   the min
   * @param max   the max
   * @param value the value
   */
  public ModernOrbSlider(double min, double max, double value) {
    super(min, max, value);
  }

  /**
   * Instantiates a new modern orb slider.
   *
   * @param value  the value
   * @param values the values
   */
  public ModernOrbSlider(double value, double... values) {
    super(value, values);
  }

  /**
   * Instantiates a new modern orb slider.
   *
   * @param value  the value
   * @param values the values
   */
  public ModernOrbSlider(double value, List<Double> values) {
    super(value, values);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    // draw buttons

    //
    // the slider line
    //

    g2.setColor(mHighlightSlider ? HIGHLIGHT : COLOR);

    int y = getHeight() / 2;

    g2.drawLine(getInsets().left + mSliderDiameter, y, mRect.getW() - getInsets().right - mSliderDiameter, y);

    // g2.drawLine(getInsets().left + SLIDER_DIAMETER, y - 1,
    // mRect.getW() - getInsets().right - SLIDER_DIAMETER,
    // y - 1);

    // g2.drawLine(getInsets().left + SLIDER_DIAMETER, y + 1,
    // mRect.getW() - getInsets().right - SLIDER_DIAMETER,
    // y + 1);

    // the mid point line

    double x = getWidth() / 2;

    g2.drawLine((int) x, y - mSliderRadius, (int) x, y + mSliderRadius);

    //
    // Show the orb
    //

    // g2.setColor(Color.WHITE);

    x = getInsets().left + mSliderDiameter % 2;

    // double cv = getClosestValue();

    // Since bins can be unevenly spaced, we first find out the closest
    // bin by value and then translate that to a pixel value by
    // multiplying it by the bin size. We then add on the difference
    // between x and the closest x as a fraction of the bin width
    // multiplied by the bin width in pixels.

    x += mPc * mGap; // + (getValue() - cv) / (getValue(mPc + 1) - cv) * mGap;

    g2.setColor(Color.WHITE);

    g2.fillOval((int) x, y - mSliderRadius, mSliderDiameter, mSliderDiameter);
  }
}
