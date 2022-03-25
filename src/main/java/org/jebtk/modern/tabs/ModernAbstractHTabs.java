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
package org.jebtk.modern.tabs;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jebtk.modern.AssetService;
import org.jebtk.modern.button.ModernButton;
import org.jebtk.modern.button.ModernButtonGroup;
import org.jebtk.modern.button.ModernButtonWidget;
import org.jebtk.modern.button.ModernCheckButton;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;

// TODO: Auto-generated Javadoc
/**
 * Provides horizontal tabs along the top or bottom of the control.
 * 
 * @author Antony Holmes
 *
 */
public abstract class ModernAbstractHTabs extends TabsController implements ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant TAB_START_X.
   */
  protected static final int TAB_START_X = AssetService.ICON_SIZE_64;

  /**
   * The constant TAB_WIDTH.
   */
  protected static final int TAB_WIDTH = 120;

  /**
   * The constant TAB_HEIGHT.
   */
  protected static final int TAB_HEIGHT = 30;

  /**
   * The constant TAB_SEPARATION_X.
   */
  protected static final int TAB_SEPARATION_X = 2;

  // protected List<ModernHTab> tabButtons = new ArrayList<ModernHTab>();
  // protected List<Integer> tabWidths = new ArrayList<Integer>();
  // protected List<Integer> tabStarts = new ArrayList<Integer>();

  /**
   * The selected tab.
   */
  protected int selectedTab = -1;

  // protected Map<ModernHTab, Integer> tabIndexMap = new HashMap<ModernHTab,
  // Integer>();
  // private Map<Integer, ModernHTab> indexTabMap = new HashMap<Integer,
  // ModernHTab>();

  /**
   * The shift left button.
   */
  protected ModernButton shiftLeftButton = new ModernButton(
      AssetService.getInstance().loadIcon("arrow_left", AssetService.ICON_SIZE_16));

  /**
   * The shift right button.
   */
  protected ModernButton shiftRightButton = new ModernButton(
      AssetService.getInstance().loadIcon("arrow_right", AssetService.ICON_SIZE_16));

  /**
   * The tab start x.
   */
  protected int tabStartX = TAB_START_X;

  /**
   * Determines which tabs to display.
   */
  protected int tabOffsetIndex = 0;

  /**
   * The view panel.
   */
  private TabsViewPanel viewPanel = null;

  /**
   * The buttons.
   */
  protected List<ModernCheckButton> buttons = new ArrayList<ModernCheckButton>();

  /**
   * The group.
   */
  protected ModernButtonGroup group = new ModernButtonGroup();

  /**
   * The button map.
   */
  protected Map<ModernCheckButton, Integer> buttonMap = new HashMap<ModernCheckButton, Integer>();

  /**
   * Instantiates a new modern abstract h tabs.
   *
   * @param model the model
   */
  public ModernAbstractHTabs(TabsModel model) {
    super(model);

    viewPanel = new TabsViewPanel(model);

    add(viewPanel, BorderLayout.CENTER);

    // this.addComponentListener(this);

    shiftLeftButton.addClickListener(this);
    shiftRightButton.addClickListener(this);
  }

  /**
   * Adds the tab.
   *
   * @param button the button
   */
  public final void addTab(ModernCheckButton button) {
    button.addClickListener(this);
    buttonMap.put(button, buttons.size());
    buttons.add(button);
    group.add(button);

    if (buttonMap.size() == 0) {
      button.setSelected(true);
    }
  }

  /**
   * Clear.
   */
  public void clear() {

    buttons.clear();
    buttonMap.clear();
    group.clear();

    // getTabsModel().removeAllTabs();
    // tabIndexMap.clear();
    // indexTabMap.clear();

    this.tabStartX = TAB_START_X;

    selectedTab = -1;

    revalidate();
    repaint();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  public final void clicked(ModernClickEvent e) {
    if (e.getSource().equals(this.shiftLeftButton)) {
      shiftLeft();
    } else if (e.getSource().equals(this.shiftRightButton)) {
      shiftRight();
    } else {
      changeTab((ModernButton) e.getSource());
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.tabs.TabsController#tabChanged(org.abh.lib.ui.modern.
   * tabs.TabEvent)
   */
  @Override
  public void tabChanged(TabEvent e) {
    if (getTabsModel().getSelectedIndex() != -1) {
      if (!buttons.get(getTabsModel().getSelectedIndex()).isSelected()) {
        buttons.get(getTabsModel().getSelectedIndex()).doClick();
      }
    }
  }

  /**
   * Change tab.
   *
   * @param button the button
   */
  private void changeTab(ModernButtonWidget button) {
    changeTab(buttonMap.get(button));
  }

  /**
   * Change tab.
   *
   * @param index the index
   */
  private void changeTab(int index) {
    getTabsModel().changeTab(index);
  }

  /**
   * Shift right.
   */
  public abstract void shiftRight();

  /**
   * Shift left.
   */
  public abstract void shiftLeft();

  /**
   * Refresh.
   */
  public abstract void refresh();
}
