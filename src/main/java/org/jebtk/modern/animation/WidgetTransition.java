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

import org.jebtk.modern.ModernWidget;

/**
 * Allows for fade in/out animation on an element.
 *
 * @author Antony Holmes
 */
public abstract class WidgetTransition extends Transition {

  /** The m widget. */
  private ModernWidget mWidget;

  /**
   * Instantiates a new widget animation.
   *
   * @param widget the widget
   */
  public WidgetTransition(ModernWidget widget) {
    mWidget = widget;
  }

  /**
   * Gets the widget.
   *
   * @return the widget
   */
  public ModernWidget getWidget() {
    return mWidget;
  }

  @Override
  public String getName() {
    return "widget";
  }

  public void bindChildren() {
    // Do nothing
  }
}
