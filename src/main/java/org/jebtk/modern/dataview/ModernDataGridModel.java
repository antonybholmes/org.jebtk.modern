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

import org.jebtk.core.event.ChangeEvent;

/**
 * Extended table model.
 *
 * @author Antony Holmes
 */
public abstract class ModernDataGridModel extends ModernDataModel {

  /**
   * The member cell style model.
   */
  protected ModernDataCellStyleModel mCellStyleModel = new ModernDataCellStyleModel();

  /**
   * The class StyleEvents.
   */
  private class StyleEvents implements ModernDataViewListener {

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.abh.lib.ui.modern.dataview.ModernDataViewListener#dataChanged(org.abh
     * .lib .event.ChangeEvent)
     */
    @Override
    public void dataChanged(ChangeEvent e) {
      fireDataUpdated();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.abh.lib.ui.modern.dataview.ModernDataViewListener#dataUpdated(org.abh
     * .lib .event.ChangeEvent)
     */
    @Override
    public void dataUpdated(ChangeEvent e) {
      fireDataUpdated();
    }

  }

  public ModernDataGridModel() {
    mCellStyleModel.addDataViewListener(new StyleEvents());
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataModel#getCellStyle(int, int)
   */
  public ModernDataCellStyle getCellStyle(int row, int col) {
    return mCellStyleModel.get(row, col);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataModel#setCellStyle(int, int,
   * org.abh.lib.ui.modern.dataview.ModernDataCellStyle)
   */
  public void setCellStyle(int row, int col, ModernDataCellStyle style) {
    mCellStyleModel.set(row, col, style);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataModel#setRowStyle(int,
   * org.abh.lib.ui.modern.dataview.ModernDataCellStyle)
   */
  public void setRowStyle(int row, ModernDataCellStyle style) {
    mCellStyleModel.setRow(row, style);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataModel#setColStyle(int,
   * org.abh.lib.ui.modern.dataview.ModernDataCellStyle)
   */
  public void setColStyle(int col, ModernDataCellStyle style) {
    mCellStyleModel.setRow(col, style);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataModel#getIsCellEditable(int,
   * int)
   */
  @Override
  public boolean getIsCellEditable(int row, int column) {
    return false;
  }
}
