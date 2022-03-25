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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.text.ParseException;
import java.util.List;

import javax.swing.Timer;

import org.jebtk.core.Mathematics;
import org.jebtk.core.collections.CollectionUtils;
import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.text.Formatter;
import org.jebtk.core.text.Formatter.NumberFormatter;
import org.jebtk.core.text.Parser;
import org.jebtk.core.text.TextUtils;
import org.jebtk.math.Linspace;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.animation.AnimationEventType;
import org.jebtk.modern.slider.Slider;
import org.jebtk.modern.text.ModernNumericalTextField;
import org.jebtk.modern.text.ModernTextField;
import org.jebtk.modern.theme.DrawUI;
import org.jebtk.modern.text.ITextProperty;

/**
 * Editable numerical text box with controls to increment and decrement the
 * value. This is a compact version with the controls within the bounds of the
 * text box, so it remains the normal widget height.
 * 
 * @author Antony Holmes
 *
 */
public class ModernCompactSpinner extends Slider implements ITextProperty {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant SPINNER_CHANGED.
   */
  public static final String SPINNER_CHANGED = "spinner_changed";

  /**
   * The member field.
   */
  ModernTextField mField = new ModernNumericalTextField();

  /**
   * The member value.
   */
  protected double mValue = -1;

  /**
   * The member increment timer.
   */
  private Timer mIncrementTimer;

  /**
   * The member decrement timer.
   */
  private Timer mDecrementTimer;

  /**
   * The constant BUTTON_WIDTH.
   */
  private static final int BUTTON_WIDTH = 16;

  /**
   * The constant BORDER.
   */
  public static final int BORDER = 2;

  /**
   * The constant BORDER_2.
   */
  private static final int BORDER_2 = 2 * BORDER;

  /**
   * The constant DELAY.
   */
  // Timer increment delay in milliseconds
  private static final int DELAY = 120;

  private static final DrawUI SPINNER_UI = new SpinnerUI();

  /**
   * The member min button x.
   */
  int mMinButtonX = -1;

  /**
   * The member button y divider.
   */
  int mButtonYDivider;

  /**
   * The member upper button.
   */
  boolean mUpperButton = true;

  /**
   * The member highlight.
   */
  boolean mHighlight = false;

  /**
   * The member button zone.
   */
  boolean mButtonZone = false;

  /**
   * The member type.
   */
  private String mSuffix = null;

  /** The m marks. */
  private double[] mMarks;

  /** The m pc. */
  private int mPc;

  /** The m dp. */
  private NumberFormatter mDp;

  /**
   * Determines whether to bound the input value within the spinner range.
   */
  private boolean mBounded = true;

  // private RasterIcon mOutlineIcon;

  /**
   * The class MouseEvents.
   */
  private class MouseEvents implements MouseListener {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(MouseEvent e) {
      if (!mButtonZone) {
        return;
      }

      if (mUpperButton) {
        mIncrementTimer.start();
      } else {
        mDecrementTimer.start();
      }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseReleased(MouseEvent e) {
      stopTimers();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseClicked(MouseEvent e) {
      // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseEntered(MouseEvent e) {
      mHighlight = true;

      repaint();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseExited(MouseEvent e) {
      mHighlight = false;
      mButtonZone = false;
      // mIncrementTimer.stop();
      // mDecrementTimer.stop();

      repaint();
    }
  }

  /**
   * The class MouseMotionActions.
   */
  private class MouseMotionActions implements MouseMotionListener {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.
     * MouseEvent)
     */
    @Override
    public void mouseDragged(MouseEvent e) {
      // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseMoved(MouseEvent e) {
      if (e.getX() < mMinButtonX) {
        mButtonZone = false;
      } else {
        mButtonZone = true;

        mUpperButton = e.getY() < mButtonYDivider;
      }

      repaint();
    }

  }

  /**
   * The class ComponentActions.
   */
  private class ComponentActions extends ComponentAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.ComponentAdapter#componentResized(java.awt.event.
     * ComponentEvent)
     */
    @Override
    public void componentResized(ComponentEvent e) {
      mMinButtonX = getWidth() - getInsets().right - BUTTON_WIDTH;

      // mOutlineIcon = new RasterIcon(new RoundedRectIcon(null, null, -1), new
      // Dimension(getWidth(), getHeight()));

      mButtonYDivider = getHeight() / 2;

      mField.setBounds(PADDING, BORDER, mMinButtonX - DOUBLE_PADDING, getHeight() - BORDER_2);
    }
  }

