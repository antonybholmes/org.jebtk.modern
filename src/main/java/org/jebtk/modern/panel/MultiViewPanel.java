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

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.event.ChangeListener;
import org.jebtk.modern.dataview.ModernDataCellsSelectionModel;
import org.jebtk.modern.dataview.ModernDataListView;
import org.jebtk.modern.dataview.ModernDataModel;
import org.jebtk.modern.dataview.ModernDataTileView;
import org.jebtk.modern.scrollpane.ModernScrollPane;
import org.jebtk.modern.table.ModernRowTable;
import org.jebtk.modern.tabs.TabsViewPanel;
import org.jebtk.modern.view.ViewModel;

/**
 * The class MultiViewPanel.
 */
public class MultiViewPanel extends TabsViewPanel implements ChangeListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member view details.
   */
  private ModernRowTable mViewDetails = new ModernRowTable();

  /**
   * The member view list.
   */
  private ModernDataListView mViewList = new ModernDataListView();

  /**
   * The member view tiles.
   */
  private ModernDataTileView mViewTiles = new ModernDataTileView();

  /**
   * The member view model.
   */
  private ViewModel mViewModel;

  /**
   * Instantiates a new multi view panel.
   *
   * @param viewModel the view model
   */
  public MultiViewPanel(ViewModel viewModel) {
    mViewModel = viewModel;

    mViewModel.addChangeListener(this);

    getTabsModel().addTab("Details", new ModernScrollPane(mViewDetails));

    // view.setModel(dataModel);

    getTabsModel().addTab("List", new ModernScrollPane(mViewList));

    // view.setModel(dataModel);

    getTabsModel().addTab("Tiles", new ModernScrollPane(mViewTiles));

    getTabsModel().changeTab(0);
  }

  /**
   * Gets the cell selection model.
   *
   * @return the cell selection model
   */
  public ModernDataCellsSelectionModel getCellSelectionModel() {
    switch (getTabsModel().getSelectedIndex()) {
    case 0:
      return mViewDetails.getCellSelectionModel();
    case 1:
      return mViewList.getCellSelectionModel();
    case 2:
      return mViewTiles.getCellSelectionModel();
    default:
      return null;
    }
  }

  /**
   * Sets the model.
   *
   * @param model the new model
   */
  public void setModel(ModernDataModel model) {
    mViewDetails.setModel(model);
    mViewList.setModel(model);
    mViewTiles.setModel(model);

    mViewDetails.getColumnModel().setWidth(0, 400);
    mViewDetails.getColumnModel().setWidth(1, 200);
  }

  /**
   * Convert row index to model.
   *
   * @param i the i
   * @return the int
   */
  public int convertRowIndexToModel(int i) {
    switch (getTabsModel().getSelectedIndex()) {
    case 0:
      return mViewDetails.getModelRowIndex(i);
    case 1:
      return mViewList.getModelRowIndex(i);
    case 2:
      return mViewTiles.getModelRowIndex(i);
    default:
      return i;
    }
  }

  /**
   * View changed.
   */
  private void viewChanged() {
    getTabsModel().changeTab(mViewModel.getView());
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.event.ChangeListener#changed(org.abh.lib.event.ChangeEvent)
   */
  @Override
  public void changed(ChangeEvent e) {
    viewChanged();
  }
}
