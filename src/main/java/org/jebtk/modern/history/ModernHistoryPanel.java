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
package org.jebtk.modern.history;

import java.awt.event.MouseListener;

import org.jebtk.modern.AssetService;
import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.button.ModernButton;
import org.jebtk.modern.button.ModernCircleButton;
import org.jebtk.modern.contentpane.HTabToolbar;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.event.ModernSelectionListener;
import org.jebtk.modern.graphics.icons.CheveronDownVectorIcon;
import org.jebtk.modern.graphics.icons.CheveronUpVectorIcon;
import org.jebtk.modern.list.ModernListCellRenderer;
import org.jebtk.modern.list.ModernListModel;
import org.jebtk.modern.scrollpane.ModernScrollPane;
import org.jebtk.modern.scrollpane.ScrollBarLocation;
import org.jebtk.modern.scrollpane.ScrollBarPolicy;
import org.jebtk.modern.window.ModernWindow;

// TODO: Auto-generated Javadoc
/**
 * The class ModernHistoryPanel.
 *
 * @param <T> the generic type
 */
public class ModernHistoryPanel<T> extends ModernComponent implements ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member up button.
   */
  private ModernButton mUpButton = new ModernCircleButton(
      AssetService.getInstance().loadIcon(CheveronUpVectorIcon.class, 12));

  /**
   * The member down button.
   */
  private ModernButton mDownButton = new ModernCircleButton(
      AssetService.getInstance().loadIcon(CheveronDownVectorIcon.class, 12));

  /**
   * The member history model.
   */
  protected ModernListModel<T> mHistoryModel = new ModernListModel<T>();

  /**
   * The member history list.
   */
  protected ModernHistoryList<T> mHistoryList = new ModernHistoryList<T>(mHistoryModel);

  /**
   * Instantiates a new modern history panel.
   *
   * @param parent the parent
   */
  public ModernHistoryPanel(ModernWindow parent) {

    // mParent = parent;

    createUi();

    mUpButton.addClickListener(this);
    mDownButton.addClickListener(this);

    // Sync ui
    mHistoryList.getModel().fireDataChanged();
  }

  /**
   * Creates the ui.
   */
  public void createUi() {
    HTabToolbar toolbar = new HTabToolbar("History");

    toolbar.add(mUpButton);
    toolbar.add(mDownButton);
    // toolbar.setBorder(BOTTOM_BORDER);

    // setHeader(toolbar);

    ModernScrollPane scrollPane = new ModernScrollPane(mHistoryList);
    scrollPane.setHorizontalScrollBarPolicy(ScrollBarPolicy.NEVER);
    scrollPane.setVerticalScrollBarPolicy(ScrollBarPolicy.AUTO_SHOW);
    scrollPane.setVScrollBarLocation(ScrollBarLocation.FLOATING);
    // scrollPane.setBorder(DOUBLE_BORDER);
    setBody(scrollPane);
  }

  /**
   * Adds the item.
   *
   * @param item the item
   * @return the t
   */
  public T addItem(T item) {
    return addItem(item, mHistoryList.getSelectedIndex());
  }

  /**
   * Adds the item.
   *
   * @param item          the item
   * @param selectedIndex the selected index
   * @return the t
   */
  public T addItem(T item, int selectedIndex) {
    if (item == null) {
      return null;
    }

    for (int i = mHistoryModel.getItemCount() - 1; i > selectedIndex; --i) {
      // System.err.println("remove " + i);

      mHistoryModel.removeValueAt(i);
    }

    mHistoryModel.addValue(item);

    mHistoryList.setSelectedIndex(mHistoryList.getItemCount() - 1);

    return item;
  }

  /**
   * Sets the cell renderer.
   *
   * @param renderer the new cell renderer
   */
  public void setCellRenderer(ModernListCellRenderer renderer) {
    mHistoryList.setCellRenderer(renderer);
  }

  /**
   * Adds the selection listener.
   *
   * @param l the l
   */
  public void addSelectionListener(ModernSelectionListener l) {
    mHistoryList.addSelectionListener(l);
  }

  /**
   * Reset history.
   */
  public void resetHistory() {
    mHistoryList.setSelectedIndex(0);
  }

  /**
   * Gets the item count.
   *
   * @return the item count
   */
  public int getItemCount() {
    return mHistoryList.getItemCount();
  }

  /**
   * Gets the selected item.
   *
   * @return the selected item
   */
  public T getSelectedItem() {
    return mHistoryList.getSelectedItem();
  }

  /**
   * Gets the selected index.
   *
   * @return the selected index
   */
  public int getSelectedIndex() {
    return mHistoryList.getSelectedIndex();
  }

  /**
   * Sets the selected index.
   *
   * @param i the new selected index
   */
  public void setSelectedIndex(int i) {
    mHistoryList.setSelectedIndex(i);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.Component#addMouseListener(java.awt.event.MouseListener)
   */
  @Override
  public void addMouseListener(MouseListener l) {
    mHistoryList.addMouseListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  @Override
  public void clicked(ModernClickEvent e) {
    if (e.getSource().equals(mUpButton)) {
      if (mHistoryList.getSelectedIndex() != 0) {
        mHistoryList.setSelectedIndex(mHistoryList.getSelectedIndex() - 1);
      }
    } else {
      if (mHistoryList.getSelectedIndex() < mHistoryList.getItemCount() - 1) {
        mHistoryList.setSelectedIndex(mHistoryList.getSelectedIndex() + 1);
      }
    }
  }

  /**
   * Clear.
   */
  public void clear() {
    mHistoryModel.clear();
  }

  /**
   * Gets the value at.
   *
   * @param index the index
   * @return the value at
   */
  public T getValueAt(int index) {
    return mHistoryList.getValueAt(index);
  }

  /**
   * Removes the value at.
   *
   * @param index the index
   */
  public void removeValueAt(int index) {
    mHistoryList.removeValueAt(index);
  }

  /**
   * Sets the row height.
   *
   * @param rowHeight the new row height
   */
  public void setRowHeight(int rowHeight) {
    mHistoryList.setRowHeight(rowHeight);
  }

}
