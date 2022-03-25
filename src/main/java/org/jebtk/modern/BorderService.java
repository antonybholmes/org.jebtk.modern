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
package org.jebtk.modern;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import org.jebtk.core.collections.DefaultHashMap;
import org.jebtk.core.collections.DefaultHashMapCreator;
import org.jebtk.core.collections.HashMapCreator;
import org.jebtk.core.collections.IterMap;

/**
 * Cache borders so they can be reused.
 *
 * @author Antony Holmes
 *
 */
public class BorderService {

  /**
   * The Class BorderServiceLoader.
   */
  private static class BorderServiceLoader {

    /** The Constant INSTANCE. */
    private static final BorderService INSTANCE = new BorderService();
  }

  /**
   * Gets the single instance of SettingsService.
   *
   * @return single instance of SettingsService
   */
  public static BorderService getInstance() {
    return BorderServiceLoader.INSTANCE;
  }

  /**
   * The map.
   */
  private final Map<Integer, IterMap<Integer, IterMap<Integer, IterMap<Integer, Border>>>> mMap = DefaultHashMap
      .create(new DefaultHashMapCreator<>(
          new DefaultHashMapCreator<>(new HashMapCreator<>())));

  /** The m line border map. */
  private final Map<Color, Border> mLineBorderMap = new HashMap<>();

  /**
   * Instantiates a new UI resources.
   */
  private BorderService() {
    // do nothing
  }

  /**
   * Creates the left border.
   *
   * @param padding the padding
   * @return a border
   */
  public final Border createLeftBorder(int padding) {
    return createBorder(0, padding, 0, 0);
  }

  /**
   * Creates a left right border.
   *
   * @param padding the padding
   * @return a border
   */
  public final Border createLeftRightBorder(int padding) {
    return createLeftRightBorder(padding, padding);
  }

  public final Border createLeftRightBorder(int left, int right) {
    return createBorder(0, left, 0, right);
  }

  /**
   * Creates the right border.
   *
   * @param padding the padding
   * @return a border
   */
  public final Border createRightBorder(int padding) {
    return createBorder(0, 0, 0, padding);
  }

  /**
   * Creates the top border.
   *
   * @param padding the padding
   * @return a border
   */
  public final Border createTopBorder(int padding) {
    return createBorder(padding, 0, 0, 0);
  }

  /**
   * Creates the bottom border.
   *
   * @param padding the padding
   * @return a border
   */
  public final Border createBottomBorder(int padding) {
    return createBorder(0, 0, padding, 0);
  }

  /**
   * Creates a uniform border for a component.
   *
   * @param padding the padding
   * @return a border
   */
  public final Border createBorder(int padding) {
    return createBorder(padding, padding, padding, padding);
  }

  /**
   * Creates the top bottom border.
   *
   * @param padding the padding
   * @return a border
   */
  public final Border createTopBottomBorder(int padding) {
    return createBorder(padding, 0, padding, 0);
  }

  /**
   * Creates the top bottom border.
   *
   * @param top    the top
   * @param bottom the bottom
   * @return the border
   */
  public final Border createTopBottomBorder(int top, int bottom) {
    return createBorder(top, 0, bottom, 0);
  }

  /**
   * Creates the top left border.
   *
   * @param padding the padding
   * @return a border
   */
  public Border createTopLeftBorder(int padding) {
    return createBorder(padding, padding, 0, 0);
  }

  /**
   * Creates the bottom left border.
   *
   * @param padding the padding
   * @return a border
   */
  public Border createBottomLeftBorder(int padding) {
    return createBorder(0, padding, padding, 0);
  }

  /**
   * Creates a border.
   *
   * @param top    the top
   * @param left   the left
   * @param bottom the bottom
   * @param right  the right
   * @return a border
   */
  public Border createBorder(int top, int left, int bottom, int right) {

    if (!mMap.get(top).get(left).get(bottom).containsKey(right)) {
      mMap.get(top).get(left).get(bottom).put(right, BorderFactory.createEmptyBorder(top, left, bottom, right));
    }

    return mMap.get(top).get(left).get(bottom).get(right);
  }

  /**
   * Returns a cached instance of a line border of a given color.
   *
   * @param color the color
   * @return the border
   */
  public Border createLineBorder(Color color) {

    if (!mLineBorderMap.containsKey(color)) {
      mLineBorderMap.put(color, BorderFactory.createLineBorder(color));
    }

    return mLineBorderMap.get(color);
  }

  public Border createBorder(int wp, int hp) {
    return createBorder(hp, wp, hp, wp);
  }
}
