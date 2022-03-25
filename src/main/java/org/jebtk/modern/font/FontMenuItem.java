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
package org.jebtk.modern.font;

import java.awt.Font;
import java.awt.Graphics2D;

import org.jebtk.modern.AssetService;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.menu.ModernIconMenuItem;

/**
 * The Class FontMenuItem.
 */
public class FontMenuItem extends ModernIconMenuItem {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The icon. */
  public static ModernIcon ICON = AssetService.getInstance().loadIcon("font", AssetService.ICON_SIZE_16);

  /** The m font. */
  private Font mFont;

  /**
   * Instantiates a new font menu item.
   *
   * @param font the font
   */
  public FontMenuItem(String font) {
    super(font, ICON);

    mFont = new Font(font, Font.PLAIN, ModernWidget.FONT.getSize());
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.menu.ModernIconMenuItem#drawForegroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    Graphics2D g2Temp = (Graphics2D) g2.create();

    g2Temp.setFont(mFont);

    super.drawForegroundAA(g2Temp);

    g2Temp.dispose();
  }
}
