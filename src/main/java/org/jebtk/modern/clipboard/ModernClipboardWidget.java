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
package org.jebtk.modern.clipboard;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import org.jebtk.modern.ModernFocusableWidget;

// TODO: Auto-generated Javadoc
/**
 * The class ModernClipboardWidget.
 */
public abstract class ModernClipboardWidget extends ModernFocusableWidget implements IClipboardUI {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The class FocusEvents.
   */
  private class FocusEvents implements FocusListener {

    /**
     * The member c.
     */
    private final IClipboardUI mC;

    /**
     * Instantiates a new focus events.
     *
     * @param c the c
     */
    public FocusEvents(IClipboardUI c) {
      mC = c;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
     */
    @Override
    public void focusGained(FocusEvent e) {
      ClipboardService.getInstance().register(mC);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
     */
    @Override
    public void focusLost(FocusEvent e) {
      ClipboardService.getInstance().unregister(mC);
    }
  }

  /**
   * Instantiates a new modern clipboard widget.
   */
  public ModernClipboardWidget() {
    addFocusListener(new FocusEvents(this));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.clipboard.Clipboard#copy()
   */
  @Override
  public void copy() {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.clipboard.Clipboard#paste()
   */
  @Override
  public void paste() {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.clipboard.Clipboard#cut()
   */
  @Override
  public void cut() {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.clipboard.ClipboardUiControl#cutEnabled()
   */
  @Override
  public boolean cutEnabled() {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.clipboard.ClipboardUiControl#copyEnabled()
   */
  @Override
  public boolean copyEnabled() {
    // TODO Auto-generated method stub
    return false;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.clipboard.ClipboardUiControl#pasteEnabled()
   */
  @Override
  public boolean pasteEnabled() {
    // TODO Auto-generated method stub
    return false;
  }
}
