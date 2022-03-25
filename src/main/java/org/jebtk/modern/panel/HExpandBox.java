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

import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.text.ModernAutoSizeLabel;

/**
 * Create a hbox containing two components, one aligned left, the other aligned
 * right that expands to fill horizontal space.
 * 
 * @author Antony Holmes
 *
 */
public class HExpandBox extends ModernComponent {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  public HExpandBox() {
    setLayout(new HExpandBoxLayout());

    // UI.setSize(this, ModernWidget.MAX_SIZE_24);
  }

  /**
   * Instantiates a new h expand box.
   *
   * @param name   the name
   * @param c2     the c 2
   * @param others the others
   */
  public HExpandBox(String name, Component c2, Component... others) {
    this(new ModernAutoSizeLabel(name), c2, others);
  }

  /**
   * Instantiates a new h expand box.
   *
   * @param c1     the c 1
   * @param c2     the c 2
   * @param others the others
   */
  public HExpandBox(Component c1, Component c2, Component... others) {
    this();

    add(c1, c2, others);
  }

  public Component add(String name, Component c2) {
    add(new ModernAutoSizeLabel(name), c2);

    return this;
  }

  public void add(String name, Component c2, Component... others) {
    add(new ModernAutoSizeLabel(name), c2, others);
  }

  public void add(Component c1, Component c2) {
    add(c1);
    add(c2);
  }

  public void add(Component c1, Component c2, Component... others) {
    add(c1);
    add(c2);

    for (Component c : others) {
      add(c);
    }
  }
}
