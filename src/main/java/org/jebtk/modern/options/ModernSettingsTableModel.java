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
package org.jebtk.modern.options;

import java.util.ArrayList;
import java.util.List;

import org.jebtk.core.Mathematics;
import org.jebtk.core.collections.CollectionUtils;
import org.jebtk.core.path.Path;
import org.jebtk.core.settings.Setting;
import org.jebtk.core.settings.SettingsService;
import org.jebtk.core.text.TextUtils;
import org.jebtk.core.tree.TreeNode;
import org.jebtk.modern.table.ModernTableModel;

/**
 * Displays key/value settings pairs and allows users to edit them.
 * 
 * @author Antony Holmes
 *
 */
public class ModernSettingsTableModel extends ModernTableModel {

  /**
   * The constant HEADER.
   */
  private static final String[] HEADER = { "Name", "Value" };

  /**
   * The member settings keys.
   */
  private List<Path> mPaths = new ArrayList<Path>();

  /**
   * The member values.
   */
  private List<Setting> mValues = new ArrayList<Setting>();

  /**
   * Instantiates a new modern settings table model.
   *
   * @param node the node
   */
  public ModernSettingsTableModel(TreeNode<List<Path>> node) {
    if (node == null || node.getValue() == null) {
      return;
    }

    for (Path path : CollectionUtils.sort(node.getValue())) {
      Setting settings = SettingsService.getInstance().getSetting(path);

      if (settings != null) {
        mPaths.add(path);
        mValues.add(settings);
      }
    }

    // Collections.sort(settingsKeys);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataModel#getColumnCount()
   */
  @Override
  public final int getColCount() {
    return HEADER.length;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataModel#getRowCount()
   */
  @Override
  public final int getRowCount() {
    return mPaths.size();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataModel#getValueAt(int, int)
   */
  @Override
  public final Object getValueAt(int row, int col) {
    switch (col) {
    case 0:
      return mPaths.get(row);
    case 1:
      return mValues.get(row).getString();
    }

    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.dataview.ModernDataModel#getColumn().getAnnotations(
   * int)
   */
  @Override
  public final String getColumnName(int column) {
    if (Mathematics.inBound(column, 0, HEADER.length)) {
      return HEADER[column];
    } else {
      return TextUtils.EMPTY_STRING;
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.dataview.ModernDataGridModel#getIsCellEditable(int,
   * int)
   */
  @Override
  public final boolean getIsCellEditable(int rowIndex, int column) {
    return column == 1;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataModel#setValueAt(int, int,
   * java.lang.Object)
   */
  @Override
  public final void setValueAt(int row, int column, Object value) {
    if (column == 1) {
      Path path = mValues.get(0).getPath();

      String s = value.toString();

      // Update with a new setting
      mValues.set(row, Setting.parse(path, s));

      SettingsService.getInstance().update(path, s);
    }

    SettingsService.getInstance().save();
  }

}