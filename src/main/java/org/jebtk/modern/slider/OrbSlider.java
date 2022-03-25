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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.List;

import org.jebtk.core.Mathematics;

/**
 * Slider with an orb button and plus minus controls.
 *
 * @author Antony Holmes
 *
 */
public abstract class OrbSlider extends SteppedSlider {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant SLIDER_RADIUS.
   */
  protected int mSliderRadius = 5;

  /**
   * The constant SLIDER_DIAMETER.
   */
  protected int mSliderDiameter = mSliderRadius * 2;

  /**
   * The drag.
   */
  private boolean mDrag;

  /**
   * The slider width.
   */
  protected int mSliderWidth;

  /**
   * The highlight slider.
   */
  protected boolean mHighlightSlider = false;

  /**
   * The class MouseMoveEvents.
   */
  private class MouseMotionEvents extends MouseMotionAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.
     * MouseEvent)
     */
    @Override
    public void mouseDragged(MouseEvent e) {
      if (!mDrag) {
        return;
      }

      double value = getValue(getIndexFromX(translateX(e.getX())));

      setValue(getClosestValue(value));
    }
  }

  /**
   * The class MouseEvents.
   */
  private class MouseEvents extends MouseAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseExited(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseExited(MouseEvent e) {
      mHighlightSlider = false;

      repaint();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseEntered(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseEntered(MouseEvent e) {
      mHighlightSlider = true;

      repaint();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(MouseEvent e) {
      if (e.getX() >= getInsets().left && e.getX() < getWidth() - getInsets().right) {
        mDrag = true;
      }

      double value = getValue(getIndexFromX(translateX(e.getX())));

      setValue(getClosestValue(value));
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseReleased(MouseEvent e) {
      mDrag = false;
    }
  }

  /**
   * The Class MouseWheelEvents.
   */
  private class MouseWheelEvents implements MouseWheelListener {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseWheelListener#mouseWheelMoved(java.awt.event.
     * MouseWheelEvent)
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
      if (e.getWheelRotation() > 0) {
        increment();
      } else {
        decrement();
      }
    }
  }

  /**
   * Instantiates a new orb slider.
   */
  public OrbSlider() {
    this(1, 100, 1);
  }

  /**
   * Instantiates a new orb slider.
   *
   * @param min   the min
   * @param max   the max
   * @param value the value
   */
  public OrbSlider(double min, double max, double value) {
    super(min, max, value);

    init();
  }

  /**
   * Instantiates a new orb slider.
   *
   * @param value  the value
   * @param values the values
   */
  public OrbSlider(double value, double... values) {
    super(value, values);

    init();
  }

  /**
   * Instantiates a new orb slider.
   *
   * @param value  the value
   * @param values the values
   */
  public OrbSlider(double value, List<Double> values) {
    super(value, values);

    init();
  }

  /**
   * Inits the.
   */
  private void init() {
    addMouseListener(new MouseEvents());
    addMouseMotionListener(new MouseMotionEvents());
    addMouseWheelListener(new MouseWheelEvents());
  }

  /**
   * Sets the radius.
   *
   * @param radius the new radius
   */
  public void setRadius(int radius) {
    mSliderRadius = radius;
    mSliderDiameter = radius * 2;

    resize();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.slider.SteppedSlider#resize()
   */
  @Override
  public void resize() {
    mSliderWidth = mInternalRect.getW() - 2 * mSliderRadius;

    mGap = mSliderWidth / (double) (mMarks.length - 1);

    repaint();
  }

  /**
   * Translate X.
   *
   * @param x the x
   * @return the int
   */
  public int translateX(int x) {
    return Mathematics.bound(x - mSliderRadius - getInsets().left, 0, mSliderWidth);
  }
}
