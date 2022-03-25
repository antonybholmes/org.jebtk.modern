package org.jebtk.modern.panel;

import java.awt.Component;

import javax.swing.border.Border;

import org.jebtk.modern.ModernComponent;

public class CardPanel2 extends ModernComponent {

  private static final long serialVersionUID = 1L;

  public CardPanel2(Component c) {
    this(c, DOUBLE_BORDER);
  }

  public CardPanel2(Component c, Border border) {
    super(new ModernComponent(c, border));

    addStyleClass("card");
  }

//  @Override
//  public void drawBackground(Graphics2D g2) {
//    Graphics2D g2Temp = ImageUtils.createAAGraphics(g2);
//
//    try {
//      DrawUIService.getInstance().getRenderer("card").draw(this, g2Temp);
//    } finally {
//      g2Temp.dispose();
//    }
//  }
}
