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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.event.HighlightEvent;
import org.jebtk.modern.event.HighlightEventProducer;
import org.jebtk.modern.event.HighlightListener;
import org.jebtk.modern.event.HighlightListeners;

// TODO: Auto-generated Javadoc
/**
 * Simple horizontal tabs using labels as buttons.
 * 
 * 
 * @author Antony Holmes
 *
 */
public class TabsPanel extends TabsController implements HighlightEventProducer {

  private static final long serialVersionUID = 1L;

  /**
   * The member tab widths.
   */
  protected List<Integer> mTabWidths = new ArrayList<Integer>();

  /**
   * The member highlight.
   */
  public int mHighlight = -1;

  protected HighlightListeners mHighlightListeners = new HighlightListeners();

  /**
   * The class MouseEvents.
   */
  private class MouseEvents extends MouseAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(MouseEvent e) {
      changeTab(e.getX(), e.getY());
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseMoved(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseMoved(MouseEvent e) {
      highlightTab(e.getX(), e.getY());
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseExited(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseExited(MouseEvent e) {
      mHighlight = -1;

      repaint();
    }
  }

  /**
   * Instantiates a new text tabs.
   *
   * @param model  the model
   * @param center the center
   */
  public TabsPanel(TabsModel model) {
    super(model);

    MouseEvents me = new MouseEvents();

    addMouseListener(me);
    addMouseMotionListener(me);

    UI.setSize(this, ModernWidget.MAX_SIZE);
  }

  /**
   * Change tab.
   *
   * @param x the x
   * @param y the y
   */
  protected void changeTab(int x, int y) {
    int tab = -1;

    int x1 = getInsets().left;
    int x2;

    for (int i = 0; i < mTabWidths.size(); ++i) {
      x2 = x1 + mTabWidths.get(i);

      if (x >= x1 && x <= x2) {
        tab = i;
        break;
      }

      x1 = x2;
    }

    if (tab != -1) {
      repaint();

      getTabsModel().changeTab(tab);
    }
  }

  /**
   * Highlight.
   *
   * @param x the x
   * @param y the y
   */
  protected void highlightTab(int x, int y) {
    int t = -1;

    int x1 = getInsets().left;
    int x2;

    for (int i = 0; i < mTabWidths.size(); ++i) {
      x2 = x1 + mTabWidths.get(i);

      if (x >= x1 && x <= x2) {
        t = i;

        break;
      }

      x1 = x2;
    }

    if (t != -1 && t != mHighlight) {
      mHighlight = t;

      fireHighlighted();
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

  public int getHighlightIndex() {
    return mHighlight;
  }
}
