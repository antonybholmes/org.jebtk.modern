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
package org.jebtk.modern.table.header;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import org.jebtk.core.geom.IntPos2D;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.dataview.ModernDataSelection;
import org.jebtk.modern.graphics.CanvasMouseEvent;
import org.jebtk.modern.graphics.DrawingContext;
import org.jebtk.modern.graphics.ImageUtils;
import org.jebtk.modern.table.ModernTable;
import org.jebtk.modern.table.TableIndexModel;

// TODO: Auto-generated Javadoc
/**
 * Draws table row headers.
 *
 * @author Antony Holmes
 */
public class ModernTableHeaderRow extends ModernTableHeader {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The drag row.
   */
  private int mDragRow = -1;

  /**
   * The drag row width.
   */
  private int dragRowWidth = -1;

  /**
   * The drag row y.
   */
  private int dragRowY = -1;

  /**
   * The member multi select.
   */
  private boolean mMultiSelect;

  /**
   * The member highlight row.
   */
  private int mHighlightRow = -1;

  /**
   * Instantiates a new modern table header row.
   *
   * @param table the table
   */
  public ModernTableHeaderRow(ModernTable table) {
    super(table);

    // System.err.println("yeah " + mTable.getModel().getRowHeadings());

    // addMouseListener(this);
    // addMouseMotionListener(this);
    // addKeyListener(this);
    // table.addComponentListener(this);

    // table.getRowModel().addClickListener(this);

    // setSize(30, table.getHeight());
    // setCanvasSize(getSize());
  }

  /**
   * Adjust offset.
   *
   * @param g2 the g2
   */
  public void adjustOffset(Graphics2D g2) {
    // g2.translate(0, table.getY(table.getVisibleCells().startRow) -
    // getViewRect().getY());
  }

