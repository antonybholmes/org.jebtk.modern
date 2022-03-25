package org.jebtk.modern.scrollpane;

import org.jebtk.core.event.Event;
import org.jebtk.core.geom.IntRect;

/**
 * Modern UI controls such as buttons should fire ModernClickEvents.
 * 
 * @author Antony Holmes
 *
 */
public class ScrollEvent extends Event {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant EVENT_STATE_CHANGED.
   */
  public static final String EVENT = "scroll";

  public final IntRect mRect;

  /**
   * Instantiates a new modern state event.
   *
   * @param source  the source
   * @param message the message
   */
  public ScrollEvent(Object source, IntRect rect) {
    super(source, EVENT);

    mRect = rect;
  }

  public IntRect getRect() {
    return mRect;
  }
}
