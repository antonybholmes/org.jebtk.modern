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
package org.jebtk.modern.button;

import java.awt.Dimension;
import java.awt.Graphics2D;

import org.jebtk.core.text.TextUtils;
import org.jebtk.modern.AssetService;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.graphics.icons.TriangleDownVectorIcon;
import org.jebtk.modern.menu.ModernPopupMenu;

// TODO: Auto-generated Javadoc
/**
 * Drop down without highlighting; just shows the selection text and the drop
 * down arrow.
 * 
 * @author Antony Holmes
 *
 */
public class ModernDropDownMenuLabelButton extends ModernDropDownWidget {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant SIZE.
   */
  private static final Dimension SIZE = new Dimension(150, ModernWidget.WIDGET_HEIGHT);

  /**
   * The member prefix.
   */
  private String mPrefix = TextUtils.EMPTY_STRING;

  /** The m trunc text. */
  private String mTruncText;

  /**
   * The class ModernClickEvents.
   */
  private class ModernClickEvents implements ModernClickListener {

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
     * modern .event.ModernClickEvent)
     */
    @Override
    public void clicked(ModernClickEvent e) {
      setText(mPrefix + e.getMessage());
    }

  }

  /**
   * Instantiates a new modern drop down menu label button.
   *
   * @param text1 the text1
   */
  public ModernDropDownMenuLabelButton(String text1) {
    super(text1);

    setup();
  }

  /**
   * Instantiates a new modern drop down menu label button.
   *
   * @param text1 the text1
   * @param menu  the menu
   */
  public ModernDropDownMenuLabelButton(String text1, ModernPopupMenu menu) {
    super(text1, menu);

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    // mPrefix = mText1;

    addClickListener(new ModernClickEvents());

    UI.setSize(this, SIZE);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.button.ModernButtonWidget#setText(java.lang.String)
   */
  @Override
  public void setText(String text) {
    super.setText(text);

    mTruncText = TextUtils.truncate(mText1, 24);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.button.ModernButtonWidget#drawBackground(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawBackground(Graphics2D g2) {
    // Do nothing
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    int x = PADDING;

    g2.drawString(mTruncText, x, getTextYPosCenter(g2, getHeight()));

    x = getWidth() - PADDING - 16;

    // Draw arrow at end of component
    AssetService.getInstance().loadIcon(TriangleDownVectorIcon.class, 16).drawIcon(g2, x, (getHeight() - 16) / 2, 16);
  }
}
