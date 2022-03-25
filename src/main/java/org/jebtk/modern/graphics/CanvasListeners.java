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
package org.jebtk.modern.graphics;

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.event.EventProducer;

/**
 * The basis for model controls in a model view controller setup.
 * 
 * @author Antony Holmes
 *
 */
public class CanvasListeners extends EventProducer<CanvasListener> implements CanvasEventProducer {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.graphics.ModernCanvasEventProducer#addCanvasListener(
   * org.abh.lib.ui.modern.graphics.ModernCanvasListener)
   */
  @Override
  public void addCanvasListener(CanvasListener l) {
    // System.err.println("mcl add " + this + " " + l);

    mListeners.add(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvasEventProducer#
   * removeCanvasListener (org.abh.lib.ui.modern.graphics.ModernCanvasListener)
   */
  @Override
  public void removeCanvasListener(CanvasListener l) {
    mListeners.remove(l);
  }

  /**
   * Fire canvas changed.
   */
  public void fireCanvasChanged() {
    fireCanvasChanged(new ChangeEvent(this));
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.graphics.ModernCanvasEventProducer#fireCanvasChanged(
   * org.abh.lib.event.ChangeEvent)
   */
  @Override
  public void fireCanvasChanged(ChangeEvent e) {
    for (CanvasListener l : mListeners) {
      l.canvasChanged(e);
    }
  }

  /**
   * Fire canvas redraw.
   */
  public void fireCanvasRedraw() {
    fireCanvasRedraw(new ChangeEvent(this));
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.graphics.ModernCanvasEventProducer#fireCanvasRedraw(
   * org .abh.lib.event.ChangeEvent)
   */
  @Override
  public void fireCanvasRedraw(ChangeEvent e) {
    for (CanvasListener l : mListeners) {
      l.redrawCanvas(e);
    }
  }

  /**
   * Fire canvas scrolled.
   */
  public void fireCanvasScrolled() {
    fireCanvasScrolled(new ChangeEvent(this));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvasEventProducer#
   * fireCanvasScrolled( org.abh.lib.event.ChangeEvent)
   */
  @Override
  public void fireCanvasScrolled(ChangeEvent e) {
    for (CanvasListener l : mListeners) {
      l.canvasScrolled(e);
    }
  }

  /**
   * Fire canvas resized.
   */
  public void fireCanvasResized() {
    fireCanvasResized(new ChangeEvent(this));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvasEventProducer#
   * fireCanvasScrolled( org.abh.lib.event.ChangeEvent)
   */
  @Override
  public void fireCanvasResized(ChangeEvent e) {
    for (CanvasListener l : mListeners) {
      l.canvasResized(e);
    }
  }
}