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

import java.awt.BorderLayout;

import org.jebtk.modern.ModernComponent;

/**
 * The Class SideTabsPanel.
 */
public class SideTabsPanel extends ModernComponent {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new side tabs panel.
   *
   * @param model the model
   */
  public SideTabsPanel(TabsModel model) {
    add(new SideTabs(model), BorderLayout.LINE_START);

    TabsViewPanel panel = new TabsViewPanel(model);
    panel.setBorder(LEFT_BORDER);

    setBody(panel);
  }
}
