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

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseWheelEvent;

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.geom.IntDim;
import org.jebtk.core.geom.IntPos2D;
import org.jebtk.modern.MouseUtils;
import org.jebtk.modern.graphics.CanvasMouseEvent;
import org.jebtk.modern.graphics.DrawingContext;
import org.jebtk.modern.graphics.ImageUtils;
import org.jebtk.modern.graphics.ModernCanvas;

/**
 * Drawing panel that allows zooming.
 * 
 * @author Antony Holmes
 *
 */
public class ZoomCanvas extends ModernCanvas {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  private static final double WHEEL_ZOOM = 0.1;

  /**
   * The member zoom model.
   */
  private ZoomModel mZoomModel;

  /**
   * The member size.
   */
  // private IntDim mSize;

  // private IntRect mviewRect = IntRect.ZERO_RECT;

  /** The m zoom size. */
  private Dimension mZoomSize;

  public ZoomCanvas() {
    this(1);
  }

  /**
   * Instantiates a new zoom canvas.
   *
   * @param canvas the canvas
   * @param zoom   the zoom
   */
  public ZoomCanvas(double zoom) {
    this(new ZoomModel(), zoom);
  }

  /**
   * Instantiates a new zoom canvas.
   *
   * @param canvas the canvas
   * @param model  the model
   */
  public ZoomCanvas(ZoomModel model) {
    this(model, 1);
  }

  /**
   * Instantiates a new zoom canvas.
   *
   * @param canvas the canvas
   * @param model  the model
   * @param zoom   the zoom
   */
  public ZoomCanvas(ZoomModel model, double zoom) {
    setZoomModel(model);

    setZoom(zoom);

    init();
  }

  public ZoomCanvas(Dimension size) {
    this();

    setSize(size);
  }

  private void init() {
    /*
     * addCanvasMouseWheelListener(new CanvasMouseWheelListener() {
     * 
     * @Override public void canvasMouseWheelMoved(CanvasMouseWheelEvent e) { if
     * (MouseUtils.hasCtrlMod(e)) { if (mZoomModel != null) { mZoomModel.setZoom(
     * mZoomModel.getZoom() + WHEEL_ZOOM * e.getWheelRotation()); } }
     * 
     * } });
     */

    addMouseWheelListener((MouseWheelEvent e) -> {
      if (MouseUtils.ctrlPressed(e)) {
        wheelZoom(e);
      }
    });
  }

  private void wheelZoom(MouseWheelEvent e) {
    mZoomModel.setZoom(mZoomModel.getZoom() + WHEEL_ZOOM * e.getWheelRotation());
  }

  /**
   * Returns the zoom model associated with the canvas.
   *
   * @return the zoom model
   */
  public ZoomModel getZoomModel() {
    return mZoomModel;
  }

  /**
   * Allows the panel to respond to a zoom model, for example when the user
   * changes a zoom slider.
   *
   * @param model the new zoom model
   */
  public void setZoomModel(ZoomModel model) {
    // if (model != null) {
    // model.removeChangeListener(this);
    // }

    // if (mZoomModel != null) {
    // mZoomModel.removeChangeListener(this);
    // }

    mZoomModel = model;

    model.addChangeListener((ChangeEvent e) -> {
      zoomChanged();
    });

    // Notify any listeners to update
    // model.fireChanged();
  }

  /*
   * @Override public final void drawTranslatedCanvas(Graphics2D g2) {
   * System.err.println("zoom vis " + getVisibleRect());
   * 
   * Graphics2D g2Temp = ImageUtils.clone(g2);
   * 
   * try { //AffineTransform tx = new AffineTransform(); //
   * //tx.concatenate(g2.getTransform() ); //tx.scale(mZoomModel.getZoom(),
   * mZoomModel.getZoom());
   * 
   * double z = mZoomModel.getZoom();
   * 
   * g2Temp.scale(z , z);
   * 
   * mCanvas.drawTranslatedCanvas(g2Temp); } finally { g2Temp.dispose(); } }
   */

  /**
   * Gets the zoom.
   *
   * @return the zoom
   */
  public double getZoom() {
    return mZoomModel.getZoom();
  }

  /**
   * Sets the zoom.
   *
   * @param zoom the new zoom
   */
  public void setZoom(double zoom) {
    mZoomModel.setZoom(zoom);
  }

