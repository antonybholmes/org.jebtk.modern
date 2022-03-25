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

import org.jebtk.modern.graphics.icons.ModernIcon;

/**
 * The class ModernCheckRadioButton.
 */
public class ModernCheckRadioButton extends ModernCheckButton {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new modern check radio button.
   *
   * @param name the name
   */
  public ModernCheckRadioButton(String name) {
    super(name);
  }

  /**
   * Instantiates a new modern check radio button.
   *
   * @param name     the name
   * @param selected the selected
   */
  public ModernCheckRadioButton(String name, boolean selected) {
    super(name);

    setSelected(selected);
  }

  /**
   * Instantiates a new modern check radio button.
   *
   * @param icon the icon
   */
  public ModernCheckRadioButton(ModernIcon icon) {
    super(icon);
  }

  /**
   * Instantiates a new modern check radio button.
   *
   * @param name the name
   * @param icon the icon
   */
  public ModernCheckRadioButton(String name, ModernIcon icon) {
    super(name, icon);
  }

  /**
   * Instantiates a new modern check radio button.
   *
   * @param icon         the icon
   * @param tooltipTitle the tooltip title
   * @param toolTipText  the tool tip text
   */
  public ModernCheckRadioButton(ModernIcon icon, String tooltipTitle, String toolTipText) {
    super(icon);

    setToolTip(tooltipTitle, toolTipText);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernTwoStateWidget#toggleSelected()
   */
  @Override
  protected final void toggleSelected() {
    // The radio button can only toggle to the
    // on state
    toggleSelected(true);
  }
}
