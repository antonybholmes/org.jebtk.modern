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
package org.jebtk.modern.css;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.jebtk.core.ColorUtils;

import org.jebtk.core.Resources;
import org.jebtk.core.collections.IterHashMap;
import org.jebtk.core.collections.IterMap;
import org.jebtk.core.io.FileUtils;
import org.jebtk.core.io.PathUtils;
import org.jebtk.core.text.TextUtils;
import org.jebtk.modern.theme.MaterialService;
import org.jebtk.modern.theme.ThemeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CSSKeyFramesService {

  public static final String COMPONENT = "component";

  private static final Logger LOG = LoggerFactory.getLogger(CSSKeyFramesService.class);

  private static final Path DEFAULT_XML_FILE = PathUtils.getPath("res/style.xml");

  private final IterMap<String, CSS2Class> mStyleMap = new IterHashMap<>();

  private boolean mAutoLoad = true;

  private static class KeyFramesServiceLoader {

    /**
     * The Constant INSTANCE.
     */
    private static final CSSKeyFramesService INSTANCE = new CSSKeyFramesService();
  }

  /**
   * Gets the single instance of SettingsService.
   *
   * @return single instance of SettingsService
   */
  public static CSSKeyFramesService getInstance() {
    return KeyFramesServiceLoader.INSTANCE;
  }

  private class StyleClassXmlHandler extends DefaultHandler {

    private String mClass;
    private int mKeyFrame = CSSProp.FROM;

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
     * java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

      switch (qName) {
        case "keyframe":
          String index = attributes.getValue("index");
          switch (index) {
            case "from":
              mKeyFrame = CSSProp.FROM;
              break;
            case "to":
              mKeyFrame = CSSProp.TO;
              break;
            default:
              mKeyFrame = Integer.parseInt(attributes.getValue("index"));
              break;
          }
          break;

        case "inherits":
          String mClass2 = attributes.getValue("class");
          // Copy Props from one class to another
          getStyleClass(mClass).update(getStyleClass(mClass2));
          break;

        case "class":
          mClass = attributes.getValue("name");
          break;

        case "property":
          CSSPropName name = CSSPropName.parse(attributes.getValue("name"));
          String value = attributes.getValue("value");
          String v = value.toLowerCase();

          CSS2Class cls = getStyleClass(mClass);

          if (TextUtils.isInt(v)) {
            cls.set(name, Integer.parseInt(v));
          } else if (v.endsWith("%")) {
            cls.set(name, new CSSNumPercent(Integer.parseInt(v.substring(0, v.length() - 1))));
          } else if (v.endsWith("px")) {
            cls.set(name, Integer.parseInt(v.substring(0, v.length() - 2)));
          } else if (v.equals("true")) {
            // cls.set(name, true);
          } else if (v.equals("false")) {
            // cls.set(name, false);
          } else {
            Color color;

            if (v.contains("trans")) {
              color = ColorUtils.TRANS;
            } else if (v.startsWith("#")) {
              color = ColorUtils.decodeHtmlColor(v);
            } else if (v.startsWith("colors.theme-32")) {
              int i = Integer.parseInt(v.substring(v.lastIndexOf("-") + 1));
              color = ThemeService.getInstance().getColors().getTheme32(i);
            } else if (v.startsWith("colors.gray-32")) {
              int i = Integer.parseInt(v.substring(v.lastIndexOf("-") + 1));
              color = ThemeService.getInstance().getColors().getGray32(i);
            } else if (v.startsWith("colors.material")) {
              color = MaterialService.getInstance().getColor(v.replace("colors.material.", TextUtils.EMPTY_STRING));
            } else {
              switch (v) {
                case "white":
                  color = Color.WHITE;
                  break;
                case "black":
                  color = Color.BLACK;
                  break;
                case "red":
                  color = Color.RED;
                  break;
                case "blue":
                  color = Color.BLUE;
                  break;
                case "green":
                  color = Color.GREEN;
                  break;
                default:
                  color = null;
                  break;
              }
            }

            if (color != null) {
              if (mKeyFrame == CSSProp.TO) {
                System.err.println("setting to color " + mClass + " " + name + " " + v + " " + cls.getColor(name) + " " + color + " " + CSSColorMapService.getInstance().get(cls.getColor(name).getFromKeyFrame().toString(), ColorUtils.toHtml(color)));

                cls.set(name, CSSColorMapService.getInstance().get(cls.getColor(name).getFromKeyFrame().toString(), ColorUtils.toHtml(color)));
              } else {
                cls.set(name, CSSColorMapService.getInstance().get(ColorUtils.toHtml(color)));

                System.err.println("setting color " + mClass + " " + name + " " + v + " " + color + " " + cls);
              }
            }
          }

          break;

        default:
          break;
      }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    @Override
    public void endElement(String uri, String localName, String qName) {
      if (qName.equals("keyframe")) {
        // reset to default
        mKeyFrame = CSSProp.FROM;
      }
    }
  }

  private CSSKeyFramesService() {
    // mStyleMap.put(COMPONENT, COMPONENT_STYLE);

    // set("border-radius", 6);
    // getStyleClass("quick-access-button").set("background-color",
    // ColorUtils.getTransparentColor75(Color.WHITE));

    /*
     * get("primary-dialog-button-f0") .set("border-color",
     * ThemeService.getInstance().getColors().getTheme32(24))
     * .set("background-color",
     * ThemeService.getInstance().getColors().getTheme32(20));
     * 
     * get("primary-dialog-button-f100") .set("border-color",
     * ThemeService.getInstance().getColors().getTheme32(28))
     * .set("background-color",
     * ThemeService.getInstance().getColors().getTheme32(24));
     */
  }

  private synchronized void autoLoad() {
    if (mAutoLoad) {
      // Set this here to stop infinite recursive calling
      // of this method.
      mAutoLoad = false;

      try {
        autoLoadXml();
      } catch (IOException | URISyntaxException | SAXException | ParserConfigurationException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Auto load xml.
   *
   * @throws IOException Signals that an I/O exception has occurred.
   * @throws URISyntaxException the URI syntax exception
   * @throws SAXException the SAX exception
   * @throws ParserConfigurationException the parser configuration exception
   */
  private synchronized void autoLoadXml()
          throws IOException, URISyntaxException, SAXException, ParserConfigurationException {
    LOG.info("Auto loading styles...");

    for (String res : Resources.getInstance()) {
      if (!res.contains("style.xml")) {
        continue;
      }

      LOG.info("Loading styles from {}...", res);

      try (InputStream is = Resources.getResInputStream(res)) {
        loadXml(is);
      }
    }

    // Load local settings that may overwrite internal settings.
    loadXml(DEFAULT_XML_FILE);

    // Load any per user settings. We flag these as being updated so
    // that on the next write cycle, they will be written back to the
    // settings file.
    // loadXml(SettingsReaderUserXml.USER_XML_FILE, true);
    LOG.info("Finished loading styles.");
  }

  private synchronized boolean loadXml(Path file) throws SAXException, IOException, ParserConfigurationException {
    if (FileUtils.exists(file)) {
      try (InputStream is = FileUtils.newBufferedInputStream(file)) {
        loadXml(is);
      }

      return true;
    } else {
      return false;
    }
  }

  private synchronized boolean loadXml(InputStream is) throws SAXException, IOException, ParserConfigurationException {
    if (is == null) {
      return false;
    }

    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser saxParser = factory.newSAXParser();

    StyleClassXmlHandler handler = new StyleClassXmlHandler();

    saxParser.parse(is, handler);

    return true;
  }

  /**
   * Return the style class from the default <code>KeyFrames.FROM</code> key
   * frame.
   *
   * @param name
   * @return
   */
//  public CSSClass getStyleClass(String name) {
//    return getStyleClass(CSSKeyFrames.FROM, name);
//  }
//
//  public CSSClass getToStyleClass(String name) {
//    return getStyleClass(CSSKeyFrames.TO, name);
//  }
  public CSS2Class getStyleClass(String name) {
    autoLoad();

    System.err.println("kfs contains:" + name + " " + contains(name));

    if (!contains(name)) {
      mStyleMap.put(name, new CSS2Class(name));
    }

    return mStyleMap.get(name);
  }

  /**
   * Returns true if the given key frame contains a given style class.
   *
   * @param name
   * @return
   */
  public boolean contains(String name) {
    autoLoad();
    
    return mStyleMap.containsKey(name);
  }

  public CSS2Class getCompStyleClass() {
    return getStyleClass(COMPONENT);
  }

  /*
   * private static final String getName(Collection<String> styles) {
   * Iterator<String> iter = styles.iterator();
   * 
   * StringBuilder buffer = new StringBuilder(iter.next());
   * 
   * while (iter.hasNext()) { buffer.append("-").append(iter.next()); }
   * 
   * return buffer.toString(); }
   */
}
