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
package org.jebtk.modern.dataview;

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.event.EventProducer;

/**
 * Provides the ability to register and unregister ModernClickEventListeners for
 * controls and provides standard functions to interface with {
 * EventListenerList by taking care of casting etc.
 *
 * @author Antony Holmes
 *
 */
public class ModernDataViewListeners extends EventProducer<ModernDataViewListener>
    implements ModernDataViewEventProducer {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant DATA_CHANGED.
   */
  public static final String DATA_CHANGED = "data_changed";

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataViewEventProducer#
   * addDataViewListener(org.abh.lib.ui.modern.dataview.ModernDataViewListener)
   */
  public void addDataViewListener(ModernDataViewListener l) {
    mListeners.add(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataViewEventProducer#
   * removeDataViewListener(org.abh.lib.ui.modern.dataview.
   * ModernDataViewListener)
   */
  public void removeDataViewListener(ModernDataViewListener l) {
    mListeners.remove(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.dataview.ModernDataViewEventProducer#fireDataChanged(
   * org.abh.lib.event.ChangeEvent)
   */
  public void fireDataChanged(ChangeEvent e) {
    for (ModernDataViewListener l : mListeners) {
      l.dataChanged(e);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.dataview.ModernDataViewEventProducer#fireDataUpdated(
   * org.abh.lib.event.ChangeEvent)
   */
  public void fireDataUpdated(ChangeEvent e) {
    for (ModernDataViewListener l : mListeners) {
      l.dataUpdated(e);
    }
  }
}
