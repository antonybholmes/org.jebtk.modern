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
package org.jebtk.modern.zoom;

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.event.ChangeListener;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.text.ModernAutoSizeLabel;

/**
 * Display current zoom settings.
 *
 * @author Antony Holmes
 *
 */
public class ModernZoomLabel extends ModernAutoSizeLabel {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member model.
   */
  private ZoomModel mModel;

  /**
   * The class ZoomEvents.
   */
  private class ZoomEvents implements ChangeListener {

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.lib.event.ChangeListener#changed(org.abh.lib.event.ChangeEvent)
     */
    @Override
    public void changed(ChangeEvent e) {
      updateZoom();
    }

  }

  /**
   * Instantiates a new modern zoom label.
   *
   * @param model the model
   */
  public ModernZoomLabel(ZoomModel model) {
    super("100%");

    mModel = model;

    mModel.addChangeListener(new ZoomEvents());

    updateZoom();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.text.ModernAutoSizeLabel#setText(java.lang.String)
   */
  @Override
  public void setText(String text) {
    super.setText(text);

    UI.setSize(this, ModernWidget.TINY_SIZE);
  }

  /**
   * Update zoom.
   */
  private void updateZoom() {
    setText(Integer.toString((int) (mModel.getZoom() * 100)) + "%");
  }
}
