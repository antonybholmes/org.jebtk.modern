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
package org.jebtk.modern.button;

import java.awt.Dimension;
import java.awt.Graphics2D;

import org.jebtk.modern.AssetService;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.graphics.icons.RadioButtonSelectedVectorIcon;
import org.jebtk.modern.graphics.icons.RadioButtonVectorIcon;

// TODO: Auto-generated Javadoc
/**
 * Radio version of a check box.
 *
 * @author Antony Holmes
 */
public class ModernRadioButton extends ModernTwoStateWidget {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The Constant ICON_1. */
  public static final ModernIcon ICON_1 = AssetService.getInstance().loadIcon(RadioButtonVectorIcon.class, 16);

  /** The Constant ICON_2. */
  public static final ModernIcon ICON_2 = AssetService.getInstance().loadIcon(RadioButtonSelectedVectorIcon.class, 16);

  /**
   * The member text1.
   */
  protected String mText1;

  /**
   * Instantiates a new modern radio button.
   */
  public ModernRadioButton() {
    setup();
  }

  /**
   * Instantiates a new modern radio button.
   *
   * @param text1 the text1
   */
  public ModernRadioButton(String text1) {
    this(text1, false);
  }

  /**
   * Instantiates a new modern radio button.
   *
   * @param text1    the text1
   * @param selected the selected
   */
  public ModernRadioButton(String text1, boolean selected) {
    this(text1, selected, ModernButton.getIconButtonSize(text1));
  }

  /**
   * Instantiates a new modern radio button.
   *
   * @param text1 the text 1
   * @param width the width
   */
  public ModernRadioButton(String text1, int width) {
    this(text1, new Dimension(width, ModernWidget.WIDGET_HEIGHT));
  }

  /**
   * Instantiates a new modern radio button.
   *
   * @param text1 the text1
   * @param size  the size
   */
  public ModernRadioButton(String text1, Dimension size) {
    this(text1, false, size);
  }

  /**
   * Instantiates a new modern radio button.
   *
   * @param text1    the text1
   * @param selected the selected
   * @param size     the size
   */
  public ModernRadioButton(String text1, boolean selected, Dimension size) {
    setText(text1);

    setSelected(selected);

    UI.setSize(this, size);

    setup();
  }

  private void setup() {
    setCSSMode(false);
    addStyleClass("checkbox", "content-outline-highlight");
    setAnimations("radio-button");
  }

  /**
   * Gets the button text.
   *
   * @return the button text.
   */
  public String getText() {
    return mText1;
  }

  /**
   * Sets the button text.
   *
   * @param text the new text
   */
  public void setText(String text) {
    mText1 = text;

    setClickMessage(text);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawBackgroundAA(java.awt.Graphics2D)
   */
  /*
   * @Override public void drawBackgroundAA(Graphics2D g2) { // Do nothing
   * 
   * int x = mInternalRect.getX() + 1; int y = (getHeight() - 16) / 2;
   * 
   * if (isSelected()) { ICON_2.drawIcon(g2, x, y, 16); } else {
   * ICON_1.drawIcon(g2, x, y, 16); } }
   */

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    int iconX = mInternalRect.getX();
    int iconY = (getHeight() - RadioAnimation.RADIO_SIZE) / 2;

    g2.setColor(TEXT_COLOR);

    if (mText1 != null) {
      int x = iconX + RadioAnimation.RADIO_SIZE + PADDING;

      g2.drawString(mText1, x, getTextYPosCenter(g2, getHeight()));
    }

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernTwoStateWidget#toggleSelected()
   */
  @Override
  protected final void toggleSelected() {
    // The radio button can only toggle to the
    // on state
    toggleSelected(true);
  }
}
