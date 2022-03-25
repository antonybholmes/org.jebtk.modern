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
package org.jebtk.modern.shadow;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JLayeredPane;

import org.jebtk.modern.ModernComponent;

/**
 * The Class ShadowPanel.
 */
public class ShadowPanel extends JLayeredPane implements ComponentListener {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The Constant COMPONENT_Z. */
  private static final Integer COMPONENT_Z = new Integer(0);

  /** The Constant SHADOW_Z. */
  private static final Integer SHADOW_Z = new Integer(100);

  /**
   * The Class RibbonShadow.
   */
  protected static class RibbonShadow extends ModernComponent {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
  }

  /** The m C. */
  protected Component mC;

  /** The m shadow. */
  protected RibbonShadow mShadow;

  /**
   * Instantiates a new shadow panel.
   *
   * @param c      the c
   * @param shadow the shadow
   */
  public ShadowPanel(Component c, RibbonShadow shadow) {
    mC = c;
    mShadow = shadow;

    add(c, COMPONENT_Z);
    add(shadow, SHADOW_Z);

    addComponentListener(this);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.ComponentListener#componentHidden(java.awt.event.
   * ComponentEvent)
   */
  @Override
  public void componentHidden(ComponentEvent arg0) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.ComponentListener#componentMoved(java.awt.event.
   * ComponentEvent)
   */
  @Override
  public void componentMoved(ComponentEvent arg0) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.ComponentListener#componentResized(java.awt.event.
   * ComponentEvent)
   */
  @Override
  public void componentResized(ComponentEvent arg0) {
    mC.setBounds(0, 0, getWidth(), getHeight());
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.ComponentListener#componentShown(java.awt.event.
   * ComponentEvent)
   */
  @Override
  public void componentShown(ComponentEvent arg0) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.Component#getSize()
   */
  @Override
  public Dimension getSize() {
    return mC.getSize();
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.JComponent#getPreferredSize()
   */
  @Override
  public Dimension getPreferredSize() {
    return mC.getPreferredSize();
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.JComponent#getMinimumSize()
   */
  @Override
  public Dimension getMinimumSize() {
    return mC.getMinimumSize();
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.JComponent#getMaximumSize()
   */
  @Override
  public Dimension getMaximumSize() {
    return mC.getMaximumSize();
  }
}
