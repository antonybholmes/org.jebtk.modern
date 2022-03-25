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

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.border.Border;

import org.jebtk.modern.UI;
import org.jebtk.modern.text.ModernAutoSizeLabel;
import org.jebtk.modern.text.ModernLabel;

/**
 * The class HBoxPanel.
 */
public class HBox extends GenericBox {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new h box panel.
   */
  public HBox() {
    super(BoxLayout.LINE_AXIS);
  }

  /**
   * Instantiates a new h box.
   *
   * @param components the components
   */
  public HBox(Component component, Component... components) {
    this();

    add(component);

    for (Component c : components) {
      add(c);
    }
  }

  public HBox(String title, Component... components) {
    this(new ModernAutoSizeLabel(title), components);
  }

  /**
   * Instantiates a new h box.
   *
   * @param border the border
   */
  public HBox(Border border) {
    this();

    setBorder(border);
  }

  /**
   * Adds the.
   *
   * @param name the name
   */
  public void add(String name) {
    add(new ModernLabel(name));
  }

  /**
   * Adds the glue.
   */
  public void addGlue() {
    add(Box.createHorizontalGlue());
  }

  /**
   * Adds the gap.
   */
  public void addGap() {
    add(UI.createHGap(5));
  }

  /**
   * Create a new HBox.
   *
   * @return the h box
   */
  public static HBox create() {
    return new HBox();
  }
}
