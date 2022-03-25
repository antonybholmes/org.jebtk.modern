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


import java.awt.Font;
import org.jebtk.core.CSSColor;

import org.jebtk.core.ColorUtils;
import org.jebtk.core.model.KeyStore;
import org.jebtk.core.settings.SettingsService;
import org.jebtk.modern.ModernWidget;

/**
 * The Class ThemeService.
 */
public class MaterialService {

  

  /**
   * The Class ThemeServiceLoader.
   */
  private static class MaterialServiceLoader {

    /** The Constant INSTANCE. */
    private static final MaterialService INSTANCE = new MaterialService();
  }

  /**
   * Gets the single instance of SettingsService.
   *
   * @return single instance of SettingsService
   */
  public static MaterialService getInstance() {
    return MaterialServiceLoader.INSTANCE;
  }

  public static final CSSColor TEXT_COLOR = ThemeService.getInstance().getColors().getTextColor();

  /**
   * The Class MaterialFonts represents standard material fonts that are cached
   * for use across a UI.
   */
  public static class MaterialFonts extends KeyStore<String, Font> {

    /**
     * Instantiates a new material fonts.
     */
    public MaterialFonts() {
      add("text", ThemeService.loadFont("theme.material.fonts.text"));
      add("bold", ThemeService.loadFont("theme.material.fonts.bold"));
      add("title", ThemeService.loadFont("theme.material.fonts.title"));
      add("subtext", ThemeService.loadFont("theme.material.fonts.subtext"));
      add("sub-heading", ThemeService.loadFont("theme.material.fonts.sub-heading"));
    }

    /**
     * Returns the default material text font.
     *
     * @return the font
     */
    public Font text() {
      return get("text");
    }

    public Font bold() {
      return get("bold");
    }

    /**
     * Subtext.
     *
     * @return the font
     */
    public Font subtext() {
      return get("subtext");
    }

    public Font subHeading() {
      return get("sub-heading");
    }
  }

  /**
   * The Class MaterialColors represents material colors that are derived from the
   * current theme and cached so they can be used across a material UI.
   */
  public static class MaterialColors extends KeyStore<String, CSSColor> {

    /**
     * Instantiates a new material colors.
     */
    public MaterialColors() {
      add("ribbon", ThemeService.getInstance().getColors().getTheme32(28));
      add("ribbon-highlight", ColorUtils.getTransparentColor(get("ribbon"), 0.92));
      add("ribbon-selected", ColorUtils.getTransparentColor(get("ribbon"), 0.87));
      add("ribbon-pressed", ColorUtils.getTransparentColor(get("ribbon"), 0.82));

      // ColorUtils.getTransparentColor75(Ribbon.BAR_BACKGROUND);

      // put("button", ColorUtils.getTransparentColor(get("ribbon"), 0.75));

      add("ribbon-menu-font", ThemeService.getInstance().getColors().getGray32(20));
      add("ribbon-menu-font-highlight", ThemeService.getInstance().getColors().getGray32(25));
      add("ribbon-menu-font-selected", ModernWidget.TEXT_COLOR);

      add("card-border", ColorUtils.getTransparentColor25(ModernWidget.LIGHT_LINE_COLOR));

      add("theme-highlight", ThemeService.getInstance().getColors().getTheme32(14));

      add("theme-selected", ThemeService.getInstance().getColors().getTheme32(16));

      add("theme-outline", ThemeService.getInstance().getColors().getTheme32(18));

      add("gray-highlight", ThemeService.getInstance().getColors().getGray32(4));

      add("gray-selected", ThemeService.getInstance().getColors().getGray32(8));

      add("gray-outline", ThemeService.getInstance().getColors().getGray32(15));

      add("dialog.button.fill", CSSColor.CSS_WHITE);

      add("dialog.button.gradient.start", ThemeService.getInstance().getColors().getGray32(2));

      add("dialog.button.gradient.end", ThemeService.getInstance().getColors().getGray32(3));

      add("dialog.button.outline", ThemeService.getInstance().getColors().getGray32(5));

      add("dialog.button.highlight", ThemeService.getInstance().getColors().getGray32(10));

      add("color.dialog.button.gradient.start", ThemeService.getInstance().getColors().getTheme32(16));

      add("color.dialog.button.gradient.end", ThemeService.getInstance().getColors().getTheme32(18));

      add("color.dialog.button.outline", ThemeService.getInstance().getColors().getTheme32(20));

      add("color.dialog.button.highlight", ThemeService.getInstance().getColors().getTheme32(27));

      add("window.background", ThemeService.getInstance().getColors().getGray32(2));

      add("window.background.gradient.start", get("window.background"));

      add("window.background.gradient.end", ThemeService.getInstance().getColors().getGray32(1));

      add("ribbon.separator", ThemeService.getInstance().getColors().getGray(3));

      add("line", ThemeService.getInstance().getColors().getGray32(7));

      add("alt.text", ThemeService.getInstance().getColors().getGray32(20));
    }

    public CSSColor altText() {
      return get("alt.txt");
    }
  }

  public static class IntConstants extends KeyStore<String, Integer> {

    /**
     * Instantiates a new material colors.
     */
    public IntConstants() {
      add("corner-radius", SettingsService.getInstance().getInt("theme.constants.corner-radius"));
    }

    public int cornerRadius() {
      return get("corner-radius");
    }
  }

  /** The m fonts. */
  private final MaterialFonts mFonts = new MaterialFonts();

  /** The m colors. */
  private final MaterialColors mColors = new MaterialColors();

  private final IntConstants mIntConsts = new IntConstants();
  

  /**
   * Fonts.
   *
   * @return the material fonts
   */
  public MaterialFonts getFonts() {
    return mFonts;
  }

  /**
   * Colors.
   *
   * @return the material colors
   */
  public MaterialColors getColors() {
    return mColors;
  }

  public IntConstants getInts() {
    return mIntConsts;
  }

  /**
   * CSSColor.
   *
   * @param name the name
   * @return the color
   */
  public CSSColor getColor(String name) {
    return getColors().get(name);
  }

  /**
   * Font.
   *
   * @param name the name
   * @return the font
   */
  public Font getFont(String name) {
    return getFonts().get(name);
  }
}
