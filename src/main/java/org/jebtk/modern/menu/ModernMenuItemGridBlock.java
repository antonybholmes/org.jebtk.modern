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
package org.jebtk.modern.menu;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.UI;
import org.jebtk.modern.button.ModernClickWidget;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.panel.ModernPanel;

// TODO: Auto-generated Javadoc
/**
 * Allows multiple menu items to be added to a grid that behaves like a single
 * menu item.
 * 
 * @author Antony Holmes
 *
 */
public class ModernMenuItemGridBlock extends ModernMenuItem implements ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member panel.
   */
  private ModernPanel mPanel = new ModernPanel();

  /**
   * The member width.
   */
  private int mWidth = -1;

  /**
   * The member items.
   */
  private List<ModernComponent> mItems = new ArrayList<ModernComponent>();

  /**
   * The member columns.
   */
  private int mColumns;

  /**
   * Instantiates a new modern menu item grid block.
   *
   * @param title         the title
   * @param menuItemWidth the menu item width
   * @param columns       the columns
   */
  public ModernMenuItemGridBlock(String title, int menuItemWidth, int columns) {
    super("Grid Block");

    // removeAll();

    add(new ModernTitleMenuItem(title), BorderLayout.PAGE_START);

    add(mPanel, BorderLayout.CENTER);

    setWidth(menuItemWidth, columns);
  }

  /**
   * Sets the width.
   *
   * @param width   the width
   * @param columns the columns
   */
  private void setWidth(int width, int columns) {
    mColumns = columns;
    mWidth = width * columns;

    mPanel.setLayout(new GridLayout(0, columns, 0, 0));

    setSize();
  }

  /**
   * Adds the.
   *
   * @param item the item
   */
  public void add(ModernClickWidget item) {
    addMenuItem(item);
  }

  /**
   * Adds the menu item.
   *
   * @param item the item
   */
  public void addMenuItem(ModernClickWidget item) {
    mPanel.add(item);
    mItems.add(item);

    item.addClickListener(this);

    setSize();
  }

  /**
   * Sets the size.
   */
  private void setSize() {
    UI.setSize(mPanel, mWidth,
        (mItems.size() / mColumns + (mItems.size() % mColumns > 0 ? 1 : 0)) * ModernIconMenuItem.HEIGHT);

    // The size of the menu is the size of the grid plus the title height
    UI.setSize(this, mWidth, mPanel.getPreferredSize().height + ModernTitleMenuItem.HEIGHT);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  @Override
  public void clicked(ModernClickEvent e) {
    // Forward the message
    fireClicked(e.getMessage());
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.widget.ModernWidget#drawForeground(java.awt.Graphics2D)
   */
  @Override
  public void drawForeground(Graphics2D g2) {
    // Do nothing
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.menu.ModernMenuItem#drawBackground(java.awt.Graphics2D)
   */
  @Override
  public void drawBackground(Graphics2D g2) {
    // Do nothing
  }
}