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
package org.jebtk.modern.status;

import java.awt.Component;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.Box;
import javax.swing.border.Border;

import org.jebtk.modern.BorderService;
import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.panel.HBox;

// TODO: Auto-generated Javadoc
/**
 * Status bar for updating users.
 * 
 * @author Antony Holmes
 *
 */
public class ModernStatusBar extends ModernComponent {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The Constant STATUS_BORDER. */
  private static final Border STATUS_BORDER = BorderService.getInstance().createBorder(1);

  /**
   * The member status label.
   */
  private final ModernStatusLabel mStatusLabel = new ModernStatusLabel(UI.STATUS_READY);

  /**
   * The member model.
   */
  private final StatusModel mModel = new StatusModel();

  /** The m L box. */
  private final Box mLBox = HBox.create();

  /** The m C box. */
  private final Box mCBox = HBox.create();

  /** The m R box. */
  private final Box mRBox = HBox.create();

  /**
   * Instantiates a new modern status bar.
   */
  public ModernStatusBar() {
    setLayout(null);

    super.add(mLBox);
    super.add(mCBox);
    super.add(mRBox);

    addLeft(createHGap());
    addLeft(mStatusLabel);

    UI.setSize(mStatusLabel, ModernWidget.EXTRA_LARGE_SIZE);
    UI.setSize(this, 200, 32);

    mModel.addStatusListener(mStatusLabel);

    addComponentListener(new ComponentAdapter() {

      @Override
      public void componentResized(ComponentEvent arg0) {
        alterLayout();
      }
    });
  }

  /**
   * Layout each box.
   */
  private void alterLayout() {
    int cw = getWidth();
    int ch = getHeight();

    mLBox.setBounds(0, 0, mLBox.getPreferredSize().width, ch);

    int w = mCBox.getPreferredSize().width;
    mCBox.setBounds((cw - w) / 2, 0, w, ch);

    w = mRBox.getPreferredSize().width;
    mRBox.setBounds(cw - w, 0, w, ch);
  }

  /*
   * @Override public final void drawBackground(Graphics2D g2) { //fill(g2,
   * Ribbon.TAB_COLOR, mRect);
   * 
   * g2.setColor(LIGHT_LINE_COLOR); g2.drawLine(0, 0, getWidth(), 0); }
   */

  /**
   * Adds the space.
   */
  public final void addSpace() {
    addLeft(Box.createHorizontalGlue());
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.Container#add(java.awt.Component)
   */
  @Override
  public Component add(Component c) {
    addLeft(c);

    return c;
  }

  /**
   * Adds the left.
   *
   * @param c the c
   */
  public void addLeft(Component c) {
    mLBox.add(c);
  }

  /**
   * Adds the center.
   *
   * @param c the c
   */
  public void addCenter(Component c) {
    mCBox.add(c);
  }

  /**
   * Adds the right.
   *
   * @param c the c
   */
  public void addRight(Component c) {
    mRBox.add(c);
  }

  /**
   * Gets the status model.
   *
   * @return the status model
   */
  public StatusModel getStatusModel() {
    return mModel;
  }
}
