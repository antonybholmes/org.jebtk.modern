package org.jebtk.modern.css;

import java.awt.Font;

public class CSSFontProp extends CSSProp<Font> {

  protected final Font mV;

  public CSSFontProp(Font v) {
    mV = v;
  }

  @Override
  public Font getKeyFrame(int frame) {
    return mV;
  }
}
