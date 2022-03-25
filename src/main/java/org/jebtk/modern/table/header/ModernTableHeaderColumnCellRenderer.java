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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import org.jebtk.modern.AssetService;
import org.jebtk.modern.CellSelectionType;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.dataview.ModernData;
import org.jebtk.modern.font.FontUtils;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.zoom.ZoomCanvas;

// TODO: Auto-generated Javadoc
/**
 * The class ModernTableHeaderColumnCellRenderer.
 */
public class ModernTableHeaderColumnCellRenderer extends ModernTableHeaderCellRenderer {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member default value.
   */
  protected String mDefaultValue = "";

  /**
   * The mode.
   */
  protected CellSelectionType mMode = CellSelectionType.NONE;

  /**
   * The end.
   */
  protected int end = 0;

  /**
   * The label.
   */
  protected String label = "";

  /**
   * The member is sort column.
   */
  private boolean mIsSortColumn;

  /**
   * The member isort ascending.
   */
  private boolean mIsortAscending;

  /**
   * The member is sortable.
   */
  private boolean mIsSortable;

  /**
   * The constant UP_ICON.
   */
  private static final ModernIcon UP_ICON = AssetService.getInstance().loadIcon("up_scroll", AssetService.ICON_SIZE_16);

  /**
   * The constant DOWN_ICON.
   */
  private static final ModernIcon DOWN_ICON = AssetService.getInstance().loadIcon("down_scroll",
      AssetService.ICON_SIZE_16);

  /** The m img line cache. */
  private Map<Integer, BufferedImage> mImgLineCache = new HashMap<Integer, BufferedImage>();

  /** The m img background cache. */
  private Map<Integer, BufferedImage> mImgBackgroundCache = new HashMap<Integer, BufferedImage>();

  /** The m img sel background cache. */
  private Map<Integer, BufferedImage> mImgSelBackgroundCache = new HashMap<Integer, BufferedImage>();

  private double mZoom;

  /**
   * Instantiates a new modern table header column cell renderer.
   */
  public ModernTableHeaderColumnCellRenderer() {
    // TODO Auto-generated constructor stub
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.dataview.ModernDataCellRenderer#drawBackground(java.
   * awt .Graphics2D)
   */
  @Override
  public void drawBackground(Graphics2D g2) {
    // GradientPaint gradient;

    // g2.setColor(LINE_COLOR);
    // g2.drawLine(0, mRect.getH() - 1, mRect.getW() - 1, mRect.getH() - 1);

    int h = mRect.getH();
    int w = mRect.getW();

    switch (mMode) {
    case HAS_FOCUS:
    case SELECTED:
      g2.setColor(SEL_BACK_COLOR);
      g2.fillRect(mRect.getX(), mRect.getY(), w, h);

      // g2.drawImage(cacheSelBackground(mRect.getW()), 0, 0, null);
      break;
    default:
      fill(g2, ModernTableHeader.HEADER_BACKGROUND, mRect);
      // g2.drawImage(cacheBackground(mRect.getW()), 0, 0, null);
      break;
    }

    g2.setColor(ModernWidget.LINE_COLOR);

    int p = w - 1;
    g2.drawLine(p, 0, p, h - 1);

    if (mMode == CellSelectionType.SELECTED) {
      // g2.drawImage(cacheSelectionLine(mRect.getW()), 0, mRect.getH() - 3,
      // null);

      g2.setColor(SEL_LINE_COLOR);
      g2.fillRect(0, h - 2, w, 2);
    } else {
      p = h - 1;
      g2.drawLine(0, p, w - 1, p);

    }
  }

  /**
   * Cache background.
   *
   * @param w the w
   * @return the buffered image
   */
  private BufferedImage cacheBackground(int w) {
    if (!mImgBackgroundCache.containsKey(w)) {
      int h = mRect.getH();

      BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
      Graphics g = bi.getGraphics();
      g.setColor(ModernTableHeader.HEADER_BACKGROUND);
      g.fillRect(0, 0, w, h);

      // g.setColor(ModernWidget.LINE_COLOR);
      // int p = w - 1;
      // g.drawLine(p, 0, p, h);
      // p = h - 1;
      // g.drawLine(0, p, w, p);

      mImgBackgroundCache.put(w, bi);
    }

    return mImgBackgroundCache.get(w);
  }

  /**
   * Cache sel background.
   *
   * @param w the w
   * @return the buffered image
   */
  private BufferedImage cacheSelBackground(int w) {
    if (!mImgSelBackgroundCache.containsKey(w)) {
      int h = mRect.getH();

      BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
      Graphics g = bi.getGraphics();
      g.setColor(SEL_BACK_COLOR);
      g.fillRect(0, 0, w, h);

      // g.setColor(ModernWidget.LINE_COLOR);
      // int p = w - 1;
      // g.drawLine(p, 0, p, h);
      // p = h - 1;
      // g.drawLine(0, p, w, p);

      mImgSelBackgroundCache.put(w, bi);
    }

    return mImgSelBackgroundCache.get(w);
  }

  /**
   * Cache selection line.
   *
   * @param w the w
   * @return the buffered image
   */
  private BufferedImage cacheSelectionLine(int w) {
    if (!mImgLineCache.containsKey(w)) {
      BufferedImage bi = new BufferedImage(w, 3, BufferedImage.TYPE_INT_RGB);
      Graphics g = bi.getGraphics();
      g.setColor(SEL_LINE_COLOR);
      g.fillRect(0, 0, w, 3);
      mImgLineCache.put(w, bi);
    }

    return mImgLineCache.get(w);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    int x;
    int y;

    g2.setColor(TEXT_COLOR);
    // g2.setFont(ModernWidget.FONT);

    /*
     * int h = mRect.getH() / Math.max(1, mNames.size()); y = getTextYPosCenter(g2,
     * h);
     * 
     * for (String name : mNames) { String text = getTruncatedText(g2, name, 0, w);
     * 
     * x = (mRect.getW() - g2.getFontMetrics().stringWidth(text)) / 2;
     * 
     * g2.drawString(text, x, y);
     * 
     * y += h; }
     */

    y = getTextYPosCenter(g2, mRect.getH());

    String text = getTruncatedText(g2, mText, 0, mRect.getW());

    x = (mRect.getW() - g2.getFontMetrics().stringWidth(text)) / 2;

    g2.drawString(text, x, y);

    if (mIsSortable && mIsSortColumn) {
      x = mRect.getW() - 16;
      y = (mRect.getH() - 16) / 2;

      if (mIsortAscending) {
        DOWN_ICON.drawIcon(g2, x, y, 16);
      } else {
        UP_ICON.drawIcon(g2, x, y, 16);
      }
    }
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

    if (isSelected) {
      mMode = CellSelectionType.SELECTED;
    } else if (hasFocus) {
      mMode = CellSelectionType.HAS_FOCUS;
    } else {
      mMode = CellSelectionType.NONE;
    }

    mZoom = ((ZoomCanvas) dataView).getZoomModel().getZoom();

    setFont(FontUtils.scale(FONT, mZoom));

    mIsSortable = dataView.getRowSortModel().getSorter().getEnabled();
    mIsSortColumn = (column == dataView.getSortCol());
    mIsortAscending = dataView.getRowSortModel().getSorter().getSortAscending();

    return this;
  }
}