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

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.jebtk.core.http.URLUtils;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.graphics.icons.ModernIcon;

/**
 * Clickable button that launches a web browser to display a URL.
 * 
 * @author Antony Holmes
 *
 */
public class ModernUrlLinkButton extends ModernLinkButton {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member url.
   */
  private URL mUrl;

  /** The m email. */
  private String mEmail;

  /**
   * Instantiates a new modern url link button.
   *
   * @param title the title
   * @param url   the url
   */
  public ModernUrlLinkButton(String title, URL url) {
    super(title);

    init(url);
  }

  /**
   * Instantiates a new modern url link button.
   *
   * @param title the title
   * @param url   the url
   * @param icon  the icon
   */
  public ModernUrlLinkButton(String title, URL url, ModernIcon icon) {
    super(title, icon);

    init(url);
  }

  /**
   * Inits the.
   *
   * @param url the url
   */
  private void init(URL url) {
    mUrl = url;

    addClickListener((ModernClickEvent e) -> {
      try {
        URLUtils.launch(mUrl);
      } catch (URISyntaxException | IOException e1) {
        e1.printStackTrace();
      }
    });
  }

  /**
   * Instantiates a new modern url link button.
   *
   * @param title the title
   * @param email the email
   */
  public ModernUrlLinkButton(String title, String email) {
    super(title);

    mEmail = email;

    addClickListener((ModernClickEvent e) -> {
      try {
        URLUtils.mailto(mEmail);
      } catch (URISyntaxException | IOException e1) {
        e1.printStackTrace();
      }
    });
  }
}
