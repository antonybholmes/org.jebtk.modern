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
package org.jebtk.modern.status;

import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import org.jebtk.modern.UI;

/**
 * The class StatusTask.
 */
public abstract class StatusTask extends SwingWorker<Void, String> {

  /**
   * The member status model.
   */
  protected StatusModel mStatusModel;

  /**
   * The class Message.
   */
  private class Message implements Runnable {

    /**
     * The member status.
     */
    private String mStatus;

    /**
     * Instantiates a new message.
     *
     * @param status the status
     */
    public Message(String status) {
      mStatus = status;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
      mStatusModel.setStatus(mStatus);
    }

  }

  /**
   * Instantiates a new status task.
   *
   * @param statusModel the status model
   */
  public StatusTask(StatusModel statusModel) {
    mStatusModel = statusModel;
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.SwingWorker#done()
   */
  @Override
  public void done() {
    publish(UI.STATUS_READY);
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.SwingWorker#process(java.util.List)
   */
  @Override
  protected void process(List<String> chunks) {
    for (String status : chunks) {
      SwingUtilities.invokeLater(new Message(status));
    }
  }
}
