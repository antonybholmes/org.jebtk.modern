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

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import org.jebtk.core.settings.SettingsService;
import org.jebtk.modern.BorderService;
import org.jebtk.modern.panel.ModernPillBorderPanel;
import org.jebtk.modern.text.ModernClipboardTextField;
import org.jebtk.modern.text.ModernTextField;
import org.jebtk.modern.text.ITextProperty;

// TODO: Auto-generated Javadoc
/**
 * The class ModernSearchPanel.
 */
public class ModernInputPanel extends ModernPillBorderPanel implements ITextProperty {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  public static final int BUTTON_SIZE = SettingsService.getInstance().getInt("theme.input-pane.internal-button-size");

  /**
   * The member search field.
   */
  private final ModernTextField mTextField = new ModernClipboardTextField();

  /**
   * Instantiates a new modern search panel.
   *
   * @param window    the window
   * @param text      the text
   * @param delimiter the delimiter
   */
  public ModernInputPanel(String text) {

    // mTextField.setBorder(LEFT_BORDER);
    add(mTextField);

    mTextField.setText(text);

    addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        int b = (getInternalRect().h - BUTTON_SIZE) / 2;
        int l = getHeight() / 2;
        setBorder(BorderService.getInstance().createBorder(b, l, b, l));
      }
    });
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.text.TextProperty#getText()
   */
  public String getText() {
    return mTextField.getText();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.text.TextProperty#setText(java.lang.String)
   */
  @Override
  public void setText(String text) {
    mTextField.setText(text);
  }
}
