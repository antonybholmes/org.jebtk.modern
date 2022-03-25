package org.jebtk.modern.scrollpane;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Rectangle;

public class ModernScrollPaneLayout implements LayoutManager {

  @Override
  public void addLayoutComponent(String name, Component comp) {
    // TODO Auto-generated method stub

  }

  @Override
  public void layoutContainer(Container parent) {

    ModernScrollPane scrollPane = (ModernScrollPane) parent;

    showScrollbars(scrollPane);

    adjustComponentSize(scrollPane);

    setupScrollBars(scrollPane);
  }

  @Override
  public Dimension minimumLayoutSize(Container parent) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Dimension preferredLayoutSize(Container parent) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void removeLayoutComponent(Component comp) {
    // TODO Auto-generated method stub

  }

  private void showScrollbars(ModernScrollPane scrollPane) {
    if (scrollPane.mComponent == null) {
      return;
    }

    //
    // Vertical bars
    //

    switch (scrollPane.mVScrollBarPolicy) {
    case AUTO:
      scrollPane.mVScrollBarNeeded = scrollPane.vScrollbarNeeded();
      scrollPane.mVScrollBar.setVisible(scrollPane.mVScrollBarNeeded);
      break;
    case AUTO_SHOW:
      scrollPane.mVScrollBarNeeded = scrollPane.vScrollbarNeeded();
      scrollPane.mVScrollBar.setVisible(scrollPane.mVScrollBarNeeded && scrollPane.mHover);
      break;
    case ALWAYS:
      scrollPane.mVScrollBarNeeded = true;
      scrollPane.mVScrollBar.setVisible(true);
      break;
    default:
      scrollPane.mVScrollBarNeeded = false;
      scrollPane.mVScrollBar.setVisible(false);
      break;
    }

    //
    // Horizontal bars
    //

    switch (scrollPane.mHScrollBarPolicy) {
    case AUTO:
      scrollPane.mHScrollBarNeeded = hScrollbarNeeded(scrollPane);
      scrollPane.mHScrollBar.setVisible(scrollPane.mHScrollBarNeeded);
      break;
    case AUTO_SHOW:
      scrollPane.mHScrollBarNeeded = hScrollbarNeeded(scrollPane);
      scrollPane.mHScrollBar.setVisible(scrollPane.mHScrollBarNeeded && scrollPane.mHover);
      break;
    case ALWAYS:
      scrollPane.mHScrollBarNeeded = true;
      scrollPane.mHScrollBar.setVisible(true);
      break;
    default:
      scrollPane.mHScrollBarNeeded = false;
      scrollPane.mHScrollBar.setVisible(false);
      break;
    }
  }

