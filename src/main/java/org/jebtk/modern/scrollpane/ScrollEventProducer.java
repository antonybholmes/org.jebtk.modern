package org.jebtk.modern.scrollpane;

/**
 * For classes that generate scroll events.
 *
 * @author Antony Holmes
 */
public interface ScrollEventProducer {

  /**
   * Add a scroll listener.
   *
   * @param l the listener.
   */
  public void addScrollListener(ScrollListener l);

  /**
   * Remove a scroll listener.
   *
   * @param l the listener.
   */
  public void removeScrollListener(ScrollListener l);

  /**
   * Fire a scroll to event.
   *
   * @param event the event
   */
  public void fireScrollTo(ScrollEvent event);
}
