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
package org.jebtk.modern.dialog;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.JComponent;

import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.button.ButtonsBox;
import org.jebtk.modern.button.ModernButton;
import org.jebtk.modern.contentpane.ModernHContentPane;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.panel.CardPanel2;
import org.jebtk.modern.panel.ModernPanel;
import org.jebtk.modern.panel.VBoxAutoWidth;
import org.jebtk.modern.text.ModernDialogHeadingLabel;
import org.jebtk.modern.window.ModernWindow;
import org.jebtk.modern.window.WindowLeftTabNav;

/**
 * Standardized dialog window for dialogs that have buttons along the bottom
 * such as OK and Cancel for when user is performing a task.
 * 
 * @author Antony Holmes
 *
 */
public class ModernDialogTaskWindow extends ModernDialogWindow implements ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The m buttons. */
  protected ModernDialogButtonsBox mButtons = new ModernDialogButtonsBox();

  /** The m ok button. */
  protected ModernButton mOkButton = new ModernDialogPrimaryButton(UI.BUTTON_OK);

  /**
   * The close button.
   */
  protected ModernButton mCancelButton = new ModernDialogButton(UI.BUTTON_CANCEL);

  private final ModernHContentPane mContentPane = new ModernHContentPane();

  private WindowLeftTabNav mIconTabs;

  /**
   * Instantiates a new modern dialog window.
   *
   * @param parent the parent
   */
  public ModernDialogTaskWindow(ModernWindow parent) {
    this(parent, true);
  }

  /**
   * Instantiates a new modern dialog window.
   *
   * @param parent the parent
   * @param modal  the modal
   */
  public ModernDialogTaskWindow(ModernWindow parent, boolean modal) {
    this(parent, modal, ModernDialogTaskType.OK_CANCEL);
  }

  /**
   * Instantiates a new modern dialog task window.
   *
   * @param parent the parent
   * @param type   the type
   */
  public ModernDialogTaskWindow(ModernWindow parent, ModernDialogTaskType type) {
    this(parent, true, type);
  }

  /**
   * Instantiates a new modern dialog task window.
   *
   * @param parent the parent
   * @param modal  the modal
   * @param type   the type
   */
  public ModernDialogTaskWindow(ModernWindow parent, boolean modal, ModernDialogTaskType type) {
    super(parent, modal);

    // If there are buttons to add, add the panel to display them.
    if (type != ModernDialogTaskType.NONE) {
      setButtons(mButtons);
    }

    setup(type);
  }

  /**
   * Set the dialog type and hence which buttons are on display.
   *
   * @param type the new up
   */
  protected void setup(ModernDialogTaskType type) {

    switch (type) {
    case CLOSE:
      mOkButton.setText(UI.MENU_CLOSE);
    case OK:
      addOkButton();
      break;
    case CANCEL:
      addCancelButton();
      break;
    case OK_CANCEL:
      addOkCancelButtons();
      break;
    default:
      break;
    }

    // setLightBackground();

    setBody(mContentPane);

    mIconTabs = new WindowLeftTabNav(getTabsPane());

    getTabsPane().tabs().setCenterTab(new VBoxAutoWidth(ModernWidget.DOUBLE_BORDER));

    setDarkBackground();
  }

  /**
   * Set the center card panel. This is will destroy any cards added using the
   * {@code addCard()} method.
   * 
   * @param c
   */
  @Override
  public void setCard(JComponent c) {
    //etCenterTab(new ModernComponent(new CardPanel2(c, ModernWidget.QUAD_BORDER), ModernWidget.DOUBLE_BORDER));
    setCenterTab(new ModernComponent(c, ModernWidget.DOUBLE_BORDER));
    
    //setBody(new ModernComponent(new CardPanel2(c, ModernWidget.QUAD_BORDER), ModernWidget.DOUBLE_BORDER));
    
    //setDarkBackground();

    // addCard(c);
  }
  
  public void setCenterTab(JComponent c) {
    getTabsPane().tabs().setCenterTab(c);
  }

  /**
   * Add a vertical card to the center UI.
   * 
   * @param c
   */
  public void addCard(Component c) {
    // getTabsPane().tabs().setCenterTab(
    // new CardPanel(new ModernComponent(c, ModernWidget.TRIPLE_BORDER),
    // ModernWidget.DOUBLE_BORDER));

    addBlock(new CardPanel2(new ModernComponent(c, ModernWidget.TRIPLE_BORDER)));

    setDarkBackground();
  }

  /**
   * Add a vertical block to the center UI. This could be space or a non card
   * item.
   * 
   * @param c
   */
  public void addBlock(Component c) {
    // getTabsPane().tabs().setCenterTab(
    // new CardPanel(new ModernComponent(c, ModernWidget.TRIPLE_BORDER),
    // ModernWidget.DOUBLE_BORDER));

    getCenterComp().add(c);
  }

  public JComponent getCenterComp() {
    return getTabsPane().tabs().centerTab().getComponent();
  }

  /**
   * Gets the button bar.
   *
   * @return the button bar
   */
  public ButtonsBox getButtonBar() {
    return mButtons;
  }

  /**
   * Return the default tabs pane available in the dialog to create horizontally
   * tabbed layouts such as a side bar and main panel.
   * 
   * @return
   */
  public ModernHContentPane getTabsPane() {
    return mContentPane;
  }

  public WindowLeftTabNav getIconTabs() {
    return mIconTabs;
  }

  /**
   * Add standard OK and Cancel buttons to the dialog. Use clicked method to
   * respond.
   */
  public void addOkCancelButtons() {
    getButtonBar().add(mOkButton);
    getButtonBar().add(ModernPanel.createHGap());
    getButtonBar().add(mCancelButton);

    mOkButton.addClickListener(this);
    mCancelButton.addClickListener(this);
  }

  /**
   * Add standard OK and Cancel buttons to the dialog. Use clicked method to
   * respond.
   */
  public void addOkButton() {
    getButtonBar().add(mOkButton);

    mOkButton.addClickListener(this);
  }

  /**
   * Add standard OK and Cancel buttons to the dialog. Use clicked method to
   * respond.
   */
  public void addCancelButton() {
    getButtonBar().add(mCancelButton);

    mCancelButton.addClickListener(this);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.event.ModernClickListener#clicked(org.abh.common.ui.
   * event. ModernClickEvent)
   */
  @Override
  public void clicked(ModernClickEvent e) {
    if (e.getSource().equals(mOkButton)) {
      mStatus = ModernDialogStatus.OK;
    }

    close();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.dialog.ModernDialogWindow#setContent(javax.swing.
   * JComponent)
   */
  @Override
  public void setContent(JComponent c) {
    setBody(new ModernComponent(c, ModernWidget.DOUBLE_BORDER));
  }

  /**
   * Create a standardized section header in a dialog vertical box.
   *
   * @param name the name
   * @param box  the box
   */
  public static void sectionHeader(final String name, Box box) {
    box.add(new ModernDialogHeadingLabel(name));
    box.add(UI.createVGap(10));
  }

  /**
   * Create a standardized mid section header in a dialog vertical box. This
   * should be used for subsequent headers after using {@code sectionHeader(..)}
   *
   * @param name the name
   * @param box  the box
   */
  public static void midSectionHeader(final String name, Box box) {
    box.add(UI.createVGap(20));
    sectionHeader(name, box);
  }
}
