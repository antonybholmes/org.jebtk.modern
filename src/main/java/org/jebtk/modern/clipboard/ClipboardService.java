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

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Collection;

import javax.swing.JList;
import javax.swing.JTable;

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.text.Join;
import org.jebtk.core.text.TextUtils;

/**
 * Provides a centralised mechanism for registering controls to send and receive
 * data from the clipboard.
 *
 * @author Antony Holmes
 *
 */
public class ClipboardService extends ClipboardEventListeners implements IClipboard {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The Class ClipboardServiceLoader.
   */
  private static class ClipboardServiceLoader {

    /** The Constant INSTANCE. */
    private static final ClipboardService INSTANCE = new ClipboardService();
  }

  /**
   * Gets the single instance of SettingsService.
   *
   * @return single instance of SettingsService
   */
  public static ClipboardService getInstance() {
    return ClipboardServiceLoader.INSTANCE;
  }

  // public static final String CLIPBOARD_EVENT = "clipboard";

  /**
   * Sent when a new control activates.
   */
  // public static final String CONTROL_ACTIVATE = "clipboard_control_activate";

  /**
   * Sent when a control deactivates.
   */
  // public static final String CONTROL_DEACTIVATE =
  // "clipboard_control_deactivate";

  public static final String CLIPBOARD_CONTROL_CHANGED = "clipboard_control_changed";

  /**
   * The member control.
   */
  private IClipboardUI mControl;

  /**
   * Instantiates a new clipboard service.
   */
  private ClipboardService() {
    // do nothing
  }

  /**
   * Register.
   *
   * @param control the control
   */
  public synchronized void register(IClipboardUI control) {
    mControl = control;

    fireClipboardChanged(new ChangeEvent(this, CLIPBOARD_CONTROL_CHANGED));
  }

  /**
   * Unregister.
   *
   * @param control the control
   */
  public synchronized void unregister(IClipboardUI control) {
    if (!control.equals(mControl)) {
      return;
    }

    mControl = null;

    fireClipboardChanged(new ChangeEvent(this, CLIPBOARD_CONTROL_CHANGED));
  }

  /**
   * Copies the current control contents to the clipboard. Same as calling
   * getControl().copy().
   */
  @Override
  public synchronized void copy() {
    if (mControl != null) {

      mControl.copy();
    }
  }

  /**
   * Pastes the clipboard to the current control. Same as calling
   * getControl().paste().
   */
  @Override
  public synchronized void paste() {
    if (mControl != null) {
      mControl.paste();
    }
  }

  /**
   * Cuts the current text from the control. Same as calling getControl().cut().
   */
  @Override
  public void cut() {
    if (mControl != null) {
      mControl.cut();
    }
  }

  /**
   * Returns the current clipboard control, which can be null.
   *
   * @return the control
   */
  public synchronized IClipboardUI getControl() {
    return mControl;
  }

  /**
   * Copies a table model to the clipboard.
   *
   * @param table the table
   */
  public static final void copy(JTable table) {
    if (table.getModel() == null) {
      return;
    }

    StringBuilder builder = new StringBuilder();

    for (int i = 0; i < table.getModel().getColumnCount(); ++i) {
      builder.append(table.getModel().getColumnName(i));

      if (i < table.getModel().getColumnCount() - 1) {
        builder.append(TextUtils.TAB_DELIMITER);
      }
    }

    builder.append(TextUtils.NEW_LINE);

    for (int i = 0; i < table.getModel().getRowCount(); ++i) {
      for (int j = 0; j < table.getModel().getColumnCount(); ++j) {
        builder.append(table.getModel().getValueAt(i, j).toString());

        if (j < table.getModel().getColumnCount() - 1) {
          builder.append(TextUtils.TAB_DELIMITER);
        }
      }

      builder.append(TextUtils.NEW_LINE);
    }

    /*
     * if (table.getSelectedRowCount() == 0) { // copy all rows for (int i = 0; i <
     * table.getModel().getRowCount(); ++i) { for (int j = 0; j <
     * table.getModel().getColumnCount(); ++j) {
     * builder.append(table.getModel().getValueAt(i, j).toString());
     * 
     * if (j < table.getModel().getColumnCount() - 1) {
     * builder.append(Text.TAB_DELIMITER); } }
     * 
     * builder.append(Text.NEWLINE); } } else { for (int i :
     * table.getSelectedRows()) { for (int j = 0; j <
     * table.getModel().getColumnCount(); ++j) {
     * builder.append(table.getModel().getValueAt(i, j).toString());
     * 
     * if (j < table.getModel().getColumnCount() - 1) {
     * builder.append(Text.TAB_DELIMITER); } }
     * 
     * builder.append(Text.NEWLINE); } }
     */

    copyToClipboard(builder.toString());
  }

  /**
   * Copy.
   *
   * @param list the list
   */
  public static final void copy(JList<?> list) {
    StringBuilder builder = new StringBuilder();

    for (int i = 0; i < list.getModel().getSize(); ++i) {
      builder.append(list.getModel().getElementAt(i));
      builder.append(TextUtils.NEW_LINE);
    }

    /*
     * if (list.getSelectedIndices().length == 0) { // copy all rows for (int i = 0;
     * i < list.getModel().getSize(); ++i) {
     * builder.append(list.getModel().getElementAt(i));
     * builder.append(Text.NEWLINE); } } else { for (int i :
     * list.getSelectedIndices()) { builder.append(list.getModel().getElementAt(i));
     * builder.append(Text.NEWLINE); } }
     */

    copyToClipboard(builder.toString());
  }

  /**
   * Copy to clipboard.
   *
   * @param text the text
   */
  public static final void copyToClipboard(StringBuilder text) {
    copyToClipboard(text.toString());
  }

  /**
   * Copy to the clipboard.
   *
   * @param text the text
   */
  public static final void copyToClipboard(String text) {
    StringSelection stringModernSelection = new StringSelection(text);
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringModernSelection, null);
  }

  /**
   * Gets the clipboard contents.
   *
   * @return the clipboard contents
   */
  public static final String getClipboardContents() {

    java.awt.datatransfer.Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

    // odd: the Object param of getContents is not currently used
    Transferable contents = clipboard.getContents(null);

    boolean hasTransferableText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);

    String result = "";

    if (hasTransferableText) {
      try {
        result = (String) contents.getTransferData(DataFlavor.stringFlavor);
      } catch (UnsupportedFlavorException | IOException e) {
        e.printStackTrace();
      }
    }

    return result;
  }

  public static void copyToClipboard(Collection<String> l) {
    copyToClipboard(Join.onNewLine().values(l).toString());
  }
}
