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

import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.core.tree.TreeNode;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.HighlightAnimation;
import org.jebtk.modern.graphics.ImageUtils;

/**
 * Allows for fade in/out animation on an element.
 *
 * @author Antony Holmes
 */
public class TreeHighlightAnimation extends HighlightAnimation {

  private final ModernTree<?> mTree;

  /**
   * Instantiates a new state animation.
   * @param widget
   */
  public TreeHighlightAnimation(ModernWidget widget) {
    super((ModernTree<?>) widget);

    mTree = (ModernTree<?>) widget;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.animation.Animation#draw(org.abh.common.ui.widget.
   * ModernWidget, java.awt.Graphics2D, java.lang.Object[])
   */
  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {

    if (mTree.mSelectionModel == null || mTree.mNodeIndexMap == null || mTree.mNodeDepthMap == null
        || mTree.mNodeRenderer == null) {
      return;
    }

    ModernTreeNodeRenderer renderer;

    Graphics2D g2Temp = ImageUtils.clone(g2);

    // account for insets etc
    // g2Temp.translate(mInternalRect.getX(), mInternalRect.getY());

    int y = 0;
    int h = 0;

    int y1 = mTree.getVisibleRect().y;
    int maxY = y1 + mTree.getVisibleRect().height; // mTree.getInternalRect().getH();

    int pc = 0;

    try {
      for (TreeNode<?> node : mTree.mFlatNodeList) {
        // Speed up so we don't plot more nodes than can
        // be seen on screen
        if (y > maxY) {
          break;
        }

        boolean isDragToNode = mTree.mDragTo != null && mTree.mDragTo.index == pc && !mTree.mDragTo.insertBetween;

        renderer = mTree.mNodeRenderer.getRenderer(mTree, node, node.equals(mTree.mHighlightNode) || isDragToNode,
            mTree.mSelectionModel.contains(mTree.mNodeIndexMap.get(node)), mTree.isFocusOwner(), isDragToNode,
            mTree.mNodeDepthMap.get(node), pc);

        h = renderer.getHeight();

        // Skip nodes until we encounter one in the
        // view space
        if (y >= y1 || y + h >= y1) {
          renderer.print(g2Temp);
        }

        g2Temp.translate(0, h);

        y += h;

        ++pc;
      }
    } finally {
      g2Temp.dispose();
    }
  }

}
