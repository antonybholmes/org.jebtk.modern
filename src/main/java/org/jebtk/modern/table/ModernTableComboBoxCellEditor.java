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
package org.jebtk.modern.table;

import java.awt.Component;

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.event.ChangeListener;
import org.jebtk.modern.combobox.ModernComboBox;
import org.jebtk.modern.dataview.ModernData;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;

// TODO: Auto-generated Javadoc
/**
 * The class ModernTableComboBoxCellEditor.
 */
public class ModernTableComboBoxCellEditor extends AbstractModernTableCellEditor
    implements ModernClickListener, ChangeListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member combo.
   */
  protected ModernComboBox mCombo;

  /**
   * The member default value.
   */
  private String mDefaultValue = "";

  /**
   * Instantiates a new modern table combo box cell editor.
   *
   * @param combo the combo
   */
  public ModernTableComboBoxCellEditor(ModernComboBox combo) {
    mCombo = combo;

    setup();
  }

  /**
   * Instantiates a new modern table combo box cell editor.
   *
   * @param combo        the combo
   * @param defaultValue the default value
   */
  public ModernTableComboBoxCellEditor(ModernComboBox combo, String defaultValue) {

    mCombo = combo;
    mDefaultValue = defaultValue;

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    mCombo.addClickListener(this);
    mCombo.addTextChangedListener(this);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataCellEditor#getCellEditorValue()
   */
  @Override
  public final Object getCellEditorValue() {
    return mCombo.getSelectedItem();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  @Override
  public final void clicked(ModernClickEvent e) {
    fireChanged(new ChangeEvent(this));
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
  public Component getCellEditorComponent(ModernData table, Object value, boolean highlight, boolean isSelected,
      boolean hasFocus, int row, int column) {
    if (value != null && !value.toString().equals("")) {
      mCombo.setText(value.toString());
    } else {
      mCombo.setText(mDefaultValue);
    }

    return mCombo;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.event.ChangeListener#changed(org.abh.lib.event.ChangeEvent)
   */
  @Override
  public void changed(ChangeEvent e) {
    fireChanged(e);
  }
}