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

import org.jebtk.modern.AssetService;
import org.jebtk.modern.UI;
import org.jebtk.modern.graphics.icons.CheckVectorIcon;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.graphics.icons.UnCheckedVectorIcon;

/**
 * The class ModernCheckBox.
 */
public class ModernCheckBox extends CheckBox { // ModernTickBox

  private static final long serialVersionUID = 1L;

  public static int ICON_SIZE = 18;

  public static final ModernIcon CHECK_ICON = AssetService.getInstance().loadIcon(CheckVectorIcon.class, ICON_SIZE);

  /** The Constant CHECK_ICON. */
  public static final ModernIcon UNCHECKED_ICON = AssetService.getInstance().loadIcon(UnCheckedVectorIcon.class,
      ICON_SIZE);

  /** The Constant CHECKED_ICON. */
  // public static final ModernIcon CHECKED_ICON = UIService.getInstance()
  // .loadIcon(CheckedVectorIcon.class, ICON_SIZE);

  /**
   * Instantiates a new modern check box.
   */
  public ModernCheckBox() {
    super();

    UI.setSize(this, ModernButton.ICON_ONLY_SIZE);

    setup();
  }

  /**
   * Instantiates a new modern check box.
   *
   * @param text the text
   */
  public ModernCheckBox(String text) {
    super(text);

    setup();
  }

  /**
   * Instantiates a new modern check box.
   *
   * @param selected the selected
   */
  public ModernCheckBox(boolean selected) {
    super(selected);

    setup();
  }

  /**
   * Instantiates a new modern check box.
   *
   * @param text     the text
   * @param selected the selected
   */
  public ModernCheckBox(String text, boolean selected) {
    super(text, selected);

    setup();
  }

  /**
   * Instantiates a new modern check box.
   *
   * @param text  the text
   * @param width the width
   */
  public ModernCheckBox(String text, int width) {
    super(text, width);

    setup();
  }

  /**
   * Instantiates a new modern check box.
   *
   * @param text the text
   * @param size the size
   */
  public ModernCheckBox(String text, Dimension size) {
    super(text, size);

    setup();
  }

  /**
   * Instantiates a new modern check box.
   *
   * @param text     the text
   * @param selected the selected
   * @param size     the size
   */
  public ModernCheckBox(String text, boolean selected, Dimension size) {
    super(text, selected, size);

    setup();
  }

  private void setup() {
    addStyleClass("checkbox");
    setAnimations("checkbox");
  }

  @Override
  public void drawForegroundAA(Graphics2D g2) {
    if (mText1 != null) {
      int x = mInternalRect.getX() + ICON_SIZE + PADDING;

      g2.setColor(isEnabled() ? TEXT_COLOR : ALT_TEXT_COLOR);
      g2.drawString(mText1, x, getTextYPosCenter(g2, getHeight()));
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.widget.ModernClickWidget#drawBackgroundAA(java.awt.
   * Graphics2D)
   */
  /*
   * @Override public void drawBackgroundAA(Graphics2D g2) { int x =
   * mInternalRect.getX(); int y = (getHeight() - 16) / 2;
   * 
   * if (isSelected()) { if (isEnabled()) { CHECKED_ICON.drawIcon(g2, x, y, 20); }
   * else { DISABLED_CHECKED_ICON.drawIcon(g2, x, y, 20); } } else {
   * CHECK_ICON.drawIcon(g2, x, y, 20); } }
   */
}
