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
import java.awt.image.BufferedImage;

import org.jebtk.core.event.ChangeEvent;

/**
 * Render child canvas as an image to improve display performance on the GUI of
 * complex shapes.
 * 
 * @author Antony Holmes
 *
 */
public class ImageCanvas extends ModernCanvas {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The image.
   */
  private BufferedImage image;

  /**
   * The member canvas.
   */
  private ModernCanvas mCanvas;

  /**
   * The class CanvasEvents.
   */
  private class CanvasEvents extends CanvasAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.abh.lib.ui.modern.graphics.ModernCanvasAdapter#canvasChanged(org.abh.
     * lib. event.ChangeEvent)
     */
    @Override
    public void canvasChanged(ChangeEvent e) {
      generate();
    }
  }

  /**
   * Instantiates a new image canvas.
   *
   * @param canvas the canvas
   */
  public ImageCanvas(ModernCanvas canvas) {
    mCanvas = canvas;

    canvas.addCanvasListener(new CanvasEvents());

    generate();
  }

  /**
   * Generate.
   */
  private void generate() {
    cache();

    fireCanvasChanged(new ChangeEvent(this));
  }

  /**
   * Cache.
   */
  private void cache() {
    Dimension size = new Dimension(mCanvas.getCanvasSize().getW(), mCanvas.getCanvasSize().getH());

    image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);

    Graphics2D g2Temp = ImageUtils.createAATextGraphics(image);

    try {
      mCanvas.drawForeground(g2Temp);
    } finally {
      g2Temp.dispose();
    }

    repaint();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvas#getCanvasSize()
   */
  @Override
  public Dimension getPreferredSize() {
    return mCanvas.getPreferredSize();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.graphics.ModernCanvas#drawCanvasForeground(java.awt.
   * Graphics2D)
   */
  @Override
  public void rasterCanvas(Graphics2D g2, DrawingContext context) {
    g2.drawImage(image, 0, 0, null);
  }
}
