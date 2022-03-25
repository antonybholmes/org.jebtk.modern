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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.Box;

import org.jebtk.modern.UI;
import org.jebtk.modern.button.ModernButton;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.event.ModernClickListeners;
import org.jebtk.modern.panel.HCenterBox;
import org.jebtk.modern.ribbon.MessageButton;
import org.jebtk.modern.text.ModernCenteredHeadingPanel;

/**
 * Used as a glass pane to present messages to the user in the current window
 * rather than a popup dialog.
 * 
 * @author Antony Holmes
 *
 */
public class MessageDialogOkCancelGlassPane extends MessageDialogStatusGlassPane implements ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Used as a glass pane to present messages to the user in the current window
   * rather than a popup dialog.
   * 
   * @author Antony Holmes
   *
   */
  private class OKCancelPanel extends MessageDialogPanel implements ModernClickListener, FocusListener {

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The member ok button.
     */
    private ModernButton mOkButton = new MessageButton(UI.BUTTON_OK);

    /**
     * The member cancel button.
     */
    private ModernButton mCancelButton = new MessageButton(UI.BUTTON_CANCEL);

    /**
     * The member message label.
     */
    private ModernCenteredHeadingPanel mMessageLabel = new ModernCenteredHeadingPanel("Heading", Color.WHITE);

    /**
     * The member listeners.
     */
    private ModernClickListeners mListeners = new ModernClickListeners();

    /**
     * Instantiates a new OK cancel panel.
     */
    public OKCancelPanel() {
      Box box2 = new HCenterBox();

      // box3.add(mMessageLabel);
      // box3.add(ModernPanel.createVGap());
      // box3.add(new ModernSubHeadingLabel("Something or other", Color.WHITE));

      UI.setSize(mMessageLabel, new Dimension(500, 50));

      box2.add(mMessageLabel);
      add(box2);
      add(UI.createVGap(20));

      box2 = new HCenterBox();
      box2.add(mOkButton);
      box2.add(UI.createHGap(10));
      box2.add(mCancelButton);
      add(box2);

      mOkButton.addClickListener(this);
      mCancelButton.addClickListener(this);

      addFocusListener(this);
    }

    /**
     * Show message.
     *
     * @param message the message
     */
    public void showMessage(String message) {
      mMessageLabel.setText(message);
    }

    /**
     * Adds the click listener.
     *
     * @param l the l
     */
    public void addClickListener(ModernClickListener l) {
      mListeners.addClickListener(l);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
     * modern .event.ModernClickEvent)
     */
    @Override
    public void clicked(ModernClickEvent e) {
      mListeners.fireClicked(e);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
     */
    @Override
    public void focusGained(FocusEvent e) {
      mCancelButton.requestFocusInWindow();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
     */
    @Override
    public void focusLost(FocusEvent arg0) {
      // TODO Auto-generated method stub

    }
  }

  /**
   * The member panel.
   */
  private OKCancelPanel mPanel = new OKCancelPanel();

  /**
   * Instantiates a new message dialog ok cancel glass pane.
   */
  public MessageDialogOkCancelGlassPane() {
    add(mPanel);

    mPanel.addClickListener(this);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.window.MessageDialogStatusGlassPane#showMessage(java.
   * lang.String, org.abh.lib.ui.modern.dialog.DialogEventListener)
   */
  @Override
  public void showMessage(String message, DialogEventListener l) {
    super.showMessage(message, l);

    mPanel.showMessage(message);

    setVisible(true);

    mPanel.requestFocusInWindow();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  @Override
  public void clicked(ModernClickEvent e) {
    setVisible(false);

    // call back
    mListeners.fireDialogStatusChanged(new DialogEvent(this,
        (e.getMessage().equals(UI.BUTTON_OK) ? ModernDialogStatus.OK : ModernDialogStatus.CANCEL)));
  }
}
