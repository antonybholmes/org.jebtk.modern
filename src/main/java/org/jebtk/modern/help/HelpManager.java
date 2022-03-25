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

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.jebtk.core.http.URLUtils;
import org.jebtk.core.settings.Setting;

/**
 * Manage showing help to users.
 * 
 * @author Antony Holmes
 *
 */
public class HelpManager {

  /**
   * The constant LOCAL_HELP_URL.
   */
  public static final File LOCAL_HELP_URL = new File("help/help.html");

  /**
   * Instantiates a new help manager.
   */
  private HelpManager() {
    // do nothing
  }

  /**
   * Launch help.
   *
   * @param file the file
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static void launchHelp(File file) throws IOException {
    URLUtils.launch(file.toURI());
  }

  /**
   * Launch help.
   *
   * @param product the product
   * @throws IOException        Signals that an I/O exception has occurred.
   * @throws URISyntaxException the URI syntax exception
   */
  public static void launchHelp(GuiAppInfo product) throws IOException, URISyntaxException {
    boolean local = true;

    // If the help settings exist, see if we want to use local help or not.
    Setting setting = product.getSetting("help.type");

    if (setting != null) {
      // If the setting is web or remote, use remote help.
      local = setting.getString().equals("local");
    }

    launchHelp(product, local);
  }

  /**
   * Launch help.
   *
   * @param product the product
   * @param local   the local
   * @throws IOException        Signals that an I/O exception has occurred.
   * @throws URISyntaxException the URI syntax exception
   */
  public static void launchHelp(GuiAppInfo product, boolean local) throws IOException, URISyntaxException {
    System.err.println("Help " + local);

    if (local) {
      launchLocalHelp(product);
    } else {
      launchRemoteHelp(product);
    }
  }

  /**
   * Launch local help.
   *
   * @param product the product
   * @throws IOException        Signals that an I/O exception has occurred.
   * @throws URISyntaxException the URI syntax exception
   */
  public static void launchLocalHelp(GuiAppInfo product) throws IOException, URISyntaxException {
    // StringBuilder buffer = new StringBuilder();

    // buffer.append(SettingsService.getInstance().getSetting(product.getName()
    // +
    // ".help.url").getValue());
    // buffer.append(HELP_URL);
    // buffer.append(product);
    // buffer.append("/index.html");

    launchHelp(LOCAL_HELP_URL.toURI());
  }

  /**
   * Launch remote help.
   *
   * @param product the product
   * @throws IOException        Signals that an I/O exception has occurred.
   * @throws URISyntaxException the URI syntax exception
   */
  public static void launchRemoteHelp(GuiAppInfo product) throws IOException, URISyntaxException {
    StringBuilder buffer = new StringBuilder();

    // System.err.println("aha" +
    // SettingsService.getInstance().getString(product.getHelpName() +
    // ".help.url"));

    buffer.append(product.getSetting("help.url").getString());
    // buffer.append("/help.html");

    URL url = new URL(buffer.toString());

    launchHelp(url);
  }

  /**
   * Launch help.
   *
   * @param url the url
   * @throws IOException        Signals that an I/O exception has occurred.
   * @throws URISyntaxException the URI syntax exception
   */
  public static void launchHelp(URL url) throws IOException, URISyntaxException {
    System.err.println("Help URL " + url);

    URLUtils.launch(url.toURI());
  }

  /**
   * Launch help.
   *
   * @param url the url
   * @throws IOException        Signals that an I/O exception has occurred.
   * @throws URISyntaxException the URI syntax exception
   */
  public static void launchHelp(URI url) throws IOException, URISyntaxException {
    URLUtils.launch(url);
  }

}
