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
package org.jebtk.modern.search;

import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.modern.ColumnLayout;
import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.UI;
import org.jebtk.modern.button.ButtonsBox;
import org.jebtk.modern.button.ModernButton;
import org.jebtk.modern.button.ModernCheckSwitch;
import org.jebtk.modern.button.ModernTwoStateWidget;
import org.jebtk.modern.dialog.ModernDialogButton;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.menu.ModernPopup;
import org.jebtk.modern.panel.ModernPanel;
import org.jebtk.modern.window.ModernWindow;

/**
 * The class FilterPopupMenu.
 */
public class FilterPopupMenu extends ModernPopup implements FilterEventListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant SIZE.
   */
  private static final Dimension SIZE = new Dimension(280, 24);

  /**
   * The member filter model.
   */
  private FilterModel mFilterModel;

  /**
   * The member button map.
   */
  private Map<String, ModernTwoStateWidget> mButtonMap = new HashMap<String, ModernTwoStateWidget>();

  /**
   * The member check menu item.
   */
  private ModernTwoStateWidget mCheckMenuItem;

  /**
   * Instantiates a new filter popup menu.
   *
   * @param parent      the parent
   * @param filterModel the filter model
   */
  public FilterPopupMenu(ModernWindow parent, FilterModel filterModel) {
    mFilterModel = filterModel;

    // mSortModel.addSampleSortListener(this);
    mFilterModel.addFilterListener(this);

    mCheckMenuItem = new ModernCheckSwitch(UI.MENU_SELECT_ALL, true);
    mCheckMenuItem.addClickListener(this);
    UI.setSize(mCheckMenuItem, SIZE);

    setup();

    // addMenuItem(new ModernMenuDivider());

    // checkMenuItem = new ModernCheckBoxMenuItem("Check All", true);
    // Ui.setSize(checkMenuItem, SIZE);

    // addMenuItem(checkMenuItem);
  }

  /**
   * Setup.
   */
  private void setup() {
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

    removeAll();

    // ModernTitleMenuItem title = new ModernTitleMenuItem("Filters");

    // Ui.setSize(title, SIZE);

    // add(title);

    ModernComponent box = new ModernComponent(new ColumnLayout(3, 160, 28));

    mCheckMenuItem.setSelected(true);
    box.add(mCheckMenuItem);

    for (String name : mFilterModel) {
      ModernTwoStateWidget menuItem = new ModernCheckSwitch(name, mFilterModel.keep(name));

      menuItem.addClickListener(this);

      UI.setSize(menuItem, SIZE);

      mButtonMap.put(name, menuItem);

      box.add(menuItem);
    }

    // ModernScrollPane scrollPane = new ModernScrollPane(box);

    // UI.setSize(scrollPane, SIZE.width, 200);
    // //scrollPane.setMinimuSize(Short.MAX_VALUE, 300);
    // scrollPane.setBorder(ModernPanel.BORDER);
    // scrollPane.setHorizontalScrollBarPolicy(ScrollBarPolicy.NEVER);
    add(box);

    Box buttonBox = new ButtonsBox();

    ModernButton button = new ModernDialogButton(UI.BUTTON_OK);
    button.addClickListener(this);
    buttonBox.add(button);

    buttonBox.add(ModernPanel.createHGap());

    button = new ModernDialogButton(UI.BUTTON_CANCEL);
    button.addClickListener(this);
    buttonBox.add(button);

    buttonBox.setBorder(ModernPanel.BORDER);
    add(buttonBox);

    // setBorder(ModernPanel.BORDER);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.menu.ModernPopup#clicked(org.abh.lib.ui.modern.event.
   * ModernClickEvent)
   */
  @Override
  public void clicked(ModernClickEvent e) {
    if (e.getMessage().equals(UI.BUTTON_OK)) {
      Map<String, Boolean> updates = new HashMap<String, Boolean>();

      for (String name : mButtonMap.keySet()) {
        updates.put(name, mButtonMap.get(name).isSelected());

      }

      mFilterModel.setSelected(updates);

      super.clicked(new ModernClickEvent(this, e.getMessage()));
    } else if (e.getMessage().equals(UI.BUTTON_CANCEL)) {
      super.clicked(new ModernClickEvent(this, e.getMessage()));
    } else if (e.getMessage().equals("Select All")) {
      for (String name : mButtonMap.keySet()) {
        mButtonMap.get(name).setSelected(mCheckMenuItem.isSelected());
      }
    } else {
      // mFilterModel.setFilter(e.getMessage(),
      // mButtonMap.get(e.getMessage()).isSelected());
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.search.FilterEventListener#filterChanged(org.abh.lib.
   * event.ChangeEvent)
   */
  @Override
  public void filtersUpdated(ChangeEvent e) {
    for (String name : mFilterModel) {
      ModernTwoStateWidget menuItem = mButtonMap.get(name);

      menuItem.setSelected(mFilterModel.keep(name));
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.search.FilterEventListener#filtersUpdated(org.abh.
   * lib. event.ChangeEvent)
   */
  @Override
  public void filtersChanged(ChangeEvent e) {
    setup();
  }
}
