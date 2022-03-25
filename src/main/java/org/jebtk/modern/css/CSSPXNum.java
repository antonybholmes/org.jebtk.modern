package org.jebtk.modern.css;

public class CSSPXNum extends CSSIntNum {
  public static CSSNum ZERO = new CSSPXNum(0);
  public static CSSNum MIN = new CSSPXNum(Integer.MIN_VALUE);

  public CSSPXNum(int v) {
    super(v);
  }
  
   @Override
  public String toString() {
    return mV + "px";
  }

  @Override
  public CSSNumType getNumType() {
    return CSSNumType.PX;
  }
}
