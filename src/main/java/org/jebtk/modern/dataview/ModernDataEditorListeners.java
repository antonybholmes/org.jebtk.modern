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
import org.jebtk.core.event.EventListeners;
import org.jebtk.core.event.EventProducer;

/**
 * Provides the ability to register and unregister ModernClickEventListeners for
 * controls and provides standard functions to interface with {
 * EventListenerList by taking care of casting etc.
 *
 * @author Antony Holmes
 *
 */
public class ModernDataEditorListeners extends EventProducer<ModernDataEditorListener>
    implements ModernDataEditorEventProducer {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataEditorEventProducer#
   * addEditorListener(org.abh.lib.ui.modern.dataview.ModernDataEditorListener)
   */
  public void addEditorListener(ModernDataEditorListener l) {
    mListeners.add(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataEditorEventProducer#
   * removeEditorListener(org.abh.lib.ui.modern.dataview.
   * ModernDataEditorListener)
   */
  public void removeEditorListener(ModernDataEditorListener l) {
    mListeners.remove(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.dataview.ModernDataEditorEventProducer#fireChanged(
   * org. abh.lib.event.ChangeEvent)
   */
  public void fireChanged(ChangeEvent e) {
    for (ModernDataEditorListener l : mListeners) {
      l.changed(e);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataEditorEventProducer#
   * fireEditingStopped()
   */
  public void fireEditingStopped() {
    // We use a copy of the events so that mListeners can be updated
    // if editing stopped triggers this to be updated (e.g. removing
    // or adding a listener). This is a fix for table controls where
    // stopping editing usually triggers the cell below to be selected
    // so that gets the focus and the editor list is adjusted for the
    // new editor. We generally dont care if new editors are being
    // added after editing stopped, only that the current list is
    // notified.
    for (ModernDataEditorListener l : new EventListeners<ModernDataEditorListener>(mListeners)) {
      // System.err.println("hmm " + l.toString());

      l.editingStopped();
    }
  }
}
