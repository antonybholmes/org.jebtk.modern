package org.jebtk.modern.theme;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

import javax.swing.JComponent;
import javax.swing.border.Border;

import org.jebtk.core.ColorUtils;
import org.jebtk.core.geom.IntRect;
import org.jebtk.modern.BorderService;
import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.panel.CardPanel;

public class MaterialUtils {
  public static final Font FONT = MaterialService.getInstance().getFonts().text();

  public static final Font TEXT_FONT = FONT;

  public static final int PADDING = ModernWidget.QUAD_PADDING;

  public static final Border BORDER = BorderService.getInstance().createBorder(PADDING);

  /** The Constant SHADOW_HEIGHT. */
  public static final int SHADOW_HEIGHT = 3;

  public static final int SHADOW_BORDER_HEIGHT = SHADOW_HEIGHT; // +
  // ModernWidget.PADDING;
  // //2 *
  // SHADOW_HEIGHT;

  /** The Constant COLOR. */
  protected static final Color SHADOW_COLOR = Color.BLACK;

  /** The Constant COLOR1. */
  public static final Color SHADOW_COLOR_1 = ColorUtils.getTransparentColor90(SHADOW_COLOR);

  /** The Constant COLOR2. */
  public static final Color SHADOW_COLOR_2 = ColorUtils.getTransparentColor100(SHADOW_COLOR);

  public static final Color HIGHLIGHT_COLOR = ColorUtils.getTransparentColor75(Color.WHITE);

  public static final int SMALL_PADDING = ModernWidget.DOUBLE_PADDING;

  public static final Color BUTTON_COLOR = MaterialService.getInstance().getColor("theme-highlight"); // ColorUtils.getTransparentColor75(Ribbon.BAR_BACKGROUND);

  public static final Color LINE_COLOR = MaterialService.getInstance().getColor("card-border"); // ColorUtils.getTransparentColor50(ModernWidget.LIGHT_LINE_COLOR);

  public static final Color SELECTED_FONT_COLOR = Color.BLACK;

  public static final Color TEXT_COLOR = ModernWidget.TEXT_COLOR;

  private MaterialUtils() {
    // Do nothing
  }

  public static void drawDropShadow(Graphics2D g2, ModernComponent c, int x, int y, int w, int h) {
    drawDropShadow(g2, c, x, y, w, h, SHADOW_HEIGHT);
  }

  /**
   * Draw a standard drop shadow effect at the specified location and dimension.
   * 
   * @param g2
   * @param x
   * @param y
   * @param w
   * @param h
   */
  public static void drawDropShadow(Graphics2D g2, ModernComponent c, int x, int y, int w, int h, int shadow) {

    int sh = 2 * shadow;

    y += h - shadow;

    GradientPaint paint = new GradientPaint(0, y, SHADOW_COLOR_1, 0, y + sh, SHADOW_COLOR_2);

    g2.setPaint(paint);

    // g2.setPaint(Color.RED);

    // WidgetRendererService.getInstance().getRenderer()
    // .fill(g2, x + 1, y, w - 2, sh);

    DrawUIService.getInstance().getRenderer("button-fill").fill(c, g2, new IntRect(x + 1, y, w - 2, sh));

    // int a = Math.max(1, h / 2);
    // g2.fillRoundRect(x, y, w, h, a, a);
    // g2.fillRect(x + 1, y, w - 2, h);
  }

  public static void drawCard(Graphics2D g2, ModernComponent c, int x, int y, int w, int h) {
    drawBorderlessCard(g2, c, x, y, w, h);

    g2.setColor(LINE_COLOR);

    // WidgetRendererService.getInstance().getRenderer().outline(g2,
    // x,
    // y,
    // w,
    // h);

    h -= MaterialUtils.SHADOW_BORDER_HEIGHT;

    g2.drawLine(1, 0, w - 2, 0);
    g2.drawLine(0, 1, 0, h - 2);

    x = w - 1;
    g2.drawLine(x, 1, x, h - 2);

    y = y + h;
    g2.drawLine(1, y, w - 2, y);
  }

  public static void drawBorderlessCard(Graphics2D g2, ModernComponent c, int x, int y, int w, int h) {

    h -= MaterialUtils.SHADOW_BORDER_HEIGHT;

    drawDropShadow(g2, c, 0, y, w, h);

    g2.setColor(Color.WHITE);
    g2.fillRect(x + 1, y + 1, w - 2, h - 2);

  }

  public static JComponent mainCard(Component c) {
    return new ModernComponent(new CardPanel(c), MaterialUtils.SMALL_PADDING);
  }
}
