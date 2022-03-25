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
package org.jebtk.modern.contentpane;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JComponent;

import org.jebtk.modern.AssetService;
import org.jebtk.modern.BorderService;
import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.button.ModernButtonWidget;
import org.jebtk.modern.button.ModernSideButton;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.panel.HBox;
import org.jebtk.modern.tabs.SizableTab;
import org.jebtk.modern.text.ModernColoredHeadingLabel;

/**
 * All content panes.
 * 
 * @author Antony Holmes
 *
 */
public class CollapseHTab extends ModernComponent {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member pane.
   */
  private SizableTab mPane;

  /**
   * The default width.
   */
  private int defaultWidth = 1;

  /**
   * The constant MIN_WIDTH.
   */
  public static final int MIN_WIDTH = 24;

  /**
   * The class ClickEvents.
   */
  private class ClickEvents implements ModernClickListener {

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
     * modern .event.ModernClickEvent)
     */
    @Override
    public void clicked(ModernClickEvent e) {
      if (mPane.getWidth() > MIN_WIDTH) {
        defaultWidth = mPane.getWidth();
        mPane.setWidth(MIN_WIDTH);
      } else {
        mPane.setWidth(defaultWidth);
      }
    }

  }

  /**
   * Instantiates a new collapse h tab.
   *
   * @param pane the pane
   * @param c    the c
   */
  public CollapseHTab(SizableTab pane, JComponent c) {
    mPane = pane;

    Box box = HBox.create();

    box.add(new ModernColoredHeadingLabel(pane.getName()));

    box.add(Box.createHorizontalGlue());

    ModernButtonWidget button = new ModernSideButton(AssetService.getInstance().loadIcon("left_arrow", 16));
    button.addClickListener(new ClickEvents());

    box.add(button);

    box.setBorder(BorderService.getInstance().createBottomBorder(10));

    add(box, BorderLayout.PAGE_START);

    add(c, BorderLayout.CENTER);

    setBorder(BORDER);
  }
}
