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

import java.awt.Component;
import java.awt.LayoutManager;

import org.jebtk.modern.BorderService;
import org.jebtk.modern.ModernWidget;

// TODO: Auto-generated Javadoc
/**
 * Provides a layout panel with side padding.
 *
 * @author Antony Holmes
 */
public class ModernPaddedPanel extends ModernWidget {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new modern padded panel.
   */
  public ModernPaddedPanel() {
    this(ModernWidget.PADDING);
  }

  /**
   * Instantiates a new modern padded panel.
   *
   * @param padding the padding
   */
  public ModernPaddedPanel(int padding) {
    setup(padding);
  }

  /**
   * Instantiates a new modern padded panel.
   *
   * @param c the c
   */
  public ModernPaddedPanel(Component c) {
    setup(ModernWidget.PADDING);

    add(c);
  }

  /**
   * Instantiates a new modern padded panel.
   *
   * @param layout the layout
   */
  public ModernPaddedPanel(LayoutManager layout) {
    super(layout);

    setup(ModernWidget.PADDING);
  }

  /**
   * Sets the up.
   *
   * @param padding the new up
   */
  private void setup(int padding) {
    setBorder(BorderService.getInstance().createBorder(padding));
  }
}
