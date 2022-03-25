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

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import org.jebtk.core.Mathematics;
import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.event.ChangeListener;
import org.jebtk.core.geom.IntPos2D;
import org.jebtk.core.text.Join;
import org.jebtk.core.text.TextUtils;
import org.jebtk.modern.MouseUtils;
import org.jebtk.modern.SelectionPolicy;
import org.jebtk.modern.SelectionRangeType;
import org.jebtk.modern.clipboard.ClipboardService;
import org.jebtk.modern.dataview.ModernData;
import org.jebtk.modern.dataview.ModernDataCell;
import org.jebtk.modern.dataview.ModernDataCellRenderer;
import org.jebtk.modern.dataview.ModernDataModel;
import org.jebtk.modern.dataview.ModernDataSelection;
import org.jebtk.modern.event.ModernSelectionListener;
import org.jebtk.modern.font.FontUtils;
import org.jebtk.modern.graphics.CanvasCursorEvent;
import org.jebtk.modern.graphics.CanvasCursorListener;
import org.jebtk.modern.graphics.CanvasKeyAdapter;
import org.jebtk.modern.graphics.CanvasListener;
import org.jebtk.modern.graphics.CanvasMouseAdapter;
import org.jebtk.modern.graphics.CanvasMouseEvent;
import org.jebtk.modern.graphics.DrawingContext;
import org.jebtk.modern.graphics.ImageUtils;
import org.jebtk.modern.table.header.ModernMatrixTableCorner;
import org.jebtk.modern.table.header.ModernTableHeader;
import org.jebtk.modern.table.header.ModernTableHeaderColumn;
import org.jebtk.modern.table.header.ModernTableHeaderRow;
import org.jebtk.modern.text.ModernTextBox;
import org.jebtk.modern.theme.ModernTheme;
import org.jebtk.modern.theme.ThemeService;
import org.jebtk.modern.zoom.ZoomModel;

// TODO: Auto-generated Javadoc
/**
 * Represents a 2D table/grid view of data.
 * 
 * @author Antony Holmes
 *
 */
