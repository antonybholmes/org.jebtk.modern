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

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import org.jebtk.core.Mathematics;
import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.TranslateXAnimation;
import org.jebtk.modern.ribbon.Ribbon;
import org.jebtk.modern.ribbon.RibbonChangeAnimation;

/**
 * Allows for fade in/out animation on an element.
 *
 * @author Antony Holmes
 * @param <T>
 */
public class SegmentChangeAnimation extends TranslateXAnimation {

  private SegmentTabs mTabs;

  // protected static final int HEIGHT = 2;

  public static final Color COLOR = Ribbon.BAR_BACKGROUND; // Color.BLACK

  /**
   * Instantiates a new state animation.
   *
   * @param ribbon the ribbon
   */
  public SegmentChangeAnimation(ModernWidget tabs) {
    super(tabs);

    mTabs = (SegmentTabs) tabs;

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

    restart();
  }

  @Override
  public synchronized void restart() {
    int selectedIndex = mTabs.getTabsModel().getSelectedIndex();
    int previousIndex = mTabs.getTabsModel().getPreviousIndex();

    if (previousIndex > selectedIndex) {
      previousIndex = selectedIndex + 1;
    } else {
      previousIndex = Mathematics.bound(selectedIndex - 1, 0, mTabs.getTabsModel().getTabCount() - 1);
    }

    int leftOffset = 0;

    if (mTabs.mCenter) {
      leftOffset = mTabs.getInsets().left
          + (mTabs.getRect().getW() - mTabs.mTabSize * mTabs.getTabsModel().getTabCount()) / 2;
    } else {
      leftOffset = mTabs.getInsets().left;
    }

    int x1 = leftOffset + previousIndex * mTabs.mTabSize;
    int x2 = leftOffset + selectedIndex * mTabs.mTabSize;

    restart(x1, x2);
  }

  @Override
  public void drawTranslation(ModernWidget widget, Graphics2D g2, Props props) {
    int h = mTabs.getInternalRect().getH();

    /*
     * mTabs.getWidgetRenderer().primaryDialogButtonFillPaint(g2, 0, 0,
     * mTabs.mTabSize, h, RenderMode.NONE, false);
     */

    // g2.setColor(COLOR);
    // mTabs.getWidgetRenderer().drawPillButtonFill(g2, 0, 0, mTabs.mTabSize,
    // h);

    g2.setColor(COLOR);
    g2.fillRect(0, h - RibbonChangeAnimation.BAR_HEIGHT, mTabs.mTabSize, RibbonChangeAnimation.BAR_HEIGHT);
  }
  
  @Override
  public String getName() {
    return "segment-change";
  }
}
