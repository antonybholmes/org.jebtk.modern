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
package org.jebtk.modern.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerException;

import org.jebtk.core.AppService;
import org.jebtk.core.io.FileIsNotADirException;
import org.jebtk.core.io.FileUtils;
import org.jebtk.core.io.PathUtils;
import org.jebtk.core.json.Json;
import org.jebtk.core.json.JsonParser;
import org.jebtk.core.xml.XmlUtils;
import org.w3c.dom.Document;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

// TODO: Auto-generated Javadoc
/**
 * Maintains a list of recently opened files as well as the currently directory,
 * allowing the lists to be shared amongst multiple windows/apps in the same
 * process.
 * 
 * @author Antony Holmes
 *
 */
public class RecentFilesService extends RecentFilesModel {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * The Class RecentFilesServiceLoader.
   */
  private static class RecentFilesServiceLoader {
    private static final RecentFilesService INSTANCE = new RecentFilesService();
  }

  /**
   * Gets the single instance of SettingsService.
   *
   * @return single instance of SettingsService
   */
  public static RecentFilesService getInstance() {
    return RecentFilesServiceLoader.INSTANCE;
  }

  /**
   * The member loaded.
   */
  private boolean mLoaded = false;

  /**
   * The constant DEFAULT_XML_FILE.
   */
  public static final Path DEFAULT_XML_FILE = PathUtils.getPath("recent.files.xml");

  /** The Constant DEFAULT_JSON_FILE. */
  public static final Path DEFAULT_JSON_FILE = PathUtils.getPath("recent.files.json");

  /** The Constant USER_JSON_FILE. */
  public static final Path USER_JSON_FILE = AppService.getInstance().getAppDir().resolve("recent.files.json");

  /**
   * The Class RecentFilesXmlHandler.
   */
  public class RecentFilesXmlHandler extends DefaultHandler {

    /** The m df. */
    private SimpleDateFormat mDf;

    /**
     * Instantiates a new recent files xml handler.
     */
    public RecentFilesXmlHandler() {
      mDf = new SimpleDateFormat(RecentFilesService.STORAGE_DATE_FORMAT);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String,
     * java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    @Override
    public final void startElement(String uri, String localName, String qName, Attributes attributes)
        throws SAXException {

      if (qName.equals("files")) {
        System.err.println("xml pwd " + attributes.getValue("pwd"));

        setPwd(PathUtils.getPath(attributes.getValue("pwd")));
      } else if (qName.equals("file")) {
        Path file = PathUtils.getPath(attributes.getValue("name"));

        if (FileUtils.exists(file)) {
          if (attributes.getValue("date") != null) {
            try {
              Date date = mDf.parse(attributes.getValue("date"));

              mDateMap.put(file, date);
            } catch (ParseException e) {
              e.printStackTrace();
            }
          }

          mFiles.add(file);
        }
      } else {

      }
    }
  }

  /**
   * Instantiates a new recent files service.
   */
  private RecentFilesService() {
    // super();
  }

  /**
   * Instantiates a new recent files service.
   *
   * @param file the file
   * @throws IOException                  Signals that an I/O exception has
   *                                      occurred.
   * @throws SAXException                 the SAX exception
   * @throws ParserConfigurationException the parser configuration exception
   * @throws FileIsNotADirException       the file is not A dir exception
   */
  public RecentFilesService(Path file)
      throws IOException, SAXException, ParserConfigurationException, FileIsNotADirException {
    super();

    loadJson(file);
  }

  /**
   * Load.
   *
   * @throws IOException                  Signals that an I/O exception has
   *                                      occurred.
   * @throws SAXException                 the SAX exception
   * @throws ParserConfigurationException the parser configuration exception
   */
  public void loadXml() throws IOException, SAXException, ParserConfigurationException {
    loadXml(DEFAULT_XML_FILE);
  }

  /**
   * Load xml.
   *
   * @param file the file
   * @throws IOException                  Signals that an I/O exception has
   *                                      occurred.
   * @throws SAXException                 the SAX exception
   * @throws ParserConfigurationException the parser configuration exception
   */
  public synchronized void loadXml(Path file) throws IOException, SAXException, ParserConfigurationException {
    if (FileUtils.exists(file)) {
      LOG.info("Parsing recent files from {}...", file);

      SAXParserFactory factory = SAXParserFactory.newInstance();

      SAXParser saxParser = null;

      saxParser = factory.newSAXParser();

      RecentFilesXmlHandler handler = new RecentFilesXmlHandler();

      saxParser.parse(file.toFile(), handler);
    }
  }

  /**
   * Load.
   *
   * @throws IOException                  Signals that an I/O exception has
   *                                      occurred.
   * @throws SAXException                 the SAX exception
   * @throws ParserConfigurationException the parser configuration exception
   */
  public void loadJson() throws IOException, SAXException, ParserConfigurationException {
    loadJson(DEFAULT_JSON_FILE);

    // Load the file from the user directory as this is where we want to
    // store things
    loadJson(USER_JSON_FILE);
  }

