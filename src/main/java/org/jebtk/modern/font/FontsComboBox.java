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

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.combobox.ModernComboBox;
import org.jebtk.modern.menu.ModernIconMenuItem;

/**
 * The class FontsComboBox.
 */
public class FontsComboBox extends ModernComboBox {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant MAX_FILES.
   */
  public static final int MAX_FILES = 10;

  /**
   * The constant MENU_ITEM_SIZE.
   */
  public static final Dimension MENU_ITEM_SIZE = new Dimension(300, WIDGET_HEIGHT);

  /**
   * Instantiates a new fonts combo box.
   */
  public FontsComboBox() {
    setup(MAX_FILES);
  }

  /**
   * Instantiates a new fonts combo box.
   *
   * @param max the max
   */
  public FontsComboBox(int max) {
    setup(max);
  }

  /**
   * Sets the up.
   *
   * @param max the new up
   */
  private void setup(int max) {
    // add some logical fonts first
    // addScrollMenuItem(new ModernIconMenuItem("Monospaced", icon));
    // addScrollMenuItem(new ModernIconMenuItem("Sans-Serif", icon));
    // addScrollMenuItem(new ModernIconMenuItem("Serif", icon));
    // addScrollMenuItem(new ModernMenuSeparator());

    String envfonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

    List<String> fonts = new ArrayList<String>();

    for (String f : envfonts) {
      fonts.add(f);
    }

    Collections.sort(fonts);

    for (String font : fonts) {
      ModernIconMenuItem item = new FontMenuItem(font);

      addScrollMenuItem(item);
    }

    // addBreakLine();

    // addModernMenuItem(new ForwardRequestMenuItem("Other...", null));

    setText("");

    UI.setSize(this, ModernWidget.LARGE_SIZE);
  }
}
