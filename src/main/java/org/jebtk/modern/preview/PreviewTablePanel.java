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

import java.io.IOException;
import java.nio.file.Path;

import org.jebtk.modern.dataview.ModernDataModel;
import org.jebtk.modern.panel.ModernPanel;
import org.jebtk.modern.scrollpane.ModernScrollPane;
import org.jebtk.modern.table.ModernSpreadsheet;
import org.jebtk.modern.table.ModernSpreadsheetBar;
import org.jebtk.modern.table.ModernTable;
import org.jebtk.modern.zoom.ZoomModel;

/**
 * The class PreviewTablePanel.
 */
public class PreviewTablePanel extends ModernPanel {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member table.
   */
  private ModernSpreadsheet mTable;

  /**
   * The member file.
   */
  private Path mPath;

  /**
   * Instantiates a new preview table panel.
   *
   * @param file the file
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public PreviewTablePanel(Path file) throws IOException {
    this(file, -1);
  }

  /**
   * Instantiates a new preview table panel.
   *
   * @param file the file
   * @param rows the rows
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public PreviewTablePanel(Path file, int rows) throws IOException {
    this(new PreviewTableModel(file, rows));

    mPath = file;
  }

  public PreviewTablePanel(ModernDataModel model) {
    this(model, new ZoomModel());
  }

  /**
   * Instantiates a new preview table panel.
   *
   * @param model the model
   */
  public PreviewTablePanel(ModernDataModel model, ZoomModel zoomModel) {
    mTable = new ModernSpreadsheet();
    mTable.setZoomModel(zoomModel);

    mTable.setModel(model);

    ModernSpreadsheetBar bar = new ModernSpreadsheetBar(mTable);

    setHeader(bar);

    ModernScrollPane scrollPane = new ModernScrollPane(mTable);
    scrollPane.getVScrollBar().setPadding(PADDING, 0);
    scrollPane.getHScrollBar().setPadding(PADDING, 0);
    setBody(scrollPane);

    setBorder(BORDER);
  }

  /**
   * Gets the file.
   *
   * @return the file
   */
  public Path getFile() {
    return mPath;
  }

  /**
   * Gets the table.
   *
   * @return the table
   */
  public ModernTable getTable() {
    return mTable;
  }
}
