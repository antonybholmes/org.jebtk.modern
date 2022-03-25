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

import java.awt.Shape;
import java.util.Map;

import org.jebtk.core.collections.DefaultHashMap;
import org.jebtk.core.collections.HashMapCreator;
import org.jebtk.core.collections.IterMap;

/**
 * Cache geometric shapes for drawing.
 *
 * @author Antony Holmes
 *
 */
public class ShapeService {

  /**
   * The Class ShapeServiceLoader.
   */
  private static class ShapeServiceLoader {

    /** The Constant INSTANCE. */
    private static final ShapeService INSTANCE = new ShapeService();
  }

  /**
   * Gets the single instance of SettingsService.
   *
   * @return single instance of SettingsService
   */
  public static ShapeService getInstance() {
    return ShapeServiceLoader.INSTANCE;
  }

  /**
   * The map.
   */
  private Map<String, IterMap<Integer, Shape>> mShapeMap = DefaultHashMap.create(new HashMapCreator<Integer, Shape>());

  /**
   * Instantiates a new UI resources.
   */
  private ShapeService() {
    // do nothing
  }

  /**
   * Load shape.
   *
   * @param name the name
   * @return the shape
   */
  public Shape loadShape(String name) {
    return loadShape(name, 16);
  }

  /**
   * Load shape.
   *
   * @param name the name
   * @param size the size
   * @return the shape
   */
  public Shape loadShape(String name, int size) {
    return loadShape(name, size, false);
  }

  /**
   * Load icon64.
   *
   * @param name     the name
   * @param size     the size
   * @param centered the centered
   * @return the modern icon
   */
  public Shape loadShape(String name, int size, boolean centered) {
    String ln = name.toLowerCase();

    if (!mShapeMap.get(ln).containsKey(size)) {
      Shape shape = null;

      if (centered) {
        if (ln.equals("square")) {
          shape = new ShapeCenteredSquare(0, 0, size);
        } else if (ln.equals("triangle")) {
          shape = new ShapeCenteredEquilateralTriangle(0, 0, size);
        } else if (ln.equals("inverted triangle")) {
          shape = new ShapeCenteredEquilateralInvertedTriangle(0, 0, size);
        } else if (ln.equals("diamond")) {
          shape = new ShapeCenteredDiamond(0, 0, size);
        } else if (ln.equals("hexagon")) {
          shape = new ShapeCenteredHexagon(0, 0, size);
        } else if (ln.equals("pentagon")) {
          shape = new ShapeCenteredPentagon(0, 0, size);
        } else if (ln.equals("parallelogram")) {
          shape = new ShapeCenteredParallelogram(0, 0, size);
        } else if (ln.equals("trapezoid")) {
          shape = new ShapeCenteredTrapezoid(0, 0, size);
        } else if (ln.equals("star")) {
          shape = new ShapeCenteredStar(0, 0, size);
        } else {
          shape = new ShapeCenteredCircle(0, 0, size);
        }
      } else {
        if (ln.equals("square")) {
          shape = new ShapeSquare(0, 0, size);
        } else if (ln.equals("triangle")) {
          shape = new ShapeEquilateralTriangle(0, 0, size);
        } else if (ln.equals("inverted triangle")) {
          shape = new ShapeEquilateralInvertedTriangle(0, 0, size);
        } else if (ln.equals("diamond")) {
          shape = new ShapeDiamond(0, 0, size);
        } else if (ln.equals("hexagon")) {
          shape = new ShapeHexagon(0, 0, size);
        } else if (ln.equals("pentagon")) {
          shape = new ShapePentagon(0, 0, size);
        } else if (ln.equals("parallelogram")) {
          shape = new ShapeParallelogram(0, 0, size);
        } else if (ln.equals("trapezoid")) {
          shape = new ShapeTrapezoid(0, 0, size);
        } else if (ln.equals("star")) {
          shape = new ShapeStar(0, 0, size);
        } else {
          shape = new ShapeCircle(0, 0, size);
        }
      }

      mShapeMap.get(ln).put(size, shape);
    }

    return mShapeMap.get(ln).get(size);
  }

  /**
   * Gets the shape.
   *
   * @param shape the shape
   * @return the shape
   */
  public static String getShape(Shape shape) {
    if (shape instanceof ShapeDiamond) {
      return "diamond";
    } else if (shape instanceof ShapeSquare) {
      return "square";
    } else if (shape instanceof ShapeEquilateralTriangle) {
      return "triangle";
    } else if (shape instanceof ShapeEquilateralInvertedTriangle) {
      return "inverted triangle";
    } else if (shape instanceof ShapeTrapezoid) {
      return "trapezoid";
    } else if (shape instanceof ShapeParallelogram) {
      return "parallelogram";
    } else if (shape instanceof ShapeHexagon) {
      return "hexagon";
    } else if (shape instanceof ShapePentagon) {
      return "pentagon";
    } else if (shape instanceof ShapeStar) {
      return "star";
    } else {
      return "circle";
    }
  }
}
