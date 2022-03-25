/**
 * Copyright (C) 2018, Antony Holmes
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
package org.jebtk.modern.text;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.List;

import org.jebtk.core.text.Join;
import org.jebtk.core.text.Splitter;
import org.jebtk.core.text.TextUtils;
import org.jebtk.modern.ModernWidget;

/**
 * Displays text in a label that wraps text at word breaks.
 * 
 * @author Antony Holmes
 *
 */
public class WrapLabel extends ModernWidget implements ITextProperty {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * The member text.
   */
  private List<String> mText;

  public WrapLabel() {
    this(TextUtils.EMPTY_STRING);
  }

  /**
   * Instantiates a new modern label panel.
   *
   * @param text the text
   */
  public WrapLabel(String text) {
    this(text, TEXT_COLOR);
  }

  /**
   * Instantiates a new modern label.
   *
   * @param text the text
   * @param font the font
   */
  public WrapLabel(String text, Font font) {
    setText(text);

    setFont(font);
  }

  /**
   * Instantiates a new modern label panel.
   *
   * @param text  the text
   * @param color the color
   */
  public WrapLabel(String text, Color color) {
    setText(text);

    setForeground(color);

    // UI.setSize(this, ModernWidget.MAX_SIZE);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.text.TextProperty#setText(java.lang.String)
   */
  @Override
  public void setText(String text) {
    mText = Splitter.onSpace().text(text);

    repaint();
  }

  /**
   * Sets the text.
   *
   * @param v the new text
   */
  public void setText(int v) {
    setText(Integer.toString(v));

    repaint();
  }

  /**
   * Sets the text.
   *
   * @param v the new text
   */
  public void setText(double v) {
    setText(Double.toString(v));

    repaint();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.text.TextProperty#getText()
   */
  @Override
  public String getText() {
    return Join.onSpace().values(mText).toString();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.text.ModernLabelPanel#drawForegroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    // System.err.println("sdf " + getInsets().top + " " + mInternalRect.getH()
    // + "
    // " + getTextVCenteredYPos(g2, mInternalRect.getH()));

    int lx = getInsets().left;
    int x = lx;
    int lh = getStringHeight(g2);
    int y = getInsets().top + lh;
    int rx = x + mInternalRect.getW();
    int w;
    int x2;

    int sw = getStringWidth(g2, TextUtils.SPACE_DELIMITER);

    for (String word : mText) {
      w = getStringWidth(g2, word);

      x2 = x + w;

      if (x2 >= rx) {
        x = lx;
        y += lh;
      }

      g2.drawString(word, x, y);

      x += w + sw;

      // if (x >= rx) {
      // x = lx;
      // y += lh;
      // }
    }
  }
}
