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
package org.jebtk.modern.ribbon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.border.Border;

import org.jebtk.modern.BorderService;
import org.jebtk.modern.button.ModernClickWidget;

// TODO: Auto-generated Javadoc
/**
 * The class RibbonMenuPanel.
 */
public class RibbonMenuPanel extends ModernClickWidget {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant RIBBON_PANEL_COLOR.
   */
  public static final Color RIBBON_PANEL_COLOR = Color.WHITE; // ThemeService.getInstance().getColors().getHighlight(1);

  /** The border. */
  public static Border BORDER = BorderService.getInstance().createBorder(40);

  /**
   * The member name.
   */
  private String mName;

  /**
   * Instantiates a new ribbon menu panel.
   *
   * @param name the name
   */
  public RibbonMenuPanel(String name) {
    mName = name;

    setBorder(BORDER);
  }

  /**
   * Instantiates a new ribbon menu panel.
   *
   * @param name   the name
   * @param layout the layout
   */
  public RibbonMenuPanel(String name, BorderLayout layout) {
    this(name);

    setLayout(layout);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#getName()
   */
  @Override
  public String getName() {
    return mName;
  }

  /**
   * Refresh.
   */
  public void refresh() {
    // do nothing
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernClickWidget#setHighlighted(boolean)
   */
  @Override
  public void setHighlighted(boolean highlight) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawBackground(java.awt.Graphics2D)
   */
  @Override
  public void drawBackground(Graphics2D g2) {
    fill(g2, RIBBON_PANEL_COLOR);
  }
}