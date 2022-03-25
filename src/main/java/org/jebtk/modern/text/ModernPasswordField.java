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

import javax.swing.BorderFactory;
import javax.swing.JPasswordField;

import org.jebtk.core.settings.SettingsService;
import org.jebtk.modern.AssetService;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.theme.ThemeService;

// TODO: Auto-generated Javadoc
/**
 * The class ModernPasswordField.
 */
public class ModernPasswordField extends JPasswordField {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant DISABLED_COLOR.
   */
  public static final Color DISABLED_COLOR = ModernWidget.ALT_TEXT_COLOR;

  /**
   * The constant ENABLED_COLOR.
   */
  public static final Color ENABLED_COLOR = ModernWidget.TEXT_COLOR;

  /**
   * Instantiates a new modern password field.
   */
  public ModernPasswordField() {

    setup();
  }

  /**
   * Instantiates a new modern password field.
   *
   * @param text the text
   */
  public ModernPasswordField(String text) {

    super(text);

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {

    this.setFocusable(true);

    // this.setHighlighter(new
    // DefaultHighlighter.DefaultHighlightPainter(ModernMenuItem.HIGHLIGHT_COLOR));

    // popup.add(menuContainer);

    // popup.pack();

    setFont(ThemeService.loadFont("widget.text"));

    setBackground(SettingsService.getInstance().getColor("theme.background"));
    setForeground(ModernWidget.TEXT_COLOR);
    setSelectedTextColor(Color.WHITE);
    setSelectionColor(ThemeService.getInstance().getColors().getTheme(2));

    setBorder(BorderFactory.createEmptyBorder());

    setMinimumSize(new Dimension(AssetService.ICON_SIZE_24, AssetService.ICON_SIZE_24));
    setMaximumSize(new Dimension(Short.MAX_VALUE, AssetService.ICON_SIZE_24));
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.text.JTextComponent#setEditable(boolean)
   */
  public final void setEditable(boolean editable) {

    super.setEditable(editable);

    this.setForeground(editable ? ENABLED_COLOR : DISABLED_COLOR);
  }
}
