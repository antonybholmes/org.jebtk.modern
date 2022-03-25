package org.jebtk.modern.dialog;

import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.button.DropDownButtonAnimation2;
import org.jebtk.modern.button.ModernDropDownWidget2;
import org.jebtk.modern.theme.DrawUIService;

public class FlatDropDownButtonAnimation2 extends DropDownButtonAnimation2 {
  public FlatDropDownButtonAnimation2(ModernDropDownWidget2 button) {
    super(button);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.widget.ModernClickWidget#drawBackgroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {
    // widget.getWidgetRenderer().drawContentBox(g2, widget.getInternalRect());

    DrawUIService.getInstance().getRenderer("content-box").draw(g2, mWidget.getInternalRect());

    super.draw(mWidget, g2, props);
  }
}
