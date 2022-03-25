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

import java.util.ArrayList;
import java.util.List;

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.modern.SelectionRangeModel;
import org.jebtk.modern.event.ModernSelectionListener;
import org.jebtk.modern.event.ModernSelectionListeners;

/**
 * A 2D selection model for coping with row and column selections.
 * 
 * @author Antony Holmes
 *
 */
public class ModernDataCellsSelectionModel extends ModernSelectionListeners implements ModernSelectionListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The rows selection model.
   */
  private SelectionRangeModel mRowsSelectionModel = new SelectionRangeModel();

  /**
   * The columns selection model.
   */
  private SelectionRangeModel mColumnsSelectionModel = new SelectionRangeModel();

  /**
   * Instantiates a new modern data cells selection model.
   */
  public ModernDataCellsSelectionModel() {
    mRowsSelectionModel.addSelectionListener(this);
    mColumnsSelectionModel.addSelectionListener(this);
  }

  /**
   * Gets the row selection model.
   *
   * @return the row selection model
   */
  public SelectionRangeModel getRowSelectionModel() {
    return mRowsSelectionModel;
  }

  /**
   * Gets the column selection model.
   *
   * @return the column selection model
   */
  public SelectionRangeModel getColumnSelectionModel() {
    return mColumnsSelectionModel;
  }

  /**
   * Clear.
   */
  public void clear() {
    mRowsSelectionModel.clear();
    mColumnsSelectionModel.clear();
  }

  /**
   * First.
   *
   * @return the modern data cell
   */
  public ModernDataCell first() {
    if (mRowsSelectionModel.size() == 0 || mColumnsSelectionModel.size() == 0) {
      return null;
    }

    return new ModernDataCell(mRowsSelectionModel.first(), mColumnsSelectionModel.first());
  }

  /**
   * Last.
   *
   * @return the modern data cell
   */
  public ModernDataCell last() {
    // System.err.println("last " + rowsSelectionModel.size() + " " +
    // columnsSelectionModel.size());

    if (mRowsSelectionModel.size() == 0 || mColumnsSelectionModel.size() == 0) {
      return null;
    }

    return new ModernDataCell(mRowsSelectionModel.last(), mColumnsSelectionModel.last());
  }

  /**
   * Returns a list of row column order cells that are selected in the table.
   *
   * @return the selected cells
   */
  public List<ModernDataCell> getSelectedCells() {
    List<ModernDataCell> cells = new ArrayList<ModernDataCell>();

    if (mRowsSelectionModel.size() == 0 || mColumnsSelectionModel.size() == 0) {
      return cells;
    }

    for (int i : mRowsSelectionModel) {
      for (int j : mColumnsSelectionModel) {
        cells.add(new ModernDataCell(i, j));
      }
    }

    return cells;
  }

  /**
   * Size.
   *
   * @return the int
   */
  public int size() {
    return mRowsSelectionModel.size() * mColumnsSelectionModel.size();
  }

  /**
   * Gets the selected col.
   *
   * @return the selected col
   */
  public int getSelectedCol() {
    if (mColumnsSelectionModel.size() == 0) {
      return -1;
    }

    return mColumnsSelectionModel.last();
  }

  /**
   * Gets the selected row.
   *
   * @return the selected row
   */
  public int getSelectedRow() {
    if (mRowsSelectionModel.size() == 0) {
      return -1;
    }

    return mRowsSelectionModel.last();
  }

  /**
   * Returns true if the cell at the row and column is within the selection.
   *
   * @param row    the row
   * @param column the column
   * @return true, if successful
   */
  public boolean contains(int row, int column) {
    return mRowsSelectionModel.contains(row) && mColumnsSelectionModel.contains(column);
  }

  /**
   * Sets the modern selection.
   *
   * @param rs the rs
   * @param re the re
   * @param cs the cs
   * @param ce the ce
   */
  public void setSelection(int rs, int re, int cs, int ce) {
    mRowsSelectionModel.setSelectionInterval(rs, re);
    mColumnsSelectionModel.setSelectionInterval(cs, ce);
  }

  /**
   * Sets the modern selection.
   *
   * @param r the r
   * @param c the c
   */
  public void setSelection(int r, int c) {
    mRowsSelectionModel.setSelection(r);
    mColumnsSelectionModel.setSelection(c);
  }

  /**
   * Adds the.
   *
   * @param r the r
   * @param c the c
   */
  public void add(int r, int c) {
    mRowsSelectionModel.add(r);
    mColumnsSelectionModel.add(c);
  }

  @Override
  public void selectionAdded(ChangeEvent e) {
    fireSelectionAdded(new ChangeEvent(this));
  }

  @Override
  public void selectionRemoved(ChangeEvent e) {
    fireSelectionRemoved(new ChangeEvent(this));
  }

}
