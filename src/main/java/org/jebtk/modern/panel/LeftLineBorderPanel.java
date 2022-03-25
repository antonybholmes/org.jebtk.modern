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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.LayoutManager;

import org.jebtk.modern.BorderService;
import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.UI;

// TODO: Auto-generated Javadoc
/**
 * Replacement ModernButton with a common skin.
 *
 * @author Antony Holmes
 *
 */
public class LeftLineBorderPanel extends ModernComponent {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new modern line border panel.
   */
  public LeftLineBorderPanel() {
    init();
  }

  /**
   * Instantiates a new modern line border panel.
   *
   * @param color the color
   */
  public LeftLineBorderPanel(Color color) {
    setBackground(color);

    init();
  }

  /**
   * Instantiates a new modern line border panel.
   *
   * @param layout the layout
   */
  public LeftLineBorderPanel(LayoutManager layout) {
    super(layout);

    init();
  }

  /**
   * Instantiates a new modern line border panel.
   *
   * @param component the component
   */
  public LeftLineBorderPanel(Component component) {
    add(component, BorderLayout.CENTER);

    init();
  }

  /**
   * Instantiates a new modern line border panel.
   *
   * @param component the component
   * @param dimension the dimension
   */
  public LeftLineBorderPanel(Component component, Dimension dimension) {
    this(component);

    UI.setSize(this, dimension);

    init();
  }

  /**
   * 1 Inits the.
   */
  private void init() {
    setBorder(BorderService.getInstance().createLeftBorder(1));
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.widget.ModernWidget#drawBackgroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawBackground(Graphics2D g2) {
    g2.setColor(LIGHT_LINE_COLOR);

    g2.drawLine(0, 0, 0, getHeight());
  }
}
