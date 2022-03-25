package org.jebtk.modern.css;

public abstract class CSS2Num {

  private final CSS2NumType mType;
  
  public CSS2Num(CSS2NumType type) {
    mType = type;
  }

  public int getInt() {
    return Integer.MIN_VALUE;
  }

  public double getFloat() {
    return Double.MIN_VALUE;
  }

  public CSS2NumType getType() {
    return mType;
  }
}
