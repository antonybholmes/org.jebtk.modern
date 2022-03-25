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

import org.jebtk.modern.dataview.ModernData1DModel;
import org.jebtk.modern.dataview.ModernDataCellRenderer;
import org.jebtk.modern.table.header.ModernTableHeaderColumnCellRenderer;

/**
 * The Class ModernTableHeadingRendererModel.
 */
public class ModernTableHeadingRendererModel extends ModernData1DModel<ModernDataCellRenderer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new modern table heading renderer model.
   */
  public ModernTableHeadingRendererModel() {
    this(new ModernTableHeaderColumnCellRenderer());
  }

  /**
   * Instantiates a new modern table heading renderer model.
   *
   * @param renderer the renderer
   */
  public ModernTableHeadingRendererModel(ModernDataCellRenderer renderer) {
    super(renderer);
  }

}
