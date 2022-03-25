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

import org.jebtk.modern.ModernWidget;

// TODO: Auto-generated Javadoc
/**
 * Horizontal tab control for top.
 * 
 * @author Antony Holmes
 *
 */
public class ModernHTabBarTop extends ModernHTabBar {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new modern h tab bar top.
   *
   * @param model the model
   */
  public ModernHTabBarTop(TabsModel model) {
    super(model);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    int tabX = TAB_START_X;
    // int x;
    int y;

    int h = getHeight() - 1;

    // int textY = getHeight() - DOUBLE_PADDING;

    // bottom line
    g2.setColor(LINE_COLOR);

    y = getHeight() - 1;

    g2.drawLine(0, y, getWidth() - 1, y);

    int textY = ModernWidget.getTextYPosCenter(g2, TAB_HEIGHT);

    for (int i = tabOffsetIndex; i < getTabsModel().getTabCount(); ++i) {
      if (i == getTabsModel().getSelectedIndex()) {
        y = getHeight() - TAB_HEIGHT;

        g2.setColor(Color.WHITE);

        g2.fillRect(tabX, y, TAB_WIDTH, h + 1);

        g2.setColor(TAB_HIGHLIGHT_COLOR);

        g2.fillRect(tabX, y, TAB_WIDTH, 2);

        // Grey lines

        g2.setColor(LINE_COLOR);

        // left line
        g2.drawLine(tabX, y, tabX, h);

        // right line
        g2.drawLine(tabX + TAB_WIDTH, y, tabX + TAB_WIDTH, h);

        // g2.drawLine(tabX + 1,
        // y,
        // tabX + TAB_WIDTH - 1,
        // y);

      }

      g2.setColor(TEXT_COLOR);
      g2.drawString(getTabsModel().getTab(i).getName(),
          tabX + (TAB_WIDTH - ModernWidget.getStringWidth(g2, getTabsModel().getTab(i).getName())) / 2, textY);

      tabX += TAB_WIDTH;
    }
  }
}
