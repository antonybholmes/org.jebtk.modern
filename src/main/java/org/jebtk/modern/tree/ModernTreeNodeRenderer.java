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

import org.jebtk.core.tree.TreeNode;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.graphics.ImageUtils;
import org.jebtk.modern.theme.DrawUIService;

// TODO: Auto-generated Javadoc
/**
 * Provides a rudimentary implementation of a node renderer that detects if is
 * selected and what the tree branching depth is. This forms the basis of
 * concrete implementations of renderers.
 *
 * @author Antony Holmes
 */
public abstract class ModernTreeNodeRenderer extends ModernWidget {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The Constant X_DEPTH_OFFSET. */
  public static final int X_DEPTH_OFFSET = 8;

  /** How much each node is offset as the depth increases. */
  protected int mXDepthOffset = X_DEPTH_OFFSET;

  /**
   * The member depth.
   */
  protected int mDepth = -1;

  /**
   * The member node is highlighted.
   */
  protected boolean mNodeIsHighlighted = false;

  /**
   * The member node is selected.
   */
  protected boolean mNodeIsSelected = false;

  /**
   * The member node.
   */
  protected TreeNode<?> mNode;

  /**
   * The member branch height.
   */
  protected int mBranchHeight = WIDGET_HEIGHT;

  /**
   * The member row.
   */
  protected int mRow;

  /**
   * The member has focus.
   */
  protected boolean mHasFocus = false;

  /**
   * The member is drag to node.
   */
  protected boolean mIsDragToNode = false;

  /**
   * The width of the region the user can click in to cause the tree to collapse
   * or expand.
   */
  protected int mCollapseRegionWidth = mBranchHeight;
  


  // private GenericButtonAnimation mRenderer;

  public ModernTreeNodeRenderer() {
    // mRenderer = new GenericButtonAnimation(this);
  }

  /**
   * Returns the height that the branch should be rendered at.
   *
   * @return the branch height
   */
  public int getBranchHeight() {
    return mBranchHeight;
  }

  /**
   * Set the height the node will be rendered at.
   *
   * @param branchHeight the new branch height
   */
  public void setBranchHeight(int branchHeight) {
    mBranchHeight = branchHeight;
  }

  /**
   * Returns the x offset for where a node should begin drawing, if the intend is
   * to display the tree as a nested graph.
   *
   * @return The cumulative x depth offset.
   */
  public int getCumulativeXDepthOffset() {
    return getDepth() * getXDepthOffset();
  }

  /**
   * Returns the amount to offset the node by for each increase in depth. Child
   * nodes have a depth greater than their parent so they can appear offset (or
   * within) the parent.
   *
   * @return The x depth offset.
   */
  public int getXDepthOffset() {
    return mXDepthOffset;
  }

  /**
   * Sets the x depth offset.
   *
   * @param xDepthOffset the new x depth offset
   */
  public void setXDepthOffset(int xDepthOffset) {
    mXDepthOffset = xDepthOffset;
  }

  /**
   * Returns the width of the region that determines whether to collapse a node or
   * not. This is so collapsable nodes can be selected without collapsing.
   *
   * @return The collapse region width
   */
  public int getCollapseRegionWidth() {
    return mCollapseRegionWidth;
  }

  /**
   * Sets the collapse region width.
   *
   * @param collapseRegionWidth the new collapse region width
   */
  public void setCollapseRegionWidth(int collapseRegionWidth) {
    mCollapseRegionWidth = collapseRegionWidth;
  }

  /**
   * Gets the depth.
   *
   * @return the depth
   */
  public int getDepth() {
    return mDepth;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawBackground(java.awt.Graphics2D)
   */
  @Override
  public void drawBackgroundAA(Graphics2D g2) {

    if (mNodeIsSelected) {
      // getWidgetRenderer().drawButton(g2, mRect, RenderMode.SELECTED);

      // g2.setColor(ModernWidgetRenderer.RIBBON_SELECTED_OUTLINE_COLOR);

      // getWidgetRenderer().drawRibbonButton(g2, mRect, RenderMode.SELECTED);
      
      DrawUIService.getInstance().getRenderer("button-fill").draw(g2, mRect, SELECTED_COLOR);

    } else if (mNodeIsHighlighted) {
      /*
       * mRenderer.drawButton(g2, mRect.getX(), mRect.getY(), mRect.getW(),
       * mRect.getH(), RenderMode.SELECTED, false);
       */

      DrawUIService.getInstance().getRenderer("button-fill").draw(g2, mRect, HIGHLIGHT_COLOR);

      // getWidgetRenderer().drawButton(g2, mRect, RenderMode.SELECTED);
    } else if (mIsDragToNode) {
      DrawUIService.getInstance().getRenderer("button-outline").draw(g2, mRect);
    } else {
      // Do nothing
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    Graphics2D g2Temp = ImageUtils.clone(g2);

    try {
      g2Temp.translate(getCumulativeXDepthOffset(), 0);

      drawNode(g2Temp);
    } finally {
      g2Temp.dispose();
    }
  }

  /**
   * Draw node.
   *
   * @param g2 the g 2
   */
  public void drawNode(Graphics2D g2) {
    // Do nothing
  }

  /**
   * Obtains a renderer for the tree node.
   *
   * @param tree              the tree control.
   * @param node              the node.
   * @param nodeIsHighlighted whether to highlight the node.
   * @param nodeIsSelected    whether the node is selected or not.
   * @param hasFocus          the has focus
   * @param isDragToNode      the is drag to node
   * @param depth             the nested depth of the node.
   * @param row               the row
   * @return the renderer
   * @Param hasFocus whether the tree has focus or not, as this can determine
   *        color.
   */
  @SuppressWarnings("unchecked")
  public ModernTreeNodeRenderer getRenderer(Tree<?> tree, TreeNode<?> node, boolean nodeIsHighlighted,
      boolean nodeIsSelected, boolean hasFocus, boolean isDragToNode, int depth, int row) {
    mNodeIsHighlighted = nodeIsHighlighted;
    mNodeIsSelected = nodeIsSelected;
    mHasFocus = hasFocus;
    mIsDragToNode = isDragToNode;
    mNode = node;
    mDepth = depth;
    mRow = row;

    setSize(tree, node, depth, row);

    // mRenderer.setStep(a.getCurrentStep());

    return this;
  }

  /**
   * Set the size of the node based on the parent tree dimensions. The size of the
   * renderer is used by the tree itself to determine its size based on the nodes
   * that are visible.
   *
   * @param tree  the tree
   * @param node  the node
   * @param depth the depth
   * @param row   the row
   */
  protected void setSize(Tree<?> tree, TreeNode<?> node, int depth, int row) {
    setSize(tree.getInternalRect().getW(), mBranchHeight);
  }
}