package org.jebtk.modern.css;

import java.awt.Graphics2D;
import org.jebtk.core.CSSColor;

import org.jebtk.core.Props;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.HoverAnimation;

public class CSSHoverAnimation extends HoverAnimation {
  public CSSHoverAnimation(ModernWidget w) {
    super(w);
  }

  @Override
  public String getName() {
    return "css-hover";
  }



//  public void fill(ModernWidget c, Graphics2D g2, int x, int y, int w, int h) {
//    DrawUIService.getInstance().getRenderer(NAME)
//        .draw(g2, x, y, w, h, getFadeColor("fill"));
//
//  }
  
  public static CSSColor getColor(String color, ModernWidget c, Props props) {
    return CSSBaseUI.getStyle(c).getColor(color, props.getInt("frame"));
  }
}
