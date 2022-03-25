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

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;

/**
 * Allows for fade in/out animation on an element.
 *
 * @author Antony Holmes
 */
public abstract class TimerAnimation extends WidgetAnimation {

  /** The m timer. */
  private AnimationTimer mTimer;

  /**
   * The Class StateEvents.
   */
  private class TimerEvents implements IAnimationListener {

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e, int frame) {
      animate(frame);
    }
  }

  /**
   * Instantiates a new state animation.
   *
   * @param w   the widget
   */
  public TimerAnimation(ModernWidget w) {
    this(w, AnimationTimer.DELAY_MS);
  }

  public TimerAnimation(ModernWidget w, int delay) {
    super(w);

    mTimer = new AnimationTimer(delay, new TimerEvents());
    //mTimer.setDelay(delay);
  }

  /**
   * Stop timer.
   */
  public void stop() {
    mTimer.stop();
    
  }

  public void start() {
    mTimer.start();
  }

  public void restart() {
    mTimer.restart();
  }

  /**
   * Animate state.
   * @param frame
   */
  public void animate(int frame) {
    repaint();
  }

  public void setFrame(int step) {
    mTimer.setCounter(step);
  }

  public void reset() {
    mTimer.reset();
  }

  public int getFrame() {
    return mTimer.getCounter();
  }

  public boolean stopped() {
    return !mTimer.isRunning();
  }
  
  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {
    System.err.println("hover:" + getFrame());
    props.set("frame", getFrame());
  }
  
  @Override
  public String getName() {
    return "widget";
  }
}
