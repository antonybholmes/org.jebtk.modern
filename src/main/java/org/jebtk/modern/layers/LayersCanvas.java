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
package org.jebtk.modern.layers;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import org.jebtk.modern.graphics.DrawingContext;
import org.jebtk.modern.graphics.ModernCanvas;

/**
 * A layer panel allows multiple graphical layers to be combined to form a
 * composite image. This makes it easier to generate complex plots.
 * 
 * @author Antony Holmes
 *
 */
public class LayersCanvas extends ModernCanvas {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The layer model.
   */
  private LayerModel mLayerModel = null;

  /**
   * The layers.
   */
  private List<LayerCanvas> layers = new ArrayList<LayerCanvas>();

  /**
   * Instantiates a new layers canvas.
   */
  public LayersCanvas() {
    this(new LayerModel());
  }

  /**
   * Instantiates a new layers canvas.
   *
   * @param model the model
   */
  public LayersCanvas(LayerModel model) {
    setLayerModel(model);

    // addCanvasListener(new CanvasEvents());
  }

  /**
   * Returns the layer model associated with this layer panel.
   *
   * @return the layer model
   */
  public LayerModel getLayerModel() {
    return mLayerModel;
  }

  /**
   * Sets the layer model.
   *
   * @param layerModel the new layer model
   */
  public void setLayerModel(LayerModel layerModel) {
    mLayerModel = layerModel;
  }

  /**
   * Adds the layer.
   *
   * @param layer the layer
   */
  public void addLayer(LayerCanvas layer) {
    mLayerModel.addLayer(layer.getName());

    layers.add(layer);

    setSize();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.graphics.ModernCanvas#drawCanvasForeground(java.awt.
   * Graphics2D)
   */
  @Override
  public void rasterCanvas(Graphics2D g2, DrawingContext context) {
    for (LayerCanvas c : layers) {
      if (!mLayerModel.isVisible(c.getName())) {
        continue;
      }

      c.rasterCanvas(g2, context);
    }
  }

  /*
   * private void setup() { // if the parent changes, update the children
   * 
   * for (ModernCanvas c : layers) { c.setCanvasSize(getCanvasSize()); }
   * 
   * repaint(); }
   */

  /**
   * Sets the size.
   */
  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.graphics.ModernCanvas#getCanvasSize()
   */
  private void setSize() {
    // Return the size of the largest layer

    int w = 0;
    int h = 0;

    for (ModernCanvas c : layers) {
      w = Math.max(w, c.getPreferredSize().width); // c.getCanvasSize().getW());
      h = Math.max(h, c.getPreferredSize().height); // c.getCanvasSize().getH());
    }

    setCanvasSize(w, h);
  }
}
