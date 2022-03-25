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

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.geom.Point2D;

import org.jebtk.modern.dataview.ModernData;
import org.jebtk.modern.dataview.ModernDataCellRenderer;

/**
 * The Class ColorMapCellRenderer.
 */
public class ColorMapCellRenderer extends ModernDataCellRenderer {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The m color map. */
  private ColorMap mColorMap;

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {

    int x = 2;

    int w = getWidth() - 4;

    LinearGradientPaint paint = mColorMap.getAnchorColors().toGradientPaint(new Point2D.Float(x, 0),
        new Point2D.Float(x + w, 0));

    g2.setPaint(paint);

    /*
     * g2.fillRoundRect(x, 2, w, getHeight() - 4,
     * MaterialService.getInstance().getInts().cornerRadius();,
     * MaterialService.getInstance().getInts().cornerRadius(););
     */

    g2.fillRect(x, 2, w, getHeight() - 4);

    // g2.setColor(LINE_COLOR);
    // g2.drawRect(x, 2, w, getHeight() - 4);

    /*
     * double c = 0; double inc = (mColorMap.getColorCount() - 1) / (double)w;
     * 
     * for (int i = 0; i < w; ++i) { g2.setColor(mColorMap.getColorByIndex((int)c));
     * 
     * g2.drawLine(x, 2, x, getHeight() - 4);
     * 
     * ++x;
     * 
     * c += inc; }
     */

    // paintDarkOutline(g2, getInternalRect());
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataCellRenderer#
   * getCellRendererComponent(org.abh.lib.ui.modern.dataview.ModernData,
   * java.lang.Object, boolean, boolean, boolean, int, int)
   */
  public final Component getCellRendererComponent(ModernData table, Object value, boolean highlight, boolean isSelected,
      boolean hasFocus, int row, int column) {

    mColorMap = (ColorMap) value;

    return super.getCellRendererComponent(table, value, highlight, isSelected, hasFocus, row, column);
  }

}
