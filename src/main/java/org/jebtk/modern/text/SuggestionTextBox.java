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
package org.jebtk.modern.text;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import org.jebtk.core.text.TextUtils;

/**
 * Shows a suggestion of what to type before deleting it as the user selects it.
 * 
 * @author Antony Holmes
 *
 */
public class SuggestionTextBox extends ModernClipboardTextField implements FocusListener {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The text to show as a suggestion. */
  private String mSuggetion;

  /**
   * Instantiates a new suggestion text box.
   *
   * @param suggestion the suggestion
   */
  public SuggestionTextBox(String suggestion) {
    super(suggestion);

    mSuggetion = suggestion;

    setForeground(DISABLED_COLOR);

    addFocusListener(this);
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.text.JTextComponent#getText()
   */
  @Override
  public String getText() {
    if (super.getText().equals(mSuggetion)) {
      return TextUtils.EMPTY_STRING;
    } else {
      return super.getText();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
   */
  @Override
  public void focusGained(FocusEvent e) {
    if (super.getText().equals(mSuggetion)) {
      setForeground(ENABLED_COLOR);

      setText(TextUtils.EMPTY_STRING);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
   */
  @Override
  public void focusLost(FocusEvent e) {
    if (getText().length() == 0) {
      setForeground(DISABLED_COLOR);

      setText(mSuggetion);
    }
  }
}
