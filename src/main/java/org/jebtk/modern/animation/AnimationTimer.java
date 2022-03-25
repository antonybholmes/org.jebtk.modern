package org.jebtk.modern.animation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import org.jebtk.core.Mathematics;

public class AnimationTimer extends javax.swing.Timer {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public static final int DELAY_MS = 30;
  public static final int STEPS = 8;
  public static final int MAX_STEP_INDEX = STEPS - 1;

  private static class AnimationTimerEvents implements ActionListener {
    private int mCounter = 0;
    private final IAnimationListener mL;
    private final int mSteps;
    private final int mMaxIndex;

    public AnimationTimerEvents(IAnimationListener l, int steps) {
      mL = l;
      mSteps = steps;
      mMaxIndex = mSteps - 1;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      System.err.println("timer:" + mCounter);
      mL.actionPerformed(e, mCounter);

      // Auto stop after mSteps
      if (mCounter == mMaxIndex) {
        ((Timer) e.getSource()).stop();
        // mCounter = 0;
      } else {
        // ++mCounter;

        setCounter(mCounter + 1);
      }
    }

    public void reset() {
      setCounter(0);
    }

    public synchronized void setCounter(int counter) {
      mCounter = Mathematics.bound(counter, 0, mMaxIndex);
    }

    public int getCounter() {
      return mCounter;
    }
  }

  private AnimationTimerEvents mE;

  public AnimationTimer(IAnimationListener l) {
    this(l, STEPS);
  }

  public AnimationTimer(IAnimationListener l, int steps) {
    this(new AnimationTimerEvents(l, steps));
  }
  
  public AnimationTimer(int delay, IAnimationListener l) {
    this(delay, l, STEPS);
  }

  public AnimationTimer(int delay, IAnimationListener l, int steps) {
    this(delay, new AnimationTimerEvents(l, steps));
  }


  public AnimationTimer(AnimationTimerEvents e) {
    this(DELAY_MS, e);
  }
  
  public AnimationTimer(int delay, AnimationTimerEvents e) {
    super(delay, e);
    mE = e;
  }

  public void reset() {
    setCounter(0);
  }
  
  //@Override
  //public void stop() {
  //  super.stop();
  //  reset();
 // }

  public void setCounter(int counter) {
    mE.setCounter(counter);
  }

  public int getCounter() {
    return mE.getCounter();
  }

//  @Override
//  public void start() {
//    super.start();
//  }

  @Override
  public void restart() {
    reset();
    start();
  }
}
