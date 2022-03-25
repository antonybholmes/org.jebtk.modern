package org.jebtk.modern.css;

public class CSS2Double extends CSS2Num {

  protected final double mV;

  public CSS2Double(double v, CSS2NumType type) {
    super(type);
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
