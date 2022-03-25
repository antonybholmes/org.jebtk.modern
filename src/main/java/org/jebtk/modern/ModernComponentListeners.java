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
package org.jebtk.modern;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import org.jebtk.core.event.EventProducer;

/**
 * The basis for model controls in a model view controller setup.
 * 
 * @author Antony Holmes
 *
 */
public class ModernComponentListeners extends EventProducer<ComponentListener> implements ModernComponentEventProducer {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernComponentEventProducer#addComponentListener(
   * java. awt.event.ComponentListener)
   */
  @Override
  public void addComponentListener(ComponentListener l) {
    mListeners.add(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.ModernComponentEventProducer#removeComponentListener(
   * java.awt.event.ComponentListener)
   */
  @Override
  public void removeComponentListener(ComponentListener l) {
    mListeners.remove(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernComponentEventProducer#fireComponentHidden(
   * java. awt.event.ComponentEvent)
   */
  @Override
  public void fireComponentHidden(ComponentEvent e) {
    for (ComponentListener l : mListeners) {
      l.componentHidden(e);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.ModernComponentEventProducer#fireComponentMoved(java.
   * awt.event.ComponentEvent)
   */
  @Override
  public void fireComponentMoved(ComponentEvent e) {
    for (ComponentListener l : mListeners) {
      l.componentMoved(e);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernComponentEventProducer#fireComponentResized(
   * java. awt.event.ComponentEvent)
   */
  @Override
  public void fireComponentResized(ComponentEvent e) {
    for (ComponentListener l : mListeners) {
      l.componentResized(e);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.ModernComponentEventProducer#fireComponentShown(java.
   * awt.event.ComponentEvent)
   */
  @Override
  public void fireComponentShown(ComponentEvent e) {
    for (ComponentListener l : mListeners) {
      l.componentShown(e);
    }
  }
}