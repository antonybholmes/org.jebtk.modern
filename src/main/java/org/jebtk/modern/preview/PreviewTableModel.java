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
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.jebtk.core.collections.CollectionUtils;
import org.jebtk.core.io.FileUtils;
import org.jebtk.core.io.Io;
import org.jebtk.core.text.TextUtils;
import org.jebtk.modern.table.ModernTableModel;

/**
 * A concrete implementation of a table model for displaying text.
 * 
 * @author Antony Holmes
 *
 */
public class PreviewTableModel extends ModernTableModel {

  /**
   * The header.
   */
  // icon, enabled, description, name
  private List<String> header;

  /**
   * The data.
   */
  private List<List<String>> data = new ArrayList<List<String>>();

  /**
   * Instantiates a new preview table model.
   *
   * @param file the file
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public PreviewTableModel(final Path file) throws IOException {
    this(file, -1);
  }

  public PreviewTableModel(final Path file, int rows) throws IOException {
    BufferedReader reader = FileUtils.newBufferedReader(file);

    int c = 0;

    try {
      String line = reader.readLine();

      if (Io.isEmptyLine(line)) {
        return;
      }

      header = TextUtils.fastSplit(line, TextUtils.TAB_DELIMITER);

      int maxColumns = -1;

      while ((line = reader.readLine()) != null) {
        if (Io.isEmptyLine(line)) {
          continue;
        }

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

      // expand the header if necessary

      // Account for the existing header in the length
      maxColumns -= header.size();

      header.addAll(CollectionUtils.replicate("", maxColumns));
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
    return header.size();
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
    if (col >= data.get(row).size()) {
      return "";
    }

    return data.get(row).get(col);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.dataview.ModernDataModel#getColumn().getAnnotations(
   * int)
   */
  @Override
  public final String getColumnName(int columnIndex) {
    return header.get(columnIndex);
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
}