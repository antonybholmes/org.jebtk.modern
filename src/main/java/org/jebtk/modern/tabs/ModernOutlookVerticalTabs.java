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
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.border.Border;

import org.jebtk.modern.AssetService;
import org.jebtk.modern.button.ModernButtonGroup;
import org.jebtk.modern.button.ModernCheckButton;
import org.jebtk.modern.button.ModernClickWidget;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.graphics.icons.ModernIcon;

/**
 * Create tabs similar to Outlook 2007.
 * 
 * @author Antony Holmes
 *
 */
public class ModernOutlookVerticalTabs extends TabsController implements ModernClickListener, ComponentListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  // private Map<ModernCheckButton, Integer> buttonMap = new
  // HashMap<ModernCheckButton, Integer>();
  // private List<Component> components = new CopyOnWriteArrayList<Component>();
  // List<Boolean> atBottom = new CopyOnWriteArrayList<Boolean>();

  /**
   * The constant VERTICAL_GAP.
   */
  private static final int VERTICAL_GAP = 0;

  /**
   * The constant TAB_HEIGHT.
   */
  private static final int TAB_HEIGHT = 32;

  /**
   * The constant GAP.
   */
  private static final int GAP = AssetService.ICON_SIZE_24;

  /**
   * The constant HALF_GAP.
   */
  private static final int HALF_GAP = GAP / 2;

  /**
   * The constant TOTAL_TAB_HEIGHT.
   */
  private static final int TOTAL_TAB_HEIGHT = VERTICAL_GAP + TAB_HEIGHT;

  /**
   * The group.
   */
  private ModernButtonGroup group = new ModernButtonGroup();

  /**
   * The buttons.
   */
  private List<ModernClickWidget> buttons = new ArrayList<ModernClickWidget>();

  /**
   * The button map.
   */
  private Map<ModernClickWidget, Integer> buttonMap = new HashMap<ModernClickWidget, Integer>();

  /**
   * The view panel.
   */
  private TabsViewPanel viewPanel = null;

  /**
   * Instantiates a new modern outlook vertical tabs.
   *
   * @param model the model
   */
  public ModernOutlookVerticalTabs(TabsModel model) {
    super(model);

    setLayout(null);

    addComponentListener(this);

    viewPanel = new TabsViewPanel(model);

    add(viewPanel);

    refresh();

    changeTab(0);
  }

  /**
   * Adds the tab.
   *
   * @param name the name
   */
  private final void addTab(String name) {
    addTab(name, AssetService.getInstance().loadIcon("blank", AssetService.ICON_SIZE_16));
  }

  /**
   * Adds the tab.
   *
   * @param name the name
   * @param icon the icon
   */
  private final void addTab(String name, ModernIcon icon) {
    ModernClickWidget button = new TabButton(name, icon); // VerticalTabsModernCheckButton(name,
    // JLabel.LEFT);

    addTab(button);
  }

  /**
   * Adds the tab.
   *
   * @param button the button
   */
  private final void addTab(ModernClickWidget button) {
    button.setBounds(0, 0, 0, TAB_HEIGHT);
    button.addClickListener(this);

    buttonMap.put(button, buttons.size());
    buttons.add(button);
    group.add(button);

    add(button);

  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.ModernComponent#setBorder(javax.swing.border.Border)
   */
  public void setBorder(Border border) {
    super.setBorder(border);

    resize();
  }

  /**
   * Resize.
   */
  private final void resize() {
    if (getInternalRect() == null) {
      return;
    }

    if (viewPanel == null) {
      return;
    }

    Rectangle bounds;

    // resize all buttons

    int buttonHeight = TOTAL_TAB_HEIGHT * buttons.size();

    for (int i = 0; i < buttons.size(); ++i) {
      bounds = buttons.get(i).getBounds();

      bounds.y = getInsets().top + mInternalRect.getH() - buttonHeight + TOTAL_TAB_HEIGHT * i;
      bounds.width = mInternalRect.getW();
      bounds.x = getInsets().left;

      buttons.get(i).setBounds(bounds);
    }

    // move the component
    bounds = viewPanel.getBounds();

    bounds.x = getInsets().left;
    bounds.y = getInsets().top;
    bounds.width = mInternalRect.getW();
    bounds.height = mInternalRect.getH() - buttonHeight - GAP;

    viewPanel.setBounds(bounds);

    revalidate();

    repaint();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    Rectangle r = new Rectangle(mInternalRect.getX(),
        getInsets().top + mInternalRect.getH() - TOTAL_TAB_HEIGHT * buttons.size() - HALF_GAP, mInternalRect.getW() - 1,
        1);

    g2.setColor(LINE_COLOR);

    g2.drawLine(r.x, r.y, r.x + r.width, r.y);

  }

  /**
   * Change tab.
   *
   * @param button the button
   */
  private final void changeTab(ModernCheckButton button) {
    changeTab(buttonMap.get(button));
  }

  /**
   * Change tab.
   *
   * @param index the index
   */
  private final void changeTab(int index) {
    getTabsModel().changeTab(index);

    resize();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  public void clicked(ModernClickEvent e) {
    // System.out.println("what " + e.getMessage());

    changeTab((ModernCheckButton) e.getSource());
  }

  /**
   * Refresh.
   */
  public void refresh() {
    clear();

    add(viewPanel);

    for (Tab tab : getTabsModel()) {
      addTab(tab.getName());
    }

    getTabsModel().changeTab(0);
  }

  /**
   * Clear.
   */
  public void clear() {
    buttons.clear();

    removeAll();

    resize();
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
        buttons.get(getTabsModel().getSelectedIndex()).doClick();
      }
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.ComponentListener#componentResized(java.awt.event.
   * ComponentEvent)
   */
  @Override
  public void componentResized(ComponentEvent e) {
    resize();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.ComponentListener#componentHidden(java.awt.event.
   * ComponentEvent)
   */
  @Override
  public void componentHidden(ComponentEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.ComponentListener#componentMoved(java.awt.event.
   * ComponentEvent)
   */
  @Override
  public void componentMoved(ComponentEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.ComponentListener#componentShown(java.awt.event.
   * ComponentEvent)
   */
  @Override
  public void componentShown(ComponentEvent e) {
    // TODO Auto-generated method stub

  }
}
