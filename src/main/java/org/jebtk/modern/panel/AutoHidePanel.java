/**
 * Copyright 2017 Antony Holmes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jebtk.modern.panel;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.border.Border;

import org.jebtk.modern.AssetService;
import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.graphics.icons.CheveronRightVectorIcon;
import org.jebtk.modern.graphics.icons.ModernIcon;

/**
 * The Class AutoHidePanel automatically hides its contents when it gets too
 * thin.
 */
public class AutoHidePanel extends ModernComponent {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The m min width. */
  private int mMinWidth;

  /** The m hidden. */
  private boolean mHidden = false;

  /** The m C. */
  private Component mC;

  /** The icon. */
  private ModernIcon ICON = AssetService.getInstance().loadIcon(CheveronRightVectorIcon.class, 12);

  /**
   * Instantiates a new auto hide panel.
   *
   * @param c        the c
   * @param minWidth the min width
   */
  public AutoHidePanel(Component c, int minWidth) {
    mC = c;
    mMinWidth = minWidth;

    setBody(c);

    addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        autoHide();
      }
    });
  }

  public AutoHidePanel(Component c, int minWidth, Border border) {
    this(c, minWidth);

    setBorder(border);
  }

  /**
   * Auto hide.
   */
  private void autoHide() {
    boolean hidden = getWidth() < mMinWidth;

    if (hidden != mHidden) {
      mHidden = hidden;

      mC.setVisible(!hidden);

      revalidate();
      repaint();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.jebtk.modern.ModernComponent#drawForeground(java.awt.Graphics2D)
   */
  @Override
  public void drawForeground(Graphics2D g2) {
    if (mHidden) {
      int x = (getWidth() - ICON.getWidth()) / 2;
      ICON.drawIcon(g2, x, DOUBLE_PADDING, 12);
    }
  }

}
