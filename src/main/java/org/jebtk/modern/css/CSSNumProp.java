package org.jebtk.modern.css;

public class CSSNumProp extends CSSProp<CSSNum> {

  protected final CSSNum mV;

  public CSSNumProp(int v) {
    mV = new CSSPXNum(v);
  }
  
  public CSSNumProp(CSSNum v) {
    mV = v;
  }

  @Override
  public CSSNum getKeyFrame(int frame) {
    return mV;
  }
}
