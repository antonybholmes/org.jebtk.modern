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

import javax.swing.Box;

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.UI;
import org.jebtk.modern.button.ModernCheckSwitch;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.panel.VBox;
import org.jebtk.modern.scrollpane.ModernScrollPane;
import org.jebtk.modern.scrollpane.ScrollBarPolicy;

/**
 * The class LayersPanel.
 */
public class LayersPanel2 extends ModernComponent implements LayerEventListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The m layer model. */
  private LayerModel mLayerModel;

  /**
   * Instantiates a new layers panel.
   *
   * @param layerModel the layer model
   */
  public LayersPanel2(LayerModel layerModel) {
    mLayerModel = layerModel;

    layerModel.addLayerListener(this);

    update();
  }

  /**
   * Update.
   */
  private void update() {
    Box box = VBox.create();

    for (String name : mLayerModel) {
      ModernCheckSwitch button = new ModernCheckSwitch(name, mLayerModel.isVisible(name));

      box.add(button);
      box.add(UI.createVGap(5));

      button.addClickListener(new ModernClickListener() {

        @Override
        public void clicked(ModernClickEvent e) {
          ModernCheckSwitch button = (ModernCheckSwitch) e.getSource();

          mLayerModel.setVisible(button.getText(), button.isSelected());
        }
      });
    }

    ModernScrollPane scrollPane = new ModernScrollPane(box);
    scrollPane.setHorizontalScrollBarPolicy(ScrollBarPolicy.NEVER);

    setBody(scrollPane);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.layers.LayerEventListener#layerChanged(org.abh.common.
   * event .ChangeEvent)
   */
  @Override
  public void layerChanged(ChangeEvent e) {
    update();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.layers.LayerEventListener#layerUpdated(org.abh.common.
   * event .ChangeEvent)
   */
  @Override
  public void layerUpdated(ChangeEvent e) {
    // Do nothing
  }
}
