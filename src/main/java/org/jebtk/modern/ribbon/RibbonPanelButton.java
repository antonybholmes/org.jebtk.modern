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
package org.jebtk.modern.ribbon;

import java.awt.Font;
import java.awt.Graphics2D;

import org.jebtk.modern.AssetService;
import org.jebtk.modern.UI;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.theme.ThemeService;

/**
 * The class RibbonPanelButton.
 */
public class RibbonPanelButton extends RibbonButton {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant FONT.
   */
  protected static final Font FONT = ThemeService.loadFont("theme.ribbon-panel-button.text-font");

  /** The Constant SIZE. */
  public static final int SIZE = 90;

  /**
   * Instantiates a new ribbon panel button.
   *
   * @param text the text
   */
  public RibbonPanelButton(String text) {
    super(text);

    setup();
  }

  /**
   * Instantiates a new ribbon panel button.
   *
   * @param icon the icon
   */
  public RibbonPanelButton(ModernIcon icon) {
    super(icon);

    setup();
  }

  /**
   * Instantiates a new ribbon panel button.
   *
   * @param text the text
   * @param icon the icon
   */
  public RibbonPanelButton(String text, ModernIcon icon) {
    super(text, icon);

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    UI.setSize(this, SIZE, SIZE);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.button.ModernButton#drawForegroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    int x = (mRect.getW() - getIcon().getWidth()) / 2;
    int y = AssetService.ICON_SIZE_16;

    mIcon.drawIcon(g2, x, y, 16);

    g2.setColor(TEXT_COLOR);
    g2.setFont(FONT);
    g2.drawString(getText(), (mRect.getW() - g2.getFontMetrics().stringWidth(getText())) / 2, mRect.getH() - 16);
  }
}
