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

import java.awt.Component;
import java.awt.Graphics2D;

import org.jebtk.modern.CellSelectionType;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.dataview.ModernData;
import org.jebtk.modern.font.FontUtils;
import org.jebtk.modern.zoom.ZoomCanvas;

// TODO: Auto-generated Javadoc
/**
 * The class ModernTableHeaderRowCellRenderer.
 */
public class ModernTableHeaderRowCellRenderer extends ModernTableHeaderCellRenderer {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The mode.
   */
  protected CellSelectionType mMode = CellSelectionType.NONE;

  /**
   * The end.
   */
  protected int end = 0;

  private double mZoom;

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.dataview.ModernDataCellRenderer#drawBackground(java.
   * awt .Graphics2D)
   */
  @Override
  public void drawBackground(Graphics2D g2) {
    // g2.setColor(ModernWidget.LINE_COLOR);
    // g2.drawLine(mRect.getW() - 1, 0, mRect.getW() - 1, mRect.getH() - 1);

    // GradientPaint gradient;

    int h = mRect.getH();
    int w = mRect.getW();

    switch (mMode) {
    case HAS_FOCUS:
    case SELECTED:
      g2.setColor(SEL_BACK_COLOR);

      g2.fillRect(mRect.getX(), mRect.getY(), w - 1, h - 1);

      // g2.setColor(ThemeService.getInstance().getThemeColor(5));

      // g2.fillRect(mRect.getW() - 2,
      // 0,
      // 2,
      // mRect.getH());

      break;
    default:
      fill(g2, ModernTableHeader.HEADER_BACKGROUND, getRect());
      break;
    }

    /*
     * gradient = new GradientPaint(0, 0,
     * ThemeService.getInstance().getColors().getHighlight(1), mRect.getW() - 1, 0,
     * LINE_COLOR, false);
     * 
     * g2.setPaint(gradient);
     */

    g2.setColor(ModernWidget.LINE_COLOR);

    int p = h - 1;
    g2.drawLine(0, p, w - 1, p);

    if (mMode == CellSelectionType.SELECTED) {
      // g2.drawImage(cacheSelectionLine(mRect.getW()), 0, mRect.getH() - 3,
      // null);

      g2.setColor(SEL_LINE_COLOR);
      g2.fillRect(w - 2, 0, 2, h);
    } else {
      p = w - 1;
      g2.drawLine(p, 0, p, h - 1);
    }

    // g2.drawRect(0, 0, mRect.getW() - 1, mRect.getH() - 1);

    /*
     * if (isFirst) { g2.drawLine(0, 0, mRect.getW() - 1, 0); }
     */
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    g2.setColor(TEXT_COLOR);
    // g2.setFont(ModernWidget.FONT);

    /*
     * int x = 0; int w = mRect.getW() / Math.max(1, mNames.size()); int y =
     * getTextYPosCenter(g2, mRect.getH());
     * 
     * for (String name : mNames) { String text = getTruncatedText(g2, name, 0, w);
     * 
     * int p = x + (w - g2.getFontMetrics().stringWidth(text)) / 2;
     * 
     * g2.drawString(text, p, y);
     * 
     * x += w; }
     */

    int y = getTextYPosCenter(g2, mRect.getH());

    String text = getTruncatedText(g2, mText, 0, mRect.getW());

    int x = (mRect.getW() - g2.getFontMetrics().stringWidth(text)) / 2;

    g2.drawString(text, x, y);
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
      setText(value.toString());
    }

    mZoom = ((ZoomCanvas) dataView).getZoomModel().getZoom();

    setFont(FontUtils.scale(FONT, mZoom));

    if (isSelected) {
      mMode = CellSelectionType.SELECTED;
    } else if (hasFocus) {
      mMode = CellSelectionType.HAS_FOCUS;
    } else {
      mMode = CellSelectionType.NONE;
    }

    return this;
  }
}