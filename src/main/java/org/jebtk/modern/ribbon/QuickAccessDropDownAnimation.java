package org.jebtk.modern.ribbon;

import org.jebtk.modern.button.ButtonCSSAnimation;
import org.jebtk.modern.button.ModernDropDownWidget;

public class QuickAccessDropDownAnimation extends ButtonCSSAnimation {
  public QuickAccessDropDownAnimation(ModernDropDownWidget button) {
    super(button);
  }

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
}
