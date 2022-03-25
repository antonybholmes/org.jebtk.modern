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

import java.util.EventListener;

import org.jebtk.core.event.ChangeEvent;

// TODO: Auto-generated Javadoc
/**
 * Notifies listeners that the canvas has changed (e.g. size) so should be
 * redrawn or the container control adjusted accordingly.
 * 
 * @author Antony Holmes
 *
 */
public interface CanvasListener extends EventListener {

  /**
   * Notify parents to revalidate as the canvas has changed. This implies the
   * whole underlying structure and or size has changed so it should be completely
   * re-rendered.
   *
   * @param e the e
   */
  public void canvasChanged(ChangeEvent e);

  /**
   * When the canvas has repainted but the content has not changed. This is used
   * in a canvas hierarchy to notify parents to repaint the children. It is
   * designed to be less intensive than canvasChanged which will cause
   * revalidation of components.
   *
   * @param e the e
   */
  public void redrawCanvas(ChangeEvent e);

  /**
   * Notify listeners that they should ensure the scroll point is visible.
   *
   * @param e the e
   */
  public void canvasScrolled(ChangeEvent e);

  /**
   * Notify the parents that the canvas has changed size, but the content is the
   * same. This is designed to be less intensive than canvas changed.
   *
   * @param e the e
   */
  public void canvasResized(ChangeEvent e);
}
