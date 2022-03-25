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

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.io.IOException;

/**
 * Rastorizes an icon (for example a vector based on) to reduce drawing
 * overhead.
 * 
 * @author Antony Holmes
 */
public class GrayScaleIcon extends RasterIcon {

  public GrayScaleIcon(ModernIcon icon) throws IOException {
    super(convert(icon));
  }

  /**
   * Convert.
   *
   * @param icon the icon
   * @return the modern icon
   * @throws IOException
   */
  public static ModernIcon convert(ModernIcon icon) throws IOException {
    BufferedImage image = convert(icon.getImage(icon.getHeight()));

    if (image == null) {
      return null;
    }

    return new RasterIcon(image);
  }

  /**
   * Convert.
   *
   * @param image the image
   * @return the image
   */
  public static BufferedImage convert(BufferedImage image) {
    if (image == null) {
      return null;
    }

    // ImageFilter filter = new GrayFilter(true, 50);
    // ImageProducer producer = new FilteredImageSource(image.getSource(),
    // filter);
    // Image grayImg = Toolkit.getDefaultToolkit().createImage(producer);

    BufferedImageOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
    BufferedImage grayImage = op.filter(image, null);

    return grayImage;
  }
}
