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
package org.jebtk.modern.font;

import java.awt.Color;
import java.awt.Font;

import org.jebtk.core.text.SentenceCaseType;
import org.jebtk.modern.window.ModernRibbonWindow;

/**
 * Standardized ribbon menu section for providing basic cut, copy and paste
 * functionality to the currently highlighted control that supports clipboard
 * operations.
 *
 * @author Antony Holmes
 *
 */
public class FontExtRibbonSection extends FontRibbonSection {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The font case button.
   */
  private SentenceCaseButton mFontCaseButton = new SentenceCaseButton();

  /**
   * Instantiates a new font ribbon section.
   *
   * @param parent the parent
   */
  public FontExtRibbonSection(ModernRibbonWindow parent) {
    super(parent);

    add(mFontCaseButton);

    mFontCaseButton.addClickListener(this);
  }

  /**
   * Setup.
   *
   * @param font         the font
   * @param fontColor    the font color
   * @param sentenceCase the sentence case
   */
  public void setup(Font font, Color fontColor, SentenceCaseType sentenceCase) {
    super.setup(font, fontColor);

    mFontCaseButton.setSentenceCase(sentenceCase);
  }

  /**
   * Gets the sentence case.
   *
   * @return the sentence case
   */
  public SentenceCaseType getSentenceCase() {
    return mFontCaseButton.getSentenceCase();
  }
}
