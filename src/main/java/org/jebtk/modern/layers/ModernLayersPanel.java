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
package org.jebtk.modern.layers;

import java.awt.BorderLayout;

import javax.swing.Box;

import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.collapsepane.ModernCollapsePane;
import org.jebtk.modern.scrollpane.ModernScrollPane;
import org.jebtk.modern.scrollpane.ScrollBarPolicy;

/**
 * The class ModernLayersPanel.
 */
public class ModernLayersPanel extends ModernWidget {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The collapse pane.
   */
  private ModernCollapsePane collapsePane = new ModernCollapsePane();

  /**
   * Instantiates a new modern layers panel.
   */
  public ModernLayersPanel() {
    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    ModernScrollPane scrollPane = new ModernScrollPane(collapsePane);
    scrollPane.setHorizontalScrollBarPolicy(ScrollBarPolicy.NEVER);
    // scrollPane.setOpaque(false);
    // scrollPane.getClip().setOpaque(false);
    // collapsePane.setOpaque(false);
    collapsePane.getNodeRenderer().setOpaque(false);
    // setOpaque(false);

    add(scrollPane, BorderLayout.CENTER);
  }

  /**
   * Adds the group.
   *
   * @param group     the group
   * @param container the container
   */
  public void addGroup(String group, Box container) {
    collapsePane.addTab(group, container, true);
  }

  /**
   * Clear.
   */
  public void clear() {
    collapsePane.clear();
  }
}
