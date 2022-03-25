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

import java.awt.Dimension;

import org.jebtk.modern.AssetService;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.ribbon.RibbonButton;

// TODO: Auto-generated Javadoc
/**
 * Lets a user pick a sort direction.
 * 
 * @author Antony Holmes
 *
 */
public class SortDirectionButton extends RibbonButton implements ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant ASCENDING_ICON.
   */
  private static final ModernIcon ASCENDING_ICON = AssetService.getInstance().loadIcon("sort_ascending",
      AssetService.ICON_SIZE_16);

  /**
   * The constant DESCENDING_ICON.
   */
  private static final ModernIcon DESCENDING_ICON = AssetService.getInstance().loadIcon("sort_descending",
      AssetService.ICON_SIZE_16);

  /**
   * The ascending.
   */
  private boolean ascending;

  /**
   * Instantiates a new sort direction button.
   *
   * @param ascending the ascending
   */
  public SortDirectionButton(boolean ascending) {
    super("A to Z", DESCENDING_ICON);

    addClickListener(this);

    setAscending(ascending);

    UI.setSize(this, new Dimension(80, ModernWidget.WIDGET_HEIGHT));
  }

  /**
   * Sets the ascending.
   *
   * @param ascending the new ascending
   */
  public void setAscending(boolean ascending) {
    this.ascending = ascending;

    if (ascending) {
      setText("A to Z");
      setIcon(DESCENDING_ICON);
    } else {
      setText("Z to A");
      setIcon(ASCENDING_ICON);
    }

    repaint();
  }

  /*
   * public final void paintComponent(Graphics g) { Graphics2D g2 = (Graphics2D)g;
   * 
   * if (isSelected()) { ModernTheme.paintHighlightedBorder(g2, rect); } else if
   * (pressed) { ModernTheme.paintHighlightedBorder(g2, rect); } else if
   * (highlight) { ModernTheme.paintHighlightedBorder(g2, rect);
   * //paintHighlighted(g2, rect); } else { // do nothing }
   * 
   * if (this.getIcon() != null) { this.getIcon().paintIcon(this, g2, iconX,
   * iconY); }
   * 
   * ThemeManager.getInstance().getTheme().paintTriangle(g2, rect); }
   */

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  public void clicked(ModernClickEvent e) {
    // setAscending(!ascending);
  }

  /**
   * Gets the ascending.
   *
   * @return the ascending
   */
  public boolean getcending() {
    return ascending;
  }
}
