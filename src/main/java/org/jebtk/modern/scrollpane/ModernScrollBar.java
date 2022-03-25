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

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.Timer;
import javax.swing.border.Border;

import org.jebtk.core.ColorUtils;
import org.jebtk.core.Mathematics;
import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.event.ChangeEventProducer;
import org.jebtk.core.event.ChangeListener;
import org.jebtk.core.event.ChangeListeners;
import org.jebtk.core.geom.IntRect;
import org.jebtk.core.settings.SettingsService;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.MouseUtils;
import org.jebtk.modern.theme.ThemeService;

/**
 * Flat, minimal chrome implementation of a scroll pane control.
 *
 * @author Antony Holmes
 *
 */
public abstract class ModernScrollBar extends ModernWidget implements ChangeEventProducer {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant SCROLLBAR_BUTTON_BASE_COLOR.
   */
  protected static final Color SCROLLBAR_BUTTON_BASE_COLOR = Color.WHITE;

  /**
   * The constant BASE_COLOR.
   */
  protected static final Color BASE_COLOR = ThemeService.getInstance().getColors().getGray(3);

  /**
   * The constant SCROLLBAR_COLOR.
   */
  protected static final Color SCROLLBAR_COLOR = ThemeService.getInstance().getColors().getGray(4);

  /**
   * The constant SCROLLBAR_OUTLINE_COLOR.
   */
  protected static final Color SCROLLBAR_OUTLINE_COLOR = ThemeService.getInstance().getColors().getGray(5);

  /**
   * The constant SCROLLBAR_HIGHLIGHT_OUTLINE_COLOR.
   */
  protected static final Color SCROLLBAR_HIGHLIGHT_OUTLINE_COLOR = ThemeService.getInstance().getColors().getGray32(16);

  /** The Constant BASE_ROUNDED_COLOR. */
  protected static final Color BASE_ROUNDED_COLOR = Color.BLACK; // ThemeService.getInstance().getColors().getHighlight32(31);

  /** The Constant ROUNDED_SCROLLBAR_COLOR. */
  protected static final Color ROUNDED_SCROLLBAR_COLOR = ColorUtils.getTransparentColor60(BASE_ROUNDED_COLOR); // ThemeService.getInstance().getColors().getHighlight32(31));

  /** The Constant ROUNDED_SCROLLBAR_BACKGROUND_COLOR. */
  protected static final Color ROUNDED_SCROLLBAR_BACKGROUND_COLOR = ColorUtils
      .getTransparentColor90(BASE_ROUNDED_COLOR);

  /**
   * The constant SCROLLBAR_HIGHLIGHT_COLOR.
   */
  protected static final Color SCROLLBAR_HIGHLIGHT_COLOR = ThemeService.getInstance().getColors().getGray(2);

  /**
   * The constant SCROLLBAR_WIDTH.
   */
  protected static final int SCROLLBAR_SIZE = SettingsService.getInstance().getInt("theme.scrollpane.scrollbar.width"); // 15

  /**
   * The constant SCROLL_TIMER_DELAY_MS.
   */
  public static final int SCROLL_TIMER_DELAY_MS = SettingsService.getInstance()
      .getInt("theme.scrollpane.scrollbar.timer-delay-ms");

  /** The Constant MINOR_STEP_SCALE_FACTOR. */
  // private static final double MINOR_STEP_SCALE_FACTOR =
  // SettingsService.getInstance().getDouble("theme.scrollpane.scrollbar.minor-step-size-scale");

  /**
   * The member min scroll bar size.
   */
  protected int mMinScrollBarSize = 8;

  /**
   * The size of the scroll bar knob.
   */
  protected int mScrollBarSize = 0;

  /**
   * The member start position.
   */
  protected Point mStartPosition = new Point();

  /**
   * Whether the vertical scrollbar is being dragged or not.
   */
  protected boolean mScrollBarDrag = false;

  /**
   * The position of the scrollbar.
   */
  protected double mScrollPosition = 0;

