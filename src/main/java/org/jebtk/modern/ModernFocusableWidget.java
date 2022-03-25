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
package org.jebtk.modern;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The basis of widgets that can grab focus.
 * 
 * @author Antony Holmes
 *
 */
public abstract class ModernFocusableWidget extends ModernWidget {

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
      if (e.isPopupTrigger()) {
        return;
      }

      if (!isFocusable()) {
        return;
      }

      if (!mAutoFocus) {
        return;
      }

      requestFocus();
    }
  }

  /**
   * The class FocusEvents.
   */
  private class FocusEvents implements FocusListener {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
     */
    @Override
    public void focusGained(FocusEvent e) {
      // System.err.println("focus " + getName());

      repaint();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
     */
    @Override
    public void focusLost(FocusEvent e) {
      repaint();
    }
  }

  /**
   * The member auto focus.
   */
  private boolean mAutoFocus = true;

  /**
   * Instantiates a new modern focusable widget.
   */
  public ModernFocusableWidget() {
    setup();
  }

  /**
   * Instantiates a new modern focusable widget.
   *
   * @param manager the manager
   */
  public ModernFocusableWidget(LayoutManager manager) {
    super(manager);

    setup();
  }
  
   public ModernFocusableWidget(Component c) {
    super(c);
    
    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    setFocusable(true);

    addFocusListener(new FocusEvents());
    addMouseListener(new MouseEvents());
  }

  /**
   * Sets whether the widget will automatically try to become focused when
   * clicked.
   *
   * @param autoFocus the new auto focus
   */
  protected final void setAutoFocus(boolean autoFocus) {
    mAutoFocus = autoFocus;
  }
}