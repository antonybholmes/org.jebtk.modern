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

import java.awt.Graphics2D;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jebtk.core.io.PathUtils;
import org.jebtk.modern.AssetService;
import org.jebtk.modern.graphics.icons.FileVectorIcon;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.menu.ModernTwoLineMenuItem;
import org.jebtk.modern.theme.MaterialService;

/**
 * The class FileMenuItem.
 */
public class RecentFileMenuItem extends ModernTwoLineMenuItem {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant SDF.
   */
  private static final String SDF = "MM/dd/yyyy HH:mm";

  /**
   * The member file.
   */
  protected Path mFile;

  /** The m date. */
  private Date mDate;

  /**
   * Instantiates a new file menu item.
   *
   * @param file the file
   * @param date the date
   */
  public RecentFileMenuItem(Path file, Date date) {
    this(file, date, AssetService.getInstance().loadIcon(FileVectorIcon.class, 32));

  }

  /**
   * Instantiates a new file menu item.
   *
   * @param file the file
   * @param date the date
   * @param icon the icon
   */
  public RecentFileMenuItem(Path file, Date date, ModernIcon icon) {
    super(PathUtils.toString(file.getFileName()), PathUtils.toString(file.getParent()), icon);

    mFile = file;
    mDate = date;

    setClickMessage(PathUtils.toString(file));
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.menu.ModernTwoLineMenuItem#drawForegroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    int iconX = PADDING;

    int h2 = getHeight() / 2;

    int y = (h2 + g2.getFontMetrics().getAscent()) / 2;
    int x;

    String dateText = new SimpleDateFormat(SDF).format(mDate);
    int rightOffset = getWidth() - getStringWidth(FONT, dateText) - DOUBLE_PADDING;

    if (mIcon != null) {
      x = iconX + mIcon.getWidth() + PADDING;
    } else {
      x = PADDING;
    }

    g2.setColor(TEXT_COLOR);
    g2.setFont(MaterialService.getInstance().getFont("text"));

    int maxTextWidth = rightOffset - DOUBLE_PADDING - x;

    String text = truncate(g2, PathUtils.getName(mFile), maxTextWidth);

    g2.drawString(text, x, y);

    g2.setColor(ALT_TEXT_COLOR);
    g2.setFont(MaterialService.getInstance().getFont("subtext"));

    y = h2 + (h2 + g2.getFontMetrics().getAscent()) / 2;

    text = truncate(g2, PathUtils.toString(mFile.getParent()), maxTextWidth);

    g2.drawString(text, x, y);

    x = rightOffset;
    y = getTextYPosCenter(g2, getHeight());

    g2.drawString(dateText, x, y);

    int iconY = (getHeight() - 32) / 2;

    mIcon.drawIcon(g2, iconX, iconY, 32);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.button.ModernButtonWidget#getText()
   */
  @Override
  public String getText() {
    // The two line file menu item should return the full
    // file name as its string, which is what the
    // action command is
    return getClickMessage();
  }

  /**
   * Gets the file.
   *
   * @return the file
   */
  public Path getFile() {
    return mFile;
  }
}
