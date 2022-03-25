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

import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.WidgetAnimation;

/**
 * Allows for fade in/out animation on an element.
 *
 * @author Antony Holmes
 */
public class RibbonSegmentAnimation extends WidgetAnimation {

  private final RibbonSegmentVertTabs mSegments;

  /**
   * Instantiates a new state animation.
   *
   * @param ribbon the ribbon
   */
  public RibbonSegmentAnimation(ModernWidget w) {
    super(w);

    mSegments = (RibbonSegmentVertTabs) w;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.animation.Animation#draw(org.abh.common.ui.widget.
   * ModernWidget, java.awt.Graphics2D, java.lang.Object[])
   */
  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {

    int n = mSegments.getTabsModel().getTabCount();

    int selectedIndex = mSegments.getTabsModel().getSelectedIndex();

    //
    // Draw the labels
    //

    int textY = ModernWidget.getTextYPosCenter(g2, RibbonSegmentVertTabs.TAB_SIZE);
    int iconY = (RibbonSegmentVertTabs.TAB_SIZE - RibbonSegmentVertTabs.ICON_SIZE) / 2;
    int tabX = RibbonSegmentVertTabs.TAB_SIZE / 2;
    int textX = tabX + RibbonSegmentVertTabs.ICON_SIZE + RibbonSegmentVertTabs.ICON_SIZE / 2;

    for (int i = 0; i < n; ++i) {
      boolean selected = i == selectedIndex;

      // g2.setColor(ModernWidget.TEXT_COLOR);
      g2.setColor(selected ? Ribbon.BAR_BACKGROUND : ModernWidget.TEXT_COLOR);
      // g2.setFont(selected ? BOLD_FONT : FONT);

      String s = mSegments.getTabsModel().getTab(i).getName();

      if (mSegments.getTabsModel().getTab(i).getIcon() != null) {
        mSegments.getTabsModel().getTab(i).getIcon().drawIcon(g2, tabX, iconY, RibbonSegmentVertTabs.TAB_SIZE);
      }

      g2.drawString(s, textX, textY);

      iconY += RibbonSegmentVertTabs.TAB_SIZE;
      textY += RibbonSegmentVertTabs.TAB_SIZE;
    }
  }
}
