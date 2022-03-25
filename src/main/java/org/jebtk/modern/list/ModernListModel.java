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
package org.jebtk.modern.list;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.jebtk.core.collections.CollectionUtils;
import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.io.Io;
import org.jebtk.core.text.TextUtils;
import org.jebtk.modern.dataview.ModernDataViewEventProducer;
import org.jebtk.modern.dataview.ModernDataViewListener;
import org.jebtk.modern.dataview.ModernDataViewListeners;

// TODO: Auto-generated Javadoc
/**
 * ModernSelection model to determine which rows are selected in a list, table,
 * tree etc.
 *
 * @author Antony Holmes
 * @param <T> the generic type
 */
public class ModernListModel<T> implements Iterable<T>, ModernDataViewEventProducer {

  /**
   * The member listeners.
   */
  private ModernDataViewListeners mListeners = new ModernDataViewListeners();

  /**
   * The member items.
   */
  private List<T> mItems = new ArrayList<T>(100);

  /**
   * Returns the number of items in the list model.
   *
   * @return the item count
   */
  public int getItemCount() {
    return mItems.size();
  }

  /**
   * Gets the value at.
   *
   * @param row the row
   * @return the value at
   */
  public T getValueAt(int row) {
    if (row < 0 || row >= mItems.size()) {
      return null;
    }

    return mItems.get(row);
  }

  /**
   * Adds the value.
   *
   * @param item the item
   */
  public void addValue(T item) {
    mItems.add(item);

    fireDataChanged();
  }

  /**
   * Sets the values.
   *
   * @param items the new values
   */
  public void setValues(Collection<T> items) {
    // remove all without notifying
    remove();

    addValues(items);
  }

  /**
   * Adds the values.
   *
   * @param items the items
   */
  public void addValues(Collection<T> items) {
    mItems.addAll(items);

    fireDataChanged();
  }

  /**
   * Adds the values.
   *
   * @param items the items
   * @param index the index
   */
  public void addValues(Collection<T> items, int index) {
    mItems.addAll(index, items);

    fireDataChanged();
  }

  /**
   * Add the items in order at the start of the list rather than the end.
   *
   * @param items the items
   */
  public void addValuesAtStart(List<T> items) {
    addValues(items, 0);
  }

  /**
   * Removes the value at.
   *
   * @param row the row
   */
  public void removeValueAt(int row) {
    if (mItems.size() == 0 || row < 0 || row >= mItems.size()) {
      return;
    }

    mItems.remove(row);

    fireDataChanged();
  }

  /**
   * Remove multiple values.
   *
   * @param indices the indices
   */
  public void removeValuesAt(ArrayList<Integer> indices) {
    for (int i : CollectionUtils.reverse(CollectionUtils.sort(indices))) {
      mItems.remove(i);
    }

    fireDataChanged();
  }

  /**
   * Clear.
   */
  public void clear() {
    remove();

    fireDataChanged();
  }

  /**
   * Remove all items without notifying of change.
   */
  public void remove() {
    mItems.clear();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataViewEventProducer#
   * addDataViewListener(org.abh.lib.ui.modern.dataview.ModernDataViewListener)
   */
  @Override
  public void addDataViewListener(ModernDataViewListener l) {
    mListeners.addDataViewListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataViewEventProducer#
   * removeDataViewListener(org.abh.lib.ui.modern.dataview.
   * ModernDataViewListener)
   */
  @Override
  public void removeDataViewListener(ModernDataViewListener l) {
    mListeners.removeDataViewListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.dataview.ModernDataViewEventProducer#fireDataChanged(
   * org.abh.lib.event.ChangeEvent)
   */
  @Override
  public void fireDataChanged(ChangeEvent e) {
    // System.err.println("sdfsdf");

    mListeners.fireDataChanged(e);
  }

  /**
   * Fire data changed.
   */
  public void fireDataChanged() {
    fireDataChanged(new ChangeEvent(this));
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.dataview.ModernDataViewEventProducer#fireDataUpdated(
   * org.abh.lib.event.ChangeEvent)
   */
  @Override
  public void fireDataUpdated(ChangeEvent e) {
    mListeners.fireDataUpdated(e);
  }

  /**
   * Fire data updated.
   */
  public void fireDataUpdated() {
    fireDataUpdated(new ChangeEvent(this));
  }

  /**
   * Indicates an item should be reordered in the list.
   *
   * @param index    the index
   * @param newIndex the new index
   */
  public void move(int index, int newIndex) {
    shift(index, newIndex);

    fireDataChanged();
  }

  /**
   * Shift.
   *
   * @param index    the index
   * @param newIndex the new index
   */
  public void shift(int index, int newIndex) {
    // don't pointless swap an item with itself
    if (index == newIndex || index == -1 || newIndex == -1) {
      return;
    }

    // Bound the new index. The index can be the number of items
    // in the list. This indicates the item should be inserted at
    // the end of the list.
    newIndex = Math.max(0, Math.min(newIndex, mItems.size()));

    // System.err.println("swap " + index + " " + newIndex);

    T tmp = mItems.get(index);

    mItems.remove(index);

    if (newIndex < getItemCount()) {
      mItems.add(newIndex, tmp);
    } else {
      mItems.add(tmp);
    }
  }

  /**
   * Shift.
   *
   * @param fromIndices the from indices
   * @param toIndices   the to indices
   */
  public void shift(List<Integer> fromIndices, List<Integer> toIndices) {
    for (int i = 0; i < fromIndices.size(); ++i) {
      shift(fromIndices.get(i), toIndices.get(i));
    }

    fireDataUpdated();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Iterable#iterator()
   */
  @Override
  public Iterator<T> iterator() {
    return mItems.iterator();
  }

  /**
   * Parse a text file into a list model. The first line is assumed to be a header
   * and is ignored.
   *
   * @param file the file
   * @return the modern list model
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static ModernListModel<String> parse(File file) throws IOException {
    ModernListModel<String> model = new ModernListModel<String>();

    BufferedReader reader = new BufferedReader(new FileReader(file));

    String line;

    try {
      // skip header
      reader.readLine();

      while ((line = reader.readLine()) != null) {
        if (Io.isEmptyLine(line)) {
          continue;
        }

        List<String> tokens = TextUtils.fastSplit(line, TextUtils.TAB_DELIMITER);

        model.addValue(tokens.get(0));
      }
    } finally {
      reader.close();
    }

    return model;
  }
}
