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

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.event.ChangeListener;
import org.jebtk.modern.spinner.ModernCompactSpinner;

/**
 * The class ColorSpinner.
 */
public class ColorSpinner extends ModernCompactSpinner {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member model.
   */
  private ColorSelectionModel mModel;

  /**
   * The member channel.
   */
  private ColorChannel mChannel;

  /**
   * The class ClickEvents.
   */
  private class ChangeEvents implements ChangeListener {

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.common.event.ChangeListener#changed(org.abh.common.event.
     * ChangeEvent)
     */
    @Override
    public void changed(ChangeEvent e) {
      mModel.updateColor(mChannel, (int) mValue);
    }
  }

  /**
   * The class ModelEvents.
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
   * Instantiates a new color spinner.
   *
   * @param model   the model
   * @param channel the channel
   */
  public ColorSpinner(ColorSelectionModel model, ColorChannel channel) {
    super(0, 0, 127, 255);

    mModel = model;
    mChannel = channel;

    model.addChangeListener(new ModelEvents());

    addChangeListener(new ChangeEvents());
  }
}
