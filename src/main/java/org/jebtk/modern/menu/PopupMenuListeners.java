/**
 * Copyright (C) 2016, Antony Holmes
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *  1. Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *  3. Neither the name of copyright holder nor the names of its contributors 
 *     may be used to endorse or promote products derived from this software 
 *     without specific prior written permission. 
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 */
package org.jebtk.modern.menu;

import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import org.jebtk.core.event.EventListeners;

/**
 * Provides the ability to register and unregister ModernClickEventListeners for
 * controls and provides standard functions to interface with {
 * EventListenerList by taking care of casting etc.
 *
 * @author Antony Holmes
 *
 */
public class PopupMenuListeners {

  /**
   * The listeners.
   */
  protected EventListeners<PopupMenuListener> listeners = new EventListeners<PopupMenuListener>();

  /**
   * Adds the.
   *
   * @param l the l
   */
  public void add(PopupMenuListener l) {
    listeners.add(l);
  }

  /**
   * Removes the.
   *
   * @param l the l
   */
  public void remove(PopupMenuListener l) {
    listeners.remove(l);
  }

  /**
   * fires the event to any registered listeners.
   *
   * @param event the event
   */
  public final void fireMenuCanceledEvent(PopupMenuEvent event) {
    for (PopupMenuListener l : listeners) {
      l.popupMenuCanceled(event);
    }
  }

  /**
   * fires the event to any registered listeners.
   *
   * @param event the event
   */
  public final void fireMenuWillBecomeInvisibleEvent(PopupMenuEvent event) {
    for (PopupMenuListener l : listeners) {
      l.popupMenuWillBecomeInvisible(event);
    }
  }

  /**
   * fires the event to any registered listeners.
   *
   * @param event the event
   */
  public final void fireMenuWillBecomeVisibleEvent(PopupMenuEvent event) {
    for (PopupMenuListener l : listeners) {
      l.popupMenuWillBecomeVisible(event);
    }
  }
}
