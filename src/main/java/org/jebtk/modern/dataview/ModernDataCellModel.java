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
package org.jebtk.modern.dataview;

import java.util.HashMap;
import java.util.Map;

import org.jebtk.core.event.ChangeEvent;

// TODO: Auto-generated Javadoc
/**
 * Generic model for storing objects associated with cells. Since we don't
 * actually want to associate objects with every cell to save memory, we lazily
 * assign objects as necessary and use a default for all cells that do not have
 * an assignment. We further allow row and column association so that individual
 * cells do not need an object.
 *
 * @author Antony Holmes
 * @param <T> the generic type
 */
public abstract class ModernDataCellModel<T> extends ModernDataViewListeners {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member default object.
   */
  private T mDefaultObject;

  /**
   * The member cell level map.
   */
  private Map<Integer, Map<Integer, T>> mCellLevelMap = new HashMap<>();

  /**
   * The member row level map.
   */
  private Map<Integer, T> mRowLevelMap = new HashMap<>();

  /**
   * The member column level map.
   */
  private Map<Integer, T> mColumnLevelMap = new HashMap<>();

  /**
   * Instantiates a new modern data cell model.
   *
   * @param defaultObject the default object
   */
  public ModernDataCellModel(T defaultObject) {
    setDefault(defaultObject);
  }

  /**
   * Sets the default.
   *
   * @param defaultObject the new default
   */
  public void setDefault(T defaultObject) {
    mDefaultObject = defaultObject;

    fireDataUpdated(new ChangeEvent(this));
  }

  /**
   * Equivalent to setDefault().
   *
   * @param defaultObject the default object
   */
  public void set(T defaultObject) {
    setDefault(defaultObject);
  }

  /**
   * Gets the.
   *
   * @param row the row
   * @param col the col
   * @return the t
   */
  public T get(int row, int col) {
    if (mCellLevelMap.containsKey(row) && mCellLevelMap.get(row).containsKey(col)) {
      return mCellLevelMap.get(row).get(col);
    } else if (mColumnLevelMap.containsKey(col)) {
      return mColumnLevelMap.get(col);
    } else if (mRowLevelMap.containsKey(row)) {
      return mRowLevelMap.get(row);
    } else {
      return mDefaultObject;
    }
  }

  /**
   * Sets the.
   *
   * @param row the row
   * @param col the col
   * @param o   the o
   */
  public void set(int row, int col, T o) {
    if (!mCellLevelMap.containsKey(row)) {
      mCellLevelMap.put(row, new HashMap<>());
    }

    mCellLevelMap.get(row).put(col, o);

    fireDataUpdated(new ChangeEvent(this));
  }

  /**
   * Sets the row.
   *
   * @param row the row
   * @param o   the o
   */
  public void setRow(int row, T o) {
    if (!mRowLevelMap.containsKey(row)) {
      mRowLevelMap.put(row, o);
    }

    fireDataUpdated(new ChangeEvent(this));
  }

  /**
   * Sets the col.
   *
   * @param col the col
   * @param o   the o
   */
  public void setCol(int col, T o) {
    if (!mColumnLevelMap.containsKey(col)) {
      mColumnLevelMap.put(col, o);
    }

    fireDataUpdated(new ChangeEvent(this));
  }
}
