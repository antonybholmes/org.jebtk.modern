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

import org.jebtk.core.settings.SettingsService;
import org.jebtk.core.text.TextUtils;
import org.jebtk.modern.ModernWidget;

// TODO: Auto-generated Javadoc
/**
 * The class ModernDataGridCellRenderer.
 */
public class ModernDataGridCellRenderer extends ModernDataCellRenderer {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The Constant MAX_CHARS. */
  private static final int MAX_CHARS = SettingsService.getInstance().getInt("ui.dataview.grid.cell.max-display-chars");

  /**
   * The member default value.
   */
  protected String mDefaultValue = TextUtils.EMPTY_STRING;

  /**
   * The member text.
   */
  protected String mText = TextUtils.EMPTY_STRING;

  /**
   * The member is number.
   */
  private boolean mIsNumber;

  /**
   * Instantiates a new modern data grid cell renderer.
   *
   * @param defaultValue the default value
   */
  public ModernDataGridCellRenderer(String defaultValue) {
    mDefaultValue = defaultValue;
  }

  /**
   * Instantiates a new modern data grid cell renderer.
   */
  public ModernDataGridCellRenderer() {
    this(TextUtils.EMPTY_STRING);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    if (mText == null) {
      return;
    }

    String text = getTruncatedText(g2, mText, mRect.getW() - DOUBLE_PADDING);

    g2.setColor(getForeground());

    int x;

    if (mIsNumber) {
      x = mRect.getW() - PADDING - g2.getFontMetrics().stringWidth(text);
    } else {
      x = PADDING;
    }

    g2.drawString(text, x, getTextYPosCenter(g2, getHeight()));
  }

  /**
   * Sets the text.
   *
   * @param text the new text
   */
  public final void setText(String text) {
    mText = text;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataCellRenderer#
   * getCellRendererComponent(org.abh.lib.ui.modern.dataview.ModernData,
   * java.lang.Object, boolean, boolean, boolean, int, int)
   */
  @Override
  public Component getCellRendererComponent(ModernData dataView, Object value, boolean highlight, boolean isSelected,
      boolean hasFocus, int row, int column) {
    if (value != null) {
      setForeground(dataView.getModel().getCellStyle(row, column).getColor());
      setBackground(dataView.getModel().getCellStyle(row, column).getBackground());

      mIsNumber = (value instanceof Number);

      // For rendering purposes, truncate long strings
      String v = TextUtils.truncate(value.toString(), MAX_CHARS);

      setText(v);

      // setText(value.toString());
    } else {
      setForeground(ModernWidget.TEXT_COLOR);

      setText(mDefaultValue);
    }

    return super.getCellRendererComponent(dataView, value, highlight, isSelected, hasFocus, row, column);
  }
}