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
package org.jebtk.modern.ribbon;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

import org.jebtk.core.text.TextUtils;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.button.ModernDropDownButton2;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.graphics.icons.RasterIcon;
import org.jebtk.modern.menu.ModernPopupMenu2;

// TODO: Auto-generated Javadoc
/**
 * Drop down button for showing menu items.
 *
 * @author Antony Holmes
 *
 */
public class RibbonLargeDropDownButton2 extends ModernDropDownButton2 implements IRibbonModeProperty {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The m compact icon.
   */
  protected ModernIcon mCompactIcon;

  /**
   * The m mode.
   */
  private RibbonSize mMode;

  /**
   * The m text 2.
   */
  private String mText2;

  /**
   * The m text.
   */
  private String mText;

  /**
   * The m show text.
   */
  private boolean mShowText = true;

  /**
   * The m icon only.
   */
  private boolean mIconOnly = false;

  private final Map<RibbonSize, Integer> mMinSizeMap = new HashMap<>();

  /**
   * Instantiates a new ribbon large drop down menu button2.
   *
   * @param icon the icon
   * @param menu the menu
   */
  public RibbonLargeDropDownButton2(ModernIcon icon, ModernPopupMenu2 menu) {
    super(icon, menu);

    mIconOnly = true;

    setup();
  }

  /**
   * Instantiates a new ribbon large drop down menu button2.
   *
   * @param text1 the text 1
   * @param icon the icon
   * @param menu the menu
   */
  public RibbonLargeDropDownButton2(String text1, ModernIcon icon, ModernPopupMenu2 menu) {
    super(text1, icon, menu);

    mText = text1;

    setup();
  }

  /**
   * Instantiates a new ribbon large drop down button.
   *
   * @param text1 the text 1
   * @param text2 the text 2
   * @param icon the icon
   * @param menu the menu
   */
  public RibbonLargeDropDownButton2(String text1, String text2, ModernIcon icon, ModernPopupMenu2 menu) {
    super(text1, icon, menu);

    mText2 = text2;

    mText = mText1 + " " + mText2;

    setClickMessage(mText);

    setup();
  }

  /**
   * Instantiates a new ribbon large drop down button.
   *
   * @param text1 the text 1
   * @param menu the menu
   */
  public RibbonLargeDropDownButton2(String text1, ModernPopupMenu2 menu) {
    super(text1, menu);

    mText = text1;

    setup();
  }

  /**
   * Instantiates a new ribbon large drop down button.
   *
   * @param menu the menu
   */
  public RibbonLargeDropDownButton2(ModernPopupMenu2 menu) {
    super(TextUtils.EMPTY_STRING, menu);

    mText = TextUtils.EMPTY_STRING;

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    mMinSizeMap.put(RibbonSize.LARGE, Ribbon.MIN_BUTTON_WIDTH);
    mMinSizeMap.put(RibbonSize.COMPACT, Ribbon.MIN_COMPACT_BUTTON_WIDTH);

    if (mIcon != null) {
      mCompactIcon = new RasterIcon(mIcon, Ribbon.COMPACT_ICON_SIZE);
    }

    setSize(RibbonSize.COMPACT);

    addStyleClass("ribbon-button");
    // setAnimations("ribbon-dropdown-button-2");
  }

  /**
   * Sets the show text.
   *
   * @param show the new show text
   */
  @Override
  public void setShowText(boolean show) {
    mShowText = show;

    setSize(mMode);
  }

  /**
   * Gets the show text.
   *
   * @return the show text
   */
  @Override
  public boolean getShowText() {
    return mShowText;
  }

  /**
   * Gets the compact icon.
   *
   * @return the compact icon
   */
  public ModernIcon getCompactIcon() {
    return mCompactIcon;
  }

  /**
   * Gets the disabled mIcon.
   *
   * @return the disabled mIcon
   */
  public ModernIcon getCompactDisabledIcon() {
    return mCompactIcon.getDisabledIcon();
  }

  @Override
  public void setSize(RibbonSize mode) {
    mMode = mode;

    setSize();
  }

  /**
   * Set the minimum width of the button in compact mode.
   *
   * @param width
   * @return
   */
  public RibbonLargeDropDownButton2 setMinWidth(RibbonSize s, int width) {
    mMinSizeMap.put(s, width);

    setSize();

    return this;
  }

  public void setSize() {
    if (mMode == RibbonSize.LARGE) {
      setLargeSize();
    } else {
      setCompactSize();
    }
  }

