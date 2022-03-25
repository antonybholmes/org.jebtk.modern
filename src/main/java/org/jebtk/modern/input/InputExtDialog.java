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
package org.jebtk.modern.input;

import java.util.ArrayList;
import java.util.List;

import org.jebtk.core.text.Splitter;
import org.jebtk.modern.UI;
import org.jebtk.modern.dialog.ModernDialogTaskWindow;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.scrollpane.ModernScrollPane;
import org.jebtk.modern.scrollpane.ScrollBarPolicy;
import org.jebtk.modern.text.ModernTextArea;
import org.jebtk.modern.window.ModernWindow;
import org.jebtk.modern.window.WindowWidgetFocusEvents;

/**
 * The class MatchDialog.
 */
public class InputExtDialog extends ModernDialogTaskWindow implements ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The m text. */
  private ModernTextArea mText = new ModernTextArea();

  /** The m delimiter. */
  private String mDelimiter;

  /**
   * Instantiates a new match dialog.
   *
   * @param parent    the parent
   * @param text      the text
   * @param delimiter the delimiter
   */
  public InputExtDialog(ModernWindow parent, String text, String delimiter) {
    super(parent);

    mDelimiter = delimiter;

    List<String> lines = Splitter.on(delimiter).trim().text(text);

    setTitle("Input");

    setup();

    createUi(lines);
  }

  /**
   * Setup.
   */
  private void setup() {
    addWindowListener(new WindowWidgetFocusEvents(mOkButton));

    setSize(600, 400);

    setResizable(true);

    UI.centerWindowToScreen(this);
  }

  /**
   * Creates the ui.
   *
   * @param lines the lines
   */
  private final void createUi(List<String> lines) {
    mText.setText(lines);

    ModernScrollPane scrollPane = new ModernScrollPane(mText);
    scrollPane.setVerticalScrollBarPolicy(ScrollBarPolicy.ALWAYS);

    setCard(scrollPane);
  }

  /**
   * Gets the window name.
   *
   * @return the window name
   */
  public List<String> getLines() {
    List<String> lines = mText.getLines();

    // Also split on lines containing delimiter
    ArrayList<String> ret = new ArrayList<String>(lines.size());

    for (String line : lines) {
      List<String> lines2 = Splitter.on(mDelimiter).trim().text(line);

      for (String line2 : lines2) {
        ret.add(line2);
      }
    }

    return ret;
  }
}
