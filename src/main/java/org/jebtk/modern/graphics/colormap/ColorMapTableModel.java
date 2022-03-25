/**
 * Copyright 2016 Antony Holmes
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
package org.jebtk.modern.graphics.colormap;

import java.util.List;

import org.jebtk.core.Mathematics;
import org.jebtk.core.collections.CollectionUtils;
import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.event.ChangeListener;
import org.jebtk.core.text.TextUtils;
import org.jebtk.modern.table.ModernTableModel;

/**
 * Provides a view onto an excel workbook.
 * 
 * @author Antony Holmes
 *
 */
public class ColorMapTableModel extends ModernTableModel implements ChangeListener {

  /**
   * The constant HEADER.
   */
  private static final String[] HEADER = { TextUtils.EMPTY_STRING, "Name" };

  /** The m names. */
  private List<String> mNames;

  /**
   * Instantiates a new bed table model.
   */
  public ColorMapTableModel() {
    ColorMapService.getInstance().addChangeListener(this);

    update();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.ui.dataview.ModernDataModel#getColumn().getAnnotations(int)
   */
  @Override
  public String getColumnName(int column) {
    if (Mathematics.inBound(column, 0, HEADER.length)) {
      return HEADER[column];
    } else {
      return TextUtils.EMPTY_STRING;
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.ui.dataview.ModernDataModel#getColumnCount()
   */
  @Override
  public final int getColCount() {
    return HEADER.length;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.ui.dataview.ModernDataModel#getRowCount()
   */
  @Override
  public final int getRowCount() {
    return mNames == null ? 0 : mNames.size();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.ui.dataview.ModernDataModel#getValueAt(int, int)
   */
  @Override
  public Object getValueAt(int row, int column) {
    switch (column) {
    case 0:
      return ColorMapService.getInstance().get(mNames.get(row));
    default:
      return mNames.get(row);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.event.ChangeListener#changed(org.abh.common.event.
   * ChangeEvent)
   */
  @Override
  public void changed(ChangeEvent e) {
    change();
  }

  /**
   * Change.
   */
  private void change() {
    update();

    fireDataChanged();
  }

  /**
   * Update.
   */
  private void update() {
    mNames = CollectionUtils.toList(ColorMapService.getInstance());
  }
}
