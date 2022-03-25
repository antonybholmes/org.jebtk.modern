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
package org.jebtk.modern.contentpane;

import java.awt.Component;

import javax.swing.Box;

import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.panel.HBox;
import org.jebtk.modern.panel.VBox;
import org.jebtk.modern.text.ModernAutoSizeLabel;
import org.jebtk.modern.text.ModernSubHeadingLabel;

/**
 * The Class VTabToolbar creates a simple toolbar with a title above it.
 */
public class VTabToolbar extends VBox {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The m box. */
  private Box mBox = HBox.create();

  /**
   * Instantiates a new v tab toolbar.
   *
   * @param title the title
   */
  public VTabToolbar(String title) {
    this(title, null);
  }

  /**
   * Instantiates a new v tab toolbar.
   *
   * @param title the title
   * @param c     the c
   */
  public VTabToolbar(String title, Component c) {
    ModernAutoSizeLabel label = new ModernSubHeadingLabel(title);

    super.add(label);
    super.add(ModernWidget.createVGap());
    super.add(mBox);

    if (c != null) {
      add(c);
    }

    // setBody(mBox);

    // setBorder(ModernWidget.BORDER);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.Container#add(java.awt.Component)
   */
  @Override
  public Component add(Component c) {
    mBox.add(c);

    return c;
  }

  // @Override
  // public void drawBackground(Graphics2D g2) {
  // g2.setColor(LINE_COLOR);

  // int y = getHeight() - 1;

  // g2.drawLine(0, y, getWidth(), y);

  // fill(g2, LINE_COLOR);
  // }
}
