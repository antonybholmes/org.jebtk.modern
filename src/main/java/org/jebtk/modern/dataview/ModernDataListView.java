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
package org.jebtk.modern.dataview;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;

import org.jebtk.core.geom.IntPos2D;
import org.jebtk.core.geom.IntRect;

// TODO: Auto-generated Javadoc
/**
 * Represents a list view of a grid similar to the list view on a file explorer.
 * Items are rendered row first.
 * 
 * @author Antony Holmes
 *
 */
public class ModernDataListView extends ModernDataGridView {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new modern data list view.
   */
  public ModernDataListView() {
    super();

    setCellRenderer(new ModernDataListCellRenderer());

    setCellSize(200, 40);
  }

  /**
   * Instantiates a new modern data list view.
   *
   * @param renderer the renderer
   */
  public ModernDataListView(ModernDataCellRenderer renderer) {
    super();

    setCellRenderer(renderer);

    setCellSize(200, 40);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataGridView#adjustSize()
   */
  @Override
  public void adjustSize() {
    if (mModel == null) {
      return;
    }

    if (mModel.getRowCount() == 0) {
      return;
    }

    mRows = getHeight() / mCellSize.height;

    if (mRows == 0) {
      return;
    }

    mCols = mModel.getRowCount() / mRows;

    if (mModel.getRowCount() % mRows != 0) {
      ++mCols;
    }

    int width = mCols * mCellSize.width;
    int height = mRows * mCellSize.height;

    setCanvasSize(new Dimension(width, height));

    refreshView();
  }

  /**
   * Provides a standard method for rendering the cells. This method ensures only
   * visible cells are rendered, which is improves drawing time since it wastes a
   * lot of effort drawing cells no one can see.
   *
   * @param g2 the g2
   */
  @Override
  public final void createImage(Graphics2D g2) {

    ModernDataSelection visibleCells = calculateVisibleCells();

    ModernDataCellRenderer renderer;

    Graphics2D g2Table = (Graphics2D) g2.create();

    // translate to the start of the rendering rectangle so that we skip
    // all non visible cells
    // translate(g2Table);

    for (int i = visibleCells.getStartCol(); i <= visibleCells.getEndCol(); ++i) {
      if (i >= getColumnCount()) {
        break;
      }

      int index = i * getRowCount();

      int x = 0;

      for (int j = 0; j < getRowCount(); ++j) {
        if (index >= mModel.getRowCount()) {
          break;
        }

        renderer = mCellRendererModel.get(i, j);

        Component c = renderer.getCellRendererComponent(this, getValueAt(index, 0), index == mHighlightCellIndex,
            mSelectionModel.contains(index, 0), this.isFocusOwner(), index, 0);

        c.setSize(mCellSize);

        c.print(g2Table);

        // Move to the next cell location.
        g2Table.translate(0, mCellSize.height);

        x += mCellSize.height;

        ++index;
      }

      // Each time we start a new row, translate back to the X origin.
      g2Table.translate(mCellSize.width, -x);
    }

    g2Table.dispose();
  }

  /**
   * Calculates the range of cells visible at any given time.
   *
   * @return the modern data selection
   */
  @Override
  protected ModernDataSelection calculateVisibleCells() {

    IntRect viewRectangle = getViewRect();

    int startRow = 0;
    int endRow = getRowCount() - 1;

    // min row

    double t = viewRectangle.getX();

    int startCol = (int) (t / mCellSize.width);

    // max row

    t += viewRectangle.getW();

    int endCol = (int) (t / mCellSize.width) + 1;

    return new ModernDataSelection(startRow, endRow, startCol, endCol);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataGridView#getCell(int)
   */
  @Override
  protected ModernDataCell getCell(int index) {
    if (index == -1) {
      return null;
    }

    int c = index / mRows;
    int r = index - c * mRows;

    return new ModernDataCell(r, c);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.dataview.ModernDataGridView#getIndex(java.awt.Point)
   */
  @Override
  protected int getIndex(IntPos2D p) {
    if (p.getX() / mCellSize.width > mCols) {
      return -1;
    }

    if (p.getY() / mCellSize.height > mRows) {
      return -1;
    }

    return (int) (p.getY() / mCellSize.height + p.getX() / mCellSize.width * mRows);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernData#getIndex(int, int)
   */
  @Override
  protected int getIndex(int row, int col) {
    if (col >= mCols) {
      return -1;
    }

    if (row >= mRows) {
      return -1;
    }

    return col * mRows + row;
  }
}
