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
package org.jebtk.modern.list;

import java.awt.Component;
import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.WidgetAnimation;
import org.jebtk.modern.dataview.ModernDataRowSelection;

/**
 * Allows for fade in/out animation on an element.
 *
 * @author Antony Holmes
 * @param <T>
 */
public class ListAnimation extends WidgetAnimation {

  private ModernList<?> mList;

  /**
   * Instantiates a new state animation.
   *
   * @param ribbon the ribbon
   */
  public ListAnimation(ModernWidget list) {
    super(list);

    mList = (ModernList<?>) list;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.animation.Animation#draw(org.abh.common.ui.widget.
   * ModernWidget, java.awt.Graphics2D, java.lang.Object[])
   */
  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {

    if (mList.mListModel == null) {
      return;
    }

    if (mList.mListModel.getItemCount() == 0) {
      return;
    }

    createImage(g2);
  }

  protected void createImage(Graphics2D g2) {
    Graphics2D g2Table = (Graphics2D) g2.create();

    double maxY = mList.getViewRect().getY() + mList.getInternalRect().getH();

    int y = mList.getInsets().top; // mRowHeight * mVisibleCells.getStartRow();

    try {
      for (int i = 0; i < mList.getItemCount(); ++i) {
        if (y > maxY) {
          break;
        }

        if (y + mList.mRowHeight >= mList.getViewRect().getY()) {
          boolean highlighted = i == mList.mHighlightCellIndex;

          boolean selected = mList.mSelectionModel.contains(i);

          Component c = mList.mRenderer.getCellRendererComponent(mList, mList.getValueAt(i), highlighted, selected,
              true, i);

          c.setSize(mList.getWidth(), mList.mRowHeight);

          c.print(g2Table);
        }

        // Move to the next cell location.
        g2Table.translate(0, mList.mRowHeight);

        y += mList.mRowHeight;
      }
    } finally {
      g2Table.dispose();
    }

    createDragImage(g2, mList.mVisibleCells);
  }

  protected void createDragImage(Graphics2D g2, ModernDataRowSelection visibleCells) {
    if (!mList.mDragEnabled || mList.mDragCellIndex == -1) {
      return;
    }

    g2.setColor(ModernList.DRAG_LINE_COLOR);

    int y = mList.mRowHeight * (Math.min(mList.mDragCellIndex, mList.mListModel.getItemCount()));

    g2.drawLine(0, y, mList.getWidth(), y);
  }
}
