package org.jebtk.modern.css;

public class CSSNumPercent extends CSSIntNum {
  public CSSNumPercent(int v) {
    super(v);
  }

  @Override
  public String toString() {
    return mV + "%";
  }

  @Override
  public CSSNumType getNumType() {
    return CSSNumType.PERCENT;
  }
}
