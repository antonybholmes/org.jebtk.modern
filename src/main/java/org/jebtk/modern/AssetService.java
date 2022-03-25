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
package org.jebtk.modern;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import org.jebtk.core.ColorUtils;
import org.jebtk.core.Resources;
import org.jebtk.core.collections.DefaultHashMap;
import org.jebtk.core.collections.DefaultHashMapCreator;
import org.jebtk.core.collections.EntryCreator;
import org.jebtk.core.collections.HashMapCreator;
import org.jebtk.core.collections.IterMap;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.graphics.icons.ModernImageIcon;
import org.jebtk.modern.graphics.icons.ModernVectorIcon;
import org.jebtk.modern.graphics.icons.MultiStateIcon;

/**
 * Allows data from a JAR to be loaded into memory.
 *
 * @author Antony Holmes
 *
 */
public class AssetService {

  /**
   * The Class UIServiceLoader.
   */
  private static class AssetServiceLoader {

    /** The Constant INSTANCE. */
    private static final AssetService INSTANCE = new AssetService();
  }

  /**
   * Gets the single instance of SettingsService.
   *
   * @return single instance of SettingsService
   */
  public static AssetService getInstance() {
    return AssetServiceLoader.INSTANCE;
  }

  /**
   * The constant ICON_SIZE_8.
   */
  public static final int ICON_SIZE_8 = 8;

  /**
   * The constant ICON_SIZE_16.
   */
  public static final int ICON_SIZE_16 = 16;

  /**
   * The constant ICON_SIZE_18.
   */
  public static final int ICON_SIZE_18 = 18;

  /**
   * The constant ICON_SIZE_20.
   */
  public static final int ICON_SIZE_20 = 20;

  /**
   * The constant ICON_SIZE_24.
   */
  public static final int ICON_SIZE_24 = 24;

  /**
   * The constant ICON_SIZE_28.
   */
  public static final int ICON_SIZE_28 = 28;

  /**
   * The constant ICON_SIZE_32.
   */
  public static final int ICON_SIZE_32 = 32;

  /**
   * The constant ICON_SIZE_36.
   */
  public static final int ICON_SIZE_36 = 36;

  /**
   * The constant ICON_SIZE_42.
   */
  public static final int ICON_SIZE_42 = 42;

  /**
   * The constant ICON_SIZE_48.
   */
  public static final int ICON_SIZE_48 = 48;

  /**
   * The constant ICON_SIZE_64.
   */
  public static final int ICON_SIZE_64 = 64;

  /**
   * The constant ICON_SIZE_72.
   */
  public static final int ICON_SIZE_72 = 72;

  /**
   * The constant ICON_SIZE_96.
   */
  public static final int ICON_SIZE_96 = 96;

  /**
   * The constant ICON_SIZE_128.
   */
  public static final int ICON_SIZE_128 = 128;

  /**
   * The constant ICON_SIZE_256.
   */
  public static final int ICON_SIZE_256 = 256;

  /**
   * The constant ICON_SIZE_25.
   */
  public static final int ICON_SIZE_25 = 25;

  /**
   * The constant ICON_SIZE_26.
   */
  public static final int ICON_SIZE_26 = 26;

  /**
   * The constant PATH.
   */
  private static final String PATH = "res/icons/";

  /**
   * The map.
   */
  private final Map<Integer, IterMap<String, MultiStateIcon>> mRasterIconMap = DefaultHashMap
      .create(new DefaultHashMapCreator<String, MultiStateIcon>(() -> new MultiStateIcon()));

  /**
   * The member vector icon map.
   */
  private final Map<String, IterMap<Integer, ModernIcon>> mVectorIconMap = DefaultHashMap
      .create(new HashMapCreator<Integer, ModernIcon>()); // .createnew
  // HashMap<Class<?>,
  // Map<Integer,
  // ModernIcon>>();

  /**
   * Instantiates a new UI resources.
   */
  private AssetService() {
    // do nothing
  }

  /**
   * Load icon64.
   *
   * @param name the name
   * @return the modern icon
   */
  public MultiStateIcon loadIcon64(String name) {
    return loadIcon(name, 64);
  }

  /**
   * Load an icon of a given size.
   *
   * @param name the name
   * @param size the size
   * @return the modern icon
   */
  public MultiStateIcon loadIcon(String name, int size) {
    return loadIcon(name, size, size);
  }

