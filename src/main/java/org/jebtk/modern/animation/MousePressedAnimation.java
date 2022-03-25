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
package org.jebtk.modern.animation;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.jebtk.modern.ModernWidget;

/**
 * Allows for fade in/out animation on an element.
 *
 * @author Antony Holmes
 */
public abstract class MousePressedAnimation extends TimerAnimation {

  protected boolean mPressed = false;

  /**
   * The Class MouseEvents.
   */
  private class MouseEvents extends MouseAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(MouseEvent e) {
      mPressed = true;

      start();
    }

    @Override
    public void mouseExited(MouseEvent e) {
      reset();
    }
  }

  /**
   * Instantiates a new mouse animation.
   *
   * @param widget the widget
   */
  public MousePressedAnimation(ModernWidget widget) {
    super(widget);

    bind(widget);
  }

  /**
   * Bind the widget so it can respond with an animation. This widget can be
   * auxiliary to the primary widget whose animation is being controlled.
   * 
   * @param mWidget
   * @return
   */
  public MousePressedAnimation bind(Component c) {
    c.addMouseListener(new MouseEvents());

    return this;
  }

  public void reset() {
    mPressed = false;

    stop();
  }

  public boolean isPressed() {
    return mPressed;
  }
}
