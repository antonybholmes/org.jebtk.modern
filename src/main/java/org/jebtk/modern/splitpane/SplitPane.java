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
package org.jebtk.modern.splitpane;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import org.jebtk.core.Mathematics;
import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.UI;

/**
 * The class AbstractSplitPane.
 */
public abstract class SplitPane extends ModernWidget {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * The member divider width.
   */
  protected int mDividerWidth = PADDING;

  /**
   * The member divider half width.
   */
  protected int mDividerHalfWidth = mDividerWidth / 2;

  // protected JComponent mC1 = null;
  // protected JComponent mC2 = null;

  /**
   * The member components.
   */
  protected List<JComponent> mComponents = new ArrayList<JComponent>();

  /**
   * The member divider locations.
   */
  protected List<Double> mDividerLocations = new ArrayList<Double>();

  /**
   * The member min size.
   */
  protected double mMinSize = 0.1;

  // protected int mMinComponentSize = 48;

  // protected int mDividerLocation = mMinComponentSize;

  /**
   * The member resizable.
   */
  protected boolean mResizable = true;

  /**
   * The member drag divider index.
   */
  protected int mDragDividerIndex = -1;

  /**
   * The class ComponentEvents.
   */
  private class ComponentEvents extends ComponentAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.ComponentAdapter#componentResized(java.awt.event.
     * ComponentEvent)
     */
    @Override
    public void componentResized(ComponentEvent e) {
      resize();
    }
  }

  /**
   * Instantiates a new abstract split pane.
   */
  public SplitPane() {
    setup();
  }

  /*
   * public AbstractSplitPane(JComponent c1, JComponent c2) { setup();
   * 
   * setComponent1(c1); setComponent2(c2); }
   */

  /**
   * Setup.
   */
  private void setup() {
    setLayout(null);

    addComponentListener(new ComponentEvents());

    setDividerWidth(0);

    UI.setSize(this, ModernWidget.MAX_SIZE_24);
  }

  /**
   * Sets the extent of how far the slider can be.
   *
   * @param size the new min component size
   */
  public void setMinComponentSize(int size) {
    // mMinComponentSize = size;

    // mDividerLocation = Math.max(mMinComponentSize, Math.min(getWidth() -
    // getInsets().right - mMinComponentSize, mDividerLocation));

    resize();
  }

  /**
   * Sets the divider width.
   *
   * @param width the new divider width
   * @return the split pane
   */
  public SplitPane setDividerWidth(int width) {
    mDividerHalfWidth = width; // Math.max(1, width);
    mDividerWidth = 2 * mDividerHalfWidth + 1;

    return this;
  }

  /**
   * Resize.
   */
  public abstract void resize();

  /**
   * Gets the checks if is draggable.
   *
   * @param e the e
   * @return the checks if is draggable
   */
  public abstract int getIsDraggable(MouseEvent e);

  /*
   * public void setComponent1(JComponent c1) { if (mC1 != null) { remove(mC1); }
   * 
   * mC1 = c1;
   * 
   * if (c1 != null) { add(c1); }
   * 
   * resize(); }
   * 
   * public void setComponent2(JComponent c2) { if (mC2 != null) { remove(mC2); }
   * 
   * mC2 = c2;
   * 
   * if (c2 != null) { add(c2); }
   * 
   * resize(); }
   */

  /**
   * Replace component.
   *
   * @param c     the c
   * @param index the index
   */
  public void replaceComponent(JComponent c, int index) {
    replaceComponent(c, mDividerLocations.get(index), index);
  }

  /**
   * Replace a component in the divider if it exists otherwise add a new
   * component.
   *
   * @param c               the c
   * @param dividerLocation the divider location
   * @param index           the index
   */
  public void replaceComponent(JComponent c, double dividerLocation, int index) {
    removeComponent(index);

    mComponents.add(index, c);
    mDividerLocations.add(index, Mathematics.bound(dividerLocation, mMinSize, 1.0 - mMinSize));
    add(c);

    resize();
  }

  /**
   * Adds the.
   *
   * @param c               the c
   * @param dividerLocation the divider location
   */
  public void add(JComponent c, double dividerLocation) {
    addComponent(c, dividerLocation);
  }

  /**
   * Add a component and indicate how large you would like it to be.
   *
   * @param c               the c
   * @param dividerLocation the divider location
   */
  public void addComponent(JComponent c, double dividerLocation) {
    mComponents.add(c);
    mDividerLocations.add(Mathematics.bound(dividerLocation, mMinSize, 1.0 - mMinSize));
    add(c);

    resize();
  }

  /**
   * Adds the empty.
   *
   * @param dividerLocation the divider location
   */
  public void addEmpty(double dividerLocation) {
    add(new ModernComponent(), dividerLocation);
  }

  /**
   * Removes the component.
   *
   * @param index the index
   */
  public void removeComponent(int index) {
    if (index < 0 || index >= mComponents.size()) {
      return;
    }

    // System.err.println("remove " + index + " " + mComponents.get(index));

    remove(mComponents.get(index));

    mComponents.remove(index);
    mDividerLocations.remove(index);

    resize();
  }

  /*
   * private void addMouseListeners(JComponent component) { if (component == null)
   * { return; }
   * 
   * component.addMouseListener(this); component.addMouseMotionListener(this);
   * 
   * for (int i = 0; i < component.getComponentCount(); ++i) {
   * addMouseListeners((JComponent)component.getComponent(i)); } }
   */

  /*
   * private void removeMouseListeners(JComponent component) {
   * component.removeMouseListener(this);
   * component.removeMouseMotionListener(this);
   * 
   * for (int i = 0; i < component.getComponentCount(); ++i) {
   * removeMouseListeners((JComponent)component.getComponent(i)); } }
   */

  /**
   * Convert coordinates to reference.
   *
   * @param e the e
   * @return the point
   */
  protected Point convertCoordinatesToReference(MouseEvent e) {
    return convertCoordinatesToReference((Component) e.getSource(), e.getX(), e.getY());
  }

  /**
   * Convert coordinates to reference.
   *
   * @param source the source
   * @param x      the x
   * @param y      the y
   * @return the point
   */
  protected Point convertCoordinatesToReference(Component source, int x, int y) {
    return SwingUtilities.convertPoint(source, x, y, this);
  }

  /**
   * Set whether the user can resize the panel.
   *
   * @param resizable true if user can resize the bar, false otherwise.
   */
  public final void setIsResizable(boolean resizable) {
    mResizable = resizable;
  }

  /**
   * Sets the normalized divider location in pixels.
   */
  // public void setDividerLocation(int dividerLocation) {
  // mDividerLocation = dividerLocation; //Math.max(minComponentSize,
  // Math.min(dividerLocation, getWidth() - minComponentSize));

  // resize();
  // }

  protected void refresh() {
    revalidate();
    repaint();
  }
}