  /**
   * Load xml.
   *
   * @param jsonFile the file
   * @throws IOException                  Signals that an I/O exception has
   *                                      occurred.
   * @throws SAXException                 the SAX exception
   * @throws ParserConfigurationException the parser configuration exception
   */
  public synchronized void loadJson(Path jsonFile) throws IOException, SAXException, ParserConfigurationException {
    // If the file does not exist, create a default instance
    if (Files.exists(jsonFile)) {
      // createNewFileList();

      LOG.info("Parsing recent files from {}...", jsonFile);

      Json json = new JsonParser().parse(jsonFile);

      Json filesJson = json.get("files");

      List<Path> files = new ArrayList<Path>();

      SimpleDateFormat df = new SimpleDateFormat(STORAGE_DATE_FORMAT);

      for (int i = 0; i < filesJson.size(); ++i) {
        Json fileJson = filesJson.get(i);

        // System.err.println("file json " + fileJson.toString());

        Path file = PathUtils.getPath(fileJson.get("file").getString());

        if (FileUtils.exists(file)) {
          Date date = null;

          try {
            date = df.parse(fileJson.get("date").getString());
          } catch (ParseException e) {
            e.printStackTrace();
          }

          files.add(file);

          mDateMap.put(file, date);

          System.err.println("recent " + file);
        }
      }

      System.err.println("recent " + files);

      mFiles.addAll(files);
      setPwd(PathUtils.getPath(json.get("pwd").getString()));
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.util.ArrayList#iterator()
   */
  @Override
  public Iterator<Path> iterator() {
    // Attempt to auto load files if they
    // have not already been done so

    try {
      autoLoad();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (SAXException e) {
      e.printStackTrace();
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    }

    return super.iterator();
  }

  /**
   * Auto load.
   *
   * @throws IOException                  Signals that an I/O exception has
   *                                      occurred.
   * @throws SAXException                 the SAX exception
   * @throws ParserConfigurationException the parser configuration exception
   */
  private synchronized void autoLoad() throws IOException, SAXException, ParserConfigurationException {
    if (!mLoaded) {
      mLoaded = true;

      LOG.info("Auto load recent files.");

      loadXml();

      loadJson();
    }
  }

  /**
   * Adds the file.
   *
   * @param file the file
   * @return true, if successful
   */
  @Override
  public synchronized boolean add(Path file) {
    boolean ret = super.add(file);

    if (ret) {
      try {
        write();
      } catch (IOException | TransformerException | ParserConfigurationException e) {
        e.printStackTrace();
      }
    }

    return ret;
  }

  /**
   * Write.
   *
   * @throws IOException                  Signals that an I/O exception has
   *                                      occurred.
   * @throws TransformerException         the transformer exception
   * @throws ParserConfigurationException the parser configuration exception
   */
  public synchronized void write() throws IOException, TransformerException, ParserConfigurationException {
    writeJson();
    // writeXml();
  }

  /**
   * Write xml.
   *
   * @throws IOException                  Signals that an I/O exception has
   *                                      occurred.
   * @throws TransformerException         the transformer exception
   * @throws ParserConfigurationException the parser configuration exception
   */
  public synchronized void writeXml() throws IOException, TransformerException, ParserConfigurationException {
    writeXml(DEFAULT_XML_FILE);
  }

  /**
   * Write xml.
   *
   * @param file the file
   * @throws IOException                  Signals that an I/O exception has
   *                                      occurred.
   * @throws TransformerException         the transformer exception
   * @throws ParserConfigurationException the parser configuration exception
   */
  public synchronized void writeXml(Path file) throws IOException, TransformerException, ParserConfigurationException {

    LOG.info("Writing recent files to {}...", file);

    Document doc = XmlUtils.createDoc();

    doc.appendChild(toXml(doc));

    // XmlDocument.writeXml(doc, file);

    XmlUtils.writeXml(doc, file);
  }

  /**
   * Write json.
   *
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public synchronized void writeJson() throws IOException {
    writeJson(USER_JSON_FILE);
  }

  /**
   * Write xml.
   *
   * @param file the file
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public synchronized void writeJson(Path file) throws IOException {
    LOG.info("Writing recent files to {}...", file);

    Json.prettyWrite(toJson(), file);
  }

  /**
   * Gets the pwd.
   *
   * @return the pwd
   */
  @Override
  public Path getPwd() {
    try {
      autoLoad();
    } catch (IOException | SAXException | ParserConfigurationException e) {
      e.printStackTrace();
    }

    return super.getPwd();
  }

  /**
   * Sets the pwd.
   *
   * @param pwd the new pwd
   * @return
   */
  @Override
  public synchronized boolean updatePwd(Path pwd) {
    pwd = pwd.toAbsolutePath();

    if (!FileUtils.isDirectory(pwd)) {
      return false;
    }

    try {
      autoLoad();
    } catch (IOException | SAXException | ParserConfigurationException e) {
      e.printStackTrace();
    }

    getPwdModel().setPwd(pwd);

    try {
      write();
    } catch (IOException | TransformerException | ParserConfigurationException e) {
      e.printStackTrace();
    }

    return true;
  }
}
