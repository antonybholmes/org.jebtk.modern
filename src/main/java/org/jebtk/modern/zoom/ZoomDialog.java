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

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.Box;

import org.jebtk.core.event.ChangeListener;
import org.jebtk.modern.button.ModernButtonGroup;
import org.jebtk.modern.button.ModernRadioButton;
import org.jebtk.modern.button.ModernTwoStateWidget;
import org.jebtk.modern.dialog.ModernDialogTaskType;
import org.jebtk.modern.dialog.ModernDialogTaskWindow;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.panel.HBox;
import org.jebtk.modern.panel.ModernPanel;
import org.jebtk.modern.spinner.ModernCompactSpinner;
import org.jebtk.modern.text.ModernAutoSizeLabel;
import org.jebtk.modern.window.ModernWindow;

/**
 * The class ZoomDialog.
 */
public class ZoomDialog extends ModernDialogTaskWindow {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  // private ZoomToolsPanel zoomPanel;

  /**
   * The check25.
   */
  private ModernTwoStateWidget mCheck25 = new ModernRadioButton("25 %");

  /**
   * The check50.
   */
  private ModernTwoStateWidget mCheck50 = new ModernRadioButton("50 %");

  /**
   * The check75.
   */
  private ModernTwoStateWidget mCheck75 = new ModernRadioButton("75 %");

  /**
   * The check100.
   */
  private ModernTwoStateWidget mCheck100 = new ModernRadioButton("100 %");

  /**
   * The check200.
   */
  private ModernTwoStateWidget mCheck200 = new ModernRadioButton("200 %");

  /**
   * The check400.
   */
  private ModernTwoStateWidget mCheck400 = new ModernRadioButton("400 %");

  /**
   * The check custom.
   */
  private ModernTwoStateWidget mCheckCustom = new ModernRadioButton("Custom");

  /**
   * The text custom.
   */
  private ModernCompactSpinner mTextCustom = new ModernCompactSpinner(1, 400, 100);

  /**
   * The member model.
   */
  private ZoomModel mModel;

  /**
   * The class FocusEvents.
   */
  private class FocusEvents implements FocusListener {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
     */
    @Override
    public void focusGained(FocusEvent arg0) {
      mCheckCustom.doClick();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
     */
    @Override
    public void focusLost(FocusEvent arg0) {
      // TODO Auto-generated method stub

    }

  }

  /**
   * Instantiates a new zoom dialog.
   *
   * @param parent the parent
   * @param model  the model
   */
  public ZoomDialog(ModernWindow parent, ZoomModel model) {
    super(parent, ModernDialogTaskType.CLOSE);

    setResizable(false);
    setDarkBackground();
    setSize(360, 400);
    setTitle("Zoom");
    mModel = model;

    // zoomPanel = new ZoomToolsPanel(model);

    Box box = Box.createVerticalBox();

    // box.add(zoomPanel);

    // box.add(Ui.createVerticalGap(20));

    box.add(mCheck400);
    box.add(ModernPanel.createVGap());
    box.add(mCheck200);
    box.add(ModernPanel.createVGap());
    box.add(mCheck100);
    box.add(ModernPanel.createVGap());
    box.add(mCheck75);
    box.add(ModernPanel.createVGap());
    box.add(mCheck50);
    box.add(ModernPanel.createVGap());
    box.add(mCheck25);
    box.add(ModernPanel.createVGap());

    Box hbox = HBox.create();

    hbox.add(mCheckCustom);
    hbox.add(mTextCustom);
    hbox.add(ModernPanel.createHGap());
    hbox.add(new ModernAutoSizeLabel("%"));
    box.add(hbox);

    setCard(box);

    mTextCustom.setText(Integer.toString((int) (model.getZoom() * 100)));

    new ModernButtonGroup().add(mCheck25).add(mCheck50).add(mCheck75).add(mCheck100).add(mCheck200).add(mCheck400)
        .add(mCheckCustom);

    mCheck25.addClickListener(this);
    mCheck50.addClickListener(this);
    mCheck75.addClickListener(this);
    mCheck100.addClickListener(this);
    mCheck200.addClickListener(this);
    mCheck400.addClickListener(this);

    mTextCustom.addFocusListener(new FocusEvents());

    mCheckCustom.addClickListener(new ModernClickListener() {

      @Override
      public void clicked(ModernClickEvent e) {
        zoomCustom();
      }
    });

    mTextCustom.addChangeListener(new ChangeListener() {
      @Override
      public void changed(org.jebtk.core.event.ChangeEvent e) {
        zoomCustom();
      }
    });
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  @Override
  public void clicked(ModernClickEvent e) {
    if (e.getSource().equals(mCheck25)) {
      mModel.setZoom(0.25);
    } else if (e.getSource().equals(mCheck50)) {
      mModel.setZoom(0.5);
    } else if (e.getSource().equals(mCheck75)) {
      mModel.setZoom(0.75);
    } else if (e.getSource().equals(mCheck100)) {
      mModel.setZoom(1);
    } else if (e.getSource().equals(mCheck200)) {
      mModel.setZoom(2);
    } else if (e.getSource().equals(mCheck400)) {
      mModel.setZoom(4);
    } else {
      super.clicked(e);
    }
  }

  /**
   * Zoom custom.
   */
  private void zoomCustom() {
    mCheckCustom.setSelected(true);
    mModel.setZoom(Double.parseDouble(mTextCustom.getText()) / 100.0);
  }
}
