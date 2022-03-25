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
package org.jebtk.modern.contentpane;

import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.HoverFadeAnimation;

/**
 * Provides the fade animation content panes
 */
public class ModernHContentPaneAnimation extends HoverFadeAnimation {

  private ModernHContentPane mPane;

  /**
   * Instantiates a content pane animation
   *
   * @param button the button
   */
  public ModernHContentPaneAnimation(ModernWidget pane) {
    super(pane);

    mPane = (ModernHContentPane) pane;

    // setFadeColor("highlight", ModernWidget.LIGHT_LINE_COLOR,
    // ModernWidget.LINE_COLOR);
    setFadeColor("highlight", ModernWidget.LINE_COLOR);
  }

  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {
    if (mPane.mDividerLocations.size() == 0) {
      return;
    }

    // int w = 8;

    g2.setColor(getFadeColor("highlight"));

    // Draw divider lines
    // g2.setColor(ModernWidget.LINE_COLOR);
    for (int divider : mPane.mDividerLocations) {

      /*
       * GradientPaint paint = new GradientPaint(divider - w, 0,
       * ColorUtils.getTransparentColor100(Color.BLACK), divider, 0,
       * ColorUtils.getTransparentColor80(Color.BLACK));
       * 
       * g2.setPaint(paint); g2.fillRect(divider - w, getInsets().top, w,
       * mInternalRect.getH());
       * 
       * paint = new GradientPaint(divider, 0,
       * ColorUtils.getTransparentColor100(Color.BLACK), divider + w, 0,
       * ColorUtils.getTransparentColor90(Color.BLACK));
       * 
       * g2.setPaint(paint); g2.fillRect(divider, getInsets().top, w,
       * mInternalRect.getH());
       * 
       */

      g2.drawLine(divider, mPane.getInsets().top, divider, mPane.getInternalRect().getH());
    }

    /*
     * System.err.println("divider " + mSelectedDivider);
     * 
     * if (mSelectedDivider != -1) { int divider =
     * dividerLocations.get(mSelectedDivider);
     * 
     * 
     * 
     * g2.setColor(ModernWidget.LINE_COLOR); g2.drawLine(divider, getInsets().top,
     * divider, mInternalRect.getH()); }
     */
  }

}
