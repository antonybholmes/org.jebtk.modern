package org.jebtk.modern.dialog;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;

import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.panel.VCenterLayout;

public class MessageDialogCenterLayout extends VCenterLayout {

  @Override
  public void layoutContainer(Container parent) {
    Insets insets = parent.getInsets();
    int maxWidth = parent.getWidth() - (insets.left + insets.right); // -
    // mIconSize;
    int maxHeight = parent.getHeight() - (insets.top + insets.bottom);
    int nComps = parent.getComponentCount();
    int x = insets.left + ModernWidget.QUAD_PADDING;
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

    // x += (maxWidth - pref.width) / 2;
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
  protected Dimension size(Container parent) {
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

    dim.width = Math.max(dim.width + insets.left + insets.right, parent.getWidth());
    dim.height += insets.top + insets.bottom;

    return dim;
  }
}