public class ModernTable extends ModernData implements ModernSelectionListener, CanvasListener, CanvasCursorListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant DEFAULT_CELL_WIDTH.
   */
  //

  /**
   * The constant SELECTION_RECT_COLOR.
   */
  public static final Color SELECTION_RECT_COLOR = ThemeService.getInstance().getColors().getTheme(5);

  /** The m heading render model. */
  protected ModernTableHeadingRendererModel mHeadingRenderModel = new ModernTableHeadingRendererModel();

  /** The m row heading render model. */
  protected ModernTableHeadingRendererModel mRowHeadingRenderModel = new ModernTableRowHeadingRendererModel();

  /**
   * Keep track of rows, for example which are selected.
   */
  protected TableIndexModel mRowModel = new TableIndexRowModel();

  /**
   * The member column model.
   */
  protected TableIndexModel mColumnModel = new TableIndexColumnModel();

  // private boolean multiRowSelect = false;

  // private boolean multiRangeSelect = false;

  /**
   * The member show row header.
   */
  private boolean mShowRowHeader = false;

  /**
   * The member show header.
   */
  private boolean mShowHeader = true;

  /**
   * The member column header sort model.
   */
  private ColumnHeaderSortModel mColumnHeaderSortModel;

  /** The m header. */
  private ModernTableHeader mHeader;

  /** The m row header. */
  private ModernTableHeader mRowHeader;

  /** The m corner. */
  private ModernMatrixTableCorner mCorner;

  /**
   * The class ColumnHeaderChangeEvents.
   */
  private class ColumnHeaderChangeEvents implements ChangeListener {

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.lib.event.ChangeListener#changed(org.abh.lib.event.ChangeEvent)
     */
    @Override
    public void changed(ChangeEvent e) {
      refreshView();
    }

  }

  /**
   * The class EnterKeyEvents.
   */
  private class EnterKeyEvents extends AbstractAction {

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      stopEditing();

      ModernDataCell cell = mSelectionModel.last();

      if (cell != null) {
        setSelectedCell(Math.min(getRowCount() - 1, cell.row + 1), cell.col, false, false);
      }
    }
  }

  /*
   * private class LeftKeyEvents extends AbstractAction { private static final
   * long serialVersionUID = 1L;
   * 
   * @Override public void actionPerformed(ActionEvent e) { ModernDataCell cell =
   * mSelectionModel.last();
   * 
   * if (cell != null) { setSelectedCell(cell.row, Math.max(0, cell.col - 1),
   * false, false); } } }
   * 
   * private class RightKeyEvents extends AbstractAction { private static final
   * long serialVersionUID = 1L;
   * 
   * @Override public void actionPerformed(ActionEvent e) { ModernDataCell cell =
   * mSelectionModel.last();
   * 
   * if (cell != null) { setSelectedCell(cell.row, Math.min(getColumnCount() - 1,
   * cell.col + 1), false, false); } } }
   * 
   * private class UpKeyEvents extends AbstractAction { private static final long
   * serialVersionUID = 1L;
   * 
   * @Override public void actionPerformed(ActionEvent e) { ModernDataCell cell =
   * mSelectionModel.last();
   * 
   * if (cell != null) { setSelectedCell(Math.max(0, cell.row - 1), cell.col,
   * false, false); } } }
   * 
   * private class DownKeyEvents extends AbstractAction { private static final
   * long serialVersionUID = 1L;
   * 
   * @Override public void actionPerformed(ActionEvent e) { ModernDataCell cell =
   * mSelectionModel.last();
   * 
   * if (cell != null) { setSelectedCell(Math.min(getRowCount() - 1, cell.row +
   * 1), cell.col, false, false); } } }
   */

  /**
   * The class KeyEvents.
   */
  private class KeyEvents extends CanvasKeyAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
     */
    @Override
    public void canvasKeyPressed(KeyEvent e) {
      ModernDataCell cell;

      switch (e.getKeyCode()) {
      case KeyEvent.VK_LEFT:
        cell = mSelectionModel.last();

        if (cell != null) {
          setSelectedCell(cell.row, Math.max(0, cell.col - 1), false, false);
        }

        break;
      case KeyEvent.VK_RIGHT:
        cell = mSelectionModel.last();

        if (cell != null) {
          setSelectedCell(cell.row, Math.min(getColCount() - 1, cell.col + 1), false, false);
        }

        break;
      case KeyEvent.VK_UP:
        cell = mSelectionModel.last();

        if (cell != null) {
          setSelectedCell(Math.max(0, cell.row - 1), cell.col, false, false);
        }

        break;
      case KeyEvent.VK_ENTER:
      case KeyEvent.VK_DOWN:
        moveDown();
        break;
      }
    }
  }

  /**
   * The Class CanvasMouseEvents.
   */
  private class CanvasMouseEvents extends CanvasMouseAdapter {
    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public void canvasMousePressed(CanvasMouseEvent e) {
      IntPos2D p = e.getScaledPos();

      IntPos2D p2 = new IntPos2D(p.getX(), p.getY());

      if (p2.getX() <= 0 || p2.getY() <= 0) {
        return;
      }

      if (e.getButton() != MouseEvent.BUTTON1) {
        return;
      }

      boolean multiSelect = (e.getModifiers() & Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()) == Toolkit
          .getDefaultToolkit().getMenuShortcutKeyMask();
      boolean multiRangeSelect = (e.getModifiers() & ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK;

      setSelectedCell(getCell(p2), multiSelect, multiRangeSelect);

      /// if (e.getClickCount() == 2) {
      // mCurrentEditorComponent.setEnabled(true);
      // }

      ModernDataCell selectedCell = mSelectionModel.last();

      if (selectedCell != null && mCurrentEditorComponent instanceof ModernTextBox) {
        ModernTextBox ec = (ModernTextBox) mCurrentEditorComponent;

        // System.err.println("ec " + ec + " " + ec.getFont().getSize());

        // Localise mouse event to the cell
        ec.mousePressed(MouseUtils.updateXY(e, toCellSpace(e, selectedCell)));
      }

      fireCanvasRedraw();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.common.ui.graphics.ModernCanvasMouseAdapter#canvasMouseReleased(
     * org. abh.common.ui.graphics.CanvasMouseEvent)
     */
    @Override
    public void canvasMouseReleased(CanvasMouseEvent e) {
      ModernDataCell selectedCell = mSelectionModel.last();

      if (selectedCell != null && mCurrentEditorComponent instanceof ModernTextBox) {
        ModernTextBox ec = (ModernTextBox) mCurrentEditorComponent;

        // Localise mouse event to the cell
        ec.mouseReleased(MouseUtils.updateXY(e, toCellSpace(e, selectedCell)));
      }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.common.ui.graphics.ModernCanvasMouseAdapter#canvasMouseClicked(
     * org. abh.common.ui.graphics.CanvasMouseEvent)
     */
    @Override
    public void canvasMouseClicked(CanvasMouseEvent e) {
      ModernDataCell selectedCell = mSelectionModel.last();

      if (selectedCell != null && mCurrentEditorComponent instanceof ModernTextBox) {
        ModernTextBox ec = (ModernTextBox) mCurrentEditorComponent;

        // Localise mouse event to the cell
        ec.mouseClicked(MouseUtils.updateXY(e, toCellSpace(e, selectedCell)));
      }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.abh.common.ui.graphics.ModernCanvasMouseAdapter#canvasMouseMoved(org.
     * abh. common.ui.graphics.CanvasMouseEvent)
     */
    @Override
    public void canvasMouseMoved(CanvasMouseEvent e) {
      ModernDataCell selectedCell = mSelectionModel.last();

      if (selectedCell != null && mCurrentEditorComponent instanceof ModernTextBox) {

        // Mouse events on the table must be localized for the
        // selected cell so that the selected cell behaves as if
        // it were getting mouse events as a first class citizen.

        ModernTextBox ec = (ModernTextBox) mCurrentEditorComponent;

        IntPos2D p = toCellSpace(e, selectedCell);

        // System.err.println(" p " + p + " " +
        // mColumnModel.getWidth(selectedCell.col) + " " +
        // mRowModel.getWidth(selectedCell.row));

        if (p.getX() >= 0 && p.getY() >= 0 && p.getX() < mColumnModel.getWidth(selectedCell.col)
            && p.getY() < mRowModel.getWidth(selectedCell.row)) {
          ec.mouseEntered(MouseUtils.updateXY(e, p));
        } else {
          ec.mouseExited(MouseUtils.updateXY(e, p));
        }
      }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.common.ui.graphics.ModernCanvasMouseAdapter#canvasMouseDragged(
     * org. abh.common.ui.graphics.CanvasMouseEvent)
     */
    @Override
    public void canvasMouseDragged(CanvasMouseEvent e) {
      ModernDataCell selectedCell = mSelectionModel.last();

      if (selectedCell != null && mCurrentEditorComponent instanceof ModernTextBox) {
        ModernTextBox ec = (ModernTextBox) mCurrentEditorComponent;

        // Localise mouse event to the cell
        ec.mouseDragged(MouseUtils.updateXY(e, toCellSpace(e, selectedCell)));
      }
    }
  }

  private class SelectionEvents implements ModernSelectionListener {
    @Override
    public void selectionAdded(ChangeEvent e) {
      int row = getSelectedRow();
      int col = getSelectedCol();

      // if (row > 0 || col > 0) {
      int x = getColumnModel().getCumOffset(col);
      int w = getColumnModel().getWidth(col);

      int y = getRowModel().getCumOffset(row);
      int h = getRowModel().getWidth(row);

      scrollRectToVisible(new Rectangle(x, y, w, h));
      // }
    }

    @Override
    public void selectionRemoved(ChangeEvent e) {
      // TODO Auto-generated method stub

    }
  }

  /**
   * Instantiates a new modern table.
   */
  public ModernTable() {
    init();
  }

  /**
   * Instantiates a new modern table.
   *
   * @param model the model
   */
  public ModernTable(ModernDataModel model) {
    setModel(model);

    init();
  }

  public ModernTable(ZoomModel zoomModel) {
    super(zoomModel);

    init();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.dataview.ModernData#setModel(org.abh.lib.ui.modern.
   * dataview.ModernDataModel)
   */
  @Override
  public void setModel(ModernDataModel model) {
    super.setModel(model);

    mColumnHeaderSortModel = new ColumnHeaderSortModel(model.getColCount());
    mColumnHeaderSortModel.addChangeListener(new ColumnHeaderChangeEvents());
  }

  /**
   * Inits the.
   */
  private void init() {
    // Listen for changes so we can update location
    getCellSelectionModel().addSelectionListener(new SelectionEvents());

    addCanvasMouseListener(new CanvasMouseEvents());

    getEditorModel().setDefault(new ModernTableTextCellEditor());

    // Bind a header to the table
    mCorner = new ModernMatrixTableCorner(this);
    mHeader = new ModernTableHeaderColumn(this);
    mRowHeader = new ModernTableHeaderRow(this);

    addCanvasKeyListener(new KeyEvents());

    getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter_pressed");
    getActionMap().put("enter_pressed", new EnterKeyEvents());

    /*
     * getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke. getKeyStroke(
     * KeyEvent.VK_LEFT, 0), "left_pressed"); getActionMap().put("left_pressed", new
     * AbstractAction() { private static final long serialVersionUID = 1L;
     * 
     * @Override public void actionPerformed(ActionEvent e) { ModernDataCell cell =
     * mSelectionModel.last();
     * 
     * if (cell != null) { setSelectedCell(cell.row, Math.max(0, cell.col - 1),
     * false, false); } }});
     */

    /*
     * getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke. getKeyStroke(
     * KeyEvent.VK_RIGHT, 0), "right_pressed"); getActionMap().put("right_pressed",
     * new AbstractAction() { private static final long serialVersionUID = 1L;
     * 
     * @Override public void actionPerformed(ActionEvent e) { ModernDataCell cell =
     * mSelectionModel.last();
     * 
     * if (cell != null) { setSelectedCell(cell.row, Math.min(getColumnCount() - 1,
     * cell.col + 1), false, false); } }});
     */

    /*
     * getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke. getKeyStroke(
     * KeyEvent.VK_UP, 0), "up_pressed"); getActionMap().put("up_pressed", new
     * AbstractAction() { private static final long serialVersionUID = 1L;
     * 
     * @Override public void actionPerformed(ActionEvent e) { ModernDataCell cell =
     * mSelectionModel.last();
     * 
     * if (cell != null) { setSelectedCell(Math.max(0, cell.row - 1), cell.col,
     * false, false); } }});
     */

    /*
     * getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke. getKeyStroke(
     * KeyEvent.VK_DOWN, 0), "down_pressed"); getActionMap().put("down_pressed", new
     * AbstractAction() { private static final long serialVersionUID = 1L;
     * 
     * @Override public void actionPerformed(ActionEvent e) { ModernDataCell cell =
     * mSelectionModel.last();
     * 
     * if (cell != null) { setSelectedCell(Math.min(getRowCount() - 1, cell.row +
     * 1), cell.col, false, false); } }});
     */

    /*
     * getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke. getKeyStroke(
     * KeyEvent.VK_LEFT, 0), "left_key"); getActionMap().put("left_key", new
     * LeftKeyEvents());
     * 
     * getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke. getKeyStroke(
     * KeyEvent.VK_RIGHT, 0), "right_key"); getActionMap().put("right_key", new
     * RightKeyEvents());
     * 
     * getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke. getKeyStroke(
     * KeyEvent.VK_UP, 0), "up_key"); getActionMap().put("up_key", new
     * UpKeyEvents());
     * 
     * getInputMap(WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke. getKeyStroke(
     * KeyEvent.VK_DOWN, 0), "down_key"); getActionMap().put("down_key", new
     * DownKeyEvents());
     */
  }

  /**
   * Update the views to reflect the table data, i.e ensure there is a column and
   * row view to represent each column and row in the table data.
   */
  @Override
  protected void updateViewModels() {

    // default to sorting on nothing
    mRowSortModel.setSortIndex(-1);

    // selectedCells.clear();
    // cellRenderers.clear();

    ChangeListener l = new ChangeListener() {
      @Override
      public void changed(ChangeEvent e) {
        fireDataChanged();
      }
    };

    if (mColumnModel != null) {
      // mColumnModel.removeClickListener(this);
      // mColumnModel.removeSelectionListener(this);
      // mColumnModel.clear();
      mColumnModel.addChangeListener(l);
      mColumnModel.addSelectionListener(this);
    }

    if (mRowModel != null) {
      // mRowModel.removeClickListener(this);
      // mRowModel.removeSelectionListener(this);
      // mRowModel.clear();
      mRowModel.addChangeListener(l);
      mRowModel.addSelectionListener(this);
    }

    // selectionModel.clearModernSelection();
    mSelectionModel.clear();

    // mColSortModel.clear();
    // mRowSortModel.clear();

    /*
     * for (int i = 0; i < model.getColumnCount(); ++i) { columnModel.addIndex(new
     * ModernTableColumn(i, defaultColumnWidth)); }
     * 
     * for (int i = 0; i < model.getRowCount(); ++i) { rowModel.addIndex(new
     * ModernTableRow(i, rowHeight)); }
     */

    // model.fireTableDataChanged();
  }

  // @Override
  // public void drawBackground(Graphics2D g2) {
  // fillBackground(g2);
  // }

  /**
   * Gets the column count.
   *
   * @return the column count
   */
  @Override
  public int getColCount() {
    return mModel == null ? 0 : mModel.getColCount();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernData#getRowCount()
   */
  @Override
  public int getRowCount() {
    return mModel == null ? 0 : mModel.getRowCount();
  }

  /**
   * Sets the view cell.
   *
   * @param row    the row
   * @param column the column
   */
  public void setViewCell(int row, int column) {
    int y = mRowModel.getCumWidth(Math.max(0, row));
    int x = mColumnModel.getCumWidth(Math.max(0, column));

    super.setViewRectangle(x, y, mColumnModel.getWidth(column), mRowModel.getWidth(row));
  }

  /*
   * public List<Integer> getSelectedRows() { return selectedRows; }
   */

  /**
   * Adjust the size of the table image.
   */
  @Override
  public void adjustSize() {
    if (mModel == null) {
      return;
    }

    if (mModel.getRowCount() == 0 || mModel.getColCount() == 0) {
      return;
    }

    mRowModel.setSize(mModel.getRowCount()); // = new
    // TableIndexModel(model.getRowCount(),
    // new
    // ModernTableRow(rowHeight));

    mColumnModel.setSize(mModel.getColCount()); // = new
    // TableIndexModel(model.getColumnCount(),
    // new
    // ModernTableColumn(defaultColumnWidth));

    int width = 0;

    if (mShowRowHeader) {
      width += 2 * mRowModel.getHeaderSize();
    }

    for (int i = 0; i < mColumnModel.size(); ++i) {
      width += mColumnModel.getWidth(i);
    }

    int height = 0;

    if (mShowHeader) {
      height += 2 * mColumnModel.getHeaderSize();
    }

    for (int i = 0; i < mRowModel.size(); ++i) {
      height += mRowModel.getWidth(i);
    }

    setCanvasSize(width, height);

    // setSize(tableSize);
    // setCanvasSize(tableSize);

    refreshView();
  }

  /*
   * public final TableIndexModel getColumnModel() { return columnModel; }
   * 
   * public TableIndexModel getRowModel() { return rowModel; }
   */

  /**
   * Returns the cumulative distance from 0 of a particular column.
   *
   * @param column the column
   * @return the x
   */
  public final int getX(int column) {
    return getX(column, getZoomModel().getZoom());
  }

  public final int getX(int column, double zoom) {
    return mColumnModel.getOffset(column, zoom);
  }

  /**
   * Returns the cumulative distance from 0 of a particular row.
   *
   * @param row the row
   * @return the y
   */
  public final int getY(int row) {
    return getY(row, getZoomModel().getZoom());
  }

  public final int getY(int column, double zoom) {
    return mRowModel.getOffset(column, zoom);
  }

  /**
   * Gets the nearest column divider.
   *
   * @param x the x
   * @return the nearest column divider
   */
  public int getNearestColumnDivider(int x) {

    // see if we are near a column line

    // x coordinates are offset so that 0,0 is the top left point where
    // the table begins, thus the headers are in negative coordinates
    // if (mShowRowHeader) {
    // x += mRowModel.getHeaderSize();
    // }

    int p = 0;

    for (int i = 0; i < mColumnModel.size(); ++i) {
      p += mColumnModel.getWidth(i);

      if (Math.abs(p - x) < PADDING) {
        return i;
      }
    }

    return -1;
  }

  /**
   * Gets the nearest row divider.
   *
   * @param y the y
   * @return the nearest row divider
   */
  public int getNearestRowDivider(int y) {
    // if (mShowHeader) {
    // y += mColumnModel.getHeaderSize();
    // }

    int p = 0;

    for (int i = 0; i < mModel.getRowCount(); ++i) {
      p += mRowModel.getWidth(i);

      if (Math.abs(p - y) < PADDING) {
        return i;
      }
    }

    return -1;
  }

  /**
   * Returns the nearest sort column for a given column.
   *
   * @param x the x
   * @return the sort column
   */
  public int getSortColumn(int x) {

    // see if we are near a column line

    int p = 0;

    for (int i = 0; i < mColumnModel.size(); ++i) {
      p += mColumnModel.getWidth(i);

      int d = p - x;

      if (d >= PADDING && d < 16) {
        return i;
      }
    }

    return -1;
  }

  /**
   * Gets the nearest swap column.
   *
   * @param x the x
   * @return the nearest swap column
   */
  public int getNearestSwapColumn(int x) {
    int p = 0;

    for (int i = 0; i < mColumnModel.size(); ++i) {
      if (x >= p && x < p + mColumnModel.getWidth(i)) {
        return i;
      }

      p += mColumnModel.getWidth(i);
    }

    return -1;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernData#getCol(int)
   */
  @Override
  public int getCol(int x) {
    // if (mShowRowHeader) {
    // x += mRowModel.getHeaderSize();
    // }

    int p = 0;

    for (int i = 0; i < mColumnModel.size(); ++i) {
      p += (int) scale(mColumnModel.getWidth(i));

      if (p >= x) {
        return i;
      }
    }

    return -1;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernData#getRow(int)
   */
  @Override
  public int getRow(int y) {
    // if (mShowHeader) {
    // y += mColumnModel.getHeaderSize();
    // }

    int p = 0;

    for (int i = 0; i < mRowModel.size(); ++i) {
      p += (int) scale(mRowModel.getWidth(i));

      if (p >= y) {
        return i;
      }
    }

    return -1;
  }

  /**
   * Sets the show row header.
   *
   * @param show the new show row header
   */
  public void setShowRowHeader(boolean show) {
    mShowRowHeader = show;

    fireDataChanged();
  }

  /**
   * Show row header.
   *
   * @return true, if successful
   */
  public boolean getShowRowHeader() {
    return mShowRowHeader;
  }

  /**
   * Set whether the header is visible when the table is displayed in a scroll
   * pane.
   *
   * @param show the new show header
   */
  public void setShowHeader(boolean show) {
    mShowHeader = show;

    fireDataChanged();
  }

  /**
   * Show header.
   *
   * @return true, if successful
   */
  public boolean getShowHeader() {
    return mShowHeader;
  }

  public String getColumnName(int col) {
    return mModel.getColumnName(col);
  }

  public String getRowName(int col) {
    return mModel.getRowName(col);
  }

  @Override
  public void copy() {
    if (mColumnModel.getSelectionModel().size() > 0) {
      // Select every row from the selected columns
      List<String> values = new ArrayList<String>(getRowCount());

      Join tabJoin = Join.onTab();

      for (int i = 0; i < getRowCount(); ++i) {
        List<String> cells = new ArrayList<String>(mColumnModel.getSelectionModel().size());

        for (int j : mColumnModel.getSelectionModel()) {
          cells.add(getValueAt(i, j) != null ? mModel.getValueAsString(i, j) : TextUtils.EMPTY_STRING);
        }

        values.add(tabJoin.values(cells).toString());
      }

      ClipboardService.copyToClipboard(Join.onNewLine().values(values).toString());
    } else if (mRowModel.getSelectionModel().size() > 0) {
      List<String> values = new ArrayList<String>(getColCount());

      Join tabJoin = Join.onTab();

      for (int i : mRowModel.getSelectionModel()) {
        List<String> cells = new ArrayList<String>(getColCount());

        for (int j = 0; j < getColCount(); ++j) {
          cells.add(getValueAt(i, j) != null ? mModel.getValueAsString(i, j) : TextUtils.EMPTY_STRING);
        }

        values.add(tabJoin.values(cells).toString());
      }

      ClipboardService.copyToClipboard(Join.onNewLine().values(values).toString());
    } else {
      super.copy();
    }
  }

  /**
   * Translate.
   *
   * @param g2           the g 2
   * @param visibleCells the visible cells
   */
  protected void translate(Graphics2D g2, ModernDataSelection visibleCells) {
    translate(g2, visibleCells, mShowHeader, mShowRowHeader);
  }

  /**
   * Translate.
   *
   * @param g2            the g 2
   * @param visibleCells  the visible cells
   * @param showHeader    the show header
   * @param showRowHeader the show row header
   */
  protected void translate(Graphics2D g2, ModernDataSelection visibleCells, boolean showHeader, boolean showRowHeader) {
    translateX(g2, visibleCells, showRowHeader);
    translateY(g2, visibleCells, showHeader);
  }

  protected void translate(Graphics2D g2, boolean showHeader, boolean showRowHeader) {
    translateX(g2, showRowHeader);
    translateY(g2, showHeader);
  }

  /**
   * Translate X.
   *
   * @param g2           the g 2
   * @param visibleCells the visible cells
   */
  public void translateX(Graphics2D g2, ModernDataSelection visibleCells) {
    translateX(g2, visibleCells, mShowRowHeader);
  }

  /**
   * Translate X.
   *
   * @param g2           the g 2
   * @param visibleCells the visible cells
   * @param showHeader   the show header
   */
  public void translateX(Graphics2D g2, ModernDataSelection visibleCells, boolean showHeader) {

    // double x = getVisibleRect().x; //0; //getX(visibleCells.getStartCol());
    // // -
    // getViewRect().getX();

    double x = getX(visibleCells.getStartCol());// - getVisibleRect().x);

    if (showHeader) {
      x += scale(mRowModel.getHeaderSize());
    }

    g2.translate(x, 0);
  }

  /**
   * Translate the header in the x direction so that it is always visible.
   * 
   * @param g2
   * @param showHeader whether to offset to render the header.
   */
  private void translateX(Graphics2D g2, boolean showHeader) {

    double x = getVisibleRect().x; // 0; //getX(visibleCells.getStartCol()); //
    // - getViewRect().getX();

    if (showHeader) {
      x += scale(mRowModel.getHeaderSize());
    }

    g2.translate(x, 0);
  }

  /**
   * Translate Y.
   *
   * @param g2           the g 2
   * @param visibleCells the visible cells
   */
  public void translateY(Graphics2D g2, ModernDataSelection visibleCells) {
    translateY(g2, visibleCells, mShowHeader);
  }

  /**
   * Translate Y.
   *
   * @param g2           the g 2
   * @param visibleCells the visible cells
   * @param showHeader   the show header
   */
  public void translateY(Graphics2D g2, ModernDataSelection visibleCells, boolean showHeader) {
    // reset so that there is no offset
    // double y = getVisibleRect().y; //getY(visibleCells.getStartRow());
    // -getViewRect().getY();

    double y = getY(visibleCells.getStartRow()); // - getVisibleRect().y);

    if (showHeader) {
      y += scale(mColumnModel.getHeaderSize());
    }

    g2.translate(0, y);
  }

  public void translateY(Graphics2D g2, boolean showHeader) {
    // reset so that there is no offset
    // double y = getVisibleRect().y; //getY(visibleCells.getStartRow());
    // -getViewRect().getY();

    double y = getVisibleRect().y;

    if (showHeader) {
      y += scale(mColumnModel.getHeaderSize());
    }

    g2.translate(0, y);
  }

  /**
   * Inv translate X.
   *
   * @param x the x
   * @return the int
   */
  public int invTranslateX(int x) {
    int ret = x - getViewRect().getX();

    // if (mShowRowHeader) {
    // ret += mRowModel.getHeaderSize();
    // }

    return ret;
  }

  /**
   * Inv translate Y.
   *
   * @param y the y
   * @return the int
   */
  public int invTranslateY(int y) {
    int ret = y - getViewRect().getY();

    // if (mShowHeader) {
    // ret += mColumnModel.getHeaderSize();
    // }

    return ret;
  }

  /**
   * Calculates the range of cells visible at any given time.
   *
   * @return the modern data selection
   */
  @Override
  public ModernDataSelection calculateVisibleCells() {
    int startCol = 0;
    int x = mColumnModel.getWidth(startCol);

    Rectangle rect = getVisibleRect();

    double t = iscale(rect.x);

    while (startCol < mColumnModel.size() - 1) {
      if (x > t) {
        break;
      }

      ++startCol;
      x += mColumnModel.getWidth(startCol);
    }

    // max col

    t += iscale(rect.width);

    if (mShowRowHeader) {
      t -= iscale(mRowModel.getHeaderSize());
    }

    int n = mColumnModel.size() - 1;

    int endCol = Math.min(startCol + 1, n);

    while (endCol < n) {
      if (x > t) {
        break;
      }

      x += mColumnModel.getWidth(endCol);
      ++endCol;
    }

    // min row

    t = iscale(rect.y);

    int startRow = 0;
    int y = mRowModel.getWidth(startRow);

    while (startRow < mRowModel.size() - 1) {
      if (y > t) {
        break;
      }

      ++startRow;
      y += mRowModel.getWidth(startRow);

    }

    // max row

    t += iscale(rect.height);

    if (mShowHeader) {
      t -= iscale(mColumnModel.getHeaderSize());
    }

    int endRow = Math.min(startRow + 1, mRowModel.size() - 1);

    while (endRow < mRowModel.size() - 1) {
      if (y > t) {
        break;
      }

      y += mRowModel.getWidth(endRow);
      ++endRow;
    }

    // SysUtils.err().println("vis cells ", startRow, endRow, startCol, endCol);

    return new ModernDataSelection(startRow, endRow, startCol, endCol);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.graphics.ModernCanvas#drawCanvasForeground(java.awt.
   * Graphics2D, org.abh.common.ui.graphics.DrawingContext)
   */
  @Override
  public void zoomCanvas(Graphics2D g2, DrawingContext context) {

    if (mModel == null || mModel.getRowCount() == 0 || mModel.getColCount() == 0) {
      return;
    }

    // required for smooth offsetting
    // g2Table.translate(viewOffset.x, viewOffset.y);

    // System.out.println("visible selection:" +
    // visibleModernSelection.getStartRow() + " " +
    // visibleModernSelection.getEndRow());
    // System.out.println("visible selection c:" +
    // visibleModernSelection.getStartCol() + " " +
    // visibleModernSelection.getEndCol());

    ModernDataSelection visibleCells = calculateVisibleCells();

    createTableImage(g2, context, visibleCells);

    // createGridLines(g2);

    // drawSelectionRectangle(g2, visibleCells);

    // ModernTheme.drawRect(g2, getRect(), LINE_COLOR);

    // g2.setColor(LINE_COLOR);
    // g2.drawLine(mRect.getW() - 1, 0, mRect.getW() - 1, mRect.getH());
    // g2.drawLine(0, mRect.getH() - 1, mRect.getW() - 1, mRect.getH() - 1);
  }

  /**
   * Provides a standard method for rendering the cells. This method ensures only
   * visible cells are rendered, which is improves drawing time since it wastes a
   * lot of effort drawing cells no one can see.
   *
   * @param g2      the g2
   * @param context the context
   */
  protected void createTableImage(Graphics2D g2, DrawingContext context) {
    createTableImage(g2, context, calculateVisibleCells());
  }

  /**
   * Creates the table image.
   *
   * @param g2           the g 2
   * @param context      the context
   * @param visibleCells the visible cells
   */
  protected void createTableImage(Graphics2D g2, DrawingContext context, ModernDataSelection visibleCells) {

    ModernDataCellRenderer renderer;

    boolean highlightRowWise;
    boolean highlightColumnWise;

    int w;
    int h;
    double zoom = getZoom();

    ModernDataCell cell = mSelectionModel.last();

    Graphics2D g2Temp = ImageUtils.clone(g2);

    try {
      // translate to the start of the rendering rectangle so that we skip
      // all non visible cells
      translate(g2Temp, visibleCells, mShowHeader, mShowRowHeader);

      createTableBackground(g2Temp, context, visibleCells);

      for (int i = visibleCells.getStartRow(); i <= visibleCells.getEndRow(); ++i) {
        if (i >= getRowCount()) {
          break;
        }

        highlightRowWise = mRowModel.isSelected(i);

        h = mRowModel.getWidth(i, zoom);

        Graphics2D g2Temp2 = ImageUtils.clone(g2Temp);

        try {
          for (int j = visibleCells.getStartCol(); j <= visibleCells.getEndCol(); ++j) {
            if (j >= getColCount()) {
              break;
            }

            highlightColumnWise = mColumnModel.isSelected(j);

            // if the row, column or individual cell is selected

            boolean highlight = highlightRowWise || highlightColumnWise;

            w = mColumnModel.getWidth(j, zoom);

            if (cell != null && cell.row == i && cell.col == j && mCurrentEditorComponent != null
                && mCurrentEditorComponent instanceof ModernTextBox) {
              ModernTextBox ec = (ModernTextBox) mCurrentEditorComponent;

              // Scale the font in this graphics context so
              // it is kept when we render the text box.
              g2Temp2.setFont(FontUtils.scale(FONT, zoom));

              ec.setSize(w, h);

              ec.rasterCanvas(g2Temp2, context);
            } else {
              renderer = mCellRendererModel.get(i, j);

              Component c = renderer.getCellRendererComponent(this, getValueAt(i, j), false, highlight, isFocusOwner(),
                  i, j);

              c.setSize(w, h);

              c.print(g2Temp2);
            }

            // Move to the next cell location.
            g2Temp2.translate(w, 0);
          }
        } finally {
          g2Temp2.dispose();
        }

        // Each time we start a new row, translate back to the X origin.
        g2Temp.translate(0, h);
      }
    } finally {
      g2Temp.dispose();
    }

    createGridLines(g2, visibleCells);

    drawSelectionRectangle(g2, visibleCells);

    // Table header needs clipping so content doesn't run into corner
    createTableHeaderImage(g2, visibleCells);

    // Create the row header by clipping
    createTableRowHeaderImage(g2, visibleCells);

    createTableCornerImage(g2);

    drawBorder(g2);
  }

  /**
   * Creates the table background.
   *
   * @param g2           the g 2
   * @param context      the context
   * @param visibleCells the visible cells
   */
  protected void createTableBackground(Graphics2D g2, DrawingContext context, ModernDataSelection visibleCells) {
    g2.setColor(Color.WHITE);

    int x = mColumnModel.getCumOffset(visibleCells.getStartCol());
    int y = mRowModel.getCumOffset(visibleCells.getStartRow());

    // System.err.println(x + " " + y + " " +
    // mColumnModel.getCumOffset(visibleCells.getEndCol()) + " " +
    // mRowModel.getCumOffset(visibleCells.getEndRow()) );

    g2.fillRect(0, 0, mColumnModel.getCumWidth(visibleCells.getEndCol()) - x,
        mRowModel.getCumWidth(visibleCells.getEndRow()) - y);

  }

  /**
   * Creates the table header image.
   *
   * @param g2           the g 2
   * @param visibleCells the visible cells
   */
  protected void createTableHeaderImage(Graphics2D g2, ModernDataSelection visibleCells) {
    if (!mShowHeader) {
      return;
    }

    Graphics2D g2Temp = ImageUtils.clone(g2);

    try {
      translateX(g2Temp, visibleCells, mShowRowHeader);
      translateY(g2Temp, false);

      mHeader.drawForeground(g2Temp);
    } finally {
      g2Temp.dispose();
    }

    /*
     * ModernDataSelection visibleCells = calculateVisibleCells();
     * 
     * Graphics2D g2Temp = (Graphics2D)g2.create();
     * 
     * // translate to the start of the rendering rectangle so that we skip // all
     * non visible cells
     * 
     * translateX(g2Temp, visibleCells);
     * 
     * ModernDataCellRenderer renderer;
     * 
     * int w;
     * 
     * try { for (int i = visibleCells.getStartCol(); i <= visibleCells.getEndCol();
     * ++i) { if (i >= getColumnCount()) { break; }
     * 
     * renderer = mHeadingRenderModel.get(i);
     * 
     * Component c = renderer.getCellRendererComponent(this,
     * mmodel.getColumnHeader().getHeader(i), false, false, false, 0, i);
     * 
     * w = mColumnModel.getWidth(i);
     * 
     * c.setSize(w, mColumnModel.getHeaderSize());
     * 
     * c.print(g2Temp);
     * 
     * // Move to the next cell location. g2Temp.translate(w, 0); } } finally {
     * g2Temp.dispose(); }
     * 
     */
  }

  /**
   * Creates the table corner image.
   *
   * @param g2 the g 2
   */
  protected void createTableCornerImage(Graphics2D g2) {
    if (mShowHeader && mShowRowHeader) {

      Graphics2D g2Temp = ImageUtils.clone(g2);

      try {
        translate(g2Temp, false, false);

        mCorner.drawForeground(g2Temp);
      } finally {
        g2Temp.dispose();
      }

      /*
       * fill(g2, ModernTableHeaderCellRenderer.HEADER_BACKGROUND, new Rectangle(0, 0,
       * mRowModel.getHeaderSize(), mColumnModel.getHeaderSize()));
       * 
       * g2.setColor(ModernWidget.LINE_COLOR);
       * 
       * int p = mRowModel.getHeaderSize() - 1; g2.drawLine(p, 0, p, mRect.getH() -
       * 1);
       * 
       * p = mColumnModel.getHeaderSize() - 1; g2.drawLine(0, p, mRect.getW(), p);
       */
    }

  }

  /**
   * Creates the table row header image.
   *
   * @param g2           the g 2
   * @param visibleCells the visible cells
   */
  protected void createTableRowHeaderImage(Graphics2D g2, ModernDataSelection visibleCells) {
    if (!mShowRowHeader) {
      return;
    }

    Graphics2D g2Temp = ImageUtils.clone(g2);

    try {
      translateX(g2Temp, false);
      translateY(g2Temp, visibleCells, mShowHeader);

      // Rectangle rect = getVisibleRect();

      // int y = getColumnModel().getHeaderSize();
      // int w = getColumnModel().getWidth(0);
      // int h = rect.height - y;

      // g2Temp.clipRect(0, rect.y + y, w, h);

      mRowHeader.drawForeground(g2Temp);
    } finally {
      g2Temp.dispose();
    }

    // mRowHeader.drawForeground(g2);

    /*
     * ModernDataSelection visibleCells = calculateVisibleCells();
     * 
     * Graphics2D g2Temp = (Graphics2D)g2.create();
     * 
     * translateY(g2Temp, visibleCells);
     * 
     * ModernDataCellRenderer renderer;
     * 
     * int h;
     * 
     * try { for (int i = visibleCells.getStartRow(); i <= visibleCells.getEndRow();
     * ++i) { if (i >= getRowCount()) { break; }
     * 
     * renderer = mRowHeadingRenderModel.get(i);
     * 
     * Component c = renderer.getCellRendererComponent(this, mModel.getRowName(i),
     * false, false, false, i, 0);
     * 
     * h = mRowModel.getWidth(i);
     * 
     * c.setSize(mRowModel.getHeaderSize(), h);
     * 
     * c.print(g2Temp);
     * 
     * // Move to the next cell location. g2Temp.translate(0, h); } } finally {
     * g2Temp.dispose(); }
     */
  }

  /**
   * Draw selection rectangle.
   *
   * @param g2           the g 2
   * @param visibleCells the visible cells
   */
  private void drawSelectionRectangle(Graphics2D g2, ModernDataSelection visibleCells) {

    // System.err.println("sel " + mRowModel.getSelectionModel().size() + " " +
    // mColumnModel.getSelectionModel().size());

    double zoom = getZoomModel().getZoom();

    if (mRowModel.getSelectionModel().size() > 0 && mColumnModel.getSelectionModel().size() == 0) {
      // Row selected

      // always draw relative to the visible cells
      drawSelectionRow(g2, visibleCells);
    } else if (mRowModel.getSelectionModel().size() == 0 && mColumnModel.getSelectionModel().size() > 0) {
      // Column selected

      // always draw relative to the visible cells
      // int y = getY(0);
      // int x = invTranslateX(mColumnModel.getSelectionModel().first()); // -
      // getViewRectangle().getX());
      // int h = getY(getRowCount() - 1) + mRowModel.getWidth(getRowCount() - 1)
      // + 1;
      // int w = getX(mColumnModel.getSelectionModel().last()) -
      // getX(mColumnModel.getSelectionModel().first()) +
      // mColumnModel.getWidth(mColumnModel.getSelectionModel().last()) + 1;

      int y = 0; // getY(visibleCells.getStartRow()); //invTranslateY(getY(0));
      int x = getX(mColumnModel.getSelectionModel().first(), zoom) - getX(visibleCells.getStartCol(), zoom); // invTranslateX(getX(mColumnModel.getSelectionModel().first()));
      // // -
      // getViewRectangle().getY();
      int h = getY(visibleCells.getEndRow(), zoom) - getY(visibleCells.getStartRow(), zoom)
          + mRowModel.getWidth(visibleCells.getEndRow(), zoom); // getColumnCount()
      // - 1) +
      // mColumnModel.getWidth(getColumnCount()
      // - 1) + 1;
      int w = getX(mColumnModel.getSelectionModel().last(), zoom) - getX(mColumnModel.getSelectionModel().first())
          + mColumnModel.getWidth(mColumnModel.getSelectionModel().last(), zoom) + 1;

      // System.err.println("h " + getY(getRowCount() - 1) + " " +
      // mRowModel.getWidth(getRowCount() - 1));

      Graphics2D g2Temp = ImageUtils.clone(g2);

      try {
        translate(g2Temp, visibleCells, mShowHeader, mShowRowHeader);

        g2Temp.setColor(SELECTION_RECT_COLOR);

        g2Temp.setStroke(ModernTheme.DOUBLE_LINE_STROKE);

        g2Temp.drawLine(x, y, x, h);

        int p = x + w - 2;

        g2Temp.drawLine(p, y, p, h);

        // Skip the top line as the header will draw that
        // if (visibleCells.getStartRow() == 0) {
        //// g2Temp.drawLine(x, y, p, y);
        // }

        if (visibleCells.getEndRow() == (getRowCount() - 1)) {
          p = y + h - 1;
          g2Temp.drawLine(x, p, x + w - 2, p);
        }
      } finally {
        g2Temp.dispose();
      }
    } else {
      // A single cell is selected
      ModernDataCell cell = mSelectionModel.last();

      if (cell != null) {
        // always draw relative to the visible cells
        int x = getX(cell.col, zoom) - getX(visibleCells.getStartCol(), zoom);
        int y = getY(cell.row, zoom) - getY(visibleCells.getStartRow(), zoom);
        int w = mColumnModel.getWidth(cell.col, zoom);
        int h = mRowModel.getWidth(cell.row, zoom);

        Graphics2D g2Temp = ImageUtils.clone(g2);

        try {
          translate(g2Temp, visibleCells, mShowHeader, mShowRowHeader);

          g2Temp.setColor(SELECTION_RECT_COLOR);
          g2Temp.setStroke(ModernTheme.DOUBLE_LINE_STROKE);
          g2Temp.drawRect(x, y, w - 2, h - 2);

        } finally {
          g2Temp.dispose();
        }
      }
    }
  }

  /**
   * Draw selection row.
   *
   * @param g2           the g 2
   * @param visibleCells the visible cells
   */
  public void drawSelectionRow(Graphics2D g2, ModernDataSelection visibleCells) {

    double zoom = getZoomModel().getZoom();

    int x = 0; // getX(visibleCells.getStartCol()); //invTranslateX(getX(0));
    int y = getY(mRowModel.getSelectionModel().first()) - getY(visibleCells.getStartRow()); // invTranslateY(getY(mRowModel.getSelectionModel().first()));
    // // -
    // getViewRectangle().getY();
    int w = getX(visibleCells.getEndCol()) - getX(visibleCells.getStartCol())
        + mColumnModel.getWidth(visibleCells.getEndCol(), zoom) - 1; // getColumnCount()
    // - 1) +
    // mColumnModel.getWidth(getColumnCount()
    // - 1) +
    // 1;
    int h = getY(mRowModel.getSelectionModel().last()) - getY(mRowModel.getSelectionModel().first())
        + mRowModel.getWidth(mRowModel.getSelectionModel().last(), zoom);

    Graphics2D g2Temp = ImageUtils.clone(g2);

    try {
      translate(g2Temp, visibleCells, mShowHeader, mShowRowHeader);

      g2Temp.setColor(SELECTION_RECT_COLOR);
      g2Temp.setStroke(ModernTheme.DOUBLE_LINE_STROKE);

      g2Temp.drawLine(x, y, w, y);

      int p = y + h - 1;

      g2Temp.drawLine(x, p, w, p);

      // if (visibleCells.getStartCol() == 0) {
      // g2Temp.drawLine(x, y, x, p);
      // }

      if (visibleCells.getEndCol() == (getColCount() - 1)) {
        p = x + w;
        g2Temp.drawLine(p, y - 1, p, y + h - 1);
      }

      // g2Temp.drawRect(x, y, w - bo2, h - bo2);
    } finally {
      g2Temp.dispose();
    }
  }

  /**
   * Creates the grid lines.
   *
   * @param g2           the g 2
   * @param visibleCells the visible cells
   */
  protected void createGridLines(Graphics2D g2, ModernDataSelection visibleCells) {
    Graphics2D g2Temp;

    //
    // Columns
    //

    g2Temp = ImageUtils.clone(g2);

    try {
      // translate to the start of the rendering rectangle so that we skip
      // all non visible cells
      translateX(g2Temp, visibleCells, mShowRowHeader);
      translateY(g2Temp, mShowHeader);

      // createGridRowLines(g2Temp, visibleCells);
      createGridColumnLines(g2Temp, visibleCells);
    } finally {
      g2Temp.dispose();
    }

    //
    // Rows
    //

    g2Temp = ImageUtils.clone(g2);

    try {
      // translate to the start of the rendering rectangle so that we skip
      // all non visible cells
      translateX(g2Temp, mShowRowHeader);
      translateY(g2Temp, visibleCells, mShowHeader);

      createGridRowLines(g2Temp, visibleCells);
    } finally {
      g2Temp.dispose();
    }
  }

  /**
   * Creates the grid row lines.
   *
   * @param g2           the g2
   * @param visibleCells the visible cells
   */
  private void createGridRowLines(Graphics2D g2, ModernDataSelection visibleCells) {

    g2.setColor(LINE_COLOR);

    int y = -1;
    int w = 0;

    double zoom = getZoomModel().getZoom();

    /*
     * for (int i = visibleCells.getStartCol(); i <= visibleCells.getEndCol(); ++i)
     * { if (i >= mColumnModel.size()) { break; }
     * 
     * w += mColumnModel.getWidth(i, zoom); }
     */

    Rectangle rect = getVisibleRect();

    w = rect.width;

    // row lines

    int i = visibleCells.getStartRow();

    while (y < rect.height) {

      // for (int i = visibleCells.getStartRow(); i <= visibleCells.getEndRow();
      // ++i)
      // {
      // if (i >= mRowModel.size()) {
      // break;
      // }

      y += mRowModel.getWidth(i, zoom);
      ++i;

      g2.drawLine(0, y, w, y);
    }
  }

  protected void drawBorder(Graphics2D g2) {
    // Do nothing
  }

  /**
   * Creates the grid column lines.
   *
   * @param g2           the g2
   * @param visibleCells the visible cells
   */
  private void createGridColumnLines(Graphics2D g2, ModernDataSelection visibleCells) {

    g2.setColor(LINE_COLOR);

    // Draw grid line in last pixel of cell
    int x = -1;

    int h = 0;

    double zoom = getZoomModel().getZoom();

    /*
     * for (int i = visibleCells.getStartRow(); i <= visibleCells.getEndRow(); ++i)
     * { if (i >= mRowModel.size()) { break; }
     * 
     * h += mRowModel.getWidth(i, zoom); }
     */

    Rectangle rect = getVisibleRect();

    h = rect.height;

    // column lines

    int i = visibleCells.getStartCol();

    while (x < rect.width) {

      // for (int i = visibleCells.getStartCol(); i <= visibleCells.getEndCol();
      // ++i)
      // {
      // if (mColumnModel == null) {
      // break;
      // }

      // if (i >= mColumnModel.size()) {
      // break;
      // }

      x += mColumnModel.getWidth(i, zoom);
      ++i;

      g2.drawLine(x, 0, x, h);
    }
  }

  /**
   * Returns the value at a given row, column on the table. Since the table may be
   * sorted, getValue may return a different value to getModel().getValue() since
   * that returns the ordering of the data as it was loaded.
   *
   * @param row the row
   * @param col the col
   * @return the value at
   */
  @Override
  public Object getValueAt(int row, int col) {
    if (mModel == null) {
      return null;
    }

    if (!Mathematics.inBound(row, 0, getRowCount())) {
      return null;
    }

    if (!Mathematics.inBound(col, 0, getColCount())) {
      return null;
    }

    return mModel.getValueAt(mRowSortModel.getSorter().getModelIndex(row),
        mColSortModel.getSorter().getModelIndex(col));
  }

  /**
   * Sets the value at a given row, column on the table. Since the table may be
   * sorted, getValue may return a different value to getModel().getValue() since
   * that returns the ordering of the data as it was loaded.
   *
   * @param row   the row
   * @param col   the col
   * @param value the value
   * @parm value
   */
  @Override
  public void setValueAt(int row, int col, Object value) {
    if (mModel == null) {
      return;
    }

    mModel.setValueAt(mRowSortModel.getSorter().getModelIndex(row), mColSortModel.getSorter().getModelIndex(col),
        value);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernData#dataChanged(org.abh.lib.event.
   * ChangeEvent)
   */
  @Override
  public void dataChanged(ChangeEvent e) {
    // if the data changes, change the sort model.

    // If the data changes, update any current sorter
    mRowSortModel.getSorter().sort(this, mRowSortModel.getSortIndex(),
        mRowSortModel.get(mRowSortModel.getSortIndex()).getSortAscending());

    super.dataChanged(e);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.graphics.ModernCanvas#translateCoordinate(int, int)
   */
  @Override
  public IntPos2D translateCoordinate(int x, int y) {
    // Adjust x and y to account for headers.

    // x += getViewRect().getX();

    if (mShowRowHeader) {
      x -= (int) scale(mRowModel.getHeaderSize());
    }

    // y += getViewRect().getY();

    if (mShowHeader) {
      y -= (int) scale(mColumnModel.getHeaderSize());
    }

    // System.err.println("table " + x + " " + y + " " + getViewRectangle());

    return new IntPos2D(x, y);

    // return new Point(x, y);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernData#getCell(java.awt.Point)
   */
  @Override
  protected ModernDataCell getCell(IntPos2D p) {
    int x = 0;

    int column = -1;

    for (int i = 0; i < mColumnModel.size(); ++i) {
      x += (int) scale(mColumnModel.getWidth(i));

      if (x > p.getX()) {
        column = i;

        break;
      }
    }

    x = 0;

    int row = -1;

    for (int i = 0; i < mRowModel.size(); ++i) {
      x += (int) scale(mRowModel.getWidth(i));

      if (x > p.getY()) {
        row = i;

        break;
      }
    }

    // if a valid cell is not selected, stop here
    if (row == -1 || column == -1) {
      return null;
    }

    return new ModernDataCell(row, column);
  }

  /**
   * Selects a cell if the mouse is over a valid cell when clicked.
   *
   * @param row              the row
   * @param column           the column
   * @param multiRowSelect   the multi row select
   * @param multiRangeSelect the multi range select
   */
  @Override
  public void setSelectedCell(int row, int column, boolean multiRowSelect, boolean multiRangeSelect) {

    // First ensure none of the rows or columns are selected.
    mColumnModel.unselectAll();
    mRowModel.unselectAll();

    if ((!multiRowSelect && !multiRangeSelect) || mSelectionPolicy != SelectionPolicy.MULTIPLE) {
      // If we are not selecting multiple cells, clear
      // the current selection
      mSelectionModel.clear();
    }

    // For the rows, use updates to prevent triggering multiple selection
    // events

    if (multiRowSelect) {
      if (mSelectionModel.getRowSelectionModel().contains(row)) {
        // If we are multi selecting and pick something we have
        // already selected, then remove it.

        mSelectionModel.getRowSelectionModel().remove(row);
      } else {
        mSelectionModel.getRowSelectionModel().update(row, SelectionRangeType.ADD);
      }
    } else if (multiRangeSelect && mSelectionModel.getRowSelectionModel().size() > 0) {
      // since we are adding intermediates, we do not
      // add the current first and last in duplicate,
      // hence the index begins one past the min and
      // ends one before the max
      mSelectionModel.getRowSelectionModel().updateSelectionInterval(mSelectionModel.getRowSelectionModel().first(),
          row, SelectionRangeType.REPLACE);
    } else {
      mSelectionModel.getRowSelectionModel().update(row, SelectionRangeType.REPLACE);
    }

    // Updating the column causes a selection event to be triggered.

    if (multiRowSelect) {
      if (mSelectionModel.getColumnSelectionModel().contains(column)) {
        // If we are multi selecting and pick something we have
        // already selected, then remove it.

        mSelectionModel.getColumnSelectionModel().remove(column);
      } else {
        mSelectionModel.getColumnSelectionModel().add(column, SelectionRangeType.ADD);
      }
    } else if (multiRangeSelect && mSelectionModel.getColumnSelectionModel().size() > 0) {
      // since we are adding intermediates, we do not
      // add the current first and last in duplicate,
      // hence the index begins one past the min and
      // ends one before the max
      mSelectionModel.getColumnSelectionModel().addSelectionInterval(mSelectionModel.getColumnSelectionModel().first(),
          column, SelectionRangeType.REPLACE);
    } else {
      mSelectionModel.getColumnSelectionModel().add(column, SelectionRangeType.REPLACE);
    }

    updateSelectedCell();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernData#setViewRectangle(int, int)
   */
  public final void setViewRectangle(int x, int y) {
    super.setViewRectangle(x, y);

    refreshView();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernData#refreshView()
   */
  protected final void refreshView() {
    // calculateVisibleCells();

    adjustEditor();

    revalidate();
    repaint();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.dataview.ModernData#removeEditor()
   */
  @Override
  public void removeEditor() {
    mCurrentEditor = null;

    // System.err.println("remove editor");

    if (mCurrentEditorComponent != null && mCurrentEditorComponent instanceof ModernTextBox) {
      ModernTextBox t = (ModernTextBox) mCurrentEditorComponent;
      t.flash(false);
      t.removeCanvasListener(this);
      removeKeyListener(t);
      /// t.setEnabled(false);
    }

    mCurrentEditorComponent = null;

    fireCanvasRedraw();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.dataview.ModernData#updateSelectedCell()
   */
  @Override
  protected void updateSelectedCell() {

    removeEditor();

    ModernDataCell selectedCell = mSelectionModel.last();

    if (selectedCell == null) {
      return;
    }

    if (mModel == null) {
      return;
    }

    if (!mModel.isCellEditable(selectedCell)) {
      return;
    }

    mCurrentEditor = getEditor(selectedCell);

    mCurrentEditor.addEditorListener(new EditorEvents());

    mCurrentEditorComponent = mCurrentEditor.getCellEditorComponent(this, getValueAt(selectedCell), true, true, true,
        selectedCell.row, selectedCell.col);

    if (mCurrentEditorComponent instanceof ModernTextBox) {
      ModernTextBox t = (ModernTextBox) mCurrentEditorComponent;

      // This is added so that table refreshes when cell caret flashes
      t.addCanvasListener(this);
      t.addCanvasCursorListener(this);

      addCanvasKeyListener(t);
      t.flash(true);
      // Only responds after a double click
      // t.setEnabled(false);
      t.setFont(FontUtils.scale(getFont(), getZoomModel().getZoom()));
    }

    fireCanvasRedraw();
  }

  /**
   * Ensures the ui is consistent with the selected cell.
   */
  /*
   * protected void updateSelectedCell() {
   * 
   * removeEditor();
   * 
   * DataViewCell selectedCell = cellsSelectionModel.last();
   * 
   * if (selectedCell == null) { return; }
   * 
   * if (!model.isCellEditable(selectedCell.row, selectedCell.column)) { return; }
   * 
   * currentEditor = columnModel.get(selectedCell.column).getEditor();
   * 
   * currentEditor.addCellEditorListener(this);
   * 
   * currentEditorComponent = currentEditor.getTableCellEditorComponent(this,
   * getValueAt(selectedCell.row, selectedCell.column), true, selectedCell.row,
   * selectedCell.column);
   * 
   * //currentEditorComponent = new JButton("test");
   * 
   * //((JButton)currentEditorComponent).setBackground(Color.BLUE);
   * 
   * 
   * // Add the component onto the table so the user can see it
   * add(currentEditorComponent);
   * 
   * adjustEditor();
   * 
   * revalidate(); repaint(); }
   */

  @Override
  protected final void adjustEditor() {
    // Do nothing
  }

  /**
   * Sets the sort column.
   *
   * @param column the new sort column
   */
  public void setSortColumn(int column) {
    if (mModel == null) {
      return;
    }

    // maintain the sort order if we switch columns or else
    // invert the sort if we are on the same column to toggle
    // the sort order
    mRowSortModel.setSortIndex(column);

    mRowSortModel.getSorter().sort(this, column, getSortCol() == column ? !mRowSortModel.get(column).getSortAscending()
        : mRowSortModel.get(column).getSortAscending());

    adjustSize();
  }

  /**
   * Sets the row height.
   *
   * @param rowHeight the new row height
   */
  public void setRowHeight(int rowHeight) {
    getRowModel().setWidth(rowHeight);
  }

  /**
   * Gets the row height.
   *
   * @return the row height
   */
  public int getRowHeight() {
    return getRowModel().getWidth();
  }

  /*
   * @Override public void keyPressed(KeyEvent e) { if (e.getKeyCode() ==
   * KeyEvent.VK_A && e.isControlDown()) { selectAll(); } else if (e.getKeyCode()
   * == KeyEvent.VK_C && e.isControlDown()) { copy(); } else { // Do nothing } }
   * 
   * @Override public void keyReleased(KeyEvent e) { // TODO Auto-generated method
   * stub }
   * 
   * @Override public void keyTyped(KeyEvent arg0) { // TODO Auto-generated method
   * stub
   * 
   * }
   */

  /**
   * Converts a row to that of the model. If a sort model is in use, this method
   * will map the selected row to the actual row it corresponds to in the unsorted
   * model.
   *
   * @param i the i
   * @return the model row index
   */
  public int getModelRowIndex(int i) {
    if (mModel == null) {
      return -1;
    }

    return mRowSortModel.getSorter().getModelIndex(i);
  }

  /**
   * Gets the column model.
   *
   * @return the column model
   */
  public TableIndexModel getColumnModel() {
    return mColumnModel;
  }

  /**
   * Gets the row model.
   *
   * @return the row model
   */
  public TableIndexModel getRowModel() {
    return mRowModel;
  }

  /**
   * Gets the renderer.
   *
   * @param col the col
   * @return the renderer
   */
  public ModernDataCellRenderer getHeadingRenderer(int col) {
    return mHeadingRenderModel.get(col);
  }

  /**
   * Gets the renderer model.
   *
   * @return the renderer model
   */
  public ModernTableHeadingRendererModel getHeadingRendererModel() {
    return mHeadingRenderModel;
  }

  /**
   * Gets the row heading renderer.
   *
   * @param col the col
   * @return the row heading renderer
   */
  public ModernDataCellRenderer getRowHeadingRenderer(int col) {
    return mRowHeadingRenderModel.get(col);
  }

  /**
   * Gets the renderer model.
   *
   * @return the renderer model
   */
  public ModernTableHeadingRendererModel getRowHeadingRenderer() {
    return mRowHeadingRenderModel;
  }

  // @Override
  // public void updateCellValue() {
  // super.updateCellValue();

  // moveDown();
  // }

  /**
   * Move down.
   */
  private void moveDown() {
    ModernDataCell cell = mSelectionModel.last();

    if (cell != null) {
      setSelectedCell(Math.min(getRowCount() - 1, cell.row + 1), cell.col, false, false);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.graphics.ModernCanvasListener#canvasChanged(org.abh.
   * common. event.ChangeEvent)
   */
  @Override
  public void canvasChanged(ChangeEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.graphics.ModernCanvasListener#redrawCanvas(org.abh.
   * common. event.ChangeEvent)
   */
  @Override
  public void redrawCanvas(ChangeEvent e) {
    // Respond to underlying textbox
    fireCanvasRedraw();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.graphics.ModernCanvasListener#canvasScrolled(org.abh.
   * common .event.ChangeEvent)
   */
  @Override
  public void canvasScrolled(ChangeEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.graphics.ModernCanvasListener#canvasResized(org.abh.
   * common. event.ChangeEvent)
   */
  @Override
  public void canvasResized(ChangeEvent e) {
    // TODO Auto-generated method stub

  }

  /**
   * Translate a table x y coordinate to that of a cell so we can pass the
   * relative cell coordinates to the cell rather than the table coordinates.
   *
   * @param e    the e
   * @param cell the cell
   * @return the int pos 2 D
   */
  private IntPos2D toCellSpace(CanvasMouseEvent e, ModernDataCell cell) {
    IntPos2D p = e.getScaledPos();

    double zoom = getZoomModel().getZoom();

    int x = p.getX() - getColumnModel().getCumOffset(cell.col, zoom);

    // if (mShowRowHeader) {
    // x -= mRowModel.getHeaderSize();
    // }

    int y = p.getY() - getRowModel().getCumOffset(cell.row, zoom);

    // if (mShowHeader) {
    // y -= mColumnModel.getHeaderSize();
    // }

    // If the x y are outside the cells boundary, reset them to -1

    if (x >= getColumnModel().getWidth(cell.col, zoom)) {
      x = -1;
    }

    if (y >= getRowModel().getWidth(cell.row, zoom)) {
      y = -1;
    }

    IntPos2D np = new IntPos2D(x, y);

    // System.err.println("cell space " + p + " " + np);

    return np;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.graphics.CanvasCursorListener#cursorChanged(org.abh.
   * common. ui.graphics.CanvasCursorEvent)
   */
  @Override
  public void cursorChanged(CanvasCursorEvent e) {
    setCursor(e.getCursor());
  }
}
