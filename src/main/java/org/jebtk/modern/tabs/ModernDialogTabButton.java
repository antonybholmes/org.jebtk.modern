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

import java.awt.Color;
import java.awt.Graphics2D;

import org.jebtk.modern.AssetService;
import org.jebtk.modern.button.ModernCheckButton;
import org.jebtk.modern.dialog.ModernDialogWindow;
import org.jebtk.modern.theme.ThemeService;

// TODO: Auto-generated Javadoc
/**
 * Low height button for small form factor toolbar buttons.
 *
 * @author Antony Holmes
 */
public class ModernDialogTabButton extends ModernCheckButton {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new modern dialog tab button.
   *
   * @param text1 the text1
   */
  public ModernDialogTabButton(String text1) {
    super(text1);

    setBackground(ModernDialogWindow.DIALOG_BACKGROUND);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernComponent#draw(java.awt.Graphics2D)
   */
  public void draw(Graphics2D g2) {
    fill(g2, getBackground(), getRect());

    int top = 0;

    if (isSelected()) {
      fill(g2, Color.WHITE, getRect());
    } else {
      top = 3;
    }

    g2.setColor(ThemeService.getInstance().getColors().getGray(4));
    int x = getWidth() - 1;
    int y = getHeight() - 1;

    g2.drawLine(0, top, 0, y);

    x = getWidth() - 1;
    g2.drawLine(x, top, x, y);

    g2.drawLine(0, top, x, top);

    if (mText1 != null) {
      x = (getWidth() - g2.getFontMetrics().stringWidth(mText1)) / 2;
      y = AssetService.ICON_SIZE_20;
      g2.setColor(getForeground());
      g2.drawString(mText1, x, y);
    }
  }
}
