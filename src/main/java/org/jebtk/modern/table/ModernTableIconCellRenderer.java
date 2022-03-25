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

import org.jebtk.modern.AssetService;
import org.jebtk.modern.dataview.ModernData;
import org.jebtk.modern.dataview.ModernDataCellRenderer;
import org.jebtk.modern.graphics.icons.ModernIcon;

// TODO: Auto-generated Javadoc
/**
 * Displays an icon in a table cell.
 * 
 * @author Antony Holmes
 *
 */
public class ModernTableIconCellRenderer extends ModernDataCellRenderer {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /// protected int end = 0;

  // protected JLabel label = new JLabel();

  /**
   * The member icon.
   */
  protected ModernIcon mIcon;

  // protected boolean highlight = true;

  /**
   * Instantiates a new modern table icon cell renderer.
   *
   * @param icon the icon
   */
  public ModernTableIconCellRenderer(ModernIcon icon) {

    mIcon = icon;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  public void drawForegroundAA(Graphics2D g2) {

    mIcon.drawIcon(g2, (this.getWidth() - AssetService.ICON_SIZE_16) / 2,
        (this.getHeight() - AssetService.ICON_SIZE_16) / 2, AssetService.ICON_SIZE_16);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataCellRenderer#
   * getCellRendererComponent(org.abh.lib.ui.modern.dataview.ModernData,
   * java.lang.Object, boolean, boolean, boolean, int, int)
   */
  public final Component getCellRendererComponent(ModernData table, Object value, boolean highlight, boolean isSelected,
      boolean hasFocus, int row, int column) {
    return super.getCellRendererComponent(table, value, highlight, isSelected, hasFocus, row, column);
  }
}