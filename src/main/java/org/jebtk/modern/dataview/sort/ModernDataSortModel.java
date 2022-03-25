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

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a collection of sortIndex sorters for a dataview. This allows
 * specific columns to have independent sorters for example if one sortIndex is
 * known to contain only numbers.
 *
 * @author Antony Holmes
 *
 */
public class ModernDataSortModel {

  /**
   * The member sort map.
   */
  private Map<Integer, ModernDataIndexSorter> mSortMap = new HashMap<Integer, ModernDataIndexSorter>();

  /**
   * If a sort index of -1 is specified, the no sorter is used to present the
   * table in an unsorted state.
   * 
   * since
   */
  private static final ModernDataIndexSorter NO_SORT = new ModernDataIndexSorter();

  /**
   * Sets a sorter for columns if non has been specified.
   */
  private ModernDataIndexSorter mDefaultSorter = NO_SORT;

  /**
   * The member sort index.
   */
  private int mSortIndex = -1;

  /**
   * Sets the default sorter.
   *
   * @param sorter the new default sorter
   */
  public final void setDefaultSorter(ModernDataIndexSorter sorter) {
    if (sorter == null) {
      return;
    }

    mDefaultSorter = sorter;
  }

  /**
   * Sets the.
   *
   * @param sortIndex the sort index
   * @param sorter    the sorter
   */
  public final void set(int sortIndex, ModernDataIndexSorter sorter) {
    if (sorter != null && sortIndex > -1) {
      mSortMap.put(sortIndex, sorter);
    }
  }

  /**
   * Sets the sort index.
   *
   * @param sortIndex the new sort index
   */
  public void setSortIndex(int sortIndex) {
    // System.err.println("Setting sort sortIndex " + sortIndex);

    mSortIndex = sortIndex;
  }

  /**
   * Gets the sort index.
   *
   * @return the sort index
   */
  public int getSortIndex() {
    return mSortIndex;
  }

  /**
   * Returns the selected sorter.
   *
   * @return the sorter
   */
  public ModernDataIndexSorter getSorter() {
    return get(mSortIndex);
  }

  /**
   * Returns the sortIndex sorter for a given table sortIndex. If the index is -1,
   * the special nosort indexer is returned so that the table can be display as it
   * was loaded (i.e unsorted)
   *
   * @param index the index
   * @return the modern data index sorter
   */
  public final ModernDataIndexSorter get(int index) {
    if (index == -1) {
      return NO_SORT;
    }

    ModernDataIndexSorter sorter = mSortMap.get(index);

    // System.err.println("get sorter " + index + " " + sorter);

    // Use a default if not specified
    if (sorter == null) {
      sorter = mDefaultSorter;
    }

    return sorter;
  }

  /**
   * Clear.
   */
  public void clear() {
    mSortMap.clear();
  }
}
