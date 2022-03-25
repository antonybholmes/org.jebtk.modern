package org.jebtk.modern.theme;

import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.core.geom.IntRect;

public class CheckedBoxUI extends CheckUI {

  @Override
  public String getName() {
    return "checkbox.checked";
  }

  @Override
  public void draw(Graphics2D g2, IntRect rect, Props props) {

    DrawUIService.getInstance().getRenderer("button-fill").draw(g2, rect, props);

    super.draw(g2, rect, props); // props);
  }
}
