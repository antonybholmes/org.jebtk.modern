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
package org.jebtk.modern.scrollpane;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import org.jebtk.modern.BorderService;

/**
 * Flat, minimal chrome implementation of a scroll pane control.
 *
 * @author Antony Holmes
 *
 */
public abstract class ModernVScrollBar extends ModernScrollBar {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The class MouseEvents.
   */
  private class MouseEvents extends MouseAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(MouseEvent e) {

      int tp = getInternalRect().getH() - getButtonSize();

      int t = getInsets().top;

      if (e.getY() >= t && e.getY() < t + getButtonSize()) {
        // setVScrollPosition(vScrollPosition - 1);

        mInc = -mScrollStepSize;

        mScrollTimer.start();
      } else if (e.getY() >= tp && e.getY() <= tp + getButtonSize()) {
        mInc = mScrollStepSize;

        mScrollTimer.start();
      } else {
        mScrollBarDrag = true;

        setNormalizedScrollPosition(translate(e.getY()));
      }

      mStartPosition.x = e.getX();
      mStartPosition.y = e.getY();

      mScrollStartPosition = mNormalizedScrollPosition;

      repaint();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseReleased(MouseEvent e) {
      if (e.getX() < 0 || e.getX() > getWidth()) {
        reset();
      }
    }
  }

  /**
   * The class MouseMotionEvents.
   */
  private class MouseMotionEvents implements MouseMotionListener {

    /** The m scroll bar. */
    private ModernScrollBar mScrollbar;

    /** The m C. */
    private Component mC;

    /**
     * Instantiates a new mouse wheel events.
     *
     * @param c         the c
     * @param scrollbar the scrollbar
     */
    public MouseMotionEvents(Component c, ModernScrollBar scrollbar) {
      mC = c;
      mScrollbar = scrollbar;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.
     * MouseEvent)
     */
    @Override
    public void mouseDragged(MouseEvent e) {
      if (mScrollBarDrag) {
        mScroller.drag(e.getY(), mStartPosition.y, mScrollStartPosition, mC, mScrollbar);
      }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseMoved(MouseEvent e) {
      // highlightUpButton = false;
      // highlightDownButton = false;
      // highlightVScrollButton = false || vScrollBarDrag;

      if (e.getX() >= getWidth() - getInsets().right - getButtonSize()) {

        double y = e.getY() - getInsets().top;

        mHighlightButton1 = y >= 0 && y < getButtonSize();

        y = e.getY() - getHeight() + getButtonSize() + getInsets().bottom;

        mHighlightButton2 = y >= 0 && y < getButtonSize();

        y = e.getY() - mScrollPosition - getButtonSize() - getInsets().top;

        mHighlightScroll = y >= 0 && y < mScrollBarSize;
      } else {
        mHighlightScroll = false || mScrollBarDrag;
        mHighlightButton1 = false;
        mHighlightButton2 = false;
      }

      if (mHighlightScroll || mScrollBarDrag || mHighlightButton1 || mHighlightButton2) {
        repaint();
      }
    }
  }

  private class KeyEvents extends KeyAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
     */
    @Override
    public void keyPressed(KeyEvent e) {
      keyScroll(e);
    }
  }

  /**
   * Instantiates a new modern v scroll bar office.
   */
  public ModernVScrollBar() {
    addMouseListener(new MouseEvents());
    addKeyListener(new KeyEvents());
    // setBorder(LEFT_BORDER);
  }

  private void keyScroll(KeyEvent e) {
    // Favor vertical scrolling over horizontal scrolling

    int k = e.getKeyCode();

    switch (k) {
    case KeyEvent.VK_UP:
      getScroller().keyScroll(true, mView, this);
      break;
    case KeyEvent.VK_DOWN:
      getScroller().keyScroll(false, mView, this);
      break;
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.scrollpane.ModernScrollBar#setCanvas(java.awt.Component)
   */
  @Override
  public void setView(Component c) {
    super.setView(c);

    addMouseMotionListener(new MouseMotionEvents(c, this));
  }

  @Override
  public double normalize(double d) {
    double ret = super.normalize(d);

    return ret;
  }

  @Override
  public void updateNormalizedScrollPosition(double p) {
    super.updateNormalizedScrollPosition(p);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.widget.ModernWidget#drawForegroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawBackgroundAA(Graphics2D g2) {
    int gap = mInternalRect.getH();

    boolean showScroll = gap >= 2 * getInternalFixedDimension() + mMinScrollBarSize;
    boolean showButtons = gap >= 2 * getInternalFixedDimension();

    if (!showScroll && !showButtons) {
      return;
    }

    int x = getInsets().left;
    int y = getInsets().top;

    if (showScroll) {

      paintScrollBarBase(g2);

      Rectangle r = new Rectangle(x, (int) (y + getButtonSize() + mScrollPosition), getInternalFixedDimension(),
          mScrollBarSize);

      // if (mVScrollBarDrag || mHighlightVScroll) {
      // paintHighlightedScrollBar(g2, r);
      // } else {
      // paintScrollBar(g2, r);
      // }

      paintScrollBar(g2, r);
    }

    if (showButtons) {
      //
      // Up button
      //

      paintUpButton(g2);

      //
      // Down button
      //

      paintDownButton(g2);
    }

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.scrollpane.ModernScrollBar#getFixedDimension()
   */
  @Override
  public int getFixedDim() {
    return getPreferredSize().width;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.scrollpane.ModernScrollBar#getVariableDim()
   */
  @Override
  public int getVariableDim() {
    return getHeight() - mPagePadding;
  }

  @Override
  protected void setPreferredSize() {
    setPreferredSize(new Dimension(mLinePadding + getInternalFixedDimension(), mPagePadding));
  }

  @Override
  public ModernScrollBar setPadding(int p1, int p2) {
    setBorder(BorderService.getInstance().createLeftRightBorder(p1, p2));

    return this;
  }
}