  /**
   * The class FieldMouseActions.
   */
  private class FieldMouseActions extends MouseAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseEntered(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseEntered(MouseEvent e) {
      mHighlight = true;
      mButtonZone = false;

      // repaint();

      getAnimations().get("css-hover").fireEvent(AnimationEventType.MOUSE_ENTERED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseExited(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseExited(MouseEvent e) {
      mHighlight = false;
      mButtonZone = false;

      repaint();
      // getAnimations().get("css-hover").fireEvent(AnimationEventType.MOUSE_EXITED);
    }
  }

  /**
   * The class KeyEvents.
   */
  private class KeyEvents extends KeyAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
     */
    @Override
    public void keyPressed(KeyEvent e) {
      switch (e.getKeyCode()) {
      case KeyEvent.VK_ENTER:
        parse();

        // fireClicked(new ModernClickEvent(this, SPINNER_CHANGED));
        fireChanged(new ChangeEvent(this, SPINNER_CHANGED));

        break;
      case KeyEvent.VK_UP:
        mIncrementTimer.start();
        break;
      case KeyEvent.VK_DOWN:
        mDecrementTimer.start();
        break;
      }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.KeyAdapter#keyReleased(java.awt.event.KeyEvent)
     */
    @Override
    public void keyReleased(KeyEvent e) {
      stopTimers();
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
      increment();
    }
  }

  /**
   * The class DecrementTask.
   */
  private class DecrementTask implements ActionListener {

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      decrement();
    }
  }

  /**
   * Instantiates a new modern compact spinner.
   *
   * @param value the value
   */
  public ModernCompactSpinner(double value) {
    this(Math.min(0, value), Math.max(10000, value), value);
  }

  /**
   * Instantiates a new modern compact spinner.
   *
   * @param min the min
   * @param max the max
   */
  public ModernCompactSpinner(double min, double max) {
    this(min, max, min);
  }

  /**
   * Instantiates a new modern compact spinner.
   *
   * @param min   the min
   * @param max   the max
   * @param value the value
   */
  public ModernCompactSpinner(double min, double max, double value) {
    this(min, max, value, 1);
  }

  /**
   * Instantiates a new modern compact spinner.
   *
   * @param min     the min
   * @param max     the max
   * @param value   the value
   * @param bounded the bounded
   */
  public ModernCompactSpinner(double min, double max, double value, boolean bounded) {
    this(min, max, value, 1, bounded);
  }

  /**
   * Create a new spinner.
   * 
   * @param min   The minimum allowed value.
   * @param max   The maximum allowed value.
   * @param value The initial value.
   * @param step  The step size.
   */
  public ModernCompactSpinner(double min, double max, double value, double step) {
    this(min, max, value, step, null);
  }

  /**
   * Create a new spinner.
   * 
   * @param min     The minimum value the spinner will go to.
   * @param max     The maximum value the spinner will go to.
   * @param value   The initial value of the spinner.
   * @param step    The increment by which the spinner can change.
   * @param bounded Whether user input is bounded by the minimum and maximum
   *                values.
   */
  public ModernCompactSpinner(double min, double max, double value, double step, boolean bounded) {
    this(min, max, value, step);

    setBounded(bounded);
  }

  /**
   * Instantiates a new modern compact spinner.
   *
   * @param min   the min
   * @param max   the max
   * @param value the value
   * @param type  the type
   */
  public ModernCompactSpinner(double min, double max, double value, String type) {
    this(min, max, value, 1, type);
  }

  /**
   * Instantiates a new modern compact spinner.
   *
   * @param min   the min
   * @param max   the max
   * @param value the value
   * @param step  the step
   * @param type  the type
   */
  public ModernCompactSpinner(double min, double max, double value, double step, String type) {
    this(min, max, value, step, 2, type);
  }

  /**
   * Instantiates a new modern compact spinner.
   *
   * @param min   the min
   * @param max   the max
   * @param value the value
   * @param step  the step
   * @param dp    the dp
   * @param type  the type
   */
  public ModernCompactSpinner(double min, double max, double value, double step, int dp, String type) {
    this(value, dp, type, Linspace.evenlySpaced(min, max, step));
  }

  /**
   * Instantiates a new modern compact spinner.
   *
   * @param value  the value
   * @param suffix the suffix
   * @param range  the range
   */
  public ModernCompactSpinner(double value, String suffix, List<Double> range) {
    this(value, 2, suffix, range);
  }

  /**
   * Instantiates a new modern compact spinner.
   *
   * @param value  the value
   * @param dp     the dp
   * @param suffix the suffix
   * @param range  the range
   */
  public ModernCompactSpinner(double value, int dp, String suffix, List<Double> range) {
    this(value, dp, suffix, CollectionUtils.toArray(range));
  }

  /**
   * Instantiates a new modern compact spinner.
   *
   * @param value the value
   * @param marks the marks
   */
  public ModernCompactSpinner(double value, double... marks) {
    this(value, 2, marks);
  }

  /**
   * Instantiates a new modern compact spinner.
   *
   * @param value the value
   * @param dp    the dp
   * @param marks the marks
   */
  public ModernCompactSpinner(double value, int dp, double... marks) {
    this(value, dp, TextUtils.EMPTY_STRING, marks);
  }

  /**
   * Instantiates a new modern compact spinner.
   *
   * @param value  the value
   * @param dp     the dp
   * @param suffix the suffix
   * @param marks  the marks
   */
  public ModernCompactSpinner(double value, int dp, String suffix, double... marks) {
    mMarks = marks;
    mSuffix = suffix;
    mDp = Formatter.decimal().dp(dp);

    setLayout(null);

    add(mField);

    mIncrementTimer = new Timer(0, new IncrementTask());
    mIncrementTimer.setDelay(DELAY);

    mDecrementTimer = new Timer(0, new DecrementTask());
    mDecrementTimer.setDelay(DELAY);

    mField.addMouseListener(new FieldMouseActions());
    mField.addKeyListener(new KeyEvents());

    addMouseListener(new MouseEvents());
    addMouseMotionListener(new MouseMotionActions());
    addMouseWheelListener(new MouseWheelEvents());
    addComponentListener(new ComponentActions());

    UI.setSize(this, ModernWidget.SMALL_SIZE);

    addAnimations("css-hover");
    addStyleClass("spinner");

    // addAnimations("spinner");
    getDrawStates().add(SPINNER_UI);

    setValue(value);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.Component#addKeyListener(java.awt.event.KeyListener)
   */
  @Override
  public void addKeyListener(KeyListener l) {
    mField.addKeyListener(l);
  }

  /**
   * Determines whether values outside the spinner's range can be entered.
   *
   * @param bounded the new bounded
   */
  public void setBounded(boolean bounded) {
    mBounded = bounded;

    // Trigger a refresh
    setValue(mValue);
  }

  /*
   * @Override public void drawBackgroundAA(Graphics2D g2) {
   * 
   * //paintOutlined(g2, getRect());
   * 
   * //if (mOutlineIcon != null) { // mOutlineIcon.drawIcon(g2, 0, 0); //}
   * 
   * int x = mInternalRect.getX(); int y = mInternalRect.getY();
   * 
   * IntRect intRect = new IntRect(x, y, mMinButtonX - x - BORDER ,
   * mInternalRect.getH());
   * 
   * getWidgetRenderer().drawBackground(g2, intRect);
   * 
   * //IntRect buttonRect = // new IntRect(mMinButtonX, 0, BUTTON_WIDTH,
   * mInternalRect.getH());
   * 
   * if (mHighlight) { getWidgetRenderer().drawButtonOutline(g2, intRect,
   * RenderMode.SELECTED);
   * 
   * //getRenderer().drawSelectedOutline(g2, buttonRect);
   * 
   * if (mButtonZone) { if (mUpperButton) { getWidgetRenderer().drawButton(g2, new
   * IntRect(mMinButtonX, 0, BUTTON_WIDTH, mButtonYDivider), RenderMode.SELECTED);
   * //paintHighlightedOutlined(g2, new Rectangle(mMinButtonX, 0, BUTTON_WIDTH,
   * mButtonYDivider)); //drawHighlightedOutline(g2, new Rectangle(mMinButtonX,
   * mButtonYDivider, BUTTON_WIDTH, mButtonYDivider)); } else {
   * getWidgetRenderer().drawButton(g2, new IntRect(mMinButtonX, mButtonYDivider,
   * BUTTON_WIDTH, mButtonYDivider), RenderMode.SELECTED);
   * //drawHighlightedOutline(g2, new Rectangle(mMinButtonX, 0, BUTTON_WIDTH,
   * mButtonYDivider)); //paintHighlightedOutlined(g2, new Rectangle(mMinButtonX,
   * mButtonYDivider, BUTTON_WIDTH, mButtonYDivider)); } } } else {
   * getWidgetRenderer().drawOutline(g2, intRect); //getRenderer().drawOutline(g2,
   * buttonRect);
   * 
   * } }
   */

  /*
   * @Override public void drawForegroundAA(Graphics2D g2) { int x = getWidth() -
   * 16; int y = (mButtonYDivider - 16) / 2;
   * 
   * UIService.getInstance().loadIcon(TriangleUpVectorIcon.class, 16).drawIcon(g2,
   * x, y, 16);
   * 
   * y += mButtonYDivider;
   * 
   * UIService.getInstance().loadIcon(TriangleDownVectorIcon.class,
   * 16).drawIcon(g2, x, y, 16); }
   */

  /**
   * Parses the.
   */
  private void parse() {
    try {
      double value;

      if (mSuffix != null && mField.getText().endsWith(mSuffix)) {
        // Strip the prefix before parsing
        value = Parser.toDouble(mField.getText().substring(0, mField.getText().length() - mSuffix.length()));
      } else {
        value = Parser.toDouble(mField.getText());
      }

      updateValue(value);
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /**
   * Set the value of the spinner and cause a change event to occur.
   *
   * @param value the new value
   */
  @Override
  public void setValue(double value) {
    updateValue(value);

    // fireClicked(new ModernClickEvent(this, SPINNER_CHANGED));
    fireChanged(new ChangeEvent(this, SPINNER_CHANGED));
  }

  /**
   * Change the value of the spinner without causing a change event.
   *
   * @param v the v
   */
  @Override
  public void updateValue(double v) {
    String vAsText;

    if (Mathematics.isInt(v)) {
      vAsText = Integer.toString((int) v);
    } else {
      vAsText = Double.toString(v);
    }

    updateValue(v, vAsText);
  }

  /**
   * Sets the bin value.
   *
   * @param value the new bin value
   */
  private void setBinValue(double value) {
    updateBinValue(value);

    // fireClicked(new ModernClickEvent(this, SPINNER_CHANGED));
    fireChanged(new ChangeEvent(this, SPINNER_CHANGED));
  }

  /**
   * When a user enters a number, the formatting is left unchanged, when the user
   * users the increment/decrement buttons, the number is formatted to a given
   * number of dp (default 2) for aesthetic reasons.
   *
   * @param v the v
   */
  private void updateBinValue(double v) {
    String vAsText;

    if (Mathematics.isInt(v)) {
      vAsText = Integer.toString((int) v);
    } else {
      vAsText = mDp.format(v);
    }

    updateValue(v, vAsText);
  }

  /**
   * Update value.
   *
   * @param v       the v
   * @param vAsText the v as text
   */
  public void updateValue(double v, String vAsText) {
    if (mBounded) {
      mValue = Mathematics.bound(v, mMarks[0], mMarks[mMarks.length - 1]);
    } else {
      mValue = v;
    }

    StringBuilder buffer = new StringBuilder(vAsText);

    // buffer.append(Double.toString(v));

    if (mSuffix != null) {
      buffer.append(mSuffix);
    }

    setText(buffer.toString());

    mPc = getPc(mValue);

    // System.err.println("value " + mValue + " " + mPc + " " + v + " " +
    // buffer.toString() + " " + mMarks[0] + " " + mMarks[mMarks.length - 1]);
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
   * Return the closest bin index to the current value.
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

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.slider.Slider#getMinValue()
   */
  @Override
  public double getMinValue() {
    return mMarks[0];
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.slider.Slider#getMaxValue()
   */
  @Override
  public double getMaxValue() {
    return mMarks[mMarks.length - 1];
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.slider.Slider#getValue()
   */
  @Override
  public double getValue() {
    // Ensure value matches what was typed
    parse();

    return mValue;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.slider.Slider#increment()
   */
  @Override
  public void increment() {
    // mPc = Mathematics.bound(mPc + 1, 0, mMarks.length - 1);

    // updateBinValue(mMarks[Mathematics.bound(mPc + 1, 0, mMarks.length - 1)]);
    setBinValue(mMarks[Mathematics.bound(mPc + 1, 0, mMarks.length - 1)]);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.slider.Slider#decrement()
   */
  @Override
  public void decrement() {
    // mPc = Mathematics.bound(mPc - 1, 0, mMarks.length - 1);

    // updateBinValue(mMarks[Mathematics.bound(mPc - 1, 0, mMarks.length - 1)]);
    setBinValue(mMarks[Mathematics.bound(mPc - 1, 0, mMarks.length - 1)]);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.text.TextProperty#getText()
   */
  @Override
  public String getText() {
    return mField.getText();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.text.TextProperty#setText(java.lang.String)
   */
  @Override
  public void setText(String text) {
    // System.err.println("text " + text + " " + getName());
    mField.setText(text);
  }

  /**
   * Stop timers.
   */
  private void stopTimers() {
    mIncrementTimer.stop();
    mDecrementTimer.stop();
  }
}
