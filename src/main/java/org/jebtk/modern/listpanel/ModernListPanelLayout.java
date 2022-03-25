package org.jebtk.modern.listpanel;

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
public class ModernListPanelLayout implements LayoutManager {

  @Override
  public void addLayoutComponent(String s, Component c) {
    // TODO Auto-generated method stub

  }

  @Override
  public void layoutContainer(Container parent) {

    ModernListPanel ml = (ModernListPanel) parent;

    Insets insets = parent.getInsets();
    int maxWidth = parent.getWidth() - (insets.left + insets.right);
    int x = insets.left;
    int y = insets.top;

    for (ModernListPanelItem item : ml) {
      int h = item.getPreferredSize().height;

      item.setBounds(x, y, maxWidth, h);

      y += h;
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

        dim.height += d.height;

        if (d.width > dim.width) {
          dim.width = d.width;
        }
      }
    }

    Insets insets = parent.getInsets();

    dim.width += insets.left + insets.right;
    dim.height += insets.top + insets.bottom;

    return dim;
  }
}
