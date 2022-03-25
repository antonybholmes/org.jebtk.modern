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
package org.jebtk.modern.text;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.Timer;
import javax.swing.border.Border;

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.event.ChangeEventProducer;
import org.jebtk.core.event.ChangeListener;
import org.jebtk.core.event.ChangeListeners;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.clipboard.ClipboardService;
import org.jebtk.modern.graphics.AAMode;
import org.jebtk.modern.graphics.DrawingContext;
import org.jebtk.modern.graphics.ImageUtils;
import org.jebtk.modern.graphics.ModernCanvas;
import org.jebtk.modern.theme.ThemeService;
import org.jebtk.modern.clipboard.IClipboard;

// TODO: Auto-generated Javadoc
/**
 * Implementation of a text box control. This is to circumvent problems with
 * JTextField including but not limited to it seems to use the native OS for
 * rendering and you cannot control the background color easily. In this
 * implementation is is transparent.
 * 
 * @author Antony Holmes
 *
 */
public class ModernTextBox extends ModernCanvas
    implements IClipboard, ITextProperty, KeyListener, MouseListener, MouseMotionListener, ChangeEventProducer {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant TEXT_BOX_SIZE.
   */
  public static final Dimension TEXT_BOX_SIZE = new Dimension(100, ModernWidget.WIDGET_HEIGHT);

  /**
   * The constant DISABLED_COLOR.
   */
  public static final Color DISABLED_COLOR = ModernWidget.ALT_TEXT_COLOR;

  /**
   * The constant ENABLED_COLOR.
   */
  public static final Color ENABLED_COLOR = ModernWidget.TEXT_COLOR;

  /** The Constant INC_TIMER_SPEED_MS. */
  private static final int INC_TIMER_SPEED_MS = 50;

  /** The Constant DEFAULT_BORDER. */
  private static final Border DEFAULT_BORDER = UI.createBorder(2); // (2,
  // PADDING,
  // 2,
  // PADDING);

  /** The Constant CARET_TIMER_SPEED_MS. */
  private static final int CARET_TIMER_SPEED_MS = 600;

  /**
   * The member buffer.
   */
  protected StringBuilder mBuffer = new StringBuilder();

  /**
   * The member selection color.
   */
  private Color mSelectionColor = ThemeService.getInstance().getColors().getGray(4);

  /** The m caret color. */
  private Color mCaretColor = ThemeService.getInstance().getColors().getGray(10);

  /**
   * The member caret.
   */
  protected int mStartCaret = 0;

  /** The m caret. */
  private int mCaret = 0;

  /**
   * The member drag caret.
   */
  protected int mEndCaret = -1;

  /** The m offset. */
  protected int mOffset = 0;

  /**
   * The member show caret.
   */
  private boolean mFlashCaret = false;

  /**
   * The member editable.
   */
  private boolean mEditable = true;

  /**
   * The member text y.
   */
  private int mTextY = -1;

  /**
   * The member selection height.
   */
  private int mSelectionHeight = -1;

  /**
   * The member caret y.
   */
  private int mCaretY;

  /** The m caret timer. */
  private Timer mCaretTimer;

  /** The m show caret. */
  private boolean mShowCaret = false;

  // private String[] mArray;

  /** The m offset array. */
  private int[] mOffsetArray;

  /** The m X array. */
  private int[] mXArray;

  /** The m view inc timer. */
  private Timer mViewIncTimer;

  /** The m view dec timer. */
  private Timer mViewDecTimer;

  /** The m alignment. */
  private ModernTextBoxAlignment mAlignment = ModernTextBoxAlignment.LEFT;

  /** The m min offset. */
  private int mMinOffset;

  /** The m change listeners. */
  private ChangeListeners mChangeListeners = new ChangeListeners();

  /**
   * Handle when textbox gets focus.
   * 
   * @author Antony Holmes
   *
   */
  private class FocusEvents implements FocusListener {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
     */
    @Override
    public void focusGained(FocusEvent e) {
      // Scrap any existing threads
      // mShowCaret = true;
      mCaretTimer.start();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
     */
    @Override
    public void focusLost(FocusEvent e) {
      mCaretTimer.stop();

      // mDragCaret = -1;
      // mShowCaret = false;
      mFlashCaret = false;

      fireCanvasRedraw();
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
      calcBounds();
    }
  }

  /*
   * private class CaretWorker extends SwingWorker<Void, Void> {
   * 
   * @Override protected Void doInBackground() throws InterruptedException { while
   * (hasFocus()) { if (mDragCaret == -1) { mShowCaret = !mShowCaret;
   * 
   * fireCanvasRedraw(); }
   * 
   * Thread.sleep(700); }
   * 
   * return null; } }
   */

  /**
   * The Class CaretTask.
   */
  private class CaretTask implements ActionListener {

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
      mFlashCaret = !mFlashCaret;

      fireCanvasRedraw();
    }
  }

  /**
   * The Class ViewIncTask.
   */
  private class ViewIncTask implements ActionListener {

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
      inc();
    }
  }

  /**
   * The Class ViewDecTask.
   */
  private class ViewDecTask implements ActionListener {

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
      dec();
    }
  }

  /**
   * Instantiates a new modern text box.
   */
  public ModernTextBox() {
    setup();
  }

  /**
   * Instantiates a new modern text box.
   *
   * @param text the text
   */
  public ModernTextBox(String text) {
    setText(text);

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {

    addKeyListener(this);
    addMouseListener(this);
    addMouseMotionListener(this);
    addFocusListener(new FocusEvents());
    addComponentListener(new ComponentEvents());

    setBorder(DEFAULT_BORDER);

    setMinimumSize(new Dimension(ModernWidget.WIDGET_HEIGHT, ModernWidget.WIDGET_HEIGHT));

    mCaretTimer = new Timer(0, new CaretTask());
    mCaretTimer.setDelay(CARET_TIMER_SPEED_MS);

    mViewIncTimer = new Timer(0, new ViewIncTask());
    mViewIncTimer.setDelay(INC_TIMER_SPEED_MS);

    mViewDecTimer = new Timer(0, new ViewDecTask());
    mViewDecTimer.setDelay(INC_TIMER_SPEED_MS);

    getAAModes().add(AAMode.TEXT);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#setFont(java.awt.Font)
   */
  @Override
  public void setFont(Font font) {
    super.setFont(font);

    calcBounds();

    updateText();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void zoomCanvas(Graphics2D g2, DrawingContext context) {
    Graphics2D g2Temp = ImageUtils.clone(g2);

    try {
      g2Temp.translate(getInsets().left, getInsets().top);
      g2Temp.clipRect(-1, -1, getInternalRect().getW(), getInternalRect().getH());
      g2Temp.translate(mMinOffset - mOffset, 0);

      drawTextBox(g2Temp, context);
    } finally {
      g2Temp.dispose();
    }
  }

  /**
   * Draw text box.
   *
   * @param g2      the g 2
   * @param context the context
   */
  public void drawTextBox(Graphics2D g2, DrawingContext context) {

    if (mEndCaret != -1) {
      int s = getCaretX(g2, Math.min(mStartCaret, mEndCaret));
      int e = getCaretX(g2, Math.max(mStartCaret, mEndCaret));

      g2.setColor(mSelectionColor);

      g2.fillRect(s, 0, (e - s), mSelectionHeight);
    }

    g2.setColor(ModernWidget.TEXT_COLOR);

    g2.drawString(mBuffer.toString(), 0, mTextY);

    if (mShowCaret || mFlashCaret) {
      int s = getCaretX(g2, mCaret);

      // System.err.println("S " + s + " " +
      // ImageUtils.getStringWidth(getFont(),mBuffer.toString()) + " " +
      // ImageUtils.getStringWidth(g2, mBuffer.toString()));

      g2.setColor(mCaretColor);
      g2.drawLine(s, 0, s, mCaretY);
    }
  }

  /**
   * Sets the selection color.
   *
   * @param color the new selection color
   */
  public void setSelectionColor(Color color) {
    mSelectionColor = color;
  }

  /**
   * Sets the caret color.
   *
   * @param color the new caret color
   */
  public void setCaretColor(Color color) {
    mCaretColor = color;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.Component#toString()
   */
  @Override
  public String toString() {
    return getText();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.text.TextProperty#getText()
   */
  @Override
  public String getText() {
    return mBuffer.toString();
  }

  /**
   * Sets the text.
   *
   * @param v the new text
   */
  public void setText(double v) {
    setText(Double.toString(v));
  }

  /**
   * Sets the text.
   *
   * @param v the new text
   */
  public void setText(int v) {
    setText(Integer.toString(v));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.text.TextProperty#setText(java.lang.String)
   */
  @Override
  public void setText(String text) {
    mBuffer = new StringBuilder(text);

    updateText();

    updateCaret(text.length());

    fireCanvasRedraw();
  }

  /**
   * Append.
   *
   * @param text the text
   */
  public void append(String text) {
    int sc = Math.min(mStartCaret, mEndCaret);
    int ec = Math.max(mStartCaret, mEndCaret);

    if (ec >= mBuffer.length()) {
      mBuffer.append(text);
    } else if (sc == ec) {
      mBuffer.insert(sc, text);
    } else if (sc != ec) {
      mBuffer.replace(sc, ec, text);
    } else {

    }

    // if (sc >= mBuffer.length()) {
    // mBuffer.append(e.getKeyChar());

    // mBuffer = new StringBuilder(text);

    updateText();

    updateCaret(sc + text.length());

    fireCanvasRedraw();
  }

  /**
   * Update text.
   */
  private void updateText() {
    if (mBuffer == null || mBuffer.length() == 0) {
      return;
    }

    String s = mBuffer.toString();

    int n = s.length();

    mXArray = new int[n];
    mXArray[0] = 0;

    Font f = getFont();

    mOffsetArray = new int[n];
    mOffsetArray[0] = 0;

    for (int i = 1; i < n; ++i) {
      // System.err.println("tee hee " + s.substring(0, i) + " " + f.getSize() +
      // " " +
      // ImageUtils.getStringWidth(f, s.substring(0, i)));
      mOffsetArray[i] = ImageUtils.getStringWidth(f, s.substring(0, i));
    }

    for (int i = 1; i < n; ++i) {
      mXArray[i] = mOffsetArray[i] - mOffsetArray[i - 1];
    }

    updateTextOffsets();
  }

  /**
   * Update caret.
   *
   * @param caret the caret
   */
  private void updateCaret(int caret) {
    updateCaret(caret, caret, caret);
  }

  /**
   * Update end caret.
   *
   * @param caret the caret
   */
  private void updateEndCaret(int caret) {
    updateCaret(mStartCaret, caret, caret);
  }

  /**
   * Update caret.
   *
   * @param start the start
   * @param end   the end
   * @param caret the caret
   */
  private void updateCaret(int start, int end, int caret) {
    mStartCaret = Math.min(start, mBuffer.length());
    mEndCaret = Math.min(end, mBuffer.length());
    mCaret = Math.min(caret, mBuffer.length());
  }

  /**
   * Sets the editable.
   *
   * @param editable the new editable
   */
  public void setEditable(boolean editable) {
    mEditable = editable;
  }

  /**
   * Checks if is editable.
   *
   * @return true, if is editable
   */
  public boolean isEditable() {
    return mEditable;
  }

  /**
   * Determine which character the caret lies between.
   *
   * @param x the x
   * @return the caret from x
   */
  private int getCaretFromX(int x) {
    if (mBuffer == null || mBuffer.length() == 0) {
      return 0;
    }

    int px = x - getInsets().left + mOffset - mMinOffset;

    int n = mOffsetArray.length;

    if (px <= mOffsetArray[0]) {
      return 0;
    } else {
      for (int i = 0; i < n - 1; ++i) {
        int p1 = mOffsetArray[i];
        int p2 = mOffsetArray[i + 1];

        if (px >= p1 && px < p2) {
          double l = p2 - p1;

          return i + (int) Math.round((px - p1) / l);
        }
      }

      return n;
    }

    /*
     * 
     * Font f = getFont();
     * 
     * int p1 = 0; int p2 = ImageUtils.getStringWidth(f, mArray[0]);
     * 
     * int px = x - getInsets().left - PADDING;
     * 
     * if (px <= 0) { return 0; }
     * 
     * for (int i = 0; i < mBuffer.length() - 1; ++i) { if (px >= p1 && px < p2) {
     * double l = p2 - p1;
     * 
     * return i + (int)Math.round((px - p1) / l); }
     * 
     * p1 = p2; p2 += ImageUtils.getStringWidth(f, mArray[i + 1]); }
     * 
     * return mBuffer.length();
     */
  }

  /**
   * Returns the x for a caret.
   *
   * @param c the c
   * @return the caret x
   */
  private int getCaretX(int c) {
    int x = 0; // getInsets().left; // + mOffset;

    if (mBuffer == null || mBuffer.length() == 0) {
      return x;
    }

    int n = mBuffer.length();

    // System.err.println(c + " " + x + " " + Arrays.toString(mXArray) + " " +
    // Arrays.toString(mOffsetArray));

    if (n == 0 || c <= 0) {
      return x;
    } else if (c >= n) {
      return x + mOffsetArray[n - 1] + mXArray[n - 1];
    } else {
      return x + mOffsetArray[c];
    }

    /*
     * if (c == 0) { return PADDING; }
     * 
     * Font f = getFont();
     * 
     * if (c >= mBuffer.length()) { return PADDING + ImageUtils.getStringWidth(f,
     * mBuffer.toString()); }
     * 
     * int p = 0;
     * 
     * for (int i = 0; i < c; ++i) { p += ImageUtils.getStringWidth(f, mArray[i]); }
     * 
     * return p + PADDING;
     */
  }

  /**
   * Gets the caret X.
   *
   * @param g2 the g 2
   * @param c  the c
   * @return the caret X
   */
  private int getCaretX(Graphics2D g2, int c) {
    int x = 0; // getInsets().left; // + mOffset;

    if (mBuffer == null || mBuffer.length() == 0) {
      return x;
    }

    int n = mBuffer.length();

    // System.err.println(c + " " + x + " " + Arrays.toString(mXArray) + " " +
    // Arrays.toString(mOffsetArray));

    if (n == 0 || c <= 0) {
      return 0;
    } else if (c >= n) {
      return g2.getFontMetrics().stringWidth(mBuffer.toString());
    } else {
      return g2.getFontMetrics().stringWidth(mBuffer.toString().substring(0, c));
    }

    /*
     * if (c == 0) { return PADDING; }
     * 
     * Font f = getFont();
     * 
     * if (c >= mBuffer.length()) { return PADDING + ImageUtils.getStringWidth(f,
     * mBuffer.toString()); }
     * 
     * int p = 0;
     * 
     * for (int i = 0; i < c; ++i) { p += ImageUtils.getStringWidth(f, mArray[i]); }
     * 
     * return p + PADDING;
     */
  }

  /**
   * Select all.
   */
  public void selectAll() {
    mStartCaret = 0;
    mEndCaret = mBuffer.length();
    mCaret = mEndCaret;

    fireCanvasRedraw();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.clipboard.Clipboard#copy()
   */
  @Override
  public void copy() {
    if (mEndCaret == -1) {
      return;
    }

    int sc = Math.min(mStartCaret, mEndCaret);
    int ec = Math.max(mStartCaret, mEndCaret);

    ClipboardService.copyToClipboard(mBuffer.substring(sc, ec));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.clipboard.Clipboard#paste()
   */
  @Override
  public void paste() {
    append(ClipboardService.getClipboardContents());
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.clipboard.Clipboard#cut()
   */
  @Override
  public void cut() {
    if (mEndCaret == -1) {
      return;
    }

    int sc = Math.min(mStartCaret, mEndCaret);
    int ec = Math.max(mStartCaret, mEndCaret);

    mBuffer.delete(sc, ec);

    mEndCaret = -1;

    fireCanvasRedraw();
  }

  /**
   * Calc bounds.
   */
  private void calcBounds() {
    if (mBuffer == null) {
      return;
    }

    Font f = getFont();

    mCaretY = getInternalRect().getH();
    mTextY = ImageUtils.getTextYPosCenter(f, mCaretY); // (getHeight() +
    // mG2.getFontMetrics().getAscent()
    // -
    // mG2.getFontMetrics().getDescent())
    // / 2;

    mSelectionHeight = mCaretY;

    updateTextOffsets();

    // mOffset = mMinOffset;
  }

  /**
   * Update text offsets.
   */
  private void updateTextOffsets() {
    Font f = getFont();

    if (mAlignment == ModernTextBoxAlignment.LEFT) {
      mMinOffset = 0;
    } else {
      mMinOffset = getInternalRect().getW() - ImageUtils.getStringWidth(f, mBuffer.toString()) - getInsets().left
          - PADDING;
    }
  }

  /**
   * Inc.
   */
  private void inc() {
    int x = getCaretX(mCaret); // mBuffer.length());

    if (x > mOffset + mInternalRect.getW()) {
      mOffset += mXArray[mCaret];
    }

    fireCanvasRedraw();
  }

  /**
   * Dec.
   */
  private void dec() {
    int x = getCaretX(mCaret);

    if (mAlignment == ModernTextBoxAlignment.LEFT) {
      if (x < mOffset) {
        mOffset = Math.max(0, mOffset - mXArray[mCaret]);
      }
    } else {
      // if (x < mOffset) {
      mOffset = Math.max(mMinOffset, mOffset - mXArray[mCaret]);
      // }
    }

    fireCanvasRedraw();
  }

  /**
   * Flash.
   *
   * @param flash the flash
   */
  public void flash(boolean flash) {
    if (flash) {
      mCaretTimer.start();
    } else {
      mCaretTimer.stop();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.graphics.ModernCanvas#canvasKeyTyped(java.awt.event.
   * KeyEvent)
   */
  @Override
  public void canvasKeyTyped(KeyEvent e) {
    super.canvasKeyTyped(e);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.graphics.ModernCanvas#canvasKeyPressed(java.awt.event.
   * KeyEvent)
   */
  @Override
  public void canvasKeyPressed(KeyEvent e) {
    keyPressed(e);

    super.canvasKeyPressed(e);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.graphics.ModernCanvas#canvasKeyReleased(java.awt.event.
   * KeyEvent)
   */
  @Override
  public void canvasKeyReleased(KeyEvent e) {
    keyReleased(e);

    super.canvasKeyReleased(e);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
   */
  @Override
  public void keyPressed(KeyEvent e) {
    mShowCaret = true;

    mCaretTimer.stop();

    // System.err.println("key " + e.getKeyCode() + " " +
    // KeyEvent.VK_BACK_SPACE);

    int sc;
    int ec;

    if (e.isControlDown() || e.isMetaDown()) {
      switch (e.getKeyCode()) {
      case KeyEvent.VK_A:
        selectAll();
        break;
      case KeyEvent.VK_C:
        copy();
        break;
      case KeyEvent.VK_V:
        paste();
        break;
      case KeyEvent.VK_X:
        cut();
        break;
      }
    } else {
      switch (e.getKeyCode()) {
      case KeyEvent.VK_BACK_SPACE:
      case KeyEvent.VK_DELETE:
        // if (mCaret > 0) {
        sc = Math.min(mStartCaret, mCaret);
        ec = Math.max(mStartCaret, mCaret);

        System.err.println("caret " + mCaret + " " + sc + " " + ec);

        if (ec > 0) {
          if (sc != ec) {
            mBuffer.delete(sc, ec);
            updateCaret(Math.max(0, sc));
          } else {
            // For single char deletion, delete the char
            // before
            --ec;
            mBuffer.deleteCharAt(ec);
            updateCaret(Math.max(0, ec));
          }

          updateText();

          dec();
          // }

          fireChanged();
        }

        break;
      case KeyEvent.VK_LEFT:
        if (e.isShiftDown()) {
          updateEndCaret(Math.max(0, mEndCaret - 1));
        } else {
          updateCaret(Math.max(0, mCaret - 1));
        }

        dec();

        break;
      case KeyEvent.VK_RIGHT:
        if (e.isShiftDown()) {
          updateEndCaret(Math.min(mBuffer.length(), mCaret + 1));
        } else {
          updateCaret(Math.min(mBuffer.length(), mCaret + 1));
        }

        inc();

        break;
      case KeyEvent.VK_ENTER:
        fireChanged();
        break;
      case KeyEvent.VK_UP:
      case KeyEvent.VK_DOWN:
      case KeyEvent.VK_SHIFT:
      case KeyEvent.VK_CAPS_LOCK:
      case KeyEvent.VK_TAB:
      case KeyEvent.VK_META:
      case KeyEvent.VK_PAGE_UP:
      case KeyEvent.VK_PAGE_DOWN:
      case KeyEvent.VK_HOME:
      case KeyEvent.VK_INSERT:
      case KeyEvent.VK_PAUSE:
      case KeyEvent.VK_PRINTSCREEN:
      case KeyEvent.VK_SCROLL_LOCK:
      case KeyEvent.VK_NUM_LOCK:
      case KeyEvent.VK_ALT:
      case KeyEvent.VK_ALT_GRAPH:
      case KeyEvent.VK_ESCAPE:
        break;
      default:
        insertChar(e);

        break;
      }
    }

    fireCanvasRedraw();
  }

  /**
   * Insert char.
   *
   * @param e the e
   */
  private void insertChar(KeyEvent e) {
    // Process other keys as chars
    int sc = Math.min(mStartCaret, mCaret);
    int ec = Math.max(mStartCaret, mCaret);

    // System.err.println("caret " + mCaret + " " + mStartCaret + " " +
    // mEndCaret);

    if (ec > 0) {
      mBuffer.delete(sc, ec);
    }

    if (sc >= mBuffer.length()) {
      mBuffer.append(e.getKeyChar());
    } else {
      mBuffer.insert(sc, e.getKeyChar());
    }

    updateText();

    if (mCaret > mBuffer.length()) {
      mOffset = 0;
    } else {

      inc();
    }

    updateCaret(sc + 1);

    fireChanged();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
   */
  @Override
  public void keyReleased(KeyEvent e) {
    mShowCaret = false;

    mCaretTimer.start();

    fireCanvasRedraw();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
   */
  @Override
  public void keyTyped(KeyEvent e) {

  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.event.ChangeEventProducer#addChangeListener(org.abh.common.
   * event.ChangeListener)
   */
  @Override
  public void addChangeListener(ChangeListener l) {
    mChangeListeners.addChangeListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.event.ChangeEventProducer#removeChangeListener(org.abh.
   * common. event.ChangeListener)
   */
  @Override
  public void removeChangeListener(ChangeListener l) {
    mChangeListeners.removeChangeListener(l);
  }

  /**
   * Fire changed.
   */
  public void fireChanged() {
    fireChanged(new ChangeEvent(this));
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.event.ChangeEventProducer#fireChanged(org.abh.common.event.
   * ChangeEvent)
   */
  @Override
  public void fireChanged(ChangeEvent e) {
    mChangeListeners.fireChanged(e);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseClicked(MouseEvent e) {
    if (e.isPopupTrigger()) {
      return;
    }

    if (e.getClickCount() == 2) {
      selectAll();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
   */
  @Override
  public void mousePressed(MouseEvent e) {
    if (e.isPopupTrigger()) {
      return;
    }

    updateCaret(getCaretFromX(e.getX()));

    mShowCaret = true;

    fireCanvasRedraw();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseReleased(MouseEvent e) {
    if (e.isPopupTrigger()) {
      return;
    }

    mShowCaret = false;

    mViewIncTimer.stop();
    mViewDecTimer.stop();

    if (mEndCaret != -1 && mEndCaret != mStartCaret) {
      mCaret = mEndCaret;
    }

    fireCanvasRedraw();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseAdapter#mouseEntered(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseEntered(MouseEvent e) {
    setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseAdapter#mouseExited(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseExited(MouseEvent e) {
    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseDragged(MouseEvent e) {
    if (e.isPopupTrigger()) {
      return;
    }

    mEndCaret = getCaretFromX(e.getX());
    mCaret = mEndCaret;

    if (e.getX() > getWidth()) {
      mViewIncTimer.start();
    } else if (e.getX() < 0) {
      mViewDecTimer.start();
    }

    fireCanvasRedraw();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseMoved(MouseEvent e) {
    // TODO Auto-generated method stub

  }
}
