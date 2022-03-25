package org.jebtk.modern.panel;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

/**
 * Vertically layout components trying to respect their preferred widths and
 * heights.
 * 
 * @author antony
 *
 */
public class HExpandBoxLayout implements LayoutManager {

  @Override
  public void addLayoutComponent(String s, Component c) {
    // TODO Auto-generated method stub

  }

  @Override
  public void layoutContainer(Container parent) {
    Insets insets = parent.getInsets();
    int x = insets.left;
    int y = insets.top;

    int maxWidth = parent.getWidth() - (x + insets.right);
    int maxHeight = parent.getHeight() - (y + insets.bottom);
    int nComps = parent.getComponentCount();

    Component c = parent.getComponent(0);
    c.setBounds(x, y, c.getPreferredSize().width, maxHeight);

    // Determine the offset of the right components
    int w = 0;

    for (int i = 1; i < nComps; ++i) {
      c = parent.getComponent(i);

      w += c.getPreferredSize().width;
    }

    x = x + maxWidth - w;

    for (int i = 1; i < nComps; ++i) {

      c = parent.getComponent(i);
      Dimension d = c.getPreferredSize();

      int h = Math.min(maxHeight, d.height);

      int y2 = y + (maxHeight - h) / 2;

      w = d.width;

      c.setBounds(x, y2, w, h);

      x += w;
    }
  }

  @Override
  public Dimension minimumLayoutSize(Container parent) {
    return preferredLayoutSize(parent);
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

        dim.height = Math.max(dim.height, d.height);

        dim.width += d.width;
      }
    }

    Insets insets = parent.getInsets();

    dim.width += insets.left + insets.right;
    dim.height += insets.top + insets.bottom;

    return dim;
  }
}
