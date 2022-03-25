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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JComponent;
import javax.swing.JPopupMenu;

import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickEventProducer;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.event.ModernClickListeners;

// TODO: Auto-generated Javadoc
/**
 * A popup is a component that will appear in a popup window.
 * 
 * @author Antony Holmes
 *
 */
public class ModernPopup extends JPopupMenu implements ModernClickListener, ModernClickEventProducer {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member listeners.
   */
  protected ModernClickListeners mListeners = new ModernClickListeners();

  /**
   * Instantiates a new modern popup.
   */
  public ModernPopup() {
    setLayout(new BorderLayout());
    setBackground(Color.WHITE);
    setOpaque(true);
    setBorder(ModernWidget.DARK_LINE_BORDER);
  }

  /**
   * Show popup.
   *
   * @param component the component
   */
  public void showPopup(JComponent component) {
    showPopup(component, component.getInsets().left, component.getHeight());
  }

  /**
   * Show popup.
   *
   * @param component the component
   * @param offsetX   the offset x
   * @param offsetY   the offset y
   */
  public void showPopup(Component component, int offsetX, int offsetY) {
    showPopup((JComponent) component, offsetX, offsetY);
  }

  /**
   * Each popup type should override this method to determine how the custom popup
   * is placed by default.
   *
   * @param component the component
   * @param offsetX   the offset x
   * @param offsetY   the offset y
   */
  public void showPopup(JComponent component, int offsetX, int offsetY) {
    // System.err.println("show popup offset " + component.getInsets().left + "
    // " +
    // component.getHeight());

    showPopup(component, new Point(offsetX, offsetY));
  }

  /**
   * Show popup.
   *
   * @param component the component
   * @param pt        the pt
   */
  public void showPopup(JComponent component, Point pt) {
    show(component, pt.x, pt.y);

    // ModernPopupWindow window = createPopupWindow(component);

    // showPopup(window, pt);
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
   */
  @Override
  public void paintComponent(Graphics g) {
    g.setColor(getBackground());
    g.fillRect(0, 0, getWidth(), getHeight());
  }

  /**
   * Clear.
   */
  public void clear() {
    removeAll();
    revalidate();
    repaint();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  @Override
  public void clicked(ModernClickEvent e) {
    setVisible(false);

    fireClicked(e);
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
}
