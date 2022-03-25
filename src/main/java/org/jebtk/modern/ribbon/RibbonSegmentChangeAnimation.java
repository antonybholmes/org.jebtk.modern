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

import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import org.jebtk.core.Props;
import org.jebtk.core.geom.IntRect;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.TranslateYAnimation;
import org.jebtk.modern.tabs.TabEvent;
import org.jebtk.modern.tabs.TabEventAdapter;
import org.jebtk.modern.theme.DrawUIService;

/**
 * Allows for fade in/out animation on an element.
 *
 * @author Antony Holmes
 * @param <T>
 */
public class RibbonSegmentChangeAnimation extends TranslateYAnimation {

  private RibbonSegmentVertTabs mTabs;

  protected static final int WIDTH = 5;

  /**
   * Instantiates a new state animation.
   *
   * @param ribbon the ribbon
   */
  public RibbonSegmentChangeAnimation(ModernWidget tabs) {
    super(tabs);

    mTabs = (RibbonSegmentVertTabs) tabs;

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

    int y1 = mTabs.getInsets().top + previousIndex * RibbonSegmentVertTabs.TAB_SIZE;

    int y2 = mTabs.getInsets().top + selectedIndex * RibbonSegmentVertTabs.TAB_SIZE;

    restart(y1, y2);
  }

  @Override
  public void drawTranslation(ModernWidget widget, Graphics2D g2, Props props) {
    /*
     * int w = mTabs.getInternalRect().getW();
     * 
     * mTabs.getWidgetRenderer().buttonFillPaint(g2, 0, 0, w,
     * RibbonSegmentVertTabs.TAB_SIZE, RenderMode.SELECTED, false);
     */

    // g2.setColor(Ribbon.BAR_BACKGROUND);

    // g2.fillRect(0, 0, WIDTH, RibbonSegmentVertTabs.TAB_SIZE);

    DrawUIService.getInstance().getRenderer("button-fill").draw(g2,
        new IntRect(0, 0, WIDTH, RibbonSegmentVertTabs.TAB_SIZE), Ribbon.BAR_BACKGROUND);
  }
}
