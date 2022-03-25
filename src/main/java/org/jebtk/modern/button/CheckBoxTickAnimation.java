package org.jebtk.modern.button;

import java.awt.Color;
import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.core.geom.IntRect;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.WidgetAnimation;
import org.jebtk.modern.theme.DrawUIService;

public class CheckBoxTickAnimation extends WidgetAnimation {
  private static final int TICK_SIZE = 16;

  private static final int OFFSET = (ModernCheckBox.ICON_SIZE - TICK_SIZE) / 2;

  public CheckBoxTickAnimation(ModernWidget widget) {
    super((ModernClickWidget) widget);
  }
  
  @Override
  public String getName() {
    return "checkbox-tick";
  }

  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {
    int x = mWidget.getInsets().left + OFFSET;
    int y = (mWidget.getHeight() - TICK_SIZE) / 2;

    if (mWidget.isEnabled()) {
      if (((ModernClickWidget) getWidget()).isSelected()) {
        // Only draw the tick if the button is selected
        // ModernCheckBox.CHECK_ICON.drawIcon(g2, x, y,
        // ModernCheckBox.ICON_SIZE);

        DrawUIService.getInstance().getRenderer("check").draw(g2, new IntRect(x, y, TICK_SIZE, TICK_SIZE), Color.WHITE);
      }
    }
  }
}
