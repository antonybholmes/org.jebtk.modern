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
package org.jebtk.modern.panel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.LayoutManager;

import javax.swing.border.Border;

import org.jebtk.modern.ModernWidget;

// TODO: Auto-generated Javadoc
/**
 * Replacement ModernButton with a common skin.
 *
 * @author Antony Holmes
 *
 */
public class ModernBorderPanel extends ModernWidget {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new modern dialog border panel.
   */
  public ModernBorderPanel() {
    init(BORDER);
  }

  /**
   * Instantiates a new modern dialog border panel.
   *
   * @param layout the layout
   */
  public ModernBorderPanel(LayoutManager layout) {
    super(layout);

    init(BORDER);
  }

  /**
   * Instantiates a new modern dialog border panel.
   *
   * @param component the component
   */
  public ModernBorderPanel(Component c) {
    this(c, BORDER);
  }

  public ModernBorderPanel(Component c, Border border) {
    add(c);

    init(border);
  }

  /**
   * Inits the.
   */
  private void init(Border b) {
    setBackground(Color.WHITE);
    setBorder(b);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.widget.ModernWidget#drawBackgroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawBackgroundAA(Graphics2D g2) {
    fillBackground(g2);

    g2.setColor(LIGHT_LINE_COLOR);
    g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
  }
}
