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
package org.jebtk.modern.menu;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import org.jebtk.modern.AssetService;
import org.jebtk.modern.dialog.ModernDialogWindow;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.theme.MaterialService;
import org.jebtk.modern.theme.ThemeService;

// TODO: Auto-generated Javadoc
/**
 * A disabled menu item for breaking up a large menu to indicate the function of
 * a block of menu items.
 * 
 * @author Antony Holmes
 *
 */
public class ModernSeparatorMenuItem extends ModernIconMenuItem {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant HEIGHT.
   */
  public static final int HEIGHT = 22;

  /**
   * The constant MIN_SIZE.
   */
  public static final Dimension MIN_SIZE = new Dimension(0, HEIGHT);

  /**
   * The constant PREFERRED_SIZE.
   */
  public static final Dimension PREFERRED_SIZE = new Dimension(160, HEIGHT);

  /**
   * The constant MAX_SIZE.
   */
  public static final Dimension MAX_SIZE = new Dimension(Short.MAX_VALUE, HEIGHT);

  /**
   * Instantiates a new modern separator menu item.
   *
   * @param text the text
   */
  public ModernSeparatorMenuItem(String text) {
    super(text);

    setup();
  }

  /**
   * Instantiates a new modern separator menu item.
   *
   * @param text the text
   * @param icon the icon
   */
  public ModernSeparatorMenuItem(String text, ModernIcon icon) {
    super(text, icon);

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    setEnabled(false);

    setPreferredSize(PREFERRED_SIZE);
    setMaximumSize(MAX_SIZE);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.button.ModernButtonWidget#drawBackground(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawBackground(Graphics2D g2) {
    fillBackground(g2, getRect());

    Rectangle r = new Rectangle(ModernMenuItem.HEIGHT, 0, mRect.getW() - ModernIconMenuItem.HEIGHT, mRect.getH());

    fill(g2, ModernDialogWindow.DIALOG_BACKGROUND, r);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.menu.ModernIconMenuItem#drawForegroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {

    if (mText1 != null) {
      int d = 0;

      if (this.getIcon() != null) {
        d = AssetService.ICON_SIZE_16 + DOUBLE_PADDING;
      }

      int x = Math.max(AssetService.ICON_SIZE_32, d);

      g2.setColor(ThemeService.getInstance().getColors().getGray(6));
      g2.setFont(ThemeService.loadFont("widget.bold-text"));
      g2.drawString(mText1, x, getTextYPosCenter(g2, getHeight()));
    }

    if (this.getIcon() != null) {
      int iconY = (getHeight() - getIcon().getHeight()) / 2;

      this.getIcon().drawIcon(g2, PADDING, iconY, AssetService.ICON_SIZE_16);
    }
  }
}