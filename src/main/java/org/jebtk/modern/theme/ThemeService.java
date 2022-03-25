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
package org.jebtk.modern.theme;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jebtk.core.Mathematics;
import org.jebtk.core.model.KeyStore;
import org.jebtk.core.path.Path;
import org.jebtk.core.settings.SettingsService;
import org.jebtk.modern.ColorTheme;
import org.jebtk.core.CSSColor;
import org.jebtk.modern.font.FontService;
import org.jebtk.modern.graphics.colormap.ColorMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class ThemeService.
 */
public class ThemeService extends ModernTheme {

  /**
   * The Class ThemeServiceLoader.
   */
  private static class ThemeServiceLoader {

    /** The Constant INSTANCE. */
    private static final ThemeService INSTANCE = new ThemeService();
  }

  /**
   * Gets the single instance of SettingsService.
   *
   * @return single instance of SettingsService
   */
  public static ThemeService getInstance() {
    return ThemeServiceLoader.INSTANCE;
  }

  /** The Constant LOG. */
  private final static Logger LOG = LoggerFactory.getLogger(ThemeService.class);

  /**
   * The Class ThemeFonts.
   */
  public static class ThemeFonts {

    /**
     * Load font.
     *
     * @param path the path
     * @return the font
     */

    /**
     * Returns the default font used by most widgets.
     *
     * @return the font
     */
    public Font getFont() {
      return loadFont("theme.widget.fonts.text");
    }

    /**
     * Returns the default bold font.
     *
     * @return the bold font
     */
    public Font getBoldFont() {
      return loadFont("theme.widget.fonts.bold-text");
    }

    /**
     * Returns the default title font.
     *
     * @return the title font
     */
    public Font getTitleFont() {
      return loadFont("theme.widget.fonts.title");
    }

    /**
     * Gets the sub title font.
     *
     * @return the sub title font
     */
    public Font getSubTitleFont() {
      return loadFont("theme.widget.fonts.sub-title");
    }

    /**
     * Returns the default heading font.
     *
     * @return the heading font
     */
    public Font getHeadingFont() {
      return loadFont("theme.widget.fonts.heading");
    }
  }

  /**
   * The Class Theme.
   */
  public static class Theme extends KeyStore<String, CSSColor> {

    /** The m highlight. */
    private ThemeColors mHighlight;

    /** The m color highlight. */
    private ThemeColors mColorHighlight;

    /** The m color map. */
    private ThemeColorMap mColorMap = new ThemeColorMap();

    /**
     * Instantiates a new theme.
     */
    public Theme() {
      this(ColorTheme.BLUE);
    }

    /**
     * Instantiates a new theme.
     *
     * @param theme the theme
     */
    public Theme(ColorTheme theme) {
      mHighlight = getColorTheme(ColorTheme.GRAY);

      setTheme(theme);

      add("light-line", getGray32(3));
      add("line", getGray32(7));
      add("dark-line", getGray32(9));

      add("text", getGray32(31)); // CSSColor.BLACK; //getHighlight32(31);
      add("alt-text", getGray32(28));
      add("disabled-text", getGray32(24));
    }

    /**
     * Sets the theme.
     *
     * @param theme the new theme
     */
    public void setTheme(ColorTheme theme) {

      LOG.info("Setting color scheme to {}", theme);

      mColorHighlight = getColorTheme(theme);
    }

    /**
     * Gets the color highlight.
     *
     * @param i the i
     * 
     * @return the color highlight
     */
    public CSSColor getTheme(int i) {
      return mColorHighlight.getColor(i);
    }

    /**
     * Gets the color highlight 32.
     *
     * @param i the i
     * @return the color highlight 32
     */
    public CSSColor getTheme32(int i) {
      return mColorHighlight.getColor32(i);
    }

    public int getThemeIndex(CSSColor color) {
      return mColorHighlight.getIndex(color);
    }

    /**
     * Gets the highlight.
     *
     * @param i the i
     * @return the highlight
     */
    public CSSColor getGray(int i) {
      return mHighlight.getColor(i);
    }

    /**
     * Choose from 32 grayscale highlight colors.
     *
     * @param i An index from 0-31 inclusive.
     * @return the highlight 32
     */
    public CSSColor getGray32(int i) {
      return mHighlight.getColor32(i);
    }

    public int getGrayIndex(CSSColor color) {
      return mHighlight.getIndex(color);
    }

    /**
     * Gets the color theme.
     *
     * @param theme the theme
     * @return the color theme
     */
    public ThemeColors getColorTheme(ColorTheme theme) {
      return getColorTheme(theme.toString());
    }

    /**
     * Gets the color theme.
     *
     * @param theme the theme
     * @return the color theme
     */
    public ThemeColors getColorTheme(String theme) {
      return mColorMap.getColors(theme);
    }

    /**
     * Gets the red theme.
     *
     * @return the red theme
     */
    public ThemeColors getRedTheme() {
      return getColorTheme(ColorTheme.RED);
    }

