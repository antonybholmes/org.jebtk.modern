package org.jebtk.modern.scrollpane;

import org.jebtk.core.event.EventProducer;

/**
 * Listen for scroll events and forward to listeners.
 * 
 * @author Antony Holmes
 *
 */
public class ScrollListeners extends EventProducer<ScrollListener> implements ScrollEventProducer {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernSelectedEventProducer#
   * addSelectedListener( org.abh.lib.ui.modern.event.ModernSelectedListener)
   */
  @Override
  public void addScrollListener(ScrollListener l) {
    mListeners.add(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernSelectedEventProducer#
   * removeSelectedListener(org.abh.lib.ui.modern.event.ModernSelectedListener)
   */
  @Override
  public void removeScrollListener(ScrollListener l) {
    mListeners.remove(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernSelectedEventProducer#
   * fireSelectedChanged( org.abh.lib.ui.modern.event.ModernSelectedEvent)
   */
  @Override
  public void fireScrollTo(ScrollEvent e) {
    for (ScrollListener l : mListeners) {
      l.scrollTo(e);
    }
  }

}