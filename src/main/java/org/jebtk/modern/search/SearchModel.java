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

import org.jebtk.core.model.ItemModel;

/**
 * The class SearchModel.
 */
public class SearchModel extends ItemModel<String> {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The m case sensitive. */
  private boolean mCaseSensitive;

  /** The m in list. */
  private boolean mInList;

  /** The m exact. */
  private boolean mExact;

  /**
   * Sets the case sensitive.
   *
   * @param caseSensitive the new case sensitive
   */
  public void setCaseSensitive(boolean caseSensitive) {
    updateCaseSensitive(caseSensitive);

    fireChanged();
  }

  /**
   * Update case sensitive.
   *
   * @param caseSensitive the case sensitive
   */
  public void updateCaseSensitive(boolean caseSensitive) {
    mCaseSensitive = caseSensitive;
  }

  /**
   * Sets the in list.
   *
   * @param inList the new in list
   */
  public void setInList(boolean inList) {
    updateInList(inList);

    fireChanged();
  }

  /**
   * Update in list.
   *
   * @param inList the in list
   */
  public void updateInList(boolean inList) {
    mInList = inList;
  }

  /**
   * Sets the exact match.
   *
   * @param exact the new exact match
   */
  public void setExactMatch(boolean exact) {
    setExactMatch(exact);

    fireChanged();
  }

  /**
   * Update exact match.
   *
   * @param exact the exact
   */
  public void updateExactMatch(boolean exact) {
    mExact = exact;
  }

  /**
   * Gets the in list.
   *
   * @return the in list
   */
  public boolean getInList() {
    return mInList;
  }

  /**
   * Gets the exact.
   *
   * @return the exact
   */
  public boolean getExact() {
    return mExact;
  }

  /**
   * Gets the case sensitive.
   *
   * @return the case sensitive
   */
  public boolean getCaseSensitive() {
    return mCaseSensitive;
  }
}
