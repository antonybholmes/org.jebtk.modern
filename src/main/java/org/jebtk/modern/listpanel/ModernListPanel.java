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
package org.jebtk.modern.listpanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.SwingUtilities;

import org.jebtk.core.Mathematics;
import org.jebtk.modern.scrollpane.VertScrollWidget;

// TODO: Auto-generated Javadoc
/**
 * Specialized implementation of a data grid that shows items in a one column
 * list.
 *
 * @author Antony Holmes
 */
public class ModernListPanel extends VertScrollWidget implements Iterable<ModernListPanelItem> {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The m auto reorder. */
  private boolean mAutoReorder = false;

  /**
   * The member list model.
   */
  private List<ModernListPanelItem> mListModel = new ArrayList<ModernListPanelItem>(10);

  /** The m Y pos. */
  private List<Integer> mYPos = new ArrayList<Integer>();

  /** The m selected C. */
  private Component mSelectedC;

  /** The m drag C. */
  private Component mDragC;

  /**
   * The member drag enabled.
   */
  private boolean mDragEnabled = true;

  /**
   * The member selected cell index.
   */
  private int mSelectedCellIndex;

  /**
   * The member drag cell index.
   */
  private int mDragCellIndex = -1;

  /** The m start Y. */
  private int mStartY;

  /** The m drag Y. */
  private int mDragY;

  /** The current position of a component. */
  private int mCY;

  /** Max preferred height of panel. */
  private int mH;

  /** The m max Y. */
  private int mMaxY;

  /** The m item H. */
  private int mItemH;

  /** The m drag block Y. */
  private int mDragBlockY = -1;

  /** The m press block Y. */
  private int mPressBlockY = -1;

  /**
   * Reference to the component that needs to be moved out of the way during
   * dragging
   */
  private Component mDraggedC;

  /** The drag direction. */
  private int mDir = Integer.MIN_VALUE;

  /**
   * The Class AnimateMovement.
   */
  private class AnimateMovement extends TransitionStepAnimation {

    /** The m index. */
    private int mIndex;

    /**
     * Instantiates a new animate movement.
     *
     * @param index the index
     * @param y2    the y 2
     */
    public AnimateMovement(int index, int y2) {
      super(null);

      mIndex = index;

      int y1 = mYPos.get(index);

      setup(y1, y2);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void animate(int step, double x) {
      mYPos.set(mIndex, (int) x); // mPressBlockY);

      layoutContainer();
    }
  }

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
      if (!SwingUtilities.isLeftMouseButton(e)) {
        return;
      }

      Point p = convert(e);

      mStartY = p.y;

      mSelectedC = (Component) e.getSource();

      // Try to move above others for drawing
      // setComponentZOrder(mSelectedC, 1000);

      mCY = mSelectedC.getY();

      mPressBlockY = getBlockY(p);

      mSelectedCellIndex = getIndex(e, null);
      mDragCellIndex = -1;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseReleased(MouseEvent e) {

      // Set item to new block position so it is nicely positioned.

      if (mSelectedCellIndex != -1 && mDragBlockY != -1) {
        mYPos.set(mSelectedCellIndex, mDragBlockY);
      }

      reset();

      layoutContainer();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseExited(java.awt.event.MouseEvent)
     */
    // @Override
    // public void mouseExited(MouseEvent e) {
    // reset();
    // }
  }

  /**
   * The class MouseMotionEvents.
   */
  private class MouseMotionEvents extends MouseAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseDragged(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseDragged(MouseEvent e) {
      if (!SwingUtilities.isLeftMouseButton(e)) {
        return;
      }

      if (e.getClickCount() > 1) {
        return;
      }

      if (!mDragEnabled) {
        return;
      }

      Point p = convert(e);

      mDragY = p.y;

      int yDiff = mDragY - mStartY;

      int y = Mathematics.bound(mCY + yDiff, 0, mMaxY);

      Component c = (Component) e.getSource();

      mDragCellIndex = getIndex(e, mSelectedC);

      mDragC = getComp(e, mSelectedC);

      mDragBlockY = getBlockY(p);

      c.setLocation(c.getX(), y);
      mYPos.set(mSelectedCellIndex, y);

      int dir = Mathematics.signum(mDragBlockY - mPressBlockY);

      // System.err.println("diff " + mDragCellIndex + " " + mDragBlockY + " " +
      // mPressBlockY + " " + dir);

      if (!dragReorder(dir)) {
        refresh();
      }
    }
  }

