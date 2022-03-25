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
package org.jebtk.modern.graphics.color;

import java.awt.Color;
import java.awt.Component;

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.modern.dataview.ModernData;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.table.AbstractModernTableCellEditor;
import org.jebtk.modern.window.ModernWindow;

// TODO: Auto-generated Javadoc
/**
 * The class ModernTableColorCellEditor.
 */
public class ModernTableColorCellEditor extends AbstractModernTableCellEditor implements ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The renderer.
   */
  protected ModernTableColorCellRenderer mRenderer;

  /**
   * The menu.
   */
  private ColorPopupMenu mMenu;

  /**
   * Instantiates a new modern table color cell editor.
   *
   * @param parent the parent
   */
  public ModernTableColorCellEditor(ModernWindow parent) {

    mMenu = new ColorPopupMenu(parent);

    mMenu.addClickListener(this);

    mRenderer = new ModernTableColorCellRenderer();

    mRenderer.addClickListener(this);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataCellEditor#getCellEditorValue()
   */
  @Override
  public final Object getCellEditorValue() {
    return mRenderer.getColor();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.dataview.ModernDataCellEditor#setFocus()
   */
  @Override
  public void setFocus() {
    mMenu.showPopup(mRenderer);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  @Override
  public final void clicked(ModernClickEvent e) {
    if (e.getMessage().equals(ColorSelectionModel.COLOR_CHANGED)) {
      setColor(mMenu.getSelectedColor());
    } else {
      mMenu.showPopup(mRenderer);
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
  public final Component getCellEditorComponent(ModernData table, Object value, boolean highlight, boolean isSelected,
      boolean hasFocus, int row, int column) {

    Color color = (Color) value;

    mMenu.setSelectedColor(color);

    return mRenderer.getCellRendererComponent(table, value, highlight, isSelected, hasFocus, row, column);
  }

  /**
   * Sets the color.
   *
   * @param color the new color
   */
  public void setColor(Color color) {
    mRenderer.setColor(color);
    mMenu.setSelectedColor(color);

    fireChanged(new ChangeEvent(this));
  }

  /**
   * Gets the color.
   *
   * @return the color
   */
  public Color getColor() {
    return mRenderer.getColor();
  }
}