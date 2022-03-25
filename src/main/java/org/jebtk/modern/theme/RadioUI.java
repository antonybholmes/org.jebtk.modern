package org.jebtk.modern.theme;

import java.awt.Color;
import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.core.geom.IntRect;
import org.jebtk.modern.ModernWidget;

public class RadioUI extends ButtonUI {

  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return "radio";
  }

  @Override
  public void draw(Graphics2D g2, IntRect rect, Props props) {

    g2.setColor(Color.WHITE);
    g2.fillOval(rect.x, rect.y, rect.w, rect.w);

    g2.setColor(ModernWidget.LINE_COLOR);

    int w2 = rect.w - 2;

    // g2.setStroke(ModernTheme.DOUBLE_LINE_STROKE);
    g2.drawOval(rect.x + 1, rect.y + 1, w2, w2);
  }
}