    /**
     * Gets the blue theme.
     *
     * @return the blue theme
     */
    public ThemeColors getBlueTheme() {
      return getColorTheme(ColorTheme.BLUE);
    }

    /**
     * Gets the green theme.
     *
     * @return the green theme
     */
    public ThemeColors getGreenTheme() {
      return getColorTheme(ColorTheme.GREEN);
    }

    /**
     * Gets the gray theme.
     *
     * @return the gray theme
     */
    public ThemeColors getGrayTheme() {
      return getColorTheme(ColorTheme.GRAY);
    }

    /**
     * Gets the orange theme.
     *
     * @return the orange theme
     */
    public ThemeColors getOrangeTheme() {
      return getColorTheme(ColorTheme.ORANGE);
    }

    /**
     * Gets the purple theme.
     *
     * @return the purple theme
     */
    public ThemeColors getPurpleTheme() {
      return getColorTheme(ColorTheme.PURPLE);
    }

    /**
     * Gets the line color.
     *
     * @return the line color
     */
    public CSSColor getLineColor() {
      return get("line");
    }

    /**
     * Gets the light line color.
     *
     * @return the light line color
     */
    public CSSColor getLightLineColor() {
      return get("light-line");
    }

    public CSSColor getDarkLineColor() {
      return get("dark-line");
    }

    /**
     * Gets the text color.
     *
     * @return the text color
     */
    public CSSColor getTextColor() {
      return get("text");// CSSColor.BLACK; //getHighlight32(31);
    }

    /**
     * Gets the alt text color.
     *
     * @return the alt text color
     */
    public CSSColor getAltTextColor() {
      return get("alt-text");
    }

    /**
     * Gets the disabled text color.
     *
     * @return the disabled text color
     */
    public CSSColor getDisabledTextColor() {
      return get("disabled-text");
    }

  }

  /**
   * The Class ThemeColorMap.
   */
  public static class ThemeColorMap {

    /** The m map. */
    private Map<String, ThemeColors> mMap = new HashMap<>();

    /**
     * Gets the colors.
     *
     * @param theme the theme
     * @return the colors
     */
    public ThemeColors getColors(ColorTheme theme) {
      return getColors(theme.toString());
    }

    /**
     * Gets the colors.
     *
     * @param theme the theme
     * @return the colors
     */
    public ThemeColors getColors(String theme) {
      String name = theme.toLowerCase();

      if (!mMap.containsKey(name)) {
        mMap.put(name, new ThemeColors(theme));
      }

      return mMap.get(name);
    }
  }

  /**
   * Creates a set of graded theme colors.
   * 
   * @author Antony Holmes
   *
   */
  public static class ThemeColors {

    /** The Constant COLORS. */
    private static final int COLORS = 32;

    /** The Constant MAX_COLOR_INDEX. */
    private static final int MAX_COLOR_INDEX = COLORS - 1;

    /** The m color map 32. */
    private ColorMap mColorMap32;

    /** The m color map. */
    private ColorMap mColorMap;

    /**
     * Instantiates a new theme colors.
     */
    public ThemeColors() {
      this(ColorTheme.BLUE);
    }

    /**
     * Instantiates a new theme colors.
     *
     * @param theme the theme
     */
    public ThemeColors(ColorTheme theme) {
      this(theme.toString());
    }

    /**
     * Instantiates a new theme colors.
     *
     * @param theme the theme
     */
    public ThemeColors(String theme) {
      String color = "theme.color-schemes.color-scheme-" + theme.toLowerCase();

      // Mathematics.bound(SettingsService.getInstance().getDouble(color +
      // ".scaling"), 0, 1);

      CSSColor color1 = SettingsService.getInstance().getColor(color + ".start-color");
      CSSColor color2 = SettingsService.getInstance().getColor(color + ".end-color");

      System.err.println("theme " + color + ".end-color" + " "
          + (SettingsService.getInstance().getSetting(color + ".end-color").toString()));

      mColorMap = ColorMap.createTwoColorMap(theme, color1, color2, 10, false);
      mColorMap32 = ColorMap.createTwoColorMap(theme, color1, color2, COLORS, false);
    }

    /**
     * Returns a theme color. The index must be between 1 and 10 inclusive.
     *
     * @param i the i
     * @return the color
     */
    public CSSColor getColor(int i) {
      return mColorMap.getColorByIndex(Mathematics.bound(i, 1, 10) - 1);
    }

    /**
     * Gets the color 32.
     *
     * @param i the i
     * @return the color 32
     */
    public CSSColor getColor32(int i) {
      return mColorMap32.getColorByIndex(Mathematics.bound(i, 0, MAX_COLOR_INDEX));
    }

    public int getIndex(CSSColor color) {
      return mColorMap32.getIndex(color);
    }
  }

  /** The m fonts. */
  private ThemeFonts mFonts = new ThemeFonts();

  /** The m colors. */
  private Theme mColors = new Theme();

  /** The m setup. */
  private boolean mSetup = true;

  /**
   * Fonts.
   *
   * @return the theme fonts
   */
  public ThemeFonts getFonts() {
    return mFonts;
  }

