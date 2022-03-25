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
package org.jebtk.modern.theme;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Stroke;

import javax.swing.border.Border;

import org.jebtk.core.path.Path;
import org.jebtk.core.path.StrictPath;
import org.jebtk.core.settings.SettingsService;
import org.jebtk.modern.BorderService;

/**
 * Provides shared resources for theming widgets in a consistent manner. This
 * allows widgets to be updated globally rather that through individual
 * customization and allows user adjustments without recompiling code.
 * 
 * This class uses hashmaps and to allow for fast multiple, concurrent reads. It
 * is assumed that the loading of theme elements will be done before any UI
 * elements are created that rely upon the theme class. Running loadTheme()
 * after GUI elements such as ModernButton have been created and painted onto a
 * JFrame may result in unexpected behavior. By default the hashmap is locked
 * after parsing the XML theme files to prevent alterations during gui drawing
 * operations.
 *
 * @author Antony Holmes
 *
 */
public class ModernTheme {

  /**
   * The constant DOUBLE_LINE_STROKE.
   */
  public static final Stroke DOUBLE_LINE_STROKE = new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

  /**
   * The constant SINGLE_LINE_STROKE.
   */
  public static final Stroke SINGLE_LINE_STROKE = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

  /**
   * The constant QUAD_LINE_STROKE.
   */
  public static final Stroke QUAD_LINE_STROKE = new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

  /**
   * The constant DASH_MARKS.
   */
  public static final float[] DASH_MARKS = { 4 };

  /**
   * The constant DASHED_LINE_STROKE.
   */
  public static final Stroke DASHED_LINE_STROKE = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1,
      DASH_MARKS, 0);

  /**
   * The constant DOTTED_MARKS.
   */
  public static final float[] DOTTED_MARKS = { 1, 2 };

  /**
   * The constant DOTTED_LINE_STROKE.
   */
  public static final Stroke DOTTED_LINE_STROKE = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1,
      DOTTED_MARKS, 0);

  /**
   * The constant LONG_DASH_MARKS.
   */
  public static final float[] LONG_DASH_MARKS = { 8 };

  /**
   * The constant LONG_DASHED_LINE_STROKE.
   */
  public static final Stroke LONG_DASHED_LINE_STROKE = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
      1, LONG_DASH_MARKS, 0);

  /**
   * The constant DASH_DOT_MARKS.
   */
  public static final float[] DASH_DOT_MARKS = { 4, 4, 2, 4 };

  /**
   * The constant DASHED_DOTTED_LINE_STROKE.
   */
  public static final Stroke DASHED_DOTTED_LINE_STROKE = new BasicStroke(1, BasicStroke.CAP_ROUND,
      BasicStroke.JOIN_ROUND, 1, DASH_DOT_MARKS, 0);

  /**
   * Load dimension.
   *
   * @param path the path
   * @return the dimension
   */
  public static final Dimension loadDimension(String path) {
    return loadDimension(new StrictPath(path));
  }

  /**
   * Load dimension.
   *
   * @param path the path
   * @return the dimension
   */
  public static final Dimension loadDimension(Path path) {
    Path p = new Path(path).append("width");

    int width = SettingsService.getInstance().getInt(p);

    p = new Path(path).append("height");

    int height = SettingsService.getInstance().getInt(p);

    return new Dimension(width, height);
  }

  /**
   * Load border.
   *
   * @param path the path
   * @return the border
   */
  public static final Border loadBorder(String path) {
    return loadBorder(new StrictPath(path));
  }

  /**
   * Load border.
   *
   * @param path the path
   * @return the border
   */
  public static final Border loadBorder(Path path) {
    Path p = new Path(path).append("top");

    int top = SettingsService.getInstance().getInt(p);

    p = new Path(path).append("left");

    int left = SettingsService.getInstance().getInt(p);

    p = new Path(path).append("bottom");

    int bottom = SettingsService.getInstance().getInt(p);

    p = new Path(path).append("right");

    int right = SettingsService.getInstance().getInt(p);

    return BorderService.getInstance().createBorder(top, left, bottom, right);
  }

  /**
   * Creates the single stroke.
   *
   * @param w the w
   * @return the stroke
   */
  public static final Stroke createSingleStroke(int w) {
    return new BasicStroke(w, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
  }

  /**
   * The constant DASHED_LINE_STROKE.
   *
   * @param w the w
   * @return the stroke
   */
  public static final Stroke createDashedStroke(int w) {
    return new BasicStroke(w, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1, DASH_MARKS, 0);
  }

  /**
   * Creates the dotted stroke.
   *
   * @param w the w
   * @return the stroke
   */
  public static final Stroke createDottedStroke(int w) {
    return new BasicStroke(w, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1, DOTTED_MARKS, 0);
  }

  /**
   * Creates the long dash stroke.
   *
   * @param w the w
   * @return the stroke
   */
  public static final Stroke createLongDashStroke(int w) {
    return new BasicStroke(w, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1, LONG_DASH_MARKS, 0);
  }

  /**
   * Creates the dash dotted stroke.
   *
   * @param w the w
   * @return the stroke
   */
  public static final Stroke createDashDottedStroke(int w) {
    return new BasicStroke(w, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1, DASH_DOT_MARKS, 0);
  }
}
