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

import javax.swing.JComponent;

import org.jebtk.modern.BorderService;
import org.jebtk.modern.text.ModernAutoSizeLabel;
import org.jebtk.modern.text.ModernColoredHeadingLabel;
import org.jebtk.modern.text.ModernHeading;

// TODO: Auto-generated Javadoc
/**
 * Provides a line separator for menus with a theme consistent with
 * ModernMenuItem.
 *
 * @author Antony Holmes
 *
 */
public class ModernSidePanel extends JComponent {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new modern side panel.
   *
   * @param title   the title
   * @param content the content
   */
  public ModernSidePanel(String title, JComponent content) {
    ModernAutoSizeLabel label = new ModernColoredHeadingLabel(title);
    label.setBorder(BorderService.getInstance().createBorder(10));

    add(label, BorderLayout.PAGE_START);

    add(content, BorderLayout.CENTER);
  }

  /**
   * Instantiates a new modern side panel.
   *
   * @param title   the title
   * @param top     the top
   * @param content the content
   */
  public ModernSidePanel(String title, JComponent top, JComponent content) {
    add(new ModernHeading(title, top), BorderLayout.PAGE_START);

    add(content, BorderLayout.CENTER);
  }

  /**
   * Instantiates a new modern side panel.
   *
   * @param title the title
   */
  public ModernSidePanel(String title) {
    ModernAutoSizeLabel label = new ModernColoredHeadingLabel(title);

    add(label, BorderLayout.PAGE_START);
  }
}