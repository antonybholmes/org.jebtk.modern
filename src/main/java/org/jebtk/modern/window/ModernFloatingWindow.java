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

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JFrame;

import org.jebtk.modern.dialog.ModernDialogButtonBox;
import org.jebtk.modern.dialog.ModernDialogContentPanel;
import org.jebtk.modern.dialog.ModernDialogStatus;
import org.jebtk.modern.help.GuiAppInfo;
import org.jebtk.modern.panel.ModernPanel;

/**
 * Window that is always floating.
 *
 */
public class ModernFloatingWindow extends JFrame {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member content panel.
   */
  private ModernPanel mContentPanel = new ModernWindowPanel();

  /**
   * The member status.
   */
  private ModernDialogStatus mStatus = ModernDialogStatus.CANCEL;

  /**
   * The member app info.
   */
  private GuiAppInfo mAppInfo;

  /**
   * The member sub title.
   */
  private String mSubTitle;

  /**
   * Instantiates a new modern floating window.
   *
   * @param appInfo the app info
   */
  public ModernFloatingWindow(GuiAppInfo appInfo) {
    mAppInfo = appInfo;

    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    setAlwaysOnTop(true);

    super.getContentPane().add(mContentPanel, BorderLayout.CENTER);

    setIconImage(appInfo.getIcon().getImage(32));

    setTitle(appInfo.getName());
  }

  /**
   * Gets the app info.
   *
   * @return the app info
   */
  public GuiAppInfo getAppInfo() {
    return mAppInfo;
  }

  /**
   * Sets the header.
   *
   * @param c the new header
   */
  protected void setHeader(Component c) {
    getContentPane().add(c, BorderLayout.PAGE_START);
  }

  /**
   * Sets the body.
   *
   * @param c the new body
   */
  public void setBody(Component c) {
    getContentPane().add(c, BorderLayout.CENTER);
  }

  /**
   * Sets the footer.
   *
   * @param c the new footer
   */
  public void setFooter(Component c) {
    getContentPane().add(c, BorderLayout.PAGE_END);
  }

  /**
   * Returns the panel.
   *
   * @return the content panel
   */
  public ModernPanel getContentPanel() {
    return mContentPanel;
  }

  /**
   * Sets the content.
   *
   * @param c the new content
   */
  public void setContent(JComponent c) {
    setBody(new ModernDialogContentPanel(c));
  }

  /**
   * Sets the buttons.
   *
   * @param c the new buttons
   */
  public void setButtons(JComponent c) {
    setFooter(new ModernDialogButtonBox(c));
  }

  /**
   * Sets the status.
   *
   * @param status the new status
   */
  public final void setStatus(ModernDialogStatus status) {
    mStatus = status;
  }

  /**
   * Gets the status.
   *
   * @return the status
   */
  public final ModernDialogStatus getStatus() {
    return mStatus;
  }

  /**
   * Set the window title but include the main app title.
   *
   * @param subTitle the new sub title
   */
  public void setSubTitle(String subTitle) {
    setTitle(subTitle + " - " + getAppInfo().getName());

    mSubTitle = subTitle;
  }

  /**
   * Gets the sub title.
   *
   * @return the sub title
   */
  public String getSubTitle() {
    return mSubTitle;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.Frame#setTitle(java.lang.String)
   */
  @Override
  public void setTitle(String title) {
    mSubTitle = title;

    super.setTitle(title);
  }

  /**
   * Terminates the application normally.
   */
  public void exit() {
    exit(0);
  }

  /**
   * Terminate application with a given status code (non zero implies error).
   *
   * @param status the status
   */
  public void exit(int status) {
    System.exit(status);
  }

  /**
   * Close the window. If this is the last windows, it causes the VM to stop as
   * well.
   */
  public void close() {
    setVisible(false);
    dispose();
  }
}
