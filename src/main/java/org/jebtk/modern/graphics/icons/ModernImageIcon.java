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
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.jebtk.core.Props;
import org.jebtk.modern.graphics.ImageUtils;

/**
 * The class ModernImageIcon.
 */
public class ModernImageIcon extends MultiResIcon {

  /**
   * The member buffered image.
   */

  public final URL mUrl;

  private BufferedImage mBuffered;

  public ModernImageIcon(URL url) {
    mUrl = url;
  }

  @Override
  public URL getURL() {
    return mUrl;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.icons.ModernIcon#drawForeground(java.awt.Graphics2D,
   * java.awt.Rectangle)
   */
  @Override
  public void drawIcon(Graphics2D g2, int x, int y, int w, int h, Props props) {
    // x = x + (w - getWidth()) / 2;
    // y = y + (h - getHeight()) / 2;

    rasterIcon(g2, x, y, w, h, props);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.icons.ModernIcon#getWidth()
   */
  @Override
  public int getWidth() {
    return getImage().getWidth();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.icons.ModernIcon#getHeight()
   */
  @Override
  public int getHeight() {
    return getImage().getHeight();
  }

  public BufferedImage getImage() {
    if (mBuffered == null) {
      try {
        mBuffered = ImageIO.read(mUrl);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return mBuffered;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.icons.ModernIcon#getImage()
   */
  @Override
  public BufferedImage getImage(int w, int h, Props props) {
    // If we want the image at its native resolution, return the buffered copy
    if (w == getWidth()) {
      return getImage();
    }

    // Scale image and cache at other resolutions
    if (!mBufMap.containsKey(w)) {

      double f = (double) w / getWidth();

      BufferedImage buf = ImageUtils.createImage(w, w);

      AffineTransform at = AffineTransform.getScaleInstance(f, f);
      // AffineTransformOp ato = new AffineTransformOp(at,
      // AffineTransformOp.TYPE_BICUBIC);
      // ato.filter(mBuffered, buf);

      Graphics2D g2 = (Graphics2D) buf.createGraphics();
      ImageUtils.setQualityHints(g2);

      try {
        g2.drawRenderedImage(getImage(), at);
      } finally {
        g2.dispose();
      }

      mBufMap.put(w, buf);
    }

    return mBufMap.get(w);
  }

//  @Override
//  public javafx.scene.image.Image getFxImage(int w) {
//    if (mImage == null) {
//      mImage = new javafx.scene.image.Image(mUrl.toString(), mSize, mSize, true,
//          true);
//    }
//
//    return mImage;
//  }
}
