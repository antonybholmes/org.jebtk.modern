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
package org.jebtk.modern.button;

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.event.ChangeEventProducer;
import org.jebtk.core.event.ChangeListener;
import org.jebtk.core.event.ChangeListeners;

/**
 * For widgets that change values.
 * 
 * @author Antony Holmes
 *
 */
public abstract class ModernChangeWidget extends ModernClickWidget implements ChangeEventProducer {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member listeners.
   */
  private ChangeListeners mListeners = new ChangeListeners();

  /**
   * Fire item changed.
   */
  protected void fireItemChanged() {
    fireChanged(new ChangeEvent(this));
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.event.ChangeEventProducer#addChangeListener(org.abh.lib.event.
   * ChangeListener)
   */
  @Override
  public void addChangeListener(ChangeListener l) {
    mListeners.addChangeListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.event.ChangeEventProducer#removeChangeListener(org.abh.lib.
   * event. ChangeListener)
   */
  @Override
  public void removeChangeListener(ChangeListener l) {
    mListeners.removeChangeListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.event.ChangeEventProducer#fireChanged(org.abh.lib.event.
   * ChangeEvent)
   */
  @Override
  public void fireChanged(ChangeEvent event) {
    mListeners.fireChanged(event);
  }
}