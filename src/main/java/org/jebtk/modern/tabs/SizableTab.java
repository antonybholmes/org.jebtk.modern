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
package org.jebtk.modern.tabs;

import javax.swing.JComponent;

/**
 * Specifies a tab that can be resized
 * 
 * @author Antony Holmes
 *
 */
public class SizableTab extends Tab {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member min width.
   */
  private int mMinWidth;

  /**
   * The member max width.
   */
  private int mMaxWidth;

  /**
   * Instantiates a new sizable content pane.
   *
   * @param name     the name
   * @param c        the c
   * @param width    the width
   * @param minWidth the min width
   * @param maxWidth the max width
   */
  public SizableTab(String name, JComponent c, int width, int minWidth, int maxWidth) {
    super(name, c);

    mMinWidth = minWidth;
    mMaxWidth = maxWidth;

    setWidth(width);
  }

  /**
   * Instantiates a new sizable content pane.
   *
   * @param name     the name
   * @param width    the width
   * @param minWidth the min width
   * @param maxWidth the max width
   */
  public SizableTab(String name, int width, int minWidth, int maxWidth) {
    super(name);

    mMinWidth = minWidth;
    mMaxWidth = maxWidth;

    setWidth(width);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.tabs.Tab#adjustWidth(int)
   */
  @Override
  public void adjustWidth(int width) {
    super.adjustWidth(Math.min(mMaxWidth, Math.max(mMinWidth, width)));
  }
}
