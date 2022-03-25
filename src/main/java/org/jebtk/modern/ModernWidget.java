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

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JComponent;
import javax.swing.border.Border;
import org.jebtk.core.CSSColor;

import org.jebtk.core.IdProperty;
import org.jebtk.core.Props;
import org.jebtk.core.geom.IntRect;
import org.jebtk.core.settings.SettingsService;
import org.jebtk.core.text.TextUtils;
import org.jebtk.modern.animation.Animation;
import org.jebtk.modern.animation.Animations;
import org.jebtk.modern.animation.EasingTransitionTimer;
import org.jebtk.modern.animation.MouseOverTransitions;
import org.jebtk.modern.animation.Transitions;
import org.jebtk.modern.font.FontUtils;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.theme.DrawUIStates;
import org.jebtk.modern.theme.MaterialService;
import org.jebtk.modern.theme.MaterialService2;
import org.jebtk.modern.theme.ThemeService;

/**
 * The basis for all controls. Implements basic message handling.
 * 
 * @author Antony Holmes
 *
 */
public abstract class ModernWidget extends ModernComponent implements IdProperty {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  //
  // Some standard shared widget properties
  //

  /**
   * The constant HIGHLIGHT_COLOR.
   */
  public static final Color HIGHLIGHT_COLOR = MaterialService2.getInstance().getGray(4);

  /** The Constant HIGHLIGHT_BORDER_COLOR. */
  public static final Color HIGHLIGHT_BORDER_COLOR = MaterialService2.getInstance().getGray(6);

  /**
   * The constant HIGHLIGHT_TEXT_COLOR.
   */
  public static final Color HIGHLIGHT_TEXT_COLOR = ThemeService.getInstance().getColors().getTheme(4);

  /**
   * The constant SELECTED_COLOR.
   */
  public static final Color SELECTED_COLOR = MaterialService2.getInstance().getGray(5);

  /**
   * The constant SELECTED_BORDER_COLOR.
   */
  public static final Color SELECTED_BORDER_COLOR = MaterialService2.getInstance().getGray(6);

  /**
   * The constant THEME_HIGHLIGHT_COLOR.
   */
  public static final Color THEME_HIGHLIGHT_COLOR = ThemeService.getInstance().getColors().getTheme(2);

  /**
   * The constant THEME_SELECTED_COLOR.
   */
  public static final Color THEME_SELECTED_COLOR = ThemeService.getInstance().getColors().getTheme(3);

  /**
   * The constant THEME_SELECTED_BORDER_COLOR.
   */
  public static final Color THEME_SELECTED_BORDER_COLOR = ThemeService.getInstance().getColors().getTheme32(10);

  /**
   * The constant OUTLINE_HIGHLIGHT_COLOR.
   */
  public static final Color OUTLINE_HIGHLIGHT_COLOR = MaterialService2.getInstance().getGray(6);

  /**
   * The constant THEME_OUTLINE_HIGHLIGHT_COLOR.
   */
  public static final Color THEME_OUTLINE_HIGHLIGHT_COLOR = ThemeService.getInstance().getColors().getTheme(6);

  /**
   * The constant OUTLINE_BACKGROUND_COLOR.
   */
  public static final Color OUTLINE_BACKGROUND_COLOR = MaterialService2.getInstance().getGray(1);

  /**
   * The constant MIN_WIDGET_HEIGHT.
   */
  public static final int MIN_WIDGET_HEIGHT = SettingsService.getInstance().getInt("theme.widget.default-height");

  /**
   * The constant WIDGET_HEIGHT.
   */
  public static final int WIDGET_HEIGHT = MIN_WIDGET_HEIGHT;

  /**
   * The constant DIALOG_2_COLOR.
   */
  private static final Color DIALOG_2_COLOR = MaterialService2.getInstance().getGray(2);

  /**
   * The constant TEXT_COLOR.
   */
  public static final CSSColor TEXT_COLOR = MaterialService2.getInstance().getTextColor();

  /**
   * The constant ALT_TEXT_COLOR.
   */
  public static final CSSColor ALT_TEXT_COLOR = MaterialService2.getInstance().getAltTextColor();

  /** The Constant TEXT_DISABLED_COLOR. */
  public static final CSSColor TEXT_DISABLED_COLOR = ThemeService.getInstance().getColors().getDisabledTextColor();

  /**
   * The constant DARK_OUTLINE_COLOR.
   */
  public static final CSSColor DARK_OUTLINE_COLOR = MaterialService2.getInstance().getGray(8);

  /**
   * The constant FONT.
   */
  public static final Font FONT = MaterialService.getInstance().getFonts().text(); // ThemeService.getInstance().getFonts().getFont();

  /** The Constant UNDER_LINE_FONT. */
  public static final Font UNDER_LINE_FONT = FontUtils.underline(FONT);

