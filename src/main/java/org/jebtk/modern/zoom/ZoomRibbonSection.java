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

import javax.swing.Box;

import org.jebtk.modern.AssetService;
import org.jebtk.modern.UI;
import org.jebtk.modern.button.ModernButtonWidget;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.graphics.icons.SearchVectorIcon;
import org.jebtk.modern.ribbon.RibbonLargeButton;
import org.jebtk.modern.ribbon.RibbonSection;
import org.jebtk.modern.ribbon.RibbonStripContainer;
import org.jebtk.modern.window.ModernRibbonWindow;
import org.jebtk.modern.window.ModernWindow;

/**
 * Standardized ribbon menu section for providing basic cut, copy and paste
 * functionality to the currently highlighted control that supports clipboard
 * operations.
 *
 * @author Antony Holmes
 *
 */
public class ZoomRibbonSection extends RibbonSection implements Zoom, ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member zoom button.
   */
  private final ModernButtonWidget mZoomButton = new RibbonLargeButton(
      AssetService.getInstance().loadIcon(SearchVectorIcon.class, 24)); // Resources.getInstance().loadIcon("zoom",
  // Resources.ICON_SIZE_32));

  /**
   * The member zoom panel.
   */
  private final ZoomRibbonPanel mZoomPanel;

  /**
   * The member parent.
   */
  private final ModernWindow mParent;

  /**
   * The member model.
   */
  private final ZoomModel mModel;

  /**
   * The member zoom slider.
   */
  //private final ModernZoomSlider mZoomSlider;

  /**
   * Instantiates a new zoom ribbon section2.
   *
   * @param parent the parent
   * @param model  the model
   */
  public ZoomRibbonSection(ModernRibbonWindow parent, ZoomModel model) {
    super(parent.getRibbon(), "Zoom");

    mParent = parent;
    mModel = model;

    mZoomPanel = new ZoomRibbonPanel(model);
    //mZoomSlider = new ModernZoomSlider(model);

    add(mZoomButton);
    add(createHGap());

    Box box = new RibbonStripContainer();

    //box.add(mZoomSlider);
    //box.add(createHGap());
    box.add(mZoomPanel);

    add(box);

    mZoomButton.setToolTip("Zoom", "Set the zoom level.");
    mZoomButton.addClickListener(this);
  }

  /**
   * Enabled or disable all the controls on the panel.
   *
   * @param enabled the enabled
   */
  public final void enableControls(boolean enabled) {
    mZoomButton.setEnabled(enabled);
    mZoomPanel.enableControls(enabled);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.zoom.Zoom#getZoom()
   */
  @Override
  public double getZoom() {
    return mZoomPanel.getZoom();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.zoom.Zoom#setZoom(double)
   */
  @Override
  public void setZoom(double zoom) {
    mZoomPanel.setZoom(zoom);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.zoom.Zoom#getMinZoom()
   */
  @Override
  public double getMinZoom() {
    return mZoomPanel.getMinZoom();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.zoom.Zoom#setMinZoom(double)
   */
  @Override
  public void setMinZoom(double zoom) {
    mZoomPanel.setMinZoom(zoom);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.zoom.Zoom#getMaxZoom()
   */
  @Override
  public double getMaxZoom() {
    return mZoomPanel.getMaxZoom();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.zoom.Zoom#setMaxZoom(double)
   */
  @Override
  public void setMaxZoom(double zoom) {
    mZoomPanel.setMaxZoom(zoom);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  public void clicked(ModernClickEvent e) {
    if (e.getSource().equals(mZoomButton)) {
      ZoomDialog dialog = new ZoomDialog(mParent, mModel);

      UI.centerWindowToScreen(dialog);

      dialog.setVisible(true);
    }
  }
}
