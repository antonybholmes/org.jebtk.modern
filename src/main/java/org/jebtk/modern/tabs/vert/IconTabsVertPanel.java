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
package org.jebtk.modern.tabs.vert;

import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.UI;
import org.jebtk.modern.tabs.TabsModel;
import org.jebtk.modern.tabs.TabsViewPanel;

/**
 * The Class SegmentTabsPanel.
 */
public class IconTabsVertPanel extends ModernComponent {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new segment tabs panel.
   *
   * @param model    the model
   * @param tabSize  the tab size
   * @param iconSize The icon size
   */
  public IconTabsVertPanel(TabsModel model, int tabSize, int iconSize) {

    TabsViewPanel panel = new TabsViewPanel(model);
    // panel.setBorder(0);

    setBody(panel);

    IconVertTabs tabs = new IconVertTabs(model, tabSize, iconSize);
    UI.setSize(tabs, tabSize, Short.MAX_VALUE);
    setLeft(tabs); // new TextTabsTriangle(model, centered));

  }
}
