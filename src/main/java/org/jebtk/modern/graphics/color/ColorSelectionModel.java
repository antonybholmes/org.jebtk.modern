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
package org.jebtk.modern.graphics.color;

import org.jebtk.core.ColorValue;
import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.event.ChangeListeners;

/**
 * The class ColorSelectionModel.
 */
public class ColorSelectionModel extends ChangeListeners {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant COLOR_CHANGED.
   */
  public static final String COLOR_CHANGED = "color_changed";

  /**
   * The constant CHANNEL_CHANGED.
   */
  public static final String CHANNEL_CHANGED = "channel_changed";

  /**
   * The member current color.
   */
  private ColorValue mCurrentColor;

  /**
   * The member new color.
   */
  private ColorValue mNewColor;

  /**
   * The member channel.
   */
  private ColorChannel mChannel = ColorChannel.RED;

  /**
   * Sets the current color.
   *
   * @param color the new current color
   */
  public void setCurrentColor(ColorValue color) {
    mCurrentColor = color;

    setNewColor(new ColorValue(color));

    // this.fireModernClickEvent(new ModernClickEvent(this, COLOR_CHANGED));

  }

  /**
   * Update color.
   *
   * @param channel the channel
   * @param value   the value
   */
  public void updateColor(ColorChannel channel, int value) {
    switch (channel) {
    case RED:
      mNewColor.mRed = value;
      break;
    case GREEN:
      mNewColor.mGreen = value;
      break;
    case BLUE:
      mNewColor.mBlue = value;
      break;
    default:
      mNewColor.mAlpha = value;
      break;
    }

    fireChanged(new ChangeEvent(this, COLOR_CHANGED));
  }

  /**
   * Sets the channel.
   *
   * @param channel the new channel
   */
  public void setChannel(ColorChannel channel) {
    mChannel = channel;

    fireChanged(new ChangeEvent(this, CHANNEL_CHANGED));
  }

  /**
   * Gets the channel.
   *
   * @return the channel
   */
  public ColorChannel getChannel() {
    return mChannel;
  }

  /**
   * Gets the new color.
   *
   * @return the new color
   */
  public ColorValue getNewColor() {
    return mNewColor;
  }

  /**
   * Gets the current color.
   *
   * @return the current color
   */
  public ColorValue getCurrentColor() {
    return mCurrentColor;
  }

  /**
   * Sets the new color.
   *
   * @param color the new color
   */
  public void setNewColor(ColorValue color) {
    mNewColor = color;

    fireChanged(new ChangeEvent(this, COLOR_CHANGED));
  }
}