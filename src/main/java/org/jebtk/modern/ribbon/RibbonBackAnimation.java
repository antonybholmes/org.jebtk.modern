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

import java.awt.BasicStroke;
import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.HoverFadeAnimation;
import org.jebtk.modern.graphics.ImageUtils;

/**
 * Provides the fade animation for quick access buttons
 */
public class RibbonBackAnimation extends HoverFadeAnimation {

  public static final int HEIGHT = 32;

  /**
   * Instantiates a new quick access animation.
   *
   * @param button the button
   */
  public RibbonBackAnimation(ModernWidget button) {
    super(button);
  }

  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {
    Graphics2D g2Temp = ImageUtils.createAAStrokeGraphics(g2);

    try {
      g2Temp.setStroke(new BasicStroke(2));

      g2Temp.setColor(getFadeColor("fill"));

      int x = ModernWidget.DOUBLE_PADDING;
      int y = (mWidget.getHeight() - HEIGHT) / 2;

      drawIcon(g2Temp, x, y);
    } finally {
      g2Temp.dispose();
    }
  }

  public void drawIcon(Graphics2D g2, int x, int y) {
    g2.setColor(ModernWidget.TEXT_COLOR);

    int x1 = x + ModernWidget.DOUBLE_PADDING;
    int y1 = y + HEIGHT / 2;

    g2.drawLine(x1, y1, x1 + HEIGHT - 2 * ModernWidget.DOUBLE_PADDING, y1);

    g2.drawLine(x1, y1, x1 + ModernWidget.PADDING, y1 - ModernWidget.PADDING);
    g2.drawLine(x1, y1, x1 + ModernWidget.PADDING, y1 + ModernWidget.PADDING);
  }
}
