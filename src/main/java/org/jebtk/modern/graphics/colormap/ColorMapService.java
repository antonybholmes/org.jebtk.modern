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

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.jebtk.core.AppService;
import org.jebtk.core.io.FileUtils;
import org.jebtk.core.io.PathUtils;
import org.jebtk.core.json.Json;
import org.jebtk.core.json.JsonParser;
import org.jebtk.core.CSSColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Settings factory for providing global settings to an application. Can load
 * settings from an XML file or a text file.
 *
 * @author Antony Holmes
 *
 */
public class ColorMapService extends ColorMaps {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The Class ColorMapServiceLoader.
   */
  private static class ColorMapServiceLoader {

    /** The Constant INSTANCE. */
    private static final ColorMapService INSTANCE = new ColorMapService();
  }

  /**
   * Gets the single instance of SettingsService.
   *
   * @return single instance of SettingsService
   */
  public static ColorMapService getInstance() {
    return ColorMapServiceLoader.INSTANCE;
  }

  /** Stores color maps created by the user. */
  public static final Path USER_COLORMAP_XML_FILE = AppService.getInstance().getAppDir().resolve("user.colormaps.xml");

  /** The Constant COLORMAP_JSON_FILE. */
  public static final Path COLORMAP_JSON_FILE = PathUtils.getPath("user.colormaps.json");

  /** The Constant USER_COLORMAP_JSON_FILE. */
  public static final Path USER_COLORMAP_JSON_FILE = AppService.getInstance().getAppDir()
      .resolve("user.colormaps.json");

  /**
   * The log.
   */
  private final Logger LOG = LoggerFactory.getLogger(ColorMapService.class);

  /**
   * Stores custom maps the user adds.
   */
  private ColorMaps mUserMaps = new ColorMaps();

  /**
   * The class ColorMapXmlHandler.
   */
  private class ColorMapXmlHandler extends DefaultHandler {

    /**
     * The member color map.
     */
    private String mName;

    /** The m anchor colors. */
    private List<CSSColor> mAnchorColors = new ArrayList<CSSColor>();

    /** The m colors. */
    private int mColors = -1;

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
     * java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

