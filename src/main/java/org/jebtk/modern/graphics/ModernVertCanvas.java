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
package org.jebtk.modern.graphics;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.Scrollable;

/**
 * The Class ModernVertWidget is designed for scrollable widgets showing
 * vertical content.
 * 
 */
public abstract class ModernVertCanvas extends ModernCanvas implements Scrollable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Gets the preferred scrollable viewport size.
   *
   * @return the preferred scrollable viewport size
   */
  @Override
  public Dimension getPreferredScrollableViewportSize() {
    return getPreferredSize();
  }

  /**
   * Gets the scrollable block increment.
   *
   * @param arg0 the arg 0
   * @param arg1 the arg 1
   * @param arg2 the arg 2
   * @return the scrollable block increment
   */
  @Override
  public int getScrollableBlockIncrement(Rectangle arg0, int arg1, int arg2) {
    // TODO Auto-generated method stub
    return 0;
  }

  /**
   * Gets the scrollable tracks viewport height.
   *
   * @return the scrollable tracks viewport height
   */
  @Override
  public boolean getScrollableTracksViewportHeight() {
    // TODO Auto-generated method stub
    return false;
  }

  /**
   * Gets the scrollable tracks viewport width.
   *
   * @return the scrollable tracks viewport width
   */
  @Override
  public boolean getScrollableTracksViewportWidth() {
    return true;
  }

  /**
   * Gets the scrollable unit increment.
   *
   * @param arg0 the arg 0
   * @param arg1 the arg 1
   * @param arg2 the arg 2
   * @return the scrollable unit increment
   */
  @Override
  public int getScrollableUnitIncrement(Rectangle arg0, int arg1, int arg2) {
    return 0;
  }

}
