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
package org.jebtk.modern.dialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.Box;
import javax.swing.SwingUtilities;

import org.jebtk.modern.UI;
import org.jebtk.modern.panel.HCenterBox;
import org.jebtk.modern.progress.ModernActivityBar2;
import org.jebtk.modern.text.ModernCenteredHeadingPanel;
import org.jebtk.modern.window.FrostedGlassPane;

// TODO: Auto-generated Javadoc
/**
 * Search dialog to notify users that a search is underway and to make it
 * obvious they should wait for the search to finish.
 * 
 * @author Antony Holmes
 *
 */
public class MessageDialogTaskGlassPane extends FrostedGlassPane {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member activity bar.
   */
  private ModernActivityBar2 mActivityBar = new ModernActivityBar2();

  /**
   * The member panel.
   */
  private TaskPanel mPanel = new TaskPanel();

  /**
   * The class TaskPaneRunnable.
   */
  private class TaskPaneRunnable implements Runnable {

    /**
     * The member message.
     */
    private String mMessage;

    /**
     * Instantiates a new task pane runnable.
     *
     * @param message the message
     */
    public TaskPaneRunnable(String message) {
      mMessage = message;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
      mPanel.showMessage(mMessage);

      requestFocusInWindow();
    }

  }

  /**
   * The class TaskPanel.
   */
  private class TaskPanel extends MessageDialogPanel implements FocusListener {

    /**
     * The constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The constant WIDTH.
     */
    private static final int WIDTH = 500;

    /**
     * The member message label.
     */
    private ModernCenteredHeadingPanel mMessageLabel = new ModernCenteredHeadingPanel("Task", Color.WHITE);

    /**
     * Instantiates a new task panel.
     */
    public TaskPanel() {
      Box box2 = new HCenterBox();

      // box3.add(mMessageLabel);
      // box3.add(ModernPanel.createVGap());
      // box3.add(new ModernSubHeadingLabel("Something or other", Color.WHITE));

      UI.setSize(mMessageLabel, new Dimension(WIDTH, 50));

      box2.add(mMessageLabel);
      add(box2);
      add(UI.createVGap(10));

      box2 = new HCenterBox();
      UI.setSize(mActivityBar, new Dimension(WIDTH, 10));
      box2.add(mActivityBar);
      add(box2);

      addFocusListener(this);
    }

    /**
     * Show message.
     *
     * @param message the message
     */
    public void showMessage(String message) {
      mMessageLabel.setText(message);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
     */
    @Override
    public void focusGained(FocusEvent e) {
      mActivityBar.start();

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
     */
    @Override
    public void focusLost(FocusEvent e) {
      System.err.println("Activty stop");
      mActivityBar.stop();
    }
  }

  /**
   * Instantiates a new message dialog task glass pane.
   */
  public MessageDialogTaskGlassPane() {
    add(mPanel);
  }

  /**
   * Show message.
   *
   * @param message the message
   */
  public void showMessage(String message) {
    SwingUtilities.invokeLater(new TaskPaneRunnable(message));
  }

  /**
   * Close.
   */
  public void close() {
    mActivityBar.stop();
    setVisible(false);
  }
}
