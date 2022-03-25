package org.jebtk.modern.button;

import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.core.geom.IntRect;
import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.WidgetAnimation;

public class RadioOutlineAnimation extends WidgetAnimation {
  public RadioOutlineAnimation(ModernWidget button) {
    super(button);
  }

  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {
    if (mWidget.isEnabled() && !mWidget.isSelected()) {
      int x = mWidget.getInsets().left;
      int y = (mWidget.getHeight() - RadioAnimation.RADIO_SIZE) / 2;
      
      g2.setColor(c.getCSSProps().getColor("border-color", props.getInt("frame")));
      
      outline(mWidget, g2, new IntRect(x, y, RadioAnimation.RADIO_SIZE, RadioAnimation.RADIO_SIZE));
    }
  }

  public void outline(ModernComponent c, Graphics2D g2, IntRect rect) {
    // Do nothing

    int wf = (int) (rect.w * RadioAnimation.RADIO_SCALE / 2) * 2;

    int x = rect.x + (rect.w - wf) / 2;
    int y = rect.y + (rect.h - wf) / 2;

     // SELECTED_OUTLINE_COLOR);
    g2.drawOval(x, y, wf, wf);
  }
}
