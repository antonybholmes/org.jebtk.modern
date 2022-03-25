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

import org.jebtk.core.geom.IntRect;
import org.jebtk.core.tree.CheckTreeNode;
import org.jebtk.core.tree.TreeNode;
import org.jebtk.modern.theme.DrawUIService;

// TODO: Auto-generated Javadoc
/**
 * Renders the icon as a checkbox.
 *
 * @author Antony Holmes
 * @param <T> the generic type
 */
public class ModernTreeCheckNodeRenderer extends TreeNodeFileRenderer {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The m checked. */
  private boolean mChecked = false;

  /** The m is check node. */
  private boolean mIsCheckNode;

  /**
   * Sets the checked.
   *
   * @param checked the new checked
   */
  public void setChecked(boolean checked) {
    mChecked = checked;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.tree.ModernTreeNodeRenderer#drawForegroundAA(java.
   * awt. Graphics2D)
   */
  @Override
  public void drawNodeIcon(Graphics2D g2) {
    if (mIsCheckNode) {

      int x = (mIconWidth - 16) / 2;
      int y = (getHeight() - 16) / 2;

      if (mChecked) {
        // getWidgetRenderer().drawChecked(g2, x, y, 16, 16,
        // RenderMode.SELECTED);
        DrawUIService.getInstance().getRenderer("checkbox.checked").draw(g2, new IntRect(x, y, 16));
      } else {
        // getWidgetRenderer().drawCheckBox(g2, x, y, 16, 16);
        DrawUIService.getInstance().getRenderer("checkbox").draw(g2, new IntRect(x, y, 16));
      }
    } else {
      super.drawNodeIcon(g2);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.tree.TreeIconNodeRenderer#getRenderer(org.abh.common.ui.
   * tree.Tree, org.abh.common.tree.TreeNode, boolean, boolean, boolean, boolean,
   * int, int)
   */
  @Override
  public ModernTreeNodeRenderer getRenderer(Tree<?> tree, TreeNode<?> node, boolean nodeIsHighlighted,
      boolean nodeIsSelected, boolean hasFocus, boolean isDragToNode, int depth, int row) {
    super.getRenderer(tree, node, nodeIsHighlighted, nodeIsSelected, hasFocus, isDragToNode, depth, row);

    if (node instanceof CheckTreeNode) {
      mIsCheckNode = true;
      mChecked = ((CheckTreeNode<?>) node).getIsChecked();
    } else {
      mIsCheckNode = false;
      mChecked = false;
    }

    return this;
  }
}