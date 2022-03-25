package org.jebtk.modern.combobox;

import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.core.geom.IntRect;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.WidgetAnimation;

public class ComboBoxArrowAnimation extends WidgetAnimation {
  private final ModernComboBox mCombo;

  public ComboBoxArrowAnimation(ModernWidget combo) {
    super(combo);

    mCombo = (ModernComboBox) combo;
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

    IntRect buttonRect = new IntRect(mCombo.mButtonX, mWidget.getInsets().top, ModernComboBox.BUTTON_WIDTH,
        rect.getH());

    ModernComboBox.DOWN_ARROW_ICON.drawIcon(g2, buttonRect.getX(), buttonRect.getY() + (buttonRect.getH() - 16) / 2,
        16);
  }
}
