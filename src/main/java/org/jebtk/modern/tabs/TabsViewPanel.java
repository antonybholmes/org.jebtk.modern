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

import javax.swing.border.Border;

/**
 * Provides a tab container for boilerpoint displaying of tabbed content.
 * 
 * @author Antony Holmes
 *
 */
public class TabsViewPanel extends ViewPanel implements TabEventListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member model.
   */
  private TabsModel mModel = new TabsModel();

  /**
   * Instantiates a new tabs view panel.
   */
  public TabsViewPanel() {
    setup();
  }

  /**
   * Instantiates a new tabs view panel.
   *
   * @param model the model
   */
  public TabsViewPanel(TabsModel model) {
    mModel = model;

    setup();
  }

  /**
   * Instantiates a new tabs view panel.
   *
   * @param model  the model
   * @param border the border
   */
  public TabsViewPanel(TabsModel model, Border border) {
    this(model);

    setBorder(border);
  }

  /**
   * Setup.
   */
  private void setup() {
    mModel.addTabListener(this);

    changeTab();
  }

  /**
   * Gets the tabs model.
   *
   * @return the tabs model
   */
  public TabsModel getTabsModel() {
    return mModel;
  }

  /**
   * Change tab.
   */
  public void changeTab() {
    if (mModel.getSelectedTab() != null) {
      changeView(mModel.getSelectedTab().getComponent());

      mModel.getSelectedTab().refreshTab();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.tabs.TabEventListener#tabChanged(org.abh.lib.ui.
   * modern. tabs.TabEvent)
   */
  @Override
  public void tabChanged(TabEvent e) {
    changeTab();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.tabs.TabEventListener#tabAdded(org.abh.lib.ui.modern.
   * tabs.TabEvent)
   */
  @Override
  public void tabAdded(TabEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.tabs.TabEventListener#tabRemoved(org.abh.lib.ui.
   * modern. tabs.TabEvent)
   */
  @Override
  public void tabRemoved(TabEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.tabs.TabEventListener#tabResized(org.abh.lib.ui.
   * modern. tabs.TabEvent)
   */
  @Override
  public void tabResized(TabEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void tabHighlighted(TabEvent e) {
    // TODO Auto-generated method stub

  }
}
