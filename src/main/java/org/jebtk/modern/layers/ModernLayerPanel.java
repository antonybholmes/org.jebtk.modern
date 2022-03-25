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
import org.jebtk.modern.event.ModernStateEvent;
import org.jebtk.modern.event.ModernStateListener;
import org.jebtk.modern.text.ModernAutoSizeLabel;
import org.jebtk.modern.theme.ThemeService;

/**
 * The class ModernLayerPanel.
 */
public class ModernLayerPanel extends ModernWidget implements LayerEventListener, ModernStateListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant SIZE.
   */
  private static final int SIZE = 32; // ModernTheme.getInstance().getClass("widget").getInt("height");

  /**
   * The check visible.
   */
  private ModernCheckBox checkVisible = new ModernLayersCheckBox(true); // ModernLayersCheckBox(true);

  /**
   * The layer model.
   */
  private LayerModel layerModel;

  /**
   * The name.
   */
  private String name;

  /**
   * Instantiates a new modern layer panel.
   *
   * @param name       the name
   * @param layerModel the layer model
   */
  public ModernLayerPanel(String name, LayerModel layerModel) {
    setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

    this.name = name;
    this.layerModel = layerModel;

    setOpaque(false);

    add(checkVisible);
    add(createHGap());
    add(new ModernAutoSizeLabel(name));

    UI.setSize(this, new Dimension(400, SIZE));

    layerModel.addLayerListener(this);

    checkVisible.setSelected(layerModel.isVisible(name));
    checkVisible.addStateListener(this);

    setAlignmentX(LEFT_ALIGNMENT);

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

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.event.ModernStateListener#stateChanged(org.abh.lib. ui.
   * modern.event.ModernStateEvent)
   */
  @Override
  public void stateChanged(ModernStateEvent e) {
    layerModel.setVisible(name, this.checkVisible.isSelected());
  }
}
