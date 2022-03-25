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
package org.jebtk.modern.graphics.color;

import java.awt.Color;

import javax.swing.Box;

import org.jebtk.core.ColorValue;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;
import org.jebtk.modern.button.ModernButtonGroup;
import org.jebtk.modern.button.ModernRadioButton;
import org.jebtk.modern.dialog.ModernDialogTaskWindow;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.panel.HBox;
import org.jebtk.modern.panel.ModernPanel;
import org.jebtk.modern.panel.VBox;
import org.jebtk.modern.spinner.ModernCompactSpinner;
import org.jebtk.modern.text.ModernAutoSizeLabel;
import org.jebtk.modern.window.ModernWindow;

/**
 * The class ColorDialog.
 */
public class ColorDialog extends ModernDialogTaskWindow implements ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member check box red.
   */
  private ModernRadioButton mCheckBoxRed = new ModernRadioButton("R");

  /**
   * The member check box green.
   */
  private ModernRadioButton mCheckBoxGreen = new ModernRadioButton("G");

  /**
   * The member check box blue.
   */
  private ModernRadioButton mCheckBoxBlue = new ModernRadioButton("B");

  /**
   * The member red spinner.
   */
  private ModernCompactSpinner mRedSpinner;

  /**
   * The member blue spinner.
   */
  private ModernCompactSpinner mBlueSpinner;

  /**
   * The member green spinner.
   */
  private ModernCompactSpinner mGreenSpinner;

  /**
   * The member alpha spinner.
   */
  private ModernCompactSpinner mAlphaSpinner;

  /**
   * The model.
   */
  private ColorSelectionModel model = new ColorSelectionModel();

  /**
   * The member html color panel.
   */
  private HtmlColorPanel mHtmlColorPanel;

  /*
   * private class AlphaSpinEvents implements ModernClickListener {
   * 
   * @Override public void clicked(ModernClickEvent e) {
   * mAlphaSlider.updateValue(mAlphaSpinner.getValue()); }
   * 
   * }
   * 
   * private class AlphaSliderEvents implements ChangeListener {
   * 
   * @Override public void changed(ChangeEvent e) {
   * mAlphaSpinner.updateValue(mAlphaSlider.getValue()); }
   * 
   * }
   */

  /**
   * Instantiates a new color dialog.
   *
   * @param parent the parent
   * @param color  the color
   */
  public ColorDialog(ModernWindow parent, Color color) {
    super(parent);

    setup(color);
  }

  /**
   * Sets the up.
   *
   * @param color the new up
   */
  private void setup(Color color) {
    setResizable(false);
    setSize(640, 360);
    setTitle("Colors");

    UI.setSize(mCheckBoxRed, ModernWidget.TINY_SIZE);
    UI.setSize(mCheckBoxGreen, ModernWidget.TINY_SIZE);
    UI.setSize(mCheckBoxBlue, ModernWidget.TINY_SIZE);

    mRedSpinner = new ColorSpinnerRed(model);
    mGreenSpinner = new ColorSpinnerGreen(model);
    mBlueSpinner = new ColorSpinnerBlue(model);
    mAlphaSpinner = new ColorSpinnerAlpha(model);

    Box box1 = VBox.create();

    Box box2 = HBox.create();
    box2.add(new ColorMatrixPanel(model));
    box2.add(UI.createHGap(20));

    Box box3 = VBox.create();

    Box box4 = HBox.create();
    box4.add(mCheckBoxRed);
    box4.add(ModernPanel.createHGap());
    box4.add(new ColorRangeRedSlider(model));
    box4.add(ModernPanel.createHGap());
    box4.add(mRedSpinner);
    box3.add(box4);

    box3.add(ModernPanel.createVGap());

    box4 = HBox.create();
    box4.add(mCheckBoxGreen);
    box4.add(ModernPanel.createHGap());
    box4.add(new ColorRangeGreenSlider(model));
    box4.add(ModernPanel.createHGap());
    box4.add(mGreenSpinner);
    box3.add(box4);

    box3.add(ModernPanel.createVGap());

    box4 = HBox.create();
    box4.add(mCheckBoxBlue);
    box4.add(ModernPanel.createHGap());
    box4.add(new ColorRangeBlueSlider(model));
    box4.add(ModernPanel.createHGap());
    box4.add(mBlueSpinner);
    box3.add(box4);

    box3.add(ModernPanel.createVGap());

    box4 = HBox.create();
    box4.add(new ModernAutoSizeLabel("Alpha", ModernWidget.TINY_SIZE));
    box4.add(ModernPanel.createHGap());
    box4.add(new AlphaSlider(model));
    box4.add(ModernPanel.createHGap());
    box4.add(mAlphaSpinner);
    box3.add(box4);

    box2.add(box3);

    box1.add(box2);

    box1.add(UI.createVGap(20));

    box2 = HBox.create();

    box2.add(new ColorCurrentNewPanel(model));

    mHtmlColorPanel = new HtmlColorPanel(model);
    mHtmlColorPanel.setAlignmentY(TOP_ALIGNMENT);
    box2.add(mHtmlColorPanel);

    box1.add(box2);

    setCard(box1);

    //
    // Non UI
    //

    ModernButtonGroup group = new ModernButtonGroup();

    group.add(mCheckBoxRed);
    group.add(mCheckBoxGreen);
    group.add(mCheckBoxBlue);

    mCheckBoxRed.addClickListener(this);
    mCheckBoxGreen.addClickListener(this);
    mCheckBoxBlue.addClickListener(this);

    // mAlphaSpinner.addClickListener(new AlphaSpinEvents());
    // mAlphaSlider.addChangeListener(new AlphaSliderEvents());

    model.setCurrentColor(ColorValue.convert(color));

    mCheckBoxRed.doClick();

    UI.centerWindowToScreen(this);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  @Override
  public void clicked(ModernClickEvent e) {
    if (e.getSource().equals(mCheckBoxRed)) {
      model.setChannel(ColorChannel.RED);
    } else if (e.getSource().equals(mCheckBoxGreen)) {
      model.setChannel(ColorChannel.GREEN);
    } else if (e.getSource().equals(mCheckBoxBlue)) {
      model.setChannel(ColorChannel.BLUE);
    } else {
      if (e.getSource().equals(mOkButton)) {
        model.setNewColor(mHtmlColorPanel.getColor());
      }

      super.clicked(e);
    }
  }

  /**
   * Gets the color.
   *
   * @return the color
   */
  public Color getColor() {
    return ColorValue.convert(model.getNewColor());
  }
}
