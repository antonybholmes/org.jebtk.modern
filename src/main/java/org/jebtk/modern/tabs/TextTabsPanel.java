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
package org.jebtk.modern.tabs;

import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;

/**
 * The Class TextTabsPanel.
 */
public class TextTabsPanel extends ModernComponent {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new text tabs panel.
   *
   * @param model the model
   */
  public TextTabsPanel(TabsModel model) {
    this(model, 0);
  }

  /**
   * Instantiates a new text tabs panel.
   *
   * @param model       the model
   * @param pagePadding the page padding
   */
  public TextTabsPanel(TabsModel model, int pagePadding) {
    TextTabsCentered tabs = new TextTabsCentered(model);
    UI.setSize(tabs, ModernWidget.MAX_SIZE, UI.createTopBottomBorder(pagePadding));

    setHeader(tabs); // new TextTabsTriangle(model, centered));

    TabsViewPanel panel = new TabsViewPanel(model);
    // panel.setBorder(TOP_BORDER);

    setBody(panel);
  }
}
