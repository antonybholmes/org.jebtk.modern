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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import org.jebtk.modern.UI;
import org.jebtk.modern.menu.ModernMenuItem;
import org.jebtk.modern.theme.MaterialService;

// TODO: Auto-generated Javadoc
/**
 * The class FileTitleMenuItem.
 */
public class FileTitleMenuItem extends ModernMenuItem {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant TITLE_COLOR.
   */
  private static final Color TITLE_COLOR = MaterialService.getInstance().getColor("ribbon"); // ThemeService.getInstance().getColors().getColorHighlight32(16);

  /**
   * The constant TITLE_SIZE.
   */
  private static final Dimension TITLE_SIZE = new Dimension(Short.MAX_VALUE, 60);

  /**
   * Instantiates a new file title menu item.
   *
   * @param text the text
   */
  public FileTitleMenuItem(String text) {
    super(text);

    setEnabled(false);

    setFont(HEADING_FONT);

    // setMinimumSize(TITLE_SIZE);
    // setPreferredSize(TITLE_SIZE);

    UI.setSize(this, TITLE_SIZE);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.menu.ModernMenuItem#drawForegroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    if (mDisplayText == null) {
      return;
    }

    g2.setColor(TITLE_COLOR);

    g2.drawString(mDisplayText, PADDING, getTextYPosCenter(g2, getHeight()));
  }
}