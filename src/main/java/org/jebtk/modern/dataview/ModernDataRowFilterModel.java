/**
 * Copyright 2017 Antony Holmes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jebtk.modern.dataview;

import java.util.List;

/**
 * The Class ModernDataRowFilterModel.
 */
public class ModernDataRowFilterModel extends ModernDataModel {

  /** The m model. */
  private ModernDataModel mModel;

  /** The m rows. */
  private List<Integer> mRows;

  /**
   * Instantiates a new modern data row filter model.
   *
   * @param model the model
   * @param rows  the rows
   */
  public ModernDataRowFilterModel(ModernDataModel model, List<Integer> rows) {
    mModel = model;
    mRows = rows;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.dataview.ModernDataModel#getRowCount()
   */
  @Override
  public int getRowCount() {
    return mRows.size();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.dataview.ModernDataModel#getColumnCount()
   */
  @Override
  public int getColCount() {
    return mModel.getColCount();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.dataview.ModernDataModel#getValueAt(int, int)
   */
  @Override
  public Object getValueAt(int row, int column) {
    return mModel.getValueAt(mRows.get(row), column);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.dataview.ModernDataModel#getIsCellEditable(int, int)
   */
  @Override
  public boolean getIsCellEditable(int row, int column) {
    return mModel.getIsCellEditable(mRows.get(row), column);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.dataview.ModernDataModel#getColumns().getNames()
   */
  @Override
  public String getColumnName(int col) {
    return mModel.getColumnName(col);
  }
}
