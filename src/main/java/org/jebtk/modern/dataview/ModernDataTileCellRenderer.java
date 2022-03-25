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

import java.awt.Component;
import java.awt.Graphics2D;

import org.jebtk.modern.AssetService;
import org.jebtk.modern.graphics.icons.FileVectorIcon;
import org.jebtk.modern.panel.ModernPanel;

// TODO: Auto-generated Javadoc
/**
 * The class ModernDataTileCellRenderer.
 */
public class ModernDataTileCellRenderer extends ModernDataGridIconTextCellRenderer {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new modern data tile cell renderer.
   */
  public ModernDataTileCellRenderer() {
    setIcon(AssetService.getInstance().loadIcon(FileVectorIcon.class, 64));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    if (this.mIcon != null) {
      // this.icon.draw(this,
      // g2,
      // new Rectangle((this.getWidth() - 64) / 2, (this.getHeight() - 64) / 2,
      // 64,
      // 64));

      this.mIcon.drawIcon(g2, (getWidth() - 64) / 2, (getWidth() - 64) / 2, 64);
    }

    String t = getTruncatedText(g2, mText, 0, mRect.getW());

    int x = (getWidth() - g2.getFontMetrics().stringWidth(t)) / 2;
    int y = getHeight() - ModernPanel.DOUBLE_PADDING;

    g2.setColor(TEXT_COLOR);
    g2.drawString(t, x, y);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataCellRenderer#
   * getCellRendererComponent(org.abh.lib.ui.modern.dataview.ModernData,
   * java.lang.Object, boolean, boolean, boolean, int, int)
   */
  public Component getCellRendererComponent(ModernData dataView, Object value, boolean highlight, boolean isSelected,
      boolean hasFocus, int row, int column) {

    setText(value.toString());

    return super.getCellRendererComponent(dataView, value, highlight, isSelected, hasFocus, row, column);
  }
}