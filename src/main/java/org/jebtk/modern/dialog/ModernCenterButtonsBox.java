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

import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;

import org.jebtk.modern.BorderService;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.panel.HBox;

// TODO: Auto-generated Javadoc
/**
 * Default layout for buttons in a dialog box.
 *
 * @author Antony Holmes
 */
public class ModernCenterButtonsBox extends Box {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member box.
   */
  private Box mBox = HBox.create();

  /**
   * Instantiates a new modern center buttons box.
   */
  public ModernCenterButtonsBox() {
    super(BoxLayout.LINE_AXIS);

    setBorder(BorderService.getInstance().createBorder(0, ModernWidget.DOUBLE_PADDING, ModernWidget.DOUBLE_PADDING,
        ModernWidget.DOUBLE_PADDING));

    setAlignmentX(LEFT_ALIGNMENT);

    super.add(Box.createHorizontalGlue());
    super.add(mBox);
    super.add(Box.createHorizontalGlue());
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

}
