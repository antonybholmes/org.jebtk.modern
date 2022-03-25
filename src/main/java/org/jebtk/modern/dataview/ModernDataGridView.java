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
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.jebtk.core.geom.IntPos2D;
import org.jebtk.core.geom.IntRect;
import org.jebtk.modern.SelectionPolicy;
import org.jebtk.modern.graphics.DrawingContext;

// TODO: Auto-generated Javadoc
/**
 * Provides a grid view of data in the data model.
 *
 * @author Antony Holmes
 */
public class ModernDataGridView extends ModernData {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member renderer.
   */
  // protected ModernDataCellRenderer mRenderer = null;

  /**
   * The member cell size.
   */
  protected Dimension mCellSize = new Dimension(0, 0);

  /**
   * The member highlight cell index.
   */
  protected int mHighlightCellIndex = -1;

  // private boolean multiItemSelect = false;

  // private boolean multiRangeSelect = false;

  /**
   * The Class MouseEvents.
   */
  private class MouseEvents extends MouseAdapter {
    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseMoved(MouseEvent e) {
      int i = getIndex(translateCoordinate(e));

      if (i == -1 || i == mHighlightCellIndex) {
        return;
      }

      mHighlightCellIndex = i;

      repaint();
    }
  }

  /**
   * Instantiates a new modern data grid view.
   */
  public ModernDataGridView() {
    addMouseListener(new MouseEvents());
  }

  /**
   * Adjust the size of the table image.
   */
  @Override
  public void adjustSize() {

    // If there is no model or no rows, there is no point adjusting
    // anything
    if (mModel == null || mModel.getRowCount() == 0) {
      return;
    }

    mCols = getWidth() / mCellSize.width;

    if (mCols == 0) {
      return;
    }

    mRows = mModel.getRowCount() / mCols;

    if (mModel.getRowCount() % mCols != 0) {
      ++mRows;
    }

    int width = mCols * mCellSize.width;
    int height = mRows * mCellSize.height;

    setCanvasSize(width, height);

    refreshView();
  }

