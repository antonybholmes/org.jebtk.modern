/**
 * Copyright (c) 2016, Antony Holmes
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
package org.jebtk.modern.graphics;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.image.BufferedImage;
import java.util.Collection;

import org.jebtk.core.collections.CollectionUtils;
import org.jebtk.core.geom.DoubleDim;
import org.jebtk.core.geom.IntDim;
import org.jebtk.core.geom.IntRect;

/**
 * ImageUtils provides methods for dealing with graphics and image objects.
 */
public class ImageUtils {

  /** The Constant DEFAULT_GRAPHICS. */
  private static final Graphics2D DEFAULT_GRAPHICS;

  static {
    // Create a default graphics context that can be used to size
    // Widgets based on the font
    BufferedImage newImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);

    DEFAULT_GRAPHICS = newImage.createGraphics();
  }

  /**
   * Instantiates a new image utils.
   */
  private ImageUtils() {
    // Do nothing
  }

  /**
   * Creates a graphics object of the given dimensions.
   *
   * @param width  the width
   * @param height the height
   * @return the graphics 2 D
   */
  public static Graphics2D createGraphics(int width, int height) {
    return createGraphics(createImage(width, height));
  }

  /**
   * Creates a graphics object from an existing image.
   *
   * @param image the image
   * @return the graphics 2 D
   */
  public static Graphics2D createAATextGraphics(final BufferedImage image) {
    Graphics2D g2 = createGraphics(image);

    setAAHints(g2);

    return g2;
  }

  public static Graphics2D createAAGraphics(final BufferedImage image, AAModes modes) {
    Graphics2D g2 = createGraphics(image);

    setAAHints(g2, modes);

    return g2;
  }

  /**
   * Creates the graphics.
   *
   * @param image the image
   * @return the graphics 2 D
   */
  public static Graphics2D createGraphics(final BufferedImage image) {
    return (Graphics2D) image.createGraphics();
  }

  /**
   * Creates the image.
   *
   * @param dim the dim
   * @return the buffered image
   */
  public static BufferedImage createImage(final Dimension dim) {
    return createImage(dim.width, dim.height);
  }

  /**
   * Creates the image.
   *
   * @param dim the dim
   * @return the buffered image
   */
  public static BufferedImage createImage(final IntDim dim) {
    return createImage(dim.getW(), dim.getH());
  }

  public static BufferedImage createImage(final DoubleDim dim) {
    return createImage((int) Math.max(1, dim.getW()), (int) Math.max(1, dim.getH()));
  }

  /**
   * Creates the image.
   *
   * @param width the width
   * @return the buffered image
   */
  public static BufferedImage createImage(int width) {
    return createImage(width, width);
  }

  /**
   * Create a buffered image that supports transparency.
   *
   * @param width  the width
   * @param height the height
   * @return the buffered image
   */
  public static BufferedImage createImage(int width, int height) {
    return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
  }

  public static Graphics2D createAAGraphics(final Graphics g, AAMode type, AAMode... types) {
    Graphics2D g2 = clone(g);

    // setAAHints(g2);

    applyHints(g2, type);

    for (AAMode t : types) {
      applyHints(g2, t);
    }

    return g2;
  }

  public static Graphics2D createAAGraphics(final Graphics g, AAModes modes) {
    if (modes.size() == 0) {
      return (Graphics2D) g;
    }

    Graphics2D g2 = clone(g);

    for (AAMode t : modes) {
      applyHints(g2, t);
    }

    return g2;
  }

  public static Graphics2D createGraphics(final Graphics g, final Collection<AAMode> types) {
    Graphics2D g2 = clone(g);

    if (CollectionUtils.isNotNullOrEmpty(types)) {
      // setAAHints(g2);

      for (AAMode t : types) {
        applyHints(g2, t);
      }
    }

    return g2;
  }

  public static Graphics2D createAAGraphics(final Graphics g) {
    return createAAGraphics(g, AAMode.AA);
  }

  /**
   * Clone the graphics context and enable text anti-aliasing hints to make text
   * look better on screen.
   *
   * @param g the g
   * @return the graphics 2 D
   */
  public static Graphics2D createAATextGraphics(final Graphics g) {
    return createAAGraphics(g, AAMode.AA, AAMode.TEXT);
  }

  /**
   * Creates the AA stroke graphics.
   *
   * @param g the g
   * @return the graphics 2 D
   */
  public static Graphics2D createAAStrokeGraphics(final Graphics g) {
    return createAAGraphics(g, AAMode.AA, AAMode.TEXT, AAMode.STROKE);
  }

  public static void setAAHints(final Graphics2D g2, AAModes modes) {
    if (modes.size() == 0) {
      return;
    }

    for (AAMode t : modes) {
      applyHints(g2, t);
    }
  }

  private static void applyHints(Graphics2D g, AAMode type) {
    switch (type) {
    case TEXT:
      setAATextHints(g);
      break;
    case STROKE:
      setAAStrokeHints(g);
      break;
    default:
      setAAHints(g);
      break;
    }
  }

  /**
   * Sets a default set of rendering hints for high quality, anti-aliased
   * graphics.
   *
   * @param g2 the new AA hints
   */
  public static final void setAAHints(Graphics2D g2) {
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
  }

  /**
   * Sets the AA text hints.
   *
   * @param g2 the new AA text hints
   */
  public static void setAATextHints(Graphics2D g2) {
    g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
  }

  /**
   * Sets hits for drawing circles with sub-pixel accuracy. It is not recommended
   * to use these hints for general purpose rendering as they produce somewhat
   * pixelated results for other shapes and text.
   *
   * @param g2 the new stroke hints
   */
  public static final void setAAStrokeHints(Graphics2D g2) {
    g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
  }

  public static void setQualityHints(Graphics2D g2) {
    g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
    g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
    g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
  }

  /**
   * Return the height occupied by a font in the current graphics context.
   *
   * @param g2 the g2
   * @return the font height
   */
  public static int getFontHeight(Graphics2D g2) {
    return g2.getFontMetrics().getAscent() + g2.getFontMetrics().getDescent();
  }

  /**
   * Gets the string width.
   *
   * @param text the text
   * @return the string width
   */
  public static int getStringWidth(String text) {
    return getStringWidth(DEFAULT_GRAPHICS, text);
  }

  /**
   * Gets the string width.
   *
   * @param g2   the g2
   * @param text the text
   * @return the string width
   */
  public static int getStringWidth(Graphics2D g2, String text) {
    return getStringWidth(g2, g2.getFont(), text);
  }

  /**
   * Gets the string width.
   *
   * @param font the font
   * @param text the text
   * @return the string width
   */
  public static int getStringWidth(Font font, String text) {
    return getStringWidth(DEFAULT_GRAPHICS, font, text);
  }

  /**
   * Gets the string width.
   *
   * @param g2   the g 2
   * @param font the font
   * @param text the text
   * @return the string width
   */
  public static int getStringWidth(Graphics2D g2, Font font, String text) {
    if (text == null) {
      return 0;
    }

    Graphics g2Temp = clone(g2);

    int w = -1;

    try {
      g2Temp.setFont(font);
      w = g2Temp.getFontMetrics().stringWidth(text);
    } finally {
      g2Temp.dispose();
    }

    return w;
  }

  /**
   * Gets the font height.
   *
   * @param s the s
   * @return the font height
   */
  public static int getFontHeight(String s) {
    return getFontBounds(DEFAULT_GRAPHICS, s).height;
  }

  /**
   * Gets the font height.
   *
   * @param s    the s
   * @param font the font
   * @return the font height
   */
  public static int getFontHeight(String s, Font font) {

    Graphics2D g2 = clone(DEFAULT_GRAPHICS);

    int h = -1;

    try {
      g2.setFont(font);

      h = getFontBounds(g2, s).height;
    } finally {
      g2.dispose();
    }

    return h;

  }

  /**
   * Returns the height required to bound the given string. This is more accurate
   * than using the ascent and descent since it measures the actual characters
   * rather than the size of the largest character in the font.
   *
   * @param g2 The graphics context
   * @param s  A string.
   * @return the font height
   */
  public static int getFontHeight(Graphics2D g2, String s) {
    return getFontBounds(g2, s).height;
  }

  /**
   * Returns the rectangle required to bound the given string. This is more
   * accurate than using the ascent and descent since it measures the actual
   * characters rather than the size of the largest character in the font.
   *
   * @param g2 The graphics context
   * @param s  A string.
   * @return the font bounds
   */
  public static Rectangle getFontBounds(Graphics2D g2, String s) {
    FontRenderContext frc = g2.getFontRenderContext();
    GlyphVector gv = g2.getFont().createGlyphVector(frc, s);
    return gv.getPixelBounds(null, 0, 0);
  }

  /**
   * Gets the text Y pos center.
   *
   * @param font   the font
   * @param height the height
   * @return the text Y pos center
   */
  public static int getTextYPosCenter(Font font, int height) {

    Graphics2D g2 = clone(DEFAULT_GRAPHICS);

    int h = -1;

    try {
      g2.setFont(font);

      h = getTextYPosCenter(g2, height);
    } finally {
      g2.dispose();
    }

    return h;

  }

  /**
   * Gets the text y pos center.
   *
   * @param g2   the g2
   * @param rect the rect
   * @return the text y pos center
   */
  public static int getTextYPosCenter(Graphics2D g2, IntRect rect) {
    return getTextYPosCenter(g2, rect.getH());
  }

  /**
   * Gets the text Y pos center.
   *
   * @param height the height
   * @return the text Y pos center
   */
  public static int getTextYPosCenter(int height) {
    return getTextYPosCenter(DEFAULT_GRAPHICS, height);
  }

  /**
   * Returns the y position to draw text at so that is vertically centered on the
   * widget.
   *
   * @param g2     the g2
   * @param height the height
   * @return the text y pos center
   */
  public static int getTextYPosCenter(Graphics2D g2, int height) {
    return getTextYPosCenter(height, g2.getFontMetrics().getAscent(), g2.getFontMetrics().getDescent());
  }

  /**
   * Gets the text Y pos center.
   *
   * @param height  the height
   * @param ascent  the ascent
   * @param descent the descent
   * @return the text Y pos center
   */
  public static int getTextYPosCenter(int height, double ascent, double descent) {
    return (int) ((height + ascent - descent) / 2);
  }

  /**
   * Clone the graphics context.
   *
   * @param g the g
   * @return the graphics 2 D
   */
  public static Graphics2D clone(final Graphics g) {
    return (Graphics2D) g.create();
  }

  /**
   * Creates the stroke.
   *
   * @param lw the lw
   * @return the stroke
   */
  public static Stroke createStroke(int lw) {
    return new BasicStroke(lw);
  }

  /**
   * Fill a rectangle.
   *
   * @param g2   the g 2
   * @param rect the rect
   */
  public static void fillRect(Graphics2D g2, IntRect rect) {
    g2.fillRect(rect.getX(), rect.getY(), rect.getW(), rect.getH());
  }

  /**
   * Clamp a value to the range 0..255
   */
  public static int clamp(int c) {
    if (c < 0)
      return 0;
    if (c > 255)
      return 255;
    return c;
  }
}
