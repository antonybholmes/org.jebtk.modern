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
package org.jebtk.modern.ribbon;

import org.jebtk.modern.dialog.ModernDialogButton;
import org.jebtk.modern.graphics.icons.ModernIcon;

/**
 * For adding quick access buttons to the ribbon.
 * 
 * @author Antony Holmes
 *
 */
public class QuickAccessButton extends ModernDialogButton {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new quick access button.
   */
  public QuickAccessButton() {
    //init();
  }

  /**
   * Instantiates a new quick access button.
   *
   * @param icon the icon
   */
  public QuickAccessButton(ModernIcon icon) {
    super(icon);

    //init();
  }

  /**
   * Instantiates a new quick access button.
   *
   * @param text the text
   */
  public QuickAccessButton(String text) {
    super(text);

    //init();
  }

  /**
   * Setup.
   */
  //private void init() {
  //  addStyleClass("quick-access-button");

    // setAnimations("button-fill"); // new QuickAccessAnimation(this));
  //}

  /*
   * @Override public void drawBackground(Graphics2D g2) { if (isEnabled()) { if
   * (mHighlight) { fill(g2, RibbonMenuItem.MENU_SELECTED_COLOR); } } }
   */

}
