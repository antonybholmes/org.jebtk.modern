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

import org.jebtk.core.NameGetter;
import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;

/**
 * An animation is a series of one or more widget drawings to indicate a sense
 * of flow or movement.
 */
public abstract class Animation implements NameGetter {

  /**
   * The animation becomes responsible handling the drawing for the widget.This method is called on each repaint.
   * @param c
   * @param g2
   * @param props
   */
  public abstract void draw(ModernWidget c, Graphics2D g2, Props props);

  /**
   * Trigger action in animation. Designed for more complex events such as
   * triggering an animation in a parent when a mouse event in a child is
   * triggered. Should make event logic simpler.
   * 
   * @param trigger name of event to trigger, e.g. "mouse-entered".
   */
  public void fireEvent(AnimationEventType trigger) {
    // Do nothing
  }
}
