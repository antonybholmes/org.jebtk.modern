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

import java.awt.Color;

import org.jebtk.modern.graphics.icons.ModernIcon;

// TODO: Auto-generated Javadoc
/**
 * A dialog button appears on dialogs and typically represents the OK and Cancel
 * buttons etc.
 *
 * @author Antony Holmes
 *
 */
public class ModernDialogPrimaryButton extends ModernDialogButton {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new modern dialog button.
   *
   * @param text1 the text1
   */
  public ModernDialogPrimaryButton(String text1) {
    super(text1);

    setup();
  }

  /**
   * Instantiates a new modern dialog button.
   *
   * @param icon the icon
   */
  public ModernDialogPrimaryButton(ModernIcon icon) {
    super(icon);

    setup();
  }

  /**
   * Instantiates a new modern dialog button.
   *
   * @param text1 the text 1
   * @param icon  the icon
   */
  public ModernDialogPrimaryButton(String text1, ModernIcon icon) {
    super(text1, icon);

    setup();
  }

  private void setup() {
    setForeground(Color.WHITE);

    addStyleClass("primary-dialog-button");

    /*
     * public static final Color OUTLINE_COLOR_1 = ThemeService.getInstance()
     * .getColors().getColorHighlight32(24);
     * 
     * public static final Color OUTLINE_COLOR_2 = ThemeService.getInstance()
     * .getColors().getColorHighlight32(28);
     * 
     * public static final Color FILL_1 = ThemeService.getInstance().getColors()
     * .getColorHighlight32(20);
     * 
     * public static final Color FILL_2 = ThemeService.getInstance().getColors()
     * .getColorHighlight32(24);
     * 
     * getKeyFrames().getStyles(0).set("border-color", ThemeService.getInstance()
     * .getColors().getColorHighlight32(24));
     * getKeyFrames().getStyles(0).set("background-color",
     * ThemeService.getInstance().getColors() .getColorHighlight32(20));
     * 
     * getKeyFrames().getStyles(100).set("border-color", ThemeService.getInstance()
     * .getColors().getColorHighlight32(28));
     * getKeyFrames().getStyles(100).set("background-color",
     * ThemeService.getInstance().getColors() .getColorHighlight32(24));
     */

    // getBackgroundAnimations().set("primary-dialog-button");

    // setAnimations("button-fill", "button-outline");
  }

  /*
   * @Override public void setText(String text) {
   * super.setText(text.toUpperCase()); }
   */

  /*
   * @Override public void drawBackgroundAA(Graphics2D g2) { if (isEnabled()) {
   * getWidgetRenderer().drawPrimaryDialogButton(g2, mInternalRect,
   * getRenderMode(), hasFocus());
   * 
   * } }
   */
}
