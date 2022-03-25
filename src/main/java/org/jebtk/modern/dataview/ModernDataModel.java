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

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.io.FileUtils;
import org.jebtk.core.text.TextUtils;

// TODO: Auto-generated Javadoc
/**
 * Provides an underlying model of tabular data for the control. This model
 * controls content whereas the dataview and other models control presentation.
 *
 * @author Antony Holmes
 *
 */
public abstract class ModernDataModel implements ModernDataViewEventProducer {

  /**
   * The member listeners.
   */
  private ModernDataViewListeners mListeners = new ModernDataViewListeners();

  /**
   * Gets the row count.
   *
   * @return the row count
   */
  public abstract int getRowCount();

  /**
   * Gets the column count.
   *
   * @return the column count
   */
  public abstract int getColCount();

  /**
   * Returns the total number of items in the table.
   *
   * @return the item count
   */
  public int getItemCount() {
    return getRowCount() * getColCount();
  }

  /**
   * Gets the value at.
   *
   * @param cell the cell
   * @return the value at
   */
  public Object getValueAt(ModernDataCell cell) {
    return getValueAt(cell.row, cell.col);
  }

  public String getColumnName(int col) {
    return TextUtils.EMPTY_STRING;
  }

  public String getRowName(int row) {
    return TextUtils.EMPTY_STRING;
  }

  /**
   * Gets the value at.
   *
   * @param row     the row
   * @param heading the heading
   * @return the value at
   */
  public Object getValueAt(int row, String heading) {
    System.err.println("mod data heading " + heading + " " + getHeadingIndex(heading));
    return getValueAt(row, getHeadingIndex(heading));
  }

  /**
   * Gets the value at.
   *
   * @param row    the row
   * @param column the column
   * @return the value at
   */
  public abstract Object getValueAt(int row, int column);

  /**
   * Sets the value at.
   *
   * @param cell  the cell
   * @param value the value
   */
  public final void setValueAt(ModernDataCell cell, Object value) {
    setValueAt(cell.row, cell.col, value);
  }

  /**
   * Set the value of the underlying data. This method should be overridden by
   * data models that allow data editing.
   *
   * @param row    the row
   * @param column the column
   * @param value  the value
   */
  public void setValueAt(int row, int column, Object value) {
    fireDataUpdated();
  }

  /**
   * Gets the cell style.
   *
   * @param cell the cell
   * @return the cell style
   */
  public ModernDataCellStyle getCellStyle(ModernDataCell cell) {
    return getCellStyle(cell.row, cell.col);
  }

  /**
   * Gets the cell style.
   *
   * @param row the row
   * @param col the col
   * @return the cell style
   */
  public ModernDataCellStyle getCellStyle(int row, int col) {
    return ModernDataCellStyle.DEFAULT_STYLE;
  }

  /**
   * Sets the cell style.
   *
   * @param cell  the cell
   * @param style the style
   */
  public void setCellStyle(ModernDataCell cell, ModernDataCellStyle style) {
    setCellStyle(cell.row, cell.col, style);
  }

  /**
   * Sets the cell style.
   *
   * @param row   the row
   * @param col   the col
   * @param style the style
   */
  public void setCellStyle(int row, int col, ModernDataCellStyle style) {
    // Do nothing
  }

  /**
   * Sets the row style.
   *
   * @param row   the row
   * @param style the style
   */
  public void setRowStyle(int row, ModernDataCellStyle style) {
    // Do nothing
  }

  /**
   * Sets the col style.
   *
   * @param col   the col
   * @param style the style
   */
  public void setColStyle(int col, ModernDataCellStyle style) {
    // Do nothing
  }

  /**
   * Should return the heading index of a heading name.
   *
   * @param heading the heading
   * @return the heading index
   */
  public int getHeadingIndex(String heading) {
    return -1;
  }

  /**
   * Checks if is cell editable.
   *
   * @param cell the cell
   * @return true, if is cell editable
   */
  public final boolean isCellEditable(ModernDataCell cell) {
    if (cell == null) {
      return false;
    }

    return getIsCellEditable(cell.row, cell.col);
  }

  /**
   * Returns true if the cell can be edited, false otherwise.
   *
   * @param row    the row
   * @param column the column
   * @return the checks if is cell editable
   */
  public boolean getIsCellEditable(int row, int column) {
    return false;
  }

  /**
   * Gets the checks if is cell enabled.
   *
   * @param cell the cell
   * @return the checks if is cell enabled
   */
  public final boolean getIsCellEnabled(ModernDataCell cell) {
    if (cell == null) {
      return false;
    }

    return getIsCellEnabled(cell.row, cell.col);
  }

  /**
   * Gets the checks if is cell enabled.
   *
   * @param row the row
   * @param col the col
   * @return the checks if is cell enabled
   */
  public boolean getIsCellEnabled(int row, int col) {
    return true;
  }

  /**
   * Gets the column class.
   *
   * @param col the col
   * @return the column class
   */
  public Class<?> getColumnClass(int col) {
    return getValueAt(0, col).getClass();
  }

  /**
   * Should clear all values from the model.
   */
  public void clear() {
    fireDataChanged();
  }

