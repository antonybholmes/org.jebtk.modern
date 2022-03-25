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
package org.jebtk.modern.table;

import java.util.List;

import org.jebtk.core.collections.CollectionUtils;
import org.jebtk.core.text.TextUtils;

/**
 * The Class ModernSelectionTableModel.
 */
public class ModernSelectionTableModel extends ModernTableModel {

  /** The m values. */
  private List<?> mValues;

  /** The m selected. */
  private List<Boolean> mSelected;

  /** The m heading. */
  private String mHeading;

  /**
   * Instantiates a new modern selection table model.
   *
   * @param heading the heading
   * @param values  the values
   */
  public ModernSelectionTableModel(String heading, List<?> values) {
    mHeading = heading;
    mValues = values;
    mSelected = CollectionUtils.replicate(false, values.size());
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.dataview.ModernDataModel#getRowCount()
   */
  @Override
  public int getRowCount() {
    return mValues.size();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.dataview.ModernDataModel#getColumnCount()
   */
  @Override
  public int getColCount() {
    return 2;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.dataview.ModernDataGridModel#getIsCellEditable(int,
   * int)
   */
  @Override
  public boolean getIsCellEditable(int row, int column) {
    return column == 0;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.dataview.ModernDataModel#getValueAt(int, int)
   */
  @Override
  public Object getValueAt(int row, int column) {
    if (column == 0) {
      return mSelected.get(row);
    } else {
      return mValues.get(row).toString();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.dataview.ModernDataModel#setValueAt(int, int,
   * java.lang.Object)
   */
  @Override
  public void setValueAt(int row, int column, Object value) {
    if (column == 0) {
      mSelected.set(row, (Boolean) value);
    }

    fireDataUpdated();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.table.ModernTableModel#getColumns().getAnnotation(int)
   */
  @Override
  public final String getColumnName(int column) {
    if (column == 0) {
      return TextUtils.EMPTY_STRING;
    } else {
      return mHeading;
    }
  }

  /**
   * Gets the checks if is selected.
   *
   * @param row the row
   * @return the checks if is selected
   */
  public boolean getIsSelected(int row) {
    return mSelected.get(row);
  }
}
