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

import java.awt.Graphics2D;

import org.jebtk.modern.theme.ThemeService;

/**
 * Simulates a hyperlink using a button.
 * 
 * @author Antony Holmes
 *
 */
public class ModernCheckLinkButton extends ModernCheckButton {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new modern check link button.
   *
   * @param title the title
   */
  public ModernCheckLinkButton(String title) {
    super(title);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.button.ModernCheckButton#drawBackground(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawBackground(Graphics2D g2) {
    // fill(g2, getRect(), getBackground());
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
    if (mText1 == null) {
      return;
    }

    boolean underline = mHighlight || isSelected();

    g2.setColor(underline ? ThemeService.getInstance().getColors().getTheme(4) : TEXT_COLOR);
    // g2.setFont(underline ? BOLD_FONT : FONT);

    int x = PADDING;
    int y = getTextYPosCenter(g2, getHeight());

    g2.drawString(mText1, x, y);
  }
}
