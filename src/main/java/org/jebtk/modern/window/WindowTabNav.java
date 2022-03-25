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
package org.jebtk.modern.window;

import javax.swing.JComponent;

import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.contentpane.ModernHContentPane;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.panel.AutoHidePanel;
import org.jebtk.modern.tabs.IconTabsPanel;
import org.jebtk.modern.tabs.IconTabsVectorIcon;
import org.jebtk.modern.tabs.TabsModel;

public class WindowTabNav {

  private TabsModel mLeftTabsModel;

  private IconTabsPanel mViewPanel;

  private final ModernHContentPane mTabsPane;

  private final String mName;

  private final String mTabSet;


  public WindowTabNav(String name, String tabSet, ModernHContentPane tabsPane) {
    mName = name;
    mTabSet = tabSet;
    mTabsPane = tabsPane;
  }

  /**
   * Return the default tabs pane available in the dialog to create horizontally
   * tabbed layouts such as a side bar and main panel.
   * 
   * @return
   */
  public ModernHContentPane getTabsPane() {
    return mTabsPane;
  }

  public void addTab(String name, char t, JComponent c) {
    addTab(name, new IconTabsVectorIcon(t), c);
  }

  /**
   * Create a left pane with editable tabs to conserve space.
   * 
   * @param name
   * @param c
   */
  public void addTab(String name, JComponent c) {
    getModel().addTab(name, c);

    addPane();
  }

  public void addTab(String name, ModernIcon icon, JComponent c) {
    getModel().addTab(name, icon, c);

    addPane();
  }

  public TabsModel getModel() {
    if (mLeftTabsModel == null) {
      mLeftTabsModel = new TabsModel();
      // mViewPanel = new OutlookTabsPanel(mLeftTabsModel); //new
      // IconTabsVertPanel(mLeftTabsModel, 40, 22);

      mViewPanel = new IconTabsPanel(mLeftTabsModel, 40, 24);

    }

    return mLeftTabsModel;
  }

  /**
   * Create the left pane to hold the left tabs if it does not exist.
   */
  public void addPane() {
    if (getTabsPane().tabs().containsTab(mName)) {
      return;
    }

    getTabsPane().tabs().tabSet(mTabSet).add(mName, new AutoHidePanel(mViewPanel, 100), 200, ModernWidget.WIDGET_HEIGHT,
        500);
  }

  public void changeTab(int index) {
    getModel().changeTab(index);
  }

  public void removeTab(String name) {
    getModel().removeTab(name);
  }
}
