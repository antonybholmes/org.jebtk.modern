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
import org.jebtk.modern.panel.ModernPanel;
import org.jebtk.modern.panel.VCenterBox;
import org.jebtk.modern.ribbon.MessageButton;
import org.jebtk.modern.ribbon.Ribbon;
import org.jebtk.modern.text.ModernCenteredHeadingPanel;
import org.jebtk.modern.window.FrostedGlassPane;

/**
 * Used as a glass pane to present messages to the user in the current window
 * rather than a popup dialog.
 * 
 * @author Antony Holmes
 *
 */
public class MessageDialogGlassPane extends FrostedGlassPane implements ModernClickListener {

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
  private class MessageDialogPanel extends ModernPanel implements ModernClickListener, FocusListener {

    /**
     * The height.
     */
    private int HEIGHT = 120;

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The member ok button.
     */
    private ModernButton mOkButton = new MessageButton(UI.BUTTON_OK);

    /**
     * The member message label.
     */
    private ModernCenteredHeadingPanel mMessageLabel = new ModernCenteredHeadingPanel("Heading", Color.WHITE);

    /**
     * The member listeners.
     */
    private ModernClickListeners mListeners = new ModernClickListeners();

    /**
     * Instantiates a new message dialog panel.
     */
    public MessageDialogPanel() {
      super(Ribbon.BAR_BACKGROUND);

      UI.setSize(this, new Dimension(Short.MAX_VALUE, HEIGHT));

      // add(mMessageLabel, BorderLayout.PAGE_START);

      Box box = new VCenterBox();

      Box box2 = new HCenterBox();

      // box3.add(mMessageLabel);
      // box3.add(ModernPanel.createVGap());
      // box3.add(new ModernSubHeadingLabel("Something or other", Color.WHITE));

      UI.setSize(mMessageLabel, new Dimension(1200, 50));

      box2.add(mMessageLabel);
      box.add(box2);
      box.add(UI.createVGap(20));

      box2 = new HCenterBox();
      box2.add(mOkButton);
      box.add(box2);

      add(box);

      mOkButton.addClickListener(this);

      addFocusListener(this);

      setFocusable(true);
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
    public void focusGained(FocusEvent arg0) {
      mOkButton.requestFocusInWindow();
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
  private MessageDialogPanel mPanel = new MessageDialogPanel();

  /**
   * Instantiates a new message dialog glass pane.
   */
  public MessageDialogGlassPane() {
    add(mPanel);

    mPanel.addClickListener(this);
  }

  /**
   * Show message.
   *
   * @param message the message
   */
  public void showMessage(String message) {
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
  }
}
