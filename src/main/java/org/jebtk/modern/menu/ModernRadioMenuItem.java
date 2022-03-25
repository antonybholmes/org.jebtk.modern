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

import java.awt.Graphics2D;

import org.jebtk.modern.AssetService;
import org.jebtk.modern.UI;
import org.jebtk.modern.button.ModernRadioButton;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.graphics.icons.RadioButtonSelectedVectorIcon;
import org.jebtk.modern.graphics.icons.RadioButtonVectorIcon;
import org.jebtk.modern.theme.DrawUIService;

// TODO: Auto-generated Javadoc
/**
 * Check box menu item.
 *
 * @author Antony Holmes
 */
public class ModernRadioMenuItem extends ModernRadioButton {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The Constant ICON_SIZE. */
  private static final int ICON_SIZE = 16;

  /** The Constant ICON_1. */
  private static final ModernIcon ICON_1 = AssetService.getInstance().loadIcon(RadioButtonVectorIcon.class, ICON_SIZE);

  /** The Constant ICON_2. */
  private static final ModernIcon ICON_2 = AssetService.getInstance().loadIcon(RadioButtonSelectedVectorIcon.class,
      ICON_SIZE);

  /**
   * Instantiates a new modern check box menu item.
   *
   * @param text the text
   */
  public ModernRadioMenuItem(String text) {
    this(text, false);
  }

  /**
   * Instantiates a new modern check box menu item.
   *
   * @param text     the text
   * @param selected the selected
   */
  public ModernRadioMenuItem(String text, boolean selected) {
    super(text);

    setToolTipsEnabled(false);

    setSelected(selected);

    UI.setSize(this, ModernMenuItem.PREFERRED_SIZE);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.widget.ModernWidget#drawBackground(java.awt.Graphics2D)
   */
  @Override
  public void drawBackground(Graphics2D g2) {
    if (isEnabled()) {
      if (mHighlight) {
        // getWidgetRenderer().drawMenu(g2, mInternalRect, RenderMode.SELECTED);
        DrawUIService.getInstance().getRenderer("button-fill").draw(g2, mInternalRect);
      }
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.button.ModernCheckButton#drawForegroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    if (mText1 != null) {
      g2.setColor(getForeground());
      g2.drawString(mText1, getHeight(), getTextYPosCenter(g2, getHeight()));
    }

    int x = (getHeight() - ICON_SIZE) / 2;
    int y = x;

    if (isSelected()) {
      ICON_2.drawIcon(g2, x, y, ICON_SIZE);
    } else {
      ICON_1.drawIcon(g2, x, y, ICON_SIZE);
    }
  }
}
