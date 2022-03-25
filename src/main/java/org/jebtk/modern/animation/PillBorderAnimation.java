package org.jebtk.modern.animation;

import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.button.PillOutlineAnimation;

public class PillBorderAnimation extends PillOutlineAnimation {
  public PillBorderAnimation(ModernWidget widget) {
    super(widget);

    bindChildren();
  }
}
