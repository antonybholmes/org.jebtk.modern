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

import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.HighlightAnimation;
import org.jebtk.modern.ribbon.RibbonChangeAnimation;
import org.jebtk.modern.ribbon.RibbonHighlightTextAnimation;

/**
 * Allows for fade in/out animation on an element.
 *
 * @author Antony Holmes
 * @param <T>
 */
public class SegmentHighlightAnimation extends HighlightAnimation {

  private SegmentTabs mTabs;

  /**
   * Instantiates a new state animation.
   *
   * @param ribbon the ribbon
   */
  public SegmentHighlightAnimation(ModernWidget w) {
    super((SegmentTabs) w);

    mTabs = (SegmentTabs) w;

    setFadeColor("highlight", RibbonHighlightTextAnimation.HIGHLIGHT_COLOR);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.animation.Animation#draw(org.abh.common.ui.widget.
   * ModernWidget, java.awt.Graphics2D, java.lang.Object[])
   */
  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {

    int x = mTabs.mLeftOffset;
    int h = mTabs.getInternalRect().getH();
    int n = mTabs.getTabsModel().getTabCount();

    int selectedIndex = mTabs.getTabsModel().getSelectedIndex();

    int highlighted = mTabs.mHighlight;

    //
    // Draw if highlighted
    //

    if (highlighted != selectedIndex && highlighted > -1 && highlighted < n) {
      x = mTabs.mLeftOffset + mTabs.mHighlight * mTabs.mTabSize;

      g2.setColor(getFadeColor("highlight"));

      // g2.drawRect(x, mSegments.getInsets().top, mSegments.mTabSize, h - 1);
      // g2.fillRect(x, mSegments.getInsets().top, mSegments.mTabSize, h);

      g2.fillRect(x, mTabs.getInsets().top + h - RibbonChangeAnimation.BAR_HEIGHT, mTabs.mTabSize,
          RibbonChangeAnimation.BAR_HEIGHT);

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
}
