/**
 * Copyright 2017 Antony Holmes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jebtk.modern.panel;

import java.awt.Component;

import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;

/**
 * A vertical box that automatically adds a space between components. Can be
 * used to quickly create simple tool bars.
 * 
 * @author Antony Holmes
 *
 */
public class VSpacedBox extends VBox {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The m space. */
  private int mSpace = ModernWidget.PADDING;

  /**
   * Instantiates a new v spaced box.
   */
  public VSpacedBox() {
    this(ModernWidget.PADDING);
  }

  /**
   * Instantiates a new v spaced box.
   *
   * @param space the space
   */
  public VSpacedBox(int space) {
    mSpace = space;
  }

  /**
   * Creates a vertical box containing some components with the default amount of
   * space between each.
   *
   * @param components the components
   */
  public VSpacedBox(Component... components) {
    this(ModernWidget.PADDING, components);
  }

  /**
   * Instantiates a new v spaced box.
   *
   * @param space      the space
   * @param components the components
   */
  public VSpacedBox(int space, Component... components) {
    this(space);

    for (Component c : components) {
      add(c);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.Container#add(java.awt.Component)
   */
  @Override
  public Component add(Component c) {
    if (getComponentCount() > 0) {
      super.add(UI.createHGap(mSpace));
    }

    super.add(c);

    return this;
  }

}
