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
import org.jebtk.modern.animation.WidgetAnimation;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.theme.ThemeService;

/**
 * Draw all the icons except the selected.
 *
 * @author Antony Holmes
 * @param <T>
 */
public class IconTabsVertIconAnimation extends WidgetAnimation {

  public static final Color ICON_COLOR = ThemeService.getInstance().getColors().getGray(5);

  private IconVertTabs mTabs;

  /**
   * Instantiates a new state animation.
   *
   * @param ribbon the ribbon
   */
  public IconTabsVertIconAnimation(ModernWidget w) {
    super(w);

    mTabs = (IconVertTabs) w;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.animation.Animation#draw(org.abh.common.ui.widget.
   * ModernWidget, java.awt.Graphics2D, java.lang.Object[])
   */
  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {
    int n = mTabs.getTabsModel().getTabCount();

    int selectedIndex = mTabs.getTabsModel().getSelectedIndex();

    int is = mTabs.getIconSize();
    int ts = mTabs.getTabSize();

    ModernIcon icon;

    int offset = (ts - is) / 2;
    // int xoffset = (mTabs.getHeight() - mTabs.getIconSize()) / 2;

    int y = mTabs.getHeight() - mTabs.getInsets().bottom - ts;

    for (int i = 0; i < n; ++i) {
      if (i != selectedIndex) {
        icon = mTabs.getTabsModel().getTab(i).getIcon();

        int y1 = y + offset;

        Color color = ICON_COLOR;

        icon.drawIcon(g2, new IntRect(offset, y1, is, is), new Props().set("color", color));
      }

      y -= ts;
    }
  }
}
