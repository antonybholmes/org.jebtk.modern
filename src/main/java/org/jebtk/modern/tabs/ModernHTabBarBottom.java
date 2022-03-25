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
import java.awt.event.MouseEvent;

import org.jebtk.modern.AssetService;
import org.jebtk.modern.theme.ModernTheme;
import org.jebtk.modern.theme.ThemeService;

// TODO: Auto-generated Javadoc
/**
 * Horizontal tab control for top.
 * 
 * @author Antony Holmes
 *
 */
public class ModernHTabBarBottom extends ModernHTabBar {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The activate close button.
   */
  private boolean activateCloseButton = false;

  /**
   * Instantiates a new modern h tab bar bottom.
   *
   * @param model the model
   */
  public ModernHTabBarBottom(TabsModel model) {
    super(model);

    // setBorder(BORDER);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    int tabX = TAB_START_X;
    int x;
    int y;

    int h = TAB_HEIGHT;

    // bottom line
    g2.setColor(ThemeService.getInstance().getColors().getGray(4));

    x = getWidth();
    y = 0;

    g2.drawLine(0, y, x, y);

    int textX;
    int textY = getTextYPosCenter(g2, h);

    Color highlight = ThemeService.getInstance().getColors().getTheme(5);

    for (int i = tabOffsetIndex; i < getTabsModel().getTabCount(); ++i) {
      g2.setColor(ThemeService.getInstance().getColors().getGray(4));
      g2.drawLine(tabX, PADDING, tabX, h - PADDING);

      g2.setFont(i == getTabsModel().getSelectedIndex() ? BOLD_FONT : FONT);

      if (i == getTabsModel().getSelectedIndex()) {

        g2.setColor(Color.WHITE);

        g2.fillRect(tabX, y, TAB_WIDTH, h);

        // draw the selected tab

        g2.setColor(ThemeService.getInstance().getColors().getGray(4)); // new
        // Color(159,
        // 182,
        // 205));

        // top line
        /*
         * g2.drawLine(tabX, y, tabX + tabWidth, y);
         */

        // left line
        g2.drawLine(tabX, y, tabX, h);

        // right line
        g2.drawLine(tabX + TAB_WIDTH, y, tabX + TAB_WIDTH, h);

        // selection line
        g2.setColor(highlight);

        g2.fillRect(tabX, h - 1, TAB_WIDTH + 1, 2);

        g2.setColor(Color.WHITE);

        g2.drawLine(tabX + 1, y, tabX + TAB_WIDTH - 1, y);
      }

      Graphics2D g2Temp = (Graphics2D) g2.create();

      g2Temp.setColor(i == tab && i != getTabsModel().getSelectedIndex() ? highlight : getForeground());

      String s = getTabsModel().getTab(i).getName();

      x = tabX + AssetService.ICON_SIZE_8;

      textX = tabX + (TAB_WIDTH - g2.getFontMetrics().stringWidth(s)) / 2;

      g2Temp.clipRect(x, y, TAB_WIDTH - 16, h);

      g2Temp.drawString(s, textX, textY);

      g2Temp.dispose();

      tabX += TAB_WIDTH;
    }

    if (getTabsModel().getTabCount() > 0) {
      g2.setColor(ThemeService.getInstance().getColors().getGray(4));
      g2.drawLine(tabX, PADDING, tabX, h - PADDING);
    }

    if (tab != -1 && getTabsModel().getTab(tab).isClosable()) {
      // draw closing x

      y = (TAB_HEIGHT - 16) / 2;
      x = TAB_START_X + TAB_WIDTH * (tab + 1) - TAB_HEIGHT + y;

      if (activateCloseButton) {
        g2.setColor(ThemeService.getInstance().getColors().getTheme(2));

        g2.fillRect(x, y, 16, 16);
      }

      y = (TAB_HEIGHT - CROSS_WIDTH) / 2;
      x = TAB_START_X + TAB_WIDTH * (tab + 1) - TAB_HEIGHT + y;

      g2.setColor(Color.BLACK);
      g2.setStroke(ModernTheme.DOUBLE_LINE_STROKE);

      g2.drawLine(x, y, x + CROSS_WIDTH, y + CROSS_WIDTH);
      g2.drawLine(x, y + CROSS_WIDTH, x + CROSS_WIDTH, y);

    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.tabs.ModernHTabBar#mouseMoved(java.awt.event.
   * MouseEvent)
   */
  @Override
  public void mouseMoved(MouseEvent e) {
    super.mouseMoved(e);

    activateCloseButton = TAB_START_X + TAB_WIDTH * (tab + 1) - e.getX() < TAB_HEIGHT;

    repaint();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.tabs.ModernHTabBar#mousePressed(java.awt.event.
   * MouseEvent)
   */
  @Override
  public void mousePressed(MouseEvent e) {
    super.mousePressed(e);

    if (tab != -1 && getTabsModel().getTab(tab).isClosable() && activateCloseButton) {

      // System.err.println("remove tab " + tab);

      getTabsModel().removeTab(getTabsModel().getTab(tab).getName());

      activateCloseButton = false;
      tab = -1;

      repaint();
    }
  }
}