  private void adjustComponentSize(ModernScrollPane scrollPane) {
    if (scrollPane.mComponent == null) {
      return;
    }

    int h = scrollPane.mInternalRect.getH();

    int border = (scrollPane.mShowClipBorder ? 1 : 0);

    if (scrollPane.mTopHeader != null) {
      Rectangle b = new Rectangle(scrollPane.mLeftHeaderOffset + border, border,
          scrollPane.mInternalRect.getW() - scrollPane.mLeftHeaderOffset - (scrollPane.mShowClipBorder ? 3 : 0),
          scrollPane.mTopHeaderOffset);

      // If the scroll bars are visible or auto show is true then resize
      // the components to allow for this
      if (scrollPane.mVScrollBarLocation == ScrollBarLocation.SIDE) {
        if (scrollPane.mVScrollBar.isVisible() || scrollPane.mVScrollBarPolicy == ScrollBarPolicy.AUTO_SHOW) {
          b.width -= scrollPane.mVScrollBar.getFixedDim();
        }
      }

      scrollPane.mTopHeader.setBounds(b);
    }

    if (scrollPane.mLeftHeader != null) {
      Rectangle b = new Rectangle(border, scrollPane.mTopHeaderOffset + border, scrollPane.mLeftHeaderOffset,
          h - scrollPane.mTopHeaderOffset - (scrollPane.mShowClipBorder ? 3 : 0));

      if (scrollPane.mHScrollBarPosition == ScrollBarLocation.SIDE) {
        if (scrollPane.mHScrollBar.isVisible() || scrollPane.mHScrollBarPolicy == ScrollBarPolicy.AUTO_SHOW) {
          b.height -= scrollPane.mHScrollBar.getFixedDim();
        }

        if (scrollPane.mFooter != null) {
          b.height -= scrollPane.mFooterOffset;
        }
      }

      scrollPane.mLeftHeader.setBounds(b);
    }

    if (scrollPane.mFooter != null) {
      Rectangle b = new Rectangle(scrollPane.mLeftHeaderOffset + border, h - scrollPane.mFooterOffset + border,
          scrollPane.mInternalRect.getW() - scrollPane.mLeftHeaderOffset - border, scrollPane.mFooterOffset);

      if (scrollPane.mVScrollBarLocation == ScrollBarLocation.SIDE) {
        if (scrollPane.mVScrollBar.isVisible() || scrollPane.mVScrollBarPolicy == ScrollBarPolicy.AUTO_SHOW) {
          b.width -= scrollPane.mVScrollBar.getFixedDim();
        }

        if (scrollPane.mHScrollBar.isVisible() || scrollPane.mHScrollBarPolicy == ScrollBarPolicy.AUTO_SHOW) {
          b.y -= scrollPane.mHScrollBar.getFixedDim();
        }
      }

      scrollPane.mFooter.setBounds(b);
    }

    if (scrollPane.mLeftHeader != null && scrollPane.mTopHeader != null && scrollPane.mTopLeftHeader != null) {
      Rectangle b = new Rectangle(border, border, scrollPane.mLeftHeaderOffset, scrollPane.mTopHeaderOffset);

      scrollPane.mTopLeftHeader.setBounds(b);
    }

    if (scrollPane.mLeftHeader != null && scrollPane.mFooter != null && scrollPane.mBottomLeftFooter != null) {
      Rectangle b = new Rectangle(border, h - scrollPane.mFooterOffset + border, scrollPane.mLeftHeaderOffset,
          scrollPane.mFooterOffset);

      if (scrollPane.mHScrollBarPosition == ScrollBarLocation.SIDE) {
        if (scrollPane.mHScrollBar.isVisible() || scrollPane.mHScrollBarPolicy == ScrollBarPolicy.AUTO_SHOW) {
          b.y -= scrollPane.mHScrollBar.getFixedDim();
        }
      }

      scrollPane.mBottomLeftFooter.setBounds(b);
    }

    Rectangle b = new Rectangle((scrollPane.mLeftHeader != null ? scrollPane.mLeftHeaderOffset : 0) + border,
        (scrollPane.mTopHeader != null ? scrollPane.mTopHeaderOffset : 0) + border,
        scrollPane.mInternalRect.getW() - (scrollPane.mLeftHeader != null ? scrollPane.mLeftHeaderOffset : 0)
            - (scrollPane.mShowClipBorder ? 2 : 0) - border,
        h - (scrollPane.mTopHeader != null ? scrollPane.mTopHeaderOffset : 0)
            - (scrollPane.mFooter != null ? scrollPane.mFooterOffset : 0) - (scrollPane.mShowClipBorder ? 2 : 0)
            - border);

    if (scrollPane.mVScrollBarLocation == ScrollBarLocation.SIDE) {
      if (scrollPane.mVScrollBar.isVisible() || scrollPane.mVScrollBarPolicy == ScrollBarPolicy.AUTO_SHOW) {
        b.width -= scrollPane.mVScrollBar.getFixedDim() + scrollPane.mVScrollSep;
      }
    }

    if (scrollPane.mHScrollBarPosition == ScrollBarLocation.SIDE) {
      if (scrollPane.mHScrollBar.isVisible() || scrollPane.mHScrollBarPolicy == ScrollBarPolicy.AUTO_SHOW) {
        b.height -= scrollPane.mHScrollBar.getFixedDim() + scrollPane.mHScrollSep;
      }
    }

    // Causes rendering errors
    // mComponent.setBounds(b);

    scrollPane.mViewport.setBounds(b);
  }

  private void setupScrollBars(ModernScrollPane scrollPane) {
    vScrollBarSetup(scrollPane);

    hScrollBarSetup(scrollPane);
  }

