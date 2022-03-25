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
package org.jebtk.modern.dialog;

import java.awt.Color;
import java.awt.LayoutManager;

import javax.swing.border.Border;

import org.jebtk.core.ColorUtils;
import org.jebtk.modern.panel.ModernGradientPanel;

// TODO: Auto-generated Javadoc
/**
 * Standard gradient panel for main ui window.
 *
 * @author Antony Holmes
 *
 */
public class DialogGradientPanel extends ModernGradientPanel {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant COLOR1.
   */
  private static final Color COLOR1 = Color.WHITE;

  /**
   * The constant COLOR2.
   */
  private static final Color COLOR2 = ColorUtils.decodeHtmlColor("#f2f2f2");

  /**
   * Instantiates a new gradient dialog panel.
   */
  public DialogGradientPanel() {
    super(COLOR1, COLOR2);
  }

  /**
   * Instantiates a new gradient dialog panel.
   *
   * @param manager the manager
   */
  public DialogGradientPanel(LayoutManager manager) {
    super(manager, COLOR1, COLOR2);
  }

  /**
   * Instantiates a new gradient dialog panel.
   *
   * @param border the border
   */
  public DialogGradientPanel(Border border) {
    super(COLOR1, COLOR2);
  }
}
