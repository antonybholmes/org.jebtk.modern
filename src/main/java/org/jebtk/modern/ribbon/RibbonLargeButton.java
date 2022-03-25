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

import org.jebtk.core.geom.IntRect;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.button.ModernButton;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.graphics.icons.RasterIcon;

// TODO: Auto-generated Javadoc
/**
 * The class RibbonButton2.
 */
public class RibbonLargeButton extends ModernButton implements IRibbonModeProperty {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The m compact icon. */
  private ModernIcon mCompactIcon = null;

  /** The m text 2. */
  private String mText2 = null;

  /** The m mode. */
  private RibbonSize mMode;

  /** The m text. */
  private String mText = null;

  /** The m show text. */
  private boolean mShowText = true;

  /**
   * Instantiates a new ribbon large button.
   *
   * @param text1 the text 1
   * @param icon  the icon
   */
  public RibbonLargeButton(String text1, ModernIcon icon) {
    super(text1, icon);

    mText = text1;

    setup();
  }

  /**
   * Instantiates a new ribbon large button.
   *
   * @param text1       the text 1
   * @param icon        the icon
   * @param compactIcon the compact icon
   */
  public RibbonLargeButton(String text1, ModernIcon icon, ModernIcon compactIcon) {
    this(text1, icon);

    mCompactIcon = compactIcon;
  }

  /**
   * Instantiates a new ribbon button2.
   *
   * @param text1 the text1
   * @param text2 the text 2
   * @param icon  the icon
   */
  public RibbonLargeButton(String text1, String text2, ModernIcon icon) {
    super(text1, icon);

    mText2 = text2;

    setup();

    mText = text1 + " " + text2;

    setClickMessage(mText);

  }

  /**
   * Instantiates a new ribbon button2.
   *
   * @param icon the icon
   */
  public RibbonLargeButton(ModernIcon icon) {
    super(icon);

    setup();
  }

  /**
   * Instantiates a new ribbon large button.
   *
   * @param text1        the text 1
   * @param icon         the icon
   * @param toolTipTitle the tool tip title
   * @param toolTipText  the tool tip text
   */
  public RibbonLargeButton(String text1, ModernIcon icon, String toolTipTitle, String toolTipText) {
    this(text1, icon);

    setToolTip(toolTipTitle, toolTipText);
  }

  /**
   * Create an icon button with a tooltip.
   *
   * @param icon         the icon
   * @param toolTipTitle the tool tip title
   * @param toolTipText  the tool tip text
   */
  public RibbonLargeButton(ModernIcon icon, String toolTipTitle, String toolTipText) {
    this(icon);

    setToolTip(toolTipTitle, toolTipText);
  }

  /**
   * Instantiates a new ribbon large button.
   *
   * @param text1        the text 1
   * @param text2        the text 2
   * @param icon         the icon
   * @param toolTipTitle the tool tip title
   * @param toolTipText  the tool tip text
   */
  public RibbonLargeButton(String text1, String text2, ModernIcon icon, String toolTipTitle, String toolTipText) {
    this(text1, text2, icon);

    setToolTip(toolTipTitle, toolTipText);
  }

  /**
   * Instantiates a new ribbon large button.
   *
   * @param text1        the text 1
   * @param icon         the icon
   * @param compactIcon  the compact icon
   * @param toolTipTitle the tool tip title
   * @param toolTipText  the tool tip text
   */
  public RibbonLargeButton(String text1, ModernIcon icon, ModernIcon compactIcon, String toolTipTitle,
      String toolTipText) {
    this(text1, icon, compactIcon);

    setToolTip(toolTipTitle, toolTipText);
  }

  /**
   * Setup.
   */
  private void setup() {
    mText = mText1;

    mCompactIcon = new RasterIcon(mIcon, 24);

    setSize(RibbonSize.COMPACT);

    addStyleClass("ribbon-button");
    // setAnimations("button-fill"); // new
    // RibbonButtonHighlightAnimation(this));
  }

  /**
   * Sets the show text.
   *
   * @param show the new show text
   */
  public void setShowText(boolean show) {
    mShowText = show;

    resize();
  }

  /**
   * Gets the show text.
   *
   * @return the show text
   */
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
   * Gets the disabled icon.
   *
   * @return the disabled icon
   */
  public ModernIcon getCompactDisabledIcon() {
    return mCompactIcon.getDisabledIcon();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.ribbon.RibbonModeProperty#setSize(org.abh.common.ui.
   * ribbon. RibbonSize)
   */
  @Override
  public void setSize(RibbonSize mode) {
    mMode = mode;

    resize();
  }

