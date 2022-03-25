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

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;

import org.jebtk.core.text.TextUtils;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;

/**
 * The class ModernCheckBox.
 */
public abstract class CheckBox extends ModernTwoStateWidget {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;


  /**
   * Instantiates a new modern check box.
   */
  public CheckBox() {
    this(TextUtils.EMPTY_STRING);
  }

  /**
   * Instantiates a new modern check box.
   *
   * @param text the text
   */
  public CheckBox(String text) {
    this(text, false);
  }

  /**
   * Instantiates a new modern check box.
   *
   * @param selected the selected
   */
  public CheckBox(boolean selected) {
    this(TextUtils.EMPTY_STRING, selected, ModernButton.getIconButtonSize());
  }

  /**
   * Instantiates a new modern check box.
   *
   * @param text     the text
   * @param selected the selected
   */
  public CheckBox(String text, boolean selected) {
    this(text, selected, ModernButton.getIconButtonSize(text));
  }

  /**
   * Instantiates a new check box.
   *
   * @param text  the text
   * @param width the width
   */
  public CheckBox(String text, int width) {
    this(text, new Dimension(width, ModernWidget.WIDGET_HEIGHT));
  }

  /**
   * Instantiates a new modern check box.
   *
   * @param text the text
   * @param size the size
   */
  public CheckBox(String text, Dimension size) {
    this(text, false, size);
  }

  /**
   * Instantiates a new modern check box.
   *
   * @param text     the text
   * @param selected the selected
   * @param size     the size
   */
  public CheckBox(String text, boolean selected, Dimension size) {
    setText(text);

    setSelected(selected);

    UI.setSize(this, size);

    // Remove css
    // getDrawStates().clear();
    setCSSMode(false);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.widget.ModernWidget#setFont(java.awt.Font)
   */
  @Override
  public void setFont(Font font) {
    super.setFont(font);

    UI.setSize(this, ModernButton.getIconButtonSize(getFont(), mText1));
  }

  /**
   * Gets the text.
   *
   * @return the text
   */
  @Override
  public String getText() {
    return mText1;
  }

  /**
   * Sets the text.
   *
   * @param text the new text
   */
  @Override
  public void setText(String text) {
    mText1 = text;

    UI.setSize(this, ModernButton.getIconButtonSize(getFont(), mText1));

    setClickMessage(text);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    if (mText1 != null) {
      int x = mInternalRect.getX() + 16 + PADDING;

      g2.setColor(isEnabled() ? TEXT_COLOR : ALT_TEXT_COLOR);
      g2.drawString(mText1, x, getTextYPosCenter(g2, getHeight()));
    }
  }
}
