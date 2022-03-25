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

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.modern.AssetService;
import org.jebtk.modern.button.ModernButtonWidget;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.ribbon.Ribbon;
import org.jebtk.modern.ribbon.RibbonLargeButton;
import org.jebtk.modern.ribbon.RibbonSection;

/**
 * Standardized ribbon menu section for providing basic cut, copy and paste
 * functionality to the currently highlighted control that supports clipboard
 * operations.
 *
 * @author Antony Holmes
 *
 */
public class ClipboardRibbonSection extends RibbonSection implements ModernClickListener, IClipboardEventListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member paste button.
   */
  private final ModernButtonWidget mPasteButton = new RibbonLargeButton("Paste",
      AssetService.getInstance().loadIcon("paste", 24));

  /**
   * The member cut button.
   */
  private final ModernButtonWidget mCutButton = new RibbonLargeButton("Cut", AssetService.getInstance().loadIcon("cut", 24));

  /**
   * The member copy button.
   */
  private final ModernButtonWidget mCopyButton = new RibbonLargeButton("Copy",
      AssetService.getInstance().loadIcon("copy", 24));

  /**
   * Instantiates a new clipboard ribbon section2.
   *
   * @param ribbon the ribbon
   */
  public ClipboardRibbonSection(Ribbon ribbon) {
    super(ribbon, "Clipboard");

    mPasteButton.addClickListener(this);
    mPasteButton.setToolTip("Paste", "Paste data from the clipboard.");

    mCutButton.addClickListener(this);
    mCutButton.setToolTip("Cut", "Cut the currently selected text.");

    mCopyButton.addClickListener(this);
    mCopyButton.setToolTip("Copy", "Copy the currently selected data to the clipboard.");

    mPasteButton.setEnabled(false);
    mCutButton.setEnabled(false);
    mCopyButton.setEnabled(false);

    add(mPasteButton);
    add(mCutButton);
    add(mCopyButton);

    ClipboardService.getInstance().addClipboardListener(this);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.clipboard.ClipboardEventListener#clipboardChanged(
   * org. abh.lib.event.ChangeEvent)
   */
  @Override
  public void clipboardChanged(ChangeEvent e) {
    mPasteButton.setEnabled(
        ClipboardService.getInstance().getControl() != null ? ClipboardService.getInstance().getControl().pasteEnabled()
            : false);
    mCutButton.setEnabled(
        ClipboardService.getInstance().getControl() != null ? ClipboardService.getInstance().getControl().cutEnabled()
            : false);
    mCopyButton.setEnabled(
        ClipboardService.getInstance().getControl() != null ? ClipboardService.getInstance().getControl().copyEnabled()
            : false);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  @Override
  public void clicked(ModernClickEvent e) {
    if (e.getSource().equals(mPasteButton)) {
      ClipboardService.getInstance().paste();
    } else if (e.getSource().equals(mCutButton)) {
      ClipboardService.getInstance().cut();
    } else if (e.getSource().equals(mCopyButton)) {
      ClipboardService.getInstance().copy();
    }
  }
}
