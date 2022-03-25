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
package org.jebtk.modern.graphics.colormap;

import java.awt.Color;
import java.awt.LinearGradientPaint;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

import org.jebtk.core.ColorUtils;
import org.jebtk.core.Mathematics;
import org.jebtk.core.collections.ArrayUtils;
import org.jebtk.core.json.Json;
import org.jebtk.core.json.JsonArray;
import org.jebtk.core.json.JsonObject;
import org.jebtk.core.json.JsonRepresentation;
import org.jebtk.core.sys.SysUtils;
import org.jebtk.core.xml.XmlRepresentation;
import org.jebtk.core.CSSColor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Represents a color gradient using a specified number of colors.
 * 
 * @author Antony Holmes
 *
 */
public class ColorMap implements Comparable<ColorMap>, XmlRepresentation, JsonRepresentation {

  /** The Constant DEFAULT_COLORS. */
  public static final int DEFAULT_COLORS = 64;

  /** The Constant GREEN. */
  public static final Color GREEN = ColorUtils.decodeHtmlColor("#00aa00");

  public static final Color GREEN_2 = ColorUtils.decodeHtmlColor("71c837");

  /** The Constant BLUE. */
  public static final Color BLUE = ColorUtils.decodeHtmlColor("#0044aa");

  public static final Color BLUE_2 = ColorUtils.decodeHtmlColor("#3771c8");

  /** The Constant PURPLE. */
  public static final Color PURPLE = ColorUtils.decodeHtmlColor("#4400aa");

  public static final Color PURPLE_2 = ColorUtils.decodeHtmlColor("#8d5fd3");

  /** The Constant YELLOW. */
  public static final Color YELLOW = ColorUtils.decodeHtmlColor("#ffd42a");

  public static final Color YELLOW_2 = ColorUtils.decodeHtmlColor("#ffff00");

  /** The Constant ORANGE. */
  public static final Color ORANGE = ColorUtils.decodeHtmlColor("#ff7f2a");

  /** The Constant PINK. */
  public static final Color PINK = ColorUtils.decodeHtmlColor("#ff0066");

  /** The Constant WINTERGREEN. */
  public static final Color WINTERGREEN = ColorUtils.decodeHtmlColor("#00d4aa");

  public static final Color WINTERGREEN_2 = ColorUtils.decodeHtmlColor("2ca089");

  /**
   * The Class AnchorColors.
   */
  public static class AnchorColors {

    /** The Constant GRADIENT_FIVE_POINT. */
    private static final float[] GRADIENT_FIVE_POINT = { 0.0f, 0.25f, 0.5f, 0.75f, 1.0f };

    /** The Constant GRADIENT_FOUR_POINT. */
    private static final float[] GRADIENT_FOUR_POINT = { 0.0f, 0.3333f, 0.6666f, 1.0f };

    /** The Constant GRADIENT_THREE_POINT. */
    private static final float[] GRADIENT_THREE_POINT = { 0.0f, 0.5f, 1.0f };

    /** The Constant GRADIENT_TWO_POINT. */
    private static final float[] GRADIENT_TWO_POINT = { 0.0f, 1.0f };

    /** The m colors. */
    private final CSSColor[] mColors;

    /**
     * Instantiates a new anchor colors.
     *
     * @param color1 the color 1
     * @param color2 the color 2
     */
    public AnchorColors(CSSColor color1, CSSColor color2) {
      this(new CSSColor[] { color1, color2 });
    }


    /**
     * Instantiates a new anchor colors.
     *
     * @param color1 the color 1
     * @param color2 the color 2
     * @param color3 the color 3
     */
    public AnchorColors(CSSColor color1, CSSColor color2, CSSColor color3) {
      this(new CSSColor[] { color1, color2, color3 });
    }


    /**
     * Instantiates a new anchor colors.
     *
     * @param color1 the color 1
     * @param color2 the color 2
     * @param color3 the color 3
     * @param color4 the color 4
     */
    public AnchorColors(CSSColor color1, CSSColor color2, CSSColor color3, CSSColor color4) {
      this(new CSSColor[] { color1, color2, color3, color4 });
    }


    /**
     * Instantiates a new anchor colors.
     *
     * @param color1 the color 1
     * @param color2 the color 2
     * @param color3 the color 3
     * @param color4 the color 4
     * @param color5 the color 5
     */
    public AnchorColors(CSSColor color1, CSSColor color2, CSSColor color3, CSSColor color4, CSSColor color5) {
      this(new CSSColor[] { color1, color2, color3, color4, color5 });
    }

    public AnchorColors(CSSColor[] colors) {
      mColors = new CSSColor[colors.length];

      SysUtils.arraycopy(colors, mColors);
    }

    /**
     * Instantiates a new anchor colors.
     *
     * @param anchorColors the anchor colors
     * @param reverse      the reverse
     */
    public AnchorColors(AnchorColors anchorColors, boolean reverse) {
      mColors = new CSSColor[anchorColors.mColors.length];

      SysUtils.arraycopy(anchorColors.mColors, mColors);

      if (reverse) {
        ArrayUtils.reverse(mColors);
      }
    }

