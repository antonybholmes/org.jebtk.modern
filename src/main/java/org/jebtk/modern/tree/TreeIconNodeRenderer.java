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
import org.jebtk.modern.graphics.icons.ModernIcon;

// TODO: Auto-generated Javadoc
/**
 * Provides a basic renderer that displays the node name, a collapse icon for
 * parent nodes and an icon depending on whether the node is a parent or a leaf.
 *
 * @author Antony Holmes
 * @param <T> the generic type
 */
public class TreeIconNodeRenderer extends ModernTreeIconTextNodeRenderer {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member leaf icon.
   */
  protected ModernIcon mLeafIcon = null;

  /**
   * The member parent icon.
   */
  protected ModernIcon mParentIcon = null;

  /** The m is parent. */
  protected boolean mIsParent = false;

  /** The m text. */
  private String mText;

  /**
   * The collapse region width.
   *
   * @param parentIcon the parent icon
   * @param leafIcon   the leaf icon
   */
  // protected int mCollapseRegionWidth = WIDGET_HEIGHT;

  /**
   * Instantiates a new tree icon node renderer.
   *
   * @param parentIcon the parent icon
   * @param leafIcon   the leaf icon
   */
  public TreeIconNodeRenderer(ModernIcon parentIcon, ModernIcon leafIcon) {
    mParentIcon = parentIcon;
    mLeafIcon = leafIcon;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.tree.ModernTreeNodeRenderer#drawForegroundAA(java.
   * awt. Graphics2D)
   */
  @Override
  public void drawNodeText(Graphics2D g2) {

    g2.setColor(getForeground());

    // if (mNodeIsSelected) {
    // g2.setFont(BOLD_FONT);
    // } else {
    // g2.setFont(FONT);
    // }

    int y = getTextYPosCenter(g2, getHeight());

    g2.drawString(getTruncatedText(g2, mText, 0, mRect.getW()), 0, y);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.tree.ModernTreeNodeRenderer#getRenderer(org.abh.lib.
   * ui. modern.tree.Tree, org.abh.lib.tree.TreeNode, boolean, boolean, boolean,
   * boolean, int, int)
   */
  @Override
  public ModernTreeNodeRenderer getRenderer(Tree<?> tree, TreeNode<?> node, boolean nodeIsHighlighted,
      boolean nodeIsSelected, boolean hasFocus, boolean isDragToNode, int depth, int row) {
    super.getRenderer(tree, node, nodeIsHighlighted, nodeIsSelected, hasFocus, isDragToNode, depth, row);

    mText = mNode.getName();

    if (node.isParent()) {
      setIcon(mParentIcon);
    } else {
      setIcon(mLeafIcon);
    }

    mIsParent = node.isParent();

    return this;
  }
}