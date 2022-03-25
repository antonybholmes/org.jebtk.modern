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
package org.jebtk.modern.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

import org.jebtk.core.ColorUtils;
import org.jebtk.modern.UI;
import org.jebtk.modern.button.ModernButton;
import org.jebtk.modern.graphics.icons.ModernIcon;

/**
 * Outline button with white background for more contract on off-white panels.
 * 
 * @author Antony Holmes
 *
 */
public class ModernSplashButton extends ModernButton {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The Constant BACKGROUND. */
  private static final Color BACKGROUND = ColorUtils.getTransparentColor90(Color.WHITE);

  /** The Constant SIZE. */
  private static final Dimension SIZE = new Dimension(48, 32);

  /**
   * Instantiates a new modern splash button.
   *
   * @param text1 the text1
   */
  public ModernSplashButton(String text1) {
    super(text1);

    setup();
  }

  /**
   * Instantiates a new modern splash button.
   *
   * @param icon the icon
   */
  public ModernSplashButton(ModernIcon icon) {
    super(icon);

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    setBackground(BACKGROUND);
    setForeground(Color.WHITE);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.button.ModernButtonWidget#drawBackground(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawBackground(Graphics2D g2) {
    if (mHighlight) {
      fillBackground(g2);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.button.ModernButton#autoSize()
   */
  @Override
  public void autoSize() {
    UI.setSize(this, SIZE);
  }
}
