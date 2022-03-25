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
package org.jebtk.modern.tooltip;

import java.awt.BorderLayout;
import java.awt.Dimension;

import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.text.ModernLabel;
import org.jebtk.modern.text.ModernLabelBold;
import org.jebtk.modern.text.WrapLabel;

/**
 * The default tooltip panel provides a simple titled tooltip that appears below
 * the ribbon.
 * 
 * @author Antony Holmes
 *
 */
public class ModernBasicToolTipPanel extends ModernToolTipPanel {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant SIZE.
   */
  private static final Dimension SIZE = new Dimension(200, 100);

  /**
   * The member title.
   */
  private ModernLabel mTitle = new ModernLabelBold("");

  /**
   * The member text.
   */
  private WrapLabel mText = new WrapLabel();

  /**
   * Instantiates a new modern basic tool tip panel.
   *
   * @param tooltip the tooltip
   */
  public ModernBasicToolTipPanel(ModernToolTip tooltip) {
    setToolTip(tooltip);

    ModernComponent panel = new ModernComponent();

    mTitle.setBorder(BOTTOM_BORDER);
    panel.add(mTitle, BorderLayout.PAGE_START);

    // mText.setEditable(false);
    // mText.setLineWrap(true);
    // mText.setWrapStyleWord(true);

    panel.setCenter(mText);

    setPreferredSize(SIZE);

    panel.setBorder(DOUBLE_BORDER);

    add(panel);
  }

  /**
   * Sets the tool tip.
   *
   * @param tooltip the new tool tip
   */
  public void setToolTip(ModernToolTip tooltip) {
    mTitle.setText(tooltip.getTitle());
    mText.setText(tooltip.getText());
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.tooltip.ModernToolTipPanel#getPreferredSize()
   */
  public Dimension getPreferredSize() {
    return SIZE;
  }
}
