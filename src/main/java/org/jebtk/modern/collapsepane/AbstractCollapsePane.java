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
package org.jebtk.modern.collapsepane;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.border.Border;

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.event.ChangeEventProducer;
import org.jebtk.core.event.ChangeListener;
import org.jebtk.core.event.ChangeListeners;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.event.HighlightEvent;
import org.jebtk.modern.event.HighlightEventProducer;
import org.jebtk.modern.event.HighlightListener;
import org.jebtk.modern.event.HighlightListeners;
import org.jebtk.modern.scrollpane.VertScrollWidget;

/**
 * A collapsable pane consists of a header and an element. The element can be
 * visible or invisible triggered when the user clicks on the header. It
 * functions similarly to a tree branch expansion except there is no nesting and
 * allows for more disparate and complex UI items, whereas FlatTree renders
 * nodes uniformly with a renderer.
 *
 * @author Antony Holmes
 *
 */
public abstract class AbstractCollapsePane extends VertScrollWidget
    implements ChangeEventProducer, HighlightEventProducer {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constant INC.
   */
  public static final int INC = 60;

  /**
   * The constant TAB_HIDDEN.
   */
  public static final String TAB_HIDDEN = "tab_hidden";

  /**
   * The member header height.
   */
  protected int mHeaderHeight = ModernWidget.WIDGET_HEIGHT;

  /**
   * The member name.
   */
  protected List<String> mTabNames = new CopyOnWriteArrayList<String>();

  /**
   * The member components.
   */
  protected List<Component> mComponents = new CopyOnWriteArrayList<Component>();

  /**
   * The member expanded.
   */
  protected List<Boolean> mExpanded = new CopyOnWriteArrayList<Boolean>();

  protected List<Integer> mHeights = new CopyOnWriteArrayList<Integer>();

  /**
   * Keep track of a components index in the tab list.
   */
  protected Map<Component, Integer> mComponentMap = new HashMap<Component, Integer>();

  /**
   * The member node renderer.
   */
  protected ModernCollapseNodeRenderer mNodeRenderer = new ModernCollapseNodeRenderer();

  /** The m listeners. */
  protected ChangeListeners mListeners = new ChangeListeners();

  private final HighlightListeners mHighlightListeners = new HighlightListeners();

  /** The m previous width. */
  private int mPreviousWidth = -1;

  /** The m previous height. */
  private int mPreviousHeight = -1;

  /** The m size. */
  private Dimension mSize;

  private int mSelectedIndex = -1;

  // protected int mPadding = DOUBLE_PADDING;

  /**
   * The Class ComponentEvents.
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
      updateSize(-1);
    }
  }

  /*
   * private class ChangeEvents implements ChangeListener {
   * 
   * @Override public void changed(ChangeEvent e) {
   * updateSize(mComponentMap.get(e.getSource())); } }
   */

  /**
   * Instantiates a new abstract collapse pane.
   */
  public AbstractCollapsePane() {
    setLayout(null);

    addComponentListener(new ComponentEvents());

    addChangeListener((ChangeEvent e) -> {
      updateSize(mSelectedIndex);
    });
  }

  /**
   * Set the renderer for displaying nodes.
   *
   * @param nodeRenderer the new node renderer
   */
  public void setNodeRenderer(ModernCollapseNodeRenderer nodeRenderer) {
    mNodeRenderer = nodeRenderer;

    repaint();
  }

  /**
   * Returns the current renderer.
   *
   * @return the node renderer
   */
  public ModernCollapseNodeRenderer getNodeRenderer() {
    return mNodeRenderer;
  }

  /**
   * Sets the header height.
   *
   * @param height the new header height
   */
  public void setHeaderHeight(int height) {
    mHeaderHeight = height;
  }

  public void addTab(String name, Component component) {
    addTab(name, component, false);
  }

  /**
   * Adds a tab to the control.
   *
   * @param name     The name of the tab.
   * @param c1       The component to be placed in the tab.
   * @param expanded Whether the tab is shown in its expanded view.
   */
  public void addTab(String name, Component c1, boolean expanded) {
    mTabNames.add(name);
    mComponents.add(c1);
    mHeights.add(c1.getPreferredSize().height);
    mExpanded.add(expanded);
    mComponentMap.put(c1, mComponentMap.size());

    // c1.setVisible(expanded);

    add(c1);

    // if (c1 instanceof AbstractCollapsePane) {
    // ((AbstractCollapsePane)c1).addChangeListener(new ChangeEvents());
    // }

    c1.addPropertyChangeListener("preferredSize", (PropertyChangeEvent e) -> {
      updateSize(mComponentMap.get(e.getSource()));
    });

    updateSize(mComponentMap.size() - 1);
  }

  /**
   * Return the number of tabs.
   *
   * @return the int
   */
  public int tabCount() {
    return mTabNames.size();
  }

  /**
   * Return the component associated with tab i.
   *
   * @param i the i
   * @return the tab component
   */
  public Component getTabComponent(int i) {
    return mComponents.get(i);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.ModernComponent#setBorder(javax.swing.border.Border)
   */
  @Override
  public void setBorder(Border border) {
    super.setBorder(border);

    dimChange();
  }

  public int getCompHeight(int i) {
    return mHeights.get(i);
  }

  // public void setCompHeight(int i, int h) {
  // mHeights.set(i, h);
  // }

  /**
   * Resizes the collapse pane without indicating to parents that it has changed.
   *
   * @param index the index
   */
  private void updateSize(int index) {
    Dimension d = layoutTabs(index);

    mSize = new Dimension(getWidth(), d.height);

    setPreferredSize(mSize);
    // setSize(mSize);

    // System.err.println("resize me");

    revalidate();
    repaint();

    Container parent = getParent();

    if (parent != null) {
      parent.revalidate();
      parent.repaint();
    }
  }

  /**
   * Dim change.
   */
  private void dimChange() {
    if (getWidth() != mPreviousWidth || getHeight() != mPreviousHeight) {
      updateSize(-1);

      mPreviousWidth = getWidth();
      mPreviousHeight = getHeight();

      // setPreferredSize(mSize);
    }
  }

  /**
   * Layout tabs.
   *
   * @param index the index
   * @return the int
   */
  public Dimension layoutTabs(int index) {
    if (mTabNames == null || mComponents == null || mExpanded == null) {
      return null;
    }

    int maxWidth = 0;
    int maxHeight = getInsets().top + mHeaderHeight;

    int x = getInsets().left;
    int w = getWidth() - getInsets().left - getInsets().right;

    for (int i = 0; i < mTabNames.size(); ++i) {
      Component c = mComponents.get(i);

      // maxHeight += mHeaderHeight;

      Dimension p = c.getPreferredSize();

      maxWidth = Math.max(maxWidth, p.width);

      // c.setVisible(mExpanded.get(i));

      int h = mExpanded.get(i) ? p.height : 0;

      // if (mExpanded.get(i)) {
      // c.getPreferredSize().height;

      // Reduce the number of component resizings
      if (i >= index) {
        c.setBounds(x, maxHeight, w, h);
      }

      // maxHeight += h;
      // } else {
      // c.setBounds(x, maxHeight, w, h);
      // }

      maxHeight += h + mHeaderHeight;

      // System.err.println("root " + maxHeight + " " + c.getPreferredSize());
    }

    // repaint();
    // revalidate();

    return new Dimension(getWidth(), maxHeight - mHeaderHeight);
  }

  /**
   * Invert expanded.
   *
   * @param index the i
   */
  protected void invertExpanded(int index) {
    mExpanded.set(index, !mExpanded.get(index));

    // mComponents.get(index).setVisible(mExpanded.get(index));

    // Unnecessary with animation
    // adjustSize(index);

    mSelectedIndex = index;

    fireChanged();

    // fireClicked(new ModernClickEvent(this, TAB_HIDDEN));
  }

  public Component getSelectedComponent() {
    return mComponents.get(getSelectedIndex());
  }

  public int getSelectedIndex() {
    return mSelectedIndex;
  }

  /**
   * Set whether all panes are in their expanded (visible) view or not.
   *
   * @param ex the new expanded
   */
  public void setExpanded(boolean ex) {
    for (int i = 0; i < mComponents.size(); ++i) {
      mExpanded.set(i, ex);

      // mComponents.get(i).setVisible(mExpanded.get(i));
    }

    updateSize(-1);
  }

  /**
   * Clear.
   */
  public void clear() {
    mSelectedIndex = -1;
    mTabNames.clear();
    mComponents.clear();
    mComponentMap.clear();
    mExpanded.clear();

    removeAll();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.event.ChangeEventProducer#addChangeListener(org.abh.common.
   * event.ChangeListener)
   */
  @Override
  public void addChangeListener(ChangeListener l) {
    mListeners.addChangeListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.event.ChangeEventProducer#removeChangeListener(org.abh.
   * common. event.ChangeListener)
   */
  @Override
  public void removeChangeListener(ChangeListener l) {
    mListeners.removeChangeListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.event.ChangeEventProducer#fireChanged(org.abh.common.event.
   * ChangeEvent)
   */
  @Override
  public void fireChanged(ChangeEvent e) {
    mListeners.fireChanged(e);
  }

  /**
   * Fire changed.
   */
  public void fireChanged() {
    fireChanged(new ChangeEvent(this));
  }

  public boolean getExpanded(int index) {
    return mExpanded.get(index);
  }

  @Override
  public void addHighlightListener(HighlightListener l) {
    mHighlightListeners.addHighlightListener(l);
  }

  @Override
  public void removeHighlightListener(HighlightListener l) {
    mHighlightListeners.removeHighlightListener(l);
  }

  public void fireHighlighted() {
    fireHighlighted(new HighlightEvent(this, mSelectedIndex));
  }

  @Override
  public void fireHighlighted(HighlightEvent e) {
    mHighlightListeners.fireHighlighted(e);
  }
}
