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
package org.jebtk.modern.scrollpane;

import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.jebtk.core.event.ChangeEvent;
import org.jebtk.core.event.ChangeListener;
import org.jebtk.modern.ModernComponent;
import org.jebtk.modern.ModernFocusableWidget;
import org.jebtk.modern.MouseUtils;
import org.jebtk.modern.UI;
import org.jebtk.modern.collapsepane.AbstractCollapsePane;
import org.jebtk.modern.dataview.ModernData;
import org.jebtk.modern.dataview.ModernDataViewListener;
import org.jebtk.modern.event.ModernClickEvent;
import org.jebtk.modern.event.ModernClickListener;
import org.jebtk.modern.graphics.CanvasListener;
import org.jebtk.modern.graphics.ModernCanvas;
import org.jebtk.modern.list.ModernList;
import org.jebtk.modern.table.ModernRowTable;
import org.jebtk.modern.table.ModernSpreadsheet;
import org.jebtk.modern.table.ModernTable;
import org.jebtk.modern.tree.ModernTree;

/**
 * Scroll pane control for scrolling around components bigger than the on screen
 * view.
 *
 * @author Antony Holmes
 *
 */
public class ModernScrollPane extends ModernFocusableWidget implements ModernClickListener, HierarchyBoundsListener,
    ModernDataViewListener, DocumentListener, CanvasListener, ChangeListener {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /** The Constant DEFAULT_Z. */
  private static final Integer DEFAULT_Z = JLayeredPane.DEFAULT_LAYER;

  /** The Constant FLOAT_Z. */
  private static final Integer FLOAT_Z = JLayeredPane.MODAL_LAYER;

  /**
   * The member component.
   */
  JComponent mComponent = null;

  /**
   * The member v scroll bar policy.
   */
  ScrollBarPolicy mVScrollBarPolicy = ScrollBarPolicy.AUTO;

  /**
   * The relative offset of the component.
   */
  private int mComponentVOffset = 0;

  /**
   * Whether the scrollbar should be visible.
   */
  // private boolean mVScrollBar.setVisible(false;

  /**
   * Whether the scroll bar is needed (independent of whether it should be
   * displayed or not).
   */
  boolean mVScrollBarNeeded = false;

  /**
   * The member v scroll bar.
   */
  ModernScrollBar mVScrollBar = null; // new ModernVScrollBarOffice(); //new
  // ModernVScrollBar();

  //
  // Horizontal
  //

  /**
   * The member h scroll bar policy.
   */
  ScrollBarPolicy mHScrollBarPolicy = ScrollBarPolicy.AUTO;

  /**
   * The relative offset of the component.
   */
  private int mComponentHOffset = 0;

  /**
   * Whether the scrollbar should be visible.
   */
  // private boolean mHScrollBar.setVisible(false;

  /**
   * The member h scroll bar needed.
   */
  boolean mHScrollBarNeeded = false;

  /**
   * The member h scroll bar.
   */
  ModernScrollBar mHScrollBar = null; // new ModernHScrollBarOffice(); //new
  // ModernHScrollBar();

  //
  // Common
  //

  private ModernTableScrollTo mScrollTo = null;

  /**
   * For when users hold down on a scroll button, the scroll will auto scroll.
   */
  private Timer mScrollTimer = null;

  /**
   * The member top header.
   */
  JViewport mTopHeader = null;

  /**
   * The member footer.
   */
  JViewport mFooter = null;

  /**
   * The member left header.
   */
  JViewport mLeftHeader = null;

  /**
   * The member bottom left footer.
   */
  JViewport mBottomLeftFooter = null;

  /**
   * The member canvas.
   */
  // private ModernCanvas mCanvas = null;

  /**
   * The member top header offset.
   */
  int mTopHeaderOffset = 0;

  /**
   * The member footer offset.
   */
  int mFooterOffset = 0;

  /**
   * The member left header offset.
   */
  int mLeftHeaderOffset = 0;

  /**
   * The member show clip border.
   */
  boolean mShowClipBorder = false;

  /**
   * The member top left header.
   */
  JViewport mTopLeftHeader = null;

  /**
   * The member inc x.
   */
  private double mIncX = 0;

  /**
   * The member inc y.
   */
  private double mIncY = 0;

  /**
   * The member hover.
   */
  boolean mHover = false;

  /**
   * The member comp insets.
   */
  // private Insets mCompInsets;

  /**
   * The member int h.
   */
  int mIntH = 0;

  /**
   * The member int w.
   */
  int mIntW = 0;

  /** The m layer panel. */
  private JLayeredPane mLayerPanel = new JLayeredPane();

  /** The m H scroll bar position. */
  ScrollBarLocation mHScrollBarPosition = ScrollBarLocation.SIDE;

  /** The m V scroll bar location. */
  ScrollBarLocation mVScrollBarLocation = ScrollBarLocation.SIDE;

  /** The m V scroll sep. */
  int mVScrollSep = 0;

  /** The m H scroll sep. */
  int mHScrollSep = 0;

  /** The m viewport. */
  JViewport mViewport;

  /**
   * Increments vertical scroll, should only be used by a timer.
   *
   * @author Antony Holmes
   */
  private class IncrementScroll implements ActionListener {

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
      incrementNormalizedScrollBarPosition(mIncX, mIncY);
    }
  }

  /**
   * Deal with dragging on the component to force the the list to scroll.
   * 
   * @author Antony Holmes
   *
   */
  private class InnerComponentMouseMotionEvents extends MouseAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseDragged(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseDragged(MouseEvent e) {
      if (e.getX() <= 0) {
        mIncX = -mHScrollBar.getScrollStepSize();
      } else if (e.getY() >= getWidth()) {
        mIncX = mHScrollBar.getScrollStepSize();
      } else {
        mIncX = 0;
      }

      if (e.getY() <= 0) {
        mIncY = -mVScrollBar.getScrollStepSize();
      } else if (e.getY() >= getHeight()) {
        mIncY = mVScrollBar.getScrollStepSize();
      } else {
        mIncY = 0;
      }

      if (mIncX != 0 || mIncY != 0) {
        mScrollTimer.start();
      }
    }
  }

  /**
   * The class InnerComponentMouseEvents.
   */
  private class InnerComponentMouseEvents extends MouseAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseReleased(MouseEvent e) {
      stop();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseExited(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseExited(MouseEvent e) {
      stop();
    }
  }

  /**
   * The class HoverMouseEvents.
   */
  private class HoverMouseEvents extends MouseAdapter {

    /**
     * The member c.
     */
    private JComponent mC;

    /** The m pressed. */
    private volatile boolean mPressed = false;

    /**
     * Instantiates a new hover mouse events.
     *
     * @param c the c
     */
    public HoverMouseEvents(JComponent c) {
      mC = c;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseEntered(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseEntered(MouseEvent e) {
      if (UI.contains(e, mC)) {
        hover(true);
      }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseExited(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseExited(MouseEvent e) {
      if (mPressed) {
        return;
      }

      // Since we are dealing with children, only cause hover to
      // false if we actually exit the bounds of the component

      if (!UI.contains(e, mC)) {
        hover(false);
      }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
     */
    @Override
    public void mousePressed(MouseEvent e) {
      if (UI.contains(e, mC)) {
        mPressed = true;
      }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
     */
    @Override
    public void mouseReleased(MouseEvent e) {
      mPressed = false;

      if (!UI.contains(e, mC)) {
        hover(false);
      }
    }
  }

  /**
   * The class InnerComponentMouseWheelEvents.
   */
  private class InnerComponentMouseWheelEvents implements MouseWheelListener {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.MouseWheelListener#mouseWheelMoved(java.awt.event.
     * MouseWheelEvent)
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
      if (MouseUtils.hasModifiers(e)) {
        return;
      }

      int notches = e.getWheelRotation();

      if (mVScrollBar.getScrollDistance() > 0) {
        // Favor vertical scrolling over horizontal scrolling

        mVScrollBar.getScroller().wheelScroll(notches, mComponent, mVScrollBar);

      } else if (mHScrollBar.getScrollDistance() > 0) {
        // mHScrollBar.mouseWheelScrolled(notches);
        mHScrollBar.getScroller().wheelScroll(notches, mComponent, mHScrollBar);
      } else {
        // do nothing
      }
    }
  }

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
      adjustDisplay();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.ComponentAdapter#componentShown(java.awt.event.
     * ComponentEvent)
     */
    @Override
    public void componentShown(ComponentEvent e) {
      adjustDisplay();
    }
  }

  /**
   * The class ContainerEvents.
   */
  private class ContainerEvents extends ContainerAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.ContainerAdapter#componentAdded(java.awt.event.
     * ContainerEvent)
     */
    @Override
    public void componentAdded(ContainerEvent e) {
      adjustDisplay();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.ContainerAdapter#componentRemoved(java.awt.event.
     * ContainerEvent)
     */
    @Override
    public void componentRemoved(ContainerEvent e) {
      adjustDisplay();
    }
  }

  /**
   * The class VScrollBarEvents.
   */
  private class VScrollBarEvents implements ChangeListener {

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.lib.event.ChangeListener#changed(org.abh.lib.event.ChangeEvent)
     */
    @Override
    public void changed(ChangeEvent e) {
      setComponentVOffset();
    }
  }

  /**
   * The class HScrollBarEvents.
   */
  private class HScrollBarEvents implements ChangeListener {

    /*
     * (non-Javadoc)
     * 
     * @see org.abh.lib.event.ChangeListener#changed(org.abh.lib.event.ChangeEvent)
     */
    @Override
    public void changed(ChangeEvent e) {
      setComponentHOffset();
    }
  }

  /**
   * The Class HScrollKeyEvents.
   */
  private class HScrollKeyEvents extends KeyAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
     */
    @Override
    public void keyPressed(KeyEvent e) {
      // Favor vertical scrolling over horizontal scrolling

      int k = e.getKeyCode();

      boolean up = k == KeyEvent.VK_UP || k == KeyEvent.VK_LEFT;

      if (mHScrollBar.getScrollDistance() > 0) {
        mHScrollBar.getScroller().keyScroll(up, mComponent, mHScrollBar);
      } else {
        if (mVScrollBar.getScrollDistance() > 0) {
          mVScrollBar.getScroller().keyScroll(up, mComponent, mVScrollBar);
        }
      }
    }
  }

  /**
   * The Class VScrollKeyEvents.
   */
  private class VScrollKeyEvents extends KeyAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
     */
    @Override
    public void keyPressed(KeyEvent e) {
      // Favor vertical scrolling over horizontal scrolling

      int k = e.getKeyCode();

      boolean up = k == KeyEvent.VK_UP || k == KeyEvent.VK_LEFT;

      if (mVScrollBar.getScrollDistance() > 0) {
        mVScrollBar.getScroller().keyScroll(up, mComponent, mVScrollBar);
      } else if (mHScrollBar.getScrollDistance() > 0) {
        mHScrollBar.getScroller().keyScroll(up, mComponent, mHScrollBar);
      } else {
        // do nothing
      }
    }
  }

  /**
   * The Class InnerCompKeyEvents.
   */
  private class InnerCompKeyEvents extends KeyAdapter {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
     */
    @Override
    public void keyPressed(KeyEvent e) {
      // Favor vertical scrolling over horizontal scrolling

      int k = e.getKeyCode();

      boolean up = k == KeyEvent.VK_UP || k == KeyEvent.VK_LEFT;

      switch (k) {
      case KeyEvent.VK_UP:
      case KeyEvent.VK_DOWN:
        if (mVScrollBar.getScrollDistance() > 0) {
          mVScrollBar.getScroller().keyScroll(up, mComponent, mVScrollBar);
        } else if (mHScrollBar.getScrollDistance() > 0) {
          mHScrollBar.getScroller().keyScroll(up, mComponent, mHScrollBar);
        } else {
          // do nothing
        }

        break;
      case KeyEvent.VK_LEFT:
      case KeyEvent.VK_RIGHT:
        if (mHScrollBar.getScrollDistance() > 0) {
          mHScrollBar.getScroller().keyScroll(up, mComponent, mHScrollBar);
        } else if (mVScrollBar.getScrollDistance() > 0) {
          mVScrollBar.getScroller().keyScroll(up, mComponent, mVScrollBar);
        } else {
          // do nothing
        }

        break;

      }
    }
  }

  private class ScrollEvents implements ScrollListener {

    @Override
    public void scrollTo(ScrollEvent e) {
      // System.err.println("scroll to " + e.getSource() + " " + e.mRect);

      mScrollTo.scrollTo(e.mRect);
    }

  }

  /**
   * Instantiates a new modern scroll pane.
   *
   * @param component the component
   */
  public ModernScrollPane(JComponent component) {
    mComponent = component;

    setup();
  }

  /**
   * Instantiates a new modern scroll pane.
   *
   * @param table the table
   */
  public ModernScrollPane(ModernSpreadsheet table) {
    mComponent = table;

    setup();

    /*
     * if (table.getShowHeader()) { setTopHeader(new
     * ModernTableHeaderColumn(table)); }
     * 
     * if (table.getShowRowHeader()) { setLeftHeader(new
     * ModernTableHeaderRow(table)); }
     * 
     * if (table.getShowHeader() && table.getShowRowHeader()) { setTopLeftHeader(new
     * ModernMatrixTableCorner(table)); }
     */

    table.getRowModel().addChangeListener(this);
    table.getColumnModel().addChangeListener(this);

    configureTable(table);

  }

  /**
   * Instantiates a new modern scroll pane.
   *
   * @param table the table
   */
  public ModernScrollPane(ModernRowTable table) {
    mComponent = table;

    setup();

    /*
     * if (table.getShowHeader()) { setTopHeader(new
     * ModernTableHeaderColumn(table)); }
     */

    table.getColumnModel().addChangeListener(this);

    configureTable(table);

  }

  /**
   * Configures the scroll pane to work well with a table using row and column
   * scrolling rather than pixel scrolling.
   *
   * @param table the new up
   */
  public void configureTable(ModernTable table) {
    mVScrollBar.setScroller(new ModernTableScrollerV(table));
    mHScrollBar.setScroller(new ModernTableScrollerH(table));

    table.addDataViewListener(this);
    // table.getCellSelectionModel().addSelectionListener(new
    // ModernTableSelectionScrollListener(table, mVScrollBar, mHScrollBar));
  }

  /**
   * Instantiates a new modern scroll pane.
   *
   * @param list the list
   */
  public ModernScrollPane(ModernList<?> list) {
    mComponent = list;

    list.addDataViewListener(this);

    setup();
  }

  /**
   * Instantiates a new modern scroll pane.
   *
   * @param pane the pane
   */
  public ModernScrollPane(AbstractCollapsePane pane) {
    mComponent = pane;

    pane.addChangeListener(this);

    setup();
  }

  /**
   * Instantiates a new modern scroll pane.
   *
   * @param data the data
   */
  public ModernScrollPane(ModernData data) {
    mComponent = data;

    data.addDataViewListener(this);

    setup();
  }

  /**
   * Instantiates a new modern scroll pane.
   *
   * @param canvas the canvas
   */
  public ModernScrollPane(ModernCanvas canvas) {
    mComponent = canvas;

    setup();
  }

  /**
   * Instantiates a new modern scroll pane.
   *
   * @param tree the tree
   */
  public ModernScrollPane(ModernTree<?> tree) {
    mComponent = tree;

    setup();

    mVScrollBar.setScroller(new ModernTreeScrollerV());
    tree.getSelectionModel().addSelectionListener(new ModernTreeSelectionScrollV(tree, mVScrollBar));
  }

  /**
   * Instantiates a new modern scroll pane.
   *
   * @param textArea the text area
   */
  public ModernScrollPane(JTextArea textArea) {
    mComponent = textArea;

    textArea.getDocument().addDocumentListener(this);

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    // setLayout(null);
    setBody(mLayerPanel);

    mScrollTo = new ModernTableScrollTo(this);

    mScrollTimer = new Timer(0, new IncrementScroll());
    mScrollTimer.setDelay(ModernScrollBar.SCROLL_TIMER_DELAY_MS);

    if (mComponent instanceof ModernCanvas) {
      ((ModernCanvas) mComponent).addCanvasListener(this);
    }

    if (mComponent instanceof ModernComponent) {
      ModernComponent mc = (ModernComponent) mComponent;
      mc.getScrollListeners().addScrollListener(new ScrollEvents());
    }

    // else {
    // mCompInsets = (Insets)mComponent.getInsets().clone();

    mComponent.addComponentListener(new ComponentEvents());
    mComponent.addContainerListener(new ContainerEvents());
    // }

    mViewport = createViewport(mComponent);

    // mLayerPanel.add(mComponent, DEFAULT_Z);
    mLayerPanel.add(mViewport, DEFAULT_Z);

    PropertyChangeListener l = new PropertyChangeListener() {
      @Override
      public void propertyChange(PropertyChangeEvent e) {
        adjustDisplay();
      }
    };

    mVScrollBar = new ModernVScrollBarMac(this, mComponent);
    mHScrollBar = new ModernHScrollBarMac(this, mComponent);

    setVerticalScrollBarPolicy(ScrollBarPolicy.AUTO);
    setVScrollBarLocation(ScrollBarLocation.SIDE);
    mVScrollBar.addChangeListener(new VScrollBarEvents());
    mVScrollBar.addKeyListener(new VScrollKeyEvents());
    mVScrollBar.setView(mComponent);
    mVScrollBar.addPropertyChangeListener("preferredSize", l);

    setHorizontalScrollBarPolicy(ScrollBarPolicy.AUTO);
    setHScrollBarLocation(ScrollBarLocation.SIDE);
    mHScrollBar.addChangeListener(new HScrollBarEvents());
    mHScrollBar.addKeyListener(new HScrollKeyEvents());
    mHScrollBar.setView(mComponent);
    mHScrollBar.addPropertyChangeListener("preferredSize", l);

    mComponent.addMouseListener(new InnerComponentMouseEvents());
    mComponent.addMouseMotionListener(new InnerComponentMouseMotionEvents());
    mComponent.addMouseWheelListener(new InnerComponentMouseWheelEvents());
    mComponent.addKeyListener(new InnerCompKeyEvents());

    addComponentListener(new ComponentEvents());

    // mComponent.addKeyListener(new KeyboardEvents());
    // addKeyListener(new KeyboardEvents());

    //
    // Register this listener to detect when we are inside or outside
    // the boundaries of the scrollpane even when the mouse events are
    // on the children

    HoverMouseEvents ml = new HoverMouseEvents(this);

    addMouseListener(ml);

    // Add the listener to the component and all its children
    UI.addMouseListenerAllChildren(mComponent, ml);

    mVScrollBar.addMouseListener(ml);
    mHScrollBar.addMouseListener(ml);

    adjustDisplay();
  }

  /**
   * Returns the component the scroll pane is showing.
   *
   * @return the component
   */
  public JComponent getComponent() {
    return mComponent;
  }

  /**
   * Sets the top header.
   *
   * @param header the new top header
   */
  public void setTopHeader(ModernScrollPaneHeader header) {
    mTopHeader = createViewport(header);
    mTopHeaderOffset = header.getFixedDimension();
    mLayerPanel.add(header, DEFAULT_Z);

    repaint();
    revalidate();
  }

  /**
   * Creates a default viewport control.
   *
   * @param c the c
   * @return the j viewport
   */
  private static JViewport createViewport(Component c) {
    JViewport port = new JViewport();
    port.setOpaque(false);
    port.setView(c);

    return port;
  }

  /**
   * Sets the left header.
   *
   * @param header the new left header
   */
  public void setLeftHeader(ModernScrollPaneHeader header) {
    if (mLeftHeader != null) {
      remove(mLeftHeader);
    }

    mLeftHeader = createViewport(header);
    mLeftHeaderOffset = header.getFixedDimension();
    mLayerPanel.add(header, DEFAULT_Z);

    adjustDisplay();
  }

  /**
   * Sets the footer.
   *
   * @param footer the footer
   * @param height the height
   */
  public void setFooter(ModernCanvas footer, int height) {
    mFooter = createViewport(footer);
    mFooterOffset = height;
    mLayerPanel.add(footer, DEFAULT_Z);
  }

  /**
   * Sets the top left header.
   *
   * @param header the new top left header
   */
  public void setTopLeftHeader(ModernComponent header) {
    if (mTopLeftHeader != null) {
      remove(mTopLeftHeader);
    }

    mTopLeftHeader = createViewport(header);
    mLayerPanel.add(header, DEFAULT_Z);

    adjustDisplay();
  }

  /**
   * Sets the bottom left footer.
   *
   * @param footer the new bottom left footer
   */
  public void setBottomLeftFooter(ModernComponent footer) {
    mBottomLeftFooter = createViewport(footer);
    mLayerPanel.add(footer, DEFAULT_Z);
  }

  /**
   * Sets the v scroll bar.
   *
   * @return the v scroll bar
   */
  // public void setVScrollBar(ModernScrollBar scrollBar) {
  // mVScrollBar = scrollBar;
  //
  // mVScrollBar.addChangeListener(new VScrollBarEvents());
  // mVScrollBar.setCanvas(mCanvas);
  // }

  /**
   * Gets the v scroll bar.
   *
   * @return the v scroll bar
   */
  public ModernScrollBar getVScrollBar() {
    return mVScrollBar;
  }

  /**
   * Sets the h scroll bar.
   *
   * @return the h scroll bar
   */
  // public void setHScrollBar(ModernScrollBar scrollBar) {
  // mHScrollBar = scrollBar;
  // mHScrollBar.addChangeListener(new HScrollBarEvents());
  // mHScrollBar.setCanvas(mCanvas);
  // }

  /**
   * Gets the h scroll bar.
   *
   * @return the h scroll bar
   */
  public ModernScrollBar getHScrollBar() {
    return mHScrollBar;
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

    adjustDisplay();
  }

  /**
   * Make sure the scroll bars are in sync with the component.
   */
  private void adjustScrollBars() {
    showScrollbars();

    setupScrollBars();
  }

  /**
   * Setup scroll bars.
   */
  private void setupScrollBars() {
    vScrollBarSetup();

    hScrollBarSetup();
  }

  /**
   * Make sure the whole display is in sync with the component.
   */
  private void adjustDisplay() {
    // adjustScrollBars();

    showScrollbars();

    adjustComponentSize();

    setupScrollBars();

    // System.err.println("sdfsdf " + mComponent.getBounds() + " " +
    // mVScrollBar.getBounds() + " " + mVScrollBar.isVisible());

    mLayerPanel.revalidate();
    mLayerPanel.repaint();
  }

  /**
   * Hover.
   *
   * @param hover the hover
   */
  private void hover(boolean hover) {
    if (hover == mHover) {
      return;
    }

    // Don't bother with this unless we are auto showing the scroll bars
    if (mVScrollBarPolicy != ScrollBarPolicy.AUTO_SHOW && mHScrollBarPolicy != ScrollBarPolicy.AUTO_SHOW) {
      return;
    }

    mHover = hover;

    adjustScrollBars();

    revalidate();
    repaint();
  }

  /**
   * Sets the display.
   */
  private void setDisplay() {
    if (mTopHeader != null) {
      // mTopHeader.updateViewRectangle(mComponentHOffset, 0);
      mTopHeader.setViewPosition(new Point(mComponentHOffset, 0));
    }

    if (mLeftHeader != null) {
      // mLeftHeader.updateViewRectangle(0, mComponentVOffset);
      mTopHeader.setViewPosition(new Point(0, mComponentVOffset));
    }

    if (mFooter != null) {
      // mFooter.updateViewRectangle(mComponentHOffset, 0);
      mFooter.setViewPosition(new Point(mComponentHOffset, 0));
    }

    // if (mCanvas != null) {
    // mCanvas.updateViewRectangle(mComponentHOffset, mComponentVOffset);
    // }

    // Move the viewport
    mViewport.setViewPosition(new Point(mComponentHOffset, mComponentVOffset));
    // mViewport.scrollRectToVisible(r);
    // mComponent.scrollRectToVisible(r);

    // adjustComponentPosition();

    // revalidate();
    // repaint();
  }

  /**
   * Change the size of the component in response to the scrollpane changing size.
   * This will only affect dimensions where the scrollbar is not visible
   */
  private void adjustComponentSize() {
    if (mComponent == null) {
      return;
    }

    int h = mInternalRect.getH();

    int border = (mShowClipBorder ? 1 : 0);

    if (mTopHeader != null) {
      Rectangle b = new Rectangle(mLeftHeaderOffset + border, border,
          mInternalRect.getW() - mLeftHeaderOffset - (mShowClipBorder ? 3 : 0), mTopHeaderOffset);

      // If the scroll bars are visible or auto show is true then resize
      // the components to allow for this
      if (mVScrollBarLocation == ScrollBarLocation.SIDE) {
        if (mVScrollBar.isVisible() || mVScrollBarPolicy == ScrollBarPolicy.AUTO_SHOW) {
          b.width -= mVScrollBar.getFixedDim();
        }
      }

      mTopHeader.setBounds(b);
    }

    if (mLeftHeader != null) {
      Rectangle b = new Rectangle(border, mTopHeaderOffset + border, mLeftHeaderOffset,
          h - mTopHeaderOffset - (mShowClipBorder ? 3 : 0));

      if (mHScrollBarPosition == ScrollBarLocation.SIDE) {
        if (mHScrollBar.isVisible() || mHScrollBarPolicy == ScrollBarPolicy.AUTO_SHOW) {
          b.height -= mHScrollBar.getFixedDim();
        }

        if (mFooter != null) {
          b.height -= mFooterOffset;
        }
      }

      mLeftHeader.setBounds(b);
    }

    if (mFooter != null) {
      Rectangle b = new Rectangle(mLeftHeaderOffset + border, h - mFooterOffset + border,
          mInternalRect.getW() - mLeftHeaderOffset - border, mFooterOffset);

      if (mVScrollBarLocation == ScrollBarLocation.SIDE) {
        if (mVScrollBar.isVisible() || mVScrollBarPolicy == ScrollBarPolicy.AUTO_SHOW) {
          b.width -= mVScrollBar.getFixedDim();
        }

        if (mHScrollBar.isVisible() || mHScrollBarPolicy == ScrollBarPolicy.AUTO_SHOW) {
          b.y -= mHScrollBar.getFixedDim();
        }
      }

      mFooter.setBounds(b);
    }

    if (mLeftHeader != null && mTopHeader != null && mTopLeftHeader != null) {
      Rectangle b = new Rectangle(border, border, mLeftHeaderOffset, mTopHeaderOffset);

      mTopLeftHeader.setBounds(b);
    }

    if (mLeftHeader != null && mFooter != null && mBottomLeftFooter != null) {
      Rectangle b = new Rectangle(border, h - mFooterOffset + border, mLeftHeaderOffset, mFooterOffset);

      if (mHScrollBarPosition == ScrollBarLocation.SIDE) {
        if (mHScrollBar.isVisible() || mHScrollBarPolicy == ScrollBarPolicy.AUTO_SHOW) {
          b.y -= mHScrollBar.getFixedDim();
        }
      }

      mBottomLeftFooter.setBounds(b);
    }

    Rectangle b = new Rectangle((mLeftHeader != null ? mLeftHeaderOffset : 0) + border,
        (mTopHeader != null ? mTopHeaderOffset : 0) + border,
        mInternalRect.getW() - (mLeftHeader != null ? mLeftHeaderOffset : 0) - (mShowClipBorder ? 2 : 0) - border,
        h - (mTopHeader != null ? mTopHeaderOffset : 0) - (mFooter != null ? mFooterOffset : 0)
            - (mShowClipBorder ? 2 : 0) - border);

    if (mVScrollBarLocation == ScrollBarLocation.SIDE) {
      if (mVScrollBar.isVisible() || mVScrollBarPolicy == ScrollBarPolicy.AUTO_SHOW) {
        b.width -= mVScrollBar.getFixedDim() + mVScrollSep;
      }
    }

    if (mHScrollBarPosition == ScrollBarLocation.SIDE) {
      if (mHScrollBar.isVisible() || mHScrollBarPolicy == ScrollBarPolicy.AUTO_SHOW) {
        b.height -= mHScrollBar.getFixedDim() + mHScrollSep;
      }
    }

    // mViewport.setBounds(b);
    mViewport.setSize(b.width, b.height);

    // Causes rendering errors
    // mComponent.setBounds(b);
    // mComponent.setSize(b.width, b.height);
    // mComponent.set(b.width, b.height);

    /*
     * // Set the preferred size of the component based on whether the // scroll
     * bars are visible or not
     * 
     * int w = -1; h = -1;
     * 
     * Dimension p = mComponent.getPreferredSize();
     * 
     * if (!mHScrollBar.isVisible()) { w = p.width; } else { w = b.width; }
     * 
     * if (mVScrollBar.isVisible()) { h = p.height; } else { h = b.height; }
     * 
     * //if ((!mHScrollBar.isVisible() && w != p.width) || //
     * (!mVScrollBar.isVisible() && h != p.height)) { if (w != p.width || h !=
     * p.height) { Dimension d = new Dimension(w, h);
     * mComponent.setPreferredSize(d); }
     */
  }

  /**
   * Adjust the component location within the container. This simulates movement
   * from the scrollbars.
   *
   * @param x the x
   * @param y the y
   */
  /*
   * private void adjustComponentPosition() { if (mComponent == null ||
   * mCompInsets == null || mCanvas != null) { return; }
   * 
   * mComponent.setBorder(BorderService.getInstance().createBorder(-(int)
   * mComponentVOffset + mCompInsets.top, -(int)mComponentHOffset +
   * mCompInsets.left, mCompInsets.bottom, mCompInsets.right));
   * 
   * //System.err.println("scroll border " + mComponent.getInsets()); }
   */

  /**
   * Increment normalized scroll position.
   *
   * @param x the x
   * @param y the y
   */
  private void incrementNormalizedScrollBarPosition(double x, double y) {
    // setNormalizedScrollBarPosition(mNormalizedHScrollBarPosition + x,
    // mNormalizedVScrollBarPosition + y);

    mHScrollBar.incNormalizedScrollPosition(x);
    mVScrollBar.incNormalizedScrollPosition(y);

    setDisplay();
  }

  /**
   * If the canvas forces a change of view, the scroll pane needs to respond and
   * move the scroll pane so the view area is visible.
   */
  private void setScrollBarPositionsFromCanvas() {
    // if (mCanvas == null) {
    // return;
    // }

    Rectangle rect = mComponent.getVisibleRect();

    double nx = Math.max(0, rect.getX() - mIntW);
    double ny = Math.max(0, rect.getY() - mIntH);

    // if (mCanvas != null) {
    nx /= mHScrollBar.getScrollDistance();
    ny /= mVScrollBar.getScrollDistance();
    // } else {
    // nx /= mComponent.getWidth();
    // ny /= mComponent.getHeight();
    // }

    setNormalizedScrollBarPosition(nx, ny);
  }

  /**
   * Sets the normalized scroll position.
   *
   * @param x the x
   * @param y the y
   */
  public void setNormalizedScrollBarPosition(double x, double y) {
    updateNormalizedScrollBarPosition(x, y);

    setDisplay();
  }

  /**
   * Show scrollbars.
   */
  private void showScrollbars() {
    if (mComponent == null) {
      return;
    }

    //
    // Vertical bars
    //

    switch (mVScrollBarPolicy) {
    case AUTO:
      mVScrollBarNeeded = vScrollbarNeeded();
      mVScrollBar.setVisible(mVScrollBarNeeded);
      break;
    case AUTO_SHOW:
      mVScrollBarNeeded = vScrollbarNeeded();
      mVScrollBar.setVisible(mVScrollBarNeeded && mHover);
      break;
    case ALWAYS:
      mVScrollBarNeeded = true;
      mVScrollBar.setVisible(true);
      break;
    default:
      mVScrollBarNeeded = false;
      mVScrollBar.setVisible(false);
      break;
    }

    //
    // Horizontal bars
    //

    switch (mHScrollBarPolicy) {
    case AUTO:
      mHScrollBarNeeded = hScrollbarNeeded();
      mHScrollBar.setVisible(mHScrollBarNeeded);
      break;
    case AUTO_SHOW:
      mHScrollBarNeeded = hScrollbarNeeded();
      mHScrollBar.setVisible(mHScrollBarNeeded && mHover);
      break;
    case ALWAYS:
      mHScrollBarNeeded = true;
      mHScrollBar.setVisible(true);
      break;
    default:
      mHScrollBarNeeded = false;
      mHScrollBar.setVisible(false);
      break;
    }
  }

  /**
   * V scrollbar needed.
   *
   * @return true, if successful
   */
  boolean vScrollbarNeeded() {
    int h = mInternalRect.getH() - mTopHeaderOffset - mFooterOffset;

    // if (mCanvas != null) {
    // return mCanvas.getCanvasSize().getH() > h;
    // } else {
    // return mComponent.getPreferredSize().height > h;
    // }

    return mComponent.getPreferredSize().height > h;
  }

  /**
   * H scrollbar needed.
   *
   * @return true, if successful
   */
  boolean hScrollbarNeeded() {
    int w = mInternalRect.getW() - mLeftHeaderOffset;

    // if (mCanvas != null) {
    // return mCanvas.getCanvasSize().getW() > w;
    // } else {
    // return mComponent.getPreferredSize().width > w;
    // }

    return mComponent.getPreferredSize().width > w;
  }

  //
  // V bar
  //

  /**
   * V scroll bar setup.
   */
  private void vScrollBarSetup() {

    // if (mVScrollBar.isVisible()) {
    Rectangle r = new Rectangle(mInternalRect.getW() - mVScrollBar.getFixedDim(), 0, mVScrollBar.getFixedDim(),
        mInternalRect.getH() - (mHScrollBar.isVisible() ? mHScrollBar.getFixedDim() : 0));

    // if (mHScrollBarPosition == ScrollBarPosition.SIDE) {
    // r.height -= (mHScrollBar.isVisible() ? mHScrollBar.getFixedDimension() :
    // 0);
    // }

    mVScrollBar.setBounds(r);

    // }

    int scrollDistance = 0;

    if (mVScrollBarNeeded) {
      // the actual amount of movable space available
      mIntH = getInternalHeight();

      if (mHScrollBarNeeded) {
        mIntH -= mHScrollBar.getFixedDim();
      }

      // How much space we need to scroll through
      // if (mCanvas != null) {
      // scrollDistance = Math.max(0,
      // mCanvas.getCanvasSize().getH() - mIntH);
      // } else {
      // scrollDistance = Math.max(0,
      // mComponent.getPreferredSize().height - mInternalRect.getH());
      // }

      scrollDistance = Math.max(0, mComponent.getPreferredSize().height - mInternalRect.getH());
    }

    mVScrollBar.setScrollDistance(scrollDistance);

    if (!mVScrollBarNeeded) {
      mVScrollBar.updateNormalizedScrollPosition(0);
      setComponentVOffset();
    }

    // mVScrollBar.setVisible(mVScrollBar.isVisible());
  }

  /**
   * Move the scrollbar n pixels. This will cause the content to move at least n
   * pixels.
   */
  void setComponentVOffset() {
    updateComponentVOffset();

    setDisplay();
  }

  /**
   * Update normalized v scroll position.
   */
  private void updateComponentVOffset() {
    // System.err.println("v p " + mVScrollDistance + " " +
    // mVScrollBar.getNormalizedScrollBarPosition() + " " +(mVScrollDistance *
    // mVScrollBar.getNormalizedScrollBarPosition()) + " " +
    // (int)(mVScrollDistance
    // * mVScrollBar.getNormalizedScrollBarPosition()));

    mComponentVOffset = (int) Math.round(mVScrollBar.getOffset()); // (int)(mVScrollDistance
    // *
    // mVScrollBar.getNormalizedScrollBarPosition());
  }

  /**
   * Update normalized scroll position.
   *
   * @param x the x
   * @param y the y
   */
  private void updateNormalizedScrollBarPosition(double x, double y) {
    updateNormalizedHScrollBarPosition(x);
    updateNormalizedVScrollBarPosition(y);
  }

  /**
   * Update normalized v scroll position.
   *
   * @param p the p
   */
  private void updateNormalizedVScrollBarPosition(double p) {
    mVScrollBar.updateNormalizedScrollPosition(p);
    updateComponentVOffset();
  }

  /**
   * Update normalized h scroll position.
   *
   * @param p the p
   */
  private void updateNormalizedHScrollBarPosition(double p) {
    mHScrollBar.updateNormalizedScrollPosition(p);
    updateComponentHOffset();
  }

  //
  // H Bar
  //

  /**
   * H scroll bar setup.
   */
  private void hScrollBarSetup() {

    // if (mHScrollBar.isVisible()) {
    Rectangle r = new Rectangle(0, mInternalRect.getH() - mHScrollBar.getFixedDim(),
        mInternalRect.getW() - (mVScrollBar.isVisible() ? mVScrollBar.getFixedDim() : 0), mHScrollBar.getFixedDim());

    // if (mHScrollBarPosition == ScrollBarPosition.SIDE) {
    // r.width -= (mVScrollBar.isVisible() ? mVScrollBar.getFixedDimension() :
    // 0);
    // }

    mHScrollBar.setBounds(r);

    int hScrollDistance = 0;

    if (mHScrollBarNeeded) {
      // the width
      mIntW = getInternalWidth();

      if (mVScrollBarNeeded) {
        // Subtract the fixed dimension if the v scroll bar is visible
        mIntW -= mVScrollBar.getFixedDim();
      }

      // if (mCanvas != null) {
      // The difference between the screen estate and the width
      // of the canvas is the scrollable distance
      // hScrollDistance = Math.max(0,
      // // mCanvas.getCanvasSize().getW() - mIntW);
      // } else {
      // hScrollDistance = Math.max(0,
      /// mComponent.getPreferredSize().width - mInternalRect.getW());
      // }

      hScrollDistance = Math.max(0, mComponent.getPreferredSize().width - mInternalRect.getW());
    }

    mHScrollBar.setScrollDistance(hScrollDistance);

    if (!mHScrollBarNeeded) {
      mHScrollBar.updateNormalizedScrollPosition(0);
      setComponentHOffset();
    }

    // mHScrollBar.setVisible(mHScrollBar.isVisible());
  }

  /**
   * Sets the normalized h scroll position.
   */
  void setComponentHOffset() {
    updateComponentHOffset();

    setDisplay();
  }

  /**
   * Update normalized h scroll position.
   */
  private void updateComponentHOffset() {
    mComponentHOffset = (int) Math.round(mHScrollBar.getOffset()); // (int)(mHScrollDistance
    // *
    // mHScrollBar.getNormalizedScrollBarPosition());
  }

  /**
   * Set the space between the view and the vertical scroll bar.
   *
   * @param sep the sep
   * @return the modern scroll pane
   */
  public ModernScrollPane setVScrollSep(int sep) {
    mVScrollSep = sep;

    adjustDisplay();

    return this;
  }

  /**
   * Sets the H scroll sep.
   *
   * @param sep the sep
   * @return the modern scroll pane
   */
  public ModernScrollPane setHScrollSep(int sep) {
    mHScrollSep = sep;

    adjustDisplay();

    return this;
  }

  /**
   * Sets the horizontal scroll bar policy.
   *
   * @param policy the new horizontal scroll bar policy
   * @return the modern scroll pane
   */
  public ModernScrollPane setHorizontalScrollBarPolicy(ScrollBarPolicy policy) {
    mHScrollBarPolicy = policy;

    adjustDisplay();

    return this;
  }

  /**
   * Sets the vertical scroll bar policy.
   *
   * @param policy the new vertical scroll bar policy
   * @return the modern scroll pane
   */
  public ModernScrollPane setVerticalScrollBarPolicy(ScrollBarPolicy policy) {
    mVScrollBarPolicy = policy;

    adjustDisplay();

    return this;
  }

  /**
   * Change both the vertical and horizontal scroll bar location.
   *
   * @param position the position
   * @return the modern scroll pane
   */
  public ModernScrollPane setScrollBarLocation(ScrollBarLocation position) {
    return setScrollBarLocation(position, position);
  }

  /**
   * Change both the vertical and horizontal scroll bar location.
   *
   * @param hl The horizontal scroll bar location.
   * @param vl The vertical scroll bar location.
   * @return the modern scroll pane
   */
  public ModernScrollPane setScrollBarLocation(ScrollBarLocation hl, ScrollBarLocation vl) {
    setHScrollBarLocation(hl);
    setVScrollBarLocation(vl);

    return this;
  }

  /**
   * Determines where the scroll bar appears relative to the content being
   * scrolled. Currently this can either be the side or floating on top.
   *
   * @param position the position
   * @return the modern scroll pane
   */
  public ModernScrollPane setHScrollBarLocation(ScrollBarLocation position) {
    mHScrollBarPosition = position;

    mHScrollBar.setLocation(position);

    mLayerPanel.remove(mHScrollBar);

    if (position == ScrollBarLocation.FLOATING) {
      mLayerPanel.add(mHScrollBar, FLOAT_Z);
    } else {
      mLayerPanel.add(mHScrollBar, DEFAULT_Z);
    }

    adjustDisplay();

    return this;
  }

  /**
   * Determines where the scroll bar appears relative to the content being
   * scrolled. Currently this can either be the side or floating on top.
   *
   * @param position the position
   * @return the modern scroll pane
   */
  public ModernScrollPane setVScrollBarLocation(ScrollBarLocation position) {
    mVScrollBarLocation = position;

    mVScrollBar.setLocation(position);

    mLayerPanel.remove(mVScrollBar);

    if (position == ScrollBarLocation.FLOATING) {
      mLayerPanel.add(mVScrollBar, FLOAT_Z);
    } else {
      mLayerPanel.add(mVScrollBar, DEFAULT_Z);
    }

    adjustDisplay();

    return this;
  }

  /**
   * Set the scrollbar policy for both axes at the same time.
   *
   * @param hPolicy the h policy
   * @param vPolicy the v policy
   * @return the modern scroll pane
   */
  public ModernScrollPane setScrollBarPolicy(ScrollBarPolicy hPolicy, ScrollBarPolicy vPolicy) {
    setHorizontalScrollBarPolicy(hPolicy);
    setVerticalScrollBarPolicy(vPolicy);

    return this;
  }

  /**
   * Sets the scroll bar policy.
   *
   * @param policy the policy
   * @return the modern scroll pane
   */
  public ModernScrollPane setScrollBarPolicy(ScrollBarPolicy policy) {
    return setScrollBarPolicy(policy, policy);
  }

  /**
   * Show clip border.
   *
   * @param showClipBorder the show clip border
   */
  public void showClipBorder(boolean showClipBorder) {
    mShowClipBorder = showClipBorder;

    adjustDisplay();
  }

  /**
   * Resets the scroll bars so they are in the start/zero position so the
   * beginning of the list, tree etc is visible.
   */
  public void resetScrollBars() {
    mVScrollBar.updateNormalizedScrollPosition(0);
    updateComponentVOffset();

    mHScrollBar.updateNormalizedScrollPosition(0);
    updateComponentHOffset();

    adjustDisplay();
  }

  /**
   * Reset v scroll bar.
   */
  public void resetVScrollBar() {
    mVScrollBar.updateNormalizedScrollPosition(0);

    setComponentVOffset();
  }

  /**
   * Reset h scroll bar.
   */
  public void resetHScrollBar() {
    mHScrollBar.updateNormalizedScrollPosition(0);

    setComponentHOffset();
  }

  /**
   * Returns the number of pixels in the y dimension that are available to display
   * content once headers and scrollbar widths are subtracted.
   *
   * @return the internal height
   */
  public int getInternalHeight() {
    return mInternalRect.getH() - mTopHeaderOffset - mFooterOffset;
  }

  /**
   * Gets the internal width.
   *
   * @return the internal width
   */
  public int getInternalWidth() {
    return mInternalRect.getW() - mLeftHeaderOffset;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernClickListener#clicked(org.abh.lib.ui.
   * modern .event.ModernClickEvent)
   */
  @Override
  public void clicked(ModernClickEvent e) {
    adjustDisplay();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.HierarchyBoundsListener#ancestorMoved(java.awt.event.
   * HierarchyEvent)
   */
  @Override
  public void ancestorMoved(HierarchyEvent e) {
    adjustDisplay();
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.HierarchyBoundsListener#ancestorResized(java.awt.event.
   * HierarchyEvent)
   */
  @Override
  public void ancestorResized(HierarchyEvent e) {
    adjustDisplay();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.dataview.ModernDataViewListener#dataChanged(org.abh.
   * lib .event.ChangeEvent)
   */
  @Override
  public void dataChanged(ChangeEvent e) {
    // System.err.println("Data changed " + mComponent.getName() + " " +
    // mCanvas.getCanvasSize());

    adjustDisplay();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.dataview.ModernDataViewListener#dataUpdated(org.abh.
   * lib .event.ChangeEvent)
   */
  @Override
  public void dataUpdated(ChangeEvent e) {
    // adjustDisplay();
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.event.DocumentListener#changedUpdate(javax.swing.event.
   * DocumentEvent)
   */
  @Override
  public void changedUpdate(DocumentEvent e) {
    adjustDisplay();
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.event.DocumentListener#insertUpdate(javax.swing.event.
   * DocumentEvent)
   */
  @Override
  public void insertUpdate(DocumentEvent e) {
    adjustDisplay();
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.event.DocumentListener#removeUpdate(javax.swing.event.
   * DocumentEvent)
   */
  @Override
  public void removeUpdate(DocumentEvent e) {
    adjustDisplay();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.graphics.ModernCanvasListener#canvasChanged(org.abh.
   * lib .event.ChangeEvent)
   */
  @Override
  public void canvasChanged(ChangeEvent e) {
    adjustDisplay();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.graphics.ModernCanvasListener#canvasResized(org.abh.
   * common. event.ChangeEvent)
   */
  @Override
  public void canvasResized(ChangeEvent e) {
    adjustDisplay();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.graphics.ModernCanvasListener#redrawCanvas(org.abh.
   * lib. event.ChangeEvent)
   */
  @Override
  public void redrawCanvas(ChangeEvent e) {
    repaint();
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.lib.ui.modern.graphics.ModernCanvasListener#canvasScrolled(org.abh.
   * lib.event.ChangeEvent)
   */
  @Override
  public void canvasScrolled(ChangeEvent e) {
    setScrollBarPositionsFromCanvas();
  }

  /**
   * Stop.
   */
  private void stop() {
    mScrollTimer.stop();

    mIncX = 0;
    mIncY = 0;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.event.ChangeListener#changed(org.abh.common.event.
   * ChangeEvent)
   */
  @Override
  public void changed(ChangeEvent e) {
    adjustDisplay();
  }
}
