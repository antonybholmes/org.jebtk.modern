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

import org.jebtk.core.event.EventProducer;

/**
 * The basis for model controls in a model view controller setup.
 * 
 * @author Antony Holmes
 *
 */
public class CanvasMouseListeners extends EventProducer<CanvasMouseListener> implements CanvasMouseEventProducer {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvasMouseEventProducer#
   * addCanvasMouseListener(org.abh.lib.ui.modern.graphics.
   * ModernCanvasMouseListener)
   */
  public void addCanvasMouseListener(CanvasMouseListener l) {
    mListeners.add(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvasMouseEventProducer#
   * removeCanvasMouseListener(org.abh.lib.ui.modern.graphics.
   * ModernCanvasMouseListener)
   */
  public void removeCanvasMouseListener(CanvasMouseListener l) {
    mListeners.remove(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvasMouseEventProducer#
   * fireCanvasMouseClicked(org.abh.lib.ui.modern.graphics.CanvasMouseEvent)
   */
  @Override
  public void fireCanvasMouseClicked(CanvasMouseEvent e) {
    for (CanvasMouseListener l : mListeners) {
      l.canvasMouseClicked(e);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvasMouseEventProducer#
   * fireCanvasMouseMoved(org.abh.lib.ui.modern.graphics.CanvasMouseEvent)
   */
  @Override
  public void fireCanvasMouseMoved(CanvasMouseEvent e) {
    for (CanvasMouseListener l : mListeners) {
      l.canvasMouseMoved(e);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvasMouseEventProducer#
   * fireCanvasMouseDragged(org.abh.lib.ui.modern.graphics.CanvasMouseEvent)
   */
  @Override
  public void fireCanvasMouseDragged(CanvasMouseEvent e) {
    for (CanvasMouseListener l : mListeners) {
      l.canvasMouseDragged(e);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvasMouseEventProducer#
   * fireCanvasMousePressed(org.abh.lib.ui.modern.graphics.CanvasMouseEvent)
   */
  @Override
  public void fireCanvasMousePressed(CanvasMouseEvent e) {
    for (CanvasMouseListener l : mListeners) {
      l.canvasMousePressed(e);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvasMouseEventProducer#
   * fireCanvasMouseReleased(org.abh.lib.ui.modern.graphics.CanvasMouseEvent)
   */
  @Override
  public void fireCanvasMouseReleased(CanvasMouseEvent e) {
    for (CanvasMouseListener l : mListeners) {
      l.canvasMouseReleased(e);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvasMouseEventProducer#
   * fireCanvasMouseEntered(org.abh.lib.ui.modern.graphics.CanvasMouseEvent)
   */
  @Override
  public void fireCanvasMouseEntered(CanvasMouseEvent e) {
    for (CanvasMouseListener l : mListeners) {
      l.canvasMouseEntered(e);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvasMouseEventProducer#
   * fireCanvasMouseExited(org.abh.lib.ui.modern.graphics.CanvasMouseEvent)
   */
  @Override
  public void fireCanvasMouseExited(CanvasMouseEvent e) {
    for (CanvasMouseListener l : mListeners) {
      l.canvasMouseExited(e);
    }
  }
}