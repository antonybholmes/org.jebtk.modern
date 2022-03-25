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
package org.jebtk.modern.input;

import org.jebtk.modern.UI;
import org.jebtk.modern.dialog.ModernDialogTaskWindow;
import org.jebtk.modern.scrollpane.ModernScrollPane;
import org.jebtk.modern.scrollpane.ScrollBarPolicy;
import org.jebtk.modern.text.ModernTextArea;
import org.jebtk.modern.window.ModernWindow;

/**
 * User can enter an integer option value.
 * 
 * @author Antony Holmes
 *
 */
public class ModernTextInputDialog extends ModernDialogTaskWindow {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member text.
   */
  private ModernTextArea mText = new ModernTextArea();

  /**
   * Instantiates a new modern text input dialog.
   *
   * @param parent the parent
   * @param title  the title
   */
  public ModernTextInputDialog(ModernWindow parent, String title) {
    this(parent, title, true);
  }

  /**
   * Instantiates a new modern text input dialog.
   *
   * @param parent   the parent
   * @param title    the title
   * @param editable the editable
   */
  public ModernTextInputDialog(ModernWindow parent, String title, boolean editable) {
    this(parent, title, editable, null);
  }

  /**
   * Instantiates a new modern text input dialog.
   *
   * @param parent   the parent
   * @param title    the title
   * @param editable the editable
   * @param text     the text
   */
  public ModernTextInputDialog(ModernWindow parent, String title, boolean editable, String text) {
    super(parent);

    setTitle(title);

    setup();

    createUi();

    mText.setEditable(editable);

    if (text != null) {
      mText.setText(text);
    }
  }

  /**
   * Setup.
   */
  private void setup() {
    setSize(640, 480);

    setResizable(true);

    UI.centerWindowToScreen(this);
  }

  /**
   * Creates the ui.
   */
  private final void createUi() {
    ModernScrollPane scrollPane = new ModernScrollPane(mText);

    scrollPane.setVerticalScrollBarPolicy(ScrollBarPolicy.ALWAYS);

    setContent(scrollPane);
  }

  /**
   * Gets the text.
   *
   * @return the text
   */
  public String getText() {
    return mText.getText();
  }
}
