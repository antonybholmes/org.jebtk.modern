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
import org.jebtk.modern.dataview.ModernData;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;

// TODO: Auto-generated Javadoc
/**
 * The class ModernTableCheckboxCellEditor.
 */
public class ModernTableCheckboxCellEditor extends AbstractModernTableCellEditor implements ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member renderer.
   */
  protected ModernTableCheckboxCellRenderer mRenderer = new ModernTableCheckboxCellRenderer();

  /**
   * Instantiates a new modern table checkbox cell editor.
   */
  public ModernTableCheckboxCellEditor() {
    mRenderer.addClickListener(this);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataCellEditor#getCellEditorValue()
   */
  public final Object getCellEditorValue() {
    return mRenderer.isSelected();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  public final void clicked(ModernClickEvent e) {
    setSelected(!isSelected());
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.dataview.ModernDataCellEditor#getCellEditorComponent(
   * org.abh.lib.ui.modern.dataview.ModernData, java.lang.Object, boolean,
   * boolean, boolean, int, int)
   */
  public final Component getCellEditorComponent(ModernData table, Object value, boolean highlight, boolean isSelected,
      boolean hasFocus, int row, int column) {

    // renderer.setSelected((Boolean)value);

    // return renderer;

    // renderer.setDisplay(table, value, isSelected, true, row, column);

    // Setup the renderer to reflect the value
    Component c = mRenderer.getCellRendererComponent(table, value, highlight, isSelected, hasFocus, row, column);

    // Before returning the component, force the selection
    // to be inverted to simulate the user clicking
    setSelected(!isSelected());

    return c;
  }

  /**
   * Checks if is selected.
   *
   * @return true, if is selected
   */
  public boolean isSelected() {
    return mRenderer.isSelected();
  }

  /**
   * Sets the selected.
   *
   * @param selected the new selected
   */
  public void setSelected(boolean selected) {
    mRenderer.setSelected(selected);

    fireChanged(new ChangeEvent(this));
  }
}