  /**
   * Load icon of a particular size and scale.
   * 
   * @param name     Name of bitmapped icon.
   * @param size     Original size.
   * @param iconSize Scaled size.
   * @return
   */
  public MultiStateIcon loadIcon(String name, int size, int iconSize) {
    if (!mRasterIconMap.containsKey(size) || !mRasterIconMap.get(size).containsKey(name)) {
      // If the icon is not cached, cache it

      StringBuilder resource;
      URL imgURL;

      resource = new StringBuilder(PATH).append(name).append("_").append(size).append("_").append(size).append(".png");

      imgURL = Resources.getResource(resource.toString());

      if (imgURL != null) {
        mRasterIconMap.get(iconSize).get(name).add("default", new ModernImageIcon(imgURL));

        // mRasterIconMap.get(iconSize).get(name).add("selected",
        // mRasterIconMap.get(iconSize).get(name).getState("default"));
        // mRasterIconMap.get(iconSize).get(name).add("highlight",
        // mRasterIconMap.get(iconSize).get(name).getState("default"));
      }

      resource = new StringBuilder(PATH).append(name).append("_selected").append("_").append(size).append("_")
          .append(size).append(".png");

      imgURL = Resources.getResource(resource.toString());

      if (imgURL != null) {
        mRasterIconMap.get(iconSize).get(name).add("selected", new ModernImageIcon(imgURL));
      }

      resource = new StringBuilder(PATH).append(name).append("_highlight").append("_").append(size).append("_")
          .append(size).append(".png");

      imgURL = Resources.getResource(resource.toString());

      if (imgURL != null) {
        mRasterIconMap.get(iconSize).get(name).add("highlight", new ModernImageIcon(imgURL));
      }
    }

    return mRasterIconMap.get(iconSize).get(name);
  }

//  public ImageView loadFxIcon(String name, int size) {
//    return new ImageView(loadIcon(name, size).getFxImage(size));
//  }
//  
//  public <T extends ModernVectorIcon> ImageView loadFxIcon(Class<T> iconClass, int size) {
//    return new ImageView(loadIcon(iconClass, size).getFxImage(size));
//  }

  /**
   * Load icon.
   *
   * @param <T>       the generic type
   * @param iconClass the icon class
   * @param size      the size
   * @return the modern icon
   */
  /*
   * public <T extends ModernVectorIcon> ModernIcon loadIcon(Class<T> iconClass,
   * int size) { String name = getClassName(iconClass);
   * 
   * if (!mVectorIconMap.get(name).containsKey(size)) { try {
   * mVectorIconMap.get(name).put(size, new RasterIcon(iconClass.newInstance(),
   * size)); } catch (InstantiationException e) { e.printStackTrace(); } catch
   * (IllegalAccessException e) { e.printStackTrace(); } }
   * 
   * return mVectorIconMap.get(name).get(size); }
   */

  /*
   * public <T extends ModernVectorColorIcon> ModernIcon loadIcon(Class<T>
   * iconClass, Color color, int size) {
   * 
   * String name = getClassName(iconClass);
   * 
   * if (!mVectorIconMap.get(name).containsKey(size)) { try { Constructor<T> cons
   * = iconClass.getConstructor(Color.class);
   * 
   * mVectorIconMap.get(name).put(size, new RasterIcon(cons.newInstance(color),
   * size)); } catch (InstantiationException e) { e.printStackTrace(); } catch
   * (IllegalAccessException e) { e.printStackTrace(); } catch
   * (NoSuchMethodException e) { e.printStackTrace(); } catch (SecurityException
   * e) { e.printStackTrace(); } catch (IllegalArgumentException e) {
   * e.printStackTrace(); } catch (InvocationTargetException e) {
   * e.printStackTrace(); } }
   * 
   * return mVectorIconMap.get(name).get(size); }
   */

  public <T extends ModernVectorIcon> ModernIcon loadIcon(Class<T> iconClass, int size) {
    return loadIcon(getClassName(iconClass), iconClass, size);
  }

  /**
   * Initializes and caches a vector icon supplying a given size and color as
   * parameters.
   *
   * @param <T>       the generic type
   * @param iconClass the icon class
   * @param color     the color
   * @param size      the size
   * @return the modern icon
   */
  public <T extends ModernVectorIcon> ModernIcon loadIcon(Class<T> iconClass, Color color, int size) {
    return loadIcon(getClassName(iconClass, color), iconClass, color, size);
  }

