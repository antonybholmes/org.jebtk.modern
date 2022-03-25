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
package org.jebtk.modern.tabs;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import org.jebtk.modern.ModernFocusableWidget;

/**
 * A controller view of the tabs i.e. handles user input.
 * 
 * @author Antony Holmes
 *
 */
public class TabsController extends ModernFocusableWidget implements TabEventListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member model.
   */
  private TabsModel mModel;

  /**
   * Instantiates a new tabs controller.
   *
   * @param model the model
   */
  public TabsController(TabsModel model) {
    mModel = model;

    model.addTabListener(this);

    getInputMap(WHEN_FOCUSED).put(KeyStroke.getKeyStroke("DOWN"), "down");
    getInputMap(WHEN_FOCUSED).put(KeyStroke.getKeyStroke("RIGHT"), "down");

    getActionMap().put("down", new AbstractAction() {

      private static final long serialVersionUID = 1L;

      @Override
      public void actionPerformed(ActionEvent e) {
        mModel.changeTab(mModel.getSelectedIndex() + 1);
      }
    });

    getInputMap(WHEN_FOCUSED).put(KeyStroke.getKeyStroke("UP"), "up");
    getInputMap(WHEN_FOCUSED).put(KeyStroke.getKeyStroke("LEFT"), "up");
    getActionMap().put("up", new AbstractAction() {

      private static final long serialVersionUID = 1L;

      @Override
      public void actionPerformed(ActionEvent e) {
        mModel.changeTab(mModel.getSelectedIndex() - 1);
      }
    });
  }

  /**
   * Return the model associated with this controller.
   *
   * @return the tabs model
   */
  public TabsModel getTabsModel() {
    return mModel;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.tabs.TabEventListener#tabChanged(org.abh.lib.ui.
   * modern. tabs.TabEvent)
   */
  @Override
  public void tabChanged(TabEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.tabs.TabEventListener#tabAdded(org.abh.lib.ui.modern.
   * tabs.TabEvent)
   */
  @Override
  public void tabAdded(TabEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.tabs.TabEventListener#tabRemoved(org.abh.lib.ui.
   * modern. tabs.TabEvent)
   */
  @Override
  public void tabRemoved(TabEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.tabs.TabEventListener#tabResized(org.abh.lib.ui.
   * modern. tabs.TabEvent)
   */
  @Override
  public void tabResized(TabEvent e) {
    // TODO Auto-generated method stub

  }

  @Override
  public void tabHighlighted(TabEvent e) {
    // TODO Auto-generated method stub

  }
}
