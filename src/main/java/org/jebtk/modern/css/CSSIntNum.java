package org.jebtk.modern.css;

public abstract class CSSIntNum extends CSSNum {
  protected final int mV;

  public CSSIntNum(int v) {
    mV = v;
  }

  @Override
  public int getInt() {
    return mV;
  }

  @Override
  public double getFloat() {
    return mV;
  }

  @Override
  public String toString() {
    return Integer.toString(mV);
  }
}
