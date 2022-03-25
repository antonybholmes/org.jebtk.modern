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
package org.jebtk.modern.input;

import org.jebtk.modern.panel.HBox;
import org.jebtk.modern.text.ModernAutoSizeLabel;
import org.jebtk.modern.text.ModernTextBorderPanel;
import org.jebtk.modern.text.ModernTextField;

/**
 * The Class InputFieldBox.
 */
public class InputFieldBox extends HBox {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;
  /** The m field. */
  private final ModernTextField mField = new ModernTextField(); 

  /**
   * Instantiates a new input field box.
   *
   * @param name the name
   * @param lw   the lw
   * @param fw   the fw
   */
  public InputFieldBox(String name, int lw, int fw) {
    add(new ModernAutoSizeLabel(name, lw));
    add(new ModernTextBorderPanel(mField, fw));
  }

  /**
   * Instantiates a new input field box.
   *
   * @param name  the name
   * @param value the value
   * @param lw    the lw
   * @param fw    the fw
   */
  public InputFieldBox(String name, String value, int lw, int fw) {
    this(name, lw, fw);
    mField.setText(value);
  }

  /**
   * Gets the text.
   *
   * @return the text
   */
  public String getText() {
    return mField.getText();
  }

}
