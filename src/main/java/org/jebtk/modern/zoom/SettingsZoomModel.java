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
import org.jebtk.core.settings.SettingsService;

/**
 * Configurable zoom model that loads and saves its settings to the Settings
 * system.
 * 
 * @author Antony Holmes
 *
 */
public class SettingsZoomModel extends ZoomModel {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member zoom setting.
   */
  private String mZoomSetting;

  /**
   * The member max setting.
   */
  private String mMaxSetting;

  /**
   * The member min setting.
   */
  private String mMinSetting;

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
      updateSettings();
    }
  }

  /**
   * Create a new settings zoom model.
   * 
   * @param zoomSetting The setting storing the current zoom.
   * @param minSetting  The setting storing the minimum allowed zoom.
   * @param maxSetting  The setting storing the maximum allowed zoom.
   */
  public SettingsZoomModel(String zoomSetting, String minSetting, String maxSetting) {
    mZoomSetting = zoomSetting;
    mMaxSetting = maxSetting;
    mMinSetting = minSetting;

    addChangeListener(new ZoomEvents());

    // Load the zoom settings from settings.
    setZoom(SettingsService.getInstance().getDouble(mZoomSetting));
    setMinZoom(SettingsService.getInstance().getDouble(mMinSetting));
    setMaxZoom(SettingsService.getInstance().getDouble(mMaxSetting));
  }

  /**
   * Write the settings.
   */
  private void updateSettings() {
    SettingsService.getInstance().update(mZoomSetting, mZoom);
    SettingsService.getInstance().update(mMinSetting, mMinZoom);
    SettingsService.getInstance().update(mMaxSetting, mMaxZoom);
  }

}
