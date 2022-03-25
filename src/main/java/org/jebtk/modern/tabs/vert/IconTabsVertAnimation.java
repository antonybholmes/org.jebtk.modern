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

import org.jebtk.core.ColorUtils;
import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.HoverFadeAnimation;
import org.jebtk.modern.tabs.IconTabs;

/**
 * Allows for fade in/out animation on an element.
 *
 * @author Antony Holmes
 * @param <T>
 */
public class IconTabsVertAnimation extends HoverFadeAnimation {

  private static final Color BACKGROUND = ColorUtils.getTransparentColor(Color.BLACK, 0.95); // ThemeService.getInstance().getColors().getHighlight(2);

  private final IconTabs mTabs;

  /**
   * Instantiates a new state animation.
   *
   * @param ribbon the ribbon
   */
  public IconTabsVertAnimation(ModernWidget w) {
    super(w);

    mTabs = (IconTabs) w;

    setFadeColor("highlight", BACKGROUND);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.animation.Animation#draw(org.abh.common.ui.widget.
   * ModernWidget, java.awt.Graphics2D, java.lang.Object[])
   */
  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {

    int x = mTabs.getInsets().left;
    int y = mTabs.getInsets().top;
    int h = mTabs.getInternalRect().getH();

    g2.setColor(getFadeColor("highlight"));
    g2.fillRect(x, y, mTabs.getInternalRect().getW(), h);
  }
  
  @Override
  public String getName() {
    return "icon-tabs-vert";
  }
}
