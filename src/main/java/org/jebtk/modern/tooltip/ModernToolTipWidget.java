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
package org.jebtk.modern.tooltip;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

import org.jebtk.modern.ModernFocusableWidget;

/**
 * Provides the ability for a widget to display tool tips. By default the
 * control will look for an underlying window that supports tool tips and send
 * tooltip events to this; however, this can be easily overridden if necessary.
 * 
 * @author Antony Holmes
 *
 */
public abstract class ModernToolTipWidget extends ModernFocusableWidget implements ModernToolTipEventProducer {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member tool tip panel.
   */
  protected ModernBasicToolTipPanel mToolTipPanel = null;

  /**
   * The delay in ms after which a tooltip is shown.
   */
  private static final int DELAY = 500;

  /**
   * The member timer.
   */
  private Timer mTimer = null;

  /**
   * The class ToolTipEvents.
   */
  private class ToolTipEvents implements ActionListener {

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
      timerDisplayToolTip();
    }
  }

  /**
   * The class MouseEvents.
   */
  private class MouseEvents extends MouseAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseEntered(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseEntered(MouseEvent e) {
      mouseShowToolTip();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseExited(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseExited(MouseEvent e) {
      mouseHideToolTips();
    }
  }

  private boolean mEnableToolTips = true;

  private ModernToolTipListener mToolTipDest;

  /**
   * Instantiates a new modern tool tip widget.
   */
  public ModernToolTipWidget() {
    setup();
  }

  /**
   * Instantiates a new modern tool tip widget.
   *
   * @param manager the manager
   */
  public ModernToolTipWidget(LayoutManager manager) {
    super(manager);

    setup();
  }
  
  public ModernToolTipWidget(Component c) {
    super(c);
    
    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    mTimer = new Timer(0, new ToolTipEvents());
    mTimer.setRepeats(false);
    mTimer.setInitialDelay(DELAY);

    addMouseListener(new MouseEvents());
  }

  /**
   * Sets whether the component will automatically generate tooltip events. The
   * default is true since most components will want to generate tooltip events
   * when the mouse hovers over them. This methods allows tooltip events to be
   * disabled for more specialized components that will manage them independently.
   * 
   * @param enable whether the tooltip events should be generated or not.
   */
  public void setToolTipsEnabled(boolean enable) {
    mEnableToolTips = enable;
  }

  /**
   * Sets the tool tip.
   *
   * @param title the title
   * @param help  the help
   * @return
   */
  public ModernToolTipWidget setToolTip(String title, String help) {
    return setToolTip(new ModernToolTip(title, help));
  }

  /**
   * Sets the tool tip.
   *
   * @param tooltip the new tool tip
   * @return
   */
  public ModernToolTipWidget setToolTip(ModernToolTip tooltip) {
    return setToolTip(new ModernToolTipSuggestHelpPanel(tooltip));
  }

  /**
   * Sets the tool tip.
   *
   * @param tooltipPanel the new tool tip
   * @return
   */
  public ModernToolTipWidget setToolTip(ModernBasicToolTipPanel tooltipPanel) {
    mToolTipPanel = tooltipPanel;

    // setToolTipsEnabled(true);

    return this;
  }

  private boolean toolTipActionable() {
    return mEnableToolTips && mToolTipPanel != null;
  }

  /**
   * Show tooltip when mouse hovers.
   */
  protected void mouseShowToolTip() {
    if (toolTipActionable()) {
      mTimer.restart(); // restart();
    }
  }

  /**
   * Hide tooltips when mouse exits component.
   */
  protected void mouseHideToolTips() {
    if (toolTipActionable()) {
      mTimer.stop();
      hideToolTips(createToolTipEvent());
    }
  }

  /**
   * Display tool tip.
   */
  private void timerDisplayToolTip() {
    // if (!mShow) {
    // return;
    // }

    // getToolTipModel().showToolTip(this, mToolTipPanel);

    // if (mEnableToolTips) {
    showToolTip(createToolTipEvent(mToolTipPanel));
    // }
  }

  @Override
  public void addToolTipListener(ModernToolTipListener l) {
    ToolTipService.getInstance().addToolTipListener(l);
  }

  @Override
  public void removeToolTipListener(ModernToolTipListener l) {
    ToolTipService.getInstance().removeToolTipListener(l);
  }

  @Override
  public void showToolTip(ModernToolTipEvent e) {
    ToolTipService.getInstance().showToolTip(e);
  }

  @Override
  public void addToolTip(ModernToolTipEvent e) {
    ToolTipService.getInstance().addToolTip(e);
  }

  @Override
  public void hideToolTip(ModernToolTipEvent e) {
    ToolTipService.getInstance().hideToolTip(e);
  }

  /**
   * Hide tool tips.
   */
  @Override
  public void hideToolTips(ModernToolTipEvent e) {
    ToolTipService.getInstance().hideToolTips(e);
  }

  /**
   * Try to find a default listener if one is not provided. Typically this will be
   * the underlying window the component is within.
   * 
   * @return
   */
  @Override
  public ModernToolTipListener getToolTipDest() {
    mToolTipDest = super.getToolTipDest();

    return mToolTipDest;
  }

  public void setToolTipDest(ModernToolTipListener dest) {
    mToolTipDest = dest;
  }
}