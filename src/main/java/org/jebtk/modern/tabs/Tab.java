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

import org.jebtk.modern.graphics.icons.ModernIcon;

/**
 * All content panes.
 * 
 * @author Antony Holmes
 *
 */
public class Tab extends TabEventListeners implements Comparable<Tab> {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member width.
   */
  private int mWidth = -1;

  /**
   * The member c.
   */
  private JComponent mC;

  /**
   * The member name.
   */
  private String mName;

  /**
   * The member closable.
   */
  private boolean mClosable;

  /** The m icon. */
  private ModernIcon mIcon;

  /**
   * Instantiates a new tab.
   *
   * @param name the name
   * @param c    the c
   */
  public Tab(String name, JComponent c) {
    this(name, null, c);
  }

  /**
   * Instantiates a new tab.
   *
   * @param name the name
   * @param icon the icon
   * @param c    the c
   */
  public Tab(String name, ModernIcon icon, JComponent c) {
    this(name, icon, c, false, TabPosition.LEFT);
  }

  /**
   * Instantiates a new tab.
   *
   * @param name the name
   */
  public Tab(String name) {
    this(name, null, null, false, TabPosition.LEFT);
  }

  /**
   * Instantiates a new tab.
   *
   * @param name      the name
   * @param c         the c
   * @param closeable the closeable
   */
  public Tab(String name, JComponent c, boolean closeable) {
    this(name, c, closeable, TabPosition.LEFT);
  }

  /**
   * Instantiates a new tab.
   *
   * @param name      the name
   * @param icon      the icon
   * @param c         the c
   * @param closeable the closeable
   */
  public Tab(String name, ModernIcon icon, JComponent c, boolean closeable) {
    this(name, icon, c, closeable, TabPosition.LEFT);
  }

  /**
   * Instantiates a new tab.
   *
   * @param name      the name
   * @param c         the c
   * @param closeable the closeable
   * @param position  the position
   */
  public Tab(String name, JComponent c, boolean closeable, TabPosition position) {
    this(name, null, c, closeable, position);
  }

  /**
   * Instantiates a new tab.
   *
   * @param name      the name
   * @param icon      the icon
   * @param c         the c
   * @param closeable the closeable
   * @param position  the position
   */
  public Tab(String name, ModernIcon icon, JComponent c, boolean closeable, TabPosition position) {
    mName = name;
    mIcon = icon;
    mClosable = closeable;

    setComponent(c);
  }

  /**
   * Returns true if the tab can be closed.
   *
   * @return true, if is closable
   */
  public boolean isClosable() {
    return mClosable;
  }

  /**
   * Gets the name.
   *
   * @return the name
   */
  public String getName() {
    return mName;
  }

  /**
   * Sets the JComponent.
   *
   * @param c the new JComponent
   */
  public void setComponent(JComponent c) {
    mC = c;
  }

  /**
   * Returns the underlying JComponent of this tab.
   *
   * @return the JComponent
   */
  public JComponent getComponent() {
    return mC;
  }

  /**
   * Gets the icon.
   *
   * @return the icon
   */
  public ModernIcon getIcon() {
    return mIcon;
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
   * Sets the width.
   *
   * @param width the new width
   */
  public void setWidth(int width) {
    adjustWidth(width);

    fireTabResized(new TabEvent(this, this));
  }

  /**
   * Adjust the width without triggering a layout change event.
   *
   * @param width the width
   */
  public void adjustWidth(int width) {
    mWidth = width;
  }

  /**
   * Refresh tab.
   */
  public void refreshTab() {

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  @Override
  public int compareTo(Tab t) {
    return mName.compareTo(t.mName);
  }
}