  /**
   * Gets the column count.
   *
   * @return the column count
   */
  public int getColumnCount() {
    return mCols;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernData#getRowCount()
   */
  public int getRowCount() {
    return mRows;
  }

  /**
   * Returns the cumulative distance from 0 of a particular col.
   *
   * @param col the col
   * @return the x
   */
  @Override
  public int getX(int col) {

    return mCellSize.width * col;
  }

  /**
   * Returns the cumulative distance from 0 of a particular row.
   *
   * @param row the row
   * @return the y
   */
  @Override
  public int getY(int row) {
    return mCellSize.height * row;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernData#getCol(int)
   */
  @Override
  public int getCol(int x) {
    return x / mCellSize.width;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernData#getRow(int)
   */
  @Override
  public int getRow(int y) {
    return y / mCellSize.height;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvas#drawForegroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void rasterCanvas(Graphics2D g2, DrawingContext context) {
    if (mModel == null) {
      return;
    }

    if (mModel.getRowCount() == 0 || mModel.getColCount() == 0) {
      return;
    }

    createImage(g2);
  }

  /**
   * Provides a standard method for rendering the cells. This method ensures only
   * visible cells are rendered, which is improves drawing time since it wastes a
   * lot of effort drawing cells no one can see.
   *
   * @param g2 the g2
   */
  public void createImage(Graphics2D g2) {
    ModernDataCellRenderer renderer;

    Graphics2D g2Table = (Graphics2D) g2.create();

    ModernDataSelection visibleCells = calculateVisibleCells();

    // translate to the start of the rendering rectangle so that we skip
    // all non visible cells
    // translate(g2Table);

    for (int i = visibleCells.getStartRow(); i <= visibleCells.getEndRow(); ++i) {
      if (i >= getRowCount()) {
        break;
      }

      int index = i * getColumnCount();

      int x = 0;

      for (int j = 0; j < getColumnCount(); ++j) {
        if (index >= mModel.getRowCount()) {
          break;
        }

        renderer = mCellRendererModel.get(i, j);

        Component c = renderer.getCellRendererComponent(this, getValueAt(index, 0), index == mHighlightCellIndex,
            mSelectionModel.contains(index, 0), this.isFocusOwner(), index, 0);

        c.setSize(mCellSize);

        c.print(g2Table);

        // Move to the next cell location.
        g2Table.translate(mCellSize.width, 0);

        x += mCellSize.width;

        ++index;
      }

      // Each time we start a new row, translate back to the X origin.
      g2Table.translate(-x, mCellSize.height);
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

    int startCol = 0;
    int endCol = getColumnCount() - 1;

    // min row

    double t = viewRectangle.getY();

    int startRow = (int) (t / mCellSize.height);

    // max row

    t += viewRectangle.getH();

    int endRow = (int) (t / mCellSize.height) + 1;

    return new ModernDataSelection(startRow, endRow, startCol, endCol);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernData#getCell(java.awt.Point)
   */
  @Override
  protected ModernDataCell getCell(IntPos2D p) {
    if (p.getX() / mCellSize.width > mCols) {
      return null;
    }

    if (p.getY() / mCellSize.height > mRows) {
      return null;
    }

    int r = (int) (p.getY() / mCellSize.height);
    int c = (int) (p.getX() / mCellSize.width);

    // System.err.println(r + " " + c);

    return new ModernDataCell(r, c);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernData#getCell(int)
   */
  @Override
  protected ModernDataCell getCell(int index) {
    if (index == -1) {
      return null;
    }

    int r = index / mCols;
    int c = index - r * mCols;

    return new ModernDataCell(r, c);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernData#getIndex(java.awt.Point)
   */
  @Override
  protected int getIndex(IntPos2D p) {
    if (p.getX() / mCellSize.width > mCols) {
      return -1;
    }

    if (p.getY() / mCellSize.height > mRows) {
      return -1;
    }

    return (int) (p.getY() / mCellSize.height * mCols + p.getX() / mCellSize.width);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernData#getPoint(int)
   */
  protected Point getPoint(int index) {
    return getPoint(getCell(index));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernData#getPoint(int, int)
   */
  @Override
  protected Point getPoint(int row, int col) {
    return new Point(col * mCellSize.width, row * mCellSize.height);
  }

  /**
   * Selects a cell if the mouse is over a valid cell when clicked.
   *
   * @param row              the row
   * @param col              the col
   * @param multiItemSelect  the multi item select
   * @param multiRangeSelect the multi range select
   */
  @Override
  public void setSelectedCell(int row, int col, boolean multiItemSelect, boolean multiRangeSelect) {
    int index = getIndex(row, col);

    if (index == -1) {
      return;
    }

    if ((!multiItemSelect && !multiRangeSelect) || mSelectionPolicy != SelectionPolicy.MULTIPLE) {
      // If we are not selecting multiple cells, clear
      // the current selection
      mSelectionModel.clear();
    }

    if (multiItemSelect) {
      if (mSelectionModel.getRowSelectionModel().contains(index)) {
        // If we are multi selecting and pick something we have
        // already selected, then remove it.

        mSelectionModel.getRowSelectionModel().remove(index);
      } else {
        mSelectionModel.getRowSelectionModel().add(index);
      }
    } else if (multiRangeSelect && mSelectionModel.getRowSelectionModel().size() > 0) {
      // since we are adding intermediates, we do not
      // add the current first and last in duplicate,
      // hence the index begins one past the min and
      // ends one before the max
      mSelectionModel.getRowSelectionModel().setSelectionInterval(mSelectionModel.getRowSelectionModel().first(),
          index);
    } else {
      mSelectionModel.getRowSelectionModel().add(index);
    }

    mSelectionModel.getColumnSelectionModel().add(0);

    updateSelectedCell();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernData#adjustEditor()
   */
  @Override
  protected final void adjustEditor() {
    if (mCurrentEditorComponent == null) {
      return;
    }

    ModernDataCell cell = mSelectionModel.last();

    Point p = getPoint(cell);

    p.x -= getViewRect().getX();
    p.y -= getViewRect().getY();

    Rectangle b = new Rectangle(getInsets().left + p.x, getInsets().top + p.y, mCellSize.width, mCellSize.height);

    mCurrentEditorComponent.setBounds(b);
  }

  /**
   * Gets the selected column.
   *
   * @return the selected column
   */
  public int getSelectedColumn() {
    return mSelectionModel.getSelectedCol();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernData#getSelectedRow()
   */
  public int getSelectedRow() {
    return mSelectionModel.getSelectedRow();
  }

  /**
   * Key pressed.
   *
   * @param e the e
   */
  public void keyPressed(KeyEvent e) {
    if (e.isControlDown() || e.isMetaDown()) {
      switch (e.getKeyCode()) {
      case KeyEvent.VK_A:
        selectAll();

        return;
      case KeyEvent.VK_C:
        copy();

        return;
      default:
        // Do nothing
      }
    }

    if (mSelectionPolicy != SelectionPolicy.MULTIPLE) {
      return;
    }
  }

  /**
   * Selects all cells or rows depending on the table type.
   */
  public void selectAll() {
    mSelectionModel.getRowSelectionModel().setSelectionInterval(0, mModel.getRowCount() - 1);
    mSelectionModel.getColumnSelectionModel().setSelection(0);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.dataview.ModernData#componentResized(java.awt.event.
   * ComponentEvent)
   */
  public void componentResized(ComponentEvent e) {
    adjustSize();

    super.componentResized(e);
  }

  /**
   * Key released.
   *
   * @param e the e
   */
  public void keyReleased(KeyEvent e) {

  }

  /**
   * Key typed.
   *
   * @param e the e
   */
  public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub

  }

  /**
   * Returns the value at a given row, col on the table. Since the table may be
   * sorted, getValue may return a different value to getModel().getValue() since
   * that returns the ordering of the data as it was loaded.
   *
   * @param i the i
   * @param j the j
   * @return the value at
   */
  public Object getValueAt(int i, int j) {
    if (mModel == null) {
      return null;
    }

    return mModel.getValueAt(i, j);
  }

  /**
   * Returns the value at a given row, col on the table. Since the table may be
   * sorted, getValue may return a different value to getModel().getValue() since
   * that returns the ordering of the data as it was loaded.
   *
   * @param i     the i
   * @param j     the j
   * @param value the value
   */
  public void setValueAt(int i, int j, Object value) {
    if (mModel == null) {
      return;
    }

    mModel.setValueAt(i, j, value);
  }

  /**
   * Converts a row to that of the model. If a sort model is in use, this method
   * will map the selected row to the actual row it corresponds to in the unsorted
   * model.
   *
   * @param i the i
   * @return the model row index
   */
  public int getModelRowIndex(int i) {
    return i; // sortModel.getSelectedSorter().getUnsortedRow(i);
  }

  /**
   * Gets the original row.
   *
   * @param row the row
   * @return the original row
   */
  public int getOriginalRow(int row) {
    return row;
  }

  /**
   * Set the renderer which determines how grid/list items appear.
   *
   * @param renderer the new cell renderer
   */
  public void setCellRenderer(ModernDataCellRenderer renderer) {
    getRendererModel().setDefault(renderer);
  }

  /**
   * Sets the cell size.
   *
   * @param dimension the new cell size
   */
  public void setCellSize(Dimension dimension) {
    if (dimension == null) {
      return;
    }

    setCellSize(dimension.width, dimension.height);
  }

  /**
   * Sets the cell size.
   *
   * @param width  the width
   * @param height the height
   */
  public void setCellSize(int width, int height) {
    mCellSize.width = width;
    mCellSize.height = height;

    adjustSize();
  }

}
