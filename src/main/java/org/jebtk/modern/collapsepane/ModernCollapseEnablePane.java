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
package org.jebtk.modern.collapsepane;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JComponent;

import org.jebtk.modern.AssetService;

/**
 * Allows collapsable panes to be created. A tick box allows each pane to be
 * enabled or disabled.
 * 
 * 
 * @author Antony Holmes
 *
 */
public class ModernCollapseEnablePane extends ModernCollapsePane implements MouseListener, MouseMotionListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant TAB_ENABLED.
   */
  public static final String TAB_ENABLED = "tab_enabled";

  /**
   * The constant CLICK_REGION_X.
   */
  private static final int CLICK_REGION_X = PADDING + AssetService.ICON_SIZE_16;

  /**
   * The enabled.
   */
  private final List<Boolean> mEnabledTabs = new CopyOnWriteArrayList<Boolean>();

  /**
   * The highlight.
   */
  private int highlight;

  /**
   * Instantiates a new modern collapse enable pane.
   */
  public ModernCollapseEnablePane() {
    addMouseListener(this);
    addMouseMotionListener(this);

    setNodeRenderer(new ModernCollapseEnableNodeRenderer());
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.collapsepane.AbstractCollapsePane#addTab(java.lang.
   * String, javax.swing.JComponent, boolean)
   */
  public void addTab(String name, JComponent c1, boolean hidden) {
    addTab(name, c1, true, hidden);
  }

  /**
   * Adds the tab.
   *
   * @param name       the name
   * @param c1         the c1
   * @param tabEnabled the tab enabled
   * @param hidden     the hidden
   */
  public void addTab(String name, JComponent c1, boolean tabEnabled, boolean hidden) {
    super.addTab(name, c1, hidden);

    mEnabledTabs.add(tabEnabled);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    Rectangle r = new Rectangle(getInsets().left, getInsets().top, getWidth() - getInsets().left - getInsets().right,
        WIDGET_HEIGHT);

    Graphics2D g2Temp = (Graphics2D) g2.create();

    g2Temp.translate(mInternalRect.getX(), r.y);

    for (int i = 0; i < mTabNames.size(); ++i) {
      ((ModernCollapseEnableNodeRenderer) mNodeRenderer).getRenderer(this, mTabNames.get(i), i, highlight == i, false,
          false, mExpanded.get(i), mEnabledTabs.get(i));

      mNodeRenderer.setSize(mInternalRect.getW(), mHeaderHeight);

      mNodeRenderer.print(g2Temp);

      g2Temp.translate(0, mHeaderHeight + (mExpanded.get(i) ? mComponents.get(i).getPreferredSize().height : 0));
    }

    g2Temp.dispose();
  }

  /**
   * Sets the enabled.
   *
   * @param i the new enabled
   */
  private void setEnabled(int i) {
    mEnabledTabs.set(i, !mEnabledTabs.get(i));

    repaint();

    // fireClicked(new ModernClickEvent(this, TAB_ENABLED));
  }

  /**
   * Gets the enabled.
   *
   * @param i the i
   * @return the enabled
   */
  public boolean getEnabled(int i) {
    return mEnabledTabs.get(i);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.collapsepane.AbstractCollapsePane#clear()
   */
  @Override
  public void clear() {
    mEnabledTabs.clear();

    super.clear();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
   */
  @Override
  public void mousePressed(MouseEvent e) {
    if (e.isPopupTrigger()) {
      return;
    }

    int y = getInsets().top;
    int i = 0;

    while (y <= e.getY()) {
      if (y + mHeaderHeight > e.getY()) {
        if (e.getX() < CLICK_REGION_X) {
          invertExpanded(i);
        } else {
          setEnabled(i);
        }

        break;
      }

      y += mHeaderHeight + (mExpanded.get(i) ? mComponents.get(i).getPreferredSize().height : 0);

      ++i;
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
   * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseExited(MouseEvent e) {
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

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseMoved(MouseEvent e) {
    if (!e.getSource().equals(this)) {
      highlight = -1;

      repaint();

      return;
    }

    int y = getInsets().top;

    highlight = 0;

    while (y <= e.getY()) {
      if (y + mHeaderHeight > e.getY()) {
        break;
      }

      if (highlight == mComponents.size() - 1) {
        break;
      }

      y += mHeaderHeight + (mExpanded.get(highlight) ? mComponents.get(highlight).getPreferredSize().height : 0);

      ++highlight;
    }

    repaint();
  }
}
