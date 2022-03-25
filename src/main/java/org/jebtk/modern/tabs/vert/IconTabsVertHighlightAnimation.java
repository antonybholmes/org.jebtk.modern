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
import org.jebtk.core.geom.IntRect;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.HighlightAnimation;
import org.jebtk.modern.theme.DrawUIService;

/**
 * Allows for fade in/out animation on an element.
 *
 * @author Antony Holmes
 * @param <T>
 */
public class IconTabsVertHighlightAnimation extends HighlightAnimation {

  // protected static final Color HIGHLIGHT_COLOR =
  // ThemeService.getInstance().getColors().getHighlight(3);

  private IconVertTabs mTabs;

  /**
   * Instantiates a new state animation.
   *
   * @param ribbon the ribbon
   */
  public IconTabsVertHighlightAnimation(ModernWidget w) {
    super((IconVertTabs) w);

    mTabs = (IconVertTabs) w;

    setFadeColor("highlight", ColorUtils.getTransparentColor(Color.BLACK, 0.95));
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
    int h = mTabs.getInternalRect().getH();
    // nt is = mTabs.getIconSize();
    int ts = mTabs.getTabSize();

    int y = mTabs.getHeight() - mTabs.getInsets().bottom - ts;

    int highlighted = mTabs.getHighlightIndex();
    int selected = mTabs.getTabsModel().getSelectedIndex();

    //
    // Draw if highlighted
    //

    if (highlighted != -1 && highlighted != selected) {
      // g2.setColor(getFadeColor("highlight"));
      // g2.fillRect(x, y - mTabs.getTabSize() * highlighted, h, h);

      DrawUIService.getInstance().getRenderer("circle-fill").draw(g2, new IntRect(x, y - ts * highlighted, h, h),
          getFadeColor("highlight"));

      /*
       * Graphics2D g2Temp = ImageUtils.createAAStrokeGraphics(g2);
       * 
       * try { g2Temp.setColor(getFade().getFadeColor("highlight")); g2Temp.fillOval(x
       * + mTabs.mTabSize * highlighted, y, h, h); } finally { g2Temp.dispose(); }
       */
    }
  }
}
