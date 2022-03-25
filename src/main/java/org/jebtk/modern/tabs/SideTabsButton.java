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
package org.jebtk.modern.tabs;

import java.awt.Graphics2D;

import org.jebtk.modern.button.ModernCheckRadioButton;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.theme.DrawUIService;

/**
 * The class SideTabsButton.
 */
public class SideTabsButton extends ModernCheckRadioButton {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new side tabs button.
   *
   * @param name the name
   */
  public SideTabsButton(String name) {
    super(name);

    setup();
  }

  /**
   * Instantiates a new side tabs button.
   *
   * @param name the name
   * @param icon the icon
   */
  public SideTabsButton(String name, ModernIcon icon) {
    super(name, icon);

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    setAlignmentY(TOP_ALIGNMENT);

    // Ui.setSize(this, SIZE);
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
      if (isSelected() || mHighlight) {
        // getWidgetRenderer().drawMenu(g2, mInternalRect, RenderMode.SELECTED);

        DrawUIService.getInstance().getRenderer("button-fill").draw(g2, mRect);
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
    // Rectangle rect = new Rectangle(0, 0, getWidth(), getHeight());

    g2.setColor(getForeground());

    g2.drawString(mText1, PADDING, getTextYPosCenter(g2, getHeight()));
  }
}
