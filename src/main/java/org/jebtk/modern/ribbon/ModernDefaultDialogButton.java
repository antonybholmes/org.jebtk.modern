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

import java.awt.Color;
import java.awt.Graphics2D;

import org.jebtk.modern.button.ModernButton;
import org.jebtk.modern.theme.ThemeService;

// TODO: Auto-generated Javadoc
/**
 * The class ModernDefaultDialogButton2.
 */
public class ModernDefaultDialogButton extends ModernButton {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant BUTTON_HIGHLIGHT_COLOR.
   */
  private static final Color BUTTON_HIGHLIGHT_COLOR = ThemeService.getInstance().getColors().getTheme(5);

  /**
   * The constant BUTTON_BACKGROUND_COLOR.
   */
  private static final Color BUTTON_BACKGROUND_COLOR = ThemeService.getInstance().getColors().getTheme(4);

  /**
   * Instantiates a new modern default dialog button2.
   *
   * @param text1 the text1
   */
  public ModernDefaultDialogButton(String text1) {
    super(text1);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.ribbon2.ModernDialogButton2#drawBackground(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawBackground(Graphics2D g2) {
    if (isEnabled()) {
      if (mHighlight) {
        fill(g2, BUTTON_HIGHLIGHT_COLOR, getRect());
      } else {
        fill(g2, BUTTON_BACKGROUND_COLOR, getRect());
      }
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ribbon2.ModernDialogButton2#drawForegroundAA(java.
   * awt. Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    int x = (getWidth() - g2.getFontMetrics().stringWidth(mText1)) / 2;

    g2.setColor(Color.WHITE);

    g2.drawString(mText1, x, getTextYPosCenter(g2, getHeight()));
  }
}
