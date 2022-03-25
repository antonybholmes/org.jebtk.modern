package org.jebtk.modern.button;

import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.css.CSSHoverAnimation;

public class ButtonCSSAnimation extends CSSHoverAnimation {
  private final ModernClickWidget mButton;

  public ButtonCSSAnimation(ModernWidget button) {
    super(button);

    mButton = (ModernClickWidget) button;
  }

  @Override
  public String getName() {
    return "button-fill";
  }

  public ModernClickWidget getButton() {
    return mButton;
  }
}