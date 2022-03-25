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

import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.core.geom.IntRect;
import org.jebtk.modern.ModernComponent;

/**
 * The Class ModernRoundedWidgetRenderer.
 */
public class PillOutlineUI extends ButtonUI {

  @Override
  public String getName() {
    return "pill-outline";
  }

  @Override
  public void draw(ModernComponent c, Graphics2D g2, IntRect rect, Props props) {

    if (props != null) {
      g2.setColor(props.getColor("color"));
    } else {
      g2.setColor(HIGHLIGHTED_FILL_COLOR);
    }

    g2.drawRoundRect(rect.x, rect.y, rect.w - 1, rect.h - 1, rect.h, rect.h);
  }
}
