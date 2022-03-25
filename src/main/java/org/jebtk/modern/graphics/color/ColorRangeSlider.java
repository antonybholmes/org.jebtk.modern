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
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import org.jebtk.core.ColorValue;
import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.event.ChangeListener;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.slider.ContinuousMacOrbSlider;

/**
 * The class ColorRangePanel.
 */
public class ColorRangeSlider extends ContinuousMacOrbSlider {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member channel.
   */
  private ColorChannel mChannel = ColorChannel.RED;

  /**
   * The member color value.
   */
  private ColorValue mColorValue = new ColorValue();

  /**
   * The member size.
   */
  private static final int WIDTH = 256;

  /**
   * The member model.
   */
  private ColorSelectionModel mModel;

  /** The m paint. */
  private GradientPaint mPaint;

  /**
   * The Class ModelEvents.
   */
  private class ModelEvents implements ChangeListener {

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
     * modern .event.ModernClickEvent)
     */
    @Override
    public void changed(ChangeEvent e) {
      switch (mChannel) {
      case RED:
        updateValue(mModel.getNewColor().mRed);
        break;
      case GREEN:
        updateValue(mModel.getNewColor().mGreen);
        break;
      case BLUE:
        updateValue(mModel.getNewColor().mBlue);
        break;
      default:
        updateValue(mModel.getNewColor().mAlpha);
        break;
      }
    }
  }

  /**
   * Instantiates a new color range panel.
   *
   * @param model   the model
   * @param channel the channel
   */
  public ColorRangeSlider(ColorSelectionModel model, ColorChannel channel) {
    super(0, 0, 127, 255);

    mModel = model;
    mChannel = channel;

    mModel.addChangeListener(new ModelEvents());

    UI.setSize(this, 256, ModernWidget.WIDGET_HEIGHT);

    addChangeListener(new ChangeListener() {

      @Override
      public void changed(ChangeEvent e) {
        int c = getIntValue(); // (int)(e.getX() * RATIO);

        c = Math.max(0, Math.min(c, 255));

        mModel.updateColor(mChannel, c);
      }
    });

    addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        int x1 = getInsets().left + mSliderRadius;
        int x2 = getWidth() - getInsets().right - mSliderRadius;

        switch (mChannel) {
        case RED:
          mPaint = new GradientPaint(x1, 0, new Color(0, mColorValue.mGreen, mColorValue.mBlue), x2, 0,
              new Color(255, mColorValue.mGreen, mColorValue.mBlue));
          break;
        case GREEN:
          mPaint = new GradientPaint(x1, 0, new Color(mColorValue.mRed, 0, mColorValue.mBlue), x2, 0,
              new Color(mColorValue.mRed, 255, mColorValue.mBlue));
          break;
        default:
          mPaint = new GradientPaint(x1, 0, new Color(mColorValue.mRed, mColorValue.mGreen, 0), x2, 0,
              new Color(mColorValue.mRed, mColorValue.mGreen, 255));
          break;
        }
      }
    });

    setBarHeight(4);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.slider.MacOrbSlider#drawBar(java.awt.Graphics2D, int)
   */
  @Override
  protected void drawBar(Graphics2D g2, int p) {
    drawBarBase(g2, p);

    int x = getInsets().left + mSliderRadius;
    int y = (getHeight() - mBarHeight) / 2;

    g2.setPaint(mPaint);

    g2.fillRoundRect(x, y, p - x + mSliderRadius, mBarHeight, mBarHeight, mBarHeight);
  }
}
