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
package org.jebtk.modern.theme;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

import org.jebtk.core.ColorUtils;
import org.jebtk.core.Props;
import org.jebtk.core.geom.IntRect;
import org.jebtk.modern.ModernComponent;

/**
 * The Class ModernRoundedWidgetRenderer.
 */
public class ColorDialogButtonUI extends ButtonUI {

  public static final Color C1 = MaterialService.getInstance().getColor("color.dialog.button.gradient.start");

  public static final Color C2 = MaterialService.getInstance().getColor("color.dialog.button.gradient.end");

  @Override
  public String getName() {
    return "color.dialog.button";
  }

  @Override
  public void fill(ModernComponent c, Graphics2D g2, IntRect rect, Props props) {

    GradientPaint gradient = ColorUtils.getVGradient(0, rect.h, C1, C2);

    g2.setPaint(gradient);

    super.fill(c, g2, rect);

    // g2.setColor(BORDER);
    // outline(g2, x, y, w, h);
  }
}