  /**
   * Translate.
   *
   * @param g2 the g2
   */
  public void translate(Graphics2D g2) {
    ModernDataSelection visibleCells = mTable.calculateVisibleCells();

    g2.translate(0, mTable.getY(visibleCells.getStartRow()) - getViewRect().getY());
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawBackground(java.awt.Graphics2D)
   */
  @Override
  public void drawBackground(Graphics2D g2) {
    fill(g2, HEADER_BACKGROUND, getRect());
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvas#drawForegroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void zoomCanvas(Graphics2D g2, DrawingContext context) {
    if (mTable == null || mTable.getModel() == null) {
      return;
    }

    ModernDataSelection visibleCells = mTable.calculateVisibleCells();

    g2.setColor(ModernWidget.LINE_COLOR);
    // g2.drawLine(0, 0, mRect.getW() - 1, 0);
    // g2.drawLine(0, mRect.getH() - 1, mRect.getW() - 1, mRect.getH() - 1);
    g2.drawLine(mRect.getW() - 1, 0, mRect.getW() - 1, mRect.getH() - 1);

    Graphics2D g2Temp = ImageUtils.clone(g2);

    int w = (int) mTable.scale(mTable.getRowModel().getHeaderSize()); // mRect.getW();
    // ///getWidth()
    // /
    // rowNames.size();

    int i = visibleCells.getStartRow();
    int y = mTable.getY(i);

    int h = mTable.getVisibleRect().y + mTable.getVisibleRect().height; // mTable.getHeight();

    try {
      // mTable.translateY(g2Temp, visibleCells);

      while (y < h) {

        // for (int i = visibleCells.getStartRow(); i <=
        // visibleCells.getEndRow(); ++i)
        // {
        // if (i >= mTable.getRowModel().size()) {
        // break;
        // }

        boolean highlight = i == mHighlightRow;
        boolean selected = mTable.getRowModel().isSelected(i)
            || mTable.getCellSelectionModel().getRowSelectionModel().contains(i);

        // first draw row number

        int hc = (int) mTable.scale(mTable.getRowModel().getWidth(i));

        int row = mTable.getModelRowIndex(i);

        String name = mTable.getRowName(row);

        Graphics2D g2Temp2 = (Graphics2D) g2Temp.create();

        try {
          // for (String name : rowNames) {
          Component c = mTable.getRowHeadingRenderer(i).getCellRendererComponent(mTable, name, highlight, selected,
              highlight, i, 0);

          c.setSize(w, hc);

          c.print(g2Temp2);

          // g2Temp2.translate(w, 0);
          // }
        } finally {
          g2Temp2.dispose();
        }

        g2Temp.translate(0, hc);

        y += hc;
        ++i;
      }
    } finally {
      g2Temp.dispose();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.table.header.ModernTableHeader#mousePressed(java.awt.
   * event.CanvasMouseEvent)
   */
  @Override
  public void canvasMousePressed(CanvasMouseEvent e) {
    if (!inHeader(e)) {
      return;
    }

    IntPos2D p = e.getScaledPos(); // mTable.translateCoordinate(e);

    int row = mTable.getRow(p.getY());

    if (row != -1) {
      /*
       * table.getCellsSelectionModel().getColumnsSelectionModel().
       * setModernSelectionInterval(0, table.getColumnCount() - 1);
       * 
       * if (!multiSelect) {
       * table.getCellsSelectionModel().getRowSelectionModel().clear();
       * //table.getRowModel().unselectAll(); }
       * 
       * table.getCellsSelectionModel().getRowSelectionModel().add(row);
       */

      // clear any cell selections
      mTable.getCellSelectionModel().clear();
      mTable.removeEditor();

      mTable.getColumnModel().unselectAll();

      if (!mMultiSelect) {
        mTable.getRowModel().unselectAll();
      }

      mTable.getRowModel().setSelected(row);
    }

    mDragRow = mTable.getNearestRowDivider(p.getY());

    if (mDragRow != -1) {
      dragRowWidth = mTable.getRowModel().getWidth(mDragRow);
      dragRowY = mTable.getY(mDragRow) + dragRowWidth;
    }

    // requestFocusInWindow();

    mTable.fireCanvasRedraw();
  }

  /**
   * In header.
   *
   * @param e the e
   * @return true, if successful
   */
  private boolean inHeader(CanvasMouseEvent e) {
    return e.getScaledPos().getX() - mTable.getViewRect().getX() <= 0; // mTable.getRowModel().getHeaderSize();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.table.header.ModernTableHeader#mouseReleased(java.
   * awt. event.CanvasMouseEvent)
   */
  @Override
  public void canvasMouseReleased(CanvasMouseEvent e) {
    mDragRow = -1;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.table.header.ModernTableHeader#mouseDragged(java.awt.
   * event.CanvasMouseEvent)
   */
  @Override
  public void canvasMouseDragged(CanvasMouseEvent e) {
    if (!inHeader(e)) {
      return;
    }

    if (mDragRow == -1) {
      return;
    }

    super.canvasMouseDragged(e);

    IntPos2D p = e.getScaledPos(); // mTable.translateCoordinate(e);

    int width = dragRowWidth + p.getY() - dragRowY;

    mTable.getRowModel().setWidth(mDragRow, width);

    mTable.fireCanvasRedraw();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.table.header.ModernTableHeader#mouseMoved(java.awt.
   * event.CanvasMouseEvent)
   */
  @Override
  public void canvasMouseMoved(CanvasMouseEvent e) {
    IntPos2D p = e.getScaledPos(); // mTable.translateCoordinate(e.getScaledPos());

    int y = p.getY();

    int index = mTable.getRow(y);

    if (index != -1 && index != mHighlightRow) {
      mHighlightRow = index;
    }

    if (inHeader(e) && mTable.getNearestRowDivider(y) != -1) {
      mTable.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
    } else {
      mTable.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    mTable.fireCanvasRedraw();

    /*
     * super.canvasMouseMoved(e);
     * 
     * IntPos p = e.getScaledPos(); //mTable.translateCoordinate(e);
     * 
     * int index = mTable.getRow(p.getY());
     * 
     * if (index == -1 || index == mHighlightRow) { return; }
     * 
     * mHighlightRow = index;
     * 
     * if (inHeader(e)) {
     * setCursor(Cursor.getPredefinedCursor(mTable.getNearestRowDivider(p.getY() )
     * != -1 ? Cursor.S_RESIZE_CURSOR : Cursor.DEFAULT_CURSOR)); }
     * 
     * mTable.fireCanvasRedraw();
     */
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.table.header.ModernTableHeader#mouseExited(java.awt.
   * event.CanvasMouseEvent)
   */
  @Override
  public void canvasMouseExited(CanvasMouseEvent e) {
    // super.canvasMouseExited(e);

    mHighlightRow = -1;

    mTable.fireCanvasRedraw();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
   */
  @Override
  public void canvasKeyPressed(KeyEvent e) {
    mMultiSelect = e.isControlDown();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
   */
  @Override
  public void canvasKeyReleased(KeyEvent e) {
    mMultiSelect = false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
   */
  @Override
  public void canvasKeyTyped(KeyEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.scrollpane.ModernScrollPaneHeader#getFixedDimension()
   */
  @Override
  public int getFixedDimension() {
    return TableIndexModel.DEFAULT_CELL_WIDTH;
  }
}
