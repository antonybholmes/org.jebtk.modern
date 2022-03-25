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
package org.jebtk.modern.tooltip;

import org.jebtk.modern.UI;
import org.jebtk.modern.button.ModernButton;
import org.jebtk.modern.dialog.ModernDialogButton;
import org.jebtk.modern.dialog.ModernDialogButtonsBox;
import org.jebtk.modern.dialog.ModernDialogPrimaryButton;
import org.jebtk.modern.dialog.ModernDialogStatus;
import org.jebtk.modern.dialog.ModernDialogTaskType;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.panel.Card;
import org.jebtk.modern.panel.ModernPanel;

/**
 * The default tool tip panel provides a simple titled tool tip that appears
 * below the ribbon.
 * 
 * @author Antony Holmes
 *
 */
public abstract class ModernToolTipPanel extends Card {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  private boolean mAutoHide = true;

  /** The m buttons. */
  protected ModernDialogButtonsBox mButtons = new ModernDialogButtonsBox();

  /** The m ok button. */
  protected ModernButton mOkButton = new ModernDialogPrimaryButton(UI.BUTTON_OK);

  /**
   * The close button.
   */
  protected ModernButton mCancelButton = new ModernDialogButton(UI.BUTTON_CANCEL);

  private ModernDialogStatus mStatus = ModernDialogStatus.CANCEL;

  public ModernToolTipPanel() {
    setBorder(BORDER);
  }

  public void setAutoHide(boolean hide) {
    mAutoHide = hide;
  }

  protected void setup(ModernDialogTaskType type) {
    setAutoHide(false);

    add(mButtons);

    switch (type) {
    case CLOSE:
      // No break as we want to run the OK case as well.
      mOkButton.setText(UI.MENU_CLOSE);
    case OK:
      addOkButton();
      break;
    case CANCEL:
      addCancelButton();
      break;
    case OK_CANCEL:
      addOkCancelButtons();
      break;
    default:
      break;
    }
  }

  public void addOkButton() {
    mButtons.add(mOkButton);

    mOkButton.addClickListener(new ModernClickListener() {
      @Override
      public void clicked(ModernClickEvent e) {
        hide(e);
      }
    });
  }

  /**
   * Add standard OK and Cancel buttons to the dialog. Use clicked method to
   * respond.
   */
  public void addCancelButton() {
    mButtons.add(mCancelButton);

    mCancelButton.addClickListener(new ModernClickListener() {
      @Override
      public void clicked(ModernClickEvent e) {
        hide(e);
      }
    });
  }

  public void addOkCancelButtons() {
    addOkButton();
    mButtons.add(ModernPanel.createHGap());
    addCancelButton();
  }

  public boolean getAutoHide() {
    return mAutoHide;
  }

  public void hideToolTip() {
    ToolTipService.getInstance().hideToolTip(this, ToolTipLevel.FORCE);
  }

  public ModernDialogStatus getStatus() {
    return mStatus;
  }

  private void hide(ModernClickEvent e) {
    if (e.getSource().equals(mOkButton)) {
      mStatus = ModernDialogStatus.OK;
    }

    hideToolTip();
  }
}
