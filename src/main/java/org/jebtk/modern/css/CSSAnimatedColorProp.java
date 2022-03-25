package org.jebtk.modern.css;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jebtk.core.CSSColor;

public class CSSAnimatedColorProp extends CSSBaseColorProp {
  protected final List<CSSColor> mColors = new ArrayList<>();
  
  public CSSAnimatedColorProp(Color v) {
    mColors.add(new CSSColor(v));
  }
  
  public CSSAnimatedColorProp(CSSColor v) {
    mColors.add(v);
  }
  
  public CSSAnimatedColorProp(CSSColor ...colors) {
    mColors.addAll(Arrays.asList(colors));
  }

  @Override
  public CSSColor getKeyFrame(int frame) {
    return mColors.get(Math.max(0, Math.min(mColors.size(), frame)));
  }
  
  @Override
  public String toString() {
    return "CSSAnimatedColorProp:" + mColors;
  }
}
