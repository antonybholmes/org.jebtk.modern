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
package org.jebtk.modern.contentpane;

import java.awt.Cursor;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.tabs.Tab;
import org.jebtk.modern.tabs.TabEvent;
import org.jebtk.modern.tabs.TabEventListener;
import org.jebtk.modern.tabs.TabsModel;

/**
 * Displays a center pane with surround left and right panes which can be
 * added/removed on the fly according to the UI needs.
 * 
 * @author Antony Holmes
 *
 */
public class ModernHContentPane extends ModernWidget {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant DIVIDER_WIDTH.
   */
  private static final int DIVIDER_WIDTH = 1;

  /** The Constant CLOSEST_D. */
  private static final int CLOSEST_D = 3;

  /**
   * The member model.
   */
  private TabsModel mModel = new TabsModel();

  /**
   * The divider locations.
   */
  protected List<Integer> mDividerLocations = new ArrayList<Integer>();

  /**
   * The member selected divider.
   */
  private int mSelectedDivider = -1;

  /**
   * The selected divider location.
   */
  private int mSelectedDividerLocation = -1;

  /**
   * The selected width left.
   */
  private int selectedWidthLeft;

  /**
   * The selected width right.
   */
  private int selectedWidthRight;

  /**
   * The class ContentPaneEvents.
   */
  private class ContentPaneEvents implements TabEventListener {

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.lib.ui.modern.tabs.TabEventListener#tabAdded(org.abh.lib.ui.
     * modern. tabs.TabEvent)
     */
    @Override
    public void tabAdded(TabEvent e) {
      refresh();

      /*
       * if (e.getPane().getName().equals(ContentPaneModel.CENTER_PANE)) { return; }
       * 
       * Timer timer = new Timer(10, null); timer.setRepeats(true);
       * timer.addClickListener(new AnimatePane(e.getPane(), timer)); timer.start();
       */
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.lib.ui.modern.tabs.TabEventListener#tabRemoved(org.abh.lib.ui.
     * modern. tabs.TabEvent)
     */
    @Override
    public void tabRemoved(TabEvent e) {
      refresh();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.lib.ui.modern.tabs.TabEventListener#tabResized(org.abh.lib.ui.
     * modern. tabs.TabEvent)
     */
    @Override
    public void tabResized(TabEvent e) {
      layoutComponents();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.lib.ui.modern.tabs.TabEventListener#tabChanged(org.abh.lib.ui.
     * modern. tabs.TabEvent)
     */
    @Override
    public void tabChanged(TabEvent e) {
      // TODO Auto-generated method stub

    }

