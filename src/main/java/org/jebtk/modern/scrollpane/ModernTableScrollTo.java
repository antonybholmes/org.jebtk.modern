/**
 * Copyright (c) 2016, Antony Holmes
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
package org.jebtk.modern.scrollpane;

import org.jebtk.core.geom.IntRect;

/**
 * Determines what happens when the component requests a scrollto action.
 *
 * @see ModernTableSelectionScrollEvent
 */
public class ModernTableScrollTo {

  private ModernScrollPane mScrollPane;

  public ModernTableScrollTo(ModernScrollPane scrollPane) {
    mScrollPane = scrollPane;
  }

  public void scrollTo(IntRect rect) {
    ModernScrollBar vscroll = mScrollPane.getVScrollBar();
    ModernScrollBar hscroll = mScrollPane.getHScrollBar();

    vscroll.setScrollPosition(scrollY(rect.y + 2 * rect.h));
    hscroll.setScrollPosition(scrollX(rect.x + 2 * rect.w));
  }

  private int scrollX(int d) {
    return scroll(mScrollPane.mViewport.getWidth(), d);
  }

  private int scrollY(int d) {
    return scroll(mScrollPane.mViewport.getHeight(), d);
  }

  private static int scroll(int h, int d) {
    // The view port must have positive size for the scroll to work
    if (h < 1) {
      return 0;
    }

    // h = Math.max(0, h);

    // TODO: Are there too many scroll events?
    // System.err.println(" normY " + h);

    return Math.max(0, d - h); // .getScrollDistance());
  }
}
