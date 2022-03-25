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

import java.awt.Component;

/**
 * The class Scroller controls how the component behaves during scrolling. This
 * is so that for example, tables can scroll by rows rather than pixels for a
 * more aesthetic interaction.
 */
public class Scroller {

  /**
   * Default to dividing the scrollable distance into 20 segments when using the
   * wheel.
   */
  private static final double NOTCH_SCROLL = 0.05;

  /**
   * Should cause the scroll pane to scroll in the given direction.
   *
   * @param notches   The number of notches the wheel scrolled by. This can be
   *                  positive (down/right) or negative (up/left) to indicate
   *                  scroll direction.
   * @param c         The component scrolled.
   * @param scrollbar the scrollbar
   */
  public void wheelScroll(int notches, Component c, ModernScrollBar scrollbar) {
    scrollbar.incNormalizedScrollPosition(notches * NOTCH_SCROLL);
  }

  /**
   * Respond to when the user drags the scroll bar.
   * 
   * @param x              The current position in pixels.
   * @param startx         The starting position in pixels.
   * @param scrollPosition The position of the scroll bar when the drag started.
   * @param c              The scroll component.
   * @param scrollbar      The scroll bar.
   */
  public void drag(int x, int startx, double scrollPosition, Component c, ModernScrollBar scrollbar) {
    double diff = (double) (x - startx) / scrollbar.getScrollPixels();

    scrollbar.setNormalizedScrollPosition(scrollPosition + diff);
  }

  /**
   * Respond to user pressing a key.
   *
   * @param up        True if the up/left key was pressed.
   * @param c         the c
   * @param scrollbar the scrollbar
   */
  public void keyScroll(boolean up, Component c, ModernScrollBar scrollbar) {
    scrollbar.incNormalizedScrollPosition((up ? -1 : 1) * NOTCH_SCROLL);
  }
}
