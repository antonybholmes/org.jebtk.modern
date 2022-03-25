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
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import org.jebtk.modern.UI;
import org.jebtk.modern.theme.ThemeService;

// TODO: Auto-generated Javadoc
/**
 * Provides horizontal tabs along the top or bottom of the control.
 * 
 * @author Antony Holmes
 *
 */
public class ModernHTabBar extends TabsController implements MouseMotionListener, MouseListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The tab start x.
   */
  protected static final int TAB_START_X = 8;

  /**
   * The constant TAB_HEIGHT.
   */
  protected static final int TAB_HEIGHT = 30;

  /**
   * The constant CROSS_WIDTH.
   */
  protected static final int CROSS_WIDTH = 6;

  /** The Constant SIZE. */
  private static final Dimension SIZE = new Dimension(Short.MAX_VALUE, TAB_HEIGHT);

  // protected List<ModernHTab> tabButtons = new ArrayList<ModernHTab>();
  // protected List<Integer> tabWidths = new ArrayList<Integer>();
  // protected List<Integer> tabStarts = new ArrayList<Integer>();

  // protected Map<ModernHTab, Integer> tabIndexMap = new HashMap<ModernHTab,
  // Integer>();
  // private Map<Integer, ModernHTab> indexTabMap = new HashMap<Integer,
  // ModernHTab>();

  /** The Constant TAB_HIGHLIGHT_COLOR. */
  protected static final Color TAB_HIGHLIGHT_COLOR = ThemeService.getInstance().getColors().getTheme(4);

  /**
   * The tab width.
   */
  protected static int TAB_WIDTH = 80;

  /**
   * Determines which tabs to display.
   */
  protected int tabOffsetIndex = 0;

  /**
   * The tab.
   */
  protected int tab = -1;

  /**
   * Instantiates a new modern h tab bar.
   *
   * @param model the model
   */
  public ModernHTabBar(TabsModel model) {
    super(model);

    UI.setSize(this, SIZE);

    addMouseMotionListener(this);
    addMouseListener(this);
  }

  /**
   * Clear.
   */
  public void clear() {

    // getTabsModel().removeAllTabs();
    // tabIndexMap.clear();
    // indexTabMap.clear();

    revalidate();
    repaint();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.tabs.TabsController#tabChanged(org.abh.lib.ui.modern.
   * tabs.TabEvent)
   */
  @Override
  public final void tabChanged(TabEvent e) {
    repaint();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
   */
  @Override
  public void mousePressed(MouseEvent e) {
    changeTab(tab);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseMoved(MouseEvent e) {
    int i = (e.getX() - TAB_START_X) / TAB_WIDTH - tabOffsetIndex;

    if (i < 0 || i > getTabsModel().getTabCount() - 1) {
      i = -1;
    }

    if (i == -1 || i == tab) {
      return;
    }

    tab = i;

    repaint();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseExited(MouseEvent e) {
    tab = -1;

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
    getTabsModel().changeTab(index);
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
}
