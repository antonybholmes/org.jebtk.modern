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
package org.jebtk.modern.dataview.sort;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.jebtk.core.Indexed;
import org.jebtk.core.text.Parser;
import org.jebtk.modern.dataview.ModernData;

/**
 * The class ModernDataRowDoubleSorter.
 */
public class ModernDataRowDoubleSorter extends ModernDataIndexMapSorter {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.dataview.sort.ModernDataIndexMapSorter#sort(org.abh.
   * lib .ui.modern.dataview.ModernData, int, boolean)
   */
  @Override
  public void sort(ModernData data, int column, boolean sortAscending) {
    super.sort(data, column, sortAscending);

    List<Indexed<Integer, Double>> values = new ArrayList<Indexed<Integer, Double>>();

    for (int i = 0; i < data.getRowCount(); ++i) {
      double v;

      try {
        v = Parser.toDouble(data.getModel().getValueAt(i, column).toString());
      } catch (ParseException e) {
        v = data.getRowCount();
        // e.printStackTrace();
      }

      values.add(new Indexed<Integer, Double>(i, v));
    }

    sort(values);
  }
}
