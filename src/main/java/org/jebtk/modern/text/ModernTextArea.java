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

import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;

import org.jebtk.core.stream.Stream;
import org.jebtk.core.text.TextUtils;
import org.jebtk.modern.ModernWidget;

// TODO: Auto-generated Javadoc
/**
 * The class ModernTextArea.
 */
public class ModernTextArea extends JTextArea {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new modern text area.
   */
  public ModernTextArea() {
    this("");
  }

  /**
   * Instantiates a new modern text area.
   *
   * @param text the text
   */
  public ModernTextArea(String text) {
    super(text);

    setFont(ModernWidget.FONT);

    // setBackground(ModernTheme.getInstance().getClass("widget").getColor("background"));

    setSelectedTextColor(ModernWidget.TEXT_COLOR);
    setSelectionColor(ModernTextField.SELECTION_COLOR);

    setBorder(BorderFactory.createEmptyBorder());

    setAlignmentX(LEFT_ALIGNMENT);
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.text.JTextComponent#setEditable(boolean)
   */
  public final void setEditable(boolean editable) {

    super.setEditable(editable);

    if (editable) {
      setForeground(ModernTextField.ENABLED_COLOR);
    } else {
      setForeground(ModernTextField.DISABLED_COLOR);
    }
  }

  /**
   * Returns the lines from a text field.
   *
   * @return the lines
   */
  public List<String> getLines() {
    return TextUtils.fastSplit(getText().trim(), TextUtils.NEW_LINE_DELIMITER);
  }

  /**
   * Sets the text.
   *
   * @param <T>   the generic type
   * @param items the new text
   */
  public <T> void setText(List<T> items) {
    setText(Stream.of(items).asString().join(TextUtils.NEW_LINE_DELIMITER));
  }

  public <T> void setText(String[] items) {
    setText(String.join(TextUtils.NEW_LINE_DELIMITER, items));
  }

  /**
   * Clear.
   */
  public void clear() {
    setText(TextUtils.EMPTY_STRING);
  }
}
