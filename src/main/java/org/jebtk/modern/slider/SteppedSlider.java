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
import java.util.List;

import org.jebtk.core.Mathematics;
import org.jebtk.core.collections.CollectionUtils;
import org.jebtk.math.Linspace;

/**
 * Basic features of a slider control that jumps from step to step.
 *
 * @author Antony Holmes
 *
 */
public abstract class SteppedSlider extends Slider {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member value.
   */
  protected double mValue = 1;

  /** The m marks. */
  protected double[] mMarks;

  /** The m pc. */
  protected int mPc = 0;

  /** The m gap. */
  protected double mGap = 0;

  /**
   * Instantiates a new stepped slider.
   *
   * @param min   the min
   * @param max   the max
   * @param value the value
   */
  public SteppedSlider(double min, double max, double value) {
    this(value, Linspace.evenlySpaced(min, max, 1));
  }

  /**
   * Instantiates a new stepped slider.
   *
   * @param value the value
   * @param marks the marks
   */
  public SteppedSlider(double value, List<Double> marks) {
    this(value, CollectionUtils.toArray(marks));
  }

  /**
   * Instantiates a new stepped slider.
   *
   * @param value the value
   * @param marks the marks
   */
  public SteppedSlider(double value, double... marks) {
    mMarks = marks;

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
    mPc = Mathematics.bound(mPc + 1, 0, mMarks.length - 1);

    setValue(mMarks[mPc]);
  }

  /**
   * Decrement.
   */
  @Override
  public void decrement() {
    System.err.println("made 2 " + mPc + " " + mMarks[mPc]);

    mPc = Mathematics.bound(mPc - 1, 0, mMarks.length - 1);

    System.err.println("made 3 " + mPc + " " + mMarks[mPc]);

    setValue(mMarks[mPc]);
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
    mValue = Mathematics.bound(v, getMinValue(), getMaxValue());

    // use a binary search to set the pc to the greatest index whose
    // mark value is less than v

    mPc = getPc(mValue);

    System.err.println("made 4 " + v + " " + mValue + " " + mPc);

    repaint();
  }

  /**
   * Return the value of a given mark.
   *
   * @param index the index
   * @return the value
   */
  public double getValue(int index) {
    return mMarks[Mathematics.bound(index, 0, mMarks.length - 1)];
  }

  /**
   * Get the closest index of the mark closest and less than or equal to v.
   *
   * @param v the v
   * @return the pc
   */
  public int getPc(double v) {
    v = Mathematics.bound(v, getMinValue(), getMaxValue());

    // use a binary search to set the pc to the greatest index whose
    // mark value is less than v

    if (v <= getMinValue()) {
      return 0;
    } else if (v >= getMaxValue()) {
      return mMarks.length - 1;
    } else {
      int i1 = 0;
      int i2 = mMarks.length - 1;

      while (i2 - i1 > 1) {
        int i = (i1 + i2) / 2;

        double v2 = mMarks[i];

        if (v2 <= v) {
          i1 = i;
        } else {
          i2 = i;
        }
      }

      return i1;
    }
  }

  /**
   * Gets the index from X.
   *
   * @param x the x
   * @return the index from X
   */
  public int getIndexFromX(int x) {
    return (int) (x / mGap);
  }

  /**
   * Gets the closest value.
   *
   * @return the closest value
   */
  public double getClosestValue() {
    return getClosestValue(getValue());
  }

  /**
   * Gets the closest value.
   *
   * @param v the v
   * @return the closest value
   */
  public double getClosestValue(double v) {
    return mMarks[getPc(v)];
  }

  /**
   * Gets the pc.
   *
   * @return the pc
   */
  public int getPc() {
    return mPc;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.slider.Slider#getMinValue()
   */
  @Override
  public double getMinValue() {
    return mMarks[0];
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.slider.Slider#getMaxValue()
   */
  @Override
  public double getMaxValue() {
    return mMarks[mMarks.length - 1];
  }

  /**
   * Resize.
   */
  public void resize() {
    mGap = getInternalRect().getW() / (double) (mMarks.length - 1);
  }
}
