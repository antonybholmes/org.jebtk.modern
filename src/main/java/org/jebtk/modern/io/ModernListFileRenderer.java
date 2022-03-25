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

import java.awt.Component;
import java.awt.Graphics2D;
import java.nio.file.Path;

import org.jebtk.core.io.PathUtils;
import org.jebtk.core.text.TextUtils;
import org.jebtk.modern.AssetService;
import org.jebtk.modern.graphics.icons.FileVectorIcon;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.list.ModernList;
import org.jebtk.modern.list.ModernListIconCellRenderer;

// TODO: Auto-generated Javadoc
/**
 * Renders a file as a list item.
 * 
 * @author Antony Holmes
 *
 */
public class ModernListFileRenderer extends ModernListIconCellRenderer {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member file.
   */
  private Path mFile;

  /**
   * Instantiates a new modern list file renderer.
   */
  public ModernListFileRenderer() {
    this(AssetService.getInstance().loadIcon(FileVectorIcon.class, 32));
  }

  /**
   * Instantiates a new modern list file renderer.
   *
   * @param icon the icon
   */
  public ModernListFileRenderer(ModernIcon icon) {
    super(icon);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.list.ModernListIconCellRenderer#
   * getCellRendererComponent(org.abh.lib.ui.modern.list.ModernList,
   * java.lang.Object, boolean, boolean, boolean, int)
   */
  @Override
  public Component getCellRendererComponent(ModernList<?> list, Object value, boolean highlight, boolean isSelected,
      boolean hasFocus, int row) {

    // setup
    super.getCellRendererComponent(list, value, highlight, isSelected, hasFocus, row);

    mFile = (Path) value;

    return this;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.list.ModernListIconCellRenderer#drawForegroundAA(
   * java. awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    int iconX = PADDING;

    int h2 = getHeight() / 2;

    int y = (h2 + g2.getFontMetrics().getAscent()) / 2;
    int x;

    if (mIcon != null) {
      x = iconX + mIcon.getWidth() + PADDING;
    } else {
      x = PADDING;
    }

    g2.setColor(TEXT_COLOR);

    g2.drawString(mFile.getFileName().toString(), x, y);

    y += h2;

    String text = TextUtils.truncateCenter(mFile.getParent() != null ? PathUtils.toString(mFile.getParent()) : "", 60);

    g2.drawString(text, x, y);

    x = getWidth() - g2.getFontMetrics().stringWidth(text) - DOUBLE_PADDING;

    int iconY = (getHeight() - 32) / 2;

    mIcon.drawIcon(g2, iconX, iconY, 32);

  }
}