    /**
     * Gets the anchor color.
     *
     * @param index the index
     * @return the anchor color
     */
    public CSSColor getAnchorColor(int index) {
      return mColors[index];
    }

    /**
     * To gradient paint.
     *
     * @param start the start
     * @param end   the end
     * @return the linear gradient paint
     */
    public LinearGradientPaint toGradientPaint(Point2D start, Point2D end) {

      // CSSColor[] cl = mColors.toArray(new CSSColor[mColors.size()]);

      switch (mColors.length) {
      case 5:
        return new LinearGradientPaint(start, end, GRADIENT_FIVE_POINT, mColors);
      case 4:
        return new LinearGradientPaint(start, end, GRADIENT_FOUR_POINT, mColors);
      case 3:
        return new LinearGradientPaint(start, end, GRADIENT_THREE_POINT, mColors);
      default:
        return new LinearGradientPaint(start, end, GRADIENT_TWO_POINT, mColors);
      }
    }

    public LinearGradientPaint toGradientPaint(Point2D start, Point2D end, double trans) {

      // CSSColor[] cl = mColors.toArray(new CSSColor[mColors.size()]);

      switch (mColors.length) {
      case 5:
        return new LinearGradientPaint(start, end, GRADIENT_FIVE_POINT, ColorUtils.trans(mColors, trans));
      case 4:
        return new LinearGradientPaint(start, end, GRADIENT_FOUR_POINT, ColorUtils.trans(mColors, trans));
      case 3:
        return new LinearGradientPaint(start, end, GRADIENT_THREE_POINT, ColorUtils.trans(mColors, trans));
      default:
        return new LinearGradientPaint(start, end, GRADIENT_TWO_POINT, ColorUtils.trans(mColors, trans));
      }
    }

    /**
     * Returns the number of anchor colors.
     *
     * @return the color count
     */
    public int getColorCount() {
      return mColors.length;
    }
  }

  /** The Constant NF. */
  private static final float NF = 255;

  /**
   * The member name.
   */
  private String mName;

  /**
   * The member max index.
   */
  private int mMaxIndex;

  /**
   * The member min.
   */
  private double mMin = 0;

  /**
   * The member max.
   */
  private double mMax = 1;

  /** The m anchor colors. */
  private AnchorColors mAnchorColors;

  /** The m colors. */
  private final CSSColor[] mColors;

  private Map<CSSColor, Integer> mIndexMap = new HashMap<>();

  public ColorMap(ColorMap colorMap) {
    this(colorMap, false);
  }

  /**
   * Clone a color map and optionally reverse it.
   *
   * @param colorMap the color map
   * @param reverse  the reverse
   */
  public ColorMap(ColorMap colorMap, boolean reverse) {
    this(colorMap.mName, colorMap, reverse);
  }

  /**
   * Instantiates a new color map.
   *
   * @param name    the name
   * @param color1  the color 1
   * @param color2  the color 2
   * @param colors  the colors
   * @param reverse the reverse
   */
  public ColorMap(String name, CSSColor color1, CSSColor color2, int colors, boolean reverse) {
    if (reverse) {
      mAnchorColors = new AnchorColors(color2, color1);
    } else {
      mAnchorColors = new AnchorColors(color1, color2);
    }

    mColors = createTwoColorMap(color1, color2, colors, reverse);

    init(name);
  }

  /**
   * Instantiates a new color map.
   *
   * @param name    the name
   * @param color1  the color 1
   * @param color2  the color 2
   * @param color3  the color 3
   * @param colors  the colors
   * @param reverse the reverse
   */
  public ColorMap(String name, CSSColor color1, CSSColor color2, CSSColor color3, int colors, boolean reverse) {
    if (reverse) {
      mAnchorColors = new AnchorColors(color3, color2, color1);
    } else {
      mAnchorColors = new AnchorColors(color1, color2, color3);
    }

    mColors = createThreeColorMap(color1, color2, color3, colors, reverse);

    init(name);
  }

  /**
   * Instantiates a new color map.
   *
   * @param name    the name
   * @param color1  the color 1
   * @param color2  the color 2
   * @param color3  the color 3
   * @param color4  the color 4
   * @param colors  the colors
   * @param reverse the reverse
   */
  public ColorMap(String name, CSSColor color1, CSSColor color2, CSSColor color3, CSSColor color4, int colors, boolean reverse) {
    if (reverse) {
      mAnchorColors = new AnchorColors(color4, color3, color2, color1);
    } else {
      mAnchorColors = new AnchorColors(color1, color2, color3, color4);
    }

    mColors = createFourColorMap(color1, color2, color3, color4, colors, reverse);

    init(name);
  }

