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
package org.jebtk.modern.panel;

import java.awt.LayoutManager;

import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.ModernWidget;

/**
 * Layout components on a regular grid.
 * 
 * @author Antony Holmes
 *
 */
public class MatrixPanel extends ModernWidget {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  // private int[] mRowHeights = null;

  // private int[] mColumnWidths= null;

  // private int mXSep; // = new CopyOnWriteArrayList<Integer>();

  // private int mYSep; // = new CopyOnWriteArrayList<Integer>();

  // private List<Component> components = new CopyOnWriteArrayList<Component>();

  /**
   * The row objects map.
   */
  // private List<List<Component>> mRowObjectsMap = new
  // CopyOnWriteArrayList<List<Component>>();

  /**
   * The member preferred width.
   */
  // private int mPreferredWidth = 0;

  /**
   * The member centre vertically.
   */
  // private boolean mCentreVertically = false;

  /** The m size. */
  // private Dimension mSize = ModernWidget.SQUARE_WIDGET_SIZE;

  /**
   * Instantiates a new matrix panel.
   *
   * @param rowHeights   the row heights
   * @param columnWidths the column widths
   */
  public MatrixPanel(int[] rowHeights, int[] columnWidths) {
    this(rowHeights, columnWidths, 0, 0);
  }

  /**
   * Instantiates a new matrix panel.
   *
   * @param rowHeights   the row heights
   * @param columnWidths the column widths
   * @param xSep         the x sep
   * @param ySep         the y sep
   */
  public MatrixPanel(int[] rowHeights, int[] columnWidths, int xSep, int ySep) {
    super.setLayout(new MatrixLayout(rowHeights, columnWidths, xSep, ySep));

    // mRowHeights = rowHeights;
    // mColumnWidths = columnWidths;

    // mXSep = xSep;
    // mYSep = ySep;

    // setPreferredWidth();
  }

  /**
   * Determines whether to layout components centred vertically.
   *
   * @param centreVertically the new centre vertically
   */
  public final void setCentreVertically(boolean centreVertically) {
    // mCentreVertically = centreVertically;

    // layoutComponents();
  }

  /*
   * private void setPreferredWidth() { if (mColumnWidths == null) { return; }
   * 
   * mPreferredWidth = 0;
   * 
   * // width of all columns plus the gap in between plus the insets
   * 
   * for (int i : mColumnWidths) { mPreferredWidth += i; }
   * 
   * for (int i = 0; i < mColumnWidths.length - 1; ++i) { mPreferredWidth +=
   * mXSep; }
   * 
   * mPreferredWidth += getInsets().left + getInsets().right; }
   */

  /*
   * public MatrixPanel(int[] rowHeights, int[] columnWidths, int[] xSep, int[]
   * ySep) { super();
   * 
   * super.setLayout(null);
   * 
   * this.rowHeights = rowHeights; this.columnWidths = columnWidths;
   * 
   * int w = getInsets().left; int h = getInsets().bottom;
   * 
   * for (int i : rowHeights) { h += i; }
   * 
   * for (int i : columnWidths) { w += i; }
   * 
   * for (int i : xSep) { this.xSep.add(i); w += i; }
   * 
   * for (int i : ySep) { this.ySep.add(i); h += i; }
   * 
   * w += getInsets().right; h += getInsets().top;
   * 
   * setCanvasSize(new Dimension(w, h)); }
   */

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.Container#setLayout(java.awt.LayoutManager)
   */
  @Override
  public final void setLayout(LayoutManager manager) {
    // super.setLayout(manager);
  }

  // public final void add(Component component, Object constraints) {
  // add(component);
  // }

  /*
   * public final Component add(Component component) { super.add(component);
   * 
   * int i = mRowObjectsMap.size() - 1;
   * 
   * if (mRowObjectsMap.size() == 0 || mRowObjectsMap.get(i).size() ==
   * mColumnWidths.length) { addRow(); ++i; }
   * 
   * mRowObjectsMap.get(i).add(component);
   * 
   * layoutComponents();
   * 
   * refresh();
   * 
   * return component; }
   */

  // public void clear() {
  // removeAll();
  // mRowObjectsMap.clear();
  // }

  /*
   * public final void removeRow(int row) { if (row < 0 || row >
   * mRowObjectsMap.size() - 1) { return; }
   * 
   * for (Component c : mRowObjectsMap.get(row)) { super.remove(c); }
   * 
   * mRowObjectsMap.remove(row);
   * 
   * layoutComponents();
   * 
   * refresh(); }
   */

  /*
   * private void refresh() { revalidate(); repaint(); }
   */

  /*
   * public final void addRow() { mRowObjectsMap.add(new
   * CopyOnWriteArrayList<Component>()); }
   */

  /*
   * public final void layoutComponents() { if (mRowHeights == null ||
   * mRowObjectsMap.size() == 0) { // setBorder is called before initialization so
   * // put a check here return; }
   * 
   * int x = getInsets().left; int y = getInsets().top;
   * 
   * if (mCentreVertically) { int rows = mRowObjectsMap.size();
   * 
   * int h = (rows - 1) * mYSep;
   * 
   * for (int i = 0; i < rows; ++i) { h += mRowHeights[i % mRowHeights.length]; }
   * 
   * y += (getHeight() - getInsets().top - getInsets().bottom - h) / 2; }
   * 
   * for (int row = 0; row < mRowObjectsMap.size(); ++row) { x = getInsets().left;
   * 
   * int r = row % mRowHeights.length;
   * 
   * for (int col = 0; col < mRowObjectsMap.get(row).size(); ++col) { int w =
   * mColumnWidths[col]; int h = mRowHeights[r];
   * 
   * mRowObjectsMap.get(row).get(col).setBounds(x, y, w, h);
   * 
   * if (col == mColumnWidths.length - 1) { y += mRowHeights[r] + mYSep; } else {
   * x += mColumnWidths[col] + mXSep; } } }
   * 
   * // if the last row was not full, add the extra // row height it occupies if
   * (mRowObjectsMap.get(mRowObjectsMap.size() - 1).size() % mColumnWidths.length
   * != 0) { y += mRowHeights[(mRowObjectsMap.size() - 1) % mRowHeights.length]; }
   * 
   * y += getInsets().bottom;
   * 
   * mSize = new Dimension(mPreferredWidth + 1, y);
   * 
   * setPreferredSize(mSize); setMinimumSize(mSize); }
   */

  /*
   * public final void setBorder(Border border) { super.setBorder(border);
   * 
   * setPreferredWidth();
   * 
   * layoutComponents(); }
   */

  /**
   * Adds the empty.
   */
  public void addEmpty() {
    add(new ModernComponent());
  }

  // @Override
  // public Dimension getMinimumSize() {
  // return mSize;
  // }

  // @Override
  // public Dimension getPreferredSize() {
  // return mSize;
  // }

  // @Override
  // public Dimension getMaximumSize() {
  // return mSize;
  // }
}
