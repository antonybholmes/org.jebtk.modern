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
package org.jebtk.modern.splitpane;

import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.HoverFadeAnimation;

/**
 * Provides the fade animation for quick access buttons
 */
public class ModernHSplitPaneLineAnimation extends HoverFadeAnimation {

  private final ModernHSplitPaneLine mPane;

  /**
   * Instantiates a new quick access animation.
   *
   * @param button the button
   */
  public ModernHSplitPaneLineAnimation(ModernWidget pane) {
    super(pane);

    mPane = (ModernHSplitPaneLine) pane;

    setFadeColor("highlight", ModernWidget.LINE_COLOR);
  }

  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {
    int y = mWidget.getInsets().top;
    int w = mWidget.getInternalRect().getW();
    int x = mWidget.getInsets().left;
    int y2 = mWidget.getHeight() - mWidget.getInsets().bottom;

    g2.setColor(getFadeColor("highlight"));

    for (int i = 0; i < mPane.mDividerLocations.size() - 1; ++i) {
      x += (int) (w * mPane.mDividerLocations.get(i));

      g2.drawLine(x, y, x, y2);
    }
  }
  
  @Override
  public String getName() {
    return "h-split-pane";
  }

}
