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
import java.nio.file.Path;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.jebtk.core.json.Json;
import org.jebtk.core.json.JsonArray;
import org.jebtk.core.json.JsonRepresentation;
import org.jebtk.core.model.NameListModel;
import org.jebtk.core.xml.XmlRepresentation;
import org.jebtk.core.xml.XmlUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Settings factory for providing global settings to an application. Can load
 * settings from an XML file or a text file.
 *
 * @author Antony Holmes
 *
 */
public class ColorMaps extends NameListModel<ColorMap> implements XmlRepresentation, JsonRepresentation {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Adds the.
   *
   * @param colorMap the color map
   */
  public void add(ColorMap colorMap) {
    add(colorMap.getName(), colorMap);
  }

  /**
   * Update.
   *
   * @param colorMap the color map
   */
  public void update(ColorMap colorMap) {
    update(colorMap.getName(), colorMap);
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
  public synchronized final void writeXml(Path file)
      throws IOException, TransformerException, ParserConfigurationException {
    Document doc = XmlUtils.createDoc(); // new XmlDocument(toXml());

    doc.appendChild(toXml(doc));

    XmlUtils.writeXml(doc, file);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.xml.XmlRepresentation#toXml()
   */
  @Override
  public Element toXml(Document doc) {
    Element element = doc.createElement("colormaps");

    for (String name : this) {
      element.appendChild(get(name).toXml(doc));
    }

    return element;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.json.JsonRepresentation#toJson()
   */
  @Override
  public Json toJson() {
    Json json = new JsonArray();

    for (String name : this) {
      json.add(get(name).toJson());
    }

    return json;
  }

  /**
   * Write json.
   *
   * @param file the file
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public synchronized void writeJson(Path file) throws IOException {
    Json.prettyWrite(toJson(), file);
  }
}
