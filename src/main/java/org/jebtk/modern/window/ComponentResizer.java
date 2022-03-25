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
package org.jebtk.modern.window;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import org.jebtk.core.sys.SysUtils;

/**
 * The ComponentResizer allows you to resize a component by dragging a border of
 * the component.
 */
public class ComponentResizer extends MouseAdapter {

  /**
   * The constant MINIMUM_SIZE.
   */
  private final static Dimension MINIMUM_SIZE = new Dimension(10, 10);

  /**
   * The constant MAXIMUM_SIZE.
   */
  private final static Dimension MAXIMUM_SIZE = new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);

  /**
   * The cursors.
   */
  private static Map<Integer, Integer> cursors = new HashMap<Integer, Integer>();
  {
    cursors.put(1, Cursor.N_RESIZE_CURSOR);
    cursors.put(2, Cursor.W_RESIZE_CURSOR);
    cursors.put(4, Cursor.S_RESIZE_CURSOR);
    cursors.put(8, Cursor.E_RESIZE_CURSOR);
    cursors.put(3, Cursor.NW_RESIZE_CURSOR);
    cursors.put(9, Cursor.NE_RESIZE_CURSOR);
    cursors.put(6, Cursor.SW_RESIZE_CURSOR);
    cursors.put(12, Cursor.SE_RESIZE_CURSOR);
  }

  /**
   * The drag insets.
   */
  private Insets mDragInsets;

  /**
   * The snap size.
   */
  private Dimension mSnapSize;

  /**
   * The direction.
   */
  private int direction;

  /**
   * The constant NORTH.
   */
  protected static final int NORTH = 1;

  /**
   * The constant WEST.
   */
  protected static final int WEST = 2;

  /**
   * The constant SOUTH.
   */
  protected static final int SOUTH = 4;

  /**
   * The constant EAST.
   */
  protected static final int EAST = 8;

  /**
   * The source cursor.
   */
  private Cursor sourceCursor;

  /**
   * The resizing.
   */
  private boolean mResizing;

  /**
   * The bounds.
   */
  private Rectangle bounds;

  /**
   * The pressed.
   */
  private Point pressed;

  /**
   * The autoscrolls.
   */
  private boolean autoscrolls;

  /**
   * The minimum size.
   */
  private Dimension mMinimumSize = MINIMUM_SIZE;

  /**
   * The maximum size.
   */
  private Dimension mMaximumSize = MAXIMUM_SIZE;

  /** The m source. */
  private Component mSource;

  /**
   * Convenience contructor. All borders are resizable in increments of a single
   * pixel. Components must be registered separately.
   */
  public ComponentResizer() {
    this(new Insets(5, 5, 5, 5), new Dimension(1, 1));
  }

  /**
   * Convenience contructor. All borders are resizable in increments of a single
   * pixel. Components can be registered when the class is created or they can be
   * registered separately afterwards.
   *
   * @param components components to be automatically registered
   */
  public ComponentResizer(Component... components) {
    this(new Insets(5, 5, 5, 5), new Dimension(1, 1), components);
  }

  /**
   * Convenience contructor. Eligible borders are resisable in increments of a
   * single pixel. Components can be registered when the class is created or they
   * can be registered separately afterwards.
   *
   * @param dragInsets Insets specifying which borders are eligible to be resized.
   * @param components components to be automatically registered
   */
  public ComponentResizer(Insets dragInsets, Component... components) {
    this(dragInsets, new Dimension(1, 1), components);
  }

  /**
   * Create a ComponentResizer.
   *
   * @param dragInsets Insets specifying which borders are eligible to be resized.
   * @param snapSize   Specify the dimension to which the border will snap to when
   *                   being dragged. Snapping occurs at the halfway mark.
   * @param components components to be automatically registered
   */
  public ComponentResizer(Insets dragInsets, Dimension snapSize, Component... components) {
    setDragInsets(dragInsets);
    setSnapSize(snapSize);
    registerComponent(components);
  }

  /**
   * Get the drag insets.
   *
   * @return the drag insets
   */
  public Insets getDragInsets() {
    return mDragInsets;
  }

  /**
   * Set the drag dragInsets. The insets specify an area where mouseDragged events
   * are recognized from the edge of the border inwards. A value of 0 for any size
   * will imply that the border is not resizable. Otherwise the appropriate drag
   * cursor will appear when the mouse is inside the resizable border area.
   *
   * @param dragInsets the new drag insets
   */
  public void setDragInsets(Insets dragInsets) {
    validateMinimumAndInsets(mMinimumSize, dragInsets);

    mDragInsets = dragInsets;
  }

  /**
   * Get the components maximum size.
   *
   * @return the maximum size
   */
  public Dimension getMaximumSize() {
    return mMaximumSize;
  }

  /**
   * Specify the maximum size for the component. The component will still be
   * constrained by the size of its parent.
   *
   * @param maximumSize the maximum size for a component.
   */
  public void setMaximumSize(Dimension maximumSize) {
    mMaximumSize = maximumSize;
  }

  /**
   * Get the components minimum size.
   *
   * @return the minimum size
   */
  public Dimension getMinimumSize() {
    return mMinimumSize;
  }

  /**
   * Specify the minimum size for the component. The minimum size is constrained
   * by the drag insets.
   *
   * @param minimumSize the minimum size for a component.
   */
  public void setMinimumSize(Dimension minimumSize) {
    validateMinimumAndInsets(minimumSize, mDragInsets);

    mMinimumSize = minimumSize;
  }

  /**
   * Remove listeners from the specified component.
   *
   * @param components the components
   */
  public void deregisterComponent(Component... components) {
    for (Component component : components) {
      component.removeMouseListener(this);
      component.removeMouseMotionListener(this);
    }
  }

  /**
   * Add the required listeners to the specified component.
   *
   * @param components the components
   */
  public void registerComponent(Component... components) {
    mSource = components[0];

    for (Component component : components) {
      component.addMouseListener(this);
      component.addMouseMotionListener(this);
    }
  }

  /**
   * Get the snap size.
   *
   * @return the snap size.
   */
  public Dimension getSnapSize() {
    return mSnapSize;
  }

  /**
   * Control how many pixels a border must be dragged before the size of the
   * component is changed. The border will snap to the size once dragging has
   * passed the halfway mark.
   *
   * @param snapSize Dimension object allows you to separately spcify a horizontal
   *                 and vertical snap size.
   */
  public void setSnapSize(Dimension snapSize) {
    mSnapSize = snapSize;
  }

  /**
   * When the components minimum size is less than the drag insets then we can't
   * determine which border should be resized so we need to prevent this from
   * happening.
   *
   * @param minimum the minimum
   * @param drag    the drag
   */
  private void validateMinimumAndInsets(Dimension minimum, Insets drag) {
    int minimumWidth = drag.left + drag.right;
    int minimumHeight = drag.top + drag.bottom;

    if (minimum.width < minimumWidth || minimum.height < minimumHeight) {
      String message = "Minimum size cannot be less than drag insets";
      throw new IllegalArgumentException(message);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseAdapter#mouseMoved(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseMoved(MouseEvent e) {
    Component source = e.getComponent();
    Point location = e.getPoint();
    direction = 0;

    if (location.x < mDragInsets.left) {
      direction += WEST;
    }

    if (location.x > source.getWidth() - mDragInsets.right - 1) {
      direction += EAST;
    }

    if (location.y < mDragInsets.top) {
      direction += NORTH;
    }

    if (location.y > source.getHeight() - mDragInsets.bottom - 1) {
      direction += SOUTH;
    }

    // Mouse is no longer over a resizable border

    if (direction == 0) {
      source.setCursor(sourceCursor);
    } else {
      int cursorType = cursors.get(direction);
      Cursor cursor = Cursor.getPredefinedCursor(cursorType);
      source.setCursor(cursor);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseAdapter#mouseEntered(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseEntered(MouseEvent e) {
    if (!mResizing) {
      Component source = e.getComponent();
      sourceCursor = source.getCursor();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseAdapter#mouseExited(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseExited(MouseEvent e) {
    if (!mResizing) {
      Component source = e.getComponent();
      source.setCursor(sourceCursor);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
   */
  @Override
  public void mousePressed(MouseEvent e) {
    // The mouseMoved event continually updates this variable

    if (direction == 0) {
      return;
    }

    // Setup for resizing. All future dragging calculations are done based
    // on the original bounds of the component and mouse pressed location.

    mResizing = true;

    Component source = e.getComponent();
    pressed = e.getPoint();
    SwingUtilities.convertPointToScreen(pressed, source);
    bounds = source.getBounds();

    SysUtils.err().println("pres ", pressed);

    // Making sure autoscrolls is false will allow for smoother resizing
    // of components

    if (mSource instanceof JComponent) {
      JComponent jc = (JComponent) mSource;
      autoscrolls = jc.getAutoscrolls();
      jc.setAutoscrolls(false);
    }
  }

  /**
   * Restore the original state of the Component.
   *
   * @param e the e
   */
  @Override
  public void mouseReleased(MouseEvent e) {
    mResizing = false;

    Component source = e.getComponent();
    source.setCursor(sourceCursor);

    if (mSource instanceof JComponent) {
      ((JComponent) mSource).setAutoscrolls(autoscrolls);
    }
  }

  /**
   * Resize the component ensuring location and size is within the bounds of the
   * parent container and that the size is within the minimum and maximum
   * constraints.
   * 
   * All calculations are done using the bounds of the component when the resizing
   * started.
   *
   * @param e the e
   */
  @Override
  public void mouseDragged(MouseEvent e) {
    if (mResizing == false) {
      return;
    }

    Component source = e.getComponent();
    Point dragged = e.getPoint();
    SwingUtilities.convertPointToScreen(dragged, source);

    // SysUtils.err().println("drag", dragged);

    changeBounds(direction, bounds, pressed, dragged);
  }

  /**
   * Change bounds.
   *
   * @param direction the direction
   * @param bounds    the bounds
   * @param pressed   the pressed
   * @param current   the current
   */
  protected void changeBounds(int direction, Rectangle bounds, Point pressed, Point current) {
    changeBounds(mSource, direction, bounds, pressed, current);
  }

  /**
   * Change bounds.
   *
   * @param source    the source
   * @param direction the direction
   * @param bounds    the bounds
   * @param pressed   the pressed
   * @param current   the current
   */
  protected void changeBounds(Component source, int direction, Rectangle bounds, Point pressed, Point current) {
    // Start with original locaton and size

    int x = bounds.x;
    int y = bounds.y;
    int width = bounds.width;
    int height = bounds.height;

    GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();

    // System.err.println("devices " + env.getScreenDevices().length);

    boolean bound = env.getScreenDevices().length == 1;

    // Resizing the West or North border affects the size and location

    if (WEST == (direction & WEST)) {
      int drag = getDragDistance(pressed.x, current.x, mSnapSize.width);
      int maximum = Math.min(width + x, mMaximumSize.width);
      drag = getDragBounded(drag, mSnapSize.width, width, mMinimumSize.width, maximum);

      x -= drag;
      width += drag;
    }

    if (NORTH == (direction & NORTH)) {
      int drag = getDragDistance(pressed.y, current.y, mSnapSize.height);
      int maximum = Math.min(height + y, mMaximumSize.height);
      drag = getDragBounded(drag, mSnapSize.height, height, mMinimumSize.height, maximum);

      y -= drag;
      height += drag;
    }

    // Resizing the East or South border only affects the size

    if (EAST == (direction & EAST)) {
      int drag = getDragDistance(current.x, pressed.x, mSnapSize.width);

      if (bound) {
        Dimension boundingSize = getBoundingSize(env, source);
        int maximum = Math.min(boundingSize.width - x, mMaximumSize.width);
        drag = getDragBounded(drag, mSnapSize.width, width, mMinimumSize.width, maximum);
      }

      width += drag;
    }

    if (SOUTH == (direction & SOUTH)) {
      int drag = getDragDistance(current.y, pressed.y, mSnapSize.height);

      if (bound) {
        Dimension boundingSize = getBoundingSize(env, source);
        int maximum = Math.min(boundingSize.height - y, mMaximumSize.height);
        drag = getDragBounded(drag, mSnapSize.height, height, mMinimumSize.height, maximum);
      }

      height += drag;
    }

    SysUtils.err().println("resize", width, height);

    source.setLocation(x, y);
    source.setSize(width, height);
    source.revalidate();
  }

  /**
   * Gets the drag distance.
   *
   * @param larger   the larger
   * @param smaller  the smaller
   * @param snapSize the snap size
   * @return the drag distance
   */
  /*
   * Determine how far the mouse has moved from where dragging started
   */
  private static int getDragDistance(int larger, int smaller, int snapSize) {
    int halfway = snapSize / 2;
    int drag = larger - smaller;
    drag += (drag < 0) ? -halfway : halfway;
    drag = (drag / snapSize) * snapSize;

    return drag;
  }

  /**
   * Gets the drag bounded.
   *
   * @param drag      the drag
   * @param snapSize  the snap size
   * @param dimension the dimension
   * @param minimum   the minimum
   * @param maximum   the maximum
   * @return the drag bounded
   */
  /*
   * Adjust the drag value to be within the minimum and maximum range.
   */
  private static int getDragBounded(int drag, int snapSize, int dimension, int minimum, int maximum) {
    while (dimension + drag < minimum) {
      drag += snapSize;
    }

    while (dimension + drag > maximum) {
      drag -= snapSize;
    }

    return drag;
  }

  /**
   * Gets the bounding size.
   *
   * @param env    the env
   * @param source the source
   * @return the bounding size
   */
  /*
   * Keep the size of the component within the bounds of its parent.
   */
  private static Dimension getBoundingSize(GraphicsEnvironment env, Component source) {
    if (source instanceof Window) {
      Rectangle bounds = env.getMaximumWindowBounds();
      return new Dimension(bounds.width, bounds.height);
    } else {
      return source.getParent().getSize();
    }
  }
}