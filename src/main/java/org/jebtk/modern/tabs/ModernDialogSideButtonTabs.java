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
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Box;

import org.jebtk.modern.AssetService;
import org.jebtk.modern.BorderService;
import org.jebtk.modern.UI;
import org.jebtk.modern.button.ModernButtonGroup;
import org.jebtk.modern.button.ModernCheckButton;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.theme.ThemeService;

/**
 * Provides a sidebar with clickable tabs.
 * 
 * @author Antony Holmes
 *
 */
public class ModernDialogSideButtonTabs extends TabsController implements ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The buttons.
   */
  private List<ModernCheckButton> buttons = new ArrayList<ModernCheckButton>();

  /**
   * The button map.
   */
  private Map<ModernCheckButton, Integer> buttonMap = new HashMap<ModernCheckButton, Integer>();

  /**
   * The group.
   */
  private ModernButtonGroup group = new ModernButtonGroup();

  /**
   * The box.
   */
  private Box box = Box.createVerticalBox();

  /**
   * The size.
   */
  private static Dimension SIZE = new Dimension(200, Short.MAX_VALUE);

  /**
   * Instantiates a new modern dialog side button tabs.
   *
   * @param model the model
   */
  public ModernDialogSideButtonTabs(TabsModel model) {
    super(model);

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    setMaximumSize(SIZE);
    setMinimumSize(SIZE);
    setPreferredSize(SIZE);

    setBorder(BorderService.getInstance().createBorder(1));

    add(box, BorderLayout.CENTER);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawBackground(java.awt.Graphics2D)
   */
  @Override
  public void drawBackground(Graphics2D g2) {
    fillBackground(g2);

    g2.setColor(ThemeService.getInstance().getColors().getGray(4));

    g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.tabs.TabsController#tabAdded(org.abh.lib.ui.modern.
   * tabs .TabEvent)
   */
  @Override
  public void tabAdded(TabEvent e) {
    refresh();
  }

  /**
   * Adds the tab.
   *
   * @param name the name
   */
  private final void addTab(String name) {
    ModernCheckButton button = new TabButton(name);

    addTab(button);
  }

  /**
   * Adds the tab.
   *
   * @param button the button
   */
  private final void addTab(ModernCheckButton button) {
    System.err.println("adding side tab");

    UI.setSize(button, new Dimension(Short.MAX_VALUE, AssetService.ICON_SIZE_32));
    button.addClickListener(this);

    buttonMap.put(button, buttons.size());
    buttons.add(button);
    group.add(button);

    box.add(button);
    // box.add(ModernTheme.createVerticalGap());
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  public void clicked(ModernClickEvent e) {
    getTabsModel().changeTab(buttonMap.get(e.getSource()));
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
      if (!this.buttons.get(getTabsModel().getSelectedIndex()).isSelected()) {
        this.buttons.get(getTabsModel().getSelectedIndex()).doClick();
      }
    }
  }

  /**
   * Refresh.
   */
  public void refresh() {
    clear();

    for (Tab tab : getTabsModel()) {
      addTab(tab.getName());
    }

    revalidate();
    repaint();
  }

  /**
   * Clear.
   */
  public void clear() {
    box.removeAll();

    buttons.clear();
    buttonMap.clear();
    group.clear();
  }

}
