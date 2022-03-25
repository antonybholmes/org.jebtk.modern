package org.jebtk.modern.collapsepane;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import org.jebtk.core.Props;
import org.jebtk.core.event.ChangeEvent;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.animation.RotationAnimation;
import org.jebtk.modern.graphics.ImageUtils;

public class CollapsePaneExpandAnimation extends RotationAnimation {

  private final ModernSubCollapsePane mPane;

  public CollapsePaneExpandAnimation(ModernWidget widget) {
    super(widget, 360, 180);

    mPane = (ModernSubCollapsePane) widget;

    mPane.addChangeListener((ChangeEvent e) -> {
      restart();
    });

    // restart();
  }

  @Override
  public void restart() {
    boolean expanded = mPane.mExpanded.get(mPane.getSelectedIndex());

    // If we are expanded then the animation must be reversed
    restart(expanded);
  }

  @Override
  public void draw(ModernWidget c, Graphics2D g2, Props props) {
    Rectangle r = new Rectangle(mWidget.getInsets().left, mWidget.getInsets().top,
        mWidget.getWidth() - mWidget.getInsets().left - mWidget.getInsets().right, mPane.mHeaderHeight);

    Graphics2D g2Temp = ImageUtils.clone(g2);

    int d = (mPane.mHeaderHeight - ModernCollapseNodeRenderer.ICON_SIZE) / 2;

    int xt = mWidget.getWidth() - mWidget.getInsets().right - ModernCollapseNodeRenderer.ICON_SIZE - d;
    int yt = r.y + d;
    int mid = ModernCollapseNodeRenderer.ICON_SIZE / 2;

    try {
      g2Temp.translate(xt, yt);

      for (int i = 0; i < mPane.mTabNames.size(); ++i) {

        Graphics2D g2Temp2 = ImageUtils.clone(g2Temp);

        try {
          g2Temp2.translate(mid, mid);

          if (i == mPane.getSelectedIndex()) {
            g2Temp2.rotate(getAngle());
          } else {
            boolean expanded = mPane.mExpanded.get(i);

            if (expanded) {
              g2Temp2.rotate(getEndAngle());
            }
          }

          g2Temp2.translate(-mid, -mid);

          ModernCollapseNodeRenderer.BRANCH_CLOSED_ICON.drawIcon(g2Temp2, 0, 0, ModernCollapseNodeRenderer.ICON_SIZE);

        } finally {
          g2Temp2.dispose();
        }

        g2Temp.translate(0,
            mPane.mHeaderHeight + (mPane.mExpanded.get(i) ? mPane.mComponents.get(i).getPreferredSize().height : 0));
      }
    } finally {
      g2Temp.dispose();
    }
  }
}
