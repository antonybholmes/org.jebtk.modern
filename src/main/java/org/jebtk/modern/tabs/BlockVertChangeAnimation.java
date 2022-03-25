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
package org.jebtk.modern.tabs;

import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.TranslateYAnimation;
import org.jebtk.modern.ribbon.RibbonChangeAnimation;

/**
 * Allows for fade in/out animation on an element.
 *
 * @author Antony Holmes
 * @param <T>
 */
public class BlockVertChangeAnimation extends TranslateYAnimation {

  private BlockVertTabs mTabs;

  /**
   * Instantiates a new state animation.
   *
   * @param ribbon the ribbon
   */
  public BlockVertChangeAnimation(ModernWidget tabs) {
    super((BlockVertTabs) tabs);

    mTabs = (BlockVertTabs) tabs;

    mTabs.getTabsModel().addTabListener(new TabEventAdapter() {
      @Override
      public void tabChanged(TabEvent e) {
        restart();
      }
    });

    mTabs.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent arg0) {
        restart();
      }
    });
  }

  public void restart() {
    int selectedIndex = mTabs.getTabsModel().getSelectedIndex();
    int previousIndex = mTabs.getTabsModel().getPreviousIndex();

    if (previousIndex == -1) {
      previousIndex = selectedIndex;
    }

    int y1 = mTabs.getInsets().top + mTabs.mOffset + previousIndex * mTabs.mTabSize;

    int y2 = mTabs.getInsets().top + mTabs.mOffset + selectedIndex * mTabs.mTabSize;

    restart(y1, y2);
  }

  @Override
  public void drawTranslation(ModernWidget widget, Graphics2D g2, Props props) {
    g2.setColor(BlockVertTabs.TEXT_TAB_SELECTED_COLOR);
    // g2.fill(mTabs.mP);
    g2.fillRect(0, 0, RibbonChangeAnimation.BAR_HEIGHT, mTabs.mTabSize);

  }
}
