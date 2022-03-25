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

import java.awt.Graphics2D;

// TODO: Auto-generated Javadoc
/**
 * Simple horizontal tabs using labels as buttons.
 * 
 * 
 * @author Antony Holmes
 *
 */
public class TextTabsCentered extends TextTabs {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member width.
   */
  private int mWidth;

  /**
   * Instantiates a new text tabs centered.
   *
   * @param model the model
   */
  public TextTabsCentered(TabsModel model) {
    super(model);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.tabs.TextTabs#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    if (mTabWidths.size() == 0) {
      g2.setFont(BOLD_FONT);

      // Account for the gaps between tabs
      mWidth = GAP_WIDTH * (getTabsModel().getTabCount() - 1);

      for (int i = 0; i < getTabsModel().getTabCount(); ++i) {
        String s = getTabsModel().getTab(i).getName(); // .toUpperCase();

        int w = g2.getFontMetrics().stringWidth(s);

        mTabWidths.add(w);

        mWidth += w;
      }
    }

    g2.setFont(FONT);

    int x = (getWidth() - mWidth) / 2;

    int textY = getInsets().top + getTextYPosCenter(g2, mInternalRect.getH());

    for (int i = 0; i < getTabsModel().getTabCount(); ++i) {
      boolean selected = i == getTabsModel().getSelectedIndex();
      boolean highlight = i == mHighlight;

      g2.setColor(selected || highlight ? THEME_SELECTED_BORDER_COLOR : TEXT_COLOR);

      g2.setFont(selected ? BOLD_FONT : FONT);

      String s = getTabsModel().getTab(i).getName(); // .toUpperCase();

      g2.drawString(s, x + (mTabWidths.get(i) - g2.getFontMetrics().stringWidth(s)) / 2, textY);

      x += mTabWidths.get(i) + GAP_WIDTH;
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.tabs.TextTabs#changeTab(int)
   */
  @Override
  protected void changeTab(int x, int y) {
    int tab = -1;

    int x1 = (getWidth() - mWidth) / 2;
    int x2;

    for (int i = 0; i < getTabsModel().getTabCount(); ++i) {
      x2 = x1 + mTabWidths.get(i);

      if (x >= x1 && x <= x2) {
        tab = i;
        break;
      }

      x1 = x2 + GAP_WIDTH;
    }

    if (tab == -1) {
      return;
    }

    getTabsModel().changeTab(tab);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.tabs.TextTabs#highlight(int)
   */
  @Override
  protected void highlightTab(int x, int y) {
    mHighlight = -1;

    int x1 = (getWidth() - mWidth) / 2;
    int x2;

    for (int i = 0; i < getTabsModel().getTabCount(); ++i) {
      x2 = x1 + mTabWidths.get(i);

      if (x >= x1 && x <= x2) {
        mHighlight = i;

        break;
      }

      x1 = x2 + GAP_WIDTH;
    }

    repaint();
  }
}
