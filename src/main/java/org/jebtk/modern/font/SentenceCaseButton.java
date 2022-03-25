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
package org.jebtk.modern.font;

import java.awt.Dimension;

import org.jebtk.core.text.SentenceCase;
import org.jebtk.core.text.SentenceCaseType;
import org.jebtk.modern.AssetService;
import org.jebtk.modern.UI;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.menu.ModernIconMenuItem;
import org.jebtk.modern.menu.ModernMenuSeparator;
import org.jebtk.modern.menu.ModernPopupMenu;
import org.jebtk.modern.ribbon.RibbonDropDownIconButton;

/**
 * The class SentenceCaseButton.
 */
public class SentenceCaseButton extends RibbonDropDownIconButton {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The sentence case modern menu item.
   */
  private ModernIconMenuItem sentenceCaseModernMenuItem = new ModernIconMenuItem(SentenceCase.FONT_CASES[0]);

  /**
   * The upper case modern menu item.
   */
  private ModernIconMenuItem upperCaseModernMenuItem = new ModernIconMenuItem(SentenceCase.FONT_CASES[1]);

  /**
   * The lower case modern menu item.
   */
  private ModernIconMenuItem lowerCaseModernMenuItem = new ModernIconMenuItem(SentenceCase.FONT_CASES[2]);

  /**
   * The capitalize modern menu item.
   */
  private ModernIconMenuItem capitalizeModernMenuItem = new ModernIconMenuItem(SentenceCase.FONT_CASES[3]);

  /**
   * The remove case item.
   */
  private ModernIconMenuItem removeCaseItem = new ModernIconMenuItem("Remove");

  /**
   * The sentence case.
   */
  private SentenceCaseType mSentenceCase = SentenceCaseType.NONE;

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
      if (e.getSource().equals(sentenceCaseModernMenuItem)) {
        mSentenceCase = SentenceCaseType.NONE;
      } else if (e.getSource().equals(upperCaseModernMenuItem)) {
        mSentenceCase = SentenceCaseType.UPPERCASE;
      } else if (e.getSource().equals(lowerCaseModernMenuItem)) {
        mSentenceCase = SentenceCaseType.LOWERCASE;
      } else if (e.getSource().equals(lowerCaseModernMenuItem)) {
        mSentenceCase = SentenceCaseType.LOWERCASE;
      } else if (e.getSource().equals(capitalizeModernMenuItem)) {
        mSentenceCase = SentenceCaseType.CAPITALIZE_EACH_WORD;
      } else {
        mSentenceCase = SentenceCaseType.NONE;
      }
    }

  }

  /**
   * Instantiates a new sentence case button.
   */
  public SentenceCaseButton() {
    super(AssetService.getInstance().loadIcon("font_case", AssetService.ICON_SIZE_16));

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    UI.setSize(this, new Dimension(40, WIDGET_HEIGHT));

    ModernPopupMenu popup = new ModernPopupMenu();

    popup.addMenuItem(sentenceCaseModernMenuItem);
    popup.addMenuItem(upperCaseModernMenuItem);
    popup.addMenuItem(lowerCaseModernMenuItem);

    UI.setSize(capitalizeModernMenuItem, new Dimension(200, WIDGET_HEIGHT));

    popup.addMenuItem(capitalizeModernMenuItem);
    popup.add(new ModernMenuSeparator());
    popup.addMenuItem(removeCaseItem);

    popup.addClickListener(new ModernClickEvents());

    setMenu(popup);
  }

  /**
   * Gets the sentence case.
   *
   * @return the sentence case
   */
  public SentenceCaseType getSentenceCase() {
    return mSentenceCase;
  }

  /**
   * Sets the sentence case.
   *
   * @param sentenceCase the new sentence case
   */
  public void setSentenceCase(SentenceCaseType sentenceCase) {
    mSentenceCase = sentenceCase;
  }
}
