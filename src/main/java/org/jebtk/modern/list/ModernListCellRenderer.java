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
package org.jebtk.modern.list;

import java.awt.Component;
import java.awt.Graphics2D;

import org.jebtk.modern.button.ModernClickWidget;

// TODO: Auto-generated Javadoc
/**
 * Interface for list renderers. These assume the same data type within each row
 *
 * @author Antony Holmes
 * @param <T> the generic type
 */
public class ModernListCellRenderer extends ModernClickWidget {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member is highlighted.
   */
  protected boolean mIsHighlighted = false;

  /**
   * The member is selected.
   */
  protected boolean mIsSelected = false;

  /**
   * The member has focus.
   */
  protected boolean mHasFocus = false;

  /**
   * The member can highlight.
   */
  protected boolean mCanHighlight = true;

  // private Color mFillColor;

  /**
   * Sets the can highlight.
   *
   * @param canHighlight the new can highlight
   */
  public void setCanHighlight(boolean canHighlight) {
    mCanHighlight = canHighlight;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawBackground(java.awt.Graphics2D)
   */
  @Override
  public void drawBackgroundAA(Graphics2D g2) {
    /*
     * if (mCanHighlight) { if (mIsSelected) { fill(g2,
     * ModernWidgetRenderer.SELECTED_FILL_COLOR); } else if (mIsHighlighted) {
     * fill(g2, mFillColor);
     * 
     * //getWidgetRenderer().drawButton(g2, getRect(), RenderMode.SELECTED); } else
     * { // Do nothing } }
     */
  }

  /**
   * Specialized instance of the cell renderer method for lists.
   *
   * @param list       the list
   * @param value      the value
   * @param highlight  the highlight
   * @param isSelected the is selected
   * @param hasFocus   the has focus
   * @param row        the row
   * @return the cell renderer component
   */
  public Component getCellRendererComponent(ModernList<?> list, Object value, boolean highlight, boolean isSelected,
      boolean hasFocus, int row) {

    mIsHighlighted = highlight;
    mIsSelected = isSelected;
    mHasFocus = hasFocus;

    // ListHighlightAnimation a =
    // (ListHighlightAnimation)list.getBackgroundAnimation();

    // Use the current fill color for animation
    // mFillColor = a.getFade().getFadeColor("fill");

    return this;
  }
}