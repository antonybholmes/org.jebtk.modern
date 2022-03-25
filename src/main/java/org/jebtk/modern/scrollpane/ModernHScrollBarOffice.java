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

import java.awt.Graphics2D;
import java.awt.Rectangle;

import org.jebtk.modern.AssetService;
import org.jebtk.modern.graphics.icons.CheveronLeftVectorIcon;
import org.jebtk.modern.graphics.icons.CheveronRightVectorIcon;
import org.jebtk.modern.graphics.icons.ModernIcon;

/**
 * Flat, minimal chrome implementation of a scroll pane control.
 *
 * @author Antony Holmes
 *
 */
public class ModernHScrollBarOffice extends ModernHScrollBar {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant LEFT_ARROW_ICON.
   */
  protected static final ModernIcon LEFT_ARROW_ICON = AssetService.getInstance().loadIcon(CheveronLeftVectorIcon.class,
      SCROLLBAR_SIZE);

  /**
   * The constant RIGHT_ARROW_ICON.
   */
  protected static final ModernIcon RIGHT_ARROW_ICON = AssetService.getInstance()
      .loadIcon(CheveronRightVectorIcon.class, SCROLLBAR_SIZE); // Resources.getInstance().loadIcon("right_scroll",
  // Resources.ICON_SIZE_16);

  /**
   * Instantiates a new modern H scroll bar office.
   */
  public ModernHScrollBarOffice() {
    setBorder(TOP_BORDER);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.scrollpane.ModernScrollBar#scrollBarSetup()
   */
  @Override
  public void scrollBarSetup() {
    scrollBarSetup(mInternalRect.getW() - 2 * mIternalFixedDim);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.scrollpane.ModernScrollBar#paintScrollBarBase(java.awt.
   * Graphics2D)
   */
  @Override
  protected void paintScrollBarBase(Graphics2D g2) {
    fill(g2, BASE_COLOR, mInternalRect);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.scrollpane.ModernScrollBar#paintScrollBar(java.awt.
   * Graphics2D, java.awt.Rectangle)
   */
  @Override
  public void paintScrollBar(Graphics2D g2, Rectangle r) {
    if (mHighlightScroll) {
      paintScrollBarHighlighted(g2, r);
    } else {
      paintScrollBarButton(g2, r);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.scrollpane.ModernHScrollBar#paintUpButton(java.awt.
   * Graphics2D)
   */
  @Override
  public void paintUpButton(Graphics2D g2) {
    Rectangle r = new Rectangle(getInsets().left, getInsets().top, mIternalFixedDim, mIternalFixedDim);

    if (mHighlightButton1) {
      paintScrollBarButtonHighlighted(g2, r);
    } else {
      paintScrollBarButton(g2, r);
    }

    int offset = (getInternalFixedDimension() - LEFT_ARROW_ICON.getWidth()) / 2;

    LEFT_ARROW_ICON.drawIcon(g2, getInsets().left + offset, getInsets().top + offset, mIternalFixedDim);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.scrollpane.ModernHScrollBar#paintDownButton(java.awt.
   * Graphics2D)
   */
  @Override
  public void paintDownButton(Graphics2D g2) {
    Rectangle r = new Rectangle(getWidth() - mIternalFixedDim - getInsets().right, getInsets().top, mIternalFixedDim,
        mIternalFixedDim);

    if (mHighlightButton2) {
      paintScrollBarButtonHighlighted(g2, r);
    } else {
      paintScrollBarButton(g2, r);
    }

    int offset = (mIternalFixedDim - RIGHT_ARROW_ICON.getWidth()) / 2;

    RIGHT_ARROW_ICON.drawIcon(g2, getWidth() - mIternalFixedDim - getInsets().right + offset, getInsets().top + offset,
        mIternalFixedDim);
  }

}
