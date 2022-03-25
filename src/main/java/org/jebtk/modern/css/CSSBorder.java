package org.jebtk.modern.css;

public abstract class CSSBorder extends CSSNum {

  

  protected final int mV;

  public CSSBorder(int v) {
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
