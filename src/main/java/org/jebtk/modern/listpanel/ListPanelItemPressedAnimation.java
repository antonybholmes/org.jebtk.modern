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

import java.awt.Color;
import java.awt.Graphics2D;

import org.jebtk.core.ColorUtils;
import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.FadeAnimation;
import org.jebtk.modern.animation.MousePressReleaseAnimation;

public class ListPanelItemPressedAnimation extends MousePressReleaseAnimation {

  private final FadeAnimation mFade;

  public ListPanelItemPressedAnimation(ModernWidget widget) {
    super(widget);

    mFade = new FadeAnimation(widget);

    mFade.setFadeColor("line", ModernWidget.DARK_LINE_COLOR);

    mFade.setFadeColor("highlight", ColorUtils.getTransparentColor(Color.BLACK, 0.92));
  }

  @Override
  public void animate(int frame) {
    if (mPressed) {
      mFade.fadeIn();
    } else {
      mFade.fadeOut();
    }

    super.animate(frame);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.widget.ModernClickWidget#drawBackgroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {
    // System.err.println("hmm " + getStep() + " " + mFade.getCurrentStep());

    g2.setColor(mFade.getFadeColor("highlight"));

    int w = mWidget.getWidth();
    int h = mWidget.getHeight();

    g2.fillRect(0, 0, w, h);

    // g2.setColor(mFade.getFadeColor("line"));

    /*
     * g2.drawLine(0, y, w, y);
     * 
     * y += widget.getHeight() - 1;
     * 
     * g2.drawLine(widget.getInsets().left, y, w, y);
     */

    // g2.drawRect(0,
    // 0,
    // w - 1,
    // h - 1);
  }
}
