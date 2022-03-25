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

import java.awt.Color;
import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.core.geom.IntRect;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.FadeAnimation;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.ribbon.Ribbon;
import org.jebtk.modern.tabs.TabEvent;
import org.jebtk.modern.tabs.TabEventAdapter;

/**
 * Allows for fade in/out animation on an element.
 *
 * @author Antony Holmes
 * @param <T>
 */
public class IconTabsVertChangeAnimation extends FadeAnimation {
  private IconVertTabs mTabs;

  /**
   * Instantiates a new state animation.
   *
   * @param ribbon the ribbon
   */
  public IconTabsVertChangeAnimation(ModernWidget tabs) {
    super((IconVertTabs) tabs);

    mTabs = (IconVertTabs) tabs;

    mTabs.getTabsModel().addTabListener(new TabEventAdapter() {
      @Override
      public void tabChanged(TabEvent e) {
        restart();
      }
    });

    setFadeColor("fill", IconTabsVertIconAnimation.ICON_COLOR, Ribbon.BAR_BACKGROUND);

    restart();
  }

  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {

    int selectedIndex = mTabs.getTabsModel().getSelectedIndex();

    if (selectedIndex == -1) {
      return;
    }

    int is = mTabs.getIconSize();
    int ts = mTabs.getTabSize();

    int y = mTabs.getHeight() - mTabs.getInsets().bottom - ts;

    ModernIcon icon = mTabs.getTabsModel().getSelectedTab().getIcon();

    int offset = (mTabs.getTabSize() - mTabs.getIconSize()) / 2;
    // int offset = (mTabs.getInternalRect().getH() - mTabs.getIconSize()) / 2;

    Color color = getFadeColor("fill");

    icon.drawIcon(g2, new IntRect(offset, y - ts * selectedIndex + offset, is, is), new Props().set("color", color));
  }
}
