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
package org.jebtk.modern.preview;

import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.panel.ModernPanel;

// TODO: Auto-generated Javadoc
/**
 * A preview provides a standardized way of displaying multiple output files on
 * a single panel (e.g. using a tree) and removes the need to separately code
 * individual widgets to display files. This is designed for methods that
 * process text files where it is desirable to show the results to the user so
 * they can quickly determine if a tool has worked correctly.
 * 
 * @author Antony Holmes
 *
 */
public abstract class PreviewPanel extends ModernWidget {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Add a preview panel to the panel view.
   *
   * @param name              the name
   * @param previewTablePanel the preview table panel
   */
  public abstract void addPreview(String name, ModernPanel previewTablePanel);

  /**
   * Return the currently selected preview, or null if nothing is selected.
   *
   * @return the selected preview
   */
  public abstract ModernPanel getSelectedPreview();

  /**
   * Remove all previews.
   */
  public abstract void clear();

  /**
   * Returns a particular preview.
   *
   * @param index the index
   * @return the preview
   */
  public abstract ModernPanel getPreview(int index);
}
