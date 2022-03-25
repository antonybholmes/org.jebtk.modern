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

import java.util.HashMap;
import java.util.Map;

/**
 * The class StatusService.
 */
public class StatusService {
  /**
   * The constant DEFAULT_CHANNEL.
   */
  private static final String DEFAULT_CHANNEL = "default";

  /**
   * The Class StatusServiceHelper.
   */
  private static class StatusServiceHelper {

    /** The Constant INSTANCE. */
    private static final StatusService INSTANCE = new StatusService();
  }

  /**
   * Gets the single instance of StatusService.
   *
   * @return single instance of StatusService
   */
  public static StatusService getInstance() {
    return StatusServiceHelper.INSTANCE;
  }

  /**
   * The member models.
   */
  private Map<String, StatusModel> mModels = new HashMap<String, StatusModel>();

  /**
   * Instantiates a new status service.
   */
  private StatusService() {
    // Do nothing
  }

  /**
   * Register.
   *
   * @param l the l
   */
  public void register(StatusEventListener l) {
    register(DEFAULT_CHANNEL, l);
  }

  /**
   * Register.
   *
   * @param channel the channel
   * @param l       the l
   */
  public void register(String channel, StatusEventListener l) {
    getModel(channel).addStatusListener(l);
  }

  /**
   * Gets the model.
   *
   * @param channel the channel
   * @return the model
   */
  private synchronized StatusModel getModel(String channel) {
    if (!mModels.containsKey(channel)) {
      mModels.put(channel, new StatusModel());
    }

    return mModels.get(channel);
  }

  /**
   * Sets the status.
   *
   * @param status the new status
   */
  public void setStatus(String status) {
    setStatus(DEFAULT_CHANNEL, status);
  }

  /**
   * Sets the status.
   *
   * @param channel the channel
   * @param status  the status
   */
  public void setStatus(String channel, String status) {
    getModel(channel).setStatus(status);
  }

  /**
   * Sets the ready.
   */
  public void setReady() {
    setReady(DEFAULT_CHANNEL);
  }

  /**
   * Sets the ready.
   *
   * @param channel the new ready
   */
  public void setReady(String channel) {
    getModel(channel).setReady();
  }
}
