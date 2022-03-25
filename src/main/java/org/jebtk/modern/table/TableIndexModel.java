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

import java.util.Map;
import java.util.TreeMap;

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.event.ChangeListeners;
import org.jebtk.modern.SelectionRangeModel;
import org.jebtk.modern.event.ModernSelectionEventProducer;
import org.jebtk.modern.event.ModernSelectionListener;

/**
 * Represents a collection of rows or columns in the table specifying how they
 * should be rendered.
 *
 * @author Antony Holmes
 *
 */
public class TableIndexModel extends ChangeListeners implements ModernSelectionEventProducer {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The Constant DEFAULT_CELL_WIDTH. */
  public static final int DEFAULT_CELL_WIDTH = 80;

  /**
   * The constant WIDTH_CHANGE_EVENT.
   */
  public static final String WIDTH_CHANGE_EVENT = "width_changed";

  /**
   * The member indices.
   */
  private Map<Integer, TableIndex> mIndices = new TreeMap<Integer, TableIndex>();

  /**
   * The member selection model.
   */
  private SelectionRangeModel mSelectionModel = new SelectionRangeModel();

  /**
   * The member default index.
   */
  private TableIndex mDefaultIndex;

  /**
   * The member size.
   */
  private int mSize = -1;

  /** The m header size. */
  private int mHeaderSize = DEFAULT_CELL_WIDTH;

  /**
   * Instantiates a new table index model.
   *
   * @param width      the width
   * @param headerSize the header size
   */
  public TableIndexModel(int width, int headerSize) {
    this(new TableIndex(width), headerSize);
  }

  /**
   * Instantiates a new table index model.
   *
   * @param defaultIndex the default index
   * @param headerSize   the header size
   */
  public TableIndexModel(TableIndex defaultIndex, int headerSize) {
    setDefaultIndex(defaultIndex);

    setHeaderSize(headerSize);
  }

  /**
   * Sets the size.
   *
   * @param size the new size
   */
  public void setSize(int size) {
    mSize = size;

    // fireClicked();
  }

  /**
   * Sets the default index.
   *
   * @param index the new default index
   */
  public void setDefaultIndex(TableIndex index) {
    mDefaultIndex = index;
  }

  /**
   * Sets the header size.
   *
   * @param headerSize the new header size
   */
  public void setHeaderSize(int headerSize) {
    mHeaderSize = headerSize;

    fireChanged();
  }

  /**
   * Gets the header size.
   *
   * @return the header size
   */
  public int getHeaderSize() {
    return mHeaderSize;
  }

  /**
   * Sets the width of a row/column.
   *
   * @param i     the row/column
   * @param width the width
   */
  public void setWidth(int i, int width) {
    TableIndex index = create(i);

    index.setWidth(width);

    fireChanged();
  }

  /**
   * Set the default renderer.
   *
   * @param i the i
   * @return the table index
   */
  // public void setCellRenderer(ModernDataCellRenderer renderer) {
  // mDefaultIndex.setCellRenderer(renderer);
  // }

  // public void setCellRenderer(int i, ModernDataCellRenderer renderer) {
  // TableIndex index = create(i);
  //
  // index.setCellRenderer(renderer);
  // }

  // public void setCellEditor(int i, ModernDataViewCellEditor editor) {
  // TableIndex index = create(i);
  //
  // index.setCellEditor(editor);
  // }

  /**
   * Create a new index if one does not exist. Indexes are only created if a
   * particular row or column is edited, otherwise they share the same default
   * index.
   * 
   * @param i
   * @return
   */
  private TableIndex create(int i) {
    TableIndex index = mIndices.get(i);

    if (index == null) {
      index = new TableIndex(mDefaultIndex);
      mIndices.put(i, index);
    }

    return index;
  }

  /**
   * Gets the.
   *
   * @param i the i
   * @return the table index
   */
  public final TableIndex get(int i) {
    TableIndex index = mIndices.get(i);

    if (index == null) {
      index = mDefaultIndex;
    }

    return index;
  }

  /**
   * Sets the.
   *
   * @param i     the i
   * @param index the index
   */
  public void set(int i, TableIndex index) {
    mIndices.put(i, index);
  }

  /**
   * Returns the offset of a particular row (e.g. the X or Y).
   *
   * @param index the index
   * @return the offset
   */
  public final int getOffset(int index) {
    int y = 0;

    int max = Math.min(index, mSize - 1);

    for (int i = 0; i < max; ++i) {
      y += get(i).getWidth();
    }

    return y;
  }

