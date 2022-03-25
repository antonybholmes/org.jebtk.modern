package org.jebtk.modern;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

public class ColumnLayout implements LayoutManager {

  private int mHeight;
  private int mWidth;
  private int mCols;
  private int mMaxIndex;

  public ColumnLayout(int cols, int w, int height) {
    mCols = cols;
    mMaxIndex = cols - 1;
    mWidth = w;
    mHeight = height;
  }

  @Override
  public void addLayoutComponent(String name, Component comp) {
    // TODO Auto-generated method stub

  }

  @Override
  public void layoutContainer(Container parent) {
    int x = parent.getInsets().left;
    int y = parent.getInsets().top;

    for (int i = 0; i < parent.getComponentCount(); ++i) {
      parent.getComponent(i).setBounds(x, y, mWidth, mHeight);

      x += mWidth;

      if (i % mCols == mMaxIndex) {
        x = parent.getInsets().left;
        y += mHeight;
      }
    }
  }

  @Override
  public Dimension minimumLayoutSize(Container parent) {
    return getSize(parent);
  }

  @Override
  public Dimension preferredLayoutSize(Container parent) {
    return getSize(parent);
  }

  private Dimension getSize(Container parent) {
    // int w = parent.getWidth() - parent.getInsets().left -
    // parent.getInsets().right;

    // cw = w / 3;

    int o = parent.getComponentCount() % mCols != 0 ? 1 : 0;

    int h = (parent.getComponentCount() / mCols + o) * mHeight + parent.getInsets().top + parent.getInsets().bottom;

    return new Dimension(mWidth * mCols + parent.getInsets().left + parent.getInsets().right, h);
  }

  @Override
  public void removeLayoutComponent(Component comp) {
    // TODO Auto-generated method stub

  }

}
