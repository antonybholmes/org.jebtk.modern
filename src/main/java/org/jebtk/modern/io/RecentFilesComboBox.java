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

import java.nio.file.Path;

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.event.ChangeListener;
import org.jebtk.core.io.PathUtils;
import org.jebtk.modern.AssetService;
import org.jebtk.modern.combobox.ModernComboBox;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.menu.ForwardRequestMenuItem;
import org.jebtk.modern.menu.ModernMenuDivider;

/**
 * The class RecentFilesComboBox.
 */
public class RecentFilesComboBox extends ModernComboBox {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant MAX_FILES.
   */
  public static final int MAX_FILES = 10;

  /**
   * The icon.
   */
  private ModernIcon icon = AssetService.getInstance().loadIcon("file", AssetService.ICON_SIZE_32);

  /**
   * The max.
   */
  private int max;

  /**
   * The recent files.
   */
  private RecentFilesService recentFiles;

  /**
   * The class RecentEvents.
   */
  private class RecentEvents implements ChangeListener {

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.lib.event.ChangeListener#changed(org.abh.lib.event.ChangeEvent)
     */
    @Override
    public void changed(ChangeEvent e) {
      reload();
    }
  }

  /**
   * Instantiates a new recent files combo box.
   *
   * @param recentFiles the recent files
   */
  public RecentFilesComboBox(RecentFilesService recentFiles) {
    setup(recentFiles, MAX_FILES);
  }

  /**
   * Instantiates a new recent files combo box.
   *
   * @param recentFiles the recent files
   * @param max         the max
   */
  public RecentFilesComboBox(RecentFilesService recentFiles, int max) {
    setup(recentFiles, max);
  }

  /**
   * Instantiates a new recent files combo box.
   *
   * @param recentFiles the recent files
   * @param icon        the icon
   */
  public RecentFilesComboBox(RecentFilesService recentFiles, ModernIcon icon) {
    this.icon = icon;

    setup(recentFiles, MAX_FILES);
  }

  /**
   * Instantiates a new recent files combo box.
   *
   * @param recentFiles the recent files
   * @param icon        the icon
   * @param max         the max
   */
  public RecentFilesComboBox(RecentFilesService recentFiles, ModernIcon icon, int max) {
    this.icon = icon;

    setup(recentFiles, max);
  }

  /**
   * Setup.
   *
   * @param recentFiles the recent files
   * @param max         the max
   */
  private void setup(RecentFilesService recentFiles, int max) {
    this.recentFiles = recentFiles;

    recentFiles.addChangeListener(new RecentEvents());
    this.max = max;

    reload();
  }

  /**
   * Reload.
   */
  private void reload() {
    getPopup().clear();

    int c = 0;

    for (Path file : recentFiles) {
      if (c == max) {
        break;
      }

      addScrollMenuItem(new FileMenuItem(file, icon));

      ++c;
    }

    addMenuItem(new ModernMenuDivider());

    addMenuItem(new ForwardRequestMenuItem("Other...", null));

    for (Path file : recentFiles) {
      // default to the first file added
      setText(PathUtils.toString(file));
      break;
    }
  }
}
