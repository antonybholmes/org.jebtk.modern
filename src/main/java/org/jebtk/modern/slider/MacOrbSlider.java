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

import org.jebtk.modern.BorderService;
import org.jebtk.modern.theme.ThemeService;

/**
 * Slider with an orb button and plus minus controls.
 *
 * @author Antony Holmes
 *
 */
public class MacOrbSlider extends OrbSlider {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The Constant HIGHLIGHT. */
  protected static final Color HIGHLIGHT = ThemeService.getInstance().getColors().getTheme32(16);

  /** The m bar height. */
  protected int mBarHeight = 4;

  /**
   * Instantiates a new modern orb slider.
   *
   * @param min   the min
   * @param max   the max
   * @param value the value
   */
  public MacOrbSlider(double min, double max, double value) {
    super(min, max, value);

    init();
  }

  /**
   * Instantiates a new mac orb slider.
   *
   * @param value  the value
   * @param values the values
   */
  public MacOrbSlider(double value, double... values) {
    super(value, values);

    init();
  }

  /**
   * Instantiates a new mac orb slider.
   *
   * @param value  the value
   * @param values the values
   */
  public MacOrbSlider(double value, List<Double> values) {
    super(value, values);

    init();
  }

  /**
   * Inits the.
   */
  private void init() {
    setRadius(6);

    setBorder(BorderService.getInstance().createLeftRightBorder(1));

    setAnimations(new MacOrbAnimation(this));
  }

  /**
   * Sets the bar height.
   *
   * @param h the new bar height
   */
  public void setBarHeight(int h) {
    mBarHeight = h;

    repaint();
  }

  /*
   * @Override public void drawBackgroundAA(Graphics2D g2) { int p =
   * (int)(getInsets().left + mSliderDiameter % 2 + mPc * mGap);
   * 
   * drawBar(g2, p); }
   */

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  /*
   * @Override public void drawForegroundAA(Graphics2D g2) { int p =
   * (int)(getInsets().left + mSliderDiameter % 2 + mPc * mGap);
   * 
   * drawOrb(g2, p); }
   */

  /**
   * Draw bar base.
   *
   * @param g2 the g 2
   * @param p  the p
   */
  protected void drawBarBase(Graphics2D g2, int p) {
    g2.setColor(LINE_COLOR);

    int x = getInsets().left + mSliderRadius;
    int y = (getHeight() - mBarHeight) / 2;

    g2.fillRoundRect(x, y, mInternalRect.getW() - mSliderDiameter, mBarHeight, mBarHeight, mBarHeight);
  }

  /**
   * Draw bar.
   *
   * @param g2 the g 2
   * @param p  the p
   */
  protected void drawBar(Graphics2D g2, int p) {
    drawBarBase(g2, p);

    int x = getInsets().left + mSliderRadius;
    int y = (getHeight() - mBarHeight) / 2;

    g2.setColor(HIGHLIGHT);

    g2.fillRoundRect(x, y, p - x + mSliderRadius, mBarHeight, mBarHeight, mBarHeight);
  }

  /**
   * Draw orb.
   *
   * @param g2 the g 2
   * @param p  the p
   */
  protected void drawOrb(Graphics2D g2, int p) {
    int y = getHeight() / 2;

    g2.setColor(Color.WHITE);
    g2.fillOval(p, y - mSliderRadius, mSliderDiameter, mSliderDiameter);

    g2.setColor(LINE_COLOR);
    g2.drawOval(p, y - mSliderRadius, mSliderDiameter, mSliderDiameter);
  }
}
