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
package org.jebtk.modern.graphics.color;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import org.jebtk.core.ColorValue;
import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.event.ChangeListener;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;

/**
 * The class ColorMatrixPanel.
 */
public class ColorMatrixPanel extends ModernWidget implements MouseMotionListener, MouseListener, ChangeListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The channel.
   */
  private ColorChannel mChannel = ColorChannel.RED;

  /**
   * The v.
   */
  private int v = 0;

  /**
   * The size.
   */
  private int size = 128;

  /**
   * The ratio.
   */
  private double ratio = 256.0 / (double) size;

  /**
   * The member model.
   */
  private ColorSelectionModel mModel;

  /**
   * Instantiates a new color matrix panel.
   *
   * @param model the model
   */
  public ColorMatrixPanel(ColorSelectionModel model) {
    mModel = model;

    model.addChangeListener(this);
    addMouseMotionListener(this);
    addMouseListener(this);

    UI.setSize(this, new Dimension(size, size));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawBackground(java.awt.Graphics2D)
   */
  @Override
  public void drawBackground(Graphics2D g2) {
    Color color;
    int hc;
    int vc;

    for (int i = 0; i < size; ++i) {
      for (int j = 0; j < size; ++j) {
        hc = (int) (i * ratio);
        vc = (int) ((size - j - 1) * ratio);

        switch (mChannel) {
        case RED:
          color = new Color(v, vc, hc);
          break;
        case GREEN:
          color = new Color(vc, v, hc);
          break;
        default:
          color = new Color(vc, hc, v);
          break;
        }

        g2.setColor(color);
        g2.drawRect(i, j, 1, 1);
      }
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    g2.setColor(Color.BLACK);

    int i;

    switch (mChannel) {
    case RED:
      // green y
      i = (int) ((255 - mModel.getNewColor().mGreen) / ratio);

      g2.drawLine(0, i, getWidth(), i);

      // blue x
      i = (int) (mModel.getNewColor().mBlue / ratio);

      g2.drawLine(i, 0, i, getHeight());
      break;
    case GREEN:
      // green y
      i = (int) ((255 - mModel.getNewColor().mRed) / ratio);

      g2.drawLine(0, i, getWidth(), i);

      // blue x
      i = (int) (mModel.getNewColor().mBlue / ratio);

      g2.drawLine(i, 0, i, getHeight());
      break;
    default:
      // green y
      i = (int) ((255 - mModel.getNewColor().mRed) / ratio);

      g2.drawLine(0, i, getWidth(), i);

      // blue x
      i = (int) (mModel.getNewColor().mGreen / ratio);

      g2.drawLine(i, 0, i, getHeight());
      break;
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  @Override
  public void changed(ChangeEvent e) {
    mChannel = mModel.getChannel();

    switch (mChannel) {
    case RED:
      v = mModel.getNewColor().mRed;
      break;
    case GREEN:
      v = mModel.getNewColor().mGreen;
      break;
    default:
      v = mModel.getNewColor().mBlue;
      break;
    }

    repaint();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
   */
  @Override
  public void mousePressed(MouseEvent e) {
    changeColor(e);
  }

  /**
   * Change color.
   *
   * @param e the e
   */
  private void changeColor(MouseEvent e) {
    ColorValue color;

    int c1 = (int) ((size - e.getY() - 1) * ratio);

    int c2 = (int) ((e.getX() - 1) * ratio);

    c1 = Math.max(0, Math.min(c1, 255));
    c2 = Math.max(0, Math.min(c2, 255));

    switch (mChannel) {
    case RED:
      color = new ColorValue(v, c1, c2);
      break;
    case GREEN:
      color = new ColorValue(c1, v, c2);
      break;
    default:
      color = new ColorValue(c1, c2, v);
      break;
    }

    mModel.setNewColor(color);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseDragged(MouseEvent e) {
    changeColor(e);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseMoved(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseClicked(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseExited(MouseEvent e) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
   */
  @Override
  public void mouseReleased(MouseEvent e) {
    // TODO Auto-generated method stub

  }
}
