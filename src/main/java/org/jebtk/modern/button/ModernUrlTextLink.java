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
import java.net.URL;

import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.ribbon.Ribbon;
import org.jebtk.modern.theme.ThemeService;

/**
 * A hyperlink style text link.
 * 
 * @author Antony Holmes
 *
 */
public class ModernUrlTextLink extends ModernUrlLinkButton {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The color1. */
  private static Color COLOR1 = Ribbon.BAR_BACKGROUND; // ThemeService.getInstance().getColors().getColorHighlight32(20);

  /** The color2. */
  private static Color COLOR2 = ThemeService.getInstance().getColors().getTheme32(16);

  /** The m color 1. */
  private Color mColor1 = COLOR1;

  /** The m color 2. */
  // private Color mColor2 = COLOR2;

  /**
   * Instantiates a new modern url text link.
   *
   * @param title the title
   * @param url   the url
   */
  public ModernUrlTextLink(String title, URL url) {
    this(title, url, COLOR1, COLOR2);
  }

  public ModernUrlTextLink(String title, URL url, Color color1) {
    this(title, url, color1, color1);
  }

  /**
   * Instantiates a new modern url text link.
   *
   * @param title  the title
   * @param url    the url
   * @param color1 the color 1
   * @param color2 the color 2
   */
  public ModernUrlTextLink(String title, URL url, Color color1, Color color2) {
    super(title, url);

    setLinkColors(color1, color2);

    init();
  }

  /**
   * Instantiates a new modern url text link.
   *
   * @param title the title
   * @param email the email
   */
  public ModernUrlTextLink(String title, String email) {
    this(title, email, COLOR1, COLOR2);
  }

  /**
   * Instantiates a new modern url text link.
   *
   * @param title  the title
   * @param email  the email
   * @param color1 the color 1
   * @param color2 the color 2
   */
  public ModernUrlTextLink(String title, String email, Color color1, Color color2) {
    super(title, email);

    setLinkColors(color1, color2);

    init();
  }

  /**
   * Instantiates a new modern url text link.
   *
   * @param title the title
   * @param url   the url
   * @param icon  the icon
   */
  public ModernUrlTextLink(String title, URL url, ModernIcon icon) {
    super(title, url, icon);

    init();
  }

  private void init() {
    addStyleClass("url-text-button");
  }

  /**
   * Sets the link colors.
   *
   * @param color1 the color 1
   * @param color2 the color 2
   */
  public void setLinkColors(Color color1, Color color2) {
    setForeground(mColor1);

    mColor1 = color1;
    // mColor2 = color2;

    repaint();
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
  @Override
  public void drawBackground(Graphics2D g2) {
    super.drawAnimatedBackground(g2);
  }

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
      g2.setColor(mColor1);
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
