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

import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.swing.BoxLayout;

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.button.ModernCheckBox;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.graphics.FrameCanvas;
import org.jebtk.modern.graphics.ImageCanvas;
import org.jebtk.modern.graphics.ModernCanvas;
import org.jebtk.modern.text.ModernAutoSizeLabel;
import org.jebtk.modern.theme.ThemeService;

/**
 * The class ModernPreviewLayerPanel.
 */
public class ModernPreviewLayerPanel extends ModernWidget implements ModernClickListener, LayerEventListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The size.
   */
  private int size = 48;

  /**
   * The check visible.
   */
  private ModernCheckBox checkVisible = new ModernLayersCheckBox(true); // ModernLayersCheckBox(true);

  /**
   * The member layer model.
   */
  private LayerModel mLayerModel;

  /**
   * The member name.
   */
  private String mName;

  /**
   * Instantiates a new modern preview layer panel.
   *
   * @param name       the name
   * @param layerModel the layer model
   * @param canvas     the canvas
   */
  public ModernPreviewLayerPanel(String name, LayerModel layerModel, ModernCanvas canvas) {
    setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

    this.mName = name;
    this.mLayerModel = layerModel;

    double scale = Math.max(0.2,
        size / (double) Math.max(canvas.getPreferredSize().width, canvas.getPreferredSize().height));

    // ZoomCanvas zoomCanvas = new ZoomCanvas(canvas, scale);

    // zoomCanvas.setCanvasSize(size, size);

    ImageCanvas imageCanvas = new ImageCanvas(canvas);

    UI.setSize(imageCanvas, size, size);

    FrameCanvas frameCanvas = new FrameCanvas(imageCanvas);

    UI.setSize(frameCanvas, size, size);

    add(checkVisible);
    add(createHGap());
    add(frameCanvas);
    add(createHGap());
    add(new ModernAutoSizeLabel(name));

    setMinimumSize(new Dimension(400, size));

    layerModel.addLayerListener(this);
    checkVisible.addClickListener(this);

    checkVisible.setSelected(layerModel.isVisible(name));

    setBorder(BORDER);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    g2.setColor(ThemeService.getInstance().getColors().getGray(1));

    g2.drawLine(0, mRect.getH() - 1, mRect.getW(), mRect.getH() - 1);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  @Override
  public void clicked(ModernClickEvent e) {
    mLayerModel.setVisible(mName, this.checkVisible.isSelected());
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.layers.LayerEventListener#layerChanged(org.abh.lib.
   * event.ChangeEvent)
   */
  @Override
  public void layerChanged(ChangeEvent e) {
    // this.checkVisible.setSelected(layerModel.isVisible(name));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.layers.LayerEventListener#layerUpdated(org.abh.common.
   * event .ChangeEvent)
   */
  @Override
  public void layerUpdated(ChangeEvent e) {
    // this.checkVisible.setSelected(layerModel.isVisible(name));
  }
}
