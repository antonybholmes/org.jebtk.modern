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
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

/**
 * The Class FontUtils.
 */
public class FontUtils {

  /** The Constant FONT_UNDERLINE_MAP. */
  private static final Map<TextAttribute, Integer> FONT_UNDERLINE_MAP = new HashMap<TextAttribute, Integer>();

  /** The Constant FONT_STRIKE_MAP. */
  private static final Map<TextAttribute, Boolean> FONT_STRIKE_MAP = new HashMap<TextAttribute, Boolean>();

  static {
    FONT_UNDERLINE_MAP.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);

    FONT_STRIKE_MAP.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
  }

  /**
   * Instantiates a new font utils.
   */
  private FontUtils() {
    // Do nothing
  }

  /**
   * Create a bold version of a font.
   *
   * @param font the font
   * @return the font
   */
  public static Font bold(final Font font) {
    return new Font(font.getFamily(), Font.BOLD, font.getSize());
  }

  /**
   * Create an underlined version of a font.
   *
   * @param font the font
   * @return the font
   */
  public static Font underline(Font font) {
    return font.deriveFont(FONT_UNDERLINE_MAP);
  }

  /**
   * Strikethrough.
   *
   * @param font the font
   * @return the font
   */
  public static Font strikethrough(Font font) {
    return font.deriveFont(FONT_STRIKE_MAP);
  }

  /**
   * Returns a scaled version of a font.
   * 
   * @param font A font.
   * @param zoom A zoom factor. 1 = no scaling, 2 = 200 %, 1.5 = 150%.
   * @return A scaled font.
   */
  public static Font scale(Font font, double zoom) {
    return font.deriveFont((float) (font.getSize() * zoom));
  }

  public static Font size(Font font, int size) {
    return new Font(font.getFamily(), font.getSize(), size);
  }
}
