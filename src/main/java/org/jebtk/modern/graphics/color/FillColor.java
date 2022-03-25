/**
 * Copyright 2017 Antony Holmes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jebtk.modern.graphics.color;

import java.awt.Color;

/**
 * The Class FillColor.
 */
public class FillColor {

  /** The m fill color. */
  private Color mFillColor;

  /** The m line color. */
  private Color mLineColor;

  /**
   * Instantiates a new fill color.
   *
   * @param lineColor the line color
   * @param fillColor the fill color
   */
  public FillColor(Color lineColor, Color fillColor) {
    mLineColor = lineColor;
    mFillColor = fillColor;
  }

  /**
   * Gets the line.
   *
   * @return the line
   */
  public Color getLine() {
    return mLineColor;
  }

  /**
   * Gets the fill.
   *
   * @return the fill
   */
  public Color getFill() {
    return mFillColor;
  }
}
