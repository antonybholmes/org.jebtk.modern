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
package org.jebtk.modern.menu;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import org.jebtk.core.settings.SettingsService;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.button.ModernButton;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.graphics.icons.ModernIcon;

// TODO: Auto-generated Javadoc
/**
 * The class ModernMenuItem.
 */
public class ModernMenuItem extends ModernButton {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant HEIGHT.
   */
  public static final int HEIGHT = SettingsService.getInstance().getInt("theme.menu.height");

  /**
   * The constant WIDTH.
   */
  public static final int WIDTH = SettingsService.getInstance().getInt("theme.menu.width");

  /**
   * The constant MIN_SIZE.
   */
  public static final Dimension MIN_SIZE = new Dimension(0, HEIGHT);

  /**
   * The constant PREFERRED_SIZE.
   */
  public static final Dimension PREFERRED_SIZE = new Dimension(WIDTH, HEIGHT);

  /**
   * The constant MAX_SIZE.
   */
  public static final Dimension MAX_SIZE = new Dimension(Short.MAX_VALUE, HEIGHT);

  // public static final Color ICON_BACKGROUND = DialogButton.LIGHT_COLOR;
  // public static final Color ICON_BACKGROUND_SEPARATOR =
  // DialogButton.DARK_COLOR;

  // orange
  // public static final Color HIGHLIGHT_COLOR = new Color(253, 238, 179); //new
  // Color(202, 225, 255);
  // public static final Color BORDER_COLOR = new Color(242, 202, 88); //new
  // Color(100, 149, 237);
  // public static final Color DARK_BORDER_COLOR = new Color(50, 100, 175);

  // public static final Color COLOR_1 = new Color(220, 230, 250);
  // //Color.WHITE;

  /**
   * Allows a menu hierarchy.
   */
  private final List<ModernMenuItem> mSubMenuItems = new ArrayList<>();

  /**
   * The text (possibly truncated) that is displayed in the UI element.
   */
  protected String mDisplayText;

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
      setHighlighted(false);
      setSelected(false);
    }
  }

  /**
   * The Class ComponentEvents.
   */
  private class ComponentEvents extends ComponentAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.ComponentAdapter#componentResized(java.awt.event.
     * ComponentEvent)
     */
    @Override
    public void componentResized(ComponentEvent e) {
      setDisplayText();
    }
  }

  /**
   * Instantiates a new modern menu item.
   *
   * @param text the text
   */
  public ModernMenuItem(String text) {
    super(text);

    setup();
  }

  /**
   * Instantiates a new modern menu item.
   *
   * @param text the text
   * @param icon the icon
   */
  public ModernMenuItem(String text, ModernIcon icon) {
    super(text, icon);

    setup();
  }

  /**
   * Instantiates a new modern menu item.
   *
   * @param text the text
   * @param icon the icon
   * @param size the size
   */
  public ModernMenuItem(String text, ModernIcon icon, Dimension size) {
    this(text, icon);

    UI.setSize(this, size);
  }

  /**
   * Setup.
   */
  private void setup() {
    // Menu items should not be displaying tooltips by default
    setToolTipsEnabled(false);

    addClickListener(new ClickEvents());
    addComponentListener(new ComponentEvents());

    UI.setSize(this, PREFERRED_SIZE);

    // setAnimations("menu");

    // update("square-borders");
    // addToStyleClass("gray-button");
    // setAnimations("button-fill");

    addStyleClass("ribbon-button");
  }

  /**
   * Returns the sub menu items associated with this menu item.
   *
   * @return the sub menu items
   */
  public final List<ModernMenuItem> getSubMenuItems() {
    return mSubMenuItems;
  }

  /*
   * @Override public void drawBackground(Graphics2D g2) { fillBackground(g2);
   * 
   * if (isEnabled() && (highlight || isSelected())) { //paintHighlighted(g2,
   * getRect()); //paintHighlightedBorder(g2, getRect());
   * 
   * g2.setColor(HIGHLIGHT); g2.setStroke(ModernTheme.DOUBLE_LINE_STROKE);
   * g2.drawRect(mInternalRect.getX() + 1, mInternalRect.getY() + 1,
   * mInternalRect.getW() - 2, mInternalRect.getH() - 2); } }
   */

  /**
   * Gets the display text.
   *
   * @return the display text
   */
  public String getDisplayText() {
    return mDisplayText;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.widget.ModernWidget#drawBackground(java.awt.Graphics2D)
   */
  /*
   * @Override public void drawBackground(Graphics2D g2) { if (isEnabled()) { if
   * (isSelected() || mHighlight) { getWidgetRenderer().drawMenu(g2,
   * mInternalRect, RenderMode.SELECTED); } } }
   */

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.button.ModernButton#drawForegroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    if (mDisplayText != null) {
      g2.setColor(getForeground());
      g2.drawString(mDisplayText, PADDING, getTextYPosCenter(g2, getHeight()));
    }
  }

  /**
   * Adds a new menu item.
   *
   * @param item the item
   */
  public void add(ModernMenuItem item) {
    getSubMenuItems().add(item);
  }

  /**
   * Determines what text should be displayed (truncating if necessary).
   */
  public void setDisplayText() {
    mDisplayText = ModernWidget.truncate(getText(), getWidth());
  }
}