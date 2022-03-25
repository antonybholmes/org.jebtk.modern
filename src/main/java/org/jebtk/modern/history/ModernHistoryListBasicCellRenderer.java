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
package org.jebtk.modern.history;

import java.awt.Color;
import java.awt.Component;

import org.jebtk.modern.list.ModernList;

// TODO: Auto-generated Javadoc
/**
 * Provides a flat look table renderer for the flat table although it can be
 * used with standard JTables as well.
 *
 * @author Antony Holmes
 * @param <T> the generic type
 */
public class ModernHistoryListBasicCellRenderer extends ModernHistoryListCellRenderer {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member text color.
   */
  protected Color mTextColor;

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.list.ModernListCellRenderer#getCellRendererComponent(
   * org.abh.lib.ui.modern.list.ModernList, java.lang.Object, boolean, boolean,
   * boolean, int)
   */
  @Override
  public Component getCellRendererComponent(ModernList<?> list, Object value, boolean highlight, boolean isSelected,
      boolean hasFocus, int row) {

    if (row <= list.getSelectedIndex()) {
      mTextColor = TEXT_COLOR;
    } else {
      mTextColor = ALT_TEXT_COLOR;
    }

    return super.getCellRendererComponent(list, value, highlight, isSelected, hasFocus, row);
  }
}