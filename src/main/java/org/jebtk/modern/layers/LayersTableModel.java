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

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.modern.table.ModernTableModel;

/**
 * The class LayersTableModel.
 */
public class LayersTableModel extends ModernTableModel implements LayerEventListener {

  /**
   * The constant HEADER.
   */
  private static final String[] HEADER = { "Visible", "Name" };

  /**
   * The member layer model.
   */
  private LayerModel mLayerModel;

  /**
   * Instantiates a new layers table model.
   *
   * @param layerModel the layer model
   */
  public LayersTableModel(LayerModel layerModel) {
    mLayerModel = layerModel;

    layerModel.addLayerListener(this);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataModel#getColumnCount()
   */
  @Override
  public final int getColCount() {
    return HEADER.length;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataModel#getRowCount()
   */
  @Override
  public final int getRowCount() {
    return mLayerModel.size();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataModel#getValueAt(int, int)
   */
  @Override
  public final Object getValueAt(int row, int col) {
    switch (col) {
    case 0:
      return mLayerModel.isVisible(row);
    default:
      return mLayerModel.get(row);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.dataview.ModernDataModel#getColumn().getAnnotations(
   * int)
   */
  @Override
  public final String getColumnName(int column) {
    return HEADER[column];
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.dataview.ModernDataGridModel#getIsCellEditable(int,
   * int)
   */
  @Override
  public final boolean getIsCellEditable(int rowIndex, int column) {
    return column == 0;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataModel#setValueAt(int, int,
   * java.lang.Object)
   */
  @Override
  public final void setValueAt(int row, int column, Object value) {
    if (column != 0) {
      return;
    }

    mLayerModel.setVisible(row, (boolean) value);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.layers.LayerEventListener#layerChanged(org.abh.lib.
   * event.ChangeEvent)
   */
  @Override
  public void layerChanged(ChangeEvent e) {
    fireDataChanged();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.layers.LayerEventListener#layerUpdated(org.abh.common.
   * event .ChangeEvent)
   */
  @Override
  public void layerUpdated(ChangeEvent e) {
    fireDataChanged();
  }

}