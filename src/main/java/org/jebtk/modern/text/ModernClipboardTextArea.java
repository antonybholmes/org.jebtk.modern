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
package org.jebtk.modern.text;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.jebtk.modern.UI;
import org.jebtk.modern.clipboard.ClipboardService;
import org.jebtk.modern.clipboard.ClipboardSharedMenu;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.menu.ModernPopupMenu;
import org.jebtk.modern.clipboard.IClipboardUI;

// TODO: Auto-generated Javadoc
/**
 * The class ModernClipboardTextArea.
 */
public class ModernClipboardTextArea extends ModernTextArea implements IClipboardUI, ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member cut enabled.
   */
  private boolean mCutEnabled = true;

  /**
   * The member paste enabled.
   */
  // private boolean copyEnabled = true;
  private boolean mPasteEnabled = true;

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
    public final void mousePressed(MouseEvent e) {
      showPopup(e);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
     */
    @Override
    public final void mouseReleased(MouseEvent e) {
      showPopup(e);
    }
  }

  /**
   * The class FocusEvents.
   */
  private class FocusEvents implements FocusListener {

    /**
     * The member c.
     */
    private IClipboardUI mC;

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
   * Instantiates a new modern clipboard text area.
   */
  public ModernClipboardTextArea() {
    this(true, true, true);
  }

  /**
   * Instantiates a new modern clipboard text area.
   *
   * @param cutEnabled   the cut enabled
   * @param copyEnabled  the copy enabled
   * @param pasteEnabled the paste enabled
   */
  public ModernClipboardTextArea(boolean cutEnabled, boolean copyEnabled, boolean pasteEnabled) {

    this("", cutEnabled, copyEnabled, pasteEnabled);
  }

  /**
   * Instantiates a new modern clipboard text area.
   *
   * @param text the text
   */
  public ModernClipboardTextArea(String text) {
    this(text, true, true, true);
  }

  /**
   * Instantiates a new modern clipboard text area.
   *
   * @param text         the text
   * @param cutEnabled   the cut enabled
   * @param copyEnabled  the copy enabled
   * @param pasteEnabled the paste enabled
   */
  public ModernClipboardTextArea(String text, boolean cutEnabled, boolean copyEnabled, boolean pasteEnabled) {

    super(text);

    mCutEnabled = cutEnabled;
    // copyEnabled = copyEnabled;
    mPasteEnabled = pasteEnabled;

    addMouseListener(new MouseEvents());
    addFocusListener(new FocusEvents(this));
  }

  /**
   * Show popup.
   *
   * @param e the e
   */
  private void showPopup(MouseEvent e) {

    if (e.isPopupTrigger()) {
      // cutModernMenuItem.setEnabled(cutEnabled && this.isEditable());
      // copyModernMenuItem.setEnabled(copyEnabled);
      // pasteModernMenuItem.setEnabled(pasteEnabled && this.isEditable());

      ModernPopupMenu popup = ClipboardSharedMenu.getInstance().getMenu(this, mCutEnabled && isEditable(), true,
          mPasteEnabled && isEditable());

      popup.showPopup(e.getComponent(), e.getX(), e.getY());
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  @Override
  public final void clicked(ModernClickEvent e) {
    if (e.getMessage().equals(UI.MENU_SELECT_ALL)) {
      selectAll();
    } else if (e.getMessage().equals(UI.MENU_CUT)) {
      cut();
    } else if (e.getMessage().equals(UI.MENU_COPY)) {
      copy();
    } else if (e.getMessage().equals(UI.MENU_PASTE)) {
      paste();
    } else {
      // do nothing
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.clipboard.ClipboardUiControl#cutEnabled()
   */
  public boolean cutEnabled() {
    return isEnabled() && isEditable();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.clipboard.ClipboardUiControl#copyEnabled()
   */
  public boolean copyEnabled() {
    return isEnabled();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.clipboard.ClipboardUiControl#pasteEnabled()
   */
  public boolean pasteEnabled() {
    return isEnabled() && isEditable();
  }
}
