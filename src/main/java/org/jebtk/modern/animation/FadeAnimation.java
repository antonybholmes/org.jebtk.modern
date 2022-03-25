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
import java.util.Map;

import org.jebtk.core.ColorUtils;
import org.jebtk.core.Props;
import org.jebtk.core.collections.DefaultHashMap;
import org.jebtk.core.collections.EntryCreator;
import org.jebtk.modern.ModernWidget;
import org.jebtk.core.CSSColor;
import org.jebtk.modern.graphics.colormap.ColorMap;
import org.jebtk.modern.graphics.colormap.NamedColors;

/**
 * Allows for fade in/out animation on an element.
 *
 * @author Antony Holmes
 */
public class FadeAnimation extends EasingAnimation {

  /** The Constant MD. */
  protected static final double MD = 1.0 / AnimationTimer.STEPS;

  /** The m fade color map. */
  private Map<Integer, NamedColors> mFadeColorMap = DefaultHashMap.create(new EntryCreator<NamedColors>() {
    @Override
    public NamedColors newEntry() {
      return new NamedColors();
    }
  });

  /** Map step to transparency level */
  // private Map<Integer, Double> mStepMap = new HashMap<Integer, Double>();

  /**
   * Instantiates a new hover fade animation.
   *
   * @param widget the widget
   */
  public FadeAnimation(ModernWidget widget) {
    super(widget);

    // double t = 0;

    // setStep(0);

    // mStepMap.put(0, 0.0);
    // mStepMap.put(AnimationTimer.MAX_STEP_INDEX, 1.0);

    // for (int i = 1; i < AnimationTimer.MAX_STEP_INDEX; ++i) {
    // mStepMap.put(i, BEZ_T[i]);
    // }
  }
  
  @Override
  public String getName() {
    return "fade";
  }

  @Override
  public void restart() {
    reset();
    start();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.animation.TimerAnimation#animate()
   */
  @Override
  public void animate(int frame) {
    getWidget().repaint();

    if (frame == AnimationTimer.MAX_STEP_INDEX) {
      stop();
    } else {
      fadeIn();
    }
  }

  /**
   * Set a fade in color that can transition linearly between transparent and
   * opaque.
   *
   * @param name  the name
   * @param color the color
   * @return
   */
  public FadeAnimation setFadeColor(String name, Color color) {
    double t = ColorUtils.getAlpha(color);

    // double d = (1.0 - t); // / AnimationTimer.STEPS;

    for (int i = 0; i < AnimationTimer.STEPS; ++i) {
      Color c = ColorUtils.getColor(color, t * BEZ_T[i]);

      mFadeColorMap.get(i).add(name, c);
    }

    return this;
  }

  public FadeAnimation setFadeColor(String name, Color color1, Color color2) {
    // We need to reverse the color map because we are using the
    // transparent fade logic. This means that the default is to start
    // in the transparent state (the last step) and work backwards to
    // opaque once the animation begins. Therefore with a color map,
    // the last color needs to be the starting color.
    CSSColor colorMap[] = ColorMap.createTwoColorMap(color1, color2, AnimationTimer.STEPS, false);

    for (int i = 0; i < AnimationTimer.STEPS; ++i) {
      mFadeColorMap.get(i).add(name, colorMap[i]);
    }

    return this;
  }

  public Color getFadeColor(String name) {
    return getFadeColorMap().get(name);
  }

  /**
   * Gets the fade color map.
   *
   * @return the fade color map
   */
  public NamedColors getFadeColorMap() {
    return getFadeColorMap(getFrame());
  }

  /**
   * Gets the fade color map.
   *
   * @param step the step
   * @return the fade color map
   */
  public NamedColors getFadeColorMap(int step) {
    return mFadeColorMap.get(step);
  }

  public NamedColors getToFadeColorMap() {
    return getFadeColorMap(AnimationTimer.MAX_STEP_INDEX);
  }

  public NamedColors getFromFadeColorMap() {
    return getFadeColorMap(0);
  }

  /**
   * Get the end color.
   * 
   * @param name The color gradient name.
   * @return
   */
  public Color getToColor(String name) {
    return getToFadeColorMap().get(name);
  }

  public Color getFromColor(String name) {
    return getFromFadeColorMap().get(name);
  }

  /**
   * Cause the color to fade in (get more opaque/darker) by one step.
   */
  public void fadeIn() {
    // mTrans = Mathematics.bound(mTrans - MD, 0, 1);
    // mCurrentStep = Mathematics.bound(mCurrentStep - 1, 0, AnimationTimer.STEPS);

    setFrame(getFrame() + 1);
  }

  public void fadeOut() {
    // mTrans = Mathematics.bound(mTrans + MD, 0, 1);
    // mCurrentStep = Mathematics.bound(mCurrentStep + 1, 0, AnimationTimer.STEPS);

    setFrame(getFrame() - 1);
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

  public void opaque() {
    setFrame(AnimationTimer.MAX_STEP_INDEX);
  }

  
}
