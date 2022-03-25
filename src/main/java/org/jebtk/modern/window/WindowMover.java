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

/**
 * This class allows you to move a Component by using a mouse. The Component
 * moved can be a high level Window (ie. Window, Frame, Dialog) in which case
 * the Window is moved within the desktop. Or the Component can belong to a
 * Container in which case the Component is moved within the Container.
 *
 * When moving a Window, the listener can be added to a child Component of the
 * Window. In this case attempting to move the child will result in the Window
 * moving. For example, you might create a custom "Title Bar" for an undecorated
 * Window and moving of the Window is accomplished by moving the title bar only.
 * Multiple components can be registered as "window movers".
 *
 * Components can be registered when the class is created. Additional components
 * can be added at any time using the registerComponent() method.
 */
public class WindowMover extends MouseAdapter {
  /**
   * The drag insets.
   */
  private Insets mDragInsets = new Insets(1, 1, 1, 1);

  /**
   * The snap size.
   */
  private Dimension mSnapSize = new Dimension(1, 1);

  /**
   * The edge insets.
   */
  private Insets mEdgeInsets = new Insets(0, 0, 0, 0);

  /**
   * The change cursor.
   */
  private boolean mChangeCursor = true;

  /**
   * The destination component.
   */
  private ModernWindow mWindow;

  /**
   * The source.
   */
  private Component mSource;

  /**
   * The pressed.
   */
  private Point mPressed;

  /**
   * The location.
   */
  private Point mLocation;

  /**
   * The original cursor.
   */
  private Cursor mOriginalCursor;

  /**
   * The potential drag.
   */
  private boolean mPotentialDrag;

  /**
   * Constructor to specify a parent component that will be moved when drag events
   * are generated on a registered child component.
   *
   * @param destinationComponent the component drage events should be forwareded
   *                             to
   * @param components           the Components to be registered for forwarding
   *                             drag events to the parent component to be moved
   */
  public WindowMover(ModernWindow destinationComponent, Component... components) {
    mWindow = destinationComponent;
    registerComponent(components);
  }

  /**
   * Get the change cursor property.
   *
   * @return the change cursor property
   */
  public boolean isChangeCursor() {
    return mChangeCursor;
  }

