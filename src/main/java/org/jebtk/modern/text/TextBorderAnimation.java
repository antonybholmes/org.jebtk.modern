package org.jebtk.modern.text;

import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.css.CSSHoverAnimation;

public class TextBorderAnimation extends CSSHoverAnimation {
  public TextBorderAnimation(ModernWidget widget) {
    super(widget);

    bindChildren();
  }
}
