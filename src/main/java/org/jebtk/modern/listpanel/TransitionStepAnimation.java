package org.jebtk.modern.listpanel;

import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.AnimationTimer;
import org.jebtk.modern.animation.TimerAnimation;
import org.jebtk.modern.animation.TranslateAnimation;

public abstract class TransitionStepAnimation extends TimerAnimation {
  /** The m pos. */
  private double[] mPos = new double[AnimationTimer.STEPS];

  public TransitionStepAnimation(ModernWidget w) {
    super(w);
  }

  public void start(double y1, double y2) {
    setup(y1, y2);

    start();
  }

  /**
   * Instantiates a new animate movement.
   *
   * @param index the index
   * @param y2    the y 2
   */
  public void setup(double y1, double y2) {
    double mD = y2 - y1;

    mPos[0] = y1;
    mPos[mPos.length - 1] = y2;

    for (int i = 1; i < AnimationTimer.MAX_STEP_INDEX; ++i) {
      mPos[i] = y1 + TranslateAnimation.BEZ_T[i] * mD;
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public synchronized void animate(int frame) {
    animate(frame, mPos[frame]);
  }

  public abstract void animate(int step, double x);

  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {
    // Do nothing
  }
}
