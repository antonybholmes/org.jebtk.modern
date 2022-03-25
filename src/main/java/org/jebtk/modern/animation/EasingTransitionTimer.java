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

/**
 * Allows for graphics to transition between two fixed points using a Bezier
 * curve to control the speed of animation
 *
 * @author Antony Holmes
 */
public class EasingTransitionTimer implements TransitionTimer {

  // Material Design recommended easing
  // https://material.io/design/motion/speed.html#easing
  // public final static CubicBezier BEZIER = CubicBezier
  // .normCubicBezier(0.4, 0.0, 0.2, 1);

  // CSS default
  public static final CubicBezier BEZIER = CubicBezier.normCubicBezier(0.25, 0.1, 0.25, 1);

  private double[] mBezT;

  private int mStep = 0;

  private int mSteps;

  /**
   * Instantiates a new state animation.
   *
   * @param ribbon the ribbon
   */
  public EasingTransitionTimer(int steps) {
    mSteps = steps;

    mBezT = new double[steps];

    int si = steps - 1;

    mBezT[0] = 0;
    mBezT[si] = 1;

    for (int i = 1; i < steps - 1; ++i) {
      mBezT[i] = Mathematics.bound(BEZIER.evalY((double) i / si), 0, 1);
    }
  }

  @Override
  public double nextT(boolean forward) {
    if (forward) {
      mStep = Math.min(mStep + 1, mSteps);
    } else {
      mStep = Math.max(0, mSteps);
    }

    return mBezT[mStep];
  }
}
