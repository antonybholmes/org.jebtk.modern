package org.jebtk.modern.css;

import java.awt.Color;
import org.jebtk.core.CSSColor;
import org.jebtk.core.ColorUtils;

public class CSSColorProp extends CSSBaseColorProp {
  public static final CSSColorProp WHITE = new CSSColorProp(CSSColor.CSS_WHITE);
  public static final CSSColorProp TRANS = new CSSColorProp(ColorUtils.TRANS);
  private final CSSColor mColor;


  public CSSColorProp(Color v) {
    mColor = new CSSColor(v);
  }
  
  public CSSColorProp(CSSColor v) {
    mColor = v;
  }

  @Override
  public CSSColor getKeyFrame(int frame) {
    return mColor;
  }
  
  @Override
  public String toString() {
    return "CSSColorProp:" + mColor;
  }
}
