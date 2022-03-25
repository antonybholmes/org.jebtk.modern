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

import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.graphics.colormap.NamedColors;

/**
 * Allows for fade in/out animation on an element.
 *
 * @author Antony Holmes
 */
public abstract class FadeMouseOverTransition extends Transition {

  private FadeAnimation mFade;

  /**
   * Instantiates a new hover fade animation.
   *
   * @param widget the widget
   */
  public FadeMouseOverTransition(ModernWidget widget) {
    mFade = new FadeAnimation(widget);
  }

  /**
   * Set a fade in color.
   *
   * @param name  the name
   * @param color the color
   */
  public void setFadeColor(String name, Color color) {
    mFade.setFadeColor(name, color);
  }

  public void setFadeColor(String name, Color color1, Color color2) {
    mFade.setFadeColor(name, color1, color2);
  }

  public Color getFadeColor(String name) {
    return mFade.getFadeColor(name);
  }

  /**
   * Get the end color.
   * 
   * @param name
   * @return
   */
  public Color getToColor(String name) {
    return mFade.getToColor(name);
  }

  public Color getFromColor(String name) {
    return mFade.getFromColor(name);
  }

  /**
   * Gets the fade color map.
   *
   * @return the fade color map
   */
  public NamedColors getFadeColorMap() {
    return mFade.getFadeColorMap();
  }

  /**
   * Gets the fade color map.
   *
   * @param step the step
   * @return the fade color map
   */
  public NamedColors getFadeColorMap(int step) {
    return mFade.getFadeColorMap(step);
  }

  @Override
  public String getName() {
    return "fade-mouse-over-transition";
  }

  @Override
  public void updateState(double t, boolean forward) {
    if (forward) {
      mFade.fadeIn();
    } else {
      mFade.fadeOut();
    }
  }
}
