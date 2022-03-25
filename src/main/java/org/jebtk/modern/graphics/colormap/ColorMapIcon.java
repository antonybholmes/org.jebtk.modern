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
import java.awt.LinearGradientPaint;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.graphics.icons.ModernVectorIcon;

/**
 * Displays a color bar.
 * 
 * @author Antony Holmes
 *
 */
public class ColorMapIcon extends ModernVectorIcon {

  /**
   * The member color map.
   */
  private ColorMap mColorMap;

  /**
   * Instantiates a new color map icon.
   *
   * @param colorMap the color map
   */
  public ColorMapIcon(ColorMap colorMap) {
    mColorMap = colorMap;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.ui.icons.ModernIcon#drawForeground(java.awt.Graphics2D,
   * java.awt.Rectangle)
   */
  @Override
  public void drawIcon(Graphics2D g2, int x, int y, int w, int h, Props props) {

    w = w - 2;
    h = h / 3;
    y = (h - h / 3) / 2;

    LinearGradientPaint paint = mColorMap.getAnchorColors().toGradientPaint(new Point2D.Float(x, 0),
        new Point2D.Float(x + w, 0));

    g2.setPaint(paint);
    g2.fillRect(x, y, w, h);

    /*
     * g2.setColor(Color.WHITE); g2.fillRect(x, y, w, h);
     * 
     * 
     * double c = 0; double inc = (mColorMap.getColorCount() - 1) / (double)w;
     * 
     * x = x + 1;
     * 
     * for (int i = 0; i < w; ++i) { g2.setColor(mColorMap.getColorByIndex((int)c));
     * 
     * g2.drawLine(x, y + 1, x, y + h - 2);
     * 
     * c += inc; ++x; }
     */

    ModernWidget.paintDarkOutline(g2, new Rectangle(x, y, w, h));
  }
}