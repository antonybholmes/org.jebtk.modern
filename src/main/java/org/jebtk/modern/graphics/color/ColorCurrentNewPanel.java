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
package org.jebtk.modern.graphics.color;

import java.awt.Graphics2D;

import org.jebtk.core.ColorValue;
import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.event.ChangeListener;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;

/**
 * The class ColorCurrentNewPanel.
 */
public class ColorCurrentNewPanel extends ModernWidget implements ChangeListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The current color.
   */
  private ColorValue currentColor = new ColorValue();

  /**
   * The new color.
   */
  private ColorValue newColor = new ColorValue();

  /**
   * The size.
   */
  private static final int HEIGHT = 48;

  /** The Constant HALF_HEIGHT. */
  private static final int HALF_HEIGHT = HEIGHT / 2;

  /**
   * The model.
   */
  private ColorSelectionModel mModel;

  /**
   * Instantiates a new color current new panel.
   *
   * @param model the model
   */
  public ColorCurrentNewPanel(ColorSelectionModel model) {
    mModel = model;

    model.addChangeListener(this);

    setAlignmentY(TOP_ALIGNMENT);

    UI.setSize(this, 200, HEIGHT);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    g2.setColor(ColorValue.convert(newColor));
    g2.fillRect(0, 0, HEIGHT, HALF_HEIGHT);

    g2.setColor(ColorValue.convert(currentColor));
    g2.fillRect(0, HALF_HEIGHT, HEIGHT, HALF_HEIGHT);

    g2.setColor(ModernWidget.TEXT_COLOR);

    g2.drawString("New", HEIGHT + PADDING, 20);
    g2.drawString("Current", HEIGHT + PADDING, 20 + HALF_HEIGHT);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  @Override
  public void changed(ChangeEvent e) {
    currentColor = mModel.getCurrentColor();
    newColor = mModel.getNewColor();

    repaint();
  }
}
