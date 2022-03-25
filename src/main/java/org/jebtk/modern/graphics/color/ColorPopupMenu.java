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
package org.jebtk.modern.graphics.color;

import java.awt.Color;

import javax.swing.BorderFactory;

import org.jebtk.modern.AssetService;
import org.jebtk.modern.BorderService;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.dialog.ModernDialogStatus;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.menu.ModernIconMenuItem;
import org.jebtk.modern.menu.ModernPopupMenu;
import org.jebtk.modern.text.ModernDialogHeadingLabel;
import org.jebtk.modern.theme.ThemeService;
import org.jebtk.modern.window.ModernWindow;

/**
 * The class ColorPopupMenu.
 */
public class ColorPopupMenu extends ModernPopupMenu {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The Constant COLOR_PICKER_BACKGROUND. */
  public static final Color COLOR_PICKER_BACKGROUND = ThemeService.getInstance().getColors().getGray(1);

  /**
   * The member color picker.
   */
  private final MultiColorPicker mColorPicker = new MultiColorPicker();

  /**
   * The member standard color picker.
   */
  private final StandardColorPicker mStandardColorPicker = new StandardColorPicker();

  /**
   * The member parent.
   */
  private final ModernWindow mParent;

  /**
   * The member color.
   */
  private Color mColor = Color.BLACK;

  /**
   * Instantiates a new color popup menu.
   *
   * @param parent the parent
   * @param color  the color
   */
  public ColorPopupMenu(ModernWindow parent, Color color) {
    mParent = parent;

    mColor = color;

    setup();
  }

  /**
   * Instantiates a new color popup menu.
   *
   * @param parent the parent
   */
  public ColorPopupMenu(ModernWindow parent) {
    mParent = parent;

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    setBackground(COLOR_PICKER_BACKGROUND);

    setBorder(
        BorderFactory.createCompoundBorder(ModernWidget.LINE_BORDER, BorderService.getInstance().createBorder(4)));

    // Dimension size = new Dimension(mColorPicker.getPreferredSize().width,
    // ModernWidget.WIDGET_HEIGHT);

    add(new ModernDialogHeadingLabel("Theme Colors"));

    add(mColorPicker);

    add(new ModernDialogHeadingLabel("Standard Colors"));

    add(mStandardColorPicker);

    // add(new ModernIconMenuItem("No Color",
    // UIService.getInstance().loadIcon("blank", 16),
    // size));

      // add(new ModernMenuDivider());
    
    addSpace();

    add(new ModernIconMenuItem("More Colors...", AssetService.getInstance().loadIcon("color_wheel", 16)));

    mColorPicker.addClickListener(this);
    mStandardColorPicker.addClickListener(this);

    // addClickListener(this);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.menu.ModernPopup#clicked(org.abh.lib.ui.modern.event.
   * ModernClickEvent)
   */
  @Override
  public void clicked(ModernClickEvent e) {
    setVisible(false);

    if (e.getSource().equals(mColorPicker)) {
      mColor = mColorPicker.getSelectedColor();

      fireClicked(new ModernClickEvent(this, ColorSelectionModel.COLOR_CHANGED));
    } else if (e.getSource().equals(mStandardColorPicker)) {
      mColor = mStandardColorPicker.getSelectedColor();

      fireClicked(new ModernClickEvent(this, ColorSelectionModel.COLOR_CHANGED));
    } else if (e.getMessage().equals("No Color")) {
      mColor = null;

      fireClicked(new ModernClickEvent(this, ColorSelectionModel.COLOR_CHANGED));
    } else if (e.getMessage().equals("More Colors...")) {
      ColorDialog dialog = new ColorDialog(mParent, mColor);

      dialog.setVisible(true);

      if (dialog.getStatus() == ModernDialogStatus.OK) {
        mColor = dialog.getColor();

        fireClicked(new ModernClickEvent(this, ColorSelectionModel.COLOR_CHANGED));
      }
    } else {
      // do nothing
    }
  }

  /**
   * Gets the selected color.
   *
   * @return the selected color
   */
  public Color getSelectedColor() {
    return mColor;
  }

  /**
   * Sets the selected color.
   *
   * @param color the new selected color
   */
  public void setSelectedColor(Color color) {
    mColor = color;
  }
}
