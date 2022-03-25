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

import java.awt.Component;
import java.awt.event.MouseEvent;

import org.jebtk.core.geom.IntPos2D;

/**
 * The class CanvasMouseEvent.
 */
public class CanvasMouseEvent extends MouseEvent {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member p.
   */
  private IntPos2D mP;

  /**
   * Instantiates a new canvas mouse event.
   *
   * @param e the e
   * @param p the p
   */
  public CanvasMouseEvent(MouseEvent e, IntPos2D p) {
    this(e.getComponent(), e.getID(), e.getWhen(), e.getModifiers(), e.getX(), e.getY(), p, e.getClickCount(),
        e.isPopupTrigger());
  }

  /**
   * Instantiates a new canvas mouse event.
   *
   * @param source the source
   * @param e      the e
   * @param p      the p
   */
  public CanvasMouseEvent(Component source, MouseEvent e, IntPos2D p) {
    this(source, e.getID(), e.getWhen(), e.getModifiers(), e.getX(), e.getY(), p, e.getClickCount(),
        e.isPopupTrigger());
  }

  /**
   * Instantiates a new canvas mouse event.
   *
   * @param source       the source
   * @param id           the id
   * @param when         the when
   * @param modifiers    the modifiers
   * @param x            The x position of the mouse regardless of canvas
   *                     modifications
   * @param y            the y
   * @param p            The possibly adjusted p indicating where on the canvas
   *                     the mouse event was generated. In the case of a zoom
   *                     canvas, this will be the coordinate adjusted for the
   *                     zoom.
   * @param clickCount   the click count
   * @param popupTrigger the popup trigger
   */
  public CanvasMouseEvent(Component source, int id, long when, int modifiers, int x, int y, IntPos2D p, int clickCount,
      boolean popupTrigger) {
    super(source, id, when, modifiers, x, y, clickCount, popupTrigger);

    mP = p;
  }

  /**
   * Returns the point associated with the canvas rather than where the mouse was
   * pressed. Often this will be the same as the mouse coordinate but for example
   * in a translated canvas or zoomed canvas the coordinates will be different to
   * reflect the transformations. For example with a zoom, this position would
   * represent the coordinate on the unscaled image rather than the actual
   * position of the mouse etc.
   *
   * @return the adjusted p
   */
  public IntPos2D getScaledPos() {
    return mP;
  }

}