  public void setLargeSize() {
    int w = DOUBLE_PADDING;
    int h;

    if (mText1 != null) {
      if (mText2 == null || mText1.length() > mText2.length()) {
        w += ModernWidget.getStringWidth(mText1);
      } else {
        w += ModernWidget.getStringWidth(mText2);
      }
    }

    w = Math.max(w, mMinSizeMap.get(RibbonSize.LARGE));

    h = Ribbon.LARGE_BUTTON_HEIGHT;

    UI.setSize(this, w, h);
  }

  /**
   * Should set the size of the button when it is in the compact mode.
   */
  public void setCompactSize() {
    int w = DOUBLE_PADDING;
    int h;

    if (mCompactIcon != null) {
      w += Ribbon.COMPACT_ICON_SIZE;
    }

    w += TRIANGLE_ICON.getWidth();

    if (!mIconOnly && mShowText && mText != null) {
      w += ModernWidget.getStringWidth(mText) + PADDING;
    }

    w = Math.max(w, mMinSizeMap.get(RibbonSize.COMPACT));

    h = Ribbon.COMPACT_BUTTON_HEIGHT;

    UI.setSize(this, w, h);
  }

  /*
   * @Override public void drawBackgroundAA(Graphics2D g2) { if (isEnabled()) { if
   * (isSelected() || mHighlight || mPopupShown) {
   * getWidgetRenderer().drawRibbonButton(g2, mInternalRect, RenderMode.SELECTED);
   * } } }
   */

 /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.button.ModernDropDownButton#drawForegroundAA(java.
   * awt. Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    int x;
    int y;

    if (isEnabled()) {
      g2.setColor(ModernWidget.TEXT_COLOR);
    } else {
      g2.setColor(ModernWidget.ALT_TEXT_COLOR);
    }

    if (mMode == RibbonSize.LARGE) {
      if (mShowText && mText1 != null) {
        y = mRect.getH() - PADDING - ModernWidget.getStringHeight();
        x = (mRect.getW() - g2.getFontMetrics().stringWidth(mText1)) / 2;
        g2.drawString(mText1, x, y);

        if (mText2 != null) {
          y += ModernWidget.getStringHeight();
          x = (mRect.getW() - g2.getFontMetrics().stringWidth(mText2)) / 2;
          g2.drawString(mText2, x, y);
        }

        x = (mRect.getW() - mIcon.getWidth()) / 2;
        y = PADDING;

        if (isEnabled()) {
          mIcon.drawIcon(g2, x, y, 32);
        } else {
          mIcon.getDisabledIcon().drawIcon(g2, x, y, 32);
        }
      } else {
        x = (mRect.getW() - mCompactIcon.getWidth()) / 2;
        y = (mRect.getH() - mCompactIcon.getHeight()) / 2;

        if (isEnabled()) {
          mIcon.drawIcon(g2, x, y, 32);
        } else {
          mIcon.getDisabledIcon().drawIcon(g2, x, y, 32);
        }
      }
    } else {
      // Compact
      x = PADDING;

      if (mCompactIcon != null) {
        y = (getHeight() - mCompactIcon.getHeight()) / 2;

        if (isEnabled()) {
          mCompactIcon.drawIcon(g2, x, y, 24);
        } else {
          mCompactIcon.getDisabledIcon().drawIcon(g2, x, y, 24);
        }

        x += mCompactIcon.getWidth();
      }

      if (!mIconOnly && mShowText && mText != null) {
        x += PADDING;
        y = ModernWidget.getTextYPosCenter(g2, mRect.getH());
        int w = getWidth() - -PADDING - TRIANGLE_ICON.getWidth() - x;

        g2.drawString(truncate(g2, mText, w), x, y);
      }
    }

    if (mMode == RibbonSize.LARGE) {
      x = (getWidth() - TRIANGLE_ICON.getWidth()) / 2;
      y = getHeight() - TRIANGLE_ICON.getHeight() - PADDING;
    } else {
      x = getWidth() - PADDING - TRIANGLE_ICON.getWidth();
      y = (getHeight() - TRIANGLE_ICON.getHeight()) / 2;
    }

    TRIANGLE_ICON.drawIcon(g2, x, y, 16);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.button.ModernButtonWidget#setText(java.lang.String)
   */
  @Override
  public void setText(String text) {
    mText = text;

    super.setText(text);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.button.ModernDropDownButton#fireClicked(org.abh.common. ui.
   * event.ModernClickEvent)
   */
  @Override
  public void fireClicked(ModernClickEvent e) {
    // mText = e.getMessage();

    if (mChangeText) {
      setText(e.getMessage());
    }

    super.fireClicked(e);
  }
}
