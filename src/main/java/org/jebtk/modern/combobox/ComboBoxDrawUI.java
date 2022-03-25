package org.jebtk.modern.combobox;

import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.core.geom.IntRect;
import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.theme.DrawUI;

public class ComboBoxDrawUI extends DrawUI {
  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.widget.ModernClickWidget#drawBackgroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void draw(ModernComponent c, Graphics2D g2, IntRect rect, Props props) {
    ModernComboBox2 combo = (ModernComboBox2) c;

    // IntRect rect = c.getInternalRect();

    IntRect buttonRect = new IntRect(combo.mButtonX, c.getInsets().top, ModernComboBox.BUTTON_WIDTH, rect.getH());

    ModernComboBox.DOWN_ARROW_ICON.drawIcon(g2, buttonRect.getX(), buttonRect.getY() + (buttonRect.getH() - 16) / 2,
        16);
  }

  @Override
  public String getName() {
    return "combobox2";
  }
}
