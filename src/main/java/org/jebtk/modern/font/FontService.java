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
package org.jebtk.modern.font;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.Map;
import org.jebtk.core.Resources;

import org.jebtk.core.collections.DefaultHashMap;
import org.jebtk.core.collections.DefaultHashMapCreator;
import org.jebtk.core.collections.HashMapCreator;
import org.jebtk.core.collections.IterMap;
import org.jebtk.core.path.Path;
import org.jebtk.core.settings.SettingsService;
import org.jebtk.modern.theme.ModernTheme;
import org.slf4j.LoggerFactory;

/**
 * The Class FontService caches fonts.
 */
public class FontService extends ModernTheme {

  /**
   * The Class ThemeServiceLoader.
   */
  private static class FontServiceLoader {

    /** The Constant INSTANCE. */
    private static final FontService INSTANCE = new FontService();
  }

  /**
   * Gets the single instance of SettingsService.
   *
   * @return single instance of SettingsService
   */
  public static FontService getInstance() {
    return FontServiceLoader.INSTANCE;
  }

  /** The m font map. */
  private final Map<String, IterMap<Integer, IterMap<Integer, IterMap<Boolean, IterMap<Boolean, Font>>>>> mFontMap = DefaultHashMap
      .create(new DefaultHashMapCreator<Integer, IterMap<Integer, IterMap<Boolean, IterMap<Boolean, Font>>>>(
          new DefaultHashMapCreator<Integer, IterMap<Boolean, IterMap<Boolean, Font>>>(
              new DefaultHashMapCreator<Boolean, IterMap<Boolean, Font>>(new HashMapCreator<Boolean, Font>()))));

  private FontService() {
    registerFonts();
  }
  
  /**
   * Load a font from the settings.
   * 
   * @param path
   * @return
   */
  public Font loadFont(String path) {
    return loadFont(new Path(path));
  }

  /**
   * Load a font from the settings.
   * 
   * @param path A path to a setting.
   * @return
   */
  public Font loadFont(Path path) {
    Path p = path.append("family");

    String family = SettingsService.getInstance().getString(p);

    p = path.append("size");

    int size = SettingsService.getInstance().getInt(p);

    p = path.append("style");

    boolean bold = SettingsService.getInstance().getString(p).contains("bold");

    // Second way of specifying bold
    p = path.append("bold");
    bold |= SettingsService.getInstance().getBool(p);

    boolean italic = SettingsService.getInstance().getString(p).contains("italic");

    // Second way of specifying italic
    p = path.append("italic");
    italic |= SettingsService.getInstance().getBool(p);

    return loadFont(family, size, bold, italic);
  }

  /**
   * Load font.
   *
   * @param size the size
   * @return the font
   */
  public Font loadFont(int size) {
    return loadFont(SettingsService.getInstance().getString("theme.widget.fonts.text.family"), size);
  }

  /**
   * Load a font.
   *
   * @param family The font family, e.g. Roboto.
   * @param size   The font size.
   * @return The font.
   */
  public Font loadFont(String family, int size) {
    return loadFont(family, size, false);
  }

  /**
   * Load a font.
   *
   * @param family The font family.
   * @param size   The font size.
   * @param bold   Whether the font should be bold.
   * @return The font.
   */
  public Font loadFont(String family, int size, boolean bold) {
    return loadFont(family, size, bold, false);
  }

  /**
   * Load a font by name.
   * 
   * @param family The font family, e.g. Roboto.
   * @param size   The font size.
   * @param bold   Whether font should be bold.
   * @param italic Whether font should be italic.
   * @return The font.
   */
  public Font loadFont(String family, int size, boolean bold, boolean italic) {
    return loadFont(family, size, bold, italic, false);
  }

