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
package org.jebtk.modern.text;

import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.button.ModernButton;
import org.jebtk.modern.css.CSSWidget;
import org.jebtk.modern.theme.DrawUIService;

// TODO: Auto-generated Javadoc
/**
 * Adds a border around text boxes so they are more visible to the user.
 *
 * @author Antony Holmes
 */
public class ModernTextBorderPanel extends CSSWidget implements ITextProperty {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member component.
   */
  private JComponent mComponent = null;

  /**
   * The constant STANDARD_SIZE.
   */
  public static final Dimension STANDARD_SIZE = new Dimension(100, ModernWidget.WIDGET_HEIGHT);

  /**
   * Instantiates a new modern text border panel.
   *
   * @param textField the text field
   * @param color     the color
   */
  public ModernTextBorderPanel(ModernTextField textField) {
    mComponent = textField;

    setBody(textField);

    setup();
  }

  /**
   * Instantiates a new modern text border panel.
   *
   * @param textField the text field
   * @param color     the color
   */
  public ModernTextBorderPanel(ModernTextBox textField) {
    mComponent = textField;

    setBody(textField);

    setup();
  }

  /**
   * Instantiates a new modern text border panel.
   *
   * @param textField the text field
   * @param width     the width
   */
  public ModernTextBorderPanel(ModernTextField textField, int width) {
    this(textField, new Dimension(width, ModernButton.getButtonHeight()));
  }

  /**
   * Instantiates a new modern text border panel.
   *
   * @param textField the text field
   * @param width     the width
   */
  public ModernTextBorderPanel(ModernTextBox textField, int width) {
    this(textField, new Dimension(width, ModernButton.getButtonHeight()));
  }

  /**
   * Instantiates a new modern text border panel.
   *
   * @param textField the text field
   * @param color     the color
   * @param size      the size
   */
  public ModernTextBorderPanel(ModernTextField textField, Dimension size) {

    mComponent = textField;

    setBody(textField);

    UI.setSize(this, size);

    setup();
  }

  /**
   * Instantiates a new modern text border panel.
   *
   * @param textField the text field
   * @param color     the color
   * @param size      the size
   */
  public ModernTextBorderPanel(ModernTextBox textField, Dimension size) {
    mComponent = textField;

    setBody(textField);

    UI.setSize(this, size);

    setup();
  }

  /**
   * Instantiates a new modern text border panel.
   *
   * @param textArea the text area
   */
  public ModernTextBorderPanel(ModernTextArea textArea) {

    mComponent = textArea;

    setBody(textArea);

    setup();
  }

  /**
   * Instantiates a new modern text border panel.
   *
   * @param textField the text field
   */
  public ModernTextBorderPanel(ModernPasswordField textField) {

    mComponent = textField;

    setBody(textField);

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    // mComponent.setBorder(SMALL_BORDER);

    // setMinimumSize(mComponent.getMinimumSize());
    // setMaximumSize(mComponent.getMaximumSize());

    // UI.setSize(this, ModernWidget.STANDARD_SIZE);

    setBorder(SMALL_BORDER);

    addStyleClass("content", "content-outline", "rounded-rect");

    setAnimations(new TextBorderAnimation(this));
  }

//  @Override
//  public void drawAnimatedBackground(Graphics2D g2) {
//    DrawUIService.getInstance().getRenderer("content-box").draw(this, g2, mRect);
//
//    super.drawAnimatedBackground(g2);
//  }

  /**
   * Gets the text component.
   *
   * @return the text component
   */
  public JComponent getTextComponent() {
    return mComponent;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.text.TextProperty#getText()
   */
  @Override
  public String getText() {
    return ((ITextProperty) mComponent).getText();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.text.TextProperty#setText(java.lang.String)
   */
  @Override
  public void setText(String text) {
    ((ITextProperty) mComponent).setText(text);
  }
}
