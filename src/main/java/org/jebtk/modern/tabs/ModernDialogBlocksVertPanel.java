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
package org.jebtk.modern.tabs;

import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.UI;

/**
 * The Class ModernDialogSegmentsPanel provides a segments widget for dialogs
 * where the segments hovers above.
 */
public class ModernDialogBlocksVertPanel extends ModernComponent {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new modern dialog segments panel.
   *
   * @param model   the model
   * @param tabSize the tab size
   */
  public ModernDialogBlocksVertPanel(TabsModel model, int tabSize) {
    BlockVertTabs tabs = new BlockVertTabs(model, tabSize * 3 / 4);

    UI.setSize(tabs, tabSize, Short.MAX_VALUE);

    setLeft(tabs); // new TextTabsTriangle(model, centered));

    TabsViewPanel panel = new TabsViewPanel(model);
    // panel.setBorder(TOP_BORDER);

    setBody(panel); // new ModernLineBorderPanel(new ModernComponent(panel,
    // LARGE_BORDER)));
  }
}
