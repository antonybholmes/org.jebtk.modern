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
package org.jebtk.modern.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.ITwoLineWidget;

/**
 * Extended menu item that displays two lines of text and an icon.
 *
 * @author Antony Holmes
 */
public class ModernTwoLineMenuItem extends ModernIconMenuItem implements ITwoLineWidget {

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
    public final void mouseEntered(MouseEvent e) {
      mColor2 = TEXT_COLOR;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseExited(java.awt.event.MouseEvent)
     */
    public final void mouseExited(MouseEvent e) {
      mColor2 = ALT_TEXT_COLOR;
    }
  }

  /**
   * The constant DEFAULT_SIZE.
   */
  public static final Dimension DEFAULT_SIZE = new Dimension(320, 48);

  /**
   * The member color1.
   */
  private Color mColor1;

  /**
   * The member color2.
   */
  private Color mColor2;

  /**
   * The member text2.
   */
  private String mText2;

  // protected JLabel label1 = new JLabel();
  // protected JLabel label2 = new JLabel();

  /**
   * Instantiates a new modern two line menu item.
   *
   * @param text1 the text1
   * @param text2 the text2
   * @param icon  the icon
   */
  public ModernTwoLineMenuItem(String text1, String text2, ModernIcon icon) {
    super(text1, icon);

    setText(text1, text2);

    setup();
  }

  public ModernTwoLineMenuItem(String text1, String text2) {
    super(text1);

    setText(text1, text2);

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    // set a reasonable size but allow for
    // resizing to the parent control
    setMinimumSize(DEFAULT_SIZE);
    setPreferredSize(DEFAULT_SIZE);
    setMaximumSize(MAX_SIZE_48);

    removeAll();

    // label1.setBorder(BorderService.getInstance().createLeftBorder(TEXT_OFFSET));
    // label2.setBorder(BorderService.getInstance().createLeftBorder(TEXT_OFFSET));

    mColor1 = TEXT_COLOR;
    mColor2 = ALT_TEXT_COLOR;

    addMouseListener(new MouseEvents());

    setTextOffset(48);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.TwoLineWidget#setText(java.lang.String,
   * java.lang.String)
   */
  public void setText(String text1, String text2) {
    setText(text1);
    setText2(text2);

    // setActionCommand(text1 + " " + text2);

    repaint();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.TwoLineWidget#setText2(java.lang.String)
   */
  public void setText2(String text) {
    mText2 = text;

    // setActionCommand(text1 + " " + text2);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.TwoLineWidget#getText2()
   */
  public String getText2() {
    return mText2;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.menu.ModernIconMenuItem#drawForegroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {

    int x = mTextOffset;
    int y = 0;

    if (mText1 != null) {
      y += 20;
      // g2.setFont(ThemeService.loadFont("widget.bold-text"));
      g2.setColor(mColor1);
      g2.drawString(mText1, x, y);
    }

    if (mText2 != null) {
      y += 20;
      // g2.setFont(ThemeService.loadFont("widget.text"));
      g2.setColor(mColor2);
      g2.drawString(mText2, x, y);
    }

    if (mIcon != null) {
      int h = mIcon.getHeight();
      int iconX = (mTextOffset - h) / 2;
      int iconY = (getHeight() - h) / 2;

      mIcon.drawIcon(g2, iconX, iconY, h);
    }
  }

}
