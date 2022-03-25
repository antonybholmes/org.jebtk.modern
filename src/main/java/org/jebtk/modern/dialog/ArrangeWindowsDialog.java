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
package org.jebtk.modern.dialog;

import javax.swing.Box;

import org.jebtk.modern.UI;
import org.jebtk.modern.button.ModernButtonGroup;
import org.jebtk.modern.button.ModernRadioButton;
import org.jebtk.modern.button.ModernTwoStateWidget;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.window.ModernWindow;
import org.jebtk.modern.window.WindowService;

/**
 * The class ArrangeWindowsDialog.
 */
public class ArrangeWindowsDialog extends ModernDialogTaskWindow {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The tiled radio button.
   */
  private ModernTwoStateWidget mTiledRadioButton = new ModernRadioButton("Tiled");

  /**
   * The horiz radio button.
   */
  private ModernTwoStateWidget mHorizRadioButton = new ModernRadioButton("Horizontal");

  /**
   * The vert radio button.
   */
  private ModernTwoStateWidget mVertRadioButton = new ModernRadioButton("Vertical");

  /**
   * The cascade radio button.
   */
  private ModernTwoStateWidget mCascadeRadioButton = new ModernRadioButton("Cascade");

  /**
   * Instantiates a new arrange windows dialog.
   *
   * @param parent the parent
   */
  public ArrangeWindowsDialog(ModernWindow parent) {
    super(parent);

    setTitle("Arrange Windows");

    setup();

    createUi();

  }

  /**
   * Setup.
   */
  private void setup() {
    ModernClickListener l = new ModernClickListener() {
      @Override
      public void clicked(ModernClickEvent e) {
        arrange();
      }
    };

    mTiledRadioButton.addClickListener(l);
    mHorizRadioButton.addClickListener(l);
    mVertRadioButton.addClickListener(l);

    setSize(360, 200);

    UI.centerWindowToScreen(this);
  }

  /**
   * Creates the ui.
   */
  private final void createUi() {

    Box box = Box.createVerticalBox();

    // mTiledRadioButton.setBorder(ModernPanel.LEFT_BORDER);
    box.add(mTiledRadioButton);
    // box.add(ModernPanel.createVGap());
    // mHorizRadioButton.setBorder(ModernPanel.LEFT_BORDER);
    box.add(mHorizRadioButton);
    // box.add(ModernPanel.createVGap());
    // mVertRadioButton.setBorder(ModernPanel.LEFT_BORDER);
    box.add(mVertRadioButton);
    // box.add(ModernTheme.createVerticalGap());
    // cascadeRadioButton.setBorder(LEFT_BORDER);
    // box.add(cascadeRadioButton);

    setContent(box);

    ModernButtonGroup group = new ModernButtonGroup();

    group.add(mTiledRadioButton);
    group.add(mHorizRadioButton);
    group.add(mVertRadioButton);
    group.add(mCascadeRadioButton);

    mTiledRadioButton.setSelected(true);
  }

  /**
   * Arrange.
   */
  private void arrange() {
    if (mHorizRadioButton.isSelected()) {
      WindowService.getInstance().arrangeHorizontally();
    } else if (mVertRadioButton.isSelected()) {
      WindowService.getInstance().arrangeVertically();
    } else {
      WindowService.getInstance().tile();
    }
  }
}
