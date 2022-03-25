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
package org.jebtk.modern.ribbon;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.Arc2D;
import java.awt.geom.GeneralPath;

import org.jebtk.modern.AssetService;
import org.jebtk.modern.UI;
import org.jebtk.modern.button.ModernCheckButton;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.theme.DrawUI;
import org.jebtk.modern.theme.MaterialService;
import org.jebtk.modern.theme.MaterialUtils;

// TODO: Auto-generated Javadoc
/**
 * The class RibbonMenuItem.
 */
public class RibbonMenuItem extends ModernCheckButton implements ComponentListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  // public static final Color HIGHLIGHT_COLOR = new Color(0x2c5aa8);
  // public static final Color SELECTED_COLOR = new Color(0x5f8dd3);

  // private static final int ICON_OFFSET = 10;
  /**
   * The constant TEXT_OFFSET.
   */
  // private static final int ICON_WIDTH = 16;
  protected static final int TEXT_OFFSET = 2 * PADDING + AssetService.ICON_SIZE_16;

  /**
   * The constant SIZE.
   */
  public static final Dimension RIBBON_MENU_ITEM_SIZE = new Dimension(RibbonFileMenuPanel.RIBBON_MENU_WIDTH,
      RibbonFileMenuPanel.RIBBON_MENU_WIDTH / 5); // * 4 / 5);

  /** The Constant RIBBON_MENU_ITEM_SIZE_2. */
  public static final Dimension RIBBON_MENU_ITEM_SIZE_2 = RIBBON_MENU_ITEM_SIZE; // new
  // Dimension(RibbonFileMenuPanel.RIBBON_MENU_WIDTH,
  // RibbonFileMenuPanel.RIBBON_MENU_WIDTH
  // *
  // 3
  // /
  // 5);

  /**
   * The constant MENU_SELECTED_COLOR.
   */
  public static final Color MENU_SELECTED_COLOR = MaterialService.getInstance().getColor("gray-selected"); // ColorUtils.getTransparentColor60(Color.WHITE);

  /**
   * The constant MENU_HIGHLIGHT_COLOR.
   */
  public static final Color MENU_HIGHLIGHT_COLOR = MaterialService.getInstance().getColor("gray-highlight");
  
  public static final DrawUI MENU_UI = new RibbonMenuItemUI();

  /** The Constant ROUNDING. */
  public static final int ROUNDING = 10;

  /** The Constant OFFSET. */
  public static final int OFFSET = 4;

  /** The m P. */
  private GeneralPath mP;

  // private JLabel label = new JLabel();

  // private int iconY;

  /**
   * Instantiates a new ribbon menu item.
   *
   * @param title the title
   */
  public RibbonMenuItem(String title) {
    super(title);

    setup();

    UI.setSize(this, RIBBON_MENU_ITEM_SIZE_2);
  }

  /**
   * Instantiates a new ribbon menu item.
   *
   * @param title the title
   * @param icon  the icon
   */
  public RibbonMenuItem(String title, ModernIcon icon) {
    super(title, icon);

    setup();

    UI.setSize(this, RIBBON_MENU_ITEM_SIZE);
  }

  /**
   * Setup.
   */
  private void setup() {

    // setLayout(new BorderLayout());

    // label.setText(title);
    // label.setForeground(Color.WHITE);

    // int offset = TEXT_OFFSET;

    /*
     * if (icon != null) { offset += ICON_OFFSET + icon.getIconWidth(); }
     */

    // label.setBorder(BorderService.getInstance().createLeftBorder(offset));

    // add(label, BorderLayout.CENTER);

    // this.addMouseListener(this);
    addComponentListener(this);

    UI.setSize(this, RIBBON_MENU_ITEM_SIZE);

    addStyleClass("dialog-button");
    
    getDrawStates().add(MENU_UI);

    // g2.drawString(this.getText(), ICON_OFFSET + Resources.ICON_SIZE_16 +
    // TEXT_OFFSET, y);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.button.ModernCheckButton#drawBackground(java.awt.
   * Graphics2D)
   */
  /*
   * @Override public void drawBackgroundAA(Graphics2D g2) { if (isSelected()) {
   * fill(g2, MENU_SELECTED_COLOR, mRect); } else if (mHighlight) { fill(g2,
   * MENU_HIGHLIGHT_COLOR, mRect); } else { // Do nothing } }
   */

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.button.ModernCheckButton#drawForegroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
//    if (getIcon() != null) {
//      int x = (getWidth() - getIcon().getWidth()) / 2;
//      int y = getHeight() * 2 / 10;
//
//      getIcon().drawIcon(g2, x, y, getIcon().getWidth());
//    }
//
//    g2.setFont(MaterialUtils.FONT);
//    // g2.setColor(TEXT_COLOR); //Color.WHITE);
//
//    if (isSelected()) {
//      g2.setColor(MaterialService.getInstance().getColor("ribbon-menu-font-selected"));
//    } else if (getHightlighted()) {
//      g2.setColor(MaterialService.getInstance().getColor("ribbon-menu-font-highlight"));
//    } else {
//      g2.setColor(MaterialService.getInstance().getColor("ribbon-menu-font"));
//    }
//
//    int x = getWidth() / 5; // getTextXPosCenter(g2, mText1, getWidth());
//    int y;
//
//    y = getTextYPosCenter(g2, getHeight());
//
//    g2.drawString(mText1, x, y);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernTwoStateWidget#setSelected(boolean)
   */
  public void setSelected(boolean selected) {

    // System.out.println(getText() + " " + selected);

    super.setSelected(selected);

    if (!selected) {
      // label.setForeground(Color.BLACK);

      setHighlighted(false);
    }
  }

  /**
   * Component resized.
   *
   * @param e the e
   */
  public final void componentResized(ComponentEvent e) {
    int h = getHeight() - 1;

    mP = new GeneralPath();
    mP.moveTo(OFFSET, ROUNDING);
    mP.append(new Arc2D.Float(OFFSET, 0, ROUNDING, ROUNDING, 180, -90, Arc2D.OPEN), true);
    mP.lineTo(getWidth(), 0);
    mP.lineTo(getWidth(), h);
    mP.lineTo(OFFSET + ROUNDING, h);
    mP.append(new Arc2D.Float(OFFSET, h - ROUNDING, ROUNDING, ROUNDING, 270, -90, Arc2D.OPEN), true);
    mP.closePath();

    repaint();
  }

  /**
   * Component shown.
   *
   * @param e the e
   */
  public final void componentShown(ComponentEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.ComponentListener#componentHidden(java.awt.event.
   * ComponentEvent)
   */
  @Override
  public void componentHidden(ComponentEvent arg0) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.ComponentListener#componentMoved(java.awt.event.
   * ComponentEvent)
   */
  @Override
  public void componentMoved(ComponentEvent e) {
    // TODO Auto-generated method stub

  }
}