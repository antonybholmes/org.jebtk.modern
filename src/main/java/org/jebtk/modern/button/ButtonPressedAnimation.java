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
package org.jebtk.modern.button;

import java.awt.Color;
import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.MousePressedAnimation;
import org.jebtk.modern.animation.TranslateAnimation;

/**
 * The Class RibbonMenuAnimation.
 */
public class ButtonPressedAnimation extends MousePressedAnimation {

  protected Color mColor;

  /**
   * Instantiates a new ribbon menu animation.
   *
   * @param button the button
   */
  public ButtonPressedAnimation(ModernWidget button, Color color) {
    super(button);

    mColor = color;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.widget.ModernClickWidget#drawBackgroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {
    // System.err.println("hmm " + isRunning() + " " + isPressed());

    if (getFrame() == -1) {
      return;
    }

    double r = TranslateAnimation.BEZ_T[getFrame()]; // /
    // (double)TimerAnimation.AnimationTimer.STEPS;

    int w = mWidget.getWidth();

    int d = (int) (w * r);

    int x = (w - d) / 2;

    g2.setColor(mColor);

    g2.fillRect(x, 0, d, mWidget.getHeight());
  }
}
