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
package org.jebtk.modern.ribbon;

import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;

import org.jebtk.modern.panel.HBox;

// TODO: Auto-generated Javadoc
/**
 * Represents a toolbar (with associated tab) on the ribbon control. Each
 * toolbar consists of sections containing buttons.
 *
 * @author Antony Holmes
 *
 */
public class RibbonToolbar extends HBox implements IRibbonModeProperty {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member name.
   */
  private final String mName;

  /**
   * The member section map.
   */
  protected Map<String, RibbonSection> mSectionMap = new HashMap<String, RibbonSection>();

  /** The m mode. */
  private RibbonSize mMode;

  /** The m ribbon. */
  private final Ribbon mRibbon;

  /**
   * Instantiates a new ribbon toolbar.
   *
   * @param ribbon the ribbon
   * @param name   the name
   */
  public RibbonToolbar(Ribbon ribbon, String name) {
    mRibbon = ribbon;
    // Ensure name is formatted in sentence case
    mName = name;

    setSize(RibbonSize.COMPACT);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.Component#getName()
   */
  @Override
  public String getName() {
    return mName;
  }

  /**
   * Fills up the rest of the toolbar with space.
   */
  public final void addSpace() {
    add(Box.createHorizontalGlue());
  }

  /**
   * Adds the.
   *
   * @param ribbonSection the ribbon section
   */
  public void add(RibbonSection ribbonSection) {
    addSection(ribbonSection);
  }

  /**
   * Adds the section to the ribbon, if a section of the same name does not
   * already exist.
   *
   * @param section the ribbon section
   * @return
   */
  public RibbonSection addSection(RibbonSection section) {
    String name = section.getName();

    if (!mSectionMap.containsKey(name)) {
      section.setSize(mMode);

      mSectionMap.put(section.getName(), section);

      super.add(section);
    }

    return mSectionMap.get(name);
  }

  // public RibbonSection getSection(String name) {
  // return mSectionMap.get(name);
  // }

  /**
   * Create a section or return an existing one if it exists.
   *
   * @param name the name
   * @return the section
   */
  public RibbonSection getSection(String name) {
    return getSection(name, false);
  }

  public RibbonSection getSection(String name, boolean showLabel) {

    if (!mSectionMap.containsKey(name)) {
      addSection(new RibbonSection(mRibbon, name, showLabel));
    }

    return mSectionMap.get(name);
  }

  /**
   * Gets the home section.
   *
   * @return the home section
   */
  public RibbonSection getHomeSection() {
    return getSection(Ribbon.HOME_TOOLBAR);
  }

  /**
   * Gets the.
   *
   * @param name the name
   * @return the ribbon section
   */
  public RibbonSection get(String name) {
    return getSection(name);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.ribbon.RibbonModeProperty#setSize(org.abh.common.ui.
   * ribbon. RibbonSize)
   */
  public void setSize(RibbonSize mode) {
    mMode = mode;

    for (RibbonSection section : mSectionMap.values()) {
      section.setSize(mode);
    }
  }
}