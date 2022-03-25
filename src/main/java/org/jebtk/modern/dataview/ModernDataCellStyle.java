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
package org.jebtk.modern.dataview;

import java.awt.Color;

import org.jebtk.core.ColorUtils;
import org.jebtk.core.text.TextUtils;

/**
 * A style element that can be used to theme cells in a dataview control.
 * 
 * @author Antony Holmes
 *
 */
public class ModernDataCellStyle {

  /**
   * The constant DEFAULT_STYLE.
   */
  public static final ModernDataCellStyle DEFAULT_STYLE = new ModernDataCellStyle();

  /**
   * The member background.
   */
  private Color mBackground = Color.WHITE;

  /**
   * The member color.
   */
  private Color mColor = Color.BLACK;
  // private Font font = ModernWidget.FONT;

  /**
   * Sets the color.
   *
   * @param color the new color
   */
  public void setColor(Color color) {
    mColor = color;
  }

  /**
   * Gets the color.
   *
   * @return the color
   */
  public Color getColor() {
    return mColor;
  }

  /**
   * Sets the background.
   *
   * @param color the new background
   */
  public void setBackground(Color color) {
    mBackground = color;
  }

  /**
   * Gets the background.
   *
   * @return the background
   */
  public Color getBackground() {
    return mBackground;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder buffer = new StringBuilder();
    buffer.append(ColorUtils.toHtml(mColor));
    buffer.append(TextUtils.COLON_DELIMITER);
    buffer.append(ColorUtils.toHtml(mBackground));

    return buffer.toString();
  }

}
