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
public class ShapePentagon extends Polygon {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The t angle. */
  public static double T_ANGLE = Mathematics.TWO_PI / 5.0;

  /** The angle 1. */
  public static double ANGLE_1 = Mathematics.HALF_PI;

  /** The angle 2. */
  public static double ANGLE_2 = ANGLE_1 + T_ANGLE;

  /** The angle 3. */
  public static double ANGLE_3 = ANGLE_2 + T_ANGLE;

  /** The angle 4. */
  public static double ANGLE_4 = ANGLE_3 + T_ANGLE;

  /** The angle 5. */
  public static double ANGLE_5 = ANGLE_4 + T_ANGLE;

  /** The cos angle 1. */
  public static double COS_ANGLE_1 = Math.cos(ANGLE_1);

  /** The cos angle 2. */
  public static double COS_ANGLE_2 = Math.cos(ANGLE_2);

  /** The cos angle 3. */
  public static double COS_ANGLE_3 = Math.cos(ANGLE_3);

  /** The cos angle 4. */
  public static double COS_ANGLE_4 = Math.cos(ANGLE_4);

  /** The cos angle 5. */
  public static double COS_ANGLE_5 = Math.cos(ANGLE_5);

  /** The sin angle 1. */
  public static double SIN_ANGLE_1 = Math.sin(ANGLE_1);

  /** The sin angle 2. */
  public static double SIN_ANGLE_2 = Math.sin(ANGLE_2);

  /** The sin angle 3. */
  public static double SIN_ANGLE_3 = Math.sin(ANGLE_3);

  /** The sin angle 4. */
  public static double SIN_ANGLE_4 = Math.sin(ANGLE_4);

  /** The sin angle 5. */
  public static double SIN_ANGLE_5 = Math.sin(ANGLE_5);

  /**
   * Instantiates a new shape diamond.
   *
   * @param x    the x
   * @param y    the y
   * @param size the size
   */
  public ShapePentagon(int x, int y, int size) {
    if (size % 2 == 0) {
      --size;
    }

    double r = size / 2.0;

    double x1 = x + size / 2.0;
    double y1 = y + size / 2.0;

    addPoint((int) (x1 + COS_ANGLE_1 * r), (int) (y1 - SIN_ANGLE_1 * r));
    addPoint((int) (x1 + COS_ANGLE_2 * r), (int) (y1 - SIN_ANGLE_2 * r));
    addPoint((int) (x1 + COS_ANGLE_3 * r), (int) (y1 - SIN_ANGLE_3 * r));
    addPoint((int) (x1 + COS_ANGLE_4 * r), (int) (y1 - SIN_ANGLE_4 * r));
    addPoint((int) (x1 + COS_ANGLE_5 * r), (int) (y1 - SIN_ANGLE_5 * r));
  }
}
