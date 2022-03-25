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

import java.util.Iterator;
import java.util.List;

import org.jebtk.core.collections.CollectionUtils;
import org.jebtk.core.collections.UniqueArrayList;
import org.jebtk.core.stream.Stream;

// TODO: Auto-generated Javadoc
/**
 * A group of file filters.
 * 
 * @author Antony Holmes
 *
 */
public class GuiFileFilterGroup extends GuiFileExtFilter implements Iterable<GuiFileExtFilter> {

  /** The m name. */
  private String mName;

  /** The m filters. */
  private List<GuiFileExtFilter> mFilters;

  /**
   * Instantiates a new gif gui file filter.
   *
   * @param name    the name
   * @param filters the filters
   */
  public GuiFileFilterGroup(String name, GuiFileExtFilter filter, GuiFileExtFilter... filters) {
    super(getExtensions(filters));

    mName = name + " (" + Stream.of(getExtension()).join(";") + ")";

    mFilters = CollectionUtils.toList(filter, filters);
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.filechooser.FileFilter#getDescription()
   */
  @Override
  public final String getDescription() {
    return mName;
  }

  /**
   * Return all the extensions of a file.
   *
   * @param filters the filters
   * @return the extensions
   */
  private static List<String> getExtensions(GuiFileExtFilter... filters) {
    List<String> exts = new UniqueArrayList<String>(filters.length);

    for (GuiFileExtFilter filter : filters) {
      for (String ext : filter.getExtensions()) {
        exts.add(ext);
      }
    }

    return exts;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Iterable#iterator()
   */
  @Override
  public Iterator<GuiFileExtFilter> iterator() {
    return mFilters.iterator();
  }
}
