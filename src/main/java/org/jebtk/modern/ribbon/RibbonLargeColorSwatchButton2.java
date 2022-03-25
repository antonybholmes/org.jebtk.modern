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

import java.awt.Color;
import java.awt.Graphics2D;

import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.graphics.color.ColorPopupMenu2;
import org.jebtk.modern.graphics.color.ColorSelectionModel;
import org.jebtk.modern.window.ModernWindow;

/**
 * Allow users to select a color for an object etc.
 * 
 * @author Antony Holmes
 *
 */
public class RibbonLargeColorSwatchButton2 extends RibbonLargeDropDownButton2 implements ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new ribbon large color swatch button.
   *
   * @param parent the parent
   */
  public RibbonLargeColorSwatchButton2(ModernWindow parent) {
    this(parent, Color.BLACK);
  }

  /**
   * Instantiates a new color swatch button.
   *
   * @param parent the parent
   * @param color  the color
   */
  public RibbonLargeColorSwatchButton2(ModernWindow parent, Color color) {
    super(new ColorPopupMenu2(parent, color));

    mMenu.addClickListener(this);

    // UI.setSize(this, ModernWidget.SIZE_48);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.button.ModernDropDownButton#drawForegroundAA(java.
   * awt. Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    int x = PADDING;

    int y = (getHeight() - 6) / 2;

    Color color = ((ColorPopupMenu2) mMenu).getSelectedColor();

    if (color != null) {
      g2.setColor(color);
      g2.fillRect(x, y, 16, 6);

      g2.setColor(Color.BLACK);
      g2.drawRect(x, y, 16, 6);
    }

    // g2.setColor(ThemeService.getInstance().getColors().getHighlight(4));
    // g2.drawRect(x, y, 32, Resources.ICON_SIZE_16);

    TRIANGLE_ICON.drawIcon(g2, getWidth() - 16, (getHeight() - 16) / 2, 16);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  @Override
  public void clicked(ModernClickEvent e) {
    mPressed = false;
    mHighlight = false;

    repaint();

    if (e.getMessage().equals(ColorSelectionModel.COLOR_CHANGED)) {
      fireClicked(new ModernClickEvent(this, ColorSelectionModel.COLOR_CHANGED));
    }
  }

  /**
   * Gets the selected color.
   *
   * @return the selected color
   */
  public Color getSelectedColor() {
    return ((ColorPopupMenu2) mMenu).getSelectedColor();
  }

  /**
   * Sets the selected color.
   *
   * @param color the new selected color
   */
  public void setSelectedColor(Color color) {
    ((ColorPopupMenu2) mMenu).setSelectedColor(color);

    repaint();
  }
}
