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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.List;

import org.jebtk.core.path.Path;
import org.jebtk.core.tree.TreeNode;
import org.jebtk.core.tree.TreeRootNode;

/**
 * Specialized tree that renderer paths as a hieraracy.
 * 
 * @author Antony Holmes
 *
 */
public class PathTree extends ModernTree<List<Path>> {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Construct a new path tree.
   * 
   * @param paths      The paths to construct the tree.
   * @param showLeaves If set to true, will show leaf nodes. In a settings dialog
   *                   etc, it may be desirable to not show the leafs for
   *                   aesthetic reasons.
   */
  public PathTree(Collection<Path> paths, boolean showLeaves) {
    ModernTreeNodeFolderRenderer renderer = new ModernTreeNodeFolderRenderer();

    setNodeRenderer(renderer);

    TreeRootNode<List<Path>> root = new TreeRootNode<List<Path>>();

    for (Path path : paths) {
      // List<String> parts = TextUtils.periodSplit(path);

      TreeNode<List<Path>> node = root;

      for (String part : path) {
        TreeNode<List<Path>> newNode = node.getChild(part);

        if (newNode == null) {
          newNode = new TreeNode<List<Path>>(part, new ArrayList<Path>());
          node.addChild(newNode);
        }

        node = newNode;

        node.getValue().add(path);
      }
    }

    // Remove leaves
    Deque<TreeNode<List<Path>>> nodeStack = new ArrayDeque<TreeNode<List<Path>>>();

    nodeStack.push(root);

    while (!nodeStack.isEmpty()) {
      TreeNode<List<Path>> node = nodeStack.pop();

      List<TreeNode<List<Path>>> toRemove = new ArrayList<TreeNode<List<Path>>>();

      for (TreeNode<List<Path>> child : node) {
        if (child.isParent() || showLeaves) {
          nodeStack.push(child);
        } else {
          toRemove.add(child);
        }
      }

      for (TreeNode<List<Path>> child : toRemove) {
        node.removeChild(child);
      }
    }

    // Once the tree is built, sort it
    nodeStack = new ArrayDeque<TreeNode<List<Path>>>();

    nodeStack.push(root);

    while (!nodeStack.isEmpty()) {
      TreeNode<List<Path>> node = nodeStack.pop();

      node.sortChildren();

      for (TreeNode<List<Path>> child : node) {
        nodeStack.push(child);
      }
    }

    setRoot(root);
  }
}