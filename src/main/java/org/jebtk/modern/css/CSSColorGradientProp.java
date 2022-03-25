package org.jebtk.modern.css;

import org.jebtk.modern.theme.ColorGradient;
import org.jebtk.modern.theme.LinearGradient;

public class CSSColorGradientProp extends CSSProp<ColorGradient> {

  static CSSColorGradientProp BLACK_WHITE = new CSSColorGradientProp(LinearGradient.BLACK_WHITE);

  protected final ColorGradient mV;

  public CSSColorGradientProp(ColorGradient v) {
    mV = v;
  }

  @Override
  public ColorGradient getKeyFrame(int frame) {
    return mV;
  }
}
