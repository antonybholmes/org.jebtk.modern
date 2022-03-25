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
public abstract class HoverFadeAnimation extends HoverAnimation {

  protected FadeAnimation mFade;

  /**
   * Instantiates a new hover fade animation.
   *
   * @param widget the widget
   */
  public HoverFadeAnimation(ModernWidget widget) {
    super(widget);

    mFade = new FadeAnimation(widget);

    /*
     * widget.addFocusListener(new FocusListener() {
     * 
     * @Override public void focusGained(FocusEvent e) { // If the button regains
     * focus, it means a dialog etc was mPressed = false; //pseudoMouseExited(); }
     * 
     * @Override public void focusLost(FocusEvent e) {
     * //System.err.println("f loss "); }});
     */
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

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.animation.MouseAnimation#animateMouseEntered()
   */
  @Override
  public void animateMouseEntered() {
    // Stop when the color is fully opaque
//    if (mFade.getStep() == TimerAnimation.AnimationTimer.MAX_STEP_INDEX) {
//      stopMouseOverTimer();
//    } else {
//      mFade.fadeIn();
//    }

    mFade.fadeIn();

    super.animateMouseEntered();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.animation.MouseAnimation#animateMouseExited()
   */
  @Override
  public void animateMouseExited() {
    mFade.setFrame(0); // reset(); //fadeOut();

    super.animateMouseExited();
  }

  /**
   * Gets the trans.
   *
   * @return the trans
   */
  // public double getTrans() {
  // return mFade.getTrans();
  // }
//
//  public void setStep(int step) {
//    mFade.setStep(step);
//  }

  // public void reset() {
  // mFade.reset();
  // }

  public void opaque() {
    mFade.opaque();
  }
}
