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
package org.jebtk.modern.list;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import org.jebtk.core.collections.CollectionUtils;
import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.geom.IntPos2D;
import org.jebtk.modern.SelectionPolicy;
import org.jebtk.modern.SelectionRangeModel;
import org.jebtk.modern.UI;
import org.jebtk.modern.dataview.ModernDataRowSelection;
import org.jebtk.modern.dataview.ModernDataViewEventProducer;
import org.jebtk.modern.dataview.ModernDataViewListener;
import org.jebtk.modern.dataview.ModernDataViewListeners;
import org.jebtk.modern.event.HighlightEvent;
import org.jebtk.modern.event.HighlightEventProducer;
import org.jebtk.modern.event.HighlightListener;
import org.jebtk.modern.event.HighlightListeners;
import org.jebtk.modern.event.ModernSelectionEventProducer;
import org.jebtk.modern.event.ModernSelectionListener;
import org.jebtk.modern.event.ModernSelectionListeners;
import org.jebtk.modern.graphics.ModernVertCanvas;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.theme.ThemeService;

// TODO: Auto-generated Javadoc
/**
 * Specialized implementation of a data grid that shows items in a one column
 * list.
 *
 * @author Antony Holmes
 * @param <T> the generic type
 */
public class ModernList<T> extends ModernVertCanvas
    implements ModernSelectionEventProducer, ModernDataViewEventProducer, HighlightEventProducer {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant DRAG_LINE_COLOR.
   */
  static final Color DRAG_LINE_COLOR = ThemeService.getInstance().getColors().getGray(6);

  /**
   * The member list model.
   */
  protected ModernListModel<T> mListModel = null;

  // protected ModernDataViewCellsSelectionModel mSelectionModel =
  // new ModernDataViewCellsSelectionModel();

  /**
   * The member selection model.
   */
  protected SelectionRangeModel mSelectionModel = new SelectionRangeModel();

  /**
   * The member renderer.
   */
  protected ModernListCellRenderer mRenderer = null;

  /**
   * The member row height.
   */
  protected int mRowHeight = WIDGET_HEIGHT;

  /**
   * The member selection listeners.
   */
  private ModernSelectionListeners mSelectionListeners = new ModernSelectionListeners();

  private HighlightListeners mHighlightListeners = new HighlightListeners();

  /**
   * The member data listeners.
   */
  private ModernDataViewListeners mDataListeners = new ModernDataViewListeners();

  protected int mPreviousHighlightCellIndex = -1;

  /**
   * The member highlight cell index.
   */
  protected int mHighlightCellIndex = -1;

  /**
   * The member drag cell index.
   */
  protected int mDragCellIndex = -1;

  /**
   * The member selection policy.
   */
  protected SelectionPolicy mSelectionPolicy = SelectionPolicy.MULTIPLE;

  /**
   * The member drag enabled.
   */
  boolean mDragEnabled = false;

  /**
   * The member visible cells.
   */
  ModernDataRowSelection mVisibleCells;

  /**
   * The member selected cell index.
   */
  private int mSelectedCellIndex;

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
      if (e.getButton() != MouseEvent.BUTTON1) {
        return;
      }

      mSelectedCellIndex = getIndex(translateCoordinate(e));
      mDragCellIndex = -1;

      // selectionModel.clearModernSelection();
      // cellsSelectionModel.clear();

      boolean multiSelect = (e.getModifiers() & Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()) == Toolkit
          .getDefaultToolkit().getMenuShortcutKeyMask();
      boolean multiRangeSelect = (e.getModifiers() & ActionEvent.SHIFT_MASK) == ActionEvent.SHIFT_MASK;

      setSelectedCell(getCell(translateCoordinate(e)), multiSelect, multiRangeSelect);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseReleased(MouseEvent e) {
      if (mDragEnabled) {
        dragReorder();
      }

      mSelectedCellIndex = -1;

      repaint();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseExited(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseExited(MouseEvent e) {
      reset();
    }
  }

  /**
   * The class MouseMotionEvents.
   */
  private class MouseMotionEvents extends MouseAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseMoved(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseMoved(MouseEvent e) {
      if (e.getClickCount() > 0) {
        return;
      }

      int i = getIndex(translateCoordinate(e));

      if (i == -1 || i == mHighlightCellIndex) {
        return;
      }

      mPreviousHighlightCellIndex = mHighlightCellIndex;
      mHighlightCellIndex = i;

      fireHighlighted();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseDragged(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseDragged(MouseEvent e) {
      if (e.getClickCount() > 1) {
        return;
      }

      if (!mDragEnabled) {
        return;
      }

      int i = getIndex(translateCoordinate(e));

      mDragCellIndex = i;

      repaint();
    }
  }

  /**
   * The class SelectionEvents.
   */
  private class SelectionEvents implements ModernSelectionListener {

    /**
     * The member list.
     */
    private ModernList<T> mList;

    /**
     * Instantiates a new selection events.
     *
     * @param list the list
     */
    public SelectionEvents(ModernList<T> list) {
      mList = list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.abh.lib.ui.modern.event.ModernSelectionListener#selectionChanged(org.
     * abh. lib.event.ChangeEvent)
     */
    @Override
    public void selectionAdded(ChangeEvent e) {
      fireSelectionAdded(new ChangeEvent(mList));
    }

    @Override
    public void selectionRemoved(ChangeEvent e) {
      fireSelectionRemoved(new ChangeEvent(mList));
    }
  }

  /**
   * The class DataEvents.
   */
  private class DataEvents implements ModernDataViewListener {

    /**
     * The member list.
     */
    private ModernList<T> mList;

    /**
     * Instantiates a new data events.
     *
     * @param list the list
     */
    public DataEvents(ModernList<T> list) {
      mList = list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.abh.lib.ui.modern.dataview.ModernDataViewListener#dataChanged(org.abh
     * .lib .event.ChangeEvent)
     */
    @Override
    public void dataChanged(ChangeEvent e) {
      adjustSize();

      fireDataChanged(new ChangeEvent(mList));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.abh.lib.ui.modern.dataview.ModernDataViewListener#dataUpdated(org.abh
     * .lib .event.ChangeEvent)
     */
    @Override
    public void dataUpdated(ChangeEvent e) {
      fireDataUpdated(new ChangeEvent(mList));
    }
  }

  /**
   * Instantiates a new modern list.
   */
  public ModernList() {
    this(new ModernListIconCellRenderer());
  }

  /**
   * Instantiates a new modern list.
   *
   * @param renderer the renderer
   */
  public ModernList(ModernListCellRenderer renderer) {
    setCellRenderer(renderer);

    setup();
  }

  /**
   * Instantiates a new modern list.
   *
   * @param icon the icon
   */
  public ModernList(ModernIcon icon) {
    this(new ModernListIconCellRenderer(icon));
  }

  /**
   * Instantiates a new modern list.
   *
   * @param model the model
   */
  public ModernList(ModernListModel<T> model) {
    this(model, new ModernListIconCellRenderer());
  }

  /**
   * Instantiates a new modern list.
   *
   * @param model the model
   * @param icon  the icon
   */
  public ModernList(ModernListModel<T> model, ModernIcon icon) {
    this(model, new ModernListIconCellRenderer(icon));
  }

  /**
   * Instantiates a new modern list.
   *
   * @param model    the model
   * @param renderer the renderer
   */
  public ModernList(ModernListModel<T> model, ModernListCellRenderer renderer) {
    setModel(model);
    setCellRenderer(renderer);

    setup();
  }

  /**
   * Instantiates a new modern list.
   *
   * @param size the size
   */
  public ModernList(Dimension size) {
    setup();

    UI.setSize(this, size);
  }

  /**
   * Setup.
   */
  private void setup() {
    addMouseListener(new MouseEvents());
    addMouseMotionListener(new MouseMotionEvents());

    // addCanvasListener(new CanvasEvents());
    // addComponentListener(new ResizeEvents());

    mSelectionModel.addSelectionListener(new SelectionEvents(this));

    getInputMap(WHEN_FOCUSED)
        .put(KeyStroke.getKeyStroke(KeyEvent.VK_A, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()), "a_pressed");
    getActionMap().put("a_pressed", new AbstractAction() {
      private static final long serialVersionUID = 1L;

      @Override
      public void actionPerformed(ActionEvent e) {
        selectAll();
      }
    });

    setAnimations("list");
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
   */
  private void dragReorder() {
    if (mDragCellIndex != -1 && mSelectionModel.size() > 0) {
      // Something must be selected to move it and it must have been
      // dragged

      int from = mSelectedCellIndex; // mSelectionModel.first();
      // int to = mDragCellIndex;

      mHighlightCellIndex = -1;

      int diff = mDragCellIndex - from;

      List<Integer> fromIndices = new ArrayList<Integer>();
      List<Integer> toIndices = new ArrayList<Integer>();

      for (int index : mSelectionModel) {
        fromIndices.add(index);
        toIndices.add(index + diff);
      }

      mListModel.shift(fromIndices, toIndices);

      // Update the new selected cells
      mSelectionModel.setSelection(toIndices);

      // Once the drag is complete, mark it invalid so a new drag
      // must be initiated.
      mDragCellIndex = -1;
    }

  }

  /**
   * Selects a cell if the mouse is over a valid cell when clicked.
   *
   * @param index            the index
   * @param multiItemSelect  the multi item select
   * @param multiRangeSelect the multi range select
   */
  protected void setSelectedCell(int index, boolean multiItemSelect, boolean multiRangeSelect) {

    if (index == -1) {
      return;
    }

    // If we a single selection and the item is already selected,
    // do nothing since we may be trying to move multiple items.
    // Without this test, if a group of items are selected and then
    // any of the items is clicked on again, the selection reverts to
    // a single selection; this prevents multiple items being selected
    // and dragged. The solution is to not change the selection if
    // one of the items being clicked on is already selected.
    if (!multiItemSelect && !multiRangeSelect && mSelectionModel.contains(index)) {
      return;
    }

    if ((!multiItemSelect && !multiRangeSelect) || mSelectionPolicy != SelectionPolicy.MULTIPLE) {
      // If we are not selecting multiple cells, clear
      // the current selection
      mSelectionModel.removeAll();
    }

    if (multiItemSelect) {
      if (mSelectionModel.contains(index)) {
        // If we are multi selecting and pick something we have
        // already selected, then remove it.

        mSelectionModel.remove(index);
      } else {
        mSelectionModel.add(index);
      }
    } else if (multiRangeSelect && mSelectionModel.size() > 0) {
      // since we are adding intermediates, we do not
      // add the current first and last in duplicate,
      // hence the index begins one past the min and
      // ends one before the max
      mSelectionModel.setSelectionInterval(mSelectionModel.first(), index);
    } else {
      mSelectionModel.add(index);
    }
  }

  /**
   * Gets the model.
   *
   * @return the model
   */
  public final ModernListModel<T> getModel() {
    return mListModel;
  }

  /**
   * Sets the model.
   *
   * @param model the new model
   */
  public void setModel(ModernListModel<T> model) {
    mListModel = model;

    model.addDataViewListener(new DataEvents(this));

    adjustSize();
  }

  /**
   * Adjust the size of the table image.
   */
  public void adjustSize() {
    setCanvasSize(getWidth(), Math.max(1, getItemCount()) * mRowHeight);

    // System.err.println("list " + getCanvasSize() + " " + getName());
  }

  /**
   * Sets the cell renderer.
   *
   * @param renderer the new cell renderer
   */
  public void setCellRenderer(ModernListCellRenderer renderer) {
    mRenderer = renderer;

    repaint();
  }

  /**
   * Gets the cell.
   *
   * @param p the p
   * @return the cell
   */
  protected int getCell(IntPos2D p) {
    if (p.getY() / mRowHeight > getItemCount()) {
      return -1;
    }

    int r = (int) (p.getY() / mRowHeight);

    return r;
  }

  /*
   * @Override public void drawBackgroundAA(Graphics2D g2) { // We want to use the
   * unadjusted canvas space (i.e. no offset changes). if (mListModel == null) {
   * return; }
   * 
   * if (mListModel.getItemCount() == 0) { return; }
   * 
   * createImage(g2); }
   * 
   * protected void createImage(Graphics2D g2) { Graphics2D g2Table =
   * (Graphics2D)g2.create();
   * 
   * double maxY = getViewRect().getY() + mInternalRect.getH();
   * 
   * int y = 0; //mRowHeight * mVisibleCells.getStartRow();
   * 
   * for (int i = 0; i < getItemCount(); ++i) { if (y > maxY) { break; }
   * 
   * if (y + mRowHeight >= getViewRect().getY()) { Component c =
   * mRenderer.getCellRendererComponent(this, getValueAt(i), i ==
   * mHighlightCellIndex , mSelectionModel.contains(i), true, i);
   * 
   * c.setSize(getWidth(), mRowHeight);
   * 
   * c.print(g2Table); }
   * 
   * // Move to the next cell location. g2Table.translate(0, mRowHeight);
   * 
   * y += mRowHeight; }
   * 
   * g2Table.dispose();
   * 
   * createDragImage(g2, mVisibleCells); }
   * 
   * protected void createDragImage(Graphics2D g2, ModernDataRowSelection
   * visibleCells) { if (!mDragEnabled || mDragCellIndex == -1) { return; }
   * 
   * g2.setColor(DRAG_LINE_COLOR);
   * 
   * int y = mRowHeight * (Math.min(mDragCellIndex, mListModel.getItemCount()));
   * 
   * g2.drawLine(0, y, getWidth(), y); }
   */

  /**
   * Returns the cumulative distance from 0 of a particular column.
   *
   * @param column the column
   * @return the x
   */
  public final int getX(int column) {
    return 0;
  }

  /**
   * Returns the cumulative distance from 0 of a particular row.
   *
   * @param row the row
   * @return the y
   */
  public final int getY(int row) {
    return mRowHeight * row;
  }

  /**
   * Gets the column.
   *
   * @param x the x
   * @return the column
   */
  public int getColumn(int x) {
    return 0;
  }

  /**
   * Gets the row.
   *
   * @param y the y
   * @return the row
   */
  public int getRow(int y) {
    return y / mRowHeight;
  }

  /**
   * Gets the index.
   *
   * @param p the p
   * @return the index
   */
  protected int getIndex(IntPos2D p) {
    return (int) (p.getY() / mRowHeight);
  }

  /**
   * Gets the point.
   *
   * @param index the index
   * @return the point
   */
  protected Point getPoint(int index) {
    return new Point(0, index * mRowHeight);
  }

  /**
   * Sets the row height.
   *
   * @param rowHeight the new row height
   */
  public void setRowHeight(int rowHeight) {
    mRowHeight = rowHeight;

    adjustSize();
  }

  /**
   * Gets the selected index.
   *
   * @return the selected index
   */
  public int getSelectedIndex() {
    return getSelectionModel().last();
  }

  /**
   * Gets the selected item.
   *
   * @return the selected item
   */
  public T getSelectedItem() {
    return mListModel.getValueAt(getSelectedIndex());
  }

  /**
   * Returns a list of the selected items.
   *
   * @return the selected items
   */
  public List<T> getSelectedItems() {
    List<T> ret = new ArrayList<T>();

    for (int i : getSelectionModel()) {
      ret.add(mListModel.getValueAt(i));
    }

    return ret;
  }

  /**
   * Returns a list of the items in the list.
   *
   * @return the items
   */
  public List<T> getItems() {
    return CollectionUtils.toList(mListModel);
  }

  /**
   * Equivalent to getListModel().clear();
   */
  public void clear() {
    mListModel.clear();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernSelectionEventProducer#
   * addSelectionListener (org.abh.lib.ui.modern.event.ModernSelectionListener)
   */
  @Override
  public void addSelectionListener(ModernSelectionListener l) {
    mSelectionListeners.addSelectionListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernSelectionEventProducer#
   * removeSelectionListener(org.abh.lib.ui.modern.event. ModernSelectionListener)
   */
  @Override
  public void removeSelectionListener(ModernSelectionListener l) {
    mSelectionListeners.removeSelectionListener(l);
  }

  @Override
  public void fireSelectionAdded(ChangeEvent e) {
    repaint();

    mSelectionListeners.fireSelectionAdded(new ChangeEvent(this, e.getMessage()));
  }

  @Override
  public void fireSelectionRemoved(ChangeEvent e) {
    repaint();

    mSelectionListeners.fireSelectionRemoved(new ChangeEvent(this, e.getMessage()));
  }

  /**
   * Sets the selected index.
   *
   * @param i the new selected index
   */
  public void setSelectedIndex(int i) {
    getSelectionModel().setSelection(i);
  }

  /**
   * Gets the value at.
   *
   * @param row the row
   * @return the value at
   */
  public T getValueAt(int row) {
    return (T) mListModel.getValueAt(row);
  }

  /**
   * Removes the value at.
   *
   * @param row the row
   */
  public void removeValueAt(int row) {
    mListModel.removeValueAt(row);
  }

  /**
   * Gets the item count.
   *
   * @return the item count
   */
  public int getItemCount() {
    if (mListModel == null) {
      return 0;
    }

    return mListModel.getItemCount();
  }

  /**
   * Gets the selection model.
   *
   * @return the selection model
   */
  public SelectionRangeModel getSelectionModel() {
    return mSelectionModel;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataViewEventProducer#
   * addDataViewListener(org.abh.lib.ui.modern.dataview.ModernDataViewListener)
   */
  @Override
  public void addDataViewListener(ModernDataViewListener l) {
    mDataListeners.addDataViewListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataViewEventProducer#
   * removeDataViewListener(org.abh.lib.ui.modern.dataview.
   * ModernDataViewListener)
   */
  @Override
  public void removeDataViewListener(ModernDataViewListener l) {
    mDataListeners.removeDataViewListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.dataview.ModernDataViewEventProducer#fireDataChanged(
   * org.abh.lib.event.ChangeEvent)
   */
  @Override
  public void fireDataChanged(ChangeEvent e) {
    mSelectionModel.clear();

    adjustSize();

    mDataListeners.fireDataChanged(e);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.dataview.ModernDataViewEventProducer#fireDataUpdated(
   * org.abh.lib.event.ChangeEvent)
   */
  @Override
  public void fireDataUpdated(ChangeEvent e) {
    mSelectionModel.clear();

    repaint();

    mDataListeners.fireDataUpdated(e);
  }

  /**
   * Select all.
   */
  public void selectAll() {
    mSelectionModel.setSelectionInterval(0, mListModel.getItemCount() - 1);
  }

  @Override
  public void addHighlightListener(HighlightListener l) {
    mHighlightListeners.addHighlightListener(l);
  }

  @Override
  public void removeHighlightListener(HighlightListener l) {
    mHighlightListeners.removeHighlightListener(l);
  }

  @Override
  public void fireHighlighted(HighlightEvent e) {
    mHighlightListeners.fireHighlighted(e);
  }

  public void fireHighlighted() {
    fireHighlighted(new HighlightEvent(this, mHighlightCellIndex));
  }

  private void reset() {
    mPreviousHighlightCellIndex = -1;
    mHighlightCellIndex = -1;

    fireHighlighted();
  }
}
