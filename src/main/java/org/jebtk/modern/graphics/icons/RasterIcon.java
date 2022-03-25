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
package org.jebtk.modern.graphics.icons;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.jebtk.core.Props;

/**
 * Rastorizes an icon (for example a vector based on) to reduce drawing
 * overhead.
 * 
 * @author Antony Holmes
 */
public class RasterIcon extends ModernIcon {

  /**
   * The buffered image.
   */
  private BufferedImage mBufferedImage;
  private ModernIcon mIcon;
  private int mSize;

  /**
   * Instantiates a new raster icon.
   *
   * @param icon the icon
   */
  public RasterIcon(ModernIcon icon) {
    this(icon, icon.getWidth());
  }

  /**
   * Instantiates a new raster icon.
   *
   * @param icon the icon
   * @param size the size
   */
  public RasterIcon(ModernIcon icon, int size) {
    this(icon, size, null);
  }

  public RasterIcon(ModernIcon icon, int size, Props props) {
    this(icon.getImage(size, props));
  }

  public RasterIcon(BufferedImage icon) {
    mBufferedImage = icon;
    mSize = icon.getWidth();
  }

  @Override
  public void drawIcon(Graphics2D g2, int x, int y, int w, int h, Props props) {
    rasterIcon(g2, x, y, w, h, props);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.icons.ModernIcon#getImage()
   */
  @Override
  public BufferedImage getImage(int w, int h, Props props) {
    return mBufferedImage;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.icons.ModernIcon#getWidth()
   */
  @Override
  public int getWidth() {
    return mSize;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.icons.ModernIcon#getHeight()
   */
  @Override
  public int getHeight() {
    return mSize;
  }

}
