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

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jebtk.core.Indexed;
import org.jebtk.modern.dataview.ModernData;

/**
 * The data sorter provides a mapping between an index and a sorted index so
 * that a dataview can arbitrary reorder a data model (e.g. sort all columns).
 *
 * @author Antony Holmes
 *
 */
public abstract class ModernDataIndexMapSorter extends ModernDataIndexSorter {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * The original sorted map.
   */
  protected Map<Integer, Integer> mOriginalSortedMap = new HashMap<Integer, Integer>();

  /**
   * The sorted original map.
   */
  protected Map<Integer, Integer> mSortedOriginalMap = new HashMap<Integer, Integer>();

  /**
   * Instantiates a new modern data index map sorter.
   */
  public ModernDataIndexMapSorter() {
    setEnabled(true);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.dataview.sort.ModernDataIndexSorter#sort(org.abh.lib.
   * ui .modern.dataview.ModernData, int, boolean)
   */
  @Override
  public void sort(ModernData data, int index, boolean sortAscending) {
    super.sort(data, index, sortAscending);

    mOriginalSortedMap.clear();
    mSortedOriginalMap.clear();
  }

  /**
   * Sort indexed values for use in a table sorter.
   *
   * @param <T>    the generic type
   * @param values the values
   */
  public <T extends Comparable<? super T>> void sort(List<Indexed<Integer, T>> values) {
    Collections.sort(values);

    if (!mSortAscending) {
      Collections.reverse(values);
    }

    for (int i = 0; i < values.size(); ++i) {
      mOriginalSortedMap.put(values.get(i).getIndex(), i);
      mSortedOriginalMap.put(i, values.get(i).getIndex());
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.sort.ModernDataIndexSorter#getModelIndex(
   * int)
   */
  @Override
  public int getModelIndex(int sortedIndex) {
    Integer i = mSortedOriginalMap.get(sortedIndex);

    if (i == null) {
      return -1;
    }

    return i;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.dataview.sort.ModernDataIndexSorter#getSortedIndex(
   * int)
   */
  @Override
  public int getSortedIndex(int originalIndex) {
    Integer i = mOriginalSortedMap.get(originalIndex);

    if (i == null) {
      return -1;
    }

    return i;
  }
}
