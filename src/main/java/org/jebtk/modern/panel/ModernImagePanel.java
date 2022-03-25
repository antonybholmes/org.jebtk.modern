/**
 * Copyright (C) 2019, Antony Holmes
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
package org.jebtk.modern.panel;

import java.awt.Graphics2D;

import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.UI;
import org.jebtk.modern.graphics.icons.ModernIcon;

/**
 * Displays an image.
 * 
 * @author Antony Holmes
 *
 */
public class ModernImagePanel extends ModernComponent {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member image.
   */
  private ModernIcon mImage;

  /** The m size. */
  private int mSize;

  private int mIconSize;

  /**
   * Instantiates a new modern image panel.
   *
   * @param image the image
   */
  public ModernImagePanel(ModernIcon image) {
    this(image, image.getWidth());
  }

  /**
   * Instantiates a new modern image panel.
   *
   * @param image the image
   * @param size  the size
   */
  public ModernImagePanel(ModernIcon image, int size) {
    setImage(image, size, size);
  }

  /**
   * Instantiates a new modern image panel.
   *
   * @param image    the image
   * @param iconSize the icon size
   * @param size     the size
   */
  public ModernImagePanel(ModernIcon image, int size, int iconSize) {
    setImage(image, size, iconSize);
  }

  /**
   * Sets the image.
   *
   * @param image    the image
   * @param iconSize the icon size
   * @param size     the size
   */
  public void setImage(ModernIcon image, int size, int iconSize) {
    mImage = image;
    mIconSize = iconSize;
    mSize = size;

    UI.setSize(this, size, size);

    repaint();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForeground(Graphics2D g2) {
    int x = (getWidth() - mIconSize) / 2;
    int y = (getHeight() - mIconSize) / 2;

    mImage.rasterIcon(g2, x, y, mIconSize, mIconSize, null);
  }
}
