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

import org.jebtk.core.Props;
import org.jebtk.math.CubicBezier;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.graphics.ImageUtils;

/**
 * Allows for graphics to transition between two fixed points linearly.
 *
 * @author Antony Holmes
 */
public abstract class RotationAnimation extends TimerAnimation {

  // Lets define a material design bezier curve to animate transitions
  public final static CubicBezier BEZIER = CubicBezier.normCubicBezier(0.4, 0.0, 0.2, 1);

  private static final int MAX_INDEX = AnimationTimer.STEPS - 1;
  private double[] mAngle = new double[AnimationTimer.STEPS];
  private int mStep = 0;
  private boolean mForwardDir = false;

  /**
   * Instantiates a new state animation.
   *
   * @param ribbon the ribbon
   */
  public RotationAnimation(ModernWidget widget, int a1, int a2) {
    super(widget);

    setup(a1, a2);
  }

  public synchronized void setup(int a1, int a2) {
    double mD = Math.toRadians(a2 - a1); // / MAX_INDEX;

    mStep = 0;

    mAngle[0] = Math.toRadians(a1);
    mAngle[mAngle.length - 1] = Math.toRadians(a2);

    for (int i = 1; i < MAX_INDEX; ++i) {
      mAngle[i] = mAngle[0] + TranslateAnimation.BEZ_T[i] * mD; // BEZIER.eval(t)
      // mXPos[i - 1]
      // + mD;
    }
  }

  public synchronized void restart(int a1, int a2) {
    setup(a1, a2);

    start();
  }

  public synchronized void restart() {
    restart(!mForwardDir);
  }

  public synchronized void restart(boolean forward) {
    mForwardDir = forward;

    if (mForwardDir) {
      mStep = 0;
    } else {
      mStep = MAX_INDEX;
    }

    super.restart();
  }

  @Override
  public synchronized void animate(int frame) {
    super.animate(frame);

    // System.err.println("translate " + getStep());

    if (mForwardDir) {
      mStep = Math.min(mStep + 1, MAX_INDEX);
    } else {
      mStep = Math.max(mStep - 1, 0);
    }
  }

  public double getAngle() {
    return mAngle[mStep];
  }

  public double getEndAngle() {
    return mAngle[MAX_INDEX];
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.animation.Animation#draw(org.abh.common.ui.widget.
   * ModernWidget, java.awt.Graphics2D, java.lang.Object[])
   */
  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {

    Graphics2D g2Temp = ImageUtils.clone(g2);

    try {
      g2.rotate(getAngle());

      drawRotation(mWidget, g2Temp, props);
    } finally {
      g2Temp.dispose();
    }
  }

  public void drawRotation(ModernWidget widget, Graphics2D g2, Props props) {

  }
}
