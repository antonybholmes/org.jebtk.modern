/**
 * Copyright 2016 Antony Holmes
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
package org.jebtk.modern.graphics.colormap;

import java.awt.Graphics2D;
import java.awt.Point;

import org.jebtk.modern.menu.ModernIconMenuItem;

/**
 * The class ColorMapMenuItem.
 */
public class ColorMapMenuItem extends ModernIconMenuItem {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The color map.
   */
  private ColorMap mColorMap;

  /**
   * Instantiates a new color map menu item.
   *
   * @param colorMap the color map
   * @param title    the title
   */
  public ColorMapMenuItem(ColorMap colorMap, String title) {
    super(title);

    mColorMap = colorMap;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.ui.menu.ModernIconMenuItem#drawForegroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    Point p = getStringCenterPlotCoordinates(g2, getRect(), mText1);

    g2.setColor(getForeground());
    g2.drawString(mText1, 32, p.y);

    int x = PADDING;
    int y = (mRect.getH() - 16) / 2;

    double c = 0;
    double inc = (mColorMap.getColorCount() - 1) / 16;

    for (int i = 0; i < 16; ++i) {
      g2.setColor(mColorMap.getColorByIndex((int) c));

      g2.drawLine(x, y, x, y + 16);

      ++x;

      c += inc;
    }

    g2.setColor(DARK_LINE_COLOR);
    g2.drawRect(PADDING, y, 16, 16);
  }

  /**
   * Gets the color map.
   *
   * @return the color map
   */
  public ColorMap getColorMap() {
    return mColorMap;
  }
}