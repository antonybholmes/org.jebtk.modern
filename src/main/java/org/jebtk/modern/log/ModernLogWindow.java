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
package org.jebtk.modern.log;

import org.jebtk.core.log.Log;
import org.jebtk.modern.UI;
import org.jebtk.modern.clipboard.ClipboardRibbonSection;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.help.GuiAppInfo;
import org.jebtk.modern.help.ModernAboutDialog;
import org.jebtk.modern.help.RibbonPanelProductInfo;
import org.jebtk.modern.options.ModernOptionsRibbonPanel;
import org.jebtk.modern.panel.ModernPaddedPanel;
import org.jebtk.modern.panel.ModernPanel;
import org.jebtk.modern.ribbon.Ribbon;
import org.jebtk.modern.ribbon.RibbonMenuItem;
import org.jebtk.modern.status.ModernStatusBar;
import org.jebtk.modern.window.ModernRibbonWindow;
import org.jebtk.modern.window.ModernWindow;
import org.jebtk.modern.window.IModernWindowConstructor;

/**
 * Window for viewing the event log.
 * 
 * @author Antony Holmes
 *
 */
public class ModernLogWindow extends ModernRibbonWindow implements IModernWindowConstructor, ModernClickListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The ribbon.
   */
  private Ribbon ribbon = null;

  /**
   * The status bar.
   */
  private ModernStatusBar statusBar = new ModernStatusBar();

  /**
   * The log table.
   */
  private LogTable logTable;

  /**
   * The member log.
   */
  private Log mLog;

  /**
   * Instantiates a new modern log window.
   *
   * @param information the information
   * @param log         the log
   */
  public ModernLogWindow(GuiAppInfo information, Log log) {
    super(information);

    mLog = log;

    setSubTitle("Log");

    createRibbon();

    createUi();

    setup();
  }

  /**
   * Setup.
   */
  public final void setup() {

    setSize(640, 480);

    UI.centerWindowToScreen(this);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.window.ModernWindowConstructor#createRibbon()
   */
  public void createRibbon() {
    // RibbongetRibbonMenu() getRibbonMenu() = new RibbongetRibbonMenu()(0);
    RibbonMenuItem menuItem;

    menuItem = new RibbonMenuItem(UI.MENU_CLOSE);
    getRibbonMenu().addTabbedMenuItem(menuItem);

    getRibbonMenu().addSeparator();

    menuItem = new RibbonMenuItem(UI.MENU_INFO);
    getRibbonMenu().addTabbedMenuItem(menuItem, new RibbonPanelProductInfo(getAppInfo()));
    // getRibbonMenu().addTabbedMenuItem(menuItem);

    menuItem = new RibbonMenuItem(UI.MENU_OPTIONS);
    getRibbonMenu().addTabbedMenuItem(menuItem, new ModernOptionsRibbonPanel(getAppInfo()));

    getRibbonMenu().addClickListener(this);

    // ribbon = new Ribbon();

    getRibbon().setHelpButtonEnabled(getAppInfo());

    // export

    getRibbon().getHomeToolbar().addSection(new ClipboardRibbonSection(getRibbon()));

    getRibbon().setSelectedIndex(1);

    // setRibbon(ribbon, getRibbonMenu());
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.window.ModernWindow#createUi()
   */
  public void createUi() {
    LogTablePanel logPanel = new LogTablePanel(mLog);

    logTable = new LogTable(logPanel);

    mLog.addLogListener(logTable);

    setBody(new ModernPanel(new ModernPaddedPanel(logPanel)));
    setFooter(statusBar);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  @Override
  public final void clicked(ModernClickEvent e) {
    if (e.getMessage().equals(UI.MENU_ABOUT)) {
      ModernAboutDialog.show(this, getAppInfo());
    } else if (e.getMessage().equals(UI.MENU_EXIT)) {
      close();
    } else {
      // Do nothing
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.window.ModernWindow#close()
   */
  @Override
  public void close() {
    mLog.removeLogListener(logTable);

    super.close();
  }

  /**
   * Create a default centered about dialog.
   *
   * @param productDetails the product details
   * @param log            the log
   */
  public static void createAndShow(GuiAppInfo productDetails, Log log) {
    ModernWindow dialog = new ModernLogWindow(productDetails, log);

    UI.centerWindowToScreen(dialog);

    dialog.setVisible(true);
  }
}
