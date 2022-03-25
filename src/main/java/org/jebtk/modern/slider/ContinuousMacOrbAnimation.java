package org.jebtk.modern.slider;

import java.awt.Color;
import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.HoverFadeAnimation;
import org.jebtk.modern.ribbon.Ribbon;
import org.jebtk.modern.theme.MaterialService;
import org.jebtk.modern.theme.ThemeService;

public class ContinuousMacOrbAnimation extends HoverFadeAnimation {
  private final ContinuousMacOrbSlider mSlider;

  protected static final Color HIGHLIGHT_2 = ThemeService.getInstance().getColors().getTheme32(24);

  public ContinuousMacOrbAnimation(ModernWidget button) {
    super(button);

    mSlider = (ContinuousMacOrbSlider) button;

    setFadeColor("highlight", MaterialService.getInstance().getColor("theme-selected"), Ribbon.BAR_BACKGROUND);
    setFadeColor("line", ModernWidget.LINE_COLOR, ModernWidget.DARK_LINE_COLOR);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.widget.ModernClickWidget#drawBackgroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {
    int p = (int) (mWidget.getInsets().left + mSlider.mSliderDiameter % 2 + mSlider.vToX());

    drawBar(g2, p);
    drawOrb(g2, p);
  }

  private void drawBar(Graphics2D g2, int p) {
    mSlider.drawBarBase(g2, p);

    int x = mSlider.getInsets().left + mSlider.mSliderRadius;
    int y = (mSlider.getHeight() - mSlider.mBarHeight) / 2;

    g2.setColor(getFadeColor("highlight"));

    g2.fillRoundRect(x, y, p - x + mSlider.mSliderRadius, mSlider.mBarHeight, mSlider.mBarHeight, mSlider.mBarHeight);
  }

  private void drawOrb(Graphics2D g2, int p) {
    int y = mSlider.getHeight() / 2;

    g2.setColor(getFadeColor("highlight")); // Color.WHITE);
    g2.fillOval(p, y - mSlider.mSliderRadius, mSlider.mSliderDiameter, mSlider.mSliderDiameter);

    // g2.setColor(getFadeColor("line"));
    // g2.drawOval(p, y - mButton.mSliderRadius,
    // mButton.mSliderDiameter,
    // mButton.mSliderDiameter);
  }
  
  @Override
  public String getName() {
    return "continuous-mac-orb";
  }
}
