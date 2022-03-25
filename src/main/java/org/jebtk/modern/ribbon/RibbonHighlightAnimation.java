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
package org.jebtk.modern.ribbon;

import java.awt.Color;
import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.HighlightAnimation;
import org.jebtk.modern.theme.MaterialService;

/**
 * Allows for fade in/out animation on an element.
 *
 * @author Antony Holmes
 */
public class RibbonHighlightAnimation extends HighlightAnimation {

  public static final Color HIGHLIGHT_COLOR = MaterialService.getInstance().getColor("ribbon-highlight");

  /** The m ribbon. */
  private Ribbon mRibbon;

  /**
   * Instantiates a new state animation.
   *
   * @param ribbon the ribbon
   */
  public RibbonHighlightAnimation(ModernWidget ribbon) {
    super((Ribbon) ribbon);

    mRibbon = (Ribbon) ribbon;

    setFadeColor("highlight", MaterialService.getInstance().getColor("gray-highlight")); // new Color(242, 242, 242));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.animation.Animation#draw(org.abh.common.ui.widget.
   * ModernWidget, java.awt.Graphics2D, java.lang.Object[])
   */
  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {

    /*
     * int x; int tabWidth;
     * 
     * for (int i = 0; i < mRibbon.mTitles.size(); ++i) { x = mRibbon.mTabStartX +
     * mRibbon.mTabStarts.get(i); tabWidth = mRibbon.mTabWidths.get(i);
     * 
     * if (i == mRibbon.mHighlightedTab) {
     * g2.setColor(mFade.getFadeColor("highlight"));
     * 
     * //g2.fill(tabShape);
     * 
     * g2.fillRect(x, Ribbon.Y_OFFSET, tabWidth, Ribbon.TAB_HEIGHT);
     * 
     * break; } }
     */

    //
    // Paint the file menu item
    //

    // int textX = x + TAB_PADDING_X;

    // g2.setColor(Color.WHITE);
    // g2.drawString(FILE_TOOLBAR, textX, y);

    //
    // Paint the tabs.
    //

    // x = mTabStartX;

    for (int i = 0; i < mRibbon.mTitles.size(); ++i) {
      int x = mRibbon.mTabStartX + mRibbon.mTabStarts.get(i);
      Integer tabWidth = mRibbon.mTabWidths.get(i);

      if (i == mRibbon.mHighlightedTab && i != mRibbon.mSelectedTab) {
        g2.setColor(getFadeColor("highlight"));
        g2.fillRect(x, Ribbon.Y_OFFSET, tabWidth, Ribbon.TAB_HEIGHT);
      }
    }
  }
}
