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
package org.jebtk.modern.tree;

import java.awt.Graphics2D;

import org.jebtk.modern.graphics.ImageUtils;
import org.jebtk.modern.graphics.icons.ModernIcon;

// TODO: Auto-generated Javadoc
/**
 * Provides a rudimentary implementation of a node renderer that detects if is
 * selected and what the tree branching depth is. This forms the basis of
 * concrete implementations of renderers.
 *
 * @author Antony Holmes
 * @param <T> the generic type
 */
public abstract class ModernTreeIconTextNodeRenderer extends ModernTreeBranchNodeRenderer {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The m icon. */
  protected ModernIcon mIcon = null;

  /**
   * The width of the space allocated to render the icon.
   */
  protected int mIconWidth = mBranchHeight;

  /**
   * Gets the icon width.
   *
   * @return the icon width
   */
  public int getIconWidth() {
    return mIconWidth;
  }

  /**
   * Sets the icon width.
   *
   * @param iconWidth the new icon width
   */
  public void setIconWidth(int iconWidth) {
    mIconWidth = iconWidth;
  }

  /**
   * Sets the icon.
   *
   * @param icon the new icon
   */
  public void setIcon(ModernIcon icon) {
    mIcon = icon;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.tree.ModernTreeBranchNodeRenderer#drawNodeIconText(java.
   * awt .Graphics2D)
   */
  @Override
  public void drawNodeIconText(Graphics2D g2) {
    drawNodeIcon(g2);

    Graphics2D g2Temp = ImageUtils.clone(g2);

    try {
      g2Temp.translate(mIconWidth, 0);

      drawNodeText(g2Temp);
    } finally {
      g2Temp.dispose();
    }
  }

  /**
   * Should render any text that appears in the node. The graphics context is
   * already adjusted to be at the correct x.
   *
   * @param g2 the g 2
   */
  public void drawNodeText(Graphics2D g2) {
    // Do nothing
  }

  /**
   * Draws the icon for the node. The graphics context is offset so that x = 0 is
   * the correctly offset start for the block the icon should be rendered in.
   * Therefore the only adjustments that may be required are centering the icon
   * about (mIconWidth, getHeight()) which represents the space allocated to draw
   * the icon.
   *
   * @param g2 the g 2
   */
  public void drawNodeIcon(Graphics2D g2) {
    if (mIcon != null) {
      int x = (mIconWidth - mIcon.getWidth()) / 2;
      int y = (getHeight() - mIcon.getHeight()) / 2;

      mIcon.drawIcon(g2, x, y, 16);
    }
  }
}