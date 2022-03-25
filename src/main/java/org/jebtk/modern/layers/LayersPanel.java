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
package org.jebtk.modern.layers;

import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.dataview.ModernDataGridCellRenderer;
import org.jebtk.modern.scrollpane.ModernScrollPane;
import org.jebtk.modern.scrollpane.ScrollBarPolicy;
import org.jebtk.modern.table.ModernRowTable;
import org.jebtk.modern.table.ModernTable;
import org.jebtk.modern.table.ModernTableCheckboxCellEditor;
import org.jebtk.modern.table.ModernTableCheckboxCellRenderer;

/**
 * The class LayersPanel.
 */
public class LayersPanel extends ModernComponent {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member table.
   */
  private ModernTable mTable = new ModernRowTable();

  /**
   * Instantiates a new layers panel.
   *
   * @param layerModel the layer model
   */
  public LayersPanel(LayerModel layerModel) {
    setup();

    setLayerModel(layerModel);
  }

  /**
   * Setup.
   */
  private void setup() {
    mTable.setShowHeader(false);

    ModernScrollPane scrollPane = new ModernScrollPane(mTable);
    scrollPane.setHorizontalScrollBarPolicy(ScrollBarPolicy.NEVER);

    setBody(scrollPane);
  }

  /**
   * Sets the layer model.
   *
   * @param layerModel the new layer model
   */
  public void setLayerModel(LayerModel layerModel) {
    LayersTableModel tableModel = new LayersTableModel(layerModel);

    mTable.setModel(tableModel);

    mTable.getRendererModel().setCol(0, new ModernTableCheckboxCellRenderer());
    mTable.getEditorModel().setCol(0, new ModernTableCheckboxCellEditor());
    mTable.getColumnModel().setWidth(0, 30);

    mTable.getRendererModel().setCol(1, new ModernDataGridCellRenderer());
    mTable.getColumnModel().setWidth(1, 200);
  }
}
