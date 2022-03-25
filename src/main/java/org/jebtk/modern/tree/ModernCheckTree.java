/**
 * Copyright 2017 Antony Holmes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jebtk.modern.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import org.jebtk.core.tree.CheckTreeNode;
import org.jebtk.core.tree.TreeNode;

/**
 * Tree for checkable nodes that will respond to mouse events and toggle whether
 * nodes are checked or not.
 *
 * @author Antony Holmes
 * @param <T> the generic type
 */
public class ModernCheckTree<T> extends ModernTree<T> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The m mode. */
  private ModernCheckTreeMode mMode = ModernCheckTreeMode.MULTI;

  /**
   * Instantiates a new modern check tree.
   */
  public ModernCheckTree() {
    this(ModernCheckTreeMode.MULTI);
  }

  /**
   * Instantiates a new modern check tree.
   *
   * @param mode the mode
   */
  public ModernCheckTree(ModernCheckTreeMode mode) {
    setMultiSelect(mode);

    setNodeRenderer(new ModernTreeCheckNodeRenderer());

    addTreeListener(new TreeEventAdapter() {

      @Override
      public void treeNodeClicked(ModernTreeEvent e) {
        if (e.getMessage().equals(NODE_CLICKED)) {
          // System.err.println("checked tree " + mMode);

          if (mMode == ModernCheckTreeMode.SINGLE || mMode == ModernCheckTreeMode.RADIO) {
            // cancel all selections
            setChecked(false);
          }

          TreeNode<T> n = getSelectedNode();

          if (n instanceof CheckTreeNode) {
            CheckTreeNode<T> node = (CheckTreeNode<T>) n;

            switch (mMode) {
            case RADIO:
              node.setChecked(true);
              break;
            case MIN_ONE:
              if (getCheckedNodes().size() > 1) {
                node.setChecked(!node.getIsChecked());
              } else {
                node.setChecked(true);
              }

              break;
            default:
              node.setChecked(!node.getIsChecked());
              break;
            }
          }
        }

      }
    });
  }

  /**
   * Sets the multi select.
   *
   * @param multiSelect the new multi select
   */
  public void setMultiSelect(ModernCheckTreeMode multiSelect) {
    mMode = multiSelect;

    setChecked(false);
  }

  /**
   * Return first checked node in the tree, or null if there are none.
   *
   * @return the checked node
   */
  public CheckTreeNode<T> getCheckedNode() {
    List<CheckTreeNode<T>> nodes = getCheckedNodes();

    if (nodes.size() > 0) {
      return nodes.get(0);
    } else {
      return null;
    }
  }

  /**
   * Return only the nodes that are checked.
   *
   * @return the checked nodes
   */
  public List<CheckTreeNode<T>> getCheckedNodes() {
    List<CheckTreeNode<T>> nodes = getCheckNodes();

    List<CheckTreeNode<T>> ret = new ArrayList<CheckTreeNode<T>>(nodes.size());

    for (CheckTreeNode<T> node : nodes) {
      if (node.getIsChecked()) {
        ret.add(node);
      }
    }

    return ret;
  }

  /**
   * Returns a list of nodes in the tree that are check nodes. Nodes are in order
   * of how they are found in the tree exploring depth first.
   *
   * @return the check nodes
   */
  public List<CheckTreeNode<T>> getCheckNodes() {
    List<TreeNode<T>> nodes = getFlattenedTree();

    List<CheckTreeNode<T>> ret = new ArrayList<CheckTreeNode<T>>(nodes.size());

    for (TreeNode<T> node : nodes) {
      if (node instanceof CheckTreeNode) {
        ret.add((CheckTreeNode<T>) node);
      }
    }

    return ret;
  }

  /**
   * Set the status of all checked nodes in the tree.
   *
   * @param checked the new checked
   */
  public void setChecked(boolean checked) {
    Deque<TreeNode<T>> stack = new ArrayDeque<TreeNode<T>>();

    stack.push(getRoot());

    while (!stack.isEmpty()) {
      TreeNode<T> node = stack.pop();

      if (node instanceof CheckTreeNode) {
        ((CheckTreeNode<T>) node).setChecked(checked);
      }

      for (TreeNode<T> child : node) {
        stack.push(child);
      }
    }

    fireCanvasChanged();
  }
}
