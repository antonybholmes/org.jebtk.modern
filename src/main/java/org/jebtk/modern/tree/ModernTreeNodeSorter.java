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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jebtk.core.tree.TreeNode;

/**
 * The experiment tree can be sorted in multiple ways. Given a list of
 * experiments and a tree, generate a custom sorted tree of experiments.
 *
 * @author Antony Holmes
 * @param <T> the generic type
 */
public abstract class ModernTreeNodeSorter<T> implements Comparable<ModernTreeNodeSorter<T>> {

  /**
   * The member name.
   */
  private String mName;

  /**
   * Instantiates a new modern tree node sorter.
   *
   * @param name the name
   */
  public ModernTreeNodeSorter(String name) {
    mName = name;
  }

  /**
   * Gets the name.
   *
   * @return the name
   */
  public String getName() {
    return mName;
  }

  /**
   * Should take a list of experiments, organise them and then populate a tree.
   *
   * @param nodes     the nodes
   * @param ascending the ascending
   * @return the list
   */
  public abstract List<TreeNode<T>> sort(List<TreeNode<T>> nodes, boolean ascending);

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  public int compareTo(ModernTreeNodeSorter<T> s) {
    // TODO Auto-generated method stub
    return mName.compareTo(s.getName());
  }

  /**
   * Sort nodes by name.
   *
   * @param <T>       the generic type
   * @param nodes     the nodes
   * @param ascending the ascending
   * @return the list
   */
  public static <T> List<TreeNode<T>> sortNodesByName(List<TreeNode<T>> nodes, boolean ascending) {

    List<String> names = new ArrayList<String>();

    Map<String, TreeNode<T>> nodeMap = new HashMap<String, TreeNode<T>>();

    for (TreeNode<T> node : nodes) {
      String name = node.getName();

      names.add(name);
      nodeMap.put(name, node);
    }

    Collections.sort(names);

    if (!ascending) {
      Collections.reverse(names);
    }

    List<TreeNode<T>> sortedNodes = new ArrayList<TreeNode<T>>();

    for (String name : names) {
      sortedNodes.add(nodeMap.get(name));
    }

    return sortedNodes;
  }
}
