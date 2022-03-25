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

import java.awt.Graphics2D;

import org.jebtk.core.Mathematics;
import org.jebtk.core.Props;
import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.geom.IntRect;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.TranslateYAnimation;
import org.jebtk.modern.event.ModernSelectionListener;
import org.jebtk.modern.theme.DrawUIService;

/**
 * Allows for fade in/out animation on an element.
 *
 * @author Antony Holmes
 * @param <T>
 */
public class ListChangeAnimation extends TranslateYAnimation {

  private ModernList<?> mTabs;

  /**
   * Instantiates a new state animation.
   *
   * @param ribbon the ribbon
   */
  public ListChangeAnimation(ModernWidget tabs) {
    super((ModernList<?>) tabs);

    mTabs = (ModernList<?>) tabs;

    mTabs.getSelectionModel().addSelectionListener(new ModernSelectionListener() {
      @Override
      public void selectionAdded(ChangeEvent e) {
        selectionRemoved(e);
      }

      @Override
      public void selectionRemoved(ChangeEvent e) {
        restart();
      }
    });

    /*
     * mTabs.addComponentListener(new ComponentAdapter() {
     * 
     * @Override public void componentResized(ComponentEvent arg0) { restart(); }});
     */
  }

  public void restart() {
    int selectedIndex = mTabs.getSelectionModel().getCurrent();
    int previousIndex = mTabs.getSelectionModel().getPrevious();

    if (previousIndex > selectedIndex) {
      previousIndex = selectedIndex + 1;
    }

    if (previousIndex < selectedIndex) {
      previousIndex = selectedIndex - 1;
    }

    previousIndex = Mathematics.bound(previousIndex, 0, mTabs.getItemCount() - 1);

    selectedIndex = Mathematics.bound(selectedIndex, 0, mTabs.getItemCount() - 1);

    int y1 = mTabs.getInsets().top + previousIndex * mTabs.mRowHeight;

    int y2 = mTabs.getInsets().top + selectedIndex * mTabs.mRowHeight;

    restart(y1, y2);
  }

  @Override
  public void drawTranslation(ModernWidget widget, Graphics2D g2, Props props) {
    /*
     * widget.getWidgetRenderer().drawMenu(g2, 0, 0, widget.getWidth(),
     * mTabs.mRowHeight, RenderMode.SELECTED, false);
     */

    DrawUIService.getInstance().getRenderer("button-fill").draw(g2,
        new IntRect(0, 0, widget.getWidth(), mTabs.mRowHeight));
  }
}
