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
package org.jebtk.modern.layers;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import org.jebtk.modern.AssetService;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.table.ModernTableCheckboxCellRenderer;
import org.jebtk.modern.theme.ThemeService;

// TODO: Auto-generated Javadoc
/**
 * Displays an icon in a table cell.
 * 
 * @author Antony Holmes
 *
 */
public class ModernTableLayerCellRenderer extends ModernTableCheckboxCellRenderer {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant VISIBLE_ICON.
   */
  private final static ModernIcon VISIBLE_ICON = AssetService.getInstance().loadIcon("layer_visible", 16);

  /**
   * The constant INVISIBLE_ICON.
   */
  private final static ModernIcon INVISIBLE_ICON = AssetService.getInstance().loadIcon("blank", 16);

  /**
   * Instantiates a new modern table layer cell renderer.
   */
  public ModernTableLayerCellRenderer() {
    setCanHighlight(false);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.table.ModernTableCheckboxCellRenderer#
   * drawForegroundAA( java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {

    int x = (this.getWidth() - 16) / 2;
    int y = (this.getHeight() - 16) / 2;

    if (selected) {
      VISIBLE_ICON.drawIcon(g2, x, y, 16);
    } else {
      INVISIBLE_ICON.drawIcon(g2, x, y, 16);
    }

    x = (this.getWidth() - AssetService.ICON_SIZE_20) / 2;
    y = (this.getHeight() - AssetService.ICON_SIZE_20) / 2;

    drawRect(g2, ThemeService.getInstance().getColors().getTheme(2), new Rectangle(x, y, 20, 20));
  }
}