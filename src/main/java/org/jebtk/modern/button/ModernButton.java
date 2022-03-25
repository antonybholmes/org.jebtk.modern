/**
 * Copyright (C) 2016, Antony Holmes
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *  1. Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *  3. Neither the name of copyright holder nor the names of its contributors 
 *     may be used to endorse or promote products derived from this software 
 *     without specific prior written permission. 
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 */
package org.jebtk.modern.button;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;

import org.jebtk.core.settings.SettingsService;
import org.jebtk.core.text.TextUtils;
import org.jebtk.modern.UI;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.theme.ModernTheme;

// TODO: Auto-generated Javadoc
/**
 * Concrete implementation of a button which responds to click events.
 *
 * @author Antony Holmes
 */
public class ModernButton extends ModernClickableButtonWidget {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant DEFAULT_SIZE.
   */
  public static final Dimension DEFAULT_SIZE = ModernTheme.loadDimension("theme.button.dimensions.default");

  /**
   * The constant ICON_TEXT_SIZE.
   */
  public static final Dimension ICON_TEXT_SIZE = ModernTheme.loadDimension("theme.button.dimensions.icon-text");

  /**
   * The constant ICON_ONLY_SIZE.
   */
  public static final Dimension ICON_ONLY_SIZE = new Dimension(getButtonHeight(), getButtonHeight());

  /** The Constant SMALL_BUTTON_HEIGHT. */
  public static final int SMALL_BUTTON_HEIGHT = SettingsService.getInstance()
      .getInt("theme.button.small-button.height");

  /** The Constant SMALL_BUTTON_SIZE. */
  public static final Dimension SMALL_BUTTON_SIZE = new Dimension(SMALL_BUTTON_HEIGHT, SMALL_BUTTON_HEIGHT);

  /** The Constant MIN_BUTTON_WIDTH. */
  private static final int MIN_BUTTON_WIDTH = 80;

  /**
   * Instantiates a new modern button.
   */
  public ModernButton() {
    UI.setSize(this, ICON_ONLY_SIZE);

    // init();
  }

  /**
   * Instantiates a new modern button.
   *
   * @param text1 the text1
   */
  public ModernButton(String text1) {
    super(text1);

    // init();
  }

  /**
   * Instantiates a new modern button.
   *
   * @param icon the icon
   */
  public ModernButton(ModernIcon icon) {
    super(icon);

    // init();
  }

  /**
   * Instantiates a new modern button.
   *
   * @param text1 the text1
   * @param icon  the icon
   */
  public ModernButton(String text1, ModernIcon icon) {
    super(text1); // ICON_TEXT_SIZE);

    setIcon(icon);

    // init();
  }

  /**
   * Instantiates a new modern button.
   *
   * @param text1 the text 1
   * @param icon  the icon
   * @param width the width
   */
  public ModernButton(String text1, ModernIcon icon, int width) {
    super(text1, icon);

    UI.setSize(this, new Dimension(width, getButtonHeight()));

    // init();
  }

  /**
   * Instantiates a new modern button.
   *
   * @param text1 the text 1
   * @param icon  the icon
   * @param size  the size
   */
  public ModernButton(String text1, ModernIcon icon, Dimension size) {
    super(text1, icon);

    UI.setSize(this, size); // ICON_TEXT_SIZE);

    // init();
  }

  // private void init() {
  // Add support for CSS style rendering that responds to property changes.
  // getDrawStates().add(DrawUIService.getInstance().getRenderer("css-draw"));
  // getAnimations().add("draw-ui");
  // }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.widget.ModernWidget#autoSize()
   */
  @Override
  public void autoSize() {
    setAspectRatio(NORMAL_RATIO);

    if (mText1 != null && mIcon != null) {
      UI.setSize(this, Math.max(MIN_BUTTON_WIDTH, getButtonWidth(mText1) + mIcon.getWidth()), getButtonHeight());
    } else if (mText1 != null) {
      UI.setSize(this, getButtonWidth(mText1), getButtonHeight());
    } else {
      UI.setSize(this, ICON_ONLY_SIZE);

      if (mIcon != null) {
        setAspectRatio(SQUARE_RATIO);
      }
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    drawIcon(g2);
    drawText(g2);
  }

  /**
   * Draw text.
   *
   * @param g2 the g 2
   */
  public void drawText(Graphics2D g2) {
    if (mText1 != null) {
      int x;

      if (mIcon != null) {
        x = mIcon.getWidth() + DOUBLE_PADDING;
      } else {
        x = (getWidth() - g2.getFontMetrics().stringWidth(mText1)) / 2;
      }

      g2.setColor(getForeground());
      g2.drawString(mText1, x, getTextYPosCenter(g2, getHeight()));
    }
  }

  /**
   * Draw icon.
   *
   * @param g2 the g 2
   */
  public void drawIcon(Graphics2D g2) {
    if (mIcon != null) {
      int iconX;

      if (TextUtils.isNullOrEmpty(mText1)) {
        iconX = (getWidth() - mIcon.getWidth()) / 2;
      } else {
        iconX = PADDING;
      }

      int iconY = (getHeight() - mIcon.getWidth()) / 2;

      if (isEnabled()) {
        mIcon.drawIcon(g2, iconX, iconY, mIcon.getWidth());
      } else {
        getDisabledIcon().drawIcon(g2, iconX, iconY, mIcon.getWidth());
      }
    }
  }

  /**
   * Gets the button size.
   *
   * @param text the text
   * @return the button size
   */
  public static Dimension getButtonSize(String text) {
    return new Dimension(getButtonWidth(text), getButtonHeight());
  }

  /**
   * Gets the icon button size based on the text that will appear in the button.
   *
   * @param text the text
   * @return the icon button size
   */
  public static Dimension getIconButtonSize(String text) {
    return getIconButtonSize(FONT, text);
  }

  /**
   * Gets the icon button size.
   *
   * @param font the font
   * @param text the text
   * @return the icon button size
   */
  public static Dimension getIconButtonSize(Font font, String text) {
    return new Dimension(getButtonWidth(font, text) + WIDGET_HEIGHT, getButtonHeight());
  }

  /**
   * Gets the icon button size.
   *
   * @return the icon button size
   */
  public static Dimension getIconButtonSize() {
    int h = getButtonHeight();

    return new Dimension(h, h);
  }

  /**
   * Returns a reasonable width to use for a text only button, based on the
   * standard graphics context.
   *
   * @param text the text
   * @return the button width
   */
  public static int getButtonWidth(String text) {
    return getButtonWidth(FONT, text);
  }

  /**
   * Gets the button width.
   *
   * @param g2   the g2
   * @param text the text
   * @return the button width
   */
  public static int getButtonWidth(Graphics2D g2, String text) {
    return getButtonWidth(g2.getFont(), text);
  }

  /**
   * Gets the button width.
   *
   * @param font the font
   * @param text the text
   * @return the button width
   */
  public static int getButtonWidth(Font font, String text) {
    return Math.max(MIN_BUTTON_WIDTH, getStringWidth(font, text) + DOUBLE_PADDING); // QUAD_PADDING;
  }

  /**
   * Returns the default height for a button given the current font.
   *
   * @return the button height
   */
  public static int getButtonHeight() {
    return getButtonHeight(getDefaultGraphics());
  }

  /**
   * Gets the button height.
   *
   * @param g2 the g2
   * @return the button height
   */
  public static int getButtonHeight(Graphics2D g2) {
    return getWidgetHeight(g2);
  }
}
