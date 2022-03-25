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
package org.jebtk.modern.graphics.shapes;

import java.awt.Polygon;

import org.jebtk.core.Mathematics;

/**
 * Create a diamond centered on x, y.
 *
 * @author Antony Holmes
 */
public class ShapeStar extends Polygon {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The t angle 2. */
  public static double T_ANGLE_2 = Mathematics.TWO_PI / 10.0;

  /** The ratio. */
  public static double RATIO = 0.4;

  /** The cos angle 1 2. */
  public static double COS_ANGLE_1_2 = Math.cos(ShapePentagon.ANGLE_1 + T_ANGLE_2);

  /** The cos angle 2 2. */
  public static double COS_ANGLE_2_2 = Math.cos(ShapePentagon.ANGLE_2 + T_ANGLE_2);

  /** The cos angle 3 2. */
  public static double COS_ANGLE_3_2 = Math.cos(ShapePentagon.ANGLE_3 + T_ANGLE_2);

  /** The cos angle 4 2. */
  public static double COS_ANGLE_4_2 = Math.cos(ShapePentagon.ANGLE_4 + T_ANGLE_2);

  /** The cos angle 5 2. */
  public static double COS_ANGLE_5_2 = Math.cos(ShapePentagon.ANGLE_5 + T_ANGLE_2);

  /** The sin angle 1 2. */
  public static double SIN_ANGLE_1_2 = Math.sin(ShapePentagon.ANGLE_1 + T_ANGLE_2);

  /** The sin angle 2 2. */
  public static double SIN_ANGLE_2_2 = Math.sin(ShapePentagon.ANGLE_2 + T_ANGLE_2);

  /** The sin angle 3 2. */
  public static double SIN_ANGLE_3_2 = Math.sin(ShapePentagon.ANGLE_3 + T_ANGLE_2);

  /** The sin angle 4 2. */
  public static double SIN_ANGLE_4_2 = Math.sin(ShapePentagon.ANGLE_4 + T_ANGLE_2);

  /** The sin angle 5 2. */
  public static double SIN_ANGLE_5_2 = Math.sin(ShapePentagon.ANGLE_5 + T_ANGLE_2);

  /**
   * Instantiates a new shape diamond.
   *
   * @param x    the x
   * @param y    the y
   * @param size the size
   */
  public ShapeStar(int x, int y, int size) {
    // We want an uneven number of pixels
    if (size % 2 == 0) {
      --size;
    }

    double r = size / 2.0;
    double r2 = r * RATIO;

    double x1 = x + size / 2.0;
    double y1 = y + size / 2.0;

    addPoint((int) (x1 + ShapePentagon.COS_ANGLE_1 * r), (int) (y1 - ShapePentagon.SIN_ANGLE_1 * r));
    addPoint((int) (x1 + COS_ANGLE_1_2 * r2), (int) (y1 - SIN_ANGLE_1_2 * r2));

    addPoint((int) (x1 + ShapePentagon.COS_ANGLE_2 * r), (int) (y1 - ShapePentagon.SIN_ANGLE_2 * r));
    addPoint((int) (x1 + COS_ANGLE_2_2 * r2), (int) (y1 - SIN_ANGLE_2_2 * r2));

    addPoint((int) (x1 + ShapePentagon.COS_ANGLE_3 * r), (int) (y1 - ShapePentagon.SIN_ANGLE_3 * r));
    addPoint((int) (x1 + COS_ANGLE_3_2 * r2), (int) (y1 - SIN_ANGLE_3_2 * r2));

    addPoint((int) (x1 + ShapePentagon.COS_ANGLE_4 * r), (int) (y1 - ShapePentagon.SIN_ANGLE_4 * r));
    addPoint((int) (x1 + COS_ANGLE_4_2 * r2), (int) (y1 - SIN_ANGLE_4_2 * r2));

    addPoint((int) (x1 + ShapePentagon.COS_ANGLE_5 * r), (int) (y1 - ShapePentagon.SIN_ANGLE_5 * r));
    addPoint((int) (x1 + COS_ANGLE_5_2 * r2), (int) (y1 - SIN_ANGLE_5_2 * r2));
  }
}
