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
package org.jebtk.modern.tabs;

import java.awt.Graphics2D;

import org.jebtk.core.geom.IntRect;
import org.jebtk.modern.AssetService;
import org.jebtk.modern.button.ModernCheckButton;
import org.jebtk.modern.graphics.icons.ModernIcon;

// TODO: Auto-generated Javadoc
/**
 * Low height button for small form factor toolbar buttons.
 *
 * @author Antony Holmes
 */
public class TabButton extends ModernCheckButton {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new tab button.
   *
   * @param text1 the text1
   * @param icon  the icon
   */
  public TabButton(String text1, ModernIcon icon) {
    super(text1, icon);

    setup();
  }

  /**
   * Instantiates a new tab button.
   *
   * @param text1 the text1
   */
  public TabButton(String text1) {
    super(text1);

    setup();
  }

  /**
   * Instantiates a new tab button.
   *
   * @param icon the icon
   */
  public TabButton(ModernIcon icon) {
    super(icon);

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    // font = new Font(getFont().getFamily(), Font.PLAIN, 14);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.button.ModernCheckButton#drawBackground(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawBackground(Graphics2D g2) {
    IntRect rect = new IntRect(0, 0, getWidth(), getHeight());

    if (isSelected()) {
      paintThemeHighlighted(g2, rect);
    } else if (mHighlight) {
      paintThemeHighlightedBorder(g2, rect);
    } else {
      super.drawBackground(g2);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.button.ModernCheckButton#drawForegroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    int iconX = mRect.getX() + PADDING;

    if (this.getIcon() != null) {
      int iconY = (mRect.getH() - AssetService.ICON_SIZE_16) / 2;

      this.getIcon().drawIcon(g2, iconX, iconY, AssetService.ICON_SIZE_16);
    }

    if (mText1 != null) {
      int x;

      if (this.getIcon() != null) {
        x = iconX + AssetService.ICON_SIZE_16 + PADDING;
      } else {
        x = AssetService.ICON_SIZE_16;
      }

      int y = AssetService.ICON_SIZE_20;
      g2.setColor(getForeground());
      g2.drawString(mText1, x, y);
    }
  }
}
