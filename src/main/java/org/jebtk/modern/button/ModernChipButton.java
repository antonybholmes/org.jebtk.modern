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

import java.awt.Color;
import java.awt.Graphics2D;

import org.jebtk.modern.UI;
import org.jebtk.modern.theme.ThemeService;

// TODO: Auto-generated Javadoc
/**
 * Low height button for small form factor toolbar buttons.
 *
 * @author Antony Holmes
 */
public class ModernChipButton extends ModernCheckButton {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  public static final Color HIGHLIGHT = ThemeService.getInstance().getColors().getGray32(4);

  public static final Color SELECTED = ThemeService.getInstance().getColors().getGray32(6);

  /**
   * Instantiates a new modern check button.
   *
   * @param text1 the text1
   */
  public ModernChipButton(String text1) {
    this(text1, false);
  }

  /**
   * Instantiates a new modern check button.
   *
   * @param text1    the text1
   * @param selected the selected
   */
  public ModernChipButton(String text1, boolean selected) {
    super(text1, selected);

    UI.setSize(this, ModernButton.getButtonWidth(text1) + 16, ModernButton.getButtonHeight());

    // setAnimations("chip-button");
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    // Rectangle rect = new Rectangle(0, 0, getWidth(), getHeight());

    int x = getHeight() / 2;

    g2.setColor(getForeground());

    g2.drawString(mText1, x, getTextYPosCenter(g2, getHeight()));
  }
}