      if (qName.equals("colormap")) {
        mName = attributes.getValue("name");
        mColors = Integer.parseInt(attributes.getValue("colors"));
      } else if (qName.equals("color")) {
        mAnchorColors
            .add(new CSSColor(Integer.parseInt(attributes.getValue("r")), Integer.parseInt(attributes.getValue("g")),
                Integer.parseInt(attributes.getValue("b")), Integer.parseInt(attributes.getValue("a"))));
      } else {
        // do nothing
      }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String,
     * java.lang.String, java.lang.String)
     */
    public void endElement(String uri, String localName, String qName) throws SAXException {

      if (qName.equals("colormap")) {
        ColorMap colorMap;

        switch (mAnchorColors.size()) {
        case 5:
          colorMap = ColorMap.createFiveColorMap(mName, mAnchorColors.get(0), mAnchorColors.get(1),
              mAnchorColors.get(2), mAnchorColors.get(3), mAnchorColors.get(4), mColors);
          break;
        case 4:
          colorMap = ColorMap.createFourColorMap(mName, mAnchorColors.get(0), mAnchorColors.get(1),
              mAnchorColors.get(2), mAnchorColors.get(3), mColors);
          break;
        case 3:
          colorMap = ColorMap.createThreeColorMap(mName, mAnchorColors.get(0), mAnchorColors.get(1),
              mAnchorColors.get(2), mColors);
          break;
        default:
          colorMap = ColorMap.createTwoColorMap(mName, mAnchorColors.get(0), mAnchorColors.get(1), mColors);
          break;
        }

        intAdd(colorMap);

        LOG.info("Loaded colormap {}.", colorMap.getName());
      }
    }
  }

  /**
   * The member auto load.
   */
  private boolean mAutoLoad = true;

  /**
   * Instantiates a new color map service.
   */
  private ColorMapService() {
    // do nothing
  }

  /*
   * private void add(ColorMap colorMap, boolean internal) { add(colorMap);
   * 
   * if (!internal) { mUserMaps.put(colorMap.getName(), colorMap); } }
   */

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.graphics.colormap.ColorMaps#add(org.abh.common.ui.
   * graphics. colormap.ColorMap)
   */
  @Override
  public void add(ColorMap colorMap) {
    update(colorMap);

    fireChanged();
  }

  /**
   * Add colormap using private api for non-public use.
   *
   * @param colorMap the color map
   */
  private void intAdd(ColorMap colorMap) {
    intUpdate(colorMap);

    fireChanged();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.graphics.colormap.ColorMaps#update(org.abh.common.ui.
   * graphics.colormap.ColorMap)
   */
  @Override
  public void update(ColorMap colorMap) {
    intUpdate(colorMap);

    /*
     * try { mUserMaps.writeXml(USER_COLORMAP_XML_FILE); } catch (IOException |
     * TransformerException | ParserConfigurationException e) { e.printStackTrace();
     * }
     */

    try {
      mUserMaps.writeJson(USER_COLORMAP_JSON_FILE);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Int update.
   *
   * @param colorMap the color map
   */
  private void intUpdate(ColorMap colorMap) {
    if (contains(colorMap.getName())) {
      return;
    }

    super.update(colorMap);

    mUserMaps.add(colorMap);
  }

  /**
   * Auto load.
   *
   * @throws IOException                  Signals that an I/O exception has
   *                                      occurred.
   * @throws SAXException                 the SAX exception
   * @throws ParserConfigurationException the parser configuration exception
   * @throws ParseException               the parse exception
   */
  private synchronized void autoLoad() throws IOException, SAXException, ParserConfigurationException, ParseException {
    if (mAutoLoad) {
      // Load some default maps
      super.add(ColorMap.createViridisMap());
      super.add(ColorMap.createJetMap());
      super.add(ColorMap.createHotMap());
      super.add(ColorMap.createCoolMap());
      super.add(ColorMap.createSpringMap());
      super.add(ColorMap.createSummerMap());
      super.add(ColorMap.createAutumnMap());
      super.add(ColorMap.createWinterMap());
      super.add(ColorMap.createBlueWhiteRedMap());
      super.add(ColorMap.createGreenWhiteRedMap());
      super.add(ColorMap.createGreenBlackRedMap());
      super.add(ColorMap.createBlueYellowMap());
      super.add(ColorMap.createWhiteRedMap());
      // super.add(ColorMap.createRedWhiteMap());
      super.add(ColorMap.createWhiteGreenMap());
      // super.add(ColorMap.createGreenWhiteMap());
      super.add(ColorMap.createWhiteBlueMap());
      // super.add(ColorMap.createBlueWhiteMap());

      super.add(ColorMap.createWhiteYellowMap());

      super.add(ColorMap.createGrayMap());

      // First load internal settings
      LOG.info("Auto loading internal colormaps from {}...", USER_COLORMAP_JSON_FILE);

      // loadXml(USER_COLORMAP_XML_FILE);

      // Load from local directory
      loadJson(COLORMAP_JSON_FILE);

      // Load from user directory
      loadJson(USER_COLORMAP_JSON_FILE);

      mAutoLoad = false;
    }
  }

  /**
   * Load xml.
   *
   * @param file the file
   * @throws SAXException                 the SAX exception
   * @throws IOException                  Signals that an I/O exception has
   *                                      occurred.
   * @throws ParserConfigurationException the parser configuration exception
   */
  public void loadXml(Path file) throws SAXException, IOException, ParserConfigurationException {
    // autoLoad();

    LOG.info("Loading colormaps from {}...", file);

    if (FileUtils.exists(file)) {
      InputStream stream = FileUtils.newBufferedInputStream(file); // new
      // FileInputStream(file);

      loadXml(stream);
    }
  }

  /**
   * Load xml.
   *
   * @param is the is
   * @throws SAXException                 the SAX exception
   * @throws IOException                  Signals that an I/O exception has
   *                                      occurred.
   * @throws ParserConfigurationException the parser configuration exception
   */
  private synchronized void loadXml(InputStream is) throws SAXException, IOException, ParserConfigurationException {
    if (is == null) {
      return;
    }

    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser saxParser = factory.newSAXParser();

    ColorMapXmlHandler handler = new ColorMapXmlHandler();

    saxParser.parse(is, handler);
  }

  /**
   * Load json.
   *
   * @param file the file
   * @throws ParseException the parse exception
   * @throws IOException    Signals that an I/O exception has occurred.
   */
  public void loadJson(Path file) throws ParseException, IOException {
    if (!FileUtils.exists(file)) {
      return;
    }

    JsonParser parser = new JsonParser();

    Json json = parser.parse(file);

    for (Json colorMapJson : json) {
      System.err.println("loading custom color map " + colorMapJson.getString("name"));

      List<CSSColor> colors = new ArrayList<CSSColor>();

      for (Json colorsJson : colorMapJson.get("anchor-colors")) {
        System.err.println("Colors " + colorsJson.getInt("r") + " " + +colorsJson.getInt("g") + " "
            + +colorsJson.getInt("b") + " " + +colorsJson.getInt("a"));

        CSSColor color = new CSSColor(colorsJson.getInt("r"), colorsJson.getInt("g"), colorsJson.getInt("b"),
            colorsJson.getInt("a"));

        colors.add(color);
      }

      ColorMap colorMap;

      switch (colors.size()) {
      case 5:
        colorMap = ColorMap.createFiveColorMap(colorMapJson.getString("name"), colors.get(0), colors.get(1),
            colors.get(2), colors.get(3), colors.get(4), colorMapJson.getInt("colors"));
        break;
      case 4:
        colorMap = ColorMap.createFourColorMap(colorMapJson.getString("name"), colors.get(0), colors.get(1),
            colors.get(2), colors.get(3), colorMapJson.getInt("colors"));
        break;
      case 3:
        colorMap = ColorMap.createThreeColorMap(colorMapJson.getString("name"), colors.get(0), colors.get(1),
            colors.get(2), colorMapJson.getInt("colors"));
        break;
      default:
        colorMap = ColorMap.createTwoColorMap(colorMapJson.getString("name"), colors.get(0), colors.get(1),
            colorMapJson.getInt("colors"));
        break;
      }

      intAdd(colorMap);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.model.NameMapModel#get(java.lang.String)
   */
  @Override
  public synchronized ColorMap get(String name) {
    // if there are no maps loaded, attempt
    // to load the default ones.

    try {
      autoLoad();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (SAXException e) {
      e.printStackTrace();
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }

    return super.get(name);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.model.NameListModel#remove(java.lang.String)
   */
  @Override
  public void remove(String name) {
    if (mUserMaps.contains(name)) {
      System.err.println("remove color map " + name);

      super.remove(name);
      mUserMaps.remove(name);

      /*
       * try { mUserMaps.writeXml(USER_COLORMAP_XML_FILE); } catch (IOException |
       * TransformerException | ParserConfigurationException e) { e.printStackTrace();
       * }
       */

      try {
        mUserMaps.writeJson(USER_COLORMAP_JSON_FILE);
      } catch (IOException e) {
        e.printStackTrace();
      }

      fireChanged();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.model.NameListModel#contains(java.lang.String)
   */
  @Override
  public boolean contains(String name) {
    return super.contains(name) || mUserMaps.contains(name);
  }

  /**
   * Gets the user maps.
   *
   * @return the user maps
   */
  public ColorMaps getUserMaps() {
    return mUserMaps;
  }
}
