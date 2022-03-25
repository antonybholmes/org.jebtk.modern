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

/**
 * Allows for graphics to transition between two fixed points using a Bezier
 * curve to control the speed of animation
 *
 * @author Antony Holmes
 */
public class LinearTransitionTimer implements TransitionTimer {

  private double mInc;

  private double mT = 0;

  /**
   * Instantiates a new state animation.
   *
   * @param ribbon the ribbon
   */
  public LinearTransitionTimer(int steps) {
    mInc = 1.0 / (steps - 1);
  }

  @Override
  public double nextT(boolean forward) {
    if (forward) {
      return Math.min(mT + mInc, 1);
    } else {
      return Math.max(mT - mInc, 0);
    }

  }
}
