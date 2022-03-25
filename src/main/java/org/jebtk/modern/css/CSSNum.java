package org.jebtk.modern.css;

public abstract class CSSNum {
  public int getInt() {
    return Integer.MIN_VALUE;
  }

  public double getFloat() {
    return getInt();
  }
  
  public abstract CSSNumType getNumType();
}
