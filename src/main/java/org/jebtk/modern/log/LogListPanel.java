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

import org.jebtk.core.log.LogEvent;
import org.jebtk.core.text.TextUtils;
import org.jebtk.modern.AssetService;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.clipboard.ClipboardService;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.list.ModernList;
import org.jebtk.modern.list.ModernListModel;
import org.jebtk.modern.menu.ModernIconMenuItem;
import org.jebtk.modern.menu.ModernMenuSeparator;
import org.jebtk.modern.menu.ModernPopupMenu;
import org.jebtk.modern.scrollpane.ModernScrollPane;

/**
 * The class LogListPanel.
 */
public class LogListPanel extends ModernWidget implements MouseListener, ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The model.
   */
  private ModernListModel<LogEvent> model = new ModernListModel<LogEvent>();

  /**
   * The list.
   */
  private ModernList<LogEvent> list = new ModernList<LogEvent>(new LogEventListItemRenderer());

  /**
   * The log popup.
   */
  private ModernPopupMenu logPopup = new ModernPopupMenu();

  /**
   * The constant MENU_CLEAR.
   */
  private static final String MENU_CLEAR = "Clear Log";

  /**
   * Instantiates a new log list panel.
   */
  public LogListPanel() {
    list.setModel(model);

    setBorder(DOUBLE_BORDER);

    ModernScrollPane scrollPane = new ModernScrollPane(list);
    // scrollPane.setBorder(lineBorder);

    add(scrollPane, BorderLayout.CENTER);

    ModernIconMenuItem menuItem = new ModernIconMenuItem(MENU_CLEAR,
        AssetService.getInstance().loadIcon("clear_log", AssetService.ICON_SIZE_16));
    menuItem.addClickListener(this);
    logPopup.add(menuItem);

    logPopup.add(new ModernMenuSeparator());

    menuItem = new ModernIconMenuItem(UI.MENU_COPY,
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
    model.addValue(event);
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
    if (e.getMessage().equals(MENU_CLEAR)) {
      model.clear();
    } else if (e.getMessage().equals(UI.MENU_COPY)) {
      copy();
    } else {
      // do nothing
    }
  }

  /**
   * Copy selected rows to the clipboard as text.
   */
  private void copy() {
    StringBuilder buffer = new StringBuilder();

    for (int i : list.getSelectionModel()) {
      LogEvent logEvent = (LogEvent) model.getValueAt(i);

      buffer.append("[");
      buffer.append(logEvent.getFormattedDate());
      buffer.append("] ");
      buffer.append(" ");
      buffer.append(logEvent.getMessage());
      buffer.append(TextUtils.NEW_LINE);
    }

    ClipboardService.copyToClipboard(buffer.toString());
  }
}
