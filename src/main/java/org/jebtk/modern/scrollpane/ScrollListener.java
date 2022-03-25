package org.jebtk.modern.scrollpane;

import java.util.EventListener;

/**
 * Listen for scroll events
 * 
 * @author Antony Holmes
 *
 */
public interface ScrollListener extends EventListener {

  /**
   * Should respond to a scroll event.
   *
   * @param e the e
   */
  public void scrollTo(ScrollEvent e);
}
