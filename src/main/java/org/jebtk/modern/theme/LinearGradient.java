package org.jebtk.modern.theme;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

import org.jebtk.modern.ModernComponent;

public class LinearGradient implements ColorGradient {

  public static final ColorGradient BLACK_WHITE = new LinearGradient(0, Color.BLACK, Color.WHITE);
  public static final ColorGradient BLUE_RED = new LinearGradient(0, Color.BLUE, Color.RED);

  private static final double OFFSET = Math.toRadians(270);
  private double mX;
  private double mY;
  private Color mC1;
  private Color mC2;

  public LinearGradient(int angle, Color c1, Color c2) {
    mC1 = c1;
    mC2 = c2;

    mX = Math.acos(Math.toRadians(angle + OFFSET));
    mY = Math.asin(Math.toRadians(angle + OFFSET));
  }

  @Override
  public void paint(Graphics2D g2, ModernComponent c) {

    int x1;
    int x2;
    if (mX > 0) {
      x1 = c.getInsets().left;
      x2 = x1 + c.getInternalRect().w;
    } else if (mX < 0) {
      x2 = c.getInsets().left;
      x1 = x2 + c.getInternalRect().w;
    } else {
      x1 = 0;
      x2 = 0;
    }

    int y1;
    int y2;
    if (mY < 0) {
      y1 = c.getInsets().left;
      y2 = x1 + c.getInternalRect().w;
    } else if (mY > 0) {
      y2 = c.getInsets().left;
      y1 = x2 + c.getInternalRect().w;
    } else {
      y1 = 0;
      y2 = 0;
    }

    GradientPaint gp = new GradientPaint(x1, y1, mC1, x2, y2, mC2);

    g2.setPaint(gp);
  }

}
