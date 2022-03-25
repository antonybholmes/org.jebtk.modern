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

import java.awt.Color;

import javax.swing.Box;
import javax.swing.SwingWorker;

import org.jebtk.modern.BorderService;
import org.jebtk.modern.UI;
import org.jebtk.modern.help.GuiAppInfo;
import org.jebtk.modern.panel.HCenterBox;
import org.jebtk.modern.panel.VBox;
import org.jebtk.modern.progress.ModernActivityBar2;
import org.jebtk.modern.text.ModernAutoSizeLabel;
import org.jebtk.modern.text.ModernSplashTitleLabel;

// TODO: Auto-generated Javadoc
/**
 * The class ModernTaskSplashScreen2.
 */
public abstract class ModernTaskSplashScreen2 extends ModernSplashScreen {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member activity bar.
   */
  private ModernActivityBar2 mActivityBar = new ModernActivityBar2();

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
   * Instantiates a new modern task splash screen2.
   *
   * @param appInfo the app info
   */
  public ModernTaskSplashScreen2(GuiAppInfo appInfo) {
    super(appInfo);
  }

  /**
   * Inits the.
   */
  public void init() {

    new SetupTask().execute();

    createUi();
  }

  /**
   * Creates the ui.
   */
  public void createUi() {

    Box box = VBox.create();

    box.add(UI.createVGap(20));

    // Box box2 = new HBoxPanel();

    // ModernImagePanel image =
    // new ModernImagePanel(getAppInfo().getLargeIcon());

    // box2.add(image);

    // box2.add(Ui.createHGap(10));

    box.add(new ModernSplashTitleLabel(getAppInfo().getName()));
    box.add(UI.createVGap(10));
    box.add(new ModernAutoSizeLabel(getAppInfo().getVersion().toString(), Color.WHITE));

    // box2.add(box3);

    box.add(UI.createVGap(60));

    UI.setSize(mActivityBar, 500, 24);
    box.add(new HCenterBox(mActivityBar));

    box.setBorder(BorderService.getInstance().createLeftRightBorder(60));
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
