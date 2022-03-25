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

import java.awt.Color;
import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.WidgetAnimation;
import org.jebtk.modern.theme.ThemeService;

/**
 * Allows for fade in/out animation on an element.
 *
 * @author Antony Holmes
 * @param <T>
 */
public class OrbTabsAnimation extends WidgetAnimation {

  private static final Color TEXT_COLOR = ThemeService.getInstance().getColors().getGray(10);

  private final OrbTabs mTabs;

  /**
   * Instantiates a new state animation.
   *
   * @param ribbon the ribbon
   */
  public OrbTabsAnimation(ModernWidget w) {
    super(w);

    mTabs = (OrbTabs) w;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.animation.Animation#draw(org.abh.common.ui.widget.
   * ModernWidget, java.awt.Graphics2D, java.lang.Object[])
   */
  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {

    int x = (mWidget.getWidth() - mTabs.mTabSize * mTabs.getTabsModel().getTabCount()) / 2; // mTabs.getInsets().left;
    int h = mTabs.getInternalRect().getH();
    int n = mTabs.getTabsModel().getTabCount();

    int selectedIndex = mTabs.getTabsModel().getSelectedIndex();

    //
    // Draw the labels
    //

    int textY = ModernWidget.getTextYPosCenter(g2, mTabs.getHeight());

    for (int i = 0; i < n; ++i) {
      g2.setColor(i == selectedIndex ? Color.WHITE : TEXT_COLOR);
      // g2.setFont(selected ? ModernWidget.BOLD_FONT : ModernWidget.FONT);

      String s = mTabs.getTabsModel().getTab(i).getName().toUpperCase().substring(0, 1);

      g2.drawString(s, x + (h - g2.getFontMetrics().stringWidth(s)) / 2, textY);

      x += mTabs.mTabSize;
    }
  }
  
  @Override
  public String getName() {
    return "orb-tabs";
  }
}
