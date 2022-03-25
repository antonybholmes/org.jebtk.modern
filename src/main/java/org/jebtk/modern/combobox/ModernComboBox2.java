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
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.jebtk.core.event.ChangeListener;
import org.jebtk.core.event.ChangeListeners;
import org.jebtk.core.text.TextUtils;
import org.jebtk.modern.AssetService;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.button.ModernDropDownWidget2;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.graphics.icons.TriangleDownVectorIcon;
import org.jebtk.modern.menu.ModernMenuItem;
import org.jebtk.modern.menu.ModernPopup2;
import org.jebtk.modern.menu.ModernPopupMenu2;
import org.jebtk.modern.menu.ModernScrollPopupMenu2;
import org.jebtk.modern.text.ModernClipboardTextField;
import org.jebtk.modern.text.ModernTextField;
import org.jebtk.modern.theme.DrawUI;

// TODO: Auto-generated Javadoc
/**
 * The class ModernComboBox.
 */
public class ModernComboBox2 extends ModernDropDownWidget2 implements KeyListener, DocumentListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant SELECTION_CHANGED.
   */
  private static final String SELECTION_CHANGED = "selection_changed";

  /**
   * The member display text.
   */
  protected ModernTextField mDisplayText = new ModernClipboardTextField("text");

  // protected ModernClipboardTextBox mDisplayText =
  // new ModernClipboardTextBox("text");

  /**
   * The constant BUTTON_WIDTH.
   */
  public static final int BUTTON_WIDTH = AssetService.ICON_SIZE_16;

  /**
   * The constant TEXT_OFFSET.
   */
  public static final int TEXT_OFFSET = 5;

  /**
   * The constant TEXT_OFFSET_2.
   */
  public static final int TEXT_OFFSET_2 = TEXT_OFFSET * 2;

  /**
   * The constant DOWN_ARROW_ICON.
   */
  public static final ModernIcon DOWN_ARROW_ICON = AssetService.getInstance().loadIcon(TriangleDownVectorIcon.class,
      16);

  private static final DrawUI COMBO_UI = new ComboBoxDrawUI();

  /**
   * The member editable.
   */
  private boolean mEditable = false;

  /**
   * The member popup.
   */
  // protected ModernScrollPopupMenu mPopup; // = new ModernComboPopup();

  /**
   * The member button x.
   */
  protected int mButtonX = -1;

  /**
   * The member items.
   */
  protected List<ModernMenuItem> mItems = new ArrayList<ModernMenuItem>();

  /**
   * The member indices.
   */
  protected Map<String, Integer> mIndices = new HashMap<String, Integer>();

  /**
   * The member item map.
   */
  protected Map<String, ModernMenuItem> mItemMap = new HashMap<String, ModernMenuItem>();

  /**
   * The member selected index.
   */
  private int mSelectedIndex = 0;

  /**
   * The member p.
   */
  private Point mP = new Point();

  /**
   * The member listeners.
   */
  private ChangeListeners mListeners = new ChangeListeners();

  /**
   * The class MouseEvents.
   */
  private class MouseEvents extends MouseAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseClicked(MouseEvent e) {
      click();
    }
  }

  /**
   * The class MouseMotionEvents.
   */
  private class MouseMotionEvents implements MouseMotionListener {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.
     * MouseEvent)
     */
    @Override
    public void mouseDragged(MouseEvent e) {
      // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseMoved(MouseEvent e) {
      mP.x = e.getX();
      mP.y = e.getY();
    }

  }

  /**
   * The class ComponentEvents.
   */
  private class ComponentEvents extends ComponentAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.ComponentAdapter#componentResized(java.awt.event.
     * ComponentEvent)
     */
    @Override
    public final void componentResized(ComponentEvent e) {
      resize();
    }
  }

  /**
   * The class PopupClickActions.
   */
  private class PopupClickActions implements ModernClickListener {

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
     * modern .event.ModernClickEvent)
     */
    @Override
    public void clicked(ModernClickEvent e) {

      if (e.getMessage() != null) {
        setText(e.getMessage());

        // System.err.println("clicked " + e.getMessage() + " " +
        // mIndices.containsKey(e.getMessage()));

        if (mIndices.containsKey(e.getMessage())) {
          setSelectedIndex(mIndices.get(e.getMessage()));
        } else {
          setSelectedIndex(-1);
        }
      }
    }
  }

  /*
   * private class PopupActions implements PopupMenuListener {
   * 
   * public final void popupMenuCanceled(PopupMenuEvent e) { mPopupShown = false;
   * setHighlighted(false);
   * 
   * }
   * 
   * public final void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
   * mPopupShown = false; setHighlighted(false);
   * 
   * }
   * 
   * public final void popupMenuWillBecomeVisible(PopupMenuEvent e) { //reset(); }
   * }
   */

  /**
   * Instantiates a new modern combo box.
   *
   * @param size the size
   */
  public ModernComboBox2(Dimension size) {
    this(new ModernScrollPopupMenu2());

    UI.setSize(this, size);
  }

  /**
   * Instantiates a new modern combo box.
   */
  public ModernComboBox2() {
    this(new ModernScrollPopupMenu2());
  }

  /**
   * Instantiates a new modern combo box.
   *
   * @param items the items
   */
  public ModernComboBox2(List<String> items) {
    this(new ModernScrollPopupMenu2());

    for (String item : items) {
      addMenuItem(item);
    }

    setSelectedIndex(0);
  }

  /**
   * Instantiates a new modern combo box.
   *
   * @param items the items
   * @param size  the size
   */
  public ModernComboBox2(List<String> items, Dimension size) {
    this(items);

    UI.setSize(this, size);
  }

  /**
   * Instantiates a new modern combo box.
   *
   * @param popup the popup
   */
  public ModernComboBox2(ModernPopupMenu2 popup) {
    this(TextUtils.EMPTY_STRING, popup);
  }

  /**
   * Instantiates a new modern combo box.
   *
   * @param text  the text
   * @param popup the popup
   */
  public ModernComboBox2(String text, ModernPopupMenu2 popup) {
    super(text, popup);

    setLayout(null);

    mDisplayText.setText(text);

    add(mDisplayText);

    addMouseListener(new MouseEvents());
    addMouseMotionListener(new MouseMotionEvents());
    addComponentListener(new ComponentEvents());

    // this.addClickListener(this);
    // this.addMouseListener(this);

    // popup = new ComboPopup();
    // popup.setFocusable(true);
    // popup.addWindowFocusListener(this);

    setMenu(popup);

    // displayText.addMouseListener(this);
    mDisplayText.addKeyListener(this);
    mDisplayText.getDocument().addDocumentListener(this);

    // popup.addWindowListener(this);
    // popup.setLocationRelativeTo(this);

    // setMinimumSize(new Dimension(10, 10));
    // setCanvasSize(ModernTheme.MAX_WIDGET_SIZE);
    // setMaximumSize(ModernTheme.MAX_WIDGET_SIZE);

    setEditable(false);

    UI.setSize(this, 120, ModernWidget.WIDGET_HEIGHT);

    resize();

    addStyleClass("content-box");

    // addAnimations("combobox2");
    getDrawStates().add(COMBO_UI);
  }

  /**
   * Sets the popup.
   *
   * @param popup the new popup
   */
  @Override
  public void setMenu(ModernPopup2 popup) {
    super.setMenu(popup);

    // mMenu.addPopupMenuListener(new PopupActions());
    mMenu.addClickListener(new PopupClickActions());
  }

  /*
   * @Override public void drawBackgroundAA(Graphics2D g2) { IntRect buttonRect =
   * new IntRect(mButtonX, mRect.getY(), BUTTON_WIDTH, mRect.getH());
   * 
   * getWidgetRenderer().drawBackground(g2, mInternalRect);
   * 
   * if (mHighlight) { getWidgetRenderer().drawButtonOutline(g2, mRect,
   * RenderMode.SELECTED); getWidgetRenderer().drawButton(g2, buttonRect,
   * RenderMode.SELECTED);
   * 
   * //g2.setColor(highlightBorderColor); //g2.drawRect(mRect.getX(),
   * mRect.getY(), mRect.getW() - 1, mRect.getH() - 1); //Theme.paintRect(g2,
   * getRect(), highlightBorderColor);
   * 
   * //g2.setColor(ThemeService.getInstance().getThemeColor(2));
   * //ModernTheme.drawRect(g2, buttonRect, highlightBorderColor);
   * //g2.drawRect(buttonX, mRect.getY(), BUTTON_WIDTH - 1, mRect.getH() - 1); }
   * else if (mPressed) { //g2.setColor(highlightBorderColor);
   * //g2.drawRect(mRect.getX(), mRect.getY(), mRect.getW() - 1, mRect.getH() -
   * 1); //paintSelectedBorder(g2, getRect());
   * 
   * //paintSelected(g2, buttonRect); getWidgetRenderer().drawButtonOutline(g2,
   * mRect, RenderMode.SELECTED); getWidgetRenderer().drawButton(g2, buttonRect,
   * RenderMode.SELECTED); } else { getWidgetRenderer().drawOutline(g2, mRect); }
   * 
   * //paintImage(this, g2, ModernDropDownMenuButton.DROP_ARROW_ICON, buttonRect);
   * 
   * DOWN_ARROW_ICON.drawIcon(g2, buttonRect.getX(), buttonRect.getY() +
   * (buttonRect.getH() - 16) / 2, 16); }
   */

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    g2.setColor(TEXT_COLOR);
    g2.drawString(getText(), ModernWidget.PADDING, getTextYPosCenter(g2, getHeight()));
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.JComponent#setEnabled(boolean)
   */
  @Override
  public void setEnabled(boolean enabled) {

    super.setEnabled(enabled);

    mDisplayText.setEditable(mEditable && enabled);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.Component#addKeyListener(java.awt.event.KeyListener)
   */
  public final void addKeyListener(KeyListener listener) {
    mDisplayText.addKeyListener(listener);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernClickWidget#doClick()
   */
  public void doClick() {
    click();
  }

  /**
   * Click.
   */
  private void click() {
    if (!this.isEnabled()) {
      return;
    }

    if (mP.x < mButtonX) {
      return;
    }

    // setHighlighted(false);
    // mPopupShown = true;

    // repaint();

    // fireModernClickEvent(new ModernClickEvent(this, SHOW_MENU));
    // mMenu.showPopup(this);

    showMenu();
  }

  /**
   * Determines whether the combobox can be edited or not.
   *
   * @param editable the new editable
   */
  public void setEditable(boolean editable) {
    mEditable = editable;

    mDisplayText.setEditable(editable && isEnabled());
    mDisplayText.setVisible(editable);
  }

  /**
   * Checks if is editable.
   *
   * @return true, if is editable
   */
  public final boolean isEditable() {
    return mEditable;
  }

  /**
   * Adds the scroll modern menu item.
   *
   * @param i the i
   */
  public final void addScrollMenuItem(int i) {
    addScrollMenuItem(Integer.toString(i));
  }

  /**
   * Adds the scroll modern menu item.
   *
   * @param text the text
   */
  public final void addScrollMenuItem(String text) {
    addScrollMenuItem(new ModernMenuItem(text));
  }

  /**
   * Adds the scroll modern menu item.
   *
   * @param item the item
   */
  public final void addScrollMenuItem(ModernMenuItem item) {
    ((ModernScrollPopupMenu2) mMenu).addScrollMenuItem(item);

    registerItem(item);
  }

  /**
   * Adds the menu item.
   *
   * @param text the text
   */
  public final void addMenuItem(String text) {
    ModernMenuItem menuItem = new ModernMenuItem(text);

    addMenuItem(menuItem);
  }

  /**
   * Adds the modern menu item.
   *
   * @param item the item
   */
  public final void addMenuItem(ModernMenuItem item) {
    ((ModernScrollPopupMenu2) mMenu).addMenuItem(item);

    registerItem(item);
  }

  /**
   * Register item.
   *
   * @param item the item
   */
  public final void registerItem(ModernMenuItem item) {

    mIndices.put(item.getClickMessage(), mItems.size());
    mItems.add(item);
    mItemMap.put(item.getClickMessage(), item);

    if (mIndices.size() == 1 && !isEditable()) {
      // default to the first item is the combo box is uneditable
      setSelectedIndex(0);
    }
  }

  /**
   * Sets the selected index.
   *
   * @param index the new selected index
   */
  public void setSelectedIndex(int index) {
    if (index == -1 || index >= mItems.size()) {
      return;
    }

    changeSelectedIndex(index);

    fireClicked(new ModernClickEvent(this, SELECTION_CHANGED));
  }

  /**
   * Change the view without triggering a click event.
   *
   * @param index the index
   */
  public void changeSelectedIndex(int index) {
    if (index == -1 || index >= mItems.size()) {
      return;
    }

    mSelectedIndex = index;

    mDisplayText.setText(mItems.get(mSelectedIndex).getText());
  }

  /**
   * Gets the selected item.
   *
   * @return the selected item
   */
  public final Object getSelectedItem() {
    return mDisplayText.getText(); // items.get(selectedIndex);
  }

  /**
   * Returns the number of items in the list.
   *
   * @return the item count
   */
  public final int getItemCount() {
    return mItems.size();
  }

  /**
   * Resize.
   */
  public final void resize() {

    Insets insets = getInsets();

    mButtonX = getWidth() - insets.right - BUTTON_WIDTH;

    mDisplayText.setBounds(mRect.getX() + TEXT_OFFSET, mRect.getY() + 1, mButtonX - TEXT_OFFSET_2, mRect.getH() - 2);

    // Resize the menu items to match the combo width
    for (ModernMenuItem item : mItems) {
      UI.setSize(item, new Dimension(getWidth(), item.getPreferredSize().height));
    }

    repaint();
  }

  /**
   * Gets the selected index.
   *
   * @return the selected index
   */
  public int getSelectedIndex() {
    return mSelectedIndex;
  }

  /**
   * Clear.
   */
  public void clear() {
    mIndices.clear();
    mItems.clear();
    mMenu.clear();
  }

  /**
   * Sets the text.
   *
   * @param text the new text
   */
  public void setText(String text) {
    super.setText(text);

    if (mDisplayText != null) {
      mDisplayText.setText(text);
    }

    repaint();
  }

  /**
   * Gets the text.
   *
   * @return the text
   */
  public String getText() {
    return mDisplayText.getText();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
   */
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
      // fireClicked(new ModernClickEvent(this, mDisplayText.getText()));

      mListeners.fireChanged();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
   */
  public void keyReleased(KeyEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
   */
  public void keyTyped(KeyEvent e) {
  }

  /**
   * Gets the item at.
   *
   * @param i the i
   * @return the item at
   */
  public Object getItemAt(int i) {
    return mItems.get(i);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.Component#toString()
   */
  public String toString() {
    return getText();
  }

  /**
   * Adds the text changed listener.
   *
   * @param l the l
   */
  public void addTextChangedListener(ChangeListener l) {
    mListeners.addChangeListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.event.DocumentListener#changedUpdate(javax.swing.event.
   * DocumentEvent)
   */
  @Override
  public void changedUpdate(DocumentEvent e) {
    mListeners.fireChanged();
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.event.DocumentListener#insertUpdate(javax.swing.event.
   * DocumentEvent)
   */
  @Override
  public void insertUpdate(DocumentEvent e) {
    changedUpdate(e);
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.event.DocumentListener#removeUpdate(javax.swing.event.
   * DocumentEvent)
   */
  @Override
  public void removeUpdate(DocumentEvent e) {
    changedUpdate(e);
  }
}
