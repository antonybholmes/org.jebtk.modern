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
package org.jebtk.modern.tabs;

import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.button.ModernButton;
import org.jebtk.modern.graphics.icons.CloseVectorIcon;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.graphics.icons.Raster16Icon;

/**
 * Close button for side panels. The cross icon changes color rather than using
 * a highlighted background. This makes it a less obtrusive UI element.
 * 
 * @author Antony Holmes
 *
 */
public class CloseButton extends ModernButton {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The Constant ICON. */
  private static final ModernIcon ICON = new Raster16Icon(new CloseVectorIcon(TEXT_COLOR));

  // private static final ModernIcon HIGHLIGHT_ICON =
  // new Raster16Icon(new
  // CloseVectorIcon(ModernWidget.THEME_SELECTED_BORDER_COLOR));

  /**
   * Instantiates a new close button.
   */
  public CloseButton() {
    super(ICON);

    addStyleClass("pill");
    //setAnimations("circle-fill");

    UI.setSize(this, ModernWidget.WIDGET_HEIGHT - 2);
  }

  /*
   * @Override public void drawBackground(Graphics2D g2) { // do nothing }
   * 
   * @Override public void drawForegroundAA(Graphics2D g2) {
   * 
   * if (mHighlight) { drawIconCentered(g2, HIGHLIGHT_ICON, this); } else {
   * drawIconCentered(g2, ICON, this); } }
   */
}
