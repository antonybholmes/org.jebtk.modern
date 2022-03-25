package org.jebtk.modern.button;

import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.css.CSSDrawUI;
import org.jebtk.modern.graphics.ImageUtils;

public class RadioSelectedAnimation extends ButtonCSSAnimation {

  private final ModernClickWidget mRadio;

  public RadioSelectedAnimation(ModernWidget widget) {
    super(widget);

    mRadio = (ModernClickWidget) widget;
  }

  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {
    if (mWidget.isEnabled() && mRadio.isSelected()) {
      int x = mWidget.getInsets().left;
      int y = (mWidget.getHeight() - RadioAnimation.RADIO_SIZE) / 2;

      Graphics2D g2Temp = ImageUtils.createAAStrokeGraphics(g2);

      try {
        fill(mWidget, g2Temp, x, y, RadioAnimation.RADIO_SIZE, RadioAnimation.RADIO_SIZE);
      } finally {
        g2Temp.dispose();
      }
    }
  }

  public void fill(ModernWidget c, Graphics2D g2, int x, int y, int w, int h) {
    // Do nothing

    /*
     * int w2 = w - 8;
     * 
     * g2.setColor(getToColor("fill")); g2.fillOval(x + 4, y + 4, w2, w2);
     * 
     * w2 = w - 2; g2.drawOval(x + 1, y + 1, w2, w2);
     */

    int w2 = w - 8;
    
    CSSDrawUI.setToColor("background-color", c, g2);
    //g2.setColor(getToColor("fill"));
    g2.fillOval(x + 4, y + 4, w2, w2);

    w2 = w - 2;

    // g2.setStroke(ModernTheme.DOUBLE_LINE_STROKE);
    g2.drawOval(x + 1, y + 1, w2, w2);

    // int w2 = w - 10;
    // g2.setColor(Color.WHITE);
    // g2.fillOval(x + 5, y + 5, w2, w2);

  }
}
