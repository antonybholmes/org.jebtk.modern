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
package org.jebtk.modern.help;

import java.awt.Graphics2D;
import java.io.IOException;
import java.net.URISyntaxException;

import org.jebtk.modern.AssetService;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.graphics.icons.HelpVectorIcon;
import org.jebtk.modern.ribbon.RibbonPanelButton;
import org.jebtk.modern.theme.ThemeService;

/**
 * The class RibbonPanelHelpButton.
 */
public class RibbonPanelHelpButton extends RibbonPanelButton implements ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The width.
   */
  private int WIDTH = 42;

  /**
   * The member product.
   */
  private GuiAppInfo mProduct;

  /**
   * Instantiates a new ribbon panel help button.
   *
   * @param product the product
   */
  public RibbonPanelHelpButton(GuiAppInfo product) {
    super("Help");

    mProduct = product;

    addClickListener(this);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.ribbon.RibbonPanelButton#drawForegroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    g2.setColor(ThemeService.getInstance().getColors().getGray(4));

    int x = (mRect.getW() - WIDTH) / 2;
    int y = 16;

    AssetService.getInstance().loadIcon(HelpVectorIcon.class, 32).drawIcon(g2, x, y, 32);

    x = (mRect.getW() - WIDTH) / 2 + (WIDTH - g2.getFontMetrics().stringWidth("?")) / 2;
    y = 16 + (WIDTH + g2.getFontMetrics().getAscent() - g2.getFontMetrics().getDescent()) / 2;

    g2.drawString("?", x, y);

    g2.setColor(TEXT_COLOR);
    g2.setFont(ThemeService.loadFont("widget.text"));

    g2.drawString(getText(), (mRect.getW() - g2.getFontMetrics().stringWidth(getText())) / 2, mRect.getH() - 16);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  public void clicked(ModernClickEvent e) {
    try {
      HelpManager.launchHelp(mProduct);
    } catch (IOException e1) {
      e1.printStackTrace();
    } catch (URISyntaxException e1) {
      e1.printStackTrace();
    }
  }
}
