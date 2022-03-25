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

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.jebtk.core.collections.CollectionUtils;

/**
 * The class StandardColorPicker.
 */
public class StandardColorPicker extends ColorPicker {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The colors. */
  private static List<List<Color>> COLORS = new ArrayList<List<Color>>();

  static {
    // No color
    COLORS.add(CollectionUtils.toList((Color) null));

    // Dark red
    COLORS.add(CollectionUtils.toList(new Color(153, 0, 0)));

    // Red
    COLORS.add(CollectionUtils.toList(new Color(255, 0, 0)));

    // orange
    COLORS.add(CollectionUtils.toList(new Color(255, 153, 51)));
    // yellow
    COLORS.add(CollectionUtils.toList(new Color(255, 255, 51)));

    COLORS.add(CollectionUtils.toList(new Color(0, 204, 0)));

    COLORS.add(CollectionUtils.toList(new Color(51, 153, 255)));

    COLORS.add(CollectionUtils.toList(new Color(153, 51, 255)));

    COLORS.add(CollectionUtils.toList(new Color(51, 0, 102)));

    COLORS.add(CollectionUtils.toList(new Color(128, 128, 128)));

    COLORS.add(CollectionUtils.toList(new Color(0, 0, 0)));

  }

  /**
   * Instantiates a new standard color picker.
   */
  public StandardColorPicker() {
    setColors(COLORS);
  }
}
