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

import org.jebtk.core.AppInfo;
import org.jebtk.core.AppVersion;
import org.jebtk.modern.graphics.icons.ModernIcon;

/**
 * Describes a product.
 * 
 * @author Antony Holmes
 *
 */
public class GuiAppInfo extends AppInfo {

  /**
   * The member icon.
   */
  private ModernIcon mIcon;

  public GuiAppInfo(String name) {
    this(name, AppVersion.DEFAULT_VERSION, null, null);
  }

  /**
   * Instantiates a new application information.
   *
   * @param name      the name
   * @param version   the version
   * @param copyright the copyright
   * @param icon      the icon
   */
  public GuiAppInfo(String name, AppVersion version, String copyright, ModernIcon icon) {
    this(name, version, copyright, icon, name.toLowerCase().replaceAll("\\s", "_"), null);
  }

  /**
   * Instantiates a new application information.
   *
   * @param name        the name
   * @param version     the version
   * @param copyright   the copyright
   * @param icon        the icon
   * @param largeIcon   the large icon
   * @param description the description
   */
  public GuiAppInfo(String name, AppVersion version, String copyright, ModernIcon icon, String description) {
    this(name, version, copyright, icon, name.toLowerCase().replaceAll("[\\.\\s]", ""), description);
  }

  /**
   * Instantiates a new application information.
   *
   * @param name        the name
   * @param version     the version
   * @param copyright   the copyright
   * @param icon        the icon
   * @param icon128     the large icon
   * @param helpName    the help name
   * @param description the description
   */
  public GuiAppInfo(String name, AppVersion version, String copyright, ModernIcon icon, String helpName,
      String description) {
    super(name, version, copyright, helpName, description);

    mIcon = icon;
  }

  /**
   * Gets the icon.
   *
   * @return the icon
   */
  public ModernIcon getIcon() {
    return mIcon;
  }
}
