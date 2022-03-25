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
package org.jebtk.modern.ribbon;

import java.awt.Dimension;

import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.UI;
import org.jebtk.modern.tabs.SegmentTabs;
import org.jebtk.modern.tabs.TabsModel;
import org.jebtk.modern.tabs.TabsViewPanel;

/**
 * The Class RibbonVertTabsPanel.
 */
public class RibbonVertTabsPanel extends ModernComponent {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new ribbon vert tabs panel.
   *
   * @param model   the model
   * @param tabSize the tab size
   */
  public RibbonVertTabsPanel(TabsModel model, int tabSize) {
    this(model, tabSize, true);
  }

  /**
   * Instantiates a new ribbon vert tabs panel.
   *
   * @param model    the model
   * @param tabSize  the tab size
   * @param centered the centered
   */
  public RibbonVertTabsPanel(TabsModel model, int tabSize, boolean centered) {
    this(model, tabSize, 0, centered);
  }

  /**
   * Instantiates a new ribbon vert tabs panel.
   *
   * @param model       the model
   * @param tabSize     the tab size
   * @param pagePadding the page padding
   * @param centered    the centered
   */
  public RibbonVertTabsPanel(TabsModel model, int tabSize, int pagePadding, boolean centered) {
    this(model, tabSize, 48, pagePadding, centered);
  }

  /**
   * Instantiates a new ribbon vert tabs panel.
   *
   * @param model       the model
   * @param tabSize     the tab size
   * @param tabHeight   the tab height
   * @param pagePadding the page padding
   * @param centered    the centered
   */
  public RibbonVertTabsPanel(TabsModel model, int tabSize, int tabHeight, int pagePadding, boolean centered) {
    SegmentTabs tabs = new RibbonSegmentVertTabs(model, tabSize, centered);

    UI.setSize(tabs, new Dimension(tabSize, Short.MAX_VALUE), UI.createRightBorder(pagePadding));

    setLeft(tabs); // new TextTabsTriangle(model, centered));

    TabsViewPanel panel = new TabsViewPanel(model);
    // panel.setBorder(TOP_BORDER);

    setBody(panel);
  }
}
