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
import java.awt.Color;
import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.HoverFadeAnimation;
import org.jebtk.modern.button.ModernClickWidget;
import org.jebtk.modern.graphics.ImageUtils;
import org.jebtk.modern.theme.ThemeService;

/**
 * Provides the fade animation for quick access buttons
 */
public class RibbonBackMenuAnimation extends HoverFadeAnimation {

  private static final Color MENU_SELECTED_COLOR_2 = ThemeService.getInstance().getColors().getGray32(12);

  /**
   * Instantiates a new quick access animation.
   *
   * @param button the button
   */
  public RibbonBackMenuAnimation(ModernClickWidget button) {
    super(button);

    // setFadeColor("fill", RibbonBackMenuItem.BASE_COLOR, Color.WHITE);
    setFadeColor("fill", RibbonMenuItem.MENU_SELECTED_COLOR, MENU_SELECTED_COLOR_2);
  }

  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {
    Graphics2D g2Temp = ImageUtils.createAAStrokeGraphics(g2);

    try {
      g2Temp.setStroke(new BasicStroke(2));

      g2Temp.setColor(getFadeColor("fill"));

      int x = (mWidget.getWidth() - RibbonBackMenuItem.HEIGHT) / 2;
      int y = (mWidget.getHeight() - RibbonBackMenuItem.HEIGHT) / 2;

      RibbonBackMenuItem.drawIcon(g2Temp, x, y);
    } finally {
      g2Temp.dispose();
    }
  }
}
