package org.jebtk.modern.spinner;

import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.core.geom.IntRect;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.HoverFadeAnimation;
import org.jebtk.modern.theme.DrawUIService;

public class SpinnerHighlightAnimation extends HoverFadeAnimation {
  private final ModernCompactSpinner mSpinner;

  public SpinnerHighlightAnimation(ModernWidget widget) {
    super(widget);

    mSpinner = (ModernCompactSpinner) widget;

    bind(mSpinner.mField);
  }

  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {
    IntRect r = mWidget.getInternalRect();

    int x = r.getX();
    int y = r.getY();

    IntRect intRect = new IntRect(x, y, mSpinner.mMinButtonX - x - ModernCompactSpinner.BORDER, r.getH());

    // widget.getWidgetRenderer().drawBackground(g2, intRect);

    DrawUIService.getInstance().getRenderer("content").draw(g2, intRect);

    // g2.setColor(getFadeColor("outline"));
    // widget.getWidgetRenderer().outline(g2, intRect);
    DrawUIService.getInstance().getRenderer("button-outline").draw(g2, intRect, getFadeColor("outline"));
  }
  
  @Override
  public String getName() {
    return "spinner-highlight";
  }
}
