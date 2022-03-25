package org.jebtk.modern.panel;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

/**
 * Center content vertically and horizontally. Content is left aligned so that
 * the widest component is centered and all others are anchored to its x
 * coordinate.
 * 
 * @author antony
 *
 */
public class VCenterLayout implements LayoutManager {

  private static final Dimension MIN_SIZE = new Dimension(1, 1);

  @Override
  public void addLayoutComponent(String s, Component c) {
    // TODO Auto-generated method stub

  }

  @Override
  public void layoutContainer(Container parent) {
    Insets insets = parent.getInsets();
    int maxWidth = parent.getWidth() - (insets.left + insets.right);
    int maxHeight = parent.getHeight() - (insets.top + insets.bottom);
    int nComps = parent.getComponentCount();
    int x = insets.left;
    int y = insets.top;

    Dimension pref = new Dimension(0, 0);

    for (int i = 0; i < nComps; i++) {
      Component c = parent.getComponent(i);

      if (c.isVisible()) {
        Dimension d = c.getPreferredSize();

        if (d.width > pref.width) {
          pref.width = d.width;
        }

        pref.height += d.height;
      }
    }

    x += (maxWidth - pref.width) / 2;
    y += (maxHeight - pref.height) / 2;

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

  protected Dimension size(Container parent) {
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
