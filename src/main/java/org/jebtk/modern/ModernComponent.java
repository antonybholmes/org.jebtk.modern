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
package org.jebtk.modern;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.Border;

import org.jebtk.core.geom.IntRect;
import org.jebtk.core.settings.SettingsService;
import org.jebtk.modern.css.CSS2Props;
import org.jebtk.modern.css.CSSDrawUI;
import org.jebtk.modern.css.CSSKeyFramesService;
import org.jebtk.modern.graphics.AAMode;
import org.jebtk.modern.graphics.AAModes;
import org.jebtk.modern.graphics.ImageUtils;
import org.jebtk.modern.scrollpane.ScrollEvent;
import org.jebtk.modern.scrollpane.ScrollEventProducer;
import org.jebtk.modern.scrollpane.ScrollListener;
import org.jebtk.modern.scrollpane.ScrollListeners;
import org.jebtk.modern.theme.DrawUI;
import org.jebtk.modern.theme.MaterialService;
import org.jebtk.modern.theme.ModernTheme;
import org.jebtk.modern.theme.ThemeService;
import org.jebtk.modern.tooltip.ModernToolTipEvent;
import org.jebtk.modern.tooltip.ModernToolTipListener;
import org.jebtk.modern.tooltip.ToolTipService;

/**
 * Standardized panel component.
 * 
 * @author Antony Holmes
 *
 */
public class ModernComponent extends JComponent implements ScrollEventProducer {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The member internal rect.
   */
  public IntRect mInternalRect = new IntRect(0, 0, 0, 0);

  /**
   * The member rect.
   */
  protected IntRect mRect = new IntRect(0, 0, 0, 0);

  /**
   * The constant BACKGROUND_COLOR.
   */
  public static final Color BACKGROUND_COLOR = SettingsService.getInstance().getColor("theme.background");

  /**
   * The constant OUTLINE_BASE_COLOR.
   */
  public static final Color OUTLINE_BASE_COLOR = ThemeService.getInstance().getColors().getGray(1);

  /**
   * The constant OUTLINE_COLOR.
   */
  public static final Color OUTLINE_COLOR = ThemeService.getInstance().getColors().getGray(6);

  /**
   * The constant LINE_COLOR.
   */
  public static final Color LINE_COLOR = MaterialService.getInstance().getColor("line");

  /** The Constant LIGHT_LINE_COLOR. */
  public static final Color LIGHT_LINE_COLOR = ThemeService.getInstance().getColors().getLightLineColor();

  /**
   * The constant DARK_LINE_COLOR.
   */
  public static final Color DARK_LINE_COLOR = ThemeService.getInstance().getColors().getDarkLineColor();

  /**
   * The constant PADDING.
   */
  public static final int PADDING = SettingsService.getInstance().getInt("theme.widget.padding");

  public static final int SMALL_PADDING = PADDING / 2;

  /**
   * The constant OUTLINE_LINE_BORDER.
   */
  public static final Border OUTLINE_LINE_BORDER = BorderService.getInstance().createLineBorder(OUTLINE_COLOR);

  /**
   * The constant DOUBLE_PADDING.
   */
  public static final int DOUBLE_PADDING = 2 * PADDING;

  /**
   * The constant TRIPLE_PADDING.
   */
  public static final int TRIPLE_PADDING = 3 * PADDING;

  /**
   * The constant QUAD_PADDING.
   */
  public static final int QUAD_PADDING = 4 * PADDING;

  /**
   * The constant BORDER.
   */
  public static final Border BORDER = BorderService.getInstance().createBorder(PADDING);

  public static final Border BORDER_1PX = BorderService.getInstance().createBorder(1);
    
  public static final Border EMPTY_BORDER = BorderFactory.createEmptyBorder();
  /**
   * The constant SMALL_BORDER.
   */
  public static final Border SMALL_BORDER = BorderService.getInstance().createBorder(SMALL_PADDING);

  /** The Constant DOUBLE_BORDER. */
  public static final Border DOUBLE_BORDER = BorderService.getInstance().createBorder(DOUBLE_PADDING);

  /** The Constant TRIPLE_BORDER. */
  public static final Border TRIPLE_BORDER = BorderService.getInstance().createBorder(TRIPLE_PADDING);

