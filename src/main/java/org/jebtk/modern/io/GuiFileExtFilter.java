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

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.filechooser.FileFilter;

import org.jebtk.core.collections.CollectionUtils;
import org.jebtk.core.io.PathUtils;
import org.jebtk.core.text.TextUtils;

/**
 * The class GuiFileExtFilter.
 *
 * @author Antony Holmes
 */
public abstract class GuiFileExtFilter extends FileFilter implements Comparable<FileFilter>, FilenameFilter {

  /**
   * The extensions.
   */
  private List<String> mExtensions = new ArrayList<String>();

  /**
   * Instantiates a new gui file ext filter.
   *
   * @param extensions the extensions
   */
  public GuiFileExtFilter(String extension, String... extensions) {
    mExtensions = CollectionUtils.toList(extension, extensions);
  }

  /**
   * Instantiates a new gui file ext filter.
   *
   * @param extensions the extensions
   */
  public GuiFileExtFilter(Collection<String> extensions) {
    mExtensions.addAll(extensions);
  }

  @Override
  public String getDescription() {
    return TextUtils.parenthesis(TextUtils.scJoin(TextUtils.prefix(mExtensions, "*."))); // "GenBank
    // (*.gb)";
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  @Override
  public int compareTo(FileFilter o) {
    return getDescription().compareTo(o.getDescription());
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof FileFilter) {
      return compareTo((FileFilter) o) == 0;
    } else {
      return false;
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return getDescription().hashCode();
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
   */
  @Override
  public boolean accept(File f) {
    if (f.isDirectory()) {
      return true;
    }

    for (String extension : mExtensions) {
      if (f.getName().toLowerCase().endsWith(extension)) {
        return true;
      }
    }

    return false;
  }

  @Override
  public boolean accept(File dir, String name) {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * Gets the extension.
   *
   * @return the extension
   */
  public String getExtension() {
    return getExtensions().get(0);
  }

  /**
   * Returns the list of extensions associated with this file filter.
   * 
   *
   * @return the extensions
   */
  public List<String> getExtensions() {
    return Collections.unmodifiableList(mExtensions);
  }

  /**
   * Adds the extension.
   *
   * @param file   the file
   * @param filter the filter
   * @return the file
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static File addExtension(File file, GuiFileExtFilter filter) throws IOException {
    return addExtension(file, filter.getExtension());
  }

  /**
   * Adds a file extension to a file name. This method will check to ensure it
   * does not create duplicate endings such as .txt.txt, but it will allow
   * .csv.txt for example.
   *
   * @param file      the file
   * @param extension the extension
   * @return the file
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public static final File addExtension(final File file, final String extension) throws IOException {
    String s = file.getCanonicalPath();

    if (!s.toLowerCase().endsWith("." + extension)) {
      s += "." + extension;
    }

    return new File(s);
  }

  /**
   * Adds the extension.
   *
   * @param file   the file
   * @param filter the filter
   * @return the path
   */
  public static Path addExtension(Path file, GuiFileExtFilter filter) {
    return PathUtils.addExtension(file, filter.getExtension());
  }
}
