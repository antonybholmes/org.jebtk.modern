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

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.event.ChangeEventProducer;
import org.jebtk.core.event.ChangeListener;
import org.jebtk.modern.ModernWidget;

/**
 * Allows for fade in/out animation on an element.
 *
 * @author Antony Holmes
 */
public abstract class ChangeAnimation extends TimerAnimation {

  /**
   * The listener interface for receiving click events. The class that is
   * interested in processing a click event implements this interface, and the
   * object created with that class is registered with a component using the
   * component's <code>addClickListener<code> method. When the click event occurs,
   * that object's appropriate method is invoked.
   *
   * @see ClickEvent
   */
  private class ChangeEvents implements ChangeListener {

    @Override
    public void changed(ChangeEvent e) {
      animateChanged();
    }
  }

  /**
   * Instantiates a new click animation.
   * 
   * @param <T>
   *
   * @param widget the widget
   */
  public <T extends ModernWidget & ChangeEventProducer> ChangeAnimation(T widget) {
    this(widget, AnimationTimer.DELAY_MS);
  }

  public <T extends ModernWidget & ChangeEventProducer> ChangeAnimation(T widget, int delay) {
    super(widget, delay);

    widget.addChangeListener(new ChangeEvents());
  }

  public void animateChanged() {
    start();
  }
}
