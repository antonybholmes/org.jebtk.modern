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
import org.jebtk.modern.dataview.ModernDataSelection;
import org.jebtk.modern.graphics.CanvasMouseEvent;
import org.jebtk.modern.graphics.DrawingContext;
import org.jebtk.modern.graphics.ImageUtils;
import org.jebtk.modern.table.ModernTable;
import org.jebtk.modern.table.TableIndex;

// TODO: Auto-generated Javadoc
/**
 * The class ModernTableHeaderColumn.
 */
public class ModernTableHeaderColumn extends ModernTableHeader {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member drag table column.
   */
  private int mDragTableColumn = -1;

  /**
   * The drag table column width.
   */
  private int mDragTableColumnWidth = -1;

  /**
   * The drag table column x.
   */
  private int mDragTableColumnX = -1;

  /**
   * The multi select.
   */
  private boolean mMultiSelect = false;

  /**
   * The highlight column.
   */
  private int mHighlightColumn = -1;

  /**
   * Instantiates a new modern table header column.
   *
   * @param table the table
   */
  public ModernTableHeaderColumn(ModernTable table) {
    super(table);

    // addMouseListener(this);
    // addMouseMotionListener(this);
    // addKeyListener(this);

    // table.getColumnModel().addClickListener(this);

    // setCanvasSize(new Dimension(table.getCanvasSize().getW(),
    // ModernTheme.getInstance().getClass("widget").getInt("height")));
    // setSize(getCanvasSize());
  }

  /**
   * Translate.
   *
   * @param g2 the g2
   */
  public void translate(Graphics2D g2) {
    ModernDataSelection visibleCells = mTable.calculateVisibleCells();

    g2.translate(mTable.getX(visibleCells.getStartCol()) - getViewRect().getX(), 0);
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
    if (mTable == null) {
      return;
    }

    if (mTable.getColumnModel() == null) {
      return;
    }

    if (mTable.getCellSelectionModel() == null) {
      return;
    }

    ModernDataSelection visibleCells = mTable.calculateVisibleCells();

    int h = (int) mTable.scale(mTable.getColumnModel().getHeaderSize());

    int w = mTable.getVisibleRect().x + mTable.getVisibleRect().width;

    // g2.setColor(ModernWidget.LINE_COLOR);
    // g2.drawLine(0, 0, 0, mRect.getH() - 1);
    // g2.drawLine(mRect.getW() - 1, 0, mRect.getW() - 1, mRect.getH() - 1);
    // g2.drawLine(0, h - 1, mRect.getW() - 1, h - 1);

    Graphics2D g2Temp = ImageUtils.clone(g2);

    int i = visibleCells.getStartCol();
    int x = mTable.getX(i);

    try {
      while (x < w) {
        // for (int i = visibleCells.getStartCol(); i <=
        // visibleCells.getEndCol(); ++i)
        // {
        // if (i >= mTable.getModel().getColumnCount()) {
        // break;
        // }

        boolean highlight = i == mHighlightColumn;
        boolean selected = mTable.getColumnModel().isSelected(i)
            || mTable.getCellSelectionModel().getColumnSelectionModel().contains(i);

        int wc = (int) mTable.scale(mTable.getColumnModel().getWidth(i));

        String name = mTable.getColumnName(i);

        Graphics2D g2Temp2 = (Graphics2D) g2Temp.create();

        try {
          // for (String name : columnNames) {
          Component c = mTable.getHeadingRenderer(i).getCellRendererComponent(mTable, name, highlight, selected,
              highlight, 0, i);

          c.setSize(wc, h);

          c.print(g2Temp2);

          // g2Temp2.translate(0, h);
          // }
        } finally {
          g2Temp2.dispose();
        }

        g2Temp.translate(wc, 0);

        x += wc;
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

    int column = mTable.getCol(p.getX());

    if (column != -1) {
      // clear any cell selections
      mTable.getCellSelectionModel().clear();
      mTable.removeEditor();

      // Make sure no rows are selected
      mTable.getRowModel().unselectAll();

      if (!mMultiSelect) {
        mTable.getColumnModel().unselectAll();
      }

      mTable.getColumnModel().setSelected(column);
    }

    int sortColumn = mTable.getSortColumn(p.getX());

    if (sortColumn != -1) {
      // mTable.setSortColumn(sortColumn);
    }

    mDragTableColumn = mTable.getNearestColumnDivider(p.getX());

    if (mDragTableColumn != -1) {
      mDragTableColumnWidth = mTable.getColumnModel().getWidth(mDragTableColumn);
      mDragTableColumnX = mTable.getX(mDragTableColumn) + mDragTableColumnWidth;

      mTable.setSortColumn(mDragTableColumn);
    }

    // swapTableColumn = table.getNearestSwapColumn(p.x);

    mTable.fireCanvasRedraw();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.table.header.ModernTableHeader#mouseReleased(java.
   * awt. event.CanvasMouseEvent)
   */
  @Override
  public void canvasMouseReleased(CanvasMouseEvent e) {
    mDragTableColumn = -1;
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

    if (mDragTableColumn == -1) {
      return;
    }

    IntPos2D p = e.getScaledPos(); // mTable.translateCoordinate(e);

    int width = mDragTableColumnWidth + p.getX() - mDragTableColumnX;

    mTable.getColumnModel().setWidth(mDragTableColumn, width);

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

    // System.err.println("header " + e.getY() + " " + e.getScaledPos());

    IntPos2D p = e.getScaledPos(); // mTable.translateCoordinate(e.getScaledPos());

    int x = p.getX();

    int index = mTable.getCol(x);

    if (index != -1 && index != mHighlightColumn) {
      mHighlightColumn = index;
    }

    if (inHeader(e) && mTable.getNearestColumnDivider(x) != -1) {
      mTable.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
    } else {
      mTable.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    mTable.fireCanvasRedraw();
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

    mHighlightColumn = -1;

    mTable.fireCanvasRedraw();
  }

  /**
   * In header.
   *
   * @param e the e
   * @return true, if successful
   */
  private boolean inHeader(CanvasMouseEvent e) {
    return e.getScaledPos().getY() - mTable.getViewRect().getY() <= 0; // mTable.getColumnModel().getHeaderSize();
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
    return TableIndex.DEFAULT_CELL_HEIGHT;
  }
}
