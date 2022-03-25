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
import org.jebtk.modern.dialog.ModernDialogTaskWindow;
import org.jebtk.modern.panel.HBox;
import org.jebtk.modern.spinner.ModernCompactSpinner;
import org.jebtk.modern.text.ModernAutoSizeLabel;
import org.jebtk.modern.window.ModernWindow;

/**
 * User can enter an integer option value.
 * 
 * @author Antony Holmes
 *
 */
public class ModernNumericalInputDialog extends ModernDialogTaskWindow {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member spinner.
   */
  private final ModernCompactSpinner mSpinner;

  /**
   * Instantiates a new modern int input dialog.
   *
   * @param parent the parent
   * @param title  the title
   * @param prompt the prompt
   * @param value  the value
   * @param min    the min
   * @param max    the max
   * @param bound  the bound
   */
  public ModernNumericalInputDialog(ModernWindow parent, String title, String prompt, double value, double min,
      double max, boolean bound) {
    super(parent);

    setTitle(title);

    mSpinner = new ModernCompactSpinner(min, max, value);
    mSpinner.setBounded(bound);

    setup();

    createUi(prompt);
  }

  /**
   * Setup.
   */
  private void setup() {
    setSize(400, 180);

    UI.centerWindowToScreen(this);
  }

  /**
   * Creates the ui.
   *
   * @param prompt the prompt
   */
  private final void createUi(String prompt) {
    // this.getWindowContentPanel().add(new JLabel("Change " +
    // getProductDetails().getProductName() + " settings", JLabel.LEFT),
    // BorderLayout.PAGE_START);

    Box box = HBox.create();

    ModernAutoSizeLabel label = new ModernAutoSizeLabel(prompt);

    box.add(label);
    box.add(Box.createHorizontalGlue());
    box.add(mSpinner);

    setCard(box);
  }

  /**
   * Gets the value.
   *
   * @return the value
   */
  public double getValue() {
    return mSpinner.getValue();
  }
}