  /** The Constant QUAD_BORDER. */
  public static final Border QUAD_BORDER = BorderService.getInstance().createBorder(QUAD_PADDING);

  /** The Constant LARGE_BORDER. */
  public static final Border LARGE_BORDER = DOUBLE_BORDER;

  /**
   * The constant TOP_BORDER.
   */
  public static final Border TOP_BORDER = BorderService.getInstance().createTopBorder(PADDING);

  public static final Border TOP_DOUBLE_BORDER = BorderService.getInstance().createTopBorder(DOUBLE_PADDING);

  /**
   * The constant LEFT_BORDER.
   */
  public static final Border LEFT_BORDER = BorderService.getInstance().createLeftBorder(PADDING);

  /**
   * The constant BOTTOM_BORDER.
   */
  public static final Border BOTTOM_BORDER = BorderService.getInstance().createBottomBorder(PADDING);

  public static final Border DOUBLE_BOTTOM_BORDER = BorderService.getInstance().createBottomBorder(DOUBLE_PADDING);

  /**
   * The constant RIGHT_BORDER.
   */
  public static final Border RIGHT_BORDER = BorderService.getInstance().createRightBorder(PADDING);

  /**
   * The constant TOP_BOTTOM_BORDER.
   */
  public static final Border TOP_BOTTOM_BORDER = BorderService.getInstance().createTopBottomBorder(PADDING);

  public static final Border TOP_BOTTOM_DOUBLE_BORDER = BorderService.getInstance()
      .createTopBottomBorder(DOUBLE_PADDING);

  /**
   * The constant LEFT_RIGHT_BORDER.
   */
  public static final Border LEFT_RIGHT_BORDER = BorderService.getInstance().createLeftRightBorder(PADDING);

  public static final ModernComponentAspectRatio NORMAL_RATIO = new ModernComponentAspectRatio();

  public static final ModernComponentAspectRatio SQUARE_RATIO = new ModernComponentAspectRatioSquare();

  public static final DrawUI CSS_DRAW = new CSSDrawUI();

  /**
   * The member listeners.
   */
  private final ModernComponentListeners mListeners = new ModernComponentListeners();

  protected ScrollListeners mScrollListeners = new ScrollListeners();

  /** The m center. */
  private Component mCenter = null;

  /** The m line padding. */
  protected int mLinePadding;

  /** The m page padding. */
  protected int mPagePadding;

  private Component mHeader;

  private Component mFooter;

  protected AAModes mAAModes = new AAModes();

  protected boolean mRasterMode = false;

  protected BufferedImage mBufferedImage;

  /**
   * Represents current Props of the component
   */
  private final CSS2Props mCSSProps = new CSS2Props();

  private ModernComponentAspectRatio mRatio = NORMAL_RATIO;

  /**
   * Controls whether to use default css renderer to draw widget.
   */
  protected boolean mCSSMode = true;

