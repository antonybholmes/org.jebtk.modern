package org.jebtk.modern.theme;

import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.core.geom.IntRect;
import org.jebtk.modern.ModernComponent;

public class ContentBoxUI extends ContentOutlineUI {
  @Override
  public String getName() {
    return "content-box";
  }

  @Override
  public void draw(ModernComponent c, Graphics2D g2, IntRect rect, Props props) {
    // g2.setColor(Color.WHITE);
    
    setColor("background-color", c, g2, props);
    
    fill(c, g2, rect, props);

    super.draw(c, g2, rect, props);
  }
}
