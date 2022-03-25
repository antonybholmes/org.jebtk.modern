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

import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jebtk.modern.BorderService;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.button.ModernButton;
import org.jebtk.modern.button.ModernButtonGroup;
import org.jebtk.modern.button.ModernCheckRadioButton;
import org.jebtk.modern.button.ModernClickWidget;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;

/**
 * Provides a sidebar with clickable tabs.
 * 
 * @author Antony Holmes
 *
 */
public class TopTabs extends TabsController implements ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member buttons.
   */
  private List<ModernClickWidget> mButtons = new ArrayList<ModernClickWidget>();

  /**
   * The member button map.
   */
  private Map<ModernClickWidget, Integer> mButtonMap = new HashMap<ModernClickWidget, Integer>();

  /**
   * The group.
   */
  private ModernButtonGroup group = new ModernButtonGroup();

  /** The m width. */
  private int mWidth;

  /** The m total width. */
  private int mTotalWidth;

  /** The m offset. */
  private int mOffset;

  /**
   * The class ComponentEvents.
   */
  private class ComponentEvents extends ComponentAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.ComponentAdapter#componentResized(java.awt.event.
     * ComponentEvent)
     */
    @Override
    public void componentResized(ComponentEvent e) {
      resize();
    }
  }

  /**
   * Instantiates a new side tabs.
   *
   * @param model the model
   */
  public TopTabs(TabsModel model) {
    super(model);

    setLayout(null); // new BoxLayout(this, BoxLayout.PAGE_AXIS));

    addComponentListener(new ComponentEvents());

    setBorder(BorderService.getInstance().createBottomBorder(1));

    UI.setSize(this, Short.MAX_VALUE, ModernButton.getButtonHeight());

    refresh();
  }

  /**
   * Adds the tab.
   *
   * @param name the name
   */
  public final void addTab(String name) {
    ModernClickWidget button = new ModernCheckRadioButton(name); // VerticalTabsModernCheckButton(name,
    // JLabel.LEFT);

    button.addClickListener(this);

    mButtonMap.put(button, mButtons.size());
    mButtons.add(button);
    group.add(button);

    add(button);
    // add(ModernTheme.createVerticalGap());

    // System.err.println("add side tab" + " " + button.getPreferredSize());

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
    changeTab(mButtonMap.get(e.getSource()));
  }

  /**
   * Refresh.
   */
  public void refresh() {
    clear();

    for (Tab tab : getTabsModel()) {
      addTab(tab.getName());
    }

    mWidth = 0;

    for (ModernClickWidget button : mButtons) {
      mWidth = Math.max(mWidth, button.getPreferredSize().width);
    }

    mTotalWidth = mWidth * mButtons.size();

    resize();

    if (getTabsModel().getSelectedIndex() != -1 && mButtons.size() > getTabsModel().getSelectedIndex()) {
      mButtons.get(getTabsModel().getSelectedIndex()).setSelected(true);
    }

    revalidate();
    repaint();
  }

  /**
   * Clear.
   */
  public void clear() {
    mButtons.clear();
    mButtonMap.clear();
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
   * @see
   * org.abh.lib.ui.modern.tabs.TabsController#tabAdded(org.abh.lib.ui.modern.
   * tabs .TabEvent)
   */
  @Override
  public void tabAdded(TabEvent e) {
    refresh();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.tabs.TabsController#tabRemoved(org.abh.lib.ui.modern.
   * tabs.TabEvent)
   */
  @Override
  public void tabRemoved(TabEvent e) {
    refresh();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    g2.setColor(ModernWidget.LINE_COLOR);

    int y = getHeight() - 1;

    g2.drawLine(0, y, getWidth(), y);
  }

  /**
   * Resize.
   */
  private void resize() {
    mOffset = (getWidth() - mTotalWidth) / 2;

    int y = 0;
    int x = mOffset;

    for (ModernClickWidget button : mButtons) {
      button.setBounds(x, y, mWidth, getHeight());

      x += mWidth;
    }
  }
}
