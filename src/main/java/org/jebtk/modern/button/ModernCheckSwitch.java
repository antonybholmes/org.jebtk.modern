/**
 * Copyright 2016 Antony Holmes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jebtk.modern.button;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.border.Border;

import org.jebtk.core.text.TextUtils;
import org.jebtk.modern.UI;

/**
 * The Class ModernCheckSwitch offers a sliding on/off switch similar to what
 * you might find in Android or iOS.
 */
public class ModernCheckSwitch extends CheckBox {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The Constant SWITCH_ICON_WIDTH. */
  // protected static final int SWITCH_ICON_WIDTH = 32;

  // How many pixels we offset the slider to account for rendering going
  /** The Constant SWITCH_ICON_OFFSET. */
  // outside the boundary from antialiasing etc.
  protected static final int SWITCH_ICON_OFFSET = 0; // 1;

  protected static final int SWITCH_MARGIN = 1; // 1;

  public static final int SWITCH_MARGIN_2X = 2 * SWITCH_MARGIN;

  protected static final int SWITCH_ICON_OFFSET_2 = 2 * SWITCH_ICON_OFFSET;

  /** The Constant SLIDER_OFFSET. */
  protected static final int SLIDER_OFFSET = 1;

  /** The Constant ORB_HEIGHT. */
  protected static final int ORB_HEIGHT = 22;

  protected static final int SLIDER_HEIGHT = ORB_HEIGHT;// + 2; // - 4;

  /** The Constant SLIDER_WIDTH. */
  protected static final int SLIDER_WIDTH = ORB_HEIGHT * 3 / 2; // (int)
  // Math.round(ORB_HEIGHT
  // *
  // Mathematics.GOLDEN_RATIO);
  // //3 / 2;

  /** The Constant SWITCH_ON_OFFSET. */
  protected static final int SWITCH_ON_OFFSET = SLIDER_WIDTH - ORB_HEIGHT;

  private Color mTextColor;

  public ModernCheckSwitch() {
    this(TextUtils.EMPTY_STRING);
  }

  /**
   * Instantiates a new modern check box.
   *
   * @param text the text
   */
  public ModernCheckSwitch(String text) {
    this(text, false);
  }

  public ModernCheckSwitch(String text, int width) {
    this(text);

    setSize(width);
  }

  /**
   * Instantiates a new modern check box.
   *
   * @param selected the selected
   */
  public ModernCheckSwitch(boolean selected) {
    this(TextUtils.EMPTY_STRING, selected);
  }

  /**
   * Instantiates a new modern check box.
   *
   * @param text     the text
   * @param selected the selected
   */
  public ModernCheckSwitch(String text, boolean selected) {
    super(text);

    addStyleClass("content-outline", "check-switch");

    setAnimations("check-switch");

    setSelected(selected);

    setSize();
  }

  public ModernCheckSwitch(String text, boolean selected, Color color) {
    this(text, selected, color, TEXT_COLOR);
  }

  public ModernCheckSwitch(String text, boolean selected, Color color, Color textColor) {
    this(text, selected);

    mTextColor = textColor;

    //getAnimations().set(new CheckSwitchAnimation(this), new CheckSwitchChangeAnimation(this));

    //setSelected(selected);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.ModernWidget#setFont(java.awt.Font)
   */
  @Override
  public void setFont(Font font) {
    super.setFont(font);

    setSize();
  }

  /**
   * Gets the text.
   *
   * @return the text
   */
  @Override
  public String getText() {
    return mText1;
  }

  /**
   * Sets the text.
   *
   * @param text the new text
   */
  @Override
  public void setText(String text) {
    mText1 = text;

    setSize();

    setClickMessage(text);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.ModernComponent#setBorder(javax.swing.border.Border)
   */
  @Override
  public void setBorder(Border border) {
    super.setBorder(border);

    setSize();
  }

  /**
   * Sets the size.
   */
  private void setSize() {
    setSize(getInsets().left + ModernButton.getStringWidth(mText1) + SLIDER_WIDTH + QUAD_PADDING);

  }

  private void setSize(int width) {
    UI.setSize(this, width, ModernButton.getButtonHeight());

  }

  /*
   * @Override public void drawBackgroundAA(Graphics2D g2) { drawSlider(g2); }
   */

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.ui.button.ModernCheckBox#drawForegroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    g2.setColor(mTextColor); // isSelected() ? TEXT_COLOR :
    // TEXT_DISABLED_COLOR);
    g2.drawString(mText1, getInsets().left + SLIDER_WIDTH + PADDING, getTextYPosCenter(g2, getHeight()));
  }
}
