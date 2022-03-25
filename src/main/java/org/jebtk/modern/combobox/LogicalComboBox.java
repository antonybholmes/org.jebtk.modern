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
package org.jebtk.modern.combobox;

import java.awt.Dimension;

import org.jebtk.core.BooleanOperator;
import org.jebtk.modern.AssetService;
import org.jebtk.modern.UI;
import org.jebtk.modern.menu.ModernIconMenuItem;
import org.jebtk.modern.menu.ModernMenuDivider;

/**
 * Preloaded combobox with logical stagements.
 *
 * @author Antony Holmes
 */
public class LogicalComboBox extends ModernComboBox {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant WIDTH.
   */
  protected static final int WIDTH = 120;

  /**
   * Gets the boolean type from index.
   *
   * @param index the index
   * @return the boolean type from index
   */
  public static final BooleanOperator getBooleanTypeFromIndex(int index) {
    switch (index) {
    case 0:
      return BooleanOperator.AND;
    case 1:
      return BooleanOperator.OR;
    case 2:
      return BooleanOperator.NOR;
    case 3:
      return BooleanOperator.XOR;
    case 4:
      return BooleanOperator.NAND;
    default:
      return BooleanOperator.INVALID;
    }
  }

  /**
   * Gets the index from type.
   *
   * @param type the type
   * @return the index from type
   */
  public static final int getIndexFromType(BooleanOperator type) {
    switch (type) {
    case AND:
      return 0;
    case OR:
      return 1;
    case NOR:
      return 2;
    case XOR:
      return 3;
    case NAND:
      return 4;
    default:
      return -1;
    }
  }

  /**
   * Instantiates a new logical combo box.
   */
  public LogicalComboBox() {
    setup();
  }

  /**
   * Instantiates a new logical combo box.
   *
   * @param operator the operator
   */
  public LogicalComboBox(BooleanOperator operator) {
    setup();

    this.setSelectedIndex(getIndexFromType(operator));
  }

  /**
   * Setup.
   */
  protected void setup() {
    ModernIconMenuItem item;

    item = new ModernIconMenuItem("AND", AssetService.getInstance().loadIcon("and", AssetService.ICON_SIZE_48));
    UI.setSize(item, new Dimension(WIDTH, AssetService.ICON_SIZE_48));
    addScrollMenuItem(item);

    item = new ModernIconMenuItem("OR", AssetService.getInstance().loadIcon("or", AssetService.ICON_SIZE_48));
    UI.setSize(item, new Dimension(WIDTH, AssetService.ICON_SIZE_48));
    addScrollMenuItem(item);

    addScrollMenuItem(new ModernMenuDivider());

    item = new ModernIconMenuItem("NOR", AssetService.getInstance().loadIcon("nor", AssetService.ICON_SIZE_48));
    UI.setSize(item, new Dimension(WIDTH, AssetService.ICON_SIZE_48));
    addScrollMenuItem(item);

    item = new ModernIconMenuItem("XOR", AssetService.getInstance().loadIcon("xor", AssetService.ICON_SIZE_48));
    UI.setSize(item, new Dimension(WIDTH, AssetService.ICON_SIZE_48));
    addScrollMenuItem(item);

    item = new ModernIconMenuItem("NAND", AssetService.getInstance().loadIcon("nand", AssetService.ICON_SIZE_48));
    UI.setSize(item, new Dimension(WIDTH, AssetService.ICON_SIZE_48));
    addScrollMenuItem(item);
  }
}
