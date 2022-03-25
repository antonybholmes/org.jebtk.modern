/**
 * Copyright 2018 Antony Holmes
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
package org.jebtk.modern.theme;

import java.awt.Color;
import java.awt.Graphics2D;

import org.jebtk.core.NameGetter;
import org.jebtk.core.Props;
import org.jebtk.core.geom.IntRect;
import org.jebtk.modern.ModernComponent;

/**
 * The Class DrawUI provides reusable drawing routines that can be shared by
 * multiple widgets. For example a button renderer can be used by a button or to
 * highlight the items in a list view for example.
 */
public abstract class DrawUI implements NameGetter {

  /** The Constant LINE_COLOR. */
  public static final Color LINE_COLOR = ThemeService.getInstance().getColors().getLineColor();

  /**
   * Draw a portion of the widget UI.
   *
   * @param g2    the g 2
   * @param rect  the rect
   */
  public void draw(Graphics2D g2, IntRect rect) {
    draw(g2, rect, new Props());
  }

  public void draw(Graphics2D g2, Color color) {
    draw(null, g2, new Props().set("color", color));
  }

  public void draw(Graphics2D g2, IntRect rect, Color color) {
    draw(g2, rect, new Props().set("color", color));
  }

  public void draw(Graphics2D g2, IntRect rect, Props props) {
    draw(null, g2, rect, props);
  }

  public void draw(ModernComponent c, Graphics2D g2) {
    draw(c, g2, new Props());
  }

  public void draw(ModernComponent c, Graphics2D g2, Color color) {
    draw(c, g2, new Props().set("color", color));
  }

  public void draw(ModernComponent c, Graphics2D g2, Props props) {
    draw(c, g2, c.getRect(), props);
  }

  public void draw(ModernComponent c, Graphics2D g2, IntRect rect) {
    draw(c, g2, rect, new Props());
  }

  public void draw(ModernComponent c, Graphics2D g2, IntRect rect, Color color) {
    draw(c, g2, rect, new Props().set("color", color));
  }

  /**
   * Draw a portion of the widget UI.
   *
   * @param g2    the g 2
   * @param c     the c
   * @param rect  the rect
   * @param props
   */
  public void draw(ModernComponent c, Graphics2D g2, IntRect rect, Props props) {
    fill(c, g2, rect, props);
    outline(c, g2, rect, props);
  }

  public void fill(ModernComponent c, Graphics2D g2, IntRect rect) {
    fill(c, g2, rect, new Props());
  }

  public void fill(ModernComponent c, Graphics2D g2, IntRect rect, Props props) {
    if (g2.getColor() != null && g2.getColor().getAlpha() > 0) {
      g2.fillRect(rect.x, rect.y, rect.w, rect.h);
    }
  }

  public void outline(ModernComponent c, Graphics2D g2, IntRect rect, Props props) {
    if (g2.getColor() != null && g2.getColor().getAlpha() > 0) {
      g2.drawRect(rect.x, rect.y, rect.w - 1, rect.h - 1);
    }
  }
}
