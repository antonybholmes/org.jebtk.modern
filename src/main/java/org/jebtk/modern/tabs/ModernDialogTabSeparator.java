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
package org.jebtk.modern.tabs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.JLabel;

import org.jebtk.modern.ModernWidget;

// TODO: Auto-generated Javadoc
/**
 * Provides a line separator for menus with a theme consistent with
 * ModernMenuItem.
 *
 * @author Antony Holmes
 *
 */
public class ModernDialogTabSeparator extends ModernWidget {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant HEIGHT.
   */
  private static final int HEIGHT = WIDGET_HEIGHT;

  /**
   * The constant MIN_SIZE.
   */
  private static final Dimension MIN_SIZE = new Dimension(1, HEIGHT);

  /**
   * The constant MAX_SIZE.
   */
  private static final Dimension MAX_SIZE = new Dimension(Short.MAX_VALUE, HEIGHT);

  /**
   * The label.
   */
  private JLabel label = new JLabel();

  /*
   * public static final BasicStroke DASH_STROKE = new BasicStroke(1,
   * BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 10, DASH_PATTERN, 0);
   */

  /**
   * Instantiates a new modern dialog tab separator.
   *
   * @param name the name
   */
  public ModernDialogTabSeparator(String name) {
    super(new BorderLayout());

    setMinimumSize(MIN_SIZE);
    setPreferredSize(MIN_SIZE);
    setMaximumSize(MAX_SIZE);
    setAlignmentX(LEFT_ALIGNMENT);
    setBorder(BORDER);

    label.setText(name);

    add(label, BorderLayout.LINE_START);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {

    // g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
    // RenderingHints.VALUE_ANTIALIAS_ON);

    g2.setColor(LINE_COLOR);

    int x = getInsets().left + label.getWidth() + PADDING;
    int y = getHeight() / 2;

    g2.drawLine(x, y, getWidth() - getInsets().right, y);
  }
}