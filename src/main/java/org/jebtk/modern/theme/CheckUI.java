package org.jebtk.modern.theme;

import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import org.jebtk.core.Props;
import org.jebtk.core.geom.IntRect;
import org.jebtk.core.settings.SettingsService;
import org.jebtk.modern.graphics.ImageUtils;

public class CheckUI extends DrawUI {
  public static final double CHECK_SCALE = SettingsService.getInstance()
      .getDouble("theme.icons.check-icon.width-scale");

  /**
   * The constant TICK_SCALE.
   */
  public static final double TICK_SCALE = SettingsService.getInstance().getDouble("theme.icons.check-icon.tick-scale");

  @Override
  public String getName() {
    return "check";
  }

  @Override
  public void draw(Graphics2D g2, IntRect rect, Props props) {

    double wf = rect.w * CHECK_SCALE;
    double t = wf * TICK_SCALE;

    double xf = rect.x + (rect.w - wf) / 2;
    double yf = rect.y + (rect.h - wf) / 2;

    GeneralPath gp = new GeneralPath();

    gp.moveTo(xf, yf + wf - t);
    gp.lineTo(xf + t, yf + wf);
    gp.lineTo(xf + wf, yf);

    Graphics2D g2Temp = ImageUtils.createAATextGraphics(g2);

    try {
      if (props != null) {
        g2Temp.setColor(props.getColor("color"));
      } else {
        g2Temp.setColor(MaterialService.TEXT_COLOR);
      }

      g2Temp.setStroke(ModernTheme.DOUBLE_LINE_STROKE);

      g2Temp.draw(gp);
    } finally {
      g2Temp.dispose();
    }
  }
}
