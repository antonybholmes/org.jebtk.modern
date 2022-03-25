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
package org.jebtk.modern.ribbon;

import java.awt.Component;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.border.Border;

import org.jebtk.core.NameGetter;
import org.jebtk.core.text.TextUtils;
import org.jebtk.modern.BorderService;
import org.jebtk.modern.UI;
import org.jebtk.modern.button.ModernButton;
import org.jebtk.modern.button.ModernClickWidget;
import org.jebtk.modern.graphics.icons.ModernIcon;
import org.jebtk.modern.text.ModernLabel;
import org.jebtk.modern.tooltip.ModernToolTipWidget;

// TODO: Auto-generated Javadoc
/**
 * Standard section that appears within a RibbonToolbar. It consists of the
 * delineated region containing buttons with a title displayed at the bottom to
 * indicate what the buttons in the section are for.
 *
 * @author Antony Holmes
 *
 */
public class RibbonSection extends ModernClickWidget implements IRibbonModeProperty, NameGetter {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  // public static final Color HIGHLIGHT_COLOR = new Color(0xf2f2f2);

  // public static final Color LINE_COLOR = new Color(0xe6e6e6);

  /**
   * The member title.
   */
  private String mTitle;

  /**
   * The height.
   */
  private int HEIGHT = 16;

  /** The large border. */
  private Border LARGE_BORDER = BorderService.getInstance().createBorder(2, 0, HEIGHT, 1);

  /** The small border. */
  private Border SMALL_BORDER = BorderService.getInstance().createBorder(0, PADDING, 0, PADDING);

  /** The Constant SEPARATOR_PADDING. */
  // public static final int SEPARATOR_PADDING = DOUBLE_PADDING;

  public static final int SEPARATOR_HEIGHT = 20;

  // private static final int SEPARATOR_PADDING = 2;

  /** The m mode. */
  private RibbonSize mMode;

  /** The m buttons. */
  private List<IRibbonModeProperty> mButtons = new ArrayList<IRibbonModeProperty>();

  /** The m ribbon. */
  protected Ribbon mRibbon;

  private ModernLabel mLabel = new RibbonSectionLabel(TextUtils.EMPTY_STRING);
  private Component mGap = UI.createHGap(10);

  /**
   * Instantiates a new ribbon section.
   *
   * @param ribbon the ribbon
   * @param title  the title
   */
  public RibbonSection(Ribbon ribbon, String title) {
    this(ribbon, title, false);
  }

  public RibbonSection(Ribbon ribbon, String title, boolean showLabel) {
    this(ribbon, title, ribbon.getRibbonSize(), showLabel);
  }

  /**
   * Instantiates a new ribbon section.
   *
   * @param ribbon the ribbon
   * @param title  the title
   * @param mode   the mode
   */
  public RibbonSection(Ribbon ribbon, String title, RibbonSize mode) {
    this(ribbon, title, mode, false);
  }

  public RibbonSection(Ribbon ribbon, String title, RibbonSize mode, boolean showLabel) {
    mRibbon = ribbon;
    mTitle = title;

    setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

    if (showLabel) {
      add(mLabel);
      add(mGap);

      mLabel.setText(getName());
    }

    setSize(mode);
  }

  /**
   * Adds the.
   *
   * @param components the components
   */
  public void add(Component... components) {
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
    if (c instanceof IRibbonModeProperty) {
      IRibbonModeProperty b = (IRibbonModeProperty) c;

      b.setSize(mMode);

      mButtons.add(b);
    }

    if (c instanceof ModernToolTipWidget) {
      // Tell component to forward events to us
      ((ModernToolTipWidget) c).setToolTipDest(mRibbon);
    }

    return super.add(c);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.common.ui.ribbon.RibbonModeProperty#setSize(org.abh.common.ui.
   * ribbon. RibbonSize)
   */
  public void setSize(RibbonSize mode) {
    mMode = mode;

    if (mode == RibbonSize.LARGE) {
      setBorder(LARGE_BORDER);
    } else {
      setBorder(SMALL_BORDER);
    }

    mLabel.setVisible(mode == RibbonSize.COMPACT);
    mGap.setVisible(mode == RibbonSize.COMPACT);

    for (IRibbonModeProperty c : mButtons) {
      c.setSize(mode);
    }

    revalidate();
    repaint();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#getName()
   */
  @Override
  public String getName() {
    return mTitle;
  }

  /*
   * @Override public final Component add(Component c) { add(c);
   * 
   * return c; }
   * 
   * public final void setContent(Component component) { setBody(component);
   * 
   * Ui.setSize(this, new Dimension(component.getPreferredSize().width,
   * Ribbon.CONTENT_HEIGHT)); }
   */

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.abh.common.ui.widget.ModernWidget#drawBackground(java.awt.Graphics2D)
   */
  @Override
  public void drawBackground(Graphics2D g2) {
    g2.setColor(Ribbon.SEPARATOR_COLOR);

    int x = mRect.getW() - 1;
    int y = (mRect.getH() - SEPARATOR_HEIGHT) / 2;

    g2.drawLine(x, y, x, y + SEPARATOR_HEIGHT);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.abh.lib.ui.modern.ModernWidget#drawForegroundAA(java.awt.Graphics2D)
   */
  @Override
  public void drawForegroundAA(Graphics2D g2) {
    /*
     * GradientPaint gradient;
     * 
     * if (over) { int y = mRect.getH() - HEIGHT / 2;
     * 
     * gradient = new GradientPaint(0, y, Color.WHITE, 0, mRect.getH(),
     * HIGHLIGHT_COLOR, false);
     * 
     * g2.setPaint(gradient);
     * 
     * g2.fillRect(0, y, getWidth(), HEIGHT); }
     */

    /*
     * gradient = new GradientPaint(0, 0, Color.WHITE, 0, mRect.getH(), LINE_COLOR,
     * false);
     */

    if (mMode == RibbonSize.LARGE) {
      g2.setColor(ALT_TEXT_COLOR);

      String text = getTruncatedText(g2, mTitle, 0, mRect.getW());

      int x = (mRect.getW() - g2.getFontMetrics().stringWidth(text)) / 2;

      int y = mRect.getH() - PADDING;

      g2.drawString(text, x, y);
    }
  }

  /**
   * Add a separator between subsections.
   */
  public void addSeparator() {
    add(new RibbonSubSectionSeparator());
  }

  public ModernButton createButton(String text, ModernIcon icon) {
    ModernButton button = new RibbonLargeButton(text, icon);

    add(button);

    return button;
  }

  public ModernButton createButton(ModernIcon icon) {
    ModernButton button = new RibbonLargeButton(icon);

    add(button);

    return button;
  }
}
