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

import org.jebtk.modern.dataview.ModernDataModel;
import org.jebtk.modern.zoom.ZoomModel;

// TODO: Auto-generated Javadoc
/**
 * Provides a spreadsheet Property view of a table. Replacement for JTable
 * without all the bulk associated with it.
 *
 * @author Antony Holmes
 *
 */
public class ModernSpreadsheet extends ModernTable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new modern row column table.
   */
  public ModernSpreadsheet() {
    setModel(new EmptyTableModel(50, 50));

    setup();
  }

  /**
   * Instantiates a new modern row column table.
   *
   * @param model the model
   */
  public ModernSpreadsheet(ModernTableModel model) {
    setModel(model);

    setup();
  }

  public ModernSpreadsheet(ZoomModel zoomModel) {
    super(zoomModel);

    setup();
  }

  /**
   * Setup.
   */
  private final void setup() {
    setShowRowHeader(true);
    setShowHeader(true);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.table.ModernTable#getRowModel()
   */
  public TableIndexModel getRowModel() {
    return mRowModel;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.table.ModernTable#setModel(org.abh.common.ui.dataview.
   * ModernDataModel)
   */
  @Override
  public void setModel(ModernDataModel model) {
    super.setModel(model);

    // Highlight A1
    getCellSelectionModel().setSelection(0, 0);
  }

  /**
   * @Override protected void drawBorder(Graphics2D g2) { g2.setColor(LINE_COLOR);
   * 
   *           Rectangle rect = getVisibleRect();
   * 
   *           int x = rect.x + rect.width - 1;
   * 
   *           g2.drawLine(x, rect.y, x, rect.y + rect.height);
   * 
   *           x = rect.y + rect.height - 1;
   * 
   *           g2.drawLine(rect.x, x, rect.x + rect.width, x); }
   */
}
