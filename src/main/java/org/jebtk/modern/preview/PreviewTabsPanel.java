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
package org.jebtk.modern.preview;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import org.jebtk.modern.panel.ModernPanel;
import org.jebtk.modern.tabs.ModernHTabBar;
import org.jebtk.modern.tabs.ModernHTabBarBottom;
import org.jebtk.modern.tabs.TabsModel;
import org.jebtk.modern.tabs.TabsViewPanel;

// TODO: Auto-generated Javadoc
/**
 * The class PreviewTabsPanel.
 */
public class PreviewTabsPanel extends PreviewPanel {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The model.
   */
  private TabsModel model = new TabsModel();

  /**
   * The tabs view.
   */
  private TabsViewPanel tabsView = new TabsViewPanel(model);

  /**
   * The preview tabs panel.
   */
  private ModernHTabBar previewTabsPanel = new ModernHTabBarBottom(model);

  /**
   * The previews.
   */
  private List<ModernPanel> previews = new ArrayList<ModernPanel>();

  /**
   * Instantiates a new preview tabs panel.
   */
  public PreviewTabsPanel() {
    add(tabsView, BorderLayout.CENTER);
    add(previewTabsPanel, BorderLayout.PAGE_END);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.preview.PreviewPanel#addPreview(java.lang.String,
   * org.abh.lib.ui.modern.panel.ModernPanel)
   */
  @Override
  public void addPreview(String name, ModernPanel previewTablePanel) {
    System.err.println("adding preview");

    model.addTab(name, previewTablePanel, false);

    previews.add(previewTablePanel);

    model.changeTab(model.getTabCount() - 1);
  }

  /**
   * Return the currently selected preview, or null if nothing is selected.
   *
   * @return the selected preview
   */
  @Override
  public ModernPanel getSelectedPreview() {
    if (model.getSelectedIndex() == -1) {
      return null;
    }

    return previews.get(model.getSelectedIndex());
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.preview.PreviewPanel#clear()
   */
  public void clear() {
    previews.clear();
    model.removeAllTabs();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.preview.PreviewPanel#getPreview(int)
   */
  @Override
  public ModernPanel getPreview(int index) {
    return previews.get(index);
  }
}
