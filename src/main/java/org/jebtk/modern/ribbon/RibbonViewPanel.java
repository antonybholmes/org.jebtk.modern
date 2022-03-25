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


import javax.swing.Box;

import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.button.ModernButton;
import org.jebtk.modern.button.ModernClickWidget;
import org.jebtk.modern.panel.HBox;
import org.jebtk.modern.panel.ModernContentPanel;
import org.jebtk.modern.tabs.ViewPanel;

/**
 * The Class RibbonViewPanel.
 */
public class RibbonViewPanel extends ModernContentPanel {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The m hide button. */
  protected ModernButton mHideButton = new RibbonHideButton();

  /** The m view panel. */
  private final ViewPanel mViewPanel = new ViewPanel();

  /**
   * Instantiates a new ribbon view panel.
   */
  public RibbonViewPanel() {
    setBorder(ModernWidget.BORDER); // BorderService.getInstance().createBorder(2));

    setBody(mViewPanel);

    Box box = HBox.create();
    //box.add(UI.createHGap(5));
    box.add(mHideButton);
    //box.add(UI.createHGap(5));
    setRight(box);

    //mHideButton.setToolTip("Minimize Ribbon", "Minimize the ribbon so it takes up less space.");
  }

//  @Override
//  public void drawBackground(Graphics2D g2) {
//    // fill(g2, Ribbon.TAB_COLOR);
//
//    int y = getHeight() - 1;
//    
//    g2.setColor(Color.WHITE);
//    g2.fillRoundRect(getInsets().left, 0, this.getInternalRect().w, y, 4, 4);
//    g2.setColor(ModernWidget.LIGHT_LINE_COLOR);
//    g2.drawRoundRect(getInsets().left, 0, this.getInternalRect().w, y, 4, 4);
//  }

  /**
   * Gets the view panel.
   *
   * @return the view panel
   */
  public ViewPanel getViewPanel() {
    return mViewPanel;
  }

  /**
   * Gets the hide button.
   *
   * @return the hide button
   */
  public ModernClickWidget getHideButton() {
    return mHideButton;
  }
}
