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

import java.awt.LayoutManager;

import org.jebtk.core.NameGetter;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.theme.ColorStyle;
import org.jebtk.modern.text.ITextProperty;

// TODO: Auto-generated Javadoc
/**
 * Provides the basic functionality of a button and some rendering, but does not
 * provide a concrete implementation of the button.
 *
 * @author Antony Holmes
 *
 */
public abstract class ModernButtonWidget extends ModernClickWidget implements ITextProperty, NameGetter {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member text1.
   */
  // protected JLabel label1 = new JLabel();
  protected String mText1 = null; // TextUtils.EMPTY_STRING;

  /**
   * The member icon.
   */
  protected ModernIcon mIcon = null;

  // protected Image buttonImage = null;
  // protected Image disabledButtonImage = null;
  // protected Image backgroundButtonImage = null;

  /**
   * Instantiates a new modern button widget.
   */
  public ModernButtonWidget() {
    init();
  }

  /**
   * Instantiates a new modern button widget.
   *
   * @param text1 the text1
   */
  public ModernButtonWidget(String text1) {
    this();

    setText(text1);
  }

  /**
   * Instantiates a new modern button widget.
   *
   * @param icon the icon
   */
  public ModernButtonWidget(ModernIcon icon) {
    this();

    setIcon(icon);
  }

  /**
   * Instantiates a new modern button widget.
   *
   * @param text1 the text1
   * @param icon  the icon
   */
  public ModernButtonWidget(String text1, ModernIcon icon) {
    this();

    setText(text1);
    setIcon(icon);
  }

  public ModernButtonWidget(LayoutManager manager) {
    super(manager);

    init();
  }

  /**
   * Sets the icon.
   *
   * @param icon the new icon
   */
  public void setIcon(ModernIcon icon) {
    mIcon = icon;

    autoSize();
  }

  /**
   * Gets the icon.
   *
   * @return the icon
   */
  public ModernIcon getIcon() {
    return mIcon;
  }

  /**
   * Gets the disabled icon.
   *
   * @return the disabled icon
   */
  public ModernIcon getDisabledIcon() {
    return mIcon.getDisabledIcon();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.text.TextProperty#getText()
   */
  @Override
  public String getText() {
    return mText1;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.text.TextProperty#setText(java.lang.String)
   */
  @Override
  public void setText(String text) {
    mText1 = text;

    setClickMessage(text);

    autoSize();
  }

  @Override
  public String getName() {
    return getText();
  }

  /**
   * Sets whether the button text is visible or not.
   * 
   * @param show
   */
  public void setShowText(boolean show) {
    // Do nothing
  }

  /**
   * Returns whether the button text should be visible or not.
   *
   * @return the show text
   */
  public boolean getShowText() {
    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernClickWidget#setHighlighted(boolean)
   */
  @Override
  public void setHighlighted(boolean highlight) {
    if (isEnabled()) {
      mHighlight = highlight;

      repaint();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.JComponent#setEnabled(boolean)
   */
  @Override
  public void setEnabled(boolean enabled) {
    super.setEnabled(enabled);

    setForeground(isEnabled() ? TEXT_COLOR : ALT_TEXT_COLOR);
  }

  private void init() {

    // setAnimations("button-fill"); // new ButtonHighlightAnimation(this));
    // //SimpleButtonAnimation.BUTTON_ANIMATION);

    // getDrawStates().set(DrawUIService.getInstance().getRenderer("button-draw"));
    // addAnimations("button-fill");
    addAnimations("css-hover");

    setButtonStyle(ButtonStyle.BUTTON);
    setColorStyle(ColorStyle.THEME);
  }

  public ModernButtonWidget setColorStyle(ColorStyle style) {
    switch (style) {
    case RIBBON:
      addStyleClass("ribbon-button");
      break;
    default:
      addStyleClass("theme-button");
      break;
    }

    return this;
  }

  /**
   * Set the drawing style of the button.
   * 
   * @param styles
   * @return
   */
  public ModernButtonWidget setButtonStyle(ButtonStyle... styles) {
    // getAnimations().clear();
    // getDrawStates().clear();

    for (ButtonStyle style : styles) {
      switch (style) {
      case PILL:
      case CIRCLE:
        addStyleClass("pill");
        // getDrawStates().set("fill",
        // DrawUIService.getInstance().getRenderer("pill-fill"));
        break;
      case PILL_CONTENT:
        addStyleClass("content-box", "pill");

        // addAnimations("pill-content");
        // addAnimations("button-fill");

        // getDrawStates().set(DrawUIService.getInstance().getRenderer("pill-content"))
        // .add(DrawUIService.getInstance().getRenderer("button-fill"));
        break;
      case CIRCLE_OUTLINE:
        // addAnimations("circle-outline");
        break;
      case RECT:
        addStyleClass("rect");
        break;
      case ROUNDED:
        addStyleClass("rounded-rect");
        break;
      case OUTLINE:
        addStyleClass("outline");
        break;
      default:
        break;
      }
    }

    return this;
  }
}
