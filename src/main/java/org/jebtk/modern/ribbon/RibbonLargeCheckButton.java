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

import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.button.ModernCheckButton;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.graphics.icons.RasterIcon;

// TODO: Auto-generated Javadoc
/**
 * The class RibbonLargeCheckButton2.
 */
public class RibbonLargeCheckButton extends ModernCheckButton implements IRibbonModeProperty {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The m mode. */
  private RibbonSize mMode;

  /** The m text 2. */
  private String mText2;

  /** The m text. */
  private String mText;

  /** The m compact icon. */
  private ModernIcon mCompactIcon;

  /** The m show text. */
  private boolean mShowText = true;

  /**
   * Instantiates a new ribbon large check button2.
   *
   * @param text1 the text1
   * @param icon  the icon
   */
  public RibbonLargeCheckButton(String text1, ModernIcon icon) {
    super(text1, icon);

    setup();
  }

  /**
   * Instantiates a new ribbon large check button.
   *
   * @param text1 the text 1
   */
  public RibbonLargeCheckButton(String text1) {
    super(text1);

    setup();
  }

  /**
   * Instantiates a new ribbon large check button.
   *
   * @param icon  the icon
   * @param text1 the text 1
   */
  public RibbonLargeCheckButton(ModernIcon icon, String text1) {
    super(icon);

    setup();

    setClickMessage(text1);
  }

  /**
   * Instantiates a new ribbon large check button2.
   *
   * @param text1        the text1
   * @param icon         the icon
   * @param toolTipTitle the tool tip title
   * @param toolTipText  the tool tip text
   */
  public RibbonLargeCheckButton(String text1, ModernIcon icon, String toolTipTitle, String toolTipText) {
    this(text1, icon);

    setToolTip(toolTipTitle, toolTipText);

    setup();
  }

  /**
   * Instantiates a new ribbon button2.
   *
   * @param text1 the text1
   * @param text2 the text 2
   * @param icon  the icon
   */
  public RibbonLargeCheckButton(String text1, String text2, ModernIcon icon) {
    this(text1, text2, icon, false);

    setup();
  }

  /**
   * Instantiates a new ribbon large check button.
   *
   * @param text1    the text 1
   * @param text2    the text 2
   * @param icon     the icon
   * @param selected the selected
   */
  public RibbonLargeCheckButton(String text1, String text2, ModernIcon icon, boolean selected) {
    super(text1, icon);

    mText2 = text2;

    setClickMessage(mText);

    setSelected(selected);

    setup();

    mText = text1 + " " + text2;
  }

  /**
   * Instantiates a new ribbon large check button.
   *
   * @param text1    the text 1
   * @param selected the selected
   */
  public RibbonLargeCheckButton(String text1, boolean selected) {
    this(text1);

    setSelected(selected);
  }

  /**
   * Setup.
   */
  private void setup() {
    mText = mText1;

    if (mIcon != null) {
      mCompactIcon = new RasterIcon(mIcon, 24);
    }

    setSize(RibbonSize.COMPACT);

    addStyleClass("dialog-button");
    // etAnimations("ribbon-button"); // new
    // RibbonButtonHighlightAnimation(this));
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
    if (mCompactIcon != null) {
      return mCompactIcon.getDisabledIcon();
    } else {
      return null;
    }
  }

  /*
   * @Override public void drawBackgroundAA(Graphics2D g2) { if (isEnabled()) { if
   * (isSelected() || mHighlight) { getWidgetRenderer().drawRibbonButton(g2,
   * mInternalRect, RenderMode.SELECTED); } } }
   */

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.button.ModernCheckButton#drawForegroundAA(java.awt.
   * Graphics2D)
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
        x = (getWidth() - g2.getFontMetrics().stringWidth(mText1)) / 2;
        g2.drawString(mText1, x, y);

        if (mText2 != null) {
          y += ModernWidget.getStringHeight();
          x = (getWidth() - g2.getFontMetrics().stringWidth(mText2)) / 2;
          g2.drawString(mText2, x, y);
        }

        x = (mRect.getW() - getIcon().getWidth()) / 2;
        y = PADDING;

        if (isEnabled()) {
          getIcon().drawIcon(g2, x, y, Ribbon.LARGE_ICON_SIZE);
        } else {
          getDisabledIcon().drawIcon(g2, x, y, Ribbon.LARGE_ICON_SIZE);
        }
      } else {
        x = (mRect.getW() - Ribbon.LARGE_ICON_SIZE) / 2;
        y = (mRect.getH() - Ribbon.LARGE_ICON_SIZE) / 2;

        if (isEnabled()) {
          getIcon().drawIcon(g2, x, y, Ribbon.LARGE_ICON_SIZE);
        } else {
          getDisabledIcon().drawIcon(g2, x, y, Ribbon.LARGE_ICON_SIZE);
        }
      }
    } else {
      if (mShowText && mText != null) {
        if (mCompactIcon != null) {
          x = PADDING;

          y = (mRect.getH() - mCompactIcon.getHeight()) / 2;

          if (isEnabled()) {
            mCompactIcon.drawIcon(g2, x, y, Ribbon.COMPACT_ICON_SIZE);
          } else {
            getCompactDisabledIcon().drawIcon(g2, x, y, Ribbon.COMPACT_ICON_SIZE);
          }

          x += mCompactIcon.getWidth();

          x += PADDING;
        } else {
          x = getTextXPosCenter(g2, mText, getWidth());
        }

        y = getTextYPosCenter(g2, getHeight());

        g2.drawString(mText, x, y);
      } else {
        if (mCompactIcon != null) {
          x = (mRect.getW() - Ribbon.COMPACT_ICON_SIZE) / 2;
          y = (mRect.getH() - Ribbon.COMPACT_ICON_SIZE) / 2;

          if (isEnabled()) {
            mCompactIcon.drawIcon(g2, x, y, Ribbon.COMPACT_ICON_SIZE);
          } else {
            getCompactDisabledIcon().drawIcon(g2, x, y, Ribbon.COMPACT_ICON_SIZE);
          }
        }
      }
    }
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
      if (mCompactIcon != null) {
        w += mCompactIcon.getWidth();
      }

      if (mShowText && mText != null) {
        w += ModernWidget.getStringWidth(mText) + PADDING;
      }

      w = Math.max(w, Ribbon.MIN_COMPACT_BUTTON_WIDTH);

      h = Ribbon.COMPACT_BUTTON_HEIGHT;
    }

    UI.setSize(this, w, h);
  }
}
