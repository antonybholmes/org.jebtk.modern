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
package org.jebtk.modern.ribbon;

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.event.ChangeListener;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.button.ButtonCSSAnimation;
import org.jebtk.modern.button.ModernDropDownWidget;

/**
 * The Class RibbonDropDownButtonAnimation.
 */
public class RibbonDropDownButtonAnimation extends ButtonCSSAnimation implements ChangeListener {

  /**
   * Instantiates a new ribbon drop down button animation.
   *
   * @param button the button
   */
  public RibbonDropDownButtonAnimation(ModernWidget button) {
    super(button);

    ((ModernDropDownWidget) button).addPopupClosedListener(this);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.animation.HoverFadeAnimation#animateMouseExited()
   */
  @Override
  public void animateMouseExited() {
    // If the popup is show, force the animation to display the button
    // by making it opaque and stopping the timer
    if (getButton().getPopupShown()) {
      //opaque();
      stop();
    } else {
      super.animateMouseExited();
    }
  }

  @Override
  public void changed(ChangeEvent e) {
    pseudoMouseExited();
  }
}
