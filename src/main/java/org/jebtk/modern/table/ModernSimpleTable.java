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
package org.jebtk.modern.table;

import java.awt.Component;
import java.awt.Graphics2D;

import org.jebtk.modern.dataview.ModernDataCellRenderer;
import org.jebtk.modern.dataview.ModernDataSelection;
import org.jebtk.modern.graphics.DrawingContext;

// TODO: Auto-generated Javadoc
/**
 * Simple table that does not highlight rows or columns.
 *
 * @author Antony Holmes
 */
public class ModernSimpleTable extends ModernRowTable {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.table.ModernTable#createTableImage(java.awt.Graphics2D,
   * org.abh.common.ui.graphics.DrawingContext,
   * org.abh.common.ui.dataview.ModernDataSelection)
   */
  @Override
  protected final void createTableImage(Graphics2D g2, DrawingContext context, ModernDataSelection visibleCells) {

    Graphics2D g2Table = (Graphics2D) g2.create();

    try {
      // translate to the start of the rendering rectangle so that we skip
      // all non visible cells
      // translate(g2Table);

      ModernDataCellRenderer renderer;

      int x;

      for (int i = visibleCells.getStartRow(); i <= visibleCells.getEndRow(); ++i) {
        if (i >= getRowCount()) {
          break;
        }

        x = 0;

        for (int j = visibleCells.getStartCol(); j <= visibleCells.getEndCol(); ++j) {
          if (j >= getColCount()) {
            continue;
          }

          renderer = mCellRendererModel.get(i, j);

          Component c = renderer.getCellRendererComponent(this, getValueAt(i, j), false, false, isFocusOwner(), i, j);

          c.setSize(mColumnModel.getWidth(j), mRowModel.getWidth(i));

          c.print(g2Table);

          // Move to the next cell location.
          g2Table.translate(mColumnModel.getWidth(j), 0);

          x += mColumnModel.getWidth(j);
        }

        // Each time we start a new row, translate back to the X origin.
        g2Table.translate(-x, mRowModel.getWidth(i));
      }
    } finally {
      g2Table.dispose();
    }
  }
}