  /**
   * The constant FONT.
   */
  public static final Font TITLE_FONT = ThemeService.getInstance().getFonts().getTitleFont();

  /** The Constant TITLE_FONT_BOLD. */
  public static final Font TITLE_FONT_BOLD = ThemeService.loadFont("theme.widget.fonts.title-bold");

  /** The Constant SUB_TITLE_FONT. */
  public static final Font SUB_TITLE_FONT = ThemeService.getInstance().getFonts().getSubTitleFont();

  /**
   * The constant HEADING_FONT.
   */
  public static final Font HEADING_FONT = ThemeService.getInstance().getFonts().getHeadingFont();

  /**
   * The constant SUB_HEADING_FONT.
   */
  public static final Font SUB_HEADING_FONT = ThemeService.loadFont("theme.widget.fonts.sub-heading");

  /**
   * The constant SUB_SUB_HEADING_FONT.
   */
  public static final Font SUB_SUB_HEADING_FONT = ThemeService.loadFont("theme.widget.fonts.sub-sub-heading");

  /**
   * The constant BOLD_FONT.
   */
  public static final Font BOLD_FONT = MaterialService.getInstance().getFonts().bold();

  /**
   * The constant MONOSPACE_FONT.
   */
  public static final Font MONOSPACE_FONT = ThemeService.loadFont("theme.widget.fonts.monospace");


  /**
   * The constant LINE_BORDER.
   */
  public static final Border LINE_BORDER = BorderService.getInstance().createLineBorder(LINE_COLOR);

  /**
   * The constant DARK_LINE_BORDER.
   */
  public static final Border DARK_LINE_BORDER = BorderService.getInstance().createLineBorder(DARK_LINE_COLOR);

  /**
   * The constant MAX_SIZE.
   */
  public static final Dimension MAX_SIZE = new Dimension(Short.MAX_VALUE, WIDGET_HEIGHT);

  /**
   * The constant STANDARD_SIZE.
   */
  public static final Dimension STANDARD_SIZE = new Dimension(100, WIDGET_HEIGHT);

  /** The Constant BIG_SIZE. */
  public static final Dimension BIG_SIZE = new Dimension(120, WIDGET_HEIGHT);

  /**
   * The constant LARGE_SIZE.
   */
  public static final Dimension LARGE_SIZE = new Dimension(150, WIDGET_HEIGHT);

  /**
   * The constant EXTRA_LARGE_SIZE.
   */
  public static final Dimension EXTRA_LARGE_SIZE = new Dimension(200, WIDGET_HEIGHT);

  /**
   * The constant VERY_LARGE_SIZE.
   */
  public static final Dimension VERY_LARGE_SIZE = new Dimension(300, WIDGET_HEIGHT);

  /**
   * The constant SMALL_SIZE.
   */
  public static final Dimension SMALL_SIZE = new Dimension(80, WIDGET_HEIGHT);

  /**
   * The constant TINY_SIZE.
   */
  public static final Dimension TINY_SIZE = new Dimension(2 * WIDGET_HEIGHT, WIDGET_HEIGHT);

  /**
   * The constant MAX_SIZE_48.
   */
  public static final Dimension MAX_SIZE_48 = new Dimension(Short.MAX_VALUE, 48);

  /**
   * The constant MAX_SIZE_24.
   */
  public static final Dimension MAX_SIZE_24 = new Dimension(Short.MAX_VALUE, 24);

  /** The Constant MAX_SIZE_32. */
  public static final Dimension MAX_SIZE_32 = new Dimension(Short.MAX_VALUE, 32);

  /**
   * The constant SQUARE_WIDGET_SIZE.
   */
  public static final Dimension SQUARE_WIDGET_SIZE = new Dimension(WIDGET_HEIGHT, WIDGET_HEIGHT);

  /** The Constant MIN_SIZE. */
  public static final Dimension MIN_SIZE = new Dimension(WIDGET_HEIGHT / 4, WIDGET_HEIGHT);

  /** The Constant INSET_1. */
  public static final Border INSET_1 = BorderService.getInstance().createLeftBorder(WIDGET_HEIGHT);

  /** The Constant INSET_2. */
  public static final Border INSET_2 = BorderService.getInstance().createLeftBorder(2 * WIDGET_HEIGHT);

  /**
   * The constant SIZE_48.
   */
  public static final Dimension SIZE_48 = new Dimension(48, WIDGET_HEIGHT);

  /** The Constant SIZE_32. */
  public static final Dimension SIZE_32 = new Dimension(32, WIDGET_HEIGHT);

  /**
   * The constant DIALOG_BUTTON_BASE.
   */
  private static final Color DIALOG_BUTTON_BASE = MaterialService2.getInstance().getGray(3);

  /**
   * The constant DIALOG_BUTTON_BORDER_COLOR.
   */
  private static final Color DIALOG_BUTTON_BORDER_COLOR = MaterialService2.getInstance().getGray(5);

