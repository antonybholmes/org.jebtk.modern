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
package org.jebtk.modern;

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.text.TextUtils;
import org.jebtk.modern.event.ModernSelectionListeners;

// TODO: Auto-generated Javadoc
/**
 * ModernSelection model to determine which rows are selected in a list, table,
 * tree etc.
 *
 * @author Antony Holmes
 *
 */
public class SelectionRangeModel extends ModernSelectionListeners implements Iterable<Integer> {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant SELECTION_CHANGED.
   */
  public static final String SELECTION_CHANGED = "selection_changed";
  // public static final String SELECTION_CLEARED = "selection_cleared";
  // public static final String SELECTION_ADDED = "selection_added";

  /**
   * The member selection set.
   */
  private final TreeSet<Integer> mSelectionSet = new TreeSet<>();

  /**
   * The member selection.
   */
  // private List<Integer> mSelection = new ArrayList<Integer>(100);

  /** The m enabled. */
  private boolean mEnabled = true;

  /** The m current. */
  private int mCurrent = -1;

  private int mPrevious = -1;

  public void setSelectionInterval(int s, int e) {
    addSelectionInterval(s, e, SelectionRangeType.REPLACE);
  }

  /**
   * Sets the selection interval.
   *
   * @param s the s
   * @param e the e
   */
  public void addSelectionInterval(int s, int e, SelectionRangeType type) {
    updateSelectionInterval(s, e, type);

    fireSelectionAdded();
  }

  public void updateSelectionInterval(int s, int e, SelectionRangeType type) {
    if (!mEnabled) {
      return;
    }

    if (type == SelectionRangeType.REPLACE) {
      removeAll();
    }

    int s1 = Math.min(s, e);
    int e1 = Math.max(s, e);

    for (int i = s1; i <= e1; ++i) {
      mSelectionSet.add(i);
    }

    mCurrent = e;

    // update();
  }

  public void setSelection(final Collection<Integer> indices) {
    addSelection(indices, SelectionRangeType.REPLACE);
  }

  /**
   * Sets the selection.
   *
   * @param indices the new selection
   * @param type
   */
  public void addSelection(final Collection<Integer> indices, SelectionRangeType type) {
    updateSelection(indices, type);

    fireSelectionAdded();
  }

  /**
   * Add multiple indices to the selection.
   *
   * @param indices the indices
   * @param type
   */
  public void updateSelection(final Collection<Integer> indices, SelectionRangeType type) {
    if (!mEnabled) {
      return;
    }

    if (type == SelectionRangeType.REPLACE) {
      removeAll();
    }

    for (int i : indices) {
      mSelectionSet.add(i);
    }

    // update();
  }

  /**
   * Remove a selected node.
   *
   * @param index the index
   */
  public void remove(int index) {
    delete(index);

    fireSelectionRemoved();
  }

  /**
   * Remove without triggering an event.
   * 
   * @param index
   */
  public void delete(int index) {
    if (mCurrent == index) {
      mCurrent = -1;
    }

    mSelectionSet.remove(index);

    // update();
  }

  /**
   * Clear.
   */
  public void clear() {
    removeAll();

    fireSelectionRemoved();
  }

  /**
   * Remove all elements without triggering an event.
   */
  public void removeAll() {
    // mPrevious = -1;
    // mCurrent = -1;
    mSelectionSet.clear();
  }

  /**
   * Update.
   */
  // private void update() {
  /// mSelection = CollectionUtils.sort(mSelectionSet);
  // }

  /**
   * Fire selection changed.
   */
  public void fireSelectionAdded() {
    fireSelectionAdded(new ChangeEvent(this));
  }

  public void fireSelectionRemoved() {
    fireSelectionRemoved(new ChangeEvent(this));
  }

  public void add(int index) {
    add(index, SelectionRangeType.ADD);
  }

  public void setSelection(int index) {
    set(index);
  }

  public void set(int index) {
    add(index, SelectionRangeType.REPLACE);
  }

  /**
   * Add a row to the selection.
   *
   * @param index the row
   * @param type
   */
  public void add(int index, SelectionRangeType type) {
    update(index, type);

    fireSelectionAdded();
  }

  /**
   * Add a new item to the selection model.
   *
   * @param index the index
   * @param type
   */
  public void update(int index, SelectionRangeType type) {
    if (!mEnabled) {
      return;
    }

    if (type == SelectionRangeType.REPLACE) {
      removeAll();
    }

    mSelectionSet.add(index);

    mPrevious = mCurrent;
    mCurrent = index;

    // update();
  }

  /**
   * Returns true if an index exists in the selection.
   *
   * @param i the i
   * @return Returns true if an i exists in the selection, false otherwise.
   */
  public boolean contains(int i) {
    return mSelectionSet.contains(i);
  }

  /**
   * Return the first selectable index.
   *
   * @return the int
   */
  public int first() {
    if (mSelectionSet.isEmpty()) {
      return -1;
    }

    return mSelectionSet.first();
  }

  /**
   * Return the currently selected item (the last item that was added even if the
   * sorted order of items means this index appears in the middle).
   *
   * @return the current
   */
  public int getCurrent() {
    return mCurrent;
  }

  /**
   * Return the last previously selected item or -1 otherwise.
   *
   * @return the current
   */
  public int getPrevious() {
    return mPrevious;
  }

  /**
   * Last.
   *
   * @return the int
   */
  public int last() {
    if (mSelectionSet.isEmpty()) {
      return -1;
    }

    return mSelectionSet.last();
  }

  /**
   * Returns the number of selected indices.
   *
   * @return the int
   */
  public int size() {
    return mSelectionSet.size();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Iterable#iterator()
   */
  @Override
  public Iterator<Integer> iterator() {
    return mSelectionSet.iterator();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return TextUtils.join(mSelectionSet, TextUtils.COMMA_DELIMITER);
  }

  /**
   * Sets the enabled.
   *
   * @param enabled the new enabled
   */
  public void setEnabled(boolean enabled) {
    mEnabled = enabled;
  }
}
