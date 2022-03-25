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
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.geom.IntPos2D;
import org.jebtk.core.geom.IntRect;
import org.jebtk.core.text.TextUtils;
import org.jebtk.modern.SelectionPolicy;
import org.jebtk.modern.clipboard.ClipboardService;
import org.jebtk.modern.dataview.sort.ModernDataSortModel;
import org.jebtk.modern.event.ModernSelectionListener;
import org.jebtk.modern.graphics.CanvasAdapter;
import org.jebtk.modern.zoom.ZoomCanvas;
import org.jebtk.modern.zoom.ZoomModel;
import org.jebtk.modern.clipboard.IClipboardUI;

// TODO: Auto-generated Javadoc
/**
 * Represents a UI view onto a data model (a row/col matrix of data).
 * 
 * @author Antony Holmes
 *
 */
public abstract class ModernData extends ZoomCanvas implements ModernDataViewEventProducer, IClipboardUI,
    ModernSelectionListener, ModernDataViewListener, FocusListener, ComponentListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member model.
   */
  protected ModernDataModel mModel;

  /**
   * The member cell renderer model.
   */
  protected ModernDataCellRendererModel mCellRendererModel = new ModernDataCellRendererModel();

  /**
   * The member cell editor model.
   */
  protected ModernDataCellEditorModel mCellEditorModel = new ModernDataCellEditorModel();

  /**
   * The member selection model.
   */
  protected ModernDataCellsSelectionModel mSelectionModel = new ModernDataCellsSelectionModel();

  /**
   * Keeps track of the ways the rows can be sorted. It can store multiple sorters
   * (one for each col) to determine how data is presented.
   */
  protected ModernDataSortModel mRowSortModel = new ModernDataSortModel();

  /**
   * The member col sort model.
   */
  protected ModernDataSortModel mColSortModel = new ModernDataSortModel();

  /*
   * protected Map<Integer, Map<Integer, AbstractFlatTableCellRenderer>>
   * cellRenderers = new HashMap<Integer, Map<Integer,
   * AbstractFlatTableCellRenderer>>();
   */

  // protected List<TableCell> selectedCells = new ArrayList<TableCell>();

  // protected List<Integer> selectedRows = new ArrayList<Integer>();

  /**
   * The member current editor component.
   */
  protected Component mCurrentEditorComponent = null;

  /**
   * The member current editor.
   */
  protected ModernDataCellEditor mCurrentEditor = null;

  // protected boolean isSortable = true;

  // private Dimension tableSize = new Dimension(-1, -1);

  // protected ModernDataSelection visibleCells = new ModernDataSelection();

  /**
   * The member selection policy.
   */
  protected SelectionPolicy mSelectionPolicy = SelectionPolicy.MULTIPLE;

  // The canvas offset specifies where the drawing view is but
  // when drawing cells we must start at the point where the cells
  // begin and not the view rectangle
  // protected Point tableCellOffset = new Point(0, 0);
  // protected Point drawingOffset = new Point(0, 0);

  // protected Point.Double viewOffset = new Point.Double();

  /**
   * The member data listeners.
   */
  protected ModernDataViewListeners mDataListeners = new ModernDataViewListeners();

  /**
   * The member cols.
   */
  protected int mCols;

  /**
   * The member rows.
   */
  protected int mRows;

  /**
   * The class EditorEvents.
   */
  public class EditorEvents implements ModernDataEditorListener {

    @Override
    public void changed(ChangeEvent e) {
      updateCellValue();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.lib.ui.modern.dataview.ModernDataEditorListener#editingStopped()
     */
    @Override
    public void editingStopped() {
      stopEditing();
    }

  }

  /**
   * If the user scrolls, stop all editing.
   * 
   * @author Antony Holmes
   *
   */
  private class CanvasEvents extends CanvasAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.abh.lib.ui.modern.graphics.ModernCanvasAdapter#canvasChanged(org.abh.
     * lib. event.ChangeEvent)
     */
    @Override
    public void canvasChanged(ChangeEvent e) {
      stopEditing();
    }
  }

  /**
   * Instantiates a new modern data.
   */
  public ModernData() {

    setFocusable(true);

    // addMouseListener(new MouseEvents());

    addComponentListener(this);
    addCanvasListener(new CanvasEvents());

    setLayout(null);

    mCellRendererModel.addDataViewListener(this);
    mCellEditorModel.addDataViewListener(this);

    mSelectionModel.addSelectionListener(this);

    // Register so we know this component responds to clipboard events.

    ClipboardService.getInstance().register(this);

    // Enable copy via keyboard
    getInputMap().put(KeyStroke.getKeyStroke("ctrl C"), "copy");
    getActionMap().put("copy", new AbstractAction() {

      private static final long serialVersionUID = 1L;

      @Override
      public void actionPerformed(ActionEvent e) {
        copy();
      }
    });
  }

  public ModernData(ZoomModel zoomModel) {
    this();

    setZoomModel(zoomModel);
  }

  /**
   * Gets the model.
   *
   * @return the model
   */
  public final ModernDataModel getModel() {
    return mModel;
  }

  /**
   * Sets the model.
   *
   * @param model the new model
   */
  public void setModel(ModernDataModel model) {
    mModel = model;

    model.addDataViewListener(this);

    updateViewModels();

    fireDataChanged();
  }

  /**
   * Gets the row sort model.
   *
   * @return the row sort model
   */
  public ModernDataSortModel getRowSortModel() {
    return mRowSortModel;
  }

  /**
   * Update the views to reflect the table data, i.e ensure there is a col and row
   * view to represent each column and row in the table data.
   */
  protected void updateViewModels() {
    mSelectionModel.clear();
  }

  /**
   * Gets the col count.
   *
   * @return the col count
   */
  public int getColCount() {
    return 0;
  }

  /**
   * Gets the row count.
   *
   * @return the row count
   */
  public int getRowCount() {
    return 0;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvas#updateViewRectangle(org.abh.
   * lib. IntRect)
   */
  @Override
  public void updateViewRectangle(IntRect rect) {
    // If the view changes, remove text boxes etc from the display

    stopEditing();

    super.updateViewRectangle(rect);
  }

  /**
   * Adjust the size of the table image.
   */
  public void adjustSize() {
    //
  }

  @Override
  public void selectionAdded(ChangeEvent e) {
    selectionRemoved(e);
  }

  @Override
  public void selectionRemoved(ChangeEvent e) {
    fireCanvasRedraw();
  }

  /**
   * Returns the cumulative distance from 0 of a particular col.
   *
   * @param col the col
   * @return the x
   */
  public abstract int getX(int col);

  /**
   * Returns the cumulative distance from 0 of a particular row.
   *
   * @param row the row
   * @return the y
   */
  public abstract int getY(int row);

  /**
   * Returns the col for a given position x.
   *
   * @param x the x
   * @return the col
   */
  public abstract int getCol(int x);

  /**
   * Returns the row for a given position y.
   *
   * @param y the y
   * @return the row
   */
  public abstract int getRow(int y);

  /**
   * Gets the cell.
   *
   * @param p the p
   * @return the cell
   */
  protected ModernDataCell getCell(IntPos2D p) {
    return null;
  }

  /**
   * Gets the cell.
   *
   * @param index the index
   * @return the cell
   */
  protected ModernDataCell getCell(int index) {
    return null;
  }

  /**
   * Gets the index.
   *
   * @param cell the cell
   * @return the index
   */
  protected final int getIndex(ModernDataCell cell) {
    if (cell == null) {
      return -1;
    }

    return getIndex(cell.row, cell.col);
  }

  /**
   * Gets the index.
   *
   * @param row the row
   * @param col the col
   * @return the index
   */
  protected int getIndex(int row, int col) {
    if (col >= mCols) {
      return -1;
    }

    if (row >= mRows) {
      return -1;
    }

    return row * mCols + col;
  }

  /**
   * Gets the index.
   *
   * @param p the p
   * @return the index
   */
  protected int getIndex(IntPos2D p) {
    return -1;
  }

  /**
   * Gets the point.
   *
   * @param index the index
   * @return the point
   */
  protected Point getPoint(int index) {
    return null;
  }

  /**
   * Gets the point.
   *
   * @param cell the cell
   * @return the point
   */
  protected final Point getPoint(ModernDataCell cell) {
    return getPoint(cell.row, cell.col);
  }

  /**
   * Gets the point.
   *
   * @param row the row
   * @param col the col
   * @return the point
   */
  protected Point getPoint(int row, int col) {
    return null;
  }

  /*
   * @Override public void translate(Graphics2D g2) { ModernDataSelection
   * visibleCells = calculateVisibleCells();
   * 
   * g2.translate(getX(visibleCells.getStartCol()) - getViewRect().getX(),
   * getY(visibleCells.getStartRow()) - getViewRect().getY()); }
   */

  /**
   * Calculates the range of cells visible at any given time.
   *
   * @return the modern data selection
   */
  protected abstract ModernDataSelection calculateVisibleCells();

  /**
   * Sets the selected cell.
   *
   * @param cell             the cell
   * @param multiRowSelect   the multi row select
   * @param multiRangeSelect the multi range select
   */
  public final void setSelectedCell(ModernDataCell cell, boolean multiRowSelect, boolean multiRangeSelect) {
    if (cell == null) {
      return;
    }

    setSelectedCell(cell.row, cell.col, multiRowSelect, multiRangeSelect);
  }

  /**
   * Selects a cell if the mouse is over a valid cell when clicked.
   *
   * @param row              the row
   * @param col              the col
   * @param multiRowSelect   the multi row select
   * @param multiRangeSelect the multi range select
   */
  public abstract void setSelectedCell(int row, int col, boolean multiRowSelect, boolean multiRangeSelect);

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvas#setViewRectangle(int, int)
   */
  public void setViewRectangle(int x, int y) {
    super.setViewRectangle(x, y);

    refreshView();
  }

  /**
   * Refresh view.
   */
  protected void refreshView() {
    adjustEditor();

    revalidate();
    repaint();
  }

  /**
   * Sets the selection policy.
   *
   * @param selectionPolicy the new selection policy
   */
  public void setSelectionPolicy(SelectionPolicy selectionPolicy) {
    mSelectionPolicy = selectionPolicy;
  }

  /**
   * Remove the current editor from the display so the user cannot see it.
   */
  public void removeEditor() {
    mCurrentEditor = null;

    if (mCurrentEditorComponent != null) {
      remove(mCurrentEditorComponent);

      mCurrentEditorComponent = null;

      revalidate();
      repaint();

      // requestFocusInWindow();
    }
  }

  /**
   * Gets the renderer.
   *
   * @param cell the cell
   * @return the renderer
   */
  public ModernDataCellRenderer getRenderer(ModernDataCell cell) {
    return getRenderer(cell.row, cell.col);
  }

  /**
   * Gets the renderer.
   *
   * @param row the row
   * @param col the col
   * @return the renderer
   */
  public ModernDataCellRenderer getRenderer(int row, int col) {
    return mCellRendererModel.get(row, col);
  }

  /**
   * Gets the renderer model.
   *
   * @return the renderer model
   */
  public ModernDataCellRendererModel getRendererModel() {
    return mCellRendererModel;
  }

  /**
   * Gets the editor.
   *
   * @param cell the cell
   * @return the editor
   */
  public ModernDataCellEditor getEditor(ModernDataCell cell) {
    return getEditor(cell.row, cell.col);
  }

  /**
   * Gets the editor.
   *
   * @param row the row
   * @param col the col
   * @return the editor
   */
  public ModernDataCellEditor getEditor(int row, int col) {
    return mCellEditorModel.get(row, col);
  }

  /**
   * Gets the editor model.
   *
   * @return the editor model
   */
  public ModernDataCellEditorModel getEditorModel() {
    return mCellEditorModel;
  }

  /**
   * Update cell value.
   */
  public void updateCellValue() {
    ModernDataCell cell = mSelectionModel.last();

    if (cell == null) {
      return;
    }

    if (mCurrentEditor != null) {
      setValueAt(cell, mCurrentEditor.getCellEditorValue());
    }

    // move the selection onto the next row in the same col
    // so it is more obvious that the user made changes

    // cell.row = (cell.row + 1) % model.getRowCount();
  }

  /**
   * Stop editing.
   */
  public void stopEditing() {
    removeEditor();
  }

  /**
   * Ensures the ui is consistent with the selected cell.
   */
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

    // Add the component onto the table so the user can see it
    add(mCurrentEditorComponent);

    adjustEditor();

    revalidate();
    repaint();
  }

  /**
   * Adjust editor.
   */
  protected void adjustEditor() {
    if (mCurrentEditor != null) {
      SwingUtilities.invokeLater(() -> {
        mCurrentEditor.setFocus();
      });
    }
  }

  /**
   * Gets the value at.
   *
   * @param cell the cell
   * @return the value at
   */
  public Object getValueAt(ModernDataCell cell) {
    if (cell == null) {
      return null;
    }

    return getValueAt(cell.row, cell.col);
  }

  /**
   * Returns the value at a given row, col on the table. Since the table may be
   * sorted, getValue may return a different value to getModel().getValue() since
   * that returns the ordering of the data as it was loaded.
   *
   * @param row    the row
   * @param column the column
   * @return the value at
   */
  public abstract Object getValueAt(int row, int column);

  /**
   * Gets the int value at.
   *
   * @param row    the row
   * @param column the column
   * @return the int value at
   */
  public int getIntValueAt(int row, int column) {
    Object v = getValueAt(row, column);

    if (v != null) {
      try {
        return TextUtils.parseInt(v.toString());
      } catch (Exception e) {
        return Integer.MIN_VALUE;
      }
    } else {
      return Integer.MIN_VALUE;
    }
  }

  /**
   * Gets the double value at.
   *
   * @param row    the row
   * @param column the column
   * @return the double value at
   */
  public double getDoubleValueAt(int row, int column) {
    Object v = getValueAt(row, column);

    if (v != null) {
      try {
        return Double.parseDouble(v.toString());
      } catch (Exception e) {
        return Double.NaN;
      }
    } else {
      return Double.NaN;
    }
  }

  /**
   * Sets the value at.
   *
   * @param cell  the cell
   * @param value the value
   */
  public void setValueAt(ModernDataCell cell, Object value) {
    if (cell == null) {
      return;
    }

    setValueAt(cell.row, cell.col, value);
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
  public abstract void setValueAt(int i, int j, Object value);

  /**
   * Gets the sort col.
   *
   * @return the sort col
   */
  public int getSortCol() {
    return mRowSortModel.getSortIndex();
  }

  /**
   * Gets the sort ascending.
   *
   * @return the sort ascending
   */
  public boolean getSortAscending() {
    return mRowSortModel.getSorter().getSortAscending();
  }

  /**
   * Gets the selected col.
   *
   * @return the selected col
   */
  public int getSelectedCol() {
    return mSelectionModel.getSelectedCol();
  }

  /**
   * Gets the selected row.
   *
   * @return the selected row
   */
  public int getSelectedRow() {
    return mSelectionModel.getSelectedRow();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.clipboard.Clipboard#copy()
   */
  @Override
  public void copy() {
    ModernDataCell cell = mSelectionModel.last();

    if (cell == null) {
      return;
    }

    ClipboardService.copyToClipboard(getValueAt(cell).toString());
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.clipboard.Clipboard#paste()
   */
  @Override
  public void paste() {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.clipboard.Clipboard#cut()
   */
  @Override
  public void cut() {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.clipboard.ClipboardUiControl#cutEnabled()
   */
  @Override
  public boolean cutEnabled() {
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.clipboard.ClipboardUiControl#copyEnabled()
   */
  @Override
  public boolean copyEnabled() {
    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.clipboard.ClipboardUiControl#pasteEnabled()
   */
  @Override
  public boolean pasteEnabled() {
    return false;
  }

  /**
   * Gets the cell selection model.
   *
   * @return the cell selection model
   */
  public ModernDataCellsSelectionModel getCellSelectionModel() {
    return mSelectionModel;
  }

  /**
   * Selects all cells or rows depending on the table type.
   */
  public void selectAll() {
    mSelectionModel.setSelection(0, getRowCount() - 1, 0, getColCount() - 1);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataViewEventProducer#
   * addDataViewListener(org.abh.lib.ui.modern.dataview.ModernDataViewListener)
   */
  @Override
  public void addDataViewListener(ModernDataViewListener l) {
    mDataListeners.addDataViewListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataViewEventProducer#
   * removeDataViewListener(org.abh.lib.ui.modern.dataview.
   * ModernDataViewListener)
   */
  @Override
  public void removeDataViewListener(ModernDataViewListener l) {
    mDataListeners.removeDataViewListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.dataview.ModernDataViewEventProducer#fireDataChanged(
   * org.abh.lib.event.ChangeEvent)
   */
  @Override
  public void fireDataChanged(ChangeEvent e) {
    mDataListeners.fireDataChanged(e);
  }

  /**
   * Fire data changed.
   */
  public void fireDataChanged() {
    adjustSize();

    fireDataChanged(new ChangeEvent(this));
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.dataview.ModernDataViewEventProducer#fireDataUpdated(
   * org.abh.lib.event.ChangeEvent)
   */
  @Override
  public void fireDataUpdated(ChangeEvent e) {
    mDataListeners.fireDataUpdated(e);
  }

  /**
   * Fire data updated.
   */
  public void fireDataUpdated() {
    fireCanvasRedraw();

    fireDataUpdated(new ChangeEvent(this));
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.dataview.ModernDataViewListener#dataChanged(org.abh.
   * lib .event.ChangeEvent)
   */
  @Override
  public void dataChanged(ChangeEvent e) {
    fireDataChanged();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.dataview.ModernDataViewListener#dataUpdated(org.abh.
   * lib .event.ChangeEvent)
   */
  @Override
  public void dataUpdated(ChangeEvent e) {
    fireDataUpdated();
  }

  /**
   * Converts a row to that of the model. If a sort model is in use, this method
   * will map the selected row to the actual row it corresponds to in the unsorted
   * model.
   *
   * @param i the i
   * @return the model row index
   */
  public abstract int getModelRowIndex(int i);

  /**
   * Gets the checks if is cell enabled.
   *
   * @param row the row
   * @param col the col
   * @return the checks if is cell enabled
   */
  public boolean getIsCellEnabled(int row, int col) {
    return mModel != null && mModel.getIsCellEnabled(row, col);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
   */
  @Override
  public void focusLost(FocusEvent e) {
    repaint();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
   */
  @Override
  public void focusGained(FocusEvent e) {
    repaint();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.ComponentListener#componentResized(java.awt.event.
   * ComponentEvent)
   */
  @Override
  public void componentResized(ComponentEvent e) {
    refreshView();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.ComponentListener#componentHidden(java.awt.event.
   * ComponentEvent)
   */
  @Override
  public void componentHidden(ComponentEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.ComponentListener#componentMoved(java.awt.event.
   * ComponentEvent)
   */
  @Override
  public void componentMoved(ComponentEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.ComponentListener#componentShown(java.awt.event.
   * ComponentEvent)
   */
  @Override
  public void componentShown(ComponentEvent e) {
    // TODO Auto-generated method stub

  }
}
