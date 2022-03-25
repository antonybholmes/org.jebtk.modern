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

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jebtk.core.collections.UniqueArrayList;

// TODO: Auto-generated Javadoc
/**
 * Model to determine which items to display.
 * 
 * @author Antony Holmes
 *
 */
public class FilterModel extends FilterEventListeners implements Iterable<String> {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member filter map.
   */
  private Map<String, Boolean> mFilterMap = new HashMap<String, Boolean>();

  /**
   * The member filters.
   */
  private List<String> mFilters = new UniqueArrayList<String>(100);

  /**
   * Returns true if the item should be kept.
   *
   * @param name the name
   * @return the filter
   */
  public boolean keep(String name) {
    if (!mFilterMap.containsKey(name)) {
      return true;
    }

    return mFilterMap.get(name);
  }

  /**
   * Sets which filter names are selected.
   *
   * @param names  the names
   * @param filter the filter
   */
  public void setSelected(Collection<String> names, boolean filter) {
    clear();

    for (String name : names) {
      mFilters.add(name);
      mFilterMap.put(name, filter);
    }

    fireFiltersChanged();
  }

  /**
   * Sets the filters.
   *
   * @param updates the updates
   */
  public void setSelected(Map<String, Boolean> updates) {
    for (String name : updates.keySet()) {
      mFilterMap.put(name, updates.get(name));
    }

    fireFiltersUpdated();
  }

  /**
   * Sets the filters.
   *
   * @param selected the new filters
   */
  public void setFilters(boolean selected) {
    for (String name : new HashSet<String>(mFilterMap.keySet())) {
      mFilterMap.put(name, selected);
    }

    fireFiltersUpdated();
  }

  /**
   * Sets the filter.
   *
   * @param name   the name
   * @param filter the filter
   */
  public void setFilter(String name, boolean filter) {
    updateFilter(name, filter);

    fireFiltersUpdated();
  }

  public void updateFilter(String name, boolean filter) {
    mFilters.add(name);

    mFilterMap.put(name, filter);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Iterable#iterator()
   */
  @Override
  public Iterator<String> iterator() {
    return mFilters.iterator();
  }

  /**
   * Clear.
   */
  public void clear() {
    mFilterMap.clear();
    mFilters.clear();

    // fireFiltersUpdated();
  }
}
