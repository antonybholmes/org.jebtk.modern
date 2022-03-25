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
package org.jebtk.modern.preview;

import java.awt.BorderLayout;

import org.jebtk.core.tree.TreeNode;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.scrollpane.ModernScrollPane;
import org.jebtk.modern.scrollpane.ScrollBarPolicy;
import org.jebtk.modern.tree.ModernTree;

// TODO: Auto-generated Javadoc
/**
 * The class PreviewTreeSidePanel.
 */
public class PreviewTreeSidePanel extends ModernWidget {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The tree.
   */
  private ModernTree<ModernWidget> tree;

  /**
   * Instantiates a new preview tree side panel.
   */
  public PreviewTreeSidePanel() {

    super(new BorderLayout());

    tree = new ModernTree<ModernWidget>();

    tree.setNodeRenderer(new PreviewModernTreeNodeRenderer());

    ModernScrollPane scrollPane = new ModernScrollPane(tree);

    scrollPane.setHorizontalScrollBarPolicy(ScrollBarPolicy.NEVER);

    add(scrollPane, BorderLayout.CENTER);
  }

  /**
   * Sets the root.
   *
   * @param previewPanel the new root
   */
  public final void setRoot(ModernWidget previewPanel) {

    // create the tree with the restriction site groups. These
    // no not change.

    tree.clear();

    tree.getRoot().addChild(new TreeNode<ModernWidget>(previewPanel.getName(), previewPanel));
  }

  /**
   * Adds the.
   *
   * @param name         the name
   * @param previewPanel the preview panel
   */
  public final void add(String name, ModernWidget previewPanel) {
    tree.getRoot().getChild(0).addChild(new TreeNode<ModernWidget>(name, previewPanel));
  }

  /**
   * Gets the tree.
   *
   * @return the tree
   */
  public ModernTree<ModernWidget> getTree() {
    return tree;
  }
}