  /**
   * The class ComponentEvents.
   */
  private class ComponentEvents implements ComponentListener {

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.ComponentListener#componentResized(java.awt.event.
     * ComponentEvent)
     */
    @Override
    public void componentResized(ComponentEvent e) {
      setInternalDimension();

      mListeners.fireComponentResized(e);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.ComponentListener#componentHidden(java.awt.event.
     * ComponentEvent)
     */
    @Override
    public void componentHidden(ComponentEvent e) {
      mListeners.fireComponentHidden(e);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.ComponentListener#componentMoved(java.awt.event.
     * ComponentEvent)
     */
    @Override
    public void componentMoved(ComponentEvent e) {
      mListeners.fireComponentMoved(e);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.ComponentListener#componentShown(java.awt.event.
     * ComponentEvent)
     */
    @Override
    public void componentShown(ComponentEvent e) {
      mListeners.fireComponentShown(e);
    }
  }

  /**
   * Instantiates a new modern component.
   */
  public ModernComponent() {
    setup();
  }

  /**
   * Instantiates a new modern component.
   *
   * @param color the color
   */
  public ModernComponent(Color color) {
    setup();

    setBackground(color);
  }

  /**
   * Instantiates a new modern component.
   *
   * @param manager the manager
   */
  public ModernComponent(LayoutManager manager) {
    setup();

    setLayout(manager);
  }

  /**
   * Instantiates a new modern component.
   *
   * @param c the c
   */
  public ModernComponent(Component c) {
    setup();

    add(c);
  }

  /**
   * Instantiates a new modern component.
   *
   * @param c      the c
   * @param border the border
   */
  public ModernComponent(Component c, Border border) {
    this(c);

    setBorder(border);
  }

  /**
   * Instantiates a new modern component.
   *
   * @param c      the c
   * @param color  the color
   * @param border the border
   */
  public ModernComponent(Component c, Color color, Border border) {
    this(c);

    setBackground(color);
    setBorder(border);
  }

  /**
   * Create a component containing a given component surrounded by a border of
   * width {@code border} pixels.
   *
   * @param c      the c
   * @param border the border
   */
  public ModernComponent(Component c, int border) {
    this(c, BorderService.getInstance().createBorder(border));
  }

  public ModernComponent(Component c, LayoutManager layout) {
    this(c);

    setLayout(layout);
  }

  public ModernComponent(Component c, Border border, Dimension size) {
    this(c, border);

    UI.setSize(this, size);
  }

  /**
   * Setup.
   */
  private void setup() {
    setLayout(new BorderLayout());

    setAlignmentX(LEFT_ALIGNMENT);
    // setAlignmentY(TOP_ALIGNMENT);

    // All components inherit the component class by default
    addStyleClass("component"); // StyleClassService.getInstance().getCompStyleClass());

    mCSSProps.addChangeListener(e -> {
      repaint();
    });

    super.addComponentListener(new ComponentEvents());

    //setOpaque(false);
    //setDoubleBuffered(true);

    // Set defaults for components
    getAAModes().add(AAMode.AA).add(AAMode.TEXT);

    getAAModes().addChangeListener(e -> {
      repaint();
    });
  }

  public void setAspectRatio(ModernComponentAspectRatio ratio) {
    mRatio = ratio;
    invalidate();
  }

  public ModernComponentAspectRatio getAspectRatio() {
    return mRatio;
  }

  @Override
  public Dimension getPreferredSize() {
    return mRatio.getPreferredSize(super.getPreferredSize(), super.getMinimumSize(), super.getMaximumSize());
  }

  @Override
  public Dimension getMinimumSize() {
    return mRatio.getMinimumSize(super.getPreferredSize(), super.getMinimumSize(), super.getMaximumSize());
  }

  @Override
  public Dimension getMaximumSize() {
    return mRatio.getMaximumSize(super.getPreferredSize(), super.getMinimumSize(), super.getMaximumSize());
  }

  public void setRasterMode(boolean on) {
    mRasterMode = on;

    repaint();
  }

  /**
   * Set how the component uses anti-aliasing.
   * 
   * @return
   */
  public AAModes getAAModes() {
    return mAAModes;
  }

  /**
   * Creates rectangle objects specifying the size and internal size (size minus
   * offsets).
   */
  private void setInternalDimension() {
    setInternalDimension(getInsets());
  }

  /**
   * Sets the internal dimension.
   *
   * @param insets the new internal dimension
   */
  private void setInternalDimension(Insets insets) {
    setInternalDimension(getWidth(), getHeight(), insets);
  }

  /**
   * Sets the internal dimension.
   *
   * @param width  the width
   * @param height the height
   */
  private void setInternalDimension(int width, int height) {
    setInternalDimension(width, height, getInsets());
  }

  /**
   * Sets the internal dimension.
   *
   * @param width  the width
   * @param height the height
   * @param insets the insets
   */
  private void setInternalDimension(int width, int height, Insets insets) {
    mRect = new IntRect(0, 0, width, height);

    int x = 0;
    int y = 0;

    if (insets != null) {
      x = insets.left;
      y = insets.top;
      mLinePadding = insets.left + insets.right;
      mPagePadding = insets.top + insets.bottom;
    } else {
      mLinePadding = 0;
      mPagePadding = 0;
    }

    mInternalRect = new IntRect(x, y, width - mLinePadding, height - mPagePadding);

    repaint();
  }

  /**
   * Returns the sum of the left and right padding.
   *
   * @return the line padding
   */
  public int getLinePadding() {
    return mLinePadding;
  }

  /**
   * Returns the sum of the top and bottom padding.
   * 
   * @return
   */
  public int getPagePadding() {
    return mPagePadding;
  }

  /**
   * Returns the internal dimensions (excluding padding) of the widget.
   *
   * @return the rect
   */
  public IntRect getRect() {
    return mRect;
  }

  /**
   * Returns the size of the component excluding the insets.
   *
   * @return the internal rect
   */
  public IntRect getInternalRect() {
    return mInternalRect;
  }

  /**
   * Set a uniform border of {@code border} pixels.
   *
   * @param border the new border
   * @return
   */
  public ModernComponent setBorder(int border) {
    setBorder(BorderService.getInstance().createBorder(border));

    return this;
  }

  public ModernComponent border(Border border) {
    setBorder(border);

    return this;
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.JComponent#setBorder(javax.swing.border.Border)
   */
  @Override
  public void setBorder(Border border) {
    if (border == null) {
      return;
    }

    setInternalDimension(border.getBorderInsets(this));

    super.setBorder(border);
  }

  public Component bottomBorder(int padding) {
    return border(
        BorderService.getInstance().createBorder(getInsets().top, getInsets().left, padding, getInsets().right));
  }

  /**
   * Set the top border on the component as a number of pixels.
   * 
   * @param padding how much padding to use for the top border.
   * @return
   */
  public Component topBorder(int padding) {
    return border(
        BorderService.getInstance().createBorder(padding, getInsets().left, getInsets().bottom, getInsets().right));
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.Component#setSize(int, int)
   */
  @Override
  public void setSize(int width, int height) {
    setInternalDimension(width, height);

    super.setSize(width, height);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.Component#setSize(java.awt.Dimension)
   */
  @Override
  public void setSize(Dimension dimension) {
    setSize(dimension.width, dimension.height);
  }

  @Override
  public void addScrollListener(ScrollListener l) {
    getScrollListeners().addScrollListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.event.ModernSelectedEventProducer#
   * removeSelectedListener(org.abh.lib.ui.modern.event.ModernSelectedListener)
   */
  @Override
  public void removeScrollListener(ScrollListener l) {
    getScrollListeners().removeScrollListener(l);
  }

  public ScrollListeners getScrollListeners() {
    return mScrollListeners;
  }

  @Override
  public void fireScrollTo(ScrollEvent e) {
    getScrollListeners().fireScrollTo(e);
  }

  @Override
  public void scrollRectToVisible(Rectangle r) {
    super.scrollRectToVisible(r);

    fireScrollTo(r);
  }

  public void scrollRectToVisible(IntRect r) {
    super.scrollRectToVisible(IntRect.toRectangle(r));

    fireScrollTo(r);
  }

  public void fireScrollTo(Rectangle r) {
    fireScrollTo(IntRect.createRect(r));
  }

  public void fireScrollTo(int x, int y, int w, int h) {
    fireScrollTo(new IntRect(x, y, w, h));
  }

  public void fireScrollTo(IntRect r) {
    fireScrollTo(new ScrollEvent(this, r));
  }

  public CSS2Props getCSSProps() {
    return mCSSProps;
  }

  public ModernComponent addStyleClass(String name, String... names) {
    // Add both from and to classes (assuming they exist).

    addCSSProps(this, name, names);

    return this;
  }


  /**
   * Gets the component.
   *
   * @return the component
   */
  public Component getComponent() {
    return getComponent(0);
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
   */
  @Override
  public final void paintComponent(Graphics g) {
    if (isVisible()) {
      draw((Graphics2D) g);
    }

    // g2.dispose();
  }

  /**
   * Separate the background and foreground rendering of components since many
   * widgets will use the same background rendering and only change how
   * information is displayed.
   *
   * @param g2 the g2
   */
  public void draw(Graphics2D g2) {
    // Render then separately on independent
    // contexts to minimize graphic errors
    // caused by adjusting the properties
    // of g2.

    Graphics2D g2Temp;

    g2Temp = ImageUtils.clone(g2);

    try {
      drawBackground(g2Temp);
    } finally {
      g2Temp.dispose();
    }

    g2Temp = ImageUtils.clone(g2);

    try {
      drawForeground(g2Temp);
    } finally {
      g2Temp.dispose();
    }
  }

  /**
   * Set whether to use the CSS renderer. For certain custom components it is
   * necessary to disable this so that the CSS style does not conflict with the
   * custom style.
   * 
   * @param cssMode
   */
  public void setCSSMode(boolean cssMode) {
    mCSSMode = cssMode;
    repaint();
  }

  public void drawBackground(Graphics2D g2) {
    if (mAAModes.contains(AAMode.AA)) {
      Graphics2D g2Temp = ImageUtils.createAATextGraphics(g2);

      try {
        drawBackgroundAA(g2Temp);
      } finally {
        g2Temp.dispose();
      }
    } else {
      drawBackgroundAA(g2);
    }
  }

  /**
   * Should be in charge of rendering the foreground using anti-aliasing.
   *
   * @param g2 the g2
   */
  public void drawBackgroundAA(Graphics2D g2) {
    if (mCSSMode) {
      CSS_DRAW.draw(this, g2);
    }
  }

  /**
   * Should be in charge of rendering the foreground using anti-aliasing.
   *
   * @param g2 the g2
   */
  public void drawForeground(Graphics2D g2) {
    rasterForeground(g2);
  }

  /**
   * Create a raster version of the foreground.
   * 
   * @param g2
   */
  public void rasterForeground(Graphics2D g2) {
    if (mRasterMode) {
      // Create an image version of the canvas and draw that to spped
      // up operations
      if (mBufferedImage == null) {
        // The canvas need only be the size of the available display

        Dimension s = getPreferredSize();

        // Make it one pixel bigger to account for borders being drawn
        mBufferedImage = ImageUtils.createImage(s.width + 1, s.height + 1);

        Graphics2D g2Temp = ImageUtils.createGraphics(mBufferedImage);

        try {
          aaForeground(g2Temp);
        } finally {
          g2Temp.dispose();
        }
      }

      g2.drawImage(mBufferedImage, 0, 0, null);
    } else {
      aaForeground(g2);
    }
  }

  /**
   * Optionally plot using anti-aliasing.
   * 
   * @param g2
   * @param offset
   * @param context
   * @param props
   */
  public void aaForeground(Graphics2D g2) {
    if (getAAModes().size() > 0) {
      Graphics2D g2Temp = ImageUtils.createAAGraphics(g2, getAAModes());

      try {
        drawForegroundAA(g2Temp);
      } finally {
        g2Temp.dispose();
      }
    } else {
      drawForegroundAA(g2);
    }
  }

  /**
   * Responsible for drawing the foreground content.
   * 
   * @param g2
   */
  public void drawForegroundAA(Graphics2D g2) {
    // Do nothing
  }

  /**
   * Sets the header.
   *
   * @param c the new header
   */
  public void setHeader(Component c) {
    setTop(c);
  }

  /**
   * Set the component at the top of the this component. For compatibility with
   * JavaFX.
   * 
   * @param c
   */
  public void setTop(Component c) {
    if (c == null) {
      return;
    }

    if (mHeader != null) {
      remove(mHeader);
    }

    mHeader = c;

    add(c, BorderLayout.PAGE_START);

    revalidate();
    repaint();
  }

  /**
   * Sets the body.
   *
   * @param c the new body
   */
  public void setBody(Component c) {
    setCenter(c);
  }

  public void setCenter(Component c) {
    if (c == null) {
      return;
    }

    if (mCenter != null) {
      remove(mCenter);
    }

    mCenter = c;

    add(c, BorderLayout.CENTER);

    revalidate();
    repaint();
  }

  /**
   * Sets the footer. Equivalent to <code>setBottom()</code>.
   *
   * @param c the new footer
   */
  public void setFooter(Component c) {
    setBottom(c);
  }

  public void setBottom(Component c) {
    if (c == null) {
      return;
    }

    if (mFooter != null) {
      remove(mFooter);
    }

    mFooter = c;

    add(c, BorderLayout.PAGE_END);

    revalidate();
    repaint();
  }

  /**
   * Sets the left.
   *
   * @param c the new left
   */
  public void setLeft(Component c) {
    add(c, BorderLayout.LINE_START);
  }

  /**
   * Sets the right.
   *
   * @param c the new right
   */
  public void setRight(Component c) {
    add(c, BorderLayout.LINE_END);
  }

  /**
   * Draw outline.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void drawOutline(Graphics2D g2, Rectangle rect) {
    drawBorder(g2, OUTLINE_COLOR, rect);
  }

  /**
   * Draw outline.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void drawOutline(Graphics2D g2, IntRect rect) {
    drawBorder(g2, OUTLINE_COLOR, rect);
  }

  /**
   * Draws a standard border of a specified color. This should be used by all
   * button like controls.
   *
   * @param g2    the g2
   * @param color the color
   * @param rect  the rect
   */
  public static void drawBorder(Graphics2D g2, Color color, Rectangle rect) {
    drawRect(g2, color, rect);
  }

  /**
   * Draw border.
   *
   * @param g2    the g2
   * @param color the color
   * @param rect  the rect
   */
  public static void drawBorder(Graphics2D g2, Color color, IntRect rect) {
    drawRect(g2, color, rect);
  }

  /**
   * Draw rect.
   *
   * @param g2    the g2
   * @param color the color
   * @param rect  the rect
   */
  public static void drawRect(Graphics2D g2, Color color, Rectangle rect) {
    g2.setColor(color);
    // g2.setStroke(ModernTheme.SINGLE_LINE_STROKE);

    g2.drawRect(rect.x, rect.y, rect.width - 1, rect.height - 1);
  }

  /**
   * Draw rect.
   *
   * @param g2    the g2
   * @param color the color
   * @param rect  the rect
   */
  public static void drawRect(Graphics2D g2, Color color, IntRect rect) {
    g2.setColor(color);
    // g2.setStroke(ModernTheme.SINGLE_LINE_STROKE);

    g2.drawRect(rect.getX(), rect.getY(), rect.getW() - 1, rect.getH() - 1);
  }

  /**
   * Draw double thick rect.
   *
   * @param g2    the g2
   * @param color the color
   * @param rect  the rect
   */
  public static void drawDoubleThickRect(Graphics2D g2, Color color, IntRect rect) {
    g2.setColor(color);

    g2.drawRect(rect.getX(), rect.getY(), rect.getW() - 1, rect.getH() - 1);

    g2.drawRect(rect.getX() + 1, rect.getY() + 1, rect.getW() - 3, rect.getH() - 3);
  }

  /**
   * Draw rect double stroke.
   *
   * @param g2    the g2
   * @param color the color
   * @param rect  the rect
   */
  public static void drawRectDoubleStroke(Graphics2D g2, Color color, IntRect rect) {
    drawRectDoubleStroke(g2, color, rect.getX(), rect.getY(), rect.getW() - 1, rect.getH() - 1);
  }

  /**
   * Draw rect double stroke.
   *
   * @param g2    the g2
   * @param color the color
   * @param x     the x
   * @param y     the y
   * @param w     the w
   * @param h     the h
   */
  public static void drawRectDoubleStroke(Graphics2D g2, Color color, int x, int y, int w, int h) {
    g2.setColor(color);
    g2.setStroke(ModernTheme.DOUBLE_LINE_STROKE);

    g2.drawRect(x + 1, y + 1, w - 2, h - 2);
  }

  /**
   * Fill in with the background color.
   *
   * @param g2 the g2
   */
  public void fillBackground(Graphics2D g2) {
    fillBackground(g2, getRect());
  }

  /**
   * Fill in with the background color.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public void fillBackground(Graphics2D g2, Rectangle rect) {
    fill(g2, getBackground(), rect);
  }

  /**
   * Fill background.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public void fillBackground(Graphics2D g2, IntRect rect) {
    fill(g2, getBackground(), rect);
  }

  /**
   * Fill.
   *
   * @param g2    the g2
   * @param color the color
   */
  public void fill(Graphics2D g2, Color color) {
    fill(g2, color, getRect());
  }

  /**
   * Fill.
   *
   * @param g2    the g2
   * @param color the color
   * @param rect  the rect
   */
  public static void fill(Graphics2D g2, Color color, Rectangle rect) {
    g2.setColor(color);

    g2.fillRect(rect.x, rect.y, rect.width, rect.height);
  }

  public void fill(Graphics2D g2, Color color1, Color color2) {
    fill(g2, color1, color2, getRect());
  }

  /**
   * Gradient fill an area vertically.
   *
   * @param g2    the g2
   * @param color the color
   * @param rect  the rect
   */
  public static void fill(Graphics2D g2, Color c1, Color c2, Rectangle rect) {
    UI.fillGradient(g2, c1, c2, rect.x, rect.y, rect.width, rect.height);
  }

  /**
   * Gradient fill an area vertically.
   * 
   * @param g2
   * @param c1
   * @param c2
   * @param rect
   */
  public static void fill(Graphics2D g2, Color c1, Color c2, IntRect rect) {
    UI.fillGradient(g2, c1, c2, rect.getX(), rect.getY(), rect.getW(), rect.getH());
  }

  /**
   * Fill in the component in the given color up to a given width.
   *
   * @param g2    the g 2
   * @param color the color
   * @param width the width
   */
  public void fill(Graphics2D g2, Color color, int width) {
    fill(g2, color, new IntRect(0, 0, width, mRect.getH()));
  }

  /**
   * Fill.
   *
   * @param g2    the g2
   * @param color the color
   * @param rect  the rect
   */
  public static void fill(Graphics2D g2, Color color, IntRect rect) {

    g2.setColor(color);

    g2.fillRect(rect.getX(), rect.getY(), rect.getW(), rect.getH());
  }

  /**
   * Creates the h gap.
   *
   * @return the component
   */
  public static Component createHGap() {
    return UI.createHGap(PADDING);
  }

  /**
   * Creates the v gap.
   *
   * @return the component
   */
  public static Component createVGap() {
    return UI.createVGap(PADDING);
  }

  /**
   * Creates the large vertical gap.
   *
   * @return the component
   */
  public static final Component createLargeVerticalGap() {
    return UI.createVGap(DOUBLE_PADDING);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * java.awt.Component#addComponentListener(java.awt.event.ComponentListener)
   */
  @Override
  public void addComponentListener(ComponentListener l) {
    // We force components to use this alternative listener so that
    // component internals can be updated before parents receive
    // notification that the component has changed size etc.
    mListeners.addComponentListener(l);
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.Component#removeComponentListener(java.awt.event.
   * ComponentListener)
   */
  @Override
  public void removeComponentListener(ComponentListener l) {
    mListeners.removeComponentListener(l);
  }

  public ModernToolTipListener getToolTipDest() {
    return ToolTipService.getToolTipWindow(this);
  }

  public ModernToolTipEvent createToolTipEvent() {
    return createToolTipEvent(this);
  }

  public ModernToolTipEvent createToolTipEvent(Component tooltip) {
    return createToolTipEvent(tooltip, (Point) null);
  }

  public ModernToolTipEvent createToolTipEvent(Component tooltip, Point p) {
    return new ModernToolTipEvent(this, getToolTipDest(), tooltip, p);
  }

  public ModernToolTipEvent createToolTipEvent(Component tooltip, int x, int y) {
    return createToolTipEvent(tooltip, new Point(x, y));
  }

  public ModernToolTipEvent createToolTipEvent(Component tooltip, MouseEvent e) {
    return createToolTipEvent(tooltip, e.getX(), e.getY());
  }

  /**
   * Add CSS Props to a component.
   * 
   * @param c
   * @param name
   * @param names
   */
  public static void addCSSProps(ModernComponent c, String name, String... names) {
    CSSKeyFramesService kfs = CSSKeyFramesService.getInstance();
    
    System.err.println("names names " + name + " " + kfs.contains(name));
    if (kfs.contains(name)) {
      
      c.getCSSProps().update(kfs.getStyleClass(name));
      
      if (name.contains("ribbon-button")) {
        System.err.println("kfs updating" + kfs.getStyleClass(name));
        
        System.err.println("kfs updating" + name + " " + c.getCSSProps());
      }
    }

    for (String n : names) {
      if (kfs.contains(n)) {
        c.getCSSProps().update(kfs.getStyleClass(n));
      }
    }
  }
}
