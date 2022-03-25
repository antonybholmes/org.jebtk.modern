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
package org.jebtk.modern.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jebtk.core.NameGetter;
import org.jebtk.core.event.ChangeListeners;

// TODO: Auto-generated Javadoc
/**
 * Allows sort objects to be shared between entities that control how samples
 * and experiments are sorted.
 *
 * @author Antony Holmes
 * @param <T> the generic type
 */
public class SortModel<T extends NameGetter> extends ChangeListeners implements Iterable<Sorter<T>> {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member sorters.
   */
  private List<Sorter<T>> mSorters = new ArrayList<Sorter<T>>();

  /**
   * The member sorter map.
   */
  private Map<String, Sorter<T>> mSorterMap = new HashMap<String, Sorter<T>>();

  /**
   * The member sorter.
   */
  private Sorter<T> mSorter;

  /**
   * The member sort ascending.
   */
  private boolean mSortAscending = true;

  /**
   * The member expanded.
   */
  private boolean mExpanded = true;

  /**
   * The member default sorter.
   */
  private Sorter<T> mDefaultSorter;

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Iterable#iterator()
   */
  public Iterator<Sorter<T>> iterator() {
    return mSorters.iterator();
  }

  /**
   * Gets the.
   *
   * @param name the name
   * @return the sorter
   */
  public Sorter<T> get(String name) {
    return mSorterMap.get(name);
  }

  /**
   * Adds the.
   *
   * @param sorter the sorter
   */
  public void add(Sorter<T> sorter) {
    mSorters.add(sorter);
    mSorterMap.put(sorter.getName(), sorter);
  }

  /**
   * Sets the default.
   *
   * @param name the new default
   */
  public void setDefault(String name) {
    mDefaultSorter = mSorterMap.get(name);

    setSorter(name);
  }

  /**
   * Gets the default.
   *
   * @return the default
   */
  public Sorter<T> getDefault() {
    return mDefaultSorter;
  }

  /**
   * Sets the sorter.
   *
   * @param name the new sorter
   */
  public void setSorter(String name) {
    if (!mSorterMap.containsKey(name)) {
      return;
    }

    mSorter = mSorterMap.get(name);

    fireChanged();
  }

  /**
   * Gets the sorter.
   *
   * @return the sorter
   */
  public Sorter<T> getSorter() {
    return mSorter;
  }

  /**
   * Sets the sort ascending.
   *
   * @param sortAscending the new sort ascending
   */
  public void setSortAscending(boolean sortAscending) {
    mSortAscending = sortAscending;

    fireChanged();
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
   * Sets the expanded.
   *
   * @param expanded the new expanded
   */
  public void setExpanded(boolean expanded) {
    if (expanded == mExpanded) {
      return;
    }

    mExpanded = expanded;

    fireChanged();
  }

  /**
   * Gets the expanded.
   *
   * @return the expanded
   */
  public boolean getExpanded() {
    return mExpanded;
  }
}
