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
import java.util.HashMap;
import java.util.Map;

import org.jebtk.core.Props;

/**
 * The class MultiStateIcon represents an icon
 */
public class MultiStateIcon extends ModernIcon {

  private static final String DEFAULT_STATE = "default";

  /**
   * The member icons.
   */
  private final Map<String, ModernIcon> mIcons = new HashMap<>();

  /**
   * The member icon.
   */
  private String mState = DEFAULT_STATE;

  public void add(ModernIcon icon) {
    add(DEFAULT_STATE, icon);
  }

  public void add(String state, ModernIcon icon) {
    mIcons.put(state, icon);

    if (!mIcons.containsKey(DEFAULT_STATE)) {
      mIcons.put(DEFAULT_STATE, icon);
    }
  }

  /**
   * Sets the icon.
   *
   * @param index the new icon
   */
  public void setState(String state) {
    mState = state;
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
    getState(mState).drawIcon(g2, x, y, w, h, props);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.icons.ModernIcon#getWidth()
   */
  @Override
  public int getWidth() {
    return getState(mState).getWidth();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.icons.ModernIcon#getHeight()
   */
  @Override
  public int getHeight() {
    return getState(mState).getHeight();
  }

  @Override
  public void rasterIcon(Graphics2D g2, int x, int y, int w, int h, Props props) {
    getState(mState).rasterIcon(g2, x, y, w, h, props);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.icons.ModernIcon#getImage()
   */
  @Override
  public BufferedImage getImage(int w, int h, Props props) {
    return getState(mState).getImage(w, h, props);
  }

  @Override
  public ModernIcon getDisabledIcon() {
    return getState(mState).getDisabledIcon();
  }

  @Override
  public ModernIcon getState(String state) {
    if (mIcons.containsKey(state)) {
      return mIcons.get(state);
    } else {
      return mIcons.get(mState);
    }
  }

  @Override
  public Iterable<String> getStates() {
    return mIcons.keySet();
  }
}
