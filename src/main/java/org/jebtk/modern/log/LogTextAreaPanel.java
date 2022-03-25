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
package org.jebtk.modern.log;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextArea;

import org.jebtk.core.log.LogEvent;
import org.jebtk.core.text.TextUtils;
import org.jebtk.modern.AssetService;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.clipboard.ClipboardService;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.menu.ModernIconMenuItem;
import org.jebtk.modern.menu.ModernMenuItem;
import org.jebtk.modern.menu.ModernMenuSeparator;
import org.jebtk.modern.menu.ModernPopupMenu;
import org.jebtk.modern.scrollpane.ModernScrollPane;
import org.jebtk.modern.text.ModernTextArea;

/**
 * The class LogTextAreaPanel.
 */
public class LogTextAreaPanel extends ModernWidget implements MouseListener, ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The text area.
   */
  private JTextArea textArea = new ModernTextArea();

  /**
   * The log popup.
   */
  private ModernPopupMenu logPopup = new ModernPopupMenu();

  /**
   * Instantiates a new log text area panel.
   */
  public LogTextAreaPanel() {

    textArea.setEditable(false);

    ModernScrollPane scrollPane = new ModernScrollPane(textArea);
    // scrollPane.showClipBorder(true);
    // scrollPane.getClip().setOpaque(true);
    // scrollPane.getClip().setBackground(Color.WHITE);

    add(scrollPane, BorderLayout.CENTER);

    ModernMenuItem menuItem = new ModernIconMenuItem(UI.MENU_COPY,
        AssetService.getInstance().loadIcon("copy", AssetService.ICON_SIZE_16));
    menuItem.addClickListener(this);
    logPopup.add(menuItem);

    logPopup.add(new ModernMenuSeparator());

    menuItem = new ModernIconMenuItem(UI.MENU_SELECT_ALL);
    menuItem.addClickListener(this);
    logPopup.add(menuItem);
  }

  /**
   * Adds the event.
   *
   * @param event the event
   */
  public final void addEvent(LogEvent event) {
    textArea.append("[");
    textArea.append(event.getFormattedDate());
    textArea.append("] ");
    textArea.append(event.getMessage());
    textArea.append(TextUtils.NEW_LINE);
    textArea.setCaretPosition(textArea.getDocument().getLength());
  }

  /**
   * Show log popup.
   *
   * @param e the e
   */
  private void showLogPopup(MouseEvent e) {
    logPopup.showPopup(e.getComponent(), e.getX(), e.getY());
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
   */
  public final void mouseClicked(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
   */
  public final void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
   */
  public final void mouseExited(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
   */
  public final void mousePressed(MouseEvent e) {
    if (e.isPopupTrigger()) {
      showLogPopup(e);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
   */
  public final void mouseReleased(MouseEvent e) {
    if (e.isPopupTrigger()) {
      showLogPopup(e);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  public final void clicked(ModernClickEvent e) {
    if (e.getMessage().equals(UI.MENU_COPY)) {
      copy();
    }
  }

  /**
   * Copy selected rows to the clipboard as text.
   */
  private void copy() {
    ClipboardService.copyToClipboard(textArea.getText());
  }
}
