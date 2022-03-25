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
import org.jebtk.modern.graphics.icons.RasterIcon;

// TODO: Auto-generated Javadoc
/**
 * Provides a rudimentary implementation of a node renderer that detects if is
 * selected and what the tree branching depth is. This forms the basis of
 * concrete implementations of renderers.
 *
 * @author Antony Holmes
 * @param <T> the generic type
 */
public abstract class ModernTreeBranchNodeRenderer extends ModernTreeNodeRenderer {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The Constant BRANCH_OPEN_ICON. */
  public static final RasterIcon BRANCH_OPEN_ICON = new RasterIcon(new ModernBranchOpenVectorIcon(), 12); // new
  // TriangleDownVectorIcon());
  // //CheveronDownVectorIcon());

  /** The Constant BRANCH_CLOSED_ICON. */
  public static final RasterIcon BRANCH_CLOSED_ICON = new RasterIcon(new ModernBranchClosedVectorIcon(), 12); // new
  // TriangleRightVectorIcon());

  /** The m branch closed icon. */
  public static ModernIcon mBranchClosedIcon = BRANCH_CLOSED_ICON; // new
  // ModernBranchClosedVectorIcon());

  /** The m branch open icon. */
  public static ModernIcon mBranchOpenIcon = BRANCH_OPEN_ICON; // new
  // ModernBranchOpenVectorIcon());

  /** The m branch width. */
  protected int mBranchWidth = 16;

  /**
   * Gets the branch width.
   *
   * @return the branch width
   */
  public int getBranchWidth() {
    return mBranchWidth;
  }

  /**
   * Set the height the node will be rendered at.
   *
   * @param branchWidth the new branch width
   */
  public void setBranchWidth(int branchWidth) {
    mBranchWidth = branchWidth;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawNode(Graphics2D g2) {
    drawNodeBranch(g2);

    Graphics2D g2Temp = ImageUtils.clone(g2);

    try {
      g2Temp.translate(mBranchWidth, 0);

      drawNodeIconText(g2Temp);
    } finally {
      g2Temp.dispose();
    }
  }

  /**
   * Draws the rest of the node excluding the branch icon. It is assumed the
   * branch icon will rename the same even if the node look changes.
   *
   * @param g2 the g 2
   */
  public void drawNodeIconText(Graphics2D g2) {
    // Do nothing
  }

  /**
   * Draws the branch icon for the node.
   *
   * @param g2 the g 2
   */
  public void drawNodeBranch(Graphics2D g2) {
    int x = (mBranchWidth - mBranchOpenIcon.getWidth()) / 2;

    int y = (getHeight() - mBranchOpenIcon.getHeight()) / 2;

    drawNodeBranch(g2, x, y);
  }

  /**
   * Draw the branch at a particular location.
   *
   * @param g2 the g 2
   * @param x  the x
   * @param y  the y
   */
  public void drawNodeBranch(Graphics2D g2, int x, int y) {
    if (mNode.isParent()) {
      if (mNode.isExpanded()) {
        mBranchOpenIcon.drawIcon(g2, x, y, 16);
      } else {
        mBranchClosedIcon.drawIcon(g2, x, y, 16);
      }
    }
  }
}