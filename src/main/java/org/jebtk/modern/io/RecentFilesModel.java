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
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.jebtk.core.collections.ArrayListCreator;
import org.jebtk.core.collections.DefaultTreeMap;
import org.jebtk.core.event.ChangeListeners;
import org.jebtk.core.io.FileIsNotADirException;
import org.jebtk.core.io.FileUtils;
import org.jebtk.core.io.PathUtils;
import org.jebtk.core.json.Json;
import org.jebtk.core.json.JsonArray;
import org.jebtk.core.json.JsonObject;
import org.jebtk.core.json.JsonRepresentation;
import org.jebtk.core.settings.SettingsService;
import org.jebtk.core.xml.XmlRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

// TODO: Auto-generated Javadoc
/**
 * Maintains a list of recently opened files as well as the currently directory,
 * allowing the lists to be shared amongst multiple windows/apps in the same
 * process.
 * 
 * @author Antony Holmes
 *
 */
public class RecentFilesModel extends ChangeListeners implements XmlRepresentation, JsonRepresentation, Iterable<Path> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  // the format to store and read back dates in, when loading the recent
  /**
   * The constant STORAGE_DATE_FORMAT.
   */
  // files list
  public static final String STORAGE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

  /**
   * The log.
   */
  protected final Logger LOG = LoggerFactory.getLogger(RecentFilesModel.class);

  /**
   * The constant MAX_FILES.
   */
  public static final int MAX_FILES = SettingsService.getInstance().getInt("ui.recent-files.max-files");

  /** The m file type map. */
  protected Map<String, List<Path>> mFileTypeMap = DefaultTreeMap.create(new ArrayListCreator<Path>());

  /** The m files. */
  protected List<Path> mFiles = new ArrayList<Path>(100);

  /** The m date map. */
  protected Map<Path, Date> mDateMap = new HashMap<Path, Date>();

  private PwdModel mPwdModel = new PwdModel();

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
  public RecentFilesModel() {

  }

  public RecentFilesModel(Path pwd) {
    mPwdModel.setPwd(pwd);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.util.ArrayList#iterator()
   */
  @Override
  public Iterator<Path> iterator() {
    return mFiles.iterator();
  }

  /**
   * Adds the.
   *
   * @param file the file
   * @return true, if successful
   */
  public synchronized boolean add(Path file) {
    boolean ret = update(file);

    if (ret) {
      fireChanged();
    }

    return ret;
  }

  public synchronized boolean update(Path file) {
    file = file.toAbsolutePath();

    if (FileUtils.exists(file)) {
      mPwdModel.setPwd(file.getParent());

      // List<Path> current = new ArrayList<Path>(mFiles);

      // Remove from its old position
      mFiles.remove(file);
      mFileTypeMap.get(PathUtils.getFileExt(file)).remove(file);

      // Add file to head of list
      mFiles.add(0, file);

      mFileTypeMap.get(PathUtils.getFileExt(file)).add(file);
      mDateMap.put(file, Calendar.getInstance().getTime());

      return true;
    } else {
      return false;
    }
  }

  /**
   * Adds the file.
   *
   * @param file the file
   * @param date the date
   */
  public synchronized void add(Path file, Date date) {
    file = file.toAbsolutePath();

    if (add(file)) {
      mDateMap.put(file, date);
    }
  }

  /**
   * Return only files with a particular extension.
   *
   * @param exts the exts
   * @return the files by ext
   */
  public List<Path> getFilesByExt(String... exts) {
    List<Path> ret = new ArrayList<Path>();

    for (String ext : exts) {
      ret.addAll(mFileTypeMap.get(ext));
    }

    return ret;
  }

  /**
   * Gets the date.
   *
   * @param file the file
   * @return the date
   */
  public Date getDate(Path file) {
    return mDateMap.get(file);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.xml.XmlRepresentation#toXml()
   */
  @Override
  public Element toXml(Document doc) {
    SimpleDateFormat df = new SimpleDateFormat(STORAGE_DATE_FORMAT);

    Element filesElement = doc.createElement("files");

    filesElement.setAttribute("pwd", mPwdModel.getPwd().toAbsolutePath().toString());

    // We write out no more than max files
    int n = Math.min(mFiles.size(), MAX_FILES);

    for (int i = 0; i < n; ++i) {
      Path f = mFiles.get(i);

      Element fileElement = doc.createElement("file");
      fileElement.setAttribute("name", PathUtils.toString(f));
      fileElement.setAttribute("date", df.format(mDateMap.get(f)));
      filesElement.appendChild(fileElement);
    }

    return filesElement;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.json.JsonRepresentation#toJson()
   */
  @Override
  public Json toJson() {
    Json o = new JsonObject();

    o.add("pwd", mPwdModel.getPwd().toAbsolutePath());

    Json filesJ = new JsonArray();

    SimpleDateFormat df = new SimpleDateFormat(STORAGE_DATE_FORMAT);

    for (Path f : this) {
      Json fileJ = new JsonObject();

      fileJ.add("file", f);
      fileJ.add("date", df.format(mDateMap.get(f)));

      filesJ.add(fileJ);
    }

    o.add("files", filesJ);

    return o;
  }

  public int size() {
    return mFiles.size();
  }

  public Path get(int index) {
    return mFiles.get(index);
  }

  public PwdModel getPwdModel() {
    return mPwdModel;
  }

  public boolean setPwd(Path pwd) {
    return getPwdModel().setPwd(pwd);
  }

  public Path getPwd() {
    return getPwdModel().getPwd();
  }

  public synchronized boolean updatePwd(Path pwd) {
    return getPwdModel().setPwd(pwd);
  }
}
