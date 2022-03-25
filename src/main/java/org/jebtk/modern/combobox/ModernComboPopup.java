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
package org.jebtk.modern.combobox;

import java.awt.Dimension;

import javax.swing.JComponent;

import org.jebtk.modern.menu.ModernScrollPopupMenu;

// TODO: Auto-generated Javadoc
/**
 * The class ModernComboPopup.
 */
public class ModernComboPopup extends ModernScrollPopupMenu {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new modern combo popup.
   */
  public ModernComboPopup() {

  }

  /**
   * Instantiates a new modern combo popup.
   *
   * @param maxHeight the max height
   */
  public ModernComboPopup(int maxHeight) {
    super(maxHeight);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.menu.ModernScrollPopupMenu#showPopup(javax.swing.
   * JComponent)
   */
  @Override
  public void showPopup(JComponent component) {

    int h = 0;
    int w = component.getWidth() - 2;

    for (int i = 0; i < this.mScrollMenuPanel.getComponentCount(); ++i) {
      w = Math.max(w, this.mScrollMenuPanel.getComponent(i).getPreferredSize().width);

      h += this.mScrollMenuPanel.getComponent(i).getPreferredSize().height;
    }

    mScrollPane.setPreferredSize(new Dimension(w, Math.min(h, mMaxHeight)));

    /*
     * int border = 0;
     * 
     * if (this.menuPanel.getComponentCount() > maxRows) { // the scroll bar will be
     * displayed so adjust the border border += ModernScrollPane.SCROLLBAR_WIDTH;
     * //scrollPane.getVerticalScrollBar().getMaximumSize().width; //w -= 2; }
     * menuPanel.setBorder(BorderService.getInstance().createRightBorder(border) );
     */

    mScrollMenuPanel.setPreferredSize(new Dimension(w, h));

    // window size

    h = 0;

    for (int i = 0; i < this.getComponentCount(); ++i) {
      h += this.getComponent(i).getPreferredSize().height;
    }

    // w += 2;
    h += 2;

    setSize(w, h);

    showPopup(component, component.getInsets().left, component.getHeight());
  }

  /**
   * Clear the scroll menu items.
   */
  public void clear() {
    mScrollMenuPanel.removeAll();

    super.clear();

    add(mScrollPane);
  }
}
