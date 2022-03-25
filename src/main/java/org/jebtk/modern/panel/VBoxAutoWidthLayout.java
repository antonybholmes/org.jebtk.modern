package org.jebtk.modern.panel;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

public class VBoxAutoWidthLayout implements LayoutManager {

  private static final Dimension MIN_SIZE = new Dimension(1, 1);

  @Override
  public void addLayoutComponent(String s, Component c) {
    // TODO Auto-generated method stub

  }

  @Override
  public void layoutContainer(Container parent) {
    Insets insets = parent.getInsets();
    int maxWidth = parent.getWidth() - (insets.left + insets.right);
    int nComps = parent.getComponentCount();
    int x = insets.left;
    int y = insets.top;

    for (int i = 0; i < nComps; i++) {
      Component c = parent.getComponent(i);
      if (c.isVisible()) {
        Dimension d = c.getPreferredSize();

        c.setBounds(x, y, maxWidth, d.height);

        y += d.height;
      }
    }
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

  private Dimension size(Container parent) {
    int nComps = parent.getComponentCount();
    Dimension dim = new Dimension(0, 0);

    Dimension d;

    for (int i = 0; i < nComps; i++) {
      Component c = parent.getComponent(i);
      if (c.isVisible()) {
        d = c.getPreferredSize();

        dim.height += d.height;
      }
    }

    Insets insets = parent.getInsets();

    dim.width = parent.getWidth();
    dim.height += insets.top + insets.bottom;

    return dim;
  }
}
