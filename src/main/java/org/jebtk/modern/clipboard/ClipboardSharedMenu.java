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

import org.jebtk.modern.UI;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.menu.ModernIconMenuItem;
import org.jebtk.modern.menu.ModernMenuSeparator;
import org.jebtk.modern.menu.ModernPopupMenu;

/**
 * Allows multiple controls to share an instance of a clipboard menu so it is
 * not duplicated on a per control basis.
 *
 * @author Antony Holmes
 *
 */
public class ClipboardSharedMenu implements ModernClickListener {

  /**
   * The Class ClipboardSharedMenuLoader.
   */
  private static class ClipboardSharedMenuLoader {

    /** The Constant INSTANCE. */
    private static final ClipboardSharedMenu INSTANCE = new ClipboardSharedMenu();
  }

  /**
   * Gets the single instance of SettingsService.
   *
   * @return single instance of SettingsService
   */
  public static ClipboardSharedMenu getInstance() {
    return ClipboardSharedMenuLoader.INSTANCE;
  }

  /**
   * The cut modern menu item.
   */
  private final ModernIconMenuItem mCutMenuItem;

  /**
   * The copy modern menu item.
   */
  private final ModernIconMenuItem mCopyMenuItem;

  /**
   * The paste modern menu item.
   */
  private final ModernIconMenuItem mPasteMenuItem;

  /**
   * The popup.
   */
  private final ModernPopupMenu mPopup;

  /**
   * The l.
   */
  private ModernClickListener lmL;

  /**
   * Instantiates a new clipboard shared menu.
   */
  private ClipboardSharedMenu() {
    mPopup = new ModernPopupMenu();

    mCutMenuItem = new ModernIconMenuItem(UI.MENU_CUT, UI.CUT_ICON);

    mCopyMenuItem = new ModernIconMenuItem(UI.MENU_COPY, UI.COPY_ICON);

    mPasteMenuItem = new ModernIconMenuItem(UI.MENU_PASTE, UI.PASTE_ICON);

    // PopupContainer menuContainer = new PopupContainer();

    mCutMenuItem.addClickListener(this);
    mPopup.add(mCutMenuItem);

    mCopyMenuItem.addClickListener(this);
    mPopup.add(mCopyMenuItem);

    mPasteMenuItem.addClickListener(this);
    mPopup.add(mPasteMenuItem);

    mPopup.add(new ModernMenuSeparator());

    ModernIconMenuItem menuItem = new ModernIconMenuItem(UI.MENU_SELECT_ALL);
    menuItem.addClickListener(this);
    mPopup.add(menuItem);
  }

  /**
   * Returns the cut/copy/paste menu. The control will be registered to recieve
   * menu events so one need only modify their clicked method to listen for
   * cut/copy/paste commands and respond accordingly.
   *
   * @param l            the l
   * @param cutEnabled   the cut enabled
   * @param copyEnabled  the copy enabled
   * @param pasteEnabled the paste enabled
   * @return the menu
   */
  public final ModernPopupMenu getMenu(ModernClickListener l, boolean cutEnabled, boolean copyEnabled,
      boolean pasteEnabled) {
    register(l);

    mCutMenuItem.setEnabled(cutEnabled);
    mCopyMenuItem.setEnabled(copyEnabled);
    mPasteMenuItem.setEnabled(pasteEnabled);

    return mPopup;
  }

  /**
   * Register.
   *
   * @param l the l
   */
  public void register(ModernClickListener l) {
    lmL = l;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  @Override
  public void clicked(ModernClickEvent e) {
    mPopup.setVisible(false);

    lmL.clicked(e);
  }
}
