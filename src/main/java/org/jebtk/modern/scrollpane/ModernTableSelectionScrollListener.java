/**
 * Copyright (c) 2016, Antony Holmes
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

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.geom.IntRect;
import org.jebtk.modern.event.ModernSelectionListener;
import org.jebtk.modern.table.ModernTable;

/**
 * A specialized scroller for responding to vertical scroll keys. This only
 * instigates scrolling when the selection rectangle moves outside the view
 * window.
 *
 * @see ModernTableSelectionScrollEvent
 */
public class ModernTableSelectionScrollListener implements ModernSelectionListener {

  /** The m table. */
  private ModernTable mTable;

  /** The m V scrollbar. */
  private ModernScrollBar mVScrollbar;

  /** The m H scrollbar. */
  private ModernScrollBar mHScrollbar;

  /**
   * Instantiates a new modern table selection scroll listener.
   *
   * @param table      the table
   * @param vScrollbar the v scrollbar
   * @param hScrollbar the h scrollbar
   */
  public ModernTableSelectionScrollListener(ModernTable table, ModernScrollBar vScrollbar, ModernScrollBar hScrollbar) {
    mTable = table;
    mVScrollbar = vScrollbar;
    mHScrollbar = hScrollbar;
  }

  @Override
  public void selectionAdded(ChangeEvent e) {
    selectionRemoved(e);
  }

  @Override
  public void selectionRemoved(ChangeEvent e) {
    IntRect viewRect = mTable.getViewRect();

    int row = mTable.getSelectedRow();

    if (row != -1) {
      /*
       * int y = mTable.getRowModel().getCumOffset(row);
       * 
       * int height = mTable.getRowModel().getWidth(row);
       * 
       * if (y < viewRect.getY()) { double p = -mVScrollbar.normalize(height); // *
       * notches; mVScrollbar.incrementNormalizedScrollPosition(p); } else if (y +
       * height >= viewRect.getY() + viewRect.getH()) { double p =
       * mVScrollbar.normalize(height); // * Math.signum(notches); // * notches;
       * mVScrollbar.incrementNormalizedScrollPosition(p); } else { // Do nothing }
       */

      int y1 = mTable.getRowModel().getCumOffset(row);

      int height = mTable.getRowModel().getWidth(row);

      int y2 = y1 + height;

      int y = viewRect.getY();

      int h = viewRect.getH();

      int yb = y + h;

      if (mTable.getShowHeader()) {
        y1 += mTable.getColumnModel().getHeaderSize();
        y2 += mTable.getColumnModel().getHeaderSize();
      }

      // System.err.println("scroll h " + x1 + " " + x2 + " " + xr + " " + col +
      // " " +
      // viewRect);

      if (y1 < h) {
        mVScrollbar.setNormalizedScrollPosition(mVScrollbar.normalize(y1));
      } else if (y2 > yb) {
        mVScrollbar.setNormalizedScrollPosition(mVScrollbar.normalize(y2));
      }
    }

    int col = mTable.getSelectedCol();

    if (col != -1) {
      /*
       * int x = mTable.getColumnModel().getCumOffset(col);
       * 
       * int width = mTable.getColumnModel().getWidth(col);
       * 
       * //System.err.println("scroll h " + x + " " + viewRect);
       * 
       * if (x < viewRect.getX()) { double p = -mHScrollbar.normalize(width); // *
       * notches; mHScrollbar.incrementNormalizedScrollPosition(p); } else if (x +
       * width >= viewRect.getX() + viewRect.getW()) { double p =
       * mHScrollbar.normalize(width); // * Math.signum(notches); // * notches;
       * mHScrollbar.incrementNormalizedScrollPosition(p); } else { // Do nothing }
       */

      int x1 = mTable.getColumnModel().getCumOffset(col);

      int width = mTable.getColumnModel().getWidth(col);

      int x2 = x1 + width;

      int x = viewRect.getX();

      int w = viewRect.getW();

      int xr = x + w;

      if (mTable.getShowRowHeader()) {
        x1 += mTable.getRowModel().getHeaderSize();
        x2 += mTable.getRowModel().getHeaderSize();
      }

      // System.err.println("scroll h " + x1 + " " + x2 + " " + xr + " " + col +
      // " " +
      // viewRect);

      if (x1 < w) {
        mHScrollbar.setNormalizedScrollPosition(mHScrollbar.normalize(x1));
      } else if (x2 > xr) {
        mHScrollbar.setNormalizedScrollPosition(mHScrollbar.normalize(x2));
      }
    }
  }
}
