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
package org.jebtk.modern.preview;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jebtk.core.text.TextUtils;
import org.jebtk.modern.table.ModernTableModel;

/**
 * A concrete implementation of a table model for displaying text.
 * 
 * @author Antony Holmes
 *
 */
public class PreviewTableNoHeaderModel extends ModernTableModel {

  /**
   * The data.
   */
  private List<List<String>> data = new ArrayList<List<String>>();

  /**
   * The max columns.
   */
  private int maxColumns;

  /**
   * Instantiates a new preview table no header model.
   *
   * @param file the file
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public PreviewTableNoHeaderModel(final File file) throws IOException {
    setup(file, -1);
  }

  /**
   * Instantiates a new preview table no header model.
   *
   * @param file the file
   * @param rows the rows
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public PreviewTableNoHeaderModel(final File file, int rows) throws IOException {
    setup(file, rows);
  }

  /**
   * Setup.
   *
   * @param file the file
   * @param rows the rows
   * @throws IOException Signals that an I/O exception has occurred.
   */
  private void setup(final File file, int rows) throws IOException {
    System.err.println("preview " + file);

    BufferedReader reader = new BufferedReader(new FileReader(file));

    int c = 0;

    maxColumns = -1;

    String line;

    try {
      while ((line = reader.readLine()) != null) {
        if (rows > 0 && c == rows) {
          break;
        }

        List<String> values = TextUtils.fastSplit(line, TextUtils.TAB_DELIMITER);

        data.add(values);

        if (values.size() > maxColumns) {
          maxColumns = values.size();
        }

        ++c;
      }
    } finally {
      reader.close();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataModel#getColumnCount()
   */
  @Override
  public final int getColCount() {
    return maxColumns;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataModel#getRowCount()
   */
  @Override
  public final int getRowCount() {
    return data.size();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataModel#getValueAt(int, int)
   */
  @Override
  public final Object getValueAt(int row, int col) {
    if (row >= data.size()) {
      return "";
    }

    if (col >= data.get(row).size()) {
      return "";
    }

    return data.get(row).get(col);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.dataview.ModernDataGridModel#getIsCellEditable(int,
   * int)
   */
  @Override
  public final boolean getIsCellEditable(int row, int column) {
    return false; // true;
  }

  @Override
  public String getColumnName(int columns) {
    return TextUtils.EMPTY_STRING;
  }
}