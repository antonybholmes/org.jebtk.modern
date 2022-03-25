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

/**
 * The class ColorPicker.
 */
public class MultiColorPicker extends ColorPicker {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant STEPS.
   */
  public static final int STEPS = 6;

  /** The colors. */
  private static ArrayList<List<Color>> COLORS = new ArrayList<List<Color>>();

  static {
    /// red
    COLORS.add(generateColors(new Color(255, 204, 204), new Color(204, 0, 0), STEPS));

    // orange
    COLORS.add(generateColors(new Color(255, 229, 204), new Color(204, 102, 0), STEPS));

    /// yellow
    COLORS.add(generateColors(new Color(255, 255, 204), new Color(204, 204, 0), STEPS));

    // green
    COLORS.add(generateColors(new Color(229, 255, 204), new Color(76, 153, 0), STEPS));

    // green
    COLORS.add(generateColors(new Color(204, 255, 204), new Color(0, 153, 0), STEPS));

    COLORS.add(generateColors(new Color(204, 255, 229), new Color(0, 153, 76), STEPS));

    // blue
    COLORS.add(generateColors(new Color(204, 255, 255), new Color(0, 153, 153), STEPS));

    COLORS.add(generateColors(new Color(204, 229, 255), new Color(0, 76, 153), STEPS));

    COLORS.add(generateColors(new Color(204, 204, 255), new Color(0, 0, 153), STEPS));

    COLORS.add(generateColors(new Color(229, 204, 255), new Color(76, 0, 153), STEPS));

    COLORS.add(generateColors(new Color(255, 204, 204), new Color(153, 0, 153), STEPS));

    COLORS.add(generateColors(new Color(255, 204, 229), new Color(153, 0, 76), STEPS));

    // gray values
    COLORS.add(generateColors(new Color(255, 255, 255), new Color(64, 64, 64), STEPS));

  }

  /**
   * Instantiates a new color picker.
   */
  public MultiColorPicker() {
    setColors(COLORS);

  }

  /**
   * Generate colors.
   *
   * @param start the start
   * @param end   the end
   * @param steps the steps
   * @return the list
   */
  protected static List<Color> generateColors(Color start, Color end, double steps) {
    List<Color> colors = new ArrayList<Color>();

    colors.add(start);

    double incR = (end.getRed() - start.getRed()) / steps;
    double incG = (end.getGreen() - start.getGreen()) / steps;
    double incB = (end.getBlue() - start.getBlue()) / steps;

    double r = start.getRed();
    double g = start.getGreen();
    double b = start.getBlue();

    for (int i = 0; i < steps - 1; ++i) {
      r += incR;
      g += incG;
      b += incB;

      colors.add(new Color((int) r, (int) g, (int) b));
    }

    return colors;
  }
}
