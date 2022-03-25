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

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.geom.IntPos2D;
import org.jebtk.core.geom.IntRect;

/**
 * Allows a canvas to be stored and manipulated within another. For example a
 * zooming canvas might scale the draw canvas before presenting the image to the
 * UI.
 * 
 * @author Antony Holmes
 *
 */
public class ContainerCanvas extends ModernCanvas implements CanvasListener, CanvasCursorListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member canvas.
   */
  protected ModernCanvas mCanvas;

  /**
   * Instantiates a new container canvas.
   *
   * @param canvas the canvas
   */
  public ContainerCanvas(ModernCanvas canvas) {
    mCanvas = canvas;

    canvas.addCanvasListener(this);
    canvas.addCanvasCursorListener(this);

    // Allow the underlying canvas to respond to
    // mouse events from this canvas
    addCanvasMouseListener(canvas);
    addCanvasKeyListener(canvas);

    addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        mCanvas.setSize(getSize());
      }
    });
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ContainerCanvas#getCanvasSize()
   */
  // @Override
  // public IntDim getCanvasSize() {
  /// return IntDim.create(getPreferredSize());
  // }

  @Override
  public void setPreferredSize(Dimension d) {
    mCanvas.setPreferredSize(d);
  }

  @Override
  public Dimension getPreferredSize() {
    return mCanvas.getPreferredSize();
  }

  // @Override
  // public void setViewRectangle(IntRect rect) {
  // super.setViewRectangle(rect);
  //
  // mCanvas.setViewRectangle(rect);
  // }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvas#rasterCanvas(java.awt.
   * Graphics2D)
   */
  @Override
  public void rasterCanvas(Graphics2D g2, DrawingContext context) {
    mCanvas.rasterCanvas(g2, context);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.graphics.ModernCanvasListener#canvasChanged(org.abh.
   * lib .event.ChangeEvent)
   */
  @Override
  public void canvasChanged(ChangeEvent e) {
    // Forward child events to parent
    fireCanvasChanged();
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
    // Forward child events to parent
    fireCanvasRedraw();
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
    // Forward child events to parent
    fireCanvasScrolled();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.graphics.ModernCanvasListener#canvasResized(org.abh.
   * common. event.ChangeEvent)
   */
  @Override
  public void canvasResized(ChangeEvent e) {
    // Forward child events to parent
    fireCanvasResized();
  }

  // @Override
  // public void setViewRectangle(IntRect rect) {
  // mCanvas.setViewRectangle(rect);
  // }

  @Override
  public void setBounds(int x, int y, int width, int height) {
    mCanvas.setBounds(x, y, width, height);
  }

  @Override
  public Rectangle getBounds() {
    return mCanvas.getBounds();
  }

  @Override
  public void scrollRectToVisible(Rectangle rect) {
    mCanvas.scrollRectToVisible(rect);
  }

  @Override
  public Rectangle getVisibleRect() {
    return mCanvas.getVisibleRect();
  }

  @Override
  public void computeVisibleRect(Rectangle visibleRect) {
    mCanvas.computeVisibleRect(visibleRect);
  }

  @Override
  public void setAutoscrolls(boolean autoscrolls) {
    mCanvas.setAutoscrolls(autoscrolls);
  }

  @Override
  public boolean getAutoscrolls() {
    return mCanvas.getAutoscrolls();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.graphics.ModernCanvas#updateViewRectangle(org.abh.common.
   * geom.IntRect)
   */
  @Override
  public void updateViewRectangle(IntRect rect) {
    mCanvas.updateViewRectangle(rect);
    // super.updateViewRectangle(rect);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.graphics.ModernCanvas#getViewRect()
   */
  @Override
  public IntRect getViewRect() {
    return mCanvas.getViewRect();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.graphics.ModernCanvas#getIntViewRectangle()
   */
  @Override
  public IntRect getIntViewRect() {
    return mCanvas.getIntViewRect();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.graphics.CanvasCursorListener#cursorChanged(org.abh.
   * common. ui.graphics.CanvasCursorEvent)
   */
  @Override
  public void cursorChanged(CanvasCursorEvent e) {
    setCursor(e.getCursor());
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.graphics.ModernCanvas#translateCoordinate(int, int)
   */
  @Override
  public IntPos2D translateCoordinate(int x, int y) {
    return mCanvas.translateCoordinate(x, y);
  }
}
