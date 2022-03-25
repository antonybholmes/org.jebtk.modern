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
package org.jebtk.modern.log;

import org.jebtk.core.collections.CircularArray;
import org.jebtk.core.log.LogEvent;
import org.jebtk.core.log.LogEventListener;
import org.jebtk.modern.table.ModernTableModel;

/**
 * The class LogTableModel.
 */
public class LogTableModel extends ModernTableModel implements LogEventListener {

  /**
   * The constant HEADER.
   */
  private static final String[] HEADER = { "Type", "Date", "Source", "Message", "ID" };

  /**
   * The member events.
   */
  private CircularArray<LogEvent> mEvents = new CircularArray<LogEvent>(1000);

  /**
   * Instantiates a new log table model.
   */
  public LogTableModel() {
    fireDataChanged();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataModel#getRowCount()
   */
  public final int getRowCount() {
    return mEvents.size();
  }

  public final int getColCount() {
    return HEADER.length;
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
      return mEvents.get(row).getType();
    case 1:
      return mEvents.get(row).getFormattedDate();
    case 2:
      return mEvents.get(row).getSource();
    case 3:
      return mEvents.get(row).getMessage();
    case 4:
      return mEvents.get(row).getId();
    }

    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.dataview.ModernDataGridModel#getIsCellEditable(int,
   * int)
   */
  public final boolean getIsCellEditable(int row, int col) {
    // Note that the data/cell address is constant,
    // no matter where the cell appears onscreen.

    return false;
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
   * @see org.abh.lib.log.LogEventListener#logEvent(org.abh.lib.log.LogEvent)
   */
  @Override
  public final void logEvent(LogEvent event) {
    mEvents.add(event);

    fireDataChanged();
  }
}