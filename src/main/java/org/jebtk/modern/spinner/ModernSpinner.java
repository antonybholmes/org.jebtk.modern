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
package org.jebtk.modern.spinner;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.BoxLayout;
import javax.swing.Timer;

import org.jebtk.core.text.TextUtils;
import org.jebtk.modern.AssetService;
import org.jebtk.modern.UI;
import org.jebtk.modern.button.ModernButton;
import org.jebtk.modern.button.ModernClickWidget;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.graphics.icons.MinusVectorIcon;
import org.jebtk.modern.graphics.icons.PlusVectorIcon;
import org.jebtk.modern.text.ModernNumericalTextField;
import org.jebtk.modern.text.ModernTextBorderPanel;
import org.jebtk.modern.text.ModernTextField;

/**
 * The class ModernSpinner.
 */
public class ModernSpinner extends ModernClickWidget {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant SPINNER_CHANGED.
   */
  public static final String SPINNER_CHANGED = "spinner_changed";

  /**
   * The field.
   */
  private final ModernTextField field = new ModernNumericalTextField();

  /**
   * The member inc button.
   */
  protected ModernButton mIncButton = new ModernButton(AssetService.getInstance().loadIcon(PlusVectorIcon.class, 16));

  /**
   * The member dec button.
   */
  protected ModernButton mDecButton = new ModernButton(AssetService.getInstance().loadIcon(MinusVectorIcon.class, 16));

  /**
   * The member min.
   */
  int mMin = 0;

  /**
   * The member max.
   */
  int mMax = 100;

  /**
   * The value.
   */
  protected int mValue = mMin;

  /** The m inc. */
  private int mInc = 0;

  /**
   * The timer.
   */
  private Timer mScrollTimer;

  /**
   * The class IncrementTask.
   */
  private class IncrementTask implements ActionListener {

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      setValue(getValue() + mInc);
    }
  }

  /**
   * The Class KeyEvents.
   */
  private class KeyEvents extends KeyAdapter {
    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
     */
    @Override
    public void keyPressed(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        try {
          parse();
        } catch (ParseException e1) {
          e1.printStackTrace();
        }
      }
    }
  }

  /**
   * The Class MouseEvents.
   */
  private class MouseEvents extends MouseAdapter {
    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseReleased(MouseEvent e) {
      mScrollTimer.stop();
    }
  }

  /**
   * Instantiates a new modern spinner.
   *
   * @param min the min
   * @param max the max
   */
  public ModernSpinner(int min, int max) {
    mMin = min;
    mMax = max;

    setup();
  }

  /**
   * Instantiates a new modern spinner.
   *
   * @param min   the min
   * @param max   the max
   * @param value the value
   */
  public ModernSpinner(int min, int max, int value) {
    mMin = min;
    mMax = max;

    setup();

    setValue(value);
  }

  /**
   * Setup.
   */
  protected void setup() {
    setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

    add(mDecButton);

    UI.setSize(field, new Dimension(50, WIDGET_HEIGHT));
    ModernTextBorderPanel p = new ModernTextBorderPanel(field);

    add(p);
    add(mIncButton);

    field.addKeyListener(new KeyEvents());

    MouseEvents me = new MouseEvents();

    mIncButton.setOpaque(false);
    mIncButton.addMouseListener(me);
    mIncButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        mInc = getStep();

        mScrollTimer.start();
      }
    });

    mDecButton.setOpaque(false);
    mDecButton.addMouseListener(me);
    mDecButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        mInc = -getStep();

        mScrollTimer.start();
      }
    });

    setValue(mMin);

    setMaximumSize(new Dimension(Short.MAX_VALUE, WIDGET_HEIGHT));
    setMinimumSize(new Dimension(WIDGET_HEIGHT, WIDGET_HEIGHT));

    setOpaque(false);

    mScrollTimer = new Timer(0, new IncrementTask());
    mScrollTimer.setDelay(100);
  }

  /**
   * Parses the.
   *
   * @throws ParseException the parse exception
   */
  private void parse() throws ParseException {
    setValue(TextUtils.parseInt(field.getText()));
  }

  /**
   * Sets the value.
   *
   * @param value the new value
   */
  public void setValue(int value) {
    setValue(value, true);
  }

  /**
   * Sets the value.
   *
   * @param value   the value
   * @param trigger the trigger
   */
  protected void setValue(int value, boolean trigger) {
    if (value < mMin) {
      value = mMin;
    }

    if (value > mMax) {
      value = mMax;
    }

    mValue = value;

    field.setText(Integer.toString(value));

    if (trigger) {
      fireClicked(new ModernClickEvent(this, SPINNER_CHANGED));
    }
  }

  /**
   * Gets the value.
   *
   * @return the value
   */
  public int getValue() {
    return mValue;
  }

  /**
   * Decrement.
   */
  public void decrement() {
    setValue(getValue() - getStep());
  }

  /**
   * Gets the step.
   *
   * @return the step
   */
  public int getStep() {
    return mInc;
  }

  /**
   * Increment.
   */
  public void increment() {
    setValue(getValue() + getStep());
  }
}
