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

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;

import org.jebtk.modern.button.ModernClickWidget;
import org.jebtk.modern.font.FontUtils;
import org.jebtk.modern.theme.ThemeService;
import org.jebtk.modern.zoom.ZoomCanvas;

// TODO: Auto-generated Javadoc
/**
 * Provides a flat look table renderer for the flat table although it can be
 * used with standard JTables as well.
 *
 * @author Antony Holmes
 *
 */
public class ModernDataCellRenderer extends ModernClickWidget {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member can highlight.
   */
  protected boolean mCanHighlight = true;

  /**
   * The member is highlighted.
   */
  protected boolean mIsHighlighted;

  /**
   * The member is selected.
   */
  protected boolean mIsSelected;

  /**
   * The member has focus.
   */
  protected boolean mHasFocus;

  /** The m enabled. */
  protected boolean mEnabled;

  /** The Constant DISABLED_COLOR. */
  public static final Color DISABLED_COLOR = ThemeService.getInstance().getColors().getGray(2);

  /**
   * Sets the can highlight.
   *
   * @param canHighlight the new can highlight
   */
  public void setCanHighlight(boolean canHighlight) {
    mCanHighlight = canHighlight;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawBackground(java.awt.Graphics2D)
   */
  @Override
  public void drawBackground(Graphics2D g2) {
    if (!mEnabled) {
      fill(g2, DISABLED_COLOR, getRect());
    }

    if (mCanHighlight && (mIsSelected || mIsHighlighted)) {
      paintHighlighted(g2, getRect());
    }
  }

  /**
   * Returns the rendering component for the given data view.
   *
   * @param dataView   the data view
   * @param value      the value
   * @param highlight  the highlight
   * @param isSelected the is selected
   * @param hasFocus   the has focus
   * @param row        the row
   * @param column     the column
   * @return the cell renderer component
   */
  public Component getCellRendererComponent(ModernData dataView, Object value, boolean highlight, boolean isSelected,
      boolean hasFocus, int row, int column) {

    mIsHighlighted = highlight;
    mIsSelected = isSelected;
    mHasFocus = hasFocus;
    mEnabled = dataView.getIsCellEnabled(row, column);

    // Scale the font to match the zoom level.

    double mZoom = ((ZoomCanvas) dataView).getZoomModel().getZoom();

    setFont(FontUtils.scale(FONT, mZoom));

    return this;
  }
}