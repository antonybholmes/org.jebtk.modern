/**
 * Copyright 2017 Antony Holmes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jebtk.modern.theme;

import java.util.HashMap;
import java.util.Map;
import org.jebtk.core.CSSColor;
import org.jebtk.modern.ColorTheme;

/**
 * The Class ThemeService.
 */
public class MaterialService2 {

  
  /**
   * The Class ThemeServiceLoader.
   */
  private static class MaterialServiceLoader {

    /**
     * The Constant INSTANCE.
     */
    private static final MaterialService2 INSTANCE = new MaterialService2();
  }

  /**
   * Gets the single instance of SettingsService.
   *
   * @return single instance of SettingsService
   */
  public static MaterialService2 getInstance() {
    return MaterialServiceLoader.INSTANCE;
  }

  private ColorTheme mTheme = ColorTheme.BLUE;

  private final MaterialColorGradients mGradients = new MaterialColorGradients();

  private final Map<String, CSSColor> mColors = new HashMap<>();

  private MaterialService2() {
    mTheme = ColorTheme.BLUE;

    mColors.put("window.background", mGradients.get(ColorTheme.GRAY).getColor(1));

    mColors.put("light-line", getGray(3));
    mColors.put("line", getGray(7));
    mColors.put("dark-line", getGray(9));

    mColors.put("text", getGray(31)); // CSSColor.BLACK; //getHighlight32(31);
    mColors.put("alt-text", getGray(28));
    mColors.put("disabled-text", getGray(24));
    
    mColors.put("dialog.button.selected", getGray(20));
  }

  public void setTheme(ColorTheme theme) {
    mTheme = theme;
  }

  public ColorTheme getTheme() {
    return mTheme;
  }

  public MaterialColorGradients getGradients() {
    return mGradients;
  }

  /**
   * Returns the currently themed gradient.
   *
   * @return
   */
  public IMaterialColorGradient getGradient() {
    return getGradient(mTheme);
  }

  public IMaterialColorGradient getGradient(ColorTheme name) {
    return getGradients().get(name);
  }

  public IMaterialColorGradient getGray() {
    return getGradient(ColorTheme.GRAY);
  }

  public CSSColor getGray(int color) {
    return getGray().getColor(color);
  }

  public CSSColor getColor(String name) {
    return mColors.get(name);
  }
  
  public CSSColor getTextColor() {
    return getColor("text");
  }
  
  public CSSColor getAltTextColor() {
    return getColor("alt-text");
  }
}
