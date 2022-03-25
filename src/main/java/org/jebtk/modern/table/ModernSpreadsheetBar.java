/**
 * Copyright (c) 2016, Antony Holmes
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
package org.jebtk.modern.table;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jebtk.core.Mathematics;
import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.text.TextUtils;
import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.UI;
import org.jebtk.modern.dataview.ModernDataCell;
import org.jebtk.modern.event.ModernSelectionListener;
import org.jebtk.modern.splitpane.ModernHSplitPaneEllipsis;
import org.jebtk.modern.text.ModernClipboardTextBox;
import org.jebtk.modern.text.ModernTextBorderPanel;
import org.jebtk.modern.text.ModernTextBox;

/**
 * The Class ModernSpreadsheetBar allows users to edit table cells.
 */
public class ModernSpreadsheetBar extends ModernComponent implements ModernSelectionListener {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The char index map. */
  private static Map<Character, Integer> CHAR_INDEX_MAP = new HashMap<Character, Integer>();

  /** The Constant POWER26. */
  private static final int[] POWER26 = new int[10];

  static {
    for (int i = 0; i < TextUtils.CAPITAL_ALPHABET_CHARS.length; ++i) {
      CHAR_INDEX_MAP.put(TextUtils.CAPITAL_ALPHABET_CHARS[i], i + 1);
    }

    for (int i = 0; i < 10; ++i) {
      POWER26[i] = Mathematics.pow(26, i);
    }
  }

  /** The Constant REF_PATTERN. */
  private static final Pattern REF_PATTERN = Pattern.compile("^([A-Za-z]+)([0-9]+)$");

  /** The m table. */
  private ModernTable mTable;

  /** The m loc text. */
  private ModernTextBox mLocText = new ModernTextBox();

  /** The m text. */
  private ModernTextBox mText = new ModernClipboardTextBox();

  /**
   * Instantiates a new modern spreadsheet bar.
   *
   * @param table the table
   */
  public ModernSpreadsheetBar(ModernTable table) {
    mTable = table;

    // Box box = HBox.create();

    ModernHSplitPaneEllipsis splitPane = new ModernHSplitPaneEllipsis();

    splitPane.addComponent(new ModernTextBorderPanel(mLocText, 100), 0.1);
    splitPane.addComponent(new ModernTextBorderPanel(mText), 0.9);
    splitPane.setDividerWidth(10);

    // box.add(new ModernTextBorderPanel(mLocText, 100));
    // box.add(UI.createHGap(10));
    // box.add(new ModernTextBorderPanel(mText));

    add(splitPane);

    setBorder(UI.createTopBottomBorder(10));

    table.getCellSelectionModel().addSelectionListener(this);

    /*
     * mLocText.addChangeListener(new ChangeListener() {
     * 
     * @Override public void changed(ChangeEvent e) { changeSelection(); }});
     */

    mLocText.addKeyListener(new KeyListener() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          changeSelection();
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

      }

      @Override
      public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

      }
    });

    mText.addKeyListener(new KeyListener() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          update();
        }
      }

      @Override
      public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

      }

      @Override
      public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

      }
    });
  }

  /**
   * Update.
   */
  private void update() {
    ModernDataCell cell = mTable.getCellSelectionModel().last();

    if (cell != null) {
      mTable.setValueAt(cell, mText.getText());
    }
  }

  /**
   * Change selection.
   */
  private void changeSelection() {
    Matcher m = REF_PATTERN.matcher(mLocText.getText());

    if (m.find()) {
      char[] letters = m.group(1).toUpperCase().toCharArray();

      System.err.println(letters);

      // USE base 26 conversion

      int power = letters.length - 1;

      int c = 0;

      for (char letter : letters) {
        int v = CHAR_INDEX_MAP.get(letter);

        c += v * POWER26[power];

        System.err.println("v " + v + " " + POWER26[power] + " " + power);

        --power;
      }

      int r = Integer.parseInt(m.group(2));

      System.err.println("c " + c + " " + r);

      if (c > 0 && r > 0) {
        mTable.getCellSelectionModel().setSelection(r - 1, c - 1);
      }
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.event.ModernSelectionListener#selectionChanged(org.abh.
   * common.event.ChangeEvent)
   */
  @Override
  public void selectionAdded(ChangeEvent e) {
    selectionRemoved(e);
  }

  @Override
  public void selectionRemoved(ChangeEvent e) {
    ModernDataCell cell = mTable.getCellSelectionModel().last();

    if (cell != null) {

      Object v = mTable.getValueAt(cell);

      if (v != null) {
        // Only update if the cell actually points to a valid cell
        // in the table
        mText.setText(v.toString());

        mLocText.setText(mTable.getModel().getColumnName(cell.col) + mTable.getRowName(cell.row));
      }
    }
  }
}
