/**
ColorSwatchButton.java * Copyright (C) 2016, Antony Holmes
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

import org.jebtk.modern.button.ModernButton;
import org.jebtk.modern.graphics.icons.ModernIcon;

// TODO: Auto-generated Javadoc
/**
 * The class RibbonButton2.
 */
public class RibbonButton extends ModernButton {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  public RibbonButton(String text1) {
    super(text1);

    init();
  }

  /**
   * Instantiates a new ribbon button.
   *
   * @param text1 the text 1
   * @param icon  the icon
   */
  public RibbonButton(String text1, ModernIcon icon) {
    super(text1, icon);

    init();
  }

  /**
   * Instantiates a new ribbon button2.
   *
   * @param text1 the text1
   * @param text2 the text 2
   * @param icon  the icon
   */
  public RibbonButton(String text1, String text2, ModernIcon icon) {
    super(text1, icon);

    init();
  }

  /**
   * Instantiates a new ribbon button2.
   *
   * @param icon the icon
   */
  public RibbonButton(ModernIcon icon) {
    super(icon);

    init();
  }

  /**
   * Instantiates a new ribbon button.
   *
   * @param text1        the text 1
   * @param icon         the icon
   * @param toolTipTitle the tool tip title
   * @param toolTipText  the tool tip text
   */
  public RibbonButton(String text1, ModernIcon icon, String toolTipTitle, String toolTipText) {
    this(text1, icon);

    setToolTip(toolTipTitle, toolTipText);

    init();
  }

  public RibbonButton(ModernIcon icon, String toolTipTitle, String toolTipText) {
    this(icon);

    setToolTip(toolTipTitle, toolTipText);

    init();
  }

  /**
   * Setup.
   */
  private void init() {
    addStyleClass("ribbon-button");
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.widget.ModernClickWidget#drawBackgroundAA(java.awt.
   * Graphics2D)
   */
  /*
   * @Override public void drawBackgroundAA(Graphics2D g2) { if (isEnabled()) { if
   * (isSelected() || mHighlight) { getWidgetRenderer().drawRibbonButton(g2,
   * mInternalRect, RenderMode.SELECTED); } } }
   */
}
