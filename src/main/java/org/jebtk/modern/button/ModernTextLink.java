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
package org.jebtk.modern.button;

import java.awt.Color;
import java.awt.Graphics2D;

import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.ribbon.Ribbon;

/**
 * A hyperlink style text link.
 * 
 * @author Antony Holmes
 *
 */
public class ModernTextLink extends ModernLinkButton {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  public ModernTextLink(String title) {
    this(title, Ribbon.BAR_BACKGROUND);
  }

  public ModernTextLink(String title, Color color) {
    super(title);

    setForeground(color);

    addStyleClass("url-text-button");
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.button.ModernButton#autoSize()
   */
  @Override
  public void autoSize() {
    if (mIcon == null) {
      UI.setSize(this, ModernWidget.getStringWidth(getFont(), mText1), ModernWidget.getStringHeight(getFont()));
    } else {
      super.autoSize();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.button.ModernButtonWidget#drawBackground(java.awt.
   * Graphics2D)
   */
  // @Override
  // public void drawBackground(Graphics2D g2) {
  // super.drawAnimatedBackground(g2);
  // }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.button.ModernButton#drawText(java.awt.Graphics2D)
   */
  @Override
  public void drawText(Graphics2D g2) {
    if (mText1 != null) {
      int x;

      if (mIcon != null) {
        x = mIcon.getWidth() + DOUBLE_PADDING;
      } else {
        x = (getWidth() - g2.getFontMetrics().stringWidth(mText1)) / 2;
      }

      int y = getTextYPosCenter(g2, getHeight());

      // boolean underline = mHighlight || isSelected();

      // g2.setFont(FONT); //underline ? UNDER_LINE_FONT : FONT);
      g2.setColor(getForeground());
      g2.drawString(mText1, x, y);

      // I don't line the way underline fonts are rendered so do it
      // manually
      // if (underline) {
      // y += LINE_OFFSET;
      //
      // g2.drawLine(x, y, x + getStringWidth(g2, mText1), y);
      // }
    }
  }
}
