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
import java.awt.Graphics2D;
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
public class ModernVContentPaneViewer extends ModernWidget {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant DIVIDER_WIDTH.
   */
  private static final int DIVIDER_WIDTH = 1;

  /**
   * The model.
   */
  private TabsModel model;

  /**
   * The divider locations.
   */
  private List<Integer> dividerLocations = new ArrayList<Integer>();

  /**
   * The selected divider.
   */
  private int selectedDivider = -1;

  /**
   * The selected divider location.
   */
  private int selectedDividerLocation = -1;

  /**
   * The selected width top.
   */
  private int selectedWidthTop;

  /**
   * The selected width bottom.
   */
  private int selectedWidthBottom;

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
      if (selectedDivider == -1) {
        return;
      }

      selectedDividerLocation = dividerLocations.get(selectedDivider);
      selectedWidthTop = model.getTab(selectedDivider).getWidth();
      selectedWidthBottom = model.getTab(selectedDivider + 1).getWidth();
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
      if (selectedDivider == -1) {
        return;
      }

      setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));

      int dy = e.getY() - selectedDividerLocation;

      Tab tab = model.getTab(selectedDivider);

      tab.adjustWidth(selectedWidthTop + dy);
      model.getTab(selectedDivider + 1).adjustWidth(selectedWidthBottom - dy);

      getModel().fireTabResized(new TabEvent(this, tab));
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseMoved(MouseEvent e) {
      selectedDivider = getClosestDivider(e.getY());

      setCursor(Cursor.getPredefinedCursor(selectedDivider != -1 ? Cursor.N_RESIZE_CURSOR : Cursor.DEFAULT_CURSOR));

    }
  }

  /**
   * Instantiates a new modern v content pane viewer.
   */
  public ModernVContentPaneViewer() {
    setLayout(null);
    setModel(new TabsModel());

    addComponentListener(new ComponentEvents());
    addMouseListener(new MouseEvents());
    addMouseMotionListener(new MouseMotionEvents());
  }

  /**
   * Sets the model.
   *
   * @param model the new model
   */
  public void setModel(TabsModel model) {
    this.model = model;

    model.addTabListener(new ContentPaneEvents());
  }

  /**
   * Gets the model.
   *
   * @return the model
   */
  public TabsModel getModel() {
    return model;
  }

  /**
   * Gets the closest divider.
   *
   * @param y the y
   * @return the closest divider
   */
  private int getClosestDivider(int y) {
    int selectedDivider = -1;

    for (int i = 0; i < dividerLocations.size(); ++i) {
      if (Math.abs(y - dividerLocations.get(i)) < 2) {
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

    dividerLocations.clear();

    for (Tab pane : model) {
      add(pane.getComponent());
    }

    for (int i = 0; i < model.getTabCount() - 1; ++i) {
      dividerLocations.add(0);
    }

    layoutComponents();
  }

  /**
   * Layout components.
   */
  private synchronized void layoutComponents() {
    int height = mInternalRect.getH();

    int totalPaneHeight = 0;

    int x = getInsets().left;
    int y = getInsets().top;

    // Get the width of all the panes not including the
    // center

    for (Tab pane : model) {
      if (pane.getName().equals(TabsModel.CENTER_PANE)) {
        continue;
      }

      totalPaneHeight += pane.getWidth() + DIVIDER_WIDTH;
    }

    // The width of the center pane is the difference between
    // the sum of the other panes and the total width. We add
    // divider width because the previous sum overcounts the
    // number of divider lines
    int ch = height - totalPaneHeight;

    int dc = 0;

    int w = mInternalRect.getW();

    for (Tab pane : model) {
      if (pane.getName().equals(TabsModel.CENTER_PANE)) {
        pane.getComponent().setBounds(x, y, w, ch);

        y += ch;
      } else {
        int h = pane.getWidth();

        pane.getComponent().setBounds(x, y, w, h);

        y += h;
      }

      if (dc < model.getTabCount() - 1) {
        dividerLocations.set(dc, y);
      }

      y += DIVIDER_WIDTH;

      ++dc;
    }

    revalidate();
    repaint();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {

    if (dividerLocations.size() == 0) {
      return;
    }

    // Draw divider lines
    // g2.setColor(LINE_COLOR);
    // for (int divider : dividerLocations) {
    // g2.drawLine(getInsets().left, divider, mInternalRect.getW(), divider);
    // }
  }
}
