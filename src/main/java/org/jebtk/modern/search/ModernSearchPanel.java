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

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.jebtk.modern.AssetService;
import org.jebtk.modern.BorderService;
import org.jebtk.modern.UI;
import org.jebtk.modern.button.ButtonStyle;
import org.jebtk.modern.button.ModernButton;
import org.jebtk.modern.button.ModernClickWidget;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickEventProducer;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.event.ModernClickListeners;
import org.jebtk.modern.graphics.icons.SearchVectorIcon;
import org.jebtk.modern.panel.ModernBorderPanel;
import org.jebtk.modern.text.ModernClipboardTextField;
import org.jebtk.modern.text.ModernTextField;
import org.jebtk.modern.theme.ColorStyle;
import org.jebtk.modern.text.ITextProperty;

// TODO: Auto-generated Javadoc
/**
 * The class ModernSearchPanel.
 */
public class ModernSearchPanel extends ModernBorderPanel
    implements ModernClickEventProducer, ModernClickListener, ITextProperty, KeyListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member search button.
   */
  private ModernClickWidget mSearchButton = new ModernButton(
      AssetService.getInstance().loadIcon(SearchVectorIcon.class, 16)).setColorStyle(ColorStyle.RIBBON)
          .setButtonStyle(ButtonStyle.CIRCLE, ButtonStyle.OUTLINE);

  /**
   * The member search field.
   */
  private ModernTextField mSearchField = new ModernClipboardTextField();

  /**
   * The member listeners.
   */
  private ModernClickListeners mListeners = new ModernClickListeners();

  /**
   * The member model.
   */
  private SearchModel mModel;

  /**
   * Instantiates a new modern search panel.
   */
  public ModernSearchPanel() {
    this(new SearchModel());
  }

  /**
   * Instantiates a new modern search panel.
   *
   * @param model the model
   */
  public ModernSearchPanel(SearchModel model) {
    mModel = model;

    System.err.println("search search " + mSearchButton.getPreferredSize() + " " + mSearchButton.getAspectRatio());

    // mSearchField.setBorder(BorderService.getInstance().createLeftBorder(20));
    add(mSearchField);
    add(mSearchButton, BorderLayout.LINE_END);

    mSearchField.addKeyListener(this);
    mSearchButton.addClickListener(this);

    mSearchField.setText(mModel.get());

    // UI.setSize(mSearchButton, 24, 24);

    addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        int b = 2; // mSearchButton.getHeight() + 4; //(getInternalRect().mH -
        // mSearchButton.getHeight()) / 2;
        setBorder(BorderService.getInstance().createBorder(b, getHeight() / 2, b, b));
      }
    });
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.event.ModernClickEventProducer#addClickListener(org.
   * abh .lib.ui.modern.event.ModernClickListener)
   */
  @Override
  public void addClickListener(ModernClickListener l) {
    mListeners.addClickListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.event.ModernClickEventProducer#removeClickListener(
   * org. abh.lib.ui.modern.event.ModernClickListener)
   */
  @Override
  public void removeClickListener(ModernClickListener l) {
    mListeners.removeClickListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.event.ModernClickEventProducer#fireClicked(org.abh.
   * lib. ui.modern.event.ModernClickEvent)
   */
  @Override
  public void fireClicked(ModernClickEvent e) {
    mListeners.fireClicked(e);
  }

  /**
   * Fire clicked.
   */
  public void fireClicked() {
    mModel.set(getText());

    fireClicked((new ModernClickEvent(this, UI.MENU_SEARCH)));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  @Override
  public final void clicked(ModernClickEvent e) {
    fireClicked();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
   */
  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      fireClicked();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
   */
  @Override
  public void keyReleased(KeyEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
   */
  @Override
  public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.text.TextProperty#getText()
   */
  public String getText() {
    return mSearchField.getText();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.text.TextProperty#setText(java.lang.String)
   */
  @Override
  public void setText(String text) {
    mSearchField.setText(text);
  }
}