  /**
   * Load icon.
   *
   * @param <T>       the generic type
   * @param iconClass the icon class
   * @param color1    the color 1
   * @param color2    the color 2
   * @param size      the size
   * @return the modern icon
   */
  public <T extends ModernVectorIcon> ModernIcon loadIcon(Class<T> iconClass, Color color1, Color color2, int size) {
    return loadIcon(getClassName(iconClass, color1, color2), iconClass, color1, color2, size);
  }

  /**
   * Load icon.
   *
   * @param <T>       the generic type
   * @param name      the name
   * @param iconClass the icon class
   * @param size      the size
   * @return the modern icon
   */
  public <T extends ModernVectorIcon> ModernIcon loadIcon(String name, Class<T> iconClass, int size) {

    if (!mVectorIconMap.get(name).containsKey(size)) {
      ModernIcon rastorIcon = ModernVectorIcon.createRastorIcon(iconClass, size);

      mVectorIconMap.get(name).put(size, rastorIcon);
    }

    return mVectorIconMap.get(name).get(size);
  }

  /**
   * Load icon.
   *
   * @param <T>       the generic type
   * @param name      the name
   * @param iconClass the icon class
   * @param color1    the color 1
   * @param color2    the color 2
   * @param size      the size
   * @return the modern icon
   */
  public <T extends ModernVectorIcon> ModernIcon loadIcon(String name, Class<T> iconClass, Color color1, Color color2,
      int size) {

    if (!mVectorIconMap.get(name).containsKey(size)) {
      ModernIcon rastorIcon = ModernVectorIcon.createRastorIcon(iconClass, color1, color2, size);

      mVectorIconMap.get(name).put(size, rastorIcon);
    }

    return mVectorIconMap.get(name).get(size);
  }

  /**
   * Load icon.
   *
   * @param <T>       the generic type
   * @param name      the name
   * @param iconClass the icon class
   * @param color1    the color 1
   * @param size      the size
   * @return the modern icon
   */
  public <T extends ModernVectorIcon> ModernIcon loadIcon(String name, Class<T> iconClass, Color color1, int size) {

    if (!mVectorIconMap.get(name).containsKey(size)) {
      ModernIcon rastorIcon = ModernVectorIcon.createRastorIcon(iconClass, color1, size);

      mVectorIconMap.get(name).put(size, rastorIcon);
    }

    return mVectorIconMap.get(name).get(size);
  }

  /**
   * Returns a standardized version of the class name.
   *
   * @param c the c
   * @return the class name
   */
  public static String getClassName(final Class<?> c) {
    return c.getName();
  }

  /**
   * Gets the class name.
   *
   * @param c     the c
   * @param color the color
   * @return the class name
   */
  public static String getClassName(final Class<?> c, Color color) {
    return getClassName(c) + "_" + ColorUtils.toHtml(color);
  }

  /**
   * Gets the class name.
   *
   * @param c      the c
   * @param color1 the color 1
   * @param color2 the color 2
   * @return the class name
   */
  public static String getClassName(final Class<?> c, Color color1, Color color2) {
    return getClassName(c) + "_" + ColorUtils.toHtml(color1) + "_" + ColorUtils.toHtml(color2);
  }

  /**
   * Load an image resource.
   *
   * @param name the name
   * @return the modern icon
   */
  public static ModernIcon loadImage(String name, int size) {
    return loadImage(name, "res/images", size);
  }

  /**
   * Load an image resource.
   *
   * @param name the name
   * @param path the path
   * @return the modern icon
   */
  public static final ModernIcon loadImage(String name, String path, int size) {
    String resource = path + "/" + name + ".png";

    // System.out.println("Loading icon: " + resource);

    URL imgURL = ClassLoader.getSystemClassLoader().getResource(resource);

    if (imgURL != null) {
      return new ModernImageIcon(imgURL);
    } else {
      System.err.println("Couldn't find file: " + resource);

      return null;
    }
  }

  /**
   * Gets the clipboard text.
   *
   * @return the clipboard text
   */
  public static String getClipboardText() {
    String ret = null;

    Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();

    Transferable clipTf = sysClip.getContents(null);

    if (clipTf != null) {
      if (clipTf.isDataFlavorSupported(DataFlavor.stringFlavor)) {
        try {
          ret = (String) clipTf.getTransferData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException | IOException e) {
          e.printStackTrace();
        }
      }
    }

    return ret;
  }

}
