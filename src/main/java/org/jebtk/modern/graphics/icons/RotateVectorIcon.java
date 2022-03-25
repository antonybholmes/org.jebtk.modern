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
package org.jebtk.modern.graphics.icons;

import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.modern.graphics.ImageUtils;

/**
 * Rotate an icon by a fixed number of degrees.
 */
public class RotateVectorIcon extends ModernVectorScalableIcon {

  /** The m icon. */
  private ModernVectorScalableIcon mIcon;

  /** The m D. */
  private double mD;

  /**
   * Instantiates a new rotate vector icon.
   *
   * @param icon the icon
   * @param d    the d
   */
  public RotateVectorIcon(ModernVectorScalableIcon icon, int d) {
    mIcon = icon;
    mD = Math.toRadians(d);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.icons.ModernIcon#drawForeground(java.awt.Graphics2D,
   * java.awt.Rectangle)
   */
  @Override
  public void drawIcon(Graphics2D g2, int x, int y, int w, int h, Props props) {
    Graphics2D g2Temp = ImageUtils.clone(g2);

    try {
      g2Temp.translate(x + w / 2, y + h / 2);
      g2Temp.rotate(mD);
      g2Temp.translate(-x - w / 2, -y - h / 2);

      mIcon.drawIcon(g2Temp, x, y, w, h);
    } finally {
      g2Temp.dispose();
    }
  }
}
