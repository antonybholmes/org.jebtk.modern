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
package org.jebtk.modern.collapsepane;

import java.awt.Graphics2D;
import java.awt.Point;

import org.jebtk.modern.AssetService;
import org.jebtk.modern.graphics.icons.CheckedVectorIcon;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.graphics.icons.UnCheckedVectorIcon;
import org.jebtk.modern.tree.ModernTreeBranchNodeRenderer;

// TODO: Auto-generated Javadoc
/**
 * Provides a rudimentary implementation of a node renderer that detects if is
 * selected and what the tree branching depth is. This forms the basis of
 * concrete implementations of renderers.
 *
 * @author Antony Holmes
 */
public class ModernCollapseEnableNodeRenderer extends ModernCollapseNodeRenderer {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The checked icon. */
  private static final ModernIcon CHECKED_ICON = AssetService.getInstance().loadIcon(CheckedVectorIcon.class, 16);

  /** The unchecked icon. */
  private static final ModernIcon UNCHECKED_ICON = AssetService.getInstance().loadIcon(UnCheckedVectorIcon.class, 16);

  /**
   * The is enabled.
   */
  private boolean mIsEnabled;

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.collapsepane.ModernCollapseNodeRenderer#
   * drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawBackgroundAA(Graphics2D g2) {
    int x = 0;

    int y = (this.getHeight() - 16) / 2;

    if (mIsExpanded) {
      ModernTreeBranchNodeRenderer.BRANCH_OPEN_ICON.drawIcon(g2, x, y, 16);
    } else {
      ModernTreeBranchNodeRenderer.BRANCH_CLOSED_ICON.drawIcon(g2, x, y, 16);
    }

    x += ModernTreeBranchNodeRenderer.BRANCH_OPEN_ICON.getWidth(); // +
    // ModernTheme.getInstance().getClass("widget").getInt("padding");

    if (mIsEnabled) {
      CHECKED_ICON.drawIcon(g2, x, y, 16);
    } else {
      UNCHECKED_ICON.drawIcon(g2, x, y, 16);
    }

    x += CHECKED_ICON.getWidth() + PADDING;

    Point p = getStringCenterPlotCoordinates(g2, getRect(), mName);

    g2.setColor(getForeground());

    g2.setFont(SUB_SUB_HEADING_FONT);

    g2.drawString(getTruncatedText(g2, mName, x, mRect.getW()), x, p.y);
  }

  /**
   * Obtains a renderer for the tree node.
   *
   * @param pane
   * @param name          the name
   * @param isHighlighted whether to highlight the node.
   * @param isSelected    whether the node is selected or not.
   * @param hasFocus      the has focus
   * @param isExpanded    the is expanded
   * @param isEnabled     the is enabled
   * @return the renderer
   * @Param hasFocus whether the tree has focus or not, as this can determine
   *        color.
   */
  public ModernCollapseEnableNodeRenderer getRenderer(AbstractCollapsePane pane, String name, int index,
      boolean isHighlighted, boolean isSelected, boolean hasFocus, boolean isExpanded, boolean isEnabled) {
    super.getRenderer(pane, name, index, isHighlighted, isSelected, hasFocus, isExpanded);

    mIsEnabled = isEnabled;

    return this;
  }
}