  /**
   * The next id.
   */
  private static AtomicInteger NEXT_ID = new AtomicInteger(0);

  /**
   * The constant DEFAULT_GRAPHICS.
   */
  private static final Graphics2D DEFAULT_GRAPHICS;

  public static final Border SINGLE_BORDER = BorderService.getInstance().createBorder(1);

  public static final Border TWO_PIXEL_BORDER = BorderService.getInstance().createBorder(2);

  private Animations mBackgroundAnimations = null;

  private final DrawUIStates mDrawStates = new DrawUIStates();

  static {
    // Create a default graphics context that can be used to size
    // Widgets based on the font
    BufferedImage newImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);

    DEFAULT_GRAPHICS = newImage.createGraphics();
    DEFAULT_GRAPHICS.setFont(FONT);
  }

  /**
   * widget id.
   */
  private final int mId = NEXT_ID.getAndIncrement();

  private MouseOverTransitions mMouseOverTransitions;

  /** The m widget renderer provider. */
  /// private WidgetRendererProvider mWidgetRendererProvider =
  /// WidgetRendererService
  // .getInstance();

  /**
   * Instantiates a new modern widget.
   */
  public ModernWidget() {
    setup();
  }

  /**
   * Instantiates a new modern widget.
   *
   * @param manager the manager
   */
  public ModernWidget(LayoutManager manager) {
    super(manager);

    setup();
  }

  /**
   * Instantiates a new modern widget.
   *
   * @param c the c
   */
  public ModernWidget(Component c) {
    super(c);

    setup();
  }

  /**
   * Setup.
   */
  private void setup() {
    setFont(FONT);
    setBackground(BACKGROUND_COLOR);
    setForeground(TEXT_COLOR);

    addStyleClass("widget");

    mBackgroundAnimations = new Animations(this);

    mBackgroundAnimations.addChangeListener(e -> {
      repaint();
    });
  }

  /**
   * Returns a unique Id for the widget.
   *
   * @return the uid
   */
  @Override
  public final int getId() {
    return mId;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.Component#getName()
   */
  @Override
  public String getName() {
    return "Widget " + getId();
  }

  public Transitions getMouseOverTransitions() {
    if (mMouseOverTransitions == null) {
      mMouseOverTransitions = new MouseOverTransitions(this, new EasingTransitionTimer(5));
    }

    return mMouseOverTransitions;
  }

  public Animation getBackgroundAnimation() {
    return getAnimations().get(0);
  }

  public Animations getAnimations() {
    return mBackgroundAnimations;
  }

  public DrawUIStates getDrawStates() {
    return mDrawStates;
  }

  public ModernWidget setAnimations(String animation, String... animations) {
    getAnimations().set(animation, animations);

    return this;
  }

  public ModernWidget addAnimations(String animation, String... animations) {
    getAnimations().add(animation, animations);

    return this;
  }

  public ModernWidget setAnimations(Animation animation, Animation... animations) {
    getAnimations().set(animation, animations);

    return this;
  }

  // public Animations getForegroundAnimations() {
  // return mForegroundAnimations;
  // }

  /**
   * Gets the widget renderer provider.
   *
   * @return the widget renderer provider
   */
  // public WidgetRendererProvider getWidgetRendererProvider() {
  // return mWidgetRendererProvider;
  // }

  /**
   * Returns a renderer for generating common widget elements.
   *
   * @return the widget renderer
   */
  // public WidgetRenderer getWidgetRenderer() {
  // return mWidgetRendererProvider.getRenderer();
  // }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.JComponent#setFont(java.awt.Font)
   */
  @Override
  public void setFont(Font font) {
    super.setFont(font);

    autoSize();
  }

  /**
   * Called when fonts or text change and can be used to resize a widget
   * accordingly. By default does nothing.
   */
  protected void autoSize() {

  }

  /**
   * Should be in charge of rendering the foreground using anti-aliasing.
   *
   * @param g2 the g2
   */
  @Override
  public void drawBackgroundAA(Graphics2D g2) {
    drawAnimatedBackground(g2);
  }

  /**
   * Forwards rendering control to an animator so that we can do animation
   * effects.
   * 
   * @param g2 The widget's graphics context.
   */
  public void drawAnimatedBackground(Graphics2D g2) {
    drawAnimatedBackground(g2, new Props());
  }
  
  public void drawAnimatedBackground(Graphics2D g2, Props props) {
    mBackgroundAnimations.draw(this, g2, props);
  }

  /*
   * @Override public void drawForeground(Graphics2D g2) { Graphics2D g2Temp =
   * ImageUtils.createAATextGraphics(g2);
   * 
   * try { drawForegroundAA(g2Temp); } finally { g2Temp.dispose(); } }
   * 
   * public void drawForegroundAA(Graphics2D g2) { Graphics2D g2Temp =
   * ImageUtils.createAATextGraphics(g2);
   * 
   * try { drawForegroundAA(g2Temp); } finally { g2Temp.dispose(); } }
   */

  /**
   * Renders the foreground with antialiasing on by default.
   *
   * @param g2 the g2
   */
  // @Override
  // public void drawForegroundAA(Graphics2D g2) {
  // // Do nothing
  // }

  /*
   * @Override public void drawBackground(Graphics2D g2) { Graphics2D g2Temp =
   * ImageUtils.createAATextGraphics(g2);
   * 
   * try { drawBackgroundAA(g2Temp); } finally { g2Temp.dispose(); } }
   */

  /*
   * public void drawBackgroundAA(Graphics2D g2) { // Do nothing }
   */

  /**
   * Draw border.
   *
   * @param g2    the g 2
   * @param color the color
   */
  public void drawBorder(Graphics2D g2, Color color) {
    drawBorder(g2, color, getRect());
  }

  /**
   * Returns true if widget is selected. This method should be overridden in
   * widgets that have a selected response (such as a check box).
   *
   * @return true, if is selected
   */
  public boolean isSelected() {
    return false;
  }

  //
  // Static methods
  //

  /**
   * Paint highlighted.
   *
   * @param g2   the g2
   * @param size the size
   */
  public static void paintHighlighted(Graphics2D g2, Dimension size) {
    paintHighlighted(g2, new Rectangle(0, 0, size.width, size.height));
  }

  /**
   * Paint highlighted.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintHighlighted(Graphics2D g2, Rectangle rect) {
    paintHighlightedBase(g2, rect);
    // paintHighlightedBorder(g2, rect);
  }

  /**
   * Paint highlighted.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintHighlighted(Graphics2D g2, IntRect rect) {
    paintHighlightedBase(g2, rect);
    // paintHighlightedBorder(g2, rect);
  }

  /**
   * Paint highlighted base.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintHighlightedBase(Graphics2D g2, Rectangle rect) {
    fill(g2, HIGHLIGHT_COLOR, rect);
  }

  /**
   * Paint highlighted base.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintHighlightedBase(Graphics2D g2, IntRect rect) {
    fill(g2, HIGHLIGHT_COLOR, rect);
  }

  /**
   * Paint highlighted border.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintHighlightedBorder(Graphics2D g2, Rectangle rect) {
    drawBorder(g2, HIGHLIGHT_BORDER_COLOR, rect);
  }

  /**
   * Paint highlighted border.
   *
   * @param g2   the g 2
   * @param rect the rect
   */
  public static void paintHighlightedBorder(Graphics2D g2, IntRect rect) {
    drawBorder(g2, HIGHLIGHT_COLOR, rect);
  }

  /**
   * Paint theme highlighted border.
   *
   * @param g2   the g 2
   * @param rect the rect
   */
  public static void paintThemeHighlightedBorder(Graphics2D g2, Rectangle rect) {
    drawBorder(g2, THEME_HIGHLIGHT_COLOR, rect);
  }

  /**
   * Paint theme highlighted border.
   *
   * @param g2   the g 2
   * @param rect the rect
   */
  public static void paintThemeHighlightedBorder(Graphics2D g2, IntRect rect) {
    drawBorder(g2, THEME_HIGHLIGHT_COLOR, rect);
  }

  /**
   * Paint color highlighted.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintColorHighlighted(Graphics2D g2, Rectangle rect) {
    paintThemeHighlightedBase(g2, rect);
    // paintHighlightedBorder(g2, rect);
  }

  /**
   * Paint theme highlighted.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintThemeHighlighted(Graphics2D g2, IntRect rect) {
    paintThemeHighlightedBase(g2, rect);
    // paintHighlightedBorder(g2, rect);
  }

  /**
   * Paint theme highlighted base.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintThemeHighlightedBase(Graphics2D g2, Rectangle rect) {
    fill(g2, THEME_HIGHLIGHT_COLOR, rect);
  }

  /**
   * Paint theme highlighted base.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintThemeHighlightedBase(Graphics2D g2, IntRect rect) {
    fill(g2, THEME_HIGHLIGHT_COLOR, rect);
  }

  /**
   * Paint highlighted outlined.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintHighlightedOutlined(Graphics2D g2, Rectangle rect) {
    paintHighlightedBase(g2, rect);
    drawHighlightedOutline(g2, rect);
  }

  /**
   * Paint highlighted outlined.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintHighlightedOutlined(Graphics2D g2, IntRect rect) {
    paintHighlightedBase(g2, rect);
    drawHighlightedOutline(g2, rect);
  }

  /**
   * Paint outlined.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintOutlined(Graphics2D g2, Rectangle rect) {
    paintBackgroundBase(g2, rect);
    drawOutline(g2, rect);
  }

  /**
   * Paint outlined.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintOutlined(Graphics2D g2, IntRect rect) {
    paintBackgroundBase(g2, rect);
    drawOutline(g2, rect);
  }

  /**
   * Paint outlined focused.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintOutlinedFocused(Graphics2D g2, Rectangle rect) {
    paintOutlinedBase(g2, rect);
    drawHighlightedOutline(g2, rect);
  }

  /**
   * Paint outlined focused.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintOutlinedFocused(Graphics2D g2, IntRect rect) {
    paintOutlinedBase(g2, rect);
    drawHighlightedOutline(g2, rect);
  }

  /**
   * Paint outlined with background.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintOutlinedWithBackground(Graphics2D g2, Rectangle rect) {
    paintOutlinedBackgroundBase(g2, rect);
    drawOutline(g2, rect);
  }

  /**
   * Paint outlined with background.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintOutlinedWithBackground(Graphics2D g2, IntRect rect) {
    paintOutlinedBackgroundBase(g2, rect);
    drawOutline(g2, rect);
  }

  /**
   * Paint outlined background base.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintOutlinedBackgroundBase(Graphics2D g2, Rectangle rect) {
    fill(g2, OUTLINE_BACKGROUND_COLOR, rect);
  }

  /**
   * Paint outlined background base.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintOutlinedBackgroundBase(Graphics2D g2, IntRect rect) {
    fill(g2, OUTLINE_BACKGROUND_COLOR, rect);
  }

  /**
   * Paint outlined base.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintOutlinedBase(Graphics2D g2, Rectangle rect) {
    fill(g2, BACKGROUND_COLOR, rect);
  }

  /**
   * Paint outlined base.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintOutlinedBase(Graphics2D g2, IntRect rect) {
    fill(g2, OUTLINE_BASE_COLOR, rect);
  }

  /**
   * Paint background base.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintBackgroundBase(Graphics2D g2, Rectangle rect) {
    fill(g2, Color.WHITE, rect);
  }

  /**
   * Paint background base.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintBackgroundBase(Graphics2D g2, IntRect rect) {
    fill(g2, Color.WHITE, rect);
  }

  /**
   * Paint dark outline.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintDarkOutline(Graphics2D g2, Rectangle rect) {
    drawBorder(g2, DARK_OUTLINE_COLOR, rect);
  }

  /**
   * Paint dark outline.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintDarkOutline(Graphics2D g2, IntRect rect) {
    drawBorder(g2, DARK_OUTLINE_COLOR, rect);
  }

  /**
   * Paints a widget to look unfocused.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintUnfocused(Graphics2D g2, Rectangle rect) {
    fill(g2, DIALOG_2_COLOR, rect);
  }

  /**
   * Paint unfocused.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintUnfocused(Graphics2D g2, IntRect rect) {
    fill(g2, DIALOG_2_COLOR, rect);
  }

  /**
   * Draws a component in the selected mode.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintSelected(Graphics2D g2, Rectangle rect) {
    paintSelectedBase(g2, rect);
    // paintSelectedBorder(g2, rect);
  }

  /**
   * Paint selected.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintSelected(Graphics2D g2, IntRect rect) {
    paintSelectedBase(g2, rect);
    // paintSelectedBorder(g2, rect);
  }

  /**
   * Paint selected base.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintSelectedBase(Graphics2D g2, Rectangle rect) {
    fill(g2, SELECTED_COLOR, rect);
  }

  /**
   * Paint selected base.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintSelectedBase(Graphics2D g2, IntRect rect) {
    fill(g2, SELECTED_COLOR, rect);
  }

  /**
   * Paint selected border.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintSelectedBorder(Graphics2D g2, Rectangle rect) {
    drawBorder(g2, SELECTED_BORDER_COLOR, rect);
  }

  /**
   * Paint selected border.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintSelectedBorder(Graphics2D g2, IntRect rect) {
    drawBorder(g2, SELECTED_BORDER_COLOR, rect);
  }

  /**
   * Paint theme selected.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintThemeSelected(Graphics2D g2, Rectangle rect) {
    paintThemeSelectedBase(g2, rect);
    // paintThemeSelectedBorder(g2, rect);
  }

  /**
   * Paint theme selected.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintThemeSelected(Graphics2D g2, IntRect rect) {
    paintThemeSelectedBase(g2, rect);
    // paintThemeSelectedBorder(g2, rect);
  }

  /**
   * Paint theme selected base.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintThemeSelectedBase(Graphics2D g2, Rectangle rect) {
    fill(g2, THEME_SELECTED_COLOR, rect);
  }

  /**
   * Paint theme selected base.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintThemeSelectedBase(Graphics2D g2, IntRect rect) {
    fill(g2, THEME_SELECTED_COLOR, rect);
  }

  /**
   * Paint theme selected border.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintThemeSelectedBorder(Graphics2D g2, Rectangle rect) {
    drawBorder(g2, THEME_SELECTED_BORDER_COLOR, rect);
  }

  /**
   * Paint theme selected border.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintThemeSelectedBorder(Graphics2D g2, IntRect rect) {
    drawBorder(g2, THEME_SELECTED_BORDER_COLOR, rect);
  }

  /**
   * Paint white background.
   *
   * @param g2   the g 2
   * @param rect the rect
   */
  public static void paintWhiteBackground(Graphics2D g2, IntRect rect) {
    fill(g2, Color.WHITE, rect);
  }

  /**
   * Paint pressed.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintPressed(Graphics2D g2, Rectangle rect) {
    paintHighlighted(g2, rect);
  }

  /**
   * Paint dialog button.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintDialogButton(Graphics2D g2, Rectangle rect) {
    paintDialogButtonBase(g2, rect);
    drawDialogButtonBorder(g2, rect);
  }

  /**
   * Paint dialog button.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintDialogButton(Graphics2D g2, IntRect rect) {
    paintDialogButtonBase(g2, rect);
    drawDialogButtonBorder(g2, rect);
  }

  /**
   * Paint dialog button focused.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintDialogButtonFocused(Graphics2D g2, Rectangle rect) {
    paintDialogButtonBase(g2, rect);
    drawHighlightedOutline(g2, rect);
  }

  /**
   * Paint dialog button pressed.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintDialogButtonPressed(Graphics2D g2, Rectangle rect) {
    paintHighlighted(g2, rect);
  }

  /**
   * Paint dialog button base.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintDialogButtonBase(Graphics2D g2, Rectangle rect) {
    // non highlight state

    /*
     * GradientPaint gradient = new GradientPaint(0, y,
     * ThemeService.getInstance().getColors().getHighlight(2), 0, y + h,
     * ThemeService.getInstance().getColors().getHighlight(3), false);
     * 
     * g2.setPaint(gradient);
     * 
     * g2.fillRect(x, y, w - 1, h);
     */

    fill(g2, DIALOG_BUTTON_BASE, rect);
  }

  /**
   * Paint dialog button base.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintDialogButtonBase(Graphics2D g2, IntRect rect) {
    fill(g2, DIALOG_BUTTON_BASE, rect);
  }

  /**
   * Draw dialog button border.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void drawDialogButtonBorder(Graphics2D g2, Rectangle rect) {
    drawBorder(g2, DIALOG_BUTTON_BORDER_COLOR, rect);
  }

  /**
   * Draw dialog button border.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void drawDialogButtonBorder(Graphics2D g2, IntRect rect) {
    drawBorder(g2, DIALOG_BUTTON_BORDER_COLOR, rect);
  }

  /**
   * Paint dialog button highlight base.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintDialogButtonHighlightBase(Graphics2D g2, Rectangle rect) {
    paintHighlightedBase(g2, rect);
  }

  /**
   * Paint dialog button pressed base.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void paintDialogButtonPressedBase(Graphics2D g2, Rectangle rect) {
    paintDialogButtonBase(g2, rect);
  }

  /**
   * Draw highlighted outline.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void drawHighlightedOutline(Graphics2D g2, Rectangle rect) {
    drawBorder(g2, OUTLINE_HIGHLIGHT_COLOR, rect);
  }

  /**
   * Draw highlighted outline.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void drawHighlightedOutline(Graphics2D g2, IntRect rect) {
    drawBorder(g2, OUTLINE_HIGHLIGHT_COLOR, rect);
  }

  /**
   * Draw outline.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void drawOutline(Graphics2D g2, IntRect rect) {
    drawBorder(g2, LIGHT_LINE_COLOR, rect);
  }

  /**
   * Draw white outline.
   *
   * @param g2   the g2
   * @param rect the rect
   */
  public static void drawWhiteOutline(Graphics2D g2, IntRect rect) {
    drawBorder(g2, Color.WHITE, rect);
  }

  /**
   * Paint image.
   *
   * @param component the component
   * @param g2        the g2
   * @param icon      the icon
   * @param rect      the rect
   */
  public static void paintImage(ModernWidget component, Graphics2D g2, ModernIcon icon, Rectangle rect) {
    icon.drawIcon(g2, rect.x + (rect.width - 16) / 2, rect.y + (rect.height - 16) / 2, 16);
  }

  /**
   * Gets the text y pos center.
   *
   * @param g2   the g2
   * @param rect the rect
   * @return the text y pos center
   */
  public static int getTextYPosCenter(Graphics2D g2, IntRect rect) {
    return getTextYPosCenter(g2, rect.getH());
  }

  /**
   * Gets the text Y pos center.
   *
   * @param height the height
   * @return the text Y pos center
   */
  public static int getTextYPosCenter(int height) {
    return getTextYPosCenter(DEFAULT_GRAPHICS, height);
  }

  /**
   * Returns the y position to draw text at so that is vertically centered on the
   * widget.
   *
   * @param g2     the g2
   * @param height the height
   * @return the text y pos center
   */
  public static int getTextYPosCenter(Graphics2D g2, int height) {
    return getTextYPosCenter(height, g2.getFontMetrics().getAscent(), g2.getFontMetrics().getDescent());
  }

  /**
   * Gets the text Y pos center.
   *
   * @param height  the height
   * @param ascent  the ascent
   * @param descent the descent
   * @return the text Y pos center
   */
  public static int getTextYPosCenter(int height, double ascent, double descent) {
    return (int) ((height + ascent - descent) / 2);
  }

  /**
   * Gets the text X pos center.
   *
   * @param text  the text
   * @param width the width
   * @return the text X pos center
   */
  public static int getTextXPosCenter(String text, int width) {
    return getTextXPosCenter(DEFAULT_GRAPHICS, text, width);
  }

  /**
   * Returns the x position such that a call to drawString(x...) will render the
   * text in the center of a block of a given width.
   *
   * @param g2    the g 2
   * @param text  the text
   * @param width the width
   * @return the text X pos center
   */
  public static int getTextXPosCenter(Graphics2D g2, String text, int width) {
    return (width - g2.getFontMetrics().stringWidth(text)) / 2;
  }

  /**
   * Center text.
   *
   * @param g2   the g 2
   * @param text the text
   * @param x    the x
   * @param y    the y
   * @return the point
   */
  public static Point centerText(Graphics2D g2, String text, int x, int y) {
    return new Point(centerTextAboutX(g2, text, x), centerTextAboutY(g2, y));
  }

  /**
   * Center text about x.
   *
   * @param g2   the g2
   * @param text the text
   * @param x    the x
   * @return the int
   */
  public static int centerTextAboutX(Graphics2D g2, String text, int x) {
    return x - g2.getFontMetrics().stringWidth(text) / 2;
  }

  /**
   * Center text about y.
   *
   * @param g2 the g2
   * @param y  the y
   * @return the int
   */
  public static int centerTextAboutY(Graphics2D g2, int y) {
    return y + (g2.getFontMetrics().getAscent() - g2.getFontMetrics().getDescent()) / 2;
  }

  /**
   * Returns a truncated version of a string to accomodate narrow nodes.
   *
   * @param g2    the g2
   * @param text  the text
   * @param width the width
   * @return the truncated text
   */
  public static String getTruncatedText(Graphics2D g2, String text, int width) {
    return getTruncatedText(g2, text, 0, width);
  }

  /**
   * Returns a truncated version of a string to accomodate narrow nodes.
   *
   * @param g2     the g2
   * @param text   the text
   * @param offset the offset
   * @param width  the width
   * @return the truncated text
   */
  public static String getTruncatedText(Graphics2D g2, String text, int offset, int width) {
    int textWidth = g2.getFontMetrics().stringWidth(text) + offset;

    if (textWidth <= width) {
      return text;
    }

    for (int i = 0; i < text.length(); ++i) {
      String t = text.substring(0, text.length() - i - 1) + "...";

      textWidth = g2.getFontMetrics().stringWidth(t) + offset;

      if (textWidth < width) {
        return t;
      }
    }

    return "";
  }

  /**
   * For a given getRect(), string and graphics context, return the coordinates
   * where to plot the string so it appears in the center of the getRect().
   *
   * @param g2   the g2
   * @param rect the rect
   * @param text the text
   * @return the string center plot coordinates
   */
  public static Point getStringCenterPlotCoordinates(Graphics2D g2, Rectangle rect, String text) {
    return getStringCenterPlotCoordinates(g2, rect.width, rect.height, text);
  }

  /**
   * Gets the string center plot coordinates.
   *
   * @param g2   the g2
   * @param rect the rect
   * @param text the text
   * @return the string center plot coordinates
   */
  public static Point getStringCenterPlotCoordinates(Graphics2D g2, IntRect rect, String text) {
    return getStringCenterPlotCoordinates(g2, rect.getW(), rect.getH(), text);
  }

  /**
   * Gets the string center plot coordinates.
   *
   * @param g2   the g2
   * @param w    the w
   * @param h    the h
   * @param text the text
   * @return the string center plot coordinates
   */
  public static Point getStringCenterPlotCoordinates(Graphics2D g2, int w, int h, String text) {
    Rectangle stringBounds = g2.getFontMetrics().getStringBounds(text, g2).getBounds();

    return new Point((w - stringBounds.width) / 2, ModernWidget.getTextYPosCenter(g2, h));
  }

  /**
   * Left align text x.
   *
   * @param g2   the g2
   * @param text the text
   * @param x    the x
   * @return the int
   */
  public static int leftAlignTextX(Graphics2D g2, String text, int x) {
    return x - g2.getFontMetrics().stringWidth(text);
  }

  /**
   * Create a default graphics context from an image so we can measure things etc
   * outside of a drawing event.
   *
   * @return the default graphics
   */
  public static Graphics2D getDefaultGraphics() {
    return DEFAULT_GRAPHICS;
  }

  /**
   * Gets the string width.
   *
   * @param text the text
   * @return the string width
   */
  public static int getStringWidth(String text) {
    return getStringWidth(DEFAULT_GRAPHICS, text);
  }

  /**
   * Gets the string width based on the graphics context.
   *
   * @param g2   the g2
   * @param text the text
   * @return the string width
   */
  public static int getStringWidth(Graphics2D g2, String text) {
    return getStringWidth(g2.getFont(), text);
  }

  /**
   * Gets the string width.
   *
   * @param font the font
   * @param text the text
   * @return the string width
   */
  public static int getStringWidth(Font font, String text) {
    if (text == null) {
      return 0;
    }

    Graphics g2 = DEFAULT_GRAPHICS.create();

    int w = 0;

    try {
      g2.setFont(font);

      w = g2.getFontMetrics().stringWidth(text);
    } finally {
      g2.dispose();
    }

    return w;
  }

  /**
   * Gets the string height.
   *
   * @return the string height
   */
  public static int getStringHeight() {
    return getStringHeight(FONT);
  }

  /**
   * Gets the string height.
   *
   * @param g2 the g2
   * @return the string height
   */
  public static int getStringHeight(Graphics2D g2) {
    return getStringHeight(g2.getFont());
  }

  /**
   * Gets the string height.
   *
   * @param font the font
   * @return the string height
   */
  public static int getStringHeight(Font font) {
    Graphics g2 = DEFAULT_GRAPHICS.create();

    g2.setFont(font);

    int h = g2.getFontMetrics().getAscent() + g2.getFontMetrics().getDescent();

    g2.dispose();

    return h;
  }

  /**
   * Gets the widget height.
   *
   * @return the widget height
   */
  public static int getWidgetHeight() {
    return getWidgetHeight(DEFAULT_GRAPHICS);
  }

  /**
   * Gets the widget height.
   *
   * @param g2 the g2
   * @return the widget height
   */
  public static int getWidgetHeight(Graphics2D g2) {
    return getWidgetHeight(g2.getFont());
  }

  /**
   * Returns the default height of a widget based on the font.
   *
   * @param font the font
   * @return the widget height
   */
  public static int getWidgetHeight(Font font) {
    return Math.max(getStringHeight(font) + DOUBLE_PADDING, MIN_WIDGET_HEIGHT);
  }

  /**
   * Truncate.
   *
   * @param text     the text
   * @param maxWidth the max width
   * @return the string
   */
  public static String truncate(String text, int maxWidth) {
    return truncate(FONT, text, maxWidth);
  }

  /**
   * Truncate.
   *
   * @param g2       the g 2
   * @param text     the text
   * @param maxWidth the max width
   * @return the string
   */
  public static String truncate(Graphics2D g2, String text, int maxWidth) {
    return truncate(g2.getFont(), text, maxWidth);
  }

  /**
   * Truncate a string to fit into a max width in pixels based on the widget font.
   * 
   * @param font     The widget font.
   * @param text     The text to display.
   * @param maxWidth The max width of the space in the widget in pixels.
   * @return The truncated string with ellipsis.
   */
  public static String truncate(Font font, String text, int maxWidth) {
    int w = getStringWidth(font, text);

    if (w <= maxWidth) {
      return text;
    }

    String t;

    // Start from the end of the string and truncate back as we are
    // more likely to remove fewer chars before a solution is found than
    // if we start from the beginning of the string.
    for (int i = text.length(); i > 0; i--) {
      t = text.substring(0, i) + TextUtils.ELLIPSIS;

      w = getStringWidth(font, t);

      if (w <= maxWidth) {
        return t;
      }
    }

    return TextUtils.EMPTY_STRING;
  }

  /**
   * Draw icon centered.
   *
   * @param g2     the g 2
   * @param icon   the icon
   * @param widget the widget
   */
  protected static void drawIconCentered(Graphics2D g2, ModernIcon icon, ModernWidget widget) {
    drawIconCentered(g2, icon, widget.getRect());
  }

  /**
   * Draws the icon in the middle of the widget.
   *
   * @param g2   the g 2
   * @param icon the icon
   * @param rect the rect
   */
  protected static void drawIconCentered(Graphics2D g2, ModernIcon icon, IntRect rect) {
    icon.drawIcon(g2, (rect.getW() - icon.getWidth()) / 2, (rect.getH() - icon.getHeight()) / 2, icon.getWidth());
  }

  /**
   * Size a widget to a given width using the default widget height.
   *
   * @param c     the c
   * @param width the width
   * @return the component
   */
  public static Component setSize(JComponent c, int width) {
    return UI.setSize(c, width, WIDGET_HEIGHT);
  }

}