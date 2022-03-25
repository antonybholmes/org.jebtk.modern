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
package org.jebtk.modern.graphics;

import java.awt.Color;

import org.jebtk.modern.AssetService;
import org.jebtk.modern.UI;
import org.jebtk.modern.button.ModernOptionalDropDownMenuButton;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.graphics.color.MultiColorPicker;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.menu.ModernIconMenuItem;
import org.jebtk.modern.menu.ModernMenuSeparator;
import org.jebtk.modern.menu.ModernPopupMenu;
import org.jebtk.modern.menu.ModernTitleMenuItem;

/**
 * The class FillColorButton.
 */
public class FillColorButton extends ModernOptionalDropDownMenuButton implements ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The color picker.
   */
  private MultiColorPicker colorPicker = new MultiColorPicker();

  /**
   * Instantiates a new fill color button.
   *
   * @param name the name
   */
  public FillColorButton(String name) {
    this(name, AssetService.getInstance().loadIcon("paint", 16));
  }

  /**
   * Instantiates a new fill color button.
   *
   * @param name the name
   * @param icon the icon
   */
  public FillColorButton(String name, ModernIcon icon) {
    super(name, icon, new ModernPopupMenu());

    setup();
  }

  /**
   * Instantiates a new fill color button.
   *
   * @param icon the icon
   */
  public FillColorButton(ModernIcon icon) {
    super(icon, new ModernPopupMenu());

    setup();

    UI.setSize(this, 40, WIDGET_HEIGHT);
  }

  /**
   * Setup.
   */
  private void setup() {
    UI.setSize(this, 120, WIDGET_HEIGHT);

    mMenu.add(new ModernTitleMenuItem("Colors"));
    mMenu.add(colorPicker);

    mMenu.add(new ModernMenuSeparator());
    mMenu.add(new ModernIconMenuItem("More colors...",
        AssetService.getInstance().loadIcon("palette", AssetService.ICON_SIZE_16)));

    colorPicker.addClickListener(this);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  @Override
  public void clicked(ModernClickEvent e) {
    mMenu.setVisible(false);

    fireClicked(new ModernClickEvent(this, e.getMessage()));

    mHighlight = false;
    setSelected(false);

    repaint();
  }

  /**
   * Gets the selected color.
   *
   * @return the selected color
   */
  public Color getSelectedColor() {
    return colorPicker.getSelectedColor();
  }

  /**
   * Sets the selected color.
   *
   * @param color the new selected color
   */
  public void setSelectedColor(Color color) {
    colorPicker.setSelectedColor(color);
  }

}
