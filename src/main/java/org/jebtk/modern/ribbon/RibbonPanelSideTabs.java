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
package org.jebtk.modern.ribbon;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;

import org.jebtk.modern.button.ModernButtonGroup;
import org.jebtk.modern.button.ModernClickWidget;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.tabs.TabEvent;
import org.jebtk.modern.tabs.TabsController;
import org.jebtk.modern.tabs.TabsModel;

/**
 * Provides a sidebar with clickable tabs on ribbon panels.
 * 
 * @author Antony Holmes
 *
 */
public class RibbonPanelSideTabs extends TabsController implements ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member buttons.
   */
  private List<ModernClickWidget> mButtons = new ArrayList<ModernClickWidget>();

  /**
   * The button map.
   */
  private Map<ModernClickWidget, Integer> buttonMap = new HashMap<ModernClickWidget, Integer>();

  /**
   * The group.
   */
  private ModernButtonGroup group = new ModernButtonGroup();

  /**
   * Instantiates a new ribbon panel side tabs.
   *
   * @param model the model
   */
  public RibbonPanelSideTabs(TabsModel model) {
    super(model);

    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
  }

  /**
   * Adds the tab.
   *
   * @param name the name
   * @param icon the icon
   */
  public final void addTab(String name, ModernIcon icon) {
    ModernClickWidget button = new RibbonPanelSideTabsButton(name, icon); // VerticalTabsModernCheckButton(name,
    // JLabel.LEFT);

    addTab(button);
  }

  /**
   * Adds the tab.
   *
   * @param button the button
   */
  private final void addTab(ModernClickWidget button) {
    button.addClickListener(this);

    buttonMap.put(button, mButtons.size());
    mButtons.add(button);
    group.add(button);

    add(button);
    // add(ModernTheme.createVerticalGap());

    mButtons.get(0).doClick();
  }

  /**
   * Change tab.
   *
   * @param tab the tab
   */
  private final void changeTab(int tab) {
    getTabsModel().changeTab(tab);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  @Override
  public void clicked(ModernClickEvent e) {
    changeTab(buttonMap.get(e.getSource()));
  }

  /**
   * Clear.
   */
  public void clear() {
    mButtons.clear();
    buttonMap.clear();
    group.clear();

    removeAll();
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
      mButtons.get(getTabsModel().getSelectedIndex()).setSelected(true);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawBackground(java.awt.Graphics2D)
   */
  @Override
  public void drawBackground(Graphics2D g2) {
    g2.setColor(LINE_COLOR);

    int x = getWidth() - 1;

    g2.drawLine(x, 0, x, getHeight());
  }
}
