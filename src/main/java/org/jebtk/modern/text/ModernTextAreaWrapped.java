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

// TODO: Auto-generated Javadoc
/**
 * The class ModernTextAreaWrapped.
 */
public class ModernTextAreaWrapped extends ModernClipboardTextArea {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new modern text area wrapped.
   *
   * @param cutEnabled   the cut enabled
   * @param copyEnabled  the copy enabled
   * @param pasteEnabled the paste enabled
   */
  public ModernTextAreaWrapped(boolean cutEnabled, boolean copyEnabled, boolean pasteEnabled) {
    super(cutEnabled, copyEnabled, pasteEnabled);

    setup();
  }

  /**
   * Instantiates a new modern text area wrapped.
   *
   * @param text the text
   */
  public ModernTextAreaWrapped(String text) {

    super(text);

    setup();
  }

  /**
   * Instantiates a new modern text area wrapped.
   *
   * @param text         the text
   * @param cutEnabled   the cut enabled
   * @param copyEnabled  the copy enabled
   * @param pasteEnabled the paste enabled
   */
  public ModernTextAreaWrapped(String text, boolean cutEnabled, boolean copyEnabled, boolean pasteEnabled) {
    super(text, cutEnabled, copyEnabled, pasteEnabled);

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    this.setWrapStyleWord(true);
    this.setLineWrap(true);
  }
}
