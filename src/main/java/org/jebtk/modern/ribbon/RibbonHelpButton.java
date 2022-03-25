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

import java.io.IOException;
import java.net.URISyntaxException;

import org.jebtk.modern.UI;
import org.jebtk.modern.button.ModernButton;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.help.GuiAppInfo;
import org.jebtk.modern.help.HelpManager;

/**
 * Launches a web browser to a particular web page.
 * 
 * @author Antony Holmes
 *
 */
public class RibbonHelpButton extends QuickAccessButton implements ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member product.
   */
  private GuiAppInfo mProduct;

  /**
   * Instantiates a new ribbon help button2.
   */
  public RibbonHelpButton() {
    super("?");

    setForeground(TEXT_COLOR); // Color.WHITE); // Ribbon.BAR_BACKGROUND); //
    setFont(BOLD_FONT);

    addClickListener(this);

    UI.setSize(this, ModernButton.ICON_ONLY_SIZE);
  }

  /**
   * Sets the help product name.
   *
   * @param product the new help product name
   */
  public void setHelpProductName(GuiAppInfo product) {
    mProduct = product;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  public void clicked(ModernClickEvent e) {
    if (e.getSource().equals(this)) {
      try {
        HelpManager.launchHelp(mProduct);
      } catch (IOException e1) {
        e1.printStackTrace();
      } catch (URISyntaxException e1) {
        e1.printStackTrace();
      }
    }
  }
}
