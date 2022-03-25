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

import org.jebtk.core.event.ChangeListeners;

/**
 * Provides a zoom model for zoom components to communicate through. Zoom is
 * expressed as a percentage 0 = 0%, 1 = 100%.
 * 
 * @author Antony Holmes
 *
 */
public class ZoomModel extends ChangeListeners implements Zoom {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant ZOOM_CHANGED_EVENT.
   */
  public static final String ZOOM_CHANGED_EVENT = "zoom_changed";

  /**
   * The constant ZOOM_STEP_CHANGED_EVENT.
   */
  public static final String ZOOM_STEP_CHANGED_EVENT = "zoom_step_changed";

  /**
   * The member zoom.
   */
  protected double mZoom = 1;

  /**
   * The member min zoom.
   */
  // protected double mZoomStep = 0.25;
  protected double mMinZoom = 0.1;

  /**
   * The member max zoom.
   */
  protected double mMaxZoom = 4;

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.zoom.Zoom#getZoom()
   */
  public double getZoom() {
    return mZoom;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.zoom.Zoom#setZoom(double)
   */
  public void setZoom(double zoom) {
    mZoom = zoom;

    fireChanged();
  }

  // public double getZoomStep() {
  // return mZoomStep;
  // }

  /**
   * Set the zoom step/increment size and notify listeners that the model has
   * changed.
   *
   * @return the min zoom
   */
  // public void setZoomStep(double step) {
  // mZoomStep = step;

  // fireZoomChanged(new ChangeEvent(this, ZOOM_STEP_CHANGED_EVENT));
  // }

  public double getMinZoom() {
    return mMinZoom;
  }

  /**
   * Set the minimum zoom level and notify any listeners of changes.
   *
   * @param zoom the new min zoom
   */
  public void setMinZoom(double zoom) {
    mMinZoom = zoom;

    fireChanged();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.zoom.Zoom#getMaxZoom()
   */
  public double getMaxZoom() {
    return mMaxZoom;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.zoom.Zoom#setMaxZoom(double)
   */
  public void setMaxZoom(double zoom) {
    mMaxZoom = zoom;

    fireChanged();
  }
}
