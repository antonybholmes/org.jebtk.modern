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

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 * The basis for rendering a component as an image so it does not waste time
 * rendering drawing primitives on each refresh.
 *
 * @author Antony Holmes
 */
public class ModernWidgetBufferedImage {

  /**
   * The size.
   */
  protected Dimension size;

  /**
   * Instantiates a new modern widget buffered image.
   *
   * @param size the size
   */
  public ModernWidgetBufferedImage(Dimension size) {
    this.size = size;
  }

  /**
   * Creates the.
   *
   * @return the buffered image
   */
  public BufferedImage create() {
    BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);

    Graphics2D g2 = (Graphics2D) image.createGraphics();

    drawIcon(g2);

    return image;
  }

  /**
   * Separate the background and foreground rendering of components since many
   * widgets will use the same background rendering and only change how
   * information is displayed.
   *
   * @param g2 the g2
   */
  public void drawIcon(Graphics2D g2) {
    // Ensure rendering is antialiased

    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    g2.setColor(ModernWidget.TEXT_COLOR);
    g2.setFont(ModernWidget.FONT);

    drawBackground(g2);

    drawForeground(g2);

    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
  }

  /**
   * Should be in charge of rendering the background.
   *
   * @param g2 the g2
   */
  public void drawBackground(Graphics2D g2) {
    // Do nothing
  }

  /**
   * Should be in charge of rendering the foreground.
   *
   * @param g2 the g2
   */
  public void drawForeground(Graphics2D g2) {
    // Do nothing
  }
}