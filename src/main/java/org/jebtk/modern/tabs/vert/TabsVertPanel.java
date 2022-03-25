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
package org.jebtk.modern.tabs.vert;

import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.event.HighlightEventProducer;
import org.jebtk.modern.tabs.TabsModel;
import org.jebtk.modern.tabs.TabsPanel;

// TODO: Auto-generated Javadoc
/**
 * Simple horizontal tabs using labels as buttons.
 * 
 * 
 * @author Antony Holmes
 *
 */
public class TabsVertPanel extends TabsPanel implements HighlightEventProducer {

  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new text tabs.
   *
   * @param model  the model
   * @param center the center
   */
  public TabsVertPanel(TabsModel model) {
    super(model);

    UI.setSize(this, ModernWidget.MAX_SIZE);
  }

  @Override
  protected void changeTab(int x, int y) {
    int tab = -1;

    int y2 = getHeight() - getInsets().bottom;
    int y1;

    for (int i = mTabWidths.size() - 1; i >= 0; --i) {
      y1 = y2 - mTabWidths.get(i);

      if (y >= y1 && y <= y2) {
        tab = i;

        break;
      }

      y2 = y1;
    }

    if (tab != -1) {
      repaint();

      getTabsModel().changeTab(tab);
    }
  }

  @Override
  protected void highlightTab(int x, int y) {
    int t = -1;

    int y2 = getHeight() - getInsets().bottom;
    int y1;

    for (int i = mTabWidths.size() - 1; i >= 0; --i) {
      y1 = y2 - mTabWidths.get(i);

      if (y >= y1 && y <= y2) {
        t = i;

        break;
      }

      y2 = y1;
    }

    if (t != -1 && t != mHighlight) {
      mHighlight = t;

      fireHighlighted();
    }
  }
}
