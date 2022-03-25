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
package org.jebtk.modern.help;

import javax.swing.Box;

import org.jebtk.modern.UI;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.panel.HBox;
import org.jebtk.modern.panel.ModernImagePanel;
import org.jebtk.modern.panel.VBox;
import org.jebtk.modern.ribbon.RibbonMenuPanel;
import org.jebtk.modern.text.ModernAutoSizeLabel;
import org.jebtk.modern.text.ModernSubHeadingLabel;
import org.jebtk.modern.text.ModernTitleLabel;
import org.jebtk.modern.window.ModernWindow;

/**
 * Displays about information for a software product.
 * 
 * @author Antony Holmes
 *
 */
public class RibbonPanelProductInfo extends RibbonMenuPanel implements ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The button.
   */
  private RibbonPanelAboutButton button;

  /**
   * Instantiates a new ribbon panel product info.
   *
   * @param product the product
   */
  public RibbonPanelProductInfo(GuiAppInfo product) {
    this(null, product);
  }

  /**
   * Instantiates a new ribbon panel product info.
   *
   * @param window the window
   */
  public RibbonPanelProductInfo(ModernWindow window) {
    this(window, window.getAppInfo());
  }

  // private JTextArea textArea = new TextArea();
  // private TabbedPane tabbedPane = new TabbedPane();

  /**
   * Instantiates a new ribbon panel product info.
   *
   * @param window  the window
   * @param product the product
   */
  public RibbonPanelProductInfo(ModernWindow window, GuiAppInfo product) {
    super("Help");

    Box box = VBox.create();

    box.add(new ModernTitleLabel("Product Information"));

    box.add(UI.createVGap(40));

    Box box2 = HBox.create();

    ModernImagePanel image = new ModernImagePanel(product.getIcon(), 128);

    box2.add(image);
    box2.add(UI.createHGap(10));
    box2.add(new ModernSubHeadingLabel(product.getName()));
    box.add(box2);

    box.add(UI.createVGap(10));

    box.add(new ModernAutoSizeLabel(product.getCopyright()));

    box.add(UI.createVGap(10));

    box.add(new ModernAutoSizeLabel("Version " + product.getVersion().toString()));

    if (window != null) {
      box.add(UI.createVGap(40));

      box.add(new ThemeSelectionPanel(window));
    }

    box.add(UI.createVGap(40));

    button = new RibbonPanelAboutButton(product);

    box.add(button);

    button.addClickListener(this);

    box.add(UI.createVGap(40));

    box.add(new RibbonHelpLinkButton(product));

    /*
     * box2 = HBox.create(); box2.add(button); button.setAlignmentY(TOP_ALIGNMENT);
     * box2.add(UI.createHGap(20));
     * 
     * Box box3 = VBox.create(); box3.add(new ModernSubTitleLabel("About " +
     * product.getName())); box3.add(UI.createVGap(10)); box3.add(new
     * ModernAutoSizeLabel("Learn more about " + product.getName() +
     * " and copyright information.")); box2.add(box3);
     * box3.setAlignmentY(TOP_ALIGNMENT);
     * 
     * box.add(box2);
     */

    setHeader(box);

    // textArea.setEditable(false);

    // textArea.setText(Resources.loadText("resources/license.txt"));
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  @Override
  public void clicked(ModernClickEvent e) {
    fireClicked(new ModernClickEvent(this, e.getMessage()));
  }
}