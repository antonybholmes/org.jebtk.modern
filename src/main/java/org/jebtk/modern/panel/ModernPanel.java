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

import org.jebtk.modern.BorderService;
import org.jebtk.modern.ModernComponent;

/**
 * Standardized panel component.
 * 
 * @author Antony Holmes
 *
 */
public class ModernPanel extends ModernComponent {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new modern panel.
   */
  public ModernPanel() {
    setup();
  }

  /**
   * Instantiates a new modern panel.
   *
   * @param color the color
   */
  public ModernPanel(Color color) {
    setup();

    setBackground(color);
  }

  /**
   * Instantiates a new modern panel.
   *
   * @param manager the manager
   */
  public ModernPanel(LayoutManager manager) {
    setup();

    setLayout(manager);
  }

  /**
   * Instantiates a new modern panel.
   *
   * @param c the c
   */
  public ModernPanel(Component c) {
    setup();

    add(c);
  }

  /**
   * Instantiates a new modern panel.
   *
   * @param c      the c
   * @param border the border
   */
  public ModernPanel(Component c, int border) {
    this(c, BorderService.getInstance().createBorder(border));
  }

  /**
   * Instantiates a new modern panel.
   *
   * @param c      the c
   * @param border the border
   */
  public ModernPanel(Component c, Border border) {
    this(c);

    setBorder(border);
  }

  /**
   * Instantiates a new modern panel.
   *
   * @param c     the c
   * @param color the color
   */
  public ModernPanel(Component c, Color color) {
    this(c);

    setBackground(color);
  }

  /**
   * Instantiates a new modern panel.
   *
   * @param c      the c
   * @param color  the color
   * @param border the border
   */
  public ModernPanel(Component c, Color color, Border border) {
    this(c, color);

    setBorder(border);
  }

  /**
   * Instantiates a new modern panel.
   *
   * @param c       the c
   * @param color   the color
   * @param padding the padding
   */
  public ModernPanel(Component c, Color color, int padding) {
    this(c, padding);

    setBackground(color);
  }

  /**
   * Setup.
   */
  private void setup() {
    setBackground(Color.WHITE);
    
    //addStyleClass("panel");
  }

  /**
   * Should be in charge of rendering the foreground using anti-aliasing.
   *
   * @param g2 the g2
   */
  @Override
  public void drawBackground(Graphics2D g2) {
    fillBackground(g2);
  }
}
