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

import org.jebtk.core.event.ChangeListeners;
import org.jebtk.modern.dataview.ModernData;

/**
 * The data sorter provides a mapping between an index and a sorted index so
 * that a dataview can arbitrary reorder a data model (e.g. sort all columns)
 *
 * @author Antony Holmes
 *
 */
public class ModernDataIndexSorter extends ChangeListeners {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * The sort ascending.
   */
  protected boolean mSortAscending = true;

  /** The m enabled. */
  private boolean mEnabled = false;

  /**
   * Sort the given index so that calls to getOriginalIndex or getSortedIndex will
   * allow the data model to be sorted.
   *
   * @param data          the data
   * @param index         the index
   * @param sortAscending the sort ascending
   */
  public void sort(ModernData data, int index, boolean sortAscending) {
    mSortAscending = sortAscending;
  }

  /**
   * Returns the original index of an item in a data model so that we can
   * correctly retrieve it from the unsorted model.
   *
   * @param sortedIndex the sorted index
   * @return the model index
   */
  public int getModelIndex(int sortedIndex) {
    return sortedIndex;
  }

  /**
   * Given an original index, returns its sorted index in the sort model.
   *
   * @param originalIndex the original index
   * @return the sorted index
   */
  public int getSortedIndex(int originalIndex) {
    return originalIndex;
  }

  /**
   * Gets the sort ascending.
   *
   * @return the sort ascending
   */
  public boolean getSortAscending() {
    return mSortAscending;
  }

  /**
   * Sets the enabled.
   *
   * @param enabled the new enabled
   */
  public void setEnabled(boolean enabled) {
    mEnabled = enabled;

    fireChanged();
  }

  /**
   * Returns true if the sorter is enabled and should be used.
   *
   * @return the enabled
   */
  public boolean getEnabled() {
    return mEnabled;
  }
}
