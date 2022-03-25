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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.Timer;

/**
 * Slider with an orb button and plus minus controls.
 *
 * @author Antony Holmes
 *
 */
public class BarButtonSlider extends SteppedSlider {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant BAR_WIDTH.
   */
  protected static final int BAR_WIDTH = 6;

  /**
   * The constant BAR_OFFSET.
   */
  protected static final int BAR_OFFSET = BAR_WIDTH / 2;

  /**
   * The constant BAR_HEIGHT.
   */
  protected static final int BAR_HEIGHT = 14;

  /**
   * The constant MID_TICK_SIZE.
   */
  protected static final int MID_TICK_SIZE = 6;

  /**
   * The button icon width.
   */
  protected int BUTTON_ICON_WIDTH = 12;

  /**
   * The button mark width.
   */
  protected int BUTTON_MARK_WIDTH = 4;

  /**
   * The button width.
   */
  protected static int BUTTON_WIDTH = 16;

  /**
   * The drag.
   */
  private boolean drag;

  /**
   * The slider width.
   */
  protected int mSliderWidth;

  /**
   * For when users hold down on a scroll button, the scroll will auto scroll.
   */
  private Timer scrollTimer;

  /**
   * The highlight minus button.
   */
  protected boolean highlightMinusButton;

  /**
   * The highlight plus button.
   */
  protected boolean highlightPlusButton;

  /**
   * The highlight slider.
   */
  protected boolean highlightSlider;

  /**
   * The class IncrementSlider.
   */
  private class IncrementSlider implements ActionListener {

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {
      setValue(mValue + 1);
    }
  }

  /**
   * The class DecrementSlider.
   */
  private class DecrementSlider implements ActionListener {

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {
      setValue(mValue - 1);
    }
  }

  /**
   * The class ComponentEvents.
   */
  private class ComponentEvents extends ComponentAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.ComponentAdapter#componentResized(java.awt.event.
     * ComponentEvent)
     */
    @Override
    public void componentResized(ComponentEvent e) {
      mSliderWidth = mInternalRect.getW() - 2 * (BAR_OFFSET + 1) - 2 * BUTTON_WIDTH;
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
      highlightMinusButton = false;
      highlightPlusButton = false;
      highlightSlider = false;

      repaint();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(MouseEvent e) {
      if (e.getX() < BUTTON_WIDTH + getInsets().left) {
        createScollTimer(new DecrementSlider());
      } else if (e.getX() >= getWidth() - getInsets().right - BUTTON_WIDTH) {
        createScollTimer(new IncrementSlider());
      } else {
        drag = true;
      }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseReleased(MouseEvent e) {
      drag = false;

      if (scrollTimer != null) {
        scrollTimer.stop();
      }
    }
  }

  /**
   * The class MouseMotionEvents.
   */
  private class MouseMotionEvents implements MouseMotionListener {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.
     * MouseEvent)
     */
    @Override
    public void mouseDragged(MouseEvent e) {
      if (!drag) {
        return;
      }

      int x = e.getX() - BUTTON_WIDTH - BAR_OFFSET - getInsets().left;

      x = Math.max(0, Math.min(mSliderWidth, x));

      double min = getMinValue();

      double d = getMaxValue() - min;

      double r = x / (double) mSliderWidth;

      double value = (r * d) + min;

      setValue(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseMoved(MouseEvent e) {
      highlightMinusButton = e.getX() >= getInsets().left && e.getX() < BUTTON_WIDTH + getInsets().left;
      highlightPlusButton = e.getX() >= mRect.getW() - BUTTON_WIDTH - getInsets().right;
      highlightSlider = !highlightMinusButton && !highlightPlusButton;

      repaint();
    }
  }

  /**
   * Instantiates a new bar button slider.
   */
  public BarButtonSlider() {
    this(1, 100, 1);
  }

  /**
   * Instantiates a new bar button slider.
   *
   * @param min   the min
   * @param max   the max
   * @param value the value
   */
  public BarButtonSlider(int min, int max, int value) {
    super(min, max, value);

    addComponentListener(new ComponentEvents());
    addMouseListener(new MouseEvents());
    addMouseMotionListener(new MouseMotionEvents());
  }

  /**
   * Creates the scoll timer.
   *
   * @param l the l
   */
  private void createScollTimer(ActionListener l) {
    // stop any existing timers

    if (scrollTimer != null) {
      scrollTimer.stop();
    }

    scrollTimer = new Timer(0, l);
    scrollTimer.setDelay(100);
    scrollTimer.start();
  }
}
