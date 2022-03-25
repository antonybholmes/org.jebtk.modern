package org.jebtk.modern.ribbon;

import java.awt.Graphics2D;

import org.jebtk.core.Props;
import org.jebtk.core.geom.IntRect;
import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.ModernWidget;
import static org.jebtk.modern.ModernWidget.getTextYPosCenter;
import org.jebtk.modern.css.CSSBaseUI;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.theme.DrawUI;

public class RibbonMenuItemUI extends DrawUI {
  
  @Override
  public void draw(ModernComponent c, Graphics2D g2, IntRect rect, Props props) {
    RibbonMenuItem widget = (RibbonMenuItem) c;
    
    if (widget.isSelected()) {
      props.set("color", RibbonMenuItem.MENU_SELECTED_COLOR);

      CSSBaseUI.cssFill(c, g2, rect, props);
    }
    
    ModernIcon icon = widget.getIcon();
    
    if (icon != null) {
      int x = (widget.getWidth() - icon.getWidth()) / 2;
      int y = widget.getHeight() * 2 / 10;

      icon.drawIcon(g2, x, y, widget.getIcon().getWidth());
    }
    
    g2.setColor(ModernWidget.TEXT_COLOR);

//    g2.setFont(MaterialUtils.FONT);
//    // g2.setColor(TEXT_COLOR); //Color.WHITE);
//
//    if (widget.isSelected()) {
//      g2.setColor(MaterialService.getInstance().getColor("ribbon-menu-font-selected"));
//    } else if (widget.getHightlighted()) {
//      g2.setColor(MaterialService.getInstance().getColor("ribbon-menu-font-highlight"));
//    } else {
//      g2.setColor(MaterialService.getInstance().getColor("ribbon-menu-font"));
//    }

    int x = widget.getWidth() / 5; // getTextXPosCenter(g2, mText1, getWidth());
    int y;

    y = getTextYPosCenter(g2, widget.getHeight());

    g2.drawString(widget.getText(), x, y);
  }

  @Override
  public String getName() {
    return "spinner";
  }
}
