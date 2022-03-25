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

import java.awt.Dimension;
import java.awt.Graphics2D;

import org.jebtk.modern.UI;
import org.jebtk.modern.button.ModernRadioButton;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.theme.DrawUIService;

// TODO: Auto-generated Javadoc
/**
 * Large radio button for selecting from options. Designed to be more graphical
 * than a radio button.
 * 
 * @author Antony Holmes
 *
 */
public class RibbonChoiceButton extends ModernRadioButton {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant SIZE.
   */
  private static final Dimension SIZE = new Dimension(Ribbon.LARGE_BUTTON_HEIGHT, Ribbon.LARGE_BUTTON_HEIGHT);

  /**
   * The member icon.
   */
  private ModernIcon mIcon;

  /**
   * Instantiates a new ribbon choice button2.
   *
   * @param icon         the icon
   * @param clickMessage the click message
   */
  public RibbonChoiceButton(ModernIcon icon, String clickMessage) {
    mIcon = icon;

    addStyleClass("dialog-button");

    setClickMessage(clickMessage);

    UI.setSize(this, SIZE);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.button.ModernRadioButton#drawBackgroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawBackgroundAA(Graphics2D g2) {
    if (isEnabled()) {
      if (isSelected() || mHighlight) {
        // getWidgetRenderer()
        // .drawRibbonButton(g2, mInternalRect, RenderMode.SELECTED);

        DrawUIService.getInstance().getRenderer("button-fill").draw(g2, mInternalRect);
      }
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.button.ModernRadioButton#drawForegroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    int x = (mRect.getW() - Ribbon.COMPACT_ICON_SIZE) / 2;
    int y = (mRect.getH() - Ribbon.COMPACT_ICON_SIZE) / 2;

    if (isEnabled()) {
      mIcon.drawIcon(g2, x, y, Ribbon.COMPACT_ICON_SIZE);
    } else {
      mIcon.getDisabledIcon().drawIcon(g2, x, y, Ribbon.COMPACT_ICON_SIZE);
    }
  }
}
