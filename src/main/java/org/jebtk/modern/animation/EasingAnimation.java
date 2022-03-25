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

import org.jebtk.core.Mathematics;
import org.jebtk.math.CubicBezier;
import org.jebtk.modern.ModernWidget;

/**
 * Allows for graphics to transition between two fixed points using a Bezier
 * curve to control the speed of animation
 *
 * @author Antony Holmes
 */
public abstract class EasingAnimation extends TimerAnimation {

  // Material Design recommended easing
  // https://material.io/design/motion/speed.html#easing
  // public final static CubicBezier BEZIER = CubicBezier
  // .normCubicBezier(0.4, 0.0, 0.2, 1);

  // CSS default
  public static final CubicBezier BEZIER = CubicBezier.normCubicBezier(0.25, 0.1, 0.25, 1);

  public static final double[] BEZ_T = new double[AnimationTimer.STEPS];

  static {
    BEZ_T[0] = 0; // BEZIER.eval(0).getY();
    BEZ_T[AnimationTimer.MAX_STEP_INDEX] = 1; // BEZIER.eval(1).getY();

    for (int i = 1; i < AnimationTimer.MAX_STEP_INDEX; ++i) {
      BEZ_T[i] = Mathematics.bound(BEZIER.evalY((double) i / AnimationTimer.MAX_STEP_INDEX), 0, 1);
    }
  }

  /**
   * Instantiates a new state animation.
   *
   * @param ribbon the ribbon
   */
  public EasingAnimation(ModernWidget widget) {
    super(widget);
  }
}