  /**
   * The number of pixels we can move the scroll bar.
   */
  protected int mScrollPixels = 0;

  /**
   * The member normalized scroll position.
   */
  protected double mNormalizedScrollPosition = 0;

  /**
   * For when users hold down on a scroll button, the scroll will auto scroll.
   */
  protected Timer mScrollTimer = null;

  /**
   * The member highlight scroll.
   */
  protected boolean mHighlightScroll = false;

  /**
   * The member highlight button1.
   */
  protected boolean mHighlightButton1 = false;

  /**
   * The member highlight button2.
   */
  protected boolean mHighlightButton2 = false;

  /**
   * The member inc.
   */
  protected double mInc = 0;

  /**
   * The number of pixels the scroll bar needs to cope with, that is the
   * difference between the dimension of the component and the same dimension of
   * the scroll pane.
   */
  protected int mScrollDistance = 0;

  /**
   * The member scroll start position.
   */
  protected double mScrollStartPosition;

  /**
   * The member listeners.
   */
  private ChangeListeners mListeners = new ChangeListeners();

  /** Used for wheel scrolling and defines. */
  // private double mMinorScrollStepSize = 0;

  /** The m scroller. */
  protected Scroller mScroller = new Scroller();

  // protected Scroller mKeyScroller = mWheelScroller;

  /** The m canvas. */
  protected Component mView;

  /**
   * The relative position of the view window between 0 and 1.
   */
  private double mOffset;

  /** The m iternal fixed dim. */
  protected int mIternalFixedDim = SCROLLBAR_SIZE;

  /** The m button size. */
  private int mButtonSize = SCROLLBAR_SIZE;

  /** The m location. */
  protected ScrollBarLocation mLocation;

  /** The Constant MIN_STEP_SIZE. */
  private static final double MIN_STEP_SIZE = 0.1; // SettingsService.getInstance().getDouble("theme.scrollpane.scrollbar.min-step-size");

