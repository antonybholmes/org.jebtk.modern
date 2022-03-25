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
package org.jebtk.modern.window;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.SwingWorker;

import org.jebtk.modern.BorderService;
import org.jebtk.modern.UI;
import org.jebtk.modern.help.GuiAppInfo;
import org.jebtk.modern.panel.HBox;
import org.jebtk.modern.panel.ModernImagePanel;
import org.jebtk.modern.progress.ModernActivityBar;
import org.jebtk.modern.text.ModernAutoSizeLabel;
import org.jebtk.modern.text.ModernTitleLabel;

// TODO: Auto-generated Javadoc
/**
 * The class ModernTaskSplashScreen.
 */
public abstract class ModernTaskSplashScreen extends ModernSplashScreen {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member activity bar.
   */
  private ModernActivityBar mActivityBar = new ModernActivityBar();

  /**
   * The class SetupTask.
   */
  private class SetupTask extends SwingWorker<Void, Void> {

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.SwingWorker#doInBackground()
     */
    @Override
    public Void doInBackground() {
      appSetup();

      return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.SwingWorker#done()
     */
    @Override
    public void done() {
      appSetupFinished();

      mActivityBar.stop();

      close();
    }
  }

  /**
   * Instantiates a new modern task splash screen.
   *
   * @param appInfo the app info
   */
  public ModernTaskSplashScreen(GuiAppInfo appInfo) {
    super(appInfo);

    new SetupTask().execute();

    createUi();
  }

  /**
   * Creates the ui.
   */
  public void createUi() {

    Box box = Box.createVerticalBox();

    Box box2 = HBox.create();

    ModernImagePanel image = new ModernImagePanel(getAppInfo().getIcon(), 128);

    box2.add(image);

    box2.add(UI.createHGap(10));

    Box box3 = Box.createVerticalBox();

    box3.add(new ModernTitleLabel(getAppInfo().getName()));
    box3.add(UI.createVGap(20));
    box3.add(new ModernAutoSizeLabel(getAppInfo().getVersion().toString()));
    box3.add(UI.createVGap(5));
    box3.add(new ModernAutoSizeLabel(getAppInfo().getDescription()));

    box3.setAlignmentX(LEFT_ALIGNMENT);

    box2.add(box3);
    box2.setAlignmentX(LEFT_ALIGNMENT);

    box.setBorder(BorderService.getInstance().createBorder(30));

    box.add(box2);

    box.add(UI.createVGap(70));

    UI.setSize(mActivityBar, new Dimension(640, 24));

    box.add(mActivityBar);

    setBody(box);

    UI.centerWindowToScreen(this);

    mActivityBar.start();
  }

  /**
   * App setup.
   */
  public abstract void appSetup();

  /**
   * App setup finished.
   */
  public abstract void appSetupFinished();
}
