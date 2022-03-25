package org.jebtk.modern.theme;

import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.core.geom.IntRect;

public class RadioSelectedUI extends RadioUI {

  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return "radio.selected";
  }

  @Override
  public void draw(Graphics2D g2, IntRect rect, Props props) {

    super.draw(g2, rect, props);

    int w2 = rect.w - 8;

    // x += (w - wf) / 2;
    // y += (h - wf) / 2;

    // g2.setColor(Color.WHITE);
    // g2.fillOval(x, y, w, w);

    if (props != null) {
      g2.setColor(props.getColor("color"));
    } else {
      g2.setColor(HIGHLIGHTED_FILL_COLOR);
    }

    // g2.drawOval(x, y, wf, wf);

    // g2.setColor(Color.WHITE);
    g2.fillOval(rect.x + 4, rect.y + 4, w2, w2);
    // g2.drawOval(x, y, w2, wf);

    w2 = rect.w - 2;
    // g2.setStroke(ModernTheme.DOUBLE_LINE_STROKE);
    g2.drawOval(rect.x + 1, rect.y + 1, w2, w2);
  }
}
