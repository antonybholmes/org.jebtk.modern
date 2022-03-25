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
package org.jebtk.modern.ribbon;

import java.awt.Color;
import java.awt.Graphics2D;

import org.jebtk.core.geom.IntRect;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.tabs.SegmentTabs;
import org.jebtk.modern.tabs.TabsModel;
import org.jebtk.modern.theme.DrawUIService;

// TODO: Auto-generated Javadoc
/**
 * Mac Style tabs.
 *
 * @author Antony Holmes
 */
public class RibbonSegmentTabs extends SegmentTabs {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new text tabs.
   *
   * @param model   the model
   * @param tabSize the tab size
   */
  public RibbonSegmentTabs(TabsModel model, int tabSize) {
    this(model, tabSize, true);
  }

  /**
   * Instantiates a new ribbon segment tabs.
   *
   * @param model    the model
   * @param tabSize  the tab size
   * @param centered the centered
   */
  public RibbonSegmentTabs(TabsModel model, int tabSize, boolean centered) {
    super(model, tabSize, centered);

    UI.setSize(this, ModernWidget.MAX_SIZE_32);

    setFont(SUB_HEADING_FONT);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {

    int x = mLeftOffset;
    int y = getInsets().top;

    int h = mInternalRect.getH();

    int n = getTabsModel().getTabCount();

    int w = mTabSize * n;

    int selectedIndex = getTabsModel().getSelectedIndex();

    createEndShapes();

    // getWidgetRenderer().drawContentBoxFill(g2, mLeftOffset, y, w, h);
    DrawUIService.getInstance().getRenderer("content").draw(g2, new IntRect(mLeftOffset, y, w, h));
    //
    // Draw if highlighted
    //

    if (mHighlight > -1 && mHighlight < n) {
      // getWidgetRenderer()
      // .drawButtonFill(g2, x, y, w, h, RenderMode.HIGHLIGHT, hasFocus());

      DrawUIService.getInstance().getRenderer("button-fill").draw(g2, new IntRect(x, y, w, h));

      if (mHighlight == 0) {
        g2.fill(mLeftEnd);
      } else if (mHighlight == n - 1) {
        g2.fill(mRightEnd);
      } else {
        g2.fillRect(x + mHighlight * mTabSize, getInsets().top, mTabSize, h);
      }
    }

    //
    // Draw the outlines
    //

    g2.setColor(LIGHT_LINE_COLOR); // getRenderer().getOutlineColor());

    x = mLeftOffset + mTabSize;

    for (int i = 1; i < getTabsModel().getTabCount(); ++i) {
      g2.drawLine(x, y, x, y + h - 1);

      x += mTabSize;
    }

    // getWidgetRenderer().drawContentBoxOutline(g2, mLeftOffset, y, w, h);
    DrawUIService.getInstance().getRenderer("content-outline").draw(g2, new IntRect(mLeftOffset, y, w, h));

    //
    // Draw the selected tab
    //

    /*
     * GradientPaint paint = new GradientPaint(0, getInsets().top,
     * TEXT_TAB_SELECTED_COLOR_1, 0, getInsets().top + h,
     * TEXT_TAB_SELECTED_COLOR_2);
     * 
     * g2.setPaint(paint);
     */

    g2.setColor(TEXT_TAB_SELECTED_COLOR_1);

    x = mLeftOffset + selectedIndex * mTabSize;

    if (selectedIndex == 0) {
      g2.fill(mLeftEnd);
    } else if (selectedIndex == n - 1) {
      g2.fill(mRightEnd);
    } else {
      g2.fillRect(x, getInsets().top, mTabSize, h);
    }

    g2.setColor(TEXT_TAB_SELECTED_OUTLINE_COLOR);

    if (selectedIndex == 0) {
      g2.draw(mLeftEnd);
    } else if (selectedIndex == n - 1) {
      g2.draw(mRightEnd);
    } else {
      g2.drawRect(x, getInsets().top, mTabSize, h);
    }

    //
    // Draw the labels
    //

    x = mLeftOffset;

    for (int i = 0; i < n; ++i) {
      boolean selected = i == selectedIndex;

      g2.setColor(selected ? Color.WHITE : TEXT_COLOR);

      int textY = getTextYPosCenter(g2, getHeight());

      String s = getTabsModel().getTab(i).getName();

      int tabX;

      if (getTabsModel().getTab(i).getIcon() != null) {
        tabX = (mTabSize - 24 - DOUBLE_PADDING - g2.getFontMetrics().stringWidth(s)) / 2;

        getTabsModel().getTab(i).getIcon().drawIcon(g2, x + tabX, (getHeight() - 24) / 2, 32);

        tabX += 24 + DOUBLE_PADDING;
      } else {
        tabX = (mTabSize - g2.getFontMetrics().stringWidth(s)) / 2;
      }

      g2.drawString(s, x + tabX, textY);

      x += mTabSize;
    }

    // g2.drawRoundRect(getInsets().left, getInsets().top, mInternalRect.getW()
    // - 1,
    // mInternalRect.getH(), ModernWidget.ROUNDING, ModernWidget.ROUNDING);
  }
}