  /**
   * Instantiates a new color map.
   *
   * @param name    the name
   * @param color1  the color 1
   * @param color2  the color 2
   * @param color3  the color 3
   * @param color4  the color 4
   * @param color5  the color 5
   * @param colors  the colors
   * @param reverse the reverse
   */
  public ColorMap(String name, CSSColor color1, CSSColor color2, CSSColor color3, CSSColor color4, CSSColor color5, int colors,
      boolean reverse) {
    if (reverse) {
      mAnchorColors = new AnchorColors(color5, color4, color3, color2, color1);
    } else {
      mAnchorColors = new AnchorColors(color1, color2, color3, color4, color5);
    }

    mColors = createFiveColorMap(color1, color2, color3, color4, color5, colors, reverse);

    init(name);
  }

  /**
   * Instantiates a new color map.
   *
   * @param name     the name
   * @param colorMap the color map
   * @param reverse  the reverse
   */
  public ColorMap(String name, ColorMap colorMap, boolean reverse) {
    mAnchorColors = new AnchorColors(colorMap.mAnchorColors, reverse);

    mColors = new CSSColor[colorMap.mColors.length];

    SysUtils.arraycopy(colorMap.mColors, mColors);

    if (reverse) {
      ArrayUtils.reverse(mColors);
    }

    init(name);
  }

  /**
   * Inits the.
   *
   * @param name the name
   */
  private void init(String name) {
    mName = name;

    mMaxIndex = mColors.length - 1;

    for (int i = 0; i < mColors.length; ++i) {
      mIndexMap.put(mColors[i], i);
    }
  }

  /**
   * Returns the index of a color, or -1 if the color does not exist.
   * 
   * @param color
   * @return
   */
  public int getIndex(CSSColor color) {
    if (mIndexMap.containsKey(color)) {
      return mIndexMap.get(color);
    } else {
      return -1;
    }
  }

  /*
   * @Override public boolean add(CSSColor color) { super.add(color);
   * 
   * mMaxIndex = getItemCount() - 1;
   * 
   * return true; }
   */

  /*
   * @Override public void add(Collection<CSSColor> colorMap) {
   * super.add(colorMap);
   * 
   * mMaxIndex = getItemCount() - 1; }
   */

  /**
   * Gets the name.
   *
   * @return the name
   */
  public String getName() {
    return mName;
  }

  /**
   * Gets the color. V should be a value between 0 and 1.
   *
   * @param v the v
   * @return the color
   */
  public CSSColor getColor(double v) {
    return getColorByIndex(getColorIndex(v));
  }

  /**
   * Gets the min.
   *
   * @return the min
   */
  public double getMin() {
    return mMin;
  }

  /**
   * Gets the max.
   *
   * @return the max
   */
  public double getMax() {
    return mMax;
  }

  /**
   * Gets the color index for a value. Values are bounded in the range [0, 1] with
   * the assumption that the matrix should be normalized before rendering
   * (independently of any other transformations).
   *
   * @param v the v
   * @return the color index
   */
  public int getColorIndex(double v) {

    double s = Mathematics.bound(v, 0, 1);
    int index = (int) (mMaxIndex * s);

    // System.err.println("v " + v + " " + s + " " + index +
    // mColors.get(index));

    return index;
  }

  /**
   * Gets the color by index.
   *
   * @param index the index
   * @return the color by index
   */
  public CSSColor getColorByIndex(int index) {
    return mColors[index];
  }

  /**
   * Gets the anchor colors.
   *
   * @return the anchor colors
   */
  public AnchorColors getAnchorColors() {
    return mAnchorColors;
  }

  /**
   * Gets the color count.
   *
   * @return the color count
   */
  public int getColorCount() {
    return mColors.length;
  }

