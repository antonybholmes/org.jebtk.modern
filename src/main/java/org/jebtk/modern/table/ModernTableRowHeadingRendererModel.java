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

import org.jebtk.modern.table.header.ModernTableHeaderRowCellRenderer;

/**
 * The Class ModernTableRowHeadingRendererModel.
 */
public class ModernTableRowHeadingRendererModel extends ModernTableHeadingRendererModel {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new modern table row heading renderer model.
   */
  public ModernTableRowHeadingRendererModel() {
    super(new ModernTableHeaderRowCellRenderer());
  }
}
