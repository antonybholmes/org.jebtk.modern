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
package org.jebtk.modern.listpanel;

import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.WidgetAnimation;

/**
 * Allows for fade in/out animation on an element.
 *
 * @author Antony Holmes
 * @param <T>
 */
public class ListPanelItemAnimation extends WidgetAnimation {

  private ModernListPanelItem mItem;

  /**
   * Instantiates a new state animation.
   *
   * @param ribbon the ribbon
   */
  public ListPanelItemAnimation(ModernWidget w) {
    super(w);

    mItem = (ModernListPanelItem) w;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.animation.Animation#draw(org.abh.common.ui.widget.
   * ModernWidget, java.awt.Graphics2D, java.lang.Object[])
   */
  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {

    g2.setColor(mItem.mColor);

    int x = mWidget.getWidth() - mWidget.getInsets().right - ModernListPanelItem.HANDLE_SIZE
        + (ModernListPanelItem.HANDLE_SIZE - ModernListPanelItem.BURGER_WIDTH) / 2;
    int y = (mWidget.getHeight() - 6) / 2;

    g2.drawLine(x, y, x + ModernListPanelItem.BURGER_WIDTH, y);
    y += 2;
    g2.drawLine(x, y, x + ModernListPanelItem.BURGER_WIDTH, y);
    y += 2;
    g2.drawLine(x, y, x + ModernListPanelItem.BURGER_WIDTH, y);
  }
}
