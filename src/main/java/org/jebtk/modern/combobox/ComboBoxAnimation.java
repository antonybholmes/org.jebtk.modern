package org.jebtk.modern.combobox;

import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.core.geom.IntRect;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.WidgetAnimation;
import org.jebtk.modern.theme.DrawUIService;

public class ComboBoxAnimation extends WidgetAnimation {

  public ComboBoxAnimation(ModernWidget combo) {
    super(combo);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.widget.ModernClickWidget#drawBackgroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {
    IntRect rect = mWidget.getInternalRect();

    DrawUIService.getInstance().getRenderer("content-box").draw(mWidget, g2, rect);
  }
}