  /**
   * Resize.
   */
  private void resize() {
    int w = DOUBLE_PADDING;
    int h;

    if (mMode == RibbonSize.LARGE) {
      if (mText1 != null) {
        if (mText2 == null || mText1.length() > mText2.length()) {
          w += ModernWidget.getStringWidth(mText1);
        } else {
          w += ModernWidget.getStringWidth(mText2);
        }
      }

      w = Math.max(w, Ribbon.MIN_BUTTON_WIDTH);

      h = Ribbon.LARGE_BUTTON_HEIGHT;
    } else {
      w += mCompactIcon.getWidth();

      if (mShowText && mText != null) {
        w += ModernWidget.getStringWidth(mText) + PADDING;
      }

      w = Math.max(w, Ribbon.MIN_COMPACT_BUTTON_WIDTH);

      h = Ribbon.COMPACT_BUTTON_HEIGHT;
    }

    UI.setSize(this, w, h);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.button.ModernButton#drawForegroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    drawForegroundAA(g2, mMode, isEnabled(), mShowText, mText1, mText2, mText, mIcon, mCompactIcon, mRect);

    /*
     * int x; int y;
     * 
     * if (isEnabled()) { g2.setColor(ModernWidget.TEXT_COLOR); } else {
     * g2.setColor(ModernWidget.ALT_TEXT_COLOR); }
     * 
     * if (mMode == RibbonMode.LARGE) { if (mText1 != null) { y = mRect.getH() -
     * PADDING - ModernWidget.getStringHeight(); x = (getWidth() -
     * g2.getFontMetrics().stringWidth(mText1)) / 2; g2.drawString(mText1, x, y);
     * 
     * if (mText2 != null) { y += ModernWidget.getStringHeight(); x = (getWidth() -
     * g2.getFontMetrics().stringWidth(mText2)) / 2; g2.drawString(mText2, x, y); }
     * 
     * x = (mRect.getW() - getIcon().getWidth()) / 2; y = PADDING;
     * 
     * if (isEnabled()) { getIcon().drawIcon(g2, x, y, 16); } else {
     * getDisabledIcon().drawIcon(g2, x, y, 16); } } else { x = (mRect.getW() -
     * mCompactIcon.getWidth()) / 2; y = (mRect.getH() - mCompactIcon.getHeight()) /
     * 2;
     * 
     * if (isEnabled()) { getIcon().drawIcon(g2, x, y, 16); } else {
     * getDisabledIcon().drawIcon(g2, x, y, 16); } }
     * 
     * 
     * } else { if (mText != null) { x = PADDING; y = (mRect.getH() -
     * mCompactIcon.getHeight()) / 2;
     * 
     * if (isEnabled()) { mCompactIcon.drawIcon(g2, x, y, 16); } else {
     * getCompactDisabledIcon().drawIcon(g2, x, y, 16); }
     * 
     * 
     * x = DOUBLE_PADDING + mCompactIcon.getWidth(); y =
     * ModernWidget.getTextYPosCenter(g2, getHeight());
     * 
     * g2.drawString(mText, x, y); } else { x = (mRect.getW() -
     * mCompactIcon.getWidth()) / 2; y = (mRect.getH() - mCompactIcon.getHeight()) /
     * 2;
     * 
     * if (isEnabled()) { mCompactIcon.drawIcon(g2, x, y, 16); } else {
     * mCompactIcon.getDisabledIcon()).drawIcon(g2, x, y, 16); } } }
     */
  }

  /**
   * Draw foreground AA.
   *
   * @param g2          the g 2
   * @param mode        the mode
   * @param enabled     the enabled
   * @param showText    the show text
   * @param text1       the text 1
   * @param text2       the text 2
   * @param text        the text
   * @param icon        the icon
   * @param compactIcon the compact icon
   * @param rect        the rect
   */
  public static void drawForegroundAA(Graphics2D g2, RibbonSize mode, boolean enabled, boolean showText, String text1,
      String text2, String text, ModernIcon icon, ModernIcon compactIcon, IntRect rect) {
    int x;
    int y;

    if (enabled) {
      g2.setColor(ModernWidget.TEXT_COLOR);
    } else {
      g2.setColor(ModernWidget.ALT_TEXT_COLOR);
    }

    if (mode == RibbonSize.LARGE) {
      if (showText && text1 != null) {
        y = rect.getH() - PADDING - ModernWidget.getStringHeight();
        x = (rect.getW() - g2.getFontMetrics().stringWidth(text1)) / 2;
        g2.drawString(text1, x, y);

        if (text2 != null) {
          y += ModernWidget.getStringHeight();
          x = (rect.getW() - g2.getFontMetrics().stringWidth(text2)) / 2;
          g2.drawString(text2, x, y);
        }

        x = (rect.getW() - Ribbon.LARGE_ICON_SIZE) / 2;
        y = PADDING;

        if (enabled) {
          icon.drawIcon(g2, x, y, Ribbon.LARGE_ICON_SIZE);
        } else {
          icon.getDisabledIcon().drawIcon(g2, x, y, Ribbon.LARGE_ICON_SIZE);
        }
      } else {
        x = (rect.getW() - Ribbon.LARGE_ICON_SIZE) / 2;
        y = (rect.getH() - Ribbon.LARGE_ICON_SIZE) / 2;

        if (enabled) {
          icon.drawIcon(g2, x, y, Ribbon.LARGE_ICON_SIZE);
        } else {
          icon.getDisabledIcon().drawIcon(g2, x, y, Ribbon.LARGE_ICON_SIZE);
        }
      }
    } else {
      if (showText && text != null) {
        x = PADDING;
        y = (rect.getH() - Ribbon.COMPACT_ICON_SIZE) / 2;

        if (enabled) {
          compactIcon.drawIcon(g2, x, y, Ribbon.COMPACT_ICON_SIZE);
        } else {
          compactIcon.getDisabledIcon().drawIcon(g2, x, y, Ribbon.COMPACT_ICON_SIZE);
        }

        x = DOUBLE_PADDING + compactIcon.getWidth();
        y = ModernWidget.getTextYPosCenter(g2, rect.getH());

        g2.drawString(text, x, y);
      } else {
        x = (rect.getW() - Ribbon.COMPACT_ICON_SIZE) / 2;
        y = (rect.getH() - Ribbon.COMPACT_ICON_SIZE) / 2;

        if (enabled) {
          compactIcon.drawIcon(g2, x, y, Ribbon.COMPACT_ICON_SIZE);
        } else {
          compactIcon.getDisabledIcon().drawIcon(g2, x, y, Ribbon.COMPACT_ICON_SIZE);
        }
      }
    }
  }

}
