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
package org.jebtk.modern.search;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.jebtk.core.collections.CircularArray;
import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.xml.Xml;
import org.xml.sax.SAXException;

// TODO: Auto-generated Javadoc
/**
 * Provides a model of storing and firing search terms so that when users type
 * into text/comboboxes, their search terms can be logged and suggested the next
 * time they perform a search.
 * 
 * @author Antony Holmes
 *
 */
public class SearchTermsService extends SearchTermEventListeners implements Iterable<String> {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  private static class SearchTermsServiceLoader {

    /** The Constant INSTANCE. */
    private static final SearchTermsService INSTANCE = new SearchTermsService();
  }

  /**
   * Gets the single instance of SettingsService.
   *
   * @return single instance of SettingsService
   */
  public static SearchTermsService getInstance() {
    return SearchTermsServiceLoader.INSTANCE;
  }

  /**
   * The constant MAX_SIZE.
   */
  // Store the first 100 terms entered
  private static final int MAX_SIZE = 100;

  /**
   * The constant SEARCH_TERMS_CHANGED.
   */
  public static final String SEARCH_TERMS_CHANGED = "search_terms_changed";

  /**
   * The constant DEFAULT_XML_FILE.
   */
  public static final File DEFAULT_XML_FILE = new File("search.terms.xml");

  /**
   * The member search terms.
   */
  private List<String> mSearchTerms = new CircularArray<String>(MAX_SIZE);

  private boolean mAutoLoad = true;

  /**
   * Instantiates a new search terms service.
   */
  private SearchTermsService() {
    // do nothing
  }

  private void autoLoad() {
    if (mAutoLoad) {
      // Set this here to stop recursive infinite calling
      // of this method.
      mAutoLoad = false;

      try {
        loadXml();
      } catch (SAXException | IOException | ParserConfigurationException e) {
        e.printStackTrace();
      }
      // autoLoadJson();

    }
  }

  /**
   * Load xml.
   *
   * @throws ParserConfigurationException the parser configuration exception
   * @throws SAXException                 the SAX exception
   * @throws IOException                  Signals that an I/O exception has
   *                                      occurred.
   */
  public final void loadXml() throws ParserConfigurationException, SAXException, IOException {
    loadXml(DEFAULT_XML_FILE);
  }

  /**
   * Load search terms from an XML file.
   *
   * @param file the file
   * @throws ParserConfigurationException the parser configuration exception
   * @throws SAXException                 the SAX exception
   * @throws IOException                  Signals that an I/O exception has
   *                                      occurred.
   */
  public final void loadXml(File file) throws ParserConfigurationException, SAXException, IOException {
    if (!file.exists()) {
      return;
    }

    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser saxParser = factory.newSAXParser();

    SearchTermsXmlHandler handler = new SearchTermsXmlHandler(this);

    saxParser.parse(file.getAbsolutePath(), handler);
  }

  /**
   * Adds the term.
   *
   * @param term the term
   */
  public final void addTerm(String term) {

    if (term.length() == 0) {
      return;
    }

    // Ignore whitespace
    if (term.matches("^\\s+$")) {
      return;
    }

    if (mSearchTerms.contains(term)) {
      return;
    }

    mSearchTerms.add(term);

    fireSearchTermsChanged(new ChangeEvent(this, SEARCH_TERMS_CHANGED));
  }

  /**
   * Write.
   *
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public final void write() throws IOException {
    write(DEFAULT_XML_FILE);
  }

  /**
   * Write.
   *
   * @param file the file
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public final void write(File file) throws IOException {
    BufferedWriter out = new BufferedWriter(new FileWriter(file));

    try {
      out.write(Xml.xmlHeader());
      // out.write('\n');
      // out.write('\n');
      out.write("<searches>");
      // out.write('\n');

      for (String search : mSearchTerms) {
        // System.err.println(search);

        out.write(Xml.startTag("term"));
        out.write(Xml.cdata(search));
        out.write(Xml.endTag("term"));
        // out.write('\n');
      }

      out.write("</searches>");
    } finally {
      // Close the output stream
      out.close();
    }
  }

  /**
   * Clear.
   */
  public final void clear() {
    mSearchTerms.clear();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Iterable#iterator()
   */
  public final Iterator<String> iterator() {
    autoLoad();

    return mSearchTerms.iterator();
  }

  /**
   * Contains.
   *
   * @param query the query
   * @return true, if successful
   */
  public final boolean contains(String query) {
    autoLoad();

    return mSearchTerms.contains(query);
  }
}
