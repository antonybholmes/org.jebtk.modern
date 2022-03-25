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
package org.jebtk.modern.contentpane;

import java.awt.Component;

import javax.swing.border.Border;

import org.jebtk.modern.button.ModernButton;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.tabs.CloseButton;
import org.jebtk.modern.tabs.TabsModel;

/**
 * All content panes.
 * 
 * @author Antony Holmes
 *
 */
public class CloseableHTab extends HTab {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member model.
   */
  private TabsModel mModel;

  /** The m title. */
  private String mTitle;

  /**
   * The class ClickEvents.
   */
  private class ClickEvents implements ModernClickListener {

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
     * modern .event.ModernClickEvent)
     */
    @Override
    public void clicked(ModernClickEvent e) {
      mModel.removeTab(mTitle);
    }

  }

  /**
   * Instantiates a new closeable h tab2.
   *
   * @param title   the title
   * @param content the content
   * @param viewer  the viewer
   */
  public CloseableHTab(String title, Component content, ModernHContentPane viewer) {
    this(title, content, viewer.tabs());
  }

  public CloseableHTab(String title, Component content, ModernHContentPane viewer, Border border) {
    this(title, content, viewer.tabs(), border);
  }

  /**
   * Instantiates a new closeable h tab2.
   *
   * @param title   the title
   * @param content the content
   * @param model   the model
   */
  public CloseableHTab(String title, Component content, TabsModel model) {
    super(title, content);

    setup(title, content, model);
  }

  public CloseableHTab(String title, Component content, TabsModel model, Border border) {
    super(title, content, border);

    setup(title, content, model);
  }

  private void setup(String title, Component content, TabsModel model) {
    mTitle = title;
    mModel = model;

    ModernButton button = new CloseButton();

    button.addClickListener(new ClickEvents());

    addToToolbar(button);
  }
}
