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
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

import javax.swing.Box;
import javax.swing.BoxLayout;

import org.jebtk.core.ColorUtils;
import org.jebtk.modern.panel.ModernPanel;

/**
 * Used as a glass pane to present messages to the user in the current window
 * rather than a popup dialog.
 * 
 * @author Antony Holmes
 *
 */
public class FrostedGlassPane extends ModernPanel {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant GLASS_COLOR.
   */
  public static final Color GLASS_COLOR = ColorUtils.getTransparentColor80(Color.BLACK);

  /**
   * Instantiates a new frosted glass pane.
   */
  public FrostedGlassPane() {
    super(GLASS_COLOR);

    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

    // capture all mouse events
    addMouseListener(new MouseAdapter() {
    });
    addMouseMotionListener(new MouseAdapter() {
    });
    addMouseWheelListener(new MouseAdapter() {
    });
    addKeyListener(new KeyAdapter() {
    });
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.Container#add(java.awt.Component)
   */
  @Override
  public Component add(Component c) {
    removeAll();

    super.add(Box.createVerticalGlue());
    super.add(c);
    super.add(Box.createVerticalGlue());

    revalidate();
    repaint();

    return c;
  }
}
