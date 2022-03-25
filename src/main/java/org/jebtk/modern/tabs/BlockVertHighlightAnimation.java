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
import org.jebtk.modern.theme.MaterialService;

/**
 * Allows for fade in/out animation on an element.
 *
 * @author Antony Holmes
 * @param <T>
 */
public class BlockVertHighlightAnimation extends HighlightAnimation {

  private BlockVertTabs mTabs;

  /**
   * Instantiates a new state animation.
   *
   * @param ribbon the ribbon
   */
  public BlockVertHighlightAnimation(ModernWidget segments) {
    super((BlockVertTabs) segments);

    mTabs = (BlockVertTabs) segments;

    setFadeColor("fill", MaterialService.getInstance().getColor("gray-highlight"));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.animation.Animation#draw(org.abh.common.ui.widget.
   * ModernWidget, java.awt.Graphics2D, java.lang.Object[])
   */
  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {

    int x = mTabs.getInsets().left; // + widget.getWidth() -
    // SegmentChangeAnimation.HEIGHT;
    int y = mTabs.getInsets().top + mTabs.mOffset;

    int n = mTabs.getTabsModel().getTabCount();

    int w = mTabs.getInternalRect().getW();

    //
    // Draw if highlighted
    //

    if (mTabs.mHighlight > -1 && mTabs.mHighlight < n) { // && mTabs.mHighlight
      // != selectedIndex) {
      // g2.setColor(TEXT_TAB_HIGHLIGHT_COLOR);
      // //getRenderer().getHighlightFillColor()); //TEXT_TAB_HIGHLIGHT_COLOR);
      // g2.fillRect(x, y + mHighlight * mTabSize, w, mTabSize);

      g2.setColor(getFadeColor("fill"));
      // g2.fillRect(x, y + mTabs.mHighlight * mTabs.mTabSize,
      // SegmentChangeAnimation.HEIGHT, mTabs.mTabSize);
      g2.fillRect(x, y + mTabs.mHighlight * mTabs.mTabSize, w, mTabs.mTabSize);

      /*
       * Graphics2D g2Temp = ImageUtils.clone(g2);
       * 
       * try { g2Temp.setColor(getFade().getFadeColor("fill"));
       * 
       * g2Temp.translate(x, y + mSegments.mHighlight * mSegments.mTabSize);
       * g2Temp.fill(mSegments.mP); } finally { g2Temp.dispose(); }
       */
    }
  }
}
