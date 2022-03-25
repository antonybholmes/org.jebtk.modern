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
package org.jebtk.modern.window;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import org.jebtk.core.ColorUtils;
import org.jebtk.modern.button.ModernButton;

/**
 * A Simple transparent button for splash screens that highlights the button
 * text when the mouse floats over it.
 * 
 * @author Antony Holmes
 *
 */
public class ModernSplashTextButton extends ModernButton {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The Constant COLOR1. */
  private static final Color COLOR1 = ColorUtils.getTransparentColor40(Color.WHITE);

  /** The Constant COLOR2. */
  private static final Color COLOR2 = Color.WHITE;

  /**
   * Instantiates a new modern splash button.
   *
   * @param text1 the text1
   */
  public ModernSplashTextButton(String text1) {
    super(text1);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.widget.ModernWidget#drawBackground(java.awt.Graphics2D)
   */
  public void drawBackground(Graphics2D g2) {
    // Do nothing
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.button.ModernButtonWidget#drawBackground(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    if (mHighlight) {
      g2.setColor(COLOR2);
    } else {
      g2.setColor(COLOR1);
    }

    Point p = centerText(g2, mText1, getWidth() / 2, getHeight() / 2);

    g2.drawString(mText1, p.x, p.y);
  }
}
