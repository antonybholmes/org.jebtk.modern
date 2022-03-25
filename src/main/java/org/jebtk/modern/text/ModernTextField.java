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
import java.text.ParseException;

import javax.swing.JTextField;

import org.jebtk.core.settings.SettingsService;
import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.theme.ThemeService;

// TODO: Auto-generated Javadoc
/**
 * The class ModernTextField.
 */
public class ModernTextField extends JTextField implements ITextProperty {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant TEXT_BOX_SIZE.
   */
  public static final Dimension TEXT_BOX_SIZE = new Dimension(100, 24);

  /**
   * The constant DISABLED_COLOR.
   */
  public static final Color DISABLED_COLOR = ModernWidget.ALT_TEXT_COLOR; // DialogButton.BORDER_COLOR;

  /**
   * The constant ENABLED_COLOR.
   */
  public static final Color ENABLED_COLOR = ModernWidget.TEXT_COLOR;

  /** The Constant SELECTION_COLOR. */
  public static final Color SELECTION_COLOR = ThemeService.getInstance().getColors().getTheme32(12);

  /**
   * Instantiates a new modern text field.
   */
  public ModernTextField() {
    setup();
  }

  /**
   * Instantiates a new modern text field.
   *
   * @param text the text
   */
  public ModernTextField(String text) {

    super(text);

    setup();
  }

  /**
   * Instantiates a new modern text field.
   *
   * @param text     the text
   * @param editable the editable
   */
  public ModernTextField(String text, boolean editable) {
    this(text);

    setEditable(editable);
  }

  /**
   * Setup.
   */
  private void setup() {

    setAlignmentX(LEFT_ALIGNMENT);

    setFont(ModernWidget.FONT);

    setBackground(SettingsService.getInstance().getColor("theme.background"));
    setForeground(ENABLED_COLOR);
    setSelectedTextColor(Color.WHITE);
    setSelectionColor(SELECTION_COLOR);

    setBorder(ModernComponent.EMPTY_BORDER);

    setMinimumSize(new Dimension(22, TEXT_BOX_SIZE.height));
    setMaximumSize(new Dimension(Short.MAX_VALUE, TEXT_BOX_SIZE.height));

    UI.setSize(this, TEXT_BOX_SIZE);
  }

  /**
   * Sets the text.
   *
   * @param value the new text
   */
  public void setText(double value) {
    setText(Double.toString(value));
  }

  /**
   * Sets the text.
   *
   * @param value the new text
   */
  public void setText(int value) {
    setText(Integer.toString(value));
  }

  /**
   * Gets the as double.
   *
   * @return the as double
   * @throws ParseException the parse exception
   */
  public double getDouble() {
    return Double.parseDouble(getText());
  }

  /**
   * Gets the as int.
   *
   * @return the as int
   * @throws ParseException the parse exception
   */
  public int getInt() {
    return Integer.parseInt(getText());
  }
}
