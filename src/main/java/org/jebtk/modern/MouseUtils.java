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
package org.jebtk.modern;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import org.jebtk.core.geom.IntPos2D;

/**
 * The Class MouseUtils.
 */
public class MouseUtils {

  /**
   * Instantiates a new mouse utils.
   */
  private MouseUtils() {
    // Do nothing
  }

  /**
   * Clone a mouse event but update its location. Useful for callback functions
   * that need to normalize mouse events.
   *
   * @param e the e
   * @param x the x
   * @param y the y
   * @return the mouse event
   */
  public static MouseEvent updateXY(final MouseEvent e, int x, int y) {
    return new MouseEvent(e.getComponent(), e.getID(), e.getWhen(), e.getModifiers(), x, y, e.getClickCount(),
        e.isPopupTrigger());
  }

  /**
   * Update XY.
   *
   * @param e the e
   * @param p the p
   * @return the mouse event
   */
  public static MouseEvent updateXY(final MouseEvent e, final IntPos2D p) {
    return updateXY(e, p.getX(), p.getY());
  }

  /**
   * Returns true if the Ctrl key is pressed whilst the mouse event is triggered.
   * 
   * @param e
   * @return
   */
  public static boolean ctrlPressed(final MouseEvent e) {
    return (e.getModifiers() & ActionEvent.CTRL_MASK) == ActionEvent.CTRL_MASK;
  }

  /**
   * Returns true if the mouse event has modifiers, i.e. keys are also pressed
   * whilst the event is triggered.
   * 
   * @param e
   * @return
   */
  public static boolean hasModifiers(MouseEvent e) {
    return e.getModifiers() != 0;
  }
}
