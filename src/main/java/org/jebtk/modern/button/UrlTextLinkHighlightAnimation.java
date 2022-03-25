package org.jebtk.modern.button;

import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.HoverFadeAnimation;

public class UrlTextLinkHighlightAnimation extends HoverFadeAnimation {
  private static final int OFFSET = 2;
  private ModernButton mButton;

  public UrlTextLinkHighlightAnimation(ModernWidget button) {
    super(button);

    mButton = (ModernButton) button;

    setFadeColor("highlight", button.getForeground());
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.widget.ModernClickWidget#drawBackgroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {
    if (getWidget().isEnabled()) { // && (getButton().getHightlighted() ||
      // getButton().getPopupShown())) {
      String text = mButton.getText();

      int x = (mWidget.getWidth() - g2.getFontMetrics().stringWidth(text)) / 2;

      int y = ModernWidget.getTextYPosCenter(g2, mWidget.getHeight()) + OFFSET;

      g2.setColor(getFadeColor("highlight"));
      g2.drawLine(x, y, x + ModernWidget.getStringWidth(g2, text), y);
    }

    // Color c = ColorUtils.getTransparentColor(Color.RED, mTrans);

    // int y = getHeight() / 2;
    //
    // g2.setColor(c);
    // g2.drawLine(0, 0, getWidth(), y);
  }
}
