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
package org.jebtk.modern.graphics;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.geom.IntDim;
import org.jebtk.core.geom.IntPos2D;
import org.jebtk.core.geom.IntRect;
import org.jebtk.modern.ModernFocusableWidget;

/**
 * A panel designed for drawing operations/graphics that is scroll aware, the
 * graphics context is translated to match the scroll parameters. This removes
 * the need to move the actual panel around in the scroll pane.
 * 
 * @author Antony Holmes
 *
 */
public class ModernCanvas extends ModernFocusableWidget
    implements CanvasEventProducer, CanvasMouseEventProducer, CanvasMouseWheelEventProducer, CanvasMouseListener,
    CanvasMouseWheelListener, CanvasKeyEventProducer, CanvasKeyListener, CanvasCursorEventProducer {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * The constant SCROLL_INCREMENT.
   */
  public static Point SCROLL_INCREMENT = new Point(100, 100);

  /**
   * The constant CANVAS_CHANGED.
   */
  public static String CANVAS_CHANGED = "canvas_changed";

  /**
   * The member canvas mid point.
   */
  private IntPos2D mCanvasMidPoint = new IntPos2D(0, 0);

  // protected IntPosition mCanvasOffset = new IntPosition(0, 0);

  /**
   * The member canvas listeners.
   */
  private CanvasListeners mCanvasListeners = new CanvasListeners();

  /**
   * The member canvas mouse listeners.
   */
  private CanvasMouseListeners mCanvasMouseListeners = new CanvasMouseListeners();

  /** The m canvas key listeners. */
  private CanvasKeyListeners mCanvasKeyListeners = new CanvasKeyListeners();

  /** The m canvas cursor listeners. */
  private CanvasCursorListeners mCanvasCursorListeners = new CanvasCursorListeners();

  private CanvasMouseWheelListeners mCanvasMouseWheelListeners = new CanvasMouseWheelListeners();

  /**
   * The member canvas size.
   */
  // private IntDim mCanvasSize = new IntDim(0, 0);

  /**
   * The member view rect.
   */
  // protected IntRect mViewRect = new IntRect(0, 0, 0, 0);

  /**
   * The member int canvas size.
   */
  private IntDim mIntCanvasSize = new IntDim(0, 0);

  /**
   * The member int view rect.
   */
  protected IntRect mIntViewRect = IntRect.ZERO_RECT;

  /**
   * The member forward canvas events.
   */
  private boolean mForwardCanvasEvents = true;

  /** The m buffer redraw. */
  private ChangeEvent mBufferRedraw = null;

  /** The m buffer scroll. */
  private ChangeEvent mBufferScroll = null;

  /** The m buffer changed. */
  private ChangeEvent mBufferChanged = null;

  /** The m buffer resized. */
  private ChangeEvent mBufferResized = null;

  /** The m cache view rect. */
  protected Rectangle mCacheViewRect;

  private static final IntDim DEFAULT_SIZE = new IntDim(100, 100);

  /**
   * Internal cache of preferred size which can be different to the public
   */
  protected IntDim mAbsPrefSize = DEFAULT_SIZE;

  /**
   * The class MouseEvents.
   */
  private class MouseEvents implements MouseListener {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseClicked(MouseEvent e) {
      fireCanvasMouseClicked(e);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseEntered(MouseEvent e) {
      fireCanvasMouseEntered(e);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseExited(MouseEvent e) {
      fireCanvasMouseExited(e);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(MouseEvent e) {
      fireCanvasMousePressed(e);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseReleased(MouseEvent e) {
      fireCanvasMouseReleased(e);
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
      fireCanvasMouseDragged(e);

      Rectangle r = new Rectangle(e.getX(), e.getY(), 1, 1);
      scrollRectToVisible(r);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseMoved(MouseEvent e) {
      fireCanvasMouseMoved(e);
    }
  }

  /**
   * The class ComponentEvents.
   */
  /*
   * private class ComponentEvents extends ComponentAdapter {
   * 
   * @Override public void componentResized(ComponentEvent e) {
   * //updateViewRectangle();
   * 
   * //updateCanvasSize();
   * 
   * //fireCanvasChanged();
   * 
   * repaint(); } }
   */

  private class KeyEvents implements KeyListener {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
     */
    @Override
    public void keyPressed(KeyEvent e) {
      fireCanvasKeyPressed(e);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
     */
    @Override
    public void keyReleased(KeyEvent e) {
      fireCanvasKeyReleased(e);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
     */
    @Override
    public void keyTyped(KeyEvent e) {
      fireCanvasKeyTyped(e);
    }
  }

  private class MouseWheelEvents implements MouseWheelListener {

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
      fireCanvasMouseWheelMoved(e);
    }

  }

  /**
   * Instantiates a new modern canvas.
   */
  public ModernCanvas() {
    setup();
  }

  /**
   * Instantiates a new modern canvas.
   *
   * @param size the size
   */
  public ModernCanvas(Dimension size) {
    setup();

    setCanvasSize(size);
  }

  /**
   * Setup.
   */
  private void setup() {
    addMouseListener(new MouseEvents());
    addMouseMotionListener(new MouseMotionEvents());
    addMouseWheelListener(new MouseWheelEvents());
    // addComponentListener(new ComponentEvents());
    addKeyListener(new KeyEvents());

    // Draw as fast as possible
    getAAModes().clear();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#getName()
   */
  @Override
  public String getName() {
    return "Canvas " + getId();
  }

  /**
   * Sets whether canvas events are forwarded or not. This can be useful if you
   * want to temporarily disable drawing operations for instance if doing a batch
   * update to a canvas where you do not want to trigger multiple drawing events
   * until it has finished updating.
   *
   * @param enabled the new forward canvas events enabled
   */
  public void setForwardCanvasEventsEnabled(boolean enabled) {
    mForwardCanvasEvents = enabled;

    if (enabled) {
      // If events have been cached, fire one representative event
      // to represent many possible triggers. This cuts down on
      // refreshes
      if (mBufferChanged != null) {
        fireCanvasChanged();
      } else if (mBufferRedraw != null) {
        fireCanvasRedraw();
      } else if (mBufferScroll != null) {
        fireCanvasScrolled();
      } else if (mBufferResized != null) {
        fireCanvasResized();
      } else {
        // Do nothing
      }

      mBufferRedraw = null;
      mBufferScroll = null;
      mBufferChanged = null;
      mBufferResized = null;
    }
  }

  /**
   * Sets the canvas size.
   *
   * @param size the size
   */
  public void updateCanvasSize(IntDim size) {
    updateCanvasSize(size.getW(), size.getH());
  }

  /**
   * Sets the canvas size.
   *
   * @param size the new canvas size
   */
  public void updateCanvasSize(Dimension size) {
    updateCanvasSize(size.width, size.height);
  }

  /**
   * Sets the canvas size.
   */
  public void setCanvasSize() {
    updateCanvasSize();

    fireCanvasChanged();
  }

  /**
   * Update canvas size.
   */
  public void updateCanvasSize() {
    updateCanvasSize(mIntCanvasSize);
  }

  /**
   * Sets the canvas size.
   *
   * @param size the new canvas size
   */
  public void setCanvasSize(IntDim size) {
    updateCanvasSize(size);

    fireCanvasResized();
  }

  /**
   * Sets the canvas size.
   *
   * @param size the new canvas size
   */
  public void setCanvasSize(Dimension size) {
    updateCanvasSize(size);

    fireCanvasResized();
  }

  /**
   * Sets the canvas size.
   *
   * @param width  the width
   * @param height the height
   */
  public void setCanvasSize(int width, int height) {
    updateCanvasSize(width, height);

    fireCanvasResized();
  }

  /**
   * Set the size of the canvas. This will take into account any borders.
   *
   * @param width  the width
   * @param height the height
   */
  public void updateCanvasSize(int width, int height) {
    mIntCanvasSize = new IntDim(width, height);

    // The total size of the canvas including margins
    // mCanvasSize = new IntDim(size.getW() + getInsets().left +
    // getInsets().right,
    // size.getH() + getInsets().top + getInsets().bottom);

    mCanvasMidPoint = new IntPos2D(width / 2, height / 2);

    setPreferredSize(
        new Dimension(width + getInsets().left + getInsets().right, height + getInsets().top + getInsets().bottom)); // IntDim.toDimension(mCanvasSize));

    // System.err.println("canvas size " + mPs + " " + this);

    revalidate();
    repaint();
  }

  // @Override
  // public Dimension getPreferredSize() {
  // return IntDim.toDimension(getCanvasSize());
  // }

  /**
   * Returns the total size of the canvas including any borders. To get the size
   * of the canvas excluding borders, query getIntCanvasSize().
   *
   * @return the canvas size
   */
  public IntDim getCanvasSize() {
    return IntDim.create(getPreferredSize()); // mCanvasSize;
  }

  /**
   * Returns the size of the canvas without borders.
   *
   * @return the int canvas size
   */
  public IntDim getIntCanvasSize() {
    return mIntCanvasSize;
  }

  /**
   * Update the canvas offset and trigger a repaint.
   *
   * @param position the new view rectangle
   */
  // public void setViewRectangle(int x, int y) {
  // setViewRectangle(new IntPosition(x, y));
  // }

  // public void setViewRectangle(IntPosition offset) {
  // mCanvasOffset = offset;

  // setViewRectangle();
  // }

  /*
   * public void setViewRectangle() { updateViewRectangle();
   * 
   * fireCanvasScrolled(); }
   * 
   * public void updateViewRectangle() { updateViewRectangle(mViewRect.getX(),
   * mViewRect.getY(), mInternalRect.getW(), mInternalRect.getH()); }
   */

  /**
   * Sets the view rectangle.
   *
   * @param position the new view rectangle
   */
  public void setViewRectangle(IntPos2D position) {
    updateViewRectangle(position);

    fireCanvasScrolled();
  }

  /**
   * Update view rectangle.
   *
   * @param position the position
   */
  public void updateViewRectangle(IntPos2D position) {
    updateViewRectangle(new IntRect(position.getX(), position.getY(), mInternalRect.getW(), mInternalRect.getH()));
  }

  /**
   * Sets the view rectangle.
   *
   * @param x the x
   * @param y the y
   */
  public void setViewRectangle(int x, int y) {
    updateViewRectangle(x, y);

    fireCanvasScrolled();
  }

  /**
   * Sets the view rectangle.
   *
   * @param dim the new view rectangle
   */
  public void setViewRectangle(IntDim dim) {
    updateViewRectangle(dim);

    fireCanvasScrolled();
  }

  /**
   * Sets the view rectangle.
   *
   * @param position the position
   * @param dim      the dim
   */
  public void setViewRectangle(IntPos2D position, IntDim dim) {
    updateViewRectangle(position, dim);

    fireCanvasScrolled();
  }

  /**
   * Sets the view rectangle.
   *
   * @param x the x
   * @param y the y
   * @param w the w
   * @param h the h
   */
  public void setViewRectangle(int x, int y, int w, int h) {
    updateViewRectangle(x, y, w, h);

    fireCanvasScrolled();
  }

  /**
   * Sets the view rectangle.
   *
   * @param rect the new view rectangle
   */
  public void setViewRectangle(IntRect rect) {
    updateViewRectangle(rect);

    fireCanvasScrolled();
  }

  /**
   * Update view rectangle.
   *
   * @param x the x
   * @param y the y
   */
  public void updateViewRectangle(int x, int y) {
    updateViewRectangle(new IntRect(x, y, mInternalRect.getW(), mInternalRect.getH()));
  }

  /**
   * Update view rectangle.
   *
   * @param size the size
   */
  public void updateViewRectangle(IntDim size) {
    updateViewRectangle(new IntRect(0, 0, size.getW(), size.getH()));
  }

  /**
   * Update view rectangle.
   *
   * @param position the position
   * @param dim      the dim
   */
  public void updateViewRectangle(IntPos2D position, IntDim dim) {
    updateViewRectangle(position.getX(), position.getY(), dim.getW(), dim.getH());
  }

  /**
   * Update the current view on the rectangle.
   *
   * @param x the x
   * @param y the y
   * @param w the w
   * @param h the h
   */
  public void updateViewRectangle(int x, int y, int w, int h) {
    updateViewRectangle(new IntRect(x, y, w, h));
  }

  /**
   * Update the viewing rect without triggering a redraw.
   *
   * @param rect the rect
   */
  public void updateViewRectangle(IntRect rect) {
    mIntViewRect = rect;

    scrollRectToVisible(rect);

    /*
     * mViewRect = new IntRect(mIntViewRect.getX() + getInsets().left,
     * mIntViewRect.getY() + getInsets().top, mIntViewRect.getW(),
     * mIntViewRect.getH());
     */
  }

  /**
   * Gets the view rectangle.
   *
   * @return the view rectangle
   */
  public IntRect getViewRect() {
    return IntRect.createRect(getVisibleRect()); // return mViewRect;
  }

  /**
   * Gets the internal view rectangle.
   *
   * @return the int view rectangle
   */
  public IntRect getIntViewRect() {
    return mIntViewRect;
  }

  /**
   * Returns the mid point of the canvas, so it does not have to be repeatedly
   * calculated.
   *
   * @return the canvas mid point
   */
  protected IntPos2D getCanvasMidPoint() {
    return mCanvasMidPoint;
  }

  /**
   * Translate x.
   *
   * @param x the x
   * @return the int
   */
  protected int translateX(int x) {
    return x + getVisibleRect().x; // mViewRect.getX();
  }

  /**
   * Translate y.
   *
   * @param y the y
   * @return the int
   */
  protected int translateY(int y) {
    return y + getVisibleRect().y; // mViewRect.getY();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.widget.ModernWidget#drawBackground(java.awt.Graphics2D)
   */
  // @Override
  // public void drawBackground(Graphics2D g2) {
  // Do nothing
  // }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.widget.ModernWidget#setFont(java.awt.Font)
   */
  @Override
  public void setFont(Font font) {
    super.setFont(font);

    fireCanvasRedraw();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.widget.ModernWidget#drawForegroundAA(java.awt.
   * Graphics2D)
   */
  // @Override
  // public void drawForegroundAA(Graphics2D g2) {
  //// rasterCanvas(g2);
  // }

  /**
   * Draw translated canvas.
   *
   * @param g2 the g 2
   */
  @Override
  public void rasterForeground(Graphics2D g2) {
    rasterCanvas(g2, DrawingContext.UI);
  }

  /**
   * In charge of rendering the canvas. This is position agnostic, drawForeground
   * will adjust the offset of the image if necessary. This method should assume
   * the picture is being rendered at 0,0.
   *
   * @param g2      the g2
   * @param context the context
   */
  // public void drawTranslatedCanvas(Graphics2D g2, DrawingContext context) {
  // cacheCanvas(g2, context);
  // }

  /**
   * Cache canvas.
   *
   * @param g2      the g 2
   * @param context the context
   */
  public void rasterCanvas(Graphics2D g2, DrawingContext context) {
    if (context == DrawingContext.UI) {

      // Create an image version of the canvas and draw that to spped
      // up operations

      if (mRasterMode) {
        Rectangle vr = getVisibleRect();

        if (mBufferedImage == null || mCacheViewRect == null || !mCacheViewRect.equals(vr)) {
          // The canvas need only be the size of the available display
          mBufferedImage = ImageUtils.createImage(getSize());

          Graphics2D g2Temp = ImageUtils.createGraphics(mBufferedImage);

          try {
            // translate(g2Temp);
            aaCanvas(g2Temp, context);
          } finally {
            g2Temp.dispose();
          }

          mCacheViewRect = vr;
        }

        g2.drawImage(mBufferedImage, 0, 0, null);
      } else {
        aaCanvas(g2, context);
      }
    } else {
      drawCanvas(g2, context);
    }
  }

  /**
   * Anti-alias the canvas.
   * 
   * @param g2
   * @param context
   */
  public void aaCanvas(Graphics2D g2, DrawingContext context) {
    if (getAAModes().size() > 0 && context == DrawingContext.UI) {
      Graphics2D g2Temp = ImageUtils.createAAGraphics(g2, getAAModes());

      try {
        zoomCanvas(g2Temp, context);
      } finally {
        g2Temp.dispose();
      }
    } else {
      zoomCanvas(g2, context);
    }
  }

  /**
   * Responsible for zoom on canvas.
   * 
   * @param g2
   * @param context
   */
  public void zoomCanvas(Graphics2D g2, DrawingContext context) {
    drawCanvas(g2, context);
  }

  /**
   * Draw canvas.
   *
   * @param g2      the g 2
   * @param context the context
   */
  public void drawCanvas(Graphics2D g2, DrawingContext context) {
    // Do nothing
  }

  /**
   * Should translate the graphics context to the current view window coordinates.
   *
   * @param g2 the g 2 temp
   */
  // public void translate(Graphics2D g2) {
  // Rectangle r = getVisibleRect();
  //
  // g2.translate(-r.x, -r.y);
  // }

  public void setPreferredSize() {
    setPreferredSize(IntDim.toDimension(mAbsPrefSize));
  }

  public void setPreferredSize(IntDim d) {
    // mAbsPrefSize = d;

    setPreferredSize(IntDim.toDimension(d));

    // fireCanvasResized();
  }

  @Override
  public void setPreferredSize(Dimension d) {
    mAbsPrefSize = IntDim.create(d);

    super.setPreferredSize(d);

    fireCanvasResized();
  }

  /**
   * Returns the absolute preferred size of the canvas at a zoom level 1, i.e. no
   * zooming. This is so the desired size of the canvas is always known.
   * 
   * @return
   */
  public IntDim getAbsPreferredSize() {
    return mAbsPrefSize;
  }

  /**
   * Translate the current mouse position from the view coordinates to the panel
   * coordinates.
   *
   * @param e the e
   * @return the point
   */
  public IntPos2D translateCoordinate(MouseEvent e) {
    return translateCoordinate(e.getX(), e.getY()); // translateCoordinate(e.getX(),
    // e.getY());
  }

  /**
   * Translate coordinate.
   *
   * @param e the e
   * @return the int pos 2 D
   */
  public IntPos2D translateCoordinate(CanvasMouseEvent e) {
    IntPos2D p = e.getScaledPos();

    return translateCoordinate(p.getX(), p.getY()); // translateCoordinate(e.getX(),
    // e.getY());
  }

  /**
   * Translate the current mouse position from the view coordinates to the panel
   * coordinates.
   *
   * @param p the p
   * @return the point
   */
  public IntPos2D translateCoordinate(Point p) {
    return translateCoordinate(p.x, p.y);
  }

  /**
   * Translate coordinate.
   *
   * @param p the p
   * @return the point
   */
  public IntPos2D translateCoordinate(IntPos2D p) {
    return translateCoordinate(p.getX(), p.getY());
  }

  /**
   * Translate the current mouse position from the view coordinates to the panel
   * coordinates.
   *
   * @param x the x
   * @param y the y
   * @return the point
   */
  public IntPos2D translateCoordinate(int x, int y) {
    // Rectangle r = getVisibleRect();
    // IntPos2D p = new IntPos2D(x + r.x, y + r.y);

    // System.err.println("mview " + x + " " + y + " " + p + " " + mViewRect);

    return new IntPos2D(x, y);

    // return new Point(x, y);
  }

  /**
   * Creates a mouse event where the x and y coordinates are adjusted for the
   * canvas.
   *
   * @param e the e
   * @return the canvas mouse event
   */
  public CanvasMouseEvent createCanvasMouseEvent(MouseEvent e) {
    return new CanvasMouseEvent(this, e, translateCoordinate(e));
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.graphics.ModernCanvasEventProducer#addCanvasListener(
   * org.abh.lib.ui.modern.graphics.ModernCanvasListener)
   */
  @Override
  public void addCanvasListener(CanvasListener l) {
    mCanvasListeners.addCanvasListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvasEventProducer#
   * removeCanvasListener (org.abh.lib.ui.modern.graphics.ModernCanvasListener)
   */
  @Override
  public void removeCanvasListener(CanvasListener l) {
    mCanvasListeners.removeCanvasListener(l);
  }

  /**
   * Fire canvas changed.
   */
  public void fireCanvasChanged() {

    fireCanvasChanged(new ChangeEvent(this));
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.graphics.ModernCanvasEventProducer#fireCanvasChanged(
   * org.abh.lib.event.ChangeEvent)
   */
  @Override
  public void fireCanvasChanged(ChangeEvent e) {
    repaint();

    if (mCanvasListeners != null) {
      if (mForwardCanvasEvents) {
        mCanvasListeners.fireCanvasChanged(e);
      } else {
        mBufferChanged = e;
      }
    }
  }

  /**
   * Fire canvas redraw.
   */
  public void fireCanvasRedraw() {
    fireCanvasRedraw(new ChangeEvent(this));
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.graphics.ModernCanvasEventProducer#fireCanvasRedraw(
   * org .abh.lib.event.ChangeEvent)
   */
  @Override
  public void fireCanvasRedraw(ChangeEvent e) {
    repaint();

    if (mCanvasListeners != null) {
      if (mForwardCanvasEvents) {
        mCanvasListeners.fireCanvasRedraw(e);
      } else {
        mBufferRedraw = e;
      }
    }
  }

  /**
   * Fire canvas scrolled.
   */
  public void fireCanvasScrolled() {
    fireCanvasScrolled(new ChangeEvent(this));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvasEventProducer#
   * fireCanvasScrolled( org.abh.lib.event.ChangeEvent)
   */
  @Override
  public void fireCanvasScrolled(ChangeEvent e) {
    repaint();

    if (mCanvasListeners != null) {
      if (mForwardCanvasEvents) {
        mCanvasListeners.fireCanvasScrolled(e);
      } else {
        mBufferScroll = e;
      }
    }
  }

  /**
   * Fire canvas resized.
   */
  public void fireCanvasResized() {
    fireCanvasResized(new ChangeEvent(this));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvasEventProducer#
   * fireCanvasScrolled( org.abh.lib.event.ChangeEvent)
   */
  @Override
  public void fireCanvasResized(ChangeEvent e) {
    repaint();

    if (mCanvasListeners != null) {
      if (mForwardCanvasEvents) {
        mCanvasListeners.fireCanvasResized(e);
      } else {
        mBufferResized = e;
      }
    }
  }

  //
  // Mouse events
  //

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvasMouseEventProducer#
   * addCanvasMouseListener(org.abh.lib.ui.modern.graphics.
   * ModernCanvasMouseListener)
   */
  @Override
  public void addCanvasMouseListener(CanvasMouseListener l) {
    mCanvasMouseListeners.addCanvasMouseListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvasMouseEventProducer#
   * removeCanvasMouseListener(org.abh.lib.ui.modern.graphics.
   * ModernCanvasMouseListener)
   */
  @Override
  public void removeCanvasMouseListener(CanvasMouseListener l) {
    mCanvasMouseListeners.removeCanvasMouseListener(l);
  }

  /**
   * Fire canvas mouse moved.
   *
   * @param e the e
   */
  public void fireCanvasMouseMoved(MouseEvent e) {
    fireCanvasMouseMoved(createCanvasMouseEvent(e));
  }

  /**
   * Fire canvas mouse dragged.
   *
   * @param e the e
   */
  public void fireCanvasMouseDragged(MouseEvent e) {
    fireCanvasMouseDragged(createCanvasMouseEvent(e));
  }

  /**
   * Fire canvas mouse clicked.
   *
   * @param e the e
   */
  public void fireCanvasMouseClicked(MouseEvent e) {
    fireCanvasMouseClicked(createCanvasMouseEvent(e));
  }

  /**
   * Fire canvas mouse pressed.
   *
   * @param e the e
   */
  public void fireCanvasMousePressed(MouseEvent e) {
    CanvasMouseEvent ce = createCanvasMouseEvent(e);

    fireCanvasMousePressed(ce);
  }

  /**
   * Fire canvas mouse released.
   *
   * @param e the e
   */
  public void fireCanvasMouseReleased(MouseEvent e) {
    fireCanvasMouseReleased(createCanvasMouseEvent(e));
  }

  /**
   * Fire canvas mouse entered.
   *
   * @param e the e
   */
  public void fireCanvasMouseEntered(MouseEvent e) {
    fireCanvasMouseEntered(createCanvasMouseEvent(e));
  }

  /**
   * Fire canvas mouse exited.
   *
   * @param e the e
   */
  public void fireCanvasMouseExited(MouseEvent e) {
    fireCanvasMouseExited(createCanvasMouseEvent(e));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvasMouseEventProducer#
   * fireCanvasMouseMoved(org.abh.lib.ui.modern.graphics.CanvasMouseEvent)
   */
  @Override
  public void fireCanvasMouseMoved(CanvasMouseEvent e) {
    mCanvasMouseListeners.fireCanvasMouseMoved(e);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvasMouseEventProducer#
   * fireCanvasMouseDragged(org.abh.lib.ui.modern.graphics.CanvasMouseEvent)
   */
  @Override
  public void fireCanvasMouseDragged(CanvasMouseEvent e) {
    mCanvasMouseListeners.fireCanvasMouseDragged(e);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvasMouseEventProducer#
   * fireCanvasMouseClicked(org.abh.lib.ui.modern.graphics.CanvasMouseEvent)
   */
  @Override
  public void fireCanvasMouseClicked(CanvasMouseEvent e) {
    mCanvasMouseListeners.fireCanvasMouseClicked(e);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvasMouseEventProducer#
   * fireCanvasMousePressed(org.abh.lib.ui.modern.graphics.CanvasMouseEvent)
   */
  @Override
  public void fireCanvasMousePressed(CanvasMouseEvent e) {
    mCanvasMouseListeners.fireCanvasMousePressed(e);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvasMouseEventProducer#
   * fireCanvasMouseReleased(org.abh.lib.ui.modern.graphics.CanvasMouseEvent)
   */
  @Override
  public void fireCanvasMouseReleased(CanvasMouseEvent e) {
    mCanvasMouseListeners.fireCanvasMouseReleased(e);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvasMouseEventProducer#
   * fireCanvasMouseEntered(org.abh.lib.ui.modern.graphics.CanvasMouseEvent)
   */
  @Override
  public void fireCanvasMouseEntered(CanvasMouseEvent e) {
    mCanvasMouseListeners.fireCanvasMouseEntered(e);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvasMouseEventProducer#
   * fireCanvasMouseExited(org.abh.lib.ui.modern.graphics.CanvasMouseEvent)
   */
  @Override
  public void fireCanvasMouseExited(CanvasMouseEvent e) {
    mCanvasMouseListeners.fireCanvasMouseExited(e);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvasMouseListener#
   * canvasMouseClicked( org.abh.lib.ui.modern.graphics.CanvasMouseEvent)
   */
  @Override
  public void canvasMouseClicked(CanvasMouseEvent e) {
    fireCanvasMouseClicked(createCanvasMouseEvent(e));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvasMouseListener#
   * canvasMouseEntered( org.abh.lib.ui.modern.graphics.CanvasMouseEvent)
   */
  @Override
  public void canvasMouseEntered(CanvasMouseEvent e) {
    fireCanvasMouseEntered(createCanvasMouseEvent(e));
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.graphics.ModernCanvasMouseListener#canvasMouseExited(
   * org.abh.lib.ui.modern.graphics.CanvasMouseEvent)
   */
  @Override
  public void canvasMouseExited(CanvasMouseEvent e) {
    fireCanvasMouseExited(createCanvasMouseEvent(e));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvasMouseListener#
   * canvasMousePressed( org.abh.lib.ui.modern.graphics.CanvasMouseEvent)
   */
  @Override
  public void canvasMousePressed(CanvasMouseEvent e) {
    CanvasMouseEvent ec = createCanvasMouseEvent(e);

    fireCanvasMousePressed(ec);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvasMouseListener#
   * canvasMouseReleased( org.abh.lib.ui.modern.graphics.CanvasMouseEvent)
   */
  @Override
  public void canvasMouseReleased(CanvasMouseEvent e) {
    fireCanvasMouseReleased(createCanvasMouseEvent(e));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvasMouseListener#
   * canvasMouseDragged( org.abh.lib.ui.modern.graphics.CanvasMouseEvent)
   */
  @Override
  public void canvasMouseDragged(CanvasMouseEvent e) {
    fireCanvasMouseDragged(createCanvasMouseEvent(e));
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.graphics.ModernCanvasMouseListener#canvasMouseMoved(
   * org .abh.lib.ui.modern.graphics.CanvasMouseEvent)
   */
  @Override
  public void canvasMouseMoved(CanvasMouseEvent e) {
    fireCanvasMouseMoved(createCanvasMouseEvent(e));
  }

  //
  // Key events
  //

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.graphics.ModernCanvasKeyListener#canvasKeyTyped(java.awt.
   * event.KeyEvent)
   */
  @Override
  public void canvasKeyTyped(KeyEvent e) {
    fireCanvasKeyTyped(e);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.graphics.ModernCanvasKeyListener#canvasKeyPressed(java.
   * awt. event.KeyEvent)
   */
  @Override
  public void canvasKeyPressed(KeyEvent e) {
    fireCanvasKeyPressed(e);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.graphics.ModernCanvasKeyListener#canvasKeyReleased(java.
   * awt .event.KeyEvent)
   */
  @Override
  public void canvasKeyReleased(KeyEvent e) {
    fireCanvasKeyReleased(e);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.graphics.ModernCanvasKeyEventProducer#
   * addCanvasKeyListener( org.abh.common.ui.graphics.ModernCanvasKeyListener)
   */
  @Override
  public void addCanvasKeyListener(CanvasKeyListener l) {
    mCanvasKeyListeners.addCanvasKeyListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.graphics.ModernCanvasKeyEventProducer#
   * removeCanvasKeyListener(org.abh.common.ui.graphics.ModernCanvasKeyListener)
   */
  @Override
  public void removeCanvasKeyListener(CanvasKeyListener l) {
    mCanvasKeyListeners.removeCanvasKeyListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.graphics.ModernCanvasKeyEventProducer#fireCanvasKeyTyped(
   * java.awt.event.KeyEvent)
   */
  @Override
  public void fireCanvasKeyTyped(KeyEvent e) {
    mCanvasKeyListeners.fireCanvasKeyTyped(e);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.graphics.ModernCanvasKeyEventProducer#
   * fireCanvasKeyPressed( java.awt.event.KeyEvent)
   */
  @Override
  public void fireCanvasKeyPressed(KeyEvent e) {
    mCanvasKeyListeners.fireCanvasKeyPressed(e);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.graphics.ModernCanvasKeyEventProducer#
   * fireCanvasKeyReleased (java.awt.event.KeyEvent)
   */
  @Override
  public void fireCanvasKeyReleased(KeyEvent e) {
    mCanvasKeyListeners.fireCanvasKeyReleased(e);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.graphics.CanvasCursorEventProducer#
   * addCanvasCursorListener( org.abh.common.ui.graphics.CanvasCursorListener)
   */
  @Override
  public void addCanvasCursorListener(CanvasCursorListener l) {
    mCanvasCursorListeners.addCanvasCursorListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.Component#setCursor(java.awt.Cursor)
   */
  @Override
  public void setCursor(Cursor cursor) {
    super.setCursor(cursor);

    fireCanvasCursorChanged();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.graphics.CanvasCursorEventProducer#
   * removeCanvasCursorListener(org.abh.common.ui.graphics.CanvasCursorListener)
   */
  @Override
  public void removeCanvasCursorListener(CanvasCursorListener l) {
    mCanvasCursorListeners.removeCanvasCursorListener(l);
  }

  /**
   * Fire canvas cursor changed.
   */
  public void fireCanvasCursorChanged() {
    fireCanvasCursorChanged(new CanvasCursorEvent(this, getCursor()));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.graphics.CanvasCursorEventProducer#
   * fireCanvasCursorChanged( org.abh.common.ui.graphics.CanvasCursorEvent)
   */
  @Override
  public void fireCanvasCursorChanged(CanvasCursorEvent e) {
    mCanvasCursorListeners.fireCanvasCursorChanged(e);
  }

  @Override
  public void canvasMouseWheelMoved(CanvasMouseWheelEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addCanvasMouseWheelListener(CanvasMouseWheelListener l) {
    mCanvasMouseWheelListeners.addCanvasMouseWheelListener(l);
  }

  @Override
  public void removeCanvasMouseWheelListener(CanvasMouseWheelListener l) {
    mCanvasMouseWheelListeners.removeCanvasMouseWheelListener(l);
  }

  @Override
  public void fireCanvasMouseWheelMoved(CanvasMouseWheelEvent e) {
    mCanvasMouseWheelListeners.fireCanvasMouseWheelMoved(e);
  }

  public void fireCanvasMouseWheelMoved(MouseWheelEvent e) {
    fireCanvasMouseWheelMoved(createCanvasMouseWheelEvent(e));
  }

  public CanvasMouseWheelEvent createCanvasMouseWheelEvent(MouseWheelEvent e) {
    return new CanvasMouseWheelEvent(this, e, translateCoordinate(e));
  }
}