  /**
   * Instantiates a new modern list panel.
   */
  public ModernListPanel() {
    setLayout(null);

    addComponentListener(new ComponentAdapter() {

      @Override
      public void componentResized(ComponentEvent e) {
        layoutContainer();
      }
    });
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.Container#add(java.awt.Component)
   */
  @Override
  public Component add(Component c) {
    return add(c, DARK_LINE_COLOR);
  }

  /**
   * Adds the.
   *
   * @param c     the c
   * @param color the color
   * @return the component
   */
  public Component add(Component c, Color color) {
    ModernListPanelItem item = new ModernListPanelItem(c, color);

    item.addMouseListener(new MouseEvents());
    item.addMouseMotionListener(new MouseMotionEvents());

    mListModel.add(item);

    super.add(item);

    if (mYPos.size() == 0) {
      mYPos.add(0);
    } else {
      int i = mYPos.size() - 1;

      mYPos.add(getComponent(i).getPreferredSize().height + mYPos.get(i));
    }

    mItemH = item.getPreferredSize().height;

    resize();

    return c;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.Container#add(java.awt.Component, java.lang.Object)
   */
  @Override
  public void add(Component c, Object o) {
    add(c);
  }

  /**
   * Gets the block Y.
   *
   * @param p the p
   * @return the block Y
   */
  private int getBlockY(Point p) {
    return Mathematics.bound(p.y / mItemH * mItemH, 0, mMaxY);
  }

  /**
   * Gets the index.
   *
   * @param e        the e
   * @param excludeC the exclude C
   * @return the index
   */
  private int getIndex(MouseEvent e, Component excludeC) {
    Point p = convert(e);

    for (int i = 0; i < mListModel.size(); ++i) {
      ModernListPanelItem item = mListModel.get(i);

      if ((excludeC == null || !item.equals(excludeC))) {
        int ty = mYPos.get(i);

        if (p.y >= ty && p.y < ty + mItemH) {
          return i;
        }
      }
    }

    return -1;
  }

  /**
   * Gets the comp.
   *
   * @param e        the e
   * @param excludeC the exclude C
   * @return the comp
   */
  private Component getComp(MouseEvent e, Component excludeC) {
    Point p = convert(e);

    for (int i = 0; i < mListModel.size(); ++i) {
      ModernListPanelItem item = mListModel.get(i);

      int ty = mYPos.get(i);

      if ((excludeC == null || !item.equals(excludeC)) && p.y >= ty && p.y < ty + mItemH) {
        return mListModel.get(i);
      }
    }

    return null;
  }

  /**
   * Convert a point on a component to panel space.
   *
   * @param e the e
   * @return the point
   */
  private Point convert(MouseEvent e) {
    Point p = SwingUtilities.convertPoint((Component) e.getSource(), e.getPoint(), this);

    p.y -= getInsets().top;

    return p;
  }

  /**
   * Allows user to drag items in a list to reorder it. This is disabled by
   * default.
   *
   * @param enabled the new drag reorder enabled
   */
  public void setDragReorderEnabled(boolean enabled) {
    mDragEnabled = enabled;

    mDragCellIndex = -1;
  }

  /**
   * Drag reorder.
   *
   * @param dir the dir
   * @return true, if successful
   */
  private boolean dragReorder(int dir) {
    // if (mDragCellIndex != -1) {
    // Something must be selected to move it and it must have been
    // dragged

    // mYPos.set(mDragCellIndex, mPressBlockY);

    if (mPressBlockY == mDragBlockY) {
      return false;
    }

    // System.err.println("s1");

    if (mDragC == null || mDragC.equals(mSelectedC)) {
      return false;
    }

    // System.err.println("s2 " + mDragC.equals(mDraggedC) + " " + (dir ==
    // mDir));

    if (mDragC.equals(mDraggedC) && dir == mDir) {
      return false;
    }

    // System.err.println("s3");

    new AnimateMovement(mDragCellIndex, mPressBlockY).start();

    // Once we are in a new block, make this block the starting block
    mPressBlockY = mDragBlockY;
    mDraggedC = mDragC;
    mDir = dir;
    mAutoReorder = true;

    layoutContainer();

    return true;
  }

  /**
   * Layout container.
   */
  private void layoutContainer() {
    Insets insets = getInsets();
    int maxWidth = getWidth() - insets.left - insets.right;
    int x = insets.left;
    int y = insets.top;

    for (int i = 0; i < mListModel.size(); ++i) {
      y = insets.top + mYPos.get(i);

      ModernListPanelItem item = mListModel.get(i);

      if (mSelectedC == null || !item.equals(mSelectedC)) {
        int h = item.getPreferredSize().height;

        item.setBounds(x, y, maxWidth, h);
      }

      // y += h;
    }

    // UI.setSize(this, maxWidth, y);

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
   * Resize.
   */
  private void resize() {
    Insets insets = getInsets();

    mH = insets.top + insets.bottom + mItemH * mListModel.size();

    mMaxY = mItemH * (mListModel.size() - 1);
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.JComponent#getPreferredSize()
   */
  @Override
  public Dimension getPreferredSize() {
    return new Dimension(getWidth(), mH);
  }

  /**
   * Gets the.
   *
   * @param index the index
   * @return the modern list panel item
   */
  public ModernListPanelItem get(int index) {
    autoReorder();

    return mListModel.get(index);
  }

  /**
   * Reorder items based on y position if necessary. This is so that when iterated
   * over, items match the order they appear in the UI.
   */
  private void autoReorder() {
    if (!mAutoReorder) {
      return;
    }

    // Order by y coordinate

    Map<Integer, Integer> ySortMap = new TreeMap<Integer, Integer>();

    for (int i = 0; i < mYPos.size(); ++i) {
      ySortMap.put(mYPos.get(i), i);
    }

    List<ModernListPanelItem> listModel = new ArrayList<ModernListPanelItem>(mListModel.size());

    List<Integer> yPos = new ArrayList<Integer>(mListModel.size());

    for (int y : ySortMap.keySet()) {
      int i = ySortMap.get(y);

      listModel.add(mListModel.get(i));
      yPos.add(mYPos.get(i));
    }

    // Swap in new arrays
    mListModel = listModel;
    mYPos = yPos;

    mAutoReorder = false;
  }

  /**
   * Reset.
   */
  private void reset() {
    mSelectedCellIndex = -1;
    mDragCellIndex = -1;
    mStartY = -1;
    mSelectedC = null;
    mDragC = null;
    mDraggedC = null;
    mDragBlockY = -1;
    mDragY = -1;
    mPressBlockY = -1;
    mDir = Integer.MIN_VALUE;
  }

  @Override
  public void drawBackground(Graphics2D g2) {
    g2.setColor(LIGHT_LINE_COLOR);

    int y = getInsets().top;

    int x = getInsets().left;
    int w = getWidth() - x - getInsets().right;

    g2.drawLine(x, y, w, y);

    y += mItemH;

    for (int i = 0; i < mListModel.size(); ++i) {
      g2.drawLine(x, y, w, y);

      y += mItemH;
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Iterable#iterator()
   */
  @Override
  public Iterator<ModernListPanelItem> iterator() {
    autoReorder();

    return mListModel.iterator();
  }
}
