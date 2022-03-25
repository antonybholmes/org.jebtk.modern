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
package org.jebtk.modern.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.Box;
import javax.swing.BoxLayout;

import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.button.ModernClickWidget;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.graphics.ImageUtils;
import org.jebtk.modern.panel.HBox;
import org.jebtk.modern.ribbon.Ribbon;

/**
 * The class ModernTitleBar.
 */
public class ModernTitleBar extends ModernClickWidget implements ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member title.
   */
  private String mTitle = "Title";

  /**
   * The member left buttons box.
   */
  private Box mLeftBox = HBox.create();

  /** The m right buttons box. */
  private Box mRightButtonsBox = HBox.create();

  /** The m left box. */
  private Box _mLeftBox = HBox.create();

  /** The m right buttons box. */
  private Box _mRightButtonsBox = HBox.create();

  /** The Constant SIZE. */
  private static final Dimension SIZE = new Dimension(Short.MAX_VALUE, Ribbon.TAB_HEIGHT);

  /**
   * Instantiates a new modern title bar.
   */
  public ModernTitleBar() {
    setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

    add(_mLeftBox);
    add(mLeftBox);
    add(Box.createHorizontalGlue());
    add(mRightButtonsBox);
    add(_mRightButtonsBox);

    setFont(BOLD_FONT);
    setForeground(Color.WHITE);
  }

  /**
   * Sets the title.
   *
   * @param title the new title
   */
  public void setTitle(String title) {
    mTitle = title;

    repaint();
  }

  /**
   * Adds the left.
   *
   * @param c the c
   */
  public void addLeft(ModernClickWidget c) {
    mLeftBox.add(c);

    c.addClickListener(this);
  }

  /**
   * Adds the left.
   *
   * @param c the c
   */
  protected void _addLeft(ModernClickWidget c) {
    _mLeftBox.add(c);

    c.addClickListener(this);
  }

  /**
   * Adds the right.
   *
   * @param c the c
   */
  public void addRight(ModernClickWidget c) {
    mRightButtonsBox.add(c);

    c.addClickListener(this);
  }

  /**
   * Adds the right.
   *
   * @param c the c
   */
  public void _addRight(ModernClickWidget c) {
    _mRightButtonsBox.add(c);

    c.addClickListener(this);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.widget.ModernWidget#drawBackground(java.awt.Graphics2D)
   */
  @Override
  public void drawBackground(Graphics2D g2) {
    fill(g2, Ribbon.BAR_BACKGROUND);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.panel.ModernPanel#drawForegroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawForeground(Graphics2D g2) {
    Point p = ModernWidget.getStringCenterPlotCoordinates(g2, getRect(), mTitle);

    Graphics2D g2Temp = ImageUtils.createAATextGraphics(g2);

    try {
      g2Temp.setColor(getForeground());
      g2Temp.drawString(mTitle, p.x, p.y);
    } finally {
      g2Temp.dispose();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  @Override
  public void clicked(ModernClickEvent e) {
    System.err.println("e " + e.getMessage());

    fireClicked(e);
  }

  /**
   * Add a quick access button.
   *
   * @param button the button
   */
  public void addQuickAccessButton(ModernClickWidget button) {
    addLeft(button);

    // button.addClickListener(new ModernClickListener(){
    // @Override
    // public final void clicked(ModernClickEvent e) {
    // fireClicked(new ModernClickEvent(this, e.getMessage()));
    // }});
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.JComponent#getPreferredSize()
   */
  public Dimension getPreferredSize() {
    return SIZE;
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.JComponent#getMinimumSize()
   */
  public Dimension getMinimumSize() {
    return SIZE;
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.JComponent#getMaximumSize()
   */
  public Dimension getMaximumSize() {
    return SIZE;
  }
}
