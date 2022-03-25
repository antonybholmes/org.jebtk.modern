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
package org.jebtk.modern.panel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.border.Border;

import org.jebtk.modern.ModernWidget;

/**
 * The class SimpleMatrixPanel.
 */
public class SimpleMatrixPanel extends ModernWidget implements ComponentListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The row heights.
   */
  private int[] rowHeights = null;

  /**
   * The column width.
   */
  private int columnWidth;

  /**
   * The x sep.
   */
  private int xSep; // = new CopyOnWriteArrayList<Integer>();

  /**
   * The y sep.
   */
  private int ySep; // = new CopyOnWriteArrayList<Integer>();

  // private List<Component> components = new CopyOnWriteArrayList<Component>();

  /**
   * The row objects map.
   */
  private List<List<Component>> rowObjectsMap = new CopyOnWriteArrayList<List<Component>>();

  /**
   * Instantiates a new simple matrix panel.
   *
   * @param rowHeights  the row heights
   * @param columnWidth the column width
   * @param xSep        the x sep
   * @param ySep        the y sep
   */
  public SimpleMatrixPanel(int[] rowHeights, int columnWidth, int xSep, int ySep) {
    super.setLayout(null);

    this.rowHeights = rowHeights;
    this.columnWidth = columnWidth;

    this.xSep = xSep;
    this.ySep = ySep;

    addComponentListener(this);
  }

  /*
   * public MatrixPanel(int[] rowHeights, int[] columnWidths, int[] xSep, int[]
   * ySep) { super();
   * 
   * super.setLayout(null);
   * 
   * this.rowHeights = rowHeights; this.columnWidths = columnWidths;
   * 
   * int w = getInsets().left; int h = getInsets().bottom;
   * 
   * for (int i : rowHeights) { h += i; }
   * 
   * for (int i : columnWidths) { w += i; }
   * 
   * for (int i : xSep) { this.xSep.add(i); w += i; }
   * 
   * for (int i : ySep) { this.ySep.add(i); h += i; }
   * 
   * w += getInsets().right; h += getInsets().top;
   * 
   * setCanvasSize(new Dimension(w, h)); }
   */

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.Container#setLayout(java.awt.LayoutManager)
   */
  public final void setLayout(LayoutManager manager) {
    // super.setLayout(manager);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.Container#add(java.awt.Component, java.lang.Object)
   */
  public final void add(Component component, Object constraints) {
    add(component);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.Container#add(java.awt.Component)
   */
  public final Component add(Component component) {
    super.add(component);

    int i = rowObjectsMap.size() - 1;

    if (rowObjectsMap.size() == 0 || rowObjectsMap.get(i).size() == 2) {
      addRow();
      ++i;
    }

    rowObjectsMap.get(i).add(component);

    layoutComponents();

    refresh();

    return component;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.Container#remove(java.awt.Component)
   */
  public final void remove(Component component) {
    // do nothing
  }

  /**
   * Clear.
   */
  public void clear() {
    removeAll();
    rowObjectsMap.clear();
  }

  /**
   * Removes the row.
   *
   * @param row the row
   */
  public final void removeRow(int row) {
    if (row < 0 || row > rowObjectsMap.size() - 1) {
      return;
    }

    for (Component c : rowObjectsMap.get(row)) {
      super.remove(c);
    }

    rowObjectsMap.remove(row);

    layoutComponents();

    refresh();
  }

  /**
   * Refresh.
   */
  private void refresh() {
    revalidate();
    repaint();
  }

  /**
   * Gets the row count.
   *
   * @return the row count
   */
  public final int getRowCount() {
    return rowObjectsMap.size();
  }

  /**
   * Adds the row.
   */
  public final void addRow() {
    rowObjectsMap.add(new CopyOnWriteArrayList<Component>());
  }

  /**
   * Layout components.
   */
  public final void layoutComponents() {
    if (rowHeights == null || rowObjectsMap.size() == 0) {
      // setBorder is called before initialization so
      // put a check here
      return;
    }

    int x = this.getInsets().left;
    int y = this.getInsets().top;

    for (int row = 0; row < rowObjectsMap.size(); ++row) {
      x = this.getInsets().left;

      int r = row % rowHeights.length;

      int w = columnWidth;
      int h = rowHeights[r];

      rowObjectsMap.get(row).get(0).setBounds(x, y, w, h);

      x += w + this.xSep;
      w = getWidth() - getInsets().right - x - PADDING;

      if (rowObjectsMap.get(row).size() > 1) {
        rowObjectsMap.get(row).get(1).setBounds(x, y, w, h);
      }

      y += rowHeights[r] + ySep;
    }

    setPreferredSize(new Dimension(0, y));
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.ModernComponent#setBorder(javax.swing.border.Border)
   */
  public final void setBorder(Border border) {
    super.setBorder(border);

    layoutComponents();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.ComponentListener#componentResized(java.awt.event.
   * ComponentEvent)
   */
  public final void componentResized(ComponentEvent e) {
    layoutComponents();
  }

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
   * @see java.awt.event.ComponentListener#componentShown(java.awt.event.
   * ComponentEvent)
   */
  @Override
  public void componentShown(ComponentEvent e) {
    // TODO Auto-generated method stub

  }
}
