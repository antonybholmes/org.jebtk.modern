package org.jebtk.modern.slider;

import java.awt.Color;
import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.core.geom.IntRect;
import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.css.CSSBaseUI;

public class ContinuousMacOrbUI extends CSSBaseUI {

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.widget.ModernClickWidget#drawBackgroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void draw(ModernComponent c, Graphics2D g2, IntRect rect, Props props) {
    System.err.println("orb " + props.getInt("frame"));
    
    ContinuousMacOrbSlider slider = (ContinuousMacOrbSlider) c;

    int p = (int) (c.getInsets().left + slider.mSliderDiameter % 2 + slider.vToX());

    drawBar(g2, slider, p, props);
    drawOrb(g2, slider, p, props);
  }

  private void drawBar(Graphics2D g2, ContinuousMacOrbSlider slider, int p, Props props) {
    slider.drawBarBase(g2, p);

    int x = slider.getInsets().left + slider.mSliderRadius;
    int y = (slider.getHeight() - slider.mBarHeight) / 2;

    g2.setColor(Color.WHITE);
    g2.fillRoundRect(x, y, p - x + slider.mSliderRadius, slider.mBarHeight, slider.mBarHeight, slider.mBarHeight);

    setColor("background-color", slider, g2, props);
    g2.fillRoundRect(x, y, p - x + slider.mSliderRadius, slider.mBarHeight, slider.mBarHeight, slider.mBarHeight);
  }

  private void drawOrb(Graphics2D g2, ContinuousMacOrbSlider slider, int p, Props props) {
    int y = slider.getHeight() / 2;

    g2.setColor(Color.WHITE);
    g2.fillOval(p, y - slider.mSliderRadius, slider.mSliderDiameter, slider.mSliderDiameter);

    setColor("background-color", slider, g2, props);
    g2.fillOval(p, y - slider.mSliderRadius, slider.mSliderDiameter, slider.mSliderDiameter);

    // g2.setColor(getFadeColor("line"));
    // g2.drawOval(p, y - mButton.sliderRadius,
    // mButton.sliderDiameter,
    // mButton.sliderDiameter);
  }

  @Override
  public String getName() {
    return "slider";
  }
}
