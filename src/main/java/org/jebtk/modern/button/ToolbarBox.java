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
import java.awt.Graphics;

import javax.swing.Box;
import javax.swing.BoxLayout;

import org.jebtk.modern.BorderService;
import org.jebtk.modern.theme.ThemeService;

/**
 * The class ToolbarBox.
 */
public class ToolbarBox extends Box {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant LINE_COLOR.
   */
  private static final Color LINE_COLOR = ThemeService.getInstance().getColors().getGray(3);

  /**
   * Instantiates a new toolbar box.
   */
  public ToolbarBox() {
    super(BoxLayout.LINE_AXIS);

    setBorder(BorderService.getInstance().createTopBottomBorder(10));

    setAlignmentX(LEFT_ALIGNMENT);
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.Box#paintComponent(java.awt.Graphics)
   */
  @Override
  public void paintComponent(Graphics g) {
    g.setColor(LINE_COLOR);

    g.drawLine(0, 0, getWidth(), 0);

    int y = getHeight() - 1;

    g.drawLine(0, y, getWidth(), y);
  }

}
