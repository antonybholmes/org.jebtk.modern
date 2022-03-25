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
import org.jebtk.modern.animation.TranslateAnimation;
import org.jebtk.modern.button.ButtonPressedAnimation;
import org.jebtk.modern.ribbon.RibbonHighlightTextAnimation;

public class SegmentPressedAnimation extends ButtonPressedAnimation {

  private SegmentTabs mTabs;

  public SegmentPressedAnimation(ModernWidget tabs) {
    super(tabs, RibbonHighlightTextAnimation.HIGHLIGHT_COLOR);

    mTabs = (SegmentTabs) tabs;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.widget.ModernClickWidget#drawBackgroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {
    // System.err.println("hmm " + isRunning() + " " + isPressed());

    if (getFrame() == -1) {
      return;
    }

    int n = mTabs.getTabsModel().getTabCount();

    int selectedIndex = mTabs.getTabsModel().getSelectedIndex();

    if (selectedIndex < 0 || selectedIndex >= n) {
      return;
    }

    double r = TranslateAnimation.BEZ_T[getFrame()]; // /
    // (double)TimerAnimation.AnimationTimer.STEPS;

    int w = mTabs.mTabSize;

    int d = (int) (w * r);

    int x = mTabs.mLeftOffset + selectedIndex * mTabs.mTabSize + (w - d) / 2;

    g2.setColor(mColor);

    g2.fillRect(x, mTabs.getInsets().top, d, mTabs.getInternalRect().getH());
  }
}
