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
package org.jebtk.modern.tabs;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.GeneralPath;

import javax.swing.border.Border;

import org.jebtk.modern.event.HighlightEvent;
import org.jebtk.modern.event.HighlightListener;
import org.jebtk.modern.theme.MaterialService;
import org.jebtk.modern.theme.ThemeService;

// TODO: Auto-generated Javadoc
/**
 * Mac Style tabs.
 *
 * @author Antony Holmes
 */
public class SegmentTabs extends TextTabs {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The Constant TEXT_TAB_SELECTED_COLOR_1. */
  public static final Color TEXT_TAB_SELECTED_COLOR_1 = ThemeService.getInstance().getColors().getTheme(3);

  /** The Constant TEXT_TAB_SELECTED_COLOR_2. */
  protected static final Color TEXT_TAB_SELECTED_COLOR_2 = ThemeService.getInstance().getColors().getTheme(4);

  /** The Constant TEXT_TAB_SELECTED_OUTLINE_COLOR. */
  protected static final Color TEXT_TAB_SELECTED_OUTLINE_COLOR = ThemeService.getInstance().getColors().getTheme(5);

  // protected static final Color TEXT_TAB_OUTLINE_COLOR =
  // ThemeService.getInstance().getColors().getHighlight(5);

  // protected static final Color TEXT_TAB_HIGHLIGHT_COLOR_1 =
  // ThemeService.getInstance().getColors().getColorHighlight(1);

  // protected static final Color TEXT_TAB_HIGHLIGHT_COLOR_2 =
  // ThemeService.getInstance().getColors().getColorHighlight(2);

  /** The Constant ROUNDING. */
  protected static final int ROUNDING = MaterialService.getInstance().getInts().cornerRadius();

  /** The m tab size. */
  protected final int mTabSize;

  /** The m left end. */
  protected GeneralPath mLeftEnd;

  /** The m right end. */
  protected GeneralPath mRightEnd;

  /** The m create end shapes. */
  protected boolean mCreateEndShapes = true;

  /** The m left offset. */
  protected int mLeftOffset;

  /**
   * Instantiates a new text tabs.
   *
   * @param model   the model
   * @param tabSize the tab size
   */
  public SegmentTabs(TabsModel model, int tabSize) {
    this(model, tabSize, true);
  }

  /**
   * Instantiates a new segment tabs.
   *
   * @param model    the model
   * @param tabSize  the tab size
   * @param centered the centered
   */
  public SegmentTabs(TabsModel model, int tabSize, boolean centered) {
    super(model, centered);

    mTabSize = tabSize;

    setFont(BOLD_FONT);

    addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        resize();
      }

