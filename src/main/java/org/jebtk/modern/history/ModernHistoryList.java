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
package org.jebtk.modern.history;

import org.jebtk.modern.event.ModernSelectionEventProducer;
import org.jebtk.modern.list.ModernList;
import org.jebtk.modern.list.ModernListModel;

// TODO: Auto-generated Javadoc
/**
 * A flowchart style control similar to a modern list, but indicates stages etc.
 *
 * @author Antony Holmes
 * @param <T> the generic type
 */
public class ModernHistoryList<T> extends ModernList<T> implements ModernSelectionEventProducer {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new modern history list.
   */
  public ModernHistoryList() {
    super();

    setup();
  }

  /**
   * Instantiates a new modern history list.
   *
   * @param renderer the renderer
   */
  public ModernHistoryList(ModernHistoryListCellRenderer renderer) {
    setup();

    setCellRenderer(renderer);
  }

  /**
   * Instantiates a new modern history list.
   *
   * @param model the model
   */
  public ModernHistoryList(ModernListModel<T> model) {
    setModel(model);

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    setRowHeight(48);

    setCellRenderer(new ModernHistoryListBasicCellRenderer());
  }

  /*
   * @Override protected final void createImage(Graphics2D g2) {
   * 
   * ModernDataRowSelection visibleCells = calculateVisibleCells();
   * 
   * Graphics2D g2Table = (Graphics2D)g2.create();
   * 
   * //int counter = 0;
   * 
   * for (int i = visibleCells.getStartRow(); i <= visibleCells.getEndRow(); ++i)
   * { if (i == getItemCount()) { break; }
   * 
   * //System.err.println("get row " + i);
   * 
   * Component c = mRenderer.getCellRendererComponent(this, getValueAt(i), i ==
   * mHighlightCellIndex, mSelectionModel.contains(i), this.isFocusOwner(), i);
   * 
   * c.setSize(getWidth(), mRowHeight);
   * 
   * c.print(g2Table);
   * 
   * // Move to the next cell location. g2Table.translate(0, mRowHeight);
   * 
   * //++counter; }
   * 
   * g2Table.dispose(); }
   */

}
