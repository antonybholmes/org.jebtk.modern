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

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

import org.jebtk.core.ColorUtils;
import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.graphics.colormap.ColorMap;

/**
 * Allows for fade in/out animation on an element.
 *
 * @author Antony Holmes
 */
public class ColorTransition extends Transition {

  /** The m fade color map. */
  private final Map<String, ColorMap> mFadeColorMap = new HashMap<>();

  private final int mSteps;

  private final double mD;

  private double mT;

  /** Map step to transparency level */
  // private Map<Integer, Double> mStepMap = new HashMap<Integer, Double>();

  /**
   * Instantiates a new hover fade animation.
   *
   * @param widget the widget
   */
  public ColorTransition(int steps) {
    mSteps = steps;
    mD = 1.0 / steps;

    // double t = 0;

    // setStep(0);

    // mStepMap.put(0, 0.0);
    // mStepMap.put(AnimationTimer.MAX_STEP_INDEX, 1.0);

    // for (int i = 1; i < AnimationTimer.MAX_STEP_INDEX; ++i) {
    // mStepMap.put(i, BEZ_T[i]);
    // }
  }

  /**
   * Set a fade in color that can transition linearly between transparent and
   * opaque.
   *
   * @param name  the name
   * @param color the color
   * @return
   */
  public void setFadeColor(String name, Color color) {
    setFadeColor(name, ColorUtils.TRANS, color);
  }

  public void setFadeColor(String name, Color color1, Color color2) {
    mFadeColorMap.put(name, ColorMap.createTwoColorMap(name, color1, color2, mSteps, false));
  }

  public Color getFromColor(String name) {
    return getColor(name, 0);
  }

  public Color getToColor(String name) {
    return getColor(name, 1);
  }

  public Color getColor(String name, double v) {
    return mFadeColorMap.get(name).getColor(v);
  }

  public Color getColor(String name) {
    return mFadeColorMap.get(name).getColor(mT);
  }

  /**
   * Gets the trans.
   *
   * @return the trans
   */
  // ublic double getTrans() {
  // return mStepMap.get(getStep());
  // }

  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {
    // TODO Auto-generated method stub

  }

  @Override
  public String getName() {
    return "color-transition";
  }

  @Override
  public void updateState(double t, boolean forward) {
    mT = t;
  }
}
