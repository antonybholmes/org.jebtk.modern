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
package org.jebtk.modern.help;

import java.net.URL;

import org.jebtk.core.settings.SettingsService;
import org.jebtk.modern.button.ModernUrlTextLink;

/**
 * Provide help in dialogs.
 * 
 * @author Antony Holmes
 *
 */
public class ModernDialogHelpButton extends ModernUrlTextLink {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Creates a new dialog help button, referring to the URL specified by the
   * settings url string. The parameter must be a setting name that maps to a URL.
   * It cannot be a URL as a string. To use a URL directory use the URL
   * constructor.
   *
   * @param settingsUrl the settings url
   */
  public ModernDialogHelpButton(String settingsUrl) {
    this(SettingsService.getInstance().getUrl(settingsUrl));
  }

  /**
   * Instantiates a new modern dialog help button.
   *
   * @param url the url
   */
  public ModernDialogHelpButton(URL url) {
    super("Help...", url);

    // getBackgroundAnimations().set(new HelpButtonHighlightAnimation(this));
  }

  /*
   * @Override public void autoSize() { UI.setSize(this, 100,
   * getWidgetHeight(getFont())); }
   * 
   * @Override public void drawBackground(Graphics2D g2) {
   * super.drawAnimatedBackground(g2); }
   * 
   * @Override public void drawText(Graphics2D g2) {
   * g2.setColor(Ribbon.BAR_BACKGROUND);
   * 
   * int x = 20 + DOUBLE_PADDING;
   * 
   * g2.drawString(mText1, x, getTextYPosCenter(g2, getHeight())); }
   */
}
