package org.jebtk.modern.collapsepane;

import java.awt.Component;
import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.core.event.ChangeEventProducer;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.ChangeAnimation;

public class CompResizeAnimation extends ChangeAnimation {

  protected static final int STEPS = 3;
  protected int[] mSizes = new int[STEPS];
  private Component mC;
  private int mDir = 1;

  public <T extends ModernWidget & ChangeEventProducer> CompResizeAnimation(T widget) {
    super(widget, 50);
  }

  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {
    // Do nothing
  }

  public void restart(Component c, int min, int max, boolean fdir) {
    stop();

    mC = c;

    mDir = fdir ? 1 : -1;

    int inc = (max - min) / (STEPS - 1);
    mSizes[0] = min;
    mSizes[mSizes.length - 1] = max;

    for (int i = 1; i < mSizes.length - 1; ++i) {
      mSizes[i] = mSizes[i - 1] + inc;
    }

    start();
  }

  @Override
  public void animate(int frame) {
    if (end()) {
      stop();
    } else {
      animateExpand();
    }
  }

  public int getDim() {
    return mSizes[getFrame()];
  }

  public void animateExpand() {
    // TODO Auto-generated method stub

  }

  public boolean end() {
    return (mDir == 1 && getFrame() == STEPS) || (mDir == -1 && getFrame() == -1);
  }

  public Component getC() {
    return mC;
  }
}