    @Override
    public void tabHighlighted(TabEvent e) {
      // TODO Auto-generated method stub

    }
  }

  /**
   * The class ComponentEvents.
   */
  private class ComponentEvents implements ComponentListener {

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
      layoutComponents();
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

  /**
   * The class MouseEvents.
   */
  private class MouseEvents implements MouseListener {

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
      setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(MouseEvent e) {
      if (mSelectedDivider == -1) {
        return;
      }

      mSelectedDividerLocation = mDividerLocations.get(mSelectedDivider);
      selectedWidthLeft = mModel.getTab(mSelectedDivider).getWidth();
      selectedWidthRight = mModel.getTab(mSelectedDivider + 1).getWidth();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseReleased(MouseEvent e) {
      setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
  }

  /**
   * The class MouseMotionEvents.
   */
  private class MouseMotionEvents implements MouseMotionListener {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.
     * MouseEvent)
     */
    @Override
    public void mouseDragged(MouseEvent e) {
      if (mSelectedDivider == -1) {
        return;
      }

      setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));

      int dx = e.getX() - mSelectedDividerLocation;

      Tab tab = mModel.getTab(mSelectedDivider);

      tab.adjustWidth(selectedWidthLeft + dx);
      mModel.getTab(mSelectedDivider + 1).adjustWidth(selectedWidthRight - dx);

      tabs().fireTabResized(new TabEvent(this, tab));
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseMoved(MouseEvent e) {
      mSelectedDivider = getClosestDivider(e.getX());

      setCursor(Cursor.getPredefinedCursor(mSelectedDivider != -1 ? Cursor.E_RESIZE_CURSOR : Cursor.DEFAULT_CURSOR));

      // repaint();
    }
  }

  /**
   * Instantiates a new modern h content pane viewer.
   */
  public ModernHContentPane() {
    setLayout(null);
    setModel(new TabsModel());

    addComponentListener(new ComponentEvents());
    addMouseListener(new MouseEvents());
    addMouseMotionListener(new MouseMotionEvents());

    // getBackgroundAnimations().set("hoz-content-pane");
  }

  /**
   * Sets the model.
   *
   * @param model the new model
   */
  public void setModel(TabsModel model) {
    mModel = model;

    model.addTabListener(new ContentPaneEvents());
  }

  /**
   * Gets the model.
   *
   * @return the model
   */
  public TabsModel tabs() {
    return mModel;
  }

  /**
   * Gets the closest divider.
   *
   * @param x the x
   * @return the closest divider
   */
  private int getClosestDivider(int x) {
    int selectedDivider = -1;

    for (int i = 0; i < mDividerLocations.size(); ++i) {
      if (Math.abs(x - mDividerLocations.get(i)) < CLOSEST_D) {
        selectedDivider = i;
        break;
      }
    }

    return selectedDivider;
  }

  /**
   * Refresh.
   */
  private void refresh() {
    removeAll();

    mDividerLocations.clear();

    for (Tab pane : mModel.left()) {
      add(pane.getComponent());
    }

    for (int i = 0; i < mModel.left().size() - 1; ++i) {
      mDividerLocations.add(0);
    }

    if (mModel.centerTab() != null) {
      add(mModel.centerTab().getComponent());
      mDividerLocations.add(0);

      if (mModel.right().size() > 0) {
        mDividerLocations.add(0);
      }
    }

    for (Tab pane : mModel.right()) {
      add(pane.getComponent());
    }

    for (int i = 0; i < mModel.right().size() - 1; ++i) {
      mDividerLocations.add(0);
    }

    layoutComponents();
  }

  /**
   * Layout components.
   */
  private synchronized void layoutComponents() {
    int width = mInternalRect.getW();

    int totalPaneWidth = 0;

    int x = getInsets().left;
    int y = getInsets().top;

    // Get the width of all the panes not including the
    // center

    for (Tab pane : mModel.left()) {
      totalPaneWidth += pane.getWidth() + DIVIDER_WIDTH;
    }

    int rightPaneWidth = 0;

    for (Tab pane : mModel.right()) {
      rightPaneWidth += pane.getWidth() + DIVIDER_WIDTH;
      totalPaneWidth += pane.getWidth() + DIVIDER_WIDTH;
    }

    // The width of the center pane is the difference between
    // the sum of the other panes and the total width. We add
    // divider width because the previous sum overcounts the
    // number of divider lines
    int cw = width - totalPaneWidth;

    int dc = 0;

    int h = mInternalRect.getH();

    for (Tab pane : mModel.left()) {
      int w = pane.getWidth();

      pane.getComponent().setBounds(x, y, w, h);

      x += w;

      // If there are no dividers then we have only
      // added left tabs
      if (dc < mDividerLocations.size()) {
        mDividerLocations.set(dc, x);
      }

      ++dc;

      x += DIVIDER_WIDTH;
    }

    if (mModel.centerTab() != null) {
      mModel.centerTab().getComponent().setBounds(x, y, cw, h);

      x += cw;

      if (mModel.right().size() > 0) {
        mDividerLocations.set(dc, x);
        ++dc;
        // x += DIVIDER_WIDTH;
      }
    }

    x = getInsets().left + width - rightPaneWidth + DIVIDER_WIDTH;

    for (Tab pane : mModel.right()) {
      int w = pane.getWidth();

      pane.getComponent().setBounds(x, y, w, h);

      x += w;

      if (dc < mModel.right().size() - 1) {
        mDividerLocations.set(dc, x);
        ++dc;
        x += DIVIDER_WIDTH;
      }
    }

    revalidate();
    repaint();
  }

  /*
   * @Override public void drawBackgroundAA(Graphics2D g2) {
   * 
   * if (mDividerLocations.size() == 0) { return; }
   * 
   * g2.setColor(ModernWidget.LIGHT_LINE_COLOR);
   * 
   * // Draw divider lines //g2.setColor(ModernWidget.LINE_COLOR); for (int
   * divider : mDividerLocations) { g2.drawLine(divider, getInsets().top, divider,
   * mInternalRect.getH()); } }
   */
}
