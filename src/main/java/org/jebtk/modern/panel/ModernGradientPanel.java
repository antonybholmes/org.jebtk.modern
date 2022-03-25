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

import javax.swing.JComponent;
import javax.swing.border.Border;

import org.jebtk.core.settings.SettingsService;
import org.jebtk.modern.UI;

// TODO: Auto-generated Javadoc
/**
 * Replacement ModernButton with a common skin.
 *
 * @author Antony Holmes
 *
 */
public class ModernGradientPanel extends ModernPanel {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member colour1.
   */
  private Color mColor1;

  /**
   * The member colour2.
   */
  private Color mColor2;

  /**
   * Instantiates a new modern gradient panel.
   */
  public ModernGradientPanel() {
    this(SettingsService.getInstance().getColor("gradient-panel.color-1"),
        SettingsService.getInstance().getColor("gradient-panel.color-2"));
  }

  /**
   * Instantiates a new modern gradient panel.
   *
   * @param colour1 the colour1
   * @param colour2 the colour2
   */
  public ModernGradientPanel(Color colour1, Color colour2) {
    mColor1 = colour1;
    mColor2 = colour2;
  }

  /**
   * Instantiates a new modern gradient panel.
   *
   * @param manager the manager
   * @param colour1 the colour1
   * @param colour2 the colour2
   */
  public ModernGradientPanel(LayoutManager manager, Color colour1, Color colour2) {

    super(manager);

    mColor1 = colour1;
    mColor2 = colour2;
  }

  /**
   * Instantiates a new modern gradient panel.
   *
   * @param c       the c
   * @param colour1 the colour 1
   * @param colour2 the colour 2
   */
  public ModernGradientPanel(Component c, Color colour1, Color colour2) {

    setBody(c);

    mColor1 = colour1;
    mColor2 = colour2;
  }

  /**
   * Instantiates a new modern gradient panel.
   *
   * @param c      the c
   * @param c1     the c 1
   * @param c2     the c 2
   * @param border the border
   */
  public ModernGradientPanel(JComponent c, Color c1, Color c2, Border border) {
    this(c, c1, c2);

    setBorder(border);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.panel.ModernPanel#drawBackground(java.awt.Graphics2D)
   */
  @Override
  public void drawBackground(Graphics2D g2) {
    UI.drawGradient(g2, getWidth(), getHeight(), mColor1, mColor2);
  }
}
