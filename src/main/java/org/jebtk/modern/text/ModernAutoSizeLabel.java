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
import java.awt.Font;

import javax.swing.border.Border;

import org.jebtk.core.text.TextUtils;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.font.FontService;

/**
 * Displays text and auto-resizes to fit the text.
 * 
 * @author Antony Holmes
 *
 */
public class ModernAutoSizeLabel extends ModernLabel {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new modern auto size label.
   */
  public ModernAutoSizeLabel() {
    this(TextUtils.EMPTY_STRING);
  }

  /**
   * Instantiates a new modern label.
   *
   * @param text the text
   */
  public ModernAutoSizeLabel(String text) {
    super(text, TEXT_COLOR);
  }

  /**
   * Instantiates a new modern auto size label.
   *
   * @param text the text
   * @param font the font
   */
  public ModernAutoSizeLabel(String text, Font font) {
    super(text, font);
  }

  /**
   * Instantiates a new modern label.
   *
   * @param text  the text
   * @param color the color
   */
  public ModernAutoSizeLabel(String text, Color color) {
    super(text, color);
  }

  /**
   * Instantiates a new modern label.
   *
   * @param text the text
   * @param size the size
   */
  public ModernAutoSizeLabel(String text, Dimension size) {
    super(text, size);
  }

  /**
   * Instantiates a new modern label.
   *
   * @param text  the text
   * @param width the width
   */
  public ModernAutoSizeLabel(String text, int width) {
    super(text, width);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#setFont(java.awt.Font)
   */
  @Override
  public void setFont(Font font) {
    super.setFont(font);

    update();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.text.ModernLabelPanel#setText(java.lang.String)
   */
  @Override
  public void setText(String text) {
    super.setText(text);

    update();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.ModernComponent#setBorder(javax.swing.border.Border)
   */
  @Override
  public void setBorder(Border border) {
    super.setBorder(border);

    update();
  }

  /**
   * Update.
   */
  private void update() {
    if (mText != null) {
      UI.setSize(this, ModernWidget.getStringWidth(getFont(), mText), ModernWidget.getStringHeight(getFont()));

      repaint();
    }
  }

  /**
   * Creates the.
   *
   * @param text the text
   * @return the modern auto size label
   */
  public static ModernAutoSizeLabel create(String text) {
    return new ModernAutoSizeLabel(text);
  }

  /**
   * Creates the.
   *
   * @param text   the text
   * @param family the family
   * @param size   the size
   * @return the modern auto size label
   */
  public static ModernAutoSizeLabel create(String text, String family, int size) {
    return create(text, family, size, false, false);
  }

  /**
   * Creates the.
   *
   * @param text   the text
   * @param family the family
   * @param size   the size
   * @param bold   the bold
   * @param italic the italic
   * @return the modern auto size label
   */
  public static ModernAutoSizeLabel create(String text, String family, int size, boolean bold, boolean italic) {
    return new ModernAutoSizeLabel(text, FontService.getInstance().loadFont(family, size, bold, italic));
  }
}
