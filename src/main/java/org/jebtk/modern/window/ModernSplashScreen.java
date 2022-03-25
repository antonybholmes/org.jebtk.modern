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
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.Box;

import org.jebtk.modern.UI;
import org.jebtk.modern.button.ModernButton;
import org.jebtk.modern.dialog.ModernDialogWindow;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.graphics.icons.CrossVectorIcon;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.graphics.icons.Raster24Icon;
import org.jebtk.modern.help.GuiAppInfo;
import org.jebtk.modern.help.HelpManager;
import org.jebtk.modern.panel.HBox;
import org.jebtk.modern.panel.ModernPanel;
import org.jebtk.modern.panel.VBox;
import org.jebtk.modern.theme.ThemeService;

/**
 * For displaying a splash screen.
 * 
 * @author Antony Holmes
 *
 */
public class ModernSplashScreen extends ModernDialogWindow {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The Constant COLOR. */
  public static final Color COLOR = ThemeService.getInstance().getColors().getTheme32(24); // new
  // Color(appInfo.getIcon().getImage().getRGB(1,
  // 1));

  /**
   * The member close button.
   */
  private ModernButton mCloseButton;

  /**
   * The member help button.
   */
  private ModernButton mHelpButton;

  /**
   * The class CloseEvents.
   */
  private class CloseEvents implements ModernClickListener {

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
     * modern .event.ModernClickEvent)
     */
    @Override
    public void clicked(ModernClickEvent e) {
      close();
    }

  }

  /**
   * The class HelpEvents.
   */
  private class HelpEvents implements ModernClickListener {

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
     * modern .event.ModernClickEvent)
     */
    @Override
    public void clicked(ModernClickEvent e) {
      try {
        HelpManager.launchHelp(mAppInfo);
      } catch (IOException e1) {
        e1.printStackTrace();
      } catch (URISyntaxException e1) {
        e1.printStackTrace();
      }
    }
  }

  /**
   * Instantiates a new modern splash screen.
   *
   * @param window  the window
   * @param appInfo the app info
   */
  public ModernSplashScreen(ModernWindow window, GuiAppInfo appInfo) {
    super(window, appInfo);

    init();
  }

  /**
   * Instantiates a new modern splash screen2.
   *
   * @param appInfo the app info
   */
  public ModernSplashScreen(GuiAppInfo appInfo) {
    super(appInfo);

    init();
  }

  /**
   * Inits the.
   */
  private void init() {

    setUndecorated(true);

    setIconImage(getAppInfo().getIcon().getImage(32));

    setTitle(getAppInfo().getName());

    // determine the color of the icon

    Box topPanel = VBox.create(); // ModernTheme.getInstance().getClass("ribbon-menu").getColor("background"));

    Box box2 = HBox.create();

    box2.add(Box.createHorizontalGlue());

    mHelpButton = new ModernSplashHelpButton();
    mHelpButton.setClickMessage(UI.MENU_HELP);
    mHelpButton.addClickListener(new HelpEvents());
    box2.add(mHelpButton);

    box2.add(ModernPanel.createHGap());

    mCloseButton = new ModernSplashTitleButton(new Raster24Icon(new CrossVectorIcon(), ModernIcon.WHITE_PROPS));
    mCloseButton.setClickMessage(UI.MENU_CLOSE);
    mCloseButton.addClickListener(new CloseEvents());
    box2.add(mCloseButton);

    topPanel.add(box2);

    topPanel.setBorder(ModernPanel.BOTTOM_BORDER);

    setHeader(new ModernPanel(topPanel, COLOR));

    setSize(600, 300);

    UI.centerWindowToScreen(this);
  }

  /**
   * Sets the buttons enabled.
   *
   * @param enabled the new buttons enabled
   */
  protected void setButtonsEnabled(boolean enabled) {
    mCloseButton.setEnabled(enabled);
    mHelpButton.setEnabled(enabled);
  }
}
