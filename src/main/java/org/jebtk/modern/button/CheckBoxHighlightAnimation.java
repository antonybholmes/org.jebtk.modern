package org.jebtk.modern.button;

import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.core.geom.IntRect;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.WidgetAnimation;
import org.jebtk.modern.theme.DrawUIService;

public class CheckBoxHighlightAnimation extends WidgetAnimation {
  public CheckBoxHighlightAnimation(ModernWidget button) {
    super(button);
  }
  
  @Override
  public String getName() {
    return "checkbox-highlight";
  }

  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {
    if (mWidget.isEnabled()) {
      int x = mWidget.getInsets().left;
      int y = (mWidget.getHeight() - ModernCheckBox.ICON_SIZE) / 2;

      
      
      DrawUIService.getInstance().getRenderer("button-outline").draw(mWidget, g2,
          new IntRect(x, y, ModernCheckBox.ICON_SIZE, ModernCheckBox.ICON_SIZE),
          mWidget.getCSSProps().getColor("border-color"));

      // DrawUIService.getInstance().getRenderer("button-outline").draw(g2,
      // x,
      // y,
      // ModernCheckBox.ICON_SIZE,
      // ModernCheckBox.ICON_SIZE,
      // getFadeColor("fill"));
    }
  }
}
