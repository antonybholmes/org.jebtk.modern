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
package org.jebtk.modern.collapsepane;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.HighlightAnimation;
import org.jebtk.modern.graphics.ImageUtils;

/**
 * Allows for fade in/out animation on an element.
 *
 * @author Antony Holmes
 */
public class CollapsePaneHighlightAnimation extends HighlightAnimation {

  private final ModernSubCollapsePane mPane;

  /**
   * Instantiates a new state animation.
   *
   * @param w
   */
  public CollapsePaneHighlightAnimation(ModernWidget w) {
    super((ModernSubCollapsePane) w);

    mPane = (ModernSubCollapsePane) w;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.animation.Animation#draw(org.abh.common.ui.widget.
   * ModernWidget, java.awt.Graphics2D, java.lang.Object[])
   */
  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {

    Rectangle r = new Rectangle(mWidget.getInsets().left, mWidget.getInsets().top,
        mWidget.getWidth() - mWidget.getInsets().left - mWidget.getInsets().right, ModernWidget.WIDGET_HEIGHT);

    Graphics2D g2Temp = ImageUtils.clone(g2);

    try {
      g2Temp.translate(mPane.getInternalRect().getX(), r.y);

      for (int i = 0; i < mPane.mTabNames.size(); ++i) {
        mPane.mNodeRenderer.getRenderer(mPane, mPane.mTabNames.get(i), i, mPane.mHighlightIndex == i, false, false,
            mPane.mExpanded.get(i));

        mPane.mNodeRenderer.setSize(mPane.getInternalRect().getW(), mPane.mHeaderHeight);

        mPane.mNodeRenderer.print(g2Temp);

        g2Temp.translate(0,
            mPane.mHeaderHeight + (mPane.mExpanded.get(i) ? mPane.mComponents.get(i).getPreferredSize().height : 0));
      }
    } finally {
      g2Temp.dispose();
    }
  }

}
