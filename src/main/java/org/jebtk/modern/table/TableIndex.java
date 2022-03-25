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
package org.jebtk.modern.table;

import org.jebtk.core.event.ChangeListeners;

/**
 * Represents a column or row in a table.
 * 
 * @author Antony Holmes
 *
 */
public class TableIndex extends ChangeListeners implements Comparable<TableIndex> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * The constant DEFAULT_CELL_HEIGHT.
   */
  public static final int DEFAULT_CELL_HEIGHT = 26;

  /**
   * The member width.
   */
  private int mWidth = -1;

  /**
   * The min width.
   */
  private int mMinWidth = DEFAULT_CELL_HEIGHT;

  /**
   * The member index.
   */
  private int mIndex;

  /**
   * Instantiates a new table index.
   *
   * @param width the width
   */
  public TableIndex(int width) {
    setWidth(width);
  }

  /**
   * Copy constructor.
   *
   * @param index the index
   */
  public TableIndex(TableIndex index) {
    this(index.getWidth());

    // mRenderer = index.mRenderer;
    // mHeadingRenderer = index.mHeadingRenderer;
    // mEditor = index.mEditor;
  }

  // public ModernDataCellRenderer getCellRenderer() {
  // return mRenderer;
  // }

  // public void setCellRenderer(ModernDataCellRenderer renderer) {
  // mRenderer = renderer;
  // }

  /**
   * Sets the cell heading renderer.
   *
   * @param width the new width
   */
  // public void setCellHeadingRenderer(ModernDataCellRenderer headingRenderer)
  // {
  // mHeadingRenderer = headingRenderer;
  // }

  // public ModernDataViewCellEditor getEditor() {
  //// return mEditor;
  // }

  // public void setCellEditor(ModernDataViewCellEditor editor) {
  // mEditor = editor;
  // }

  /**
   * Sets the width.
   *
   * @param width the new width
   */
  public void setWidth(int width) {
    mWidth = Math.max(width, mMinWidth);

    fireChanged();
  }

  /**
   * Gets the width.
   *
   * @return the width
   */
  public int getWidth() {
    return mWidth;
  }

  /**
   * Gets the heading renderer.
   *
   * @return the heading renderer
   */
  // public ModernDataCellRenderer getHeadingRenderer() {
  // return mHeadingRenderer;
  // }

  /**
   * Gets the index.
   *
   * @return the index
   */
  public int getIndex() {
    return mIndex;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  @Override
  public int compareTo(TableIndex tableIndex) {
    if (mIndex < tableIndex.getIndex()) {
      return -1;
    } else if (mIndex > tableIndex.getIndex()) {
      return 1;
    } else {
      return 0;
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof TableIndex)) {
      return false;
    }

    return compareTo((TableIndex) o) == 0;
  }
}
