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

import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.event.HighlightEvent;
import org.jebtk.modern.event.HighlightEventProducer;
import org.jebtk.modern.event.HighlightListener;

/**
 * Highlight animation that automatically registers to respond to highlight
 * events.
 *
 * @author Antony Holmes
 */
public abstract class HighlightAnimation extends FadeAnimation {

  /**
   * The listener interface for receiving state events. The class that is
   * interested in processing a state event implements this interface, and the
   * object created with that class is registered with a component using the
   * component's <code>addStateListener<code> method. When the state event occurs,
   * that object's appropriate method is invoked.
   *
   * @see StateEvent
   */
  private class Listener implements HighlightListener {

    @Override
    public void highlighted(HighlightEvent e) {
      restart();
    }
  }

  /**
   * Instantiates a new state animation.
   * @param <T>
   * @param widget
   */
  public <T extends ModernWidget & HighlightEventProducer> HighlightAnimation(T widget) {
    super(widget);

    widget.addHighlightListener(new Listener());
  }
}
