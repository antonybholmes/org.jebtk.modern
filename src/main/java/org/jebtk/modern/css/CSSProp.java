package org.jebtk.modern.css;

public abstract class CSSProp<T> {
  public static final int FROM = 0;
  public static final int TO = 7;
  
  public abstract T getKeyFrame(int frame);
  
  public T getFromKeyFrame() {
    return getKeyFrame(FROM);
  }

  /**
   * Returns the last key frame in the animation series.
   * 
   * @return
   */
  public T getToKeyFrame() {
    return getKeyFrame(TO);
  }
}
