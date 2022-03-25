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
package org.jebtk.modern.splitpane;

import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.jebtk.core.Mathematics;

/**
 * Horizonal split pane i.e. add a vertical line between two panels.
 * 
 * @author Antony Holmes
 *
 */
public class HSplitPane extends SplitPane {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member start x.
   */
  private int mStartX;

  /**
   * The member div start x.
   */
  private double mDivStartX;

  /**
   * The member w.
   */
  private int mW;

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
      setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public final void mousePressed(MouseEvent e) {
      if (!mResizable) {
        return;
      }

      mDragDividerIndex = getIsDraggable(e);

      if (mDragDividerIndex != -1) {
        mStartX = e.getX();
        mDivStartX = mDividerLocations.get(mDragDividerIndex);
      }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
     */
    @Override
    public final void mouseReleased(MouseEvent e) {
      mDragDividerIndex = -1;
      mStartX = -1;
      mDivStartX = -1;
      setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.
     * MouseEvent)
     */
    @Override
    public void mouseDragged(MouseEvent e) {
      if (mDragDividerIndex == -1) {
        return;
      }

      double r = mDivStartX + ((double) e.getX() - mStartX) / mInternalRect.getW();

      r = Math.max(mMinSize, r);

      if (mDragDividerIndex == 0) {
        r = Math.max(mMinSize, r);
      } else {
        r = Math.max(mDividerLocations.get(mDragDividerIndex - 1) + mMinSize, r);
      }

      // The last divider is ignored because the last component is glued
      // to the right side of the screen so its divider location is
      // ignored.
      if (mDragDividerIndex == mDividerLocations.size() - 2) {
        r = Math.min(1.0 - mMinSize, r);
      } else {
        r = Math.min(mDividerLocations.get(mDragDividerIndex + 1) - mMinSize, r);
      }

      r = Mathematics.bound(r, 0, 1);

      mDividerLocations.set(mDragDividerIndex, r);

      resize();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseMoved(MouseEvent e) {
      if (!mResizable) {
        return;
      }

      setCursor(Cursor.getPredefinedCursor(getIsDraggable(e) != -1 ? Cursor.W_RESIZE_CURSOR : Cursor.DEFAULT_CURSOR));
    }
  }

  /**
   * Instantiates a new h split pane.
   */
  public HSplitPane() {
    MouseEvents me = new MouseEvents();

    addMouseListener(me);
    addMouseMotionListener(me);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.splitpane.AbstractSplitPane#resize()
   */
  @Override
  public void resize() {
    // The amount of space to distribute is the width less the space
    // occupied by all of the dividers
    mW = getInternalRect().getW();

    int x = 0;
    int x2 = x + mW;
    int h = mInternalRect.getH();

    // mW -= (mComponents.size() - 1) * mDividerWidth;

    for (int i = 0; i < mComponents.size() - 1; ++i) {
      int cw = (int) (mW * mDividerLocations.get(i));

      Rectangle b = new Rectangle(x + getInsets().left, getInsets().top, cw - x - mDividerHalfWidth, h);

      mComponents.get(i).setBounds(b);

      x += cw + mDividerHalfWidth + 1;
    }

    if (!mComponents.isEmpty()) {
      // process the last one

      Rectangle b = new Rectangle(x, getInsets().top, x2 - x - 1, h);

      mComponents.get(mComponents.size() - 1).setBounds(b);
    }

    refresh();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.splitpane.AbstractSplitPane#getIsDraggable(java.awt.
   * event.MouseEvent)
   */
  @Override
  public int getIsDraggable(MouseEvent e) {
    if (!mResizable) {
      return -1;
    }

    int x; // = getInsets().left;
    int rx = e.getX();

    int dragDivider = -1;

    for (int i = 0; i < mDividerLocations.size() - 1; ++i) {
      x = getInsets().left + (int) (mW * mDividerLocations.get(i));

      if (Math.abs(rx - x) <= mDividerHalfWidth) {
        dragDivider = i;
        break;
      }
    }

    return dragDivider;
  }

}
