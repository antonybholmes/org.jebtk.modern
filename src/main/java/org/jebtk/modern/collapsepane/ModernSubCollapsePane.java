/**
 * Copyright (C) 2016, Antony Holmes
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *  1. Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *  3. Neither the name of copyright holder nor the names of its contributors 
 *     may be used to endorse or promote products derived from this software 
 *     without specific prior written permission. 
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 */
package org.jebtk.modern.collapsepane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * Allows collapsable panes to be created.
 * 
 * @author Antony Holmes
 *
 */
public class ModernSubCollapsePane extends AbstractCollapsePane {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The highlight index.
   */
  protected int mHighlightIndex = -1;

  /**
   * The class MouseEvents.
   */
  private class MouseEvents extends MouseAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(MouseEvent e) {
      if (e.isPopupTrigger()) {
        return;
      }

      int y = getInsets().top;
      int i = 0;

      while (y <= e.getY() && i < mExpanded.size()) {
        if (y + mHeaderHeight > e.getY()) {
          invertExpanded(i);

          break;
        }

        y += mHeaderHeight + (mExpanded.get(i) ? mComponents.get(i).getPreferredSize().height : 0);

        ++i;
      }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseExited(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseExited(MouseEvent e) {
      mHighlightIndex = -1;

      fireHighlighted();
    }
  }

  /**
   * The class MouseMotionEvents.
   */
  private class MouseMotionEvents extends MouseMotionAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseMotionAdapter#mouseMoved(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseMoved(MouseEvent e) {
      int y = getInsets().top;

      int i = 0;
      int h = -1;

      while (i < mExpanded.size()) {
        if (i >= mComponents.size()) {
          break;
        }

        if (y > e.getY()) {
          break;
        }

        if (y <= e.getY() && y + mHeaderHeight > e.getY()) {
          h = i;
        }

        if (h == mComponents.size() - 1) {
          break;
        }

        y += mHeaderHeight + (mExpanded.get(i) ? mComponents.get(i).getPreferredSize().height : 0);

        ++i;
      }

      if (h != -1 && h != mHighlightIndex) {
        mHighlightIndex = h;

        fireHighlighted();
      }
    }
  }

  /**
   * Instantiates a new modern collapse pane.
   */
  public ModernSubCollapsePane() {
    addMouseListener(new MouseEvents());
    addMouseMotionListener(new MouseMotionEvents());

    setBorder(LEFT_BORDER);

    setHeaderHeight(36);

    setAnimations("sub-collapse-pane");
  }
}
