package org.jebtk.modern.panel;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

import org.jebtk.core.Mathematics;

/**
 * Align component in center of screen and dynamically change its width to be a
 * percentage of its parent.
 *
 */
public class HAutoStretchLayout implements LayoutManager {

  private static final Dimension MIN_SIZE = new Dimension(1, 1);

  private HAlignment mAlign = HAlignment.CENTER;

  private double mPW;

  private double mMW;

  public HAutoStretchLayout(double width) {
    this(width, HAlignment.CENTER);
  }

  public HAutoStretchLayout(double width, HAlignment align) {
    this(width, 0.1, align);
  }

  public HAutoStretchLayout(double width, double min, HAlignment align) {
    mPW = Mathematics.bound(width, 0, 1);
    mMW = min;
    mAlign = align;
  }

  @Override
  public void addLayoutComponent(String s, Component c) {
    // TODO Auto-generated method stub

  }

  @Override
  public void layoutContainer(Container parent) {
    Insets insets = parent.getInsets();
    int maxWidth = parent.getWidth() - (insets.left + insets.right);
    int maxHeight = parent.getHeight() - (insets.top + insets.bottom);
    int x = insets.left;
    int y = insets.top;

    Component c = parent.getComponent(0);

    int w = (int) Math.round(maxWidth * Math.max(mMW, mPW));
    int h = Math.min(c.getPreferredSize().height, maxHeight);

    y = (parent.getHeight() - h) / 2;

    switch (mAlign) {
    case CENTER:
      x += (maxWidth - w) / 2;
      break;
    case RIGHT:
      x = parent.getWidth() - insets.right - maxWidth;
      break;
    default:
      break;
    }

    c.setBounds(x, y, w, h);
  }

  @Override
  public Dimension minimumLayoutSize(Container parent) {
    return MIN_SIZE;
  }

  @Override
  public Dimension preferredLayoutSize(Container parent) {
    return size(parent);
  }

  @Override
  public void removeLayoutComponent(Component comp) {
    // TODO Auto-generated method stub
  }

  protected Dimension size(Container parent) {
    Dimension dim = new Dimension(0, 0);

    Component c = parent.getComponent(0);

    dim.width = parent.getWidth();
    dim.height = c.getHeight();

    Insets insets = parent.getInsets();

    dim.width += insets.left + insets.right;
    dim.height += insets.top + insets.bottom;

    return dim;
  }
}
