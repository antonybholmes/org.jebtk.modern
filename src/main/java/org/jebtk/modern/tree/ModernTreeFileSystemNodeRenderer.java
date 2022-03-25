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

import java.nio.file.Path;

import org.jebtk.core.io.FileUtils;
import org.jebtk.core.tree.TreeNode;

// TODO: Auto-generated Javadoc
/**
 * Basic renderer for displaying directories and files.
 *
 * @author Antony Holmes
 *
 */
public class ModernTreeFileSystemNodeRenderer extends TreeNodeFileCountRenderer {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.tree.TreeIconNodeCountRenderer#getRenderer(org.abh.
   * lib. ui.modern.tree.Tree, org.abh.lib.tree.TreeNode, boolean, boolean,
   * boolean, boolean, int, int)
   */
  @Override
  public ModernTreeNodeRenderer getRenderer(Tree<?> tree, TreeNode<?> node, boolean nodeIsHighlighted,
      boolean nodeIsSelected, boolean hasFocus, boolean isDragToNode, int depth, int row) {
    super.getRenderer(tree, node, nodeIsHighlighted, nodeIsSelected, hasFocus, isDragToNode, depth, row);

    if (node.isParent() || FileUtils.isDirectory((Path) node.getValue())) {
      // we renderer as a parent if the node is classified as a parent {
      // or it is an empty directory.
      mIcon = mParentIcon;
    } else {
      mIcon = mLeafIcon;
    }

    return this;
  }
}