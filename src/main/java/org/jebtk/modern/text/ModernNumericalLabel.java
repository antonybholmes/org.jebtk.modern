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
package org.jebtk.modern.text;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

/**
 * Displays text.
 * 
 * @author Antony Holmes
 *
 */
public class ModernNumericalLabel extends ModernLabel {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new modern label.
   *
   * @param text the text
   */
  public ModernNumericalLabel(String text) {
    super(text, TEXT_COLOR);
  }

  /**
   * Instantiates a new modern label.
   *
   * @param text  the text
   * @param color the color
   */
  public ModernNumericalLabel(String text, Color color) {
    super(text, color);
  }

  /**
   * Instantiates a new modern label.
   *
   * @param text the text
   * @param size the size
   */
  public ModernNumericalLabel(String text, Dimension size) {
    super(text, size);
  }

  /**
   * Instantiates a new modern label.
   *
   * @param text  the text
   * @param width the width
   */
  public ModernNumericalLabel(String text, int width) {
    super(text, width);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.text.ModernLabelPanel#drawForegroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    // System.err.println("sdf " + getInsets().top + " " + mInternalRect.getH()
    // + "
    // " + getTextVCenteredYPos(g2, mInternalRect.getH()));
    g2.drawString(mText, getWidth() - getInsets().right - getStringWidth(g2, mText),
        getInsets().top + getTextYPosCenter(g2, mInternalRect));
  }
}
