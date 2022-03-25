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
public class RibbonHighlightTextAnimation extends HighlightAnimation {

  public static final Color HIGHLIGHT_COLOR = MaterialService.getInstance().getColor("ribbon-highlight");

  /** The m ribbon. */
  private Ribbon mRibbon;

  /**
   * Instantiates a new state animation.
   *
   * @param ribbon the ribbon
   */
  public RibbonHighlightTextAnimation(ModernWidget ribbon) {
    super((Ribbon) ribbon);

    mRibbon = (Ribbon) ribbon;

    setFadeColor("highlight", MaterialService.getInstance().getColor("ribbon-menu-font"),
        MaterialService.getInstance().getColor("ribbon"));
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

    int x;
    int y = Ribbon.TAB_BODY_Y - Ribbon.TAB_HEIGHT + (Ribbon.TAB_HEIGHT + g2.getFontMetrics().getAscent()) / 2;
    int tabWidth;

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

    // g2.setFont(ModernWidget.BOLD_FONT);

    for (int i = 0; i < mRibbon.mTitles.size(); ++i) {
      x = mRibbon.mTabStartX + mRibbon.mTabStarts.get(i);
      tabWidth = mRibbon.mTabWidths.get(i);

      // Render the headings

      // if (mToolbarVisible && i == mSelectedTab) {
      // g2.setColor(BAR_BACKGROUND);
      // } else {
      // g2.setColor(i == mSelectedTab ? BAR_BACKGROUND : TEXT_COLOR);
      // //Color.WHITE);
      // }

      // if (i == mRibbon.mSelectedTab) {
      // g2.setColor(Ribbon.BAR_BACKGROUND);
      // } else if (i == mRibbon.mHighlightedTab) {
      // g2.setColor(getFadeColor("highlight"));
      // } else {
      // g2.setColor(MaterialService.getInstance().getColor("ribbon-menu-font"));
      // }

      if (i == mRibbon.mSelectedTab) {
        g2.setColor(Ribbon.BAR_BACKGROUND);
      } else {
        g2.setColor(ModernWidget.TEXT_COLOR); // Color.WHITE);
      }

      int textX = x + (tabWidth - g2.getFontMetrics().stringWidth(mRibbon.mTitles.get(i))) / 2;

      g2.drawString(mRibbon.mTitles.get(i), textX, y);
    }
  }
}
