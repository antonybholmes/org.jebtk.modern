package org.jebtk.modern.css;

public class CSS2Int extends CSS2Num {

  protected final int mV;

  public CSS2Int(int v, CSS2NumType type) {
    super(type);
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
