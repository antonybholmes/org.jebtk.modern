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

import org.jebtk.modern.ModernWidget;

/**
 * Displays a color bar.
 * 
 * @author Antony Holmes
 *
 */
public class ColorMapPanel extends ModernWidget {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member color map.
   */
  private ColorMap mColorMap;

  /**
   * Instantiates a new color map panel.
   *
   * @param colorMap the color map
   */
  public ColorMapPanel(ColorMap colorMap) {
    mColorMap = colorMap;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.ui.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    int x = getInsets().left;

    int w = mInternalRect.getW();

    double c = 0;
    double inc = (mColorMap.getColorCount() - 1) / (double) w;

    for (int i = 0; i < w; ++i) {
      g2.setColor(mColorMap.getColorByIndex((int) c));

      g2.drawLine(x, mInternalRect.getY(), x, mInternalRect.getY() + mInternalRect.getH() - 1);

      ++x;

      c += inc;
    }

    paintDarkOutline(g2, getInternalRect());
  }
}