      @Override
      public void componentShown(ComponentEvent e) {
        resize();
      }
    });

    // resize();

    setAnimations("segment-tabs");

    resize();
  }

  @Override
  public void drawBackgroundAA(Graphics2D g2) {
    createTabs(g2);

    drawAnimatedBackground(g2);
  }

  /*
   * @Override public void drawBackgroundAA(Graphics2D g2) {
   * 
   * int x = mLeftOffset; int y = getInsets().top;
   * 
   * int h = mInternalRect.getH();
   * 
   * int n = getTabsModel().getTabCount();
   * 
   * int w = mTabSize * n;
   * 
   * int selectedIndex = getTabsModel().getSelectedIndex();
   * 
   * createEndShapes();
   * 
   * // // Draw basic outline //
   * 
   * // getWidgetRenderer().drawContentBox(g2, // mLeftOffset, // y, // w, // h);
   * // // x = mLeftOffset + mTabSize; // // for (int i = 1; i <
   * getTabsModel().getTabCount(); ++i) { // g2.drawLine(x, y, x, y + h - 1); //
   * // x += mTabSize; // }
   * 
   * 
   * // // Draw if highlighted //
   * 
   * if (mHighlight > -1 && mHighlight < n) { x = mLeftOffset + mHighlight *
   * mTabSize;
   * 
   * // getWidgetRenderer().buttonFillPaint(g2, // x, // getInsets().top, //
   * mTabSize, // h, // RenderMode.SELECTED, // hasFocus()); // // if (mHighlight
   * == 0) { // g2.fill(mLeftEnd); // } else if (mHighlight == n - 1) { //
   * g2.fill(mRightEnd); // } else { // g2.fillRect(x, getInsets().top, mTabSize,
   * h); // } // // g2.fillRect(x, getInsets().top, mTabSize, h); //
   * 
   * getWidgetRenderer().buttonOutlinePaint(g2, x, getInsets().top, mTabSize, h,
   * RenderMode.HIGHLIGHT, hasFocus());
   * 
   * // if (mHighlight == 0) { // g2.draw(mLeftEnd); // } else if (mHighlight == n
   * - 1) { // g2.draw(mRightEnd); // } else { // g2.drawRect(x, getInsets().top,
   * mTabSize, h - 1); // }
   * 
   * g2.drawRect(x, getInsets().top, mTabSize, h - 1); }
   * 
   * // // Draw the outlines //
   * 
   * //getRenderer().buttonOutlinePaint(g2, selectedIndex, y, w, h);
   * 
   * 
   * 
   * // // Draw the selected tab //
   * 
   * x = mLeftOffset + selectedIndex * mTabSize;
   * 
   * getWidgetRenderer().primaryDialogButtonFillPaint(g2, x, getInsets().top,
   * mTabSize, h, RenderMode.NONE, hasFocus());
   * 
   * 
   * // if (selectedIndex == 0) { // g2.fill(mLeftEnd); // } else if
   * (selectedIndex == n - 1) { // g2.fill(mRightEnd); // } else { //
   * g2.fillRect(x, getInsets().top, mTabSize, h); // }
   * 
   * g2.fillRect(x, getInsets().top, mTabSize, h);
   * 
   * // getWidgetRenderer().buttonOutlinePaint(g2, // x, // getInsets().top, //
   * mTabSize, // h, // RenderMode.SELECTED, // hasFocus()); // // if
   * (selectedIndex == 0) { // g2.draw(mLeftEnd); // } else if (selectedIndex == n
   * - 1) { // g2.draw(mRightEnd); // } else { // g2.drawRect(x, getInsets().top,
   * mTabSize, h - 1); // }
   * 
   * 
   * // // Draw the labels //
   * 
   * x = mLeftOffset;
   * 
   * for (int i = 0; i < n; ++i) { boolean selected = i == selectedIndex; boolean
   * highlight = i == mHighlight;
   * 
   * g2.setColor(selected ? Color.WHITE : TEXT_COLOR); g2.setFont(selected ?
   * BOLD_FONT : FONT);
   * 
   * int textY = getTextYPosCenter(g2, getHeight());
   * 
   * String s = getTabsModel().getTab(i).getName(); //.toUpperCase();
   * 
   * g2.drawString(s, x + (mTabSize - g2.getFontMetrics().stringWidth(s)) / 2,
   * textY);
   * 
   * x += mTabSize; }
   * 
   * //g2.drawRoundRect(getInsets().left, getInsets().top, mInternalRect.getW() -
   * 1, mInternalRect.getH(), ModernWidget.ROUNDING, ModernWidget.ROUNDING); }
   */

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.tabs.TabsController#tabChanged(org.abh.common.ui.tabs.
   * TabEvent)
   */
  @Override
  public void tabChanged(TabEvent e) {
    resize();

    super.tabChanged(e);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.tabs.TextTabs#changeTab(int, int)
   */
  @Override
  protected void changeTab(int x, int y) {
    getTabsModel().changeTab(getTab(x, y));
  }

  @Override
  protected void highlightTab(int x, int y) {
    int t = getTab(x, y);

    if (t >= 0 && t < getTabsModel().getTabCount() && t != mHighlight) {
      mHighlight = t;

      fireHighlighted();
    }
  }

  /**
   * Gets the tab.
   *
   * @param x the x
   * @param y the y
   * @return the tab
   */
  protected int getTab(int x, int y) {
    return (x - mLeftOffset) / mTabSize;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.ModernComponent#setBorder(javax.swing.border.Border)
   */
  public void setBorder(Border border) {
    super.setBorder(border);

    resize();
  }

  /**
   * Resize.
   */
  public void resize() {
    // mTabSize = mInternalRect.getW() / Math.max(1,
    // getTabsModel().getTabCount());

    if (mCenter) {
      mLeftOffset = getInsets().left + (mInternalRect.getW() - mTabSize * getTabsModel().getTabCount()) / 2;
    } else {
      mLeftOffset = getInsets().left;
    }

    mCreateEndShapes = true;
    // createEndShapes();

    repaint();
  }

  /**
   * Creates the end shapes.
   */
  protected void createEndShapes() {
    if (mCreateEndShapes) {
      int w = mTabSize;
      int h = mInternalRect.getH() - 1;
      int x = mLeftOffset;
      int y = getInsets().top;

      mLeftEnd = new GeneralPath();
      mLeftEnd.moveTo(x, getInsets().top + ROUNDING);
      mLeftEnd.append(new Arc2D.Double(x, y, ROUNDING, ROUNDING, 180, -90, Arc2D.OPEN), true);
      mLeftEnd.lineTo(x + w, y);
      mLeftEnd.lineTo(x + w, y + h);
      mLeftEnd.lineTo(x + ROUNDING, y + h);
      mLeftEnd.append(new Arc2D.Double(x, y + h - ROUNDING, ROUNDING, ROUNDING, 270, -90, Arc2D.OPEN), true);
      // mLeftEnd.lineTo(getInsets().left, getInsets().top + ROUNDING);
      mLeftEnd.closePath();

      x += (getTabsModel().getTabCount() - 1) * mTabSize;

      mRightEnd = new GeneralPath();
      mRightEnd.moveTo(x, y);
      mRightEnd.lineTo(x + w - ROUNDING, y);
      mRightEnd.append(new Arc2D.Double(x + w - ROUNDING, y, ROUNDING, ROUNDING, 90, -90, Arc2D.OPEN), true);
      mRightEnd.lineTo(x + w, y + h - ROUNDING);
      mRightEnd.append(new Arc2D.Double(x + w - ROUNDING, y + h - ROUNDING, ROUNDING, ROUNDING, 0, -90, Arc2D.OPEN),
          true);
      mRightEnd.lineTo(x, y + h);
      mRightEnd.closePath();

      mCreateEndShapes = false;
    }
  }

  @Override
  public void addHighlightListener(HighlightListener l) {
    mHighlightListeners.addHighlightListener(l);
  }

  @Override
  public void removeHighlightListener(HighlightListener l) {
    mHighlightListeners.removeHighlightListener(l);
  }

  public void fireHighlighted() {
    fireHighlighted(new HighlightEvent(this, mHighlight));
  }

  @Override
  public void fireHighlighted(HighlightEvent e) {
    mHighlightListeners.fireHighlighted(e);
  }
}
