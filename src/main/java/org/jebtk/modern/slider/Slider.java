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

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.event.ChangeEventProducer;
import org.jebtk.core.event.ChangeListener;
import org.jebtk.core.event.ChangeListeners;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.css.CSSWidget;

/**
 * Basic features of a slider control without any ui elements.
 *
 * @author Antony Holmes
 *
 */
public abstract class Slider extends CSSWidget implements ChangeEventProducer {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant VALUE_CHANGED.
   */
  public static final String VALUE_CHANGED = "value_changed";

  /**
   * The member value.
   */
  protected double mValue = 1;

  /**
   * The member change listeners.
   */
  private final ChangeListeners mChangeListeners = new ChangeListeners();

  /**
   * The member click listeners.
   */
  // private ModernClickListeners mClickListeners = new ModernClickListeners();

  public Slider() {
    UI.setSize(this, 160, ModernWidget.WIDGET_HEIGHT);
  }

  /**
   * Increment.
   */
  public abstract void increment();

  /**
   * Decrement.
   */
  public abstract void decrement();

  /**
   * Gets the value.
   *
   * @return the value
   */
  public abstract double getValue();

  /**
   * Gets the int value.
   *
   * @return the int value
   */
  public int getIntValue() {
    return (int) getValue();
  }

  /**
   * Sets the value.
   *
   * @param value the new value
   */
  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.slider.Slider#setValue(int)
   */
  public void setValue(double value) {
    updateValue(value);

    fireChanged(new ChangeEvent(this, VALUE_CHANGED));
  }

  /**
   * Update the slider without causing a change event.
   *
   * @param v the v
   */
  public abstract void updateValue(double v);

  /**
   * Gets the min value.
   *
   * @return the min value
   */
  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.slider.Slider#getMinValue()
   */
  public abstract double getMinValue();

  /**
   * Gets the max value.
   *
   * @return the max value
   */
  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.slider.Slider#getMaxValue()
   */
  public abstract double getMaxValue();

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.event.ChangeEventProducer#addChangeListener(org.abh.lib.event.
   * ChangeListener)
   */
  @Override
  public void addChangeListener(ChangeListener l) {
    mChangeListeners.addChangeListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.event.ChangeEventProducer#removeChangeListener(org.abh.lib.
   * event. ChangeListener)
   */
  @Override
  public void removeChangeListener(ChangeListener l) {
    mChangeListeners.removeChangeListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.event.ChangeEventProducer#fireChanged(org.abh.lib.event.
   * ChangeEvent)
   */
  @Override
  public void fireChanged(ChangeEvent event) {
    mChangeListeners.fireChanged(event);
  }

  /**
   * @Override public void addClickListener(ModernClickListener l) {
   *           mClickListeners.addClickListener(l); }
   * 
   * @Override public void removeClickListener(ModernClickListener l) {
   *           mClickListeners.removeClickListener(l); }
   * 
   * @Override public void fireClicked(ModernClickEvent e) {
   *           mClickListeners.fireClicked(e); }
   */
}