  /**
   * Set the change cursor property.
   *
   * @param changeCursor the new change cursor
   */
  public void setChangeCursor(boolean changeCursor) {
    mChangeCursor = changeCursor;
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
   * Set the drag insets. The insets specify an area where mouseDragged events
   * should be ignored and therefore the component will not be moved. This will
   * prevent these events from being confused with a MouseMotionListener that
   * supports component resizing.
   *
   * @param dragInsets the new drag insets
   */
  public void setDragInsets(Insets dragInsets) {
    mDragInsets = dragInsets;
  }

  /**
   * Get the bounds insets.
   *
   * @return the bounds insets
   */
  public Insets getEdgeInsets() {
    return mEdgeInsets;
  }

  /**
   * Set the edge insets. The insets specify how close to each edge of the parent
   * component that the child component can be moved. Positive values means the
   * component must be contained within the parent. Negative values means the
   * component can be moved outside the parent.
   *
   * @param edgeInsets the new edge insets
   */
  public void setEdgeInsets(Insets edgeInsets) {
    mEdgeInsets = edgeInsets;
  }

  /**
   * Remove listeners from the specified component.
   *
   * @param components the components
   */
  public void deregisterComponent(Component... components) {
    for (Component component : components) {
      component.removeMouseListener(this);
    }
  }

  /**
   * Add the required listeners to the specified component.
   *
   * @param components the components
   */
  public void registerComponent(Component... components) {
    for (Component component : components) {
      component.addMouseListener(this);
      component.addMouseMotionListener(this);
    }
  }

  /**
   * Get the snap size.
   *
   * @return the snap size
   */
  public Dimension getSnapSize() {
    return mSnapSize;
  }

  /**
   * Set the snap size. Forces the component to be snapped to the closest grid
   * position. Snapping will occur when the mouse is dragged half way.
   *
   * @param snapSize the new snap size
   */
  public void setSnapSize(Dimension snapSize) {
    if (snapSize.width < 1 || snapSize.height < 1) {
      throw new IllegalArgumentException("Snap sizes must be greater than 0");
    }

    mSnapSize = snapSize;
  }

  /**
   * Sets the up for dragging.
   *
   * @param e the new up for dragging
   */
  private void setupForDragging(MouseEvent e) {
    // mSource.addMouseMotionListener(this);
    mPotentialDrag = true;

    // Determine the component that will ultimately be moved

    mPressed = e.getLocationOnScreen();
    mLocation = mWindow.getLocation();

    if (mChangeCursor) {
      mOriginalCursor = mSource.getCursor();
      mSource.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
    }
  }

  /**
   * Setup the variables used to control the moving of the component:
   * 
   * source - the source component of the mouse event destination - the component
   * that will ultimately be moved pressed - the Point where the mouse was pressed
   * in the destination component coordinates.
   *
   * @param e the e
   */
  @Override
  public void mousePressed(MouseEvent e) {
    mSource = e.getComponent();

    int width = mSource.getSize().width - mDragInsets.left - mDragInsets.right;
    int height = mSource.getSize().height - mDragInsets.top - mDragInsets.bottom;

    Rectangle r = new Rectangle(mDragInsets.left, mDragInsets.top, width, height);

    if (r.contains(e.getPoint())) {
      setupForDragging(e);
    }
  }

  /**
   * Move the component to its new location. The dragged Point must be in the
   * destination coordinates.
   *
   * @param e the e
   */
  @Override
  public void mouseDragged(MouseEvent e) {
    Point dragged = e.getLocationOnScreen();

    int dragX = getDragDistance(dragged.x, mPressed.x, mSnapSize.width);
    int dragY = getDragDistance(dragged.y, mPressed.y, mSnapSize.height);

    int locationX = mLocation.x + dragX;
    int locationY = mLocation.y + dragY;

    // Mouse dragged events are not generated for every pixel the mouse
    // is moved. Adjust the location to make sure we are still on a
    // snap value.

    // while (locationX < edgeInsets.left)
    // locationX += snapSize.width;

    // while (locationY < edgeInsets.top)
    // locationY += snapSize.height;

    // Dimension d = getBoundingSize(destination);

    // while (locationX + destination.getSize().width + edgeInsets.right >
    // d.width)
    // locationX -= snapSize.width;

    // while (locationY + destination.getSize().height + edgeInsets.bottom >
    // d.height)
    // locationY -= snapSize.height;

    // Adjustments are finished, move the component

    // System.err.println("x y " + locationX + " " + locationY);

    mWindow.setLocation(locationX, locationY);
  }

  /**
   * Restore the original state of the Component.
   *
   * @param e the e
   */
  @Override
  public void mouseReleased(MouseEvent e) {
    if (mPotentialDrag) {
      // mSource.removeMouseMotionListener(this);
      mPotentialDrag = false;

      if (mChangeCursor) {
        mSource.setCursor(mOriginalCursor);
      }
    }
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
   * Determine how far the mouse has moved from where dragging started (Assume
   * drag direction is down and right for positive drag distance)
   */
  private int getDragDistance(int larger, int smaller, int snapSize) {
    int halfway = snapSize / 2;
    int drag = larger - smaller;
    drag += (drag < 0) ? -halfway : halfway;
    drag = (drag / snapSize) * snapSize;

    return drag;
  }

  /**
   * Gets the bounding size.
   *
   * @param source the source
   * @return the bounding size
   */
  /*
   * Get the bounds of the parent of the dragged component.
   */
  private Dimension getBoundingSize(Component source) {
    if (source instanceof Window) {
      GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
      Rectangle bounds = env.getMaximumWindowBounds();
      return new Dimension(bounds.width, bounds.height);
    } else {
      return source.getParent().getSize();
    }
  }
}