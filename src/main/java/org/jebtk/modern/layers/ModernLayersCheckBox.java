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
package org.jebtk.modern.layers;

import java.awt.Color;
import java.awt.Graphics2D;

import org.jebtk.modern.AssetService;
import org.jebtk.modern.button.ModernCheckBox;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.theme.ThemeService;

/**
 * The class ModernLayersCheckBox.
 */
public class ModernLayersCheckBox extends ModernCheckBox {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant IMAGE_CHECKED.
   */
  public static final ModernIcon IMAGE_CHECKED = AssetService.getInstance().loadIcon("layer_visible", 16);

  /**
   * Instantiates a new modern layers check box.
   */
  public ModernLayersCheckBox() {

  }

  /**
   * Instantiates a new modern layers check box.
   *
   * @param selected the selected
   */
  public ModernLayersCheckBox(boolean selected) {
    super(selected);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.button.ModernCheckBox#drawForegroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    int iconX = mInternalRect.getX() + PADDING;
    int iconY = (getHeight() - 16) / 2;

    g2.setColor(Color.WHITE);
    g2.fillRect(iconX, iconY, 16, 16);

    if (isSelected()) {
      IMAGE_CHECKED.drawIcon(g2, iconX, iconY, AssetService.ICON_SIZE_16);
    }

    g2.setColor(ThemeService.getInstance().getColors().getGray(5));
    g2.drawRect(iconX, iconY, 16, 16);
  }
}
