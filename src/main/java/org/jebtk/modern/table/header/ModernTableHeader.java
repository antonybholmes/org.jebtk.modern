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
package org.jebtk.modern.table.header;

import java.awt.Color;

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.modern.dataview.ModernDataViewListener;
import org.jebtk.modern.event.ModernSelectionListener;
import org.jebtk.modern.graphics.CanvasKeyListener;
import org.jebtk.modern.graphics.CanvasMouseEvent;
import org.jebtk.modern.graphics.CanvasMouseListener;
import org.jebtk.modern.scrollpane.ModernScrollPaneHeader;
import org.jebtk.modern.table.ModernTable;
import org.jebtk.modern.theme.ThemeService;

// TODO: Auto-generated Javadoc
/**
 * The class ModernTableHeader.
 */
public class ModernTableHeader extends ModernScrollPaneHeader
    implements ModernSelectionListener, ModernDataViewListener, CanvasMouseListener, CanvasKeyListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant MAX_ANNOTATIONS.
   */
  protected static final int MAX_ANNOTATIONS = 10;

  /**
   * The constant HEADER_BACKGROUND.
   */
  public static final Color HEADER_BACKGROUND = ThemeService.getInstance().getColors().getGray(1);

  /** The Constant HEADER_SELECTED_COLOR. */
  public static final Color HEADER_SELECTED_COLOR = ThemeService.getInstance().getColors().getTheme(5);

  /** The m table. */
  protected ModernTable mTable;

  /**
   * Instantiates a new modern table header.
   *
   * @param table the table
   */
  public ModernTableHeader(ModernTable table) {
    setLayout(null);

    mTable = table;

    table.addCanvasMouseListener(this);
    table.addCanvasKeyListener(this);
    // addMouseMotionListener(this);

    table.getCellSelectionModel().addSelectionListener(this);
    // table.addComponentListener(this);
    table.addDataViewListener(this);
  }

  /*
   * @Override public void drawBackground(Graphics2D g2) { //fill(g2, getRect(),
   * ThemeService.getInstance().getColors().getHighlight(1));
   * 
   * fillBackground(g2); }
   */

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.event.ModernSelectionListener#selectionChanged(org.
   * abh. lib.event.ChangeEvent)
   */
  @Override
  public void selectionAdded(ChangeEvent e) {
    repaint();
  }

  @Override
  public void selectionRemoved(ChangeEvent e) {
    repaint();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.dataview.ModernDataViewListener#dataChanged(org.abh.
   * lib .event.ChangeEvent)
   */
  @Override
  public void dataChanged(ChangeEvent e) {
    repaint();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.dataview.ModernDataViewListener#dataUpdated(org.abh.
   * lib .event.ChangeEvent)
   */
  @Override
  public void dataUpdated(ChangeEvent e) {
    repaint();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.scrollpane.ModernScrollPaneHeader#getFixedDimension()
   */
  @Override
  public int getFixedDimension() {
    // TODO Auto-generated method stub
    return 0;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvasMouseListener#
   * canvasMouseClicked( org.abh.lib.ui.modern.graphics.CanvasMouseEvent)
   */
  @Override
  public void canvasMouseClicked(CanvasMouseEvent e) {
    fireCanvasMouseClicked(e);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvasMouseListener#
   * canvasMouseEntered( org.abh.lib.ui.modern.graphics.CanvasMouseEvent)
   */
  @Override
  public void canvasMouseEntered(CanvasMouseEvent e) {
    fireCanvasMouseEntered(e);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.graphics.ModernCanvasMouseListener#canvasMouseExited(
   * org.abh.lib.ui.modern.graphics.CanvasMouseEvent)
   */
  @Override
  public void canvasMouseExited(CanvasMouseEvent e) {
    fireCanvasMouseExited(e);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvasMouseListener#
   * canvasMousePressed( org.abh.lib.ui.modern.graphics.CanvasMouseEvent)
   */
  @Override
  public void canvasMousePressed(CanvasMouseEvent e) {
    fireCanvasMousePressed(e);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvasMouseListener#
   * canvasMouseReleased( org.abh.lib.ui.modern.graphics.CanvasMouseEvent)
   */
  @Override
  public void canvasMouseReleased(CanvasMouseEvent e) {
    fireCanvasMouseReleased(e);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvasMouseListener#
   * canvasMouseDragged( org.abh.lib.ui.modern.graphics.CanvasMouseEvent)
   */
  @Override
  public void canvasMouseDragged(CanvasMouseEvent e) {
    fireCanvasMouseDragged(e);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.graphics.ModernCanvasMouseListener#canvasMouseMoved(
   * org .abh.lib.ui.modern.graphics.CanvasMouseEvent)
   */
  @Override
  public void canvasMouseMoved(CanvasMouseEvent e) {
    fireCanvasMouseMoved(e);
  }
}
