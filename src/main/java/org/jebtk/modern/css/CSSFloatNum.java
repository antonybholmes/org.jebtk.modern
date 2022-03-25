package org.jebtk.modern.css;

public abstract class CSSFloatNum extends CSSNum {

  protected final double mV;

  public CSSFloatNum(double v) {
    mV = v;
  }

  @Override
  public int getInt() {
    return (int) mV;
  }

  @Override
  public double getFloat() {
    return mV;
  }

  @Override
  public String toString() {
    return Double.toString(mV);
  }
}
