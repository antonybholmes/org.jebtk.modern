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
package org.jebtk.modern.button;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.jebtk.modern.dialog.ModernDialogButton;

import org.jebtk.modern.graphics.icons.ModernIcon;


/**
 * Simulates a hyperlink using a button.
 * 
 * @author Antony Holmes
 *
 */
public class ModernLinkButton extends ModernDialogButton {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The class MouseEvents.
   */
  private class MouseEvents extends MouseAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseEntered(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseEntered(MouseEvent e) {
      setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseExited(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseExited(MouseEvent e) {
      setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
  }

  /**
   * Instantiates a new modern link button.
   *
   * @param title the title
   */
  public ModernLinkButton(String title) {
    super(title);

    init();
  }

  /**
   * Instantiates a new modern link button.
   *
   * @param title the title
   * @param icon  the icon
   */
  public ModernLinkButton(String title, ModernIcon icon) {
    super(title, icon);

    init();
  }

  /**
   * Inits the.
   */
  private void init() {
    addMouseListener(new MouseEvents());
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.button.ModernButtonWidget#drawBackground(java.awt.
   * Graphics2D)
   */
  // @Override
  // public void drawBackground(Graphics2D g2) {
  // // Do nothing
  // }

  /*
   * @Override public void drawText(Graphics2D g2) { if (mText1 != null) { int x;
   * 
   * if (mIcon != null) { x = mIcon.getWidth() + DOUBLE_PADDING; } else { x =
   * (getWidth() - g2.getFontMetrics().stringWidth(mText1)) / 2; }
   * 
   * int y = getTextYPosCenter(g2, getHeight());
   * 
   * boolean underline = mHighlight || isSelected();
   * 
   * g2.setFont(FONT); //underline ? UNDER_LINE_FONT : FONT); g2.setColor(COLOR);
   * g2.drawString(mText1, x, y);
   * 
   * // I don't line the way underline fonts are rendered so do it // manually if
   * (underline) { y += LINE_OFFSET;
   * 
   * g2.drawLine(x, y, x + getStringWidth(g2, mText1), y); } } }
   */
}
