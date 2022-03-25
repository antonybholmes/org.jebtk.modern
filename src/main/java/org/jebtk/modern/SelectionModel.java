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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jebtk.core.collections.CollectionUtils;
import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.text.TextUtils;
import org.jebtk.modern.event.ModernSelectionListeners;

// TODO: Auto-generated Javadoc
/**
 * Generic model for storing items and notifying of changes.
 *
 * @author Antony Holmes
 * @param <T> the generic type
 */
public class SelectionModel<T> extends ModernSelectionListeners implements Iterable<T> {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The items.
   */
  private final List<T> mItems = new ArrayList<T>();


  /**
   * Sets the.
   *
   * @param item the item
   */
  public final void set(T item) {
    clearNoUpdate();

    add(item);
  }

  /**
   * Sets the.
   *
   * @param items the items
   */
  public void set(Set<T> items) {
    clearNoUpdate();

    add(items);
  }

  /**
   * Sets the.
   *
   * @param items the items
   */
  public void set(SelectionModel<T> items) {
    clearNoUpdate();

    add(items);
  }

  /**
   * Clear the current selection and set a new list of items.
   *
   * @param items the items
   */
  public final void set(List<T> items) {
    clearNoUpdate();

    add(items);
  }

  /**
   * Adds the.
   *
   * @param item the item
   */
  public final void add(T item) {
    add(CollectionUtils.asList(item));
  }

  /**
   * Adds the.
   *
   * @param items the items
   */
  public void add(Set<T> items) {
    if (CollectionUtils.isNullOrEmpty(items)) {
      return;
    }

    for (T item : items) {
      this.mItems.add(item);
    }

    fireSelectionAdded(new ChangeEvent(this));
  }

  /**
   * Adds the.
   *
   * @param items the items
   */
  public void add(SelectionModel<T> items) {
    if (items == null) {
      return;
    }

    for (T item : items) {
      mItems.add(item);
    }

    fireSelectionAdded(new ChangeEvent(this));
  }

  /**
   * Adds the.
   *
   * @param items the items
   */
  public void add(List<T> items) {
    if (items == null) {
      return;
    }

    for (T item : items) {
      mItems.add(item);
    }

    fireSelectionAdded(new ChangeEvent(this));
  }

  /**
   * Returns a list copy of the items in the selection.
   *
   * @return the items
   */
  public List<T> getItems() {
    List<T> ret = new ArrayList<>();

    for (T item : mItems) {
      ret.add(item);
    }

    return ret;
  }

  /**
   * Remove a selected node.
   *
   * @param index the index
   */
  public void remove(int index) {
    mItems.remove(index);
  }

  /**
   * Removes the.
   *
   * @param item the item
   */
  public void remove(T item) {
    mItems.remove(item);
  }

  /**
   * Clear.
   */
  public final void clear() {
    clearNoUpdate();

    fireSelectionRemoved(new ChangeEvent(this));
  }

  /**
   * Clear no update.
   */
  public final void clearNoUpdate() {
    mItems.clear();
  }

  /**
   * Returns the first selected item or null if nothing is selected.
   *
   * @return the selected
   */
  public T getSelected() {
    return get(0);
  }

  /**
   * Gets the.
   *
   * @param index the index
   * @return the t
   */
  public T get(int index) {
    if (mItems.isEmpty() || index < 0 || index >= mItems.size()) {
      return null;
    }

    return mItems.get(index);
  }

  /**
   * Size.
   *
   * @return the int
   */
  public int size() {
    return mItems.size();
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

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return TextUtils.join(mItems, TextUtils.COMMA_DELIMITER);
  }
}