  /**
   * CSSColor maps with the same name are considered equal.
   *
   * @param m the m
   * @return true, if successful
   */
  /*
   * @Override public boolean equals(Object o) { if (o instanceof ColorMap) {
   * return compareTo((ColorMap)o) == 0; } else { return false; } }
   */

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  @Override
  public int compareTo(ColorMap m) {
    return mName.compareTo(m.mName);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.xml.XmlRepresentation#toXml()
   */
  @Override
  public Element toXml(Document doc) {
    Element element = doc.createElement("colormap");
    element.setAttribute("name", mName);
    element.setAttribute("colors", Integer.toString(mColors.length));

    Element anchorElement = doc.createElement("anchor-colors");

    for (CSSColor color : this.mColors) {
      anchorElement.appendChild(color.toXml(doc));
    }

    element.appendChild(anchorElement);

    return element;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.json.JsonRepresentation#toJson()
   */
  @Override
  public Json toJson() {
    Json json = new JsonObject();

    json.add("name", mName);
    json.add("colors", mColors.length);

    Json array = new JsonArray();

    for (CSSColor color : mAnchorColors.mColors) {
      array.add(color.toJson());
    }

    json.add("anchor-colors", array);

    return json;
  }

  //
  // Static methods
  //

  /**
   * Creates the two color map.
   *
   * @param name   the name
   * @param color1 the color 1
   * @param color2 the color 2
   * @param colors the colors
   * @return the color map
   */
  public static ColorMap createTwoColorMap(String name, Color color1, Color color2, int colors) {
    return createTwoColorMap(name, color1, color2, colors, false);
  }

  /**
   * Creates the two color map.
   *
   * @param name    the name
   * @param color1  the color 1
   * @param color2  the color 2
   * @param colors  the colors
   * @param reverse the reverse
   * @return the color map
   */
  public static ColorMap createTwoColorMap(String name, 
          Color color1, 
          Color color2, 
          int colors, 
          boolean reverse) {
    return new ColorMap(name, new CSSColor(color1), new CSSColor(color2), colors, reverse);
  }

  /**
   * Creates the two color map.
   *
   * @param color1  the color 1
   * @param color2  the color 2
   * @param colors  the colors
   * @param reverse the reverse
   * @return the list
   */
  public static CSSColor[] createTwoColorMap(Color color1, Color color2, int colors, boolean reverse) {
    CSSColor[] ret = new CSSColor[colors];
    ret[0] = new CSSColor(color1);
    ret[ret.length - 1] = new CSSColor(color2);
    
    float f;
    
    int R = color1.getRed();
    int G = color1.getGreen();
    int B = color1.getBlue();
    int A = color1.getAlpha();
    
    int diffR = color2.getRed() - R;
    int diffG = color2.getGreen() - G;
    int diffB = color2.getBlue() - B;
    int diffA = color2.getAlpha() - A;
    
    CSSColor c;
    
    for (int i = 1; i < colors - 1; ++i) {
      f = (float)i / colors;
      
      c = new CSSColor(R + (int)(diffR * f), 
          G + (int)(diffG * f), 
          B + (int)(diffB * f), 
          A + (int)(diffA * f));
      
      ret[i] = c;
    }
    
    if (reverse) {
      ArrayUtils.reverse(ret);
    }
    
    return ret;
    
    
//    float[] r = Mathematics.floatRepeat(color2.getRed() / NF, colors);
//    float[] g = Mathematics.floatRepeat(color2.getGreen() / NF, colors);
//    float[] b = Mathematics.floatRepeat(color2.getBlue() / NF, colors);
//    float[] a = Mathematics.floatRepeat(color2.getAlpha() / NF, colors);
//
//    int cols = colors / 2 * 2;
//
//    float rinc = (color2.getRed() - color1.getRed()) / NF;
//    float ginc = (color2.getGreen() - color1.getGreen()) / NF;
//    float binc = (color2.getBlue() - color1.getBlue()) / NF;
//    float ainc = (color2.getAlpha() - color1.getAlpha()) / NF;
//
//    float rf = color1.getRed() / NF;
//    float gf = color1.getGreen() / NF;
//    float bf = color1.getBlue() / NF;
//    float af = color1.getAlpha() / NF;
//
//    // fill in the gaps
//
//    float bsf = cols - 1;
//
//    for (int i = 0; i < cols; ++i) {
//      float p = i / bsf;
//
//      r[i] = bound(rf + rinc * p);
//      g[i] = bound(gf + ginc * p);
//      b[i] = bound(bf + binc * p);
//      a[i] = bound(af + ainc * p);
//    }
//
//    // Set the end color
//    r[r.length - 1] = color2.getRed() / NF;
//    g[g.length - 1] = color2.getGreen() / NF;
//    b[b.length - 1] = color2.getBlue() / NF;
//    a[a.length - 1] = color2.getAlpha() / NF;
//
//    CSSColor[] ret = new CSSColor[colors];
//
//    for (int i = 0; i < colors; ++i) {
//      ret[i] = new CSSColor(r[i], g[i], b[i], a[i]);
//    }
//
//    if (reverse) {
//      ArrayUtils.reverse(ret);
//    }
//
//    return ret;
  }

  /**
   * Creates the three color map.
   *
   * @param name   the name
   * @param color1 the color 1
   * @param color2 the color 2
   * @param color3 the color 3
   * @param colors the colors
   * @return the color map
   */
  public static ColorMap createThreeColorMap(String name, Color color1, Color color2, Color color3, int colors) {
    return createThreeColorMap(name, color1, color2, color3, colors, false);
  }

  /**
   * Creates the three color map.
   *
   * @param name    the name
   * @param color1  the color 1
   * @param color2  the color 2
   * @param color3  the color 3
   * @param colors  the colors
   * @param reverse the reverse
   * @return the color map
   */
  public static ColorMap createThreeColorMap(String name, Color color1, Color color2, Color color3, int colors,
      boolean reverse) {
    return new ColorMap(name, new CSSColor(color1), new CSSColor(color2), new CSSColor(color3), colors, reverse);
  }

  /**
   * Creates the three color map.
   *
   * @param color1  the color 1
   * @param color2  the color 2
   * @param color3  the color 3
   * @param colors  the colors
   * @param reverse the reverse
   * @return the list
   */
  public static CSSColor[] createThreeColorMap(Color color1, Color color2, Color color3, int colors, boolean reverse) {
    float[] r = Mathematics.floatRepeat(color3.getRed() / NF, colors);
    float[] g = Mathematics.floatRepeat(color3.getGreen() / NF, colors);
    float[] b = Mathematics.floatRepeat(color3.getBlue() / NF, colors);
    float[] a = Mathematics.floatRepeat(color3.getAlpha() / NF, colors);

    // Set the color reference points

    int binSize = colors / 2; // (int) Math.round(colors / 2.0); // colors / 2;
    int cols = binSize * 2;

    // int mid = colors / 2;

    float bsf = binSize - 1; // binSize;

    float[] rinc = { (color2.getRed() - color1.getRed()) / NF, (color3.getRed() - color2.getRed()) / NF };

    float[] ginc = { (color2.getGreen() - color1.getGreen()) / NF, (color3.getGreen() - color2.getGreen()) / NF };

    float[] binc = { (color2.getBlue() - color1.getBlue()) / NF, (color3.getBlue() - color2.getBlue()) / NF };

    float[] ainc = { (color2.getAlpha() - color1.getAlpha()) / NF, (color3.getAlpha() - color2.getAlpha()) / NF };

    float[] rf = { color1.getRed() / NF, color2.getRed() / NF };
    float[] gf = { color1.getGreen() / NF, color2.getGreen() / NF };
    float[] bf = { color1.getBlue() / NF, color2.getBlue() / NF };
    float[] af = { color1.getAlpha() / NF, color2.getAlpha() / NF };

    // fill in the gaps
    for (int i = 0; i < cols; ++i) {
      // 0 = lower bin, 1 = upper bin
      int bin = i / binSize;

      // bound p within a block
      float p = (i % binSize) / bsf;

      r[i] = bound(rf[bin] + rinc[bin] * p);
      g[i] = bound(gf[bin] + ginc[bin] * p);
      b[i] = bound(bf[bin] + binc[bin] * p);
      a[i] = bound(af[bin] + ainc[bin] * p);
    }

    /*
     * int s = colors - mid;
     * 
     * for (int i = 0; i < mid; ++i) { int ix = i + s; // 0 = lower bin, 1 = upper
     * bin //int bin = i / binSize;
     * 
     * // bound p within a block float p = (i + 1) / bsf;
     * 
     * r[ix] = bound(rf[1] + rinc[1] * p); g[ix] = bound(gf[1] + ginc[1] * p); b[ix]
     * = bound(bf[1] + binc[1] * p); a[ix] = bound(af[1] + ainc[1] * p); }
     * 
     * // Set the mid point color if (colors % 2 == 1) { r[mid] =
     * bound(color2.getRed() / NF); g[mid] = bound(color2.getGreen() / NF); b[mid] =
     * bound(color2.getBlue() / NF); a[mid] = bound(color2.getAlpha() / NF); }
     */

    CSSColor[] ret = new CSSColor[colors];

    for (int i = 0; i < colors; ++i) {
      ret[i] = new CSSColor(r[i], g[i], b[i], a[i]);
    }

    if (reverse) {
      ArrayUtils.reverse(ret);
    }

    return ret;
  }

  private static float bound(float v) {
    return Mathematics.bound(v, 0, 1);
  }

  /**
   * Creates the four color map.
   *
   * @param name   the name
   * @param color1 the color 1
   * @param color2 the color 2
   * @param color3 the color 3
   * @param color4 the color 4
   * @param colors the colors
   * @return the color map
   */
  public static ColorMap createFourColorMap(String name, Color color1, Color color2, Color color3, Color color4,
      int colors) {
    return createFourColorMap(name, color1, color2, color3, color4, colors, false);
  }

  /**
   * Creates the four color map.
   *
   * @param name    the name
   * @param color1  the color 1
   * @param color2  the color 2
   * @param color3  the color 3
   * @param color4  the color 4
   * @param colors  the colors
   * @param reverse the reverse
   * @return the color map
   */
  public static ColorMap createFourColorMap(String name, Color color1, Color color2, Color color3, Color color4,
      int colors, boolean reverse) {
    return new ColorMap(name, new CSSColor(color1), new CSSColor(color2), new CSSColor(color3), new CSSColor(color4), colors, reverse);
  }

  /**
   * Creates the four color map.
   *
   * @param color1  the color 1
   * @param color2  the color 2
   * @param color3  the color 3
   * @param color4  the color 4
   * @param colors  the colors
   * @param reverse the reverse
   * @return the list
   */
  public static CSSColor[] createFourColorMap(Color color1, Color color2, Color color3, Color color4, int colors,
      boolean reverse) {
    float[] r = Mathematics.floatRepeat(color4.getRed() / NF, colors);
    float[] g = Mathematics.floatRepeat(color4.getGreen() / NF, colors);
    float[] b = Mathematics.floatRepeat(color4.getBlue() / NF, colors);
    float[] a = Mathematics.floatRepeat(color4.getAlpha() / NF, colors);

    // Set the color reference points

    int binSize = colors / 3; // (int) Math.round(colors / 3.0); // + (colors %
    // 2 == 0 ? 0 :
    int cols = binSize * 3;

    float bsf = binSize - 1;

    float[] rinc = { (color2.getRed() - color1.getRed()) / NF, (color3.getRed() - color2.getRed()) / NF,
        (color4.getRed() - color3.getRed()) / NF };

    float[] ginc = { (color2.getGreen() - color1.getGreen()) / NF, (color3.getGreen() - color2.getGreen()) / NF,
        (color4.getGreen() - color3.getGreen()) / NF };

    float[] binc = { (color2.getBlue() - color1.getBlue()) / NF, (color3.getBlue() - color2.getBlue()) / NF,
        (color4.getBlue() - color3.getBlue()) / NF };

    float[] ainc = { (color2.getAlpha() - color1.getAlpha()) / NF, (color3.getAlpha() - color2.getAlpha()) / NF,
        (color4.getAlpha() - color3.getAlpha()) / NF };

    float[] rf = { color1.getRed() / NF, color2.getRed() / NF, color3.getRed() / NF };

    float[] gf = { color1.getGreen() / NF, color2.getGreen() / NF, color3.getGreen() / NF };

    float[] bf = { color1.getBlue() / NF, color2.getBlue() / NF, color3.getBlue() / NF };

    float[] af = { color1.getAlpha() / NF, color2.getAlpha() / NF, color3.getAlpha() / NF };

    // fill in the gaps
    for (int i = 0; i < cols; ++i) {
      int bin = i / binSize;

      // System.err.println("cmap " + colors + " " + binSize + " " + i + " " +
      // bin);

      // Bound p within a block since we are scaling colors from
      // the beginning of the block to the end and then repeating
      // for each block.
      float p = (i % binSize) / bsf; // (i / (float)binSize) % 1.0f;

      r[i] = bound(rf[bin] + rinc[bin] * p);
      g[i] = bound(gf[bin] + ginc[bin] * p);
      b[i] = bound(bf[bin] + binc[bin] * p);
      a[i] = bound(af[bin] + ainc[bin] * p);
    }

    CSSColor[] ret = new CSSColor[colors];

    for (int i = 0; i < colors; ++i) {
      ret[i] = new CSSColor(r[i], g[i], b[i], a[i]);
    }

    if (reverse) {
      ArrayUtils.reverse(ret);
    }

    return ret;
  }

  /**
   * Creates the five color map.
   *
   * @param name   the name
   * @param color1 the color 1
   * @param color2 the color 2
   * @param color3 the color 3
   * @param color4 the color 4
   * @param color5 the color 5
   * @param colors the colors
   * @return the color map
   */
  public static ColorMap createFiveColorMap(String name, Color color1, Color color2, Color color3, Color color4,
      Color color5, int colors) {
    return createFiveColorMap(name, color1, color2, color3, color4, color5, colors, false);
  }

  /**
   * Creates the five color map.
   *
   * @param name    the name
   * @param color1  the color 1
   * @param color2  the color 2
   * @param color3  the color 3
   * @param color4  the color 4
   * @param color5  the color 5
   * @param colors  the colors
   * @param reverse the reverse
   * @return the color map
   */
  public static ColorMap createFiveColorMap(String name, Color color1, Color color2, Color color3, Color color4,
      Color color5, int colors, boolean reverse) {
    return new ColorMap(name, new CSSColor(color1), new CSSColor(color2), new CSSColor(color3), new CSSColor(color4), new CSSColor(color5), colors, reverse);
  }

  /**
   * Creates the five color map.
   *
   * @param color1  the color 1
   * @param color2  the color 2
   * @param color3  the color 3
   * @param color4  the color 4
   * @param color5  the color 5
   * @param colors  the colors
   * @param reverse the reverse
   * @return the list
   */
  public static CSSColor[] createFiveColorMap(Color color1, Color color2, Color color3, Color color4, Color color5,
      int colors, boolean reverse) {
    float[] r = Mathematics.floatRepeat(color5.getRed() / NF, colors);
    float[] g = Mathematics.floatRepeat(color5.getGreen() / NF, colors);
    float[] b = Mathematics.floatRepeat(color5.getBlue() / NF, colors);
    float[] a = Mathematics.floatRepeat(color5.getAlpha() / NF, colors);

    // Set the color reference points

    int binSize = colors / 4; // (int) Math.round(colors / 4.0); // + (colors %
    // 2 == 0 ? 0 :
    colors = binSize * 4;

    float bsf = binSize - 1;

    float[] rinc = { (color2.getRed() - color1.getRed()) / NF, (color3.getRed() - color2.getRed()) / NF,
        (color4.getRed() - color3.getRed()) / NF, (color5.getRed() - color4.getRed()) / NF };

    float[] ginc = { (color2.getGreen() - color1.getGreen()) / NF, (color3.getGreen() - color2.getGreen()) / NF,
        (color4.getGreen() - color3.getGreen()) / NF, (color5.getGreen() - color4.getGreen()) / NF };

    float[] binc = { (color2.getBlue() - color1.getBlue()) / NF, (color3.getBlue() - color2.getBlue()) / NF,
        (color4.getBlue() - color3.getBlue()) / NF, (color5.getBlue() - color4.getBlue()) / NF };

    float[] ainc = { (color2.getAlpha() - color1.getAlpha()) / NF, (color3.getAlpha() - color2.getAlpha()) / NF,
        (color4.getAlpha() - color3.getAlpha()) / NF, (color5.getAlpha() - color4.getAlpha()) / NF };

    float[] rf = { color1.getRed() / NF, color2.getRed() / NF, color3.getRed() / NF, color4.getRed() / NF };

    float[] gf = { color1.getGreen() / NF, color2.getGreen() / NF, color3.getGreen() / NF, color4.getGreen() / NF };

    float[] bf = { color1.getBlue() / NF, color2.getBlue() / NF, color3.getBlue() / NF, color4.getBlue() / NF };

    float[] af = { color1.getAlpha() / NF, color2.getAlpha() / NF, color3.getAlpha() / NF, color4.getAlpha() / NF };

    // fill in the gaps
    for (int i = 0; i < colors; ++i) {
      int bin = i / binSize;

      // System.err.println("cmap " + colors + " " + binSize + " " + i + " " +
      // bin);

      // Bound p within a block since we are scaling colors from
      // the beginning of the block to the end and then repeating
      // for each block.
      float p = (i % binSize) / bsf; // (i / (float)binSize) % 1.0f;

      r[i] = bound(rf[bin] + rinc[bin] * p);
      g[i] = bound(gf[bin] + ginc[bin] * p);
      b[i] = bound(bf[bin] + binc[bin] * p);
      a[i] = bound(af[bin] + ainc[bin] * p);
    }

    CSSColor[] ret = new CSSColor[colors];

    for (int i = 0; i < colors; ++i) {
      ret[i] = new CSSColor(r[i], g[i], b[i], a[i]);
    }

    if (reverse) {
      ArrayUtils.reverse(ret);
    }

    return ret;
  }

  /**
   * Creates the blue white red map.
   *
   * @return the color map
   */
  public static ColorMap createBlueWhiteRedMap() {
    return createBlueWhiteRedMap(false);
  }

  /**
   * Creates the blue white red map.
   *
   * @param invert the invert
   * @return the color map
   */
  public static ColorMap createBlueWhiteRedMap(boolean invert) {
    return createThreeColorMap("Blue White Red", BLUE, CSSColor.WHITE, CSSColor.RED, DEFAULT_COLORS, invert);
  }

  /**
   * Creates the green white red map.
   *
   * @return the color map
   */
  public static ColorMap createGreenWhiteRedMap() {
    return createGreenWhiteRedMap(false);
  }

  /**
   * Creates the green white red map.
   *
   * @param invert the invert
   * @return the color map
   */
  public static ColorMap createGreenWhiteRedMap(boolean invert) {
    return createThreeColorMap("Green White Red", GREEN, CSSColor.WHITE, CSSColor.RED, DEFAULT_COLORS, invert);
  }

  /**
   * Creates the green black red map.
   *
   * @return the color map
   */
  public static ColorMap createGreenBlackRedMap() {
    return createGreenBlackRedMap(false);
  }

  /**
   * Creates the blue red map.
   *
   * @param invert the invert
   * @return the color map
   */
  public static ColorMap createGreenBlackRedMap(boolean invert) {
    return createThreeColorMap("Green Black Red", GREEN, CSSColor.BLACK, CSSColor.RED, DEFAULT_COLORS, invert);
  }

  public static ColorMap createViridisMap() {
    return createViridisMap(false);
  }

  public static ColorMap createViridisMap(boolean invert) {
    return createFiveColorMap("Viridis", PURPLE_2, BLUE_2, WINTERGREEN_2, GREEN_2, YELLOW_2, DEFAULT_COLORS, invert);
  }

  /**
   * Creates the jet map.
   *
   * @return the color map
   */
  public static ColorMap createJetMap() {
    return createJetMap(false);
  }

  /**
   * Creates the white blue map.
   *
   * @param invert the invert
   * @return the color map
   */
  public static ColorMap createJetMap(boolean invert) {
    return createFiveColorMap("Jet", BLUE, Color.CYAN, GREEN, YELLOW, Color.RED, DEFAULT_COLORS, invert);
  }

  /**
   * Creates the hot map.
   *
   * @return the color map
   */
  public static ColorMap createHotMap() {
    return createHotMap(false);
  }

  /**
   * Creates the hot map.
   *
   * @param invert the invert
   * @return the color map
   */
  public static ColorMap createHotMap(boolean invert) {
    return createFiveColorMap("Hot", CSSColor.BLACK, CSSColor.RED, CSSColor.ORANGE, CSSColor.YELLOW, Color.WHITE, DEFAULT_COLORS,
        invert);
  }

  /**
   * Creates the cool map.
   *
   * @return the color map
   */
  public static ColorMap createCoolMap() {
    return createCoolMap(false);
  }

  /**
   * Creates the cool map.
   *
   * @param invert the invert
   * @return the color map
   */
  public static ColorMap createCoolMap(boolean invert) {
    return createTwoColorMap("Cool", Color.CYAN, PINK, DEFAULT_COLORS, invert);
  }

  /**
   * Creates the spring map.
   *
   * @return the color map
   */
  public static ColorMap createSpringMap() {
    return createSpringMap(false);
  }

  /**
   * Creates the spring map.
   *
   * @param invert the invert
   * @return the color map
   */
  public static ColorMap createSpringMap(boolean invert) {
    return createTwoColorMap("Spring", PINK, CSSColor.YELLOW, DEFAULT_COLORS, invert);
  }

  /**
   * Creates the summer map.
   *
   * @return the color map
   */
  public static ColorMap createSummerMap() {
    return createSummerMap(false);
  }

  /**
   * Creates the summer map.
   *
   * @param invert the invert
   * @return the color map
   */
  public static ColorMap createSummerMap(boolean invert) {
    return createTwoColorMap("Summer", GREEN, CSSColor.YELLOW, DEFAULT_COLORS, invert);
  }

  /**
   * Creates the winter map.
   *
   * @return the color map
   */
  public static ColorMap createWinterMap() {
    return createWinterMap(false);
  }

  /**
   * Creates the winter map.
   *
   * @param invert the invert
   * @return the color map
   */
  public static ColorMap createWinterMap(boolean invert) {
    return createTwoColorMap("Winter", BLUE, WINTERGREEN, DEFAULT_COLORS, invert);
  }

  /**
   * Creates the autumn map.
   *
   * @return the color map
   */
  public static ColorMap createAutumnMap() {
    return createAutumnMap(false);
  }

  /**
   * Creates the autumn map.
   *
   * @param invert the invert
   * @return the color map
   */
  public static ColorMap createAutumnMap(boolean invert) {
    return createTwoColorMap("Autumn", CSSColor.RED, CSSColor.YELLOW, DEFAULT_COLORS, invert);
  }

  /**
   * Creates the gray map.
   *
   * @return the color map
   */
  public static ColorMap createGrayMap() {
    return createGrayMap(false);
  }

  /**
   * Creates the gray map.
   *
   * @param invert the invert
   * @return the color map
   */
  public static ColorMap createGrayMap(boolean invert) {
    return createTwoColorMap("Gray", CSSColor.WHITE, CSSColor.BLACK, DEFAULT_COLORS, invert);
  }

  /**
   * Creates the white blue map.
   *
   * @return the color map
   */
  public static ColorMap createWhiteBlueMap() {
    return createWhiteBlueMap(false);
  }

  /**
   * Creates the white blue map.
   *
   * @param invert the invert
   * @return the color map
   */
  public static ColorMap createWhiteBlueMap(boolean invert) {
    return createTwoColorMap("White Blue", CSSColor.WHITE, BLUE, DEFAULT_COLORS, invert);
  }

  public static ColorMap createWhiteYellowMap() {
    return createWhiteYellowMap(false);
  }

  /**
   * Creates the white blue map.
   *
   * @param invert the invert
   * @return the color map
   */
  public static ColorMap createWhiteYellowMap(boolean invert) {
    return createTwoColorMap("White Yellow", CSSColor.WHITE, YELLOW, DEFAULT_COLORS, invert);
  }

  /**
   * Creates the blue yellow map.
   *
   * @return the color map
   */
  public static ColorMap createBlueYellowMap() {
    return createBlueYellowMap(false);
  }

  /**
   * Creates the blue yellow map.
   *
   * @param invert the invert
   * @return the color map
   */
  public static ColorMap createBlueYellowMap(boolean invert) {
    return createTwoColorMap("Blue Yellow", BLUE, CSSColor.YELLOW, DEFAULT_COLORS, invert);
  }

  /**
   * Creates the white red map.
   *
   * @return the color map
   */
  public static ColorMap createWhiteRedMap() {
    return createWhiteRedMap(false);
  }

  /**
   * Creates the white red map.
   *
   * @param invert the invert
   * @return the color map
   */
  public static ColorMap createWhiteRedMap(boolean invert) {
    return createTwoColorMap("White Red", CSSColor.WHITE, CSSColor.RED, DEFAULT_COLORS, invert);
  }

  /**
   * Creates the white green map.
   *
   * @return the color map
   */
  public static ColorMap createWhiteGreenMap() {
    return createWhiteGreenMap(false);
  }

  /**
   * Creates the white green map.
   *
   * @param invert the invert
   * @return the color map
   */
  public static ColorMap createWhiteGreenMap(boolean invert) {
    return createTwoColorMap("White Green", CSSColor.WHITE, GREEN, DEFAULT_COLORS, invert);
  }

  /**
   * Creates the white blue map.
   *
   * @param name  the name
   * @param color the color
   * @return the color map
   */
  public static ColorMap createWhiteToColorMap(String name, Color color) {
    return createWhiteToColorMap(name, color, DEFAULT_COLORS, false);
  }

  /**
   * Creates the white to color map.
   *
   * @param name    the name
   * @param color   the color
   * @param colors  the colors
   * @param reverse the reverse
   * @return the color map
   */
  public static ColorMap createWhiteToColorMap(String name, Color color, int colors, boolean reverse) {
    return createTwoColorMap(name, Color.WHITE, color, colors, reverse);
  }

}
