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
package org.jebtk.modern.search;

import java.util.List;

import javax.swing.Box;

import org.jebtk.core.text.Splitter;
import org.jebtk.modern.BorderService;
import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.button.CheckBox;
import org.jebtk.modern.button.ModernCheckSwitch;
import org.jebtk.modern.dialog.ModernDialogTaskWindow;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.panel.ModernBorderPanel;
import org.jebtk.modern.panel.VBox;
import org.jebtk.modern.scrollpane.ModernScrollPane;
import org.jebtk.modern.text.ModernSubHeadingLabel;
import org.jebtk.modern.text.ModernTextArea;
import org.jebtk.modern.window.ModernWindow;
import org.jebtk.modern.window.WindowWidgetFocusEvents;

/**
 * The class MatchDialog.
 */
public class SearchDialog extends ModernDialogTaskWindow implements ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The m check in list. */
  private CheckBox mCheckInList = new ModernCheckSwitch("Match in list", true);

  /** The m check exact. */
  private CheckBox mCheckExact = new ModernCheckSwitch("Match entire contents");

  /** The m check case. */
  private CheckBox mCheckCase = new ModernCheckSwitch("Case sensitive");

  /** The m text. */
  private ModernTextArea mText = new ModernTextArea();

  /**
   * Instantiates a new match dialog.
   *
   * @param parent    the parent
   * @param text      the text
   * @param delimiter the delimiter
   */
  public SearchDialog(ModernWindow parent, String text, String delimiter) {
    super(parent);

    List<String> lines = Splitter.on(delimiter).trim().text(text);

    setTitle("Search");

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
    // this.getWindowContentPanel().add(new JLabel("Change " +
    // getProductDetails().getProductName() + " settings", JLabel.LEFT),
    // BorderLayout.PAGE_START);

    ModernComponent content = new ModernComponent();

    content.setHeader(new ModernSubHeadingLabel("Search for:", BorderService.getInstance().createBottomBorder(5)));

    mText.setText(lines);

    ModernScrollPane scrollPane = new ModernScrollPane(mText);
    // scrollPane.setVerticalScrollBarPolicy(ScrollBarPolicy.ALWAYS);

    content.setBody(new ModernBorderPanel(new ModernComponent(scrollPane, ModernWidget.BORDER)));

    Box box = VBox.create();

    box.add(UI.createVGap(10));
    box.add(mCheckInList);
    // box.add(UI.createVGap(5));
    box.add(mCheckExact);
    // box.add(UI.createVGap(5));
    box.add(mCheckCase);

    content.setFooter(box);

    setCard(content);
  }

  /**
   * Gets the window name.
   *
   * @return the window name
   */
  public List<String> getLines() {
    return mText.getLines();
  }

  /**
   * Case sensitive.
   *
   * @return true, if successful
   */
  public boolean caseSensitive() {
    return mCheckCase.isSelected();
  }

  /**
   * Gets the in list.
   *
   * @return the in list
   */
  public boolean getInList() {
    return mCheckInList.isSelected();
  }

  /**
   * Gets the exact.
   *
   * @return the exact
   */
  public boolean getExact() {
    return mCheckExact.isSelected();
  }
}
