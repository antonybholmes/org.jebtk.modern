package org.jebtk.modern;

import javax.swing.event.AncestorEvent;

public abstract class SingleUseAncestorMovedListener extends AncestorAdapter {

  private boolean mFirst = true;

  @Override
  public void ancestorMoved(AncestorEvent event) {
    if (mFirst) {
      mFirst = false;

      System.err.println("moved firsty");
      action(event);
    }
  }

  public abstract void action(AncestorEvent event);
}
