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

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.jebtk.core.Mathematics;

/**
 * Basic features of a slider control that jumps from step to step.
 *
 * @author Antony Holmes
 *
 */
public abstract class ContinuousSlider extends Slider {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  private static final int MID_ZONE = 2;

  /**
   * The member value.
   */
  protected double mValue = 1;

  /** The m gap. */
  protected double mGap = 0;

  private final double mMin;

  private final double mMid;

  private final double mMax;

  protected double mD1;

  protected double mD2;

  protected double mGap2;

  protected double mGap1;

  protected int mHalfD;

  protected double mP1;

  protected double mP2;

  protected int mPixelInc;

  /**
   * Instantiates a new stepped slider.
   *
   * @param value the value
   * @param marks the marks
   */
  public ContinuousSlider(double value, double min, double mid, double max) {
    mMin = min;
    mMid = mid;
    mMax = max;

    mD1 = mid - min;
    mD2 = max - mid;

    setLayout(null);

    setValue(value);

    addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        resize();
      }
    });

    addKeyListener(new KeyListener() {

      @Override
      public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_UP:
        case KeyEvent.VK_RIGHT:
          increment();
          break;
        case KeyEvent.VK_DOWN:
        case KeyEvent.VK_LEFT:
          decrement();
          break;
        }
      }

      @Override
      public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub

      }

      @Override
      public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub

      }
    });

    // Force a sync in case another resize event is not triggered.
    resize();
  }

  /**
   * Increment.
   */
  @Override
  public void increment() {
    setValue(getValue(vToX(getValue()) + mPixelInc));
  }

  /**
   * Decrement.
   */
  @Override
  public void decrement() {
    setValue(getValue(vToX(getValue()) - mPixelInc));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.slider.Slider#getValue()
   */
  @Override
  public double getValue() {
    return mValue;
  }

  /**
   * Update the slider without causing a change event.
   *
   * @param v the v
   */
  @Override
  public void updateValue(double v) {
    mValue = bound(v);

    // use a binary search to set the pc to the greatest index whose
    // mark value is less than v

    repaint();
  }

  private double bound(double v) {
    return Mathematics.bound(v, getMinValue(), getMaxValue());
  }

  /**
   * Return the value of a given mark.
   *
   * @param index the index
   * @return the value
   */
  public double getValue(int x) {

    if (Math.abs(x - mHalfD) <= MID_ZONE) {
      return mMid;
    } else if (x > mHalfD) {
      return bound(mMid + (x - mHalfD) * mGap2);
    } else {
      // if (x < mHalfD)
      return bound(mMin + x * mGap1);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.slider.Slider#getMinValue()
   */
  @Override
  public double getMinValue() {
    return mMin;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.slider.Slider#getMaxValue()
   */
  @Override
  public double getMaxValue() {
    return mMax;
  }

  /**
   * Returns the current slider value in terms of pixels
   * 
   * @return
   */
  public int vToX() {
    return vToX(getValue());
  }

  public int vToX(double v) {
    if (v < mMid) {

      // System.err.println("ss " + v + " " + mMin + " " + mP1 + " " +
      // Math.round((v -
      // mMin) * mP1));

      return (int) Math.round((v - mMin) * mP1);
    } else if (v > mMid) {
      return mHalfD + (int) Math.round((v - mMid) * mP2);
    } else {
      return mHalfD;
    }
  }

  /**
   * Resize.
   */
  public void resize() {
    mHalfD = getInternalRect().getW() / 2;

    mPixelInc = getInternalRect().getW() / 20;

    // how much each pixel of movement is worth
    mGap1 = mD1 / mHalfD;
    mGap2 = mD2 / mHalfD;

    mP1 = mHalfD / mD1;
    mP2 = mHalfD / mD2;
  }
}
