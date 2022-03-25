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
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.Arc2D;
import java.awt.geom.GeneralPath;

import org.jebtk.core.ColorUtils;
import org.jebtk.modern.ribbon.RibbonMenuItem;
import org.jebtk.modern.theme.MaterialService;

// TODO: Auto-generated Javadoc
/**
 * Mac Style tabs.
 *
 * @author Antony Holmes
 */
public class BlockVertTabs extends TextTabs implements ComponentListener {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The Constant TAB_SIZE. */
  static final int TAB_SIZE = 42;

  /** The Constant TEXT_TAB_HIGHLIGHT_COLOR. */
  static final Color TEXT_TAB_HIGHLIGHT_COLOR = ColorUtils.getTransparentColor90(Color.BLACK);

  /** The Constant TEXT_TAB_SELECTED_COLOR. */
  static final Color TEXT_TAB_SELECTED_COLOR = ColorUtils.getTransparentColor50(Color.BLACK); // Color.WHITE;

  // private static final Color BG_COLOR =
  // ThemeService.getInstance().getColors().getHighlight32(0);
  // //ColorUtils.getTransparentColor10(Color.WHITE);

  /** The m tab size. */
  int mTabSize;

  /** The m P. */
  GeneralPath mP;

  /** The Constant OFFSET. */
  protected int mOffset = 16;

  // private static final int ICON_SIZE = 24;

  /**
   * Instantiates a new block vert tabs.
   *
   * @param model the model
   */
  public BlockVertTabs(TabsModel model) {
    this(model, TAB_SIZE);
  }

  /**
   * Instantiates a new block vert tabs.
   *
   * @param model   the model
   * @param tabSize the tab size
   */
  public BlockVertTabs(TabsModel model, int tabSize) {
    super(model, false);

    mTabSize = tabSize;
    mOffset = tabSize / 2;

    setFont(MaterialService.getInstance().getFonts().text());

    addComponentListener(this);

    setAnimations("block-vert-tabs");
  }

  /*
   * @Override public void drawBackgroundAA(Graphics2D g2) { int x =
   * getInsets().left; int y = getInsets().top + OFFSET;
   * 
   * int n = getTabsModel().getTabCount();
   * 
   * int y1;
   * 
   * //int w = mInternalRect.getW();
   * 
   * int selectedIndex = getTabsModel().getSelectedIndex();
   * 
   * // // Draw if highlighted //
   * 
   * if (mHighlight > -1 && mHighlight < n && mHighlight != selectedIndex) {
   * //g2.setColor(TEXT_TAB_HIGHLIGHT_COLOR);
   * //getRenderer().getHighlightFillColor()); //TEXT_TAB_HIGHLIGHT_COLOR);
   * //g2.fillRect(x, y + mHighlight * mTabSize, w, mTabSize);
   * 
   * Graphics2D g2Temp = ImageUtils.clone(g2);
   * 
   * try { g2Temp.setColor(TEXT_TAB_HIGHLIGHT_COLOR);
   * 
   * g2Temp.translate(x, y + mHighlight * mTabSize); g2Temp.fill(mP); } finally {
   * g2Temp.dispose(); } }
   * 
   * 
   * // // Draw the selected tab //
   * 
   * y1 = y + selectedIndex * mTabSize;
   * 
   * //g2.setColor(TEXT_TAB_SELECTED_COLOR); //g2.fillRect(x, y1, w, mTabSize);
   * 
   * Graphics2D g2Temp = ImageUtils.clone(g2);
   * 
   * try { g2Temp.setColor(TEXT_TAB_SELECTED_COLOR);
   * 
   * g2Temp.translate(x, y1); g2Temp.fill(mP); } finally { g2Temp.dispose(); } }
   */

  @Override
  public void drawBackgroundAA(Graphics2D g2) {
    super.drawAnimatedBackground(g2);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {

    int y = getInsets().top + mOffset;

    int n = getTabsModel().getTabCount();
    // int selectedIndex = getTabsModel().getSelectedIndex();

    int y1 = y;

    // int textY = getTextYPosCenter(g2, mTabSize);
    // int iconY = (mTabSize - ICON_SIZE) / 2;
    // int tabX = mTabSize / 2;
    // int textX = tabX + ICON_SIZE + ICON_SIZE / 2;

    g2.setColor(TEXT_COLOR);

    int x = getWidth() / 5;

    for (int i = 0; i < n; ++i) {
      // boolean selected = i == selectedIndex;

      // g2.setFont(MaterialUtils.FONT); //selected ? BOLD_FONT : FONT);
      // g2.setColor(selected ? Color.WHITE : TEXT_COLOR);

      String s = getTabsModel().getTab(i).getName();

      // int textX;

      // if (getTabsModel().getTab(i).getIcon() != null) {
      // getTabsModel().getTab(i).getIcon().drawIcon(g2, tabX, iconY, mTabSize);
      // textX = tabX + ICON_SIZE + ICON_SIZE / 2;
      // } else {
      // textX = tabX; //DOUBLE_PADDING; //tabX; //getTextXPosCenter(g2, s, w);
      // }

      g2.drawString(s, x, y1 + getTextYPosCenter(g2, mTabSize));

      y1 += mTabSize;
      // iconY += mTabSize;
      // textY += mTabSize;
    }

    // g2.drawRoundRect(getInsets().left, getInsets().top, mInternalRect.getW()
    // - 1,
    // mInternalRect.getH(), ModernWidget.ROUNDING, ModernWidget.ROUNDING);
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

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.tabs.TextTabs#highlightTab(int, int)
   */
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
    return (y - getInsets().top - mOffset) / mTabSize;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.ComponentListener#componentHidden(java.awt.event.
   * ComponentEvent)
   */
  @Override
  public void componentHidden(ComponentEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.ComponentListener#componentMoved(java.awt.event.
   * ComponentEvent)
   */
  @Override
  public void componentMoved(ComponentEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.ComponentListener#componentResized(java.awt.event.
   * ComponentEvent)
   */
  @Override
  public void componentResized(ComponentEvent e) {
    mP = new GeneralPath();
    mP.moveTo(RibbonMenuItem.OFFSET, RibbonMenuItem.ROUNDING);
    mP.append(new Arc2D.Float(RibbonMenuItem.OFFSET, 0, RibbonMenuItem.ROUNDING, RibbonMenuItem.ROUNDING, 180, -90,
        Arc2D.OPEN), true);
    mP.lineTo(getWidth(), 0);
    mP.lineTo(getWidth(), mTabSize);
    mP.lineTo(RibbonMenuItem.OFFSET + RibbonMenuItem.ROUNDING, mTabSize);
    mP.append(new Arc2D.Float(RibbonMenuItem.OFFSET, mTabSize - RibbonMenuItem.ROUNDING, RibbonMenuItem.ROUNDING,
        RibbonMenuItem.ROUNDING, 270, -90, Arc2D.OPEN), true);
    mP.closePath();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.ComponentListener#componentShown(java.awt.event.
   * ComponentEvent)
   */
  @Override
  public void componentShown(ComponentEvent e) {
    // TODO Auto-generated method stub

  }
}
