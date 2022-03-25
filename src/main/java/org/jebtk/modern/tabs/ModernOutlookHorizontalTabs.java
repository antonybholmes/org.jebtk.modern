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
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.theme.ThemeService;
import org.jebtk.modern.view.ViewModel;

// TODO: Auto-generated Javadoc
/**
 * Provides horizontal tabs along the top or bottom of the control.
 * 
 * @author Antony Holmes
 *
 */
public class ModernOutlookHorizontalTabs extends ModernWidget implements MouseMotionListener, MouseListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant TAB_START_X.
   */
  private static final int TAB_START_X = 16;

  /**
   * The constant COLOR.
   */
  private static final Color COLOR = ThemeService.getInstance().getColors().getGray(3);

  // protected List<ModernHTab> tabButtons = new ArrayList<ModernHTab>();
  /**
   * The member tab starts.
   */
  // protected List<Integer> tabWidths = new ArrayList<Integer>();
  protected List<Integer> mTabStarts = new ArrayList<Integer>();

  /**
   * The highlight tab.
   */
  private int highlightTab = -1;

  /**
   * The member model.
   */
  private ViewModel mModel;

  /** The Constant FONT. */
  private static final Font FONT = ThemeService.loadFont("theme/text-tabs/text-font"); // ThemeService.loadFont("text-tabs.text");

  /**
   * The constant BOLD_FONT.
   */
  private static final Font BOLD_FONT = ThemeService.loadFont("theme/text-tabs/text-font-bold");

  /**
   * The member items.
   */
  private List<String> mItems = new ArrayList<String>();

  /**
   * The member selected index.
   */
  private int mSelectedIndex;

  /**
   * Instantiates a new modern outlook horizontal tabs.
   *
   * @param model the model
   */
  public ModernOutlookHorizontalTabs(ViewModel model) {
    mModel = model;

    addMouseListener(this);
    addMouseMotionListener(this);

    setLayout(null);

    UI.setSize(this, 42, 42);
  }

  /**
   * Clear.
   */
  public void clear() {

    mTabStarts.clear();

    repaint();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
   */
  @Override
  public void mousePressed(MouseEvent e) {
    changeTab(highlightTab);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseMoved(MouseEvent e) {
    int index = -1;

    for (int i = 0; i < mItems.size(); ++i) {
      if (e.getX() >= mTabStarts.get(i) && e.getX() < mTabStarts.get(i + 1)) {
        index = i;
        break;
      }
    }

    if (index == -1 || index == highlightTab) {
      return;
    }

    highlightTab = index;

    repaint();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseExited(MouseEvent e) {
    highlightTab = -1;

    repaint();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseDragged(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  /**
   * Change tab.
   *
   * @param index the index
   */
  private void changeTab(int index) {
    mModel.setView(mItems.get(index));

    mSelectedIndex = index;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawBackground(java.awt.Graphics2D)
   */
  @Override
  public final void drawBackground(Graphics2D g2) {
    fill(g2, COLOR);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    int x;

    int y = getTextYPosCenter(g2, getHeight());

    if (mTabStarts.size() == 0) {
      g2.setFont(BOLD_FONT);

      x = TAB_START_X;

      // We add one more tabstart than there are tabs so
      // that we know where the end tab finishes

      for (int i = 0; i < mItems.size(); ++i) {
        mTabStarts.add(x);

        x += g2.getFontMetrics().stringWidth(mItems.get(i)) + WIDGET_HEIGHT;
      }

      mTabStarts.add(x);
    }

    for (int i = 0; i < mItems.size(); ++i) {
      x = mTabStarts.get(i);

      if (i == mSelectedIndex) {
        g2.setFont(BOLD_FONT);
      } else {
        g2.setFont(FONT);
      }

      if (i == mSelectedIndex) {
        g2.setColor(ThemeService.getInstance().getColors().getTheme(5));
      } else if (i == highlightTab) {
        g2.setColor(ThemeService.getInstance().getColors().getTheme(4));
      } else {
        g2.setColor(ThemeService.getInstance().getColors().getGray(9));
      }

      g2.drawString(mItems.get(i), x, y);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseClicked(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseReleased(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  /**
   * Adds the tab.
   *
   * @param item the item
   */
  public void addTab(String item) {
    mItems.add(item);

    mTabStarts.clear();
  }

}
