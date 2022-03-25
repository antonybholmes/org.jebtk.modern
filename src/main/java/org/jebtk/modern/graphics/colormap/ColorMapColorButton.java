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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import org.jebtk.modern.UI;
import org.jebtk.modern.graphics.color.ColorSwatchButton;
import org.jebtk.modern.window.ModernWindow;

/**
 * Allow users to select a color for an object etc.
 * 
 * @author Antony Holmes
 *
 */
public class ColorMapColorButton extends ColorSwatchButton {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The Constant SIZE. */
  private static final Dimension SIZE = new Dimension(15, 15);

  /**
   * Instantiates a new color swatch button.
   *
   * @param parent the parent
   * @param color  the color
   */
  public ColorMapColorButton(ModernWindow parent, Color color) {
    super(parent, color);

    UI.setSize(this, SIZE);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.button.ModernDropDownButton#drawBackground(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawBackgroundAA(Graphics2D g2) {
    GeneralPath s = new GeneralPath();

    int w = getWidth() - 1;
    int h = getHeight() - 1;

    s.moveTo(7, 0);
    s.lineTo(w, 7);
    s.lineTo(w, h);
    s.lineTo(0, h);
    s.lineTo(0, 7);
    s.closePath();

    Color color = mPopup.getSelectedColor();

    g2.setColor(color);
    g2.fill(s);

    if (isSelected() || mHighlight || mPopupShown) {
      g2.setColor(Color.BLACK);
      g2.draw(s);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.button.ModernDropDownButton#drawForegroundAA(java.
   * awt. Graphics2D)
   */
  @Override
  public void drawForeground(Graphics2D g2) {
    // Do nothing
  }
}