  /**
   * Colors.
   *
   * @return the theme
   */
  public Theme getColors() {
    return mColors;
  }

  /**
   * Sets the theme.
   *
   * @throws ClassNotFoundException          the class not found exception
   * @throws InstantiationException          the instantiation exception
   * @throws IllegalAccessException          the illegal access exception
   * @throws FontFormatException             the font format exception
   * @throws IOException                     Signals that an I/O exception has
   *                                         occurred.
   * @throws UnsupportedLookAndFeelException the unsupported look and feel
   *                                         exception
   */
  public void setTheme() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
      FontFormatException, IOException, UnsupportedLookAndFeelException {
    setTheme(ColorTheme.BLUE);
  }

  /**
   * Sets the color theme used by the Modern UI and sets the overall look and feel
   * and loads fonts etc.
   *
   * @param theme the new theme
   * @throws FontFormatException             the font format exception
   * @throws IOException                     Signals that an I/O exception has
   *                                         occurred.
   * @throws ClassNotFoundException          the class not found exception
   * @throws InstantiationException          the instantiation exception
   * @throws IllegalAccessException          the illegal access exception
   * @throws UnsupportedLookAndFeelException the unsupported look and feel
   *                                         exception
   */
  public void setTheme(ColorTheme theme) throws FontFormatException, IOException, ClassNotFoundException,
      InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

    getColors().setTheme(theme);

    if (mSetup) {
      setLookAndFeel();
      //registerFonts();
      // setWidgetRenderer();

      mSetup = false;
    }

    // Resources.getInstance().registerFont("DroidSansMono.ttf");
    // Resources.registerFont("SourceSansPro-Regular.ttf");
    // Resources.registerFont("Roboto-Regular.ttf");
    // Resources.registerFont("OpenSans-Bold.ttf");
    // Resources.registerFont("Lato-Regular.ttf");
    // Resources.registerFont("Open Modern Bold", "OpenSans-Bold.ttf");
  }

  /**
   * Sets the widget renderer.
   */
  // public static final void setWidgetRenderer() {
  // Set the default way widgets are rendered.
  // WidgetRendererService.getInstance().set(new ModernRoundedWidgetRenderer());
  // }

  /**
   * Sets the look and feel.
   *
   * @throws ClassNotFoundException          the class not found exception
   * @throws InstantiationException          the instantiation exception
   * @throws IllegalAccessException          the illegal access exception
   * @throws UnsupportedLookAndFeelException the unsupported look and feel
   *                                         exception
   */
  public static final void setLookAndFeel()
      throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

    // Set some style properties

//    List<String> looks = new ArrayList<>();
//    looks.add("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//    looks.add("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
//    looks.add("com.sun.java.swing.plaf.gtk.GTK");

    // Keep trying them until an error is not thrown

    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
  }

  /**
   * Register fonts.
   *
   * @throws FontFormatException the font format exception
   * @throws IOException         Signals that an I/O exception has occurred.
   */
//  public static final void registerFonts() throws FontFormatException, IOException {
//    Resources.getInstance().registerFont("Inconsolata-Regular.ttf");
//
//    // Resources.getInstance().registerFont("ProductSans-Regular.ttf");
//
//    // Resources.getInstance().registerFont("Futura_LT_Book.ttf");
//
//    // Resources.getInstance().registerFont("Roboto-Light.ttf");
//    // Resources.getInstance().registerFont("Roboto-Regular.ttf");
//    // Resources.getInstance().registerFont("Roboto-Medium.ttf");
//    // Resources.getInstance().registerFont("Roboto-Bold.ttf");
//
//    //Resources.getInstance().registerFont("SourceSansPro-Light.ttf");
//    //Resources.getInstance().registerFont("SourceSansPro-Regular.ttf");
//    //Resources.getInstance().registerFont("SourceSansPro-SemiBold.ttf");
//    
//    Resources.getInstance().registerFont("Inter-Light.ttf");
//    Resources.getInstance().registerFont("Inter-Regular.ttf");
//    Resources.getInstance().registerFont("Inter-SemiBold.ttf");
//
//    // Resources.getInstance().registerFont("Raleway-Light.ttf");
//    // Resources.getInstance().registerFont("Raleway-Regular.ttf");
//
//    // Resources.getInstance().registerFont("NotoSans-Regular.ttf");2202
//
//    // Resources.getInstance().registerFont("OpenSans-Regular.ttf");
//    // Resources.getInstance().registerFont("OpenSans-Light.ttf");
//    // Resources.getInstance().registerFont("OpenSans-SemiBold.ttf");
//  }

  /**
   * Load font.
   *
   * @param path the path
   * @return the font
   */
  public static Font loadFont(String path) {
    return loadFont(new Path(path));
  }

  public static Font loadFont(Path path) {
    Path p = new Path(path).append("family");

    String family = SettingsService.getInstance().getString(p);

    p = new Path(path).append("size");

    int size = SettingsService.getInstance().getInt(p);

    return FontService.getInstance().loadFont(family, size, SettingsService.getInstance().getString(p).equals("bold"));
  }
}
