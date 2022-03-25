/**
 * Copyright 2016 Antony Holmes
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
package org.jebtk.modern.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.jebtk.core.Resources;
import org.jebtk.core.collections.IterHashMap;
import org.jebtk.core.io.FileUtils;
import org.jebtk.core.json.Json;
import org.jebtk.core.json.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Provides a service of file filters for use in designing dialogs etc.
 *
 * @author Antony Holmes
 *
 */
public class FileFilterService implements Iterable<String> {

  /**
   * The Class SettingsServiceLoader.
   */
  private static class FileFilterServiceLoader {

    /** The Constant INSTANCE. */
    private static final FileFilterService INSTANCE = new FileFilterService();
  }

  /**
   * Gets the single instance of SettingsService.
   *
   * @return single instance of SettingsService
   */
  public static FileFilterService getInstance() {
    return FileFilterServiceLoader.INSTANCE;
  }

  protected final Logger LOG = LoggerFactory.getLogger(FileFilterService.class);

  /**
   * The member settings.
   */
  protected Map<String, GuiFileExtFilter> mFilterMap = new IterHashMap<>();

  /** The m update map. */
  // protected Map<Path, Boolean> mUpdateMap = new HashMap<Path, Boolean>();

  /**
   * The class SettingsXmlHandler.
   */
  private class FileFilterXmlHandler extends DefaultHandler {
    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
     * java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

      if (qName.equals("setting")) {
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

    }
  }

  /**
   * The member auto loaded.
   */
  private boolean mAutoLoad = true;

  /**
   * Instantiates a new settings service.
   */
  private FileFilterService() {
    // do nothing
  }

  /**
   * Gets the setting.
   *
   * @param name
   * @return the setting
   */
  public synchronized GuiFileExtFilter getFilter(String name) {
    autoLoad();

    return mFilterMap.get(name);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Iterable#iterator()
   */
  @Override
  public Iterator<String> iterator() {
    autoLoad();

    return mFilterMap.keySet().iterator();
  }

  /**
   * Attempt to find all settings files and load them.
   */
  private synchronized void autoLoad() {
    if (mAutoLoad) {
      // Set this here to stop recursive infinite calling
      // of this method.
      mAutoLoad = false;

      // autoLoadXml();
      // autoLoadJson();

      try {
        autoLoadJson();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Auto load xml.
   *
   * @throws IOException                  Signals that an I/O exception has
   *                                      occurred.
   * @throws URISyntaxException           the URI syntax exception
   * @throws SAXException                 the SAX exception
   * @throws ParserConfigurationException the parser configuration exception
   */
  private synchronized void autoLoadXml()
      throws IOException, URISyntaxException, SAXException, ParserConfigurationException {
    LOG.info("Auto loading XML settings...");

    for (String res : Resources.getInstance()) {
      if (!res.contains("file-filters.xml")) {
        continue;
      }

      LOG.info("Loading file filters from {}...", res);

      loadXml(Resources.getResInputStream(res), false);
    }

    // Load local settings that may overwrite internal settings.
    // loadXml(SettingsReaderPackageXml.DEFAULT_XML_FILE, false);

    // Load any per user settings. We flag these as being updated so
    // that on the next write cycle, they will be written back to the
    // settings file.
    // loadXml(SettingsReaderUserXml.USER_XML_FILE, true);

    LOG.info("Finished loading file filters.");
  }

  /**
   * Auto load json.
   *
   * @throws ParseException the parse exception
   * @throws IOException    Signals that an I/O exception has occurred.
   */
  private synchronized void autoLoadJson() throws IOException {
    LOG.info("Auto loading JSON settings...");

    for (String res : Resources.getInstance()) {
      if (!res.contains("file-filters.json")) {
        continue;
      }

      LOG.info("Loading file filters from {}...", res);

      loadJson(Resources.getResInputStream(res));
    }

    LOG.info("Finished loading file filters.");
  }

  /**
   * Load xml.
   *
   * @param file   the file
   * @param update the update
   * @throws SAXException                 the SAX exception
   * @throws IOException                  Signals that an I/O exception has
   *                                      occurred.
   * @throws ParserConfigurationException the parser configuration exception
   */
  public void loadXml(Path file, boolean update) throws SAXException, IOException, ParserConfigurationException {
    if (file == null || !FileUtils.exists(file)) {
      return;
    }

    LOG.info("Loading file filters from {}...", file);

    try (InputStream stream = FileUtils.newBufferedInputStream(file)) {
      loadXml(stream, update);
    }
  }

  /**
   * Load xml.
   *
   * @param is     the is
   * @param update the update
   * @return true, if successful
   * @throws SAXException                 the SAX exception
   * @throws IOException                  Signals that an I/O exception has
   *                                      occurred.
   * @throws ParserConfigurationException the parser configuration exception
   */
  private synchronized boolean loadXml(InputStream is, boolean update)
      throws SAXException, IOException, ParserConfigurationException {
    if (is == null) {
      return false;
    }

    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser saxParser = factory.newSAXParser();

    // FileFilterXmlHandler handler = new FileFilterXmlHandler(update);

    // saxParser.parse(is, handler);

    return true;
  }

  /**
   * Load json.
   *
   * @param file   the file
   * @param update the update
   * @throws FileNotFoundException the file not found exception
   * @throws IOException           Signals that an I/O exception has occurred.
   */
  private void loadJson(Path file) throws IOException {
    if (file == null || !FileUtils.exists(file)) {
      return;
    }

    LOG.info("Loading user settings from {}...", file);

    try (InputStream is = FileUtils.newBufferedInputStream(file)) {
      loadJson(is);
    }
  }

  /**
   * Load xml.
   *
   * @param is     the is
   * @param update the update
   * @return true, if successful
   * @throws IOException Signals that an I/O exception has occurred.
   */
  private synchronized boolean loadJson(InputStream is) throws IOException {
    if (is == null) {
      return false;
    }

    Json json = Json.parse(is);

    for (int i = 0; i < json.size(); ++i) {
      Json filterJson = json.get(i);

      String name = filterJson.getString("name");

      mFilterMap.put(name, new SimpleGuiFileFilter(filterJson.getString("description"),
          JsonUtils.toStringList(filterJson.get("filters"))));

    }

    return true;
  }
}