  /**
   * Zoom changed.
   */
  public void zoomChanged() {
    setPreferredSize();

    // fireCanvasChanged();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.graphics.ContainerCanvas#canvasChanged(org.abh.lib.
   * event.ChangeEvent)
   */
  
  
  /*
   * @Override public void canvasChanged(ChangeEvent e) { // since the underlying
   * canvas changed, adapt to it
   * 
   * System.err.println("zoom hmm");
   * 
   * setCanvasSize(); }
   */

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvas#setCanvasSize()
   */
  /*
   * @Override public void setCanvasSize() { double z = 1;
   * 
   * if (mZoomModel != null) { z = mZoomModel.getZoom(); }
   * 
   * updateIntViewRect();
   * 
   * //Dimension size = mCanvas.getPreferredSize();
   * 
   * mZoomSize = new Dimension((int)Math.round(mPs.width * z),
   * (int)Math.round(mPs.height * z));
   * 
   * //setPreferredSize(mZoomSize);
   * 
   * fireCanvasResized(); }
   */

  @Override
  public void setPreferredSize(Dimension d) {
    mZoomSize = zoom(d);

    super.setPreferredSize(mZoomSize);

    // Update to the non-zoomed version
    mAbsPrefSize = IntDim.create(d);

    // fireCanvasChanged();
  }

  public Dimension zoom(Dimension d) {
    double z = 1;

    if (mZoomModel != null) {
      z = mZoomModel.getZoom();
    }

    return new Dimension((int) Math.round(d.width * z), (int) Math.round(d.height * z));
  }

  /**
   * Inline zoom function where d will be modified to reflect the zoomed
   * dimensions.
   * 
   * @param d A dimension that will be updated.
   */
  public void zoom2(Dimension d) {
    double z = 1;

    if (mZoomModel != null) {
      z = mZoomModel.getZoom();
    }

    d.width = (int) Math.round(d.width * z);
    d.height = (int) Math.round(d.height * z);
  }

  public Dimension zoom(IntDim d) {
    double z = 1;

    if (mZoomModel != null) {
      z = mZoomModel.getZoom();
    }

    return new Dimension((int) Math.round(d.getW() * z), (int) Math.round(d.getH() * z));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.graphics.ContainerCanvas#getPreferredSize()
   */
  // @Override
  // public Dimension getPreferredSize() {
  // return mZoomSize;
  // }

  /*
   * private void updateIntViewRect() { double z = getZoom(); IntRect rect =
   * mviewRect; //getViewRectangle();
   * 
   * IntPos2D zOffset = IntPos2D.create(Math.round((rect.getX()) / z),
   * Math.round((rect.getY()) / z));
   * 
   * //IntDim size = mCanvas.getCanvasSize();
   * 
   * // As we zoom out, the view rectangle of the zoom canvas represents // a much
   * larger area on the original canvas
   * 
   * IntDim zSize = IntDim.create(Math.round(rect.getW() / z),
   * Math.round(rect.getH() / z));
   * 
   * updateViewRectangle(zOffset, zSize); }
   */

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernComponent#setSize(java.awt.Dimension)
   */
  // @Override
  // public void setSize(Dimension size) {
  // super.setSize(size);
  //
  // updateIntViewRect();
  // }

  /**
   * Scale a possibly scaled pixel position back to what it should be in a zoom
   * level = 1 space.
   * 
   * @param p
   * @return
   */
  public double iscale(int p) {
    return p / getZoomModel().getZoom();
  }

  /**
   * Scale a coordinate to the current zoom.
   * 
   * @param p
   * @return
   */
  public double scale(int p) {
    return p * getZoomModel().getZoom();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.graphics.ModernCanvas#translateCoordinate(org.abh.common.
   * ui .graphics.CanvasMouseEvent)
   */
  @Override
  public IntPos2D translateCoordinate(CanvasMouseEvent e) {
    return translateCoordinate(e.getX(), e.getY());
  }

  /**
   * Translate the coordinate allowing for the zoom.
   *
   * @param x the x
   * @param y the y
   * @return the point
   */
  @Override
  public IntPos2D translateCoordinate(int x, int y) {
    // IntPos2D p = super.translateCoordinate(x, y);

    double z = getZoom();

    IntPos2D newP = IntPos2D.create((int) Math.round(x / z), (int) Math.round(y / z));

    return newP;
  }

  @Override
  public void zoomCanvas(Graphics2D g2, DrawingContext context) {
    if (context == DrawingContext.UI) {
      Graphics2D g2Temp = ImageUtils.clone(g2);

      try {
        // AffineTransform tx = new AffineTransform(); //
        // tx.concatenate(g2.getTransform() );
        // tx.scale(mZoomModel.getZoom(), mZoomModel.getZoom());

        double z = mZoomModel.getZoom();

        g2Temp.scale(z, z);

        // mCanvas.drawTranslatedCanvas(g2Temp);

        drawCanvas(g2Temp, context);

      } finally {
        g2Temp.dispose();
      }
    } else {
      drawCanvas(g2, context);
    }
  }
}
