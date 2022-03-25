/**
 * Copyright (C) 2019, Antony Holmes
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

import java.util.HashMap;
import java.util.Map;

import org.jebtk.modern.UI;
import org.jebtk.modern.button.ButtonStyle;
import org.jebtk.modern.button.ModernButtonGroup;
import org.jebtk.modern.button.ModernCheckRadioButton;
import org.jebtk.modern.button.ModernClickWidget;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.panel.VBoxAutoWidthLayout;
import org.jebtk.modern.theme.ColorStyle;

/**
 * Create vertical buttons to change between tabs.
 * 
 * @author Antony Holmes
 *
 */
public class OutlookTabs extends TabsController {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  private Map<String, ModernClickWidget> mTabMap = new HashMap<String, ModernClickWidget>();
  private Map<ModernClickWidget, Tab> mButtonMap = new HashMap<ModernClickWidget, Tab>();

  /**
   * Instantiates a new text tabs.
   *
   * @param model  the model
   * @param center the center
   */
  public OutlookTabs(TabsModel model) {
    super(model);

    setLayout(new VBoxAutoWidthLayout());

    // setBorder(BORDER);
  }

  @Override
  public void tabAdded(TabEvent e) {
    refresh();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.tabs.TabEventListener#tabRemoved(org.abh.lib.ui.
   * modern. tabs.TabEvent)
   */
  @Override
  public void tabRemoved(TabEvent e) {
    refresh();
  }

  public void tabChanged(TabEvent e) {
    String name = e.getTab().getName();

    if (mTabMap.containsKey(name)) {
      mTabMap.get(name).doClick();
    }
  }

  private void refresh() {
    mTabMap.clear();
    mButtonMap.clear();
    removeAll();

    ModernButtonGroup group = new ModernButtonGroup();

    for (Tab tab : getTabsModel()) {
      ModernClickWidget button = new ModernCheckRadioButton(tab.getName()).setColorStyle(ColorStyle.RIBBON)
          .setButtonStyle(ButtonStyle.RECT);

      UI.setSize(button, 100, 32);

      add(button);
      group.add(button);
      mTabMap.put(tab.getName(), button);
      mButtonMap.put(button, tab);

      button.addClickListener(new ModernClickListener() {

        @Override
        public void clicked(ModernClickEvent e) {
          changeTab((ModernClickWidget) e.getSource());
        }
      });
    }
  }

  private void changeTab(ModernClickWidget e) {
    getTabsModel().changeTab(mButtonMap.get(e).getName());
  }
}
