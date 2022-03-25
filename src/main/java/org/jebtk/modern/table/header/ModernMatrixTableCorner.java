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
package org.jebtk.modern.table.header;

import java.awt.Graphics2D;

import org.jebtk.core.geom.IntRect;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.table.ModernTable;

/**
 * Paints the corner of a table.
 *
 * @author Antony Holmes
 */
public class ModernMatrixTableCorner extends ModernWidget {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member table.
   */
  private ModernTable mTable;

  /**
   * Instantiates a new modern matrix table corner.
   *
   * @param <T>    the generic type
   * @param matrix the matrix
   */
  public <T> ModernMatrixTableCorner(ModernTable matrix) {
    mTable = matrix;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawBackground(java.awt.Graphics2D)
   */
  @Override
  public void drawBackground(Graphics2D g2) {
    fill(g2, ModernTableHeader.HEADER_BACKGROUND, mInternalRect);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    int w = (int) mTable.scale(mTable.getRowModel().getHeaderSize());
    int h = (int) mTable.scale(mTable.getColumnModel().getHeaderSize());

    fill(g2, ModernTableHeader.HEADER_BACKGROUND, new IntRect(0, 0, w, h));

    g2.setColor(ModernWidget.LINE_COLOR);

    g2.drawLine(0, h - 1, w - 1, h - 1);

    g2.drawLine(w - 1, 0, w - 1, h - 1);

    /*
     * g2.setColor(ThemeService.getInstance().getColors().getHighlight(2));
     * 
     * 
     * // Draw triangle in corner
     * 
     * int o = getHeight() / 4; int h = o * 2;
     * 
     * GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 3);
     * 
     * polygon.moveTo(getWidth() - o, o);
     * 
     * polygon.lineTo(getWidth() - o, o + h); polygon.lineTo(getWidth() - o - h, o +
     * h);
     * 
     * polygon.closePath(); g2.fill(polygon);
     */

    /*
     * Graphics2D g2Temp = ImageUtils.clone(g2);
     * 
     * try { g2Temp.setColor(TEXT_COLOR);
     * 
     * // g2Temp.translate(w, 0);
     * 
     * if (mTable.getModel().getIndex().getNames() != null) { for (String name :
     * mTable.getModel().getIndex().getNames()) { String text =
     * getTruncatedText(g2Temp, name, w);
     * 
     * int x = (w - g2Temp.getFontMetrics().stringWidth(text)) / 2; int y =
     * getTextYPosCenter(g2Temp, h);
     * 
     * g2Temp.drawString(text, x, y);
     * 
     * g2Temp.translate(w, 0); } } } finally { g2Temp.dispose(); }
     */
  }
}
