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

import org.jebtk.core.Props;
import org.jebtk.core.geom.IntRect;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.WidgetAnimation;
import org.jebtk.modern.theme.DrawUIService;
import org.jebtk.modern.theme.MaterialService;

/**
 * Allows for fade in/out animation on an element.
 *
 * @author Antony Holmes
 * @param <T>
 */
public class RibbonSegmentSelectedAnimation extends WidgetAnimation {

  private final RibbonSegmentVertTabs mSegments;

  /**
   * Instantiates a new state animation.
   *
   * @param ribbon the ribbon
   */
  public RibbonSegmentSelectedAnimation(ModernWidget segments) {
    super(segments);

    mSegments = (RibbonSegmentVertTabs) segments;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.animation.Animation#draw(org.abh.common.ui.widget.
   * ModernWidget, java.awt.Graphics2D, java.lang.Object[])
   */
  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {

    int x = mSegments.getInsets().left;
    int y = mSegments.getInsets().top;

    int y1;

    int w = mSegments.getInternalRect().getW();

    int selected = mSegments.getTabsModel().getSelectedIndex();

    //
    // Draw if highlighted
    //

    if (selected != -1) { // highlighted != selectedIndex &&
      y1 = y + selected * RibbonSegmentVertTabs.TAB_SIZE;

      // g2.setColor(MaterialService.getInstance().getColor("ribbon-selected"));
      // g2.fillRect(x, y1, w, RibbonSegmentVertTabs.TAB_SIZE);

      DrawUIService.getInstance().getRenderer("button-fill").draw(g2,
          new IntRect(x, y1, w, RibbonSegmentVertTabs.TAB_SIZE),
          MaterialService.getInstance().getColor("ribbon-selected"));
    }
  }
}
