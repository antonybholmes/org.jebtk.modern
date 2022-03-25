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
package org.jebtk.modern.dataview;

import java.awt.Component;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.modern.panel.ModernPanel;
import org.jebtk.modern.text.ModernNumericalTextField;
import org.jebtk.modern.text.ModernTextField;

// TODO: Auto-generated Javadoc
/**
 * The class ModernDataGridCellEditor.
 */
public class ModernDataGridCellEditor extends ModernDataCellEditor implements DocumentListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member text field.
   */
  private ModernTextField mTextField = new ModernTextField();

  /**
   * The member number field.
   */
  private ModernNumericalTextField mNumberField = new ModernNumericalTextField();

  /**
   * The member text panel.
   */
  private ModernPanel mTextPanel = new ModernPanel(mTextField, ModernPanel.SMALL_BORDER);

  /**
   * The member number panel.
   */
  private ModernPanel mNumberPanel = new ModernPanel(mNumberField, ModernPanel.SMALL_BORDER);

  /**
   * The member is number.
   */
  private boolean mIsNumber;

  /*
   * private final class TextHighlightWorker implements Runnable {
   * 
   * public void run() { if (mIsNumber) { mNumberField.requestFocusInWindow();
   * mNumberField.selectAll(); } else { mTextField.requestFocusInWindow();
   * mTextField.selectAll(); } } }
   */

  /**
   * Instantiates a new modern data grid cell editor.
   */
  public ModernDataGridCellEditor() {
    this(true);
  }

  /**
   * Instantiates a new modern data grid cell editor.
   *
   * @param editable the editable
   */
  public ModernDataGridCellEditor(boolean editable) {
    mTextField.setEditable(editable);
    mTextField.getDocument().addDocumentListener(this);

    mNumberField.setEditable(editable);
    mNumberField.getDocument().addDocumentListener(this);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataCellEditor#getCellEditorValue()
   */
  @Override
  public final Object getCellEditorValue() {
    if (mIsNumber) {
      return mNumberField.getDouble();
    } else {
      return mTextField.getText();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.dataview.ModernDataCellEditor#getCellEditorComponent(
   * org.abh.lib.ui.modern.dataview.ModernData, java.lang.Object, boolean,
   * boolean, boolean, int, int)
   */
  @Override
  public Component getCellEditorComponent(ModernData view, Object value, boolean highlight, boolean isSelected,
      boolean hasFocus, int row, int column) {

    mIsNumber = false;

    if (value != null) {
      if (value instanceof Double) {
        mIsNumber = true;
        mNumberField.setText((Double) value);
        return mNumberPanel;
      } else if (value instanceof Integer) {
        mIsNumber = true;
        mNumberField.setText((Integer) value);
        return mNumberPanel;
      } else {
        mTextField.setText(value.toString());
        return mTextPanel;
      }
    } else {
      return mTextPanel;
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataCellEditor#setFocus()
   */
  @Override
  public void setFocus() {
    if (mIsNumber) {
      mNumberField.requestFocusInWindow();
      mNumberField.selectAll();
    } else {
      mTextField.requestFocusInWindow();
      mTextField.selectAll();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.event.DocumentListener#changedUpdate(javax.swing.event.
   * DocumentEvent)
   */
  @Override
  public void changedUpdate(DocumentEvent e) {
    fireChanged(new ChangeEvent(this));
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.event.DocumentListener#insertUpdate(javax.swing.event.
   * DocumentEvent)
   */
  @Override
  public void insertUpdate(DocumentEvent e) {
    changedUpdate(e);
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.event.DocumentListener#removeUpdate(javax.swing.event.
   * DocumentEvent)
   */
  @Override
  public void removeUpdate(DocumentEvent e) {
    changedUpdate(e);
  }
}