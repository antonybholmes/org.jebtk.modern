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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.event.ChangeListener;
import org.jebtk.modern.AssetService;
import org.jebtk.modern.UI;
import org.jebtk.modern.button.ModernButton;
import org.jebtk.modern.button.ModernCircleButton;
import org.jebtk.modern.graphics.icons.MinusVectorIcon;
import org.jebtk.modern.graphics.icons.PlusVectorIcon;
import org.jebtk.modern.panel.HBox;
import org.jebtk.modern.slider.ContinuousMacOrbSlider;
import org.jebtk.modern.slider.Slider;

/**
 * Zoom slider for status bar.
 *
 * @author Antony Holmes
 */
public class ModernZoomSlider extends HBox implements Zoom {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member slider.
   */
  private final Slider mSlider = new ContinuousMacOrbSlider(1, 0.1, 1, 5); // new
  // MacOrbSlider(1,
  // LEVELS,
  // MID_LEVEL);

  /**
   * The constant TIMER_DELAY.
   */
  private static final int TIMER_DELAY = 50;

  /**
   * The member model.
   */
  private final ZoomModel mModel;

  /**
   * The member min zoom.
   */
  // private double mMinZoom;

  /**
   * The member zoom in button.
   */
  private final ModernButton mZoomInButton = (ModernButton) new ModernCircleButton(
      AssetService.getInstance().loadIcon(PlusVectorIcon.class, 16), "Zoom In", "Zoom in.");

  /**
   * The member zoom out button.
   */
  private final ModernButton mZoomOutButton = (ModernButton) new ModernCircleButton(
      AssetService.getInstance().loadIcon(MinusVectorIcon.class, 16), "Zoom Out", "Zoom out.");

  // private double[] mZooms;

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
   * The class ZoomInEvents.
   */
  private class ZoomInEvents extends MouseAdapter implements ActionListener {

    /**
     * The member timer.
     */
    private final Timer mTimer;

    /**
     * Instantiates a new zoom in events.
     */
    public ZoomInEvents() {
      mTimer = new Timer(TIMER_DELAY, this);
      mTimer.setInitialDelay(0);
      mTimer.setRepeats(true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(MouseEvent e) {
      mTimer.start();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseReleased(MouseEvent e) {
      mTimer.stop();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      mSlider.increment();
    }
  }

  /**
   * The class ZoomOutEvents.
   */
  private class ZoomOutEvents extends MouseAdapter implements ActionListener {

    /**
     * The member timer.
     */
    private final Timer mTimer;

    /**
     * Instantiates a new zoom out events.
     */
    public ZoomOutEvents() {
      mTimer = new Timer(TIMER_DELAY, this);
      mTimer.setInitialDelay(0);
      mTimer.setRepeats(true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(MouseEvent e) {
      mTimer.start();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseReleased(MouseEvent e) {
      mTimer.stop();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      mSlider.decrement();
    }
  }

  /**
   * Instantiates a new modern zoom slider2.
   *
   * @param model the model
   */
  public ModernZoomSlider(ZoomModel model) {
    mModel = model;

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    UI.setSize(mZoomOutButton, ModernButton.SMALL_BUTTON_SIZE);
    add(mZoomOutButton);
    // add(UI.createHGap(5));
    add(mSlider);
    // add(UI.createHGap(5));
    UI.setSize(mZoomInButton, ModernButton.SMALL_BUTTON_SIZE);
    add(mZoomInButton);

    mZoomOutButton.addMouseListener(new ZoomOutEvents());
    mZoomInButton.addMouseListener(new ZoomInEvents());

    mModel.addChangeListener(new ZoomEvents());

    mSlider.addChangeListener((ChangeEvent e) -> {
      mModel.setZoom(mSlider.getValue()); // getZ((int)mSlider.getValue()));
    });

    updateZoom();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.zoom.Zoom#setZoom(double)
   */
  @Override
  public void setZoom(double zoom) {
    mModel.setZoom(zoom);
  }

  /**
   * Update zoom.
   */
  private void updateZoom() {
    mSlider.updateValue(mModel.getZoom()); // getP(mModel.getZoom()));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.zoom.Zoom#getZoom()
   */
  @Override
  public double getZoom() {
    return mSlider.getValue();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.zoom.Zoom#getMinZoom()
   */
  @Override
  public double getMinZoom() {
    return mSlider.getMinValue();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.zoom.Zoom#setMinZoom(double)
   */
  @Override
  public void setMinZoom(double zoom) {

    // update();

    // mSlider.setMinValue(getP(zoom));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.zoom.Zoom#getMaxZoom()
   */
  @Override
  public double getMaxZoom() {
    return mSlider.getMaxValue();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.zoom.Zoom#setMaxZoom(double)
   */
  @Override
  public void setMaxZoom(double zoom) {

    // update();

    // mSlider.setMaxValue(getP(zoom));
  }

  /*
   * private void update() { mPInc = (mMaxZoom - 1) / HALF_LEVEL; mPDec = (1 -
   * mMinZoom) / HALF_LEVEL;
   * 
   * mZooms = new double[LEVELS]; //mPMap = new HashMap<Double,>
   * 
   * mZooms[0] = mMinZoom;
   * 
   * for (int i = 1; i < HALF_LEVEL; ++i) { mZooms[i] = mZooms[i - 1] + mPDec; }
   * 
   * mZooms[HALF_LEVEL] = 1;
   * 
   * mZooms[mZooms.length - 1] = mMaxZoom;
   * 
   * for (int i = mZooms.length - 2; i > HALF_LEVEL; --i) { mZooms[i] = mZooms[i +
   * 1] - mPInc; } }
   */

  /*
   * private double getZ(int p) { return mZooms[p]; }
   */

  /*
   * private int getP(double zoom) { int p;
   * 
   * if (zoom > 1.0) { p = MID_LEVEL + (int)Math.round((zoom - 1.0) / mPInc);
   * //(int)zoom + MID_LEVEL - 1; } else if (zoom < 1.0) { p = MID_LEVEL -
   * (int)Math.round((1.0 - zoom) / mPDec); //MID_LEVEL - (int)(1 / zoom) + 1; }
   * else { p = MID_LEVEL; }
   * 
   * return p; }
   */
}
