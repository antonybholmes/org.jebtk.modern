/**
 * Copyright (c) 2016, Antony Holmes
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
package org.jebtk.modern.scrollpane;

import java.awt.Component;

import org.jebtk.modern.table.ModernTable;

/**
 * A specialized scroller for responding to vertical scroll keys. This only
 * instigates scrolling when the selection rectangle moves outside the view
 * window.
 */
public abstract class ModernTableScroller extends Scroller {

  /** The m table. */
  protected ModernTable mTable;

  /**
   * Instantiates a new modern table scroller.
   *
   * @param table the table
   */
  public ModernTableScroller(ModernTable table) {
    mTable = table;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.scrollpane.Scroller#keyScroll(boolean,
   * java.awt.Component, org.abh.common.ui.scrollpane.ModernScrollBar)
   */
  @Override
  public void keyScroll(boolean up, Component c, ModernScrollBar scrollbar) {
    // Disable the default key scrolling so that keys can be used to
    // move around cells rather than scrolling. Instead use a selection
    // listener on the table to work out if the selected cell is outside
    // the visible range and therefore should be scrolled to.
  }
}