  private void vScrollBarSetup(ModernScrollPane scrollPane) {

    // if (mVScrollBar.isVisible()) {
    Rectangle r = new Rectangle(scrollPane.mInternalRect.getW() - scrollPane.mVScrollBar.getFixedDim(), 0,
        scrollPane.mVScrollBar.getFixedDim(), scrollPane.mInternalRect.getH()
            - (scrollPane.mHScrollBar.isVisible() ? scrollPane.mHScrollBar.getFixedDim() : 0));

    // if (mHScrollBarPosition == ScrollBarPosition.SIDE) {
    // r.height -= (mHScrollBar.isVisible() ? mHScrollBar.getFixedDimension() :
    // 0);
    // }

    scrollPane.mVScrollBar.setBounds(r);

    // }

    int scrollDistance = 0;

    if (scrollPane.mVScrollBarNeeded) {
      // the actual amount of movable space available
      scrollPane.mIntH = scrollPane.getInternalHeight();

      if (scrollPane.mHScrollBarNeeded) {
        scrollPane.mIntH -= scrollPane.mHScrollBar.getFixedDim();
      }

      // How much space we need to scroll through
      // if (mCanvas != null) {
      // scrollDistance = Math.max(0,
      // mCanvas.getCanvasSize().getH() - mIntH);
      // } else {
      // scrollDistance = Math.max(0,
      // mComponent.getPreferredSize().height - mInternalRect.getH());
      // }

      scrollDistance = Math.max(0, scrollPane.mComponent.getPreferredSize().height - scrollPane.mInternalRect.getH());
    }

    scrollPane.mVScrollBar.setScrollDistance(scrollDistance);

    if (!scrollPane.mVScrollBarNeeded) {
      scrollPane.mVScrollBar.updateNormalizedScrollPosition(0);
      scrollPane.setComponentVOffset();
    }

    // mVScrollBar.setVisible(mVScrollBar.isVisible());
  }

  private void hScrollBarSetup(ModernScrollPane scrollPane) {

    // if (mHScrollBar.isVisible()) {
    Rectangle r = new Rectangle(0, scrollPane.mInternalRect.getH() - scrollPane.mHScrollBar.getFixedDim(),
        scrollPane.mInternalRect.getW()
            - (scrollPane.mVScrollBar.isVisible() ? scrollPane.mVScrollBar.getFixedDim() : 0),
        scrollPane.mHScrollBar.getFixedDim());

    // if (mHScrollBarPosition == ScrollBarPosition.SIDE) {
    // r.width -= (mVScrollBar.isVisible() ? mVScrollBar.getFixedDimension() :
    // 0);
    // }

    scrollPane.mHScrollBar.setBounds(r);

    int hScrollDistance = 0;

    if (scrollPane.mHScrollBarNeeded) {
      // the width
      scrollPane.mIntW = scrollPane.getInternalWidth();

      if (scrollPane.mVScrollBarNeeded) {
        // Subtract the fixed dimension if the v scroll bar is visible
        scrollPane.mIntW -= scrollPane.mVScrollBar.getFixedDim();
      }

      // if (mCanvas != null) {
      // The difference between the screen estate and the width
      // of the canvas is the scrollable distance
      // hScrollDistance = Math.max(0,
      // // mCanvas.getCanvasSize().getW() - mIntW);
      // } else {
      // hScrollDistance = Math.max(0,
      /// mComponent.getPreferredSize().width - mInternalRect.getW());
      // }

      hScrollDistance = Math.max(0, scrollPane.mComponent.getPreferredSize().width - scrollPane.mInternalRect.getW());
    }

    scrollPane.mHScrollBar.setScrollDistance(hScrollDistance);

    if (!scrollPane.mHScrollBarNeeded) {
      scrollPane.mHScrollBar.updateNormalizedScrollPosition(0);
      scrollPane.setComponentHOffset();
    }

    // mHScrollBar.setVisible(mHScrollBar.isVisible());
  }

  /**
   * H scrollbar needed.
   *
   * @return true, if successful
   */
  private boolean hScrollbarNeeded(ModernScrollPane scrollPane) {
    int w = scrollPane.mInternalRect.getW() - scrollPane.mLeftHeaderOffset;

    // if (mCanvas != null) {
    // return mCanvas.getCanvasSize().getW() > w;
    // } else {
    // return mComponent.getPreferredSize().width > w;
    // }

    return scrollPane.mComponent.getPreferredSize().width > w;
  }
}