  /**
   * Removes the value at.
   *
   * @param row    the row
   * @param column the column
   */
  public void removeValueAt(int row, int column) {
    fireDataChanged();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataViewEventProducer#
   * addDataViewListener(org.abh.lib.ui.modern.dataview.ModernDataViewListener)
   */
  public void addDataViewListener(ModernDataViewListener l) {
    mListeners.addDataViewListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataViewEventProducer#
   * removeDataViewListener(org.abh.lib.ui.modern.dataview.
   * ModernDataViewListener)
   */
  public void removeDataViewListener(ModernDataViewListener l) {
    mListeners.removeDataViewListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.dataview.ModernDataViewEventProducer#fireDataChanged(
   * org.abh.lib.event.ChangeEvent)
   */
  public void fireDataChanged(ChangeEvent event) {
    mListeners.fireDataChanged(event);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.dataview.ModernDataViewEventProducer#fireDataUpdated(
   * org.abh.lib.event.ChangeEvent)
   */
  public void fireDataUpdated(ChangeEvent event) {
    mListeners.fireDataUpdated(event);
  }

  /**
   * Fire data changed.
   */
  public void fireDataChanged() {
    fireDataChanged(new ChangeEvent(this));
  }

  /**
   * Fire data updated.
   */
  public void fireDataUpdated() {
    fireDataUpdated(new ChangeEvent(this));
  }

  /**
   * Convenience method that will return a row as a list of strings.
   *
   * @param row the row
   * @return the row as text
   */
  public List<String> getRowAsText(int row) {
    List<String> values = new ArrayList<String>(getColCount());

    for (int i = 0; i < getColCount(); ++i) {
      values.add(getValueAt(row, i) != null ? getValueAsString(row, i) : "");
    }

    return values;
  }

  /**
   * Extract all the values in a column as strings.
   * 
   * @param col
   * @return
   */
  public List<String> getColAsText(int col) {
    List<String> values = new ArrayList<String>(getRowCount());

    for (int i = 0; i < getRowCount(); ++i) {
      values.add(getValueAt(i, col) != null ? getValueAsString(i, col) : "");
    }

    return values;
  }

  /**
   * Gets the value as string.
   *
   * @param row    the row
   * @param column the column
   * @return the value as string
   */
  public String getValueAsString(int row, int column) {
    Object v = getValueAt(row, column);

    if (v == null) {
      return null;
    }

    return v.toString();
  }

  /**
   * Gets the value as string.
   *
   * @param row     the row
   * @param heading the heading
   * @return the value as string
   */
  public String getValueAsString(int row, String heading) {
    Object v = getValueAt(row, heading);

    if (v == null) {
      return null;
    }

    return v.toString();
  }

  /**
   * Convert a string to a number. Will return 0 if the cell is null or empty.
   *
   * @param row     the row
   * @param heading the heading
   * @return the value as int
   * @throws ParseException the parse exception
   */
  public int getValueAsInt(int row, String heading) throws ParseException {
    return TextUtils.parseInt(getValueAsString(row, heading));
  }

  /**
   * Gets the value as int.
   *
   * @param row    the row
   * @param column the column
   * @return the value as int
   * @throws ParseException the parse exception
   */
  public int getValueAsInt(int row, int column) throws ParseException {
    return TextUtils.parseInt(getValueAsString(row, column));
  }

  /**
   * Gets the value as bool.
   *
   * @param row    the row
   * @param column the column
   * @return the value as bool
   */
  public boolean getValueAsBool(int row, int column) {
    return (Boolean) getValueAt(row, column);
  }

  /**
   * Gets the value as double.
   *
   * @param row     the row
   * @param heading the heading
   * @return the value as double
   * @throws ParseException the parse exception
   */
  public double getValueAsDouble(int row, String heading) throws ParseException {
    return TextUtils.parseDouble(getValueAsString(row, heading));
  }

  /**
   * Gets the value as double.
   *
   * @param row    the row
   * @param column the column
   * @return the value as double
   * @throws ParseException the parse exception
   */
  public double getValueAsDouble(int row, int column) throws ParseException {
    return TextUtils.parseDouble(getValueAsString(row, column));
  }

  //
  // Static methods
  //

  /**
   * Return the index of a header containing some text or return -1 if the text is
   * not found.
   *
   * @param model the model
   * @param text  the text
   * @return the int
   */
  public static int findFirst(ModernDataModel model, String text) {
    String lc = text.toLowerCase();

    for (int i = 0; i < model.getColCount(); ++i) {
      if (model.getColumnName(i).toLowerCase().contains(lc)) {
        return i;
      }
    }

    return -1;
  }

  /**
   * Match first.
   *
   * @param model the model
   * @param text  the text
   * @return the int
   */
  public static int matchFirst(ModernDataModel model, String text) {
    String lc = text.toLowerCase();

    for (int i = 0; i < model.getColCount(); ++i) {
      if (model.getColumnName(i).toLowerCase().equals(lc)) {
        return i;
      }
    }

    return -1;
  }

  /**
   * Write.
   *
   * @param model the model
   * @param file  the file
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static void write(ModernDataModel model, Path file) throws IOException {
    BufferedWriter writer = FileUtils.newBufferedWriter(file);

    try {
      for (int i = 0; i < model.getColCount(); ++i) {
        if (i > 0) {
          writer.write(TextUtils.TAB_DELIMITER);
        }

        writer.write(model.getColumnName(i));
      }

      writer.newLine();

      for (int i = 0; i < model.getRowCount(); ++i) {
        for (int j = 0; j < model.getColCount(); ++j) {
          writer.write(model.getValueAsString(i, j)); // != null ?
          // model.getValueAt(i,
          // j).toString() : "";

          if (j < model.getColCount() - 1) {
            writer.write(TextUtils.TAB_DELIMITER);
          }
        }

        writer.newLine();
      }
    } finally {
      writer.close();
    }
  }

}
