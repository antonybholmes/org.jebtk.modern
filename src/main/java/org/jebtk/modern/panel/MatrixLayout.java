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

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

/**
 * The class SimpleMatrixPanel.
 */
public class MatrixLayout implements LayoutManager {

  /**
   * The row heights.
   */
  private int[] mRowHeights = null;

  /**
   * The column width.
   */
  private int[] mColumnWidths;

  /**
   * The x sep.
   */
  private int mXSep; // = new CopyOnWriteArrayList<Integer>();

  /**
   * The y sep.
   */
  private int mYSep; // = new CopyOnWriteArrayList<Integer>();

  public MatrixLayout(int[] rowHeights, int[] columnWidths) {
    this(rowHeights, columnWidths, 0, 0);
  }

  /**
   * Instantiates a new simple matrix panel.
   *
   * @param rowHeights  the row heights
   * @param columnWidth the column width
   * @param xSep        the x sep
   * @param ySep        the y sep
   */
  public MatrixLayout(int[] rowHeights, int[] columnWidths, int xSep, int ySep) {
    mRowHeights = rowHeights;
    mColumnWidths = columnWidths;

    mXSep = xSep;
    mYSep = ySep;
  }

  @Override
  public void addLayoutComponent(String name, Component comp) {
    // TODO Auto-generated method stub

  }

  @Override
  public void layoutContainer(Container parent) {
    Insets insets = parent.getInsets();
    int nComps = parent.getComponentCount();
    int x = insets.left;
    int y = insets.top;

    int r = 0;
    int c = 0;

    int h = mRowHeights[r];

    Component comp;

    for (int i = 0; i < nComps; i++) {
      int w = mColumnWidths[c];

      comp = parent.getComponent(i);

      comp.setBounds(x, y, w, h);

      ++c;

      if (c == mColumnWidths.length) {
        c = 0;
        ++r;
        x = insets.left;
        y += h + mYSep;
        h = mRowHeights[r % mRowHeights.length];
      } else {
        x += w + mXSep;
      }
    }
  }

  @Override
  public Dimension minimumLayoutSize(Container parent) {
    return size(parent);
  }

  @Override
  public Dimension preferredLayoutSize(Container parent) {
    return size(parent);
  }

  private Dimension size(Container parent) {
    int w = 0;

    for (int i = 0; i < mColumnWidths.length; ++i) {
      w += mColumnWidths[i];
    }

    w += mXSep * (mColumnWidths.length - 1);

    int nComps = parent.getComponentCount();

    int rows = nComps / mColumnWidths.length + 1;

    int h = 0;

    for (int i = 0; i < rows; ++i) {
      h += mRowHeights[i % mRowHeights.length];
    }

    h += mYSep * (rows - 1);

    return new Dimension(w, h);
  }

  @Override
  public void removeLayoutComponent(Component comp) {
    // TODO Auto-generated method stub

  }
}
