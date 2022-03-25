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
package org.jebtk.modern.menu;

import java.awt.Dimension;
import java.awt.Graphics2D;

import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.graphics.icons.ModernIcon;

// TODO: Auto-generated Javadoc
/**
 * The class ModernIconMenuItem.
 */
public class ModernIconMenuItem extends ModernMenuItem {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant TEXT_OFFSET.
   */
  public static final int TEXT_OFFSET = 24 + 2 * PADDING;

  /**
   * The constant ICON_BACKGROUND_WIDTH.
   */
  public static final int ICON_BACKGROUND_WIDTH = HEIGHT; // 26;

  /** The m text offset. */
  protected int mTextOffset;

  /**
   * Instantiates a new modern icon menu item.
   *
   * @param text the text
   */
  public ModernIconMenuItem(String text) {
    super(text);

    init();
  }

  /**
   * Instantiates a new modern icon menu item.
   *
   * @param title the title
   * @param icon  the icon
   */
  public ModernIconMenuItem(String title, ModernIcon icon) {
    super(title, icon);

    init();
  }

  /**
   * Instantiates a new modern icon menu item.
   *
   * @param title the title
   * @param icon  the icon
   * @param size  the size
   */
  public ModernIconMenuItem(String title, ModernIcon icon, Dimension size) {
    super(title, icon, size);

    init();
  }

  /**
   * Inits the.
   */
  private void init() {
    int offset = 0;

    if (mIcon != null) {
      offset = Math.max(mIcon.getWidth() + 2 * PADDING, TEXT_OFFSET);
    } else {
      offset = TEXT_OFFSET;
    }

    setTextOffset(offset);
  }

  /**
   * Set the pixel position where the text is drawn on the menu item. By default
   * this is set so that a 16x16 icon can be drawn in the space.
   *
   * @param offset the offset
   * @return the modern icon menu item
   */
  public ModernIconMenuItem setTextOffset(int offset) {
    mTextOffset = offset; // (mIcon != null ? mIcon.getWidth() : 0) + 2 *
    // PADDING;

    repaint();

    return this;
  }

  /*
   * @Override public void drawBackground(Graphics2D g2) { if (isEnabled() &&
   * mHighlight) { paintHighlighted(g2, getRect()); } }
   */

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.menu.ModernMenuItem#drawForegroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    if (mIcon != null) {
      int iconX = (mTextOffset - mIcon.getHeight()) / 2;
      int iconY = (getHeight() - mIcon.getHeight()) / 2;

      mIcon.drawIcon(g2, iconX, iconY, mIcon.getHeight());

      // iconX += mIcon.getHeight() + PADDING;
    }

    if (mDisplayText != null) {
      g2.setColor(getForeground());
      g2.drawString(mDisplayText, mTextOffset, getTextYPosCenter(g2, getHeight()));
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.menu.ModernMenuItem#setDisplayText()
   */
  @Override
  public void setDisplayText() {
    mDisplayText = ModernWidget.truncate(getText(), getWidth() - getHeight());
  }
}