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
import java.util.Collection;

import javax.swing.Box;

import org.jebtk.core.text.Join;
import org.jebtk.core.text.TextUtils;
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
import org.jebtk.modern.graphics.icons.PlusVectorIcon;
import org.jebtk.modern.graphics.icons.SearchVectorIcon;
import org.jebtk.modern.panel.HBox;
import org.jebtk.modern.panel.ModernPillBorderPanel;
import org.jebtk.modern.text.ModernClipboardTextField;
import org.jebtk.modern.text.ModernTextField;
import org.jebtk.modern.window.ModernWindow;
import org.jebtk.modern.text.ITextProperty;

// TODO: Auto-generated Javadoc
/**
 * The class ModernSearchPanel.
 */
public class ModernSearchExtPanel extends ModernPillBorderPanel
    implements ModernClickEventProducer, ModernClickListener, ITextProperty, KeyListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member search button.
   */
  private final ModernClickWidget mSearchButton = new ModernButton(
      AssetService.getInstance().loadIcon(SearchVectorIcon.class, 16)).setButtonStyle(ButtonStyle.CIRCLE_OUTLINE);

  /** The m search 2 button. */
  private final ModernClickWidget mSearch2Button = new ModernButton(
      AssetService.getInstance().loadIcon(PlusVectorIcon.class, 16)).setButtonStyle(ButtonStyle.CIRCLE_OUTLINE); // UIResources.getInstance().loadIcon("binoculars",
  // 16));

  /**
   * The member search field.
   */
  private final ModernTextField mSearchField = new ModernClipboardTextField();

  /**
   * The member listeners.
   */
  private final ModernClickListeners mListeners = new ModernClickListeners();

  /**
   * The member model.
   */
  private SearchModel mModel;

  /** The m delimiter. */
  private String mDelimiter;

  /** The m window. */
  private ModernWindow mWindow;

  /**
   * Instantiates a new modern search panel.
   *
   * @param window the window
   */
  public ModernSearchExtPanel(ModernWindow window) {
    this(window, new SearchModel(), TextUtils.COMMA_DELIMITER);
  }

  /**
   * Instantiates a new modern search ext panel.
   *
   * @param window    the window
   * @param delimiter the delimiter
   */
  public ModernSearchExtPanel(ModernWindow window, String delimiter) {
    this(window, new SearchModel(), delimiter);
  }

  /**
   * Instantiates a new modern search panel.
   *
   * @param window    the window
   * @param model     the model
   * @param delimiter the delimiter
   */
  public ModernSearchExtPanel(ModernWindow window, SearchModel model, String delimiter) {
    mWindow = window;
    mModel = model;
    mDelimiter = delimiter;

    add(mSearchField);

    Box box = HBox.create();

    box.add(mSearchButton);
    box.add(mSearch2Button);

    add(box, BorderLayout.LINE_END);

    mSearchField.addKeyListener(this);

    mSearchButton.addClickListener((ModernClickEvent e) -> {
      search();
    });

    mSearch2Button.addClickListener((ModernClickEvent e) -> {
      search2();
    });

    mSearchField.setText(mModel.get());

    addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        int w = getHeight() / 2;
        setBorder(BorderService.getInstance().createBorder(2, w, 2, w));
      }
    });
  }

  /**
   * Search.
   */
  private void search() {
    mModel.updateCaseSensitive(false);
    mModel.updateInList(true);
    mModel.updateExactMatch(false);

    fireClicked();
  }

  /**
   * Search 2.
   */
  private void search2() {
    SearchDialog dialog = new SearchDialog(mWindow, mSearchField.getText(), mDelimiter);

    dialog.setVisible(true);

    if (dialog.isCancelled()) {
      return;
    }

    mModel.updateCaseSensitive(dialog.caseSensitive());
    mModel.updateInList(dialog.getInList());
    mModel.updateExactMatch(dialog.getExact());

    search(dialog.getLines());
  }

  /**
   * Search.
   *
   * @param items the items
   */
  public void search(Collection<String> items) {
    mSearchField.setText(Join.on(mDelimiter).values(items).toString());

    search();
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
      search();
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

  /**
   * Gets the in list.
   *
   * @return the in list
   */
  public boolean getInList() {
    return mModel.getInList();
  }

  /**
   * Gets the exact.
   *
   * @return the exact
   */
  public boolean getExact() {
    return mModel.getExact();
  }

  /**
   * Gets the case sensitive.
   *
   * @return the case sensitive
   */
  public boolean getCaseSensitive() {
    return mModel.getCaseSensitive();
  }
}
