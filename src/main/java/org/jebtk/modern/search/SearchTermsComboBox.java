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
import java.util.Collections;
import java.util.List;

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.modern.combobox.ModernComboBox;
import org.jebtk.modern.menu.ModernIconMenuItem;

/**
 * Specialised combobox for showing selecting search criteria.
 *
 * @author Antony Holmes
 */
public class SearchTermsComboBox extends ModernComboBox implements SearchTermEventListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant MAX_SEARCHES.
   */
  private static final int MAX_SEARCHES = 5;

  /**
   * Instantiates a new search terms combo box.
   *
   * @param searchTermsModel the search terms model
   */
  public SearchTermsComboBox(SearchTermsService searchTermsModel) {
    searchTermsModel.addSearchTermListener(this);
    addSearches();

    setEditable(true);
  }

  /**
   * Adds the searches.
   */
  public final void addSearches() {
    int c = 0;

    clear();

    List<String> terms = new ArrayList<String>();

    for (String search : SearchTermsService.getInstance()) {
      // searchesCombo.addItem(search);

      // create a list of 10 search items

      if (c == MAX_SEARCHES) {
        break;
      }

      ++c;

      terms.add(search);
    }

    Collections.sort(terms);

    for (String term : terms) {
      addScrollMenuItem(new ModernIconMenuItem(term));
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.search.SearchTermEventListener#searchTermsChanged(
   * org. abh.lib.event.ChangeEvent)
   */
  @Override
  public void searchTermsChanged(ChangeEvent e) {
    addSearches();
  }
}
