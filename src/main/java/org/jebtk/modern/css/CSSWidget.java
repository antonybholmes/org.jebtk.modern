package org.jebtk.modern.css;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import org.jebtk.core.Props;

import org.jebtk.modern.theme.DrawUI;
import org.jebtk.modern.tooltip.ModernToolTipWidget;

// TODO: Auto-generated Javadoc
/**
 * Create a panel with a pill shaped border
 *
 * @author Antony Holmes
 *
 */
public abstract class CSSWidget extends ModernToolTipWidget {

  private static final long serialVersionUID = 1L;

  public CSSWidget() {
    //init();
  }

  public CSSWidget(LayoutManager layout) {
    super(layout);

    //init();
  }
  
  public CSSWidget(Component c) {
    super(c);
  }

//  private void init() {
//    // getAnimations().add("draw-ui");
//    // getDrawStates().add(DrawUIService.getInstance().getRenderer("css-draw"));
//    // addAnimations("css-hover");
//  }

  @Override
  public void drawAnimatedBackground(Graphics2D g2) {
    Props props = new Props();
    
    // Run animation updates
    super.drawAnimatedBackground(g2, props);

    if (mCSSMode) {
      CSS_DRAW.draw(this, g2, props);
    }

    // Run any drawing components that may have changed from animations
    for (DrawUI d : getDrawStates()) {
      d.draw(this, g2, props);
    }
  }

}
