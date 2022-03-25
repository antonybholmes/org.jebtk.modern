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

import java.awt.Dimension;

import org.jebtk.modern.UI;
import org.jebtk.modern.graphics.icons.ModernIcon;

// TODO: Auto-generated Javadoc
/**
 * A ribbon implementation of a radio button. Once selected it cannot be
 * unselected by the user, instead it must be done programmatically through the
 * API or else have the button in a ModernButtonGroup so that selection of
 * another button causes it to become unselected.
 * 
 * @author Antony Holmes
 */
public class RibbonLargeRadioButton extends RibbonLargeCheckButton {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new ribbon large radio button2.
   *
   * @param text1 the text1
   */
  public RibbonLargeRadioButton(String text1) {
    this(text1, false);
  }

  /**
   * Instantiates a new ribbon large radio button.
   *
   * @param text1    the text 1
   * @param selected the selected
   */
  public RibbonLargeRadioButton(String text1, boolean selected) {
    super(text1);

    setSelected(selected);
  }

  /**
   * Instantiates a new ribbon large radio button.
   *
   * @param text1 the text 1
   * @param icon  the icon
   */
  public RibbonLargeRadioButton(String text1, ModernIcon icon) {
    this(text1, icon, false);
  }

  /**
   * Instantiates a new ribbon large radio button2.
   *
   * @param text1    the text1
   * @param icon     the icon
   * @param selected the selected
   */
  public RibbonLargeRadioButton(String text1, ModernIcon icon, boolean selected) {
    super(text1, icon);

    setSelected(selected);
  }

  /**
   * Instantiates a new ribbon large radio button.
   *
   * @param text1 the text 1
   * @param text2 the text 2
   * @param icon  the icon
   */
  public RibbonLargeRadioButton(String text1, String text2, ModernIcon icon) {
    this(text1, text2, icon, false);
  }

  /**
   * Instantiates a new ribbon large radio button2.
   *
   * @param text1    the text1
   * @param text2    the text 2
   * @param icon     the icon
   * @param selected the selected
   */
  public RibbonLargeRadioButton(String text1, String text2, ModernIcon icon, boolean selected) {
    super(text1, text2, icon);

    setSelected(selected);
  }

  /**
   * Instantiates a new ribbon large radio button2.
   *
   * @param text1 the text1
   * @param icon  the icon
   * @param size  the size
   */
  public RibbonLargeRadioButton(String text1, ModernIcon icon, Dimension size) {
    this(text1, icon);

    UI.setSize(this, size);
  }

  /**
   * Instantiates a new ribbon large radio button2.
   *
   * @param text         the text
   * @param icon         the icon
   * @param toolTipTitle the tool tip title
   * @param toolTipText  the tool tip text
   */
  public RibbonLargeRadioButton(String text, ModernIcon icon, String toolTipTitle, String toolTipText) {
    this(text, icon, false, toolTipTitle, toolTipText);
  }

  /**
   * Instantiates a new ribbon large radio button2.
   *
   * @param text         the text
   * @param icon         the icon
   * @param toolTipTitle the tool tip title
   * @param toolTipText  the tool tip text
   * @param size         the size
   */
  public RibbonLargeRadioButton(String text, ModernIcon icon, String toolTipTitle, String toolTipText, Dimension size) {
    this(text, icon, toolTipTitle, toolTipText);

    UI.setSize(this, size);
  }

  /**
   * Instantiates a new ribbon large radio button2.
   *
   * @param text         the text
   * @param icon         the icon
   * @param selected     the selected
   * @param toolTipTitle the tool tip title
   * @param toolTipText  the tool tip text
   */
  public RibbonLargeRadioButton(String text, ModernIcon icon, boolean selected, String toolTipTitle,
      String toolTipText) {
    this(text, icon, selected);

    setToolTip(toolTipTitle, toolTipText);
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
