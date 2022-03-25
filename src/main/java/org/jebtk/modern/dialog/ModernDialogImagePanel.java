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
package org.jebtk.modern.dialog;

import java.awt.Dimension;
import java.awt.Graphics2D;

import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.graphics.icons.ModernIcon;

/**
 * Displays an image.
 * 
 * @author Antony Holmes
 *
 */
public class ModernDialogImagePanel extends ModernWidget {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The image.
   */
  private ModernIcon mImage;

  /**
   * The size.
   */
  private Dimension mSize;

  /**
   * Instantiates a new modern dialog image panel.
   *
   * @param image the image
   * @param size  the size
   */
  public ModernDialogImagePanel(ModernIcon image, int size) {
    setImage(image, size);
  }

  /**
   * Instantiates a new modern dialog image panel.
   *
   * @param image the image
   * @param size  the size
   */
  public ModernDialogImagePanel(ModernIcon image, Dimension size) {
    setImage(image, size);
  }

  /**
   * Sets the image.
   *
   * @param image the image
   * @param size  the size
   */
  public void setImage(ModernIcon image, int size) {
    setImage(image, new Dimension(size, size));
  }

  /**
   * Sets the image.
   *
   * @param image the image
   * @param size  the size
   */
  public void setImage(ModernIcon image, Dimension size) {
    mImage = image;
    mSize = size;

    UI.setSize(this, size);

    repaint();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    int x = (getWidth() - mSize.width) / 2;
    int y = (getHeight() - mSize.height) / 2;

    mImage.drawIcon(g2, x, y, mSize.width, mSize.height);
  }
}
