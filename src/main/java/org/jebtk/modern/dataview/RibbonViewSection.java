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
package org.jebtk.modern.dataview;

import org.jebtk.modern.AssetService;
import org.jebtk.modern.button.ModernButtonGroup;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.ribbon.Ribbon;
import org.jebtk.modern.ribbon.RibbonLargeRadioButton;
import org.jebtk.modern.ribbon.RibbonSection;
import org.jebtk.modern.view.ViewModel;

/**
 * Standardized ribbon menu section for providing basic cut, copy and paste
 * functionality to the currently highlighted control that supports clipboard
 * operations.
 *
 * @author Antony Holmes
 *
 */
public class RibbonViewSection extends RibbonSection implements ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The list button.
   */
  private RibbonLargeRadioButton mListButton = new RibbonLargeRadioButton("List",
      AssetService.getInstance().loadIcon("view_list", AssetService.ICON_SIZE_32));

  /**
   * The tiles button.
   */
  private RibbonLargeRadioButton mTilesButton = new RibbonLargeRadioButton("Tiles",
      AssetService.getInstance().loadIcon("view_tiles", AssetService.ICON_SIZE_32));

  /**
   * The details button.
   */
  private RibbonLargeRadioButton mDetailsButton = new RibbonLargeRadioButton("Details",
      AssetService.getInstance().loadIcon("view_details", AssetService.ICON_SIZE_32));

  /**
   * The member model.
   */
  private ViewModel mModel;

  /**
   * Instantiates a new ribbon view section.
   *
   * @param ribbon the ribbon
   * @param model  the model
   */
  public RibbonViewSection(Ribbon ribbon, ViewModel model) {
    super(ribbon, "View");

    mModel = model;

    mListButton.setToolTip("List", "Display items in a list.");
    mListButton.addClickListener(this);

    mTilesButton.setToolTip("Tiles", "Display items as tiles.");
    mTilesButton.addClickListener(this);

    mDetailsButton.setToolTip("Details", "Display items in a detailed list.");
    mDetailsButton.addClickListener(this);

    add(mDetailsButton);
    add(mListButton);
    add(mTilesButton);

    ModernButtonGroup group = new ModernButtonGroup();

    group.add(mListButton);
    group.add(mDetailsButton);
    group.add(mTilesButton);

    mDetailsButton.doClick();
  }

  /**
   * Enabled or disable all the controls on the panel.
   *
   * @param enabled the new enabled
   */
  public final void setEnabled(boolean enabled) {
    mDetailsButton.setEnabled(enabled);
    mTilesButton.setEnabled(enabled);
    mListButton.setEnabled(enabled);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  public void clicked(ModernClickEvent e) {
    if (mModel == null) {
      return;
    }

    if (e.getSource().equals(mDetailsButton)) {
      mModel.setView("Details");
    } else if (e.getSource().equals(mTilesButton)) {
      mModel.setView("Tiles");
    } else if (e.getSource().equals(mListButton)) {
      mModel.setView("List");
    } else {
      // do nothing
    }
  }
}
