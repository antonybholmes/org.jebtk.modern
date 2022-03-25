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

import javax.swing.Box;

import org.jebtk.modern.UI;
import org.jebtk.modern.button.ModernCheckBox;
import org.jebtk.modern.dialog.ModernDialogHelpWindow;
import org.jebtk.modern.dialog.ModernDialogTaskType;
import org.jebtk.modern.panel.HBox;
import org.jebtk.modern.window.ModernWindow;

/**
 * User can enter an integer option value.
 * 
 * @author Antony Holmes
 *
 */
public class ModernQuestionInputDialog extends ModernDialogHelpWindow {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member spinner.
   */
  private ModernCheckBox mCheckQuestion = null;

  /**
   * Instantiates a new modern question input dialog.
   *
   * @param parent   the parent
   * @param title    the title
   * @param question the question
   */
  public ModernQuestionInputDialog(ModernWindow parent, String title, String question) {
    this(parent, title, question, false);
  }

  /**
   * Instantiates a new modern int input dialog.
   *
   * @param parent   the parent
   * @param title    the title
   * @param question the question
   * @param checked  the checked
   */
  public ModernQuestionInputDialog(ModernWindow parent, String title, String question, boolean checked) {
    super(parent, ModernDialogTaskType.OK_CANCEL);

    setTitle(title);

    mCheckQuestion = new ModernCheckBox(question, checked);

    setup();

    createUi();
  }

  /**
   * Instantiates a new modern question input dialog.
   *
   * @param parent   the parent
   * @param title    the title
   * @param question the question
   * @param help     the help
   */
  public ModernQuestionInputDialog(ModernWindow parent, String title, String question, String help) {
    this(parent, title, question, help, false);
  }

  /**
   * Instantiates a new modern question input dialog.
   *
   * @param parent   the parent
   * @param title    the title
   * @param question the question
   * @param help     the help
   * @param checked  the checked
   */
  public ModernQuestionInputDialog(ModernWindow parent, String title, String question, String help, boolean checked) {
    super(parent, help, ModernDialogTaskType.OK_CANCEL);

    setTitle(title);

    mCheckQuestion = new ModernCheckBox(question, checked);

    setup();

    createUi();
  }

  /**
   * Setup.
   */
  private void setup() {
    setSize(400, 160);

    UI.centerWindowToScreen(this);
  }

  /**
   * Creates the ui.
   */
  private void createUi() {
    // this.getWindowContentPanel().add(new JLabel("Change " +
    // getProductDetails().getProductName() + " settings", JLabel.LEFT),
    // BorderLayout.PAGE_START);

    Box box = HBox.create();

    box.add(mCheckQuestion);

    setContent(box);
  }

  /**
   * Gets the value.
   *
   * @return the value
   */
  public boolean getAnswer() {
    return mCheckQuestion.isSelected();
  }
}
