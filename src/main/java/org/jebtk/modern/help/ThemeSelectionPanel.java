/**
 * Copyright 2017 Antony Holmes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jebtk.modern.help;

import org.jebtk.modern.UI;
import org.jebtk.modern.button.ModernButton;
import org.jebtk.modern.dialog.ModernDialogButton;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.panel.HBox;
import org.jebtk.modern.text.ModernAutoSizeLabel;
import org.jebtk.modern.window.ModernWindow;

/**
 * The Class ThemeSelectionPanel.
 */
public class ThemeSelectionPanel extends HBox {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The m restart button. */
  private ModernButton mRestartButton = new ModernDialogButton("Restart...");

  /** The m combo. */
  private ThemeSelectionCombo mCombo = new ThemeSelectionCombo();

  /**
   * Instantiates a new theme selection panel.
   *
   * @param window the window
   */
  public ThemeSelectionPanel(final ModernWindow window) {

    add(new ModernAutoSizeLabel("Theme"));

    add(UI.createHGap(5));

    add(mCombo);

    add(UI.createHGap(10));

    add(mRestartButton);

    mRestartButton.setVisible(false);

    mRestartButton.addClickListener(new ModernClickListener() {

      @Override
      public void clicked(ModernClickEvent e) {
        window.restart();
      }
    });

    mCombo.addClickListener(new ModernClickListener() {

      @Override
      public void clicked(ModernClickEvent e) {
        mRestartButton.setVisible(true);
      }
    });
  }
}
