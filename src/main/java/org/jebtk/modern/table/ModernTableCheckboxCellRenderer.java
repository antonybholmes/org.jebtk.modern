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
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import org.jebtk.modern.AssetService;
import org.jebtk.modern.dataview.ModernData;
import org.jebtk.modern.dataview.ModernDataCellRenderer;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.graphics.icons.CheckedVectorIcon;
import org.jebtk.modern.graphics.icons.UnCheckedVectorIcon;

// TODO: Auto-generated Javadoc
/**
 * The class ModernTableCheckboxCellRenderer.
 */
public class ModernTableCheckboxCellRenderer extends ModernDataCellRenderer implements MouseListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant CHECKBOX_CHANGED.
   */
  public static final String CHECKBOX_CHANGED = "checkbox_changed";

  /**
   * The selected.
   */
  protected boolean selected = false;

  /**
   * Instantiates a new modern table checkbox cell renderer.
   */
  public ModernTableCheckboxCellRenderer() {
    addMouseListener(this);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {

    int iconX = (getWidth() - AssetService.ICON_SIZE_16) / 2;
    int iconY = (getHeight() - AssetService.ICON_SIZE_16) / 2;

    // g2.setColor(Color.WHITE);
    // g2.fillRect(0, 0, getWidth(), getHeight());

    if (selected) {
      AssetService.getInstance().loadIcon(CheckedVectorIcon.class, 16).drawIcon(g2, iconX, iconY,
          AssetService.ICON_SIZE_16);
    } else {
      AssetService.getInstance().loadIcon(UnCheckedVectorIcon.class, 16).drawIcon(g2, iconX, iconY,
          AssetService.ICON_SIZE_16);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.dataview.ModernDataCellRenderer#
   * getCellRendererComponent(org.abh.lib.ui.modern.dataview.ModernData,
   * java.lang.Object, boolean, boolean, boolean, int, int)
   */
  public final Component getCellRendererComponent(ModernData table, Object value, boolean highlight, boolean isSelected,
      boolean hasFocus, int row, int column) {

    selected = (Boolean) value;

    // return renderer;

    // this.selected = (Boolean)value;

    // repaint();

    return super.getCellRendererComponent(table, value, highlight, isSelected, hasFocus, row, column);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernClickWidget#isSelected()
   */
  public boolean isSelected() {
    return selected;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernClickWidget#setSelected(boolean)
   */
  public void setSelected(boolean selected) {
    this.selected = selected;

    repaint();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
   */
  @Override
  public void mousePressed(MouseEvent e) {
    fireClicked(new ModernClickEvent(this, CHECKBOX_CHANGED));
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseClicked(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseExited(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseReleased(MouseEvent e) {
    // TODO Auto-generated method stub

  }
}