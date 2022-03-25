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

import java.awt.Component;
import java.awt.Dimension;
import java.util.List;

import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.button.ModernClickWidget;
import org.jebtk.modern.scrollpane.ModernScrollPane;
import org.jebtk.modern.scrollpane.ScrollBarPolicy;

// TODO: Auto-generated Javadoc
/**
 * The class ModernScrollPopupMenu.
 */
public class ModernScrollPopupMenu2 extends ModernPopupMenu2 {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member menu panel.
   */
  protected ModernComponent mScrollMenuPanel = new ModernScrollMenuPanel();

  /**
   * The member scroll pane.
   */
  protected ModernScrollPane mScrollPane = null;

  /**
   * The member max height.
   */
  protected int mMaxHeight;

  /**
   * Instantiates a new modern scroll popup menu.
   */
  public ModernScrollPopupMenu2() {
    this(260);
  }

  /**
   * Instantiates a new modern scroll popup menu.
   *
   * @param maxHeight the max height
   */
  public ModernScrollPopupMenu2(int maxHeight) {
    mMaxHeight = maxHeight;

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {

    mScrollPane = new ModernScrollPane(mScrollMenuPanel);
    // scrollPane.setScrollbarMarginWidth(Resources.ICON_SIZE_16);

    // scrollPane.getViewport().setBackground(Color.WHITE);
    // scrollPane.getViewport().setBorder(BorderFactory.createEmptyBorder());

    // Dimension size = new Dimension(menuItemSize.width * columns,
    // menuItemSize.height * maxRows);
    //
    // scrollPane.setCanvasSize(size);
    mScrollPane.setHorizontalScrollBarPolicy(ScrollBarPolicy.NEVER);
    // scrollPane.setBorder(BorderService.getInstance().createLineBorder(Color.red));

    add(mScrollPane);
  }

  /**
   * Adds the scroll menu items.
   *
   * @param items the items
   */
  public final void addScrollMenuItems(List<ModernMenuItem> items) {
    for (ModernMenuItem item : items) {
      addScrollMenuItem(item);

      for (ModernMenuItem subItem : item.getSubMenuItems()) {
        addScrollMenuItem(subItem);
      }
    }
  }

  /**
   * Adds the scroll menu item.
   *
   * @param menuItem the menu item
   */
  public void addScrollMenuItem(Component menuItem) {
    mScrollMenuPanel.add(menuItem);
  }

  public void addScrollMenuItem(ModernClickWidget menuItem) {

    mItems.add(menuItem);

    menuItem.addClickListener(this);

    // menuItem.setCanvasSize(menuItemSize);
    // menuItem.setMaximumSize(menuItemSize);

    mScrollMenuPanel.add(menuItem);

    setSize();
  }

  /**
   * Clear scroll menu items.
   */
  public void clearScrollMenuItems() {

    mItems.clear();

    mScrollMenuPanel.removeAll();

    revalidate();
    repaint();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.menu.ModernPopup#showPopup(javax.swing.JComponent)
   */
  private void setSize() {

    int h = 0;
    int w = 0;

    for (int i = 0; i < mScrollMenuPanel.getComponentCount(); ++i) {
      w = Math.max(w, mScrollMenuPanel.getComponent(i).getPreferredSize().width);

      h += mScrollMenuPanel.getComponent(i).getPreferredSize().height;
    }

    w = Math.max(w, getWidth());

    mScrollMenuPanel.setPreferredSize(new Dimension(w, h));

    // w += scrollPane.getScrollbarMarginWidth();

    mScrollPane.setPreferredSize(new Dimension(w, Math.min(h, mMaxHeight)));
  }

  /**
   * Clear the scroll menu items.
   */
  @Override
  public void clear() {
    mScrollMenuPanel.removeAll();

    super.clear();

    add(mScrollPane);
  }

  public ModernComponent getScrollPanel() {
    return mScrollMenuPanel;
  }
}
