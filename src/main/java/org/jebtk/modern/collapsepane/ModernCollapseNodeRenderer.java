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

import java.awt.Graphics2D;

import org.jebtk.core.geom.IntRect;
import org.jebtk.modern.ModernWidget;
import org.jebtk.modern.graphics.icons.CheveronDownVectorIcon;
import org.jebtk.modern.graphics.icons.CheveronUpVectorIcon;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.graphics.icons.RasterIcon;
import org.jebtk.modern.theme.MaterialService;

// TODO: Auto-generated Javadoc
/**
 * Provides a rudimentary implementation of a node renderer that detects if is
 * selected and what the tree branching depth is. This forms the basis of
 * concrete implementations of renderers.
 *
 * @author Antony Holmes
 */
public class ModernCollapseNodeRenderer extends ModernWidget {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  static final int ICON_SIZE = 12;

  public static final ModernIcon BRANCH_OPEN_ICON = new RasterIcon(new CheveronUpVectorIcon(), ICON_SIZE); // new
  // TriangleDownVectorIcon());
  // //CheveronDownVectorIcon());

  /** The Constant BRANCH_CLOSED_ICON. */
  public static final ModernIcon BRANCH_CLOSED_ICON = new RasterIcon(new CheveronDownVectorIcon(), ICON_SIZE); // new
  // TriangleRightVectorIcon());

  /**
   * The member is highlighted.
   */
  protected boolean mIsHighlighted = false;

  /**
   * The member is selected.
   */
  protected boolean mIsSelected = false;

  /**
   * The member has focus.
   */
  protected boolean mHasFocus = false;

  /**
   * The member is expanded.
   */
  protected boolean mIsExpanded = false;

  /**
   * The member branch height.
   */
  // private int mBranchHeight = 32;

  /**
   * The member name.
   */
  protected String mName;

  // private FadeAnimation mRenderer;

  private int mIndex;

  // public ModernCollapseNodeRenderer() {
  // mRenderer = new FadeAnimation(this);

  // mRenderer.setFadeColor("fill", FILL_COLOR, FILL_COLOR_2);
  // }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawBackgroundAA(Graphics2D g2) {
    drawCollapseBar(g2, mName, mIsExpanded, mRect);
  }

  /**
   * Obtains a renderer for the tree node.
   *
   * @param name          the name
   * @param isHighlighted whether to highlight the node.
   * @param isSelected    whether the node is selected or not.
   * @param hasFocus      the has focus
   * @param isExpanded    the is expanded
   * @return the renderer
   * @Param hasFocus whether the tree has focus or not, as this can determine
   *        color.
   */
  public ModernCollapseNodeRenderer getRenderer(AbstractCollapsePane pane, String name, int index,
      boolean isHighlighted, boolean isSelected, boolean hasFocus, boolean isExpanded) {
    mName = name; // .toUpperCase();
    mIndex = index;
    mIsHighlighted = isHighlighted;
    mIsSelected = isSelected;
    mHasFocus = hasFocus;
    mIsExpanded = isExpanded;

    // CollapseHighlightAnimation a =
    // (CollapseHighlightAnimation)pane.getBackgroundAnimation();

    // if (mIsHighlighted) {
    // mRenderer.setStep(a.getCurrentStep());
    // } else {
    // mRenderer.reset();
    // }

    return this;
  }

  public void drawCollapseBar(Graphics2D g2, String name, boolean expanded, IntRect rect) {
    // if (mIsHighlighted) {
    // paintThemeHighlighted(g2, getRect());
    // }

    // if (mIsExpanded) {

    /*
     * GradientPaint gradColor = new GradientPaint(0, 0, COLLAPSE_FILL_COLOR_1, 0,
     * h, COLLAPSE_FILL_COLOR_2);
     * 
     * g2.setPaint(gradColor);
     */

    // g2.setColor(Color.WHITE); //mRenderer.getFadeColor("fill"));

    // ImageUtils.fillRect(g2, rect);

    int yt = rect.getY() + (rect.getH() - ICON_SIZE) / 2;

    int xt = getWidth() - ICON_SIZE - PADDING - getInsets().right;

    // if (expanded) {
    // BRANCH_OPEN_ICON.drawIcon(g2, xt, yt, ICON_SIZE);
    // } else {
    // BRANCH_CLOSED_ICON.drawIcon(g2, xt, yt, ICON_SIZE);
    // }

    yt = ModernWidget.getTextYPosCenter(g2, rect.getH());

    xt = rect.getX() + PADDING; // +
    // ModernTheme.getInstance().getClass("widget").getInt("padding");

    g2.setColor(ModernWidget.TEXT_COLOR);

    g2.setFont(MaterialService.getInstance().getFont("text")); // SUB_SUB_HEADING_FONT);

    g2.drawString(ModernWidget.getTruncatedText(g2, name, rect.getX(), rect.getW()), xt, yt);
  }
}