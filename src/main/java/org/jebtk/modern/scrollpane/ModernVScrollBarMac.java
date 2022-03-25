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
package org.jebtk.modern.scrollpane;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import org.jebtk.core.Props;

/**
 * Flat, minimal chrome implementation of a scroll pane control.
 *
 * @author Antony Holmes
 *
 */
public class ModernVScrollBarMac extends ModernVScrollBar {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new modern v scroll bar office.
   */
  public ModernVScrollBarMac(ModernScrollPane scrollPane, Component c) {
    setButtonSize(0);
    setInternalFixedDimension(10);
    setMinimumScrollBarSize(20);

    // Bind both scroll bar and the component to the animation so that
    // if the user moves over the component, the scroll bar will also
    // animate
    setAnimations(new MacScrollAnimation(this));
    // bind(scrollPane)
    // .bind(c));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.scrollpane.ModernScrollBar#scrollBarSetup()
   */
  @Override
  public void scrollBarSetup() {
    // Determine the number of pixels that can be scrolled which is the
    // height of the scrollbar less the space for the two end buttons
    scrollBarSetup(mInternalRect.getH());
  }

  /*
   * @Override public void paintScrollBarBase(Graphics2D g2) { Graphics2D g2Temp =
   * ImageUtils.createAATextGraphics(g2);
   * 
   * int rounding = mIternalFixedDim;
   * 
   * try { g2Temp.setColor(ROUNDED_SCROLLBAR_BACKGROUND_COLOR);
   * 
   * g2Temp.fillRoundRect(getInsets().left, getInsets().top, rounding,
   * mInternalRect.getH(), rounding, rounding); } finally { g2Temp.dispose(); } }
   */

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.scrollpane.ModernScrollBar#paintScrollBar(java.awt.
   * Graphics2D, java.awt.Rectangle)
   */
  @Override
  public void paintScrollBar(Graphics2D g2, Rectangle r) {
    // Graphics2D g2Temp = ImageUtils.createAATextGraphics(g2);

    int rounding = mIternalFixedDim;

    // try {
    // g2.setColor(ROUNDED_SCROLLBAR_COLOR);

    // g2.fillRoundRect(r.x, r.y, r.width, r.height, rounding, rounding);

    getAnimations().draw(this, g2, new Props().set("rect", r).set("rounding", rounding));

    // } finally {
    // g2Temp.dispose();
    // }
  }

}
