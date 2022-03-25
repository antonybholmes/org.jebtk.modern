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
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;

import org.jebtk.modern.button.ModernClickWidget;
import org.jebtk.modern.panel.VBoxAutoSizeLayout;

// TODO: Auto-generated Javadoc
/**
 * Specialised popup for displaying menus.
 *
 * @author Antony Holmes
 */
public class ModernPopupMenu2 extends ModernPopup2 {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member items.
   */
  protected List<ModernClickWidget> mItems = new ArrayList<ModernClickWidget>();

  /**
   * The member cons.
   */
  // public static GridBagConstraints CONS = new GridBagConstragetInts();

  // static {
  // CONS.fill = GridBagConstraints.HORIZONTAL;
  // CONS.weightx = 1;
  // CONS.gridx = 0;
  // }

  /**
   * Instantiates a new modern popup menu.
   */
  public ModernPopupMenu2() {
    setLayout(new VBoxAutoSizeLayout());
  }

  /**
   * Instantiates a new modern popup menu.
   *
   * @param menuItems the menu items
   */
  public ModernPopupMenu2(ModernClickWidget... menuItems) {
    this();

    add(menuItems);
  }

  /**
   * Gets the item count.
   *
   * @return the item count
   */
  public int getItemCount() {
    return mItems.size();
  }

  /**
   * Adds the menu item.
   *
   * @param menuItem the menu item
   */
  public void addMenuItem(ModernClickWidget menuItem) {
    add(menuItem);
  }

  @Override
  public void add(Component c, Object constraints) {
    if (c instanceof ModernClickWidget) {
      add((ModernClickWidget) c);
    } else {
      add(c);
    }
  }

  @Override
  public Component add(Component c) {
    if (c instanceof ModernClickWidget) {
      return add((ModernClickWidget) c);
    } else {
      return super.add(c);
    }
  }

  /**
   * Adds the.
   *
   * @param menuItem the menu item
   * @return
   */
  public Component add(ModernClickWidget c) {
    mItems.add(c);

    c.addClickListener(this);

    super.add(c);

    return c;
  }

  /**
   * Adds the.
   *
   * @param menuItems the menu items
   */
  public void add(ModernClickWidget... menuItems) {
    for (ModernClickWidget menuItem : menuItems) {
      add(menuItem);
    }
  }

  // @Override
  // public Component add(Component c) {
  // super.add(c);
  //
  // return c;
  // }

  /**
   * Adds the glue.
   */
  public void addGlue() {
    add(Box.createVerticalGlue());
  }

  public void addSeparator() {
    addMenuItem(new ModernMenuSeparator());
  }

  /*
   * public void addPopupMenuListener(PopupMenuListener l) {
   * popupListeners.add(l); }
   */

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.menu.ModernPopup#clear()
   */
  public void clear() {
    // super.removeAll();
    mItems.clear();

    super.clear();
  }

  /*
   * public final PopupMenuListeners getPopupMenuListeners() { return
   * popupListeners; }
   */

  /**
   * Gets the.
   *
   * @param index the index
   * @return the modern click widget
   */
  public ModernClickWidget get(int index) {
    return mItems.get(index);
  }
}
