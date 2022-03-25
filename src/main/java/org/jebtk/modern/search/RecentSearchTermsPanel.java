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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JScrollPane;

import org.jebtk.modern.AssetService;
import org.jebtk.modern.button.ModernButton;
import org.jebtk.modern.button.ModernButtonWidget;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.panel.ModernPanel;
import org.jebtk.modern.ribbon.RibbonMenuPanel;
import org.jebtk.modern.ribbon.RibbonPanelTitle;

// TODO: Auto-generated Javadoc
/**
 * The class RecentSearchTermsPanel.
 */
public class RecentSearchTermsPanel extends RibbonMenuPanel implements ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The content.
   */
  private Box content = Box.createVerticalBox();

  /**
   * The icon.
   */
  private ModernIcon icon;

  /**
   * The map.
   */
  private Map<ModernButton, String> map = new HashMap<ModernButton, String>();

  /**
   * The selected search.
   */
  private String selectedSearch = null;

  /**
   * The constant ITEM_HEIGHT.
   */
  private static final int ITEM_HEIGHT = 40;

  /**
   * The display count.
   */
  private int displayCount = 10;

  /**
   * Instantiates a new recent search terms panel.
   */
  public RecentSearchTermsPanel() {
    super("Recent searches");

    setup("Recent searches", AssetService.getInstance().loadIcon("recent_search", AssetService.ICON_SIZE_32));
  }

  /**
   * Setup.
   *
   * @param title the title
   * @param icon  the icon
   */
  private void setup(String title, ModernIcon icon) {

    this.icon = icon;

    setLayout(new BorderLayout());
    setBackground(Color.WHITE);

    add(new RibbonPanelTitle(title), BorderLayout.PAGE_START);

    /*
     * model = new ListTableModel<java.io.File>(recentFiles.getRecentFiles());
     * table.setModel(model);
     * 
     * table.setRowHeight(40);
     * 
     * table.setDefaultRenderer(Object.class, { new
     * RecentFilesTableCellRenderer(icon));
     * 
     * table.setBackground(Color.WHITE); //table.setAutoCreateRowSorter(true);
     * //table.addMouseListener(this); //table.setMinimumSize(new Dimension(250,
     * 250)); table.setTableHeader(null);
     */

    // table.getRowSorter().toggleSortOrder(0);

    JScrollPane scrollPane = new JScrollPane(content);
    scrollPane.setBorder(BORDER);
    scrollPane.setViewportBorder(BorderFactory.createEmptyBorder());
    scrollPane.getViewport().setBackground(Color.WHITE);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane.setBackground(Color.WHITE);

    setBorder(BORDER);

    add(scrollPane, BorderLayout.CENTER);

    ModernPanel buttonPanel = new ModernPanel(new FlowLayout(FlowLayout.RIGHT));

    ModernButtonWidget button = new ModernButton("Clear",
        AssetService.getInstance().loadIcon("clear", AssetService.ICON_SIZE_16));

    button.addClickListener(this);

    buttonPanel.add(button);

    add(buttonPanel, BorderLayout.PAGE_END);
  }

  /**
   * Reload.
   */
  public final void reload() {

    content.removeAll();
    map.clear();

    int c = 0;

    for (String search : SearchTermsService.getInstance()) {
      if (c == displayCount) {
        break;
      }

      ModernButton item = new RecentSearchTermButton(search, icon);

      item.setPreferredSize(new Dimension(0, ITEM_HEIGHT));
      item.setMaximumSize(new Dimension(Short.MAX_VALUE, ITEM_HEIGHT));
      item.addClickListener(this);

      map.put(item, search);

      content.add(item);

      ++c;
    }

    content.add(Box.createVerticalGlue());
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  public final void clicked(ModernClickEvent e) {
    if (e.getMessage().equals("Clear")) {

      clear();

      return;
    }

    selectedSearch = map.get(e.getSource());

    fireClicked(new ModernClickEvent(this, e.getMessage()));
  }

  /**
   * Clear.
   */
  private void clear() {

    // clear the list of items

    SearchTermsService.getInstance().clear();
    content.removeAll();
    content.revalidate();
    content.repaint();
  }

  /**
   * Gets the selected search.
   *
   * @return the selected search
   */
  public final String getSelectedSearch() {
    return selectedSearch;
  }
}
