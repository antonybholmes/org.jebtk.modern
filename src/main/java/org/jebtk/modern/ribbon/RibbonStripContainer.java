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

import java.awt.Component;

import javax.swing.Box;

import org.jebtk.modern.panel.HBox;
import org.jebtk.modern.panel.VCenterBox;

/**
 * For storing a strip of smaller buttons.
 *
 * @author Antony Holmes
 */
public class RibbonStripContainer extends VCenterBox {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member hbox.
   */
  private Box mHbox = HBox.create();

  /**
   * Instantiates a new ribbon strip container2.
   */
  public RibbonStripContainer() {
    super.add(mHbox);
  }

  /**
   * Instantiates a new ribbon strip container.
   *
   * @param components the components
   */
  public RibbonStripContainer(Component... components) {
    this();

    for (Component c : components) {
      add(c);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.panel.VCenterBoxPanel#add(java.awt.Component)
   */
  @Override
  public Component add(Component c) {
    mHbox.add(c);

    return c;
  }

}
