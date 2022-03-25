package org.jebtk.modern.button;

import java.awt.Graphics2D;
import org.jebtk.core.Props;

import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.WidgetAnimation;
import org.jebtk.modern.theme.DrawUIService;

public class PillOutlineAnimation extends WidgetAnimation {
  public PillOutlineAnimation(ModernWidget button) {
    super(button);
  }

  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {
    // if (mode == RenderMode.NONE && !hasFocus) {
    // return;
    // }

    DrawUIService.getInstance().getRenderer("pill-outline").draw(c, g2, 
            c.getCSSProps().getColor("border-color", props.getInt("frame")));

    // g2.setColor(getFadeColor("outline"));

    // outline(g2, x, y, w, h);
  }

  // public void outline(Graphics2D g2, int x, int y, int w, int h) {
  // getWidget().getWidgetRenderer().outline(g2, x, y, w, h);
  //
  // UIDrawService.getInstance().get("button-outline").draw(g2, x, y, w, h,
  // getFadeColor("outline"));
  // }
}
