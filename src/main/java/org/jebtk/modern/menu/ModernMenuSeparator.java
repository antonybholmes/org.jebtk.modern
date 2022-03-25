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
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

// TODO: Auto-generated Javadoc
/**
 * Provides a line separator for menus with a theme consistent with
 * ModernMenuItem.
 *
 * @author Antony Holmes
 *
 */
public class ModernMenuSeparator extends ModernIconMenuItem implements ComponentListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant HEIGHT.
   */
  private static final int HEIGHT = 1;

  /**
   * The constant MIN_SIZE.
   */
  private static final Dimension MIN_SIZE = new Dimension(1, HEIGHT);

  /**
   * The constant MAX_SIZE.
   */
  private static final Dimension MAX_SIZE = new Dimension(Short.MAX_VALUE, HEIGHT);

  /**
   * The y.
   */
  protected int y = 0;

  /**
   * Instantiates a new modern menu separator.
   */
  public ModernMenuSeparator() {
    super("Separator");

    setMinimumSize(MIN_SIZE);
    setPreferredSize(MIN_SIZE);
    setMaximumSize(MAX_SIZE);

    setEnabled(false);

    addComponentListener(this);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.menu.ModernIconMenuItem#drawForegroundAA(java.awt.
   * Graphics2D)
   */
  @Override
  public void drawForeground(Graphics2D g2) {
    g2.setColor(LINE_COLOR);

    g2.drawLine(0, y, getWidth(), y);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.ComponentListener#componentResized(java.awt.event.
   * ComponentEvent)
   */
  public void componentResized(ComponentEvent e) {
    y = getHeight() / 2;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.ComponentListener#componentHidden(java.awt.event.
   * ComponentEvent)
   */
  @Override
  public void componentHidden(ComponentEvent e) {
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

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.ComponentListener#componentShown(java.awt.event.
   * ComponentEvent)
   */
  @Override
  public void componentShown(ComponentEvent e) {
    // TODO Auto-generated method stub

  }
}