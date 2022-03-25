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
 */
public class OrbTabsHighlightAnimation extends WidgetAnimation {

  protected static final Color ORB_COLOR = ThemeService.getInstance().getColors().getGray(3);

  protected static final Color ORB_COLOR_2 = ThemeService.getInstance().getColors().getGray(5);

  private final OrbTabs mTabs;

  /**
   * Instantiates a new state animation.
   *
   * @param widget
   */
  public OrbTabsHighlightAnimation(ModernWidget widget) {
    super(widget);

    mTabs = (OrbTabs) widget;

    // getFade().setFadeColor("highlight", ORB_COLOR, ORB_COLOR_2);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.animation.Animation#draw(org.abh.common.ui.widget.
   * ModernWidget, java.awt.Graphics2D, java.lang.Object[])
   */
  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {
    int x = (mTabs.getWidth() - mTabs.mTabSize * mTabs.mTabWidths.size()) / 2; // mTabs.getInsets().left;
    int h = mTabs.getInternalRect().getH();
    int n = mTabs.getTabsModel().getTabCount();
    int y = mTabs.getInsets().top;

    int highlighted = mTabs.mHighlight;

    //
    // Draw if highlighted
    //

    /*
     * if (highlighted != selectedIndex && highlighted > -1 && highlighted < n) { x
     * += mTabs.mHighlight * mTabs.mTabSize;
     * 
     * g2.setColor(getFade().getFadeColor("highlight"));
     * 
     * g2.fillOval(x, mTabs.getInsets().top, h, h); }
     */

    for (int i = 0; i < n; ++i) {
      g2.setColor(i == highlighted ? c.getCSSProps().getColor("background-color", props.getInt("frame")) : ORB_COLOR);
      g2.fillOval(x, y, h, h);

      x += mTabs.mTabSize;
    }

    //
    // Draw the outlines
    //

    // getRenderer().buttonOutlinePaint(g2, selectedIndex, y, w, h);

    //
    // Draw the selected tab
    //

    // x = mSegments.mLeftOffset + selectedIndex * mSegments.mTabSize;
    //
    // mSegments.getWidgetRenderer().primaryDialogButtonFillPaint(g2,
    // x,
    // mSegments.getInsets().top,
    // mSegments.mTabSize,
    // h,
    // RenderMode.NONE,
    // false);
    //
    // g2.fillRect(x, mSegments.getInsets().top, mSegments.mTabSize, h);
  }
  
  @Override
  public String getName() {
    return "orb-tabs-highlight";
  }
}
