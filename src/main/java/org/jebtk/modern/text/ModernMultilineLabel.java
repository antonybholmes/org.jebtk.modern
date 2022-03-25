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
package org.jebtk.modern.text;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.jebtk.core.text.TextUtils;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.graphics.DrawingContext;
import org.jebtk.modern.graphics.ModernCanvas;

/**
 * Displays multiple lines of text, breaking lines at a space break before the
 * line ends.
 * 
 * @author Antony Holmes
 *
 */
public class ModernMultilineLabel extends ModernCanvas implements ITextProperty {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member h.
   */
  private int mH;

  /**
   * The member text.
   */
  private String mText;

  /**
   * The member g2.
   */
  private Graphics2D mG2;

  /**
   * The member lines.
   */
  private List<String> mLines;

  /**
   * The class ComponentEvents.
   */
  private class ComponentEvents extends ComponentAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.ComponentAdapter#componentResized(java.awt.event.
     * ComponentEvent)
     */
    @Override
    public void componentResized(ComponentEvent e) {
      resize();
    }
  }

  /**
   * Instantiates a new modern multiline label.
   */
  public ModernMultilineLabel() {
    this(TextUtils.EMPTY_STRING);
  }

  /**
   * Instantiates a new modern multiline label.
   *
   * @param text the text
   */
  public ModernMultilineLabel(String text) {
    setFont(ModernWidget.FONT);

    addComponentListener(new ComponentEvents());

    BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    mG2 = (Graphics2D) image.getGraphics();
    mG2.setFont(getFont());

    mH = mG2.getFontMetrics().getAscent() + mG2.getFontMetrics().getDescent();
    // mW = mG2.getFontMetrics().stringWidth("A");

    setText(text);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.graphics.ModernCanvas#drawCanvasForeground(java.awt.
   * Graphics2D)
   */
  @Override
  public void rasterCanvas(Graphics2D g2, DrawingContext context) {
    if (mLines == null) {
      return;
    }

    g2.setColor(getForeground());

    int x = getInsets().left;
    int y = getInsets().top + getTextYPosCenter(g2, mH);

    for (String line : mLines) {

      g2.drawString(line, x, y);

      y += mH;
    }
  }

  /**
   * Resize.
   */
  private void resize() {
    // if (getWidth() < 2) {
    // return;
    // }

    mLines = new ArrayList<String>();

    List<String> words = TextUtils.fastSplit(mText, TextUtils.SPACE_DELIMITER);

    StringBuilder buffer = new StringBuilder();

    for (String word : words) {
      // See what happens when we add a new word, if this exceeds the
      // line width, create a new line
      StringBuilder tp = new StringBuilder(buffer.toString()).append(TextUtils.SPACE_DELIMITER).append(word);

      int w = mG2.getFontMetrics().stringWidth(tp.toString());

      if (w > getWidth()) {
        mLines.add(buffer.toString());

        buffer = new StringBuilder();
      }

      buffer.append(word).append(TextUtils.SPACE_DELIMITER);
    }

    if (buffer.length() > 0) {
      mLines.add(buffer.toString());
    }

    // System.err.println("x " + mInternalRect.getW() + " " + (mH *
    // mLines.size()));

    setPreferredSize(new Dimension(Short.MAX_VALUE, mH * mLines.size()));
    setMaximumSize(getPreferredSize());
    setCanvasSize(getPreferredSize());
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.text.TextProperty#getText()
   */
  @Override
  public String getText() {
    return mText;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.text.TextProperty#setText(java.lang.String)
   */
  @Override
  public void setText(String text) {
    mText = text;

    resize();
  }
}
