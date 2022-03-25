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
package org.jebtk.modern.graphics.icons;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.jebtk.core.Props;
import org.jebtk.modern.graphics.ImageUtils;

/**
 * Represents a vector icon that uses graphics primitives to create a scalable
 * image. Since vector icons require more computation, the RastorIcon class can
 * cache the vector icon as a bitmapped image at a particular size so it can be
 * used repeated on a GUI for greater efficiency.
 * 
 * This class contains multiple static icon constants that can be shared amongst
 * multiple classes.
 * 
 * @author Antony Holmes
 *
 */
public abstract class ModernVectorIcon extends MultiResIcon {

  /**
   * The constant HIDE_UP_16_ICON.
   */
  public static final ModernIcon HIDE_UP_16_ICON = new Raster16Icon(new CheveronUpVectorIcon(), WHITE_PROPS);

  /**
   * The constant HIDE_DOWN_16_ICON.
   */
  public static final ModernIcon HIDE_DOWN_16_ICON = new Raster16Icon(new CheveronDownVectorIcon(), WHITE_PROPS);

  /**
   * The constant HIDE_LEFT_ICON.
   */
  public static final ModernIcon HIDE_LEFT_ICON = new Raster16Icon(new CheveronLeftVectorIcon());

  /**
   * The constant HIDE_RIGHT_ICON.
   */
  public static final ModernIcon HIDE_RIGHT_ICON = new Raster16Icon(new CheveronRightVectorIcon());

  /**
   * The constant UP_16_ICON.
   */
  public static final ModernIcon UP_16_ICON = new Raster16Icon(new HideUpVectorIcon());

  /**
   * The constant DOWN_16_ICON.
   */
  public static final ModernIcon DOWN_16_ICON = new Raster16Icon(new HideDownVectorIcon());

  /**
   * The constant LEFT_16_ICON.
   */
  public static final ModernIcon LEFT_16_ICON = new Raster16Icon(new HideLeftVectorIcon());

  /**
   * The constant RIGHT_16_ICON.
   */
  public static final ModernIcon RIGHT_16_ICON = new Raster16Icon(new HideRightVectorIcon());

  /**
   * Returns -1 since a vector icon has no intrinsic width.
   *
   * @return the height
   */
  @Override
  public int getWidth() {
    return -1;
  }

  /**
   * Returns -1 since a vector icon has no intrinsic height.
   *
   * @return the height
   */
  @Override
  public int getHeight() {
    return -1;
  }

  @Override
  public BufferedImage getImage(int w, int h, Props props) {
    if (!mBufMap.containsKey(w)) {
      BufferedImage buf = ImageUtils.createImage(w, w);

      Graphics2D g2 = (Graphics2D) buf.createGraphics();

      ImageUtils.setQualityHints(g2);

      try {
        drawIcon(g2, w, h, props);
      } finally {
        g2.dispose();
      }

      mBufMap.put(w, buf);
    }

    return mBufMap.get(w);
  }

  //
  // Static methods
  //

  /**
   * Create a rastorized icon supplying two colors as parameters.
   *
   * @param <T>       the generic type
   * @param iconClass the icon class
   * @param size      the size
   * @return the modern icon
   */
  public static <T extends ModernVectorIcon> ModernIcon createRastorIcon(Class<T> iconClass, int size) {

    ModernIcon icon = createIcon(iconClass);

    if (icon != null) {
      return new RasterIcon(icon, size);
    } else {
      return null;
    }
  }

  /**
   * Create a rastorized icon supplying two colors as parameters.
   *
   * @param <T>       the generic type
   * @param iconClass the icon class
   * @param color1    the color 1
   * @param size      the size
   * @return the modern icon
   */
  public static <T extends ModernVectorIcon> ModernIcon createRastorIcon(Class<T> iconClass, Color color1, int size) {

    ModernIcon icon = createIcon(iconClass); // , color1);

    if (icon != null) {
      return new RasterIcon(icon, size, new Props().set("color", color1));
    } else {
      return null;
    }
  }

  /**
   * Creates the rastor icon.
   *
   * @param <T>       the generic type
   * @param iconClass the icon class
   * @param color1    the color 1
   * @param color2    the color 2
   * @param size      the size
   * @return the modern icon
   */
  public static <T extends ModernVectorIcon> ModernIcon createRastorIcon(Class<T> iconClass, Color color1, Color color2,
      int size) {

    ModernIcon icon = createIcon(iconClass); // , color1, color2);

    if (icon != null) {
      return new RasterIcon(icon, size, new Props().set("color1", color1).set("color2", color2));
    } else {
      return null;
    }
  }

  /**
   * Creates the icon.
   *
   * @param <T>       the generic type
   * @param iconClass the icon class
   * @return the modern icon
   */
  public static <T extends ModernVectorIcon> ModernIcon createIcon(Class<T> iconClass) {

    T icon = null;

    try {
      icon = iconClass.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
    }

    return icon;
  }

  /**
   * Creates the icon.
   *
   * @param <T>       the generic type
   * @param iconClass the icon class
   * @param color1    the color 1
   * @param color2    the color 2
   * @return the modern icon
   */
  public static <T extends ModernVectorIcon> ModernIcon createIcon(Class<T> iconClass, Color color1, Color color2) {

    T icon = null;

    try {
      Constructor<T> cons = iconClass.getConstructor(Color.class, Color.class);

      icon = cons.newInstance(color1, color2);
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (SecurityException e) {
      e.printStackTrace();
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }

    return icon;
  }

  /**
   * Creates the icon.
   *
   * @param <T>       the generic type
   * @param iconClass the icon class
   * @param color1    the color 1
   * @return the modern icon
   */
  public static <T extends ModernVectorIcon> ModernIcon createIcon(Class<T> iconClass, Color color1) {

    T icon = null;

    try {
      Constructor<T> cons = iconClass.getConstructor(Color.class);

      icon = cons.newInstance(color1);
    } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
        | IllegalArgumentException | InvocationTargetException e) {
      e.printStackTrace();
    }

    return icon;
  }

}
