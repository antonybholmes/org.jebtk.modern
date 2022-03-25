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
import org.jebtk.core.geom.IntRect;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.HoverFadeAnimation;
import org.jebtk.modern.theme.DrawUIService;
import org.jebtk.modern.theme.MaterialService;

/**
 * Provides the fade animation for quick access buttons
 */
public class RibbonBackHighlightAnimation extends HoverFadeAnimation {

  public static final int HEIGHT = 32;

  /**
   * Instantiates a new quick access animation.
   *
   * @param button the button
   */
  public RibbonBackHighlightAnimation(ModernWidget button) {
    super(button);

    // setFadeColor("fill", RibbonBackMenuItem.BASE_COLOR, Color.WHITE);
    setFadeColor("fill", MaterialService.getInstance().getColor("gray-highlight"));
  }

  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {
    int x = ModernWidget.DOUBLE_PADDING;
    int y = (mWidget.getHeight() - HEIGHT) / 2;

    DrawUIService.getInstance().getRenderer("circle-fill").draw(g2, new IntRect(x, y, HEIGHT, HEIGHT),
        getFadeColor("fill"));
  }
}
