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

import org.jebtk.modern.button.ModernDropDownIconButton2;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.menu.ModernPopupMenu2;

// TODO: Auto-generated Javadoc
/**
 * Flat button for dialogs.
 *
 * @author Antony Holmes
 *
 */
public class ModernDialogFlatDropDownIconButton2 extends ModernDropDownIconButton2 {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new modern dialog flat button.
   *
   * @param icon the icon
   */
  public ModernDialogFlatDropDownIconButton2(ModernIcon icon) {
    super(icon);

    setup();
  }

  /**
   * Instantiates a new modern dialog flat drop down icon button.
   *
   * @param icon the icon
   * @param menu the menu
   */
  public ModernDialogFlatDropDownIconButton2(ModernIcon icon, ModernPopupMenu2 menu) {
    super(icon, menu);

    setup();
  }

  private void setup() {
    setAnimations(new FlatDropDownButtonAnimation2(this));
  }

  /*
   * @Override public void drawBackgroundAA(Graphics2D g2) { if (isEnabled()) { if
   * (isSelected() || mHighlight || mPopupShown) {
   * getWidgetRenderer().drawButton(g2, mInternalRect, RenderMode.SELECTED,
   * hasFocus()); } else { getWidgetRenderer().drawContentBox(g2, mInternalRect);
   * } } }
   */
}