  /**
   * Load font.
   *
   * @param family    the family
   * @param size      the size
   * @param bold      the bold
   * @param italic    the italic
   * @param underline the underline
   * @return the font
   */
  public Font loadFont(String family, int size, boolean bold, boolean italic, boolean underline) {
    return loadFont(family, size, bold, italic, underline, false);
  }

  /**
   * Load font.
   *
   * @param family       the family
   * @param size         the size
   * @param bold         the bold
   * @param italic       the italic
   * @param underline    the underline
   * @param stikethrough the stikethrough
   * @return the font
   */
  public Font loadFont(String family, int size, boolean bold, boolean italic, boolean underline, boolean stikethrough) {
    int style;

    if (bold) {
      style = Font.BOLD;
    } else {
      style = Font.PLAIN;
    }

    if (italic) {
      style = style | Font.ITALIC;
    }

    return loadFont(family, style, size, underline, stikethrough);
  }

  /**
   * Load font.
   *
   * @param family the family
   * @param style  the style
   * @param size   the size
   * @return the font
   */
  public Font loadFont(String family, int style, int size) {
    return loadFont(family, style, size, false);
  }

  /**
   * Load font.
   *
   * @param family    the family
   * @param style     the style
   * @param size      the size
   * @param underline the underline
   * @return the font
   */
  public Font loadFont(String family, int style, int size, boolean underline) {
    return loadFont(family, style, size, underline, false);
  }

  /**
   * Load font.
   *
   * @param family        The font family, e.g. Roboto.
   * @param style         The font style.
   * @param size          The font size.
   * @param underline     the underline
   * @param strikethrough the strikethrough
   * @return The font.
   */
  public Font loadFont(String family, int style, int size, boolean underline, boolean strikethrough) {
    if (!mFontMap.get(family).get(style).get(size).get(underline).containsKey(strikethrough)) {

      Font f = new Font(family, style, size);

      if (underline) {
        f = FontUtils.underline(f);
      }

      if (strikethrough) {
        f = FontUtils.strikethrough(f);
      }

      mFontMap.get(family).get(style).get(size).get(underline).put(strikethrough, f);
    }

    return mFontMap.get(family).get(style).get(size).get(underline).get(strikethrough);
  }

  
  public static final void registerFonts() {
    try {
      
      for(String font : SettingsService.getInstance().getString("fonts").split(",")) {
        Resources.getInstance().registerFont(font.trim());
      }
      
      
      
      // Resources.getInstance().registerFont("ProductSans-Regular.ttf");
      
      // Resources.getInstance().registerFont("Futura_LT_Book.ttf");
      
      // Resources.getInstance().registerFont("Roboto-Light.ttf");
      // Resources.getInstance().registerFont("Roboto-Regular.ttf");
      // Resources.getInstance().registerFont("Roboto-Medium.ttf");
      // Resources.getInstance().registerFont("Roboto-Bold.ttf");
      
      //Resources.getInstance().registerFont("SourceSansPro-Light.ttf");
      //Resources.getInstance().registerFont("SourceSansPro-Regular.ttf");
      //Resources.getInstance().registerFont("SourceSansPro-SemiBold.ttf");
      
      //Resources.getInstance().registerFont("Inter-Light.ttf");
      //Resources.getInstance().registerFont("Inter-Regular.ttf");
      //Resources.getInstance().registerFont("Inter-SemiBold.ttf");
      
      // Resources.getInstance().registerFont("Raleway-Light.ttf");
      // Resources.getInstance().registerFont("Raleway-Regular.ttf");
      
      // Resources.getInstance().registerFont("NotoSans-Regular.ttf");2202
      
      // Resources.getInstance().registerFont("OpenSans-Regular.ttf");
      // Resources.getInstance().registerFont("OpenSans-Light.ttf");
      // Resources.getInstance().registerFont("OpenSans-SemiBold.ttf");
    } catch (FontFormatException | IOException ex) {
      LoggerFactory.getLogger(FontService.class.getName()).error(ex.getMessage());
    }
  }
}
