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
package org.jebtk.modern.button;

import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.menu.ModernPopupMenu;

// TODO: Auto-generated Javadoc
/**
 * Acts like a flat drop down menu button, but alters the button text.
 * 
 * @author Antony Holmes
 *
 */
public class ModernDropDownMenuComboButton extends ModernDropDownButton {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The class ModernClickEvents.
   */
  private class ModernClickEvents implements ModernClickListener {

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
     * modern .event.ModernClickEvent)
     */
    @Override
    public void clicked(ModernClickEvent e) {
      setText(e.getMessage());
    }

  }

  /**
   * Instantiates a new modern drop down menu combo button.
   *
   * @param text1 the text1
   */
  public ModernDropDownMenuComboButton(String text1) {
    super(text1);

    setup();
  }

  /**
   * Instantiates a new modern drop down menu combo button.
   *
   * @param text1 the text1
   * @param icon  the icon
   * @param menu  the menu
   */
  public ModernDropDownMenuComboButton(String text1, ModernIcon icon, ModernPopupMenu menu) {
    super(text1, icon, menu);

    setup();
  }

  /**
   * Instantiates a new modern drop down menu combo button.
   *
   * @param text1 the text1
   * @param menu  the menu
   */
  public ModernDropDownMenuComboButton(String text1, ModernPopupMenu menu) {
    super(text1, menu);

    setup();
  }

  /**
   * Instantiates a new modern drop down menu combo button.
   *
   * @param icon the icon
   * @param menu the menu
   */
  public ModernDropDownMenuComboButton(ModernIcon icon, ModernPopupMenu menu) {
    super(icon, menu);

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    addClickListener(new ModernClickEvents());
  }
}
