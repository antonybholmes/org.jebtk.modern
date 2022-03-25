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
import org.jebtk.modern.animation.TranslateAnimation;
import org.jebtk.modern.button.ButtonPressedAnimation;

/**
 * The Class RibbonMenuAnimation.
 */
public class RibbonPressedAnimation extends ButtonPressedAnimation {

  private final Ribbon mRibbon;
  private int mHighlight = -1;

  /**
   * Instantiates a new ribbon menu animation.
   *
   * @param button the button
   */
  public RibbonPressedAnimation(ModernWidget w) {
    super(w, RibbonHighlightTextAnimation.HIGHLIGHT_COLOR);

    mRibbon = (Ribbon) w;
  }

  @Override
  public void start() {
    // We only want the tab highlighted at the time the mouse is pressed
    // to be animated.
    mHighlight = mRibbon.mHighlightedTab;

    super.start();
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

    int x;
    int tabWidth;

    for (int i = 0; i < mRibbon.mTitles.size(); ++i) {
      x = mRibbon.mTabStartX + mRibbon.mTabStarts.get(i);
      tabWidth = mRibbon.mTabWidths.get(i);

      // the file tab
      // GeneralPath tabShape = mRibbon.createTab(x,
      // Ribbon.Y_OFFSET,
      // tabWidth,
      // Ribbon.TAB_HEIGHT);

      if (i == mHighlight) {
        g2.setColor(mColor);

        // g2.fill(tabShape);

        g2.fillRect(x, Ribbon.Y_OFFSET, tabWidth, Ribbon.TAB_HEIGHT);

        double r = TranslateAnimation.BEZ_T[getFrame()]; // /
        // (double)TimerAnimation.AnimationTimer.STEPS;

        int w = tabWidth;

        int d = (int) (w * r);

        x += (w - d) / 2;

        g2.setColor(mColor);

        g2.fillRect(x, Ribbon.Y_OFFSET, d, Ribbon.TAB_HEIGHT);

        break;
      }
    }
  }
}
