package org.jebtk.modern;

import javax.swing.event.AncestorEvent;

public abstract class SingleUseAncestorAddedListener extends AncestorAdapter {

  private boolean mFirst = true;

  @Override
  public void ancestorAdded(AncestorEvent event) {
    if (mFirst) {
      mFirst = false;

      action(event);
    }
  }

  public abstract void action(AncestorEvent event);
}
