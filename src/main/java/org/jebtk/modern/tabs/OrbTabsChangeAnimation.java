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

import org.jebtk.core.Mathematics;
import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.TranslateXAnimation;

/**
 * Allows for fade in/out animation on an element.
 *
 * @author Antony Holmes
 */
public final class OrbTabsChangeAnimation extends TranslateXAnimation {

  private final OrbTabs mTabs;

  /**
   * Instantiates a new state animation.
   *
   * @param tabs
   */
  public OrbTabsChangeAnimation(ModernWidget tabs) {
    super(tabs);

    mTabs = (OrbTabs) tabs;

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
  public void restart() {
    int selectedIndex = mTabs.getTabsModel().getSelectedIndex();
    int previousIndex = mTabs.getTabsModel().getPreviousIndex();

    if (previousIndex > selectedIndex) {
      previousIndex = selectedIndex + 1;
    } else {
      previousIndex = Mathematics.bound(selectedIndex - 1, 0, mTabs.getTabsModel().getTabCount() - 1);
    }

    int leftOffset = (mTabs.getWidth() - mTabs.mTabSize * mTabs.mTabWidths.size()) / 2; // mTabs.getInsets().left;

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

    g2.setColor(mTabs.getCSSProps().getColor("background-color")); // Ribbon.BAR_BACKGROUND);
    g2.fillOval(0, mTabs.getInsets().top, h, h);
  }
  
  @Override
  public String getName() {
    return "orb-tabs-change";
  }
}
