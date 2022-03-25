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

import java.awt.Dimension;
import java.nio.file.Path;

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.event.ChangeListener;
import org.jebtk.modern.AssetService;
import org.jebtk.modern.UI;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.menu.ForwardRequestMenuItem;
import org.jebtk.modern.menu.ModernIconMenuItem;
import org.jebtk.modern.menu.ModernMenuSeparator;
import org.jebtk.modern.menu.ModernScrollPopupMenu;

/**
 * The class RecentFilesPopup.
 */
public class RecentFilesPopup extends ModernScrollPopupMenu implements ChangeListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant MAX_FILES.
   */
  public static final int MAX_FILES = 5;

  /**
   * The recent files.
   */
  private RecentFilesService mRecentFiles;

  /**
   * The max.
   */
  private int max;

  /**
   * The icon.
   */
  private ModernIcon mIcon;

  /**
   * Instantiates a new recent files popup.
   *
   * @param recentFiles the recent files
   */
  public RecentFilesPopup(RecentFilesService recentFiles) {
    setup(recentFiles, AssetService.getInstance().loadIcon("file", AssetService.ICON_SIZE_32), MAX_FILES);
  }

  /**
   * Instantiates a new recent files popup.
   *
   * @param recentFiles the recent files
   * @param icon        the icon
   */
  public RecentFilesPopup(RecentFilesService recentFiles, ModernIcon icon) {
    setup(recentFiles, icon, MAX_FILES);
  }

  /**
   * Instantiates a new recent files popup.
   *
   * @param recentFiles the recent files
   * @param icon        the icon
   * @param max         the max
   */
  public RecentFilesPopup(RecentFilesService recentFiles, ModernIcon icon, int max) {
    setup(recentFiles, icon, max);
  }

  /**
   * Setup.
   *
   * @param recentFiles the recent files
   * @param icon        the icon
   * @param max         the max
   */
  private void setup(RecentFilesService recentFiles, ModernIcon icon, int max) {
    mRecentFiles = recentFiles;
    recentFiles.addChangeListener(this);
    mIcon = icon;
    this.max = max;

    reload();
  }

  /**
   * Reload.
   */
  private void reload() {
    int c = 0;

    clear();

    for (Path file : mRecentFiles) {
      if (c == max) {
        break;
      }

      ModernIconMenuItem item = new FileMenuItem(file, mIcon);

      UI.setSize(item, new Dimension(400, AssetService.ICON_SIZE_48));

      addScrollMenuItem(item);

      ++c;
    }

    addMenuItem(new ModernMenuSeparator());

    addMenuItem(new ForwardRequestMenuItem("Other...", null));
  }

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
