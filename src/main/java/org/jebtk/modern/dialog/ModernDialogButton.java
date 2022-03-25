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
package org.jebtk.modern.dialog;

import org.jebtk.modern.button.ModernButton;
import org.jebtk.modern.graphics.icons.ModernIcon;

// TODO: Auto-generated Javadoc
/**
 * A dialog button appears on dialogs and typically represents the OK and Cancel
 * buttons etc.
 *
 * @author Antony Holmes
 *
 */
public class ModernDialogButton extends ModernButton {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  public ModernDialogButton() {
    init();
  }
  
  /**
   * Instantiates a new modern dialog button.
   *
   * @param text1 the text1
   */
  public ModernDialogButton(String text1) {
    super(text1);

    init();
  }

  /**
   * Instantiates a new modern dialog button.
   *
   * @param icon the icon
   */
  public ModernDialogButton(ModernIcon icon) {
    super(icon);

    init();
  }

  /**
   * Instantiates a new modern dialog button.
   *
   * @param text1 the text 1
   * @param icon  the icon
   */
  public ModernDialogButton(String text1, ModernIcon icon) {
    super(text1, icon);

    init();
  }

  public ModernDialogButton(String text1, ModernIcon icon, int width) {
    super(text1, icon, width);

    init();
  }

  private void init() {
    // setFont(BOLD_FONT);

    addStyleClass("dialog-button");
    
    //setOpaque(true);

    // setAnimations("button-fill", "button-outline");
  }

  /*
   * @Override public void drawBackgroundAA(Graphics2D g2) { if (isEnabled()) {
   * getWidgetRenderer().drawDialogButton(g2, mInternalRect, getRenderMode(),
   * hasFocus()); } }
   */
}
