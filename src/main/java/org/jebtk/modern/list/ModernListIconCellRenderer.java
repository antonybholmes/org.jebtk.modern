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
package org.jebtk.modern.list;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import org.jebtk.core.text.TextUtils;
import org.jebtk.modern.AssetService;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.text.ITextProperty;

// TODO: Auto-generated Javadoc
/**
 * Provides a flat look table renderer for the flat table although it can be
 * used with standard JTables as well.
 *
 * @author Antony Holmes
 * @param <T> the generic type
 */
public class ModernListIconCellRenderer extends ModernListCellRenderer implements ITextProperty {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member icon.
   */
  protected ModernIcon mIcon = null;

  /**
   * The member text.
   */
  protected String mText = TextUtils.EMPTY_STRING;

  /**
   * Instantiates a new modern list icon cell renderer.
   */
  public ModernListIconCellRenderer() {
    // Do nothing
  }

  /**
   * Instantiates a new modern list icon cell renderer.
   *
   * @param icon the icon
   */
  public ModernListIconCellRenderer(ModernIcon icon) {
    setIcon(icon);
  }

  /**
   * Sets the icon.
   *
   * @param icon the new icon
   */
  public void setIcon(ModernIcon icon) {
    mIcon = icon;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    int iconX = PADDING;

    int x;

    if (mIcon != null) {
      x = iconX + AssetService.ICON_SIZE_16 + PADDING;
    } else {
      x = PADDING;
    }

    int y = AssetService.ICON_SIZE_16;

    g2.setFont(ModernWidget.FONT);
    g2.setColor(TEXT_COLOR);
    g2.drawString(mText, x, y);

    if (mIcon != null) {
      if (mText == null || mText.length() == 0) {
        iconX = (getWidth() - AssetService.ICON_SIZE_16) / 2;
      }

      int iconY = (getHeight() - AssetService.ICON_SIZE_16) / 2;

      mIcon.drawIcon(g2, new Rectangle(iconX, iconY, 16, 16));
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.list.ModernListCellRenderer#getCellRendererComponent(
   * org.abh.lib.ui.modern.list.ModernList, java.lang.Object, boolean, boolean,
   * boolean, int)
   */
  @Override
  public Component getCellRendererComponent(ModernList<?> list, Object value, boolean highlight, boolean isSelected,
      boolean hasFocus, int row) {
    setText(value.toString());

    return super.getCellRendererComponent(list, value, highlight, isSelected, hasFocus, row);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.text.TextProperty#setText(java.lang.String)
   */
  @Override
  public void setText(String text) {
    mText = text;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.text.TextProperty#getText()
   */
  @Override
  public String getText() {
    return mText;
  }
}