  /**
   * The normalized amount the component must shift for each pixel the scroll bar
   * moves.
   */
  protected double mScrollStepSize = MIN_STEP_SIZE;

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
      reset();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseReleased(MouseEvent e) {
      mScrollBarDrag = false;
      // mHighlightScroll = false;

      reset();
    }
  }

  /**
   * The class MouseWheelEvents.
   */
  private class MouseWheelEvents implements MouseWheelListener {

    /** The m scroll bar. */
    private ModernScrollBar mScrollBar;

    /**
     * Instantiates a new mouse wheel events.
     *
     * @param scrollBar the scroll bar
     */
    public MouseWheelEvents(ModernScrollBar scrollBar) {
      mScrollBar = scrollBar;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseWheelListener#mouseWheelMoved(java.awt.event.
     * MouseWheelEvent)
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
      // Don't scroll if keyboard modifiers are pressed so that alterntive
      // actions can be implemented without the scrolling interfering.
      if (!MouseUtils.hasModifiers(e)) {
        mScroller.wheelScroll(e.getWheelRotation(), mView, mScrollBar);
      }
    }
  }

  /**
   * Increments vertical scroll, should only be used by a timer.
   *
   * @author Antony Holmes
   *
   */
  private class IncrementScroll implements ActionListener {

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      incNormalizedScrollPosition(mInc);
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
      scrollBarSetup();
    }
  }

  /**
   * Instantiates a new modern scroll bar.
   */
  public ModernScrollBar() {
    setLayout(null);

    mScrollTimer = new Timer(0, new IncrementScroll());
    mScrollTimer.setDelay(SCROLL_TIMER_DELAY_MS);

    // component.addPropertyChangeListener(this);
    // component.addAncestorListener(this);
    // component.addHierarchyListener(this);

    addMouseListener(new MouseEvents());
    addMouseWheelListener(new MouseWheelEvents(this));
    addComponentListener(new ComponentEvents());
  }

  /**
   * Scroll bar setup.
   */
  public abstract void scrollBarSetup();

  /**
   * Scroll bar setup.
   *
   * @param scrollWidth The number of pixels that can be scrolled, i.e between the
   *                    two scroll buttons.
   */
  protected void scrollBarSetup(int scrollWidth) {
    // int vDiff = mVScrollDistance;

    // if (mShowVScrollBar) {
    // vDiff += SCROLLBAR_WIDTH;
    // }

    // += mTopHeaderOffset;

    // vDiff += mFooterOffset;

    // The size of the scroll bar is the difference between the space
    // available and the scrollable distance
    mScrollBarSize = Math.max(scrollWidth - mScrollDistance, mMinScrollBarSize);

    // The number of pixels we can move the scroll bar
    mScrollPixels = Math.max(scrollWidth - mScrollBarSize, 0);

    // how many pixels the content has to move for each pixel the
    // scroll bar uses. Since the scrollbar occupies some space, the number
    // we need the ratio between the number of scrollable pixels and
    // the number of pixels we need to move so that we know for each pixel
    // we shift the scrollbar, how much the component must be shifted.

    if (mScrollPixels > 0) {
      mScrollStepSize = Math.max((double) mScrollDistance / mScrollPixels, MIN_STEP_SIZE);
    } else {
      mScrollStepSize = 0;
    }

    // System.err.println("scrollbar " + scrollWidth + " " + mScrollBarSize + "
    // " +
    // mScrollDistance + " " + mScrollBarDistance + " " + mScrollStepSize);

    updateNormalizedScrollPosition();

    repaint();
  }

  /**
   * Should paint the scroll bar base underneath the buttons and knob.
   *
   * @param g2 the g 2
   */
  protected void paintScrollBarBase(Graphics2D g2) {
    // TODO Auto-generated method stub
  }

  /**
   * Should paint the scroll bar or knob.
   *
   * @param g2 the g 2
   * @param r  the r
   */
  protected void paintScrollBar(Graphics2D g2, Rectangle r) {
    // TODO Auto-generated method stub

  }

  /**
   * Should paing the up/left button.
   *
   * @param g2 the g 2
   */
  protected void paintUpButton(Graphics2D g2) {
    // TODO Auto-generated method stub

  }

  /**
   * Should paint the down/right button.
   *
   * @param g2 the g 2
   */
  protected void paintDownButton(Graphics2D g2) {
    // TODO Auto-generated method stub

  }

  //
  // How scrollbars should appear
  //

  /**
   * Paint scroll bar base.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  protected void paintScrollBarBase(Graphics2D g2, Rectangle rect) {
    fill(g2, BASE_COLOR, rect);
  }

  /**
   * Paint scroll bar base.
   *
   * @param g2   the g 2
   * @param rect the rect
   */
  protected void paintScrollBarBase(Graphics2D g2, IntRect rect) {
    fill(g2, BASE_COLOR, rect);
  }

  /**
   * Set the scroll position in pixels. Equivalent to
   * <code>setNormalizedScrollPosition(normalize(p))</code>.
   * 
   * @param p the position in pixels
   */
  public void setScrollPosition(int p) {
    setNormalizedScrollPosition(normalize(p));
  }

  /**
   * Move the scrollbar n pixels. This will cause the content to move at least n
   * pixels.
   *
   * @param p the new normalized scroll position
   */
  public void setNormalizedScrollPosition(double p) {
    updateNormalizedScrollPosition(p);

    fireChanged();
  }

  /**
   * Update normalized scroll position.
   *
   * @param p the p
   */
  public void updateNormalizedScrollPosition(double p) {
    // Only update if there is a change
    if (p != mNormalizedScrollPosition) {
      mNormalizedScrollPosition = Mathematics.bound(p, 0, 1);

      updateNormalizedScrollPosition();
    }
  }

  /**
   * Update normalized scroll position.
   */
  private void updateNormalizedScrollPosition() {
    mScrollPosition = mScrollPixels * mNormalizedScrollPosition;
    mOffset = mScrollDistance * mNormalizedScrollPosition;

    repaint();
  }

  /**
   * Gets the normalized scroll position.
   *
   * @return the normalized scroll position
   */
  public double getNormalizedScrollPosition() {
    return mNormalizedScrollPosition;
  }

  /**
   * Increment normalized scroll position.
   *
   * @param pos the pos
   */
  public void incNormalizedScrollPosition(double pos) {
    setNormalizedScrollPosition(mNormalizedScrollPosition + pos);
  }

  /**
   * Translate a coordinate to the scrollbar distance space.
   *
   * @param x the x
   * @return the double
   */
  public double translate(int x) {
    return (double) (x - getButtonSize() - mScrollBarSize / 2) / mScrollPixels;
  }

  /**
   * Set the minimum size in pixels for the scroll bar.
   *
   * @param size the new minimum scroll bar size
   */
  public void setMinimumScrollBarSize(int size) {
    mMinScrollBarSize = size;
  }

  /**
   * Set the number of pixels the component can move (i.e. the difference between
   * the view window and the actual component's size.
   *
   * @param d the new scroll distance
   */
  public void setScrollDistance(int d) {
    mScrollDistance = d;

    scrollBarSetup();
  }

  /**
   * Gets the scroll step size.
   *
   * @return the scroll step size
   */
  public double getScrollStepSize() {
    return mScrollStepSize;
  }

  /**
   * Returns the number of scrollable pixels the scroll bar can move, i.e the
   * difference between the scrollbar size and the component size.
   * 
   * @return The scrollable pixel count.
   */
  public double getScrollPixels() {
    return mScrollPixels;
  }

  /**
   * Sets the internal fixed dimension.
   *
   * @param size the new internal fixed dimension
   */
  public void setInternalFixedDimension(int size) {
    mIternalFixedDim = size;

    setPreferredSize();
  }

  public void setBorder(Border border) {
    super.setBorder(border);

    setPreferredSize();
  }

  protected abstract void setPreferredSize();

  /**
   * Get the fixed size dimension of the scrollbar exclusive of margins.
   *
   * @return the int fixed dimension
   */
  public int getInternalFixedDimension() {
    return mIternalFixedDim;
  }

  /**
   * Sets the button size.
   *
   * @param size the new button size
   */
  public void setButtonSize(int size) {
    mButtonSize = size;
  }

  /**
   * Get the fixed size dimension of the scrollbar exclusive of margins.
   *
   * @return the int fixed dimension
   */
  public int getButtonSize() {
    return mButtonSize;
  }

  /**
   * Returns the fixed size dimension of the scroll bar including margins.
   *
   * @return the fixed dimension
   */
  public abstract int getFixedDim();

  /**
   * Gets the variable dimension, e.g. for a vertical scroll bar that is its
   * height and for a horizontal scroll bar, that is its width.
   *
   * @return the variable dim
   */
  public abstract int getVariableDim();

  /**
   * Normalize a pixel position to the scroll distance.
   *
   * @param d the d
   * @return the double
   */
  public double normalize(double d) {
    // Convert d
    if (mScrollDistance > 0) {
      return Mathematics.bound(d / mScrollDistance, -1, 1);
    } else {
      return 0;
    }
  }

  /**
   * Normalizes a distance taking into account the sign of notches.
   *
   * @param d       the d
   * @param notches the notches
   * @return A normalized representation of d.
   */
  public double normalize(double d, int notches) {
    return normalize(d) * Math.signum(notches);
  }

  /**
   * Scale.
   *
   * @param d the d
   * @return the double
   */
  public double scale(double d) {
    // Convert d
    if (mScrollDistance > 0) {
      return Mathematics.bound((d - getVariableDim()) / mScrollDistance, 0, 1);
    } else {
      return 0;
    }
  }

  /**
   * Returns the difference between the component size and the viewport size.
   *
   * @return the scroll distance
   */
  public int getScrollDistance() {
    return mScrollDistance;
  }

  /**
   * Returns the pixel offset of the scroll bar for setting the view rectangle of
   * a component.
   *
   * @return the offset
   */
  public double getOffset() {
    return mOffset;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.event.ChangeEventProducer#addChangeListener(org.abh.lib.event.
   * ChangeListener)
   */
  @Override
  public void addChangeListener(ChangeListener l) {
    mListeners.addChangeListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.event.ChangeEventProducer#removeChangeListener(org.abh.lib.
   * event. ChangeListener)
   */
  @Override
  public void removeChangeListener(ChangeListener l) {
    mListeners.removeChangeListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.event.ChangeEventProducer#fireChanged(org.abh.lib.event.
   * ChangeEvent)
   */
  @Override
  public void fireChanged(ChangeEvent e) {
    mListeners.fireChanged(e);
  }

  /**
   * Fire changed.
   */
  public void fireChanged() {
    fireChanged(new ChangeEvent(this));
  }

  /**
   * Sets the canvas.
   *
   * @param c the new canvas
   */
  public void setView(Component c) {
    mView = c;
  }

  /**
   * Sets the scroller which governs how much the scrollbar moves.
   *
   * @param scroller the new scroller
   */
  public void setScroller(Scroller scroller) {
    mScroller = scroller;
  }

  /**
   * Gets the scroller.
   *
   * @return the scroller
   */
  // public Scroller getKeyScroller() {
  // return mKeyScroller;
  // }

  // public void setKeyScroller(Scroller scroller) {
  // mKeyScroller = scroller;
  // }

  /**
   * Gets the scroller.
   *
   * @return the scroller
   */
  public Scroller getScroller() {
    return mScroller;
  }

  /**
   * Reset the scrollbar back to its default, unhighlighted state.
   */
  public void reset() {
    mHighlightScroll = false || mScrollBarDrag;
    mHighlightButton1 = false;
    mHighlightButton2 = false;

    mScrollTimer.stop();
    mInc = 0;

    repaint();
  }

  /**
   * Sets the location.
   *
   * @param position the new location
   */
  public void setLocation(ScrollBarLocation position) {
    mLocation = position;
  }

  /**
   * Set the padding on the fixed dimension.
   * 
   * @param p1
   */
  public ModernScrollBar setPadding(int p1) {
    return setPadding(p1, p1);
  }

  /**
   * Set the padding on the fixed scroll bar dimension.
   * 
   * @param p1
   * @param p2
   * @return
   */
  public abstract ModernScrollBar setPadding(int p1, int p2);

  //
  // Static methods
  //

  /**
   * Paint scroll bar highlighted.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  protected static void paintScrollBarHighlighted(Graphics2D g2, Rectangle rect) {
    fill(g2, SCROLLBAR_HIGHLIGHT_COLOR, rect);
    drawRect(g2, SCROLLBAR_HIGHLIGHT_OUTLINE_COLOR, rect);
  }

  /**
   * Paint scroll bar button.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  protected static void paintScrollBarButton(Graphics2D g2, Rectangle rect) {
    paintScrollBarButtonBase(g2, rect);
    paintScrollBarButtonBorder(g2, rect);
  }

  /**
   * Paint scroll bar button highlighted.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  protected static void paintScrollBarButtonHighlighted(Graphics2D g2, Rectangle rect) {
    paintHighlightedScrollBarButtonBase(g2, rect);
    drawRect(g2, SCROLLBAR_HIGHLIGHT_OUTLINE_COLOR, rect);
  }

  /**
   * Paint highlighted scroll bar button base.
   *
   * @param g2   the g 2
   * @param rect the rect
   */
  protected static void paintHighlightedScrollBarButtonBase(Graphics2D g2, Rectangle rect) {
    fill(g2, BASE_COLOR, rect);
  }

  /**
   * Paint scroll bar button base.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  protected static void paintScrollBarButtonBase(Graphics2D g2, Rectangle rect) {
    fill(g2, SCROLLBAR_BUTTON_BASE_COLOR, rect);
  }

  /**
   * Paint scroll bar button border.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  protected static void paintScrollBarButtonBorder(Graphics2D g2, Rectangle rect) {
    drawRect(g2, SCROLLBAR_OUTLINE_COLOR, rect);
  }

}
