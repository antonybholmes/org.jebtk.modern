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

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.jebtk.core.http.URLUtils;
import org.jebtk.core.settings.SettingsService;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.graphics.icons.HelpVectorIcon;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.graphics.icons.ModernVectorScalableIcon;
import org.jebtk.modern.menu.ModernIconMenuItem;
import org.jebtk.modern.theme.ThemeService;

/**
 * Provide help in dialogs.
 * 
 * @author Antony Holmes
 *
 */
public class ModernMenuHelpItem extends ModernIconMenuItem {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The Constant SIZE. */
  private static final int SIZE = 20; // ModernWidget.WIDGET_HEIGHT

  /** The Constant ICON. */
  private static final ModernIcon ICON = ModernVectorScalableIcon.createRastorIcon(HelpVectorIcon.class,
      ThemeService.getInstance().getColors().getTheme(5), ThemeService.getInstance().getColors().getTheme(6), SIZE);

  /** The m url. */
  private URL mUrl;

  /**
   * Creates a new dialog help button, referring to the URL specified by the
   * settings url string. The parameter must be a setting name that maps to a URL.
   * It cannot be a URL as a string. To use a URL directory use the URL
   * constructor.
   *
   * @param settingsUrl the settings url
   */
  public ModernMenuHelpItem(String settingsUrl) {
    this("Help...", SettingsService.getInstance().getUrl(settingsUrl));
  }

  /**
   * Instantiates a new modern menu help item.
   *
   * @param name        the name
   * @param settingsUrl the settings url
   */
  public ModernMenuHelpItem(String name, String settingsUrl) {
    this(name, SettingsService.getInstance().getUrl(settingsUrl));
  }

  /**
   * Instantiates a new modern dialog help button.
   *
   * @param name the name
   * @param url  the url
   */
  public ModernMenuHelpItem(String name, URL url) {
    this(name, url, ICON);
  }

  /**
   * Instantiates a new modern menu help item.
   *
   * @param title the title
   * @param url   the url
   * @param icon  the icon
   */
  public ModernMenuHelpItem(String title, URL url, ModernIcon icon) {
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

    addClickListener(new ModernClickListener() {

      @Override
      public void clicked(ModernClickEvent e) {
        try {
          URLUtils.launch(mUrl);
        } catch (URISyntaxException e1) {
          e1.printStackTrace();
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }
    });
  }
}
