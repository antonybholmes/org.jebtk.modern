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

/**
 * The class ModernCanvasAdapter.
 */
public class CanvasAdapter implements CanvasListener {

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.graphics.ModernCanvasListener#canvasChanged(org.abh.
   * lib .event.ChangeEvent)
   */
  @Override
  public void canvasChanged(ChangeEvent e) {
    // Do nothing
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.graphics.ModernCanvasListener#redrawCanvas(org.abh.
   * lib. event.ChangeEvent)
   */
  @Override
  public void redrawCanvas(ChangeEvent e) {
    // Do nothing
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.graphics.ModernCanvasListener#canvasScrolled(org.abh.
   * lib.event.ChangeEvent)
   */
  @Override
  public void canvasScrolled(ChangeEvent e) {
    // Do nothing
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.graphics.ModernCanvasListener#canvasResized(org.abh.
   * common. event.ChangeEvent)
   */
  @Override
  public void canvasResized(ChangeEvent e) {
    // TODO Auto-generated method stub

  }
}