  public final int getOffset(int index, double zoom) {
    int y = 0;

    int max = Math.min(index, mSize - 1);

    for (int i = 0; i < max; ++i) {
      y += get(i).getWidth(); // (int)(get(i).getWidth() * zoom);
    }

    return (int) (y * zoom);
  }

  /**
   * Size.
   *
   * @return the int
   */
  public final int size() {
    return mSize;
  }

  /**
   * Sets the selected.
   *
   * @param i the new selected
   */
  public void setSelected(int i) {
    getSelectionModel().add(i);
  }

  /**
   * Returns true if the column/row is selected.
   *
   * @param i the i
   * @return true, if is selected
   */
  public boolean isSelected(int i) {
    return getSelectionModel().contains(i);
  }

  /**
   * Remove all columns.
   */
  public void clear() {
    mSelectionModel.clear();
    mIndices.clear();
  }

  /**
   * Remove the selections on all the headers.
   * 
   */
  public void unselectAll() {
    mSelectionModel.clear();
  }

  /**
   * Select all the indexes at once.
   */
  public void selectAll() {
    mSelectionModel.setSelectionInterval(0, mSize - 1);
  }

  /**
   * Returns the selection model associated with a table index. The selection
   * model contains the indices of all table indexes (either row or columns) that
   * are currently selected. This provides a faster method of determining what is
   * selected rather than iterating over all the indexes to determine if they are
   * selected or not.
   *
   * @return the selection model
   */
  public SelectionRangeModel getSelectionModel() {
    return mSelectionModel;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernSelectionEventProducer#
   * addSelectionListener (org.abh.lib.ui.modern.event.ModernSelectionListener)
   */
  @Override
  public void addSelectionListener(ModernSelectionListener l) {
    mSelectionModel.addSelectionListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernSelectionEventProducer#
   * removeSelectionListener(org.abh.lib.ui.modern.event. ModernSelectionListener)
   */
  @Override
  public void removeSelectionListener(ModernSelectionListener l) {
    mSelectionModel.removeSelectionListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernSelectionEventProducer#
   * fireSelectionChanged (org.abh.lib.event.ChangeEvent)
   */
  @Override
  public void fireSelectionAdded(ChangeEvent e) {
    mSelectionModel.fireSelectionAdded(new ChangeEvent(this, e.getMessage()));
  }

  @Override
  public void fireSelectionRemoved(ChangeEvent e) {
    mSelectionModel.fireSelectionRemoved(new ChangeEvent(this, e.getMessage()));
  }

  /**
   * Gets the heading renderer.
   *
   * @param i the i
   * @return the heading renderer
   */
  // public ModernDataCellRenderer getHeadingRenderer(int i) {
  // return get(i).getHeadingRenderer();
  // }

  // public ModernDataCellRenderer getCellRenderer(int i) {
  // return get(i).getCellRenderer();
  // }

  /**
   * Gets the width of the row/column.
   *
   * @param i the i
   * @return the width
   */
  public int getWidth(int i) {
    return get(i).getWidth();
  }

  public int getWidth(int i, double zoom) {
    return (int) (getWidth(i) * zoom);
  }

  /**
   * Returns the cumulative offset + width of row.
   *
   * @param i the i
   * @return the cum width
   */
  public int getCumWidth(int i) {
    return getCumOffset(i) + getWidth(i);
  }

  /**
   * Gets the cumulative offset up until the start of the row.
   *
   * @param index the index
   * @return the cum offset
   */
  public int getCumOffset(int index) {
    int ret = 0;

    for (int i = 0; i < index; ++i) {
      ret += getWidth(i);
    }

    return ret;
  }

  /**
   * Gets the cumulative offset up until the start of the row/column accounting
   * for any zoom.
   *
   * @param index The index.
   * @param zoom  The zoom level.
   * @return The cumulative offset
   */
  public int getCumOffset(int index, double zoom) {
    int ret = 0;

    for (int i = 0; i < index; ++i) {
      ret += (int) (getWidth(i) * zoom);
    }

    return ret;
  }

  // public ModernDataViewCellEditor getCellEditor(int i) {
  // return get(i).getEditor();
  // }

  /**
   * Gets the default index.
   *
   * @return the default index
   */
  public TableIndex getDefaultIndex() {
    return mDefaultIndex;
  }

  /**
   * Set the default width of all indices.
   *
   * @param width the new width
   */
  public void setWidth(int width) {
    mDefaultIndex.setWidth(width);

    // Notify any listeners that the
    // table has been updated
    fireChanged();
  }

  /**
   * Gets the width.
   *
   * @return the width
   */
  public int getWidth() {
    return mDefaultIndex.getWidth();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return mSelectionModel.toString();
  }
}
