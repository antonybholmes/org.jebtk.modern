package org.jebtk.modern.button;

import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.theme.DrawUIService;

public class CircleOutlineAnimation extends ButtonCSSAnimation {
  public CircleOutlineAnimation(ModernWidget button) {
    super(button);
  }

  @Override
  public String getName() {
    return "circle-outline";
  }

  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {
    if (getWidget().isEnabled()) {

      DrawUIService.getInstance().getRenderer("circle-outline").draw(mWidget, 
              g2,
              mWidget.getCSSProps().getColor("border-color", props.getInt("frame")));

      /*
       * Graphics2D g2Temp = ImageUtils.createAAStrokeGraphics(g2);
       * 
       * try { if (widget instanceof ModernClickWidget) { if (((ModernClickWidget)
       * widget).isSelected()) {
       * g2Temp.setColor(ModernWidgetRenderer.SELECTED_FILL_COLOR); } else {
       * g2Temp.setColor(getFadeColor("fill")); } } else {
       * g2Temp.setColor(getFadeColor("fill")); }
       * 
       * IntRect rect = getWidget().getInternalRect();
       * 
       * g2Temp.fillOval(rect.getX(), rect.getY(), rect.getW(), rect.getH()); }
       * finally { g2Temp.dispose(); }
       */
    }
  }
}
