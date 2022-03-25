/**
 * Copyright (c) 2016, Antony Holmes
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
package org.jebtk.modern.dialog;

import org.jebtk.modern.button.ModernButton;
import org.jebtk.modern.help.ModernDialogHelpButton;
import org.jebtk.modern.window.ModernWindow;

/**
 * Standardized dialog that provides a help button to link to a URL, such as a
 * wiki, to offer more help.
 */
public class ModernDialogHelpWindow extends ModernDialogTaskWindow {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The m help button. */
  protected ModernButton mHelpButton;

  /**
   * Instantiates a new modern dialog help window.
   *
   * @param parent  the parent
   * @param helpUrl the help url
   */
  public ModernDialogHelpWindow(ModernWindow parent, String helpUrl) {
    this(parent, helpUrl, ModernDialogTaskType.OK_CANCEL);
  }

  /**
   * Instantiates a new modern dialog help window.
   *
   * @param parent  the parent
   * @param helpUrl the help url
   * @param type    the type
   */
  public ModernDialogHelpWindow(ModernWindow parent, String helpUrl, ModernDialogTaskType type) {
    super(parent, type);

    mHelpButton = new ModernDialogHelpButton(helpUrl);

    // If the layout is unspecified, do not add the help button
    if (type != ModernDialogTaskType.NONE) {
      getButtonBar().addLeft(mHelpButton);
    }
  }

  /**
   * Instantiates a new modern dialog help window.
   *
   * @param parent the parent
   * @param type   the type
   */
  public ModernDialogHelpWindow(ModernWindow parent, ModernDialogTaskType type) {
    super(parent, type);
  }
}
