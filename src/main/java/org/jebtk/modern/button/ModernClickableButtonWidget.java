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

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import org.jebtk.modern.graphics.icons.ModernIcon;

// TODO: Auto-generated Javadoc
/**
 * Provides a clickable button widget with highlighting but does not provide the
 * rest of the gui.
 *
 * @author Antony Holmes
 */
public abstract class ModernClickableButtonWidget extends ModernButtonWidget {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The class MouseEvents.
   */
  private class MouseEvents extends MouseAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(MouseEvent e) {
      if (!e.isPopupTrigger() && isEnabled()) {
        fireClicked();
      }
    }
  }

  /**
   * The class ActionEvents.
   */
  private class ActionEvents extends AbstractAction {

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      fireClicked();
    }
  }

  /**
   * Instantiates a new modern clickable button widget.
   */
  public ModernClickableButtonWidget() {
    setup();
  }

  /**
   * Instantiates a new modern clickable button widget.
   *
   * @param text1 the text1
   */
  public ModernClickableButtonWidget(String text1) {
    super(text1);

    setup();
  }

  /**
   * Instantiates a new modern clickable button widget.
   *
   * @param icon the icon
   */
  public ModernClickableButtonWidget(ModernIcon icon) {
    super(icon);

    setup();
  }

  /**
   * Instantiates a new modern clickable button widget.
   *
   * @param text1 the text1
   * @param icon  the icon
   */
  public ModernClickableButtonWidget(String text1, ModernIcon icon) {
    super(text1, icon);

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    addMouseListener(new MouseEvents());

    // respond to enter key
    getInputMap(WHEN_FOCUSED).put(KeyStroke.getKeyStroke("ENTER"), "enter_pressed");
    getActionMap().put("enter_pressed", new ActionEvents());
  }
}
