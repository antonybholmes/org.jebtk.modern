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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.jebtk.modern.AssetService;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.ITwoLineWidget;

/**
 * The class ModernTwoLineCheckButton.
 */
public class ModernTwoLineCheckButton extends ModernCheckButton implements ITwoLineWidget {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The class MouseEvents.
   */
  private class MouseEvents implements MouseListener {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
     */
    public final void mouseEntered(MouseEvent e) {
      color2 = TEXT_COLOR;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
     */
    public final void mouseExited(MouseEvent e) {
      color2 = ALT_TEXT_COLOR;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseClicked(MouseEvent e) {
      // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(MouseEvent e) {
      // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseReleased(MouseEvent e) {
      // TODO Auto-generated method stub

    }
  }

  /**
   * The constant DEFAULT_SIZE.
   */
  public static final Dimension DEFAULT_SIZE = new Dimension(400, AssetService.ICON_SIZE_48);

  /**
   * The color1.
   */
  protected Color color1;

  /**
   * The color2.
   */
  protected Color color2;

  /**
   * The text2.
   */
  private String text2;

  // protected JLabel label1 = new JLabel();
  // protected JLabel label2 = new JLabel();

  /**
   * Instantiates a new modern two line check button.
   */
  public ModernTwoLineCheckButton() {
    setup();
  }

  /**
   * Instantiates a new modern two line check button.
   *
   * @param icon the icon
   */
  public ModernTwoLineCheckButton(ModernIcon icon) {
    super(icon);

    setup();
  }

  /**
   * Instantiates a new modern two line check button.
   *
   * @param text1 the text1
   * @param text2 the text2
   * @param icon  the icon
   */
  public ModernTwoLineCheckButton(String text1, String text2, ModernIcon icon) {
    super(text1, icon);

    setText2(text2);

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    // set a reasonable size but allow for
    // resizing to the parent control
    // setMinimumSize(DEFAULT_SIZE);
    setPreferredSize(DEFAULT_SIZE);
    setMaximumSize(ModernWidget.MAX_SIZE_48);

    color1 = TEXT_COLOR;
    color2 = ALT_TEXT_COLOR;

    addMouseListener(new MouseEvents());
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

    setClickMessage(text1 + " " + text2);

    repaint();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.TwoLineWidget#setText2(java.lang.String)
   */
  public void setText2(String text) {
    this.text2 = text;

    setClickMessage(text + " " + text2);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.TwoLineWidget#getText2()
   */
  public String getText2() {
    return text2;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.button.ModernCheckButton#drawForegroundAA(java.awt.
   * Graphics2D)
   */
  public void drawForegroundAA(Graphics2D g2) {
    int x = AssetService.ICON_SIZE_48;
    int y = 0;

    if (mText1 != null) {
      y += AssetService.ICON_SIZE_20;
      g2.setColor(color1);
      g2.drawString(mText1, x, y);
    }

    if (text2 != null) {
      y += AssetService.ICON_SIZE_20;
      g2.setColor(color2);
      g2.drawString(text2, x, y);
    }

    if (this.getIcon() != null) {
      int iconY = (getHeight() - AssetService.ICON_SIZE_16) / 2;

      this.getIcon().drawIcon(g2, PADDING, iconY, AssetService.ICON_SIZE_16);
    }
  }
}
