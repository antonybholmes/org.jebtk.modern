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
package org.jebtk.modern.table;

import java.awt.Graphics2D;

import org.jebtk.modern.SelectionRangeModel;
import org.jebtk.modern.dataview.ModernDataModel;
import org.jebtk.modern.dataview.ModernDataSelection;
import org.jebtk.modern.graphics.ImageUtils;
import org.jebtk.modern.theme.ModernTheme;

// TODO: Auto-generated Javadoc
/**
 * Provides similar functionality to the JTable.
 *
 * @author Antony Holmes
 *
 */
public class ModernRowTable extends ModernTable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new modern row table.
   */
  public ModernRowTable() {
    setup();
  }

  /**
   * Instantiates a new modern row table.
   *
   * @param model the model
   */
  public ModernRowTable(ModernDataModel model) {
    setModel(model);

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    setShowHeader(true);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvas#drawForegroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  protected void createGridLines(Graphics2D g2, ModernDataSelection visibleCells) {
    // Do nothing as we don't want the grid lines
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernData#selectAll()
   */
  @Override
  public void selectAll() {
    super.selectAll();

    selectRows();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.table.ModernTable#setSelectedCell(int, int,
   * boolean, boolean)
   */
  @Override
  public void setSelectedCell(int row, int column, boolean multiSelect, boolean multiRangeSelect) {
    super.setSelectedCell(row, column, multiSelect, multiRangeSelect);

    selectRows();
  }

  /**
   * Select rows.
   */
  private void selectRows() {
    mRowModel.unselectAll();

    for (int i : mSelectionModel.getRowSelectionModel()) {
      mRowModel.setSelected(i);
    }
  }

  /**
   * Gets the selection model.
   *
   * @return the selection model
   */
  public SelectionRangeModel getSelectionModel() {
    return getCellSelectionModel().getRowSelectionModel();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.table.ModernTable#drawSelectionRow(java.awt.Graphics2D,
   * org.abh.common.ui.dataview.ModernDataSelection)
   */
  @Override
  public void drawSelectionRow(Graphics2D g2, ModernDataSelection visibleCells) {
    int x = 0; // getX(visibleCells.getStartCol()); //invTranslateX(getX(0));
    int y = getY(mRowModel.getSelectionModel().first()) - getY(visibleCells.getStartRow()); // invTranslateY(getY(mRowModel.getSelectionModel().first()));
    // // -
    // mViewRect.getY();
    int w = getX(visibleCells.getEndCol()) - getX(visibleCells.getStartCol())
        + mColumnModel.getWidth(visibleCells.getEndCol()) - 1; // getColumnCount()
    // - 1) +
    // mColumnModel.getWidth(getColumnCount()
    // - 1) + 1;
    int h = getY(mRowModel.getSelectionModel().last()) - getY(mRowModel.getSelectionModel().first())
        + mRowModel.getWidth(mRowModel.getSelectionModel().last());

    Graphics2D g2Temp = ImageUtils.clone(g2);

    try {
      translate(g2Temp, visibleCells);

      g2Temp.setColor(SELECTION_RECT_COLOR);
      g2Temp.setStroke(ModernTheme.DOUBLE_LINE_STROKE);

      g2Temp.drawLine(x, y, w, y);

      int p = y + h - 1;

      g2Temp.drawLine(x, p, w, p);

      if (visibleCells.getStartCol() == 0) {
        g2Temp.drawLine(x + 1, y - 1, x + 1, p);
      }

      if (visibleCells.getEndCol() == (getColCount() - 1)) {
        p = x + w;
        g2Temp.drawLine(p, y - 1, p, y + h - 1);
      }

      // g2Temp.drawRect(x, y, w - bo2, h - bo2);
    } finally {
      g2Temp.dispose();
    }
  }
}
