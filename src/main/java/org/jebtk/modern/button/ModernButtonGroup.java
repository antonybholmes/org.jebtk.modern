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

import java.util.HashSet;
import java.util.Set;

import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.event.ModernStateEvent;
import org.jebtk.modern.event.ModernStateListener;

/**
 * Groups buttons and ensures only one at a time can be selected.
 * 
 * @author Antony Holmes
 *
 */
public class ModernButtonGroup implements ModernClickListener, ModernStateListener {

  /**
   * The buttons.
   */
  private Set<ModernClickWidget> buttons = new HashSet<ModernClickWidget>();

  /**
   * The current.
   */
  private ModernClickWidget current;

  /**
   * Instantiates a new modern button group.
   *
   * @param buttons the buttons
   */
  public ModernButtonGroup(ModernClickWidget... buttons) {
    for (ModernClickWidget button : buttons) {
      add(button);
    }
  }

  /**
   * Adds the.
   *
   * @param button the button
   * @return
   */
  public ModernButtonGroup add(ModernClickWidget button) {
    addButton(button);

    return this;
  }

  /*
   * public void add(ModernRadioButton button) { addButton(button); }
   * 
   * public void add(ModernRadioCheckBox button) { addButton(button); }
   * 
   * public void add(ModernRadioMenuItem button) { addButton(button); }
   * 
   * public void add(RibbonLargeRadioButton button) { addButton(button); }
   */

  /**
   * Adds the button.
   *
   * @param button the button
   */
  private void addButton(ModernClickWidget button) {
    // button.addClickListener(this);

    button.addStateListener(this);

    this.buttons.add(button);
  }

  /**
   * Clear.
   */
  public void clear() {
    buttons.clear();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  @Override
  public void clicked(ModernClickEvent e) {
    update((ModernClickWidget) e.getSource());
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.event.ModernStateListener#stateChanged(org.abh.lib. ui.
   * modern.event.ModernStateEvent)
   */
  @Override
  public void stateChanged(ModernStateEvent e) {
    update((ModernClickWidget) e.getSource());
  }

  /**
   * Update.
   *
   * @param source the source
   */
  private void update(ModernClickWidget source) {
    /*
     * if (current != null && current.equals(source) && !source.isSelected()) {
     * source.setSelected(true); }
     */

    // System.err.println("source 1 " + source + " " + source.isSelected());

    if (!source.isSelected()) {
      return;
    }

    /*
     * if (source.isSelected()) { current = source; }
     */

    if (current != null && source.equals(current)) {
      return;
    }

    // System.err.println("source " + source + " " + source.isSelected());

    current = source;

    for (ModernClickWidget button : buttons) {
      if (button.equals(source)) {
        continue;
      }

      button.setHighlighted(false);
      button.setSelected(false);
    }
  }
}
